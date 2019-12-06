package BaseDatosFactory;

import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author alber
 */
public interface BaseDeDatos {
    public Connection abrir( ) ;
    public void cerrar( );
    public DefaultTableModel Select(String queryString) throws SQLException;
    public boolean Insert(String queryString) throws SQLException;
    public boolean iniciarSesion(String queryString) throws SQLException;
    public boolean Update (String queryString) throws SQLException;
}
