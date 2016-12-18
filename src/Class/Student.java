package Class;

public class Student
{
	private String number;
	private String name;
	private int sex;
	private String professional;
	private String college;
	private Birthday birthday;
	private String address;
	private String phone;

	public Student(String number, String name, int sex, String professional, String college, Birthday birthday,
			String address, String phone)
	{
		this.number = number;
		this.name = name;
		this.sex = sex;
		this.professional = professional;
		this.college = college;
		this.birthday = birthday;
		this.address = address;
		this.phone = phone;
	}

	public String getNumber()
	{
		return number;
	}

	public void setNumber(String number)
	{
		this.number = number;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getSex()
	{
		return sex;
	}

	public void setSex(int sex)
	{
		this.sex = sex;
	}

	public String getProfessional()
	{
		return professional;
	}

	public void setProfessional(String professional)
	{
		this.professional = professional;
	}

	public String getCollege()
	{
		return college;
	}

	public void setCollege(String college)
	{
		this.college = college;
	}

	public Birthday getBirthday()
	{
		return birthday;
	}

	public void setBirthday(Birthday birthday)
	{
		this.birthday = birthday;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	@Override
	public String toString()
	{
		return "'" + number + "','" + name + "'," + sex + ",'" + professional
				+ "','" + college + "','" + birthday + "','" + address + "','" + phone + "'";
	}
	
	public String update()
	{
		return "set number='"+number+"',name='"+name+"',sex="+sex+",professional='"+professional+"',college='"+college+"',birthday='"+birthday+"',address='"+address+"',phone='"+phone+"'";
	}

}
