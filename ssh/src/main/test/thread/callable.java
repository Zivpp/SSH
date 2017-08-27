package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class callable {
	
	static List<String> list = new ArrayList<>();
	
	public static void main(String[] args) {
		
		List<Callable<Object>> callableList = new ArrayList<Callable<Object>>();
		//newFixedThreadPool : limit number of thread
		ExecutorService taskExecutor = Executors.newFixedThreadPool(10);
		
		try{
			
			long start = System.currentTimeMillis();
			
			Runnable r1 = () -> {list.add("r1");};
			Runnable r2 = () -> {
				try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
				list.add("r2");
			};
			Runnable r3 = () -> {
				try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
				list.add("r3");
			};
			Runnable r4 = () -> {list.add("r4");};
			
			callableList.add(Executors.callable(r1));
			callableList.add(Executors.callable(r2));
			callableList.add(Executors.callable(r3));
			callableList.add(Executors.callable(r4));
			
			taskExecutor.invokeAll(callableList,5000,TimeUnit.MILLISECONDS);
			
			long end = System.currentTimeMillis();
			
			taskExecutor.shutdown();
			
			list.forEach(s -> System.out.print(s +","));
			System.out.println();
			System.out.println("time : " + String.valueOf(end - start));
			
		}catch(Exception e){
			System.out.println(e);
		}
		

		
	}
	
	
	
}
