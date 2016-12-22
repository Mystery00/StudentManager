package Swing;

import java.util.List;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Class.Score;
import Const.Constant;
import Method.SqlUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;

public class ShowScore extends JDialog
{
	private static final long serialVersionUID = 1L;

	public ShowScore(List<Score> list)
	{
		setTitle("\u6210\u7EE9\u67E5\u8BE2");
		setBounds(100, 100, 226, 300);
		getContentPane().setLayout(new BorderLayout(0, 0));

		Object[][] data = new Object[list.size()][2];
		for (int i = 0; i < list.size(); i++)
		{
			data[i][0] = SqlUtil.getClassName(list.get(i).getCode());
			data[i][1] = list.get(i).getScore();
		}

		JLabel show_label = new JLabel("\u5B66\u53F7\uFF1A" + list.get(0).getNumber());
		getContentPane().add(show_label, BorderLayout.NORTH);

		JTable table = new JTable(data, Constant.SCORE);
		JScrollPane jScrollPane = new JScrollPane(table);
		getContentPane().add(jScrollPane, BorderLayout.CENTER);
	}

}
