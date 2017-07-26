package ssh.action.systemConfig;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import bean.CfgSystemConfig;
import ssh.action.BaseAction;
import ssh.dao.ICfgSystemConfigDao;
import ssh.util.CacheUtil;

@Component("systemConfigAction")
public class SystemConfigAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	@Qualifier("cfgSystemConfigDaoImpl")
	private ICfgSystemConfigDao cfgSystemConfigDao;
	
	public String getSCPInitialData() {
		
		try{
			
			//Ignore service level, just test.
			//List<CfgSystemConfig> sysCfgDatas = cfgSystemConfigDao.getAllDatas();
			List<CfgSystemConfig> sysCfgDatas = CacheUtil.getSysCfgDatas();
			CfgSystemConfig test = CacheUtil.getSysCfgById("1");
			List<CfgSystemConfig> tmpList = CacheUtil.getSysCfgByCodeCate("TableHeader");
			
			//return datas
			super.dataHandler(sysCfgDatas);
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		return SUCCESS;
		
	}

}
