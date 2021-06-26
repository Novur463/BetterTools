package com.novur.bettertools.framework.math.experience;

import com.novur.bettertools.framework.math.MathUtil;
import org.bukkit.block.Block;

public class ExperienceUtil {

    public ExperienceUtil() {}

    public int getBlockedEXP(Block block) {
        int lowerBound = 0, upperBound = 0;

        switch(block.getType()) {
            case COAL_ORE:
                upperBound = 2;
                break;
            case REDSTONE_ORE:
                lowerBound = 1;
                upperBound = 5;
                break;
            case LAPIS_ORE:
            case NETHER_QUARTZ_ORE:
                lowerBound = 2;
                upperBound = 5;
                break;
            case DIAMOND_ORE:
            case EMERALD_ORE:
                lowerBound = 3;
                upperBound = 7;
                break;
        }

        return MathUtil.getThreadRandom().nextInt(lowerBound,upperBound + 1);
    }

    public boolean isBlockEXPRetrieveable(Block block) {
        switch(block.getType()) {
            case COAL_ORE:
            case REDSTONE_ORE:
            case LAPIS_ORE:
            case NETHER_QUARTZ_ORE:
            case DIAMOND_ORE:
            case EMERALD_ORE:
                return true;
        }

        return false;
    }

    public int getSmeltedEXP(Block block) {
        int exp = 0;

        switch(block.getType()) {
            case STONE:
                exp = 1;
                break;
            case IRON_ORE:
                exp = 2;
                break;
            case GOLD_ORE:
                exp = 3;
                break;
        }

        return exp;
    }

    public boolean isSmeltedEXPRetrieveable(Block block) {
        switch(block.getType()) {
            case STONE:
            case IRON_ORE:
            case GOLD_ORE:
                return true;
        }

        return false;
    }
}
