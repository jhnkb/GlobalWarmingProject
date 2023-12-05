

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	private JRadioButton radio1;
	private JRadioButton radio2;
	private JComboBox comboBox;
	private ImageIcon islandimage;
	
	private Integer yearhot = 0000;
	private Integer yearcold = 0000;
	private Double hottesttemp = 0.0;
	private Double coldesttemp = 0.0;
	private Double ratediff;
	private JLabel differencenumber;
	private Double tempdiff;
	private Library library = new Library();
	
	
	

	public GWView() {
		
		
		super("Global Warming");
		setSize(1450, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		leftPanel = new JPanel();
		centerPanel = new JPanel();
		rightPanel = new JPanel();
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		
		createleftPanel();
		createmiddlePanel();
		createrightPanel();
		
		Border blackline = BorderFactory.createLineBorder(Color.black);
		add(leftPanel, BorderLayout.WEST);
		leftPanel.setBorder(blackline);
		add(centerPanel, BorderLayout.CENTER);
		centerPanel.setBorder(blackline);
		add(rightPanel, BorderLayout.EAST);
		rightPanel.setBorder(blackline);
		
		setVisible(true);
	
	}
	
	private void createleftPanel() {
		
		
		islandimage = new ImageIcon(getClass().getResource("islandimage.png"));
		JLabel image = new JLabel(islandimage);
		
		JLabel caption = new JLabel("This country or territory is comprised of island(s)");
	
		JPanel panel1 = new JPanel();
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
		
		JPanel panel2 = new JPanel();
		
		JPanel panel3 = new JPanel();
		panel3.setLayout(new FlowLayout());
		
		JPanel panel4 = new JPanel();
		
		JPanel panel5 = new JPanel();
		panel5.setLayout(new FlowLayout());
		
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
		submit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String place = (String) comboBox.getSelectedItem();
				Integer year1 = Integer.valueOf(field1.getText());
				Integer year2 = Integer.valueOf(field2.getText());
				
				Double temp1 = library.getTemp(place, year1);
				Double temp2 = library.getTemp(place, year2);
				tempdiff = temp1 - temp2;
				
				differencenumber.setText(String.valueOf(tempdiff));
				
				if radio1.isSelected() {
					
				}
			}
			
		});
		
		//PANEL 3
		JLabel difference = new JLabel("Difference in temperature:");
		differencenumber = new JLabel(String.valueOf(tempdiff));
		
		//PANEL 4 - for radio buttons
		radio1 = new JRadioButton();
		JLabel celsius = new JLabel("Show in Celsius");
		radio2 = new JRadioButton();
		JLabel fahrenheit = new JLabel("Show in Fahrenheit");
		
		panel1.add(title);
		
		panel1.add(Box.createRigidArea(new Dimension(0,10)));
		
		panel1.add(instruction);
		
		panel1.add(comboBox);
		
		panel1.add(panel5);
		
		panel1.add(instruction2);
	
		panel1.add(year1);
	
		panel1.add(field1);
		
		panel1.add(year2);
		
		panel1.add(field2);
		
		
		
		panel1.add(panel3);
		
		panel1.add(panel4);
		
		panel2.add(submit);
		
		panel1.add(panel2);
		
		panel3.add(difference);
		panel3.add(differencenumber);
		panel4.add(radio1);
		panel4.add(celsius);
		panel4.add(radio2);
		panel4.add(fahrenheit);
		panel5.add(image);
		panel5.add(caption);
		
		
		
		leftPanel.add(panel1);
		
	}
	
	private void createmiddlePanel() {
		
		Library june = new Library();
		int n = june.indexlist.size();
		String[] list = new String[n];
		for (int i = 0; i<n; i++) {
			list[i] = june.indexlist.get(i);
		}
		comboBox = new JComboBox(list);
		
		
		JLabel title = new JLabel("HOTTEST AND COLDEST YEAR BY COUNTRY OR TERRITORY");
		title.setFont(new Font("Times New Roman", Font.BOLD, 15));
		title.setAlignmentX(CENTER_ALIGNMENT);
		JLabel instruction = new JLabel("Pick a Country or Territory:");
		instruction.setAlignmentX(CENTER_ALIGNMENT);
		JLabel hottemp = new JLabel("Hottest Temperature: " + String.valueOf(hottesttemp));
		hottemp.setAlignmentX(CENTER_ALIGNMENT);
		JLabel year = new JLabel("in the year of "+String.valueOf(yearhot));
		year.setAlignmentX(CENTER_ALIGNMENT);
		JLabel coldtemp = new JLabel("Coldest Temperature: " + String.valueOf(coldesttemp));
		coldtemp.setAlignmentX(CENTER_ALIGNMENT);
		JLabel year1 = new JLabel(String.valueOf("in the year of "+ String.valueOf(yearcold)));
		year1.setAlignmentX(CENTER_ALIGNMENT);
		JButton submit = new JButton("SUBMIT");
		submit.setAlignmentX(CENTER_ALIGNMENT);
		JRadioButton radio1 = new JRadioButton();
		JLabel celsius = new JLabel("Show in Celsius");
		JRadioButton radio2 = new JRadioButton();
		JLabel fahrenheit = new JLabel("Show in Fahrenheit");
		
		JPanel panel1 = new JPanel();
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
		
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		
		panel1.add(title);	
		panel1.add(Box.createRigidArea(new Dimension(0,10)));
		panel1.add(instruction);
		panel1.add(comboBox);
		
		panel2.add(radio1);
		panel2.add(celsius);
		panel3.add(radio2);
		panel3.add(fahrenheit);
		panel1.add(panel2);
		panel1.add(panel3);
		panel1.add(Box.createRigidArea(new Dimension(0,10)));
		panel1.add(hottemp);
		panel1.add(year);
		panel1.add(coldtemp);
		panel1.add(year1);
		panel1.add(Box.createRigidArea(new Dimension(0,15)));
		panel1.add(submit);
		
		centerPanel.add(panel1);

	}
	
	private void createrightPanel() {
		
		Library june = new Library();
		int n = june.indexlist.size();
		String[] list = new String[n];
		for (int i = 0; i<n; i++) {
			list[i] = june.indexlist.get(i);
		}
		comboBox = new JComboBox(list);
		
		JLabel title = new JLabel("TEMPERATURE RATE CALCULATOR BY COUNTRY OR TERRITORY");
		title.setFont(new Font("Times New Roman", Font.BOLD, 15));
		title.setAlignmentX(CENTER_ALIGNMENT);
		JLabel instruction = new JLabel("Pick a Country or Territory:");
		instruction.setAlignmentX(CENTER_ALIGNMENT);
		JLabel instruction2 = new JLabel("Enter years between 1961 and 2022");
		instruction2.setAlignmentX(CENTER_ALIGNMENT);
		JLabel instruction3 = new JLabel("(This will calculate the temperature rate increase or decrease between both years.)");
		instruction3.setAlignmentX(CENTER_ALIGNMENT);
		JLabel year1 = new JLabel("Year 1");
		year1.setAlignmentX(CENTER_ALIGNMENT);
		JLabel year2 = new JLabel("Year 2");
		year2.setAlignmentX(CENTER_ALIGNMENT);
		JTextField field1 = new JTextField();
		field1.setPreferredSize(new Dimension (70, 30));
		JTextField field2 = new JTextField();
		field2.setPreferredSize(new Dimension (70,30));
		JLabel rate = new JLabel("The rate increase or decrease is " + String.valueOf(ratediff) + "%" );
		rate.setAlignmentX(CENTER_ALIGNMENT);
		JButton submit = new JButton("SUBMIT");
		submit.setAlignmentX(CENTER_ALIGNMENT);
		
		JPanel panel1 = new JPanel();
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
		
		panel1.add(title);
		panel1.add(Box.createRigidArea(new Dimension(0,10)));
		panel1.add(instruction);
		panel1.add(comboBox);
		panel1.add(instruction2);
		panel1.add(instruction3);
		panel1.add(year1);
		panel1.add(field1);
		panel1.add(year2);
		panel1.add(field2);
		
		panel1.add(Box.createRigidArea(new Dimension(10,10)));
		panel1.add(rate);
		panel1.add(Box.createRigidArea(new Dimension(10,10)));
		panel1.add(submit);
		
		
		
		rightPanel.add(panel1);
	}
	
	public void updateUI() {
		
	}
	
	public static void main(String[] args)
	   {
		//create a VIew object
		  GWView view = new GWView();
		  
	   }
	
}
