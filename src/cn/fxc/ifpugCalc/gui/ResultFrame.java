package cn.fxc.ifpugCalc.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import cn.fxc.ifpugCalc.gui.list.DataObjectList;
import cn.fxc.ifpugCalc.gui.list.TransactionObjectList;
import cn.fxc.ifpugCalc.manager.CalcManager;
import cn.fxc.ifpugCalc.model.DataObject;
import cn.fxc.ifpugCalc.model.DataType;
import cn.fxc.ifpugCalc.model.TransactionObject;
import cn.fxc.ifpugCalc.model.TransactionType;

public class ResultFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6778490664878620755L;
	private JPanel jpOverview;
	private JMenuBar jmOperation;
	private JLabel jLResult;
	private CalcManager cm;
	int [][]UFPWeight = {{7,10,15},{5,7,10},{3,4,6},{4,5,7},{3,4,6}};
	public ResultFrame(){
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("IFPUG计算工具");
		jmOperation = getOperationMenuBar();
		this.setJMenuBar(jmOperation);
		this.cm = new CalcManager();
		
		jpOverview = new JPanel();
		jLResult = new JLabel("");
		jpOverview.add(jLResult);
		this.add(jpOverview);
		getFPResult();
		this.setPreferredSize(new Dimension(450, 250));
		this.setSize(450,250);
	}


	
	public void getFPResult() {
		int [][]complexityNumber = new int[5][3];
		
		for(DataObject dod:cm.getDataObjectList(DataType.ILF)){
			complexityNumber[0][dod.getComplexity().ordinal()]++;
		}
		for(DataObject dod:cm.getDataObjectList(DataType.EIF)){
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
		String result = "<html><body><table><tr><td>估算\\复杂性</td><td>低</td><td>中</td><td>高</td></tr>";
		result+="<tr><td>ILF</td><td>"+complexityNumber[0][0]+"</td><td>"+complexityNumber[0][1]+"</td><td>"+complexityNumber[0][2]+"</td></tr>";
		result+="<tr><td>EIF</td><td>"+complexityNumber[1][0]+"</td><td>"+complexityNumber[1][1]+"</td><td>"+complexityNumber[1][2]+"</td></tr>";
		result+="<tr><td>EI</td><td>"+complexityNumber[2][0]+"</td><td>"+complexityNumber[2][1]+"</td><td>"+complexityNumber[2][2]+"</td></tr>";
		result+="<tr><td>EO</td><td>"+complexityNumber[3][0]+"</td><td>"+complexityNumber[3][1]+"</td><td>"+complexityNumber[3][2]+"</td></tr>";
		result+="<tr><td>EQ</td><td>"+complexityNumber[4][0]+"</td><td>"+complexityNumber[4][1]+"</td><td>"+complexityNumber[4][2]+"</td></tr></table>";
		result+="<p>UFP为"+UFP+",VAF为"+fVAF+",FP为"+FP+"</p></body></html>";
		jLResult.setText(result);

	}

	private JMenuBar getOperationMenuBar() {
		JMenuBar jm = new JMenuBar();
		
		JMenu file = new JMenu("文件");
		JMenuItem open = new JMenuItem("打开");
		JMenuItem save = new JMenuItem("保存");
		file.add(open);
		open.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser fileChooser = new JFileChooser();

		        // 设置默认显示的文件夹为当前文件夹
		        fileChooser.setCurrentDirectory(new File("."));

		        // 设置文件选择的模式（只选文件、只选文件夹、文件和文件均可选）
		        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		        // 设置是否允许多选
//		        fileChooser.setMultiSelectionEnabled(true);

		        // 添加可用的文件过滤器（FileNameExtensionFilter 的第一个参数是描述, 后面是需要过滤的文件扩展名 可变参数）
		        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("*.savedata", "savedata"));
		        // 设置默认使用的文件过滤器
		        //fileChooser.setFileFilter(new FileNameExtensionFilter("image(*.jpg, *.png, *.gif)", "jpg", "png", "gif"));

		        // 打开文件选择框（线程将被阻塞, 直到选择框被关闭）
		        int result = fileChooser.showOpenDialog(ResultFrame.this);

		        if (result == JFileChooser.APPROVE_OPTION) {
		            // 如果点击了"确定", 则获取选择的文件路径
		        	try {
			            File file = fileChooser.getSelectedFile();
			            if (!file.exists()) {	//文件不存在则创建文件
							return;
			    		}
			            FileInputStream is = new FileInputStream(file);
			            ObjectInputStream ois = new ObjectInputStream(is);
			            CalcManager ncm = (CalcManager) ois.readObject();
			            ois.close();
			            cm = ncm;
			            getFPResult();
		        	} catch (Exception e1) {
		            	e1.printStackTrace();
		            }
		            // 如果允许选择多个文件, 则通过下面方法获取选择的所有文件
		            // File[] files = fileChooser.getSelectedFiles();

		            //msgTextArea.append("打开文件: " + file.getAbsolutePath() + "\n\n");
		        }
		
			}
		});
		file.add(save);
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setSelectedFile(new File("1.savedata"));

		        // 设置默认显示的文件夹为当前文件夹
		        fileChooser.setCurrentDirectory(new File("."));

		        // 设置文件选择的模式（只选文件、只选文件夹、文件和文件均可选）
		        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		        // 设置是否允许多选
