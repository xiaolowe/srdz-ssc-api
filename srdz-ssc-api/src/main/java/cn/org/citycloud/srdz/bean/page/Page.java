package cn.org.citycloud.srdz.bean.page;

public class Page {
	
	private int page=1;
	
	private int pageSize=10;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
