package ssh.dao;

import java.util.List;

import bean.CfgSystemConfig;

public interface ICfgSystemConfigDao {
	
	/**
	 * insert need transaction;
	 * @param csc
	 */
	public void insert(CfgSystemConfig csc);

	/**
	 * select by cfg_system_config id
	 * @param id
	 * @return
	 */
	public CfgSystemConfig queryById(int id);

	/**
	 * update need transaction;
	 * @param csc
	 * @return
	 */
	public CfgSystemConfig update(CfgSystemConfig csc);

	/**
	 * delete need transaction
	 * @param id
	 */
	public void deleteById(int id);

	/**
	 * selecet by id(script way)
	 * @param id
	 * @return
	 */
	public List<CfgSystemConfig> searchById(int id);
    
}
