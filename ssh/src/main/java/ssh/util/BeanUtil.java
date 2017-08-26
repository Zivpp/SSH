package ssh.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BeanUtil {

	/**
	 * Bean class �ഫ�� key : value, ���� Table
	 * ��B�]�p, �u���� :�Ʀr�B�r��
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static HashMap<String,String> transferBeanToHashMap(Object obj) throws Exception{
		
		HashMap<String,String> result = null;
		
		if(obj != null){
			
			result = new HashMap<String,String>();
			
			Field[] fs = obj.getClass().getDeclaredFields();
			for(Field f : fs){
				
				String getStr = "get" + StringUtil.upperCaseAtFirst(f.getName());
				
				//when static parameters, not do anything
				if(!java.lang.reflect.Modifier.isStatic(f.getModifiers())){
					
					Method getMethod = obj.getClass().getMethod(getStr);
					Object copyResult = getMethod.invoke(obj);
					String tmpValue = null;
					if(copyResult != null){
						tmpValue = String.valueOf(copyResult);
					}
					
					result.put(f.getName(), tmpValue);
					
				}
				
			}
			
		}
		
		return result;
	}
	
	/**
	 * copyBean ���s���Ȫ��d��A�N��� copy �� toBean
	 * @param toBean
	 * @param copyBean
	 * @return
	 */
	public static Object beanCopy(Object toBean,Object copyBean) throws Exception{
		
		if(toBean != null && copyBean != null){
			
			//�x�s toBean �� set ��k
			List<String> toBeanSetMethods = new ArrayList<String>();
			
			//1. ���N toBean ���Ҧ���k��X�A�Ω�P�_�y��n copy ��
			Field[] tbfiles = toBean.getClass().getDeclaredFields();
			for(Field f : tbfiles){
				//when static parameters, no doing 
				if(!java.lang.reflect.Modifier.isStatic(f.getModifiers())){
					String setStr = "set" + StringUtil.upperCaseAtFirst(f.getName());
					toBeanSetMethods.add(setStr);
				}
			}
			
			//2. �p�G copyBean �� get��k�A����
			//   �A�P�_ set ��k���L�s�b toBeanSetMethods��
			//   ��ӱ���ŦX�Acopy to toBean�C
			Field[] cbfiles = copyBean.getClass().getDeclaredFields();
			for(Field f : cbfiles){
				
				String getStr = "get" + StringUtil.upperCaseAtFirst(f.getName());
				String setStr = "set" + StringUtil.upperCaseAtFirst(f.getName());
				
				//when static parameters, not do anything
				if(!java.lang.reflect.Modifier.isStatic(f.getModifiers())
						&& toBeanSetMethods.contains(setStr)){
					
					Method getMethod = copyBean.getClass().getMethod(getStr);
					Object copyResult = getMethod.invoke(copyBean);
					
					if(copyResult != null){
						//���o parameters ���A
						String resultClassStr =  copyResult.getClass().toString();
						Method setMethod = null;
//						if(resultClassStr.equals("class java.lang.Integer")){  //Integer �P�䥦���@��
//							 setMethod = toBean.getClass().getMethod(setStr,Integer.TYPE);
//						}else{
//							 setMethod = toBean.getClass().getMethod(setStr,copyResult.getClass());
//						}
						setMethod = toBean.getClass().getMethod(setStr,copyResult.getClass());
						//�I�s set ��k��ȥ�i�h
						setMethod.invoke(toBean,copyResult);
					}
				}
			}
		}
		
		return toBean;
	}

	/**
	 * swap A, B
	 * @param List<Object>
	 * @param A
	 * @param B
	 */
	public static void swap(List<Integer> intlist, int A, int B) {
		Integer tmp = intlist.get(A);
		intlist.set(A, intlist.get(B));
		intlist.set(B, tmp);
	}
}
