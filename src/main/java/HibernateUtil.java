import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

	private static HibernateUtil instance;

	private final Session session;

	private HibernateUtil(){
		this.session = buildSession();
	}

	private static Session buildSession() {
		StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure().build();
		Metadata metadata = new MetadataSources(standardRegistry).buildMetadata();
		SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		return session;
	}

	public static HibernateUtil startSession(){
		if(instance == null) {
			instance = new HibernateUtil();
		}
		return instance;
	}

	public static Session getSession(){
		if (instance == null) {
			startSession();
		}
		return instance.session;
	}

	public static void commit(){
		if (instance != null) {
			instance.session.getTransaction().commit();
		}else {
			throw new IllegalArgumentException("Session not started");
		}
	}

	public static void closeSession(){
		if (instance != null) {
			instance.session.close();
		}else {
			throw new IllegalArgumentException("Session not started");
		}
	}
}
