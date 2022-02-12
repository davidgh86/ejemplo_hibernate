package entities;

import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="departamento")
public class Departamento {

    @Id
    @Column(name="id_dpto", columnDefinition="INT(11)")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "departamento_sequence")
    @GenericGenerator(name = "departamento_sequence", strategy = "native")
	private int idDpto;

    @Column(name="nom_dpto", columnDefinition="CHAR(32)")
	private String nomDpto;

   	@ManyToOne
	@JoinColumn(name = "id_sede")
	private Sede sede;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="departamento")
    private Set<Empleado> empleados = new HashSet<>();

	public Departamento() {
	}

	public Departamento(String nomDpto, Sede sede) {
		this.nomDpto = nomDpto;
		this.sede = sede;
	}

    public int getIdDpto() {
        return idDpto;
    }

    public void setIdDpto(int idDpto) {
        this.idDpto = idDpto;
    }

    public String getNomDpto() {
        return nomDpto;
    }

    public void setNomDpto(String nomDpto) {
        this.nomDpto = nomDpto;
    }

    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }

    public Set<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(Set<Empleado> empleados) {
        this.empleados = empleados;
    }
//
//	public int getIdDpto() {
//		return idDpto;
//	}
//
//	public void setIdDpto(int idDpto) {
//		this.idDpto = idDpto;
//	}
//
//	public String getNomDpto() {
//		return nomDpto;
//	}
//
//	public void setNomDpto(String nomDpto) {
//		this.nomDpto = nomDpto;
//	}
//
//	public Sede getSede() {
//		return sede;
//	}
//
//	public void setSede(Sede sede) {
//		this.sede = sede;
//	}
//
////	public Set<Empleado> getEmpleados() {
////		return empleados;
////	}
////
////	public void setEmpleados(Set<Empleado> empleados) {
////		this.empleados = empleados;
////	}
////	public void AddEmpleado(Empleado e) {
////		this.empleados.add(e);
////	}


    @Override
    public String toString() {
        return "Departamento{" +
                "idDpto=" + idDpto +
                ", nomDpto='" + nomDpto + '\'' +
                ", sede=" + sede +
                ", empleados=" + empleados +
                '}';
    }
}
