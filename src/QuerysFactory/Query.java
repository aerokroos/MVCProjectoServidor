/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuerysFactory;

/**
 *
 * @author villalobos28
 */
public interface Query {
    public String insertarMafia(String Membership,String Activities,String Customs);
    
    public String verMafias();
    
    public String insertaFamilia(String Family_Name,String Years_Active,String Territory,String Membership,String Criminal_Activities,int Mafia_ID);
    
    public String verFamilias();
    
    public String insertarMiembro (String Family_Name,String Personal_Name,String DateJoinedClan,String DateOfBirth,String Deceased,String Skills,int Family_ID);
    
    public String verMiembros();
    
    public String verJerarquias();
    
    public String insertaJerarquia(String Level_Number,String Entry_Name,int Mafia_ID);
    
    public String insertaTipoDeAsociacion(String Name,String Description);
    
    public String verTiposDeAsociacion();
    
    public String insertaMiembros_Asociacion(int Member_ID,int AssociatonTypeCode);
    
    public String verMiembros_Asociacion ();
    
    public String vista_MiembrosyAsociaciones (int Member_ID);
    
    public String vista_MiembrosDeMafia (int Mafia_ID);
    
    public String iniciarSesion (String Username, String Password);
    
    public String insertausuario (String Username, String Password, String Email);
    
    public String verUsuarios ();
    
    public String CambioMiembroEstadoVida (int Member_ID, String status);
}
