package ssh.service;

public interface ILoginService {

	/**
	 * Login service
	 * @param account
	 * @param password
	 * @return
	 * @throws Exception
	 */
	String login(String account, String password) throws Exception;

}
