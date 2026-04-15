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

/**
 *
 * @author vitor
 */
@WebServlet(urlPatterns = {"/IBGEDataServlet"})
public class IBGEDataServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String IBGE_API_URL = "https://servicodados.ibge.gov.br/api/v1/localidades/estados";


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
            out.println("<title>Servlet IBGEDataServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet IBGEDataServlet at " + request.getContextPath() + "</h1>");
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
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Obtém o valor selecionado no menu suspenso
        String estadoSelecionado = request.getParameter("estado");

        out.println("<html><body>");
        out.println("<h1>Informações do Estado Selecionado</h1>");

        if (estadoSelecionado != null) {
            out.println("<p>Estado Selecionado: " + estadoSelecionado + "</p>");
            // Aqui você pode adicionar lógica para buscar informações específicas do estado
            // Como exemplo, vamos exibir algumas informações estáticas
            if (estadoSelecionado.equals("SP")) {
                out.println("<p>Capital: São Paulo</p>");
                out.println("<p>População: 12.33 milhões</p>");
                out.println("<p>Área: 248.209 km²</p>");
            } else if (estadoSelecionado.equals("RJ")) {
                out.println("<p>Capital: Rio de Janeiro</p>");
                out.println("<p>População: 6.72 milhões</p>");
                out.println("<p>Área: 43.696 km²</p>");
            } else if (estadoSelecionado.equals("MG")) {
                out.println("<p>Capital: Belo Horizonte</p>");
                out.println("<p>População: 21.17 milhões</p>");
                out.println("<p>Área: 586.522 km²</p>");
            } else {
                out.println("<p>Estado não reconhecido</p>");
            }
        } else {
            out.println("<p>Nenhum estado selecionado</p>");
        }

        out.println("</body></html>");
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
