package com.rsostream.crud.services;

import com.kumuluz.ee.logs.LogManager;
import com.kumuluz.ee.logs.Logger;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.rsostream.crud.models.Device;
import com.rsostream.crud.models.DeviceSettings;
import com.rsostream.crud.models.sensorReadings.*;
import com.rsostream.crud.properties.PropertiesMongoDB;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Destroyed;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@ApplicationScoped
public class ServiceMongoDB {

    private static final Logger log = LogManager.getLogger(ServiceMongoDB.class.getName());


    @Inject
    PropertiesMongoDB propertiesMongoDB;

    private MongoClient mongoClient;
    private MongoDatabase db;
    //    private MongoCollection<Document> deviceCollection;
    private MongoCollection<Device> deviceCollection;
    private MongoCollection<DeviceSettings> settingsCollection;
    private MongoCollection<AltitudeReading> altitudeDataCollection;
    private MongoCollection<BatteryReading> batteryDataCollection;
    private MongoCollection<GPSReading> gpsDataCollection;
    private MongoCollection<HumidityReading> humidityDataCollection;
    private MongoCollection<LuxReading> luxDataCollection;

    private void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        mongoClient = new MongoClient(
                new MongoClientURI(propertiesMongoDB.getUri())
                /*propertiesMongoDB.getUri(),
                MongoClientOptions.builder()
                        .codecRegistry(pojoCodecRegistry).build()*/
        );

