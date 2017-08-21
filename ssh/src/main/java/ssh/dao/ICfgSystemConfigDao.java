package ssh.dao;

import java.util.List;

import bean.CfgSystemConfig;

public interface ICfgSystemConfigDao {
	
	/**
	 * insert need transaction;
	 * @param csc
	 * @throws Exception 
	 */
	public void insert(CfgSystemConfig csc) throws Exception;

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
	 * delete by id
	 * @param id
	 */
	public void deleteById(int id);

	/**
	 * delete by id
	 * @param id
	 */
	public void deleteById(String id);
	
	/**
	 * selecet by id(script way), note : 如用自動轉換, select 出的名稱要與變數名稱相同, 不然都會被轉為大寫
	 * @param id
	 * @return
	 */
	public List<CfgSystemConfig> searchById(int id);

	/**
	 * 取得 CFG_SYSTEM_CONFIG 所有資料, 根據 Code_Cate and Seq asc
	 * @return
	 */
	public List<CfgSystemConfig> getAllData();

	/**
	 * 撈取 CFG_SYSTEM_CONFIG 所有資料, 根據排序 Key Word 排序
	 * @param string
	 * @return
	 */
	public List<CfgSystemConfig> searchCfgSysDataBySort(String orderKey, String type);

	/**
	 * batch delete by id
	 * @param deleteCfgSysIdList
	 */
	public void deleteByIdList(List<String> deleteCfgSysIdList);

	/**
	 * id list sort by table header
	 * @param header
	 * @param sortBy
	 * @param sortIdList
	 * @return
	 */
	public List<String> cfgSysConSortByHeader(String header, String orderkey, List<String> sortIdList);

	/**
	 * get new Id and Seq by Code Cate
	 * @param codeCate
	 * @return
	 */
	public CfgSystemConfig getNewIdSeqByCodeCate(String codeCate);

    
}
