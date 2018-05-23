package de.scandio.settingsframework.settings;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Test for {@link Settings}.
 *
 * @author Georg Schmidl
 */
public class SettingsTest {

    private static Map<String, String> DEFAULT_VALUES = new HashMap<String, String>() {{
        put("a", "aa");
        put("b", "bb");
        put("c", "");
        put("d", null);
    }};

    @Test
    public void getValuesToStoreNoDefault() throws Exception {
        Map<String, String> newValues = new HashMap<String, String>() {{
            put("a", "aa");
            put("b", "bb");
            put("c", "");
            put("d", null);
        }};

        assertEquals(new HashMap<String, String>() {{
            put("a", "aa");
            put("b", "bb");
            put("c", "");
            put("d", null);
        }}, new Settings().getValuesToStore(newValues));
    }

    @Test
    public void getValuesToStoreNoChange() throws Exception {
        Map<String, String> newValues = new HashMap<String, String>() {{
            put("a", "aa");
            put("b", "bb");
            put("c", "");
            put("d", null);
        }};

        assertEquals(new HashMap<String, String>() {{

        }}, new Settings().setDefaultValues(DEFAULT_VALUES).getValuesToStore(newValues));
    }

    @Test
    public void getValuesToStoreValueRemoved() throws Exception {
        Map<String, String> newValues = new HashMap<String, String>() {{
            put("a", null);
            put("b", "bb");
            put("c", "");
            put("d", null);
        }};

        assertEquals(new HashMap<String, String>() {{
            put("a", null);
        }}, new Settings().setDefaultValues(DEFAULT_VALUES).getValuesToStore(newValues));
    }

    @Test
    public void getValuesToStoreValueAdded() throws Exception {

        Map<String, String> newValues = new HashMap<String, String>() {{
            put("a", "aa");
            put("b", "bb");
            put("c", "");
            put("d", null);
            put("e", "ee");
        }};

        assertEquals(new HashMap<String, String>() {{
            put("e", "ee");
        }}, new Settings().setDefaultValues(DEFAULT_VALUES).getValuesToStore(newValues));
    }

    @Test
    public void getValuesToStoreValueAddedWithStored() throws Exception {

        Map<String, String> storedValues = new HashMap<String, String>() {{
            put("e", "ee");
        }};

        Map<String, String> newValues = new HashMap<String, String>() {{
            put("f", "ff");
        }};

        assertEquals(new HashMap<String, String>() {{
            put("e", "ee");
            put("f", "ff");
        }}, new Settings().setDefaultValues(DEFAULT_VALUES).setStoredValues(storedValues).getValuesToStore(newValues));
    }

    @Test
    public void getValuesToStoreValueRemovedWithStored() throws Exception {

        Map<String, String> storedValues = new HashMap<String, String>() {{
            put("e", "ee");
        }};

        Map<String, String> newValues = new HashMap<String, String>() {{
            put("a", null);
        }};

        assertEquals(new HashMap<String, String>() {{
            put("e", "ee");
            put("a", null);
        }}, new Settings().setDefaultValues(DEFAULT_VALUES).setStoredValues(storedValues).getValuesToStore(newValues));
    }

    @Test
    public void getSettingsToStoreValueRestoreDefault() throws Exception {

        Map<String, String> storedValues = new HashMap<String, String>() {{
            put("a", "xx");
        }};

        Map<String, String> newValues = new HashMap<String, String>() {{
            put("a", "aa");
        }};

        assertEquals(new HashMap<String, String>() {{
            
        }}, new Settings().setDefaultValues(DEFAULT_VALUES).setStoredValues(storedValues).getValuesToStore(newValues));
    }

    @Test
    public void getValuesToStoreValueRestoreDefaultAndAdd() throws Exception {

        Map<String, String> storedValues = new HashMap<String, String>() {{
            put("a", "xx");
        }};

        Map<String, String> newValues = new HashMap<String, String>() {{
            put("a", "aa");
            put("e", "ee");
        }};

        assertEquals(new HashMap<String, String>() {{
            put("e", "ee");
        }}, new Settings().setDefaultValues(DEFAULT_VALUES).setStoredValues(storedValues).getValuesToStore(newValues));
    }

    @Test
    public void getValuesEmpty() throws Exception {

        assertEquals(new HashMap<String, String>() {{

        }}, new Settings().getValues());
    }

    @Test
    public void getValuesEmptyWithDefault() throws Exception {

        assertEquals(new HashMap<String, String>() {{
            put("a", "aa");
            put("b", "bb");
            put("c", "");
            put("d", null);
        }}, new Settings().setDefaultValues(DEFAULT_VALUES).getValues());
    }

    @Test
    public void getValuesEmptyWithDefaultAndStored() throws Exception {

        Map<String, String> storedValues = new HashMap<String, String>() {{
            put("a", "xx");
        }};

        assertEquals(new HashMap<String, String>() {{
            put("a", "xx");
            put("b", "bb");
            put("c", "");
            put("d", null);
        }}, new Settings().setDefaultValues(DEFAULT_VALUES).setStoredValues(storedValues).getValues());
    }

