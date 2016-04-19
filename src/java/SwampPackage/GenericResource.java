/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SwampPackage;

import java.math.BigDecimal;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author iosdev
 */
@Path("generic")
public class GenericResource {

    // working path 23.227.190.85:8080/webApp155/path/generic/getTest
    EntityManagerFactory emf;
    EntityManager em;
    Location location;
    Gamemode gamemode;
    Beacon beacon;
    Hunt hunt;
    Clue clue;
    String text;
    List<Gamemode> gamemodeList;
    JsonObjectBuilder jsonObjectBuilder;
    JsonObjectBuilder jsonHeaderObjectBuilder;
    JsonObjectBuilder beaconObjectBuilder;
    JsonObjectBuilder beaconHeaderBuilder;
    JsonObjectBuilder clueObjectBuilder;
    JsonObjectBuilder clueHeaderBuilder;
    JsonObjectBuilder huntObjectBuilder;
    JsonObjectBuilder huntHeaderBuilder;
    JsonObjectBuilder locationObjectBuilder;
    JsonObjectBuilder locationHeaderBuilder;
    JsonArrayBuilder jsonArrayBuilder;
    JsonArrayBuilder clueArrayBuilder;
    JsonArrayBuilder huntArrayBuilder;
    JsonArrayBuilder locationArrayBuilder;
    JsonArrayBuilder beaconArrayBuilder;
    JsonObject clueObject;
    JsonObject jsonObject;
    JsonObject jsonObjectHead;
    JsonObject huntObject;
    JsonObject locationObject;
    JsonObject beaconObject;
    List<Hunt> huntList;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }

    @GET
    @Path("test")
    public String test() {
        return "mattimatti";
    }

    /**
     * Retrieves representation of an instance of SwampPackage.GenericResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    // @Produces(MediaType.APPLICATION_XML)
    @Produces("text/plain")
    public String getXml() {
        //TODO return proper representation object
        // throw new UnsupportedOperationException();
        return "matti";
    }

    /**
     * PUT method for updating or creating an instance of GenericResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }

    @GET
    @Path("getGameMode")
    @Produces("application/xml")
    public List<Gamemode> getGameModes() {
        createTransaction();
        // gamemode = (Gamemode) em.createNamedQuery("Gamemode.findByGamemodeid").setParameter("gamemodeid", 1).getSingleResult();
        gamemodeList = em.createNamedQuery("Gamemode.findAll").getResultList();
        endTransaction();
        return gamemodeList;
    }

    @GET
    @Path("getGamemodesJson")
    @Produces("application/json")
    public Response getGamemodesJson() {
        Response response;
        jsonObjectBuilder = Json.createObjectBuilder();
        jsonArrayBuilder = Json.createArrayBuilder();
        createTransaction();
        try {
            gamemodeList = em.createNamedQuery("Gamemode.findAll").getResultList();
            for (Gamemode gameMode : gamemodeList) {
                jsonObjectBuilder.add("id", gameMode.getGamemodeid());
                jsonObjectBuilder.add("title", gameMode.getTitle());
                jsonObjectBuilder.add("description", gameMode.getDescription());
                jsonArrayBuilder.add(jsonObjectBuilder.build());
            }
            response = Response.ok(jsonArrayBuilder.build()).build();
        } catch (Exception e) {
            jsonObjectBuilder.add("error", e.getMessage());
            response = Response.status(404).entity(jsonObjectBuilder.build()).build();
        }
        endTransaction();
        return response;
    }

    @GET
    @Path("getGamemodesNew")
    @Produces("application/json")
    public Response getGamemodesNew() {
        Response response;
        jsonObjectBuilder = Json.createObjectBuilder();
        jsonArrayBuilder = Json.createArrayBuilder();
        jsonHeaderObjectBuilder = Json.createObjectBuilder();
        createTransaction();
        try {
            gamemodeList = em.createNamedQuery("Gamemode.findAll").getResultList();
            for (Gamemode gameMode : gamemodeList) {
                jsonObjectBuilder.add("id", gameMode.getGamemodeid());
                jsonObjectBuilder.add("title", gameMode.getTitle());
                jsonObjectBuilder.add("description", gameMode.getDescription());
                jsonObject = jsonObjectBuilder.build();
                jsonArrayBuilder.add(jsonObject);
            }
            jsonHeaderObjectBuilder.add("GameModes", jsonArrayBuilder.build());
            jsonObjectHead = jsonHeaderObjectBuilder.build();
            response = Response.ok(jsonObjectHead).build();
        } catch (Exception e) {
            jsonObjectBuilder.add("error", e.getMessage());
            response = Response.status(404).entity(jsonObjectBuilder.build()).build();
        }
        endTransaction();
        return response;
    }

    @GET
    @Path("getAll")
    @Produces("application/json")
    public Response getAll() {
        Response response;
        jsonObjectBuilder = Json.createObjectBuilder();
        huntObjectBuilder = Json.createObjectBuilder();
        huntHeaderBuilder = Json.createObjectBuilder();
        beaconObjectBuilder = Json.createObjectBuilder();
        beaconHeaderBuilder = Json.createObjectBuilder();
        beaconArrayBuilder = Json.createArrayBuilder();
        huntArrayBuilder = Json.createArrayBuilder();
        locationObjectBuilder = Json.createObjectBuilder();
        locationHeaderBuilder = Json.createObjectBuilder();
        locationArrayBuilder = Json.createArrayBuilder();
        
        clueObjectBuilder = Json.createObjectBuilder();
        clueArrayBuilder = Json.createArrayBuilder();
        
        jsonArrayBuilder = Json.createArrayBuilder();
        jsonHeaderObjectBuilder = Json.createObjectBuilder();
        createTransaction();
        try {
            gamemodeList = em.createNamedQuery("Gamemode.findAll").getResultList();
            for (Gamemode gameMode : gamemodeList) {
                jsonObjectBuilder.add("id", gameMode.getGamemodeid());
                jsonObjectBuilder.add("title", gameMode.getTitle());
                jsonObjectBuilder.add("description", gameMode.getDescription());
                for (Hunt hunt : gameMode.getHuntCollection()) {
                    huntObjectBuilder.add("GameModeId", gameMode.getGamemodeid());
                    huntObjectBuilder.add("List size", gameMode.getHuntCollection().size());
                    huntObjectBuilder.add("id", hunt.getHuntid());
                    huntObjectBuilder.add("title", hunt.getTitle());
                    huntObjectBuilder.add("description", hunt.getDescription());
                    huntObjectBuilder.add("winTitle", hunt.getWintitle());
                    huntObjectBuilder.add("winDescription", hunt.getWindescription());
                    for (Location location : hunt.getLocationCollection()) {
                        locationObjectBuilder.add("id", location.getLocationid());
                        locationObjectBuilder.add("winTitle", location.getWintitle());
                        locationObjectBuilder.add("winDescription", location.getWindescription());
                        beacon = (Beacon) em.createNamedQuery("Beacon.findByBeaconid").setParameter("beaconid", location.getBeaconid().getBeaconid()).getSingleResult();
                        beaconObjectBuilder.add("beaconid", beacon.getBeaconid());
                        beaconObjectBuilder.add("uuid", beacon.getUuid());
                        beaconObjectBuilder.add("major", beacon.getMajor());
                        beaconObjectBuilder.add("minor", beacon.getMinor());
                        beaconObject = beaconObjectBuilder.build();
                        beaconArrayBuilder.add(beaconObject);
                        for (Clue clue : location.getClueCollection()) {
                            clueObjectBuilder.add("id", clue.getClueid());
                            clueObjectBuilder.add("title", clue.getCluetitle());
                            clueObjectBuilder.add("description", clue.getCluedescription());
                            clueObject = clueObjectBuilder.build();
                            clueArrayBuilder.add(clueObject);
                        }
                        locationObjectBuilder.add("Clues", clueArrayBuilder.build());
                        clueArrayBuilder = Json.createArrayBuilder();
                        locationObjectBuilder.add("Beacon", beaconArrayBuilder.build());
                        beaconArrayBuilder = Json.createArrayBuilder();
                        locationObject = locationObjectBuilder.build();
                        locationArrayBuilder.add(locationObject);
                    }
                    huntObjectBuilder.add("Locations", locationArrayBuilder.build());
                    locationArrayBuilder = Json.createArrayBuilder();
                    huntObject = huntObjectBuilder.build();
                    huntArrayBuilder.add(huntObject);
                }
                jsonObjectBuilder.add("Hunts", huntArrayBuilder.build());
                huntArrayBuilder = Json.createArrayBuilder();

                jsonObject = jsonObjectBuilder.build();
                jsonArrayBuilder.add(jsonObject);
            }
            jsonHeaderObjectBuilder.add("GameModes", jsonArrayBuilder.build());
            jsonObjectHead = jsonHeaderObjectBuilder.build();
            response = Response.ok(jsonObjectHead).build();
        } catch (Exception e) {
            jsonObjectBuilder.add("error", e.getMessage());
            response = Response.status(404).entity(jsonObjectBuilder.build()).build();
        }
        endTransaction();
        return response;
    }

    @GET
    @Path("getHuntsByGamemodeId/{id}")
    @Produces("application/json")
    public Response getHuntsByGamemodeId(@PathParam("id") String gamemodeid) {
        Response response;
        jsonObjectBuilder = Json.createObjectBuilder();
        jsonArrayBuilder = Json.createArrayBuilder();
        createTransaction();
        try {
            gamemode = (Gamemode) em.createNamedQuery("Gamemode.findByGamemodeid").setParameter("gamemodeid", Integer.parseInt(gamemodeid)).getSingleResult();
            for (Hunt hunt : gamemode.getHuntCollection()) {
                jsonObjectBuilder.add("id", hunt.getHuntid());
                jsonObjectBuilder.add("title", hunt.getTitle());
                jsonObjectBuilder.add("description", hunt.getDescription());
                jsonObjectBuilder.add("winTitle", hunt.getWintitle());
                jsonObjectBuilder.add("winDescription", hunt.getWindescription());
                jsonArrayBuilder.add(jsonObjectBuilder.build());
            }
            response = Response.ok(jsonArrayBuilder.build()).build();
        } catch (Exception e) {
            jsonObjectBuilder.add("error", e.getMessage());
            response = Response.status(404).entity(jsonObjectBuilder.build()).build();
        }
        endTransaction();
        return response;
    }

    @GET
    @Path("getLocationByHuntId/{id}")
    @Produces("application/json")
    public Response getLocationByHuntId(@PathParam("id") String huntid) {
        Response response;
        jsonObjectBuilder = Json.createObjectBuilder();
        jsonArrayBuilder = Json.createArrayBuilder();
        createTransaction();
        try {
            hunt = (Hunt) em.createNamedQuery("Hunt.findByHuntid").setParameter("huntid", Integer.parseInt(huntid)).getSingleResult();
            for (Location location : hunt.getLocationCollection()) {
                jsonObjectBuilder.add("id", location.getLocationid());
                jsonObjectBuilder.add("winTitle", location.getWintitle());
                jsonObjectBuilder.add("winDescription", location.getWindescription());
                beacon = (Beacon) em.createNamedQuery("Beacon.findByBeaconid").setParameter("beaconid", 1).getSingleResult();
                jsonObjectBuilder.add("beaconId", beacon.getBeaconid());
                jsonObjectBuilder.add("uuid", beacon.getUuid());
                jsonObjectBuilder.add("major", beacon.getMajor());
                jsonObjectBuilder.add("minor", beacon.getMinor());
                jsonArrayBuilder.add(jsonObjectBuilder.build());
            }
            response = Response.ok(jsonArrayBuilder.build()).build();
        } catch (Exception e) {
            // jsonObjectBuilder.add("error", e.getMessage());
            jsonObjectBuilder.add("error!", "the error is here  " + e + " AND here maybe " + e.getMessage());
            response = Response.status(404).entity(jsonObjectBuilder.build()).build();
        }
        endTransaction();
        return response;
    }

    @GET
    @Path("getCluesByLocationId/{id}")
    @Produces("application/json")
    public Response getCluesByLocationId(@PathParam("id") String locationid) {
        Response response;
        jsonObjectBuilder = Json.createObjectBuilder();
        jsonArrayBuilder = Json.createArrayBuilder();
        createTransaction();
        try {
            location = (Location) em.createNamedQuery("Location.findByLocationid").setParameter("locationid", Integer.parseInt(locationid)).getSingleResult();
            for (Clue clue : location.getClueCollection()) {
                jsonObjectBuilder.add("id", clue.getClueid());
                jsonObjectBuilder.add("title", clue.getCluetitle());
                jsonObjectBuilder.add("description", clue.getCluedescription());
                jsonArrayBuilder.add(jsonObjectBuilder.build());
            }
            response = Response.ok(jsonArrayBuilder.build()).build();
        } catch (Exception e) {
            jsonObjectBuilder.add("error!", "the error is here  " + e + " AND here maybe " + e.getMessage());
            response = Response.status(404).entity(jsonObjectBuilder.build()).build();
        }
        endTransaction();
        return response;
    }

    @GET
    @Path("huntTest")
    @Produces("text/plain")
    public int huntTest() {
        createTransaction();
        hunt = (Hunt) em.createNamedQuery("Hunt.findByHuntid").setParameter("huntid", 1).getSingleResult();
        endTransaction();
        return hunt.getLocationCollection().size();
    }

    @GET
    @Path("getTest")
    public int getTest() {
        createTransaction();
        location = (Location) em.createNamedQuery("Location.findByLocationid").setParameter("locationid", 1).getSingleResult();
        //return location.getWintitle();
        endTransaction();
        return location.getClueCollection().size();
    }

    public void createTransaction() {
        emf = Persistence.createEntityManagerFactory("webAppPU");
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }

    public void endTransaction() {
        em.getTransaction().commit();
        emf.close();
    }

}
