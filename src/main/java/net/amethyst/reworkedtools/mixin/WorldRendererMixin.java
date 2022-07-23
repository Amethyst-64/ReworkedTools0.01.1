package net.amethyst.reworkedtools.mixin;

import net.amethyst.reworkedtools.magna.api.MagnaTool;
import net.amethyst.reworkedtools.magna.api.event.ToolRadiusCallback;
import net.amethyst.reworkedtools.magna.config.MagnaConfig;
import net.amethyst.reworkedtools.magna.impl.AppendedObjectIterator;
import it.unimi.dsi.fastutil.longs.Long2ObjectLinkedOpenHashMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectMaps;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.BlockBreakingInfo;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

@Mixin(WorldRenderer.class)
@Environment(EnvType.CLIENT)
public class WorldRendererMixin {

    @Shadow @Final private MinecraftClient client;
    @Shadow private ClientWorld world;
    @Shadow @Final private Long2ObjectMap<SortedSet<BlockBreakingInfo>> blockBreakingProgressions;

    @Inject(at = @At("HEAD"), method = "drawBlockOutline", cancellable = true)
    private void drawBlockOutline(MatrixStack stack, VertexConsumer vertexConsumer, Entity entity, double d, double e, double f, BlockPos blockPos, BlockState blockState, CallbackInfo ci) {

        // ensure player is not null
        if(this.client.player == null) {
            return;
        }

        // ensure world is not null
        if(this.client.world == null) {
            return;
        }

        // show extended outline if the player is holding a magna tool
        ItemStack heldStack = this.client.player.getInventory().getMainHandStack();
        if (heldStack.getItem() instanceof MagnaTool tool) {

            // do not show extended outline if player is sneaking and the config option is enabled
                            // only show extended outline for block raytraces
                if (client.crosshairTarget instanceof BlockHitResult crosshairTarget) {
                    // if the tool should not render outlines, abort mission now
                    if(!tool.renderOutline(world, (BlockHitResult) client.crosshairTarget, client.player, heldStack)) {
                        return;
                    }

                    BlockPos crosshairPos = crosshairTarget.getBlockPos();
                    BlockState crosshairState = client.world.getBlockState(crosshairPos);

                    // ensure we are not looking at air or an invalid block
                    if (!crosshairState.isAir() && client.world.getWorldBorder().contains(crosshairPos) && tool.isBlockValidForBreaking(world, crosshairPos, heldStack)) {
                        int radius = ToolRadiusCallback.EVENT.invoker().getRadius(heldStack, tool.getRadius(heldStack));
                        List<BlockPos> positions = tool.getBlockFinder().findPositions(world, client.player, radius, tool.getDepth(heldStack));
                        List<VoxelShape> outlineShapes = new ArrayList<>();
                        outlineShapes.add(VoxelShapes.empty());

                        // assemble outline shape
                        for (BlockPos position : positions) {
                            if(!tool.isBlockValidForBreaking(world, position, heldStack)) {
                                continue;
                            }

                            BlockPos diffPos = position.subtract(crosshairPos);
                            BlockState offsetShape = world.getBlockState(position);

                            // if enableFull3x3 is 'true', all blocks will gain an outline, even if they are air
                            if (!offsetShape.isAir() ) {
                                // if fullBlockHitbox is 'true', all blocks will have a 16x16x16 hitbox regardless of their outline shape
                                        outlineShapes.add(offsetShape.getOutlineShape(world, position).offset(diffPos.getX(), diffPos.getY(), diffPos.getZ()));
                            }
                        }

                        outlineShapes.forEach(shape -> {
                            // draw extended hitbox
                            WorldRenderer.drawShapeOutline(
                                    stack,
                                    vertexConsumer,
                                    shape, // blockState.getOutlineShape(this.world, blockPos, ShapeContext.of(entity))
                                    (double) crosshairPos.getX() - d,
                                    (double) crosshairPos.getY() - e,
                                    (double) crosshairPos.getZ() - f,
                                    0.0F,
                                    0.0F,
                                    0.0F,
                                    0.4F);
                        });

                        // cancel 1x1 hitbox that would normally render
                        ci.cancel();
                    }
                }
            }
        }

    
    @ModifyVariable(method = "render",
                    at = @At(value = "INVOKE", target = "Lit/unimi/dsi/fastutil/objects/ObjectSet;iterator()Lit/unimi/dsi/fastutil/objects/ObjectIterator;",
                             shift = At.Shift.BY, by = 2), ordinal = 0)
    private ObjectIterator<Long2ObjectMap.Entry<SortedSet<BlockBreakingInfo>>> appendBlockBreakingProgressions(ObjectIterator<Long2ObjectMap.Entry<SortedSet<BlockBreakingInfo>>> originalIterator) {
        return new AppendedObjectIterator<>(originalIterator, getCurrentExtraBreakingInfos());
    }

    
    @Unique
    private Long2ObjectMap<BlockBreakingInfo> getCurrentExtraBreakingInfos() {
        assert client.player != null;

            ItemStack heldStack = this.client.player.getInventory().getMainHandStack();

        
        return Long2ObjectMaps.emptyMap();
    }
}