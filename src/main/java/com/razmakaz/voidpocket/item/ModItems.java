package com.razmakaz.voidpocket.item;

import com.razmakaz.voidpocket.VoidPocket;
import com.razmakaz.voidpocket.item.custom.VoidPocketItem;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.eventbus.api.IEventBus;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
            VoidPocket.MOD_ID);

    public static final RegistryObject<Item> VOID_POCKET = ITEMS.register("voidpocket",
            () -> new VoidPocketItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC))); // Use your custom item

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
