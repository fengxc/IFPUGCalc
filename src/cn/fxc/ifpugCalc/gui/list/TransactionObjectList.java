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
	JButton jbAdd = new JButton("����");
	JButton jbRemove = new JButton("ɾ��");
	JButton jbClose = new JButton("�ر�") ;
	private TransactionType type;
	List<TransactionObject> transactionObjectList;
	public TransactionObjectList(ResultFrame r, TransactionType d){
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setPreferredSize(new Dimension(800, 600));
		this.setSize(800, 600);
		this.setTitle("XX������");
		parentForm = r;
		type = d;
		transactionObjectList = parentForm.getCm().getTransactionObjectList(type);

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
				TransactionObjectEditor i = new TransactionObjectEditor(TransactionObjectList.this,parentForm, type);
				i.setVisible(true);

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
		jListModel =  new DefaultComboBoxModel(transactionObjectList.toArray());  //����ģ��
		jLDataObjectList = new JList();
		jLDataObjectList.setSize(new Dimension(400, 600));
		jLDataObjectList.setPreferredSize(new Dimension(400, 600));
		jLDataObjectList.setModel(jListModel);
		jplist.add(jLDataObjectList);
	}

	protected void delete(int index) {
		parentForm.getCm().removeTransactionObject(type, index);
		jListModel =  new DefaultComboBoxModel(transactionObjectList.toArray());  //����ģ��
		jLDataObjectList.setModel(jListModel);
	}

	public void addTransaction(TransactionObject n) {
		parentForm.getCm().addTransactionObject(n);
		jListModel =  new DefaultComboBoxModel(transactionObjectList.toArray());  //����ģ��
		jLDataObjectList.setModel(jListModel);
	}

	
	
	
}