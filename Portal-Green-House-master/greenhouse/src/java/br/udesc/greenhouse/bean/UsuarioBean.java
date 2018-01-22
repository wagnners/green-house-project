/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.greenhouse.bean;

import br.udesc.greenhouse.modelo.dao.core.FactoryDAO;
import br.udesc.greenhouse.modelo.entidade.Oficina;
import br.udesc.greenhouse.modelo.entidade.Periodo;
import br.udesc.greenhouse.modelo.entidade.Usuario;
import br.udesc.greenhouse.uc.GerenciarUsuariosUC;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DualListModel;

/**
 *
 * @author ignoi
 */
@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable {

    private List<Usuario> usuarios;
    private List<Usuario> selecionados;
    private List<Usuario> filtrados;
    private Usuario selecionado;
    private Usuario novo;
    private GerenciarUsuariosUC gerenciador;
//    private DualListModel<Periodo> periodos;
    private Periodo periodo;
    private List<Periodo> source;
//    private List<Periodo> target;
    private List<String> longs;

    public List<String> getLongs() {
        return longs;
    }

    public void setLongs(List<String> longs) {
        this.longs = longs;
    }

//    public DualListModel<Periodo> getPeriodos() {
//        return periodos;
//    }
//
//    private DualListModel<Periodo> periodosE;
//    private List<Periodo> source1;
//    private List<Periodo> target1;
    @PostConstruct
    public void init() {

        gerenciador = new GerenciarUsuariosUC();

//        target = new ArrayList<>();
//        target1 = new ArrayList<>();
//        source1 = new ArrayList<>();
        listar();

        novo = new Usuario();
        periodo = new Periodo();

//        periodosE = new DualListModel<>(source1, target1);
    }

    public void inserir(ActionEvent actionEvent) {
//        // mudei pra target
//        novo.setPeriodos(periodos.getTarget());
        List<Periodo> p = new ArrayList<>();
        for (String l : longs) {
            p.add(FactoryDAO.getFactoryDAO().getPeridoDAO().pesquisar(Long.parseLong(l)));
        }
        novo.setPeriodos(p);
        System.out.println(novo);
        System.out.println("inseri aqui");
        if (gerenciador.inserir(novo)) {
            novo = new Usuario();
            RequestContext.getCurrentInstance().execute("PF('dlg1').hide();");
            notificar("Sucesso", "Usuário inserido com sucesso!");
        } else {
            notificar("Falha", "Erro ao inserir usuário!");
        }
        listar();
    }

    public void editar(ActionEvent actionEvent) {
        if (selecionado != null) {
            System.out.println("editei aqui");
            List<Periodo> p = new ArrayList<>();
            for (String l : longs) {
                p.add(FactoryDAO.getFactoryDAO().getPeridoDAO().pesquisar(Long.parseLong(l)));
            }
            selecionado.setPeriodos(p);
//            selecionado.setPeriodos(periodosE.getTarget());
            if (gerenciador.editar(selecionado)) {
                RequestContext.getCurrentInstance().execute("PF('dlg2').hide();");
                notificar("Sucesso", "Usuário editado com sucesso!");
            } else {
                notificar("Falha", "Erro ao editar usuário!");
            }
            listar();
            selecionado = null;
        } else {
            notificar("Falha", "É necessário selecionar um usuário para editar.");
        }
    }

    public void remover(ActionEvent actionEvent) {
        if (selecionado == null) {
            notificar("Falha", "É necessário selecionar um usuário antes de excluir.");
        } else {
            if (gerenciador.remover(selecionado.getUsuarioid())) {
                notificar("Sucesso", "Usuário removido com sucesso!");
            } else {
                notificar("Falha", "Erro ao remover usuário!");
            }
            listar();
        }
    }

    public void editar1() {
        try {
//            source1 = gerenciador.listarPeriodos();
//
//            target1 = selecionado.getPeriodos();
//
//            source1.removeAll(target1);
//            periodosE = new DualListModel<>(source1, target1);
            List<String> strings = new ArrayList();
            for (Periodo p : selecionado.getPeriodos()) {
                strings.add(p.toString());
            }
            longs = strings;
            RequestContext.getCurrentInstance().execute("PF('dlg2').show();");
        } catch (NullPointerException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro!", "É necessário selecionar um usuário para editar"));
        }

    }

    public void listar() {
        System.out.println("listei aqui");
        this.usuarios = gerenciador.listar();
        this.source = gerenciador.listarPeriodos();

//        periodos = new DualListModel<>(source, target);
//        periodosE = new DualListModel<>(source, target);
    }

    public void onRowSelect(SelectEvent event) {
        selecionado = ((Usuario) event.getObject());
        System.out.println(selecionado.toString() + " aqui");
    }

    public void onRowUnselect(UnselectEvent event) {
        selecionado = null;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Usuario getSelecionado() {
        return selecionado;
    }

    public void setSelecionado(Usuario selecionado) {
        this.selecionado = selecionado;
    }

    public void notificar(String title, String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(title, msg));
    }

    public List<Usuario> getSelecionados() {
        return selecionados;
    }

    public void setSelecionados(List<Usuario> selecionados) {
        this.selecionados = selecionados;
    }

    public Usuario getNovo() {
        return novo;
    }

    public void setNovo(Usuario novo) {
        this.novo = novo;
    }

    public List<Usuario> getFiltrados() {
        return filtrados;
    }

    public void setFiltrados(List<Usuario> filtrados) {
        this.filtrados = filtrados;
    }

//    public void setPeriodos(DualListModel<Periodo> periodos) {
//        this.periodos = periodos;
//    }
    public void onTransfer(TransferEvent event) {
        StringBuilder builder = new StringBuilder();
        for (Object item : event.getItems()) {
            builder.append(((Periodo) item).getDiaDaSemana()).append("<br />");
        }

        FacesMessage msg = new FacesMessage();
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        msg.setSummary("Items Transferred");
        msg.setDetail(builder.toString());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onSelect(SelectEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Selected", event.getObject().toString()));
    }

    public void onUnselect(UnselectEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Unselected", event.getObject().toString()));
    }

    public void onReorder() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "List Reordered", null));
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public void incluir() {
        if (gerenciador.incluir(periodo)) {
            System.out.println("vou resetar o perído");
            periodo = new Periodo();

            RequestContext.getCurrentInstance().execute("PF('incluir').hide();");
            notificar("Sucesso", "Período inserido com sucesso!");
        } else {
            notificar("Erro", "Hora inicial não pode ser maior que final.");
        }
        listar();
    }

//    public DualListModel<Periodo> getPeriodosE() {
//        return periodosE;
//    }
//
//    public void setPeriodosE(DualListModel<Periodo> periodosE) {
//        this.periodosE = periodosE;
//    }
    public List<Periodo> getSource() {
        return source;
    }

    public void setSource(List<Periodo> source) {
        this.source = source;
    }

}
