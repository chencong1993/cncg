package com.cncg.entity;

public class Menu extends BaseEntity<Menu>{
	
	//表实体
    private Integer menuId;

    private String menuName;

    private String menuPath;

    private Integer parentId;

    private String menuDescription;

    private String state;

    private String iconCls;
    
    private String parentName;

    public Integer getMenuId() {
        return menuId;
    }
    
    public String getMenuName() {
        return menuName;
    }

    public String getMenuPath() {
        return menuPath;
    }
    
    public Integer getParentId() {
        return parentId;
    }

    public String getMenuDescription() {
        return menuDescription;
    }

    public String getState() {
        return state;
    }

    public String getIconCls() {
        return iconCls;
    }
    
    public String getParentName() {
        return parentName;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public void setMenuPath(String menuPath) {
        this.menuPath = menuPath;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public void setMenuDescription(String menuDescription) {
        this.menuDescription = menuDescription;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

}