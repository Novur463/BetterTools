package com.novur.bettertools.events.tool.block;

import com.novur.bettertools.BetterTools;
import com.novur.bettertools.framework.region.RegionType;
import com.novur.bettertools.framework.tool.Tools;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class BlockPickaxe implements Listener {
    private final BetterTools betterTools;
    public BlockPickaxe(BetterTools betterTools) {
        this.betterTools = betterTools;
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if(event.isCancelled()) return;

        ItemStack itemStack = event.getPlayer().getInventory().getItemInMainHand();

        if(itemStack == null || itemStack.getType() == Material.AIR) return;

        if (betterTools.getToolUtility().isDonorItem(itemStack)) {
            if (betterTools.getToolUtility().is(itemStack, Tools.BLOCK_PICKAXE)) {

                Block block = event.getBlock();

                if(betterTools.getRegionHandler().inRegion(RegionType.MINE, block)) {

                    if (betterTools.getBlockPickHandler().isBlockable(block)) {

                        ItemStack toDrop = betterTools.getBlockPickHandler().getItem(block);

                        if (betterTools.getExperienceUtil().isBlockEXPRetrieveable(block)) {
                            event.getPlayer().giveExp(betterTools.getExperienceUtil().getBlockedEXP(block));
                        }

                        event.setDropItems(false);
                        block.getLocation().getWorld().dropItemNaturally(block.getLocation(), toDrop);

                        if (betterTools.getSoundHandler().hasProfile(Tools.BLOCK_PICKAXE)) {
                            betterTools.getSoundHandler().index(Tools.BLOCK_PICKAXE).play(event.getPlayer());
                        }

                        if (betterTools.getEffectHandler().hasProfile(Tools.BLOCK_PICKAXE)) {
                            betterTools.getEffectHandler().index(Tools.BLOCK_PICKAXE).play(event.getPlayer());
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
