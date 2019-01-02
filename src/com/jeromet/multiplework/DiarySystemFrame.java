package com.jeromet.multiplework;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


/** 
 * @author  作者: Jerome
 * @date 创建时间：2018年12月8日 上午9:50:23 
 * @version 5.0 
 * @description:  
 */

public class DiarySystemFrame {
	private JFrame mainFrame;
	private JPanel listPanel, diaryPanel, listBottomPanel, contentPanel, contentBottomPanel, 
	mainPanel, topPanel, firstPanel, titlePanel, datePanel;
	private JList<Diary> lists;
	private DefaultListModel<Diary> listModel;
	private JLabel weatherLbl, emotionLbl, writeDateLbl, titleLbl, contentLbl, themeLbl, userLbl, exitLbl;
	private JTextField yearTxt, monthTxt, dayTxt, titleTxt, findTxt;
	private JTextArea contentTxt;
	private JComboBox<String> weatherCombo, emotionCombo, findCombo;
	private JButton addBtn, deleteBtn, clearBtn, findBtn, editBtn, cancelBtn, okBtn;
	private LoginSystemFrame loginDiglog;
	private String username = LoginSystemFrame.userName;
	private ArrayList<Diary> diaries;
	private boolean isAdd = false;

	
	protected DiarySystemFrame() {
		init();
	}
	
