

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class GWView extends JFrame {
	
	private JPanel leftPanel;
	private JPanel centerPanel;
	private JPanel rightPanel;
	private JPanel mainPanel;
	private JRadioButton radio1;
	private JRadioButton radio2;
	private JRadioButton radio3;
	private JRadioButton radio4;
	private JComboBox comboBox;
	private JComboBox comboBox2;
	private JComboBox comboBox3;
	private ImageIcon islandimage;
	private ImageIcon islandimagebw;
	
	
	private Integer yearhot;
	private Integer yearcold;
	private Double hottesttemp ;
	private Double coldesttemp ;
	private Double ratediff;
	private JLabel differencenumber;
	private Double maintempdiff;
	private String place;
	private String place1;
	private String place2;
	private Library library = new Library();
	private String[] list;
	private JButton submit;
	private JButton submit1;
	private JButton submit2;
	private JTextField field2;
	private JTextField field1;
	private JTextField field3;
	private JTextField field4;
	
	

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
		islandimagebw = new ImageIcon(getClass().getResource("islandimagebw.png"));
		JLabel image = new JLabel(islandimagebw);
		
		JLabel caption = new JLabel("This country or territory is not an island nation/territory");
	
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
		
		field1 = new JTextField("");
		field1.setPreferredSize(new Dimension (100, 30));
		field2 = new JTextField("");
		field2.setPreferredSize(new Dimension (100,30));
		
		 field1.getDocument().addDocumentListener(new DocumentListener() {
		        @Override
		        public void insertUpdate(DocumentEvent e) {
		            updateSubmitButtonState();
		        }

		        @Override
		        public void removeUpdate(DocumentEvent e) {
		            updateSubmitButtonState();
		        }

		        @Override
		        public void changedUpdate(DocumentEvent e) {
		            
		        }
		    });
		 
		 field2.getDocument().addDocumentListener(new DocumentListener() {
		        @Override
		        public void insertUpdate(DocumentEvent e) {
		            updateSubmitButtonState();
		        }

		        @Override
		        public void removeUpdate(DocumentEvent e) {
		            updateSubmitButtonState();
		        }

		        @Override
		        public void changedUpdate(DocumentEvent e) {
		            
		        }
		    });

		//get string array of all countries
		//using library object
		
		int n = library.indexlist.size();
		list = new String[n];
		for (int i = 0; i<n; i++) {
			list[i] = library.indexlist.get(i);
		}
		comboBox = new JComboBox(list);
		place = list[0];
		
		comboBox.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource()== comboBox) {
					place = (String) comboBox.getSelectedItem();
					int index = library.indexlist.indexOf(place);
					if (library.getCountry(index).getClass() != Island.class) {
						image.setIcon(islandimagebw);
						caption.setText("This country or territory is not an island nation/territory");
					}
					else {
						image.setIcon(islandimage);
						caption.setText("This country or territory is an island nation/territory");
					}
				}
			}
		});
		
		
		submit = new JButton("SUBMIT");
		submit.setEnabled(false);
		submit.addActionListener(new ActionListener(){
		
			@Override
			public void actionPerformed(ActionEvent e)  {
				System.out.println(place);
				
				Integer year1 = Integer.valueOf(field1.getText());
				Integer year2 = Integer.valueOf(field2.getText());
				
				if (!isValidYear(year1) || !isValidYear(year2)) {
					submit.setEnabled(false);
					}else {
						maintempdiff = library.getTemp(place, year1) - library.getTemp(place, year2);
					}
				
				if (radio2.isSelected()) {
					maintempdiff = (maintempdiff * (9/5)) + 32;
				}
				
				differencenumber.setText(String.valueOf(maintempdiff));
			}
			
		});
		
		
		
		//PANEL 3
		JLabel difference = new JLabel("Difference in temperature:");
		differencenumber = new JLabel(String.valueOf(maintempdiff));
		
		//PANEL 4 - for radio buttons
		radio1 = new JRadioButton();
		JLabel celsius = new JLabel("Show in Celsius");
		radio2 = new JRadioButton();
		JLabel fahrenheit = new JLabel("Show in Fahrenheit");
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(radio1);
		radio1.setSelected(true);
		buttonGroup.add(radio2);
		
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
		islandimage = new ImageIcon(getClass().getResource("islandimage.png"));
		islandimagebw = new ImageIcon(getClass().getResource("islandimagebw.png"));
		JLabel image = new JLabel(islandimagebw);
		
		JLabel caption1 = new JLabel("This country or territory is not an island nation/territory");

	int n = library.indexlist.size();
	String[] list = new String[n];
	for (int i = 0; i<n; i++) {
		list[i] = library.indexlist.get(i);
	}
	comboBox2 = new JComboBox(list);
	place1 = list[0];
	
	
	comboBox2.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource()== comboBox2) {
				place1 = (String) comboBox2.getSelectedItem();
				int index = library.indexlist.indexOf(place1);
				if (library.getCountry(index).getClass() != Island.class) {
					image.setIcon(islandimagebw);
					caption1.setText("This country or territory is not an island nation/territory");
				}
				else {
					image.setIcon(islandimage);
					caption1.setText("This country or territory is an island nation/territory");
				}
			}
		}
	});
	
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
	submit1 = new JButton("SUBMIT");
	submit1.setAlignmentX(CENTER_ALIGNMENT);
	
	submit1.addActionListener(new ActionListener(){
		
		@Override
		public void actionPerformed(ActionEvent e)  {
			System.out.println(place1);
			int index = library.indexlist.indexOf(place1);
			Country country = (Country) library.getCountry(index);
			Hashtable<Integer, Double> yeartemp = country.getyeartemphash();
			Hashtable<Double, Integer> tempyear = country.gettempyearhash();
			Double[] tokens = new Double[62];
			Double celciusYear;
			
			for (int i=0, j=1961; i < 63 && j<2023; i++, j++) {
				tokens[i] = yeartemp.get(j);
			}
			
			Double maxValue = tokens[0];
			Double minValue = tokens[0];
			
			for (int i = 1; i < tokens.length; i++) {
				if (tokens[i] > maxValue) {
					hottesttemp = tokens[i];
					yearhot = tempyear.get(hottesttemp);
				}
				if (tokens[i] < minValue) {
					coldesttemp = tokens[i];
					yearcold = tempyear.get(coldesttemp);
				}
			}
			
			if (radio4.isSelected()) {
				coldesttemp = (coldesttemp * (9/5)) + 32;
				hottesttemp = (hottesttemp * (9/5)) + 32;
			}
			
			hottemp.setText("Hottest Temperature: " + String.valueOf(hottesttemp));
			year.setText("in the year of "+String.valueOf(yearhot));
			coldtemp.setText("Coldest Temperature: " + String.valueOf(coldesttemp));
			year1.setText("in the year of "+String.valueOf(yearcold));
			
		}
	});
	
	
	radio3 = new JRadioButton();
	JLabel celsius = new JLabel("Show in Celsius");
	radio4 = new JRadioButton();
	JLabel fahrenheit = new JLabel("Show in Fahrenheit");
	
	ButtonGroup buttonGroup = new ButtonGroup();
	buttonGroup.add(radio3);
	radio3.setSelected(true);
	buttonGroup.add(radio4);
	
	JPanel panel1 = new JPanel();
	panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
	
	
	JPanel panel4 = new JPanel();
	JPanel panel2 = new JPanel();
	panel4.add(image);
	panel4.add(caption1);
	JPanel panel3 = new JPanel();
	
	panel1.add(title);	
	panel1.add(Box.createRigidArea(new Dimension(0,10)));
	panel1.add(instruction);
	panel1.add(comboBox2);
	panel1.add(panel4);
	panel2.add(radio3);
	panel2.add(celsius);
	panel3.add(radio4);
	panel3.add(fahrenheit);
	panel1.add(panel2);
	panel1.add(panel3);
	panel1.add(Box.createRigidArea(new Dimension(0,10)));
	panel1.add(hottemp);
	panel1.add(year);
	panel1.add(coldtemp);
	panel1.add(year1);
	panel1.add(Box.createRigidArea(new Dimension(0,15)));
	panel1.add(submit1);
	
	centerPanel.add(panel1);

}

	private void createrightPanel() {
	
	JLabel image = new JLabel(islandimagebw);
	
	JLabel caption2 = new JLabel("This country or territory is not an island nation/territory");
	
	comboBox3 = new JComboBox(list);
	place2 = list[0];
	
	comboBox3.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource()== comboBox3) {
				place2 = (String) comboBox3.getSelectedItem();
				int index = library.indexlist.indexOf(place2);
				if (library.getCountry(index).getClass() != Island.class) {
					image.setIcon(islandimagebw);
					caption2.setText("This country or territory is not an island nation/territory");
				}
				else {
					image.setIcon(islandimage);
					caption2.setText("This country or territory is an island nation/territory");
				}
			}
		}
	});
	
	
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
	field3 = new JTextField();
	field3.setPreferredSize(new Dimension (70, 30));
	field4 = new JTextField();
	field4.setPreferredSize(new Dimension (70,30));
	
	JLabel rate = new JLabel("The rate change is " + String.valueOf(ratediff) + "%" );
	JLabel comment = new JLabel();
	comment.setAlignmentX(CENTER_ALIGNMENT);
	rate.setAlignmentX(CENTER_ALIGNMENT);
	submit2 = new JButton("SUBMIT");
	submit2.setEnabled(false);
	submit2.setAlignmentX(CENTER_ALIGNMENT);
	
	
	
	JPanel panel1 = new JPanel();
	panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
	
	JPanel panel2 = new JPanel();
	panel2.add(image);
	panel2.add(caption2);
	
	panel1.add(title);
	panel1.add(Box.createRigidArea(new Dimension(0,10)));
	panel1.add(instruction);
	panel1.add(comboBox3);
	panel1.add(panel2);
	panel1.add(instruction2);
	panel1.add(instruction3);
	panel1.add(year1);
	panel1.add(field3);
	panel1.add(year2);
	panel1.add(field4);
	
	panel1.add(Box.createRigidArea(new Dimension(10,10)));
	panel1.add(rate);
	panel1.add(Box.createRigidArea(new Dimension(10,10)));
	panel1.add(comment);
	panel1.add(submit2);
	
	 field3.getDocument().addDocumentListener(new DocumentListener() {
	        @Override
	        public void insertUpdate(DocumentEvent e) {
	            updateSubmitButtonState2();
	        }

	        @Override
	        public void removeUpdate(DocumentEvent e) {
	            updateSubmitButtonState2();
	        }

	        @Override
	        public void changedUpdate(DocumentEvent e) {
	            
	        }
	    });
	 
	 field4.getDocument().addDocumentListener(new DocumentListener() {
	        @Override
	        public void insertUpdate(DocumentEvent e) {
	            updateSubmitButtonState2();
	        }

	        @Override
	        public void removeUpdate(DocumentEvent e) {
	            updateSubmitButtonState2();
	        }

	        @Override
	        public void changedUpdate(DocumentEvent e) {
	            
	        }
	    });
	
	 submit2.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e)  {
				System.out.println(place2);
				Integer year1 = Integer.valueOf(field3.getText());
				Integer year2 = Integer.valueOf(field4.getText());
				
				Double change = library.getpercentChange(place2, year1, year2);
			
				rate.setText("The rate change is " + String.valueOf(change) + "%");
				comment.setText("From " + year1 + " to " + year2 + " the rate of change is " + change + "%." );
			}
			
		});
	
	
	rightPanel.add(panel1);
}
	
	private void updateSubmitButtonState() {
		
		 try {
		        int year1 = Integer.parseInt(field1.getText());
		        int year2 = Integer.parseInt(field2.getText());

		        boolean validInput = isValidYear(year1) && isValidYear(year2) &&
		                !field1.getText().isEmpty() && !field2.getText().isEmpty();

		        submit.setEnabled(validInput);
		    } catch (NumberFormatException e) {
		        // Handle the case where the input is not a valid integer
		        submit.setEnabled(false);
		    }
		
	}
	
	private void updateSubmitButtonState2() {
		
		 try {
		        int year1 = Integer.parseInt(field3.getText());
		        int year2 = Integer.parseInt(field4.getText());

		        boolean validInput = isValidYear(year1) && isValidYear(year2) &&
		                !field3.getText().isEmpty() && !field4.getText().isEmpty();

		        submit2.setEnabled(validInput);
		    } catch (NumberFormatException e) {
		        // Handle the case where the input is not a valid integer
		        submit2.setEnabled(false);
		    }
		
	}

	private boolean isValidYear(Integer year) {
		return year >= 1961 && year <= 2022;
	}
	public static void main(String[] args)
	   {
		//create a VIew object
		  GWView view = new GWView();
	   }


}
