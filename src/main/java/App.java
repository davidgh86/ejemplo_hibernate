import org.hibernate.Session;

import java.text.ParseException;

public class App {
    public static void main(String[] args) throws ParseException {

        Session session = HibernateUtil.buildSession();

        CargadorDatos cargadorDatos = new CargadorDatos(session);
        cargadorDatos.cargarDatos();

        Consultas consulta = new Consultas(session);
        consulta.executeConsultas();

        Modificaciones modificaciones = new Modificaciones(session);
        modificaciones.executeModifications();

        session.getTransaction().commit();
        session.close();

    }
}
