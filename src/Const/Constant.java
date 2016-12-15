package Const;

public class Constant
{
	public final static String DATABASENAME = "student";
	public final static String TABLENAME_USER = "user";
	public final static String TABLENAME_STUDENT = "student";
	public final static String TABLENAME_SCORE = "score";
	public final static String TABLENAME_CLASS="class";
	public final static String TABLE_COLUMNS_USER = "username text,password text,manager bool";
	public final static String TABLE_COLUMNS_STUDENT = "num char(14),name text,sex integer,professional text,college text,birthday text,address text,phone char(12)";
	public final static String TABLE_COLUMNS_SCORE = "num char(14),code char(10),score int";
	public final static String TABLE_COLUMNS_CLASS="name text,code text";

	public static final String[] STUDENT_COLUMNS =
	{ "ѧ��", "����", "�Ա�", "רҵ", "ѧԺ", "��������", "��ͥסַ", "�绰" };
	public static final String[] DATABASE_CODE={"num","name","sex","professional","college","birthday","address","phone"};
	public static final String[] PROFESSIONAL={"�������ѧ�뼼��","�������","����������"};
	public static final String[] CLASS={"�γ̴���","�γ̳ɼ�"};
}
