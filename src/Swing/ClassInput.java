package Swing;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Class.Student_Class;
import Const.Constant;
import Method.InputFormat;
import Method.SqlUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ClassInput
{
	private static JFrame frame = new JFrame("\u8BFE\u7A0B\u5F55\u5165\u754C\u9762");
	private Student_Class local;
	private JTextField name_input;
	private JTextField code_input;
	private JButton btn_done;
	private JButton btn_reset;
	private int tag = 0;

	public ClassInput(Student_Class student_Class)
	{
		this();
		tag = 1;
		local = student_Class;
		setDefault();
	}

	public ClassInput()
	{
		initialize();
		monitor();
	}

	private void initialize()
	{
		frame.getContentPane().setLayout(null);

		JLabel name_label = new JLabel("\u8BFE\u7A0B\u540D\u79F0");
		name_label.setBounds(10, 10, 54, 15);
		frame.getContentPane().add(name_label);

		name_input = new JTextField();
		name_input.setBounds(74, 7, 200, 21);
		frame.getContentPane().add(name_input);
		name_input.setColumns(10);

		JLabel code_label = new JLabel("\u8BFE\u7A0B\u4EE3\u7801");
		code_label.setBounds(10, 50, 54, 15);
		frame.getContentPane().add(code_label);

		code_input = new JTextField();
		code_input.setBounds(74, 47, 200, 21);
		frame.getContentPane().add(code_input);
		code_input.setColumns(10);

		btn_done = new JButton("\u786E\u5B9A");
		btn_done.setBounds(181, 90, 93, 23);
		frame.getContentPane().add(btn_done);

		btn_reset = new JButton("\u91CD\u7F6E");
		btn_reset.setBounds(74, 90, 93, 23);
		frame.getContentPane().add(btn_reset);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(100, 100, 300, 160);
		frame.setVisible(true);
	}

	private void monitor()
	{
		btn_done.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (!InputFormat.isEmpty(name_input) && InputFormat.isNumber(code_input))
				{
					Student_Class student_Class = new Student_Class(local.get_id(), name_input.getText().toString(),
							code_input.getText().toString());
					if (tag == 0)
					{
						SqlUtil.insertToTable(Constant.TABLENAME_CLASS, Constant.COLUMNS_CLASS, student_Class);
					} else
					{
						SqlUtil.updateClass(student_Class);
					}
					JOptionPane.showMessageDialog(null, "录入成功！");
				} else
				{
					JOptionPane.showMessageDialog(null, "格式错误！");
				}
			}
		});
		btn_reset.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				name_input.setText(null);
				code_input.setText(null);
			}
		});
	}

	private void setDefault()
	{
		name_input.setText(local.getName());
		code_input.setText(local.getCode());
	}
}
