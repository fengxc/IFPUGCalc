package cn.fxc.ifpugCalc.model;

public class FieldObject {
	public static Integer maxFieldId = 0;
	private Integer id;
	private String name;
	private String RETName;
	private DataObject parent;
	
	public FieldObject(String name, DataObject parent) {
		super();
		this.name = name;
		this.parent = parent;
		maxFieldId++;
		id = maxFieldId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public DataObject getParent() {
		return parent;
	}
	public void setParent(DataObject parent) {
		this.parent = parent;
	}
	public String getRETName() {
		return RETName;
	}
	public void setRETName(String rETName) {
		RETName = rETName;
	}
	
	@Override
	public String toString() {
		return  parent+":"+name ;
	}
	
}
