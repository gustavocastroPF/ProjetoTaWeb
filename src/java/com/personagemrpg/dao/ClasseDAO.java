package com.personagemrpg.dao;

import com.personagemrpg.modelo.Classe;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author gustavo
 */
@Stateful
public class ClasseDAO<T> extends DAOGenerico<Classe> implements Serializable{

    public ClasseDAO() {
        super();
        super.setClassePersistente(Classe.class);
    }
    
}
