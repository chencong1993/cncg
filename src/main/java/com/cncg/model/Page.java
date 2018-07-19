package com.cncg.model;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cncg.util.CookieUtils;
import com.cncg.util.StringUtils;

public class Page<T> {

	private int pageNo=1;		//页码（1-）
	private int pageSize=10;	//每页记录数
	private int start;			//limit start,end
	private int end;			//limit start,end
	private int count;			//总数
	private List<T> list=new ArrayList<T>();	//分页列表
	
	public Page(HttpServletRequest request,HttpServletResponse response) {
		super();
		String no = request.getParameter("pageNo");
		String size = request.getParameter("pageSize");
		if(StringUtils.toInteger(no)>0) {
			this.pageNo = StringUtils.toInteger(no);
			//CookieUtils.setCookie(response, "pageNo", no);
		}/*else {
			no = CookieUtils.getCookie(request, "pageNo");
			if(StringUtils.toInteger(no)>0) {
				this.pageNo = StringUtils.toInteger(no);
			}
		}*/
		if(StringUtils.toInteger(size)>0) {
			this.pageSize = StringUtils.toInteger(size);
			CookieUtils.setCookie(response, "pageSize", size);
		}else {
			size = CookieUtils.getCookie(request, "pageSize");
			if (StringUtils.toInteger(size)>0) {
				this.pageSize = StringUtils.toInteger(size);
			}
		}
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
		//合理设置页码(当limit 起始参数大于等于记录总数时，设置页码为当前记录数最大页码)
		int start = (pageNo-1)*pageSize+1;
		if(start>this.count) {
			this.pageNo=(int)Math.ceil((double)this.count/(double)this.pageSize);
		}
	}

	public int getStart(){
		return (this.pageNo-1)*this.pageSize;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd(){
		return this.pageSize;
	}

	public void setEnd(int end) {
		this.end = end;
	}
	
	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("<ul>");
		sb.append("<li><a data=\"\" onclick=\"page(1,").append(this.pageSize).append(")\">首页</a></li>");
		int pages = (int) Math.ceil((double)this.count/(double)this.pageSize);//总页面
		int step=  pages>7 ? 7 : pages;//分页按钮数小于等于7个
		int start = (pages-this.pageNo)>2 ? (this.pageNo-3) : (pages-6);//起始页码
		for(int i=0;i<step;i++){
			int data=i+1;
			if(step==7 && this.pageNo>4){
				data=start;
				start++;
			}
			sb.append("<li><a data=\"")
				.append(data)
				.append("\" onclick=\"page(")
				.append(data)
				.append(",")
				.append(this.pageSize)
				.append(")\">")
				.append(data)
				.append("</a></li>");
		}
		sb.append("<li><a data=\"\" onclick=\"page(")
			.append(pages)
			.append(",")
			.append(this.pageSize)
			.append(")\">尾页</a></li></ul>")
			.append("<ul><li><div><label>当前</label><input type=\"text\" value=\"")
			.append(this.pageNo)
			.append("\"/><label>页</label>，<label>共")
			.append(pages)
			.append("页</label>，<label>每页</label><input type=\"text\" value=\"")
			.append(this.pageSize)
			.append("\"/><label>条</label>，<label>共")
			.append(this.count)
			.append("条</label></div></li></ul>");
		
		return sb.toString();
	}
	
}
