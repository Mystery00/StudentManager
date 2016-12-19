package Swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Class.Student_Class;
import Const.Constant;
import Method.SqlUtil;
import Method.TableRefreshNotify;

public class ClassManager extends JFrame
{
	private static final long serialVersionUID = 1L;
	private static List<Student_Class> list = SqlUtil.getStudentClass();
	private JScrollPane jScrollPane;
	private JTable table;
	private JPopupMenu jPopupMenu = new JPopupMenu();
	private JMenuItem mEdt;
	private JMenuItem mDel;
	private JMenuItem mIns;
	private JMenuItem mRef;

	public ClassManager()
	{
		initialize();
		monitor();
	}

	private void initialize()
	{
		setTitle("\u8BFE\u7A0B\u7BA1\u7406");
		setBounds(100, 100, 300, 300);

		table = new JTable();
		TableRefreshNotify.refresh(table, getData(list), Constant.CLASS);
		jScrollPane = new JScrollPane(table);
		getContentPane().add(jScrollPane);

		mEdt = new JMenuItem("�༭");
		jPopupMenu.add(mEdt);

		mIns = new JMenuItem("����");
		jPopupMenu.add(mIns);

		mDel = new JMenuItem("ɾ��");
		jPopupMenu.add(mDel);

		mRef = new JMenuItem("ˢ��");
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
					ClassInput classInput = new ClassInput(list.get(table.getSelectedRow()));
					classInput.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					classInput.setVisible(true);
				} else
				{
					JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�޸ĵ����ݣ�");
				}
			}
		});
		mIns.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				ClassInput classInput = new ClassInput();
				classInput.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				classInput.setVisible(true);
			}
		});
		mDel.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (table.getSelectedColumn() != -1)
				{
					int k = SqlUtil.deleteClass(list.get(table.getSelectedRow()).get_id());
					if (k == 1)
					{
						list.remove(table.getSelectedRow());
						JOptionPane.showMessageDialog(null, "ɾ���ɹ���");
					}
					TableRefreshNotify.refresh(table, getData(list), Constant.CLASS);
				} else
				{
					JOptionPane.showMessageDialog(null, "��ѡ��Ҫɾ�������ݣ�");
				}
			}
		});
		mRef.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				list = SqlUtil.getStudentClass();
				TableRefreshNotify.refresh(table, getData(list), Constant.CLASS);
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

	private Object[][] getData(List<Student_Class> list)
	{
		Object[][] data = new Object[list.size()][2];
		for (int i = 0; i < list.size(); i++)
		{
			data[i][0] = list.get(i).getName();
			data[i][1] = list.get(i).getCode();
		}
		return data;
	}
}
