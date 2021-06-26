package com.novur.bettertools.events.tool.bountiful;

import com.novur.bettertools.BetterTools;
import com.novur.bettertools.framework.item.ItemUtility;
import com.novur.bettertools.framework.region.RegionType;
import com.novur.bettertools.framework.string.StringUtil;
import com.novur.bettertools.framework.tool.Tools;
import com.novur.bettertools.framework.tool.tools.BountifulPickHandler;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;

public class BountifulPickaxe implements Listener {
    private final BetterTools betterTools;
    private StringUtil stringUtil;
    private Tools tools;
    private BountifulPickHandler bountifulPickHandler;
    private ItemUtility itemUtility;
    public BountifulPickaxe(BetterTools betterTools) {
        this.betterTools = betterTools;
        this.stringUtil = betterTools.getStringUtil();
        this.tools = Tools.BOUNTIFUL_PICKAXE;
        this.bountifulPickHandler = betterTools.getBountifulPickHandler();
        this.itemUtility = betterTools.getItemUtility();
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if(event.isCancelled()) return;

        ItemStack itemStack = event.getPlayer().getInventory().getItemInMainHand();

        if(itemStack == null || itemStack.getType() == Material.AIR) return;

        if(betterTools.getToolUtility().isDonorItem(itemStack)) {
            if (betterTools.getToolUtility().is(itemStack, Tools.BOUNTIFUL_PICKAXE)) {

                Block block = event.getBlock();

                if(betterTools.getRegionHandler().inRegion(RegionType.MINE, block)) {

                    HashSet<Block> selectionSet = betterTools.getAOEUtil().getSelection(block);

                    boolean fortuneApplied = false;
                    Material dropItemType = Material.AIR;
                    boolean itemHasSilk = itemStack.containsEnchantment(Enchantment.SILK_TOUCH);
                    boolean itemHasFortune = itemStack.containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS);

                    for(Block blockSelection : selectionSet) {

                        if(bountifulPickHandler.isResourceBlock(blockSelection)) {
                            if(!bountifulPickHandler.hasOverridingOre(selectionSet, blockSelection)) {
                                dropItemType = bountifulPickHandler.getBlockedResourceDrop(blockSelection);
                            }
                        }

                        if(blockSelection.getType() == Material.COAL_ORE) {
                            if(!bountifulPickHandler.hasOverridingOre(selectionSet, blockSelection)) {
                                if(itemHasSilk) {
                                    dropItemType = Material.COAL_ORE;
                                }
                                else{
                                    dropItemType = Material.COAL;
                                    if(itemHasFortune) fortuneApplied = true;
                                }
                            }
                        }

                        else if(blockSelection.getType() == Material.IRON_ORE) {
                            if(!bountifulPickHandler.hasOverridingOre(selectionSet, blockSelection)) {
                                dropItemType = Material.IRON_ORE;
                            }
                        }

                        else if(blockSelection.getType() == Material.LAPIS_ORE) {
                            if(!bountifulPickHandler.hasOverridingOre(selectionSet, blockSelection)) {
                                if(itemHasSilk) {
                                    dropItemType = Material.LAPIS_ORE;
                                }
                                else {
                                    dropItemType = Material.LAPIS_LAZULI;
                                    if(itemHasFortune) fortuneApplied = true;
                                }
                            }
                        }

                        else if(blockSelection.getType() == Material.REDSTONE_ORE) {
                            if(!bountifulPickHandler.hasOverridingOre(selectionSet, blockSelection)) {
                                if(itemHasSilk) {
                                    dropItemType = Material.REDSTONE_ORE;
                                }
                                else {
                                    dropItemType = Material.REDSTONE;
                                    if(itemHasFortune) fortuneApplied = true;
                                }
                            }
                        }

                        else if(blockSelection.getType() == Material.GOLD_ORE) {
                            if(!bountifulPickHandler.hasOverridingOre(selectionSet, blockSelection)) {
                                dropItemType = Material.GOLD_ORE;
                            }
                        }

                        else if(blockSelection.getType() == Material.DIAMOND_ORE) {
                            if(!bountifulPickHandler.hasOverridingOre(selectionSet, blockSelection)) {
                                if(itemHasSilk) {
                                    dropItemType = Material.DIAMOND_ORE;
                                }
                                else {
                                    dropItemType = Material.DIAMOND;
                                    if(itemHasFortune) fortuneApplied = true;
                                }
                            }
                        }

                        else if(blockSelection.getType() == Material.EMERALD_ORE) {
                            if(!bountifulPickHandler.hasOverridingOre(selectionSet, blockSelection)) {
                                if(itemHasSilk) {
                                    dropItemType = Material.EMERALD_ORE;
                                }
                                else {
                                    dropItemType = Material.EMERALD;
                                    if(itemHasFortune) fortuneApplied = true;
                                }
                            }
                        }

                        else if(blockSelection.getType() == Material.ANCIENT_DEBRIS) {
                            if(!bountifulPickHandler.hasOverridingOre(selectionSet, blockSelection)) {
                                dropItemType = Material.ANCIENT_DEBRIS;
                            }
                        }

                        else if(blockSelection.getType() == Material.ICE || blockSelection.getType() == Material.PACKED_ICE) {
                            if(!bountifulPickHandler.hasOverridingOre(selectionSet, blockSelection)) {
                                if(itemHasSilk) {
                                    block.breakNaturally(itemStack);
                                }

                                else {
                                    block.setType(Material.AIR);
                                }
                            }
                        }

                    }

                    if(dropItemType != null && dropItemType != Material.AIR) {
                        ItemStack itemToDrop = new ItemStack(dropItemType);

                        if(fortuneApplied) {
                            itemToDrop.setAmount(betterTools.getFortuneUtil().getBountifulDropCount(dropItemType, itemStack));
                        }

                        if(itemToDrop.getAmount() < 0) itemToDrop.setAmount(1);

                        event.setDropItems(false);
                        block.getWorld().dropItemNaturally(block.getLocation(), itemToDrop);

                        if(betterTools.getSoundHandler().hasProfile(tools)) {
                            betterTools.getSoundHandler().index(tools).play(event.getPlayer());
                        }

                        if(betterTools.getEffectHandler().hasProfile(tools)) {
                            betterTools.getSoundHandler().index(tools).play(event.getPlayer());
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
