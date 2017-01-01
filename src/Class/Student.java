package Class;

public class Student
{
	private int _id;
	private String number;
	private String name;
	private int sex;
	private String professional;
	private String college;
	private Birthday birthday;
	private String address;
	private String phone;

	public Student(int _id, String number, String name, int sex, String professional, String college, Birthday birthday,
			String address, String phone)
	{
		this._id = _id;
		this.number = number;
		this.name = name;
		this.sex = sex;
		this.professional = professional;
		this.college = college;
		this.birthday = birthday;
		this.address = address;
		this.phone = phone;
	}

	public Student(String number, String name, int sex, String professional, String college, Birthday birthday,
			String address, String phone)
	{
		this(-1, number, name, sex, professional, college, birthday, address, phone);
	}
	
	public int get_id()
	{
		return _id;
	}

	public String getNumber()
	{
		return number;
	}

	public String getName()
	{
		return name;
	}

	public int getSex()
	{
		return sex;
	}

	public String getProfessional()
	{
		return professional;
	}

	public String getCollege()
	{
		return college;
	}

	public Birthday getBirthday()
	{
		return birthday;
	}

	public String getAddress()
	{
		return address;
	}

	public String getPhone()
	{
		return phone;
	}

	@Override
	public String toString()
	{
		return "'" + number + "','" + name + "'," + sex + ",'" + professional + "','" + college + "','" + birthday
				+ "','" + address + "','" + phone + "'";
	}

	public String update()
	{
		return "set number='" + number + "',name='" + name + "',sex=" + sex + ",professional='" + professional
				+ "',college='" + college + "',birthday='" + birthday + "',address='" + address + "',phone='" + phone
				+ "'";
	}

}
