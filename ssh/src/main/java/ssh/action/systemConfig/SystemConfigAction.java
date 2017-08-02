package ssh.action.systemConfig;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import bean.CfgSystemConfig;
import login.TestStringData;
import ssh.action.BaseAction;
import ssh.service.ICfgSystemConfigService;
import ssh.util.CacheUtil;

@Component("systemConfigAction")
public class SystemConfigAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	
	private CfgSystemConfig addData;
	
	@Autowired
	@Qualifier("cfgSystemConfigServiceImpl")
	private ICfgSystemConfigService cfgSystemConfigService;
	
	public String getSCPInitialData() {
		
		try{
			
			HashMap<String,Object> result = cfgSystemConfigService.getSCPInitialData();
			
			super.dataHandler(result);
			
		}catch(Exception e){
			System.out.println(e.getMessage());
			super.dataHandler(e);
		}
		
		return SUCCESS;
		
	}
	
	/**
	 * ·s¼W¤@µ§ CFG_SYSTEM_CONFIG DATA
	 * @return
	 */
	public String addCfgSystemConfig() throws Exception{
		
		try{
			
			cfgSystemConfigService.addFromConfigPage(addData);
			
		}catch(Exception e){
			System.out.println(e.getMessage());
			super.dataHandler(e);
		}
		
		return SUCCESS;
	}

	public CfgSystemConfig getAddData() {
		return addData;
	}

	public void setAddData(CfgSystemConfig addData) {
		this.addData = addData;
	}



}
