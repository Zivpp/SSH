package bean;

public class PaginatinStartEnd {
	
	Integer pIndex;
	Integer start;
	Integer end;
	
	public PaginatinStartEnd(){}
	
	public PaginatinStartEnd(Integer pIndex, Integer start, Integer end) {
		this.pIndex = pIndex;
		this.start = start;
		this.end = end;
	}
	public Integer getpIndex() {
		return pIndex;
	}
	public void setpIndex(Integer pIndex) {
		this.pIndex = pIndex;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getEnd() {
		return end;
	}
	public void setEnd(Integer end) {
		this.end = end;
	}
	
}
