package eu.camonetwork.resourcepackfix.Infrastructure.Data.Configs;

import java.util.List;
import java.util.Map;

public class DefaultConfig extends ConfigManager{
    public DefaultConfig() {
        this.setFileName("config.yml");
    }

    public List<Map<String, Object>> getItems() {
        return (List<Map<String, Object>>) getConfig().getList("items");
    }

}
