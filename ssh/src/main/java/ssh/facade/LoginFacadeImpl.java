package ssh.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import ssh.service.ILoginService;

@Component("loginFacadeImpl")
public class LoginFacadeImpl implements ILoginFacade{
	
	@Autowired
	@Qualifier("loginServiceImpl")
	private ILoginService loginService;

	public String login(String account, String password) throws Exception {
		try{
			
			return loginService.login(account,password);			
			
		}catch(Exception e){
			
			System.out.println(e.getMessage());
			
		}
		return null;
	}

}
