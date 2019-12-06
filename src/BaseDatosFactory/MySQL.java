
package BaseDatosFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author alber
 */
public class MySQL implements BaseDeDatos {

    public static Connection CONNECTION;
    public static final String HOSTNAME = "localhost";
    public static final String DBNAME = "MafiaOrganisedCrime";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "root";
    public static final String PORT = "8889";

    @Override
    public Connection abrir() {
        String URL = "jdbc:mysql://" + HOSTNAME + ":" + PORT + "/" + DBNAME;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            CONNECTION = (Connection) DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Conexión a MySQL");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        return CONNECTION;
    }

    @Override
    public void cerrar() {
        try {
            if (CONNECTION != null) {
                CONNECTION.close();
                System.out.println("Se cerro");
            }
        } catch (SQLException ex) {
            System.out.println("Excepción: " + ex.toString());
        }
    }

    @Override
    public DefaultTableModel Select(String queryString) throws SQLException {
        Statement st = null;
        ResultSet rs = null;
        DefaultTableModel dtm = new DefaultTableModel();
        try { // try-catch-finally
            CONNECTION = abrir();
            // Inicializar Statement y Resulset
            st = CONNECTION.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            // rs = st.executeQuery( "SELECT expediente, nombre, sexo, activo FROM Alumno");
            rs = st.executeQuery(queryString);
            // Obtiene Meta Datos de la consulta que regresa.
            // En ete caso Número y Nombre de Columnas
            ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
            // Agregamos las columas en mi modelo de datos
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                dtm.addColumn(rsmd.getColumnName(i));
            }
            // Agregamos los registos al modelo de datos
            int numeroRegistros = 0;
            if (rs.last()) // Pone el cursor en el último registro
            {
                numeroRegistros = rs.getRow(); // Obtiene el número de registros
            }
            rs.beforeFirst(); // Pone el cursor antes del primer registo

            if (numeroRegistros > 0) {
                // Se utiliza un arreglo de Object para obtener los datos, ya que son de diferentes tipos
                Object[] registros = new Object[rsmd.getColumnCount()];
                while (rs.next()) {
                    // Por cada columna obtiene el valor de la celda
                    for (int i = 0; i < rsmd.getColumnCount(); i++) {
                        // registros obtiene i, ya que un arreglo empieza en la posición 0
                        // rs.getObject(i+1) ya que la columna empieza en 1, no 0
                        registros[i] = rs.getObject(i + 1);
                    }
                    // Agrega un registro de datos al modelo
                    dtm.addRow(registros);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Excepción: " + ex.toString());
            System.out.println("Excepción: " + ex.toString());
        } finally {
            // Cierra o null los objetos
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (CONNECTION != null) {
                    CONNECTION.close();
                }
            } catch (SQLException ex) {
                System.out.println("Excepción: " + ex.toString());
            }
        }
        return dtm;
    }

    @Override
    public boolean Insert(String queryString) throws SQLException {
        Statement st = null;
        int regreso = 0;
        try {
            CONNECTION = abrir();
            // Inicializar Statement y Resulset
            st = CONNECTION.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            regreso = st.executeUpdate(queryString);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Excepción: " + ex.toString());
            // System.out.println("Excepción: " + ex.toString());
        } finally {
            // Cierra o null los objetos
            try {
                if (st != null) {
                    st.close();
                }
                if (CONNECTION != null) {
                    CONNECTION.close();
                }
            } catch (SQLException ex) {
                System.out.println("Excepción: " + ex.toString());
            }
        }
        if (regreso > 0) {return true;}
        return false;
    }

    @Override
    public boolean iniciarSesion(String queryString) throws SQLException {
        Statement st = null;
        ResultSet rs = null;
        try {
            CONNECTION = abrir();
            st = CONNECTION.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            rs = st.executeQuery(queryString);
            if (rs.next()) {return true;}
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Excepción: " + ex.toString());
        }
        return false;
    }

    @Override
    public boolean Update(String queryString) throws SQLException {
        CONNECTION = abrir();
        PreparedStatement pstm = CONNECTION.prepareStatement(queryString);
        if (pstm.executeUpdate() == 1) {
            //miConexion
            return true;
        }
        return false;
    }

    
}
