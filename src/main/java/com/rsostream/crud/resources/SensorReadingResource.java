package com.rsostream.crud.resources;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kumuluz.ee.logs.LogManager;
import com.kumuluz.ee.logs.Logger;
import com.kumuluz.ee.logs.cdi.Log;
import com.rsostream.crud.models.sensorReadings.*;
import com.rsostream.crud.services.ServiceMongoDB;
import com.rsostream.crud.util.GsonUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.lang.reflect.Type;
import java.util.List;

@Log
@Path("/reading")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class SensorReadingResource {
    private static final Logger log = LogManager.getLogger(SensorReadingResource.class.getName());

    @Inject
    ServiceMongoDB serviceMongoDB;

    private Gson serializer = GsonUtils.getGson();

    @GET
    @Path("/{imei}")
    public Response getSensorReadingsByDeviceId(@PathParam("imei") String imei) {
        return Response.ok().build();
    }

    @POST
    @Path("/lux")
    public Response insertSensorReading(LuxReading sensorReading) {
        try {
            log.info(sensorReading.toString());
            serviceMongoDB.insertLuxReading(sensorReading);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return Response.status(500).build();
        }
        return Response.ok(sensorReading).build();
    }

    @GET
    @Path("/lux/{imei}")
    public Response getLuxReadings(@PathParam("imei") String imei) {
        List<LuxReading> readings;
        try {
            log.info("Obtaining altitude readings for device " + imei);
            readings = serviceMongoDB.getLuxReadings(imei);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return Response.status(500).build();
        }
        Type listOfDevices = new TypeToken<List<LuxReading>>(){}.getType();
        if(readings != null) {
            return Response.ok(serializer.toJson(readings, listOfDevices)).build();
        }
        return Response.noContent().build();
    }

    @Log
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/gps")
    public Response insertSensorReading(GPSReading sensorReading) {
        try {
            log.info(sensorReading.toString());
            serviceMongoDB.insertGpsReading((GPSReading) sensorReading);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return Response.status(500).build();
        }
        return Response.ok(sensorReading).build();
    }

    @GET
    @Path("/gps/{imei}")
    public Response getGPSReadings(@PathParam("imei") String imei) {
        List<GPSReading> readings;
        try {
            log.info("Obtaining altitude readings for device " + imei);
            readings = serviceMongoDB.getGPSReadings(imei);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return Response.status(500).build();
        }
        Type listOfDevices = new TypeToken<List<GPSReading>>(){}.getType();
        if(readings != null) {
            return Response.ok(serializer.toJson(readings, listOfDevices)).build();
        }
        return Response.noContent().build();
    }


    @POST
    @Path("/alt")
    public Response insertSensorReading(AltitudeReading sensorReading) {
        try {
            log.info(sensorReading.toString());
            serviceMongoDB.insertAltimeterReading(sensorReading);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return Response.status(500).build();
        }
        return Response.ok(sensorReading).build();
    }

    @GET
    @Path("/alt/{imei}")
    public Response getAltReadings(@PathParam("imei") String imei) {
        List<AltitudeReading> readings;
        try {
            log.info("Obtaining altitude readings for device " + imei);
            readings = serviceMongoDB.getAltitudeReadings(imei);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return Response.status(500).build();
        }
        Type listOfDevices = new TypeToken<List<AltitudeReading>>(){}.getType();
        if(readings != null) {
            return Response.ok(serializer.toJson(readings, listOfDevices)).build();
        }
        return Response.noContent().build();
    }

    @POST
    @Path("/hum")
    public Response insertSensorReading(HumidityReading sensorReading) {
        try {
            log.info(sensorReading.toString());
            serviceMongoDB.insertHumidityReading(sensorReading);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return Response.status(500).build();
        }
        return Response.ok(sensorReading).build();
    }

    @GET
    @Path("/hum/{imei}")
    public Response getHumidityReadings(@PathParam("imei") String imei) {
        List<HumidityReading> readings;
        try {
            log.info("Obtaining altitude readings for device " + imei);
            readings = serviceMongoDB.getHumidityReadings(imei);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return Response.status(500).build();
        }
        Type listOfDevices = new TypeToken<List<HumidityReading>>(){}.getType();
        if(readings != null) {
            return Response.ok(serializer.toJson(readings, listOfDevices)).build();
        }
        return Response.noContent().build();
    }

    @POST
    @Path("/bat")
    public Response insertSensorReading(BatteryReading sensorReading) {
        try {
            log.info(sensorReading.toString());
            serviceMongoDB.insertBatteryReading(sensorReading);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return Response.status(500).build();
        }
        return Response.ok(sensorReading).build();
    }

    @GET
    @Path("/bat/{imei}")
    public Response getBatteryReadings(@PathParam("imei") String imei) {
        List<BatteryReading> readings;
        try {
            log.info("Obtaining altitude readings for device " + imei);
            readings = serviceMongoDB.getBatteryReadings(imei);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return Response.status(500).build();
        }
        Type listOfDevices = new TypeToken<List<BatteryReading>>(){}.getType();
        if(readings != null) {
            return Response.ok(serializer.toJson(readings, listOfDevices)).build();
        }
        return Response.noContent().build();
    }
}
