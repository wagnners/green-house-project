/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.greenhouse.bean;

import br.udesc.greenhouse.modelo.entidade.Imagem;
import br.udesc.greenhouse.modelo.entidade.Oficina;
import br.udesc.greenhouse.uc.GerenciarOficinasUC;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.imageio.ImageIO;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author ignoi
 */
@ManagedBean
@ViewScoped
public class OficinaBean {

    private List<Oficina> oficinas;
    private List<Oficina> selecionados;
    private List<Oficina> filtrados;
    private Oficina selecionado;
    private Oficina novo;
    private GerenciarOficinasUC gerenciador;

    public void handleFileUpload(FileUploadEvent event) {
        if (selecionado == null) {
            novo.setFotoDestaque(event.getFile().getContents());
        } else {
            selecionado.setFotoDestaque(event.getFile().getContents());
        }

        notificar("Sucesso!", "Foto " + event.getFile().getFileName() + " adicionada!");
    }

    public void handleFilesUpload(FileUploadEvent event) {
        if (selecionado == null) {
            novo.addFoto(new Imagem(event.getFile().getContents()));
        } else {
            selecionado.addFoto(new Imagem(event.getFile().getContents()));
        }
        notificar("Sucesso!", "Foto " + event.getFile().getFileName() + " adicionada!");
    }

    @PostConstruct
    public void init() {
        gerenciador = new GerenciarOficinasUC();
        novo = new Oficina();

//        String relativeWebPath = "/images/res/cam.png";
//        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance()
//                .getExternalContext().getContext();
//        String absoluteDiskPath = servletContext.getRealPath(relativeWebPath);
//        System.out.println(absoluteDiskPath);
//        try {
//            novo.setFotoDestaque(extractBytes(absoluteDiskPath));
//        } catch (Exception e) {
//
//        }
        listar();
    }

    public void inserir(ActionEvent actionEvent) {
        //                https://www.youtube.com/v/KZnUr8lcqjo
        if (novo.getVideo().trim().isEmpty() || novo.getVideo().startsWith(" https://www.youtube.com/embed/")) {
            if (gerenciador.inserir(novo)) {
                novo = new Oficina();
                RequestContext.getCurrentInstance().execute("PF('ndlg1').hide();");
                notificar("Sucesso", "Notícia inserida com sucesso!");
            } else {
                notificar("Falha", "Erro ao inserir oficina!");
            }
        } else {
            if (novo.getVideo().startsWith("https://www.youtube.com/watch?")) {
                String link = novo.getVideo();
                String[] partes = link.split("=");
                novo.setVideo("https://www.youtube.com/embed/" + partes[1]);
                if (gerenciador.inserir(novo)) {
                    novo = new Oficina();
                    RequestContext.getCurrentInstance().execute("PF('ndlg1').hide();");
                    notificar("Sucesso", "Oficina inserida com sucesso!");
                } else {
                    notificar("Falha", "Erro ao inserir oficina!");
                }
            } else {
                notificar("Falha", "Formato de URL inválida!");
            }
        }

        listar();
    }

    public void editar(ActionEvent actionEvent) {

        if (selecionado != null) {
            if (selecionado.getVideo().trim().isEmpty() || selecionado.getVideo().startsWith("https://www.youtube.com/v/")) {
                if (gerenciador.editar(selecionado)) {
                    RequestContext.getCurrentInstance().execute("PF('ndlg2').hide();");
                    notificar("Sucesso", "Notícia editada com sucesso!");
                } else {
                    notificar("Falha", "Erro ao editar oficina!");
                }
            } else {
                if (selecionado.getVideo().startsWith("https://www.youtube.com/watch?")) {
                    String link = selecionado.getVideo();
                    String[] partes = link.split("=");
                    selecionado.setVideo("https://www.youtube.com/v/" + partes[1]);
                    if (gerenciador.editar(selecionado)) {
                        RequestContext.getCurrentInstance().execute("PF('ndlg2').hide();");
                        notificar("Sucesso", "Notícia editada com sucesso!");
                    } else {
                        notificar("Falha", "Erro ao editar oficina!");
                    }
                } else {
                    notificar("Falha", "Formato de URL inválida!s=");
                }
            }

            listar();
            selecionado = null;
        } else {
            notificar("Falha", "É necessário selecionar uma oficina para editar.");
        }
    }

    public void remover(ActionEvent actionEvent) {
        if (selecionado == null) {
            notificar("Falha", "É necessário selecionar uma oficina antes de excluir.");
        } else {
            if (gerenciador.remover(selecionado.getOficinaid())) {
                notificar("Sucesso", "Notícia removida com sucesso!");
            } else {
                notificar("Falha", "Erro ao remover oficina!");
            }
            listar();
        }
    }

    public void listar() {
        System.out.println("listei aqui");
        this.oficinas = gerenciador.listar();
    }

    public void onRowSelect(SelectEvent event) {
        selecionado = ((Oficina) event.getObject());
        System.out.println(selecionado.toString() + " aqui");
    }

    public void onRowUnselect(UnselectEvent event) {
        selecionado = null;
    }

    public List<Oficina> getOficinas() {
        return oficinas;
    }

    public void setOficinas(List<Oficina> oficinas) {
        this.oficinas = oficinas;
    }

    public Oficina getSelecionado() {
        return selecionado;
    }

    public void setSelecionado(Oficina selecionado) {
        this.selecionado = selecionado;
    }

    public void notificar(String title, String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(title, msg));
    }

    public List<Oficina> getSelecionados() {
        return selecionados;
    }

    public void setSelecionados(List<Oficina> selecionados) {
        this.selecionados = selecionados;
    }

    public Oficina getNovo() {
        return novo;
    }

    public void setNovo(Oficina novo) {
        this.novo = novo;
    }

    public List<Oficina> getFiltrados() {
        return filtrados;
    }

    public void setFiltrados(List<Oficina> filtrados) {
        this.filtrados = filtrados;
    }

    public byte[] extractBytes(String path) throws IOException {
        File img = new File(path);
        BufferedImage bufferedImage = ImageIO.read(img);

        WritableRaster raster = bufferedImage.getRaster();
        DataBufferByte data = (DataBufferByte) raster.getDataBuffer();
        return data.getData();
    }

}
