package de.scandio.settingsframework.stores;

import de.scandio.settingsframework.settings.Store;

import java.util.HashMap;
import java.util.Map;

public class SimpleStore implements Store {

    private Map<String, String> values = new HashMap<>();

    @Override
    public Map<String, String> loadValues(String storageKey) {
        return values;
    }

    @Override
    public void storeValues(String storageKey, Map<String, String> values) {
        this.values = values;
    }

    @Override
    public void removeValues(String storageKey) {
        this.values = new HashMap<>();
    }
}
