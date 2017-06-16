package ssh.facade;

import org.springframework.stereotype.Component;

import systemConfig.SysCfgCode;

@Component("loginFacadeImpl")
public class LoginFacadeImpl implements ILoginFacade{

	public String login(String account, String password) throws Exception {
		try{
			return SysCfgCode.SUCCESS;
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return null;
	}

}
