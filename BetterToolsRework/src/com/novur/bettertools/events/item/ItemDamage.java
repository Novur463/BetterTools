package com.novur.bettertools.events.item;

import com.novur.bettertools.BetterTools;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;

public class ItemDamage implements Listener {
    private final BetterTools betterTools;
    public ItemDamage(BetterTools betterTools) {
        this.betterTools = betterTools;
    }

    @EventHandler
    public void onDamage(PlayerItemDamageEvent event) {
        ItemStack itemStack = event.getItem();

        if(betterTools.getToolUtility().isDonorItem(itemStack)) {
            event.setDamage(0);
            event.setCancelled(true);
        }
    }

}
