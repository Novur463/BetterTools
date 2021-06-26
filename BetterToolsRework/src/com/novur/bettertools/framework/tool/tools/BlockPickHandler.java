package com.novur.bettertools.framework.tool.tools;

import com.novur.bettertools.framework.tool.tools.enums.Blocks;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class BlockPickHandler {
    private final Map<Material, Material> materialMap;

    public BlockPickHandler() {
        materialMap = new HashMap<>();

        for(Blocks blocks : Blocks.values()) {
            materialMap.put(blocks.getBlockMaterial(), blocks.getItemMaterial());
        }
    }

    public Material getMaterial(Block block) {
        return materialMap.get(block.getType());
    }

    public ItemStack getItem(Block block) {
        return new ItemStack(materialMap.get(block.getType()));
    }

    public boolean isBlockable(Block block) {
        return materialMap.containsKey(block.getType());
    }

    public Map<Material, Material> getMaterialMap() {
        return materialMap;
    }
}
