package com.novur.bettertools.framework.effect;

import com.novur.bettertools.BetterTools;
import com.novur.bettertools.framework.tool.Tools;

import java.util.HashMap;
import java.util.Map;

public class EffectHandler {
    private final BetterTools betterTools;
    private Map<Tools, EffectProfile> profileMap;

    public EffectHandler(BetterTools betterTools) {
        this.betterTools = betterTools;
        this.profileMap = new HashMap<>();

        init();
    }

    private void init() {
        for(Tools tools : Tools.values()) {
            if(hasEffectEnabled(tools)) {
                EffectProfile effectProfile = new EffectProfile(tools, betterTools);
                profileMap.put(tools,effectProfile);
            }
        }
    }

    public void reload() {
        profileMap.clear();
        init();
    }

    private boolean hasEffectEnabled(Tools tools) {
        return betterTools.getConfig().getBoolean("effect." + tools.getConfigID() + ".play");
    }

    public boolean hasProfile(Tools tools) {
        return profileMap.containsKey(tools);
    }

    public EffectProfile index(Tools tools) {
        return profileMap.get(tools);
    }
}
