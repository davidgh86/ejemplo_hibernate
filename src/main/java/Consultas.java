import entities.*;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Consultas {

    public static void main(String[] args) throws ParseException {

        Session session = HibernateUtil.buildSession();

        List<Sede> sedes = session.createQuery("select s from Sede s", Sede.class).list();
        System.out.println("Lsitado de sedes: " + sedes);

        List<Departamento> departamentos = session.createQuery("select d from Departamento d", Departamento.class).list();
        System.out.println("Lsitado de departamentos: " + departamentos);

        List<Empleado> empleados = session.createQuery("select e from Empleado e", Empleado.class).list();
        System.out.println("Lsitado de empleados: " + empleados);

        List<Proyecto> proyectos = session.createQuery("select p from Proyecto p", Proyecto.class).list();
        System.out.println("Lsitado de proyectos: " + proyectos);

        String countQuery = "select  s,count(e.dni),(select count(*) from Proyecto proy) from Sede s inner join s.departamentos d inner join d.empleados e group by s.idSede";
        List<Object[]> lista = session.createQuery(countQuery, Object[].class).list();
        for (Object[] row: lista) {
            Sede sede = (Sede) row[0];
            Number numeroEmpleados = (Number) row[1];
            Number numeroProyectos = (Number) row[2];
            System.out.println("Numero de empleados por proyecto y sede : " + sede.getIdSede() + " " + sede.getNombreSede() + " nºempleados : "
                    + numeroEmpleados.intValue() + " nº proyectos : " + numeroProyectos.intValue());
        }

        String nombreDepartamento = "departamento1sede1";
        String countQueryDepartamentoByNombre = "select count(*) from Departamento d where nombreDepartamento = :nombre";
        Query<Long> numeroDepartamentos = session.createQuery(countQueryDepartamentoByNombre, Long.class);
        numeroDepartamentos.setParameter("nombre", nombreDepartamento);
        List<Long> numero = numeroDepartamentos.getResultList();
        System.out.println("número de departamentos con nombre " + nombreDepartamento + ": " + numero.get(0));

//        String querySueldo = "select e.nombreEmpleado from Empleado e where e.dni = (select de.dni from DatosEmpleado de where de.sueldoBrutoAnual = (select max(dee.sueldoBrutoAnual) from DatosEmpleado dee))";
//        // max(de.sueldoBrutoAnual) from DatosEmpleado de inner join de.empleado e
//        List<Object[]> mayor_sueldo = session.createQuery(querySueldo, Object[].class).list();
//        for (int i = 0; i < mayor_sueldo.size(); i++) {
//            Object[] objeto = mayor_sueldo.get(i);
//            String emp = (String) objeto[0];
//            Integer cant_sueldo = (Integer) objeto[1];
//            System.out.println("Empleado con mayor sueldo : " + emp + " " + cant_sueldo);
//        }

        // Empleado con mayor sueldo
//        String query_sueldo = "select e.nombreEmpleado,max(ed.sueldoBrutoAnual) from DatosEmpleado ed inner join ed.empleado e";
//        List<Object[]> mayor_sueldo = session.createQuery(query_sueldo, Object[].class).list();
//        for (int i = 0; i < mayor_sueldo.size(); i++) {
//            Object[] objeto = mayor_sueldo.get(i);
//            String emp = (String) objeto[0];
//            Integer cant_sueldo = (Integer) objeto[1];
//            System.out.println("Empleado con mayor sueldo : " + emp + " " + cant_sueldo);
//        }

        String dni = "10000000C";
        String queryNativaEmpleadoConDNI = "select * from empleado where dni = :dni";
        Query<Empleado> empleadoConDni = session.createNativeQuery(queryNativaEmpleadoConDNI, Empleado.class);
        empleadoConDni.setParameter("dni", dni);

        Empleado empleado = empleadoConDni.getSingleResult();
        System.out.println("Empleado con dni " + dni + ": " + empleado);

//        Query<Set> queryProyectosDeSede = session.createQuery("select s.proyectos from Sede s where idSede = :idSede", Set.class);
//        queryProyectosDeSede.setParameter("idSede", "sede1");
//        Set proyectosDeSede = queryProyectosDeSede.getSingleResult();
//        System.out.println("Proyectos de sede1: "+ proyectosDeSede);
//
//        session.close();

    }




}