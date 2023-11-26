

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.Border;


public class GWView extends JFrame{
	
	private JPanel leftPanel;
	private JPanel centerPanel;
	private JPanel rightPanel;
	private JPanel mainPanel;
	private JComboBox comboBox;
	GridBagConstraints gbc = new GridBagConstraints();

	public GWView() {
	
		super("Global Warming");
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainPanel = new JPanel();
		
		leftPanel = new JPanel();
		centerPanel = new JPanel();
		rightPanel = new JPanel();
		
		createleftPanel();
		
		
		Border blackline = BorderFactory.createLineBorder(Color.black);
		add(leftPanel, BorderLayout.WEST);
		leftPanel.setBorder(blackline);
		add(centerPanel, BorderLayout.CENTER);
		add(rightPanel, BorderLayout.EAST);
		
		
		setVisible(true);
	
	}
	
	private void createleftPanel() {
		
		//create top panel
		JPanel panel1 = new JPanel();
		
		
		//create center panel
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridBagLayout());
		
		//create bottom panel
		JPanel panel3 = new JPanel();
		
		
		
		//PANEL 1
		JLabel title = new JLabel("TEMPERATURE DIFFERENCE CALCULATOR BY YEAR");
		title.setFont(new Font("Times New Roman", Font.BOLD, 15));
		title.setAlignmentX(CENTER_ALIGNMENT);
		
		//PANEL 2
		JLabel instruction = new JLabel("Pick a Country:");
		instruction.setAlignmentX(CENTER_ALIGNMENT);
		JLabel year1 = new JLabel("Year 1");
		year1.setAlignmentX(CENTER_ALIGNMENT);
		JLabel year2 = new JLabel("Year 2");
		year2.setAlignmentX(CENTER_ALIGNMENT);
		
		JTextField field1 = new JTextField();
		field1.setPreferredSize(new Dimension (100, 30));
		JTextField field2 = new JTextField();
		field2.setPreferredSize(new Dimension (100,30));
		
//		BoxLayout layout = new BoxLayout(leftPanel, BoxLayout.Y_AXIS);
//		leftPanel.setLayout(layout);
		
		//get string array of all countries
		//using library object
		Library june = new Library();
		int n = june.indexlist.size();
		String[] list = new String[n];
		for (int i = 0; i<n; i++) {
			list[i] = june.indexlist.get(i);
		}
		comboBox = new JComboBox(list);
		
		//PANEL 3
		/**
		 
		 **/
		
		
		panel1.add(title, BorderLayout.CENTER);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel2.add(instruction, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		panel2.add(comboBox, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		panel2.add(year1, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		panel2.add(field1, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		panel2.add(year2, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 5;
		panel2.add(field2, gbc);
		
		leftPanel.add(panel1);
		leftPanel.add(panel2);
		
	}
	
	public static void main(String[] args)
	   {
		//create a VIew object
		  GWView view = new GWView();
		  
	   }
	
}
