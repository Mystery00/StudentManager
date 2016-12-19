package Class;

public class User
{
	private int _id;
	private String username;
	private String password;
	private boolean manager;

	public User(String username, String password)
	{
		this(-1, username, password, false);
	}

	public User(int _id, String username, String password, boolean manager)
	{
		this._id = _id;
		this.username = username;
		this.password = password;
		this.manager = manager;
	}
	
	public int get_id()
	{
		return _id;
	}

	public String getUsername()
	{
		return "'" + username + "'";
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return "'" + password + "'";
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public boolean isManager()
	{
		return manager;
	}

	@Override
	public String toString()
	{
		return "'" + username + "','" + password + "'," + manager;
	}

}
