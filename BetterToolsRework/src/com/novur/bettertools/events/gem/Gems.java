package com.novur.bettertools.events.gem;

import com.novur.bettertools.BetterTools;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class Gems implements Listener {
    private final BetterTools betterTools;
    public Gems(BetterTools betterTools) {
        this.betterTools = betterTools;
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if(event.isCancelled()) return;

        ItemStack itemStack = event.getPlayer().getInventory().getItemInMainHand();

        if(itemStack == null || itemStack.getType() == Material.AIR) return;

        if(betterTools.getToolUtility().isDonorItem(itemStack)) return;

        if(betterTools.getGemHandler().shouldDiscover()) {
            betterTools.getGemHandler().giveGems(event.getPlayer());
        }
    }
}
