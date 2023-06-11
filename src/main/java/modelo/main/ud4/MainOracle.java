package modelo.main.ud4;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import modelo.OracleProject;
import oracle.jdbc.OracleResultSet;
import oracle.jdbc.datasource.impl.OracleDataSource;

public class MainOracle {
	

	public static void main(String[] args) {
		OracleDataSource ods= null;
		Connection conn =null;
		try {
			ods = new OracleDataSource();
			
			
//TO DO 
			//Update url por la ip que corresponda
			String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
			ods.setURL(url);
			ods.setUser("people_user");
			ods.setPassword("abc123.");
			conn= ods.getConnection();

			// Create Oracle DatabaseMetaData object
			DatabaseMetaData meta = conn.getMetaData();

			// gets driver info:
			System.out.println("JDBC driver version is " + meta.getDriverVersion());

//TO DO
			//Crear el select que permite obtener todos los objetos de la tabla dept_table
			PreparedStatement stmt = conn.prepareStatement("SELECT...");
			OracleResultSet rs = (OracleResultSet) stmt.executeQuery();
			while (rs.next()) {		


				
				Object s = rs.getObject(1, OracleProject.getOracleDataFactory());

				if (s != null) {
					if (s instanceof OracleProject) {						
						System.out.println(s);
					}

					else {
						System.out.println("Unknown type");
						System.out.println(s);
					}
				}
			}	

		} catch (SQLException e) {
			System.err.println("Ha ocurrido una exception: " + e.getMessage());
			e.printStackTrace();
		}
	
		
		finally {
			if(conn!=null) {
				try {
				conn.close();
				}
				catch(SQLException e) {
					System.err.println("Ha ocurrido una exception cerrando la conexión: " + e.getMessage());
					e.printStackTrace();
				}
			}
		}
	}

}

