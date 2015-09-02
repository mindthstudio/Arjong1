package dbformat;

import java.io.*;

public class DBFormat{
	public static DBFILE create(File f){
		try{
			FileInputStream fis = new FileInputStream(f);
			String db;
			int sizeOfClass;
			int fileSize = (int)f.length();
			byte[] dataByte = new byte[fileSize];
			fis.read(dataByte);
			fis.close();
			
			db = new String(dataByte);
			//System.out.println(db);
			
			String[] aSize = db.split(">");
			sizeOfClass = aSize.length;
			return(new DBFILE(db.trim(),sizeOfClass));
		}catch(IOException e){
			javax.swing.JOptionPane.showMessageDialog(null,e.toString(),"Error Report",javax.swing.JOptionPane.ERROR_MESSAGE);
		}
		return(null);
	}
	
}