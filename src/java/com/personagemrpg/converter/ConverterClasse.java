
package com.personagemrpg.converter;

import com.personagemrpg.modelo.Classe;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author gustavo
 */
@FacesConverter(value = "converterClasse")
public class ConverterClasse implements Serializable, Converter {

    @PersistenceContext(unitName = "PersonagemRPG-WebPU")
    private EntityManager em;    
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.equals("Selecione um registro")){
            return null;
        }
        return em.find(Classe.class, Integer.parseInt(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o == null){
            return null;
        }
        Classe obj = (Classe) o;
        return obj.getId().toString();
    }

}
