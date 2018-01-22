/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.greenhouse.modelo.entidade;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author ignoi
 */
@Entity
@Table(name = "oficinas")
public class Oficina implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "oficinaid")
    private long oficinaid;

    @Column(nullable = false)
    private long capacidade;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false, length = 1000)
    private String descricao;

    @Column(length = 1000000000)
    private byte[] fotoDestaque;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_categoria")
    private List<Imagem> fotos;

    @Column
    private String video;
    
    @Column
    private String evento;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "oficina_periodo", joinColumns = @JoinColumn(name = "oficinaid"), inverseJoinColumns = @JoinColumn(name = "periodoid"))
    private List<Periodo> periodos;

    @ManyToMany(mappedBy = "oficinas", cascade = CascadeType.MERGE)
    private List<Usuario> usuarios;

    public Oficina() {
        fotos = new ArrayList<>();
        periodos = new ArrayList<>();
        usuarios = new ArrayList<>();

    }

    public long getOficinaid() {
        return oficinaid;
    }

    public void setOficinaid(long oficinaid) {
        this.oficinaid = oficinaid;
    }

    public long getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(long capacidade) {
        this.capacidade = capacidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public byte[] getFotoDestaque() {
        return fotoDestaque;
    }

    public void setFotoDestaque(byte[] fotoDestaque) {
        this.fotoDestaque = fotoDestaque;
    }

    public List<Imagem> getFotos() {
        return fotos;
    }

    public void setFotos(List<Imagem> fotos) {
        this.fotos = fotos;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String videos) {
        this.video = videos;
    }

    public void addFoto(Imagem url) {
        fotos.add(url);
    }

    public List<Periodo> getPeriodos() {
        return periodos;
    }

    public void setPeriodos(List<Periodo> periodos) {
        this.periodos = periodos;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public String toString() {
        return nome;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }
    
    

}
