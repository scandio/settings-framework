package de.scandio.settingsframework.settings;

import java.util.HashMap;
import java.util.Map;

/**
 * Settings for a single key.
 *
 * @author Georg Schmidl
 */
public class Setting {

    private final Settings settings;
    private final String key;

    public Setting(Settings settings, String key) {
        this.settings = settings;
        this.key = key;
    }

    public Setting setDefaultValue(String defaultValue) {
        this.settings.setDefaultValues(new HashMap<String, String>() {{
            if (defaultValue != null) {
                put(key, defaultValue);
            }
        }});
        return this;
    }

    public Setting setStoredValue(String storedValue) {
        this.settings.setStoredValues(new HashMap<String, String>() {{
            if (storedValue != null) {
                put(key, storedValue);
            }
        }});
        return this;
    }

    public Setting setMask(String mask) {
        this.settings.setMasks(new HashMap<String, String>() {{
            if (mask != null) {
                put(key, mask);
            }
        }});
        return this;
    }

    public String getValueToStore(String newValue) {
        return this.settings.getValuesToStore(new HashMap<String, String>() {{
            if (newValue != null) {
                put(key, newValue);
            }
        }}).get(key);
    }

    public String getValue() {
        return this.settings.getValues().get(key);
    }

    public String getMaskedValue() {
        return this.settings.getMaskedValues().get(key);
    }

    public Setting addMigrator(SettingMigrator migratorForKey) {
        this.settings.addMigrator(values -> {
            Map<String, String> newValues = new HashMap<>(values);
            newValues.put(key, migratorForKey.migrate(values.get(key)));
            return newValues;
        });
        return this;
    }
}
