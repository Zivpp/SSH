package ssh.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import bean.CfgSystemConfig;
import ssh.dao.baseDao.DaoBaseMariadb;
import ssh.util.CacheUtil;

@Component("cfgSystemConfigDaoImpl")
public class CfgSystemConfigDaoImpl extends DaoBaseMariadb implements ICfgSystemConfigDao{ 
	
    public void insert(CfgSystemConfig csc) throws Exception {
    	try{
    		
            // 取得Session
        	Session session = getSessionFactory();
            // 開啟交易
            Transaction tx= session.beginTransaction();
            // 直接儲存物件
            session.save(csc); 
            // 送出交易
            tx.commit();
            session.close(); 
    		
    	}catch(Exception e){
    		System.out.println(e.getMessage());
    		throw new Exception(e);
    	}
    }

	@Override
	public CfgSystemConfig queryById(int id) {
		
		
		CfgSystemConfig csc= (CfgSystemConfig)getSessionFactory().get(CfgSystemConfig.class, id);
		
		getSessionFactory().close(); 
		
		return csc;
	}

	@Override
	public CfgSystemConfig update(CfgSystemConfig csc) {
		
		Session session = getSessionFactory();
		
		Transaction tx = session.beginTransaction();
		session.update(csc);
		tx.commit();
		session.close(); 
		
		session = getSessionFactory();
		CfgSystemConfig re= (CfgSystemConfig)session.get(CfgSystemConfig.class, csc.getId());
		session.close(); 
		
		return re;
	}

	@Override
	public void deleteById(int id) {
		
		CfgSystemConfig target = queryById(id);
		
		if(target != null){
			Session session = getSessionFactory();
			Transaction tx = session.beginTransaction();
			session.delete(target);
			tx.commit();
			session.close(); 
		}
		
	}

	@Override
	public List<CfgSystemConfig> searchById(int id) {
		
		Session session = getSessionFactory();
		
		String sql = "Select id from cfg_system_config where id = :id";
		
		Query query = session.createSQLQuery(sql)
			.setParameter("id", id)
			.setResultTransformer(Transformers.aliasToBean(CfgSystemConfig.class));
		
		List tempList = query.list();
		List<CfgSystemConfig> result = new ArrayList<CfgSystemConfig>();
		
		for(Object obj : tempList){
			//CfgSystemConfig tempCsc = (CfgSystemConfig)obj;
			//result.add(tempCsc);
		}
		
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CfgSystemConfig> getAllData() {
		
		try{
			List<CfgSystemConfig> result = 
					getSessionFactory().createCriteria(CfgSystemConfig.class).addOrder(Order.asc("id")).addOrder(Order.asc("codeCate")).addOrder(Order.asc("seq")).list();
			return result;
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		return null;
	}
}
