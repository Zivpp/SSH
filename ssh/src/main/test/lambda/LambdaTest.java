package lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
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
		
		//Stream.forEach
		List<Integer> intL = new ArrayList<>();
		IntStream.range(0, 10).forEach(intL::add);
		intL.forEach(System.out::print);
		System.out.println();
		
		//Max
		OptionalInt  maxIndex = intL.stream().mapToInt(Integer::intValue).max();
		System.out.println(intL.get(maxIndex.getAsInt()));
		
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
				.map(i -> i)
				.collect(Collectors.joining(",","[","]"));	
		System.out.println(result);
		
		//flatMap
		List<String> a01 = Arrays.asList("A","B","C");
		List<String> a02 = Arrays.asList("D","E","F");
		List<String> ar = Stream.of(a01,a02)
				.flatMap(array -> array.stream())
				.collect(Collectors.toList());
		System.out.println(ar);
		
	}

}
