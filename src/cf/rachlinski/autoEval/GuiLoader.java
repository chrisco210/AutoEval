package cf.rachlinski.autoEval;

import cf.rachlinski.autoEval.gui.*;
import cf.rachlinski.autoEval.gui.MenuBar;
import cf.rachlinski.autoEval.popupMenus.NumberChooser;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.IOException;

public final class GuiLoader implements Runnable {
	private static final int WINDOW_WIDTH = 1024;
	private static final int WINDOW_HEIGHT = 720;

	@Override
	public void run() 
	{
		GuiController controller = new GuiController();

		GUI g = new GUI(
				"AutoEval",
				new Dimension(GuiLoader.WINDOW_WIDTH, GuiLoader.WINDOW_HEIGHT),
				null,
				new StatusBar(),
				new ConsolePane(),
				null,
				new JTree(),
				null,
				new NumberChooser()
		);

		g.setCenterPane(new CenterTabPane(g));
		g.setTopMenu(new MenuBar(g.getListener()));


		//TODO catch exceptions
		try
		{
			g.assembleComponents(new LayoutMap(this.getClass().getResourceAsStream("/resources/layout.xml")));
		}
		catch (ParserConfigurationException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (SAXException e)
		{
			e.printStackTrace();
		}

		controller.setWindow(g);

		controller.setVisible(true);

		controller.getWindow().setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}
