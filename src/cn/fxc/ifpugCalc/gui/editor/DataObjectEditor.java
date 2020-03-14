package cn.fxc.ifpugCalc.gui.editor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import cn.fxc.ifpugCalc.gui.list.DataObjectList;
import cn.fxc.ifpugCalc.model.DataObject;
import cn.fxc.ifpugCalc.model.DataType;
import cn.fxc.ifpugCalc.model.FieldObject;

public class DataObjectEditor extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6741569606317534338L;
	private int editType = 0; //0新增 1修改
	private DataObject don;
	private DataObjectList parent;

	private JLabel jLName = new JLabel("名称");
	private JTextField jFName = new JTextField("");

	private JTable jRETFields;
	private DefaultTableModel jRETMField;
	
	private JTable jDETFields;
	private DefaultTableModel jDETMField;
	
	private JButton jbOK = new JButton("确定");
	private JButton jbClose = new JButton("取消");
	private DataType type;

	public DataObjectEditor(DataObjectList dataObjectList, DataType t) {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		editType = 0;
		parent =dataObjectList;
		type = t;
		this.setTitle(type.name()+"修改器");
		this.setLayout(new BorderLayout(5,5));
		JPanel up =new JPanel();
		this.add(up,BorderLayout.NORTH);
		up.add(jLName);
		up.add(jFName);
		jFName.setPreferredSize(new Dimension(200, 30));
		jFName.setSize(200, 30);
		
		jRETMField = new DefaultTableModel(new String[]{"RET识别" },1);
		jRETFields= new JTable(jRETMField);
		jRETFields.setFillsViewportHeight(true);  
		JScrollPane scrollPane1 = new JScrollPane(jRETFields);  
		addListetener(jRETFields,jRETMField);
		
		jDETMField = new DefaultTableModel(new String[]{"DET识别" },1);
		jDETFields= new JTable(jDETMField);
		jDETFields.setFillsViewportHeight(true); 
		JScrollPane scrollPane2 = new JScrollPane(jDETFields);  
		addListetener(jDETFields,jDETMField);
		
		JPanel down =new JPanel();
		this.add(down,BorderLayout.SOUTH);
		down.add(jbOK);
		down.add(jbClose); 
		this.add(scrollPane1,BorderLayout.WEST);
		this.add(scrollPane2,BorderLayout.EAST);
		jbOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(editType == 0){
					if(jFName.getText()!=null&&jFName.getText().length()>0){
						DataObject n = new DataObject(jFName.getText(), type);
						
						for(int i = 0;i<jRETMField.getRowCount()-1;i++){
							String fieldName = (String) jRETMField.getValueAt(i, 0);
							n.getRetList().add(fieldName);
						}
						
						for(int i = 0;i<jDETMField.getRowCount()-1;i++){
							String fieldName = (String) jDETMField.getValueAt(i, 0);
							n.getDetList().add(fieldName);
						}
						
						parent.addData(n);
						DataObjectEditor.this.dispose();
					}
				}else{
					if(jFName.getText()!=null&&jFName.getText().length()>0){
						DataObject n =don;
						n.setName(jFName.getText());
						n.getRetList().clear();
						for(int i = 0;i<jRETMField.getRowCount()-1;i++){
							String fieldName = (String) jRETMField.getValueAt(i, 0);
							n.getRetList().add(fieldName);
						}
						n.getDetList().clear();
						for(int i = 0;i<jDETMField.getRowCount()-1;i++){
							String fieldName = (String) jDETMField.getValueAt(i, 0);
							n.getDetList().add(fieldName);
						}
						
						parent.updateData(n);
						DataObjectEditor.this.dispose();
					}
				}
			}
		});
		jbClose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DataObjectEditor.this.dispose();
			}
		});
		this.pack();
	}
	
	public DataObjectEditor(DataObjectList dataObjectList, DataType t, DataObject don) {
		this(dataObjectList,  t); 
		this.don = don;
		jFName.setText(don.getName());
		List<String> retList = don.getRetList();
		jRETMField.removeRow(0);
		for(String ret:retList)
			jRETMField.addRow(new String[]{ret});
		jRETMField.addRow(new String[]{""});
		jDETMField.removeRow(0);
		List<String> detList = don.getDetList();
		for(String det:detList)
			jDETMField.addRow(new String[]{det});
		jDETMField.addRow(new String[]{""});
		editType = 1;
	}


	private void addListetener(final JTable jTable,final DefaultTableModel jDefaultTableModel) {
		jTable.addMouseListener(new MouseListener() {
			
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
			    int rowI = jTable.rowAtPoint(e.getPoint());// 得到table的行号
			    System.out.println(rowI);
			    if (rowI > -1){
			    	if(rowI==jDefaultTableModel.getRowCount()-1){
			    		jDefaultTableModel.addRow(new String[]{"",""});
			    	}
			    }
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	public DataType getType() {
		return type;
	}

	public void setType(DataType type) {
		this.type = type;
	}
	
}
