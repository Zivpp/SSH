package ssh.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BeanUtil {

	/**
	 * Bean class 轉換為 key : value, 類似 Table
	 * 初步設計, 只提供 :數字、字串
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
	 * copyBean 內存有值的攔位，將其值 copy 到 toBean
	 * @param toBean
	 * @param copyBean
	 * @return
	 */
	public static Object beanCopy(Object toBean,Object copyBean) throws Exception{
		
		if(toBean != null && copyBean != null){
			
			//儲存 toBean 的 set 方法
			List<String> toBeanSetMethods = new ArrayList<String>();
			
			//1. 先將 toBean 的所有方法抓出，用於判斷稍後要 copy 的
			Field[] tbfiles = toBean.getClass().getDeclaredFields();
			for(Field f : tbfiles){
				//when static parameters, no doing 
				if(!java.lang.reflect.Modifier.isStatic(f.getModifiers())){
					String setStr = "set" + StringUtil.upperCaseAtFirst(f.getName());
					toBeanSetMethods.add(setStr);
				}
			}
			
			//2. 如果 copyBean 的 get方法，有值
			//   再判斷 set 方法有無存在 toBeanSetMethods中
			//   兩個條件符合，copy to toBean。
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
						//取得 parameters 型態
						String resultClassStr =  copyResult.getClass().toString();
						Method setMethod = null;
//						if(resultClassStr.equals("class java.lang.Integer")){  //Integer 與其它不一樣
//							 setMethod = toBean.getClass().getMethod(setStr,Integer.TYPE);
//						}else{
//							 setMethod = toBean.getClass().getMethod(setStr,copyResult.getClass());
//						}
						setMethod = toBean.getClass().getMethod(setStr,copyResult.getClass());
						//呼叫 set 方法把值丟進去
						setMethod.invoke(toBean,copyResult);
					}
				}
			}
		}
		
		return toBean;
	}
}
