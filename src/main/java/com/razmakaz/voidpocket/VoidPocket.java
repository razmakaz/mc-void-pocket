package com.razmakaz.voidpocket;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;
import com.razmakaz.voidpocket.item.ModItems;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(VoidPocket.MOD_ID)
public class VoidPocket {
    public static final String MOD_ID = "voidpocket";

    private static final Logger LOGGER = LogUtils.getLogger();

    public VoidPocket() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(eventBus);

        eventBus.addListener(this::setup);
    }

    public void setup(final FMLCommonSetupEvent event) {
        LOGGER.info("VoidPocket setup method registered");
    }

    public static String getRegistryName(String name) {
        return MOD_ID + ":" + name;
    }
}