package ssh.service;

import java.util.HashMap;

import bean.CfgSystemConfig;

public interface ICfgSystemConfigService{

	/**
	 * 取得 CFG_SYSTEM_CONFIG pages 需要的資料
	 * @return
	 * @throws Exception 
	 */
	public HashMap<String, Object> getSCPInitialData() throws Exception;
	
	/**
	 * add one cfg_system_config data
	 * @throws Exception
	 */
	public void addFromConfigPage(CfgSystemConfig csc) throws Exception;

}
