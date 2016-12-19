package Class;

public class Score
{
	private int _id;
	private String number;
	private String code;
	private int score;

	public Score(int _id, String number, String code, int score)
	{
		this._id = _id;
		this.number = number;
		this.code = code;
		this.score = score;
	}

	public Score(String number, String code, int score)
	{
		this(-1, number, code, score);
	}

	public int get_id()
	{
		return _id;
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
