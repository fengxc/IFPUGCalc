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
package cn.fxc.ifpugCalc.gui.editor;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
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

	private DataObjectList parent;

	private JLabel jLName = new JLabel("名称");
	private JTextField jFName = new JTextField("");
	
	private JTable jTFields;
	private DefaultTableModel jDTMField;
	
	private JButton jbOK = new JButton("确定");
	private JButton jbClose = new JButton("取消");
	private DataType type;

	public DataObjectEditor(DataObjectList dataObjectList, DataType t) {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setPreferredSize(new Dimension(480,540));
		this.setSize(480, 540);
		parent =dataObjectList;
		type = t;
		this.setTitle(type.name()+"修改器");
		this.setLayout(new FlowLayout(FlowLayout.LEFT,5,5));
		this.add(jLName);
		this.add(jFName);
		jFName.setPreferredSize(new Dimension(200, 30));
		jFName.setSize(200, 30);
		jDTMField = new DefaultTableModel(new String[]{"字段名","所属RET"},1);
		jTFields= new JTable(jDTMField);
		JScrollPane scrollPane = new JScrollPane(jTFields);  
		jTFields.setFillsViewportHeight(true);  
		this.add(scrollPane);
		jTFields.addMouseListener(new MouseListener() {
			
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
			    int rowI = jTFields.rowAtPoint(e.getPoint());// 得到table的行号
			    System.out.println(rowI);
			    if (rowI > -1){
			    	if(rowI==jDTMField.getRowCount()-1){
			    		jDTMField.addRow(new String[]{"",""});
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
		this.add(jbOK);
		this.add(jbClose);
		jbOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(jFName.getText()!=null&&jFName.getText().length()>0){
					DataObject n = new DataObject(jFName.getText(), type);
					
					for(int i = 0;i<jDTMField.getRowCount()-1;i++){
						String fieldName = (String) jDTMField.getValueAt(i, 0);
						String RETName = (String) jDTMField.getValueAt(i, 1);
						System.out.println(fieldName+":"+RETName);
						FieldObject f = new FieldObject(fieldName, n);
						f.setRETName(RETName);
						n.getFieldList().add(f);
					}
					
					parent.addData(n);
					DataObjectEditor.this.dispose();
				}
			}
		});
		jbClose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DataObjectEditor.this.dispose();
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
