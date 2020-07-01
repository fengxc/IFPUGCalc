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
		this.setTitle("IFPUG���㹤��");
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
		String result = "<html><body><table><tr><td>����\\������</td><td>��</td><td>��</td><td>��</td></tr>";
		result+="<tr><td>ILF</td><td>"+complexityNumber[0][0]+"</td><td>"+complexityNumber[0][1]+"</td><td>"+complexityNumber[0][2]+"</td></tr>";
		result+="<tr><td>EIF</td><td>"+complexityNumber[1][0]+"</td><td>"+complexityNumber[1][1]+"</td><td>"+complexityNumber[1][2]+"</td></tr>";
		result+="<tr><td>EI</td><td>"+complexityNumber[2][0]+"</td><td>"+complexityNumber[2][1]+"</td><td>"+complexityNumber[2][2]+"</td></tr>";
		result+="<tr><td>EO</td><td>"+complexityNumber[3][0]+"</td><td>"+complexityNumber[3][1]+"</td><td>"+complexityNumber[3][2]+"</td></tr>";
		result+="<tr><td>EQ</td><td>"+complexityNumber[4][0]+"</td><td>"+complexityNumber[4][1]+"</td><td>"+complexityNumber[4][2]+"</td></tr></table>";
		result+="<p>UFPΪ"+UFP+",VAFΪ"+fVAF+",FPΪ"+FP+"</p></body></html>";
		jLResult.setText(result);

	}

	private JMenuBar getOperationMenuBar() {
		JMenuBar jm = new JMenuBar();
		
		JMenu file = new JMenu("�ļ�");
		JMenuItem open = new JMenuItem("��");
		JMenuItem save = new JMenuItem("����");
		file.add(open);
		open.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser fileChooser = new JFileChooser();

		        // ����Ĭ����ʾ���ļ���Ϊ��ǰ�ļ���
		        fileChooser.setCurrentDirectory(new File("."));

		        // �����ļ�ѡ���ģʽ��ֻѡ�ļ���ֻѡ�ļ��С��ļ����ļ�����ѡ��
		        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		        // �����Ƿ������ѡ
//		        fileChooser.setMultiSelectionEnabled(true);

		        // ��ӿ��õ��ļ���������FileNameExtensionFilter �ĵ�һ������������, ��������Ҫ���˵��ļ���չ�� �ɱ������
		        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("*.savedata", "savedata"));
		        // ����Ĭ��ʹ�õ��ļ�������
		        //fileChooser.setFileFilter(new FileNameExtensionFilter("image(*.jpg, *.png, *.gif)", "jpg", "png", "gif"));

		        // ���ļ�ѡ����߳̽�������, ֱ��ѡ��򱻹رգ�
		        int result = fileChooser.showOpenDialog(ResultFrame.this);

		        if (result == JFileChooser.APPROVE_OPTION) {
		            // ��������"ȷ��", ���ȡѡ����ļ�·��
		        	try {
			            File file = fileChooser.getSelectedFile();
			            if (!file.exists()) {	//�ļ��������򴴽��ļ�
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
		            // �������ѡ�����ļ�, ��ͨ�����淽����ȡѡ��������ļ�
		            // File[] files = fileChooser.getSelectedFiles();

		            //msgTextArea.append("���ļ�: " + file.getAbsolutePath() + "\n\n");
		        }
		
			}
		});
		file.add(save);
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setSelectedFile(new File("1.savedata"));

		        // ����Ĭ����ʾ���ļ���Ϊ��ǰ�ļ���
		        fileChooser.setCurrentDirectory(new File("."));

		        // �����ļ�ѡ���ģʽ��ֻѡ�ļ���ֻѡ�ļ��С��ļ����ļ�����ѡ��
		        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		        // �����Ƿ������ѡ
//		        fileChooser.setMultiSelectionEnabled(true);

		        // ��ӿ��õ��ļ���������FileNameExtensionFilter �ĵ�һ������������, ��������Ҫ���˵��ļ���չ�� �ɱ������
		        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("*.savedata", "savedata"));
		        // ����Ĭ��ʹ�õ��ļ�������
		        //fileChooser.setFileFilter(new FileNameExtensionFilter("image(*.jpg, *.png, *.gif)", "jpg", "png", "gif"));

		        // ���ļ�ѡ����߳̽�������, ֱ��ѡ��򱻹رգ�
		        int result = fileChooser.showSaveDialog(ResultFrame.this);

		        if (result == JFileChooser.APPROVE_OPTION) {
		            // ��������"ȷ��", ���ȡѡ����ļ�·��
		        	try {
			            File file = fileChooser.getSelectedFile();
				         if (file.getAbsolutePath().toUpperCase().endsWith("savedata".toUpperCase()))
				         {
//			        	    // ����ļ�����ѡ����չ�������ģ���ʹ��ԭ��
//			        	  
			        	  } else {
//			        	    // �������ѡ������չ��
//			        		  file.renameTo(new File(filepath+"."+ext));
			        		  file = new File(file.getPath()+"."+"savedata");
			        	  }
			            if (!file.exists()) {	//�ļ��������򴴽��ļ�
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
		            // �������ѡ�����ļ�, ��ͨ�����淽����ȡѡ��������ļ�
		            // File[] files = fileChooser.getSelectedFiles();

		            //msgTextArea.append("���ļ�: " + file.getAbsolutePath() + "\n\n");
		        }
		
			}
		});
		jm.add(file);
		
		JMenu data = new JMenu("���ݹ���");
		JMenuItem ilf = new JMenuItem("ILF����");
		ilf.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DataObjectList i = new DataObjectList(ResultFrame.this, DataType.ILF);
				i.setVisible(true);

			}
		});
		JMenuItem eif = new JMenuItem("EIF����");
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

		JMenu transaction = new JMenu("�������");
		JMenuItem EI = new JMenuItem("EI����");
		JMenuItem EO = new JMenuItem("EO����");
		JMenuItem EQ = new JMenuItem("EQ����");
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
		
		JMenu vaf = new JMenu("Ӱ������");
		JMenuItem vafEditor = new JMenuItem("VAF������д");
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
