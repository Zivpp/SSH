package lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import bean.CfgSystemConfig;
import ssh.dao.ICfgSystemConfigDao;

public class LambdaTest {

	public static void main(String[] args) {

		//Single Function
		Runnable runnbale = () -> System.out.println("run me!");
		runnbale.run();
		Thread t = new Thread(() -> System.out.println("run me 2!"));
		t.start();
		
		//Stream
		IntStream.range(0, 10).forEach(i -> System.out.print(i));
		System.out.println();
		IntStream.range(0, 10).forEach(System.out::print);
		System.out.println();
		
		//Collection
//		ApplicationContext context = new FileSystemXmlApplicationContext("src/main/resources/applicationContext.xml");
//		ICfgSystemConfigDao dao = (ICfgSystemConfigDao) context.getBean("cfgSystemConfigDaoImpl");
//		List<CfgSystemConfig> cscList = dao.getAllData();
//		cscList.forEach(s -> System.out.print(s.getId() + ", "));
//		System.out.println();
		
		//Set and Collection.toSet
		Set<String> ss = Stream.of("A","A","A","A","B").collect(Collectors.toSet());
		System.out.println(ss.toString());
		
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
