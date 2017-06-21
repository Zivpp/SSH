package GRUD.cfgSystemConfig;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import bean.CfgSystemConfig;
import ssh.dao.ICfgSystemConfigDao;

public class Insert {
		
	public static void main(String[] args) {
	
		try{
			
//			Session session = HibernateUtil.getSessionFactory().openSession();
//		    Transaction tx = session.beginTransaction();
		
			
			ApplicationContext context = new FileSystemXmlApplicationContext("src/main/resources/applicationContext.xml");
		        
		    // 建立DAO物件
			ICfgSystemConfigDao userDAO = (ICfgSystemConfigDao) context.getBean("cfgSystemConfigDaoImpl");
			
		    CfgSystemConfig csc = new CfgSystemConfig();
		    csc.setId(3);
		    csc.setCodeCate("Test");
		    csc.setCateName("測試");
		    csc.setCode("Test03");
		    csc.setCodeName("測試2");
		    csc.setCodeValue("2");
		    csc.setCodeDesc("Test 02 go");
		    csc.setSeq(2);
		    csc.setParentId(1);
		    csc.setCreateDate(new Date());
		    csc.setCreateUser("Ziv");
		    csc.setUpdateDate(new Date());
		    csc.setUpdateUser("Ziv");
		    

		    userDAO.insert(csc);
		    
//		    session.save(csc);
//		    
//		    tx.commit();
//		    session.close();
//		    HibernateUtil.shutdown();
		    
		}catch(Exception e){
			System.out.println(e);
		}

    }

}
