package net.amethyst.reworkedtools.omegaconfig.gson;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import net.amethyst.reworkedtools.omegaconfig.api.Syncing;

public class SyncableExclusionStrategy implements ExclusionStrategy {

    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        return f.getAnnotations().stream().noneMatch(annotation -> annotation instanceof Syncing);
    }

    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        return false;
    }
}
