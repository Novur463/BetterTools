package com.novur.bettertools.framework.tool.tools;

import com.novur.bettertools.BetterTools;
import com.novur.bettertools.framework.region.RegionType;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.HashSet;

public class LaserPickHandler {
    private final BetterTools betterTools;
    private int laserLength;
    public LaserPickHandler(BetterTools betterTools) {
        this.betterTools = betterTools;
        init();
    }

    private void init() {
        this.laserLength = betterTools.getConfig().getInt("items.laser.laser.range");
    }

    public void reload() {
        init();
    }

    public HashSet<Block> getBlocksLasered(Block centreBlock, Player player) {
        HashSet<Block> blocks = new HashSet<>();

        for(int count = 0; count < getLaserLength(); count++) {
            Block affected = getAffected(centreBlock, player, count);

            if(affected != null && affected.getType() != Material.AIR) {
                if(betterTools.getRegionHandler().inRegion(RegionType.MINE, affected)) {
                    blocks.add(affected);
                }
            }
        }

        return blocks;
    }

    private Block getAffected(Block centreBlock, Player player, int count) {
        Block block = null;
        Location playerLocation = player.getLocation(), blockLocation = centreBlock.getLocation();
        float yaw = playerLocation.getYaw(), pitch = playerLocation.getPitch();
        double x = blockLocation.getX(), y = blockLocation.getY(), z = blockLocation.getZ();

        if(pitch < 45.0F && pitch > -45.0F) {

            if(yaw >= 45.0F && yaw < 135.0F ||  yaw >= -315.0F && yaw < -225.0F) {
                block = new Location(blockLocation.getWorld(), x - count, y, z).getBlock();
            }

            else if(yaw >= 135.0F && yaw < 225.0F || yaw >= -255.0F && yaw < -135.0F) {
                block = new Location(blockLocation.getWorld(), x, y, z - count).getBlock();
            }

            else if(yaw >= 225.0F && yaw < 315.0F || yaw >= -135.0F && yaw < -45.0F) {
                block = new Location(blockLocation.getWorld(), x + count, y, z).getBlock();
            }

            else if(yaw >= 315.0F && yaw <= 360.0F || yaw >= 0.0F && yaw < 45.0F || yaw >= -45.0F && yaw < 0.0F || yaw >= 0.0F && yaw < -315.0F) {
                block = new Location(blockLocation.getWorld(), x, y, z + count).getBlock();
            } else {
                block = new Location(blockLocation.getWorld(), x, y, z).getBlock();
            }
        }

        else if(pitch <= -45.0F) {
            block = new Location(blockLocation.getWorld(), x, y + count, z).getBlock();
        }

        else if(pitch >= 45.0F) {
            block = new Location(blockLocation.getWorld(), x, y - count, z).getBlock();
        }

        return block;
    }

    public int getLaserLength() {
        return laserLength;
    }
}
