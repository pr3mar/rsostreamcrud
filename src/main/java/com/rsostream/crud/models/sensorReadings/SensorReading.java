package com.rsostream.crud.models.sensorReadings;

import com.rsostream.crud.util.InvalidMessageException;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;


@XmlRootElement
public class SensorReading {
    public final EnumType TYPE;
    private Date timeObtained;
    private String imei;
    private int signalQuality;
    private static int numberOfAttributes = 3;

    public SensorReading() {
        this.TYPE = EnumType.SUPER;
    }

    SensorReading(Date timeObtained, String imei, int signalQuality) {
        this.TYPE = EnumType.SUPER;
        this.timeObtained = timeObtained;
        this.imei = imei;
        this.signalQuality = signalQuality;
    }

    SensorReading(EnumType type, Date timeObtained, String imei, int signalQuality) {
        this.TYPE = type;
        this.timeObtained = timeObtained;
        this.imei = imei;
        this.signalQuality = signalQuality;
    }

    public Date getTimeObtained() {
        return timeObtained;
    }

    public void setTimeObtained(Date timeObtained) {
        this.timeObtained = timeObtained;
    }

    public int getNumberOfAttributes() {
        return numberOfAttributes;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public int getSignalQuality() {
        return signalQuality;
    }

    public void setSignalQuality(int signalQuality) {
        this.signalQuality = signalQuality;
    }

    public static SensorReading createReading(String[] data) throws InvalidMessageException {
        if (data.length != SensorReading.numberOfAttributes) {
            throw new InvalidMessageException();
        }
        Date dateObtained = new Date(Long.parseLong(data[0]));
        String IMEI = data[1];
        int signalQuality = Integer.parseInt(data[2]);
        return new SensorReading(dateObtained, IMEI, signalQuality);
    }
}
