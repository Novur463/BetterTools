package com.novur.bettertools.framework.tool.tools.enums;

import org.bukkit.Material;

public enum Blocks {
    COAL_ORE(Material.COAL_ORE, Material.COAL_BLOCK),
    IRON_ORE(Material.IRON_ORE, Material.IRON_BLOCK),
    GOLD_ORE(Material.GOLD_ORE, Material.GOLD_BLOCK),
    REDSTONE_ORE(Material.REDSTONE_ORE, Material.REDSTONE_BLOCK),
    LAPIS_ORE(Material.LAPIS_ORE, Material.LAPIS_BLOCK),
    EMERALD_ORE(Material.EMERALD_ORE, Material.EMERALD_BLOCK),
    DIAMOND_ORE(Material.DIAMOND_ORE, Material.DIAMOND_BLOCK),
    QUARTZ_ORE(Material.NETHER_QUARTZ_ORE, Material.QUARTZ_BLOCK);

    Material blockMaterial, itemMaterial;

    Blocks(Material blockMaterial, Material itemMaterial) {
        this.blockMaterial = blockMaterial;
        this.itemMaterial = itemMaterial;
    }

    public Material getBlockMaterial() {
        return blockMaterial;
    }

    public Material getItemMaterial() {
        return itemMaterial;
    }
}
