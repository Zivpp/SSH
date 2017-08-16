package ssh.util;

import java.util.ArrayList;
import java.util.List;

import bean.CfgSystemConfig;

/**
 * 初始化設定都此 class
 * @author User
 *
 */
public class ConfigUtil {

	
	public static List<String> getCfgSystemConfigValue(List<CfgSystemConfig> cscList) {
		
		List<String> result = null;
		
		if(cscList != null && cscList.size() > 0){
			
			result = new ArrayList<String>();
			
			for(CfgSystemConfig csc : cscList){
				result.add(csc.getCodeValue());
			}
			
		}
		
		return result;
	}
}
