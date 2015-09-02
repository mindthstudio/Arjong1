package client;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import data.*;

public class Client implements ActionListener{
	private Arjong app = new Arjong();
	private int width = 300,height = 200;
	
	static void renderSplashFrame(Graphics2D g,int frame){
		final String[] comps = {"data","path","menu","graphics","openGL","openAL","LWJGL","Slick","Databases","String","package","jre"};
		final float version = 1.5f;
		
		g.fillRect(120,140,280,40);
		g.setPaintMode();
		g.setColor(Color.WHITE);
		g.drawString("loading : "+comps[(frame/5)%comps.length]+"...",130,165);
		g.drawString("version : "+version,320,196);
		g.setComposite(AlphaComposite.Clear);
	}

	public Client(){
		final SplashScreen splash = SplashScreen.getSplashScreen();
		if(splash == null){
			System.out.printf("error splash.png not fond\n");
			return;
		}
		Graphics2D g = splash.createGraphics();
		if(g == null){
			System.out.printf("g is null\n");
			return;
		}

		for(int i = 0;i<150;i++){
			renderSplashFrame(g,i);
			splash.update();
			try{
				Thread.sleep(15);
			}catch(InterruptedException e){
			}
		}

		splash.close();
	}
	
	public void actionPerformed(ActionEvent ae){
		System.exit(0);
	}

	private static WindowListener closeWindow = new WindowAdapter(){
		public void windowClosing(WindowEvent e){
			e.getWindow().dispose();
		}
	};
	
	public static void main(String[] agvs) throws org.newdawn.slick.SlickException {
		JFrame frame = new JFrame();
		Toolkit toolkit = frame.getToolkit();
		Dimension size = toolkit.getScreenSize();

		int scrWidth,scrHeight;
		try{
			File file = new File("config.conf");
			FileInputStream fis = new FileInputStream(file);
			DataInputStream dis = new DataInputStream(fis);
			String dataA = ""+dis.readInt();
			String dataB = ""+dis.readInt();
			dis.close();
			
			Client client = new Client();
			client.app.main(new String[]{dataA,dataB});
		}catch(IOException e){
			Client client = new Client();
			client.app.main(new String[]{"0","1"});
		}catch(Exception e){
			scrWidth = 300;
			scrHeight = 300;
			String errorReport = e.toString();
			
			frame.setTitle("Error Report");
			frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
			frame.setSize(scrWidth,scrHeight);
			frame.setLocation((size.width-scrWidth)/2,(size.height-scrHeight)/2);
			JPanel panel = new JPanel();
			frame.getContentPane().add(panel);
			panel.setLayout(null);
			
			JButton butt = new JButton("Close");
			butt.setBounds((scrWidth-150)/2,scrHeight-100,130,60);
			butt.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						System.exit(0);
					}
			});
			
			
			JTextPane pane = new JTextPane();
			pane.setBounds(0,10,scrWidth,scrHeight/2+10);
			pane.setContentType("text/html");
			String msg = "<p><b>Exception</p></b>"+
						"<p>"+errorReport+"</p>"+
						"<r><p>send report to <b><u>http://www.mindtech.co.cc</u></b></p></r>";
			
			
			pane.setText(msg);
			pane.setEditable(false);
			panel.add(pane);
			panel.add(butt);
			frame.add(panel);
			frame.setResizable(false);
			frame.setVisible(true);
		}
	}
}