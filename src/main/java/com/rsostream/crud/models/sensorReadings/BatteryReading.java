package com.rsostream.crud.models.sensorReadings;

import com.rsostream.crud.util.InvalidMessageException;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
public class BatteryReading extends SensorReading {
    private float batteryLevel;
    public static float numberOfAttributes = 4;

    public BatteryReading() {}

    public BatteryReading(Date dateObtained, String IMEI, int signalQuality, float batteryLevel) {
        super(EnumType.BAT, dateObtained, IMEI, signalQuality);
        this.batteryLevel = batteryLevel;
    }

    public float getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(float batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public static BatteryReading createReading(String[] data) throws InvalidMessageException {
        if (data.length != BatteryReading.numberOfAttributes) {
            throw new InvalidMessageException();
        }
        Date dateObtained = new Date(Long.parseLong(data[0]));
        String IMEI = data[1];
        int signalQuality = Integer.parseInt(data[2]);
        float batteryLevel = Float.parseFloat(data[3]);
        return new BatteryReading(dateObtained, IMEI, signalQuality, batteryLevel);
    }
}
