package CRUD.cfgSystemConfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import ssh.dao.ICfgSystemConfigDao;

public class Delete {
	
	public static void main(String[] args) {
		
		try{
			ApplicationContext context = new FileSystemXmlApplicationContext("src/main/resources/applicationContext.xml");
			ICfgSystemConfigDao userDAO = (ICfgSystemConfigDao) context.getBean("cfgSystemConfigDaoImpl");
			userDAO.deleteById(4);
			System.out.println("Delete Success");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

}
