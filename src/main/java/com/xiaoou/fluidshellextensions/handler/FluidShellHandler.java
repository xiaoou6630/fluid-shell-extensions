package com.xiaoou.fluidshellextensions.handler;

import com.xiaoou.fluidshellextensions.effects.EffectExecutor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import rbasamoyai.createbigcannons.munitions.big_cannon.fluid_shell.FluidBlobEffectRegistry;
import rbasamoyai.createbigcannons.munitions.big_cannon.fluid_shell.FluidBlobBurst;
import rbasamoyai.createbigcannons.munitions.big_cannon.fluid_shell.EndFluidStack;

@Mod.EventBusSubscriber(modid = "fluid_shell_extensions", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class FluidShellHandler {
    @SubscribeEvent
    public static void onFluidShellExplode(FluidBlobEffectRegistry.OnFluidShellExplode.Context context) {
        Level level = context.level();
        Vec3 pos = new Vec3(context.x(), context.y(), context.z());
        EndFluidStack fluidStack = context.fluid();
        ResourceLocation fluidId = fluidStack.getFluid().getRegistryName();
        int amount = fluidStack.getAmount();

        EffectExecutor.executeEffects(level, pos, fluidId, amount);
    }

    @SubscribeEvent
    public static void onFluidShellHitBlock(FluidBlobEffectRegistry.OnHitBlock.Context context) {
        Level level = context.level();
        Vec3 pos = context.result().getBlockPos().relative(context.result().getDirection()).getCenter();
        EndFluidStack fluidStack = context.fluid();
        ResourceLocation fluidId = fluidStack.getFluid().getRegistryName();
        int amount = fluidStack.getAmount();

        EffectExecutor.executeEffects(level, pos, fluidId, amount);
    }
}