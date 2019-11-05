/**
 * Clase para la gestión de la conexión y las ocnsultas con la BBDD.
 * BBDD: Firebird 2.5
 * Driver: JDBC Driver jaybird-full-3.0.3 
 */
package ConexionFirebird2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Carlos León Remedios
 * 2º DAM - Acceso a Datos
 * Ejercicio 12/03/2018
 */
public class Datos {
    
    private Connection conexion;
    private final String PATH = System.getProperty("user.dir");
    private final String USER = "SYSDBA";
    private final String PASS = "masterkey";
    private ResultSet resultado = null;
    private Statement st;
    private PreparedStatement ps;
    
    public void conectar(){
        try{
            Class.forName("org.firebirdsql.jdbc.FBDriver");
            conexion = DriverManager.getConnection("jdbc:firebirdsql:localhost/3055:"
                    +PATH+"\\data\\EJERCICIO03.fdb", USER, PASS);
        } catch (SQLException sqle){
            System.out.println("Error en la conexión:\n"+sqle.getMessage());
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Error registrando el driver:\n"+cnfe.getMessage());
        }
    }
    
    public ResultSet consultarClientes(){
        try {
            st = conexion.createStatement();
            resultado = st.executeQuery("select nif, nombre from Clientes order by nombre");
        } catch (SQLException sqlex){
            System.out.println("Error en la consulta:\n"+sqlex.getMessage());
        }
        return resultado;
    }
    
    public ResultSet consultarCliente(String nif) {
        ResultSet cliente = null;
        try {
            st = conexion.createStatement();
            cliente = st.executeQuery("select nif, nombre, direccion, telefono from Clientes "
                    + "where nif = \'"+nif+"\'");
        } catch (SQLException sqlex){
            System.out.println("Error en la consulta:\n"+sqlex.getMessage());
        } 
        return cliente;
    }
    
    public int borrarCliente(String nif){
        int borrado = 0;
        conectar();
        try {
            String sql = "delete from Clientes where nif = ?";
            ps = conexion.prepareStatement(sql);
            ps.setString(1, nif);
            borrado = ps.executeUpdate();
            //System.out.println(String.valueOf(borrado));
            return borrado;
        } catch (SQLException sqle) {
            System.out.println("Error al borrar el cliente:\n"+sqle.getMessage());
        }
        return borrado;
    }
    
    public int insertarCliente(String nif, String nombre, String dir, String tel){
        int insertado = 0;
        conectar();
        try {
            String sql = "insert into Clientes (nif, nombre, direccion, telefono) "
                    + "values (?,?,?,?)";
            ps = conexion.prepareStatement(sql);
            ps.setString(1, nif);
            ps.setString(2, nombre);
            ps.setString(3, dir);
            ps.setString(4, tel);
            insertado = ps.executeUpdate();
            return insertado;
        } catch (SQLException sqle) {
            System.out.println("Error al insertar el cliente:\n"+sqle.getMessage());
            return insertado;
        }
    }
    
    public int modificarCliente(String nifOld, String nifNew, String nombre, 
            String dir, String tel){
        conectar();
        int modificado = 0;
        try {
            String sql = "update Clientes set "
                    + "nif = ?, "
                    + "nombre = ?,"
                    + "direccion = ?,"
                    + "telefono = ? "
                    + "where nif = ?";
            ps = conexion .prepareStatement(sql);
            ps.setString(1, nifNew);
            ps.setString(2, nombre);
            ps.setString(3, dir);
            ps.setString(4, tel);
            ps.setString(5, nifOld);
            modificado = ps.executeUpdate();
            return modificado;
        } catch (SQLException sqle) {
            System.out.println("Error al modificar el cliente:\n"+sqle.getMessage());
            return modificado;
        }
    }
}
