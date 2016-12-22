package Swing;

import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import Class.Score;
import Const.Constant;
import Method.SqlUtil;
import Method.TableRefreshNotify;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ShowScore extends JDialog
{
	private static final long serialVersionUID = 1L;
	private List<Score> list;
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

	public ShowScore(List<Score> list)
	{
		this.list = list;
		initialize();
		monitor();
	}

	private void initialize()
	{
		setTitle("\u6210\u7EE9\u67E5\u8BE2");
		setBounds(100, 100, 500, 300);

		JLabel show_label = new JLabel("\u5B66\u53F7\uFF1A" + list.get(0).getNumber());
		getContentPane().add(show_label, BorderLayout.SOUTH);

		panel = new JPanel();
		panel.setBounds(0, 0, 500, 36);
		panel.setVisible(false);
		getContentPane().add(panel, BorderLayout.NORTH);

		label = new JLabel("\u67E5\u627E\u7C7B\u578B\uFF1A");
		panel.add(label);

		search_type = new JComboBox<>();
		search_type.setModel(new DefaultComboBoxModel<>(Constant.SCORE));
		search_type.setSelectedIndex(-1);
		panel.add(search_type);

		search_text = new JTextField();
		search_text.setColumns(20);
		panel.add(search_text);

		btn_done = new JButton("\u786E\u8BA4");
		panel.add(btn_done);

		table = new JTable();
		TableRefreshNotify.refresh(table, getData(list), Constant.SCORE);
		jScrollPane = new JScrollPane(table);
		getContentPane().add(jScrollPane);

		mEdt = new JMenuItem("编辑");
		jPopupMenu.add(mEdt);

		mIns = new JMenuItem("插入");
		jPopupMenu.add(mIns);

		mDel = new JMenuItem("删除");
		jPopupMenu.add(mDel);

		mSear = new JMenuItem("搜索");
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
					ScoreInput scoreInput = new ScoreInput(list.get(table.getSelectedRow()));
					scoreInput.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					scoreInput.setVisible(true);
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
				ScoreInput scoreInput = new ScoreInput();
				scoreInput.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				scoreInput.setVisible(true);
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
		mDel.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (table.getSelectedColumn() != -1)
				{
					int k = SqlUtil.deleteScore(list.get(table.getSelectedRow()).get_id());
					if (k == 1)
					{
						list.remove(table.getSelectedRow());
						JOptionPane.showMessageDialog(null, "删除成功！");
					}
					TableRefreshNotify.refresh(table, getData(list), Constant.SCORE);
				} else
				{
					JOptionPane.showMessageDialog(null, "请选择要删除的数据！");
				}
			}
		});
		mRef.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				panel.setVisible(false);
				list = SqlUtil.getScore(list.get(0).getNumber());
				TableRefreshNotify.refresh(table, getData(list), Constant.SCORE);
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
					String[] where = Constant.DATABASE_CODE_SCORE;
					TableRefreshNotify.refresh(table, getData(SqlUtil.searchScore(where[search_type.getSelectedIndex()],
							"%" + search_text.getText().toString() + "%")), Constant.SCORE);
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

	private Object[][] getData(List<Score> list)
	{
		Object[][] data = new Object[list.size()][2];
		for (int i = 0; i < list.size(); i++)
		{
			data[i][0] = SqlUtil.getClassName(list.get(i).getCode());
			data[i][1] = list.get(i).getScore();
		}
		return data;
	}
}
