package cn.fxc.ifpugCalc.model;

public class TransactionDataElement {
	public static Integer maxFieldId = 0;
	private Integer id;
	//private String name;
	private FieldObject transactionField;
	private TransactionObject parent;
	
	public TransactionDataElement(FieldObject transactionField, TransactionObject parent) {
		super();
		this.transactionField = transactionField;
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

	public FieldObject getTransactionField() {
		return transactionField;
	}
	public void setTransactionField(FieldObject transactionField) {
		this.transactionField = transactionField;
	}

	@Override
	public String toString() {
		return parent+" related "+transactionField;
	}
}
