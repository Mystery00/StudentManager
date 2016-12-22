package Swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import Class.User;
import Const.Constant;
import Method.SqlUtil;
import Method.TableRefreshNotify;

public class UserManager extends JDialog
{
	private static final long serialVersionUID = 1L;
	private static List<User> list = SqlUtil.getUser();
	private JScrollPane jScrollPane;
	private JTable table;
	private JPopupMenu jPopupMenu = new JPopupMenu();
	private JMenuItem mEdt;
	private JMenuItem mDel;
	private JMenuItem mIns;
	private JMenuItem mSear;
	private JMenuItem mRef;
	private JTextField search_text;
	private JButton btn_done;
	private JComboBox<String> search_type;
	private JLabel label;
	private JPanel panel;

	public UserManager()
	{
		initialize();
		monitor();
	}

	private void initialize()
	{
		setTitle("\u7528\u6237\u7BA1\u7406");
		setBounds(100, 100, 400, 300);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 400, 36);
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
		TableRefreshNotify.refresh(table, getData(list), Constant.USER);
		jScrollPane = new JScrollPane(table);
		getContentPane().add(jScrollPane);

		mEdt = new JMenuItem("编辑");
		jPopupMenu.add(mEdt);

		mIns = new JMenuItem("插入");
		jPopupMenu.add(mIns);

		mDel = new JMenuItem("删除");
		jPopupMenu.add(mDel);
		
		mSear=new JMenuItem("搜索");
		jPopupMenu.add(mSear);

		mRef = new JMenuItem("刷新");
		jPopupMenu.add(mRef);
	}

	private void monitor()
	{
		mEdt.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (table.getSelectedColumn() != -1)
				{
					try
					{
						SignUpDialog dialog = new SignUpDialog(list.get(table.getSelectedRow()));
						dialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
						dialog.setVisible(true);
					} catch (Exception e1)
					{
						e1.printStackTrace();
					}
				} else
				{
					JOptionPane.showMessageDialog(null, "请选择要修改的数据！");
				}
			}
		});
		mIns.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					SignUpDialog dialog = new SignUpDialog();
					dialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e1)
				{
					e1.printStackTrace();
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
					int k = SqlUtil.deleteUser(list.get(table.getSelectedRow()).get_id());
					if (k == 1)
					{
						list.remove(table.getSelectedRow());
						JOptionPane.showMessageDialog(null, "删除成功！");
					}
					TableRefreshNotify.refresh(table, getData(list), Constant.USER);
				} else
				{
					JOptionPane.showMessageDialog(null, "请选择要删除的数据！");
				}
			}
		});
		mSear.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				panel.setVisible(true);
			}
		});
		mRef.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				panel.setVisible(false);
				list = SqlUtil.getUser();
				TableRefreshNotify.refresh(table, getData(list), Constant.USER);
			}
		});
		table.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (e.getButton() == MouseEvent.BUTTON3)
				{
					jPopupMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		jScrollPane.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (e.getButton() == MouseEvent.BUTTON3)
				{
					jPopupMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
	}

	private Object[][] getData(List<User> list)
	{
		Object[][] data = new Object[list.size()][3];
		for (int i = 0; i < list.size(); i++)
		{
			data[i][0] = list.get(i).getUsername();
			data[i][1] = list.get(i).getPassword();
			data[i][2] = list.get(i).isManager();
		}
		return data;
	}
}
