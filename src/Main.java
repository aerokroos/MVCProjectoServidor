
import Controlador.IProxy;
import Controlador.IProxyImpl;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author villalobos28
 */
public class Main {
    public static void main(String[] args){
        try {
            Registry registro = LocateRegistry.createRegistry(6900);
            IProxy generador = new IProxyImpl();
            registro.rebind("server", generador);
            System.out.println("Online");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
