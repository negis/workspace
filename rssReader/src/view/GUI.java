package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.SwingUtilities;
import javax.xml.bind.JAXBException;
import model.channel;
import model.item;

import controller.connector;

public class GUI extends JFrame { 
	
	JTextField urlField;
	JList jl;
	JTextArea detailTextArea;
	List<item> itemList;
	
	/*
	 * GUI class for RSSReader
	 */

    public GUI() {
        
       setTitle("RSS READER");
       setSize(300, 200);
       setDefaultCloseOperation(EXIT_ON_CLOSE);    
       this.setLayout(new BorderLayout());
       initComponent();
    }
    
    /*
	 * Class for initialize the components.
	 */
    
	private void initComponent() {
		JPanel topPanel = new JPanel();
		JPanel bottomPanel = new JPanel();
		topPanel.setLayout(new FlowLayout());
		bottomPanel.setLayout(new FlowLayout());
		
		// Initialize and set properties of URL Text Field.
		urlField = new JTextField();
		urlField.setPreferredSize(new Dimension(300,20));
		
		// Initialize and add listener of Connect button.
		JButton connectButton = new JButton("Connect");
		connectButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                connector ct = new connector();
                try {
                	addListNaja(ct.getObjectFromUrl(new URL(urlField.getText())));
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
		topPanel.add(urlField);
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
	
	private void addListNaja(List<channel> cn) {
		
		itemList = cn.get(0).getLit();
		List<String> title = new ArrayList<String>();
		
		for(item e: itemList){title.add(e.getTitle());}
		jl.setListData(title.toArray());

	}
    
}
