package com.personagemrpg.dao;

import com.personagemrpg.modelo.Outfit;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author gustavo
 */
@Stateful
public class OutfitDAO<T> extends DAOGenerico<Outfit> implements Serializable{

    public OutfitDAO() {
        super();
        super.setClassePersistente(Outfit.class);
    }
    
}
