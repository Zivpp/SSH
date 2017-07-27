package ssh.service;

import java.util.HashMap;

public interface ICfgSystemConfigService{

	/**
	 * 取得 CFG_SYSTEM_CONFIG pages 需要的資料
	 * @return
	 * @throws Exception 
	 */
	public HashMap<String, Object> getSCPInitialData() throws Exception;

}
