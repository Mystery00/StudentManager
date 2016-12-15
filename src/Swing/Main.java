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
import Method.TableRefreshNotify;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JPanel;

public class Main
{
	private static JFrame frame = new JFrame("\u5B66\u751F\u4FE1\u606F\u6D4F\u89C8");
	private JTable table;
	private JMenuItem menuItem_refresh;
	private JMenuItem menuItem_exit;
	private JMenuItem menuItem_score_add;
	private JMenuItem menuItem_add;
	private JMenuItem menuItem_search;
	private JTextField search_text;
	private JButton btn_done;
	private JComboBox<String> search_type;
	private JLabel label;
	private JPanel panel;

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

		panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);

		label = new JLabel("\u67E5\u627E\u7C7B\u578B\uFF1A");
		panel.add(label);

		search_type = new JComboBox<>();
		panel.add(search_type);
		search_type.setModel(new DefaultComboBoxModel<>(Constant.STUDENT_COLUMNS));

		search_text = new JTextField();
		search_text.setColumns(20);
		panel.add(search_text);

		btn_done = new JButton("\u786E\u8BA4");
		panel.add(btn_done);
		btn_done.setVisible(false);
		search_text.setVisible(false);
		search_type.setVisible(false);
		label.setVisible(false);
		table = new JTable();
		TableRefreshNotify.refresh(table, getData(SqlUtil.searchStudent()), Constant.STUDENT_COLUMNS);
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

		menuItem_score_add = new JMenuItem("\u6210\u7EE9\u5F55\u5165");
		menu_edit.add(menuItem_score_add);

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
		menuItem_refresh.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				TableRefreshNotify.refresh(table, getData(SqlUtil.searchStudent()), Constant.STUDENT_COLUMNS);
			}
		});
		menuItem_exit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				System.exit(0);
			}
		});
		menuItem_score_add.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				new ScoreInput();
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
					StudentInput dialog = new StudentInput();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
		menuItem_search.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				search_text.setVisible(true);
				btn_done.setVisible(true);
				search_type.setVisible(true);
				label.setVisible(true);
			}
		});
		btn_done.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				// TODO Auto-generated method stub
				String[] where = Constant.DATABASE_CODE;
				TableRefreshNotify.refresh(table, getData(SqlUtil.searchStudent(where[search_type.getSelectedIndex()],
						"%" + search_text.getText().toString() + "%")), Constant.STUDENT_COLUMNS);
			}
		});
	}

	private Object[][] getData(List<Student> list)
	{
		Object[][] data = new Object[list.size()][8];
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
		return data;
	}
}
