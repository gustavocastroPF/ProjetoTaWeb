package com.personagemrpg.controle;


import com.personagemrpg.dao.OutfitDAO;
import com.personagemrpg.modelo.Outfit;
import com.personagemrpg.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author gustavo
 */
@Named(value = "controleOutfit")
@SessionScoped
public class ControleOutfit implements Serializable {

    @EJB
    private OutfitDAO<Outfit> dao;
    private Outfit obj;
    private Boolean editando;

    public ControleOutfit() {
        editando = false;
    }

    public String listar() {
        editando = false;
        return "/privado/outfit/listar?faces-redirect=true";
    }

    public void novo() {
        obj = new Outfit();
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

    public OutfitDAO<Outfit> getDao() {
        return dao;
    }

    public void setDao(OutfitDAO<Outfit> dao) {
        this.dao = dao;
    }

    public Outfit getObj() {
        return obj;
    }

    public void setObj(Outfit obj) {
        this.obj = obj;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }

}
