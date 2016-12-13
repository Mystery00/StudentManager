package Swing;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;

public class ScoreInput extends JDialog
{
	private JTextField number_input;
	private JTextField score_input;


	/**
	 * Create the dialog.
	 */
	public ScoreInput()
	{
		setTitle("\u6210\u7EE9\u5F55\u5165");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
		setBounds(100, 100, 245, 190);
		getContentPane().setLayout(null);
		
		JLabel number_label = new JLabel("\u5B66\u53F7\uFF1A");
		number_label.setBounds(10, 10, 80, 15);
		getContentPane().add(number_label);
		
		number_input = new JTextField();
		number_input.setBounds(100, 7, 120, 21);
		getContentPane().add(number_input);
		
		JLabel code_label = new JLabel("\u8BFE\u7A0B\u4EE3\u7801\uFF1A");
		code_label.setBounds(10, 50, 80, 15);
		getContentPane().add(code_label);
		
		JComboBox code_input = new JComboBox();
		code_input.setModel(new DefaultComboBoxModel(new String[] {"134658745", "134658746", "134658747", "134658748"}));
		code_input.setBounds(100, 47, 120, 21);
		getContentPane().add(code_input);
		
		JLabel score_label = new JLabel("\u6210\u7EE9\uFF1A");
		score_label.setBounds(10, 90, 80, 15);
		getContentPane().add(score_label);
		
		score_input = new JTextField();
		score_input.setBounds(100, 87, 120, 21);
		getContentPane().add(score_input);
		
		JButton btn_done = new JButton("\u786E\u8BA4");
		btn_done.setBounds(126, 118, 93, 23);
		getContentPane().add(btn_done);
		
		JButton btn_reset = new JButton("\u91CD\u7F6E");
		btn_reset.setBounds(10, 118, 93, 23);
		getContentPane().add(btn_reset);
	}
}
