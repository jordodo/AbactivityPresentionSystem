package nl.hu.ipass.abactivity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class GebruikerDAO extends BaseDAO {
	  

	  private List<Gebruiker> selectCustomers(String query) {
	    List<Gebruiker> results = new ArrayList<Gebruiker>();
	    try (Connection con = super.getConnection()){
	      PreparedStatement pstmt = con.prepareStatement(query);
	      ResultSet dbResultSet = pstmt.executeQuery();
	      while (dbResultSet.next()) {
	        int gcode = dbResultSet.getInt("gcode");
	        String gebruikersNaam = dbResultSet.getString("gebruikersnaam");
	        String wachtwoord = dbResultSet.getString("wachtwoord");
	        int is_admin = dbResultSet.getInt("is_admin");
	        int kcode = dbResultSet.getInt("kcode");
	        
	         
	        
	        Gebruiker newGebruiker = new Gebruiker(gcode,gebruikersNaam,wachtwoord,is_admin, kcode);
	        
	        results.add(newGebruiker);
	      }
	    } catch (SQLException sqle) {sqle.printStackTrace();}
	    return results;
	  }

	  public List<Gebruiker> findAll() { 
	    return selectCustomers("SELECT gcode, gebruikersnaam, wachtwoord, is_admin, kcode FROM gebruikers"); 
	  }
	  
	  public String vindRolVoorGebruiker(String gebruikersNaam, String wachtwoord){
		  String rol = null;
		  String query = "SELECT is_admin FROM gebruikers WHERE gebruikersnaam = ? AND wachtwoord = ?";
		  
		  try (Connection con =  super.getConnection()) {
		  PreparedStatement pstmt = con.prepareStatement(query);
		  pstmt.setString(1, gebruikersNaam);
		  pstmt.setString(2, wachtwoord);
		  ResultSet rs = pstmt.executeQuery();
		  if(rs.next()) 
		  rol= Integer.toString(rs.getInt("is_admin"));
		  } 
		  
		  catch(SQLException sqle) {
		  sqle.printStackTrace(); 
		  }
		  return rol ;
	  }
	  
	  
	  public void insertGebruikerIntoDatabase(Gebruiker gebruiker){
		  	int gcode = gebruiker.getGcode();
	        String gebruikersNaam = gebruiker.getGebruikersNaam();
	        String wachtwoord = gebruiker.getWachtwoord();
	        int is_admin = gebruiker.getIsAdmin();
	        int kcode = gebruiker.getKcode();
	        
	     
	        String query = "INSERT INTO `database abactivity`.`gebruikers` (`gcode`, `gebruikersnaam`, `wachtwoord`, `is_admin`, `kcode`)VALUES ('" +gcode+ "','"+gebruikersNaam+"','"+wachtwoord+"','"+is_admin+"','"+kcode+"')";
	        
		  
		  
		  try (Connection con =  super.getConnection()) {
		  PreparedStatement pstmt = con.prepareStatement(query);
		  System.out.println(query);
			pstmt.executeUpdate(query);
	      
		  }
		  catch(SQLException sqle) {
			  sqle.printStackTrace();
			   System.out.println("Nope ");
			  }
			  
		  }
	  
	  public void wijzigWachtwoord(String gebruikersNaam, String oudWachtwoord, String nieuwWachtwoord){
		  	
	        String query = "UPDATE `database abactivity`.`gebruikers` SET `wachtwoord`='" + nieuwWachtwoord + "' WHERE `gebruikersnaam`='" + gebruikersNaam + "'";
	        
		  
		  
		  try (Connection con =  super.getConnection()) {
		  PreparedStatement pstmt = con.prepareStatement(query);
		  System.out.println(query);
			pstmt.executeUpdate(query);
	      
		  }
		  catch(SQLException sqle) {
			  sqle.printStackTrace();
			   System.out.println("Nope ");
			  }
			  
		  }

	public void deleteGebruiker(String gebruikersnaam) {
		
		String query = "DELETE FROM `database abactivity`.`gebruikers` WHERE `gebruikersnaam`='" + gebruikersnaam + "'";
		

		  
		  
		  try (Connection con =  super.getConnection()) {
		  PreparedStatement pstmt = con.prepareStatement(query);
		  System.out.println(query);
			pstmt.executeUpdate(query);
	      
		  }
		  catch(SQLException sqle) {
			  sqle.printStackTrace();
			   System.out.println("Nope ");
			  }
			  
		  }
	}
	  
	


