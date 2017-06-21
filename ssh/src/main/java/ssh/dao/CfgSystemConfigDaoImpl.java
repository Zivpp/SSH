package ssh.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import bean.CfgSystemConfig;

public class CfgSystemConfigDaoImpl implements ICfgSystemConfigDao{
	
	private SessionFactory sessionFactory; 
    
    public CfgSystemConfigDaoImpl() {
    }
    
    public CfgSystemConfigDaoImpl(SessionFactory sessionFactory) { 
        this.setSessionFactory(sessionFactory);
    }
    
    public void setSessionFactory(SessionFactory sessionFactory) { 
        this.sessionFactory = sessionFactory; 
    } 
    
    public void insert(CfgSystemConfig csc) {
        // 取得Session
        Session session = sessionFactory.openSession();
        // 開啟交易
        Transaction tx= session.beginTransaction();
        // 直接儲存物件
        session.save(csc); 
        // 送出交易
        tx.commit();
        session.close(); 
    }

    public CfgSystemConfig find(Integer id) {
    	
        Session session = sessionFactory.openSession(); 
        
        CfgSystemConfig user = (CfgSystemConfig) session.get(CfgSystemConfig.class, id);
        
        session.close();
        
        return user;
    }

}
