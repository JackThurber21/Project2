package project2;

import java.awt.Dimension;

import javax.swing.JPanel;

import project1.Patient;
import project1.PatientCollection;

import java.awt.Label;
import java.awt.Font;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.JScrollBar;
import java.awt.ScrollPane;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import java.util.Random;

public class mainPanel extends JPanel{
	private JLabel patientData;
	private PatientCollection myPats;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textField;
	private JTable table;

	public mainPanel() {
	
		myPats = new PatientCollection("./data.csv");

		setBackground(Color.RED);
		setPreferredSize(new Dimension(800,500));
		setLayout(null);

		
		JLabel patientData = new JLabel("Patient Data: ");
		patientData.setBounds(131, 54, 350, 14);
		add(patientData);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Patient pat = myPats.getPatient((String)comboBox.getSelectedItem());
				patientData.setText(pat.toString());
			}
		});
		ArrayList<String> myIds = myPats.getIds();
		comboBox.setModel(new DefaultComboBoxModel(myIds.toArray()));
		comboBox.setBounds(75, 10, 147, 22);
		add(comboBox);

		JLabel lblPatient = new JLabel("Patient");
		lblPatient.setBounds(75, 54, 46, 14);
		add(lblPatient);

		JButton btnAddPatients = new JButton("Add Patients");
		btnAddPatients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser("./");

				int returnValue = jfc.showOpenDialog(null);

				if (returnValue == JFileChooser.APPROVE_OPTION) {
					//File selectedFile = jfc.getSelectedFile();
					myPats.addPatientsFromFile("" +jfc.getSelectedFile());
					ArrayList<String> myIds = myPats.getIds();
					comboBox.setModel(new DefaultComboBoxModel(myIds.toArray()));
				}  

			}
		});
		btnAddPatients.setBounds(75, 67, 176, 23);
		add(btnAddPatients);

//		

		JRadioButton rdbtnCr = new JRadioButton("CR");
		buttonGroup.add(rdbtnCr);
		rdbtnCr.setBounds(75, 123, 109, 23);
		add(rdbtnCr);

		JRadioButton rdbtnDp = new JRadioButton("DP");
		buttonGroup.add(rdbtnDp);
		rdbtnDp.setBounds(75, 149, 109, 23);
		add(rdbtnDp);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("./Childrens Hospital2.png"));
		lblNewLabel.setBounds(0, 179, 436, 321);
		add(lblNewLabel);
		
		JButton btnDeleteSelectedPatient = new JButton("Delete Selected Patient");
		btnDeleteSelectedPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			Patient pat = myPats.removePatient((String)comboBox.getSelectedItem());
			ArrayList<String> myIds = myPats.getIds();
			comboBox.setModel(new DefaultComboBoxModel(myIds.toArray()));
			JOptionPane.showMessageDialog(btnDeleteSelectedPatient, "Patient Was Deleted!");
			}
		});
		btnDeleteSelectedPatient.setBounds(75, 93, 176, 23);
		add(btnDeleteSelectedPatient);
		
		JButton btnSetResults = new JButton("Set Results");
		btnSetResults.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnCr != null)
				{
					myPats.setResultForPatient((String) comboBox.getSelectedItem(), "CR");
				}
				if(rdbtnDp !=null){
					
					myPats.setResultForPatient((String) comboBox.getSelectedItem(), "DP");
									}
			}
		});
		btnSetResults.setBounds(190, 123, 115, 23);
		add(btnSetResults);
		
		JLabel lblMakeSureTo = new JLabel("Make Sure to re-select the patient after setting results!");
		lblMakeSureTo.setBounds(190, 153, 327, 14);
		add(lblMakeSureTo);
		
		
		JButton btnClickMe = new JButton("Click Me!");
		btnClickMe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				Random rand = new Random();

				// Obtain a number between [0 - 3].
				int n = rand.nextInt(4);
				if(n ==0) {
					setBackground(Color.BLUE);
				}
				if(n ==1) {
					setBackground(Color.GREEN);
				}
				if(n ==2) {
					setBackground(Color.YELLOW);
				}
				if(n ==3) {
					setBackground(Color.MAGENTA);
				}
					

			}
		});
		btnClickMe.setBounds(701, 466, 89, 23);
		add(btnClickMe);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(560, 10, 240, 166);
		add(scrollPane);
		
		
		JTextPane textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
		
		textPane.setText(myPats.toString());
		
	

	}
	public void doClose() {
		myPats.doWrite("./ testwrite.csv");
	}
}