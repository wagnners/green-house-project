/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.greenhouse.bean;

import br.udesc.greenhouse.modelo.entidade.Configuracao;
import br.udesc.greenhouse.uc.ConfiguracaoUC;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author ignoi
 */
@ManagedBean
@ViewScoped
public class ConfiguracoesBean {

    private Configuracao configuracao;
    private ConfiguracaoUC uc;

    @PostConstruct
    public void init() {
        uc = new ConfiguracaoUC();
        configuracao = uc.getConfiguracao();
    }

    public Configuracao getConfiguracao() {
        return configuracao;
    }

    public void setConfiguracao(Configuracao configuracao) {
        this.configuracao = configuracao;
    }

    public void salvar() {
        try {

            uc.salvarConfiguracao(configuracao);
            saveMessage("Sucesso!", "Novas configurações aplicadas!");
        } catch (Exception e) {
            SessionUtil.saveMessage("Problema ao salvar configurações", "Tente novamente mais tarde");
        }
    }
    
    public static void saveMessage(String title, String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(title, msg));
    }

}
