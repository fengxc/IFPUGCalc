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
package cn.fxc.ifpugCalc.manager;

import java.util.ArrayList;
import java.util.List;

import cn.fxc.ifpugCalc.model.DataObject;
import cn.fxc.ifpugCalc.model.DataType;
import cn.fxc.ifpugCalc.model.TransactionObject;
import cn.fxc.ifpugCalc.model.TransactionType;

public class CalcManager {

	private List<DataObject> ilfList;
	private List<DataObject> elfList;
	private List<TransactionObject> eiList;
	private List<TransactionObject> eoList;
	private List<TransactionObject> eqList;
	private int[] VAFScore;

	public CalcManager() {
		ilfList = new ArrayList<DataObject>();
		elfList = new ArrayList<DataObject>();
		eiList = new ArrayList<TransactionObject>();
		eoList = new ArrayList<TransactionObject>();
		eqList = new ArrayList<TransactionObject>();
		setVAFScore(new int[14]);
	}
	
	public List<DataObject> getDataObjectList(DataType t){
		if(t.equals(DataType.ILF)){
			return ilfList;
		}else if(t.equals(DataType.ELF)){
			return elfList;
		}else
			return null;
	}

	public List<TransactionObject> getTransactionObjectList(TransactionType t){
		if(t.equals(TransactionType.EI)){
			return eiList;
		}else if(t.equals(TransactionType.EO)){
			return eoList;
		}else if(t.equals(TransactionType.EQ)){
			return eqList;
		}else
			return null;
	}
	public boolean addDataObject(DataObject o){
		if(o.getType().equals(DataType.ILF)){
			ilfList.add(o);
			return true;
		}else if(o.getType().equals(DataType.ELF)){
			elfList.add(o);
			return true;
		}else{
			return false;
		}
		
		
	}
	
	public boolean removeDataObject(DataType d, int index){
		if(d.equals(DataType.ILF)){
			ilfList.remove(index);
			return true;
		}else if(d.equals(DataType.ELF)){
			elfList.remove(index);
			return true;
		}else{
			return false;
		}
	}

	public boolean addTransactionObject(TransactionObject n) {
		if(n.getType().equals(TransactionType.EI)){
			eiList.add(n);
			return true;
		}else if(n.getType().equals(TransactionType.EO)){
			eoList.add(n);
			return true;
		}else if(n.getType().equals(TransactionType.EQ)){
			eqList.add(n);
			return true;
		}else{
			return false;
		}		
	}
	
	public boolean removeTransactionObject(TransactionType d, int index){
		if(d.equals(TransactionType.EI)){
			eiList.remove(index);
			return true;
		}else if(d.equals(TransactionType.EO)){
			eoList.remove(index);
			return true;
		}else if(d.equals(TransactionType.EQ)){
			eqList.remove(index);
			return true;
		}else{
			return false;
		}
	}

	public int[] getVAFScore() {
		return VAFScore;
	}

	public void setVAFScore(int[] vAFScore) {
		VAFScore = vAFScore;
	}
}
