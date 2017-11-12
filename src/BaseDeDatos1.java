import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class BaseDeDatos1 { 
    /**
     * Metodo encargado de enviar una consulta SQL a una base de datos.
     * Realiza la conexion a la base de datos dada y luego ejectura 
     * la consulta.
     * **NOTA** 
     *   Se deben poner los correctos parametros en "con = DriverManager.getConnection("jdbc:postgresql://192.168.56.56:5432/Mascotas","postgres","people098");"
     *  IP_PUERTO,nombre de la base de datos, usuario de PostgreSQL y contraseña.
     * @param consulta
     * @return resultado de la consulta. Null si la conexion fallo.
     */

public ResultSet enviarConsulta(String consulta) {
    Connection con = null;
    try {
        // Carga el driver JDBC para PostgreSQL
        Class.forName("org.postgresql.Driver").newInstance();
        // Obtiene una conexión a la base de datos
        con = DriverManager.getConnection("jdbc:postgresql://192.168.56.56:5432/Mascotas","postgres","people098");
        if (!con.isClosed()){
            System.out.println("Successfully connected to PostgreSQL server using TCP/IP...");
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
