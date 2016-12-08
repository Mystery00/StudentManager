package Swing;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Class.User;
import Const.Constant;
import Method.InputFormat;
import Method.SqlUtil;

import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class SignInDialog extends JDialog
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField_Username;
	private JTextField textField_Password;
	private JButton btnLogin;
	private JButton btnRegister;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		/**
		 * ִ�����ݿ⽨�����
		 */
		SqlUtil.createDatabase(Constant.DATABASENAME);
		SqlUtil.createTable(Constant.DATABASENAME, Constant.TABLENAME_USER, Constant.TABLE_COLUMNS_USER);
		SqlUtil.createTable(Constant.DATABASENAME, Constant.TABLENAME_STUDENT, Constant.TABLE_COLUMNS_STUDENT);
		SqlUtil.createTable(Constant.DATABASENAME, Constant.TABLENAME_SCORE, Constant.TABLE_COLUMNS_SCORE);

		/**
		 * ��ʾ��¼����
		 */
		try
		{
			SignInDialog dialog = new SignInDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SignInDialog()
	{
		initialize();
		monitor();
	}

	private void initialize()
	{
		// TODO Auto-generated method stub
		setTitle("\u767B\u9646");
		setBounds(100, 100, 400, 300);
		setResizable(false);
		getContentPane().setLayout(null);

		JLabel label_Username = new JLabel("\u7528\u6237\u540D\uFF1A");
		label_Username.setForeground(Color.CYAN);
		label_Username.setBounds(50, 40, 75, 30);
		getContentPane().add(label_Username);

		JLabel label_Password = new JLabel("\u5BC6\u7801\uFF1A");
		label_Password.setForeground(Color.CYAN);
		label_Password.setBounds(50, 100, 75, 30);
		getContentPane().add(label_Password);

		textField_Username = new JTextField();
		textField_Username.setForeground(Color.BLACK);
		textField_Username.setBounds(135, 45, 200, 21);
		getContentPane().add(textField_Username);

		textField_Password = new JTextField();
		textField_Password.setForeground(Color.BLACK);
		textField_Password.setBounds(135, 105, 200, 21);
		getContentPane().add(textField_Password);

		btnLogin = new JButton("\u767B\u9646");
		btnLogin.setForeground(Color.BLACK);
		btnLogin.setBounds(50, 187, 93, 23);
		getContentPane().add(btnLogin);

		btnRegister = new JButton("\u6CE8\u518C");
		btnRegister.setForeground(Color.BLACK);
		btnRegister.setBounds(227, 187, 93, 23);
		getContentPane().add(btnRegister);
		
		ImageIcon icon=new ImageIcon("background.jpg");
		JLabel jLabel=new JLabel(icon);
		getLayeredPane().add(jLabel, new Integer(Integer.MIN_VALUE));
		jLabel.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
		Container container=getContentPane();
		((JPanel)container).setOpaque(false);
	}

	private void monitor()
	{
		btnLogin.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if (!InputFormat.isEmpty(textField_Username) && !InputFormat.isEmpty(textField_Password))
				{
					login();
				} else
				{
					JOptionPane.showMessageDialog(null, "��ʽ���󣡣���");
				}
			}
		});
		btnRegister.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				try
				{
					SignUpDialog dialog = new SignUpDialog();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	private void login()
	{
		String username = textField_Username.getText().toString().trim();
		String password = textField_Password.getText().toString().trim();
		User user = new User(username, password);
		ResultSet set = SqlUtil.searchUser(user);
		boolean result = false;
		try
		{
			while (set.next())
			{
				result = username.equals(set.getString("username")) && password.equals(set.getString("password"));
			}
			set.close();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (result)
		{
			new Main();
			this.setVisible(false);
		} else
		{
			JOptionPane.showMessageDialog(null, "��Ϣ���󣡣���");
		}
	}
}
