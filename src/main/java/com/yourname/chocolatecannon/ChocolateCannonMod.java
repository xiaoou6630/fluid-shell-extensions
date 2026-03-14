package net.xiaoou.chocolatecannon;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.xiaoou.chocolatecannon.effect.ChocolateEffectRegistry;
import net.xiaoou.chocolatecannon.compat.AcidCompat;
import net.xiaoou.chocolatecannon.compat.PurpleSodaCompat;

@Mod("chocolatecannon")
public class ChocolateCannonMod {
    public ChocolateCannonMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;
        
        ChocolateEffectRegistry.registerEffects();
        AcidCompat.init();
        PurpleSodaCompat.init();
        
        modEventBus.register(this);
        forgeEventBus.register(this);
    }
}
