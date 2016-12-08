package Swing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Class.User;
import Const.Constant;
import Method.InputFormat;
import Method.SqlUtil;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SignUpDialog extends JDialog
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField_Username;
	private JTextField textField_Password;
	private JButton btnDone;

	/**
	 * Create the dialog.
	 */
	public SignUpDialog()
	{
		initialize();
		monitor();
	}

	private void initialize()
	{
		// TODO Auto-generated method stub
		setTitle("\u6CE8\u518C");
		setBounds(100, 100, 334, 255);
		getContentPane().setLayout(null);

		JLabel label_Username = new JLabel("\u7528\u6237\u540D\uFF1A");
		label_Username.setBounds(10, 40, 75, 30);
		getContentPane().add(label_Username);

		JLabel label_Password = new JLabel("\u5BC6\u7801\uFF1A");
		label_Password.setBounds(10, 100, 75, 30);
		getContentPane().add(label_Password);

		textField_Username = new JTextField();
		textField_Username.setBounds(95, 45, 200, 21);
		getContentPane().add(textField_Username);

		textField_Password = new JTextField();
		textField_Password.setBounds(95, 105, 200, 21);
		getContentPane().add(textField_Password);

		btnDone = new JButton("\u5B8C\u6210");
		btnDone.setBounds(180, 180, 90, 25);
		getContentPane().add(btnDone);
	}

	private void monitor()
	{
		btnDone.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				// TODO Auto-generated method stub
				if (!InputFormat.isEmpty(textField_Username) && !InputFormat.isEmpty(textField_Password))
				{
					String username = textField_Username.getText().toString().trim();
					String password = textField_Password.getText().toString().trim();
					User user = new User(username, password);
					if (SqlUtil.insertToTable(Constant.TABLENAME_USER, user) == 1)
					{
						JOptionPane.showMessageDialog(null, "注册成功！！！");
						return;
					}
				}
				JOptionPane.showMessageDialog(null, "格式错误！！！");
			}
		});
	}
}
