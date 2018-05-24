package de.scandio.settingsframework.settings;

import java.util.Map;

public class Settings {

    private String storageKey;
    private final Values values;
    private final Store store;

    public Settings(Store store, Config config) {
        if (store == null || config == null) {
            throw new IllegalArgumentException("Store or Config cannot be null");
        }

        this.store = store;
        this.values = new Values();

        this.storageKey = config.getStorageKey();
        this.values.setAllowedKeys(config.getAllowedKeys());
        this.values.setDefaultValues(config.getDefaultValues());
        this.values.setMasks(config.getMasks());
        this.values.setMigrators(config.getMigrators());
    }

    public void setValues(Map<String, String> newValues) {
        Map<String, String> storedValues = store.loadValues(this.storageKey);

        Map<String, String> valuesToStore = this.values
                .getValuesToStore(newValues, storedValues);

        store.storeValues(this.storageKey, valuesToStore);
    }

    public Map<String, String> getValues() {
        Map<String, String> storedValues = store.loadValues(this.storageKey);
        return this.values.getValues(storedValues);
    }

    public Map<String, String> getMaskedValues() {
        Map<String, String> storedValues = store.loadValues(this.storageKey);
        return this.values.getMaskedValues(storedValues);
    }

    public String getValue(String key) {
        Map<String, String> values = this.getValues();
        return values != null ? values.get(key) : null;
    }

    public String getMaskedValue(String key) {
        Map<String, String> maskedValues = this.getMaskedValues();
        return maskedValues != null ? maskedValues.get(key) : null;
    }

    public void resetValues() {
        store.removeValues(this.storageKey);
    }
}
