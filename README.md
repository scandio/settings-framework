# Settings Framework

A framework that helps to store and migrate application settings. Currently it is used primarily in Atlassian
Confluence plugin.

## Confluence plugin QuickStart

This is a simple QuickStart for adding a settings page to a Confluence plugin.

### Add the maven repository

Add the Scandio Maven repository to your pom.xml.

    <repositories>
        <repository>
            <id>scandio-public-releases</id>
            <name>Scandio Public Releases Repository</name>
            <url>https://github.com/scandio/mvn-repo/raw/master/releases</url>
        </repository>
        <repository>
            <id>scandio-public-snapshots</id>
            <name>Scandio Public Snapshots Repository</name>
            <url>https://github.com/scandio/mvn-repo/raw/master/snapshots</url>
        </repository>
    </repositories>

### Add settings-framework dependency

Add the maven dependency to your pom.xml.

    <dependency>
        <groupId>de.scandio</groupId>
        <artifactId>settings-framework</artifactId>
        <version>0.5.0</version>
    </dependency>
    
    
### Add a config class

Add a config class and configure your settings.

    public class SettingsConfig implements InitializingBean {
    
        @Override
        public void afterPropertiesSet() {
            SingletonConfig.getConfig()
                    .setStorageKey("settings-framework-example.settings")
                    .addAllowedKey("exampleInput")
                    .addAllowedKey("exampleDefault")
                    .addAllowedKey("exampleMasked")
                    .addAllowedKey("exampleCheckbox")
                    .putDefaultValue("exampleDefault", "This is a default value")
                    .putMask("exampleMasked", "********");
            
        }
    }
    
### Add your settings HTML

Add a Velocity template with the settings HTML.

    <html>
    <head>
        <title>$action.getText("settings-framework-example.settings")</title>
        <meta name="decorator" content="atl.admin"/>
        <content tag="selectedWebItem">settings-framework-example-settings-item</content>
    </head>
    <body>
    
        #requireResourcesForContext("settings")
    
        <div class="settings" data-url="$req.contextPath/rest/settings-framework-example/latest/settings">
    
            <div class="spinner" style="text-align: center; padding: 10px;">
                <span class="aui-icon aui-icon-wait">Loading...</span>
            </div>
    
            <form class="aui" style="display: none;">
    
                <div class="field-group">
                    <label for="exampleInput">$i18n.getText("settings-framework-example.settings.exampleInput.label")</label>
                    <input class="text long-field" type="text" name="exampleInput" id="exampleInput">
                    <div class="description">$i18n.getText("settings-framework-example.settings.exampleInput.desc")</div>
                </div>
    
                <div class="field-group">
                    <label for="exampleDefault">$i18n.getText("settings-framework-example.settings.exampleDefault.label")</label>
                    <input class="text long-field" type="text" name="exampleDefault" id="exampleDefault">
                    <div class="description">$i18n.getText("settings-framework-example.settings.exampleDefault.desc")</div>
                </div>
    
                <div class="field-group">
                    <label for="exampleMasked">$i18n.getText("settings-framework-example.settings.exampleMasked.label")</label>
                    <input class="text long-field" type="text" name="exampleMasked" id="exampleMasked">
                    <div class="description">$i18n.getText("settings-framework-example.settings.exampleMasked.desc")</div>
                </div>
    
                <div class="field-group">
                    <div class="checkbox">
                        <input class="checkbox" type="checkbox" name="exampleCheckbox" id="exampleCheckbox">
                        <label for="exampleCheckbox">$i18n.getText("settings-framework-example.settings.exampleCheckbox.label")</label>
                        <div class="description">$i18n.getText("settings-framework-example.settings.exampleCheckbox.desc")</div>
                    </div>
                </div>
    
                <div class="buttons-container">
                    <div class="buttons">
                        <input class="aui-button submit" type="submit" value="$i18n.getText("settings-framework-example.settings.save")">
                        <span class="spinner" style="text-align: center; padding: 10px; display: none;">
                        <span class="aui-icon aui-icon-wait">Loading...</span>
                      </span>
                        <span class="success" style="text-align: center; padding: 10px; display: none;">
                        <span class="aui-icon aui-icon-success">Success!</span>
                      </span>
                    </div>
                </div>
    
            </form>
        </div>
    
    </body>
    </html>
    
    
