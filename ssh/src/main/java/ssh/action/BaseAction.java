package ssh.action;

import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Struts2 統一回傳值控制
 * @author Ziv.Wu
 *
 */
public abstract class BaseAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	public final static String DATA_KEY = "data";
	public final static String EXCEPTION_KEY = "exception";
	
	public Map<String, Object> result = new HashMap<String, Object>();
	
	
	/**
	 * Struts 統一回傳資料方法 (沒有值也OK)
	 * @param data
	 */
	protected void dataHandler() {
		this.dataHandler(null);
	}
	
	
	/**
	 * Struts 統一回傳資料方法
	 * @param data
	 */
	protected void dataHandler(Object datas) {
		
		if(datas != null && datas instanceof Exception){
			Exception ex = (Exception)datas;
			this.result.put(EXCEPTION_KEY, ex.getMessage());
		}
		
		 if(datas != null){
			 this.result.put(DATA_KEY, datas);
		 }
		 
	}

	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}
	
}
