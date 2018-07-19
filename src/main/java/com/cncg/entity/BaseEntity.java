package com.cncg.entity;

import com.cncg.model.Page;

public class BaseEntity<T> {

	private Page<T> page;

	private String ids ;
	
	public Page<T> getPage() {
		return page;
	}

	public void setPage(Page<T> page) {
		this.page = page;
	}

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }
	
}
