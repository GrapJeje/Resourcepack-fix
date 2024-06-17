package eu.camonetwork.resourcepackfix.Infrastructure.Data.Configs;

public class MessagesConfig extends ConfigManager{
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

    public String noItemsInMainHand() {
        String message = getConfig().getString("noitemsinmainhand");
        return applyPrefix(message);
    }

    public String noItemsFoundInConfigError() {
        String message = getConfig().getString("noitemsfoundinconfigerror");
        return applyPrefix(message);
    }

    public String itemAlreadyHaveCmd() {
        String message = getConfig().getString("itemalreadyhavecmd");
        return applyPrefix(message);
    }

    public String successful() {
        String message = getConfig().getString("successful");
        return applyPrefix(message);
    }

    public String noItemsFoundInConfig() {
        String message = getConfig().getString("noitemsfoundinconfig");
        return applyPrefix(message);
    }

    public String noPermissions() {
        String message = getConfig().getString("nopermissions");
        return applyPrefix(message);
    }

    public String configReloaded() {
        String message = getConfig().getString("configreloaded");
        return applyPrefix(message);
    }

    public String foundNonSubCommands() {
        String message = getConfig().getString("foundnonsubcommands");
        return applyPrefix(message);
    }

    public String invalidConfigError() {
        String message = getConfig().getString("invalidconfigerror");
        return applyPrefix(message);
    }
}
