package modelo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Struct;

import oracle.jdbc.OracleData;
import oracle.jdbc.OracleDataFactory;
import oracle.sql.CHAR;
import oracle.sql.DATE;
import oracle.sql.NUMBER;
import oracle.sql.STRUCT;
//https://docs.oracle.com/en/database/oracle/oracle-database/18/jjdbc/Oracle-object-types.html#GUID-E47B8141-3C4C-44D5-BB5C-2F8E16DF5140

public class OracleProject implements OracleData, OracleDataFactory {

	

	private NUMBER projno;
	private CHAR projname;
	private DATE startdate;



	public static OracleDataFactory getOracleDataFactory() {
		return new OracleProject();
	}	




	public OracleProject(NUMBER projno, CHAR projname, DATE startdate) {
		super();
		this.projno = projno;
		this.projname = projname;
		this.startdate = startdate;
	}




	public OracleProject() {
		
	}

	@Override
	public Object toJDBCObject(Connection c) throws SQLException {
		Object[] attributes = { projno, projname, startdate };
		Struct struct = c.createStruct("PEOPLE_USER.PROJECT_TABLE", attributes);
		return struct;
	}

	@Override
	public OracleData create(Object jdbcValue, int sqlType) throws SQLException {

		if (jdbcValue == null)
			return null;
		Object[] attributes = ((STRUCT) jdbcValue).getOracleAttributes();
		return new OracleProject((NUMBER) attributes[0], (CHAR) attributes[1], (DATE) attributes[2]);

	}




	@Override
	public String toString() {
		try {
			return "OracleProject [projno=" + projno.intValue() + ", projname=" + projname + ", startdate=" + startdate + "]";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "OracleProject [projno=" + projno + ", projname=" + projname + ", startdate=" + startdate + "]";
			
		}
	}



	
	

//	@Override
//	public String toString() {
//		try {
//			return "JDepartamento [deptno=" + projno.intValue() + ", name=" + projname + ", location=" + startdate + "]";
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return "JDepartamento [deptno= , name=" + projname + ", location=" + startdate + "]";
//		}
//	}




	
}
