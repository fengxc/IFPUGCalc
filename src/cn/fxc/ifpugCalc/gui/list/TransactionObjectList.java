package cn.fxc.ifpugCalc.gui.list;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
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
import cn.fxc.ifpugCalc.gui.editor.TransactionObjectEditor;
import cn.fxc.ifpugCalc.model.TransactionObject;
import cn.fxc.ifpugCalc.model.TransactionType;

public class TransactionObjectList extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7364556594927682193L;
	ResultFrame parentForm;
	JList jLDataObjectList;
	
	ListModel jListModel;
	JPanel jplist = new JPanel();
	JPanel jpbutton = new JPanel();
	JButton jbAdd = new JButton("新增");
	JButton jbEdit = new JButton("修改");
	JButton jbRemove = new JButton("删除");
	JButton jbClose = new JButton("关闭") ;
	private TransactionType type;
	List<TransactionObject> transactionObjectList;
	public TransactionObjectList(ResultFrame r, TransactionType d){
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		//this.setPreferredSize(new Dimension(800, 600));
		this.setSize(800, 600);
		type = d;
		this.setTitle(type.name()+"管理器");
		parentForm = r;
		transactionObjectList = parentForm.getCm().getTransactionObjectList(type);

		initial();
	}

	private void initial() {
		this.setLayout(new BorderLayout());
		jplist.setSize(new Dimension(300,300));
		jplist.setPreferredSize(new Dimension(300,300));
		this.add(jplist, BorderLayout.WEST);
		this.add(jpbutton, BorderLayout.EAST);
		jpbutton.setLayout(new GridLayout(4,1,25,25));
		jpbutton.add(jbAdd);
		jpbutton.add(jbEdit);
		jpbutton.add(jbRemove);
		jpbutton.add(jbClose);
		jbAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TransactionObjectEditor i = new TransactionObjectEditor(TransactionObjectList.this,parentForm, type);
				i.setVisible(true);

			}
		});
		jbEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(jLDataObjectList.getSelectedIndex()>=0){
					int index = jLDataObjectList.getSelectedIndex();
					TransactionObjectEditor i = new TransactionObjectEditor(TransactionObjectList.this,parentForm, type,(TransactionObject)jListModel.getElementAt(index));
					i.setVisible(true);
				}

			}
		});
		jbRemove.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(jLDataObjectList.getSelectedIndex());
				if(jLDataObjectList.getSelectedIndex()>=0){
					int index = jLDataObjectList.getSelectedIndex();
					TransactionObjectList.this.delete(index);					
				}
				
			}
		});
		jbClose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TransactionObjectList.this.dispose();
			}
		});
		jListModel =  new DefaultComboBoxModel(transactionObjectList.toArray());  //数据模型
		jLDataObjectList = new JList();
		jLDataObjectList.setSize(new Dimension(300, 300));
		jLDataObjectList.setPreferredSize(new Dimension(300, 300));
		jLDataObjectList.setModel(jListModel);
		jplist.add(jLDataObjectList);
		this.pack();
	}

	protected void delete(int index) {
		parentForm.getCm().removeTransactionObject(type, index);
		jListModel =  new DefaultComboBoxModel(transactionObjectList.toArray());  //数据模型
		jLDataObjectList.setModel(jListModel);
		parentForm.getFPResult();
	}

	public void addTransaction(TransactionObject n) {
		parentForm.getCm().addTransactionObject(n);
		jListModel =  new DefaultComboBoxModel(transactionObjectList.toArray());  //数据模型
		jLDataObjectList.setModel(jListModel);
		parentForm.getFPResult();
	}

	public void updateData(TransactionObject n) {
		jListModel =  new DefaultComboBoxModel(transactionObjectList.toArray());  //数据模型
		jLDataObjectList.setModel(jListModel);		
		parentForm.getFPResult();
	}

	
	
	
}
