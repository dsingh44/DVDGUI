
import java.awt.LayoutManager;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.FlowLayout;

public class DvdGui {
	
	private DVDCollection dvdlist;
	JList dvdjlist;
	JScrollPane dvdlstpane;
	
	JLabel label = new JLabel();
	
	public DvdGui(DVDCollection dl) {
		dvdlist = dl;
		createWindow();
	}
	//declare j frame
	JFrame frame = new JFrame("DvdGui");
	private void createWindow() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		createUI(frame);
		frame.setSize(560, 200);
		//center on screen
		frame.setLocationRelativeTo(null);
		//make visible
		frame.setVisible(true);
	}
	
	private void createUI(final JFrame frame) {
		JPanel panel = new JPanel();
		LayoutManager layout = new FlowLayout();
		
		panel.setLayout(layout);
		//create a text field
		JTextField textField = new JTextField(20);
		
		dvdjlist = new JList();
		dvdlstpane = new JScrollPane(dvdlstpane);
		
		dvdjlist.setListData(dvdlist.DVDinfo());
		dvdjlist.setSelectedIndex(0);
		panel.add(dvdlstpane);
		
		dvdjlist.addListSelectionListener(new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent e) {
				String Selecteddvd=(String) dvdjlist.getSelectedValue();
				label.setText(Selecteddvd);
			}
	
		});
		Box buttonBox = Box.createVerticalBox();
		
		//add modify button
		JButton addmodifyButton = new JButton("Add/Modify DVD");
		addmodifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doAddOrModifyDVD();
			}
		});
		//remove button
		JButton removeButton = new JButton("Remove DVD");
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doRemoveDVD();
			}
		});
		//rating button
		JButton ratingButton = new JButton("Get DVDs By Rating");
		ratingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doGetDVDsByRating();
			}
		});
		//running time button
		JButton runningtimeButton = new JButton("Get Total Running Time");
		runningtimeButton. addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doGetTotalRunningTime();
			}
		});
		//save Button
		JButton saveButton = new JButton ("Save");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doSave();
			}
		});
		//exit button
		JButton ExitButton = new JButton("Exit");
		ExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doexit();
			}
		});
		
		panel.add(addmodifyButton);
		panel.add(removeButton);
		panel.add(ratingButton);
		panel.add(runningtimeButton);
		panel.add(saveButton);
		panel.add(ExitButton);
		panel.add(dvdjlist);
		
		
		frame.getContentPane().add(panel);
		
		//display
		frame.setVisible(true);
		
	}
	
	private void doAddOrModifyDVD() 
	{
		//request the title
		String title = JOptionPane.showInputDialog("Enter title");
		if( title == null) {
			return;
		}
		title = title.toUpperCase();
		//request the rating
		String rating = JOptionPane.showInputDialog("Enter rating for "+ title);
		if (rating == null) {
			return;
		}
		rating = rating.toUpperCase();
		
		//request the running time
		String time = JOptionPane.showInputDialog("Enter running time for " + title);
		if (time == null) {
			
		}
		//Add or modify the DVD
		dvdlist.addOrModifyDVD(title, rating, time);
		
		//Display current collection to console for debugging
		JOptionPane.showMessageDialog(frame,"Adding/Modifying: " + title +"," +rating + ","+ time);
		JOptionPane.showMessageDialog(frame,dvdlist);
		
	}
	private void doRemoveDVD() {
		//Request the title
		String title = JOptionPane.showInputDialog("Enter title");
		if (title == null) {
			return;
		}
		title = title.toUpperCase();
		
		//remove DVD
		dvdlist.removeDVD(title);
		
		//Display current collection to console for debugging
		JOptionPane.showMessageDialog(frame,"Removing: " + title);
		JOptionPane.showMessageDialog(frame,dvdlist);
		
	}
	
	private void doGetDVDsByRating() {
		//Request the rating
		String rating = JOptionPane.showInputDialog("Enter rating");
		if(rating == null) {
			return;
		}
		rating = rating.toUpperCase();
		
		String results = dvdlist.getDVDsByRating(rating);
		JOptionPane.showMessageDialog(frame,"DVDs with rating "+rating);
		JOptionPane.showMessageDialog(frame,results);
		
	}
	
	private void doGetTotalRunningTime() {
		
		int total = dvdlist.getTotalRunningTime();
		JOptionPane.showMessageDialog(frame,"Total Running Time of DVDs: "+ total);
	}
	
	private void doSave() {
		dvdlist.save();
		JOptionPane.showMessageDialog(frame,"Filesaved!");
		System.exit(0);
	}
	
	private void doexit() {
		JOptionPane.showMessageDialog(frame,"Click ok to exit");
		System.exit(0);
		
	}
	

}
