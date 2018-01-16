package com.rsostream.crud.properties;

import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import com.kumuluz.ee.configuration.cdi.ConfigValue;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@ConfigBundle("mongo-db")
public class PropertiesMongoDB {

    @ConfigValue(watch = true)
    private String uri;

    @ConfigValue(watch = true)
    private String host;

    @ConfigValue(watch = true)
    private int port;

    @ConfigValue(watch = true)
    private String username;

    @ConfigValue(watch = true)
    private String password;

    @ConfigValue(watch = true)
    private String db;

    @ConfigValue(watch = true)
    private String deviceCollection;

    @ConfigValue(watch = true)
    private String settingsCollection;

    @ConfigValue(watch = true)
    private String sensorDataCollection;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getDeviceCollection() {
        return deviceCollection;
    }

    public void setDeviceCollection(String deviceCollection) {
        this.deviceCollection = deviceCollection;
    }

    public String getSettingsCollection() {
        return settingsCollection;
    }

    public void setSettingsCollection(String settingsCollection) {
        this.settingsCollection = settingsCollection;
    }

    public String getSensorDataCollection() {
        return sensorDataCollection;
    }

    public void setSensorDataCollection(String sensorDataCollection) {
        this.sensorDataCollection = sensorDataCollection;
    }
}
