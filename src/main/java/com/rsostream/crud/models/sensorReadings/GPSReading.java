package com.rsostream.crud.models.sensorReadings;

import com.rsostream.crud.util.InvalidMessageException;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
public class GPSReading extends SensorReading {
    private float lat;
    private float lng;
    private float speed;
    public static int numberOfAttributes = 6;

    public GPSReading(){}

    public GPSReading(Date timeObtained, String IMEI, int signalQuality, float lat, float lng, float speed) {
        super(EnumType.GPS, timeObtained, IMEI, signalQuality);
        this.lat = lat;
        this.lng = lng;
        this.speed = speed;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public static GPSReading createReading(String[] data) throws InvalidMessageException {
        if (data.length != GPSReading.numberOfAttributes) {
            throw new InvalidMessageException();
        }
        Date dateObtained = new Date(Long.parseLong(data[0]));
        String IMEI = data[1];
        int signalQuality = Integer.parseInt(data[2]);
        float lat = Float.parseFloat(data[3]);
        float lng = Float.parseFloat(data[4]);
        float speed = Float.parseFloat(data[5]);
        return new GPSReading(dateObtained, IMEI, signalQuality, lat, lng, speed);
    }
}
