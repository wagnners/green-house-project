/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.greenhouse.bean;

import br.udesc.greenhouse.modelo.dao.core.FactoryDAO;
import br.udesc.greenhouse.modelo.entidade.Oficina;
import br.udesc.greenhouse.modelo.entidade.Registro;
import br.udesc.greenhouse.modelo.entidade.Usuario;
import br.udesc.greenhouse.uc.GerenciarRegistrosUC;
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
public class AtividadeBean {

    private List<Registro> registros;
    private List<Registro> selecionados;
    private List<Registro> filtrados;
    private Registro selecionado;
    private Registro novo;
    private GerenciarRegistrosUC gerenciador;
    private Usuario usuario;
    private List<Oficina> oficinas;
    private Oficina oficina;

    @PostConstruct
    public void init() {

        usuario = (Usuario) SessionUtil.getParam("usuario");

        gerenciador = new GerenciarRegistrosUC();

        oficinas = gerenciador.getOficinas();

        criar();
        listar();
    }

    private long idex;

    public long getIdex() {
        return idex;
    }

    public void setIdex(long idex) {
        this.idex = idex;
    }

    public void inserir(ActionEvent actionEvent) {
        Oficina o = FactoryDAO.getFactoryDAO().getOficinaDAO().pesquisar(idex);
        novo.setOficina(o);
        if (gerenciador.inserir(novo)) {
            criar();
            RequestContext.getCurrentInstance().execute("PF('ndlg1').hide();");
            notificar("Sucesso", "Atividade inserida com sucesso!");
        } else {
            notificar("Falha", "Erro ao inserir atividade!");
        }
        listar();
    }

    public void editar(ActionEvent actionEvent) {
        if (selecionado != null) {
            if (usuario.equals(selecionado.getUsuario())) {

                if (gerenciador.editar(selecionado)) {
                    RequestContext.getCurrentInstance().execute("PF('ndlg2').hide();");
                    notificar("Sucesso", "Atividade editada com sucesso!");
                } else {
                    notificar("Falha", "Erro ao editar atividade!");
                }
                listar();
                selecionado = null;
            } else {
                notificar("Falha", "A atividade é de outro usuário!");
            }
        } else {
            notificar("Falha", "É necessário selecionar uma atividade para editar.");
        }
    }

    public void remover(ActionEvent actionEvent) {
        if (selecionado == null) {
            notificar("Falha", "É necessário selecionar uma atividade antes de excluir.");
        } else {
            if (usuario.equals(selecionado.getUsuario())) {
                if (gerenciador.remover(selecionado.getRegistroid())) {
                    notificar("Sucesso", "Atividade removida com sucesso!");
                } else {
                    notificar("Falha", "Erro ao remover atividade!");
                }
            } else {
                notificar("Falha", "A atividade é de outro usuário!");
            }
            listar();
        }
    }

    public void listar() {
        System.out.println("listei aqui");
        this.registros = gerenciador.getRegistros();
    }

    public void onRowSelect(SelectEvent event) {
        selecionado = ((Registro) event.getObject());
        System.out.println(selecionado.toString() + " aqui");
    }

    public void onRowUnselect(UnselectEvent event) {
        selecionado = null;
    }

    public List<Registro> getRegistros() {
        return registros;
    }

    public void setRegistros(List<Registro> registros) {
        this.registros = registros;
    }

    public Registro getSelecionado() {
        return selecionado;
    }

    public void setSelecionado(Registro selecionado) {
        this.selecionado = selecionado;
    }

    public void notificar(String title, String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(title, msg));
    }

    public List<Registro> getSelecionados() {
        return selecionados;
    }

    public void setSelecionados(List<Registro> selecionados) {
        this.selecionados = selecionados;
    }

    public Registro getNovo() {
        return novo;
    }

    public void setNovo(Registro novo) {
        this.novo = novo;
    }

    public List<Registro> getFiltrados() {
        return filtrados;
    }

    public void setFiltrados(List<Registro> filtrados) {
        this.filtrados = filtrados;
    }

    private void criar() {
        novo = new Registro();
        novo.setUsuario(usuario);
    }

    public List<Oficina> getOficinas() {
        return oficinas;
    }

    public void setOficinas(List<Oficina> oficinas) {
        this.oficinas = oficinas;
    }

    public Oficina getOficina() {
        return oficina;
    }

    public void setOficina(Oficina oficina) {
        this.oficina = oficina;
    }

}
