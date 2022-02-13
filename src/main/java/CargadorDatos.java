import entities.*;
import org.hibernate.Session;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;

public class CargadorDatos {

    Session session;

    CargadorDatos(Session session){
        this.session = session;
    }

    public void cargarDatos() throws ParseException {

        saveDataSede("sede1", "10000000");
        saveDataSede("sede2", "20000000");
    }

    private void saveDataSede(String nombreSede, String prefixDNI) throws ParseException {
        Sede sede1 = new Sede(nombreSede);
        Proyecto proyecto1 = new Proyecto(toDate("2020-01-11"), toDate("2020-02-12"), "proyecto1"+nombreSede, Collections.singleton(sede1));
        Proyecto proyecto2 = new Proyecto(toDate("2020-01-12"), toDate("2020-03-12"), "proyecto2"+nombreSede, Collections.singleton(sede1));
        Proyecto proyecto3 = new Proyecto(toDate("2020-01-13"), toDate("2020-04-12"), "proyecto3"+nombreSede, Collections.singleton(sede1));

        sede1.setProyectos(new HashSet<>(Arrays.asList(proyecto1, proyecto2, proyecto3)));

        Departamento departamento1 = new Departamento("departamento1"+nombreSede, sede1);
        Departamento departamento2 = new Departamento("departamento2"+nombreSede, sede1);
        Departamento departamento3 = new Departamento("departamento3"+nombreSede, sede1);

        Empleado empleado1 = new Empleado(prefixDNI +"B", "empleado1"+nombreSede);
        Empleado empleado2 = new Empleado(prefixDNI +"C", "empleado2"+nombreSede);
        Empleado empleado3 = new Empleado(prefixDNI +"D", "empleado3"+nombreSede);
        Empleado empleado4 = new Empleado(prefixDNI +"E", "empleado4"+nombreSede);
        empleado1.setDepartamento(departamento1);
        empleado2.setDepartamento(departamento1);
        empleado3.setDepartamento(departamento1);
        empleado4.setDepartamento(departamento1);

        Empleado empleado5 = new Empleado(prefixDNI +"F", "empleado5"+nombreSede);
        Empleado empleado6 = new Empleado(prefixDNI +"G", "empleado6"+nombreSede);
        Empleado empleado7 = new Empleado(prefixDNI +"H", "empleado7"+nombreSede);
        Empleado empleado8 = new Empleado(prefixDNI +"I", "empleado8"+nombreSede);
        empleado5.setDepartamento(departamento2);
        empleado6.setDepartamento(departamento2);
        empleado7.setDepartamento(departamento2);
        empleado8.setDepartamento(departamento2);

        Empleado empleado9 = new Empleado(prefixDNI +"J", "empleado9"+nombreSede);
        Empleado empleado10 = new Empleado(prefixDNI +"K", "empleado10"+nombreSede);
        Empleado empleado11 = new Empleado(prefixDNI +"L", "empleado11"+nombreSede);
        Empleado empleado12 = new Empleado(prefixDNI +"M", "empleado12"+nombreSede);
        empleado9.setDepartamento(departamento3);
        empleado10.setDepartamento(departamento3);
        empleado11.setDepartamento(departamento3);
        empleado12.setDepartamento(departamento3);

        DatosEmpleado datosEmpleado1 = new DatosEmpleado("A", 10000, empleado1);
        DatosEmpleado datosEmpleado2 = new DatosEmpleado("B", 15000, empleado2);
        DatosEmpleado datosEmpleado3 = new DatosEmpleado("C", 20000, empleado3);
        DatosEmpleado datosEmpleado4 = new DatosEmpleado("D", 25000, empleado4);
        DatosEmpleado datosEmpleado5 = new DatosEmpleado("E", 30000, empleado5);
        DatosEmpleado datosEmpleado6 = new DatosEmpleado("F", 35000, empleado6);
        DatosEmpleado datosEmpleado7 = new DatosEmpleado("G", 40000, empleado7);
        DatosEmpleado datosEmpleado8 = new DatosEmpleado("H", 45000, empleado8);
        DatosEmpleado datosEmpleado9 = new DatosEmpleado("I", 50000, empleado9);
        DatosEmpleado datosEmpleado10 = new DatosEmpleado("J", 55000, empleado10);
        DatosEmpleado datosEmpleado11 = new DatosEmpleado("K", 60000, empleado11);
        DatosEmpleado datosEmpleado12 = new DatosEmpleado("L", 65000, empleado12);


        empleado1.setDatosEmpleado(datosEmpleado1);
        empleado2.setDatosEmpleado(datosEmpleado2);
        empleado3.setDatosEmpleado(datosEmpleado3);
        empleado4.setDatosEmpleado(datosEmpleado4);
        empleado5.setDatosEmpleado(datosEmpleado5);
        empleado6.setDatosEmpleado(datosEmpleado6);
        empleado7.setDatosEmpleado(datosEmpleado7);
        empleado8.setDatosEmpleado(datosEmpleado8);
        empleado9.setDatosEmpleado(datosEmpleado9);
        empleado10.setDatosEmpleado(datosEmpleado10);
        empleado11.setDatosEmpleado(datosEmpleado11);
        empleado12.setDatosEmpleado(datosEmpleado12);

        departamento1.setEmpleados(new HashSet<>(Arrays.asList(empleado1, empleado2, empleado3, empleado4)));
        departamento2.setEmpleados(new HashSet<>(Arrays.asList(empleado5, empleado6, empleado7, empleado8)));
        departamento3.setEmpleados(new HashSet<>(Arrays.asList(empleado9, empleado10, empleado11, empleado12)));

        sede1.setDepartamentos(new HashSet<>(Arrays.asList(departamento1, departamento2, departamento3)));

        session.save(sede1);
        session.getTransaction().commit();
    }

    public Date toDate(String s) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.parse(s);

    }
}