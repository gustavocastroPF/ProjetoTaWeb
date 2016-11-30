package com.personagemrpg.dao;

import com.personagemrpg.modelo.Item;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author gustavo
 */
@Stateful
public class ItemDAO<T> extends DAOGenerico<Item> implements Serializable{

    public ItemDAO() {
        super();
        super.setClassePersistente(Item.class);
    }
    
}
