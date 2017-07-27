package ssh.action.systemConfig;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import bean.CfgSystemConfig;
import ssh.action.BaseAction;
import ssh.dao.ICfgSystemConfigDao;
import ssh.service.ICfgSystemConfigService;
import ssh.util.CacheUtil;

@Component("systemConfigAction")
public class SystemConfigAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	@Qualifier("cfgSystemConfigServiceImpl")
	private ICfgSystemConfigService cfgSystemConfigService;
	
	public String getSCPInitialData() {
		
		try{
			
			HashMap<String,Object> result = cfgSystemConfigService.getSCPInitialData();
			
			super.dataHandler(result);
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		return SUCCESS;
		
	}

}
