# Settings Framework

Framework that helps to store and migrate application values, configs, options, properties, preferences, ...

## Values

A key-value-map (Map<String, String>) is used for the values. A `Values` object can be initialized with a map of the
default values and a map of the currently stored values.

### Values to store

The method `getValuesToStore` returns the values that need to be stored, i.e. all values that differ from the default
values. 
        
     Map<String, String> valuesToStore = new Values()
         .setDefaultValues(defaultValues)
         .setStoredValues(storedValues)
         .getValuesToStore(newValues);

### Values

The method `getValues` returns the complete values map by combining the default and stored values. 

    Map<String, String> values = new Values()
        .setDefaultValues(defaultValues)
        .setStoredValues(storedValues)
        .getValues();

### Migrators   

Migrators can be added if values keys or values need to be migrated. A migrator gets the a map of values and needs to
return the migrated map.

    Migrator migrator = values -> {
        Map<String, String> newValues = new HashMap<>(values);
        // do something with the newValues here
        return newValues;
    };

    Map<String, String> values = new Values()
        .setDefaultValues(defaultValues)
        .setStoredValues(storedValues)
        .addMigrator(migrator)
        .getValues());
        
### Masks

Masks can be used to mask values, in a user interface for example. You can define a mask for a value, and by calling
`getMaskedValues()` instead of `getValues()`, you will get the masked instead of the real values. If you provide masked
values in the newValues map for `getValuesToStore()`, the stored values will be kept.

    Map<String, String> values = new Values()
        .setMasks(masks)
        .setStoredValues(storedValues)
        .getMaskedValues();
       
## Examples

|                    | Default values     | Stored values     | New values      | getValues                  | getValuesToStore     |
| ------------------ | ------------------ | ----------------- | --------------- | -------------------------- | -------------------- |
| Add values         | `[a -> 1, b -> 2]` | `[]`              |`[c -> 3]`       | `[a -> 1, b -> 2, c -> 3]` | `[c -> 3]`           |
| Overwrite values   | `[a -> 1, b -> 2]` | `[]`              |`[a -> 100]`     | `[a -> 100, b -> 2]`       | `[a -> 100]`         |
| Restore values     | `[a -> 1, b -> 2]` | `[a -> 100]`      |`[a -> 1]`       | `[a -> 1, b -> 2]`         | `[]`                 |
