package Swing;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Class.BackgroundPanel;
import Class.Score;
import Class.Student;
import Class.User;
import Const.Constant;
import Method.BrowerOpen;
import Method.SqlUtil;
import Method.TableRefreshNotify;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import java.awt.Toolkit;

public class Main extends JFrame
{
	private static final long serialVersionUID = 1L;
	private User user;
	private JTable table;
	private JScrollPane jScrollPane;
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
	private JMenu menu_about;
	private JMenuItem menuItem_code;
	private JMenuItem menuItem_github;
	private JMenuItem menuItem_logout;

	public Main(User user)
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/img/xhu.jpg")));
		this.user = user;
		initialize();
		monitor();
	}

	private void initialize()
	{
		setTitle("\u5B66\u751F\u7BA1\u7406\u7CFB\u7EDF\uFF08\u5F53\u524D\u7528\u6237\uFF1A"+user.getUsername()+"\uFF09");
		setBounds(100, 100, 1000, 666);
		setResizable(false);

		panel = new JPanel();
		panel.setBounds(0, 0, 1000, 36);
		panel.setVisible(false);
		getContentPane().add(panel);

		label = new JLabel("\u67E5\u627E\u7C7B\u578B\uFF1A");
		panel.add(label);

		search_type = new JComboBox<>();
		search_type.setModel(new DefaultComboBoxModel<>(Constant.STUDENT));
		search_type.setSelectedIndex(-1);
		panel.add(search_type);

		search_text = new JTextField();
		search_text.setColumns(20);
		panel.add(search_text);

		btn_done = new JButton("\u786E\u8BA4");
		panel.add(btn_done);

		table = new JTable();
		table.setRowHeight(20);
		jScrollPane = new JScrollPane(table);
		refresh(SqlUtil.searchStudent());
		getContentPane().add(jScrollPane);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menu_main = new JMenu("\u83DC\u5355");
		menuBar.add(menu_main);

		menuItem_refresh = new JMenuItem("\u5237\u65B0");
		menu_main.add(menuItem_refresh);
		
		menuItem_logout = new JMenuItem("\u6CE8\u9500");
		menu_main.add(menuItem_logout);

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
		menu_manager.setVisible(false);
		menuBar.add(menu_manager);

		menuItem_class = new JMenuItem("\u8BFE\u7A0B\u7BA1\u7406");
		menu_manager.add(menuItem_class);

		menuItem_user = new JMenuItem("\u7528\u6237\u7BA1\u7406");
		menu_manager.add(menuItem_user);

		menu_about = new JMenu("\u5173\u4E8E");
		menuBar.add(menu_about);

		menuItem_code = new JMenuItem("\u8F6F\u4EF6\u6E90\u7801");
		menu_about.add(menuItem_code);

		menuItem_github = new JMenuItem("GitHub");
		menu_about.add(menuItem_github);

		mEdit = new JMenuItem("编辑");
		jPopupMenu.add(mEdit);

		mShow = new JMenuItem("查看成绩");
		jPopupMenu.add(mShow);

		mDel = new JMenuItem("删除");
		jPopupMenu.add(mDel);

		if (user.isManager())
		{
			menu_manager.setVisible(true);
		}

		ImageIcon icon = new ImageIcon(getClass().getResource("/img/main_back.jpg"));
		BackgroundPanel backgroundPanel = new BackgroundPanel(icon.getImage());
		backgroundPanel.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
		getContentPane().add(backgroundPanel);
	}

	private void monitor()
	{
		menuItem_refresh.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				search_text.setText(null);
				search_type.setSelectedItem(-1);
				panel.setVisible(false);
				refresh(SqlUtil.searchStudent());
			}
		});
		menuItem_logout.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				SignInDialog.main(null);
				dispose();
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
				new ScoreInput();
			}
		});
		menuItem_add.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent actionEvent)
			{
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
				panel.setVisible(true);
				refresh(SqlUtil.searchStudent());
			}
		});
		menuItem_class.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				ClassManager classManager = new ClassManager();
				classManager.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				classManager.setVisible(true);
			}
		});

		menuItem_user.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				UserManager userManager = new UserManager();
				userManager.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				userManager.setVisible(true);
			}
		});
		menuItem_code.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				BrowerOpen.openUrl("https://github.com/Mystery00/StudentManager");
			}
		});
		menuItem_github.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				BrowerOpen.openUrl("https://github.com/Mystery00");
			}
		});
		btn_done.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				if (search_type.getSelectedIndex() == -1 || search_text.getText().length() == 0)
				{
					JOptionPane.showMessageDialog(null, "请补全信息！");
				} else
				{
					String[] where = Constant.DATABASE_CODE_STUDENT;
					refresh(SqlUtil.searchStudent(where[search_type.getSelectedIndex()],
									"%" + search_text.getText().toString() + "%"));
				}
			}
		});
		mEdit.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
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
				} else
				{
					JOptionPane.showMessageDialog(null, "请选择要修改的数据！");
				}
			}
		});
		mShow.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (table.getSelectedRow() != -1)
				{
					List<Score> list = SqlUtil.getScore(showList.get(table.getSelectedRow()).getNumber());
					if (list.size() == 0)
					{
						JOptionPane.showMessageDialog(null, "该学生无成绩信息！");
						return;
					}
					try
					{
						ShowScore dialog = new ShowScore(list);
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);
					} catch (Exception e1)
					{
						e1.printStackTrace();
					}
				} else
				{
					JOptionPane.showMessageDialog(null, "请选择要查看的数据！");
				}
			}
		});
		mDel.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (table.getSelectedColumn() != -1)
				{
					int k = SqlUtil.deleteStudent("number", showList.get(table.getSelectedRow()).getNumber());
					if (k == 1)
					{
						showList.remove(table.getSelectedRow());
						JOptionPane.showMessageDialog(null, "删除成功！");
					}
					refresh(showList);
				} else
				{
					JOptionPane.showMessageDialog(null, "请选择要删除的数据！");
				}
			}
		});
		table.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (e.getButton() == MouseEvent.BUTTON3)
				{
					// 弹出右键菜单
					jPopupMenu.show(e.getComponent(), e.getX(), e.getY());
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

	private void refresh(List<Student> list)
	{
		TableRefreshNotify.refresh(table, getData(list), Constant.STUDENT);
		if (panel.isVisible())
		{
			jScrollPane.setBounds(0, 36, 1000, 25 + 20 * table.getRowCount());
		} else
		{
			jScrollPane.setBounds(0, 0, 1000, 25 + 20 * table.getRowCount());
		}
	}
}
