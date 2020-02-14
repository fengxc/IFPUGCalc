package cn.fxc.ifpugCalc.gui.editor;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListModel;
import cn.fxc.ifpugCalc.gui.ResultFrame;
import cn.fxc.ifpugCalc.gui.list.TransactionObjectList;
import cn.fxc.ifpugCalc.model.DataObject;
import cn.fxc.ifpugCalc.model.DataType;
import cn.fxc.ifpugCalc.model.FieldObject;
import cn.fxc.ifpugCalc.model.FileReferencedElement;
import cn.fxc.ifpugCalc.model.TransactionDataElement;
import cn.fxc.ifpugCalc.model.TransactionObject;
import cn.fxc.ifpugCalc.model.TransactionType;

public class TransactionObjectEditor extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4888490422801061241L;

	private TransactionObjectList parent;
	ResultFrame resultFrame;
	private JLabel jLName = new JLabel("名称");
	private JTextField jFName = new JTextField("");
//	
//	private JTable jTFields;
//	private DefaultTableModel jDTMField;
//	
	JPanel jpDataObject;
	JList jLDataObject;
	ListModel jMDataObject;

	JList jLSelectedDataObject;
	ListModel jMSelectedDataObject;

	JPanel jpFieldObject;
	JList jLFieldObject;
	ListModel jMFieldObject;

	JList jLSelectedFieldObject;
	ListModel jMSelectedFieldObject;

	
	private JButton jbOK = new JButton("确定");
	private JButton jbClose = new JButton("取消");
	
	private JButton jbDataObjectAdd = new JButton("添加");
	private JButton jbDataObjectRemove = new JButton("删除");
	
	private JButton jbFieldObjectAdd = new JButton("添加");
	private JButton jbFieldObjectRemove = new JButton("删除");
	private TransactionType type;

	private List<DataObject> allDO = new ArrayList<DataObject>();
	private List<DataObject> selectedDO = new ArrayList<DataObject>();
	
	private List<FieldObject> availableFO = new ArrayList<FieldObject>();
	private List<FieldObject> selectedFO = new ArrayList<FieldObject>();

	public TransactionObjectEditor(TransactionObjectList transactionObjectList, ResultFrame parentForm, TransactionType t) {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setPreferredSize(new Dimension(560, 760));
		this.setSize(600, 760);
		parent = transactionObjectList;
		resultFrame = parentForm;
		type = t;
		this.setTitle(type.name()+"修改器");
		this.setLayout(new FlowLayout(FlowLayout.LEFT,5,5));
		this.add(jLName);
		this.add(jFName);
		jFName.setPreferredSize(new Dimension(200, 30));
		jFName.setSize(200, 30);
		allDO = new ArrayList<DataObject>();
		allDO.addAll(resultFrame.getCm().getDataObjectList(DataType.ILF));
		allDO.addAll(resultFrame.getCm().getDataObjectList(DataType.ELF));
		
		jMDataObject =  new DefaultComboBoxModel(allDO.toArray());  //数据模型
		jLDataObject = new JList();
		jLDataObject.setSize(new Dimension(200, 300));
		jLDataObject.setPreferredSize(new Dimension(200, 300));
		jLDataObject.setModel(jMDataObject);
		
		jMSelectedDataObject =  new DefaultComboBoxModel();  //数据模型
		jLSelectedDataObject = new JList();
		jLSelectedDataObject.setSize(new Dimension(200, 300));
		jLSelectedDataObject.setPreferredSize(new Dimension(200, 300));
		jLSelectedDataObject.setModel(jMSelectedDataObject);
		
		jbDataObjectAdd = new JButton("添加");
		jbDataObjectAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(jLDataObject.getSelectedIndex()>=0){
					Object sel = jLDataObject.getSelectedValue();
					if(!selectedDO.contains(sel))
						selectedDO.add((DataObject) sel);	
					jMSelectedDataObject =  new DefaultComboBoxModel(selectedDO.toArray());  //数据模型
					jLSelectedDataObject.setModel(jMSelectedDataObject);
					availableFO.clear();
					for(DataObject dod:selectedDO){
						availableFO.addAll(dod.getFieldList());
					}
					jMFieldObject =  new DefaultComboBoxModel(availableFO.toArray());  //数据模型
					jLFieldObject.setModel(jMFieldObject);
				}				
			}
		});
		jbDataObjectRemove = new JButton("删除");
		jbDataObjectRemove.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(jLSelectedDataObject.getSelectedIndex()>=0){
					Object sel = jLSelectedDataObject.getSelectedValue();
					selectedDO.remove(sel);
					jMSelectedDataObject =  new DefaultComboBoxModel(selectedDO.toArray());  //数据模型
					jLSelectedDataObject.setModel(jMSelectedDataObject);
					availableFO.clear();
					for(DataObject dod:selectedDO){
						availableFO.addAll(dod.getFieldList());
					}
					jMFieldObject =  new DefaultComboBoxModel(availableFO.toArray());  //数据模型
					jLFieldObject.setModel(jMFieldObject);
					List<FieldObject> toDelete = new ArrayList<FieldObject>();
					for(FieldObject f:selectedFO){
						if(!availableFO.contains(f)){
							toDelete.add(f);
						}
					}
					for(FieldObject f:toDelete){
						selectedFO.remove(f);	
					}
					jMSelectedFieldObject =  new DefaultComboBoxModel(selectedFO.toArray());  //数据模型
					jLSelectedFieldObject.setModel(jMSelectedFieldObject);
				}				
			}
		});
		jpDataObject = new JPanel();
		
		jpDataObject.add(jLDataObject);
		jpDataObject.add(jbDataObjectAdd);
		jpDataObject.add(jbDataObjectRemove);
		jpDataObject.add(jLSelectedDataObject);
		
		this.add(jpDataObject);
		
		
		jMFieldObject =  new DefaultComboBoxModel(availableFO.toArray());  //数据模型
		jLFieldObject = new JList();
		jLFieldObject.setSize(new Dimension(200, 300));
		jLFieldObject.setPreferredSize(new Dimension(200, 300));
		jLFieldObject.setModel(jMFieldObject);
		
		jMSelectedFieldObject =  new DefaultComboBoxModel();  //数据模型
		jLSelectedFieldObject = new JList();
		jLSelectedFieldObject.setSize(new Dimension(200, 300));
		jLSelectedFieldObject.setPreferredSize(new Dimension(200, 300));
		jLSelectedFieldObject.setModel(jMSelectedFieldObject);
		
		jbFieldObjectAdd = new JButton("添加");
		jbFieldObjectAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(jLFieldObject.getSelectedIndex()>=0){
					Object sel = jLFieldObject.getSelectedValue();
					if(!selectedFO.contains(sel))
						selectedFO.add( (FieldObject) sel);	
					jMSelectedFieldObject =  new DefaultComboBoxModel(selectedFO.toArray());  //数据模型
					jLSelectedFieldObject.setModel(jMSelectedFieldObject);
					
				}				
			}
		});
		jbFieldObjectRemove = new JButton("删除");
		jbFieldObjectRemove.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(jLSelectedFieldObject.getSelectedIndex()>=0){
					Object sel = jLSelectedFieldObject.getSelectedValue();
					selectedFO.remove(sel);	
					jMSelectedFieldObject =  new DefaultComboBoxModel(selectedFO.toArray());  //数据模型
					jLSelectedFieldObject.setModel(jMSelectedFieldObject);
					
				}				
			}
		});
		jpFieldObject = new JPanel();

		jpFieldObject.add(jLFieldObject);
		jpFieldObject.add(jbFieldObjectAdd);
		jpFieldObject.add(jbFieldObjectRemove);
		jpFieldObject.add(jLSelectedFieldObject);
		
		this.add(jpFieldObject);
		
		this.add(jbOK);
		this.add(jbClose);
		jbOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(jFName.getText()!=null&&jFName.getText().length()>0){
					TransactionObject n = new TransactionObject(jFName.getText(), type);
					
					for(DataObject dod:selectedDO){
						FileReferencedElement a = new FileReferencedElement(dod, n);
						n.getFtrList().add(a);
					}

					for(FieldObject fod:selectedFO){
						TransactionDataElement a = new TransactionDataElement(fod, n);
						n.getDetList().add(a);
					}
					parent.addTransaction(n);
					TransactionObjectEditor.this.dispose();
				}
			}
		});
		jbClose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TransactionObjectEditor.this.dispose();
			}
		});
		
	}

	public TransactionType getType() {
		return type;
	}

	public void setType(TransactionType type) {
		this.type = type;
	}
	
}
