package com.rsostream.crud.resources;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kumuluz.ee.logs.cdi.Log;
import com.rsostream.crud.models.Device;
import com.rsostream.crud.models.sensorReadings.SensorReading;
import com.rsostream.crud.services.ServiceMongoDB;
import com.rsostream.crud.util.GsonUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.lang.reflect.Type;
import java.util.List;

@Log
@Path("/device")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class DeviceResource {

    @Inject
    private ServiceMongoDB serviceMongoDB;

    private Gson serializer = GsonUtils.getGson();

    @GET
    public Response getDevices() {
        List<Device> devices = serviceMongoDB.findAll();
        if(devices == null) {
            return Response.status(400).build();
        }
        Type listOfDevices = new TypeToken<List<Device>>(){}.getType();
        return Response.ok(serializer.toJson(devices, listOfDevices)).build();
    }

    @GET
    @Path("/{imei}")
    public Response getDevices(@PathParam("imei") String imei) {
        Device device = serviceMongoDB.findByIMEI(imei);
        if(device == null) {
            return Response.status(400).build();
        }
        String response = serializer.toJson(device);
        return Response.ok(response).build();
    }

    @POST
    public Response insertDevice(Device device) {
        try {
            if (serviceMongoDB.createNewDevice(device)) {
                return Response.ok(device).build();
            } else {
                return Response.status(400).build();
            }
        } catch (Exception e) {
            return Response.status(500).build();
        }
    }
}
