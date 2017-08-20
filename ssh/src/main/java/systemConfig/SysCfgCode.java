package systemConfig;

public class SysCfgCode {

	public static final String SUCCESS = "SUCCESS";
	public static final String defaultUser = "Ziv";
	public static final String desc = "desc";
	public static final String asc = "asc";
	
	/**
	 * table : CFG_SYSTEM_CONFIG.CODE
	 * @author Ziv
	 *
	 */
	public static final class Code {
		public static final String RPPdefault_use ="RPPdefault_use";

	}
	
	/**
	 * table : CFG_SYSTEM_CONFIG.CODE_CATE
	 * @author Ziv
	 *
	 */
	public static final class CodeCate {
		
		public static final String TableHeader = "TableHeader";
		public static final String Add_TableHeader = "Add_TableHeader";
		public static final String RecordsPerPage = "RecordsPerPage";
		public static final String TreeBranch = "TreeBranch";
		public static final String TreeTrunk = "TreeTrunk";
		public static final String TreeRoot = "TreeRoot";
		public static final String pages = "pages";
		
	}
	
	/**
	 * 對應 CfgSystemConfig 資料欄位形態, struts2 josn 傳輸對應檢查使用
	 * @author Ziv
	 *
	 */
	public static final class CfgDataType {
		public static final String string = "string";
		public static final String number = "number";
	}
	
}
