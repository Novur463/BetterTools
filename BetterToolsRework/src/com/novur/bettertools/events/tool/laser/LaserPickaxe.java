package com.novur.bettertools.events.tool.laser;

import com.novur.bettertools.BetterTools;
import com.novur.bettertools.framework.crate.CrateUtil;
import com.novur.bettertools.framework.region.RegionType;
import com.novur.bettertools.framework.tool.Tools;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;

public class LaserPickaxe implements Listener {
    private final BetterTools betterTools;
    public LaserPickaxe(BetterTools betterTools) {
        this.betterTools = betterTools;
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if(event.isCancelled()) return;

        ItemStack itemStack = event.getPlayer().getInventory().getItemInMainHand();

        if(itemStack == null || itemStack.getType() == Material.AIR) return;

        if(betterTools.getToolUtility().isDonorItem(itemStack)) {
            if(betterTools.getToolUtility().is(itemStack, Tools.LASER_PICKAXE)) {

                Block block = event.getBlock();

                if(betterTools.getRegionHandler().inRegion(RegionType.MINE, block)) {

                    HashSet<Block> blockSet = betterTools.getLaserPickHandler().getBlocksLasered(block, event.getPlayer());

                    for(Block affectedBlocks : blockSet) {
                        if(!betterTools.getCrateUtil().isIgnored(affectedBlocks.getType())) affectedBlocks.breakNaturally(itemStack);
                    }

                    if(betterTools.getSoundHandler().hasProfile(Tools.LASER_PICKAXE)) {
                        betterTools.getSoundHandler().index(Tools.LASER_PICKAXE).play(event.getPlayer());
                    }

                    if(betterTools.getEffectHandler().hasProfile(Tools.LASER_PICKAXE)) {
                        betterTools.getEffectHandler().index(Tools.LASER_PICKAXE).play(event.getPlayer());
                    }

                    if(betterTools.getGemHandler().shouldDiscover(itemStack, blockSet)) {
                        betterTools.getGemHandler().giveGems(event.getPlayer());
                    }
                }

                if(event.getBlock().getType() == Material.ICE) {
                    betterTools.getIce().handle(block, itemStack);
                }
            }
        }
    }
}
