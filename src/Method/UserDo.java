package Method;

import java.sql.ResultSet;
import java.sql.SQLException;

import Class.User;

public class UserDo
{
	public static boolean isTrue(String username,String password)
	{
		User user=new User(username, password);
		ResultSet set=SqlUtil.searchUser(user);
		boolean result;
		try
		{
			while(set.next())
			{
				System.out.println(set.getString("password"));
				result=(set.getString("password")==password);
				System.out.println(result);
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