	private void init() {
		mainFrame = new JFrame("日记");
		

		//顶部用户面板
		topPanel = new JPanel();
		topPanel.setLayout(new GridBagLayout());//设置为网状包布局
		GridBagConstraints topGbCons = new GridBagConstraints();
		themeLbl = new JLabel("欢迎使用日记系统");
		themeLbl.setFont(new Font("宋体",Font.BOLD, 20));//设置字体
		topGbCons.fill = GridBagConstraints.HORIZONTAL;//设置为横向填充
		topGbCons.gridx = 0;
		topGbCons.gridy = 0;
		topGbCons.insets = new Insets(5, 10, 5, 10);//设置左右间隔
		topPanel.add(themeLbl, topGbCons);
		
		userLbl = new JLabel(username);
		topGbCons.gridx = 1;
		topGbCons.insets = new Insets(5, 10, 5, 5);
		topPanel.add(userLbl, topGbCons);
		
		exitLbl = new JLabel("【退出登录】");
		topGbCons.gridx = 2;
		topGbCons.insets = new Insets(5, 0, 5, 10);
		//给退出登录标签添加鼠标点击事件
		exitLbl.addMouseListener(new MouseListener() {
			//释放
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			
			//按下
			@Override
			public void mousePressed(MouseEvent e) {
				exitLbl.setForeground(new Color(241, 23, 220));
			}
			
			//未悬浮其上
			@Override
			public void mouseExited(MouseEvent e) {
				exitLbl.setForeground(new Color(100,149,237));
			}
			
			//悬浮在其上
			@Override
			public void mouseEntered(MouseEvent e) {
				exitLbl.setForeground(Color.BLUE);
			}
			
			//按下并释放 完整的点击
			@Override
			public void mouseClicked(MouseEvent e) {
				loginDiglog = new LoginSystemFrame();
				mainFrame.setVisible(false);
				loginDiglog.show();
			}
		});
		topPanel.add(exitLbl, topGbCons);
		
		
		//主面板
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints mainGbCons = new GridBagConstraints();
		
		//左侧列表面板
		listPanel = new JPanel();
		listPanel.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		listPanel.setLayout(new BorderLayout());
		//列表设置
		listModel = new DefaultListModel<>();
		lists = new JList<>(listModel);
		diaries = DiaryUtil.getAllDiary(username);
		showAllDiary();
		lists.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//设置列表只可单选
		lists.setBorder(BorderFactory.createRaisedBevelBorder());//列表区域设置边框
		lists.setFixedCellHeight(50);
		listPanel.add(new JScrollPane(lists));
		
		//添加列表事件
		lists.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				setDiaryShow();
				isAdd = false;
			}
		});
		
		//列表底部面板
		listBottomPanel = new JPanel();
		listBottomPanel.setLayout(new GridBagLayout());//网包布局
		GridBagConstraints listGbCon = new GridBagConstraints();
		//新建按钮
		addBtn = new JButton("新建");
		listGbCon.gridx = 0;
		listGbCon.gridy = 0;
		listGbCon.gridwidth = 1;
		listGbCon.gridheight = 1;
		listGbCon.insets = new Insets(10, 10, 10, 10);
		listGbCon.fill = GridBagConstraints.NONE;
		listGbCon.anchor = GridBagConstraints.LINE_START;
		listGbCon.weightx = 1;
		listGbCon.weighty =1;
		addBtn.addActionListener(new BtnActionListener());//新建按钮添加事件
		listBottomPanel.add(addBtn, listGbCon);
		//删除按钮
		deleteBtn = new JButton("删除");
		listGbCon.fill = GridBagConstraints.NONE;
		listGbCon.anchor = GridBagConstraints.BASELINE;
		listGbCon.gridx = 1;
		listGbCon.gridy = 0;
		deleteBtn.addActionListener(new BtnActionListener());
		listBottomPanel.add(deleteBtn, listGbCon);
		//清空按钮
		clearBtn = new JButton("清空");
		listGbCon.anchor = GridBagConstraints.LINE_END;
		listGbCon.gridx = 2;
		listGbCon.gridy = 0;
		clearBtn.addActionListener(new BtnActionListener());
		listBottomPanel.add(clearBtn, listGbCon);
		//查找方式选择框
		String[] findType = {"按天气查找","按心情查找","按标题查找","按内容查找","按日期范围查找"};
		listGbCon.fill = GridBagConstraints.NONE;
		listGbCon.anchor = GridBagConstraints.LINE_START;
		findCombo = new JComboBox<>(findType);
		listGbCon.gridx = 0;
		listGbCon.gridy = 1;
		findCombo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(findCombo.getSelectedIndex()==4&&lists.getSelectedIndex()>=0) {
					FindDiaryDialog findDiaryDialog = new FindDiaryDialog(mainFrame, "查找", true);
					findCombo.setSelectedIndex(0);
					findDiaryDialog.show();
				} else if(lists.getSelectedIndex()<0){
					JOptionPane.showMessageDialog(mainFrame, "清先添加日记哦");
				}
			}
		});
		
		
		listBottomPanel.add(findCombo, listGbCon);
		//查找内容
		findTxt = new JTextField(20);
		listGbCon.anchor = GridBagConstraints.BASELINE;
		listGbCon.gridx = 1;
		listGbCon.gridy = 1;
		listGbCon.insets = new Insets(15, 10, 10, 10);
		findTxt.setText("请输入查找内容");
		findTxt.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Object source = e.getSource();
				if(source==findTxt && findTxt.getText().trim().equals("请输入查找内容")) {
					findTxt.setText("");
				} 
			}
				
			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		listBottomPanel.add(findTxt, listGbCon);
		//查找按钮
		findBtn = new JButton("查找");
		listGbCon.fill = GridBagConstraints.NONE;
		listGbCon.anchor = GridBagConstraints.LINE_END;
		listGbCon.insets = new Insets(10, 10, 10, 10);
		listGbCon.gridx = 2;
		listGbCon.gridy = 1;
		findBtn.addActionListener(new BtnActionListener());
		listBottomPanel.add(findBtn, listGbCon);
		
		listPanel.add(listBottomPanel, BorderLayout.SOUTH);
		//设置左侧列表面板的属性
		mainGbCons.fill = GridBagConstraints.BOTH;
		mainGbCons.weightx = 1;
		mainGbCons.weighty = 1;
		mainGbCons.gridx = 0;
		mainGbCons.gridy = 0;
		mainGbCons.insets = new Insets(20, 30, 20, 10);
		mainPanel.add(listPanel, mainGbCons);
		
		
		//右侧内容面板
		contentPanel = new JPanel();
		contentPanel.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		contentPanel.setLayout(new BorderLayout());

		//日记内容面板
		diaryPanel = new JPanel();
		mainGbCons.gridx = 1;
		mainGbCons.gridy = 0;
		mainGbCons.gridwidth = 2;
		diaryPanel.setLayout(new GridBagLayout());
		GridBagConstraints diaryPanelGbCons = new GridBagConstraints();
		//天气标签
		weatherLbl = new JLabel("天气");

		//天气选择框
		String[] weath = {"晴天", "下雨", "多云", "阴天", "下雪"};
		weatherCombo = new JComboBox<>(weath);
		
		//心情标签
		emotionLbl = new JLabel("心情");
		
		//心情选择框
		String[] emotions = {"快乐", "生气", "伤心", "活力", "懊恼"};
		emotionCombo = new JComboBox<>(emotions);
		
		//天气心情面板
		firstPanel = new JPanel();
		firstPanel.setLayout(new FlowLayout());
		firstPanel.add(weatherLbl);
		firstPanel.add(weatherCombo);
		firstPanel.add(emotionLbl);
		firstPanel.add(emotionCombo);
		diaryPanelGbCons.gridx = 0;
		diaryPanelGbCons.gridy = 0;
		diaryPanelGbCons.gridwidth = 5;
		diaryPanelGbCons.fill = GridBagConstraints.NONE;
		diaryPanelGbCons.anchor = GridBagConstraints.BASELINE_LEADING;
		diaryPanelGbCons.weightx=1;
		diaryPanel.add(firstPanel, diaryPanelGbCons);
		
		//日期标签
		writeDateLbl = new JLabel("日期");
		//年输入框
		yearTxt = new JTextField(5);
		//月输入框
		monthTxt = new JTextField(3);
		//日输入框
		dayTxt = new JTextField(3);
		
		datePanel = new JPanel();
		datePanel.setLayout(new FlowLayout());
		datePanel.add(writeDateLbl);
		datePanel.add(yearTxt);
		datePanel.add(new JLabel("-"));
		datePanel.add(monthTxt);
		datePanel.add(new JLabel("-"));
		datePanel.add(dayTxt);
		diaryPanelGbCons.gridx = 0;
		diaryPanelGbCons.gridy = 1;
		diaryPanelGbCons.gridwidth = 5;
		diaryPanelGbCons.fill = GridBagConstraints.NONE;
		diaryPanelGbCons.anchor = GridBagConstraints.BASELINE_LEADING;
		diaryPanel.add(datePanel, diaryPanelGbCons);
		
		//标题标签
		titleLbl = new JLabel("标题");
		
		//标题输入框
		titleTxt = new JTextField(20);
		titleTxt.setPreferredSize(new Dimension(50, 20));
		
		//话题面板
		titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout());
		titlePanel.add(titleLbl);
		titlePanel.add(titleTxt);
		diaryPanelGbCons.gridx = 0;
		diaryPanelGbCons.gridy = 2;
		diaryPanelGbCons.gridwidth = 5;
		diaryPanelGbCons.fill = GridBagConstraints.NONE;
		diaryPanelGbCons.anchor = GridBagConstraints.BASELINE_LEADING;
		diaryPanel.add(titlePanel, diaryPanelGbCons);
		
		//内容标签
		contentLbl = new JLabel("内容");
		diaryPanelGbCons.gridx = 0;
		diaryPanelGbCons.gridy = 3;
		diaryPanelGbCons.fill = GridBagConstraints.NONE;
		diaryPanelGbCons.insets = new Insets(5, 5, 5, 5);
		diaryPanelGbCons.weightx = 0;
		diaryPanel.add(contentLbl, diaryPanelGbCons);
		
		//内容输入框
		contentTxt = new JTextArea(5,4);
		diaryPanelGbCons.fill = GridBagConstraints.BOTH;
		diaryPanelGbCons.weighty = 1;
		diaryPanelGbCons.gridwidth = 6;
		diaryPanelGbCons.gridx = 0;
		diaryPanelGbCons.gridy = 4;
		diaryPanel.add(new JScrollPane(contentTxt), diaryPanelGbCons);
		
		//内容底部按钮面板
		contentBottomPanel = new JPanel();
		GridBagConstraints contentBottomGbCons = new GridBagConstraints();
		//编辑按钮
		editBtn = new JButton("编辑");
		contentBottomGbCons.fill = GridBagConstraints.NONE;
		contentBottomGbCons.anchor = GridBagConstraints.LINE_START;
		contentBottomGbCons.gridx = 0;
		contentBottomGbCons.gridy = 0;
		contentBottomGbCons.weightx = 1;
		contentBottomGbCons.weighty = 0;
		contentBottomGbCons.insets = new Insets(5, 5, 5, 5);
		editBtn.addActionListener(new BtnActionListener());
		contentBottomPanel.add(editBtn, contentBottomGbCons);
		//取消按钮
		cancelBtn = new JButton("取消");
		contentBottomGbCons.gridx = 2;
		contentBottomGbCons.anchor = GridBagConstraints.BASELINE;
		cancelBtn.addActionListener(new BtnActionListener());
		contentBottomPanel.add(cancelBtn, contentBottomGbCons);
		//确认按钮
		okBtn = new JButton("确认");
		contentBottomGbCons.gridx = 3;
		contentBottomGbCons.anchor = GridBagConstraints.LINE_END;
		okBtn.addActionListener(new BtnActionListener());
		contentBottomPanel.add(okBtn, contentBottomGbCons);
		contentPanel.add(diaryPanel);
		contentPanel.add(contentBottomPanel,BorderLayout.SOUTH);
		
		setDiaryShow();//默认各个文本框中的内容为list的第一项的内容
		setDiaryEditAble(false);//设置默认内容面板不可编辑
		listPanel.setOpaque(false); 
		diaryPanel.setOpaque(false);
		listBottomPanel.setOpaque(false); 
		contentPanel.setOpaque(false);
		contentBottomPanel.setOpaque(false);
		topPanel.setOpaque(false);
		mainPanel.setOpaque(false);
		firstPanel.setOpaque(false);
		titlePanel.setOpaque(false); 
		datePanel.setOpaque(false);
		mainPanel.add(contentPanel, mainGbCons);
		setBack();
		mainFrame.add(mainPanel, BorderLayout.CENTER);
		mainFrame.add(topPanel, BorderLayout.NORTH);
		
		mainFrame.setSize(GraphicalUtil.WIDTH, GraphicalUtil.HEIGHT);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//设置背景
	public void setBack(){
	    ((JComponent) mainFrame.getContentPane()).setOpaque(false);
	    ImageIcon img = new ImageIcon("img"+File.separator+"stretched_for_diary.jpg"); //添加图片
	    JLabel background = new JLabel(img); 
	    mainFrame.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
	    background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
	}

	//展示界面
	protected void show() {
		mainFrame.setVisible(true);
	}
	
	//隐藏界面
	protected void hide() {
		mainFrame.setVisible(false);
	}
	
	//输出所有日记
	protected void showAllDiary() {
		listModel.clear();
		diaries = DiaryUtil.getAllDiary(username);
		if(diaries.size()>0) {
			for(Diary diary:diaries) {
				listModel.addElement(diary);
			}
			lists.setSelectedIndex(0);
		}
	}
	
	//设置日记内容是否可以编辑
	private void setDiaryEditAble(boolean editAble) {
		yearTxt.setEditable(editAble);
		monthTxt.setEditable(editAble);
		dayTxt.setEditable(editAble);
		titleTxt.setEditable(editAble);
		contentTxt.setEditable(editAble);
	}
	
	//清空日记内容控制
	private void clearControl(int clearType) {
		switch (clearType) {
		case 0:
			weatherCombo.setSelectedIndex(0);
			emotionCombo.setSelectedIndex(0);
			yearTxt.setText("");
			monthTxt.setText("");
			dayTxt.setText("");
			titleTxt.setText("");
			contentTxt.setText("");
			break;
		case 1:
			yearTxt.setText("");
			monthTxt.setText("");
			dayTxt.setText("");
			break;
		case 2:
			titleTxt.setText("");
			break;
		case 3:
			contentTxt.setText("");
			break;
		}
	}
	
	//设置日记内容面板显示的内容
	private void setDiaryShow() {
		if (lists.getSelectedIndex()>=0) {
			Diary diary = lists.getSelectedValue();
			weatherCombo.setSelectedItem(diary.getWeather());
			emotionCombo.setSelectedItem(diary.getMood());
			String diaryDate = diary.getDate().getValue();
			String[] dates = diaryDate.split("-");
			yearTxt.setText(dates[0]);
			monthTxt.setText(dates[1]);
			dayTxt.setText(dates[2]);
			titleTxt.setText(diary.getTitle());
			contentTxt.setText(diary.getContent());
		}
	}
	
	//查找日记
	private void findDiary(String username,int findType, String findInfo) {
		listModel.clear();
		diaries = DiaryUtil.findDiary(username, findType, findInfo);
		if(diaries.size()>0) {
			for(Diary diary:diaries) {
				listModel.addElement(diary);
			}
			lists.setSelectedIndex(0);
		} else {
			JOptionPane.showMessageDialog(mainFrame, "没有查询到符合条件的日记");
			showAllDiary();
		}
	}
	
	//按钮监听类
	class BtnActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if (source==addBtn) { //按下新建按钮时
				clearControl(0);
				setDiaryEditAble(true);
				isAdd = true;
			} else if(source==deleteBtn&&lists.getSelectedIndex()>=0){ //按下删除按钮时
				int answer = JOptionPane.showConfirmDialog(mainFrame, "确认删除所选项？", "警告", JOptionPane.YES_NO_OPTION);
				if(answer==JOptionPane.YES_OPTION) {
					Diary diary = lists.getSelectedValue();
					DiaryUtil.deleteDiary(username, DiaryUtil.getID(diary));
					listModel.remove(lists.getSelectedIndex());
				}
			} else if(source==clearBtn&&lists.getSelectedIndex()>=0){ //按下清空按钮时
				int answer = JOptionPane.showConfirmDialog(mainFrame, "是否清空所有日记？", "警告", JOptionPane.YES_NO_OPTION);
				if(answer==JOptionPane.YES_OPTION) {
					DiaryUtil.clearDiary(username);
					listModel.clear();
					clearControl(0);
				}
			} else if(source==findBtn&&lists.getSelectedIndex()>=0){ //按下查找按钮时
				if (findTxt.getText().trim().equals("")) {
				} else {
					findDiary(username, findCombo.getSelectedIndex(), findTxt.getText().trim());
				}
			} else if(source==editBtn&&lists.getSelectedIndex()>=0){ //按下编辑按钮时
				setDiaryEditAble(true);
			} else if(source==cancelBtn){ //按下取消按钮时
				setDiaryEditAble(false);
			} else if(source==okBtn){ //按下确认按钮时
				boolean editAble = true;
				String date = yearTxt.getText().trim()+"-"+monthTxt.getText().trim()+"-"+dayTxt.getText().trim();
				if (!DiaryUtil.chkDateFormat(date)) {
					JOptionPane.showMessageDialog(mainFrame, "日期格式不正确");
					clearControl(1);
				} else if(!DiaryUtil.chkTitle(titleTxt.getText().trim())){
					JOptionPane.showMessageDialog(mainFrame, "标题格式不正确");
					clearControl(2);
				} else if(contentTxt.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(mainFrame, "内容不能为空");
					clearControl(3);
				} else {
					editAble = false;
				}
				setDiaryEditAble(editAble);
				if (!editAble&&isAdd) {
					Diary diary = new Diary(date, String.valueOf(weatherCombo.getSelectedItem()).trim(), 
							String.valueOf(emotionCombo.getSelectedItem()).trim(), 
							titleTxt.getText().trim(), contentTxt.getText());
					DiaryUtil.addDiary(diary, username);
					listModel.addElement(diary);
					JOptionPane.showMessageDialog(mainFrame, "添加成功");
					isAdd = false;
				}  else if(!editAble&&!isAdd&&lists.getSelectedIndex()>=0) {
					Diary updateDiary = new Diary(date, String.valueOf(weatherCombo.getSelectedItem()).trim(), 
							String.valueOf(emotionCombo.getSelectedItem()).trim(), 
							titleTxt.getText().trim(), contentTxt.getText());
					Diary diary = lists.getSelectedValue();
					DiaryUtil.updateDiary(username, DiaryUtil.getID(diary), updateDiary);
					listModel.setElementAt(updateDiary, lists.getSelectedIndex());
					JOptionPane.showMessageDialog(mainFrame, "修改成功");
				} else if(!editAble&&lists.getSelectedIndex()<0){
					JOptionPane.showMessageDialog(mainFrame, "请先添加日记哦");
					clearControl(0);
				}
			}
			showAllDiary();
		}
	}
}
