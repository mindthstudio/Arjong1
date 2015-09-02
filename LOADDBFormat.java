package dbformat;

/*
##########################

 name database < scope value ; scope world

##########################
*/

public class LOADDBFormat{
	private String name = new String();
	private String[] val = new String[2];

	public LOADDBFormat(){
	}
	
	public LOADDBFormat(String name,String[] parms){
		this.name = name;
		val[0] = new String();
		val[1] = new String();
		
		for(int i = 0;i<parms.length;i++){
			if(!parms[i].equals("")){
				try{
					val[i] = parms[i];
				}catch(Exception e){
					System.out.println("Not a data");
				}
			}
		}
	}
	
	public static LOADDBFormat[] datap(int count,String db){
		String[] dataClass,classScope,cutData;
		classScope = db.split(">");

		dataClass = classScope[count].split("<");
			
		cutData = dataClass[1].split(",");
		int size = cutData.length-1;
		LOADDBFormat dbf[] = new LOADDBFormat[size];
			
		for(int i = 0;i<size;i++){
				dbf[i] = new LOADDBFormat(dataClass[0],cutData[i].split(":"));
		}
		
		return(dbf);
	}
	
	public String getName(){
		return(name);
	}
	
	public String getHead(){
		return(val[0]);
	}
	
	public String getValue(){
		return(val[1]);
	}
}