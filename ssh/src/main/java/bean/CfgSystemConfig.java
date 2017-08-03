package bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "CFG_SYSTEM_CONFIG")
public class CfgSystemConfig {

    private Integer id;
	private String codeCate;
	private String cateName;
	private Integer parentId;
	private Integer seq;
	private String code;
	private String codeName;
	private String codeValue;
	private String codeDesc;
	private Date createDate;
	private String createUser;
	private Date updataDate;
	private String updataUser;
	
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "CODE_CATE", nullable = false)
	public String getCodeCate() {
		return this.codeCate;
	}

	public void setCodeCate(String codeCate) {
		this.codeCate = codeCate;
	}

	@Column(name = "CATE_NAME", nullable = false)
	public String getCateName() {
		return this.cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	@Column(name = "PARENT_ID", nullable = false)
	public int getParentId() {
		return this.parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	@Column(name = "CODE", unique = true, nullable = false)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "CODE_NAME")
	public String getCodeName() {
		return this.codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	@Column(name = "CODE_VALUE")
	public String getCodeValue() {
		return this.codeValue;
	}

	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}

	@Column(name = "CODE_DESC", length = 2000)
	public String getCodeDesc() {
		return this.codeDesc;
	}

	public void setCodeDesc(String codeDesc) {
		this.codeDesc = codeDesc;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_DATE", nullable = false)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "CREATE_USER", nullable = false)
	public String getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATA_DATE", nullable = false)
	public Date getUpdataDate() {
		return this.updataDate;
	}

	public void setUpdataDate(Date updataDate) {
		this.updataDate = updataDate;
	}

	@Column(name = "UPDATA_USER", nullable = false)
	public String getUpdataUser() {
		return this.updataUser;
	}

	public void setUpdataUser(String updataUser) {
		this.updataUser = updataUser;
	}

	@Column(name = "SEQ", nullable = false)
	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}
	
}
