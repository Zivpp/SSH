package ssh.service;

import java.util.HashMap;
import java.util.List;

import bean.CfgSystemConfig;

public interface ICfgSystemConfigService{

	/**
	 * 取得 CFG_SYSTEM_CONFIG pages 需要的資料
	 * @return
	 * @throws Exception 
	 */
	public HashMap<String, Object> getSCPInitialData() throws Exception;
	
	/**
	 * add cfg_system_config data
	 * @throws Exception
	 */
	public void addFromConfigPage(CfgSystemConfig csc) throws Exception;

	/**
	 * edit cfg_system_config data
	 * @param oldEditId 
	 * @throws Exception
	 */
	public HashMap<Boolean, String> editFromConfigPage(CfgSystemConfig editData, String oldEditId);

	/**
	 * remove cfg_system_config data
	 * @param removeData
	 */
	public void removeFromConfigPage(CfgSystemConfig removeData);

	/**
	 * batch remove cfg_system_config data
	 * @param deleteCfgSysIdList
	 */
	public void removeCfgSysByBatch(List<String> deleteCfgSysIdList);

	/**
	 * Id List sort by table header
	 * @param header
	 * @param sortBy
	 * @param sortIdList
	 * @return
	 */
	public List<String> cfgSysConSortByHeader(String header, Boolean sortBy, List<String> sortIdList);

	/**
	 * save tree view edit data
	 * @param tvEditDataList
	 * @throws Exception 
	 */
	public void tvEditSave(List<CfgSystemConfig> tvEditDataList) throws Exception;

	/**
	 * remove tree view edit data
	 * @param tvRemoveId
	 * @return
	 */
	public String tvEditRemove(String tvRemoveId);

}
