package com.personagemrpg.controle;

import com.personagemrpg.dao.ClasseDAO;
import com.personagemrpg.dao.ItemDAO;
import com.personagemrpg.dao.OutfitDAO;
import com.personagemrpg.dao.PersonagemDAO;
import com.personagemrpg.modelo.Classe;
import com.personagemrpg.modelo.Item;
import com.personagemrpg.modelo.Outfit;
import com.personagemrpg.modelo.Personagem;
import com.personagemrpg.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author gustavo
 */
@Named(value = "controlePersonagem")
@SessionScoped
public class ControlePersonagem implements Serializable {

    @EJB
    private PersonagemDAO<Personagem> dao;
    @EJB
    private ClasseDAO<Classe> daoClasse;
    @EJB
    private OutfitDAO<Outfit> daoOutfit;
    private Personagem obj;
    private Boolean editando;
    @EJB
    private ItemDAO<Item> daoItem;
    private Item item;
    private Boolean editandoItem;

    public ControlePersonagem() {
        editando = false;
        editandoItem = false;
    }

    public String listar() {
        editando = false;
        return "/privado/personagem/listar?faces-redirect=true";
    }

    public void novo() {
        editando = true;
        obj = new Personagem();
        editandoItem = false;
    }

    public void alterar(Integer id) {
        try {
            obj = dao.getObjectById(id);
            editando = true;
            editandoItem = false;
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

     public void novoItem() {
        editandoItem = true;
    }

    public void salvarItem() {
        System.out.println("aqui");
        if (!obj.getItens().contains(item)) {
            obj.getItens().add(item);
            editandoItem = false;
            Util.mensagemInformacao("Item adicionado com sucesso!");
        } else {
            Util.mensagemErro("Personagem já possui este item!");
        }
    }

    public void removeItem(Item objeto) {
        obj.getItens().remove(objeto);
        Util.mensagemInformacao("Item removido com sucesso");
    }

    public PersonagemDAO<Personagem> getDao() {
        return dao;
    }

    public void setDao(PersonagemDAO<Personagem> dao) {
        this.dao = dao;
    }

    public Personagem getObj() {
        return obj;
    }

    public void setObj(Personagem obj) {
        this.obj = obj;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }

    public ClasseDAO<Classe> getDaoClasse() {
        return daoClasse;
    }

    public void setDaoClasse(ClasseDAO<Classe> daoClasse) {
        this.daoClasse = daoClasse;
    }

    public OutfitDAO<Outfit> getDaoOutfit() {
        return daoOutfit;
    }

    public void setDaoOutfit(OutfitDAO<Outfit> daoOutfit) {
        this.daoOutfit = daoOutfit;
    }

    public ItemDAO<Item> getDaoItem() {
        return daoItem;
    }

    public void setDaoItem(ItemDAO<Item> daoItem) {
        this.daoItem = daoItem;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Boolean getEditandoItem() {
        return editandoItem;
    }

    public void setEditandoItem(Boolean editandoItem) {
        this.editandoItem = editandoItem;
    }

}
