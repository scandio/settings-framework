package de.scandio.settingsframework.settings;

import java.util.Map;

public class Settings {

    private String storageKey;
    private final Values values;
    private final Store store;

    public Settings(Store store) {
        this.store = store;
        this.values = new Values();
    }

    public Settings withConfig(Config config) {
        this.storageKey = config.getStorageKey();
        this.values.setDefaultValues(config.getDefaultValues());
        this.values.setMasks(config.getMasks());
        this.values.setMigrators(config.getMigrators());
        return this;
    }

    public Settings withStorageKey(String storageKey) {
        this.storageKey = storageKey;
        return this;
    }

    public Settings withDefaultValues(Map<String, String> defaultValues) {
        this.values.setDefaultValues(defaultValues);
        return this;
    }

    public Settings withMasks(Map<String, String> masks) {
        this.values.setMasks(masks);
        return this;
    }

    public Settings withMigrator(Migrator migrator) {
        this.values.addMigrator(migrator);
        return this;
    }

    public void setValues(Map<String, String> values) {
        Map<String, String> storedValues = store.loadValues(this.storageKey);

        Map<String, String> valuesToStore = this.values
                .setStoredValues(storedValues)
                .getValuesToStore(values);

        store.storeValues(this.storageKey, valuesToStore);
    }

    public Map<String, String> getValues() {
        Map<String, String> storedValues = store.loadValues(this.storageKey);

        return this.values
                .setStoredValues(storedValues)
                .getValues();
    }

    public Map<String, String> getMaskedValues() {
        Map<String, String> storedValues = store.loadValues(this.storageKey);

        return this.values
                .setStoredValues(storedValues)
                .getMaskedValues();
    }

    public void resetValues() {
        store.removeValues(this.storageKey);
    }
}
