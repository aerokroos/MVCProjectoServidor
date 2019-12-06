package BaseDatosFactory;
public class FactoryImpl  implements Factory{
    public final static String SQLServer = "SQLServer";
    public final static String MYSQL = "MySQL";
    @Override
    public BaseDeDatos creaConexion(String DBMS) {
        switch (DBMS){
            case SQLServer:
                return new SQLServer();
            case MYSQL:
                return new MySQL();
        }
        return null;
    }
}
