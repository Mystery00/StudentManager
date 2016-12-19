package Swing;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Class.User;
import Const.Constant;
import Method.InputFormat;
import Method.SqlUtil;

import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class SignUpDialog extends JDialog
{
	private static final long serialVersionUID = 1L;
	private User local;
	private JTextField textField_Username;
	private JTextField textField_Password;
	private JRadioButton manager_input;
	private JButton btnDone;
	private int tag = 0;

	public SignUpDialog()
	{
		initialize();
		monitor();
	}

	public SignUpDialog(User user)
	{
		this();
		tag = 1;
		textField_Username.setText(user.getUsername());
		textField_Password.setText(user.getPassword());
		manager_input.setVisible(true);
		manager_input.setSelected(user.isManager());
		local = user;
	}

	private void initialize()
	{
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

		manager_input = new JRadioButton("\u7BA1\u7406\u5458");
		manager_input.setBounds(95, 181, 104, 23);
		manager_input.setVisible(false);
		getContentPane().add(manager_input);

		btnDone = new JButton("\u5B8C\u6210");
		btnDone.setBounds(205, 181, 90, 25);
		getContentPane().add(btnDone);
	}

	private void monitor()
	{
		btnDone.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				if (!InputFormat.isEmpty(textField_Username) && !InputFormat.isEmpty(textField_Password))
				{
					String username = textField_Username.getText().toString().trim();
					String password = textField_Password.getText().toString().trim();
					User user = new User(username, password);
					ResultSet set = SqlUtil.searchUser(user);
					try
					{
						if (set.next())
						{
							tag = 1;
							local = new User(set.getInt("_id"), set.getString("username"), set.getString("password"),
									set.getBoolean("manager"));
						}
					} catch (SQLException e)
					{
						e.printStackTrace();
					}
					if (tag == 0)
					{
						SqlUtil.insertToTable(Constant.TABLENAME_USER, Constant.COLUMNS_USER, user);
					} else
					{
						user = new User(local.get_id(), username, password, manager_input.isSelected());
						SqlUtil.updateUser(user);
					}
					JOptionPane.showMessageDialog(null, "录入成功！！！");
				} else
				{
					JOptionPane.showMessageDialog(null, "格式错误！！！");
				}
			}
		});
	}
}
