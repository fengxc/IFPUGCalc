package cn.fxc.ifpugCalc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DataObject  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1555294133284850035L;

	public static Integer maxDataId = 0;

	private Integer id;
	private String name;
	private String fieldStr;
	//List<FieldObject> fieldList;
	List<String> retList;
	List<String> detList;
	
	public DataObject(String name, DataType type) {
		super();
		this.name = name;
		this.type = type;
		maxDataId++;
		id = maxDataId;
		retList = new ArrayList<String>();
		detList = new ArrayList<String>();
		
		//fieldList = new ArrayList<FieldObject>();
		
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
		return  name + "[RET:"+retList.size()+"]"+ "[DET:"+detList.size()+"]";
	}
	
	public Complexity getComplexity(){
		int RET=retList.size();
//		List<String> RETList = new ArrayList<String>();
//		for(FieldObject f:fieldList){
//			String ftrStr = f.getRETName();
//			if(ftrStr==null)
//				ftrStr ="";
//			boolean hasFound = false;
//			for(String s:RETList){
//				if(s.equals(ftrStr)){
//					hasFound = true;
//					break;
//				}
//			}
//			if(!hasFound){
//				RETList.add(ftrStr);
//				RET++;
//			}
//		}
		int DET=detList.size();
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
/*	public List<FieldObject> getFieldList() {
		return fieldList;
	}
	public void setFieldList(List<FieldObject> fieldList) {
		this.fieldList = fieldList;
	}*/
	public String getFieldStr() {
		return fieldStr;
	}
	public void setFieldStr(String fieldStr) {
		this.fieldStr = fieldStr;
	}
	public List<String> getRetList() {
		return retList;
	}
	public void setRetList(List<String> retList) {
		this.retList = retList;
	}
	public List<String> getDetList() {
		return detList;
	}
	public void setDetList(List<String> detList) {
		this.detList = detList;
	}
}
