package ssh.dao.baseDao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import ssh.apconfig.SqlProperties;

public class DaoBaseMariadb {
	
    @Autowired
    @Qualifier("sqlProperties")
    private SqlProperties sqlProperties;

	@Autowired
	@Qualifier("sessionFactory_MariaDB")
	protected SessionFactory sessionFactory;

	public Session getSessionFactory() {
		return sessionFactory.openSession();
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	} 
	
	/**
	 * ¨ú±oSQL»y¥y
	 * from sqlProperties
	 * @param key
	 * @return
	 */
    public String getSqlStatment(String key) {
        return this.sqlProperties.getProperty(key);
    }
}
