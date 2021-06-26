package com.novur.bettertools.framework.math;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class FortuneUtil {

    public FortuneUtil() {}

    private int random(int bound) {
        return MathUtil.getThreadRandom().nextInt(bound);
    }

    private int a(Material material) {
        return material == Material.LAPIS_ORE ? 2 + (3) : 1;
    }

    private int b(Material material) {
        return material == Material.LAPIS_LAZULI ? 2 + (3) : 1;
    }

    public int getDropCount(Material material, ItemStack itemStack) {
        if(itemStack.containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS)) {
            int fortuneLevel = itemStack.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);

            if(fortuneLevel > 0) {
                int drops = random(fortuneLevel + 2) - 1;

                if(drops < 0) drops = 0;

                return a(material) * (drops + 1);
            }
        }

        return a(material);
    }

    public int getBountifulDropCount(Material material, ItemStack itemStack) {
        if(itemStack.containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS)) {
            int fortuneLevel = itemStack.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);

            if(fortuneLevel > 0) {
                int drops = random(fortuneLevel + 2) - 1;

                if(drops < 0) drops = 0;

                return b(material) * (drops + 1);
            }
        }

        return b(material);
    }

    public boolean isFortuneable(Block block) {
        switch(block.getType()) {
            case COAL_ORE:
            case LAPIS_ORE:
            case REDSTONE_ORE:
            case DIAMOND_ORE:
            case EMERALD_ORE:
            case ANCIENT_DEBRIS:
                return true;
            default:
                return false;
        }
    }
}
