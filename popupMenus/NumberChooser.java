package popupMenus;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class NumberChooser extends AbstractValueSelector implements ChangeListener, ActionListener  {
	/**
	 * The current value of the spinner
	 */
	private int value;
	GridLayout lay;
	JButton accept;
	JSpinner numberChooser;
	final SpinnerNumberModel numModel = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
	
	public NumberChooser()
	{
		createForm();
		createInputField();
	}
	
	/**
	 * Creates the spinner to choose the number value
	 */
	public void createInputField()
	{
		lay = new GridLayout(1,2);
		pane.setLayout(lay);
		numberChooser = new JSpinner(numModel);
		numberChooser.addChangeListener(this);
		pane.add(numberChooser);
		accept = new JButton("OK");
		accept.addActionListener(this);
		pane.add(accept);
	}

	/**
	 * Set int value to the value of the numberChooser
	 */
	void setValue() 
	{
		value = (int)numberChooser.getValue();
	}
	
	/**
	 * Gets the value of the spinner
	 * @return Integer value of the spinner
	 */
	public int getValue()
	{
		return(value);
	}
	
	/*
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == numberChooser)
			setValue();
		else if(e.getSource() == accept)
		{
			setValue();
			System.out.println(value);
			hide();
		}
	}
	*/
	
	@Override
	public void stateChanged(ChangeEvent arg0) {
		setValue();
		System.out.println(getValue());
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		setValue();
		hide();
		System.out.println(getValue());
	}

	@Override
	void displayForm() {
		
	}
}
