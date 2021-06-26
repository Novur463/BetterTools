package com.novur.bettertools.framework.tool.tools;

import com.novur.bettertools.BetterTools;
import com.novur.bettertools.framework.tool.tools.ore.Weights;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.HashSet;

public class BountifulPickHandler {
    private final BetterTools betterTools;
    public BountifulPickHandler(BetterTools betterTools) {
        this.betterTools = betterTools;
    }

    public Material getDropType(Material material) {
        switch(material) {
            case EMERALD_BLOCK:
                return Material.EMERALD_BLOCK;
            case ANCIENT_DEBRIS:
                return Material.ANCIENT_DEBRIS;
            case NETHERITE_BLOCK:
                return Material.NETHERITE_BLOCK;
            default:
                return Material.AIR;
        }
    }

    public boolean isResourceBlock(Block selectedBlock) {
        switch(selectedBlock.getType()) {
            case COAL_BLOCK:
            case IRON_BLOCK:
            case LAPIS_BLOCK:
            case REDSTONE_BLOCK:
            case GOLD_BLOCK:
            case DIAMOND_BLOCK:
            case EMERALD_BLOCK:
            case NETHERITE_BLOCK:
                return true;
            default:
                return false;
        }
    }

    public Material getBlockedResourceDrop(Block selectedBlock) {
        switch(selectedBlock.getType()) {
            case COAL_BLOCK:
                return Material.COAL_BLOCK;
            case IRON_BLOCK:
                return Material.IRON_BLOCK;
            case LAPIS_BLOCK:
                return Material.LAPIS_BLOCK;
            case REDSTONE_BLOCK:
                return Material.REDSTONE_BLOCK;
            case GOLD_BLOCK:
                return Material.GOLD_BLOCK;
            case DIAMOND_BLOCK:
                return Material.DIAMOND_BLOCK;
            case EMERALD_BLOCK:
                return Material.EMERALD_BLOCK;
            case NETHERITE_BLOCK:
                return Material.NETHERITE_BLOCK;
            default:
                return Material.AIR;
        }
    }

    public boolean hasOverridingOre(HashSet<Block> selectionSet, Block toCheck) {
        for(Block selection : selectionSet) {

            if(Weights.hasWeight(selection) && Weights.hasWeight(toCheck)) {

                if(Weights.getWeight(selection) > Weights.getWeight(toCheck)) {
                    return true;
                }

            }

        }

        return false;
    }

}
