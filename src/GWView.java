

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.Border;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class GWView extends JFrame{
	
	private JPanel leftPanel;
	private JPanel centerPanel;
	private JPanel rightPanel;
	private JPanel mainPanel;
	private JComboBox comboBox;
	private ImageIcon image1;
	private Double tempdiff = 0.0;
	


	public GWView() {
		
		super("Global Warming");
		setSize(1000, 340);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		leftPanel = new JPanel();
		centerPanel = new JPanel();
		rightPanel = new JPanel();
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		
		createleftPanel();
		
		Border blackline = BorderFactory.createLineBorder(Color.black);
		add(leftPanel, BorderLayout.WEST);
		leftPanel.setBorder(blackline);
		add(centerPanel, BorderLayout.CENTER);
		add(rightPanel, BorderLayout.EAST);
		
		setVisible(true);
	
	}
	
	private void createleftPanel() {
	
		JPanel panel1 = new JPanel();
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
		
		JPanel panel2 = new JPanel();
		
		JPanel panel3 = new JPanel();
		panel3.setLayout(new FlowLayout());
		
		JPanel panel4 = new JPanel();
		
		//PANEL 1
		JLabel title = new JLabel("TEMPERATURE DIFFERENCE CALCULATOR BY YEAR");
		title.setFont(new Font("Times New Roman", Font.BOLD, 15));
		title.setAlignmentX(CENTER_ALIGNMENT);
		JLabel instruction = new JLabel("Pick a Country or Territory:");
		instruction.setAlignmentX(CENTER_ALIGNMENT);
		JLabel instruction2 = new JLabel("Enter a year between 1961 and 2022");
		instruction2.setAlignmentX(CENTER_ALIGNMENT);
		JLabel year1 = new JLabel("Year 1");
		year1.setAlignmentX(CENTER_ALIGNMENT);
		JLabel year2 = new JLabel("Year 2");
		year2.setAlignmentX(CENTER_ALIGNMENT);
		
		JTextField field1 = new JTextField();
		field1.setPreferredSize(new Dimension (100, 30));
		JTextField field2 = new JTextField();
		field2.setPreferredSize(new Dimension (100,30));

		//get string array of all countries
		//using library object
		Library june = new Library();
		int n = june.indexlist.size();
		String[] list = new String[n];
		for (int i = 0; i<n; i++) {
			list[i] = june.indexlist.get(i);
		}
		comboBox = new JComboBox(list);
		
		//PANEL 2
		JButton submit = new JButton("SUBMIT");
		
		//PANEL 3
		JLabel difference = new JLabel("Difference in temperature:");
		JLabel differencenumber = new JLabel(String.valueOf(tempdiff));
		
		//PANEL 4 - for radio buttons
		JRadioButton radio1 = new JRadioButton();
		JLabel celsius = new JLabel("Convert to Celsius");
		JRadioButton radio2 = new JRadioButton();
		JLabel fahrenheit = new JLabel("Convert to Fahrenheit");
		
		panel1.add(title);
		
		panel1.add(instruction);
		
		panel1.add(comboBox);
		
		panel1.add(instruction2);
	
		panel1.add(year1);
	
		panel1.add(field1);
		
		panel1.add(year2);
		
		panel1.add(field2);
		
		panel1.add(panel2);
		
		panel1.add(panel3);
		
		panel1.add(panel4);
		
		panel2.add(submit);
		
		panel3.add(difference);
		panel3.add(differencenumber);
		panel4.add(radio1);
		panel4.add(celsius);
		panel4.add(radio2);
		panel4.add(fahrenheit);
		
		leftPanel.add(panel1);
		
	}
	
	private void createmiddlePanel() {
		
	}
	
	public static void main(String[] args)
	   {
		//create a VIew object
		  GWView view = new GWView();
		  
	   }
	
}
