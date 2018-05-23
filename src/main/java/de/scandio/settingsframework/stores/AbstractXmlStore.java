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
    protected final String storageKey;

    public AbstractXmlStore(String storageKey) {
        this.xStream = new XStream();
        this.xStream.setClassLoader(getClass().getClassLoader());
        this.storageKey = storageKey;
    }

    @Override
    public Map<String, String> loadValues() {
        Map<String, String> storedValues = new HashMap<>();

        String storedValuesXml = loadValuesXml();

        if (storedValuesXml != null) {
            try {
                storedValues = (Map<String, String>) xStream.fromXML(storedValuesXml);
            } catch (ClassCastException e) {
                log.error("Error casting valuesXml for key '{}'", this.storageKey);
            }
        }
        return storedValues;
    }

    @Override
    public void storeValues(Map<String, String> values) {
        if (values.isEmpty()) {
            log.debug("Removing valuesXml for key '{}'", storageKey);
            removeValuesXml();
        } else {
            String valuesXml = xStream.toXML(values);

            log.debug("Storing valuesXml for key '{}': {}", storageKey, valuesXml);
            storeValuesXml(valuesXml);
        }
    };

    @Override
    public void removeValues() {
        removeValuesXml();
    }

    protected abstract String loadValuesXml();

    protected abstract void removeValuesXml();

    protected abstract void storeValuesXml(String valuesXml);
}