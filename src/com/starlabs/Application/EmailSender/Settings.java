package com.starlabs.Application.EmailSender;

import java.awt.*;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

@SuppressWarnings({ "serial", "unused" })
public class Settings extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField usernamefield;
	private JTextField hostfield;
	private JTextField portfield;
	private JLabel statuslabel;
	private static Settings dialog;
	private JPasswordField passwordField;
	private JButton btnCancelButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dialog = new Settings();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Settings() {
		setTitle("Settings\r\n");
		setBounds(100, 100, 399, 223);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblUsername = new JLabel("Username");
			lblUsername.setBounds(20, 26, 92, 14);
			contentPanel.add(lblUsername);
		}
		{
			JLabel lblPassword = new JLabel("Password");
			lblPassword.setBounds(20, 51, 106, 14);
			contentPanel.add(lblPassword);
		}
		{
			JLabel lblHost = new JLabel("Host");
			lblHost.setBounds(20, 76, 46, 14);
			contentPanel.add(lblHost);
		}
		{
			JLabel lblPort = new JLabel("Port");
			lblPort.setBounds(20, 101, 46, 14);
			contentPanel.add(lblPort);
		}
		{
			usernamefield = new JTextField();
			usernamefield.setBounds(136, 23, 213, 20);
			contentPanel.add(usernamefield);
			usernamefield.setColumns(10);
		}
		{
			passwordField = new JPasswordField();
			passwordField.setBounds(136, 48, 213, 20);
			contentPanel.add(passwordField);
		}
		{
			hostfield = new JTextField();
			hostfield.setBounds(136, 73, 213, 20);
			contentPanel.add(hostfield);
			hostfield.setColumns(10);
		}
		{
			portfield = new JTextField();
			portfield.setBounds(136, 98, 213, 20);
			contentPanel.add(portfield);
			portfield.setColumns(10);
		}
		{
			statuslabel = new JLabel("");
			statuslabel.setBounds(20, 138, 151, 14);
			contentPanel.add(statuslabel);
		}
		{
			JButton btnokbutton = new JButton("OK");
			btnokbutton.addActionListener(new ActionListener() {
				@SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent e) {
					
					if (usernamefield.getText().equals("")||passwordField.getText().equals("")||hostfield.getText().equals("")||portfield.getText().equals("")){
						statuslabel.setText("All fields are mandatory");
					}
					else{ 
					Properties props = new Properties();
					try{
						props.load(new FileInputStream(new File("application.properties")));
						} catch(FileNotFoundException e1){
							e1.printStackTrace();
						} catch(IOException e2){
							e2.printStackTrace();
						}
				
					props.setProperty("username", usernamefield.getText());
					props.setProperty("password",  passwordField.getText());
					props.setProperty("host", hostfield.getText());
					props.setProperty("port", portfield.getText());
				
					try {
						props.store(new FileOutputStream(new File("application.properties")),null);
						statuslabel.setText("Properties Updated");
					} catch (FileNotFoundException e1) {						
						statuslabel.setText("Properties Not Updated");
					} catch (IOException e1) {
						statuslabel.setText("Properties Not Updated");
					}
					}
					
				}
					
			});
			btnokbutton.setBounds(201, 129, 84, 23);
			contentPanel.add(btnokbutton);
		}
		{
			btnCancelButton = new JButton("Cancel");
			btnCancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Settings.this.setVisible(false);
					Settings.this.dispatchEvent(new WindowEvent(
							Settings.this, WindowEvent.WINDOW_CLOSING));
				}
			});
			btnCancelButton.setBounds(284, 129, 89, 23);
			contentPanel.add(btnCancelButton);
		}
	
		
			
		
	}
}
