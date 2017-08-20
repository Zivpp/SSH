package ssh.service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import bean.AddCSCData;
import bean.CfgSystemConfig;
import bean.PaginatinStartEnd;
import ssh.dao.ICfgSystemConfigDao;
import ssh.util.BeanUtil;
import ssh.util.CacheUtil;
import ssh.util.StringUtil;
import systemConfig.SysCfgCode;

@Component("cfgSystemConfigServiceImpl")
public class CfgSystemConfigServiceImpl implements ICfgSystemConfigService{

	@Autowired
	@Qualifier("cfgSystemConfigDaoImpl")
	private ICfgSystemConfigDao cfgSystemConfigDao;
	
	@Autowired
	@Qualifier("cacheUtil")
	private CacheUtil cacheUtil;
	
	@Override
	public HashMap<String, Object> getSCPInitialData() throws Exception {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		try{
			
			List<CfgSystemConfig> tmpTHData = null;
			List<CfgSystemConfig> allCSCData = null;
			ArrayList<ArrayList<Object>> tbData = null;
			List<AddCSCData> addThData = null;
			List<String> recordsPerPage = null;
			HashMap<String,Integer> pagBtnCountMenu = null;
			HashMap<String,List<PaginatinStartEnd>> dataShowRangeMenu = null;
		
			//TableHeader
			tmpTHData = CacheUtil.getSysCfgByCodeCate(SysCfgCode.CodeCate.TableHeader);
			if(tmpTHData != null && tmpTHData.size() > 0){
				
				List<String> thData = new ArrayList<String>();
				for(CfgSystemConfig csc : tmpTHData){
					thData.add(csc.getCodeValue());
				}
				result.put("tableHeader", thData);
				
				//TableBody : match thData which success
				allCSCData = CacheUtil.getSysCfgDatas();
				if(allCSCData != null && allCSCData.size() > 0){		
					tbData = buildTableBodyData(thData,allCSCData);
					result.put("tableBody", tbData);
				}
			}
			
			//Add_TableHeader
			List<CfgSystemConfig> tmpAddTHData = CacheUtil.getSysCfgByCodeCate(SysCfgCode.CodeCate.Add_TableHeader);
			if(tmpAddTHData != null && tmpAddTHData.size() > 0){
				addThData = buildAddCfgSystemConfigBean(tmpAddTHData);
				result.put("addCfgSysConBean", addThData);
			}
			
			//RecordsPerPage default
			CfgSystemConfig rppDefault = CacheUtil.getSysCfgByCode(SysCfgCode.Code.RPPdefault_use);
			result.put("rppDefault", rppDefault.getCodeValue());
			
			//RecordsPerPage && dataShowMenu && pagBtnCountMenu
			List<CfgSystemConfig> tmpRPP = CacheUtil.getSysCfgByCodeCate(SysCfgCode.CodeCate.RecordsPerPage);
			if(tmpRPP != null && tmpRPP.size() > 0){
				recordsPerPage = new ArrayList<String>();
				for(CfgSystemConfig rpp : tmpRPP){
					recordsPerPage.add(rpp.getCodeValue());
				}
				result.put("recordsPerPage", recordsPerPage);
				
				Float allDataCount  = Float.valueOf(allCSCData.size());
				pagBtnCountMenu = new HashMap<String,Integer>();
				for(String rpp : recordsPerPage){
					if(!"All".equals(rpp)){
						int count = (int)Math.ceil(allDataCount/Float.valueOf(Integer.valueOf(rpp)));
						pagBtnCountMenu.put(rpp, count);
					}else{
						pagBtnCountMenu.put(rpp, 1);
					}
				}
				result.put("pagBtnCountMenu", pagBtnCountMenu);
				
				dataShowRangeMenu = new HashMap<String,List<PaginatinStartEnd>>();
				List<PaginatinStartEnd> tmpPSEList = null;
				PaginatinStartEnd tmpPSE = null;
				for(String rpp : recordsPerPage){
					
					tmpPSEList = new ArrayList<PaginatinStartEnd>();
					
					if(!"All".equals(rpp)){
						
						int intRpp = Integer.valueOf(rpp);
						Integer pCount  = pagBtnCountMenu.get(rpp);
						
						for(int i=1;i<=pCount;i++){
							tmpPSE = new PaginatinStartEnd();
							tmpPSE.setpIndex(i);
							tmpPSE.setStart(intRpp * i - intRpp);
							tmpPSE.setEnd(intRpp * i - 1);
							tmpPSEList.add(tmpPSE);
						}
						dataShowRangeMenu.put(rpp, tmpPSEList);
						
					}else{
						tmpPSE = new PaginatinStartEnd(1,0,(int)(allDataCount-1));
						tmpPSEList.add(tmpPSE);
						dataShowRangeMenu.put(rpp, tmpPSEList);
					}
				}
				result.put("dataShowRangeMenu", dataShowRangeMenu);
				
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
	private List<AddCSCData> buildAddCfgSystemConfigBean(List<CfgSystemConfig> tmpAddTHData) {
		
		List<AddCSCData> result = new ArrayList<AddCSCData>();
		
		//1.naming mapping : DB name -> java bean attr name
		List<String> camelCaseNameList = new ArrayList<String>(); //result of before mapping work
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
		
		//2. key : attr name | value : attr type
		HashMap<String,String> cscNameMap = new HashMap<String,String>();
		CfgSystemConfig csc = new CfgSystemConfig();
		Field[] fs = csc.getClass().getDeclaredFields();
		for(Field f : fs){
			String name = f.getName();
			String type = determineType(f);
			cscNameMap.put(name,type);
		}
		
		//3. 根據 header 判斷是否顯示
		AddCSCData tmpData;
		for(String ccName : camelCaseNameList){
			
			tmpData = new AddCSCData();
			
			if(cscNameMap.containsKey(ccName)){
				tmpData.setName(ccName);
				tmpData.setDataType(cscNameMap.get(ccName));
				result.add(tmpData);
			}
		}
		
		return result;
	}

	/**
	 * 決定 string or number, 前端欄位判斷使用
	 * @param f
	 * @return
	 */
	private String determineType(Field f) {
		
		String result = null;
		
		Class a = f.getType();
		
		if(Number.class.isAssignableFrom(a)){
			result = SysCfgCode.CfgDataType.number;
		}else{
			result = SysCfgCode.CfgDataType.string;
		}
		
		return result;
	}

	/**
	 * 從 CfgSystemConfig List 中，抓取與 TableHeader 對應的資料
	 * @param thData : table header data
	 * @param tmpCSC : all cfgSystemConfig data
	 * @return
	 */
	private ArrayList<ArrayList<Object>> buildTableBodyData(List<String> thData, List<CfgSystemConfig> tmpCSC) throws Exception{
		
		ArrayList<ArrayList<Object>> result = new ArrayList<ArrayList<Object>>();
		
		//1.排除_線 and 全部轉為大寫統一
		List<String> beRegexThData = new ArrayList<String>();
		for(String tmpTh : thData){
			String tmpStr = tmpTh.replace("_", "").toUpperCase();
			beRegexThData.add(tmpStr);
		}
		
		//2.for each CfgSystemConfig data
		for(CfgSystemConfig csc : tmpCSC){
			
			HashMap<String,String> tmpHashMap = new HashMap<String,String>(); //String_1 : 參數大寫名稱(對應TableHeader), String_2 : 參數值
			ArrayList<Object> tmpList = new ArrayList<Object>(); //存放Table Body Data
			
			//A. 先把每一筆的  CfgSystemConfig 參數名稱轉為大寫(用來比對)和參數值轉為String -> 存在一個會被刷新的 HashMap, key=Table Header
			Field[] fs = csc.getClass().getDeclaredFields();
			for(Field f : fs){
				//String_1
				String String_1 = f.getName().toUpperCase();
				//String_2 
				String String_2 = "";
				String getStr = "get" + StringUtil.upperCaseAtFirst(f.getName());
				//static parameters not get
				if(!java.lang.reflect.Modifier.isStatic(f.getModifiers())){
					Method getMethod = csc.getClass().getMethod(getStr);
					Object scsResult = getMethod.invoke(csc);
					String_2 = String.valueOf(scsResult);
				} 
				if(!StringUtil.isEmpty(String_1) && !StringUtil.isEmpty(String_2)){
					tmpHashMap.put(String_1, String_2);
				}
			}
			
			//B.相對應資料放入 tmpList
			for(String tmpTh : beRegexThData){
				if(tmpHashMap.containsKey(tmpTh)){
					tmpList.add(tmpHashMap.get(tmpTh));
				}else{
					tmpList.add("Null");
				}
			}
			
			result.add(tmpList);
		}
		
		return result;
	}

	@Override
	public void addFromConfigPage(CfgSystemConfig csc) throws Exception {
		
		if(csc != null){
			csc.setCreateUser(SysCfgCode.defaultUser);
			csc.setCreateDate(new Date());
			csc.setUpdateUser(SysCfgCode.defaultUser);
			csc.setUpdateDate(new Date());
			cfgSystemConfigDao.insert(csc);
			
			cacheUtil.init();
			
		}		
	}

	@Override
	public HashMap<Boolean, String> editFromConfigPage(CfgSystemConfig editData,String oldEditId) {
		
		HashMap<Boolean, String> result = new HashMap<Boolean, String>();
		
		try{
			
			CfgSystemConfig oldCsc = CacheUtil.getSysCfgById(oldEditId);
			CfgSystemConfig tmpCsc = CacheUtil.getSysCfgById(String.valueOf(editData.getId()));
			
			if(tmpCsc != null){
				if(oldCsc.getId() == tmpCsc.getId()){
					editData.setCreateDate(tmpCsc.getCreateDate());
					editData.setCreateUser(tmpCsc.getCreateUser());
					editData.setUpdateDate(new Date());
					editData.setUpdateUser(tmpCsc.getUpdateUser());
					cfgSystemConfigDao.update(editData);
					
					cacheUtil.init();
					
					result.put(true, "");
				}else{
					throw new Exception("ID 已經存在");
				}
			}else{
				throw new Exception("ID 不存在");
			}
					
		}catch(Exception e){
			System.out.println(e.getMessage());
			result.put(false, e.getMessage());
		}
		
		return result;
	}

	@Override
	public void removeFromConfigPage(CfgSystemConfig removeData) {
		try{
			
			cfgSystemConfigDao.deleteById(removeData.getId());
			
			cacheUtil.init();
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void removeCfgSysByBatch(List<String> deleteCfgSysIdList) {	
		
		try{
		
			cfgSystemConfigDao.deleteByIdList(deleteCfgSysIdList);
			
			cacheUtil.init();
		
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	@Override
	public List<String> cfgSysConSortByHeader(String header, Boolean sortBy, List<String> sortIdList) {
		
		String orderKey;
		
		if(sortBy){
			orderKey = SysCfgCode.asc;
		}else{
			orderKey = SysCfgCode.desc;
		}
		
		return cfgSystemConfigDao.cfgSysConSortByHeader(header,orderKey,sortIdList);
	}

	@Override
	public void tvEditSave(List<CfgSystemConfig> tvEditDataList) throws Exception {
	
		if(tvEditDataList != null && tvEditDataList.size() > 0){
			
			//如果存在 update, 不存在則 insert
			for(CfgSystemConfig csc : tvEditDataList){
				
				CfgSystemConfig exist = CacheUtil.getSysCfgById(String.valueOf(csc.getId()));
				
				//update
				if(exist != null){
					exist = (CfgSystemConfig)BeanUtil.beanCopy(exist, csc);
					cfgSystemConfigDao.update(exist);
				}else{
				//insert
	
					if(StringUtil.isEmpty(csc.getCodeValue())){
						csc.setCodeName(csc.getCode());
					}	
					if(csc.getParentId() == null){
						csc.setParentId(0);
					}
					csc.setUpdateDate(new Date());
					csc.setUpdateUser(SysCfgCode.defaultUser);
					csc.setCreateDate(new Date());
					csc.setCreateUser(SysCfgCode.defaultUser);
					
					List<CfgSystemConfig> tmpCsc =  CacheUtil.getSysCfgByCodeCate(csc.getCodeCate());
					Integer newSeq = 0;
					Integer newId = 0;
					for(CfgSystemConfig c : tmpCsc){
						if(newSeq <= c.getSeq()){
							newSeq = c.getSeq() + 1;
						}
						if(newId <= c.getId()){
							newId = c.getId() + 1 ;
						}
					}
					csc.setSeq(newSeq);
					csc.setId(newId);
					csc.setCodeName("New Tree View Item");
					csc.setCateName(tmpCsc.get(0).getCateName());
					csc.setCodeValue(csc.getCode());
					cfgSystemConfigDao.insert(csc);
					
				}
			}
			
			cacheUtil.init();
			
		}
		
		
		
	}

	@Override
	public String tvEditRemove(String tvRemoveId) {

		String errorMassage = "";
		
		if(!StringUtil.isEmpty(tvRemoveId)){
			
			CfgSystemConfig reCsc = CacheUtil.getSysCfgById(tvRemoveId);
			
			if(reCsc.getCodeCate().equals(SysCfgCode.CodeCate.TreeBranch)){
				cfgSystemConfigDao.deleteById(tvRemoveId);
			}else{
				//check whether has children
				List<CfgSystemConfig> cscList = CacheUtil.getSysCfgByParentId(tvRemoveId);
				if(cscList != null && cscList.size() > 0){
					errorMassage = "Unable to remove. Child(ren) node(s) existed in target node.";
				}else{
					cfgSystemConfigDao.deleteById(tvRemoveId);
				}
			}
				
		}else{
			errorMassage = "Null : Remove ID";
		}
		
		cacheUtil.init();
		
		return errorMassage;
	}

	@Override
	public List<String> getBranchPageOptions() {
		
		List<String> result = new ArrayList<String>();
		
		List<CfgSystemConfig> cscList = CacheUtil.getSysCfgByCodeCate(SysCfgCode.CodeCate.pages);
		if(cscList != null && cscList.size() > 0){
			for(CfgSystemConfig csc : cscList){
				result.add(csc.getCodeValue());
			}

		}
		
		return result;
	}
	
}
