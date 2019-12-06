package Controlador;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author alber
 */
public interface IProxy extends Remote {
    
    public void eligeBaseDeDatos(String manejador) throws RemoteException;
    
    public boolean inicarSesion(String usuario, String contrasena) throws RemoteException;
    
    public boolean insertarUsuario(String Username, String Password, String Email) throws RemoteException;
    
    public boolean insertarMafia(String dueno,String actividades,String lema) throws RemoteException;
    
    public DefaultTableModel verMafias() throws RemoteException;
    
    public boolean insertarFamilia(String Family_Name,String Years_Active,String Territory,String Membership,String Criminal_Activities,int Mafia_ID) throws RemoteException;
    
    public DefaultTableModel verFamilia() throws RemoteException;
    
    public boolean insertarMiembro (String Family_Name,String Personal_Name,String DateJoinedClan,String DateOfBirth,String Deceased,String Skills,int Family_ID) throws RemoteException;
    
    public DefaultTableModel verMiembros() throws RemoteException;
    
    public DefaultTableModel verJerarquias() throws RemoteException;
    
    public boolean insertaJerarquia(String Level_Number,String Entry_Name,int Mafia_ID) throws RemoteException;
    
    public boolean insertaTipoDeAsociacion(String Name,String Description) throws RemoteException;
    
    public DefaultTableModel verTiposDeAsociacion() throws RemoteException;
    
    public boolean insertaMiembros_Asociacion(int Member_ID, int AssociatonTypeCode) throws RemoteException;
    
    public DefaultTableModel verMiembros_Asociacion() throws RemoteException;
    
    public DefaultTableModel vista_MiembrosyAsociaciones (int Member_ID) throws RemoteException;
    
    public DefaultTableModel vista_MiembrosDeMafia (int Mafia_ID) throws RemoteException;
    
    public List<String> validaRegistro(String usuario, String contra, String correo) throws RemoteException;
    
    public DefaultTableModel verUsuarios() throws RemoteException;
    
    public boolean CambioMiembroEstadoVida (int Member_ID, String status) throws RemoteException; 
}
