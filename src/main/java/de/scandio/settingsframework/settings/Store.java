package de.scandio.settingsframework.settings;

import java.util.Map;

public interface Store {

    Map<String, String> loadValues(String storageKey);

    void storeValues(String storageKey, Map<String, String> values);

    void removeValues(String storageKey);
}