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
}
