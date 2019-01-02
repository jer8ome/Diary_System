package com.jeromet.multiplework;

import java.awt.Font;
import java.awt.Window;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;

/** 
 * @author  作者: Jerome
 * @date 创建时间：2019年1月1日 下午4:34:36 
 * @version 1.0 
 * @description:  
 */

public class GraphicalUtil {

	protected final static int WIDTH = 1615;  //界面宽
	protected final static int HEIGHT = 765;  //界面长
	
	//设置全局字体
	protected static void setUIFont(){
		Font f = new Font("宋体",Font.PLAIN,18);
		String   names[]={ "Label", "CheckBox", "PopupMenu","MenuItem", "CheckBoxMenuItem",
				"JRadioButtonMenuItem","ComboBox", "Button", "Tree", "ScrollPane",
				"TabbedPane", "EditorPane", "TitledBorder", "Menu", "TextArea",
				"OptionPane", "MenuBar", "ToolBar", "ToggleButton", "ToolTip",
				"ProgressBar", "TableHeader", "Panel", "List", "ColorChooser",
				"PasswordField","TextField", "Table", "Label", "Viewport",
				"RadioButtonMenuItem","RadioButton", "DesktopPane", "InternalFrame"
		}; 
		for (String item : names) {
			 UIManager.put(item+ ".font",f); 
		}
	}
	
	public static void setBack(Window mainFrame){
		ImageIcon img = new ImageIcon("img"+File.separator+"stretched.jpg"); //添加图片
		JLabel background = new JLabel(img);
		if(mainFrame instanceof JFrame) {
			 ((JComponent) ((JFrame) mainFrame).getContentPane()).setOpaque(false);
			 ((JFrame) mainFrame).getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
		} else {
			 ((JComponent) ((JDialog) mainFrame).getContentPane()).setOpaque(false);
			 ((JDialog) mainFrame).getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
		}
//		mainFrame.getSize().
	    background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
//		background.setBounds(0, 0, mainFrame.getSize().width, mainFrame.getSize().height);
//		background.setSize(mainFrame.getSize().width, mainFrame.getSize().height);
	}
	
	
	
}
