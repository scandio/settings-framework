package de.scandio.settingsframework.settings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Values {

    private Map<String, String> defaultValues;
    private Map<String, String> storedValues;
    private Map<String, String> masks;
    private List<Migrator> migrators;

    public Values setDefaultValues(Map<String, String> defaultValues) {
        this.defaultValues = defaultValues;
        return this;
    }

    public Values setStoredValues(Map<String, String> storedValues) {
        this.storedValues = storedValues;
        return this;
    }

    public Values setMasks(Map<String, String> masks) {
        this.masks = masks;
        return this;
    }

    public Map<String, String> getValuesToStore(Map<String, String> newValues) {
        Map<String, String> newUnmaskedValues;

        if (this.masks != null) {
            newUnmaskedValues = new HashMap<>();
            for (Map.Entry<String, String> entry : newValues.entrySet()) {
                if (this.masks.get(entry.getKey()) != null && this.masks.get(entry.getKey()).equals(entry.getValue())) {
                    if (this.storedValues != null && this.storedValues.get(entry.getKey()) != null) {
                        newUnmaskedValues.put(entry.getKey(), this.storedValues.get(entry.getKey()));
                    }
                } else {
                    newUnmaskedValues.put(entry.getKey(), entry.getValue());
                }
            }
        } else {
            newUnmaskedValues = newValues;
        }

        Map<String, String> valuesToStore = new HashMap<>();

        if (this.storedValues != null) {
            valuesToStore.putAll(migrate(this.storedValues));
        }

        if (newUnmaskedValues != null) {
            valuesToStore.putAll(newUnmaskedValues);
        }

        if (this.defaultValues != null && !this.defaultValues.isEmpty() && !valuesToStore.isEmpty()) {
            this.defaultValues.forEach((key, value) -> {
                if (valuesToStore.containsKey(key) && valueEquals(valuesToStore.get(key), value)) {
                    valuesToStore.remove(key);
                }
            }) ;
        }

        return valuesToStore;
    }

    public Map<String, String> getValues() {
        Map<String, String> values = this.defaultValues != null ? new HashMap<>(this.defaultValues) : new HashMap<>();
        if (this.storedValues != null) {
            values.putAll(migrate(this.storedValues));
        }
        return values;
    }

    public Map<String, String> getMaskedValues() {
        Map<String, String> maskedValues;

        if (this.masks != null) {
            maskedValues = new HashMap<>();
            for (Map.Entry<String, String> entry : this.getValues().entrySet()) {
                String value = entry.getValue();
                if (value != null && value.length() > 0 && masks.get(entry.getKey()) != null) {
                    value = masks.get(entry.getKey());
                }
                maskedValues.put(entry.getKey(), value);
            }
        } else {
            maskedValues = this.getValues();
        }

        return maskedValues;
    }

    public Values addMigrator(Migrator migrator) {
        if (this.migrators == null) {
            this.migrators = new ArrayList<>();
        }
        this.migrators.add(migrator);
        return this;
    }

    private Map<String, String> migrate(Map<String, String> values) {
        if (this.migrators != null) {
            for (Migrator migrator : this.migrators) {
                values = migrator.migrate(values);
            }
        }
        return values;
    }

    private boolean valueEquals(String left, String right) {
        return left == null && right == null || !(left == null || right == null) && left.equals(right);
    }
}
