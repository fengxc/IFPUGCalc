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
package cn.fxc.ifpugCalc.gui.list;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListModel;

import cn.fxc.ifpugCalc.gui.ResultFrame;
import cn.fxc.ifpugCalc.gui.editor.DataObjectEditor;
import cn.fxc.ifpugCalc.model.DataObject;
import cn.fxc.ifpugCalc.model.DataType;

public class DataObjectList extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3964016853525912568L;
	ResultFrame parent;
	JList jLDataObjectList;
	
	ListModel jListModel;
	JPanel jplist = new JPanel();
	JPanel jpbutton = new JPanel();
	JButton jbAdd = new JButton("新增");
	JButton jbRemove = new JButton("删除");
	JButton jbClose = new JButton("关闭") ;
	private DataType type;
	List<DataObject> dataObjectList;
	public DataObjectList(ResultFrame r, DataType d){
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setPreferredSize(new Dimension(800, 600));
		this.setSize(800, 600);
		type = d;
		this.setTitle(type.name()+"管理器");
		parent = r;
		dataObjectList = parent.getCm().getDataObjectList(type);

		initial();
	}

	private void initial() {
		this.setLayout(new BorderLayout());
		jplist.setSize(new Dimension(400, 600));
		jplist.setPreferredSize(new Dimension(400, 600));
		this.add(jplist, BorderLayout.WEST);
		this.add(jpbutton, BorderLayout.EAST);
		jpbutton.setLayout(new FlowLayout(FlowLayout.LEFT,5,5));
		jpbutton.add(jbAdd);
		jpbutton.add(jbRemove);
		jpbutton.add(jbClose);
		jbAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DataObjectEditor i = new DataObjectEditor(DataObjectList.this, type);
				i.setVisible(true);

			}
		});
		jbRemove.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(jLDataObjectList.getSelectedIndex());
				if(jLDataObjectList.getSelectedIndex()>=0){
					int index = jLDataObjectList.getSelectedIndex();
					DataObjectList.this.delete(index);					
				}
				
			}
		});
		jbClose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DataObjectList.this.dispose();
			}
		});
		jListModel =  new DefaultComboBoxModel(dataObjectList.toArray());  //数据模型
		jLDataObjectList = new JList();
		jLDataObjectList.setSize(new Dimension(400, 600));
		jLDataObjectList.setPreferredSize(new Dimension(400, 600));
		jLDataObjectList.setModel(jListModel);
		jplist.add(jLDataObjectList);
	}

	protected void delete(int index) {
		parent.getCm().removeDataObject(type, index);
		jListModel =  new DefaultComboBoxModel(dataObjectList.toArray());  //数据模型
		jLDataObjectList.setModel(jListModel);
	}

	public void addData(DataObject n) {
		parent.getCm().addDataObject(n);
		jListModel =  new DefaultComboBoxModel(dataObjectList.toArray());  //数据模型
		jLDataObjectList.setModel(jListModel);
	}

	
	
	
}
