package cn.fxc.ifpugCalc.model;

public class FileReferencedElement {
	public static Integer maxFieldId = 0;
	private Integer id;
	//private String name;
	private DataObject referencedFile;
	private TransactionObject parent;
	
	public FileReferencedElement(DataObject referencedFile, TransactionObject parent) {
		super();
		this.referencedFile = referencedFile;
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
	public TransactionObject getParent() {
		return parent;
	}
	public void setParent(TransactionObject parent) {
		this.parent = parent;
	}
	
	@Override
	public String toString() {
		return parent+" referenced "+referencedFile;
	}
	
}
