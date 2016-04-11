package bbs.others;

public class Page {
	private Long currentPage;	//当前页
	private Long pageCount;		//总页数
	private String biaozhi;			//4个选择，first，last，middle，onlyone
	public Page(Long currentPage, Long pageCount, String biaozhi) {
		super();
		this.currentPage = currentPage;
		this.pageCount = pageCount;
		this.biaozhi = biaozhi;
	}
	
	public Page() {
		super();
	}

	public Long getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Long currentPage) {
		this.currentPage = currentPage;
	}
	public Long getPageCount() {
		return pageCount;
	}
	public void setPageCount(Long pageCount) {
		this.pageCount = pageCount;
	}
	public String getBiaozhi() {
		return biaozhi;
	}
	public void setBiaozhi(String biaozhi) {
		this.biaozhi = biaozhi;
	}

}
