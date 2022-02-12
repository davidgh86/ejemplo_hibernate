package entities;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "empleados_datos_prof")
public class DatosEmpleado {

    @Id
    @Column(columnDefinition="CHAR(9)")
	private String dni;

	@Column(name = "categoria", columnDefinition="CHAR(2)")
	private String categoria;

	@Column(name="sueldo_bruto_anual", columnDefinition="Decimal(8,2)")
	private float sueldoBrutoAnual;

	@NotNull
	@OneToOne(mappedBy = "datosEmpleado")
	private Empleado empleado;

	public DatosEmpleado(){}

	public DatosEmpleado(String categoria, int sueldoBrutoAnual, Empleado empleado) {
		this.categoria = categoria;
		this.sueldoBrutoAnual = sueldoBrutoAnual;
		this.setEmpleado(empleado);
	}

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public float getSueldoBrutoAnual() {
        return sueldoBrutoAnual;
    }

    public void setSueldoBrutoAnual(float sueldoBrutoAnual) {
        this.sueldoBrutoAnual = sueldoBrutoAnual;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
        this.dni = empleado.getDni();
    }

    @Override
    public String toString() {
        return "DatosEmpleado{" +
                "dni='" + getDni() + '\'' +
                ", categoria='" + getCategoria() + '\'' +
                ", sueldoBrutoAnual=" + getSueldoBrutoAnual() +
                '}';
    }
}
