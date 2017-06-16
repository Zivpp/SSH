package ssh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import ssh.dao.ILoginDao;
import systemConfig.SysCfgCode;

@Component("loginServiceImpl")
public class LoginServiceImpl implements ILoginService{

	@Autowired
	@Qualifier("LoginDaoImpl")
	private ILoginDao loginDao;
	
	public String login(String account, String password) throws Exception {
		
		String empId = loginDao.login(account,password);
		
		return SysCfgCode.SUCCESS;
	}

}
