package fr.tableikea.quantara.utils;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemBuilder {

    private ItemStack item;
    private ItemMeta itemM;

    public ItemBuilder() {
    }

    public ItemBuilder(Material material, int amont, String displayname, List<String> lore) {
        this.item = new ItemStack(material, amont);
        this.itemM = this.item.getItemMeta();
        this.itemM.setDisplayName(displayname);
        this.itemM.setLore(lore);
        this.item.setItemMeta(this.itemM);
    }

    public ItemBuilder(ItemStack itemStack, int amont, String displayname, List<String> lore) {
        this.item = itemStack;
        this.item.setAmount(amont);
        this.itemM = this.item.getItemMeta();
        this.itemM.setDisplayName(displayname);
        this.itemM.setLore(lore);
        this.item.setItemMeta(this.itemM);
    }

    public ItemBuilder(Material material, int amont, Component displayname, List<Component> lore) {
        this.item = new ItemStack(material, amont);
        this.itemM = this.item.getItemMeta();
        this.itemM.displayName(displayname);
        this.itemM.lore(lore);
        this.item.setItemMeta(this.itemM);
    }

    public ItemBuilder(ItemStack itemStack, int amont, Component displayname, List<Component> lore) {
        this.item = itemStack;
        this.item.setAmount(amont);
        this.itemM = this.item.getItemMeta();
        this.itemM.displayName(displayname);
        this.itemM.lore(lore);
        this.item.setItemMeta(this.itemM);
    }

    public ItemBuilder(Material material, int amont, String displayname) {
        this((Material)material, amont, (String)displayname, (List)null);
    }

    public ItemStack getItem() {
        return item;
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }

    public ItemMeta getItemM() {
        return itemM;
    }

    public void setItemM(ItemMeta itemM) {
        this.itemM = itemM;
    }

    public ItemStack toItemStack() {
        return this.item;
    }

}
