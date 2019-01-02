package com.jeromet.multiplework;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


/** 
 * @author  作者: Jerome
 * @date 创建时间：2018年12月3日 下午4:29:08 
 * @version 5.0 
 * @description:  
 */

public class FindPwdDialog {
	private JLabel usernameLbl, passwordLbl, passwordConfirmLbl, questionLbl, answerLbl;
	private JTextField usernameTxt, tipsAnswerTxt;
	private JComboBox<String> tipsQuestionCombo;
	private JPasswordField passwordTxt, passwordConfirmTxt;
	private JButton updateBtn, exitBtn;
	private JPanel mainPanel;
	private JDialog mainDialog;
	
	public FindPwdDialog(JFrame parent,String myName,boolean modal) {
		init(parent, myName, modal);
	}
	
	private void init(JFrame parent,String myName,boolean modal) {
		mainDialog = new JDialog(parent, myName, modal);
		
		
		//主面板
		mainPanel = new JPanel();
		GridBagLayout mainGbl = new GridBagLayout();
		GridBagConstraints mainGbCons = new GridBagConstraints();
		mainPanel.setLayout(mainGbl);
		//用户名标签
		usernameLbl = new JLabel("用户名");
		mainGbCons.fill = GridBagConstraints.BOTH;
		mainGbCons.gridx = 0;
		mainGbCons.gridy = 0;
		mainGbCons.insets = new Insets(5, 0, 5, 10);
		mainPanel.add(usernameLbl, mainGbCons);
		//密码标签
		passwordLbl = new JLabel("密码");
		mainGbCons.gridx = 0;
		mainGbCons.gridy = 1;
		mainPanel.add(passwordLbl, mainGbCons);
		//确认密码标签
		passwordConfirmLbl = new JLabel("确认密码");
		mainGbCons.gridx = 0;
		mainGbCons.gridy = 2;
		mainPanel.add(passwordConfirmLbl, mainGbCons);
		//问题标签
		questionLbl = new JLabel("问题");
		mainGbCons.gridx = 0;
		mainGbCons.gridy = 3;
		mainPanel.add(questionLbl, mainGbCons);
		//答案标签
		answerLbl = new JLabel("答案");
		mainGbCons.gridx = 0;
		mainGbCons.gridy = 4;
		mainPanel.add(answerLbl, mainGbCons);
		//用户输入框
		usernameTxt = new JTextField();
		mainGbCons.gridx = 1;
		mainGbCons.gridy = 0;
		usernameTxt.setPreferredSize(new Dimension(150, 20));
		mainGbCons.insets = new Insets(5, 0, 5, 10);
		mainPanel.add(usernameTxt, mainGbCons);
		//密码输入框
		passwordTxt = new JPasswordField();
		mainGbCons.gridx = 1;
		mainGbCons.gridy = 1;
		mainPanel.add(passwordTxt, mainGbCons);
		//确认密码输入框
		passwordConfirmTxt = new JPasswordField();
		mainGbCons.gridx = 1;
		mainGbCons.gridy = 2;
		mainPanel.add(passwordConfirmTxt, mainGbCons);
		//问题选择框
		tipsQuestionCombo = new JComboBox<>(UserUtil.questions);
		mainGbCons.gridx = 1;
		mainGbCons.gridy = 3;
		mainPanel.add(tipsQuestionCombo, mainGbCons);
		//答案输入框
		tipsAnswerTxt = new JTextField(); 
		mainGbCons.gridx = 1;
		mainGbCons.gridy = 4;
		mainPanel.add(tipsAnswerTxt, mainGbCons);
		//修改密码按钮
		updateBtn = new JButton("修改密码");
		mainGbCons.gridx = 0;
		mainGbCons.gridy = 5;
		mainGbCons.fill = GridBagConstraints.NONE;
		mainGbCons.anchor = GridBagConstraints.BASELINE_LEADING;
		updateBtn.addActionListener(new BtnActionListener());
		mainPanel.add(updateBtn, mainGbCons);
		//退出按钮
		exitBtn = new JButton("退出");
		mainGbCons.gridx = 1;
		mainGbCons.gridy = 5;
		mainGbCons.anchor = GridBagConstraints.BASELINE_TRAILING;
		exitBtn.addActionListener(new BtnActionListener());
		mainPanel.add(exitBtn, mainGbCons);
		mainPanel.setOpaque(false);
		setBack();
		
		mainDialog.add(mainPanel);
		mainDialog.setTitle("找回密码");
		mainDialog.setSize(1615, 765);
		mainDialog.setLocationRelativeTo(null);
	}
	
	public void setBack(){
	    ((JComponent) mainDialog.getContentPane()).setOpaque(false);
	    ImageIcon img = new ImageIcon("img"+File.separator+"stretched.jpg");//ship.jpg"); //添加图片
	    JLabel background = new JLabel(img); 
	    mainDialog.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
	    background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
	}
	
	//展示面板
	public void show() {
		mainDialog.setVisible(true);
	}
	
	//隐藏面板
	public void hide() {
		mainDialog.setVisible(false);
	}
	
	//清空控制
	private void clearControl(int clearType) {
		switch (clearType) {
		case 1:
			usernameTxt.setText("");
			passwordTxt.setText("");
			passwordConfirmTxt.setText("");
			tipsAnswerTxt.setText("");
			break;
		case 2:
			passwordTxt.setText("");
			passwordConfirmTxt.setText("");
			tipsAnswerTxt.setText("");
			break;
		case 3:
			tipsAnswerTxt.setText("");
			break;
		}
	}
	
	//按钮监听类
	class BtnActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if (source==updateBtn) { //按下修改密码按钮时
				boolean hasNoError = false;
				if(!UserUtil.isExistUser(usernameTxt.getText().trim())) {
					JOptionPane.showMessageDialog(mainDialog, "不存在该用户");
					clearControl(1);
				}else {
					if (!UserUtil.verifyPassword(String.valueOf(passwordTxt.getPassword()).trim())) {
						JOptionPane.showMessageDialog(mainDialog, "密码格式不正确");
						clearControl(2);
					} else if(!UserUtil.verifyPasswordAgain(String.valueOf(passwordTxt.getPassword()).trim(), String.valueOf(passwordConfirmTxt.getPassword()).trim())){
						JOptionPane.showMessageDialog(mainDialog, "两次密码不一致");
						clearControl(2);
					} else if(!UserUtil.verifyQuestionOwned(usernameTxt.getText().trim(), String.valueOf(tipsQuestionCombo.getSelectedItem()), tipsAnswerTxt.getText().trim())) {
						JOptionPane.showMessageDialog(mainDialog, "密保问题错误");
						clearControl(3);
					} else {
						hasNoError = true;
					}
				}
				
				if(hasNoError) {
					UserUtil.updatePassword(usernameTxt.getText().trim(), String.valueOf(passwordTxt.getPassword()).trim());
					JOptionPane.showMessageDialog(updateBtn, "修改成功");
					mainDialog.setVisible(false);
				}
				
			} else if(source==exitBtn){ //按下退出按钮时
				int answer = JOptionPane.showConfirmDialog(exitBtn, "是否退出", "警告", JOptionPane.YES_NO_OPTION);
				if(answer==JOptionPane.YES_OPTION) {
					mainDialog.setVisible(false);
				}
			}
		}
		
	}
	
}
