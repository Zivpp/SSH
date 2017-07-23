package ssh.dao.baseDao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class DaoBaseMariadb {

	@Autowired
	@Qualifier("sessionFactory_MariaDB")
	protected SessionFactory sessionFactory;

	public Session getSessionFactory() {
		return sessionFactory.openSession();
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	} 
	
	
}
