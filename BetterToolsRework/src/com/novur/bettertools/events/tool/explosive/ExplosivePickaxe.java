package com.novur.bettertools.events.tool.explosive;

import com.novur.bettertools.BetterTools;
import com.novur.bettertools.framework.object.DonatorTool;
import com.novur.bettertools.framework.region.RegionType;
import com.novur.bettertools.framework.tool.Tools;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;

public class ExplosivePickaxe implements Listener {
    private final BetterTools betterTools;
    private final Tools tools;
    public ExplosivePickaxe(BetterTools betterTools) {
        this.betterTools = betterTools;
        this.tools = Tools.EXPLOSIVE_PICKAXE;
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if(event.isCancelled()) return;

        ItemStack itemStack = event.getPlayer().getInventory().getItemInMainHand();

        if(itemStack == null || itemStack.getType() == Material.AIR) return;

        if (betterTools.getToolUtility().isDonorItem(itemStack)) {
            if(betterTools.getToolUtility().is(itemStack, tools)) {

                Block block = event.getBlock();

                if(betterTools.getRegionHandler().inRegion(RegionType.MINE, block)) {

                    HashSet<Block> selectionSet = betterTools.getAOEUtil().getSelection(block);

                    for(Block affectedBlocks : selectionSet) {

                        if(!betterTools.getCrateUtil().isIgnored(affectedBlocks.getType())) {
                            affectedBlocks.breakNaturally(itemStack);
                        }

                    }

                    if(betterTools.getSoundHandler().hasProfile(tools)) {
                        betterTools.getSoundHandler().index(tools).play(event.getPlayer());
                    }

                    if(betterTools.getEffectHandler().hasProfile(tools)) {
                        betterTools.getEffectHandler().index(tools).play(event.getPlayer());
                    }

                    if(betterTools.getGemHandler().shouldDiscover(itemStack, selectionSet)) {
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
