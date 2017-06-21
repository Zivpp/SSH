package ssh.dao;

import bean.CfgSystemConfig;

public interface ICfgSystemConfigDao {
	
	public void insert(CfgSystemConfig csc);
	
    public CfgSystemConfig find(Integer id);
    
}
