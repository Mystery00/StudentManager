package Method;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class TableRefreshNotify
{
	public static void refresh(JTable table, Object[][] datas, Object[] columns)
	{
		TableModel tableModel=table.getModel();
		if (tableModel != null)
		{
			((DefaultTableModel) tableModel).getDataVector().clear();
		}
		DefaultTableModel defaultTableModel = new DefaultTableModel(datas, columns);
		table.setModel(defaultTableModel);
	}
}
