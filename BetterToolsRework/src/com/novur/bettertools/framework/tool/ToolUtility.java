package com.novur.bettertools.framework.tool;

import com.novur.bettertools.BetterTools;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.inventory.ItemStack;

public class ToolUtility {
    private final BetterTools betterTools;
    public ToolUtility(BetterTools betterTools) {
        this.betterTools = betterTools;
    }

    public boolean is(ItemStack itemStack, Tools tools) {
        NBTItem nbtItem = new NBTItem(itemStack);

        return nbtItem.getString("donorTool").equalsIgnoreCase(tools.getNBTID());
    }

    public boolean isDonorItem(ItemStack itemStack) {
        NBTItem nbtItem = new NBTItem(itemStack);

        return nbtItem.hasKey("donorTool");
    }

    public Tools getFromItem(ItemStack itemStack) {
        if(isDonorItem(itemStack)) {

            NBTItem nbtItem = new NBTItem(itemStack);

            for(Tools tools : Tools.values()) {
                if(nbtItem.getString("donorTool").equalsIgnoreCase(tools.getNBTID())) {
                    return tools;
                }
            }

        }

        return null;
    }
}