//		        fileChooser.setMultiSelectionEnabled(true);

		        // 添加可用的文件过滤器（FileNameExtensionFilter 的第一个参数是描述, 后面是需要过滤的文件扩展名 可变参数）
		        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("*.savedata", "savedata"));
		        // 设置默认使用的文件过滤器
		        //fileChooser.setFileFilter(new FileNameExtensionFilter("image(*.jpg, *.png, *.gif)", "jpg", "png", "gif"));

		        // 打开文件选择框（线程将被阻塞, 直到选择框被关闭）
		        int result = fileChooser.showSaveDialog(ResultFrame.this);

		        if (result == JFileChooser.APPROVE_OPTION) {
		            // 如果点击了"确定", 则获取选择的文件路径
		        	try {
			            File file = fileChooser.getSelectedFile();
				         if (file.getAbsolutePath().toUpperCase().endsWith("savedata".toUpperCase()))
				         {
//			        	    // 如果文件是以选定扩展名结束的，则使用原名
//			        	  
			        	  } else {
//			        	    // 否则加上选定的扩展名
//			        		  file.renameTo(new File(filepath+"."+ext));
			        		  file = new File(file.getPath()+"."+"savedata");
			        	  }
			            if (!file.exists()) {	//文件不存在则创建文件
							file.createNewFile();
			    		}
			            FileOutputStream os = new FileOutputStream(file);
			            ObjectOutputStream oos = new ObjectOutputStream(os);
			            oos.writeObject(cm);
			            oos.flush();
			            oos.close();
		        	} catch (IOException e1) {
		            	e1.printStackTrace();
		            }
		            // 如果允许选择多个文件, 则通过下面方法获取选择的所有文件
		            // File[] files = fileChooser.getSelectedFiles();

		            //msgTextArea.append("打开文件: " + file.getAbsolutePath() + "\n\n");
		        }
		
			}
		});
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
		JMenuItem eif = new JMenuItem("EIF管理");
		eif.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DataObjectList i = new DataObjectList(ResultFrame.this, DataType.EIF);
				i.setVisible(true);

			}
		});
		data.add(ilf);
		data.add(eif);
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
		r.setVisible(true);
		//r.testData(r);
	}
	
	

/*	private void testData(ResultFrame r) {
		DataObject do1 = new DataObject("cat", DataType.ILF);
		do1.getFieldList().add(new FieldObject("age", do1));
		DataObject do2 = new DataObject("catFood", DataType.EIF);
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
