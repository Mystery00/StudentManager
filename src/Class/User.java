package Class;

public class User
{
	private String username;
	private String password;
	private boolean manager;
	
	
	
	public User(String username, String password)
	{
		this(username, password, false);
	}
	public User(String username, String password, boolean manager)
	{
		this.username = username;
		this.password = password;
		this.manager = manager;
	}
	public String getUsername()
	{
		return "'"+username+"'";
	}
	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return "'"+password+"'";
	}

	public void setPassword(String password)
	{
		this.password = password;
	}
	@Override
	public String toString()
	{
		return "'" + username + "','" + password + "'," + manager;
	}
	
}
