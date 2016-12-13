package Swing;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Class.Class;
import Const.Constant;
import javax.swing.JLabel;
import java.awt.BorderLayout;

public class ShowInfo
{
	private static JFrame frame = new JFrame("\u6210\u7EE9\u67E5\u8BE2");

	/**
	 * Create the frame.
	 */
	public ShowInfo(List<Class> list)
	{
		Object[][] data =new Object[list.size()][2];
		for(int i=0;i<list.size();i++)
		{
			data[i][0]=list.get(i).getCode();
			data[i][1]=list.get(i).getScore();
		}
		
		JLabel show_label = new JLabel("\u5B66\u53F7\uFF1A");
		frame.getContentPane().add(show_label, BorderLayout.NORTH);
		
		
		JTable table = new JTable(data, Constant.CLASS);
		JScrollPane jScrollPane = new JScrollPane(table);
		frame.getContentPane().add(jScrollPane);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 226, 300);
		frame.setVisible(true);
	}

}
