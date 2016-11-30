package com.personagemrpg.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author gustavo
 */
public class Util {

    public static void mensagemErro(String mensagem) {
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, "");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }

    public static void mensagemInformacao(String mensagem) {
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, "");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }

    public static String getMensagemErro(Exception e) {
        while (e.getCause() != null) {
            e = (Exception) e.getCause();
        }
        String retorno = e.getMessage();
        return retorno;
    }
}
