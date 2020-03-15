package cn.fxc.ifpugCalc.model;

import java.io.Serializable;

public class FileReferencedElement  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4912545169012847548L;
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
	
	public DataObject getReferencedFile() {
		return referencedFile;
	}
	public void setReferencedFile(DataObject referencedFile) {
		this.referencedFile = referencedFile;
	}
	@Override
	public String toString() {
		return parent+" referenced "+referencedFile;
	}
	
}
