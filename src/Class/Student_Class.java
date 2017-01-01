package Class;

public class Student_Class
{
	private int _id;
	private String name;
	private String code;

	public Student_Class(int _id, String name, String code)
	{
		this._id = _id;
		this.name = name;
		this.code = code;
	}

	public Student_Class(String name, String code)
	{
		this(-1, name, code);
	}

	public int get_id()
	{
		return _id;
	}

	public String getName()
	{
		return name;
	}

	public String getCode()
	{
		return code;
	}

	@Override
	public String toString()
	{
		return "'" + name + "','" + code + "'";
	}

	public String update()
	{
		return "set name='" + name + "',code='" + code + "'";
	}
}
