/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UnoGame.Web.Rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import uno.Game;
import uno.Player;
import uno.Card;

@RequestScoped
@Path("/game")
public class UnoGame {

    public static String currentGameId = "";
    public static String currentPlayerId = "";
    public static Player player = null;
    // public static String strdiscardcard;
    private final String clientlhostport = "http://localhost:8383";

    public static Map<String, Game> gamelist = new HashMap<String, Game>();

    /*  To create new Game*/
    @POST
    @Path("/creategame")
    @Produces("application/json")
    public Response create(@FormParam("title") String name) {

        String strgname = name;
        String strgid = UUID.randomUUID().toString().substring(0, 8);
        currentGameId = strgid;

        Game g = new Game(strgid, strgname, "waiting");

        gamelist.put(currentGameId, g);
        try {
            URI location = new java.net.URI(clientlhostport + "/UnoGameWebClient/NewGameStart.html");
            return Response.seeOther(location).build();
        } catch (URISyntaxException ex) {
            return Response.status(404).entity("Redirect Fail").build();
        }

    }

    /*  To get Game Id and Title to show in start game(creater view)*/
    @GET
    @Path("/getstartgame")
    @Produces("text/json")
    public Response newGame() {
        JsonObject jso;
        //JsonArrayBuilder jsa = Json.createArrayBuilder();

        Game g = gamelist.get(currentGameId);

        jso = Json.createObjectBuilder()
                .add("gid", g.getGameid())
                .add("name", g.getName())
                .add("status", g.getStatus())
                .build();

        Response resp = Response.ok(jso.toString())
                .header("Access-Control-Allow-Origin", "*")
                .build();
        return resp;
    }

    /* To get waiting player to start game*/
    @GET
    @Path("/getwaitingplayer")
    @Produces("text/json")
    public Response waitingplayer() {
        JsonObject jso;
        //JsonArrayBuilder jsa = Json.createArrayBuilder();

        Game g = gamelist.get(currentGameId);

        jso = Json.createObjectBuilder()
                .add("gid", g.getGameid())
                .add("gname", g.getName())
                .add("playerId", currentPlayerId)
                .build();

        Response resp = Response.ok(jso.toString())
                .header("Access-Control-Allow-Origin", "*")
                .build();
        return resp;
    }

    /*  To get Game list*/
    @GET
    @Path("/getgame")
    @Produces("application/json")
    public Response getgame() {
        JsonObject jso;
        JsonArrayBuilder jsa = Json.createArrayBuilder();

        Iterator entries = gamelist.entrySet().iterator();
        while (entries.hasNext()) {
            Entry thisEntry = (Entry) entries.next();
            Object key = thisEntry.getKey();
            Object value = thisEntry.getValue();

            jso = Json.createObjectBuilder()
                    .add("gid", thisEntry.getKey().toString())
                    .add("title", ((Game) thisEntry.getValue()).getName())
                    .add("status", ((Game) thisEntry.getValue()).getStatus())
                    .build();

            jsa.add(jso);
        }

        Response resp = Response.ok(jsa.build().toString())
                .header("Access-Control-Allow-Origin", "*")
                .build();
        return resp;
    }

    /*  To create new player(join to game)*/
    @POST
    @Path("/createplayer")
    @Produces("application/json")
    public Response createplayer(@FormParam("gameId") String gameId, @FormParam("playerName") String playerName) {

        currentGameId = gameId;
        String strplayername = playerName;
        String playerId = UUID.randomUUID().toString().substring(0, 8);
        Player p = new Player(playerId, strplayername);
        currentPlayerId = playerId;
        gamelist.get(currentGameId).AddPlayer(p);
        System.out.println(currentGameId + "," + p);
        try {
            URI location = new java.net.URI(clientlhostport + "/UnoGameWebClient/waitingforplayers.html");
            return Response.seeOther(location).build();
        } catch (URISyntaxException ex) {
            return Response.status(404).entity("Redirect Fail").build();
        }

    }

