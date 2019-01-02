package com.jeromet.multiplework;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/** 
 * @author  作者: Jerome
 * @date 创建时间：2018年12月5日 下午2:45:14 
 * @version 5.0 
 * @description:  
 */

public class LoginSystemFrame {
	protected static String userName;
	private JFrame mainFrame;
	private JLabel usernameLbl, passwordLbl;
	private JTextField usernameTxt;
	private JPasswordField passwordTxt;
	private JPanel mainPanel;
	private JButton loginBtn;
	private JLabel registerLbl, findPasswordLbl;
	private RegisteredDialog registerDialog;
	private FindPwdDialog findPwdDialog;
	private DiarySystemFrame diarySystemFrame;
	
	protected LoginSystemFrame() {
		init();
	}
	
	private void init() {
		mainFrame = new JFrame();
		GraphicalUtil.setUIFont();
		
		//主面板
		mainPanel = new JPanel(new GridBagLayout());
		GridBagConstraints mainCons = new GridBagConstraints();
		//标题标签
		JLabel title=new JLabel("欢迎登陆日记系统");
		title.setFont(new Font("宋体",Font.BOLD, 28));
		mainCons.gridx = 0;
		mainCons.gridy = 0;
		mainCons.gridwidth = 3;
		mainCons.insets = new Insets(0, 0, 10, 0);
		mainPanel.add(title, mainCons);
		//用户名标签
		usernameLbl = new JLabel();
		usernameLbl.setIcon(new ImageIcon("icons"+File.separator+"user.png", "用户"));
		mainCons.fill = GridBagConstraints.BOTH;
		mainCons.insets = new Insets(5, 0, 5, 0);
		mainCons.gridwidth = 1;
		mainCons.gridx = 0;
		mainCons.gridy = 1;
		mainPanel.add(usernameLbl, mainCons);
		//密码标签
		passwordLbl = new JLabel();
		passwordLbl.setIcon(new ImageIcon("icons"+File.separator+"password.png", "密码"));
		mainCons.gridx = 0;
		mainCons.gridy = 2;
		mainPanel.add(passwordLbl, mainCons);
		//用户输入框
		usernameTxt = new JTextField("用户名");
		usernameTxt.setPreferredSize(new Dimension(150, 20));
		usernameTxt.addMouseListener(new LabMouseListener());
		mainCons.gridx = 1;
		mainCons.gridy = 1;
		mainPanel.add(usernameTxt, mainCons);
		//密码输入框
		passwordTxt = new JPasswordField();
		mainCons.gridx = 1;
		mainCons.gridy = 2;
		passwordTxt.addMouseListener(new LabMouseListener());
		mainPanel.add(passwordTxt, mainCons);
		//注册按钮
		registerLbl = new JLabel("立即注册");
		registerLbl.setForeground(new Color(100,149,237));
		mainCons.insets = new Insets(5, 20, 5, 10);
		mainCons.gridx = 2;
		mainCons.gridy = 1;
		registerLbl.addMouseListener(new LabMouseListener());
		mainPanel.add(registerLbl, mainCons);
		
		//忘记密码按钮
		findPasswordLbl = new JLabel("忘记密码");
		mainCons.gridx = 2;
		mainCons.gridy = 2;
		findPasswordLbl.setForeground(new Color(100,149,237));
		findPasswordLbl.addMouseListener(new LabMouseListener());
		mainPanel.add(findPasswordLbl, mainCons);
		//登录按钮
		loginBtn = new JButton("登录");
		mainCons.fill = GridBagConstraints.HORIZONTAL;
		mainCons.gridx = 1;
		mainCons.gridy = 3;
		mainCons.gridwidth = 1;
		loginBtn.addActionListener(new BtnActionListener());
		mainPanel.add(loginBtn, mainCons);
		mainPanel.setOpaque(false);
		GraphicalUtil.setBack(mainFrame);
		
		
		mainFrame.add(mainPanel);
		mainFrame.setTitle("登录界面");
		mainFrame.setSize(GraphicalUtil.WIDTH, GraphicalUtil.HEIGHT);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	

	//展示界面
	public void show() {
		mainFrame.setVisible(true);
	}
	
	//隐藏界面
	public void hide() {
		mainFrame.setVisible(false);
	}
	
	//鼠标监听类
	class LabMouseListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			Object source = e.getSource();
			if(source==registerLbl) {
				registerDialog = new RegisteredDialog(mainFrame, "注册", true);
				registerDialog.show();
				usernameTxt.setText("用户名");
			} else if(source==findPasswordLbl) {
				findPwdDialog = new FindPwdDialog(mainFrame, "找回密码", true);
				findPwdDialog.show();
				usernameTxt.setText("用户名");
			} 
		}

		@Override
		public void mousePressed(MouseEvent e) {
			Object source = e.getSource();
			if(source==usernameTxt && usernameTxt.getText().trim().equals("用户名")) {
				usernameTxt.setText("");
			} else if(source==passwordTxt) {
			} else if(source==registerLbl||source==findPasswordLbl){
				((JComponent) e.getSource()).setForeground(new Color(241, 23, 220));
			}
		}
			

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			Object source = e.getSource();
			if(source==usernameTxt || source==passwordTxt) {
			} else {
				((JComponent) e.getSource()).setForeground(Color.BLUE);
			}
		}

		@Override
		public void mouseExited(MouseEvent e) {
			Object source = e.getSource();
			if(source==usernameTxt || source==passwordTxt) {
			} else {
				((JComponent) e.getSource()).setForeground(new Color(100,149,237));
			}
		}
		
	}
	
	//按钮监听类
	class BtnActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if(source == loginBtn) { //按下登录按钮时
				if(UserUtil.verifyPasswordOwned(usernameTxt.getText().trim(), String.valueOf(passwordTxt.getPassword()).trim())) {
					userName = usernameTxt.getText().trim();
					mainFrame.setVisible(false);
					diarySystemFrame = new DiarySystemFrame();
					diarySystemFrame.show();
				}else {
					JOptionPane.showMessageDialog(loginBtn, "用户名或密码不正确");
				}
			}
		}
	}
}








