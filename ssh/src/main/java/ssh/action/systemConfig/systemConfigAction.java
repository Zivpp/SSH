package ssh.action.systemConfig;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import bean.CfgSystemConfig;
import ssh.action.BaseAction;
import ssh.dao.ICfgSystemConfigDao;

public class systemConfigAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	@Qualifier("cfgSystemConfigDaoImpl")
	private ICfgSystemConfigDao cfgSystemConfigDao;
	
	public String getAllSysCfgParam() {
		
		//Ignore service level, just test.
		//List<CfgSystemConfig> sysCfgDatas = cfgSystemConfigDao.getAllDatas();
		
		//super.dataHandler(sysCfgDatas);
		
		return SUCCESS;
		
	}

}
