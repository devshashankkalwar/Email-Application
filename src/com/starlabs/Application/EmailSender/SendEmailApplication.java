package com.starlabs.Application.EmailSender;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;

import javax.swing.JPanel;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;

@SuppressWarnings("unused")
public class SendEmailApplication {

	private JFrame frame;
	private JTextField totextField;
	private JTextField subtextfield;
	private JTextArea bodyofeamil;
	private JLabel statusLabel;
	private String to;
	private String sub;
	private String body;
	private Settings settings;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SendEmailApplication window = new SendEmailApplication();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SendEmailApplication() {
		initialize();
		settings= new Settings();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Email Application");
		Image img = new ImageIcon(this.getClass().getResource("/Emailicon.png")).getImage();
		frame.setIconImage(img);
		frame.setBounds(100, 100, 800, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setFont(new Font("Calibri", Font.BOLD, 15));
		lblTo.setBounds(50, 50, 70, 20);
		frame.getContentPane().add(lblTo);
		
		JLabel lblSubject = new JLabel("Subject");
		lblSubject.setFont(new Font("Calibri", Font.BOLD, 15));
		lblSubject.setBounds(50, 100, 84, 20);
		frame.getContentPane().add(lblSubject);
		
		JLabel lblBodyOfEmail = new JLabel("Body Of Email");
		lblBodyOfEmail.setFont(new Font("Calibri", Font.BOLD, 15));
		lblBodyOfEmail.setBounds(50, 150, 101, 20);
		frame.getContentPane().add(lblBodyOfEmail);
		
		totextField = new JTextField();
		totextField.setBounds(84, 50, 635, 20);
		frame.getContentPane().add(totextField);
		totextField.setColumns(10);
		
		
		subtextfield = new JTextField();
		subtextfield.setBounds(112, 100, 607, 20);
		frame.getContentPane().add(subtextfield);
		subtextfield.setColumns(10);
		
		bodyofeamil = new JTextArea();
		bodyofeamil.setBounds(50, 181, 669, 166);
		frame.getContentPane().add(bodyofeamil);
		
		statusLabel = new JLabel("");
		statusLabel.setBounds(50, 369, 455, 14);
		frame.getContentPane().add(statusLabel);
		
		JButton btnSendButton = new JButton("Send Email");
		btnSendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				statusLabel.setText("Sending Email......");
				to = totextField.getText();
				sub = subtextfield.getText();
				body = bodyofeamil.getText();
				Thread task1= new Thread(new Runnable() {
					
					@Override
					public void run() {

				try {
					EmailManager.sendMail(to,sub,body);
					statusLabel.setText("Email Sent Successfully");
				} catch (Exception e) {
					System.out.println(e);
					statusLabel.setText("Unable to Send Email");
				}	}
				});
				task1.start();
			}
		});
		btnSendButton.setBounds(622, 369, 97, 23);
		frame.getContentPane().add(btnSendButton);
		
		JButton btnResetButton = new JButton("Reset");
		btnResetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				totextField.setText("");
				subtextfield.setText("");
				bodyofeamil.setText("");
				statusLabel.setText("");
			}
		});
		btnResetButton.setBounds(515, 369, 97, 23);
		frame.getContentPane().add(btnResetButton);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmSettings = new JMenuItem("Settings");
		mntmSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				settings.setVisible(true);
			}
		});
	
		mntmSettings.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/Settings-icon.png")).getImage()));
		mnFile.add(mntmSettings);
	}
}
