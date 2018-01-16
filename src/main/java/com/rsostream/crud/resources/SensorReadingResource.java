package com.rsostream.crud.resources;

import com.kumuluz.ee.logs.LogManager;
import com.kumuluz.ee.logs.Logger;
import com.kumuluz.ee.logs.cdi.Log;
import com.rsostream.crud.models.sensorReadings.*;
import com.rsostream.crud.services.ServiceMongoDB;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Log
@Path("/reading")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class SensorReadingResource {
    private static final Logger log = LogManager.getLogger(SensorReadingResource.class.getName());

    @Inject
    ServiceMongoDB serviceMongoDB;

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
}
