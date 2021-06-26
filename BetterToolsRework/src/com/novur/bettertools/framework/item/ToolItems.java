package com.novur.bettertools.framework.item;

import com.novur.bettertools.BetterTools;
import com.novur.bettertools.framework.tool.Tools;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ToolItems {
    private final BetterTools betterTools;
    private Map<Tools, ItemStack> toolMap;

    public ToolItems(BetterTools betterTools) {
        this.betterTools = betterTools;
        this.toolMap = new HashMap<>();

        init();
    }

    private void init() {
        for(Tools tools : Tools.values()) {
            Material material;

            try {
                material = Material.matchMaterial(betterTools.getConfig().getString("items." + tools.getConfigID() + ".material"));
            } catch(IllegalArgumentException ex) {
                betterTools.getLogger().severe("ERROR! Material " + betterTools.getConfig().getString("items." + tools.getConfigID() + ".material") + " could not be parsed for Item " + tools.getName() + "! Defaulting to DIRT");
                material = Material.DIRT;
            }

            String displayName = betterTools.getConfig().getString("items." + tools.getConfigID() + ".name");
            List<String> lore = betterTools.getConfig().getStringList("items." + tools.getConfigID() + ".lore");

            ItemBuilder itemBuilder = new ItemBuilder(material, displayName, lore, 1);

            ItemStack itemStack = itemBuilder.build();
            NBTItem nbtItem = new NBTItem(itemStack);
            nbtItem.setString("donorTool", tools.getNBTID());

            if(betterTools.getConfig().getBoolean("items." + tools.getConfigID() + ".unbreakable")) {
                nbtItem.setString("unbreakable", "true");
            }

            ItemStack finalisedItem = nbtItem.getItem();

            toolMap.put(tools,finalisedItem);
        }
    }

    public void reload() {
        toolMap.clear();
        init();
    }

    public boolean isRegistered(Tools tools) {
        return toolMap.containsKey(tools) && toolMap.get(tools) != null;
    }

    public ItemStack index(Tools tools) {
        return toolMap.get(tools);
    }

}
