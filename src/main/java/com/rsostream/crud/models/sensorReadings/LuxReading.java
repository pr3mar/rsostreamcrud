package com.rsostream.crud.models.sensorReadings;

import com.rsostream.crud.util.InvalidMessageException;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
public class LuxReading extends SensorReading {
    private float lux;
    public static int numberOfAttributes = 4;

    public LuxReading(){}

    public LuxReading(Date dateObtained, String IMEI, int signalQuality, float lux) {
        super(EnumType.LUX, dateObtained, IMEI, signalQuality);
        this.lux = lux;
    }

    public float getLux() {
        return lux;
    }

    public void setLux(float lux) {
        this.lux = lux;
    }

    public static LuxReading createReading(String[] data) throws InvalidMessageException {
        if (data.length != LuxReading.numberOfAttributes) {
            throw new InvalidMessageException();
        }
        Date dateObtained = new Date(Long.parseLong(data[0]));
        String IMEI = data[1];
        int signalQuality = Integer.parseInt(data[2]);
        float lux = Float.parseFloat(data[3]);
        return new LuxReading(dateObtained, IMEI, signalQuality, lux);
    }
}
