package lambda;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import bean.CfgSystemConfig;
import ssh.dao.ICfgSystemConfigDao;
import ssh.util.CacheUtil;

public class SortedTest {

	public static void main(String[] args) {
		
		ApplicationContext context = new FileSystemXmlApplicationContext("src/main/resources/applicationContext.xml");
		ICfgSystemConfigDao dao = (ICfgSystemConfigDao) context.getBean("cfgSystemConfigDaoImpl");
		//List<CfgSystemConfig> cscList = dao.getAllData();
		List<CfgSystemConfig> cscList = CacheUtil.getSysCfgDatas();
		
		//asc
		cscList.sort((c1,c2) -> c1.getId().compareTo(c2.getId()));
		cscList.forEach(c -> System.out.print(c.getId() + ","));
		System.out.println();
		//desc
		cscList = CacheUtil.getSysCfgDatas();
		cscList.sort((c1,c2) -> c2.getId().compareTo(c1.getId()));
		cscList.forEach(c -> System.out.print(c.getId() + ","));
		System.out.println();
		
	}

}
