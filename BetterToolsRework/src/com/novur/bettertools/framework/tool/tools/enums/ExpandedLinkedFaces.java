package com.novur.bettertools.framework.tool.tools.enums;

import org.bukkit.block.BlockFace;

public enum ExpandedLinkedFaces {
    NORTH(BlockFace.NORTH),
    NORTH_EAST(BlockFace.NORTH_EAST),
    EAST(BlockFace.EAST),
    SOUTH_EAST(BlockFace.SOUTH_EAST),
    SOUTH(BlockFace.SOUTH),
    SOUTH_WEST(BlockFace.SOUTH_WEST),
    WEST(BlockFace.WEST),
    NORTH_WEST(BlockFace.NORTH_WEST),
    SELF(BlockFace.SELF);

    BlockFace linkedFace;

    ExpandedLinkedFaces(BlockFace linkedFace) {
        this.linkedFace = linkedFace;
    }

    public BlockFace getLinkedFace() {
        return linkedFace;
    }
}
