package Controlador;

import BaseDatosFactory.BaseDeDatos;
import BaseDatosFactory.Factory;
import BaseDatosFactory.FactoryImpl;
import PatronComposite.CompositeValidador;
import PatronComposite.ContrasenaValidador;
import PatronComposite.CorreoValidador;
import PatronComposite.InformacionUsuario;
import PatronComposite.Validador;
import QuerysFactory.CatoryQueryImpl;
import QuerysFactory.FactoryQuery;
import QuerysFactory.Query;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author alber
 */
public class IProxyImpl extends UnicastRemoteObject implements IProxy {

    Factory factory = new FactoryImpl();
    BaseDeDatos bd;

    FactoryQuery factoryquery = new CatoryQueryImpl();
    Query Query;

    DefaultTableModel dt;
    String query = "";

    public IProxyImpl() throws RemoteException {
        super();
    }

    @Override
    public void eligeBaseDeDatos(String manejador) throws RemoteException {
        bd = factory.creaConexion(manejador);
        Query = factoryquery.creaQuery(manejador);
        //bd.abrir();
    }

    @Override
    public boolean inicarSesion(String usuario, String contrasena) throws RemoteException {
        query = Query.iniciarSesion(usuario, contrasena);
        try {
            if (bd.iniciarSesion(query)) {
                System.out.println("Valido inicio");
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
    }

    @Override
    public boolean insertarUsuario(String Username, String Password, String Email) {

        query = Query.insertausuario(Username, Password, Email);
        try {
            if (bd.Insert(query) == true) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
    }

    @Override
    public boolean insertarMafia(String dueno, String actividades, String lema) throws RemoteException {
        query = Query.insertarMafia(dueno, actividades, lema);
        try {
            if (bd.Insert(query) == true) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
    }

    @Override
    public DefaultTableModel verMafias() throws RemoteException {
        query = Query.verMafias();
        try {
            dt = bd.Select(query);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return dt;
    }

    @Override
    public boolean insertarFamilia(String nombreFam, String anosActivos, String territorio, String dueno, String actCriminales, int id_mafia) throws RemoteException {
        query = Query.insertaFamilia(nombreFam, anosActivos, territorio, dueno, actCriminales, id_mafia);
        try {
            if (bd.Insert(query) == true) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
    }

    @Override
    public DefaultTableModel verFamilia() throws RemoteException {
        query = Query.verFamilias();
        try {
            dt = bd.Select(query);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return dt;
    }

    @Override
    public boolean insertarMiembro(String Family_Name, String Personal_Name, String DateJoinedClan, String DateOfBirth, String Deceased, String Skills, int Family_ID) throws RemoteException {
        query = Query.insertarMiembro(Family_Name, Personal_Name, DateJoinedClan, DateOfBirth, Deceased, Skills, Family_ID);
        try {
            if (bd.Insert(query) == true) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
    }

    @Override
    public DefaultTableModel verMiembros() throws RemoteException {
        query = Query.verMiembros();
        try {
            dt = bd.Select(query);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return dt;
    }

    @Override
    public DefaultTableModel verJerarquias() throws RemoteException {
        query = Query.verJerarquias();
        try {
            dt = bd.Select(query);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return dt;
    }

    @Override
    public boolean insertaJerarquia(String Level_Number, String Entry_Name, int Mafia_ID) throws RemoteException {
        query = Query.insertaJerarquia(Level_Number, Entry_Name, Mafia_ID);
        try {
            if (bd.Insert(query) == true) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
    }

    @Override
    public boolean insertaTipoDeAsociacion(String Name, String Description) throws RemoteException {
        query = Query.insertaTipoDeAsociacion(Name, Description);
        try {
            if (bd.Insert(query) == true) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
    }

    @Override
    public DefaultTableModel verTiposDeAsociacion() throws RemoteException {
        query = Query.verTiposDeAsociacion();
        try {
            dt = bd.Select(query);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return dt;
    }

    @Override
    public boolean insertaMiembros_Asociacion(int Member_ID, int AssociatonTypeCode) throws RemoteException {
        query = Query.insertaMiembros_Asociacion(Member_ID, AssociatonTypeCode);
        try {
            if (bd.Insert(query) == true) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
    }

    @Override
    public DefaultTableModel verMiembros_Asociacion() {
        query = Query.verMiembros_Asociacion();
        try {
            dt = bd.Select(query);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return dt;
    }

    @Override
    public DefaultTableModel vista_MiembrosyAsociaciones(int Member_ID) {
        query = Query.vista_MiembrosyAsociaciones(Member_ID);
        try {
            dt = bd.Select(query);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return dt;
    }

    @Override
    public DefaultTableModel vista_MiembrosDeMafia(int Mafia_ID) throws RemoteException {
        query = Query.vista_MiembrosDeMafia(Mafia_ID);
        try {
            dt = bd.Select(query);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return dt;
    }

    @Override
    public List<String> validaRegistro(String usuario, String contra, String correo) throws RemoteException {
        List<Validador<InformacionUsuario>> validadores = new ArrayList<>();
        List<String> errores = new ArrayList<>();
        validadores.add(new CorreoValidador());
        validadores.add(new ContrasenaValidador());
        CompositeValidador composite = new CompositeValidador(validadores);
        InformacionUsuario infoUsuario = new InformacionUsuario(usuario, contra, correo);
        errores = composite.validate(infoUsuario);
        return errores;
    }

    @Override
    public DefaultTableModel verUsuarios() throws RemoteException {
        query = Query.verUsuarios();
        try {
            dt = bd.Select(query);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return dt;
    }

    @Override
    public boolean CambioMiembroEstadoVida(int Member_ID, String status) throws RemoteException {
        query = Query.CambioMiembroEstadoVida(Member_ID, status);
        try {
            if (bd.Update(query) == true) {
                //setChanged();
                //notifyObservers(new Integer(Member_ID));
                return true;
            }
        } catch (SQLException ex) {
            //Logger.getLogger(IProxyImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
