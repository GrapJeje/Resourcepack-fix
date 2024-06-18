package eu.camonetwork.resourcepackfix.Infrastructure.Data.Configs;

public class MessagesConfig extends ConfigManager {
    public MessagesConfig() {
        this.setFileName("messages.yml");
    }

    public String getPrefix() {
        String prefix = getConfig().getString("prefix");
        return (prefix != null) ? prefix : "";
    }

    private String applyPrefix(String message) {
        String prefix = getPrefix();
        if (prefix.isEmpty()) {
            return message.replace("{PREFIX}", "");
        }
        return message.replace("{PREFIX}", prefix);
    }

    private String getMessageOrDefault(String key, String defaultValue) {
        String message = getConfig().getString(key);
        if (message == null || message.isEmpty()) {
            return defaultValue;
        }
        return applyPrefix(message);
    }

    public String noItemsInMainHand() {
        return getMessageOrDefault("noitemsinmainhand", "");
    }

    public String noItemsFoundInConfigError() {
        return getMessageOrDefault("noitemsfoundinconfigerror", "");
    }

    public String itemAlreadyHaveCmd() {
        return getMessageOrDefault("itemalreadyhavecmd", "");
    }

    public String successful() {
        return getMessageOrDefault("successful", "");
    }

    public String noItemsFoundInConfig() {
        return getMessageOrDefault("noitemsfoundinconfig", "");
    }

    public String noPermissions() {
        return getMessageOrDefault("nopermissions", "");
    }

    public String configReloaded() {
        return getMessageOrDefault("configreloaded", "");
    }

    public String foundNonSubCommands() {
        return getMessageOrDefault("foundnonsubcommands", "");
    }

    public String invalidConfigError() {
        return getMessageOrDefault("invalidconfigerror", "");
    }

    public String invalidItem() {
        return getMessageOrDefault("invaliditem", "");
    }

    public String noDisplayname() {
        return getMessageOrDefault("nodisplayname", "");
    }
}
