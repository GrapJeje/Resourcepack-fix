package eu.camonetwork.resourcepackfix;

import eu.camonetwork.resourcepackfix.Infrastructure.Data.Configs.DefaultConfig;
import eu.camonetwork.resourcepackfix.Infrastructure.Data.Configs.MessagesConfig;
import eu.camonetwork.resourcepackfix.Infrastructure.Helpers.ItemBuilder;
import eu.camonetwork.resourcepackfix.Infrastructure.Helpers.Text;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Map;

public class Manager {

    private final DefaultConfig defaultConfig;
    private final MessagesConfig messagesConfig;

    public Manager(DefaultConfig defaultConfig, MessagesConfig messagesConfig) {
        this.defaultConfig = defaultConfig;
        this.messagesConfig = messagesConfig;
    }

    public void checkAndApplyItemConfig(Player player) {
        ItemStack mainHandItem = player.getInventory().getItemInMainHand();
        if (mainHandItem == null || mainHandItem.getType() == Material.AIR) {
            player.sendMessage(Text.colorize(messagesConfig.noItemsInMainHand()));
            return;
        }

        List<Map<String, Object>> items = defaultConfig.getItems();
        if (items == null) {
            player.sendMessage(Text.colorize(messagesConfig.noItemsFoundInConfigError()));
            return;
        }

        ItemMeta originalItemMeta = mainHandItem.getItemMeta();
        if (originalItemMeta == null) {
            player.sendMessage(Text.colorize(messagesConfig.invalidItem()));
            return;
        }

        if (!mainHandItem.getItemMeta().hasDisplayName()) {
            player.sendMessage(Text.colorize(messagesConfig.noDisplayname()));
            return;
        }

        String originalDisplayName = originalItemMeta.hasDisplayName() ? originalItemMeta.getDisplayName() : null;
        int originalAmount = mainHandItem.getAmount();

        for (Map<String, Object> itemConfig : items) {
            if (itemConfig == null) continue;

            String itemName = (String) itemConfig.get("name");
            String itemMaterial = (String) itemConfig.get("material");
            Integer itemCustomModelData = (Integer) itemConfig.get("custommodeldata");

            if (itemName == null || itemMaterial == null || itemCustomModelData == null) {
                player.sendMessage(Text.colorize(messagesConfig.invalidConfigError()));
                continue;
            }

            if (!Text.equalsIgnoreColorAndFormatting(originalDisplayName, itemName) &&
                    !mainHandItem.getType().name().equals(itemMaterial)) {
                continue;
            }

            if (originalItemMeta.hasCustomModelData() && originalItemMeta.getCustomModelData() == itemCustomModelData) {
                player.sendMessage(Text.colorize(messagesConfig.itemAlreadyHaveCmd()));
                return;
            }

            ItemBuilder itemBuilder = ItemBuilder.fromItemStack(mainHandItem)
                    .setCustomModelData(itemCustomModelData);

            ItemStack updatedItem = itemBuilder.getItem();
            updatedItem.setAmount(originalAmount);

            player.getInventory().setItemInMainHand(updatedItem);

            player.sendMessage(Text.colorize(messagesConfig.successful()));
            return;
        }

        player.sendMessage(Text.colorize(messagesConfig.noItemsFoundInConfig()));
    }
}
