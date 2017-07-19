package nl.hu.ipass.abactivity.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.hu.ipass.abactivity.Klant;
import nl.hu.ipass.abactivity.KlantDAO;

import nl.hu.ipass.abactivity.Gebruiker;
import nl.hu.ipass.abactivity.GebruikerDAO;

@WebServlet(urlPatterns = "/DynamicServlet.do")
public class DynamicServlet extends HttpServlet 
{
	private KlantDAO KlantDAO = new KlantDAO();
	private GebruikerDAO GebruikerDAO =  new GebruikerDAO();
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
	
		ArrayList<Klant> alleKlanten = (ArrayList<Klant>) getAllCustomers();
		
		for (int i = 0; i < alleKlanten.size(); i++) {
			System.out.println(alleKlanten.get(i));
		}
		
		ArrayList<Gebruiker> alleGebruikers = (ArrayList<Gebruiker>) getAllGebruikers();
		
		for (int i = 0; i < alleGebruikers.size(); i++) {
			System.out.println(alleGebruikers.get(i));
		}
		
		/*
		String name = req.getParameter("username");
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");

		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println(" <title>Dynamic Example</title>");
		out.println(" <body>");
		out.println(" <h2>Dynamic webapplication example</h2>");
		out.println(" <h2>Hello " + name + "!</h2>");
		out.println(" </body>");
		out.println("</html>");
		
		*/
	}
	public List<Klant> getAllCustomers() {
		System.out.println("Yes");
		return KlantDAO.findAll();
		
	}
	
	public List<Gebruiker> getAllGebruikers() {
		System.out.println("Yes");
		return GebruikerDAO.findAll();
		
	}
}