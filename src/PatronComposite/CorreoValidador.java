/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatronComposite;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alber
 */
public class CorreoValidador implements Validador<InformacionUsuario>{

    int i = 0;
    
    @Override
    public List<String> validate(InformacionUsuario info) {
        List<String> errors = new ArrayList<>();
        String email = info.getCorreo();
        for (int j = 0; j < email.length(); j++) {
            if (email.charAt(j) == '@') {
                i++;
            }
        }
        if (i != 1) {
            errors.add("El email no es válido");
        }
        return errors;
    } 
}
