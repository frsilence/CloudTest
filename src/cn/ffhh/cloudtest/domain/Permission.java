package cn.ffhh.cloudtest.domain;

public class Permission {
	private Integer id;//权限Id，主键
	private String permissionName;//权限名称
	private String permissionDesc;//权限描述
	private Integer types;//类型：1=页面，2=API
	private Integer partentId;//上级ID
	private String url;//url
	private String method;//请求方式
	private Integer listorder;//排序
	private Boolean display;//是否显示
	private String iconname;//图标
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPermissionName() {
		return permissionName;
	}
	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}
	public String getPermissionDesc() {
		return permissionDesc;
	}
	public void setPermissionDesc(String permissionDesc) {
		this.permissionDesc = permissionDesc;
	}
	public Integer getTypes() {
		return types;
	}
	public void setTypes(Integer types) {
		this.types = types;
	}
	public Integer getPartentId() {
		return partentId;
	}
	public void setPartentId(Integer partentId) {
		this.partentId = partentId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public Integer getListorder() {
		return listorder;
	}
	public void setListorder(Integer listorder) {
		this.listorder = listorder;
	}
	public Boolean getDisplay() {
		return display;
	}
	public void setDisplay(Boolean display) {
		this.display = display;
	}
	public String getIconname() {
		return iconname;
	}
	public void setIconname(String iconname) {
		this.iconname = iconname;
	}
	@Override
	public String toString() {
		return "Permission [id=" + id + ", permissionName=" + permissionName + ", permissionDesc=" + permissionDesc
				+ ", types=" + types + ", partentId=" + partentId + ", url=" + url + ", method=" + method + ", listorder="
				+ listorder + ", display=" + display + ", iconname=" + iconname + "]";
	}
	
	
}
