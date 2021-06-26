package com.novur.bettertools.events.tool.lj;

import com.novur.bettertools.BetterTools;
import com.novur.bettertools.framework.region.RegionType;
import com.novur.bettertools.framework.tool.Tools;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class LumberjackAxe implements Listener {
    private final BetterTools betterTools;
    public LumberjackAxe(BetterTools betterTools) {
        this.betterTools = betterTools;
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if(event.isCancelled()) return;

        ItemStack itemStack = event.getPlayer().getInventory().getItemInMainHand();

        if(itemStack == null || itemStack.getType() == Material.AIR) return;

        if(betterTools.getToolUtility().isDonorItem(itemStack)) {
            if(betterTools.getToolUtility().is(itemStack, Tools.LUMBERJACK_AXE)) {

                Block block = event.getBlock();

                if(betterTools.getRegionHandler().inRegion(RegionType.LOG, block)) {

                    if(betterTools.getLumberjackAxeHandler().isLog(block)) {

                        for(Block affected : betterTools.getLumberjackAxeHandler().getLumberBlocks(block)) {
                            affected.breakNaturally(itemStack);
                        }

                        if(betterTools.getSoundHandler().hasProfile(Tools.LUMBERJACK_AXE)) {
                            betterTools.getSoundHandler().index(Tools.LUMBERJACK_AXE).play(event.getPlayer());
                        }

                        if(betterTools.getEffectHandler().hasProfile(Tools.LUMBERJACK_AXE)) {
                            betterTools.getEffectHandler().index(Tools.LUMBERJACK_AXE).play(event.getPlayer());
                        }

                    }

                    if(betterTools.getGemHandler().shouldDiscover()) {
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
