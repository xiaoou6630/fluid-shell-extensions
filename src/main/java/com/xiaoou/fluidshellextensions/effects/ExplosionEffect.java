package com.xiaoou.fluidshellextensions.effects;

import com.xiaoou.fluidshellextensions.config.EffectConfig;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ExplosionEffect {
    public static void execute(Level level, Vec3 pos, EffectConfig.ExplosionEffect config) {
        float power = config.getPower();
        boolean breakBlocks = config.isBreak_blocks();

        level.explode(
                null,  // 爆炸源
                pos.x(),
                pos.y(),
                pos.z(),
                power,
                breakBlocks,
                Level.ExplosionInteraction.TNT
        );
    }
}