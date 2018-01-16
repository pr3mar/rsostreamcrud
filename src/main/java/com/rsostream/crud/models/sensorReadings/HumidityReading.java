package com.rsostream.crud.models.sensorReadings;

import com.rsostream.crud.util.InvalidMessageException;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
public class HumidityReading extends SensorReading {
    private float temperature;
    private float humidity;
    static int numberOfAttributes = 5;

    public HumidityReading(){}

    public HumidityReading (Date dateObtained, String IMEI, int signalQuality, float temperature, float humidity) {
        super(EnumType.HUM, dateObtained, IMEI, signalQuality);
        this.temperature = temperature;
        this.humidity = humidity;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public static HumidityReading createReading(String[] data) throws InvalidMessageException {
        if (data.length != HumidityReading.numberOfAttributes) {
            throw new InvalidMessageException();
        }
        Date dateObtained = new Date(Long.parseLong(data[0]));
        String IMEI = data[1];
        int signalQuality = Integer.parseInt(data[2]);
        float temperature = Integer.parseInt(data[3]);
        float humidity = Integer.parseInt(data[4]);
        return new HumidityReading(dateObtained, IMEI, signalQuality, temperature, humidity);
    }
}
