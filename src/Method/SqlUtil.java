package Method;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import Class.Birthday;
import Class.Score;
import Class.Student;
import Class.Student_Class;
import Class.User;
import Const.Constant;

public class SqlUtil
{
	public static int deleteUser(int id)
	{
		int k = 0;
		Statement statement = getStatement(Constant.DATABASENAME);
		if (statement != null)
		{
			try
			{
				k = statement.executeUpdate("delete from " + Constant.TABLENAME_USER + " where _id=" + id + ";");
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return k;
	}

	public static int deleteClass(int id)
	{
		int k = 0;
		Statement statement = getStatement(Constant.DATABASENAME);
		if (statement != null)
		{
			try
			{
				k = statement.executeUpdate("delete from " + Constant.TABLENAME_CLASS + " where _id=" + id + ";");
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return k;
	}

	public static int deleteStudent(String columns, String data)
	{
		int k = 0;
		Statement statement = getStatement(Constant.DATABASENAME);
		if (statement != null)
		{
			try
			{
				k = statement.executeUpdate(
						"delete from " + Constant.TABLENAME_STUDENT + " where " + columns + "=" + data + ";");
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return k;
	}

	public static List<Student> searchStudent()
	{
		return searchStudent(null, null);
	}

	public static List<Student> searchStudent(String columns, String data)
	{
		List<Student> students = new ArrayList<>();
		Statement statement = getStatement(Constant.DATABASENAME);
		ResultSet resultSet = null;
		try
		{
			if (columns != null && data != null)
			{
				resultSet = statement.executeQuery(
						"select * from " + Constant.TABLENAME_STUDENT + " where " + columns + " like '" + data + "'");
			} else
			{
				resultSet = statement.executeQuery("select * from " + Constant.TABLENAME_STUDENT);
			}
			while (resultSet.next())
			{
				int id = resultSet.getInt("_id");
				String number = resultSet.getString("number");
				String name = resultSet.getString("name");
				int sex = resultSet.getShort("sex");
				String professional = resultSet.getString("professional");
				String college = resultSet.getString("college");
				Birthday birthday;
				birthday = new Birthday(resultSet.getString("birthday"));
				String address = resultSet.getString("address");
				String phone = resultSet.getString("phone");
				Student student = new Student(id, number, name, sex, professional, college, birthday, address, phone);
				students.add(student);
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Êý¾Ý¿â´íÎó£¡£¡£¡");
		}
		return students;
	}

	public static ResultSet searchUser(User user)
	{
		Statement statement = getStatement(Constant.DATABASENAME);
		ResultSet resultSet = null;
		try
		{
			resultSet = statement.executeQuery(
					"select * from " + Constant.TABLENAME_USER + " where username='" + user.getUsername() + "'");
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultSet;
	}

	public static List<Student_Class> getStudentClass()
	{
		List<Student_Class> list = new ArrayList<>();
		Statement statement = getStatement(Constant.DATABASENAME);
		ResultSet resultSet = null;
		try
		{
			resultSet = statement.executeQuery("select * from " + Constant.TABLENAME_CLASS + ";");
			while (resultSet.next())
			{
				int id = resultSet.getInt("_id");
				String name = resultSet.getString("name");
				String code = resultSet.getString("code");
				list.add(new Student_Class(id, name, code));
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public static List<Score> getScore(String number)
	{
		List<Score> list = new ArrayList<>();
		Statement statement = getStatement(Constant.DATABASENAME);
		ResultSet resultSet = null;
		try
		{
			resultSet = statement
					.executeQuery("select * from " + Constant.TABLENAME_SCORE + " where number=" + number + ";");
			while (resultSet.next())
			{
				int id = resultSet.getInt("_id");
				String code = resultSet.getString("code");
				int score = resultSet.getInt("score");
				list.add(new Score(id, number, code, score));
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Êý¾Ý¿â´íÎó£¡£¡£¡");
		}
		return list;
	}

	public static List<Student_Class> getClass(String code)
	{
		List<Student_Class> list = new ArrayList<>();
		Statement statement = getStatement(Constant.DATABASENAME);
		ResultSet resultSet = null;
		try
		{
			resultSet = statement
					.executeQuery("select * from " + Constant.TABLENAME_CLASS + " where code='" + code + "';");
			while (resultSet.next())
			{
				int id = resultSet.getInt("_id");
				String name = resultSet.getString("name");
				list.add(new Student_Class(id, name, code));
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public static List<User> getUser()
	{
		List<User> list = new ArrayList<>();
		Statement statement = getStatement(Constant.DATABASENAME);
		ResultSet resultSet = null;
		try
		{
			resultSet = statement.executeQuery("select * from " + Constant.TABLENAME_USER + ";");
			while (resultSet.next())
			{
				int id = resultSet.getInt("_id");
				String username = resultSet.getString("username");
				String password = resultSet.getString("password");
				boolean manager = resultSet.getBoolean("manager");
				list.add(new User(id, username, password, manager));
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public static String getClassName(String code)
	{
		Statement statement = getStatement(Constant.DATABASENAME);
		String name = null;
		try
		{
			ResultSet resultSet = statement.executeQuery("select * from class where code=" + code + ";");
			while (resultSet.next())
			{
				name = resultSet.getString("name");
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return name;
	}

	public static int insertToTable(String tableName, String columns, Object data)
	{
		int k = 0;
		Statement statement = getStatement(Constant.DATABASENAME);
		if (statement != null)
		{
			try
			{
				k = statement.executeUpdate("insert into " + tableName + columns + " values(" + data + ");");
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return k;
	}

	public static int updateStudent(Student student)
	{
		int k = 0;
		Statement statement = getStatement(Constant.DATABASENAME);
		if (statement != null)
		{
			try
			{
				k = statement.executeUpdate("update " + Constant.TABLENAME_STUDENT + " " + student.update()
						+ " where _id=" + student.get_id() + ";");
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return k;
	}

	public static int updateClass(Student_Class student)
	{
		int k = 0;
		Statement statement = getStatement(Constant.DATABASENAME);
		if (statement != null)
		{
			try
			{
				k = statement.executeUpdate("update " + Constant.TABLENAME_CLASS + " " + student.update()
						+ " where _id=" + student.get_id() + ";");
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return k;
	}

	public static int updateUser(User user)
	{
		int k = 0;
		Statement statement = getStatement(Constant.DATABASENAME);
		if (statement != null)
		{
			try
			{
				k = statement.executeUpdate("update " + Constant.TABLENAME_USER + " " + user.update() + " where _id="
						+ user.get_id() + ";");
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return k;
	}

	public static int update(Score score)
	{
		int k = 0;
		Statement statement = getStatement(Constant.DATABASENAME);
		if (statement != null)
		{
			try
			{
				k = statement.executeUpdate("update " + Constant.TABLENAME_SCORE + " " + score.update() + " where _id="
						+ score.get_id() + ";");
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return k;
	}

	public static int createDatabase(String databaseName)
	{
		int k = 0;
		Statement statement = getStatement(null);
		if (statement != null)
		{
			try
			{
				k = statement.executeUpdate("create database if not exists " + databaseName + ";");
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return k;
	}

	public static int createTable(String databaseName, String tableName, String columns)
	{
		int k = 0;
		Statement statement = getStatement(databaseName);
		if (statement != null)
		{
			try
			{
				k = statement.executeUpdate("create table if not exists " + tableName + " (" + columns + ");");
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return k;
	}

	private static Statement getStatement(String databasename)
	{
		Statement statement = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = null;
			if (databasename == null)
				connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "root");
			else
			{
				connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databasename,
						"root", "root");
			}
			statement = (Statement) connection.createStatement();
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return statement;
	}
}
