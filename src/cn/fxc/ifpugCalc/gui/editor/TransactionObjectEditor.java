package cn.fxc.ifpugCalc.gui.editor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.table.DefaultTableModel;

import cn.fxc.ifpugCalc.gui.ResultFrame;
import cn.fxc.ifpugCalc.gui.list.TransactionObjectList;
import cn.fxc.ifpugCalc.model.DataObject;
import cn.fxc.ifpugCalc.model.DataType;
import cn.fxc.ifpugCalc.model.FileReferencedElement;
import cn.fxc.ifpugCalc.model.TransactionObject;
import cn.fxc.ifpugCalc.model.TransactionType;

public class TransactionObjectEditor extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4888490422801061241L;
	private int editType = 0; //0新增 1修改
	private TransactionObject ton;
	private TransactionObjectList parent;
	ResultFrame resultFrame;
	private JLabel jLName = new JLabel("名称");
	private JTextField jFName = new JTextField("");

	JPanel jpDataObject;
	JList jLDataObject;
	ListModel jMDataObject;

	JList jLSelectedDataObject;
	ListModel jMSelectedDataObject;

//	JPanel jpFieldObject;
//	JList jLFieldObject;
//	ListModel jMFieldObject;
//
//	JList jLSelectedFieldObject;
//	ListModel jMSelectedFieldObject;


	private JTable jDETFields;
	private DefaultTableModel jDETMField;
	
	
	
	private JButton jbOK = new JButton("确定");
	private JButton jbClose = new JButton("取消");
	
	private JButton jbDataObjectAdd = new JButton("添加");
	private JButton jbDataObjectRemove = new JButton("删除");
	
//	private JButton jbFieldObjectAdd = new JButton("添加");
//	private JButton jbFieldObjectRemove = new JButton("删除");
	private TransactionType type;

	private List<DataObject> allDO = new ArrayList<DataObject>();
	private List<DataObject> selectedDO = new ArrayList<DataObject>();
	
//	private List<FieldObject> availableFO = new ArrayList<FieldObject>();
//	private List<FieldObject> selectedFO = new ArrayList<FieldObject>();

	public TransactionObjectEditor(TransactionObjectList transactionObjectList, ResultFrame parentForm, TransactionType t) {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setPreferredSize(new Dimension(950	, 400));
		this.setSize(900, 450);
		parent = transactionObjectList;
		resultFrame = parentForm;
		type = t;
		this.setTitle(type.name()+"修改器");
		this.setLayout(new BorderLayout(5,5));
		JPanel up =new JPanel();
		this.add(up,BorderLayout.NORTH);
		up.add(jLName);
		up.add(jFName);
		jFName.setPreferredSize(new Dimension(200, 30));
		jFName.setSize(200, 30);
		allDO = new ArrayList<DataObject>();
		allDO.addAll(resultFrame.getCm().getDataObjectList(DataType.ILF));
		allDO.addAll(resultFrame.getCm().getDataObjectList(DataType.EIF));
		
		jMDataObject =  new DefaultComboBoxModel(allDO.toArray());  //数据模型
		jLDataObject = new JList();
		jLDataObject.setSize(new Dimension(100, 200));
		jLDataObject.setPreferredSize(new Dimension(100, 200));
		jLDataObject.setModel(jMDataObject);
		
		jMSelectedDataObject =  new DefaultComboBoxModel();  //数据模型
		jLSelectedDataObject = new JList();
		jLSelectedDataObject.setSize(new Dimension(100, 200));
		jLSelectedDataObject.setPreferredSize(new Dimension(100, 200));
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
					//availableFO.clear();
					//for(DataObject dod:selectedDO){
						//availableFO.addAll(dod.getFieldList());
					//}
//					jMFieldObject =  new DefaultComboBoxModel(availableFO.toArray());  //数据模型
//					jLFieldObject.setModel(jMFieldObject);
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
					//availableFO.clear();
					//for(DataObject dod:selectedDO){
						//availableFO.addAll(dod.getFieldList());
					//}
//					jMFieldObject =  new DefaultComboBoxModel(availableFO.toArray());  //数据模型
//					jLFieldObject.setModel(jMFieldObject);
//					List<FieldObject> toDelete = new ArrayList<FieldObject>();
//					for(FieldObject f:selectedFO){
//						if(!availableFO.contains(f)){
//							toDelete.add(f);
//						}
//					}
//					for(FieldObject f:toDelete){
//						selectedFO.remove(f);	
//					}
//					jMSelectedFieldObject =  new DefaultComboBoxModel(selectedFO.toArray());  //数据模型
//					jLSelectedFieldObject.setModel(jMSelectedFieldObject);
				}				
			}
		});
		jpDataObject = new JPanel(new BorderLayout(5,5));
		jpDataObject.add(new JLabel("FTR选择"), BorderLayout.NORTH);
		jpDataObject.add(jLDataObject,BorderLayout.WEST);
		JPanel FTRButtons = new JPanel(new GridLayout(2,1,25,25));
		FTRButtons.add(jbDataObjectAdd,0);
		FTRButtons.add(jbDataObjectRemove,1);
		jpDataObject.add(FTRButtons);
		jpDataObject.add(jLSelectedDataObject,BorderLayout.EAST);
		
		this.add(jpDataObject,BorderLayout.WEST);
		

		jDETMField = new DefaultTableModel(new String[]{"DET识别" },1);
		jDETFields= new JTable(jDETMField);
		jDETFields.setFillsViewportHeight(true); 
		JScrollPane scrollPane2 = new JScrollPane(jDETFields);  
		addListetener(jDETFields,jDETMField);
		this.add(scrollPane2,BorderLayout.EAST);
