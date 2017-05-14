package mainPackage;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;

public class GUI extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	JMenuBar topMenu;
	JMenu file;
	JMenuItem open;
	JLabel statusLabel;
	public File source;
	
	public static void main(String[] args)
	{
		new GUI();     
	}
	public GUI()
	{
        setSize(500,500);        
        setDefaultCloseOperation(EXIT_ON_CLOSE);    
		Container pane = getContentPane();
		pane.setLayout(new BorderLayout());
		
		//Menu Bar
		topMenu = new JMenuBar();
		open = new JMenuItem("Open");
		open.addActionListener(this);
		file = new JMenu("File");
		file.add(open);
		topMenu.add(file);
		pane.add(topMenu, BorderLayout.NORTH);
		
		//Status Bar
		statusLabel = new JLabel();
		statusLabel.setText("Done.");
		pane.add(statusLabel, BorderLayout.SOUTH);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		//Read menu bar inputs
		if(e.getSource() == open){
			JFileChooser fc = new JFileChooser();
			int i = fc.showOpenDialog(this);
			if(i == JFileChooser.APPROVE_OPTION)
			{
				source = fc.getSelectedFile();
				System.out.println(source.getPath());
			}
		}
	}
	
	public void setStatus(String s)		//update the status bar
	{
		statusLabel.setText(s);
	}
	
}
