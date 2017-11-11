import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class BaseDeDatos1 {    

public ResultSet enviarConsulta(String consulta) {
    Connection con = null;
    try {
        // Carga el driver JDBC para MySQL
        Class.forName("org.postgresql.Driver").newInstance();
        // Obtiene una conexión a la base de datos
        con = DriverManager.getConnection("jdbc:postgresql://192.168.56.56:5432/Mascotas","postgres","people098");
        if (!con.isClosed()){
            System.out.println("Successfully connected to MySQL server using TCP/IP...");
        }
        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery(consulta);
        return rs;
    } 
    catch (Exception e) {
        System.err.println("Exception: " + e.getMessage());} 
    finally {
    try {if (con != null) con.close();} 
        catch (SQLException e) {e.printStackTrace();}
    }
        return null;
}

}
