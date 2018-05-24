package de.scandio.settingsframework.settings;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ValuesTest {

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
        }}, new Values().getValuesToStore(newValues, null));
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

        }}, new Values().setDefaultValues(DEFAULT_VALUES).getValuesToStore(newValues, null));
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
        }}, new Values().setDefaultValues(DEFAULT_VALUES).getValuesToStore(newValues, null));
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
        }}, new Values().setDefaultValues(DEFAULT_VALUES).getValuesToStore(newValues, null));
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
        }}, new Values().setDefaultValues(DEFAULT_VALUES).getValuesToStore(newValues, storedValues));
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
        }}, new Values().setDefaultValues(DEFAULT_VALUES).getValuesToStore(newValues, storedValues));
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
            
        }}, new Values().setDefaultValues(DEFAULT_VALUES).getValuesToStore(newValues, storedValues));
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
        }}, new Values().setDefaultValues(DEFAULT_VALUES).getValuesToStore(newValues, storedValues));
    }

    @Test
    public void getValuesEmpty() throws Exception {

        assertEquals(new HashMap<String, String>() {{

        }}, new Values().getValues(null));
    }

    @Test
    public void getValuesEmptyWithDefault() throws Exception {

        assertEquals(new HashMap<String, String>() {{
            put("a", "aa");
            put("b", "bb");
            put("c", "");
            put("d", null);
        }}, new Values().setDefaultValues(DEFAULT_VALUES).getValues(null));
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
        }}, new Values().setDefaultValues(DEFAULT_VALUES).getValues(storedValues));
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
        }}, new Values()
                .setDefaultValues(DEFAULT_VALUES)
                .setMasks(masks)
                .getValues(storedValues));
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
        }}, new Values()
                .setDefaultValues(DEFAULT_VALUES)
                .setMasks(masks)
                .getMaskedValues(storedValues));
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
        }}, new Values()
                .setDefaultValues(DEFAULT_VALUES)
                .setMasks(masks)
                .getMaskedValues(storedValues));
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
        }}, new Values()
                .setDefaultValues(DEFAULT_VALUES)
                .setMasks(masks)
                .getValuesToStore(new HashMap<String, String>() {{
                    put("a", "aaa");
                    put("b", "bb");
                    put("c", "");
                    put("d", null);
                    put("e", "**");
                }}, storedValues));
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
        }}, new Values()
                .setDefaultValues(DEFAULT_VALUES)
                .setMasks(masks)
                .getValuesToStore(new HashMap<String, String>() {{
                    put("a", "aaa");
                    put("b", "bb");
                    put("c", "");
                    put("d", null);
                    put("e", "eee");
                }}, storedValues));
    }

    @Test
    public void migrateKey() throws Exception {
        Map<String, String> storedValues = new HashMap<String, String>() {{
            put("a-old", "xx");
        }};

        Migrator migrator = values -> {
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
        }}, new Values().setDefaultValues(DEFAULT_VALUES).addMigrator(migrator).getValues(storedValues));

        Map<String, String> newValues = new HashMap<String, String>() {{
            put("a", "xx");
            put("b", "bb");
            put("c", "");
            put("d", null);
        }};

        assertEquals(new HashMap<String, String>() {{
            put("a", "xx");
        }}, new Values().setDefaultValues(DEFAULT_VALUES).addMigrator(migrator).getValuesToStore(newValues, storedValues));
    }

    @Test
    public void migrateValue() throws Exception {
        Map<String, String> storedValues = new HashMap<String, String>() {{
            put("a", "xx");
        }};

        Migrator migrator = values -> {
            Map<String, String> newValues = new HashMap<>(values);
            newValues.put("a", "yy");
            return newValues;
        };

        assertEquals(new HashMap<String, String>() {{
            put("a", "yy");
            put("b", "bb");
            put("c", "");
            put("d", null);
        }}, new Values().setDefaultValues(DEFAULT_VALUES).addMigrator(migrator).getValues(storedValues));

        Map<String, String> newValues = new HashMap<String, String>() {{
            put("a", "zz");
            put("b", "bb");
            put("c", "");
            put("d", null);
        }};

        assertEquals(new HashMap<String, String>() {{
            put("a", "zz");
        }}, new Values().setDefaultValues(DEFAULT_VALUES).addMigrator(migrator).getValuesToStore(newValues, storedValues));
    }

    @Test
    public void allowedKeys() throws Exception {
        Map<String, String> newValues = new HashMap<String, String>() {{
            put("a", "aa");
            put("b", "bb");
            put("c", "");
            put("d", null);
        }};

        assertEquals(new HashMap<String, String>() {{
            put("a", "aa");
            put("c", "");
        }}, new Values().setAllowedKeys(Arrays.asList("a", "c")).getValuesToStore(newValues, null));
    }
}