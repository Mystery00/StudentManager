package Method;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;

/**
 * �ж������Ƿ�Ϸ�
 * @author mystery0
 *
 */
public class InputFormat
{
	public static boolean isEmpty(JTextField textField)
	{
		return textField.getText().length()==0;
	}
	
	public static boolean isNumber(JTextField textField)
	{
		Pattern pattern=Pattern.compile("[0-9]+");
		Matcher matcher=pattern.matcher(textField.getText().toString().trim());
		return matcher.matches()&&!isEmpty(textField);
	}
}
