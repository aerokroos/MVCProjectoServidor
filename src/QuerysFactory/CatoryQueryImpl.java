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
public class CatoryQueryImpl implements FactoryQuery {
    public final static String SQLServer = "SQLServer";
    public final static String MYSQL = "MySQL";
    @Override
    public Query creaQuery(String SMBD) {
        switch (SMBD) {
            case SQLServer:
                return new SQLServerQuerys();
            case MYSQL:
                return new MySQLQuerys();
        }
        return null;
    }
}
