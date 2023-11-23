

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;


public class GWView extends JFrame{
	
	private JPanel leftPanel;
	private JPanel centerPanel;
	private JPanel rightPanel;
	private JPanel mainPanel;
	private JComboBox comboBox;

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
		
		
		Library june = new Library();
		int n = june.indexlist.size();
		String[] list = new String[n];
		for (int i = 0; i<n; i++) {
			list[i] = june.indexlist.get(i);
			System.out.println(june.getTemp("Philippines", 1961));
//			System.out.println(june.library1.get(0).getyeartemphash());
		}
		
		
		JLabel title = new JLabel("TEMPERATURE DIFFERENCE CALCULATOR");
		title.setFont(new Font("Times New Roman", Font.BOLD, 20));
		title.setAlignmentX(CENTER_ALIGNMENT);
		JLabel title2 = new JLabel("BY YEAR");
		title2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		title2.setAlignmentX(CENTER_ALIGNMENT);
		
		JLabel instruction = new JLabel("Pick a Country:");
		instruction.setAlignmentX(CENTER_ALIGNMENT);
		JLabel year1 = new JLabel("Year 1");
		year1.setAlignmentX(CENTER_ALIGNMENT);
		JLabel year2 = new JLabel("Year 2");
		year2.setAlignmentX(CENTER_ALIGNMENT);
		
		BoxLayout layout = new BoxLayout(leftPanel, BoxLayout.Y_AXIS);
		leftPanel.setLayout(layout);
		JPanel inpanel = new JPanel();
		inpanel.setLayout(new BoxLayout(inpanel, BoxLayout.PAGE_AXIS));

		comboBox = new JComboBox(list);
		comboBox.setMaximumSize(comboBox.getPreferredSize());
		
		
		inpanel.add(comboBox);
		
		leftPanel.add(title, BorderLayout.CENTER);
		leftPanel.add(title2, BorderLayout.CENTER);
		leftPanel.add(Box.createVerticalStrut(20));
		leftPanel.add(instruction,BorderLayout.CENTER);
		leftPanel.add(inpanel);
		leftPanel.add(Box.createVerticalStrut(20));
		leftPanel.add(year1);
		leftPanel.add(Box.createVerticalStrut(20));
		leftPanel.add(year2);
		
	}
	
	public static void main(String[] args)
	   {
		//create a VIew object
		  GWView view = new GWView();
		  
	   }
	
}
