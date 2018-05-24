package de.scandio.settingsframework.settings;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Config {

    private String storageKey;
    private Map<String, String> defaultValues;
    private Map<String, String> masks;
    private List<Migrator> migrators;

    public Config() {
    }

    public Config(String storageKey, Map<String, String> defaultValues, Map<String, String> masks, List<Migrator> migrators) {
        this.storageKey = storageKey;
        this.defaultValues = defaultValues;
        this.masks = masks;
        this.migrators = migrators;
    }

    public String getStorageKey() {
        return storageKey;
    }

    public Map<String, String> getDefaultValues() {
        return defaultValues;
    }

    public Map<String, String> getMasks() {
        return masks;
    }

    public List<Migrator> getMigrators() {
        return migrators;
    }

    public Config setStorageKey(String storageKey) {
        this.storageKey = storageKey;
        return this;
    }

    public Config setDefaultValues(Map<String, String> defaultValues) {
        this.defaultValues = defaultValues;
        return this;
    }

    public Config setMasks(Map<String, String> masks) {
        this.masks = masks;
        return this;
    }

    public Config setMigrators(List<Migrator> migrators) {
        this.migrators = migrators;
        return this;
    }

    public Config putDefaultValue(String key, String value) {
        if (this.defaultValues == null) {
            this.defaultValues = new LinkedHashMap<>();
        }
        this.defaultValues.put(key, value);
        return this;
    }

    public Config putMask(String key, String value) {
        if (this.masks == null) {
            this.masks = new LinkedHashMap<>();
        }
        this.masks.put(key, value);
        return this;
    }

    public Config addMigrator(Migrator migrator) {
        if (this.migrators == null) {
            this.migrators = new ArrayList<>();
        }
        this.migrators.add(migrator);
        return this;
    }
}
