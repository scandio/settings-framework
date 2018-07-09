package de.scandio.settingsframework.settings;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Flags {

    private String storageKey;
    private final Values values;
    private final Store store;

    public Flags(Store store, FlagConfig config) {
        if (store == null || config == null) {
            throw new IllegalArgumentException("Store or Config cannot be null");
        }

        this.store = store;
        this.values = new Values();

        this.storageKey = config.getStorageKey();
        this.values.setAllowedKeys(config.getAllowedFlags());
        this.values.setDefaultValues(config.getAllowedFlags() != null ? config.getAllowedFlags()
                .stream()
                .collect(Collectors.toMap(e -> e, e -> "false")) : null);
    }

    public void set(Map<String, Boolean> newValues) {
        Map<String, String> storedValues = store.loadValues(this.storageKey);

        Map<String, String> valuesToStore = this.values
                .getValuesToStore(newValues.entrySet()
                        .stream()
                        .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue() ? "true" : "false")), storedValues);

        store.storeValues(this.storageKey, valuesToStore);
    }

    public Map<String, Boolean> get() {
        Map<String, String> storedValues = store.loadValues(this.storageKey);
        return this.values.getValues(storedValues).entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> "true".equals(e.getValue())));
    }

    public boolean get(String key) {
        Map<String, Boolean> values = this.get();
        return values != null ? values.get(key) : false;
    }

    public void set(String key, boolean newValue) {
        Map<String, Boolean> newFlags = new HashMap<>(this.get());
        newFlags.put(key, newValue);
        this.set(newFlags);
    }

    public void reset() {
        store.removeValues(this.storageKey);
    }
}
