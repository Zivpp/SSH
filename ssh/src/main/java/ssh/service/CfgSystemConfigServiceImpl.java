package ssh.service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import bean.CfgSystemConfig;
import ssh.util.CacheUtil;
import ssh.util.StringUtil;
import systemConfig.SysCfgCode;

@Component("cfgSystemConfigServiceImpl")
public class CfgSystemConfigServiceImpl implements ICfgSystemConfigService{

	@Override
	public HashMap<String, Object> getSCPInitialData() throws Exception {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		try{
			//TableHeader
			List<CfgSystemConfig> tmpTHData = CacheUtil.getSysCfgByCodeCate(SysCfgCode.CodeCate.TableHeader);
			if(tmpTHData != null && tmpTHData.size() > 0){
				
				List<String> thData = new ArrayList<String>();
				for(CfgSystemConfig csc : tmpTHData){
					thData.add(csc.getCodeValue());
				}
				result.put("tableHeader", thData);
				
				//TableBody : match thData which success
				List<CfgSystemConfig> tmpCSC = CacheUtil.getSysCfgDatas();
				if(tmpCSC != null && tmpCSC.size() > 0){		
					ArrayList<ArrayList<String>> tbData = buildTableBodyData(thData,tmpCSC);
					result.put("tableBody", tbData);
				}
			}
			
			//Add_TableHeader && empty cfg_sysytem_config bean
			List<CfgSystemConfig> tmpAddTHData = CacheUtil.getSysCfgByCodeCate(SysCfgCode.CodeCate.Add_TableHeader);
			if(tmpAddTHData != null && tmpAddTHData.size() > 0){
				HashMap<String,String> addThData = buildAddCfgSystemConfigBean(tmpAddTHData);
				result.put("addCfgSysConBean", addThData);
			}
			
		}catch(Exception e){
			throw new Exception(e);
		}
		
		return result;
	}

	/**
	 * 將 CfgSystemConfig 轉為 HashMap,key : attr, value : true = show
	 * @param tmpAddTHData
	 * @return
	 */
	private HashMap<String, String> buildAddCfgSystemConfigBean(List<CfgSystemConfig> tmpAddTHData) {
		
		LinkedHashMap<String, String> result = new LinkedHashMap<String, String>();
		List<String> camelCaseNameList = new ArrayList<String>(); //result of before mapping work
		
		//1.naming mapping : DB name -> java bean attr name
		for(CfgSystemConfig csc : tmpAddTHData){
			String tmpName = csc.getCodeValue();
			//a. 取得_的位置+1(轉大寫用)
			int upStrIndex = 0; //存放_的位置
			char[] tmpCharAry = tmpName.toCharArray();
			int tmpCount = 0;
			for(char c : tmpCharAry){
				tmpCount++;
				if("_".equals(String.valueOf(c))){
					upStrIndex = tmpCount; //因為從0開始, 可以直接取得+1位置
					break;
				}
			}
			//b. 全部轉為小寫
			tmpName = tmpName.toLowerCase();
			//c. 將_後的字轉為大寫, 拿掉_
			if(upStrIndex != 0){
				String upStr = String.valueOf(tmpName.charAt(upStrIndex)).toUpperCase();
				String newName = tmpName.substring(0, upStrIndex-1) + upStr + tmpName.substring(upStrIndex+1);
				camelCaseNameList.add(newName);
			}else{
				camelCaseNameList.add(tmpName);
			}
		}
		
		//2. 取得 csc attr id,存在 camelCaseNameMap 則放 true(show)
		CfgSystemConfig csc = new CfgSystemConfig();
		List<String> cscNameList = new ArrayList<String>();
		Field[] fs = csc.getClass().getDeclaredFields();
		for(Field f : fs){
			cscNameList.add(f.getName());
		}
		
		//3. 因排序問題,header 為主
		for(String ccn : camelCaseNameList){
			for(String cscN : cscNameList){
				if(ccn.equals(cscN)){
					result.put(cscN, "true");
					break;
				}
			}
		}
		
		return result;
	}

	/**
	 * 從 CfgSystemConfig List 中，抓取與 TableHeader 對應的資料
	 * @param thData : table header data
	 * @param tmpCSC : all cfgSystemConfig data
	 * @return
	 */
	private ArrayList<ArrayList<String>> buildTableBodyData(List<String> thData, List<CfgSystemConfig> tmpCSC) throws Exception{
		
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		
		//1.排除_線 與 全部轉為大寫統一
		List<String> beRegexThData = new ArrayList<String>();
		for(String tmpTh : thData){
			String tmpStr = tmpTh.replace("_", "").toUpperCase();
			beRegexThData.add(tmpStr);
		}
		
		//2.抓取每一筆 CfgSystemConfig 資料
		//A. 先把每一筆的  CfgSystemConfig 參數名稱轉為大寫(用來比對)和參數值轉為String -> 存在一個會被刷新的 HashMap, key=Table Header Data
		//B. 相對應資料放入 tmpList
		//C. 完整一整個 List 再放進 result
		for(CfgSystemConfig csc : tmpCSC){
			
			HashMap<String,String> tmpHashMap = new HashMap<String,String>(); //String_1 : 參數大寫名稱(對應TableHeader), String_2 : 參數值
			ArrayList<String> tmpList = new ArrayList<String>(); //存放Table Body Data
			
			//A.
			Field[] fs = csc.getClass().getDeclaredFields();
			for(Field f : fs){
				//String_1
				String String_1 = f.getName().toUpperCase();
				//String_2 
				String String_2 = "";
				String getStr = "get" + StringUtil.upperCaseAtFirst(f.getName());
				//static parameters, not get
				if(!java.lang.reflect.Modifier.isStatic(f.getModifiers())){
					Method getMethod = csc.getClass().getMethod(getStr);
					Object scsResult = getMethod.invoke(csc);
					String_2 = String.valueOf(scsResult);
				} 
				if(!StringUtil.isEmpty(String_1) && !StringUtil.isEmpty(String_2)){
					tmpHashMap.put(String_1, String_2);
				}
			}
			
			//B.
			for(String tmpTh : beRegexThData){
				
				if(tmpHashMap.containsKey(tmpTh)){
					tmpList.add(tmpHashMap.get(tmpTh));
				}else{
					tmpList.add("Null");
				}
			
			}
			
			//C.
			result.add(tmpList);
			
		}
		
		return result;
	}

	
	
}
