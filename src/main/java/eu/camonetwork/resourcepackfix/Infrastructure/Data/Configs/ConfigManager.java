package eu.camonetwork.resourcepackfix.Infrastructure.Data.Configs;

import eu.camonetwork.resourcepackfix.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.logging.Level;

public abstract class ConfigManager {
    private FileConfiguration customConfig;
    private File customConfigFile = null;
    private String subFolder;
    private String fileName;

    public void reload() {
        if (customConfigFile == null) {
            customConfigFile = new File(Main.instance.getDataFolder(), getFileName());
        }
        if (!customConfigFile.exists()) this.saveDefaultConfig();

        customConfig = YamlConfiguration.loadConfiguration(customConfigFile);

        Reader defConfigStream = new InputStreamReader(Objects.requireNonNull(Main.instance.getResource(getFileName())), StandardCharsets.UTF_8);
        YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
        customConfig.setDefaults(defConfig);
    }


    public FileConfiguration getConfig() {
        if (customConfig == null) {
            this.reload();
        }
        return customConfig;
    }

    public boolean save() {
        if (customConfig == null || customConfigFile == null) {
            return false;
        }
        try {
            getConfig().save(customConfigFile);
        } catch (IOException ex) {
            Main.instance.getLogger().log(Level.SEVERE, "Could not save config to " + customConfigFile, ex);
        }
        this.reload();
        return true;
    }

    public void saveDefaultConfig() {
        if (customConfigFile == null) {
            customConfigFile = new File(getFolder(), getFileName());
        }
        if (!customConfigFile.exists()) {
            Main.instance.saveResource(getFileName(), false);
        }
    }

    private File getFolder() {
        return new File(Main.instance.getDataFolder() + this.getSubFolder());
    }

    public String getFileName() {
        return this.getSubFolder() + fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSubFolder() {
        return (subFolder == null ? "" : subFolder + "/");
    }

    public void setSubFolder(String subFolder) {
        this.subFolder = subFolder;
    }
}
