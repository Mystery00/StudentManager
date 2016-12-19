package Swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Class.Student_Class;
import Const.Constant;
import Method.SqlUtil;
import Method.TableRefreshNotify;

public class ManagerLayout
{
	private static JFrame frame = new JFrame("\u7BA1\u7406\u5458\u64CD\u4F5C\u754C\u9762");
	private static List<Student_Class> list = SqlUtil.getStudentClass();
	private JTable table;
	private JPopupMenu jPopupMenu = new JPopupMenu();
	private JMenuItem mEdt;
	private JMenuItem mDel;
	private JMenuItem mIns;
	private JMenuItem mRef;

	public ManagerLayout()
	{
		initialize();
		monitor();
	}

	private void initialize()
	{
		table = new JTable();
		TableRefreshNotify.refresh(table, getData(list), Constant.CLASS);
		JScrollPane jScrollPane = new JScrollPane(table);
		frame.getContentPane().add(jScrollPane);

		mEdt = new JMenuItem("±à¼­");
		jPopupMenu.add(mEdt);

		mIns = new JMenuItem("²åÈë");
		jPopupMenu.add(mIns);

		mDel = new JMenuItem("É¾³ý");
		jPopupMenu.add(mDel);

		mRef = new JMenuItem("Ë¢ÐÂ");
		jPopupMenu.add(mRef);

		frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		frame.setBounds(100, 100, 300, 300);
		frame.setVisible(true);
	}

	private void monitor()
	{
		mEdt.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				new ClassInput(list.get(table.getSelectedRow()));
			}
		});
		mIns.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				new ClassInput();
			}
		});
		mDel.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				SqlUtil.deleteClass(list.get(table.getSelectedRow()).get_id());
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
		frame.addMouseListener(new MouseAdapter()
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
