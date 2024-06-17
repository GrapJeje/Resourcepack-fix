package eu.camonetwork.resourcepackfix.Infrastructure.Helpers;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemBuilder {
    private ItemStack item = new ItemStack(Material.AIR);

    public static ItemBuilder fromType(Material type) {
        ItemBuilder itemBuilder = new ItemBuilder();
        itemBuilder.setType(type);
        return itemBuilder;
    }

    public Material getType() {
        return item.getType();
    }

    public ItemBuilder setType(Material type) {
        item.setType(type);

        return this;
    }

    public ItemStack getItem() {
        return this.item;
    }

    public String getDisplayName() {
        if (item.getItemMeta() == null) {
            return "";
        }
        return item.getItemMeta().getDisplayName();
    }

    public int getCustomModelData() {
        if (this.item.getItemMeta() == null) {
            return 0;
        }
        return this.item.getItemMeta().getCustomModelData();
    }

    public ItemBuilder setCustomModelData(int customModelData) {
        ItemMeta itemMeta = item.getItemMeta();

        if (itemMeta == null) {
            return this;
        }

        itemMeta.setCustomModelData(customModelData);
        item.setItemMeta(itemMeta);

        return this;
    }

}
