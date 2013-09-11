package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.xml.bind.JAXBException;

import model.channel;
import model.item;
import controller.connector;

public class testStudentInputUI extends JFrame {
	
	JTextField nameField;
	JTextField idField;
	JList jl;
	JTextArea detailTextArea;
	List<item> itemList;

    public GUI() {
        
       setTitle("Test Student Injection");
       setSize(300, 200);
       setDefaultCloseOperation(EXIT_ON_CLOSE);    
       this.setLayout(new BorderLayout());
       initComponent();
    }
    
	private void initComponent() {
		JPanel topPanel = new JPanel();
		JPanel bottomPanel = new JPanel();
		topPanel.setLayout(new FlowLayout());
		bottomPanel.setLayout(new FlowLayout());
		
		// Initialize and set properties of Name and Id Text Field.
		idField = new JTextField();
		idField.setPreferredSize(new Dimension(300,20));
		nameField = new JTextField();
		nameField.setPreferredSize(new Dimension(300,20));
		
		// Initialize and add listener of Connect button.
		JButton connectButton = new JButton("Add");
		connectButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                connector ct = new connector();
                try {
                	addListNaja(ct.getObjectFromUrl(new URL(nameField.getText())));
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JAXBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
            }
        });
		
		// Add Components of top panel. 
		topPanel.add(nameField);
		topPanel.add(connectButton);
		
		// Initialize and set properties of detail JList
		jl = new JList();
		jl.setPreferredSize(new Dimension(350,400));
		
		// Initialize and set properties of detail text area
		detailTextArea = new JTextArea();
		detailTextArea.setPreferredSize(new Dimension(400,400));
		detailTextArea.setLineWrap(true);
		detailTextArea.setAutoscrolls(true);
		
		jl.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		    	detailTextArea.setText(itemList.get(jl.getSelectedIndex()).getDescription());
		    }
		});
		
		
		// Add Components of bottom panel. 
		bottomPanel.add(jl);
		bottomPanel.add(detailTextArea);
		
		// Add each panel to the JFrame
		
		this.add(topPanel,BorderLayout.NORTH);
		this.add(bottomPanel, BorderLayout.SOUTH);
		this.pack();
	}
	

}
