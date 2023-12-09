

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	private JComboBox comboBox;
	private JComboBox comboBox2;
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
	private Library library = new Library();
	private String[] list;
	private JButton submit;
	private JTextField field2;
	private JTextField field1;
	
	
	

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
//		createmiddlePanel();
//		createrightPanel();
		
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
		JLabel image = new JLabel(islandimage);
		
		JLabel caption = new JLabel("This country or territory is an island nation/territory");
	
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
//					Country test = library.getCountry(index);
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
	
	private boolean isValidYear(Integer year) {
		return year >= 1961 && year <= 2022;
	}
	public static void main(String[] args)
	   {
		//create a VIew object
		  GWView view = new GWView();
	   }


}
