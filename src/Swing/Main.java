package Swing;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;

import Class.Student;
import Const.Constant;
import Method.SqlUtil;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class Main
{
	private static JFrame frame = new JFrame("\u5B66\u751F\u4FE1\u606F\u6D4F\u89C8");
	private JMenuItem menuItem_refresh;
	private JMenuItem menuItem_exit;
	private JMenuItem menuItem_add;
	private JMenuItem menuItem_search;
	private Object[][] data;

	/**
	 * Create the application.
	 */
	public Main()
	{
		initialize();
		monitor();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		List<Student> list = SqlUtil.searchStudent();
		data = new Object[list.size()][8];
		for (int i = 0; i < list.size(); i++)
		{
			data[i][0] = list.get(i).getNumber();
			data[i][1] = list.get(i).getName();
			data[i][3] = list.get(i).getProfessional();
			data[i][4] = list.get(i).getCollege();
			data[i][5] = list.get(i).getBirthday();
			data[i][6] = list.get(i).getAddress();
			data[i][7] = list.get(i).getPhone();
			switch (list.get(i).getSex())
			{
			case 1:// boy
				data[i][2] = "ÄÐ";
				break;
			case 2:// girl
				data[i][2] = "Å®";
				break;
			}
		}
		JTable table = new JTable(data, Constant.STUDENT_COLUMNS);
		JScrollPane jScrollPane = new JScrollPane(table);
		frame.getContentPane().add(jScrollPane);

		JMenuBar menuBar = new JMenuBar();
		frame.setTitle("\u5B66\u751F\u4FE1\u606F");
		frame.setJMenuBar(menuBar);

		JMenu menu_main = new JMenu("\u83DC\u5355");
		menuBar.add(menu_main);

		menuItem_refresh = new JMenuItem("\u5237\u65B0");
		menu_main.add(menuItem_refresh);

		menuItem_exit = new JMenuItem("\u9000\u51FA");

		menu_main.add(menuItem_exit);

		JMenu menu_edit = new JMenu("\u7F16\u8F91");
		menuBar.add(menu_edit);

		menuItem_add = new JMenuItem("\u6570\u636E\u5F55\u5165");
		menu_edit.add(menuItem_add);

		menuItem_search = new JMenuItem("\u6570\u636E\u67E5\u8BE2");
		menu_edit.add(menuItem_search);

		frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		frame.setBounds(100, 100, 638, 390);
		frame.setVisible(true);
	}

	private void monitor()
	{
		menuItem_exit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				System.exit(0);
			}
		});
		menuItem_add.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent actionEvent)
			{
				// TODO Auto-generated method stub
				try
				{
					InputDialog dialog = new InputDialog();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
}
