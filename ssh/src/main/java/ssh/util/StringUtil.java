package ssh.util;

public class StringUtil {
	
    /**
     * 判斷字串是否為 null 或是空字串
     * @param str : 字串
     * @return
     */
    public static boolean isEmpty(String str) {
        boolean result = false;
        
        if (str == null || "".equals(str.trim())) {
            result = true;
        }
        
        return result;
    }

	/**把第一個字母轉成大寫
	 * @param digits
	 * @return
	 */
	public static String upperCaseAtFirst(String digits) {
		StringBuilder sb = new StringBuilder(digits);
		sb = sb.replace(0, 1, String.valueOf(sb.charAt(0)).toUpperCase());
		return sb.toString();
	}
    
    
}
