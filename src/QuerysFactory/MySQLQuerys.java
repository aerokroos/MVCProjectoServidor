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
public class MySQLQuerys implements Query {

    @Override
    public String insertarMafia(String Membership, String Activities, String Customs) {
        return "INSERT INTO `MafiaOrganisedCrime`.`Mafia`\n"
                + "(`Membership`,`Activities`,`Customs`)\n"
                + "VALUES\n"
                + "('" + Membership + "','" + Activities + "','" + Customs + "');";
    }

    @Override
    public String verMafias() {
        return "SELECT `Mafia`.`Mafia_ID`,\n"
                + "    `Mafia`.`DateOfOrigin`,\n"
                + "    `Mafia`.`Membership`,\n"
                + "    `Mafia`.`Activities`,\n"
                + "    `Mafia`.`Customs`\n"
                + "FROM `MafiaOrganisedCrime`.`Mafia`;";
    }

    @Override
    public String insertaFamilia(String Family_Name, String Years_Active, String Territory, String Membership, String Criminal_Activities, int Mafia_ID) {
        return "INSERT INTO `MafiaOrganisedCrime`.`Families`\n"
                + "(`Family_Name`,`Years_Active`,`Territory`,`Membership`,`Criminal_Activities`,`Mafia_ID`)\n"
                + "VALUES\n"
                + "('" + Family_Name + "','" + Years_Active + "','" + Territory + "','" + Membership + "','" + Criminal_Activities + "','" + Mafia_ID + "');";
    }

    @Override
    public String verFamilias() {
        return "SELECT `Families`.`Family_ID`,\n"
                + "    `Families`.`Family_Name`,\n"
                + "    `Families`.`Years_Active`,\n"
                + "    `Families`.`Territory`,\n"
                + "    `Families`.`Membership`,\n"
                + "    `Families`.`Criminal_Activities`,\n"
                + "    `Families`.`Mafia_ID`\n"
                + "FROM `MafiaOrganisedCrime`.`Families`;";
    }

    @Override
    public String insertarMiembro(String Family_Name, String Personal_Name, String DateJoinedClan, String DateOfBirth, String Deceased, String Skills, int Family_ID) {
        return "INSERT INTO `MafiaOrganisedCrime`.`Members`\n"
                + "(`Family_Name`,`Personal_Name`,`DateJoinedClan`,`DateOfBirth`,`Deceased`,`Skills`,`Family_ID`)\n"
                + "VALUES\n"
                + "('" + Family_Name + "','" + Personal_Name + "','" + DateJoinedClan + "','" + DateOfBirth + "','" + Deceased + "','" + Skills + "','" + Family_ID + "');";
    }

    @Override
    public String verMiembros() {
        return "SELECT `Members`.`Member_ID`,\n"
                + "    `Members`.`Family_Name`,\n"
                + "    `Members`.`Personal_Name`,\n"
                + "    `Members`.`DateJoinedClan`,\n"
                + "    `Members`.`DateOfBirth`,\n"
                + "    `Members`.`Deceased`,\n"
                + "    `Members`.`Skills`,\n"
                + "    `Members`.`Family_ID`\n"
                + "FROM `MafiaOrganisedCrime`.`Members`;";
    }

    @Override
    public String verJerarquias() {
        return "SELECT * FROM MafiaOrganisedCrime.Mafia_Hierarchy;";
    }

    @Override
    public String insertaJerarquia(String Level_Number, String Entry_Name, int Mafia_ID) {
        return "INSERT INTO `MafiaOrganisedCrime`.`Mafia_Hierarchy`\n"
                + "(`Level_Number`,`Entry_Name`,`Mafia_ID`)\n"
                + "VALUES\n"
                + "('" + Level_Number + "','" + Entry_Name + "','" + Mafia_ID + "');";
    }

    @Override
    public String insertaTipoDeAsociacion(String Name, String Description) {
        return "INSERT INTO `MafiaOrganisedCrime`.`AssociatonTypes`\n"
                + "(`Name`,`Description`)\n"
                + "VALUES\n"
                + "('" + Name + "','" + Description + "');";
    }

    @Override
    public String verTiposDeAsociacion() {
        return "SELECT * FROM MafiaOrganisedCrime.AssociatonTypes;";
    }

    @Override
    public String insertaMiembros_Asociacion(int Member_ID, int AssociatonTypeCode) {
        return "INSERT INTO `MafiaOrganisedCrime`.`Members_AssociatonTypes`\n"
                + "(`Member_ID`,`AssociatonTypeCode`)\n"
                + "VALUES\n"
                + "('" + Member_ID + "','" + AssociatonTypeCode + "');";
    }

    @Override
    public String verMiembros_Asociacion() {
        return "SELECT * FROM MafiaOrganisedCrime.Members_AssociatonTypes;";
    }

    @Override
    public String vista_MiembrosyAsociaciones(int Member_ID) {
        return "SELECT m.Family_Name as 'Nombre de la familia', m.Personal_Name as 'Nombre personal',\n"
                + "        m.Deceased as '¿Vivo o muerto?', m.Skills as 'Habilidades', a.Name as 'Organizaciónes a la que pertenece'\n"
                + "\n"
                + "FROM `Members` as m \n"
                + "    JOIN `Members_AssociatonTypes` as ma \n"
                + "        ON m.Member_ID = ma.Member_ID\n"
                + "            JOIN `AssociatonTypes` as a \n"
                + "                ON a.AssociatonTypeCode = ma.AssociatonTypeCode\n"
                + "                    WHERE m.Member_ID =" + Member_ID + ";";
    }

    @Override
    public String vista_MiembrosDeMafia(int Mafia_ID) {
        return "SELECT m.Mafia_ID, m.Membership as 'Dueño', \n"
                + "f.Family_Name as 'Familia', me.Personal_Name as 'Nombre de miembro'\n"
                + "        \n"
                + "\n"
                + "FROM `Mafia` AS m JOIN `Families` as f ON m.Mafia_ID = f.Mafia_ID\n"
                + "    JOIN `Members` as me ON f.Family_ID = me.Family_ID\n"
                + "        WHERE m.Mafia_ID = '" + Mafia_ID + "';";
    }

    @Override
    public String iniciarSesion(String Username, String Password) {
        return "SELECT User_ID FROM `Users`\n"
                + "WHERE `Username` = '" + Username + "'\n"
                + "AND `Password` = '" + Password + "';";
    }

    @Override
    public String insertausuario(String Username, String Password, String Email) {
        return "INSERT INTO `MafiaOrganisedCrime`.`Users`\n"
                + "(`Username`,`Password`,`Email`)\n"
                + "VALUES\n"
                + "('"+Username+"','"+Password+"','"+Email+"');";
    }

    @Override
    public String verUsuarios() {
        return "SELECT * FROM `MafiaOrganisedCrime`.`Users`;";
    }

    @Override
    public String CambioMiembroEstadoVida(int Member_ID, String status) {
        return "UPDATE `Members` SET `Deceased` = "+status+" WHERE Member_ID = "+Member_ID+"";
    }
    
}
