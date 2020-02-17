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


public class TransactionObject {
	public static Integer maxTransactionId = 0;

	private Integer id;
	private String name;
	private TransactionType type;
	private List<FileReferencedElement> ftrList;
	private List<TransactionDataElement> detList;
	public TransactionObject(String name, TransactionType type) {
		super();
		this.name = name;
		this.type = type;
		ftrList = new ArrayList<FileReferencedElement>();
		detList = new ArrayList<TransactionDataElement>();
		maxTransactionId++;
		id = maxTransactionId;
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
	public TransactionType getType() {
		return type;
	}
	public void setType(TransactionType type) {
		this.type = type;
	}
	

	@Override
	public String toString() {
		return  name ;
	}

	public Complexity getComplexity(){
		int FTR=ftrList.size();
		int DET=detList.size();
		if(type.equals(TransactionType.EI)){
			if(FTR<=1){
				if(DET<16)
					return Complexity.Simple;
				else
					return Complexity.Average;
			}else if(FTR>1&&FTR<3){
				if(DET<5)
					return Complexity.Simple;
				else if(DET<16)
					return Complexity.Average;
				else
					return Complexity.Complex;
			}else{
				if(DET<5)
					return Complexity.Average;
				else
					return Complexity.Complex;
			}
		}else{
			if(FTR<=1){
				if(DET<20)
					return Complexity.Simple;
				else
					return Complexity.Average;
			}else if(FTR>1&&FTR<4){
				if(DET<6)
					return Complexity.Simple;
				else if(DET<20)
					return Complexity.Average;
				else
					return Complexity.Complex;
			}else{
				if(DET<6)
					return Complexity.Average;
				else
					return Complexity.Complex;
			}
		}
				
	}
	public List<FileReferencedElement> getFtrList() {
		return ftrList;
	}
	public void setFtrList(List<FileReferencedElement> ftrList) {
		this.ftrList = ftrList;
	}
	public List<TransactionDataElement> getDetList() {
		return detList;
	}
	public void setDetList(List<TransactionDataElement> detList) {
		this.detList = detList;
	}
}
