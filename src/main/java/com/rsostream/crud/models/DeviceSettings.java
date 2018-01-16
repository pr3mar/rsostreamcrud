package com.rsostream.crud.models;

import org.bson.types.ObjectId;

public class DeviceSettings {
    private ObjectId _id;
    private int deviceId;
    private boolean settings;

    public DeviceSettings() {
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public boolean isSettings() {
        return settings;
    }

    public void setSettings(boolean settings) {
        this.settings = settings;
    }


    @Override
    public String toString() {
        return "deviceId = " + this.getDeviceId() + ", Settings = " + this.isSettings();
    }
}
