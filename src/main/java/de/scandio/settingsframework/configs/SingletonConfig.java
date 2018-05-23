package de.scandio.settingsframework.configs;

import de.scandio.settingsframework.settings.Config;
import de.scandio.settingsframework.settings.Migrator;

import java.util.List;
import java.util.Map;

public class SingletonConfig implements Config {

    private static SingletonConfig instance = null;

    private String storageKey;
    private Map<String, String> defaultValues;
    private Map<String, String> masks;
    private List<Migrator> migrators;

    @Override
    public String getStorageKey() {
        return storageKey;
    }

    @Override
    public Map<String, String> getDefaultValues() {
        return defaultValues;
    }

    @Override
    public Map<String, String> getMasks() {
        return masks;
    }

    @Override
    public List<Migrator> getMigrators() {
        return migrators;
    }

    public void setStorageKey(String storageKey) {
        this.storageKey = storageKey;
    }

    public void setDefaultValues(Map<String, String> defaultValues) {
        this.defaultValues = defaultValues;
    }

    public void setMasks(Map<String, String> masks) {
        this.masks = masks;
    }

    public void setMigrators(List<Migrator> migrators) {
        this.migrators = migrators;
    }

    public static SingletonConfig getInstance() {
        if(instance == null) {
            instance = new SingletonConfig();
        }
        return instance;
    }
}
