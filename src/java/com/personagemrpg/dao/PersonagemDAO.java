package com.personagemrpg.dao;

import com.personagemrpg.modelo.Personagem;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author gustavo
 */
@Stateful
public class PersonagemDAO<T> extends DAOGenerico<Personagem> implements Serializable{

    public PersonagemDAO() {
        super();
        super.setClassePersistente(Personagem.class);
    }
    
}
