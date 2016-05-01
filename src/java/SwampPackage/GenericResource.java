/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SwampPackage;

import java.math.BigDecimal;
import java.util.List;
import javax.json.Json;
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
import javax.ws.rs.POST;
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
    User newUser;
    User user;
    CompletedHunt completedHunt;
    String text;
    Integer userScore;
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
    JsonObjectBuilder scoresObjectBuilder;
    JsonObjectBuilder scoresHeaderObectBuilder;
    JsonArrayBuilder scoresArrayBuilder;
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
    List<Gamemode> gamemodeList;
    List<Hunt> huntList;
    List<User> userList;

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
    @Path("addUser/{username}/{password}/{media}/{description}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces("application/json")
    public Response addUser(@PathParam("username") String username,
            @PathParam("description") String desc,
            @PathParam("password") String password,
            @PathParam("media") String media) {
        Response response;
        createTransaction();
        jsonObjectBuilder = Json.createObjectBuilder();
        this.userList = em.createNamedQuery("User.findAll").getResultList();
        for (User u : this.userList) {
            if (u.getUsername().equalsIgnoreCase(username)) {
                jsonObjectBuilder.add("User exists", "The user already exists");
                response = Response.status(402).entity(jsonObjectBuilder.build()).build();
                endTransaction();
                return response;
            }
        }
        newUser = new User();
        newUser.setUsername(username);
        newUser.setUserpass(password);
        newUser.setUsermedia("http://23.227.190.85/hasseman.jpg");
        newUser.setUserdescription(desc);
        em.persist(newUser);
        user = (User) em.createNamedQuery("User.findByUsername").setParameter("username", username).getSingleResult();
        jsonObjectBuilder.add("OK", user.getUserid());
        response = Response.status(200).entity(jsonObjectBuilder.build()).build();
        endTransaction();
        return response;
    }

    @GET
    @Path("checkPassword/{username}/{password}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces("application/json")
    public Response checkPassword(@PathParam("username") String username, @PathParam("password") String password) {
        Response response;
        createTransaction();
        jsonObjectBuilder = Json.createObjectBuilder();
        jsonArrayBuilder = Json.createArrayBuilder();
        jsonHeaderObjectBuilder = Json.createObjectBuilder();
        this.userList = em.createNamedQuery("User.findAll").getResultList();
        for (User u : this.userList) {
            if (u.getUsername().equals(username) && u.getUserpass().equals(password)) {
                jsonObjectBuilder.add("id", u.getUserid());
                jsonObjectBuilder.add("username", u.getUsername());
                jsonObjectBuilder.add("description", u.getUserdescription());
                jsonObjectBuilder.add("media", u.getUsermedia());
                jsonHeaderObjectBuilder.add("user", jsonArrayBuilder.add(jsonObjectBuilder.build()).build());

                response = Response.status(200).entity(jsonHeaderObjectBuilder.build()).build();
                endTransaction();
                return response;
            }
        }
        endTransaction();
        jsonObjectBuilder.add("error", "Info doesn't match");
        response = Response.status(402).entity(jsonObjectBuilder.build()).build();
        return response;
    }

    /* @GET
    @Path("getUserData/{userid}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces("application/json")
    public Response getUserData(@PathParam("userid") String userid) {
        Response response;
        jsonObjectBuilder = Json.createObjectBuilder();
        this.user = (User) em.createNamedQuery("User.findByUserid").setParameter("userid", Integer.parseInt(userid)).getSingleResult();
        jsonObjectBuilder.add("", BigDecimal.ONE)
        
        return response;
    } */
    @GET
    @Path("saveScore/{userId}/{huntId}/{points}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces("application/json")
    public Response saveScore(@PathParam("userId") String userid, @PathParam("huntId") String huntid, @PathParam("points") String points) {
        Response response;
        jsonObjectBuilder = Json.createObjectBuilder();
        createTransaction();
        user = (User) em.createNamedQuery("User.findByUserid").setParameter("userid", Integer.parseInt(userid)).getSingleResult();
        hunt = (Hunt) em.createNamedQuery("Hunt.findByHuntid").setParameter("huntid", Integer.parseInt(huntid)).getSingleResult();
        completedHunt = new CompletedHunt();
        completedHunt.setUserid(user);
        completedHunt.setHuntid(hunt);
        completedHunt.setPoints(Integer.parseInt(points));
        em.persist(completedHunt);
        jsonObjectBuilder.add("OK", "Saved points");
        response = Response.status(200).entity(jsonObjectBuilder.build()).build();
        endTransaction();
        return response;
    }

    @GET
    @Path("getUserScores")
    @Produces("application/json")
    public Response getUserScores() {
        jsonObjectBuilder = Json.createObjectBuilder();
        jsonHeaderObjectBuilder = Json.createObjectBuilder();
        jsonArrayBuilder = Json.createArrayBuilder();
        createTransaction();
        userList = em.createNamedQuery("User.findAll").getResultList();
        for (User u : userList) {
            userScore = 0;
            for (CompletedHunt compHunt : u.getCompletedHuntCollection()) {
                userScore += compHunt.getPoints();
            }
            if (userScore > 0) {
                jsonObjectBuilder.add("id", u.getUserid());
                jsonObjectBuilder.add("username", u.getUsername());
                jsonObjectBuilder.add("description", u.getUserdescription());
                jsonObjectBuilder.add("media", u.getUsermedia());
                jsonObjectBuilder.add("score", userScore);
                jsonArrayBuilder.add(jsonObjectBuilder.build());
                jsonObjectBuilder = Json.createObjectBuilder();
            }
        }
        jsonHeaderObjectBuilder.add("users", jsonArrayBuilder.build());
        endTransaction();
        return Response.ok(jsonHeaderObjectBuilder.build()).build();
    }

    @GET
    @Path("getHuntScoreByUserId/{userId}/{huntId}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces("application/json")
    public Response getHuntScoreByUserId(@PathParam("userId") String userid, @PathParam("huntId") String huntid) {
        Response response;
        jsonObjectBuilder = Json.createObjectBuilder();
        createTransaction();
        user = (User) em.createNamedQuery("User.findByUserid").setParameter("userid", Integer.parseInt(userid)).getSingleResult();
        for (CompletedHunt huntComplete : user.getCompletedHuntCollection()) {
            if (huntComplete.getHuntid().getHuntid() == Integer.parseInt(huntid)) {
                jsonObjectBuilder.add("points", huntComplete.getPoints());
                return Response.ok(jsonObjectBuilder.build()).build();
            }
        }
        response = Response.ok("No score").build();
        endTransaction();
        return response;
    }

    @GET
    @Path("getHuntScores/{huntId}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces("application/json")
    public Response getHuntScores(@PathParam("huntId") String huntid) {
        Response response;
        jsonObjectBuilder = Json.createObjectBuilder();
        jsonArrayBuilder = Json.createArrayBuilder();
        createTransaction();
        hunt = (Hunt) em.createNamedQuery("Hunt.findByHuntid").setParameter("huntid", Integer.parseInt(huntid)).getSingleResult();
        for (CompletedHunt huntComplete : hunt.getCompletedHuntCollection()) {
            jsonObjectBuilder.add(huntComplete.getUserid().getUsername(), huntComplete.getPoints());
            jsonArrayBuilder.add(jsonObjectBuilder.build());
            jsonObjectBuilder = Json.createObjectBuilder();
        }
        response = Response.ok(jsonArrayBuilder.build()).build();
        endTransaction();
        return response;
    }

    // get all necessary data to iniatiate the game
    @GET
    @Path("getAll")
    @Produces("application/json")
    public Response getAll() {
        Response response;
        jsonObjectBuilder = Json.createObjectBuilder();
        huntObjectBuilder = Json.createObjectBuilder();
        huntHeaderBuilder = Json.createObjectBuilder();
        scoresObjectBuilder = Json.createObjectBuilder();
        scoresHeaderObectBuilder = Json.createObjectBuilder();
        scoresArrayBuilder = Json.createArrayBuilder();
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
                jsonObjectBuilder.add("media", gameMode.getMedia());
                for (Hunt hunt : gameMode.getHuntCollection()) {
                    huntObjectBuilder.add("id", hunt.getHuntid());
                    huntObjectBuilder.add("title", hunt.getTitle());
                    huntObjectBuilder.add("description", hunt.getDescription());
                    huntObjectBuilder.add("winTitle", hunt.getWintitle());
                    huntObjectBuilder.add("winDescription", hunt.getWindescription());
                    huntObjectBuilder.add("media", hunt.getMedia());
                    for (CompletedHunt scores : hunt.getCompletedHuntCollection()) {
                        scoresObjectBuilder.add("username", scores.getUserid().getUsername());
                        scoresObjectBuilder.add("score", scores.getPoints());
                        scoresArrayBuilder.add(scoresObjectBuilder.build());
                    }
                    for (Location location : hunt.getLocationCollection()) {
                        locationObjectBuilder.add("id", location.getLocationid());
                        locationObjectBuilder.add("title", location.getTitle());
                        locationObjectBuilder.add("media", location.getMedia());
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
                            clueObjectBuilder.add("media", clue.getMedia());
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
                    huntObjectBuilder.add("Scores", scoresArrayBuilder.build());
                    scoresArrayBuilder = Json.createArrayBuilder();
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
