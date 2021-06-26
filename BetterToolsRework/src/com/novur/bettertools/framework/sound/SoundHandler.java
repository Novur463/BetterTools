package com.novur.bettertools.framework.sound;

import com.novur.bettertools.BetterTools;
import com.novur.bettertools.framework.tool.Tools;

import java.util.HashMap;
import java.util.Map;

public class SoundHandler {
    private final BetterTools betterTools;
    private Map<Tools, SoundProfile> profileMap;

    public SoundHandler(BetterTools betterTools) {
        this.betterTools = betterTools;
        this.profileMap = new HashMap<>();

        init();
    }

    private void init() {
        for(Tools tools : Tools.values()) {
            if(hasSoundEnabled(tools)) {
                SoundProfile soundProfile = new SoundProfile(tools, betterTools);
                profileMap.put(tools,soundProfile);
            }
        }
    }

    public void reload() {
        profileMap.clear();
        init();
    }

    private boolean hasSoundEnabled(Tools tools) {
        return betterTools.getConfig().getBoolean("sounds." + tools.getConfigID() + ".play");
    }

    public boolean hasProfile(Tools tools) {
        return profileMap.containsKey(tools);
    }

    public SoundProfile index(Tools tools) {
        return profileMap.get(tools);
    }
}
