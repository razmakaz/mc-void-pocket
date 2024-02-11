package com.razmakaz.voidpocket.item.custom;

import java.util.List;

import javax.annotation.Nullable;

import com.mojang.logging.LogUtils;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class VoidPocketItem extends Item {

    protected String itemName;

    public VoidPocketItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand interactionHand) {
        if (!world.isClientSide && interactionHand == InteractionHand.MAIN_HAND) {

            ItemStack itemStack = player.getItemInHand(interactionHand);
            ItemStack offHandStack = player.getItemInHand(InteractionHand.OFF_HAND);

            CompoundTag tag = itemStack.getOrCreateTag();
            if (!offHandStack.isEmpty()) {
                // Store the item name in the NBT tag
                String itemName = offHandStack.getItem().getRegistryName().toString();
                player.sendMessage(new TextComponent("Voiding: " + itemName), player.getUUID());
                tag.putString("StoredItemName", itemName);
            } else {
                // If the off hand is empty, remove the stored item name from the NBT tag
                player.sendMessage(new TextComponent("Voiding nothing."), player.getUUID());
                tag.remove("StoredItemName");
            }
            itemStack.setTag(tag);
        }
        return InteractionResultHolder.success(player.getItemInHand(interactionHand));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        // TODO Auto-generated method stub
        super.appendHoverText(stack, level, components, flag);

        CompoundTag tag = stack.getTag();
        if (tag != null) {
            if (tag.contains("StoredItemName")) {
                String storedItemName = tag.getString("StoredItemName");
                components.add(new TextComponent("Voiding: " + storedItemName));
            }
        }
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        // TODO Auto-generated method stub
        CompoundTag tag = stack.getTag();
        return tag != null && tag.contains("StoredItemName");
    }

}
