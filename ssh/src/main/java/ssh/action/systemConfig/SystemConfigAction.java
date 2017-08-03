package ssh.action.systemConfig;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import bean.CfgSystemConfig;
import login.TestStringData;
import ssh.action.BaseAction;
import ssh.service.ICfgSystemConfigService;
import ssh.util.BeanUtil;
import ssh.util.CacheUtil;

@Component("systemConfigAction")
public class SystemConfigAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	
	private CfgSystemConfig addData;
	private String cfgSysId;
	
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
	
	/**
	 * search cfg_system_config by id
	 * @return
	 * @throws Exception
	 */
	public String searchCfgSysConById() throws Exception{
		
		try{
			
			CfgSystemConfig csc = CacheUtil.getSysCfgById(cfgSysId);
			HashMap<String,String> result = BeanUtil.transferBeanToHashMap(csc);
			super.dataHandler(result);
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

	public String getCfgSysId() {
		return cfgSysId;
	}

	public void setCfgSysId(String cfgSysId) {
		this.cfgSysId = cfgSysId;
	}



}
