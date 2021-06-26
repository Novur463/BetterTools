package com.novur.bettertools.framework.effect;

import com.novur.bettertools.BetterTools;
import com.novur.bettertools.framework.tool.Tools;
import org.bukkit.Effect;
import org.bukkit.entity.Player;

public class EffectProfile {
    private final Tools tools;
    private final BetterTools betterTools;
    private Effect effect;

    public EffectProfile(Tools tools, BetterTools betterTools) {
        this.tools = tools;
        this.betterTools = betterTools;

        if(isValidEffect(betterTools.getConfig().getString("effect." + tools.getConfigID() + ".effect"))) {
            this.effect = Effect.valueOf(betterTools.getConfig().getString("effect." + tools.getConfigID() + ".effect"));
        }
    }

    public void play(Player player) {
        player.playEffect(player.getLocation(), effect, 0);
    }

    private boolean isValidEffect(String string) {
        Effect effect;

        try {
            effect = Effect.valueOf(string);
            return true;
        } catch(IllegalArgumentException | NullPointerException ex) {
            betterTools.getLogger().severe("Effect '" + string + "' could not be parsed for Tool " + tools.getName());
        }

        return false;
    }


}
