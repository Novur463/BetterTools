package com.novur.bettertools.framework.crate;

import org.bukkit.Material;

public class CrateUtil {
    public CrateUtil() {}

    public boolean isIgnored(Material material) {
        return material == Material.ENDER_CHEST || material == Material.CHEST || material == Material.TRAPPED_CHEST;
    }
}
