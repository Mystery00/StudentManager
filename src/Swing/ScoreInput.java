package Swing;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Class.Score;
import Class.Student_Class;
import Const.Constant;
import Method.InputFormat;
import Method.SqlUtil;

import javax.swing.JComboBox;
import javax.swing.JButton;

import java.util.List;

import javax.swing.DefaultComboBoxModel;

public class ScoreInput extends JDialog
{
    private static final long serialVersionUID = 1L;
    private JTextField number_input;
    private JTextField score_input;
    private JComboBox<Object> code_input;
    private JButton btn_done;
    private JButton btn_reset;
    private List<Student_Class> list;
    private int tag = 0;
    private Score score;

    public ScoreInput()
    {
        initialize();
        monitor();
    }

    public ScoreInput(Score score)
    {
        this();
        tag = 1;
        this.score = score;
        setDefault(score);
    }

    private void setDefault(Score score)
    {
        number_input.setText(score.getNumber());
        score_input.setText(String.valueOf(score.getScore()));
        for (int i = 0; i < list.size(); i++)
        {
            if (list.get(i).getCode().equals(score.getCode()))
            {
                code_input.setSelectedIndex(i);
                break;
            }
        }
    }

    private void initialize()
    {
        list = SqlUtil.getStudentClass();
        String[] classs = new String[list.size()];
        for (int i = 0; i < list.size(); i++)
        {
            classs[i] = list.get(i).getName();
        }

        setTitle("\u6210\u7EE9\u5F55\u5165");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setVisible(true);
        setBounds(100, 100, 245, 190);
        getContentPane().setLayout(null);

        JLabel code_label = new JLabel("\u8BFE\u7A0B\u540D\u79F0\uFF1A");
        code_label.setBounds(10, 10, 80, 15);
        getContentPane().add(code_label);

        code_input = new JComboBox<>();
        code_input.setModel(new DefaultComboBoxModel<>(classs));
        code_input.setBounds(100, 7, 120, 21);
        code_input.setSelectedIndex(-1);
        getContentPane().add(code_input);

        JLabel number_label = new JLabel("\u5B66\u53F7\uFF1A");
        number_label.setBounds(10, 50, 80, 15);
        getContentPane().add(number_label);

        number_input = new JTextField();
        number_input.setBounds(100, 47, 120, 21);
        getContentPane().add(number_input);

        JLabel score_label = new JLabel("\u6210\u7EE9\uFF1A");
        score_label.setBounds(10, 90, 80, 15);
        getContentPane().add(score_label);

        score_input = new JTextField();
        score_input.setBounds(100, 87, 120, 21);
        getContentPane().add(score_input);

        btn_done = new JButton("\u786E\u8BA4");
        btn_done.setBounds(126, 118, 93, 23);
        getContentPane().add(btn_done);

        btn_reset = new JButton("\u91CD\u7F6E");
        btn_reset.setBounds(10, 118, 93, 23);
        getContentPane().add(btn_reset);
    }

    private void monitor()
    {
        btn_done.addActionListener(arg0 ->
        {
            if (InputFormat.isNumber(score_input) && InputFormat.isNumber(number_input))
            {
                Student_Class sClass = list.get(code_input.getSelectedIndex());
                String number = number_input.getText();
                int score = Integer.parseInt(score_input.getText());
                if (tag == 0)
                {
                    SqlUtil.insertToTable(Constant.TABLENAME_SCORE, Constant.COLUMNS_SCORE,
                            new Score(number, sClass.getCode(), score));
                } else
                {
                    SqlUtil.update(new Score(this.score.get_id(), number, sClass.getCode(), score));
                }
                JOptionPane.showMessageDialog(null, "录入成功！");
            } else
            {
                JOptionPane.showMessageDialog(null, "格式错误！！！");
            }
        });
        btn_reset.addActionListener(e ->
        {
            number_input.setText(null);
            code_input.setSelectedIndex(-1);
            score_input.setText(null);
        });
    }
}
