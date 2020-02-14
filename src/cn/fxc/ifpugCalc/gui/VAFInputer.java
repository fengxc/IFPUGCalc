package cn.fxc.ifpugCalc.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class VAFInputer extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4784285492920275822L;
	JPanel jpScoreDashboardPanel;
	private JButton jbOK = new JButton("确定");
	private JButton jbClose = new JButton("关闭");
	private JLabel[] jLVAFDestLabelArray = new JLabel[14];
	private JSpinner[] jSPVAFScoreArray = new JSpinner[14];
	private SpinnerNumberModel[] jpM = new SpinnerNumberModel[14];
	private String[] info = {"1．数据通讯 （Data Communications） ",
	"2．分布式数据处理 （Distributed Data Processing） ",
	"3．性能 （Performance）  ",
	"4．使用强度高的配置 （Heavily Used Configuration） ", 
	"5．交易速度 （Transaction Rate）  ",
	"6．在线数据输入 （Online Data Entry） ", 
	"7．最终用户的效率 （End-User Efficiency） ", 
	"8．在线更新（Online Update）  ",
	"9．复杂的处理 （Complex Processing） ", 
	"10．可重用性 （Reusability）  ",
	"11．安装的简易性 （Installation Ease） ", 
	"12．运行的简易性 （Operational Ease）  ",
	"13．多场地 （Multiple Sites）  ",
	"14．允许变更 （Facilitate Change） "};
	private JPanel[] jpScorePanelArray = new JPanel[15];
	ResultFrame r;
	public VAFInputer(ResultFrame r){
		this.r=r;
		this.setTitle("影响因子修改");
		JPanel buttonPanel = new JPanel(new FlowLayout());
		int[] score = r.getCm().getVAFScore();
		jpScoreDashboardPanel = new JPanel();
		jpScoreDashboardPanel.setLayout(new GridLayout(3, 5));
		jpScoreDashboardPanel.setPreferredSize(new Dimension(1100,200));
		jpScoreDashboardPanel.setSize(new Dimension(1100,200));
		for(int i=0;i<14;i++){
			jpScorePanelArray[i] = new JPanel();
			jpScorePanelArray[i].setLayout(new BorderLayout());
			jpM[i] = new SpinnerNumberModel(score[i],0,5,1);
			jSPVAFScoreArray[i] = new JSpinner(jpM[i]);
			jpScorePanelArray[i].add(jSPVAFScoreArray[i],BorderLayout.SOUTH);
			jLVAFDestLabelArray[i] = new JLabel(info[i]);
			jpScorePanelArray[i].add(jLVAFDestLabelArray[i],BorderLayout.NORTH);
			jpScoreDashboardPanel.add(jpScorePanelArray[i]);
		}
		buttonPanel.add(jbOK);
		buttonPanel.add(jbClose);
		
		jpScoreDashboardPanel.add(buttonPanel);
		this.add(jpScoreDashboardPanel);
		this.setPreferredSize(new Dimension(1100,280));
		this.setSize(new Dimension(1100,280));
		jbOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int[] score = VAFInputer.this.r.getCm().getVAFScore();
				for(int i=0;i<14;i++){
					int n = (Integer) jpM[i].getValue();
					score[i] = n;
				}
				VAFInputer.this.dispose();
			}
		});
		jbClose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VAFInputer.this.dispose();
			}
		});
	}


}
