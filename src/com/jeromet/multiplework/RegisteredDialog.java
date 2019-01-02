package com.jeromet.multiplework;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

public class RegisteredDialog {
	private static VerificationCode verificationCode;
	private JDialog mainDialog;
	private JPanel mainpanel;
	private JLabel usernameLbl, displayNameLbl, passwordLbl, passwordConfirmLbl, emailLbl, tipsQuestionsLbl, tipsAnswerLbl, verifyLbl;
	private JTextField usernameTxt, displayNameTxt,  emailTxt, tipsTxt, verifyTxt, verificationCodeTxt;
	private JPasswordField passwordTxt, passwordConfirmTxt;
	private JButton registeredBtn;
	private JComboBox<String> questionCombo;
	
	public RegisteredDialog(JFrame parent,String myName,boolean modal) {
		init(parent, myName, modal);
	}
	
	private void init(JFrame parent,String myName,boolean modal) {
		mainDialog = new JDialog(parent, myName, modal);
		
		
		//主面板
		mainpanel = new JPanel();
		verificationCode = new VerificationCode();
		GridBagLayout mainGbl = new GridBagLayout();
		GridBagConstraints mainGbCons = new GridBagConstraints();
		mainpanel.setLayout(mainGbl);
		//用户名标签
		usernameLbl = new JLabel("用户名");
		mainGbCons.fill = GridBagConstraints.BOTH;
		mainGbCons.gridx = 0;
		mainGbCons.gridy = 0;
		mainGbCons.insets = new Insets(5, 0, 5, 10);
		mainpanel.add(usernameLbl, mainGbCons);
		//显示名标签
		displayNameLbl = new JLabel("显示名");
		mainGbCons.gridx = 0;
		mainGbCons.gridy = 1;
		mainpanel.add(displayNameLbl, mainGbCons);
		//密码标签
		passwordLbl = new JLabel("密码");
		mainGbCons.gridx = 0;
		mainGbCons.gridy = 2;
		mainpanel.add(passwordLbl, mainGbCons);
		//确认密码标签
		passwordConfirmLbl = new JLabel("确认密码");
		mainGbCons.gridx = 0;
		mainGbCons.gridy = 3;
		mainpanel.add(passwordConfirmLbl, mainGbCons);
		//邮箱标签
		emailLbl = new JLabel("邮箱");
		mainGbCons.gridx = 0;
		mainGbCons.gridy = 4;
		mainpanel.add(emailLbl, mainGbCons);
		//密码提示问题标签
		tipsQuestionsLbl = new JLabel("密保问题");
		mainGbCons.gridx = 0;
		mainGbCons.gridy = 5;
		mainpanel.add(tipsQuestionsLbl, mainGbCons);
		//密码提示答案标签
		tipsAnswerLbl = new JLabel("密保答案");
		mainGbCons.gridx = 0;
		mainGbCons.gridy = 6;
		mainpanel.add(tipsAnswerLbl, mainGbCons);
		//验证码标签
		verifyLbl = new JLabel("验证码");
		mainGbCons.gridx = 0;
		mainGbCons.gridy = 7;
		mainpanel.add(verifyLbl, mainGbCons);
		//用户名输入框
		usernameTxt = new JTextField();
		mainGbCons.gridx = 1;
		mainGbCons.gridy = 0;
		mainGbCons.gridwidth = 100;
		usernameTxt.setPreferredSize(new Dimension(150, 20));
		mainGbCons.insets = new Insets(5, 0, 5, 10);
		mainpanel.add(usernameTxt, mainGbCons);
		//显示名输入框
		displayNameTxt = new JTextField();
		mainGbCons.gridx = 1;
		mainGbCons.gridy = 1;
		mainpanel.add(displayNameTxt, mainGbCons);
		//密码输入框
		passwordTxt = new JPasswordField();
		mainGbCons.gridx = 1;
		mainGbCons.gridy = 2;
		mainpanel.add(passwordTxt, mainGbCons);
		//确认密码输入框
		passwordConfirmTxt = new JPasswordField(); 
		mainGbCons.gridx = 1;
		mainGbCons.gridy = 3;
		mainpanel.add(passwordConfirmTxt, mainGbCons);
		//邮箱输入框
		emailTxt = new JTextField();
		mainGbCons.gridx = 1;
		mainGbCons.gridy = 4;
		mainpanel.add(emailTxt, mainGbCons);
		//密保问题选择框
		questionCombo = new JComboBox<>(UserUtil.questions);
		mainGbCons.gridx = 1;
		mainGbCons.gridy = 5;
		mainpanel.add(questionCombo, mainGbCons);
		//密保答案输入框
		tipsTxt = new JTextField();
		mainGbCons.gridx = 1;
		mainGbCons.gridy = 6;
		mainpanel.add(tipsTxt, mainGbCons);
		//验证码答案输入框
		verifyTxt = new JTextField();
		mainGbCons.gridx = 1;
		mainGbCons.gridy = 7;
		mainpanel.add(verifyTxt, mainGbCons);
		//验证码显示框
		verificationCodeTxt = new JTextField();
		verificationCodeTxt.setEditable(false);
		verificationCodeTxt.setText(verificationCode.buildArichmetic());
		//鼠标点击课更换验证码
		verificationCodeTxt.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				verificationCodeTxt.setText(verificationCode.buildArichmetic());
			}
		});
		mainGbCons.gridx = 0;
		mainGbCons.gridy = 8;
		mainpanel.add(verificationCodeTxt, mainGbCons);
		//注册按钮
		registeredBtn = new JButton("注册");
		mainGbCons.gridx = 1;
		mainGbCons.gridy = 8;
		registeredBtn.addActionListener(new BtnActionListener());
		mainpanel.add(registeredBtn, mainGbCons);
		mainpanel.setOpaque(false);
		GraphicalUtil.setBack(mainDialog);
		
		mainDialog.add(mainpanel);
		mainDialog.setTitle("注册");
		mainDialog.setSize(GraphicalUtil.WIDTH, GraphicalUtil.HEIGHT);
		mainDialog.setLocationRelativeTo(null);
	}
	
	//显示界面
	public void show() {
		mainDialog.setVisible(true);
	}
	
	//隐藏界面
	public void hide() {
		mainDialog.setVisible(false);
	}
	
	//清空控制
	private void clearControl(int clearType) {
		switch (clearType) {
		case 1:
			usernameTxt.setText("");
			displayNameTxt.setText("");
			passwordTxt.setText("");
			passwordConfirmTxt.setText("");
			emailTxt.setText("");
			tipsTxt.setText("");
			verifyTxt.setText("");
			break;
		case 2:
			displayNameTxt.setText("");
			passwordTxt.setText("");
			passwordConfirmTxt.setText("");
			emailTxt.setText("");
			tipsTxt.setText("");
			verifyTxt.setText("");
			break;
		case 3:
			passwordTxt.setText("");
			passwordConfirmTxt.setText("");
			emailTxt.setText("");
			tipsTxt.setText("");
			verifyTxt.setText("");
			break;
		case 4:
			emailTxt.setText("");
			tipsTxt.setText("");
			verifyTxt.setText("");
			break;
		case 5:
			tipsTxt.setText("");
			verifyTxt.setText("");
			break;
		}
	}
	
	//判断是否全为数字
	public final static boolean isNumeric(String s) {  
        if (s != null && !"".equals(s.trim()))  {
        	 return s.matches("^[0-9]*$"); 
        } else  {
        	return false;
        }
    }
	
	//按钮监听类
	class BtnActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			boolean formatCorrect = true;
			if (!UserUtil.verifyUsername(usernameTxt.getText().trim())) {
				formatCorrect = false;
				JOptionPane.showMessageDialog(mainDialog, "用户名格式不正确");
				clearControl(1);
			} else if(!UserUtil.verifyDisplayName(displayNameTxt.getText().trim())){
				formatCorrect = false;
				JOptionPane.showMessageDialog(mainDialog, "显示名格式不正确");
				clearControl(1);
			} else if(!UserUtil.verifyPassword(String.valueOf(passwordTxt.getPassword()).trim())){
				formatCorrect = false;
				JOptionPane.showMessageDialog(mainDialog, "密码格式不正确");
				clearControl(2);
			} else if(!UserUtil.verifyPasswordAgain(String.valueOf(passwordTxt.getPassword()).trim(), String.valueOf(passwordConfirmTxt.getPassword()).trim())){
				formatCorrect = false;
				JOptionPane.showMessageDialog(mainDialog, "两次密码不一致");
				clearControl(3);
			} else if(!UserUtil.verifyEmail(emailTxt.getText().trim())){
				formatCorrect = false;
				JOptionPane.showMessageDialog(mainDialog, "邮箱格式不正确");
				clearControl(4);
			} else if(tipsTxt.getText().trim().equals("")) {
				formatCorrect = false;
				JOptionPane.showMessageDialog(mainDialog, "密保问题答案不能为空");
				clearControl(5);
			} else if(isNumeric(verifyTxt.getText().trim())){
				if(!verificationCode.judgeArichmeticCorrectness(Integer.valueOf(verifyTxt.getText().trim()))) {
					formatCorrect = false;
					JOptionPane.showMessageDialog(mainDialog, "验证码错误");
					clearControl(5);
					verificationCodeTxt.setText(verificationCode.buildArichmetic());
				}
			}
			//如果格式全部正确
			if(formatCorrect) {
				User user = new User(usernameTxt.getText().trim(), displayNameTxt.getText().trim(), 
						String.valueOf(passwordTxt.getPassword()).trim(), emailTxt.getText().trim(), 
						String.valueOf(questionCombo.getSelectedItem()), tipsTxt.getText().trim());
				UserUtil.addUser(user);
				JOptionPane.showMessageDialog(mainDialog, "注册成功");
				mainDialog.setVisible(false);
			}
		}
	}

}
