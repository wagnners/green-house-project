/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.greenhouse.bean;

import br.udesc.greenhouse.modelo.dao.core.ConfiguracaoDAO;
import br.udesc.greenhouse.modelo.dao.core.FactoryDAO;
import br.udesc.greenhouse.modelo.entidade.Configuracao;
import br.udesc.greenhouse.modelo.entidade.Noticia;
import br.udesc.greenhouse.modelo.entidade.Oficina;
import br.udesc.greenhouse.uc.FormularioMensagemUC;
import br.udesc.greenhouse.uc.GerenciarNoticiasUC;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author ignoi
 */
@ManagedBean
@RequestScoped
public class IndexBean {

    private List<Noticia> usuarios;
    private List<Oficina> oficinas;
    private GerenciarNoticiasUC gerenciador;
    private Gson g;
    private String assunto, corpo, nome, emailOrigem;
    private Configuracao config;
    private ConfiguracaoDAO cdao;

    private Oficina teste;

    @PostConstruct
    public void init() {
        cdao = FactoryDAO.getFactoryDAO().getConfiguracaoDAO();
        if (cdao.listar().isEmpty()) {
            cdao.inserir(new Configuracao("email@email.com", "Aqui deve constar o texto sobre nós", "Aqui deve constar o endereço do projeto"));
        }
        config = cdao.listar().get(0);
        g = new Gson();
        gerenciador = new GerenciarNoticiasUC();
        usuarios = gerenciador.listar();
        oficinas = FactoryDAO.getFactoryDAO().getOficinaDAO().listar();
        limpar();

//        html = new HtmlPanelGroup();
//        html.setLayout("block");
//
//        FacesContext fc = FacesContext.getCurrentInstance();
//        ImageStreamer is = new ImageStreamer();
//
//        GraphicImage imagem = new GraphicImage();
//        try {
//            imagem.setValue(is.getImage(teste));
//        } catch (IOException ex) {
//            System.out.println("DEU EXCEÇÂO");
//        }
//        imagem.setWidth("400px");
////        
//        
//        
//        HtmlOutputLink link = new HtmlOutputLink();
//        link.setValue("oficinas.jsf");
//        
//        OutputLabel label = new OutputLabel();
//        label.setValue(teste.getNome());
//        
//     
//        link.getChildren().add(label);
//        
//        LightBox box = new LightBox();
//        box.setIframe(true);
//        box.getChildren().add(link);
//
//        html.getChildren().add(0, box);
    }

    public List<Noticia> getNoticias() {
        return usuarios;
    }

    public void setNoticias(List<Noticia> usuarios) {
        this.usuarios = usuarios;
    }

    public void onRowSelect(SelectEvent event) {
        teste = ((Oficina) event.getObject());
        System.out.println(teste.toString() + " aqui");
    }

    public void onRowUnselect(UnselectEvent event) {
        teste = null;
    }

    public String getJsonList() {
        System.out.println(g.toJson(usuarios));
        return (usuarios == null) ? "" : g.toJson(usuarios);
    }

    public String getJsonOficinas() {
        System.out.println(g.toJson(oficinas) + "tagggggggggg");
        return (oficinas == null) ? "" : g.toJson(oficinas);
    }

    public String getJsonConfig() {
        System.out.println(g.toJson(config) + "tagggggggggg");
        return (config == null) ? "" : g.toJson(config);
    }

    public void sendEmail() {
        try {
            if (!emailOrigem.contains(" ")) {
                FormularioMensagemUC fm = new FormularioMensagemUC();
                fm.enviarEmail(assunto, corpo, nome, emailOrigem);
                saveMessage("Sucesso!", "E-mail enviado com sucesso!");
            } else {
                saveMessage("Erro ao enviar e-mail", "O endereço de e-mail não deve conter espaços!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            saveMessage("Erro ao enviar e-mail", "Por favor, tente novamente mais tarde.");
        }
        limpar();
    }

    public List<Noticia> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Noticia> usuarios) {
        this.usuarios = usuarios;
    }

    public GerenciarNoticiasUC getGerenciador() {
        return gerenciador;
    }

    public void setGerenciador(GerenciarNoticiasUC gerenciador) {
        this.gerenciador = gerenciador;
    }

    public Gson getG() {
        return g;
    }

    public void setG(Gson g) {
        this.g = g;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getCorpo() {
        return corpo;
    }

    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmailOrigem() {
        return emailOrigem;
    }

    public void setEmailOrigem(String emailOrigem) {
        this.emailOrigem = emailOrigem;
    }

    public void limpar() {
        assunto = "";
        corpo = "";
        nome = "";
        emailOrigem = "";
    }

    public static void saveMessage(String title, String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(title, msg));
    }

    public List<Oficina> getOficinas() {
        return oficinas;
    }

    public void setOficinas(List<Oficina> oficinas) {
        this.oficinas = oficinas;
    }

    public Oficina getTeste() {
        return teste;
    }

    public void setTeste(Oficina teste) {
        this.teste = teste;
    }

    public void check() {
        if (teste == null) {
            System.out.println("nulo");
        } else {
            System.out.println("not nulo");
        }
    }

}
