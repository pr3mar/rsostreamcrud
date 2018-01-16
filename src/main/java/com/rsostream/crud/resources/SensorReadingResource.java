package com.rsostream.crud.resources;

import com.kumuluz.ee.logs.LogManager;
import com.kumuluz.ee.logs.Logger;
import com.kumuluz.ee.logs.cdi.Log;
import com.rsostream.crud.models.sensorReadings.*;
import com.rsostream.crud.services.ServiceMongoDB;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
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
            serviceMongoDB.insertNewReading(sensorReading);
        } catch (Exception e) {
            return Response.status(500).build();
        }
        return Response.ok(sensorReading).build();
    }

    @POST
    @Path("/gps")
    public Response insertSensorReading(GPSReading sensorReading) {
        try {
            log.info(sensorReading.toString());
            serviceMongoDB.insertNewReading(sensorReading);
        } catch (Exception e) {
            return Response.status(500).build();
        }
        return Response.ok(sensorReading).build();
    }

    @POST
    @Path("/alt")
    public Response insertSensorReading(AltitudeReading sensorReading) {
        try {
            log.info(sensorReading.toString());
            serviceMongoDB.insertNewReading(sensorReading);
        } catch (Exception e) {
            return Response.status(500).build();
        }
        return Response.ok(sensorReading).build();
    }

    @POST
    @Path("/hum")
    public Response insertSensorReading(HumidityReading sensorReading) {
        try {
            log.info(sensorReading.toString());
            serviceMongoDB.insertNewReading(sensorReading);
        } catch (Exception e) {
            return Response.status(500).build();
        }
        return Response.ok(sensorReading).build();
    }

    @POST
    @Path("/bat")
    public Response insertSensorReading(BatteryReading sensorReading) {
        try {
            log.info(sensorReading.toString());
            serviceMongoDB.insertNewReading(sensorReading);
        } catch (Exception e) {
            return Response.status(500).build();
        }
        return Response.ok(sensorReading).build();
    }
}
