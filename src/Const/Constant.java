package Const;

public class Constant
{
	public final static String DATABASENAME = "student";
	public final static String TABLENAME_USER = "user";
	public final static String TABLENAME_STUDENT = "student";
	public final static String TABLENAME_SCORE = "score";
	public final static String TABLENAME_CLASS="class";
	public final static String TABLE_COLUMNS_USER = "username text,password text,manager bool";
	public final static String TABLE_COLUMNS_STUDENT = "number char(14),name text,sex integer,professional text,college text,birthday text,address text,phone char(12)";
	public final static String TABLE_COLUMNS_SCORE = "number char(14),code char(10),score int";
	public final static String TABLE_COLUMNS_CLASS="name text,code text";

	public static final String[] STUDENT_COLUMNS =
	{ "学号", "姓名", "性别", "专业", "学院", "出生日期", "家庭住址", "电话" };
	public static final String[] DATABASE_CODE={"num","name","sex","professional","college","birthday","address","phone"};
	public static final String[] PROFESSIONAL={"计算机科学与技术","软件工程","物联网工程"};
	public static final String[] CLASS={"课程名称","课程成绩"};
}
