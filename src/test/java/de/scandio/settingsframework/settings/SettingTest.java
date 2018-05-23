package de.scandio.settingsframework.settings;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test for {@link Setting}.
 *
 * @author Georg Schmidl
 */
public class SettingTest {

    private static String KEY = "_key";
    private static String DEFAULT_VALUE = "a";

    @Test
    public void getValueToStore() throws Exception {
        String storedValue = "b";
        String newValue = "c";
        assertEquals("c", new Settings().forKey(KEY).setDefaultValue(DEFAULT_VALUE).setStoredValue(storedValue).getValueToStore(newValue));
    }

    @Test
    public void getValueToStoreAllIsNull() throws Exception {
        assertEquals(null, new Settings().forKey(KEY).setDefaultValue(DEFAULT_VALUE).setStoredValue(null).getValueToStore(null));
    }

    @Test
    public void getValueToStoreNoStored() throws Exception {
        String newValue = "b";
        assertEquals("b", new Settings().forKey(KEY).setDefaultValue(DEFAULT_VALUE).getValueToStore(newValue));
    }

    @Test
    public void getValueToStoreRestoreDefault() throws Exception {
        String storedValue = "b";
        String newValue = "a";
        assertEquals(null, new Settings().forKey(KEY).setDefaultValue(DEFAULT_VALUE).setStoredValue(storedValue).getValueToStore(newValue));
    }

    @Test
    public void getValueToStoreStoredIsNull() throws Exception {
        String storedValue = null;
        String newValue = "b";
        assertEquals("b", new Settings().forKey(KEY).setDefaultValue(DEFAULT_VALUE).setStoredValue(storedValue).getValueToStore(newValue));
    }

    @Test
    public void getValue() throws Exception {
        String storedValue = "b";
        assertEquals("b", new Settings().forKey(KEY).setDefaultValue(DEFAULT_VALUE).setStoredValue(storedValue).getValue());
    }

    @Test
    public void getValueNoStored() throws Exception {
        assertEquals("a", new Settings().forKey(KEY).setDefaultValue(DEFAULT_VALUE).getValue());
    }

    @Test
    public void getValueStoredIsNull() throws Exception {
        String storedValue = null;
        assertEquals("a", new Settings().forKey(KEY).setDefaultValue(DEFAULT_VALUE).setStoredValue(storedValue).getValue());
    }

    @Test
    public void getMaskedValue() throws Exception {
        String storedValue = "b";
        assertEquals("*", new Settings().forKey(KEY).setDefaultValue(DEFAULT_VALUE).setMask("*").setStoredValue(storedValue).getMaskedValue());
    }

    @Test
    public void migrateValue() throws Exception {
        String storedValue = "xx";

        SettingMigrator migrator = value -> "yy";
        assertEquals("yy", new Settings().forKey(KEY).setDefaultValue(DEFAULT_VALUE).setStoredValue(storedValue).addMigrator(migrator).getValue());

        String newValue = "zz";
        assertEquals("zz", new Settings().forKey(KEY).setDefaultValue(DEFAULT_VALUE).setStoredValue(storedValue).addMigrator(migrator).getValueToStore(newValue));
    }
}