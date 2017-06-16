package ssh.dao;

public interface ILoginDao {

	/**
	 * Long dao
	 * @param account
	 * @param password
	 * @return
	 * @throws Exception 
	 */
	String login(String account, String password) throws Exception;

}
