package ssh.dao;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import bean.User;
import ssh.util.HibernateUtil;
import systemConfig.SysCfgCode;

@Component("LoginDaoImpl")
public class LoginDaoImpl implements ILoginDao{

	public String login(String account, String password) throws Exception {
	
	try{
//		Session session = HibernateUtil.getSessionFactory().openSession();
//	    Transaction tx = session.beginTransaction();
//	
//	    User user = new User();
//	    user.setUserId(3);
//	    user.setUserName("Ziv");
//	    user.setCreateBy("Ziv");
//	    user.setCreateDate(new Date());
//	
//	    session.save(user);
//	    tx.commit();
//	
//	    session.close();
//	    HibernateUtil.shutdown();
	}catch(Exception e){
		throw new Exception(e);
	}	
		
		return SysCfgCode.SUCCESS;
	}
	
}