        db = mongoClient.getDatabase(propertiesMongoDB.getDb());
        db = db.withCodecRegistry(pojoCodecRegistry);
        deviceCollection = db.getCollection(propertiesMongoDB.getDeviceCollection(), Device.class);
//        deviceCollection = db.getCollection(propertiesMongoDB.getDeviceCollection());
        settingsCollection = db.getCollection(propertiesMongoDB.getSettingsCollection(), DeviceSettings.class);
        altitudeDataCollection = db.getCollection(propertiesMongoDB.getSensorDataCollection(), AltitudeReading.class);
        batteryDataCollection = db.getCollection(propertiesMongoDB.getSensorDataCollection(), BatteryReading.class);
        gpsDataCollection = db.getCollection(propertiesMongoDB.getSensorDataCollection(), GPSReading.class);
        humidityDataCollection = db.getCollection(propertiesMongoDB.getSensorDataCollection(), HumidityReading.class);
        luxDataCollection = db.getCollection(propertiesMongoDB.getSensorDataCollection(), LuxReading.class);
    }

    private void stop(@Observes @Destroyed(ApplicationScoped.class) Object destroyed) {
        mongoClient.close();
    }


    public List<Device> findAll() {
        try {
            List<Device> docs = new ArrayList<Device>() {
            };
            Block<Device> devices = new Block<Device>() {
                @Override
                public void apply(final Device device) {
                    docs.add(device);
                }
            };
            deviceCollection.find().forEach(devices);
            log.info("Found: " + docs.size() + " devices.");
            return docs;
        } catch (NullPointerException e) {
            log.info("Did not find any devices.");
            e.printStackTrace();
            return null;
        }
    }

    public Device findByIMEI(String IMEI) {
        try {
            Device doc = deviceCollection.find(eq("imei", IMEI)).first();
            log.info(":D :D :D FOUND:" + doc);
            return doc;
        } catch (NullPointerException e) {
            log.info("Did not find any devices.");
            e.printStackTrace();
            return null;
        }
    }

    public boolean createNewDevice(Device device) throws Exception {
        try {
            deviceCollection.insertOne(device);
            return true;
        } catch (MongoWriteException e) {
            log.error("Device not unique!");
            return false;
        }
    }

    public List<AltitudeReading> getAltitudeReadings(String imei) {
        try {
            List<AltitudeReading> docs = new ArrayList<AltitudeReading>() {
            };
            Block<AltitudeReading> readings = new Block<AltitudeReading>() {
                @Override
                public void apply(final AltitudeReading reading) {
                    docs.add(reading);
                }
            };
            altitudeDataCollection.find(
                    and(
                     eq("imei", imei),
                            eq("TYPE","ALT")
                    )).forEach(readings);
            log.info("Found: " + docs.size() + " readings.");
            return docs;
        } catch (NullPointerException e) {
            log.info("Did not find any devices.");
            e.printStackTrace();
            return null;
        }
    }

    public List<HumidityReading> getHumidityReadings(String imei) {
        try {
            List<HumidityReading> docs = new ArrayList<HumidityReading>() {
            };
            Block<HumidityReading> readings = new Block<HumidityReading>() {
                @Override
                public void apply(final HumidityReading reading) {
                    docs.add(reading);
                }
            };
            humidityDataCollection.find(
                    and(
                     eq("imei", imei),
                            eq("TYPE","HUM")
                    )).forEach(readings);
            log.info("Found: " + docs.size() + " readings.");
            return docs;
        } catch (NullPointerException e) {
            log.info("Did not find any devices.");
            e.printStackTrace();
            return null;
        }
    }

    public List<BatteryReading> getBatteryReadings(String imei) {
        try {
            List<BatteryReading> docs = new ArrayList<BatteryReading>() {
            };
            Block<BatteryReading> readings = new Block<BatteryReading>() {
                @Override
                public void apply(final BatteryReading reading) {
                    docs.add(reading);
                }
            };
            batteryDataCollection.find(
                    and(
                     eq("imei", imei),
                            eq("TYPE","BAT")
                    )).forEach(readings);
            log.info("Found: " + docs.size() + " readings.");
            return docs;
        } catch (NullPointerException e) {
            log.info("Did not find any devices.");
            e.printStackTrace();
            return null;
        }
    }

    public List<GPSReading> getGPSReadings(String imei) {
        try {
            List<GPSReading> docs = new ArrayList<GPSReading>() {
            };
            Block<GPSReading> readings = new Block<GPSReading>() {
                @Override
                public void apply(final GPSReading reading) {
                    docs.add(reading);
                }
            };
            gpsDataCollection.find(
                    and(
                     eq("imei", imei),
                            eq("TYPE","GPS")
                    )).forEach(readings);
            log.info("Found: " + docs.size() + " readings.");
            return docs;
        } catch (NullPointerException e) {
            log.info("Did not find any devices.");
            e.printStackTrace();
            return null;
        }
    }

    public List<LuxReading> getLuxReadings(String imei) {
        try {
            List<LuxReading> docs = new ArrayList<LuxReading>() {
            };
            Block<LuxReading> readings = new Block<LuxReading>() {
                @Override
                public void apply(final LuxReading reading) {
                    docs.add(reading);
                }
            };
            luxDataCollection.find(
                    and(
                     eq("imei", imei),
                            eq("TYPE","LUX")
                    )).forEach(readings);
            log.info("Found: " + docs.size() + " readings.");
            return docs;
        } catch (NullPointerException e) {
            log.info("Did not find any devices.");
            e.printStackTrace();
            return null;
        }
    }


    public boolean insertAltimeterReading(AltitudeReading reading) throws Exception {
        try {
            altitudeDataCollection.insertOne(reading);
            return true;
        } catch (MongoWriteException|NullPointerException e) {
            log.error("Inserting new sensor reading failed:" + e.getMessage());
            return false;
        }
    }

    public boolean insertBatteryReading(BatteryReading reading) throws Exception {
        try {
            batteryDataCollection.insertOne(reading);
            return true;
        } catch (MongoWriteException|NullPointerException e) {
            log.error("Inserting new sensor reading failed:" + e.getMessage());
            return false;
        }
    }

    public boolean insertGpsReading(GPSReading reading) throws Exception {
        try {
            gpsDataCollection.insertOne(reading);
            return true;
        } catch (MongoWriteException|NullPointerException e) {
            log.error("Inserting new sensor reading failed:" + e.getMessage());
            return false;
        }
    }

    public boolean insertHumidityReading(HumidityReading reading) throws Exception {
        try {
            humidityDataCollection.insertOne(reading);
            return true;
        } catch (MongoWriteException|NullPointerException e) {
            log.error("Inserting new sensor reading failed:" + e.getMessage());
            return false;
        }
    }
    public boolean insertLuxReading(LuxReading reading) throws Exception {
        try {
            luxDataCollection.insertOne(reading);
            return true;
        } catch (MongoWriteException|NullPointerException e) {
            log.error("Inserting new sensor reading failed:" + e.getMessage());
            return false;
        }
    }
}
