package com.novur.bettertools.framework.item;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.stream.Collectors;

public class ItemBuilder {
    private ItemStack itemStack;
    private ItemMeta itemMeta;
    private Material material;
    private String displayName;
    private List<String> lore;
    private int amount;

    public ItemBuilder(Material material, String displayName, List<String> lore, int amount) {
        this.material = material;
        this.itemStack = new ItemStack(material);
        this.itemMeta = itemStack.getItemMeta();
        this.displayName = displayName;
        this.lore = lore;
        this.amount = amount;
    }

    //Getters
    public Material getMaterial() {
        return material;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public String getDisplayName() {
        return displayName;
    }

    public List<String> getLore() {
        return lore;
    }

    public int getAmount() {
        return amount;
    }

    public ItemMeta getItemMeta() {
        return itemMeta;
    }


    //Setters
    public void setMaterial(Material material) {
        this.material = material;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setLore(List<String> lore) {
        this.lore = lore;
    }

    /*public void setUnbreakable(boolean bool) {
        NBTItem nbtItem = new NBTItem(itemStack);
        if(bool) {
            nbtItem.setString("unbreakable", "true");
        }

        else {
            nbtItem.setString("unbreakable", "false");
        }
    }*/

    public void setAsDonorTool(boolean bool) {
        NBTItem nbtItem = new NBTItem(itemStack);
        nbtItem.setString("donorTool", "true");
    }

    //Build
    public ItemStack build() {
        itemMeta.setDisplayName(color(this.displayName));
        itemMeta.setLore(color(this.lore));
        itemStack.setItemMeta(this.itemMeta);

        itemStack.setAmount(this.amount);
        return itemStack;
    }


    //Misc
    private String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    private List<String> color(List<String> stringList) {
        return stringList.stream().map(this::color).collect(Collectors.toList());
    }
}
