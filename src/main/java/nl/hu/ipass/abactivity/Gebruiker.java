package nl.hu.ipass.abactivity;

import java.util.concurrent.ThreadLocalRandom;
import nl.hu.ipass.abactivity.GebruikerDAO;
import nl.hu.ipass.abactivity.KlantDAO;

public class Gebruiker {

	private int gcode;
	private String gebruikersNaam;
	private String wachtwoord;
	private int isAdmin;
	private Klant klant;
	
	private KlantDAO klantDAO =  new KlantDAO();
	private GebruikerDAO gebruikerDAO =  new GebruikerDAO();
	
	public Gebruiker(int gcode,String gebruikersNaam, String wachtwoord, int isAdmin, int kcode){
		this.gcode = gcode;
		this.gebruikersNaam = gebruikersNaam;
		this.wachtwoord = wachtwoord;
		this.isAdmin = isAdmin;
		this.klant = klantDAO.findKlantByID(kcode);
	}
	public Gebruiker(String gebruikersNaam, String wachtwoord, int isAdmin, int kcode){
		int randomNum = ThreadLocalRandom.current().nextInt(0, 99999 + 1);
		
		this.gcode = randomNum;
		this.gebruikersNaam = gebruikersNaam;
		this.wachtwoord = wachtwoord;
		this.isAdmin = isAdmin;
		this.klant = klantDAO.findKlantByID(kcode);
		gebruikerDAO.insertGebruikerIntoDatabase(this);
	}
	
	
	
	public int getGcode() {
		return gcode;
	}
	public void setGcode(int gcode) {
		this.gcode = gcode;
	}
	public String getGebruikersNaam() {
		return gebruikersNaam;
	}
	public void setGebruikersNaam(String gebruikersNaam) {
		this.gebruikersNaam = gebruikersNaam;
	}
	public int getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}
	public Klant getKlant() {
		return klant;
	}
	public void setKlant(Klant klant) {
		this.klant = klant;
	}
	
	
	public String getWachtwoord() {
		return wachtwoord;
	}
	public void setWachtwoord(String wachtwoord) {
		this.wachtwoord = wachtwoord;
	}
	public KlantDAO getKlantDAO() {
		return klantDAO;
	}
	public void setKlantDAO(KlantDAO klantDAO) {
		this.klantDAO = klantDAO;
	}
	
	public int getKcode(){
		int kcode;
		kcode = klant.getKcode();
		return kcode;
	}
	public String toString(){
		String s;
		if (klant != null) {
		s = "Deze gebruiker heeft als gebruikersNaam: " + gebruikersNaam + " en als wachtwoord: " + wachtwoord + ". Gebruikercode is: " + gcode + ". Adminstatus is: " + isAdmin + ". Hoort bij klant; " + klant.getFullName();
		} else{
			s = "Deze gebruiker heeft als gebruikersNaam: " + gebruikersNaam + " en als wachtwoord: " + wachtwoord + ". code: " + gcode + ". Adminstatus is: " + isAdmin;
		}
		return s;
	}
}
