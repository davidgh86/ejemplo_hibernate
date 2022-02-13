import java.text.ParseException;

public class App {
    public static void main(String[] args) throws ParseException {

        CargadorDatos.cargarDatos();

        Consultas.executeConsultas();

        Modificaciones.executeModifications();

        HibernateUtil.closeSession();

    }
}
