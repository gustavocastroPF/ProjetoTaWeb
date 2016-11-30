package com.personagemrpg.controle;

import com.personagemrpg.dao.ItemDAO;
import com.personagemrpg.modelo.Item;
import com.personagemrpg.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author gustavo
 */
@Named(value = "controleItem")
@SessionScoped
public class ControleItem implements Serializable {

    @EJB
    private ItemDAO<Item> dao;
    private Item obj;
    private Boolean editando;

    public ControleItem() {
        editando = false;
    }

    public String listar() {
        editando = false;
        return "/privado/item/listar?faces-redirect=true";
    }

    public void novo() {
        obj = new Item();
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

    public ItemDAO<Item> getDao() {
        return dao;
    }

    public void setDao(ItemDAO<Item> dao) {
        this.dao = dao;
    }

    public Item getObj() {
        return obj;
    }

    public void setObj(Item obj) {
        this.obj = obj;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }

}
