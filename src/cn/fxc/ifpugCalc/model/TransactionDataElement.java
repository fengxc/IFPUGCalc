/**
 * MIT License
 * 
 * Copyright (c) 2020 fengxc
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:

 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
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
