package com.novur.bettertools.framework.tool.tools.ore;

import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.HashMap;
import java.util.Map;

public enum Weights {
    ICE(Material.ICE, 0),
    PACKED_ICE(Material.PACKED_ICE, 0),
    COAL(Material.COAL_ORE, 1),
    COAL_BLOCK(Material.COAL_BLOCK,2),
    IRON(Material.IRON_ORE, 3),
    IRON_BLOCK(Material.IRON_BLOCK, 4),
    LAPIS(Material.LAPIS_ORE, 5),
    LAPIS_BLOCK(Material.LAPIS_BLOCK, 6),
    REDSTONE(Material.REDSTONE_ORE, 7),
    REDSTONE_BLOCK(Material.REDSTONE_BLOCK, 8),
    GOLD(Material.GOLD_ORE, 9),
    GOLD_BLOCK(Material.GOLD_BLOCK, 10),
    DIAMOND(Material.DIAMOND_ORE, 11),
    DIAMOND_BLOCK(Material.DIAMOND_BLOCK, 12),
    EMERALD(Material.EMERALD_ORE,13 ),
    EMERALD_BLOCK(Material.EMERALD_BLOCK, 14),
    ANCIENT_DEBRIS(Material.ANCIENT_DEBRIS, 15),
    NETHERITE_BLOCK(Material.NETHERITE_BLOCK, 16);

    Material blockMaterial;
    int weight;
    private static Map<Material, Integer> weightMap = new HashMap<>();

    Weights(Material blockMaterial, int weight) {
        this.blockMaterial = blockMaterial;
        this.weight = weight;
    }

    static {
        for(Weights weights : Weights.values()) {
            weightMap.put(weights.getBlockMaterial(), weights.getWeight());
        }
    }

    public static boolean hasWeight(Material material) {
        return weightMap.containsKey(material);
    }

    public static boolean hasWeight(Block block) {
        return weightMap.containsKey(block.getType());
    }

    public static int getWeight(Material material) {
        return weightMap.get(material);
    }

    public static int getWeight(Block block) {
        return weightMap.get(block.getType());
    }

    public Material getBlockMaterial() {
        return blockMaterial;
    }

    public int getWeight() {
        return weight;
    }
}
