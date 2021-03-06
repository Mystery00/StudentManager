package Swing;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Class.Birthday;
import Class.Student;
import Const.Constant;
import Method.InputFormat;
import Method.SqlUtil;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;

import java.util.Calendar;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;

public class StudentInput extends JDialog
{
	private static final long serialVersionUID = 1L;
	private Student localStudent;
	private JTextField number_input;
	private JTextField name_input;
	private JTextField phone_input;
	private JTextField collage_input;
	private JTextField address_input;
	private JRadioButton radioButton_boy;
	private JRadioButton radioButton_girl;
	private JComboBox<String> professional_input = new JComboBox<>();
	private JComboBox<String> year_input = new JComboBox<>();
	private JComboBox<String> month_input = new JComboBox<>();
	private JComboBox<String> day_input = new JComboBox<>();
	private JButton button_done;
	private JButton button_reset;
	private static int year_count = Calendar.getInstance().get(Calendar.YEAR) - 1960 + 1;
	private int year;
	private int month;
	private int day;
	private int tag = 0;

	private int sex = 1;

	/**
	 * Create the dialog.
	 */
	public StudentInput()
	{
		initialize();
		monitor();
	}

	public StudentInput(Student student)
	{
		localStudent = student;
		tag = 1;
		initialize();
		setDefault(student);
		monitor();
	}

	private void setDefault(Student student)
	{
		number_input.setText(student.getNumber());
		name_input.setText(student.getName());
		phone_input.setText(student.getPhone());
		collage_input.setText(student.getCollege());
		address_input.setText(student.getAddress());
		sex = student.getSex();
		if (sex == 1)
		{
			radioButton_boy.setSelected(true);
		} else
		{
			radioButton_girl.setSelected(true);
		}
		for (int i = 0; i < Constant.PROFESSIONAL.length; i++)
		{
			if (Constant.PROFESSIONAL[i].equals(student.getProfessional()))
			{
				professional_input.setSelectedIndex(i);
				break;
			}
		}
		year = student.getBirthday().getYear();
		month = student.getBirthday().getMonth();
		day = student.getBirthday().getDay();
		month_input.setEnabled(true);
		day_input.setEnabled(true);
		year_input.setSelectedIndex(student.getBirthday().getYear() - 1960);
		month_input.setSelectedIndex(student.getBirthday().getMonth() - 1);
		setDays(student.getBirthday().getYear(), student.getBirthday().getMonth(), day_input);
		day_input.setSelectedIndex(student.getBirthday().getDay() - 1);
	}

	private void initialize()
	{
		setTitle("\u4FE1\u606F\u5F55\u5165");
		setBounds(100, 100, 450, 373);
		getContentPane().setLayout(null);

		JLabel number_label = new JLabel("\u5B66\u53F7\uFF1A");
		number_label.setBounds(10, 20, 50, 20);
		getContentPane().add(number_label);

		number_input = new JTextField();
		number_input.setBounds(60, 20, 120, 20);
		getContentPane().add(number_input);

		JLabel name_label = new JLabel("\u59D3\u540D\uFF1A");
		name_label.setBounds(200, 20, 50, 20);
		getContentPane().add(name_label);

		name_input = new JTextField();
		name_input.setBounds(260, 20, 120, 21);
		getContentPane().add(name_input);

		JLabel sex_label = new JLabel("\u6027\u522B\uFF1A");
		sex_label.setBounds(10, 71, 50, 20);
		getContentPane().add(sex_label);

		radioButton_boy = new JRadioButton("\u7537");
		radioButton_boy.setSelected(true);
		radioButton_boy.setBounds(60, 70, 57, 23);
		getContentPane().add(radioButton_boy);

		radioButton_girl = new JRadioButton("\u5973");
		radioButton_girl.setBounds(123, 70, 57, 23);
		getContentPane().add(radioButton_girl);

		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(radioButton_boy);
		buttonGroup.add(radioButton_girl);

		JLabel phone_label = new JLabel("\u7535\u8BDD\uFF1A");
		phone_label.setBounds(200, 70, 50, 20);
		getContentPane().add(phone_label);

		phone_input = new JTextField();
		phone_input.setBounds(260, 70, 120, 21);
		getContentPane().add(phone_input);

		JLabel collage_label = new JLabel("\u5B66\u9662\uFF1A");
		collage_label.setBounds(10, 120, 50, 20);
		getContentPane().add(collage_label);

		collage_input = new JTextField();
		collage_input.setBounds(60, 120, 320, 21);
		getContentPane().add(collage_input);

		JLabel professional_label = new JLabel("\u4E13\u4E1A\uFF1A");
		professional_label.setBounds(10, 160, 50, 20);
		getContentPane().add(professional_label);

		professional_input.setBounds(60, 160, 320, 21);
		professional_input.setModel(new DefaultComboBoxModel<>(Constant.PROFESSIONAL));
		professional_input.setSelectedIndex(-1);
		getContentPane().add(professional_input);

		JLabel birthday_label = new JLabel("\u51FA\u751F\u65E5\u671F\uFF1A");
		birthday_label.setBounds(10, 210, 70, 20);
		getContentPane().add(birthday_label);

		String[] years = new String[year_count];
		for (int i = 0; i < year_count; i++)
		{
			years[i] = String.valueOf(i + 1960);
		}
		year_input.setModel(new DefaultComboBoxModel<>(years));
		year_input.setSelectedIndex(0);
		year_input.setBounds(90, 210, 70, 21);
		getContentPane().add(year_input);

		JLabel year_label = new JLabel("\u5E74");
		year_label.setBounds(170, 210, 30, 20);
		getContentPane().add(year_label);

		String[] months = new String[12];
		for (int i = 0; i < 12; i++)
		{
			months[i] = String.valueOf(i + 1);
		}
		month_input.setModel(new DefaultComboBoxModel<>(months));
		month_input.setSelectedIndex(0);
		month_input.setEnabled(false);
		month_input.setBounds(200, 210, 50, 21);
		getContentPane().add(month_input);

		JLabel month_label = new JLabel("\u6708");
		month_label.setBounds(260, 210, 30, 20);
		getContentPane().add(month_label);

		day_input.setEnabled(false);
		day_input.setBounds(290, 210, 50, 21);
		getContentPane().add(day_input);

		JLabel day_label = new JLabel("\u65E5");
		day_label.setBounds(350, 210, 30, 20);
		getContentPane().add(day_label);

		JLabel address_label = new JLabel("\u5730\u5740\uFF1A");
		address_label.setBounds(10, 260, 50, 20);
		getContentPane().add(address_label);

		address_input = new JTextField();
		address_input.setBounds(60, 260, 320, 20);
		getContentPane().add(address_input);
		address_input.setColumns(10);

		button_done = new JButton("\u786E\u8BA4");
		button_done.setBounds(287, 301, 93, 23);
		getContentPane().add(button_done);

		button_reset = new JButton("\u91CD\u7F6E");
		button_reset.setBounds(170, 301, 93, 23);
		getContentPane().add(button_reset);
	}

