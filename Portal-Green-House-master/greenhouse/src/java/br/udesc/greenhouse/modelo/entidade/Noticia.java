/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.greenhouse.modelo.entidade;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author ignoi
 */
@Entity
@Table(name = "noticias")
public class Noticia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column
    private long noticiaid;

    @Column
    private String foto;

    @Column(nullable = false, length = 1000)
    private String titulo;

    @Column
    private boolean fixada;

    @Column(nullable = false, length = 10000)
    private String descricao;

    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date data;

    public Noticia() {
        data = Calendar.getInstance().getTime();
    }

    public Noticia(long noticiaid, String foto, String titulo, boolean fixada, String descricao, Date data) {
        this.noticiaid = noticiaid;
        this.foto = foto;
        this.titulo = titulo;
        this.fixada = fixada;
        this.descricao = descricao;
        this.data = data;
    }

    public long getNoticiaid() {
        return noticiaid;
    }

    public void setNoticiaid(long noticiaid) {
        this.noticiaid = noticiaid;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public boolean isFixada() {
        return fixada;
    }

    public void setFixada(boolean fixada) {
        this.fixada = fixada;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    private String extrairHora(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(date);
    }
    
    private String extrairData(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }
    
    public String getD(){
        return extrairData(data);
    }

}
