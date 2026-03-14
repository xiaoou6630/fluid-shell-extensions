package net.xiaoou.chocolatecannon.effect;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.ForgeRegistries;
import rbasamoyai.createbigcannons.munitions.big_cannon.fluid_shell.FluidBlobEffectRegistry;

public class ChocolateEffectRegistry {
    public static void registerEffects() {
        Fluid chocolateFluid = ForgeRegistries.FLUIDS.getValue(new ResourceLocation("create", "chocolate"));
        if (chocolateFluid != null) {
            FluidBlobEffectRegistry.registerFluidShellExplosionEffect(chocolateFluid, ChocolateEffectHandler::onChocolateExplode);
            FluidBlobEffectRegistry.registerHitBlock(chocolateFluid, ChocolateEffectHandler::onChocolateHitBlock);
        }
        
        // Register Acid from Alex's Caves
        Fluid acidFluid = ForgeRegistries.FLUIDS.getValue(new ResourceLocation("alexscaves", "acid"));
        if (acidFluid != null) {
            FluidBlobEffectRegistry.registerFluidShellExplosionEffect(acidFluid, ChocolateEffectHandler::onAcidExplode);
            FluidBlobEffectRegistry.registerHitBlock(acidFluid, ChocolateEffectHandler::onAcidHitBlock);
        }
        
        // Register Purple Soda from Alex's Caves
        Fluid purpleSodaFluid = ForgeRegistries.FLUIDS.getValue(new ResourceLocation("alexscaves", "purple_soda"));
        if (purpleSodaFluid != null) {
            FluidBlobEffectRegistry.registerFluidShellExplosionEffect(purpleSodaFluid, ChocolateEffectHandler::onPurpleSodaExplode);
            FluidBlobEffectRegistry.registerHitBlock(purpleSodaFluid, ChocolateEffectHandler::onPurpleSodaHitBlock);
        }
    }
}
