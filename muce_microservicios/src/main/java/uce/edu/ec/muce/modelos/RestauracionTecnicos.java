package uce.edu.ec.muce.modelos;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "RESTAURACIONTECNICOS", catalog = "", schema = "MUCE")
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestauracionTecnicos implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RESTAURACIONTEC_SEQ")
    @SequenceGenerator(sequenceName = "RESTAURACIONTEC_SEQ", allocationSize = 1, name = "RESTAURACIONTEC_SEQ")
    @Basic(optional = false)
    @Column(name = "rsttcn_id", nullable = false, precision = 0, scale = -127)
	private Long id;
	
	@JoinColumn(name = "rst_id", referencedColumnName = "rst_id")
    @ManyToOne(optional = false)
	private Restauracion restauracion;
	
	@JoinColumn(name = "rsttcn_tipo", referencedColumnName = "ctl_id")
    @ManyToOne(optional = false)
	private Catalogo tipo;
	
    @Size(min = 1, max = 150)
    @Column(name = "rsttcn_nombre", length = 150)
	private String nombre;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Restauracion getRestauracion() {
		return restauracion;
	}
	public void setRestauracion(Restauracion restauracion) {
		this.restauracion = restauracion;
	}
	public Catalogo getTipo() {
		return tipo;
	}
	public void setTipo(Catalogo tipo) {
		this.tipo = tipo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
