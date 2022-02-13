package entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="proyecto")
public class Proyecto {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "proyecto_squence")
    @GenericGenerator(name = "proyecto_squence", strategy = "native")
    @Column(name="id_proy", columnDefinition="INT(11)")
	private int id;

    @Column(name="fechaInicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;

    @Column(name="f_fin")
	@Temporal(TemporalType.DATE)
	private Date fechaFin;

    @Column(name="nom_proy", columnDefinition="CHAR(20)")
    private String nombreProyecto;

    @ManyToMany(mappedBy = "proyectos")
	private Set<Sede> sedes;

	public Proyecto() {
	}

	public Proyecto(Date fechaInicio, Date fechaFin, String nombreProyecto, Set<Sede> sedes) {
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.nombreProyecto = nombreProyecto;
		this.sedes = sedes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getNombreProyecto() {
		return nombreProyecto;
	}

	public void setNombreProyecto(String nombreProyecto) {
		this.nombreProyecto = nombreProyecto;
	}

	public Set<Sede> getSedes() {
		return sedes;
	}

	public void setSedes(Set<Sede> sedes) {
		this.sedes = sedes;
	}

	@Override
	public String toString() {
		return "Proyecto{" +
				"id=" + getId() +
				", fechaInicio=" + getFechaInicio() +
				", fechaFin=" + getFechaFin() +
				", nombreProyecto='" + getNombreProyecto() +
				'}';
	}
}
