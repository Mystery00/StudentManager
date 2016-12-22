package Const;

public class Constant
{
	public final static String DATABASENAME = "student";
	public final static String TABLENAME_USER = "user";
	public final static String TABLENAME_STUDENT = "student";
	public final static String TABLENAME_SCORE = "score";
	public final static String TABLENAME_CLASS = "class";
	public final static String TABLE_COLUMNS_USER = "_id integer not null primary key auto_increment,username text,password text,manager bool";
	public final static String TABLE_COLUMNS_STUDENT = "_id integer not null primary key auto_increment,number char(14),name text,sex integer,professional text,college text,birthday text,address text,phone char(12)";
	public final static String TABLE_COLUMNS_SCORE = "_id integer not null primary key auto_increment,number char(14),code char(10),score int";
	public final static String TABLE_COLUMNS_CLASS = "_id integer not null primary key auto_increment,name text,code text";
	public final static String COLUMNS_USER = "(username,password,manager)";
	public final static String COLUMNS_STUDENT = "(number,name,sex,professional,college,birthday,address,phone)";
	public final static String COLUMNS_SCORE = "(number,code,score)";
	public final static String COLUMNS_CLASS = "(name,code)";

	public static final String[] DATABASE_CODE_STUDENT =
	{ "number", "name", "sex", "professional", "college", "birthday", "address", "phone" };
	public static final String[] DATABASE_CODE_USER =
	{ "username", "password","manager" };
	public static final String[] DATABASE_CODE_CLASS =
	{ "name", "code" };
	public static final String[] STUDENT =
	{ "学号", "姓名", "性别", "专业", "学院", "出生日期", "家庭住址", "电话" };
	public static final String[] PROFESSIONAL =
	{ "计算机科学与技术", "软件工程", "物联网工程" };
	public static final String[] CLASS =
	{ "课程名称", "课程代码" };
	public static final String[] SCORE =
	{ "课程名称", "课程成绩" };
	public static final String[] USER =
	{ "用户名", "密码", "管理员身份" };
}
