package config;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;


public class Config extends JFrame{
	private JComboBox[] combobox = new JComboBox[2];
	private JButton[] button = new JButton[2];
	
	public Config(){
		JLabel[] label = new JLabel[2];
		
		label[0] = new JLabel("Sycn Mode");
		label[0].setBounds(20,20,80,20);
		this.add(label[0]);
		
		label[1] = new JLabel("Screen Mode");
		label[1].setBounds(20,50,80,20);
		this.add(label[1]);
	
		combobox[0] = new JComboBox();
		combobox[0].addItem("off");
		combobox[0].addItem("on");
		combobox[0].setBounds(100,20,170,20);
		combobox[0].setBackground(Color.WHITE);
		this.add(combobox[0]);
	
		combobox[1] = new JComboBox();
		combobox[1].addItem("Full Screen Mode");
		combobox[1].addItem("Window Screen Mode");
		combobox[1].setBounds(100,50,170,20);
		combobox[1].setBackground(Color.WHITE);
		this.add(combobox[1]);
	
		button[0] = new JButton("Apply");
		button[0].setBounds(20,120,110,30);
		button[0].addActionListener(new ActionListener(){
			private String[] mode = new String[2];
			public void actionPerformed(ActionEvent e){
				mode[0] = combobox[0].getSelectedItem().toString();
				mode[1] = combobox[1].getSelectedItem().toString();
				configeSystemOutput(mode[0],mode[1]);
			}
		});
		this.add(button[0]);
		
		button[1] = new JButton("Cancel");
		button[1].setBounds(160,120,110,30);
		button[1].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		this.add(button[1]);
	
		this.setTitle("Config Setting");
		this.setSize(300,200);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public void configeSystemOutput(String a,String b){
		try{
			File file = new File("config.conf");
			FileOutputStream fos = new FileOutputStream(file);
			DataOutputStream dos = new DataOutputStream(fos);
			
			if(a.equals("off")){
				dos.writeInt(0);
			}else{
				dos.writeInt(1);
			}
			if(b.equals("Full Screen Mode")){
				dos.writeInt(1);
			}else{
				dos.writeInt(0);
			}
			dos.close();
		}catch(IOException e){
		}
	}
	
	public static void main(String[] args) {
		new Config();
	}
}