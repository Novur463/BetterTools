package com.novur.bettertools.events.tool.smelters;

import com.novur.bettertools.BetterTools;
import com.novur.bettertools.framework.region.RegionType;
import com.novur.bettertools.framework.tool.Tools;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class SmeltersPickaxe implements Listener {
    private final BetterTools betterTools;
    public SmeltersPickaxe(BetterTools betterTools) {
        this.betterTools = betterTools;
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if(event.isCancelled()) return;

        ItemStack itemStack = event.getPlayer().getInventory().getItemInMainHand();

        if(itemStack == null || itemStack.getType() == Material.AIR) return;

        if(betterTools.getToolUtility().isDonorItem(itemStack)) {
            if(betterTools.getToolUtility().is(itemStack, Tools.SMELTERS_PICKAXE)) {

                Block block = event.getBlock();

                if(betterTools.getRegionHandler().inRegion(RegionType.MINE, block)) {

                    if (betterTools.getSmelterPickHandler().canSmelt(block)) {

                        ItemStack toDrop = new ItemStack(betterTools.getSmelterPickHandler().getDrop(block));
                        if (isFortuneable(block)) {
                            int dropCount = betterTools.getFortuneUtil().getDropCount(block.getType(), itemStack);
                            if(block.getType() == Material.ANCIENT_DEBRIS) dropCount = dropCount / 2;
                            if (dropCount < 0) dropCount = 1;

                            toDrop.setAmount(dropCount);
                        }

                        if (toDrop.getAmount() == 0) toDrop.setAmount(1);
                        if(toDrop == null || toDrop.getType() == Material.AIR) return;

                        event.setDropItems(false);

                        block.getLocation().getWorld().dropItemNaturally(block.getLocation(), toDrop);

                        if (betterTools.getSoundHandler().hasProfile(Tools.SMELTERS_PICKAXE)) {
                            betterTools.getSoundHandler().index(Tools.SMELTERS_PICKAXE).play(event.getPlayer());
                        }

                        if (betterTools.getEffectHandler().hasProfile(Tools.SMELTERS_PICKAXE)) {
                            betterTools.getEffectHandler().index(Tools.SMELTERS_PICKAXE).play(event.getPlayer());
                        }

                        if (betterTools.getExperienceUtil().isSmeltedEXPRetrieveable(block)) {
                            event.getPlayer().giveExp(betterTools.getExperienceUtil().getSmeltedEXP(block));
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

    private boolean isFortuneable(Block block) {
        return block.getType() == Material.GOLD_ORE || block.getType() == Material.IRON_ORE || block.getType() == Material.ANCIENT_DEBRIS;
    }

}
