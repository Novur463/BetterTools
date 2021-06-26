package com.novur.bettertools.framework.tool.tools.enums;

import org.bukkit.block.BlockFace;

public enum LinkedFaces {
    NORTH(BlockFace.NORTH),
    EAST(BlockFace.EAST),
    SOUTH(BlockFace.SOUTH),
    WEST(BlockFace.WEST),
    SELF(BlockFace.SELF);

    BlockFace linkedFace;

    LinkedFaces(BlockFace linkedFace) {
        this.linkedFace = linkedFace;
    }

    public BlockFace getLinkedFace() {
        return linkedFace;
    }
}
