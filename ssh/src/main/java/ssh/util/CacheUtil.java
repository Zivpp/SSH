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
	public static ConcurrentHashMap<String,List<CfgSystemConfig>> sysCfgByCodeCate = new ConcurrentHashMap<String,List<CfgSystemConfig>>(); //by codeCate
	//by code
	
	@Autowired
	@Qualifier("cfgSystemConfigDaoImpl")
	private ICfgSystemConfigDao cfgSystemConfigDao;
	
	@PostConstruct
	public void init() {	
		this.initCfgSystemConfig();
	}	
	
	/**
	 * 初始化 CFG_SYSTEM_CONFIG 相關 cache data
	 * Reset static : use a temp variable
	 * Quote : https://stackoverflow.com/questions/35446345/can-you-reset-a-static-variable
	 */
	private void initCfgSystemConfig() {
		
		List<CfgSystemConfig> tmpAllSysCfgData = cfgSystemConfigDao.getAllData();
		ConcurrentHashMap<String,List<CfgSystemConfig>> tmpSysCfgByCodeCate = new ConcurrentHashMap<String,List<CfgSystemConfig>>();
		ConcurrentHashMap<String,CfgSystemConfig> tmpSysCfgById = new ConcurrentHashMap<String,CfgSystemConfig>();
		
		if(tmpAllSysCfgData != null && tmpAllSysCfgData.size() > 0){
			
			for(CfgSystemConfig csc : tmpAllSysCfgData){
				
				//By Id
				tmpSysCfgById.put(String.valueOf(csc.getId()), csc);

				//By Code Cate : 如果 codeCate list 存在就取出 -> 新增一筆 -> 再存入 ; 沒有就新創一組 List
				String tmpCodeCate = csc.getCodeCate();
				List<CfgSystemConfig> tmpList = (tmpSysCfgByCodeCate.get(tmpCodeCate) != null)?
						tmpSysCfgByCodeCate.get(tmpCodeCate):new ArrayList<CfgSystemConfig>();
				tmpList.add(csc);
				tmpSysCfgByCodeCate.put(tmpCodeCate, tmpList);
				
			}
		}	
		
		allSysCfgData = tmpAllSysCfgData;
		sysCfgById = tmpSysCfgById;
		sysCfgByCodeCate = tmpSysCfgByCodeCate;
		
	}

	
	/**
	 * 取得 CFG_SYSTEM_CONFIG by Id
	 * @param id
	 * @return
	 */
	public CfgSystemConfig getSysCfgById(String id){
		return this.sysCfgById.get(id);
	}
	
	public static List<CfgSystemConfig> getSysCfgDatas() {
		return allSysCfgData;
	}

	public void setSysCfgDatas(List<CfgSystemConfig> sysCfgDatas) {
		this.allSysCfgData = sysCfgDatas;
	}

	public ICfgSystemConfigDao getCfgSystemConfigDao() {
		return this.cfgSystemConfigDao;
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
