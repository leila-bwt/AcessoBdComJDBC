package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;

public class Program {

	public static void main(String[] args) {
	
		Connection conn = null;
		PreparedStatement st = null; // Monta consulta SQL e permite colocar os argumentos depois
		try {
			conn = DB.getConnection();
			
			st = conn.prepareStatement("UPDATE seller SET BaseSalary = BaseSalary + ? WHERE (DepartmentId = ?)");

					st.setDouble(1, 200);
					st.setInt(2, 3);
			
			int rowsAffected = st.executeUpdate(); // vai armazenar o  numero de linhas afetadas e executar o comando SQL	
			
			System.out.println("Done! Rows affected: " + rowsAffected);			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}		
	}
}