package com.rsostream.crud.models.sensorReadings;


import com.rsostream.crud.util.InvalidMessageException;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
public class AltitudeReading extends SensorReading {
    private float altitude;
    public static int numberOfAttributes = 4;

    public AltitudeReading() {
    }

    public AltitudeReading(Date dateObtained, String IMEI, int signalQuality, float altitude) {
        super(EnumType.ALT, dateObtained, IMEI, signalQuality);
        this.altitude = altitude;
    }

    public float getAltitude() {
        return altitude;
    }

    public void setAltitude(float altitude) {
        this.altitude = altitude;
    }

    public static AltitudeReading createReading(String[] data) throws InvalidMessageException {
        if (data.length != AltitudeReading.numberOfAttributes) {
            throw new InvalidMessageException();
        }
        Date dateObtained = new Date(Long.parseLong(data[0]));
        String IMEI = data[1];
        int signalQuality = Integer.parseInt(data[2]);
        float altitude = Float.parseFloat(data[3]);
        return new AltitudeReading(dateObtained, IMEI, signalQuality, altitude);
    }
}
