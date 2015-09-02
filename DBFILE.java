package dbformat;

public class DBFILE{
	private String dat;
	private int dbSize;
	
	public DBFILE(String data,int dbSize){
		this.dat = data;
		this.dbSize = dbSize;
	}
	
	public String getString(){
		return(this.dat);
	}
	
	public int size(){
		return(dbSize);
	}
}