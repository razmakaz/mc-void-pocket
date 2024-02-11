package com.razmakaz.voidpocket.events;

import com.razmakaz.voidpocket.VoidPocket;
import com.razmakaz.voidpocket.item.custom.VoidPocketItem;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = VoidPocket.MOD_ID)
public class ModEventHandler {

    @SubscribeEvent
    public static void onItemPickup(EntityItemPickupEvent event) {
        Player player = event.getPlayer();
        ItemStack pickedUpItem = event.getItem().getItem();

        for (ItemStack stack : player.getInventory().items) {
            if (stack.getItem() instanceof VoidPocketItem) {
                CompoundTag tag = stack.getOrCreateTag();
                if (tag.contains("StoredItemName")) {
                    String storedItemName = tag.getString("StoredItemName");
                    String pickedUpItemName = pickedUpItem.getItem().getRegistryName().toString();

                    if (pickedUpItemName.equals(storedItemName)) {
                        player.sendMessage(new TextComponent("Didn't pick up " + pickedUpItemName), player.getUUID());
                        player.level.playSound(null, // Player - playing the sound to everyone if null, or just to the
                                                     // player if player is specified
                                player.getX(), player.getY(), player.getZ(), // The location of the sound
                                SoundEvents.ANCIENT_DEBRIS_STEP, // The sound that will be played
                                SoundSource.PLAYERS, // The source of the sound
                                0.5f, // Volume
                                0.5f); // Pitch

                        // Set the count of the picked up item to 0 to prevent it from being picked up
                        event.getItem().getItem().setCount(0);

                        // Mark the entity as dead to ensure it is removed from the world
                        event.getItem().discard();

                        // Cancel the event to prevent the item from being picked up
                        event.setCanceled(true);
                    }
                    return;
                }
            }
        }
    }
}
