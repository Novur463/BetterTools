package com.novur.bettertools.framework.tool.tools;

import com.novur.bettertools.BetterTools;
import com.novur.bettertools.framework.region.RegionType;
import com.novur.bettertools.framework.tool.tools.enums.ExpandedLinkedFaces;
import com.novur.bettertools.framework.tool.tools.enums.LinkedFaces;
import org.bukkit.Location;
import org.bukkit.block.Block;

import java.util.HashSet;

public class LumberjackAxeHandler {
    private final BetterTools betterTools;
    public LumberjackAxeHandler(BetterTools betterTools) {
        this.betterTools = betterTools;
    }

    public HashSet<Block> getLumberBlocks(Block centre) {
        HashSet<Block> blocks = new HashSet<>();
        Location location = centre.getLocation();

        for(int i = location.getBlockY(); i < location.getWorld().getHighestBlockYAt(location.getBlockX(),location.getBlockZ()); i++) {


            if(isLog(location.getBlock())) {

                if(betterTools.getRegionHandler().inRegion(RegionType.LOG, location.getBlock())) {
                    blocks.add(location.getBlock());
                }

                blocks.addAll(getSingleLayer(location.getBlock()));
            }

            location = location.add(0,1,0);
        }

        return blocks;
    }

    public HashSet<Block> getSingleLayer(Block centre) {
        HashSet<Block> blocks = new HashSet<>();

        for(ExpandedLinkedFaces expandedLinkedFaces : ExpandedLinkedFaces.values()) {
            Block affectedBlock = centre.getRelative(expandedLinkedFaces.getLinkedFace());

            if(betterTools.getRegionHandler().inRegion(RegionType.LOG, affectedBlock)) {

                if(isLog(affectedBlock)) {
                    blocks.add(affectedBlock);
                }

            }
        }

        return blocks;
    }

    public boolean isLog(Block block) {
        switch(block.getType()) {
            case OAK_LOG:
            case BIRCH_LOG:
            case ACACIA_LOG:
            case JUNGLE_LOG:
            case SPRUCE_LOG:
            case DARK_OAK_LOG:
                return true;
            default:
                return false;
        }
    }
}
