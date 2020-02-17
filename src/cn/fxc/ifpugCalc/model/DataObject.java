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

import java.util.ArrayList;
import java.util.List;

public class DataObject {
	public static Integer maxDataId = 0;

	private Integer id;
	private String name;
	private String fieldStr;
	List<FieldObject> fieldList;
	public DataObject(String name, DataType type) {
		super();
		this.name = name;
		this.type = type;
		maxDataId++;
		id = maxDataId;
		fieldList = new ArrayList<FieldObject>();
		
	}
	private DataType type;
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
	public DataType getType() {
		return type;
	}
	public void setType(DataType type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return  name ;
	}
	
	public Complexity getComplexity(){
		int RET=0;
		List<String> RETList = new ArrayList<String>();
		for(FieldObject f:fieldList){
			String ftrStr = f.getRETName();
			if(ftrStr==null)
				ftrStr ="";
			boolean hasFound = false;
			for(String s:RETList){
				if(s.equals(ftrStr)){
					hasFound = true;
					break;
				}
			}
			if(!hasFound){
				RETList.add(ftrStr);
				RET++;
			}
		}
		int DET=fieldList.size();
		if(RET<=1){
			if(DET<51)
				return Complexity.Simple;
			else
				return Complexity.Average;
		}else if(RET>1&&RET<6){
			if(DET<20)
				return Complexity.Simple;
			else if(DET<51)
				return Complexity.Average;
			else
				return Complexity.Complex;
		}else{
			if(DET<20)
				return Complexity.Average;
			else
				return Complexity.Complex;
		}
		
	}
	public List<FieldObject> getFieldList() {
		return fieldList;
	}
	public void setFieldList(List<FieldObject> fieldList) {
		this.fieldList = fieldList;
	}
	public String getFieldStr() {
		return fieldStr;
	}
	public void setFieldStr(String fieldStr) {
		this.fieldStr = fieldStr;
	}
}
