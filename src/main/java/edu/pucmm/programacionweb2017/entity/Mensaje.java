package edu.pucmm.programacionweb2017.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by gusta on 18-Jun-17.
 */
@Entity
@Table(name = "mensaje")
@Access(AccessType.FIELD)
public class Mensaje {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "emisor")
    private Usuario emisor;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "receptor")
    private Usuario receptor;
    @Column(name = "mensaje")
    private String mensaje;
    @Column(name = "fecha")
    private Date fecha;

    public Mensaje() {
    }

    public Mensaje(Usuario emisor, Usuario receptor, String mensaje, Date fecha) {
        this.emisor = emisor;
        this.receptor = receptor;
        this.mensaje = mensaje;
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getEmisor() {
        return emisor;
    }

    public void setEmisor(Usuario emisor) {
        this.emisor = emisor;
    }

    public Usuario getReceptor() {
        return receptor;
    }

    public void setReceptor(Usuario receptor) {
        this.receptor = receptor;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
