package com.rsostream.crud.models;

import org.bson.types.ObjectId;

public final class Device {
    private ObjectId id;
    private String imei;

    public Device() {
    }

    public Device(ObjectId id, String imei) {
        this.id = id;
        this.imei = imei;
    }

    public Device(String imei) {
        this.id = new ObjectId();
        this.imei = imei;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    @Override
    public String toString() {
        return "imei = " + this.imei;
    }
}
