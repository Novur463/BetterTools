package com.novur.bettertools.framework.tool.tools.aoe;

import com.novur.bettertools.BetterTools;
import com.novur.bettertools.framework.region.RegionType;
import com.novur.bettertools.framework.tool.tools.enums.ExpandedLinkedFaces;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

import java.util.HashSet;

public class AOEUtil {
    private final BetterTools betterTools;
    public AOEUtil(BetterTools betterTools) {
        this.betterTools = betterTools;
    }

    private HashSet<Block> getExpandedLayer(Block centre) {
        HashSet<Block> blocks = new HashSet<>();

        for(ExpandedLinkedFaces expandedLinkedFaces : ExpandedLinkedFaces.values()) {
            Block affectedBlock = centre.getRelative(expandedLinkedFaces.getLinkedFace());

            if (affectedBlock != null && !affectedBlock.getType().name().contains("AIR")) {
                if (betterTools.getRegionHandler().inRegion(RegionType.MINE, affectedBlock)) {

                    if (!betterTools.getCrateUtil().isIgnored(affectedBlock.getType())) {
                        blocks.add(affectedBlock);
                    }

                }
            }
        }

        return blocks;
    }

    public HashSet<Block> getSelection(Block centreBlock) {
        HashSet<Block> blockSelection = new HashSet<>();

        blockSelection.addAll(getExpandedLayer(centreBlock));
        blockSelection.addAll(getExpandedLayer(centreBlock.getRelative(BlockFace.UP)));
        blockSelection.addAll(getExpandedLayer(centreBlock.getRelative(BlockFace.DOWN)));

        blockSelection.removeIf(block -> !betterTools.getRegionHandler().inRegion(RegionType.MINE, block) || block.getType() == Material.AIR);

        return blockSelection;
    }
}
