package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import db.DB;

public class Program {

	public static void main(String[] args) {
		
		// inserir dados no db
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Connection conn = null;
		PreparedStatement st = null; // Monta consulta SQL e permite colocar os argumentos depois
		try {
			conn = DB.getConnection();
			
			/*st = conn.prepareStatement(
					"Insert Into seller "
					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId)"
					+ "VALUES "
					+"(?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS); // retorna as chaves geradas automaticamente - no caso o ID
			
			st.setString(1, "Carl Purple");
			st.setString(2, "carl@gmail.com");
			st.setDate(3, new java.sql.Date(sdf.parse("22/04/1985").getTime())); // para instanciar data no JDBC e jogar no PreparedStatement nao pode usar o java.util
			st.setDouble(4, 3000.00);
			st.setInt(5, 4);
			*/
			
			st = conn.prepareStatement(
					"insert into department (Name) values ('D1'),('D2')",
					Statement.RETURN_GENERATED_KEYS);
			
			int rowsAffected = st.executeUpdate(); // vai executar os comandos para inserção dos dados. Retorna o n de linhas alteradas no db		
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys(); // vai retornar as chaves automaticas (no caso ID) em formato de tabela
				while(rs.next()) { // vai pércorrer o ResultSet
					int id = rs.getInt(1); // pega o valor da primeira coluna
					System.out.println("Done! Id = " + id);
				}
			}
			else {
				System.out.println("No rown affected!");
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		/*catch(ParseException e ) {
			e.printStackTrace(); // imprime msg de erro caso essa data esteja no formato inválido
		}*/
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
		
		
	}
}