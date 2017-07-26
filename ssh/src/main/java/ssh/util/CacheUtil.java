package ssh.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import bean.CfgSystemConfig;
import ssh.dao.ICfgSystemConfigDao;

@Component("cacheUtil")
public class CacheUtil {
	
	public static List<CfgSystemConfig> allSysCfgData = new ArrayList<CfgSystemConfig>(); //all cfg_system_config data
	public static ConcurrentHashMap<String,CfgSystemConfig> sysCfgById = new ConcurrentHashMap<String,CfgSystemConfig>(); //by Id
	//by code
	public static ConcurrentHashMap<String,List<CfgSystemConfig>> sysCfgByCodeCate = new ConcurrentHashMap<String,List<CfgSystemConfig>>(); //by codeCate
	
	@Autowired
	@Qualifier("cfgSystemConfigDaoImpl")
	private ICfgSystemConfigDao cfgSystemConfigDao;
	
	@PostConstruct
	public void init() {
		
		initGetSysCfgConData();

	}

	/**
	 * 初始化 CFG_SYSTEM_CONFIG 相關 cache data
	 */
	private void initGetSysCfgConData() {
		
		allSysCfgData = cfgSystemConfigDao.getAllData();
		
		if(allSysCfgData != null && allSysCfgData.size() > 0){
			
			for(CfgSystemConfig csc : allSysCfgData){
				
				//By Id
				sysCfgById.put(String.valueOf(csc.getId()), csc);

				//By Code Cate : 如果 codeCate list 存在就取出 -> 新增一筆 -> 再存入 ; 沒有就新創一組 List
				String tmpCodeCate = csc.getCodeCate();
				List<CfgSystemConfig> tmpList = (sysCfgByCodeCate.get(tmpCodeCate) != null)?
						sysCfgByCodeCate.get(tmpCodeCate):new ArrayList<CfgSystemConfig>();
				tmpList.add(csc);
				sysCfgByCodeCate.put(tmpCodeCate, tmpList);
				
			}
		}		
	}

	
	/**
	 * 取得 CFG_SYSTEM_CONFIG by Id
	 * @param id
	 * @return
	 */
	public static CfgSystemConfig getSysCfgById(String id){
		return sysCfgById.get(id);
	}
	
	public static List<CfgSystemConfig> getSysCfgDatas() {
		return allSysCfgData;
	}

	public static void setSysCfgDatas(List<CfgSystemConfig> sysCfgDatas) {
		CacheUtil.allSysCfgData = sysCfgDatas;
	}

	public ICfgSystemConfigDao getCfgSystemConfigDao() {
		return cfgSystemConfigDao;
	}

	public void setCfgSystemConfigDao(ICfgSystemConfigDao cfgSystemConfigDao) {
		this.cfgSystemConfigDao = cfgSystemConfigDao;
	}

	/**
	 * 取得 CFG_SYSTEM_CONFIG List by Code_Cate
	 * @param codeCate
	 * @return
	 */
	public static List<CfgSystemConfig> getSysCfgByCodeCate(String codeCate) {
		
		if(sysCfgByCodeCate.get(codeCate) != null && sysCfgByCodeCate.get(codeCate).size() > 0){
			return sysCfgByCodeCate.get(codeCate);
		}
		
		return null;
	}

	
}
