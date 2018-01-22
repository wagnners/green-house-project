/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.greenhouse.bean;

import br.udesc.greenhouse.modelo.entidade.Noticia;
import br.udesc.greenhouse.uc.GerenciarNoticiasUC;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author ignoi
 */
@ManagedBean
@ViewScoped
public class NoticiaBean {

    private List<Noticia> noticias;
    private List<Noticia> selecionados;
    private List<Noticia> filtrados;
    private Noticia selecionado;
    private Noticia novo;
    private GerenciarNoticiasUC gerenciador;

    @PostConstruct
    public void init() {
        gerenciador = new GerenciarNoticiasUC();
        novo = new Noticia();
        listar();
    }

    public void inserir(ActionEvent actionEvent) {
        if (gerenciador.inserir(novo)) {
            novo = new Noticia();
            RequestContext.getCurrentInstance().execute("PF('ndlg1').hide();");
            notificar("Sucesso", "Notícia inserida com sucesso!");
        } else {
            notificar("Falha", "Erro ao inserir notícia!");
        }
        listar();
    }

    public void editar(ActionEvent actionEvent) {
        if (selecionado != null) {
            if (gerenciador.editar(selecionado)) {
                RequestContext.getCurrentInstance().execute("PF('ndlg2').hide();");
                notificar("Sucesso", "Notícia editada com sucesso!");
            } else {
                notificar("Falha", "Erro ao editar notícia!");
            }
            listar();
            selecionado = null;
        } else {
            notificar("Falha", "É necessário selecionar uma notícia para editar.");
        }
    }

    public void remover(ActionEvent actionEvent) {
        if (selecionado == null) {
            notificar("Falha", "É necessário selecionar uma notícia antes de excluir.");
        } else {
            if (gerenciador.remover(selecionado.getNoticiaid())) {
                notificar("Sucesso", "Notícia removida com sucesso!");
            } else {
                notificar("Falha", "Erro ao remover notícia!");
            }
            listar();
        }
    }

    public void listar() {
        System.out.println("listei aqui");
        this.noticias = gerenciador.listar();
    }

    public void onRowSelect(SelectEvent event) {
        selecionado = ((Noticia) event.getObject());
        System.out.println(selecionado.toString() + " aqui");
    }

    public void onRowUnselect(UnselectEvent event) {
        selecionado = null;
    }

    public List<Noticia> getNoticias() {
        return noticias;
    }

    public void setNoticias(List<Noticia> noticias) {
        this.noticias = noticias;
    }

    public Noticia getSelecionado() {
        return selecionado;
    }

    public void setSelecionado(Noticia selecionado) {
        this.selecionado = selecionado;
    }

    public void notificar(String title, String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(title, msg));
    }

    public List<Noticia> getSelecionados() {
        return selecionados;
    }

    public void setSelecionados(List<Noticia> selecionados) {
        this.selecionados = selecionados;
    }

    public Noticia getNovo() {
        return novo;
    }

    public void setNovo(Noticia novo) {
        this.novo = novo;
    }

    public List<Noticia> getFiltrados() {
        return filtrados;
    }

    public void setFiltrados(List<Noticia> filtrados) {
        this.filtrados = filtrados;
    }

}