    /*  To get players list for each game*/
    @GET
    @Path("/getplayer")
    @Produces("application/json")
    public Response getplayer() {
        JsonObject jso;
        JsonArrayBuilder jsa = Json.createArrayBuilder();

        Game g = gamelist.get(currentGameId);
        List<Player> pList = g.getListofplayers();

        for (int i = 0; i < pList.size(); i++) {
            Player player = pList.get(i);
            jso = Json.createObjectBuilder()
                    .add("gameId", currentGameId)
                    .add("gameName", g.getName())
                    .add("playerId", player.getPlayerid())
                    .add("playerName", player.getName())
                    .build();
            jsa.add(jso);

        }
        Response resp = Response.ok(jsa.build().toString())
                .header("Access-Control-Allow-Origin", "*")
                .build();
        return resp;
    }

    /* To get discard card*/
    @GET
    @Path("/discardcard")
    @Produces("text/plain")
    public Response showTableCards() {
        JsonObject jso;

        Game g = gamelist.get(currentGameId);

        Card c = g.DiscardCard();

        jso = Json.createObjectBuilder()
                .add("gid", g.getGameid())
                .add("gname", g.getName())
                .add("color", c.getColor())
                .add("type", c.getType())
                .add("value", c.getValue())
                .add("image", c.getImage())
                .build();

        Response resp = Response.ok(jso.toString())
                .header("Access-Control-Allow-Origin", "*")
                .build();
        return resp;
    }

    /*  To start Table Game(distribute cards)*/
    @POST
    @Path("/tablegamestart")
    @Produces("application/json")
    public Response StartTableGame(@FormParam("startgameId") String startgameId) {

        currentGameId = startgameId;

        // String strcurrentpid = playerId;
        Game g = gamelist.get(currentGameId);

        g.CreateGame();
        g.DistributeCard();

//        Card c = g.DiscardCard();
//        strdiscardcard=c.getImage();
        List<Player> p = g.getListofplayers();

        for (int i = 0; i < p.size(); i++) {
            player = p.get(i);
            if (player.getPlayerid().equals(currentPlayerId)) {
                break;
            } else {
                player = null;
            }
        }
        try {
            URI location = new java.net.URI(clientlhostport + "/UnoGameWebClient/playerlist.html");
            return Response.seeOther(location).build();
        } catch (URISyntaxException ex) {
            return Response.status(404).entity("Redirect Fail").build();
        }

    }

    /*  To start Player Game*/
    @POST
    @Path("/playergamestart")
    @Produces("text/plain")
    public Response startPlayer(@FormParam("gid") String gid, @FormParam("pid") String pid) {

        currentGameId = gid;
        currentPlayerId = pid;        

        try {
            URI location = new java.net.URI(clientlhostport + "/UnoGameWebClient/playercards.html");
            return Response.seeOther(location).build();
        } catch (URISyntaxException ex) {
            return Response.status(404).entity("Redirect Fail").build();
        }
    }


    /*  To get distributed cards(7cards) for each player*/
    @GET
    @Path("/getcard")
    @Produces("application/json")
    public Response showPlayCards() {
        JsonObject jso;
        JsonArrayBuilder jsa = Json.createArrayBuilder();
        Player player = null;
        Game g = gamelist.get(currentGameId);
        List<Player> p = g.getListofplayers();

        for (int i = 0; i < p.size(); i++) {
            player = p.get(i);
            if (player.getPlayerid().equals(currentPlayerId)) {
                break;
            } else {
                player = null;
            }
        }
        List<Card> cardinhand = player.getCardinhandlist();

        for (int i = 0; i < cardinhand.size(); i++) {
            Card card = cardinhand.get(i);

            jso = Json.createObjectBuilder()
                    .add("color", card.getColor())
                    .add("type", card.getType())
                    .add("value", card.getValue())
                    .add("image", card.getImage())
                    .build();

            jsa.add(jso);

        }

        Response resp = Response.ok(jsa.build().toString())
                .header("Access-Control-Allow-Origin", "*")
                .build();

        return resp;
    }

}
