/**
 * Crear una tabla de clientes en una base de datos (cualquiera que sea relacional) 
 * con las siguientes columnas:
 * - NIf char(9) primary key,
 * - Nombre varchar(100),
 * - direccion varchar(150),
 * - telefono char(9)
 * Introducir datos de prueba y despu�s hacer un programa en Java que nos visualice 
 * una rejilla  con el NIF y el Nombre (solo estas dos columnas).
 * Cuando hagamos doble clic encima de una fila nos debe aparecer una ventana con 
 * todos los datos de ese cliente, es decir: nif, nombre, direcci�n y tel�fono.
 * Debe ser posible Insertar, modificar y borrar registros de la tabla. Cada vez que
 * se hace una modificaci�n se debe mostrar el resultado en la rejilla de datos.
 */
package ConexionFirebird2;

import java.sql.SQLException;

/**
 * @author Carlos Le�n Remedios
 * 2� DAM - Acceso a Datos
 * Ejercicio 12/03/2018
 */
public class Main {
   public static void main (String[] args) throws SQLException{
       VentanaPrincipal vp = new VentanaPrincipal();
       vp.setVisible(true);
   } 
}
