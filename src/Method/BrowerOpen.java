package Method;

import java.awt.Desktop;
import java.awt.Desktop.Action;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class BrowerOpen
{
	public static void openUrl(String url)
	{
		Desktop desktop = Desktop.getDesktop();
		if (desktop.isSupported(Action.BROWSE))
		{
			try
			{
				desktop.browse(new URI(url));
			} catch (IOException | URISyntaxException e)
			{
				e.printStackTrace();
			}
		}
	}
}
