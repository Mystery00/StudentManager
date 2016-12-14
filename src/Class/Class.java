package Class;

public class Class
{
	private String number;
	private String code;
	private int score;

	public Class(String number, String code, int score)
	{
		this.number = number;
		this.code = code;
		this.score = score;
	}

	public String getNumber()
	{
		return number;
	}

	public void setNumber(String number)
	{
		this.number = number;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public int getScore()
	{
		return score;
	}

	public void setScore(int score)
	{
		this.score = score;
	}

	@Override
	public String toString()
	{
		return "'" + number + "','" + code + "'," + score;
	}

}
