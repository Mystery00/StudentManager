package Swing;

import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Class.Student_Class;
import Const.Constant;
import Method.SqlUtil;
import Method.TableRefreshNotify;

public class ManagerLayout
{
	private static JFrame frame=new JFrame("\u7BA1\u7406\u5458\u64CD\u4F5C\u754C\u9762");
	private JTable table;

	/**
	 * Create the dialog.
	 */
	public ManagerLayout()
	{
		initialize();
		monitor();
	}

	private void initialize()
	{
		// TODO Auto-generated method stub
		table=new JTable();
		TableRefreshNotify.refresh(table, getData(SqlUtil.getStudentClass()), Constant.CLASS);
		JScrollPane jScrollPane = new JScrollPane(table);
		frame.getContentPane().add(jScrollPane);

		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		frame.setBounds(100, 100, 450, 300);
		frame.setVisible(true);
	}
	
	private void monitor()
	{
		
	}

	private Object[][] getData(List<Student_Class> list)
	{
		Object[][] data = new Object[list.size()][2];
		for (int i = 0; i < list.size(); i++)
		{
			data[i][0]=list.get(i).getName();
			data[i][1]=list.get(i).getCode();
		}
		return data;
	}
}
