package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import db.DB;
import db.DbException;


public class Program {

	public static void main(String[] args) {

		Connection conn = null;
		Statement st = null;
		try {
			conn = DB.getConnection();

			conn.setAutoCommit(false); // todas as operações ficarão pendentes de uma confirmação explicita do programador

			st = conn.createStatement();

			int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 4");

			/*int x = 1;
			if(x < 2){
				throw new SQLException("Fake error");
			}*/

			int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 1");

			conn.commit(); // confirma final da transação

			System.out.println("rows1 " + rows1);
			System.out.println("rows2 " + rows2);
			
		}
		catch (SQLException e) {
			try {
				conn.rollback();
				throw new DbException("Transaction rolled back! Cause by: " + e.getMessage());
			} catch (SQLException e1) {
				throw new DbException("Error trying to rollback! Caused by: " + e1.getMessage());
			} // volta ao estado inicial do banco
			
		} 
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}
}

/* Transação é um aoperação que tem que manter a consistencia do banco de dados.
Tem quatro propriedades:
Atomica = ou acontece tudo ou não acontece nada
Consistente
Isolada
Duravel  */ 





