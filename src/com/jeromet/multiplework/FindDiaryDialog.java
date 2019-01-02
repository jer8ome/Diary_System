package com.jeromet.multiplework;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
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
public class FindDiaryDialog {
	private JDialog mainDialog;
	private JPanel listPanel, diaryPanel,  contentPanel,  
	mainPanel, topPanel, firstPanel, titlePanel, datePanel;
	private JList<Diary> lists;
	private DefaultListModel<Diary> listModel;
	private JLabel weatherLbl, emotionLbl, writeDateLbl, titleLbl, contentLbl, diaryRangeLbl;
	private JTextField yearTxt, monthTxt, dayTxt, titleTxt,  startYearTxt, startMonthTxt, 
	startDayTxt, endYearTxt, endMonthTxt, endDayTxt;
	private JTextArea contentTxt;
	private JComboBox<String> weatherCombo, emotionCombo;
	private JButton findBtn;
	private String username =  LoginSystemFrame.userName;
	private ArrayList<Diary> diaries;
	
	public FindDiaryDialog(JFrame parent,String myName,boolean modal) {
		init(parent, myName, modal);
	}
	
	private void init(JFrame parent,String myName,boolean modal) {
		mainDialog = new JDialog(parent, myName, modal);
		
		//顶部用户面板
		topPanel = new JPanel();
		topPanel.setLayout(new GridBagLayout());//设置为网状包布局
		GridBagConstraints topGbCons = new GridBagConstraints();
		JPanel findDatePanel = new JPanel();
		diaryRangeLbl = new JLabel("日期范围:");
		//年输入框
		startYearTxt = new JTextField(5);
		endYearTxt = new JTextField(5);
		//月输入框
		startMonthTxt = new JTextField(3);
		endMonthTxt = new JTextField(3);
		//日输入框
		startDayTxt = new JTextField(3);
		endDayTxt = new JTextField(3);
		
		findDatePanel.setLayout(new FlowLayout());
		findDatePanel.add(diaryRangeLbl);
		findDatePanel.add(startYearTxt);
		findDatePanel.add(new JLabel("-"));
		findDatePanel.add(startMonthTxt);
		findDatePanel.add(new JLabel("-"));
		findDatePanel.add(startDayTxt);
		findDatePanel.add(new JLabel("~"));
		findDatePanel.add(endYearTxt);
		findDatePanel.add(new JLabel("-"));
		findDatePanel.add(endMonthTxt);
		findDatePanel.add(new JLabel("-"));
		findDatePanel.add(endDayTxt);
		topGbCons.gridx = 0;
		topGbCons.gridy = 0;
		topGbCons.fill = GridBagConstraints.NONE;
		topPanel.add(findDatePanel);
		
		findBtn = new JButton("查找");
		topGbCons.gridx = 1;
		topGbCons.gridy = 0;
		findBtn.addActionListener(new BtnActionListener());
		topPanel.add(findBtn);
		
		
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
			}
		});
		
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
		contentPanel.add(diaryPanel);
		
		setDiaryShow();//默认各个文本框中的内容为list的第一项的内容
		setDiaryEditAble(false);//设置默认内容面板不可编辑
		listPanel.setOpaque(false); 
		diaryPanel.setOpaque(false);
		contentPanel.setOpaque(false);
		topPanel.setOpaque(false);
		mainPanel.setOpaque(false);
		firstPanel.setOpaque(false);
		titlePanel.setOpaque(false); 
		datePanel.setOpaque(false);
		findDatePanel.setOpaque(false);
		mainPanel.add(contentPanel, mainGbCons);
		GraphicalUtil.setBack(mainDialog);
		mainDialog.add(mainPanel, BorderLayout.CENTER);
		mainDialog.add(topPanel, BorderLayout.NORTH);
		
		mainDialog.setSize(GraphicalUtil.WIDTH, GraphicalUtil.HEIGHT);
		mainDialog.setLocationRelativeTo(null);
	}
	
	//展示界面
	public void show() {
		mainDialog.setVisible(true);
	}
	
	//隐藏界面
	public void hide() {
		mainDialog.setVisible(false);
	}
	
	//输出所有日记
	public void showAllDiary() {
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
	

	//设置日记内容面板显示的内容
	private void setDiaryShow() {
		if (lists.getSelectedIndex()>=0) {
			Diary diary = lists.getSelectedValue();
			weatherCombo.setEditable(true);
			emotionCombo.setEditable(true);
			weatherCombo.setSelectedItem(diary.getWeather());
			emotionCombo.setSelectedItem(diary.getMood());
			weatherCombo.setEditable(false);
			emotionCombo.setEditable(false);
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
	private void findDiary(String username,String startDate, String endDate) {
		listModel.clear();
		diaries = DiaryUtil.findDiaryByRange(username, startDate, endDate);
		if(diaries.size()>0) {
			for(Diary diary:diaries) {
				listModel.addElement(diary);
			}
			lists.setSelectedIndex(0);
		} else {
			JOptionPane.showMessageDialog(mainDialog, "没有查询到符合条件的日记");
			clearTxt();
			showAllDiary();
		}
	}
	
	//清空文本框
	private void clearTxt() {
		startYearTxt.setText(""); 
		startMonthTxt.setText(""); 
		startDayTxt.setText(""); 
		endYearTxt.setText(""); 
		endMonthTxt.setText(""); 
		endDayTxt.setText("");
	}
	
	//按钮监听类
	
	class BtnActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if (source==findBtn) { //按下查找按钮时
				String startDate = startYearTxt.getText().trim()+"-"+startMonthTxt.getText().trim()
						+"-"+startDayTxt.getText().trim();
				String endDate = endYearTxt.getText().trim()+"-"+endMonthTxt.getText().trim()
						+"-"+endDayTxt.getText().trim();
				if(DiaryUtil.chkDateFormat(startDate)&&DiaryUtil.chkDateFormat(endDate)) {
					findDiary(username, startDate, endDate);
				} else {
					JOptionPane.showMessageDialog(mainDialog, "日期格式不正确");
					clearTxt();
					showAllDiary();
				}
			} 
		}
	}
	
}
