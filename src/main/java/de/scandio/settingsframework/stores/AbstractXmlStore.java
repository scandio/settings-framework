package de.scandio.settingsframework.stores;

import com.thoughtworks.xstream.XStream;
import de.scandio.settingsframework.settings.Store;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractXmlStore implements Store {

    private final static Logger log = LoggerFactory.getLogger(AbstractXmlStore.class);

    private final XStream xStream;

    public AbstractXmlStore() {
        this.xStream = new XStream();
        this.xStream.setClassLoader(getClass().getClassLoader());
    }

    @Override
    public Map<String, String> loadValues(String storageKey) {
        Map<String, String> storedValues = new HashMap<>();

        String storedValuesXml = loadValuesXml(storageKey);

        if (storedValuesXml != null) {
            try {
                storedValues = (Map<String, String>) xStream.fromXML(storedValuesXml);
            } catch (ClassCastException e) {
                log.error("Error casting valuesXml for key '{}'", storageKey);
            }
        }
        return storedValues;
    }

    @Override
    public void storeValues(String storageKey, Map<String, String> values) {
        if (values.isEmpty()) {
            log.debug("Removing valuesXml for key '{}'", storageKey);
            removeValuesXml(storageKey);
        } else {
            String valuesXml = xStream.toXML(values);

            log.debug("Storing valuesXml for key '{}': {}", storageKey, valuesXml);
            storeValuesXml(storageKey, valuesXml);
        }
    };

    @Override
    public void removeValues(String storageKey) {
        removeValuesXml(storageKey);
    }

    protected abstract String loadValuesXml(String storageKey);

    protected abstract void removeValuesXml(String storageKey);

    protected abstract void storeValuesXml(String storageKey, String valuesXml);
}