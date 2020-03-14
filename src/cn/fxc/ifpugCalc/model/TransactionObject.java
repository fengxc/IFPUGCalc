package cn.fxc.ifpugCalc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class TransactionObject  implements Serializable{
	public static Integer maxTransactionId = 0;

	private Integer id;
	private String name;
	private TransactionType type;
	private List<FileReferencedElement> ftrList;
	private List<String> detList;
	public TransactionObject(String name, TransactionType type) {
		super();
		this.name = name;
		this.type = type;
		ftrList = new ArrayList<FileReferencedElement>();
		detList = new ArrayList<String>();
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
		return  name + "[FTR:"+ftrList.size()+"]"+ "[DET:"+detList.size()+"]";
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
	public List<String> getDetList() {
		return detList;
	}
	public void setDetList(List<String> detList) {
		this.detList = detList;
	}
}
