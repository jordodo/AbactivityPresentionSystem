package nl.hu.ipass.abactivity;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import nl.hu.ipass.abactivity.Klant;

public class KlantDAO extends BaseDAO {
	  

	  private List<Klant> selectCustomers(String query) {
	    List<Klant> results = new ArrayList<Klant>();
	    try (Connection con = super.getConnection()){
	      PreparedStatement pstmt = con.prepareStatement(query);
	      ResultSet dbResultSet = pstmt.executeQuery();
	      while (dbResultSet.next()) {
	        int kcode = dbResultSet.getInt("kcode");
	        String voornaam = dbResultSet.getString("voornaam");
	        String tussenvoegsel = dbResultSet.getString("tussenvoegsel");
	        String achternaam = dbResultSet.getString("achternaam");
	        String emailadres = dbResultSet.getString("email");
	        String bedrijf = dbResultSet.getString("bedrijf");
	        
	        
	        Klant newKlant = new Klant(kcode,voornaam,tussenvoegsel,achternaam,emailadres,bedrijf);
	        
	        results.add(newKlant);
	      }
	    } catch (SQLException sqle) {sqle.printStackTrace();}
	    return results;
	  }

	  public List<Klant> findAll() { 
	    return selectCustomers("SELECT kcode, voornaam, tussenvoegsel, achternaam, email, bedrijf FROM klant"); 
	  }
	  
	  public Klant findKlantByID(int kcode){
		  Klant k = null;
		  ArrayList<Klant> tempArray = (ArrayList<Klant>) findAll();
		  for (int i = 0; i < tempArray.size(); i++) {
			  if (kcode == (tempArray.get(i).getKcode())) {
				  k = tempArray.get(i);
			  }
		  }
			  return k;
			
		}
	  
	  public void insertKlantIntoDatabase(Klant klant){
		  	int kcode = klant.getKcode();
	        String voornaam = klant.getVoornaam();
	        String tussenvoegsel = klant.getTussenvoegsel();
	        String achternaam = klant.getAchternaam();
	        String emailadres = klant.getEmailadres();
	        String bedrijf = klant.getBedrijf();
	        System.out.println("123" + bedrijf + "456");
	        String query = " ";
	        if (!tussenvoegsel.equals("") && !bedrijf.equals("")){
	        	query = "INSERT INTO `database abactivity`.`klant` (`kcode`, `voornaam`, `tussenvoegsel`, `achternaam`, `bedrijf`, `email`) VALUES ('" +kcode+"','"+voornaam+"','"+tussenvoegsel+"','"+achternaam+"','"+bedrijf+"','"+emailadres+"')";
			}
			else if (!tussenvoegsel.equals("") && bedrijf.equals("")){
				query = "INSERT INTO `database abactivity`.`klant` (`kcode`, `voornaam`, `tussenvoegsel`, `achternaam`, `email`) VALUES ('" +kcode+"','"+voornaam+"','"+tussenvoegsel+"','"+achternaam+"','"+emailadres+"')";
			}
			else if (tussenvoegsel.equals("") && !bedrijf.equals("")){
				query = "INSERT INTO `database abactivity`.`klant` (`kcode`, `voornaam`, `achternaam`, `bedrijf`, `email`) VALUES ('" +kcode+"','"+voornaam+"','"+achternaam+"','"+bedrijf+"','"+emailadres+"')";
			}
			else {
				query = "INSERT INTO `database abactivity`.`klant` (`kcode`, `voornaam`, `achternaam`, `email`) VALUES ('"+kcode+"','"+voornaam+"','"+tussenvoegsel+"','"+achternaam+"','"+emailadres+"')";
			}
	        
	        
	        
	    
		  
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
	  
	  public void deleteKlant(String kcode) {
			
			String query = "DELETE FROM `database abactivity`.`klant` WHERE `kcode`='" + kcode + "'";
			


			  
			  
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
	

