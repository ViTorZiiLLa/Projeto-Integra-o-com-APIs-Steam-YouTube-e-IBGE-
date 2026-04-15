/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import com.google.gson.Gson;
import jakarta.json.JsonObject;
import jakarta.json.stream.JsonParser;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.stream.Collectors;
import javax.net.ssl.HttpsURLConnection;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author vitor
 */
@WebServlet(urlPatterns = {"/YoutubeSearchServlet"})
public class YoutubeSearchServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet YoutubeSearchServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet YoutubeSearchServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        String API_KEY = "Coloque sua apiKey aqui";
        String channelName = request.getParameter("searchQuery");
        String apiUrl = "https://www.googleapis.com/youtube/v3/search?key=" + API_KEY + "&type=channel&q=" + channelName;

          try {
        URL url = new URL(apiUrl);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        JSONParser parser = new JSONParser();
        JSONObject jsonResponse = (JSONObject) parser.parse(new InputStreamReader(connection.getInputStream()));

        // Obtém o array "items" do JSON
        JSONArray itemsArray = (JSONArray) jsonResponse.get("items");

        // Configura o tipo de resposta e a codificação
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        // Escreve a resposta na página web
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Resultados da Pesquisa:</h1>");
        out.println("<ul>");

        // Itera sobre os itens do array
        for (Object item : itemsArray) {
            JSONObject channel = (JSONObject) ((JSONObject) item).get("id");
            String channelId = (String) channel.get("channelId");
            String channelUrl = "https://www.youtube.com/channel/" + channelId;
            JSONObject snippet = (JSONObject) ((JSONObject) item).get("snippet");
            String channelTitle = (String) snippet.get("title");
            out.println("<li><a href='" + channelUrl + "'>" + channelTitle + "</a></li>");
        }

        out.println("</ul>");
        out.println("</body></html>");
        out.close();
    } catch (Exception e) {
        e.printStackTrace();
        response.getWriter().println("Erro ao acessar a API do YouTube.");
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
