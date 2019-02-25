package org.activiti.gestion_stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Random;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;


public class MyJavaDelegate implements JavaDelegate{
	
public void execute(DelegateExecution execution) throws Exception {

	Class.forName("org.h2.Driver");
	Connection conn= DriverManager.getConnection("jdbc:h2:~test", "sa", "");
	Statement stmt = conn.createStatement();
	
	String anneeUniv = (String) execution.getVariable("anneeUniv");
	String nom = (String) execution.getVariable("nom");
	String prenom = (String) execution.getVariable("prenom");
	String adresse = (String) execution.getVariable("adresse");
	String societe = (String) execution.getVariable("société");
	String sujet = (String) execution.getVariable("sujet");
	
	
	
	// Creating a table
	String createTableString = "" + "CREATE TABLE IF NOT EXISTS CONVENTION"
								  + "(" 
								  + "ID INT,"
								  + "ANNEE_UNIV VARCHAR(10),"
								  + "NOM VARCHAR(255)," 
								  + "PRENOM VARCHAR(255),"
								  + "ADRESSE VARCHAR(255),"
								  + "ENTREPRISE VARCHAR(255),"
								  + "SUJET_STAGE VARCHAR(500),"
								  + "PRIMARY KEY(ID)" + ");";
	
	stmt.execute(createTableString);

	PreparedStatement st = conn.prepareStatement("INSERT INTO CONVENTION VALUES(?,?,?,?,?,?)"); 
	st.setInt(1, new Random().nextInt());
	st.setString(2, anneeUniv);
	st.setString(3,nom);
	st.setString(4,prenom);
	st.setString(5,adresse);
	st.setString(6,societe);
	st.setString(7,sujet);
	st.executeUpdate();
	conn.commit();
	conn.close();
	
	

	}

}