/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uce.edu.ec.muce.modelos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "FOTOGRAFIA", catalog = "", schema = "MUCE")
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL )
public class Piezafotograficadetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PIEZAFOTOGRAFICADETALLE_SEQ")
    @SequenceGenerator(sequenceName = "Piezafotograficadetalle_seq", allocationSize = 1, name = "PIEZAFOTOGRAFICADETALLE_SEQ")
    
    

    @Column(name = "ftg_id", nullable = false, precision = 0, scale = -127)
    private Long detalleid;

    @Size(min = 1, max = 600)
    @Column(name = "ftg_titulo", nullable = false, length = 600)
    private String titulo;
    
    
    
    @Size(min = 1, max = 600)
    @Column(name = "ftg_tematica", nullable = false, length = 600)
    private String tematica;
    
    
    
    @Size(min = 1, max = 600)
    @Column(name = "ftg_tipologia", nullable = false, length = 600)
    private String tipologia;
    
    
    
    @Size(min = 1, max = 600)
    @Column(name = "ftg_procedimiento", nullable = false, length = 600)
    private String procedimiento;
    
    @Size(max = 600)
    @Column(name = "ftg_autor", length = 600)
    private String autor;
    
    
    @Size(min = 1, max = 10)
    @Column(name = "ftg_siglo_ano", nullable = false, length = 10)
    private String sigloano;
    
    
    @Size(min = 1, max = 600)
    @Column(name = "ftg_fondo", nullable = false, length = 600)
    private String fondo;
    
    
    @Size(min = 1, max = 10)
    @Column(name = "ftg_alto", nullable = false, length = 10)
    private String alto;
    
    
    @Size(min = 1, max = 10)
    @Column(name = "ftg_largo", nullable = false, length = 10)
    private String largo;
    
    
    @Size(min = 1, max = 600)
    @Column(name = "ftg_inscripciones", nullable = false, length = 600)
    private String inscripciones;
    
    
    @Size(min = 1, max = 600)
    @Column(name = "ftg_descripcion", nullable = false, length = 600)
    private String descripcion;
    
    
    @Size(min = 1, max = 600)
    @Column(name = "ftg_elementos_relacionado", nullable = false, length = 600)
    private String elementosrelacionado;
    
    @Size(max = 600)
    @Column(name = "aud_usuario_registro_id", length = 600)
    private String usuarioregistroid;
    
    @Column(name = "aud_fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecharegistro;

    @JoinColumn(name = "msb_id", referencedColumnName = "msb_id", nullable = false)
    @ManyToOne(cascade = {CascadeType.ALL})

    private Piezamuseable piezamuseableid;


    public Piezafotograficadetalle() {
    }

    public Piezafotograficadetalle(Long detalleid) {
        this.detalleid = detalleid;
    }

    public Piezafotograficadetalle(Long detalleid, String titulo, String tematica, String tipologia, String procedimiento, String sigloano, String fondo, String alto, String largo, String inscripciones, String descripcion, String elementosrelacionado) {
        this.detalleid = detalleid;
        this.titulo = titulo;
        this.tematica = tematica;
        this.tipologia = tipologia;
        this.procedimiento = procedimiento;
        this.sigloano = sigloano;
        this.fondo = fondo;
        this.alto = alto;
        this.largo = largo;
        this.inscripciones = inscripciones;
        this.descripcion = descripcion;
        this.elementosrelacionado = elementosrelacionado;
    }

    public Long getDetalleid() {
        return detalleid;
    }

    public void setDetalleid(Long detalleid) {
        this.detalleid = detalleid;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public String getProcedimiento() {
        return procedimiento;
    }

    public void setProcedimiento(String procedimiento) {
        this.procedimiento = procedimiento;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getSigloano() {
        return sigloano;
    }

    public void setSigloano(String sigloano) {
        this.sigloano = sigloano;
    }

    public String getFondo() {
        return fondo;
    }

    public void setFondo(String fondo) {
        this.fondo = fondo;
    }

    public String getAlto() {
        return alto;
    }

    public void setAlto(String alto) {
        this.alto = alto;
    }

    public String getLargo() {
        return largo;
    }

    public void setLargo(String largo) {
        this.largo = largo;
    }

    public String getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(String inscripciones) {
        this.inscripciones = inscripciones;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getElementosrelacionado() {
        return elementosrelacionado;
    }

    public void setElementosrelacionado(String elementosrelacionado) {
        this.elementosrelacionado = elementosrelacionado;
    }

    public String getUsuarioregistroid() {
        return usuarioregistroid;
    }

    public void setUsuarioregistroid(String usuarioregistroid) {
        this.usuarioregistroid = usuarioregistroid;
    }

    public Date getFecharegistro() {
        return fecharegistro;
    }

    public void setFecharegistro(Date fecharegistro) {
        this.fecharegistro = fecharegistro;
    }

    public Piezamuseable getPiezamuseableid() {
        return piezamuseableid;
    }

    public void setPiezamuseableid(Piezamuseable piezamuseableid) {
        this.piezamuseableid = piezamuseableid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalleid != null ? detalleid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Piezafotograficadetalle)) {
            return false;
        }
        Piezafotograficadetalle other = (Piezafotograficadetalle) object;
        if ((this.detalleid == null && other.detalleid != null) || (this.detalleid != null && !this.detalleid.equals(other.detalleid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uce.edu.ec.muce.modelos.Piezafotograficadetalle[ detalleid=" + detalleid + " ]";
    }
    
}
