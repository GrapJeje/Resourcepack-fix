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

        for (Map<String, Object> itemConfig : items) {
            if (itemConfig == null) continue;

            String configName = (String) itemConfig.get("name");
            String configMaterial = (String) itemConfig.get("material");
            Integer configCustomModelData = (Integer) itemConfig.get("custommodeldata");

            if (configName == null || configMaterial == null || configCustomModelData == null) {
                player.sendMessage(Text.colorize(messagesConfig.invalidConfigError()));
                continue;
            }

            ItemMeta itemMeta = mainHandItem.getItemMeta();
            if (itemMeta != null && configName.equals(itemMeta.getDisplayName()) &&
                    mainHandItem.getType().name().equals(configMaterial)) {

                if (itemMeta.hasCustomModelData() && itemMeta.getCustomModelData() == configCustomModelData) {
                    player.sendMessage(Text.colorize(messagesConfig.itemAlreadyHaveCmd()));
                } else {
                    ItemBuilder itemBuilder = ItemBuilder.fromType(mainHandItem.getType())
                            .setCustomModelData(configCustomModelData);
                    player.getInventory().setItemInMainHand(itemBuilder.getItem());
                    player.sendMessage(Text.colorize(messagesConfig.successful()));
                }
                return;
            }
        }

        player.sendMessage(Text.colorize(messagesConfig.noItemsFoundInConfig()));
    }
}
