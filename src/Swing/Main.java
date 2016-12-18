package Swing;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;

import Class.Student;
import Class.User;
import Const.Constant;
import Method.SqlUtil;
import Method.TableRefreshNotify;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

public class Main
{
	private User user;
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
	private JPopupMenu jPopupMenu = new JPopupMenu();
	private JMenuItem mShow;
	private JMenuItem mDel;
	private JMenuItem mEdit;
	private List<Student> showList;
	private JMenu menu_manager;
	private JMenuItem menuItem_class;
	private JMenuItem menuItem_user;

	/**
	 * Create the application.
	 */
	public Main(User user)
	{
		this.user = user;
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
		search_type.setModel(new DefaultComboBoxModel<>(Constant.STUDENT_COLUMNS));
		search_type.setSelectedIndex(-1);
		panel.add(search_type);

		search_text = new JTextField();
		search_text.setColumns(20);
		panel.add(search_text);

		btn_done = new JButton("\u786E\u8BA4");
		panel.add(btn_done);

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

		menu_manager = new JMenu("\u7BA1\u7406\u5458\u64CD\u4F5C");
		menuBar.add(menu_manager);

		menuItem_class = new JMenuItem("\u8BFE\u7A0B\u7BA1\u7406");
		menu_manager.add(menuItem_class);

		menuItem_user = new JMenuItem("\u7528\u6237\u7BA1\u7406");
		menu_manager.add(menuItem_user);

		mEdit = new JMenuItem("编辑");
		jPopupMenu.add(mEdit);

		mShow = new JMenuItem("查看成绩");
		jPopupMenu.add(mShow);

		mDel = new JMenuItem("删除");
		jPopupMenu.add(mDel);

		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 638, 390);
		frame.setVisible(true);

		btn_done.setVisible(false);
		search_text.setVisible(false);
		search_type.setVisible(false);
		label.setVisible(false);
		menu_manager.setVisible(false);

		if (user.isManager())
		{
			menu_manager.setVisible(true);
		}
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
				search_text.setText(null);
				search_type.setSelectedItem(-1);
				search_text.setVisible(false);
				btn_done.setVisible(false);
				search_type.setVisible(false);
				label.setVisible(false);
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
		menuItem_class.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				new ManagerLayout();
			}
		});

		menuItem_user.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub

			}
		});
		btn_done.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				// TODO Auto-generated method stub
				if (search_type.getSelectedIndex() == -1 || search_text.getText().length() == 0)
				{
					JOptionPane.showMessageDialog(null, "请补全信息！");
				} else
				{
					String[] where = Constant.DATABASE_CODE;
					TableRefreshNotify.refresh(table,
							getData(SqlUtil.searchStudent(where[search_type.getSelectedIndex()],
									"%" + search_text.getText().toString() + "%")),
							Constant.STUDENT_COLUMNS);
				}
			}
		});
		mEdit.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				// TODO Auto-generated method stub
				if (table.getSelectedRow() != -1)
				{
					try
					{
						StudentInput dialog = new StudentInput(showList.get(table.getSelectedRow()));
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);
					} catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		});
		mShow.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				if (table.getSelectedRow() != -1)
				{
					new ShowClass(SqlUtil.getScore(showList.get(table.getSelectedRow()).getNumber()));
				}
			}
		});
		mDel.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				int k = SqlUtil.deleteStudent("num", showList.get(table.getSelectedRow()).getNumber());
				System.out.println(k);
				if (k == 1)
				{
					showList.remove(table.getSelectedRow());
				}
				TableRefreshNotify.refresh(table, getData(showList), Constant.STUDENT_COLUMNS);
			}
		});
		table.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				// TODO Auto-generated method stub
				if (e.getButton() == MouseEvent.BUTTON3)
				{
					// 弹出右键菜单
					jPopupMenu.show(frame, e.getX(), e.getY());
				}
			}
		});
	}

	private Object[][] getData(List<Student> list)
	{
		showList = list;
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
				data[i][2] = "男";
				break;
			case 2:// girl
				data[i][2] = "女";
				break;
			}
		}
		return data;
	}
}