//		jMFieldObject =  new DefaultComboBoxModel(availableFO.toArray());  //数据模型
//		jLFieldObject = new JList();
//		jLFieldObject.setSize(new Dimension(200, 300));
//		jLFieldObject.setPreferredSize(new Dimension(200, 300));
//		jLFieldObject.setModel(jMFieldObject);
//		
//		jMSelectedFieldObject =  new DefaultComboBoxModel();  //数据模型
//		jLSelectedFieldObject = new JList();
//		jLSelectedFieldObject.setSize(new Dimension(200, 300));
//		jLSelectedFieldObject.setPreferredSize(new Dimension(200, 300));
//		jLSelectedFieldObject.setModel(jMSelectedFieldObject);
		
//		jbFieldObjectAdd = new JButton("添加");
//		jbFieldObjectAdd.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				if(jLFieldObject.getSelectedIndex()>=0){
//					Object sel = jLFieldObject.getSelectedValue();
//					if(!selectedFO.contains(sel))
//						selectedFO.add( (FieldObject) sel);	
//					jMSelectedFieldObject =  new DefaultComboBoxModel(selectedFO.toArray());  //数据模型
//					jLSelectedFieldObject.setModel(jMSelectedFieldObject);
//					
//				}				
//			}
//		});
//		jbFieldObjectRemove = new JButton("删除");
//		jbFieldObjectRemove.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				if(jLSelectedFieldObject.getSelectedIndex()>=0){
//					Object sel = jLSelectedFieldObject.getSelectedValue();
//					selectedFO.remove(sel);	
//					jMSelectedFieldObject =  new DefaultComboBoxModel(selectedFO.toArray());  //数据模型
//					jLSelectedFieldObject.setModel(jMSelectedFieldObject);
//					
//				}				
//			}
//		});
//		jpFieldObject = new JPanel();
//
//		jpFieldObject.add(jLFieldObject);
//		jpFieldObject.add(jbFieldObjectAdd);
//		jpFieldObject.add(jbFieldObjectRemove);
//		jpFieldObject.add(jLSelectedFieldObject);
//		
//		this.add(jpFieldObject);
		JPanel down =new JPanel();
		this.add(down,BorderLayout.SOUTH);
		down.add(jbOK);
		down.add(jbClose); 
		jbOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(editType == 0){
					if(jFName.getText()!=null&&jFName.getText().length()>0){
						TransactionObject n = new TransactionObject(jFName.getText(), type);
						
						for(DataObject dod:selectedDO){
							FileReferencedElement a = new FileReferencedElement(dod, n);
							n.getFtrList().add(a);
						}
						
						for(int i = 0;i<jDETMField.getRowCount()-1;i++){
							String fieldName = (String) jDETMField.getValueAt(i, 0);
							n.getDetList().add(fieldName);
						}
						parent.addTransaction(n);
						TransactionObjectEditor.this.dispose();
					}
				}else{
					if(jFName.getText()!=null&&jFName.getText().length()>0){
						TransactionObject n = ton;
						n.setName(jFName.getText());
						n.getFtrList().clear();
						for(DataObject dod:selectedDO){
							FileReferencedElement a = new FileReferencedElement(dod, n);
							n.getFtrList().add(a);
						}
						n.getDetList().clear();
						for(int i = 0;i<jDETMField.getRowCount()-1;i++){
							String fieldName = (String) jDETMField.getValueAt(i, 0);
							n.getDetList().add(fieldName);
						}
						parent.updateData(n);
						TransactionObjectEditor.this.dispose();
					}

						
				}
			}
		});
		jbClose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TransactionObjectEditor.this.dispose();
			}
		});
		this.pack();
	}
	
	public TransactionObjectEditor(TransactionObjectList transactionObjectList, ResultFrame parentForm, TransactionType t,TransactionObject n) {
		this(transactionObjectList, parentForm, t);
		ton=n;
		
		jFName.setText(ton.getName());
//		List<String> retList = ton.getRetList();
//		jRETMField.removeRow(0);
//		for(String ret:retList)
//			jRETMField.addRow(new String[]{ret});
//		jRETMField.addRow(new String[]{""});
		List<FileReferencedElement>ftrs = ton.getFtrList();
		for(FileReferencedElement ftr:ftrs){
			selectedDO.add(ftr.getReferencedFile());
		}
		jMSelectedDataObject =  new DefaultComboBoxModel(selectedDO.toArray());  //数据模型
		jLSelectedDataObject.setModel(jMSelectedDataObject);
		jDETMField.removeRow(0);
		List<String> detList = ton.getDetList();
		for(String det:detList)
			jDETMField.addRow(new String[]{det});
		jDETMField.addRow(new String[]{""});
		editType = 1;
	}

	public TransactionType getType() {
		return type;
	}

	public void setType(TransactionType type) {
		this.type = type;
	}
	
	private void addListetener(final JTable jTable,final DefaultTableModel jDefaultTableModel) {
		jTable.addMouseListener(new MouseListener() {
			
			
			@Override
			public void mouseClicked(MouseEvent e) {
			    int rowI = jTable.rowAtPoint(e.getPoint());// 得到table的行号
			    System.out.println(rowI);
			    if (rowI > -1){
			    	if (e.getButton() == MouseEvent.BUTTON3){
			    		if(rowI!=jDefaultTableModel.getRowCount()-1){
				    		jDefaultTableModel.removeRow(rowI);
				    	}
			    	}else{
				    	if(rowI==jDefaultTableModel.getRowCount()-1){
				    		jDefaultTableModel.addRow(new String[]{"",""});
				    	}
			    		
			    	}
			    }
			}

			@Override
			public void mousePressed(MouseEvent e) {
				
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				
				
			}
		});
	}

	
}
