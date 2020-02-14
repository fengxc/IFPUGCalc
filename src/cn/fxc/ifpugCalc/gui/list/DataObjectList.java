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
	JButton jbAdd = new JButton("����");
	JButton jbRemove = new JButton("ɾ��");
	JButton jbClose = new JButton("�ر�") ;
	private DataType type;
	List<DataObject> dataObjectList;
	public DataObjectList(ResultFrame r, DataType d){
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setPreferredSize(new Dimension(800, 600));
		this.setSize(800, 600);
		this.setTitle("XX������");
		parent = r;
		type = d;
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
		jListModel =  new DefaultComboBoxModel(dataObjectList.toArray());  //����ģ��
		jLDataObjectList = new JList();
		jLDataObjectList.setSize(new Dimension(400, 600));
		jLDataObjectList.setPreferredSize(new Dimension(400, 600));
		jLDataObjectList.setModel(jListModel);
		jplist.add(jLDataObjectList);
	}

	protected void delete(int index) {
		parent.getCm().removeDataObject(type, index);
		jListModel =  new DefaultComboBoxModel(dataObjectList.toArray());  //����ģ��
		jLDataObjectList.setModel(jListModel);
	}

	public void addData(DataObject n) {
		parent.getCm().addDataObject(n);
		jListModel =  new DefaultComboBoxModel(dataObjectList.toArray());  //����ģ��
		jLDataObjectList.setModel(jListModel);
	}

	
	
	
}