### Add the settings plugin modules

Add the required plugin modules from the settings framework.

    <!-- SETTINGS -->
    
    <component key="settings-config-service" name="Settings config service"
               class="de.scandio.settingsframework.services.SingletonConfigService">
        <interface>de.scandio.settingsframework.services.ConfigService</interface>
    </component>

    <component key="settings-service" name="Settings service"
               class="de.scandio.settingsframework.services.ConfluenceSettingsService">
        <interface>de.scandio.settingsframework.services.SettingsService</interface>
    </component>

    <web-resource key="settings-resources" name="Settings Resources">
        <dependency>com.atlassian.auiplugin:ajs</dependency>
        <resource type="download" name="settings.js" location="settings-framework/js/settings.js"/>
        <context>settings</context>
    </web-resource>

    <!-- /SETTINGS -->
    
### Add a settings action, etc.

Add a settings action with a web item, make sure you have a REST and i18n module and add your config as a component.

    <resource type="i18n" name="i18n" location="settings-framework-example"/>
    
    <rest key="settings-framework-example-test" path="/settings-framework-example" version="1.0"/>

    <component key="settings-framework-example-settings-config" name="Settings config"
               class="de.scandio.confluence.plugins.settingsframeworkexample.SettingsConfig">
    </component>

    <web-item key="settings-framework-example-settings-item" section="system.admin/configuration" weight="200">
        <label key="settings-framework-example.settings" />
        <link>/admin/plugins/settings-framework-example/settings.action</link>
    </web-item>

    <xwork key="settings-framework-example-actions" name="Actions">
        <package name="settings-framework-example-admin-actions" extends="default" namespace="/admin/plugins/settings-framework-example">
            <default-interceptor-ref name="defaultStack"/>

            <action name="settings" class="com.atlassian.confluence.core.ConfluenceActionSupport">
                <result name="success" type="velocity">/settings-framework-example/templates/actions/settings.vm</result>
            </action>
        </package>
    </xwork>
    
## Config

There are number of configuration options

### Storage key

The storage key is used by the store to persist the settings.

Example:

    config.setStorageKey("myStorageKey");

### Allowed keys

A list of allowed keys can be configured to restrict what keys will be persisted. If no keys are given, everything will
be stored.

Example:

    config.addAllowedKey("myKey");
    

### Default values

When default values are set, the values will not be stored if they match the configured value.

Example:

    config.putDefaultValue("myKey", "This is a default value");

### Masks

Masks can be used to mask values in the settings form for example. You can define a mask for a value, and by calling
`getMasked()` instead of `get()`, you will get the masked instead of the real values.

Example:

     config.putMask("myKey", "********");

### Migrators

Migrators can be added if values keys or values need to be migrated. A migrator gets the a map of values and needs to
return the migrated map.

    Migrator migrator = values -> {
        Map<String, String> newValues = new HashMap<>(values);
        // do something with the newValues here
        return newValues;
    };

    config.addMigrator(migrator);
    
## How values are stored

The following table illustrates how settings are stored. A key-value-map (Map<String, String>) is used for the values.

|                    | Default values     | Stored values     | New values      | getValues                  | getValuesToStore     |
| ------------------ | ------------------ | ----------------- | --------------- | -------------------------- | -------------------- |
| Add values         | `[a -> 1, b -> 2]` | `[]`              |`[c -> 3]`       | `[a -> 1, b -> 2, c -> 3]` | `[c -> 3]`           |
| Overwrite values   | `[a -> 1, b -> 2]` | `[]`              |`[a -> 100]`     | `[a -> 100, b -> 2]`       | `[a -> 100]`         |
| Restore values     | `[a -> 1, b -> 2]` | `[a -> 100]`      |`[a -> 1]`       | `[a -> 1, b -> 2]`         | `[]`                 |
