package com.novur.bettertools.events.tool.lightningsword;

import com.novur.bettertools.BetterTools;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class LightningSwordDamage implements Listener {
    private final BetterTools betterTools;
    public LightningSwordDamage(BetterTools betterTools) {
        this.betterTools = betterTools;
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if(event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();


        }
    }
}
