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
public class ContrasenaValidador implements Validador<InformacionUsuario>{

    int mayusculas = 0;
    int especiales = 0;
    int numericos = 0;
    
    
    @Override
    public List<String> validate(InformacionUsuario info) {
        
        List<String> errores = new ArrayList<>();
        
        String contrasena = info.getContrasena();
        
        if (contrasena.isEmpty()) {
            errores.add("La contraseña está vacía");
        } else {
            if (contrasena.length() < 8) {
                errores.add("La contraseña debe contener mínimo 8 caracteres");
            } 
            if (forzarContrasena(contrasena) == false) {
                errores.add("La contraseña debe contener al menos una mayúscula"
                        + "dos caracteres numéricos y dos caracteres especiales");
            }
        }
        return errores;
        
    }
    
    private boolean forzarContrasena(String contrasena) {

        for (int i = 0; i < contrasena.length(); i++) {
            if (Character.isLetter(contrasena.charAt(i))) {
                if (Character.isUpperCase(contrasena.charAt(i))) {
                    mayusculas++;
                }
            } else {
                if (Character.isDigit(contrasena.charAt(i))) {
                    numericos++;
                } else {
                    especiales++;
                }
            }
        }      
        if (mayusculas >= 1 && numericos >= 2 && especiales >= 2) {
            return true;
        }
        return false;
    }
    
    
}
