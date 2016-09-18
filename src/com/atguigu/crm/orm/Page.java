package com.atguigu.crm.orm;

import java.util.List;

public class Page<T> {
	private int pageNo;
	private int pageSize=5;
	private int totalPages;
	private long totalCount;
	public List<T> content;
	
	public int getPrev(){
		if(isHasPrev()){
			return this.pageNo-1;
		}
		return this.pageNo;
	}
	public boolean isHasPrev(){
		
		return this.pageNo>1;
	}
	public int getNext(){
		if(isHasNext()){
			return this.pageNo+1;
		}
		return this.pageNo;
	}
	public boolean isHasNext(){
		
		return this.pageNo<getTotalPages();
	}
	public List<T> getContent() {
		return content;
	}
	public void setContent(List<T> content) {
		this.content = content;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		if(pageNo<1){
			pageNo=1;
		}
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPages() {
		totalPages=(int) (totalCount/pageSize);
		if(totalCount%pageSize!=0){
			totalPages++;
		}
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
		
		if(this.pageNo>getTotalPages()){
			this.pageNo=getTotalPages();
		}
	}
	
	
}
