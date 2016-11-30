package com.personagemrpg.controle;

import com.personagemrpg.dao.ClasseDAO;
import com.personagemrpg.modelo.Classe;
import com.personagemrpg.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author gustavo
 */
@Named(value = "controleClasse")
@SessionScoped
public class ControleClasse implements Serializable {

    @EJB
    private ClasseDAO<Classe> dao;
    private Classe obj;
    private Boolean editando;

    public ControleClasse() {
        editando = false;
    }

    public String listar() {
        editando = false;
        return "/privado/classe/listar?faces-redirect=true";
    }

    public void novo() {
        obj = new Classe();
        editando = true;
    }

    public void alterar(Integer id) {
        try {
            obj = dao.getObjectById(id);
            editando = true;
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar obj: " + Util.getMensagemErro(e));
        }
    }

    public void remover(Integer id) {
        try {
            obj = dao.getObjectById(id);
            dao.remove(obj);
            Util.mensagemInformacao("Objeto removido com sucesso!");
        } catch (Exception e) {
            Util.mensagemErro("Erro ao remover obj: " + Util.getMensagemErro(e));
        }
    }

    public void salvar() {
        try {
            if (obj.getId() == null) {
                dao.persist(obj);
            } else {
                dao.merge(obj);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso!");
            editando = false;
        } catch (Exception e) {
            System.out.println("Erro:" + Util.getMensagemErro(e));
            Util.mensagemErro("Erro ao persistir obj: " + Util.getMensagemErro(e));
        }
    }

    public ClasseDAO<Classe> getDao() {
        return dao;
    }

    public void setDao(ClasseDAO<Classe> dao) {
        this.dao = dao;
    }

    public Classe getObj() {
        return obj;
    }

    public void setObj(Classe obj) {
        this.obj = obj;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }

}
