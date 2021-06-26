package com.novur.bettertools.framework.tool.tools.enums;

import org.bukkit.Material;

public enum Smelter {
    STONE(Material.STONE, Material.STONE),
    IRON_ORE(Material.IRON_ORE, Material.IRON_INGOT),
    GOLD_ORE(Material.GOLD_ORE, Material.GOLD_INGOT),
    ANCIENT_DEBRIS(Material.ANCIENT_DEBRIS, Material.NETHERITE_SCRAP);

    Material blockMaterial, itemMaterial;

    Smelter(Material blockMaterial, Material itemMaterial) {
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