	private void monitor()
	{
		year_input.addActionListener(e ->
		{
			month_input.setEnabled(true);
			year = Integer.parseInt(year_input.getSelectedItem().toString());
		});
		month_input.addActionListener(arg0 ->
		{
			month = Integer.parseInt(month_input.getSelectedItem().toString());
			setDays(year, month, day_input);
		});
		day_input.addActionListener(arg0 -> day = Integer.parseInt(day_input.getSelectedItem().toString()));
		radioButton_boy.addChangeListener(arg0 -> sex = 1);
		radioButton_girl.addChangeListener(e -> sex = 2);
		button_done.addActionListener(e ->
		{
			if (InputFormat.isNumber(number_input) && !InputFormat.isEmpty(name_input)
					&& InputFormat.isNumber(phone_input) && !InputFormat.isEmpty(address_input)
					&& !isEmpty(professional_input) && !isEmpty(day_input))
			{
				String number = number_input.getText().trim();
				String name = name_input.getText().trim();
				String professional = professional_input.getSelectedItem().toString();
				String college = collage_input.getText();
				Birthday birthday = new Birthday(year + "-" + month + "-" + day);
				String address = address_input.getText();
				String phone = phone_input.getText().trim();
				if (!SqlUtil.searchStudent("number", number).isEmpty())
				{
					tag = 1;
					localStudent = SqlUtil.searchStudent("number", number).get(0);
				}
				if (tag == 0)
				{
					Student student = new Student(number, name, sex, professional, college, birthday, address,
							phone);
					SqlUtil.insertToTable(Constant.TABLENAME_STUDENT, Constant.COLUMNS_STUDENT, student);
				} else
				{
					Student student = new Student(localStudent.get_id(), number, name, sex, professional, college,
							birthday, address, phone);
					SqlUtil.updateStudent(student);
				}
				JOptionPane.showMessageDialog(null, "信息录入成功！！！");
			} else
			{
				System.out.println(InputFormat.isNumber(number_input));
				System.out.println(!InputFormat.isEmpty(name_input));
				System.out.println(InputFormat.isNumber(phone_input));
				System.out.println(!InputFormat.isEmpty(address_input));
				System.out.println(!isEmpty(professional_input));
				System.out.println(!isEmpty(day_input));
				JOptionPane.showMessageDialog(null, "信息错误！！！");
			}
		});
		button_reset.addActionListener(arg0 ->
		{
			number_input.setText(null);
			name_input.setText(null);
			phone_input.setText(null);
			collage_input.setText(null);
			address_input.setText(null);
			radioButton_boy.setSelected(true);
			professional_input.setSelectedIndex(-1);
			year_input.setSelectedIndex(0);
			month_input.setEnabled(false);
			day_input.setEnabled(false);
		});
	}

	private void setDays(int year, int month, JComboBox<String> day_input)
	{
		int day_count;
		switch (isThirty(month))
		{
		case 0:
			if (year % 4 == 0)
			{
				day_count = 29;
			} else
			{
				day_count = 28;
			}
			break;
		case 1:
			day_count = 31;
			break;
		default:
			day_count = 30;
			break;
		}
		String[] days = new String[day_count];
		for (int i = 0; i < day_count; i++)
		{
			days[i] = String.valueOf(i + 1);
		}
		day_input.setModel(new DefaultComboBoxModel<>(days));
		day_input.setEnabled(true);
	}

	private int isThirty(int month)
	{
		if (month == 2)
			return 0;
		int[] data =
		{ 1, 3, 5, 7, 8, 10, 12 };
		for (int temp : data)
		{
			if (temp == month)
				return 1;
		}
		return -1;
	}

	private <E> boolean isEmpty(JComboBox<E> jComboBox)
	{
		return jComboBox.getSelectedItem() == null;
	}
}
