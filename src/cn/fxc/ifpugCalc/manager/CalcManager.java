package cn.fxc.ifpugCalc.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.fxc.ifpugCalc.model.DataObject;
import cn.fxc.ifpugCalc.model.DataType;
import cn.fxc.ifpugCalc.model.FileReferencedElement;
import cn.fxc.ifpugCalc.model.TransactionObject;
import cn.fxc.ifpugCalc.model.TransactionType;

public class CalcManager implements Serializable{

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
			DataObject o = ilfList.get(index);
			delFtr(o, eiList);
			delFtr(o, eoList);
			delFtr(o, eqList);
			ilfList.remove(index);
			
			return true;
		}else if(d.equals(DataType.ELF)){
			elfList.remove(index);
			return true;
		}else{
			return false;
		}
	}

	private void delFtr(DataObject o, List<TransactionObject> tList) {
		for(TransactionObject t:tList){
			List<FileReferencedElement> ftrList = t.getFtrList();
			List<FileReferencedElement> ftrListToDel = new ArrayList<FileReferencedElement>();
			for(FileReferencedElement ftr:ftrList){
				if(ftr.getReferencedFile().equals(o)){
					ftrListToDel.add(ftr);
				}
			}
			for(FileReferencedElement ftr:ftrListToDel){
				ftrList.remove(ftr);
			}
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
