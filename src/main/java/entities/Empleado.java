package entities;
import javax.persistence.*;

@Entity
@Table(name="empleado")
public class Empleado {

	@Id
    @Column(columnDefinition="CHAR(9)")
	private String dni;

	@Column(name = "nom_empleado", columnDefinition="CHAR(40)")
	private String nombreEmpleado;

	@ManyToOne
	@JoinColumn(name = "id_dpto")
	private Departamento departamento;

	@OneToOne(cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private DatosEmpleado datosEmpleado;

	public Empleado() {
	}

	public Empleado(String dni, String nombreEmpleado) {
		this.dni = dni;
		this.nombreEmpleado = nombreEmpleado;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombreEmpleado() {
		return nombreEmpleado;
	}

	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public DatosEmpleado getDatosEmpleado() {
		return datosEmpleado;
	}

	public void setDatosEmpleado(DatosEmpleado datosEmpleado) {
		this.datosEmpleado = datosEmpleado;
	}

	@Override
	public String toString() {
		return "Empleado{" +
				"dni='" + dni + '\'' +
				", nombreEmpleado='" + nombreEmpleado + '\'' +
				", departamento=" + departamento +
				", datosEmpleado=" + datosEmpleado +
				'}';
	}
}
