package nl.hu.ipass.abactivity.webservices;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import nl.hu.ipass.abactivity.Gebruiker;
import nl.hu.ipass.abactivity.GebruikerDAO;
import nl.hu.ipass.abactivity.Klant;
import nl.hu.ipass.abactivity.KlantDAO;

@Path("/testing")
public class AbactivityPath {
	
	private GebruikerDAO gebruikerDAO =  new GebruikerDAO();
	private KlantDAO klantDAO =  new KlantDAO();

	@GET
	@Path("/getgebruiker")
	@RolesAllowed("0")
	@Produces("application/json")
	public String testFunction(){
		
		
		
		ArrayList<Gebruiker> gebruikerLijst = (ArrayList<Gebruiker>) getAllGebruikers();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		for (int i = 0; i < gebruikerLijst.size(); i++) {
		  JsonObjectBuilder job = Json.createObjectBuilder();
		  job.add("name", gebruikerLijst.get(i).toString());
		  jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();

	}
	@GET
	@Path("/getgebruiker2")
	@RolesAllowed("1")
	@Produces("application/json")
	public String testFunction2(){
		
		
		
		ArrayList<Gebruiker> gebruikerLijst = (ArrayList<Gebruiker>) getAllGebruikers();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		for (int i = 0; i < gebruikerLijst.size(); i++) {
		  JsonObjectBuilder job = Json.createObjectBuilder();
		  job.add("name", gebruikerLijst.get(i).toString());
		  jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();

	}
	@GET
	@Path("/getgebruiker3")
	@RolesAllowed({ "0", "1"})
	@Produces("application/json")
	public String testFunction3(){
	
		
		
		ArrayList<Gebruiker> gebruikerLijst = (ArrayList<Gebruiker>) getAllGebruikers();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		for (int i = 0; i < gebruikerLijst.size(); i++) {
		  JsonObjectBuilder job = Json.createObjectBuilder();
		  job.add("name", gebruikerLijst.get(i).toString());
		  jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();

	}
	
	public List<Gebruiker> getAllGebruikers() {
		System.out.println("Yes");
		return gebruikerDAO.findAll();
	}
	
	public List<Klant> getAllKlanten() {
		System.out.println("Yes");
		return klantDAO.findAll();
	}
		@POST
		@Path("/maakgebruiker")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		public String maakGebruikerAan(@FormParam("username") String username, @FormParam("password") String password, @FormParam("kcode") String kcode) {
			String s = "Gelukt, gebruiker aangemaakt";
			try {
				
				
				String tussencode = kcode;
				// Authenticate the user against the database
				System.out.println("Password:"  + password);
				
				System.out.println("user:"  +username);
				System.out.println("kcode:"  +kcode);
				int newcode = Integer.parseInt(tussencode);
				System.out.println("newkode:"  +newcode);
				Gebruiker gebruiker =  new Gebruiker(username, password, 0, newcode);
				
				
				gebruikerDAO.insertGebruikerIntoDatabase(gebruiker);
				
				
				
				// Return the token on the response
				

			} catch (Exception e) {
				s = "Er ging toch iets mis, probeer opnieuw of contacteer een admin";
			}
			return s;
		}
		
		@POST
		@Path("/maakklant")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		public String maakKlantAan(@FormParam("voornaam") String voornaam, @FormParam("tussenvoegsel") String tussenvoegsel, @FormParam("achternaam") String achternaam, @FormParam("bedrijf") String bedrijf, @FormParam("email") String emailadres ) {
			String s = "Gelukt, klant aangemaakt";
			try {
				
				
				// Authenticate the user against the database
				
				Klant klant =  new Klant(voornaam,  tussenvoegsel,  achternaam, emailadres, bedrijf);
				
				s = Integer.toString(klant.getKcode());
				klantDAO.insertKlantIntoDatabase(klant);
				
				
				
				// Return the token on the response
				

			} catch (Exception e) {
				s = "Er ging toch iets mis, probeer opnieuw of contacteer een admin";
			}
			return s;
		}
		
		@POST
		@Path("/wijzigWachtwoord")
		//@RolesAllowed("0")
		@Produces("application/json")
		public String wijzigWachtwoord(@FormParam("username") String gebruikersnaam, @FormParam("password") String oudWachtwoord, @FormParam("password2") String nieuwWachtwoord) {
			
			String s = "Gelukt, wachtwoord gewijzigt";
			try {
				
				
				// Authenticate the user against the database
				
				if( !oudWachtwoord.equals(nieuwWachtwoord)){
					
				
				gebruikerDAO.wijzigWachtwoord(gebruikersnaam, oudWachtwoord, nieuwWachtwoord);;
				s = "Wachtwoord gewijzigt";
			}
				else {
					s = "Wachtwoorden mogen niet gelijk aan elkaar zijn";
				}
				
				
				// Return the token on the response
				

			} catch (Exception e) {
				s = "Er ging toch iets mis, probeer opnieuw of contacteer een admin";
			}
			return s;
		}
		
		@POST
		@Path("/deleteGebruiker")
		//@RolesAllowed("1")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		public String deleteGebruiker(@FormParam("username") String gebruikersnaam) {
			
			String s = "Gelukt, gebruiker verwijderd";
			try {
				gebruikerDAO.deleteGebruiker(gebruikersnaam);
				
				// Return the token on the response
				

			} catch (Exception e) {
				s = "Er ging toch iets mis, probeer opnieuw of contacteer een admin";
			}
			return s;
		}
		
		@POST
		@Path("/deleteKlant")
		//@RolesAllowed("1")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		public String deleteKlant(@FormParam("kcode") String kcode) {
			
			String s = "Gelukt, klant en bijbehorende gebruiker verwijderd";
			try {
				klantDAO.deleteKlant(kcode);
				
				// Return the token on the response
				

			} catch (Exception e) {
				s = "Er ging toch iets mis, probeer opnieuw of contacteer een admin";
			}
			return s;
		}
		
		@GET
		@Path("/getklant")
		@RolesAllowed("1")
		@Produces("application/json")
		public String getKlanten(){
			
			
			
			ArrayList<Klant> klantenLijst = (ArrayList<Klant>) getAllKlanten();
			JsonArrayBuilder jab = Json.createArrayBuilder();
			for (int i = 0; i < klantenLijst.size(); i++) {
			  JsonObjectBuilder job = Json.createObjectBuilder();
			  job.add("name", klantenLijst.get(i).toString());
			  jab.add(job);
			}
			JsonArray array = jab.build();
			return array.toString();
	}
}
	
	

