package nl.hu.ipass.abactivity;

import java.util.concurrent.ThreadLocalRandom;


public class Klant {
	
	private int kcode;
	private String voornaam;
	private String tussenvoegsel;
	private String achternaam;
	private String emailadres;
	private String bedrijf;
	
	KlantDAO klantDAO =  new KlantDAO();
	
	public Klant(String voornaam, String tussenvoegsel, String achternaam,String emailadres,String bedrijf){
		int randomNum = ThreadLocalRandom.current().nextInt(0, 99999 + 1);
		this.kcode = randomNum;
		this.voornaam = voornaam;
		this.tussenvoegsel = tussenvoegsel;
		this.achternaam = achternaam;
		this.emailadres = emailadres;
		this.bedrijf = bedrijf;
		klantDAO.insertKlantIntoDatabase(this);
	}
	public Klant(int kcode, String voornaam, String tussenvoegsel, String achternaam,String emailadres,String bedrijf){
		this.kcode = kcode;
		this.voornaam = voornaam;
		this.tussenvoegsel = tussenvoegsel;
		this.achternaam = achternaam;
		this.emailadres = emailadres;
		this.bedrijf = bedrijf;
	}
	
	
	public String getVoornaam() {
		return voornaam;
	}
	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}
	public String getTussenvoegsel() {
	
		return tussenvoegsel;
	}
	public void setTussenvoegsel(String tussenvoegsel) {
		this.tussenvoegsel = tussenvoegsel;
	}
	public String getAchternaam() {
		return achternaam;
	}
	public void setAchternaam(String achternaam) {
		this.achternaam = achternaam;
	}
	public int getKcode() {
		return kcode;
	}
	public void setKcode(int kcode) {
		this.kcode = kcode;
	}
	public String getEmailadres() {
		return emailadres;
	}
	public void setEmailadres(String emailadres) {
		this.emailadres = emailadres;
	}
	public String getBedrijf() {
		
		return bedrijf;
	}
	public void setBedrijf(String bedrijf) {
		this.bedrijf = bedrijf;
	}
	
	public String getFullName(){
		String naam;
		if (tussenvoegsel == null) {
			naam = voornaam + " " + achternaam;
		}
		else{
			naam = voornaam + " " + tussenvoegsel + " " + achternaam;
		}
		return naam;
	}
	
	public String toString(){
		String s;
		if (tussenvoegsel != null && bedrijf != null){
			s = voornaam + " " + tussenvoegsel + " " + achternaam + " is klant bij Abactivity en heeft de email: " + emailadres + ". Het bedrijf dat deze persoon vertegenwoordigd is: " + bedrijf + " Klantcode bij deze persoon is: " + kcode;
		}
		else if (tussenvoegsel != null && bedrijf == null){
			s = voornaam + " " + tussenvoegsel + " " + achternaam + " is klant bij Abactivity en heeft de email: " + emailadres + " Klantcode bij deze persoon is: " + kcode;
		}
		else if (tussenvoegsel == null && bedrijf != null){
			s = voornaam + " " + achternaam + " is klant bij Abactivity en heeft de email: " + emailadres + ". Het bedrijf dat deze persoon vertegenwoordigd is: " + bedrijf + " Klantcode bij deze persoon is: " + kcode;
		}
		else {
			s = voornaam + " " + achternaam + " is klant bij Abactivity en heeft de email: " + emailadres + " Klantcode bij deze persoon is: " + kcode;
		}
		return s;
	}
}
