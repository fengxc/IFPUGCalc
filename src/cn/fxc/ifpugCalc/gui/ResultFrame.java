package cn.fxc.ifpugCalc.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import cn.fxc.ifpugCalc.gui.list.DataObjectList;
import cn.fxc.ifpugCalc.gui.list.TransactionObjectList;
import cn.fxc.ifpugCalc.manager.CalcManager;
import cn.fxc.ifpugCalc.model.DataObject;
import cn.fxc.ifpugCalc.model.DataType;
import cn.fxc.ifpugCalc.model.FieldObject;
import cn.fxc.ifpugCalc.model.TransactionObject;
import cn.fxc.ifpugCalc.model.TransactionType;

public class ResultFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6778490664878620755L;
	private JPanel jpOverview;
	private JMenuBar jmOperation;
	private JButton jbCalc;
	private JLabel jLResult;
	private CalcManager cm;
	int [][]UFPWeight = {{3,4,6},{4,5,7},{3,4,6},{7,10,15},{5,7,10}};
	public ResultFrame(){
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("IFPUG计算工具");
		jmOperation = getOperationMenuBar();
		this.setJMenuBar(jmOperation);
		this.cm = new CalcManager();
		
		jpOverview = new JPanel();
		jbCalc = new JButton("Calc!");
		jpOverview.add(jbCalc);
		jbCalc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int [][]complexityNumber = new int[5][3];
				
				for(DataObject dod:cm.getDataObjectList(DataType.ILF)){
					complexityNumber[0][dod.getComplexity().ordinal()]++;
				}
				for(DataObject dod:cm.getDataObjectList(DataType.ELF)){
					complexityNumber[1][dod.getComplexity().ordinal()]++;
				}
				for(TransactionObject dod:cm.getTransactionObjectList(TransactionType.EI)){
					complexityNumber[2][dod.getComplexity().ordinal()]++;
				}
				for(TransactionObject dod:cm.getTransactionObjectList(TransactionType.EO)){
					complexityNumber[3][dod.getComplexity().ordinal()]++;
				}
				for(TransactionObject dod:cm.getTransactionObjectList(TransactionType.EQ)){
					complexityNumber[4][dod.getComplexity().ordinal()]++;
				}
				int UFP=0;
				for(int i=0;i<5;i++){
					for(int j=0;j<3;j++){
						UFP+=complexityNumber[i][j]*UFPWeight[i][j];
					}
				}
				int[] score = getCm().getVAFScore();
				int VAF = 65;
				for(int i=0;i<14;i++){
					VAF += score[i];
				}
				float fVAF = 0.01f  * VAF;
				float FP =   (VAF * UFP)*0.01f ;
				String result = "<html><body><p align=\"center\">估算\\复杂性&emsp;低&emsp;中&emsp;高<br/>";
				result+="&emsp;ILF&emsp;&emsp;"+complexityNumber[0][0]+"&emsp;"+complexityNumber[0][1]+"&emsp;"+complexityNumber[0][2]+"<br/>";
				result+="&emsp;ELF&emsp;&emsp;"+complexityNumber[1][0]+"&emsp;"+complexityNumber[1][1]+"&emsp;"+complexityNumber[1][2]+"<br/>";
				result+="&emsp;EI&emsp;&emsp;"+complexityNumber[2][0]+"&emsp;"+complexityNumber[2][1]+"&emsp;"+complexityNumber[2][2]+"<br/>";
				result+="&emsp;EO&emsp;&emsp;"+complexityNumber[3][0]+"&emsp;"+complexityNumber[3][1]+"&emsp;"+complexityNumber[3][2]+"<br/>";
				result+="&emsp;EQ&emsp;&emsp;"+complexityNumber[4][0]+"&emsp;"+complexityNumber[4][1]+"&emsp;"+complexityNumber[4][2]+"<br/>";
				result+="UFP为"+UFP+",VAF为"+fVAF+",FP为"+FP+"</p></body></html>";
				jLResult.setText(result);
			}
		});
		jLResult = new JLabel("Ready");
		jpOverview.add(jLResult);
		this.add(jpOverview);

		this.setPreferredSize(new Dimension(450, 250));
		this.setSize(450,250);
	}



	private JMenuBar getOperationMenuBar() {
		JMenuBar jm = new JMenuBar();
		
		JMenu file = new JMenu("文件");
		JMenuItem open = new JMenuItem("打开");
		JMenuItem save = new JMenuItem("保存");
		file.add(open);
		file.add(save);
		jm.add(file);
		
		JMenu data = new JMenu("数据管理");
		JMenuItem ilf = new JMenuItem("ILF管理");
		ilf.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DataObjectList i = new DataObjectList(ResultFrame.this, DataType.ILF);
				i.setVisible(true);

			}
		});
		JMenuItem elf = new JMenuItem("ELF管理");
		elf.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DataObjectList i = new DataObjectList(ResultFrame.this, DataType.ELF);
				i.setVisible(true);

			}
		});
		data.add(ilf);
		data.add(elf);
		jm.add(data);

		JMenu transaction = new JMenu("事务管理");
		JMenuItem EI = new JMenuItem("EI管理");
		JMenuItem EO = new JMenuItem("EO管理");
		JMenuItem EQ = new JMenuItem("EQ管理");
		EI.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TransactionObjectList i = new TransactionObjectList(ResultFrame.this, TransactionType.EI);
				i.setVisible(true);

			}
		});
		EO.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TransactionObjectList i = new TransactionObjectList(ResultFrame.this, TransactionType.EO);
				i.setVisible(true);
			}
		});
		EQ.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TransactionObjectList i = new TransactionObjectList(ResultFrame.this, TransactionType.EQ);
				i.setVisible(true);
			}
		});
		transaction.add(EI);
		transaction.add(EO);
		transaction.add(EQ);
		jm.add(transaction);
		
		JMenu vaf = new JMenu("影响因子");
		JMenuItem vafEditor = new JMenuItem("VAF参数填写");
		vaf.add(vafEditor);
		vafEditor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VAFInputer v = new VAFInputer(ResultFrame.this);
				v.setVisible(true);
				
			}
		});
		jm.add(vaf);
		
		return jm;
	}



	public static void main(String[] args) {
		ResultFrame r = new ResultFrame();
		//r.testData(r);
	}
	
	

/*	private void testData(ResultFrame r) {
		DataObject do1 = new DataObject("cat", DataType.ILF);
		do1.getFieldList().add(new FieldObject("age", do1));
		DataObject do2 = new DataObject("catFood", DataType.ELF);
		do2.getFieldList().add(new FieldObject("productedDate", do2));

		r.getCm().addDataObject(do1);
		r.getCm().addDataObject(do2);
	}*/



	public CalcManager getCm() {
		return cm;
	}



	public void setCm(CalcManager cm) {
		this.cm = cm;
	}
}
