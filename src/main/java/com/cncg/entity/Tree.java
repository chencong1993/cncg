package com.cncg.entity;

public class Tree {
	
	private Integer id;
	private Integer pId;
	private String name;
	private boolean open;
	private boolean checked;
	
	
	public Tree() {
		super();
	}
	
	public Tree(Integer id, Integer pId, String name, boolean open, boolean checked) {
		super();
		this.id = id;
		this.pId = pId;
		this.name = name;
		this.open = open;
		this.checked = checked;
	}

	public Tree(Menu menu) {
		super();
		this.id = menu.getMenuId();
		this.pId = menu.getParentId();
		this.name = menu.getMenuName();
		this.open = true;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getpId() {
		return pId;
	}
	public void setpId(Integer pId) {
		this.pId = pId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean getOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public boolean getChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}

}
