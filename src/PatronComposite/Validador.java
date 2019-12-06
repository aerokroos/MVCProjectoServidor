/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatronComposite;

import java.util.List;

/**
 *
 * @author alber
 */
public interface Validador<T> {
        
    List<String> validate(T info);

}
