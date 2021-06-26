package com.novur.bettertools.framework.api;

import com.novur.bettertools.framework.tool.Tools;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class GemDiscoverEvent extends Event implements Cancellable {
    private final ItemStack itemInHand;
    private final Block block;
    private final Player player;
    private int gemAmount;
    private boolean isItemDonorTool;
    //private final Tools toolInHand;

    private boolean isCancelled;
    private static final HandlerList HANDLER_LIST = new HandlerList();

    public GemDiscoverEvent(Player player, ItemStack itemInHand, Block block, int gemAmount) {
        this.player = player;
        this.itemInHand = itemInHand;
        this.block = block;
        this.gemAmount = gemAmount;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    public ItemStack getItemInHand() {
        return itemInHand;
    }

    public Block getBlock() {
        return block;
    }

    public Player getPlayer() {
        return player;
    }

    public int getGemAmount() {
        return gemAmount;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }
}
