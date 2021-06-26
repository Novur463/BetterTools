package com.novur.bettertools.framework.tool.tools;

import com.novur.bettertools.framework.tool.tools.enums.Smelter;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.HashMap;
import java.util.Map;

public class SmelterPickHandler {
    private final Map<Material, Material> materialMap;
    public SmelterPickHandler() {
        this.materialMap = new HashMap<>();

        for(Smelter smelter : Smelter.values()) {
            materialMap.put(smelter.getBlockMaterial(), smelter.getItemMaterial());
        }
    }

    public Material getDrop(Block block) {
        return materialMap.get(block.getType());
    }

    public boolean canSmelt(Block block) {
         return materialMap.containsKey(block.getType());
    }

    public Map<Material, Material> getMaterialMap() {
        return materialMap;
    }
}
