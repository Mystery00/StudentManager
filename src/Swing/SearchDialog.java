package Swing;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Method.InputFormat;

public class SearchDialog extends JDialog
{
	private JTextField number_input;


	/**
	 * Create the dialog.
	 */
	public SearchDialog()
	{
		setVisible(true);
		setTitle("\u67E5\u8BE2\u4FE1\u606F");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 220, 110);
		getContentPane().setLayout(null);
		
		JLabel number_label = new JLabel("\u5B66\u53F7\uFF1A");
		number_label.setBounds(10, 10, 50, 15);
		getContentPane().add(number_label);
		
		number_input = new JTextField();
		number_input.setBounds(70, 7, 124, 21);
		getContentPane().add(number_input);
		number_input.setColumns(10);
		
		JButton btn_serach = new JButton("\u67E5\u8BE2");
		btn_serach.setBounds(10, 38, 184, 23);
		btn_serach.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				if(InputFormat.isNumber(number_input))
				{
					//查找数据
					new ShowInfo(new ArrayList<>());
					dispose();
				}else
				{
					JOptionPane.showMessageDialog(null, "学号输入错误！");
				}
			}
		});
		getContentPane().add(btn_serach);
	}
}
