import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Scanner;

public class Modificaciones {

	Session session;

	Modificaciones(Session session) {
		this.session = session;
	}

	public void executeModifications(){

		String dni = "10000000C";
		int aumento = 10000;
		int empleadosConSubidaDeSueldo = aumentarSueldo(session, dni, aumento);
		System.out.println("Empleados con subida de sueldo :" + empleadosConSubidaDeSueldo);
		
		String proyectoAntiguo = "proyecto1sede1";
		String nuevoProyecto = "proyectoNuevo";
		empleadosConSubidaDeSueldo = renombrarProyecto(session, proyectoAntiguo, nuevoProyecto);
		System.out.println("Filas afectadas :" + empleadosConSubidaDeSueldo);

		int idDepartamento = 1;
		eliminarDepartamento(session, idDepartamento);

	}

	private void eliminarDepartamento(Session session, int idDepartamento) {
		// Eliminar empleados del departamento para no violar claves foraneas
		int empleadosEliminados = deleteEmpleado(session, idDepartamento);
		System.out.println("Empleados eliminados : " + empleadosEliminados);

		int departamentosEliminados = eliminarDepartamentos(session, idDepartamento);
		System.out.println("Departamentos eliminados : " + departamentosEliminados);
	}

	private int eliminarDepartamentos(Session session, int idDepartamento) {
		String deleteDepartamentoStringQuery = "delete from Departamento where idDepartamento = :id";
		Query<?> deleteDepartamentoQuery = session.createQuery(deleteDepartamentoStringQuery);
		deleteDepartamentoQuery.setParameter("id", idDepartamento);
		return deleteDepartamentoQuery.executeUpdate();
	}

	private int deleteEmpleado(Session session, int idDepartamento) {
		String deleteStringQuery = "delete from Empleado where departamento.idDepartamento = :id";
		Query<?> deleteQuery = session.createQuery(deleteStringQuery);
		deleteQuery.setParameter("id", idDepartamento);
		return deleteQuery.executeUpdate();
	}

	private int renombrarProyecto(Session session, String proyectoAntiguo, String nuevoProyecto) {
		int empleadosConSubidaDeSueldo;
		String queryStringUpdateNombreProyecto = "update Proyecto set nombreProyecto =  :nuevo where nombreProyecto = :antiguo";
		Query<?> queryNuevoProyecto = session.createQuery(queryStringUpdateNombreProyecto);
		queryNuevoProyecto.setParameter("nuevo", nuevoProyecto);
		queryNuevoProyecto.setParameter("antiguo", proyectoAntiguo);
		empleadosConSubidaDeSueldo = queryNuevoProyecto.executeUpdate();
		return empleadosConSubidaDeSueldo;
	}

	private int aumentarSueldo(Session session, String dni, int aumento) {
		String queryMayorSueldo = "select max(ed.sueldoBrutoAnual) from DatosEmpleado ed inner join ed.empleado e";
		Float sueldoMayor = session.createQuery(queryMayorSueldo, Float.class).getSingleResult();

		String queryStringAumentoDeSueldo = "update DatosEmpleado set sueldoBrutoAnual = :nuevosueldo where dni = :dniEmpleado ";
		Query<?> queryAumentoDeSueldo = session.createQuery(queryStringAumentoDeSueldo);
		queryAumentoDeSueldo.setParameter("nuevosueldo", sueldoMayor+aumento);
		queryAumentoDeSueldo.setParameter("dniEmpleado", dni);

		return queryAumentoDeSueldo.executeUpdate();
	}

}