    @Test
    public void getValuesWithMask() throws Exception {
        Map<String, String> masks = new HashMap<String, String>() {{
            put("e", "**");
        }};

        Map<String, String> storedValues = new HashMap<String, String>() {{
            put("a", "aaa");
            put("e", "ee");
        }};

        assertEquals(new HashMap<String, String>() {{
            put("a", "aaa");
            put("b", "bb");
            put("c", "");
            put("d", null);
            put("e", "ee");
        }}, new Settings()
                .setDefaultValues(DEFAULT_VALUES)
                .setStoredValues(storedValues)
                .setMasks(masks)
                .getValues());
    }

    @Test
    public void getMaskedValuesWithMask() throws Exception {
        Map<String, String> masks = new HashMap<String, String>() {{
            put("e", "**");
        }};

        Map<String, String> storedValues = new HashMap<String, String>() {{
            put("a", "aaa");
            put("e", "ee");
        }};

        assertEquals(new HashMap<String, String>() {{
            put("a", "aaa");
            put("b", "bb");
            put("c", "");
            put("d", null);
            put("e", "**");
        }}, new Settings()
                .setDefaultValues(DEFAULT_VALUES)
                .setStoredValues(storedValues)
                .setMasks(masks)
                .getMaskedValues());
    }

    @Test
    public void getEmptyMaskedValuesWithMask() throws Exception {
        Map<String, String> masks = new HashMap<String, String>() {{
            put("e", "**");
        }};

        Map<String, String> storedValues = new HashMap<String, String>() {{
            put("a", "aaa");
            put("e", "");
        }};

        assertEquals(new HashMap<String, String>() {{
            put("a", "aaa");
            put("b", "bb");
            put("c", "");
            put("d", null);
            put("e", "");
        }}, new Settings()
                .setDefaultValues(DEFAULT_VALUES)
                .setStoredValues(storedValues)
                .setMasks(masks)
                .getMaskedValues());
    }

    @Test
    public void getValuesToStoreWithMaskedUnchanged() throws Exception {
        Map<String, String> masks = new HashMap<String, String>() {{
            put("e", "**");
        }};

        Map<String, String> storedValues = new HashMap<String, String>() {{
            put("e", "ee");
        }};

        assertEquals(new HashMap<String, String>() {{
            put("a", "aaa");
            put("e", "ee");
        }}, new Settings()
                .setDefaultValues(DEFAULT_VALUES)
                .setStoredValues(storedValues)
                .setMasks(masks)
                .getValuesToStore(new HashMap<String, String>() {{
                    put("a", "aaa");
                    put("b", "bb");
                    put("c", "");
                    put("d", null);
                    put("e", "**");
                }}));
    }

    @Test
    public void getValuesToStoreWithMaskedChanged() throws Exception {
        Map<String, String> masks = new HashMap<String, String>() {{
            put("e", "**");
        }};

        Map<String, String> storedValues = new HashMap<String, String>() {{
            put("e", "ee");
        }};

        assertEquals(new HashMap<String, String>() {{
            put("a", "aaa");
            put("e", "eee");
        }}, new Settings()
                .setDefaultValues(DEFAULT_VALUES)
                .setStoredValues(storedValues)
                .setMasks(masks)
                .getValuesToStore(new HashMap<String, String>() {{
                    put("a", "aaa");
                    put("b", "bb");
                    put("c", "");
                    put("d", null);
                    put("e", "eee");
                }}));
    }

    @Test
    public void migrateKey() throws Exception {
        Map<String, String> storedValues = new HashMap<String, String>() {{
            put("a-old", "xx");
        }};

        SettingsMigrator migrator = values -> {
            Map<String, String> newValues = new HashMap<>(values);
            newValues.remove("a-old");
            newValues.put("a", values.get("a-old"));
            return newValues;
        };

        assertEquals(new HashMap<String, String>() {{
            put("a", "xx");
            put("b", "bb");
            put("c", "");
            put("d", null);
        }}, new Settings().setDefaultValues(DEFAULT_VALUES).setStoredValues(storedValues).addMigrator(migrator).getValues());

        Map<String, String> newValues = new HashMap<String, String>() {{
            put("a", "xx");
            put("b", "bb");
            put("c", "");
            put("d", null);
        }};

        assertEquals(new HashMap<String, String>() {{
            put("a", "xx");
        }}, new Settings().setDefaultValues(DEFAULT_VALUES).setStoredValues(storedValues).addMigrator(migrator).getValuesToStore(newValues));
    }

    @Test
    public void migrateValue() throws Exception {
        Map<String, String> storedValues = new HashMap<String, String>() {{
            put("a", "xx");
        }};

        SettingsMigrator migrator = values -> {
            Map<String, String> newValues = new HashMap<>(values);
            newValues.put("a", "yy");
            return newValues;
        };

        assertEquals(new HashMap<String, String>() {{
            put("a", "yy");
            put("b", "bb");
            put("c", "");
            put("d", null);
        }}, new Settings().setDefaultValues(DEFAULT_VALUES).setStoredValues(storedValues).addMigrator(migrator).getValues());

        Map<String, String> newValues = new HashMap<String, String>() {{
            put("a", "zz");
            put("b", "bb");
            put("c", "");
            put("d", null);
        }};

        assertEquals(new HashMap<String, String>() {{
            put("a", "zz");
        }}, new Settings().setDefaultValues(DEFAULT_VALUES).setStoredValues(storedValues).addMigrator(migrator).getValuesToStore(newValues));
    }
}