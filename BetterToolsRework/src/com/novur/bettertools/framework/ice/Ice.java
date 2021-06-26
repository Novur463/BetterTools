package com.novur.bettertools.framework.ice;

import com.novur.bettertools.BetterTools;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class Ice {
    private final BetterTools betterTools;
    public Ice(BetterTools betterTools) {
        this.betterTools = betterTools;
    }

    public boolean isIce(Block block) {
        return block.getType() == Material.ICE || block.getType() == Material.PACKED_ICE;
    }

    public void handle(Block block, ItemStack itemStack) {

        if(itemStack != null && itemStack.getType() != Material.AIR) {

            if(betterTools.getItemUtility().hasEnchantment(itemStack, Enchantment.SILK_TOUCH)) {
                block.breakNaturally(itemStack);
            }

            else {
                block.setType(Material.AIR);
            }

        }
    }
}
