package com.locationcast.cassandra.persistence;

import org.testng.annotations.Test;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class CreateTableTest {

	

   @Test
   public void testCreateTable(){

	  
	   String query = "CREATE TABLE user(id int PRIMARY KEY , "
         + "address text, "
         + "name text);";

		
      //Creating Cluster object
      Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
   
      //Creating Session object
      Session session = cluster.connect("test");
 
      //Executing the query
      session.execute(query);
 
      System.out.println("Table created");
   }
	
   @Test
   public void testCassandraJDBC(){
	   Connection con = null;
       String KS = "cassandrademocql";

       try 
       {
           Class.forName("org.apache.cassandra.cql.jdbc.CassandraDriver");
           con = DriverManager.getConnection("jdbc:cassandra://localhost:9160/");

          Statement stmt = con.createStatement();
          String createKeyspace= " CREATE KEYSPACE test WITH replication = {'class':'SimpleStrategy', 'replication_factor' : 3};";
          
          stmt.execute(createKeyspace);

       }


       catch (Exception ex) {
           ex.printStackTrace();
       }
   }
}
