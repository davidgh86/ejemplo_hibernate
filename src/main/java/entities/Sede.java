package entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Table(name="sede")
@Entity
public class Sede {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "sede_sequence")
  @GenericGenerator(name = "sede_sequence", strategy = "native")
  @Column(name = "id_sede", columnDefinition="INT(11)")
  private Integer idSede;

  @Column(name = "nom_sede", columnDefinition="CHAR(20)", nullable=false)
  private String nombreSede;

  @Min(1)
  @NotNull
  @ManyToMany(cascade = { CascadeType.ALL })
  @JoinTable(
          name = "proyecto_sede",
          joinColumns = { @JoinColumn(name = "id_sede", columnDefinition="INT(11)") },
          inverseJoinColumns = { @JoinColumn(name = "id_proy", columnDefinition="INT(11)") }
  )
  private Set<Proyecto> proyectos = new HashSet<>();

  @OneToMany(cascade = CascadeType.ALL, mappedBy="sede")
  private Set<Departamento> departamentos = new HashSet<>();

  public Set<Departamento> getDepartamentos() {
    return departamentos;
  }

  public void setDepartamentos(Set<Departamento> departamentos) {
    this.departamentos = departamentos;
  }

  public Sede(){}

  public Sede(String nombreSede) {
    this.nombreSede = nombreSede;
  }

  public Integer getIdSede() {
    return idSede;
  }

  public void setIdSede(Integer idSede) {
    this.idSede = idSede;
  }

  public String getNombreSede() {
    return nombreSede;
  }

  public void setNombreSede(String nombreSede) {
    this.nombreSede = nombreSede;
  }

  public Set<Proyecto> getProyectos() {
    return proyectos;
  }

  public void setProyectos(Set<Proyecto> proyectos) {
    this.proyectos = proyectos;
  }

  @Override
  public String toString() {
    return "Sede{" +
            "idSede=" + getIdSede() +
            ", nombreSede='" + getNombreSede() +
            '}';
  }
}