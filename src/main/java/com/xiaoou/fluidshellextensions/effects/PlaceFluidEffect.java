package com.xiaoou.fluidshellextensions.effects;

import com.xiaoou.fluidshellextensions.FluidShellExtensionsMod;
import com.xiaoou.fluidshellextensions.config.EffectConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;

public class PlaceFluidEffect {
    public static void execute(Level level, Vec3 pos, EffectConfig.PlaceFluidEffect config, ResourceLocation fluid) {
        if (level.isClientSide) return;

        net.minecraft.world.level.material.Fluid fluidObj = ForgeRegistries.FLUIDS.getValue(fluid);
        if (fluidObj == null) {
            FluidShellExtensionsMod.LOGGER.warn("Fluid not found: {}", fluid);
            return;
        }

        BlockState fluidState = fluidObj.defaultFluidState().createLegacyBlock();
        int radius = config.getRadius();
        boolean checkAir = config.isCheck_air();

        for (int dx = -radius; dx <= radius; dx++) {
            for (int dz = -radius; dz <= radius; dz++) {
                BlockPos blockPos = new BlockPos(
                        (int) pos.x() + dx,
                        (int) pos.y(),
                        (int) pos.z() + dz
                );

                if (checkAir && !level.isEmptyBlock(blockPos)) continue;

                level.setBlockAndUpdate(blockPos, fluidState);
            }
        }
    }
}