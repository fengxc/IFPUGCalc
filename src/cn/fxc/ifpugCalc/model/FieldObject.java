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
