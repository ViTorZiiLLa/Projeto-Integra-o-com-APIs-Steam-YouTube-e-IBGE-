/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author vitor
 */
@WebServlet(urlPatterns = {"/SteamProfileServlet"})
public class SteamProfileServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String apiKey = "Coloque a sua apiKey";
        // ID do usuário da Steam
        String steamId = request.getParameter("steamId");
        // URL da API da Steam para obter informações do perfil
        String apiUrl = "http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?key=" + apiKey + "&steamids=" + steamId;
        try{
            // Faz a requisição HTTP para a API da Steam
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            // Lê a resposta da API
            Scanner scanner = new Scanner(url.openStream());
            StringBuilder responseBuilder = new StringBuilder();
            while (scanner.hasNext()) {
                responseBuilder.append(scanner.nextLine());
            }
            scanner.close();

            // Analisa a resposta JSON
            JSONParser parser = new JSONParser();
            JSONObject jsonResponse = (JSONObject) parser.parse(responseBuilder.toString());
            JSONObject responseObj = (JSONObject) jsonResponse.get("response");
            // Obtém o array "players" do objeto "response"
            JSONArray playersArray = (JSONArray) responseObj.get("players");

            // Obtém o primeiro objeto do array (índice 0)
            Object firstPlayerObject = playersArray.get(0);

            // Converte o objeto para JSONObject
            JSONObject player = (JSONObject) firstPlayerObject;

            // Configura o tipo de resposta e o codificação
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");

            // Escreve a resposta na página web
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h1>Perfil da Steam:</h1>");
            out.println("<p>Nome: " + player.get("personaname") + "</p>");
            out.println("<p>URL do Perfil: <a href='" + player.get("profileurl") + "'>" + player.get("profileurl") + "</a></p>");
            out.println("<p>Status: " + player.get("personastate") + "</p>");
            out.println("</body></html>");
            out.close();
        }
        catch (Exception e){
            e.printStackTrace();
            response.getWriter().println("Erro ao obter perfil da Steam.");
            
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
