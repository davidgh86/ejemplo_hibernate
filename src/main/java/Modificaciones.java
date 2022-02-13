import org.hibernate.query.Query;

public class Modificaciones {

	public static void executeModifications(){

		String dni = "10000000C";
		int aumento = 10000;
		int empleadosConSubidaDeSueldo = aumentarSueldo(dni, aumento);
		System.out.println("Empleados con subida de sueldo :" + empleadosConSubidaDeSueldo);
		
		String proyectoAntiguo = "proyecto1sede1";
		String nuevoProyecto = "proyectoNuevo";
		empleadosConSubidaDeSueldo = renombrarProyecto(proyectoAntiguo, nuevoProyecto);
		System.out.println("Filas afectadas :" + empleadosConSubidaDeSueldo);

		int idDepartamento = 1;
		eliminarDepartamento(idDepartamento);

	}

	private static void eliminarDepartamento(int idDepartamento) {
		// Eliminar empleados del departamento para no violar claves foraneas
		int empleadosEliminados = deleteEmpleado(idDepartamento);
		System.out.println("Empleados eliminados : " + empleadosEliminados);

		int departamentosEliminados = eliminarDepartamentos(idDepartamento);
		System.out.println("Departamentos eliminados : " + departamentosEliminados);
	}

	private static int eliminarDepartamentos(int idDepartamento) {
		String deleteDepartamentoStringQuery = "delete from Departamento where idDepartamento = :id";
		Query<?> deleteDepartamentoQuery = HibernateUtil.getSession().createQuery(deleteDepartamentoStringQuery);
		deleteDepartamentoQuery.setParameter("id", idDepartamento);
		int result = deleteDepartamentoQuery.executeUpdate();

		HibernateUtil.commit();
		return result;
	}

	private static int deleteEmpleado(int idDepartamento) {
		String deleteStringQuery = "delete from Empleado where departamento.idDepartamento = :id";
		Query<?> deleteQuery = HibernateUtil.getSession().createQuery(deleteStringQuery);
		deleteQuery.setParameter("id", idDepartamento);
		int result = deleteQuery.executeUpdate();

		HibernateUtil.commit();

		return result;
	}

	private static int renombrarProyecto(String proyectoAntiguo, String nuevoProyecto) {
		int empleadosConSubidaDeSueldo;
		String queryStringUpdateNombreProyecto = "update Proyecto set nombreProyecto =  :nuevo where nombreProyecto = :antiguo";
		Query<?> queryNuevoProyecto = HibernateUtil.getSession().createQuery(queryStringUpdateNombreProyecto);
		queryNuevoProyecto.setParameter("nuevo", nuevoProyecto);
		queryNuevoProyecto.setParameter("antiguo", proyectoAntiguo);
		empleadosConSubidaDeSueldo = queryNuevoProyecto.executeUpdate();
		HibernateUtil.commit();
		return empleadosConSubidaDeSueldo;
	}

	private static int aumentarSueldo(String dni, int aumento) {
		String queryMayorSueldo = "select max(ed.sueldoBrutoAnual) from DatosEmpleado ed inner join ed.empleado e";
		Float sueldoMayor = HibernateUtil.getSession().createQuery(queryMayorSueldo, Float.class).getSingleResult();

		String queryStringAumentoDeSueldo = "update DatosEmpleado set sueldoBrutoAnual = :nuevosueldo where dni = :dniEmpleado ";
		Query<?> queryAumentoDeSueldo = HibernateUtil.getSession().createQuery(queryStringAumentoDeSueldo);
		queryAumentoDeSueldo.setParameter("nuevosueldo", sueldoMayor+aumento);
		queryAumentoDeSueldo.setParameter("dniEmpleado", dni);

		int result = queryAumentoDeSueldo.executeUpdate();

		HibernateUtil.commit();
		return result;
	}

}
