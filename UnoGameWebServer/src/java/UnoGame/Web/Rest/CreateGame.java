/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UnoGame.Web.Rest;

import java.io.IOException;
import java.util.UUID;
import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uno.Game;

/**
 *
 * @author Moe Kyaw Kyaw
 */
public class CreateGame extends HttpServlet{
     @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {

        String title = req.getParameter("title");
        String gId = UUID.randomUUID().toString().substring(0, 8);
        Game unoGame = new Game(gId, title, "waiting");
       // GlobalHashMap.hm.put(gId, unoGame);

        JsonObject json = Json.createObjectBuilder()
                .add("gId", unoGame.getGameid())
                .add("title", unoGame.getName())
                .add("status", unoGame.getStatus()).build();
        System.out.println(">>>Json: " + json.toString());

       // resp.setStatus(HttpServletResponse.SC_OK);       
        //resp.setContentType("application/json");
        resp.setStatus(200);

    }

}
