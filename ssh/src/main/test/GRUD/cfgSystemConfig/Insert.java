package GRUD.cfgSystemConfig;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import bean.CfgSystemConfig;
import bean.User;
import ssh.util.HibernateUtil;

public class Insert {

	public static void main(String[] args) {
	
		try{
			
			Session session = HibernateUtil.getSessionFactory().openSession();
		    Transaction tx = session.beginTransaction();
		
		    CfgSystemConfig csc = new CfgSystemConfig();
		    
		    csc.setId(1l);
		    csc.setCodeCate("Test");
		    csc.setCateName("ด๚ธี");
		    csc.setCode("Test01");
		    csc.setCodeName("ด๚ธี01");
		    csc.setCodeValue("1");
		    csc.setCodeDesc("Test 01 go");
		    csc.setSeq(1l);
		    csc.setCreateDate(new Date());
		    csc.setCreateUser("Ziv");
		    csc.setUpdateDate(new Date());
		    csc.setUpdateUser("Ziv");
		    
		    session.save(csc);
		    
		    tx.commit();
		    session.close();
		    HibernateUtil.shutdown();
		    
		}catch(Exception e){
			System.out.println(e);
		}

    }

}
