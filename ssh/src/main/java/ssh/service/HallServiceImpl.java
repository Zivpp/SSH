package ssh.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import bean.CfgSystemConfig;
import ssh.util.CacheUtil;
import ssh.util.ConfigUtil;
import systemConfig.SysCfgCode;

@Component("hallServiceImpl")
public class HallServiceImpl implements IHallService{

	@Override
	public HashMap<String,Object> hallPageInitial() {

		HashMap<String,Object> result = new HashMap<String,Object>();
		
		result.put("root", CacheUtil.getSysCfgByCodeCate(SysCfgCode.CodeCate.TreeRoot));
		result.put("trunk", CacheUtil.getSysCfgByCodeCate(SysCfgCode.CodeCate.TreeTrunk));
		result.put("branch", CacheUtil.getSysCfgByCodeCate(SysCfgCode.CodeCate.TreeBranch));
		
		return result;
	}

	
}
