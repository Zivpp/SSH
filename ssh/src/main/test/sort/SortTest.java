package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import ssh.util.BeanUtil;

public class SortTest {

	public static void main(String[] args) {

		int[] intArray = new int[5];
		for(int i =0;i< 5;i++){
			Random rand = new Random();
			intArray[i] = rand.nextInt(100+1);
		}
		Arrays.stream(intArray).forEach(i -> System.out.print(i + ", "));
		System.out.println();
		for(int i = intArray.length - 1; i > 0; --i){
			for(int j = 0 ; j < i ; j ++){
				if(intArray[j] < intArray[j+1]){
					int tmep = intArray[j];
					intArray[j] = intArray[j+1];
					intArray[j+1] = tmep;
				}
			}
		}
		Arrays.stream(intArray).forEach(i -> System.out.print(i + ", "));
		System.out.println();
		
		
		List<Integer> intlist = new ArrayList<Integer>();
		for(int i =0 ; i < 5 ; i++){
			Random r = new Random();
			intlist.add(r.nextInt(100+1));
		}
		intlist.stream().forEach(i -> System.out.print(i+", "));
		System.out.println();
		for(int i = intlist.size()-1; i>0 ;i--){
			for(int j=0;j<i;j++){
				if(intlist.get(j) > intlist.get(j+1)){
					BeanUtil.swap(intlist,j,j+1);
				}
			}
		}
		intlist.stream().forEach(i -> System.out.print(i+", "));
		System.out.println();
		
	}

}
