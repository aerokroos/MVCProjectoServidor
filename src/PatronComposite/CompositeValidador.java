/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatronComposite;

//import com.microsoft.rest.Validator;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alber
 */
public class CompositeValidador<T> implements Validador<T> {

    private final List<Validador<T>> validadors;
    
    public CompositeValidador(List<Validador<T>> validadors) {
        this.validadors = validadors;
    }
    @Override
    public List<String> validate(T info) {
        List<String> errores = new ArrayList<>();

        for (Validador e :  validadors) {
            errores.addAll(e.validate(info));
        }

        return errores;
    
    }
    
    
    
}
