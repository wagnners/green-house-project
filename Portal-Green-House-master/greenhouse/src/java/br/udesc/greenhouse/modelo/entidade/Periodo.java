/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.greenhouse.modelo.entidade;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author ignoi
 */
@Entity
@Table(name = "periodos")
public class Periodo implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column
    private long periodoid;

    @Column(nullable = false)
    private String diaDaSemana;

    @Column(nullable = false)
    @Temporal(javax.persistence.TemporalType.TIME)
    private Date horaInicio;

    @Column(nullable = false)
    @Temporal(javax.persistence.TemporalType.TIME)
    private Date horaFim;

    @ManyToMany(mappedBy = "periodos", cascade = CascadeType.MERGE)
    private List<Usuario> usuarios;

    @ManyToMany(mappedBy = "periodos", cascade = CascadeType.MERGE)
    private List<Oficina> oficinas;

    public Periodo() {
        usuarios = new ArrayList<>();
        oficinas = new ArrayList<>();
    }

    public long getPeriodoid() {
        return periodoid;
    }

    public void setPeriodoid(long periodoid) {
        this.periodoid = periodoid;
    }

    public String getDiaDaSemana() {
        return diaDaSemana;
    }

    public void setDiaDaSemana(String diaDaSemana) {
        this.diaDaSemana = diaDaSemana;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(Date horaFim) {
        this.horaFim = horaFim;
    }
    
    public void addUsuario(Usuario u){
        usuarios.add(u);
    }
    
    public void addOficina(Oficina o){
        oficinas.add(o);
    }

    @Override
    public String toString() {
        return diaDaSemana + " - " + extrairHora(horaInicio) + " às " + extrairHora(horaFim);
    }

    private String extrairHora(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(date);
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Oficina> getOficinas() {
        return oficinas;
    }

    public void setOficinas(List<Oficina> oficinas) {
        this.oficinas = oficinas;
    }
}
