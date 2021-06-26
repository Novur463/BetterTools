package com.novur.bettertools.framework.tool;

import com.novur.bettertools.framework.Util;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public enum Tools {
    SMELTERS_PICKAXE("Smelters Pickaxe", "smelter", "smelters", Util.asHash("smelters", "smelterspick", "smelterspickaxe")),
    BLOCK_PICKAXE("Block Pickaxe", "block", "block", Util.asHash("block", "blockpick", "blockpickaxe")),
    BOUNTIFUL_PICKAXE("Bountiful Pickaxe", "bountiful", "bountiful", Util.asHash("bountiful", "bountifulpick", "bountifulpickaxe")),
    EXPLOSIVE_PICKAXE("Explosive Pickaxe", "explosive", "explosive", Util.asHash("explosive", "explosivepick", "explosivepickaxe")),
    LASER_PICKAXE("Laser Pickaxe", "laser", "laser", Util.asHash("laser", "laserpick", "laserpickaxe")),
    LUMBERJACK_AXE("Lumberjack Axe", "lumberjack", "lumberjack", Util.asHash("lj", "lumberaxe", "lumberjack", "lumberjackaxe"));
    //LIGHTNING_SWORD("Lightning Sword", "lightningsword", "lightningSword", Util.asHash("lightning", "lightningsword", "lsword")),
    //POISON_BOW("Poison Bow", "poisonbow", "poisonBow", Util.asHash("poison", "poisonbow", "pbow"));

    String name, configID, nbtID;
    HashSet<String> aliases;
    private static Map<String, Tools> toolMap = new HashMap<>();

    static {
        for(Tools tools : values()) {
            for(String alias : tools.getAliases()) {
                toolMap.put(alias, tools);
            }
        }
    }

    Tools(String name, String configID, String nbtID, HashSet<String> aliases) {
        this.name = name;
        this.configID = configID;
        this.nbtID = nbtID;
        this.aliases = aliases;
    }

    public String getName() {
        return name;
    }

    public String getConfigID() {
        return configID;
    }

    public String getNBTID() {
        return nbtID;
    }

    public HashSet<String> getAliases() {
        return aliases;
    }

    public static boolean isRegistered(String string) {
        return toolMap.containsKey(string);
    }

    public static Tools index(String string) {
        return toolMap.get(string);
    }
}
