package lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import bean.CfgSystemConfig;
import ssh.dao.ICfgSystemConfigDao;

public class LambdaTest {

	public static void main(String[] args) {

		//Single Function
		Runnable runnbale = () -> System.out.println("run me!");
		runnbale.run();
		
		//Collection
		ApplicationContext context = new FileSystemXmlApplicationContext("src/main/resources/applicationContext.xml");
		ICfgSystemConfigDao dao = (ICfgSystemConfigDao) context.getBean("cfgSystemConfigDaoImpl");
		List<CfgSystemConfig> cscList = dao.getAllData();
		cscList.forEach(s -> System.out.print(s.getId() + ", "));
		System.out.println();
		
		//Collectors.joining
		List<String> productList = new ArrayList<String>();
		productList.add("A");
		productList.add("B");
		productList.add("C");
		
		String result = productList.stream()
				.map(id -> id)
				.collect(Collectors.joining(",","[","]"));
		System.out.println(result);
	}

}
