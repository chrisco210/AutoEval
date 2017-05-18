package popupMenus;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import mainPackage.GUI;

public class NumberChooser extends AbstractValueSelector implements ActionListener {
	private int value;
	GridLayout lay;
	JButton accept;
	JSpinner numberChooser;
	final SpinnerNumberModel numModel = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
	
	
	public void createInputField()
	{
		lay = new GridLayout(1,2);
		pane.setLayout(lay);
		numberChooser = new JSpinner(numModel);
		pane.add(numberChooser);
		accept = new JButton("OK");
		accept.addActionListener(this);
		pane.add(accept);
		
	}
	public Integer getValue()
	{
		setValue();
		return(value);
	}

	void setValue() 
	{
		value = (int)numberChooser.getValue();
	}

	public void actionPerformed(ActionEvent e) 
	{
		System.out.println(value);
		hide();
	}
}
