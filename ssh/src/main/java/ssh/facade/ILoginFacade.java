package ssh.facade;

public interface ILoginFacade {

	/**
	 * Login facade
	 * @param account
	 * @param password
	 * @throws Exception
	 */
	String login(String account, String password) throws Exception;

}
