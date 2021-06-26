package com.novur.bettertools.framework.tool.tools;

import com.novur.bettertools.BetterTools;
import com.novur.bettertools.framework.math.MathUtil;
import org.bukkit.entity.Player;

public class LightningSwordHandler {
    private final BetterTools betterTools;
    private float chance;
    private double damage;

    public LightningSwordHandler(BetterTools betterTools) {
        this.betterTools = betterTools;
        this.chance = (float) betterTools.getConfig().getDouble("tools.lightningsword.chance");
        this.damage = betterTools.getConfig().getDouble("tools.lightningsword.damage");
    }

    public float getChance() {
        return chance;
    }

    public double getDamage() {
        return damage;
    }

    public boolean shouldHit() {
        return MathUtil.getThreadRandom().nextFloat() < chance;
    }

    public void damage(Player player) {
        player.getWorld().strikeLightning(player.getLocation());
    }
}
