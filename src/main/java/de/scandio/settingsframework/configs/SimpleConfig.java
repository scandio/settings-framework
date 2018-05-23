package de.scandio.settingsframework.configs;

import de.scandio.settingsframework.settings.Config;
import de.scandio.settingsframework.settings.Migrator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SimpleConfig implements Config {

    private String storageKey;
    private Map<String, String> defaultValues;
    private Map<String, String> masks;
    private List<Migrator> migrators;

    public SimpleConfig() {
    }

    public SimpleConfig(String storageKey, Map<String, String> defaultValues, Map<String, String> masks, List<Migrator> migrators) {
        this.storageKey = storageKey;
        this.defaultValues = defaultValues;
        this.masks = masks;
        this.migrators = migrators;
    }

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

    public SimpleConfig setStorageKey(String storageKey) {
        this.storageKey = storageKey;
        return this;
    }

    public SimpleConfig setDefaultValues(Map<String, String> defaultValues) {
        this.defaultValues = defaultValues;
        return this;
    }

    public SimpleConfig setMasks(Map<String, String> masks) {
        this.masks = masks;
        return this;
    }

    public SimpleConfig setMigrators(List<Migrator> migrators) {
        this.migrators = migrators;
        return this;
    }

    public SimpleConfig addMigrator(Migrator migrator) {
        if (this.migrators == null) {
            this.migrators = new ArrayList<>();
        }
        this.migrators.add(migrator);
        return this;
    }
}
