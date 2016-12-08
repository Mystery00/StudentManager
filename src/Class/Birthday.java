package Class;

import java.util.StringTokenizer;

public class Birthday
{
	private int year;
	private int month;
	private int day;

	public Birthday(int year, int month, int day)
	{
		this.year = year;
		this.month = month;
		this.day = day;
	}
	
	public Birthday(String date)
	{
		// TODO Auto-generated constructor stub
		date=date.replaceAll("-", " ");
		StringTokenizer stringTokenizer=new StringTokenizer(date);
		int[] tokens=new int[3];
		int i=0;
		while(stringTokenizer.hasMoreTokens())
		{
			tokens[i]=Integer.parseInt(stringTokenizer.nextToken());
			i++;
		}
		this.year=tokens[0];
		this.month=tokens[1];
		this.day=tokens[2];
	}

	@Override
	public String toString()
	{
		return year + "-" + month + "-" + day;
	}

}
