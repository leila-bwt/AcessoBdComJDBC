package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;

public class Program {

    public static void main(String[]args){

        Connection conn = null;
        Statement st = null; //usado para enviar comandos SQL ao banco de dados
        ResultSet rs = null; //usada para representar o conjunto de resultados de uma consulta SQL

        try {
            conn = DB.getConnection();

            st = conn.createStatement();

            rs = st.executeQuery("select * from department");

            while (rs.next()){
                System.out.println(rs.getInt("Id") + ", " + rs.getString("Name"));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    
}
