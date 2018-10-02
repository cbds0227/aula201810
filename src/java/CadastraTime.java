/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Aluno
 */
@WebServlet(urlPatterns = {"/CadastraTime"})
public class CadastraTime extends HttpServlet {

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
            out.println("<title>Servlet CadastraTime</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CadastraTime at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        
        
          Time l = new Time();
        l.setNome(String.valueOf(request.getParameter("nome")));
        l.setDatadefundacao(String.valueOf(request.getParameter("datadefundacao")));
        l.setEndereco(String.valueOf(request.getParameter("endereco")));
        l.setCidade(String.valueOf(request.getParameter("cidade")));
        l.setEstado(String.valueOf(request.getParameter("estado")));
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://10.0.0.1:3306/batepapo",
                                                         "batepapo", 
                                                         "batepapo");
            
            String sql = "insert into TimeDionesAntonio (nome, datadefundacao, endereco, cidade, estado) values (?,?,?,?,?)";
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, l.getNome());
            ps.setString(2, l.getDatadefundacao());
            ps.setString(3, l.getEndereco());
            ps.setString(4, l.getCidade());
            ps.setString(5, l.getEstado());
            
            ps.execute();
            
            con.close();
            
            response.getWriter().println("Cadastro realizado com sucesso!");
        } catch (Exception ex) {
            Logger.getLogger(CadastraTime.class.getName()).log(Level.SEVERE, null, ex);
            ex.getMessage();
            response.getWriter().println("Erro! Seu cadastro não foi realizado!");
        }
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
