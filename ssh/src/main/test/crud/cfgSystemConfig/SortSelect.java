package crud.cfgSystemConfig;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import bean.CfgSystemConfig;
import ssh.dao.ICfgSystemConfigDao;

public class SortSelect {

public static void main(String[] args) {
		
		try{
			ApplicationContext context = new FileSystemXmlApplicationContext("src/main/resources/applicationContext.xml");
			ICfgSystemConfigDao dao = (ICfgSystemConfigDao) context.getBean("cfgSystemConfigDaoImpl");
			List<CfgSystemConfig> csc = dao.searchCfgSysDataBySort("id","DESC");
			System.out.println(csc.get(0).getId());
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
	}
	
}
