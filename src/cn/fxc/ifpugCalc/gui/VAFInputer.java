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
	private JButton jbOK = new JButton("È·¶¨");
	private JButton jbClose = new JButton("¹Ø±Õ");
	private JLabel[] jLVAFDestLabelArray = new JLabel[14];
	private JSpinner[] jSPVAFScoreArray = new JSpinner[14];
	private SpinnerNumberModel[] jpM = new SpinnerNumberModel[14];
	private JPanel[] jpScorePanelArray = new JPanel[15];
	ResultFrame r;
	public VAFInputer(ResultFrame r){
		this.r=r;
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
			jLVAFDestLabelArray[i] = new JLabel("info");
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
