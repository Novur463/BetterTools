package com.novur.bettertools.framework.item;

import com.novur.bettertools.BetterTools;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class ItemUtility {
    private final BetterTools betterTools;
    public ItemUtility(BetterTools betterTools) {
        this.betterTools = betterTools;
    }

    public boolean hasEnchantment(ItemStack itemStack, Enchantment enchantment) {
        return itemStack.containsEnchantment(enchantment);
    }

    public int getEnchantmentLevel(ItemStack itemStack, Enchantment enchantment) {
        return itemStack.getEnchantmentLevel(enchantment);
    }

    public boolean isUnbreakable(ItemStack itemStack) {
        NBTItem nbtItem = new NBTItem(itemStack);

        return nbtItem.getString("unbreakable").equalsIgnoreCase("true");
    }
}
