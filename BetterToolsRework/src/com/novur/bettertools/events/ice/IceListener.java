package com.novur.bettertools.events.ice;

import com.novur.bettertools.BetterTools;
import com.novur.bettertools.framework.ice.Ice;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class IceListener implements Listener {
    private final BetterTools betterTools;
    private Ice ice;
    public IceListener(BetterTools betterTools) {
        this.betterTools = betterTools;
        this.ice = betterTools.getIce();
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBreak(BlockBreakEvent event) {
        if(event.isCancelled()) return;

        ItemStack itemStack = event.getPlayer().getInventory().getItemInMainHand();
        Block block = event.getBlock();

        if (ice.isIce(event.getBlock())) {
            if (itemStack == null || itemStack.getType() == Material.AIR) {
                block.setType(Material.AIR);
            }

            else {
                if (!betterTools.getToolUtility().isDonorItem(itemStack)) {
                    boolean hasSilk = itemStack.containsEnchantment(Enchantment.SILK_TOUCH);
                    if (hasSilk) {
                        block.breakNaturally(itemStack);

                    } else {
                        if (block.getType() == Material.ICE) block.setType(Material.AIR);
                    }
                }
            }
        }
    }
}
