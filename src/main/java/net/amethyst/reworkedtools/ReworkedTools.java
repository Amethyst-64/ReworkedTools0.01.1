package net.amethyst.reworkedtools;

import net.amethyst.reworkedtools.item.ModItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReworkedTools implements ModInitializer {
	public static final String MOD_ID = "reworkedtools";
	public static final Logger LOGGER = LoggerFactory.getLogger(ReworkedTools.MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
	}
}
