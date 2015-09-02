package data;

import org.newdawn.slick.*;
import org.newdawn.slick.gui.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.particles.*;
import java.io.*;             
import java.text.*;
import java.util.*;
import dbformat.DBFormat;

enum POSITION{
	STOPR,STOPL,
	WALKR,WALKL,
	ATKR1,ATKL1,
	ATKR2,ATKL2,
	SITR,SITL,
	BENDR,BENDL,
	DEADR,DEADL,
};

enum JOBS{
	Warrior,Archer,Wizard;
	
	public int getID(){
		switch(this){
			case Archer: return(0);
			case Warrior: return(1);
			case Wizard: return(2);
			default: return(-1);
		}
	}
	
	public String getName(){
		switch(this){
			case Warrior: return("พลดาบ");
			case Archer:  return("พลธนู");
			case Wizard:  return("พ่อมด");
			default : return("null");
		}
	}
	
	public String getWeapon(){
		switch(this){
			case Warrior: return("ดาบ");
			case Archer:  return("ธนู");
			case Wizard:  return("ไม้เท้าเวท");
			default : return("null");
		}
	}
};

enum SoliderJob{
	sword,archer,axe,twohand,hero;
	
	public int getID(){
		switch(this){
			case sword:return(0);
			case archer:return(1);
			case axe:return(2);
			case twohand:return(3);
			case hero:return(4);
			default:return(-1);
		}
	}
}

enum WOLKERS{
	Villagers,Farmer,Carpenter;
	
	public String getName(){
		switch(this){
			case Villagers: return("ชาวบ้าน");
			case Carpenter: return("ช่างไ้ม้");
			case Farmer: return("เกษตรกร");
			default : return("null");
		}
	}
	
};

enum Map{
	Map_1("ทางไปฟาร์ม" ,(byte)1),
	Map_2("สถานีอนามัย และ ห้องสมุด" ,(byte)2),
	Map_3("หมู่บ้านโบราณ",(byte)3),
	Map_4("ประตูเมือง"	,(byte)4),
	Map_5("ชายแดน"	,(byte)5),
	Map_6("ดินแดนศัตรู",(byte)6),
	Map_7("ค่ายศัตรู",(byte)7);
	
	Map(String n,byte id){
		this.name = n;
		this.index = id;
		this.LoadResource();
	}
	public int getX(){
		return(x);
	}	
	public int getY(){
		return(y);
	}	
	public void setPos(int x,int y){
		this.x=x;
		this.y=y;
	}
	public int getSize(){
		return(7);
	}
	public int getWidth(){
		return(1024);
	}
	public Image getMap() throws SlickException {
		switch(this){
			case Map_1 :return(new Image(FileList.mapDir+"map_base04.png"));
			case Map_2 :return(new Image(FileList.mapDir+"map_base05.png"));
			case Map_3 :return(new Image(FileList.mapDir+"map_base06.png"));
			case Map_4 :return(new Image(FileList.mapDir+"map_base07.png"));
			case Map_5 :return(new Image(FileList.mapDir+"map_base08.png"));
			case Map_6 :return(new Image(FileList.mapDir+"map_base_dark.png"));
			case Map_7 :return(new Image(FileList.mapDir+"map_base09.png"));
			default:return(new Image(FileList.mapDir+"map_base.png")); 
		}
	}
	public void rendingObjectMap(Color c) throws SlickException{
		switch(this){
			case Map_1 :{
				g.drawImage(img[3],0,510,c);
				g.drawImage(img[0],400,200,c);
				break;
			}
			case Map_2 :{
				g.drawImage(img[3],0,510,c);
				g.drawImage(img[1],650,220,c);
				break;
			}
			case Map_3 :{
				g.drawImage(img[3],0,493,c);
				g.drawImage(img[0],510,220,c);
				break;
			}
			case Map_4 :{
				g.drawImage(img[3],0,493,c);
				g.drawImage(img[4],594,359,c);
				g.drawImage(img[0],270,250,c);
				break;
			}
			case Map_5 :{
				g.drawImage(img[5],0,470,new Color(c.getRed(),c.getGreen(),c.getBlue(),150));
				break;
			}
			case Map_6 :{
				g.drawImage(img[7],0,470,new Color(c.getRed(),c.getGreen(),c.getBlue(),150));
				break;
			}
			case Map_7:{
				g.drawImage(img[6],218,342,c);
				break;
			}
		}
	}
	public String get(){
		return("Map name : "+this.name+" ID : "+this.index);
	}
	public String getName(){
		return(this.name);
	}
	public byte getIndex(){
		return(this.index);
	}
	public void LoadResource(){
		try{
			this.img = new Image[]{
				new Image(FileList.objDir+"Tree1.png"),
				new Image(FileList.objDir+"Tree2.png"),
				new Image(FileList.objDir+"Tree3.png"),
				new Image(FileList.objDir+"plant_01.png"),
				new Image(FileList.objDir+"Door.png"),
				new Image(FileList.objDir+"plant_02.png"),
				new Image(FileList.objDir+"Door2.png"),
				new Image(FileList.objDir+"plant_03.png"),
			};
		}catch(SlickException e){
		}
	}
	public Image[] getImageMap(){
		return(this.img);
	}
	
	private Image[] img = new Image[8];
	private Graphics g = new Graphics();
	private String name;
	private byte index;
	private int x=0,y=0;
}

enum General_Shield{
	gradeL("",300,(byte)5,(byte)1);
	General_Shield(String name,int price,byte def,byte lv){
		this.name = name;
		this.price = price;
		this.def = def;
		this.lv = lv;
	}
	
	public String name;
	public int price;
	public byte def,lv;
}

enum General_Boot{
	
}

enum General_Glove{
	
}

enum Weapon_Sword{
	gradeL("",200,(short)12, (byte)1,(byte)2,(byte)1,""),
	gradeK("",500,(short)18, (byte)1,(byte)2,(byte)2,""),
	gradeJ("",1000,(short)26, (byte)1,(byte)3,(byte)3,""),
	gradeI("",1700,(short)34, (byte)2,(byte)3,(byte)4,""),
	gradeH("",2500,(short)40, (byte)2,(byte)4,(byte)5,""),
	gradeG("",3800,(short)45, (byte)2,(byte)4,(byte)6,""),
	gradeF("",4900,(short)50, (byte)3,(byte)5,(byte)7,""),
	gradeE("",6700,(short)55, (byte)3,(byte)5,(byte)8,""),
	gradeD("",7800,(short)60,(byte)4,(byte)6,(byte)9,""),
	gradeC("",8900,(short)65,(byte)4,(byte)6,(byte)10,""),
	gradeB("",9650,(short)70,(byte)4,(byte)7,(byte)11,""),
	gradeA("",11400,(short)80,(byte)5,(byte)8,(byte)12,"");
	
	Weapon_Sword(String name,int price,short atk,byte abi,byte agi,byte lv,String comment){
		this.name = name;
		this.price = price;
		this.atk = atk;
		this.abi = abi;
		this.agi = agi;
		this.lv = lv;
		this.comment = comment;
	}
	
	public String getName(){return(name);}
	public String getComment(){return(comment);}
	public short getAtk(){return(atk);}
	public int getPrice(){return(price);}
	public byte getAgi(){return(agi);}
	public byte getAbi(){return(abi);}
	public byte getLv(){return(lv);}
	
	public Image getImg() throws SlickException {
		switch(this){
			case gradeL: return(new Image(FileList.iconDir+"ic80.png"));
			case gradeK: return(new Image(FileList.iconDir+"ic80.png"));
			case gradeJ: return(new Image(FileList.iconDir+"ic80.png"));
			case gradeI: return(new Image(FileList.iconDir+"ic80.png"));
			case gradeH: return(new Image(FileList.iconDir+"ic80.png"));
			case gradeG: return(new Image(FileList.iconDir+"ic80.png"));
			case gradeF: return(new Image(FileList.iconDir+"ic80.png"));
			case gradeE: return(new Image(FileList.iconDir+"ic80.png"));
			case gradeD: return(new Image(FileList.iconDir+"ic80.png"));
			case gradeC: return(new Image(FileList.iconDir+"ic80.png"));
			case gradeB: return(new Image(FileList.iconDir+"ic80.png"));
			case gradeA: return(new Image(FileList.iconDir+"ic80.png"));
			default: return(new Image(FileList.iconDir+"ic80.png"));
		}
	}
	
	private String name,comment;
	private short atk;
	private int price;
	private byte agi,abi,lv;
}

enum Warrior_Amor{
}

enum Warrior_Hat{
}

enum Arrow{
	a,b,c;
	
	public Image getImage() throws SlickException{
		switch(this){
			case a:return(new Image(FileList.imgeDir+"arrow1"));
			case b:return(new Image(FileList.imgeDir+"arrow1"));
			case c:return(new Image(FileList.imgeDir+"arrow1"));
			default:return(new Image(FileList.imgeDir+"arrow1"));
		}
	}
	
	public Image getIcon() throws SlickException{
		switch(this){
			case a:return(new Image(FileList.iconDir+"ic11.png"));
			case b:return(new Image(FileList.iconDir+"ic11.png"));
			case c:return(new Image(FileList.iconDir+"ic11.png"));
			default:return(new Image(FileList.iconDir+"ic11.png"));
		}
	}
	
	public String getComment(){
		switch(this){
			case a:return("ลูกธนูไม้");
			case b:return("ลูกธนูหัวโลหะ");
			case c:return("ลูกธนูหัวเงิน");
			default:return("null");
		}
	}
	
	public int getATK(){
		switch(this){
			case a:return(2);
			case b:return(4);
			case c:return(8);
			default:return(0);
		}
	}
}

enum Weapon_Bow{
	gradeL("",200,(short)10, (byte)1,(byte)2,(byte)1,""),
	gradeK("",500,(short)16, (byte)1,(byte)2,(byte)2,""),
	gradeJ("",1000,(short)24, (byte)1,(byte)3,(byte)3,""),
	gradeI("",1700,(short)32, (byte)2,(byte)3,(byte)4,""),
	gradeH("",2500,(short)38, (byte)2,(byte)4,(byte)5,""),
	gradeG("",3800,(short)44, (byte)2,(byte)4,(byte)6,""),
	gradeF("",4900,(short)50, (byte)3,(byte)5,(byte)7,""),
	gradeE("",6700,(short)56, (byte)3,(byte)5,(byte)8,""),
	gradeD("",7800,(short)60,(byte)4,(byte)6,(byte)9,""),
	gradeC("",8900,(short)65,(byte)4,(byte)6,(byte)10,""),
	gradeB("",9650,(short)76,(byte)4,(byte)7,(byte)11,""),
	gradeA("",11400,(short)80,(byte)5,(byte)8,(byte)12,"");
	
	Weapon_Bow(String name,int price,short atk,byte abi,byte dex,byte lv,String comment){
		this.name = name;
		this.price = price;
		this.atk = atk;
		this.abi = abi;
		this.dex = dex;
		this.lv = lv;
		this.comment = comment;
	}
	
	public String getName(){return(name);}
	public String getComment(){return(comment);}
	public short getAtk(){return(atk);}
	public int getPrice(){return(price);}
	public byte getDex(){return(dex);}
	public byte getAbi(){return(abi);}
	public byte getLv(){return(lv);}
	
	public Image getImg() throws SlickException {
		switch(this){
			case gradeL: return(new Image(FileList.iconDir+"ic81.png"));
			case gradeK: return(new Image(FileList.iconDir+"ic81.png"));
			case gradeJ: return(new Image(FileList.iconDir+"ic81.png"));
			case gradeI: return(new Image(FileList.iconDir+"ic81.png"));
			case gradeH: return(new Image(FileList.iconDir+"ic81.png"));
			case gradeG: return(new Image(FileList.iconDir+"ic81.png"));
			case gradeF: return(new Image(FileList.iconDir+"ic81.png"));
			case gradeE: return(new Image(FileList.iconDir+"ic81.png"));
			case gradeD: return(new Image(FileList.iconDir+"ic81.png"));
			case gradeC: return(new Image(FileList.iconDir+"ic81.png"));
			case gradeB: return(new Image(FileList.iconDir+"ic81.png"));
			case gradeA: return(new Image(FileList.iconDir+"ic81.png"));
			default: return(new Image(FileList.iconDir+"ic81.png"));
		}
	}
	
	private String name,comment;
	private short atk;
	private int price;
	private byte dex,abi,lv;
}

enum Archer_Amor{
}

enum Archer_Hat{
}

enum Weapon_Wizard{
	gradeL("",200,(short)5,(short)15, (byte)1,(byte)2,(byte)1,""),
	gradeK("",500,(short)8,(short)25, (byte)1,(byte)2,(byte)2,""),
	gradeJ("",1000,(short)14,(short)35, (byte)1,(byte)3,(byte)3,""),
	gradeI("",1700,(short)18,(short)45, (byte)2,(byte)3,(byte)4,""),
	gradeH("",2500,(short)24,(short)55, (byte)2,(byte)4,(byte)5,""),
	gradeG("",3800,(short)30,(short)65, (byte)2,(byte)4,(byte)6,""),
	gradeF("",4900,(short)35,(short)75, (byte)3,(byte)5,(byte)7,""),
	gradeE("",6700,(short)40,(short)85, (byte)3,(byte)5,(byte)8,""),
	gradeD("",7800,(short)46,(short)95,(byte)4,(byte)6,(byte)9,""),
	gradeC("",8900,(short)52,(short)105,(byte)4,(byte)6,(byte)10,""),
	gradeB("",9650,(short)58,(short)115,(byte)4,(byte)7,(byte)11,""),
	gradeA("",11400,(short)64,(short)130,(byte)5,(byte)8,(byte)12,"");
	
	Weapon_Wizard(String name,int price,short atk,short matk,byte abi,byte intl,byte lv,String comment){
		this.name = name;
		this.price = price;
		this.atk = atk;
		this.matk = matk;
		this.abi = abi;
		this.intl = intl;
		this.lv = lv;
		this.comment = comment;
	}
	
	public String getName(){return(name);}
	public String getComment(){return(comment);}
	public short getAtk(){return(atk);}
	public short getMatk(){return(matk);}
	public int getPrice(){return(price);}
	public byte getInt(){return(intl);}
	public byte getAbi(){return(abi);}
	public byte getLv(){return(lv);}
	public Image getImg() throws SlickException {
		switch(this){
			case gradeL: return(new Image(FileList.iconDir+"ic82.png"));
			case gradeK: return(new Image(FileList.iconDir+"ic82.png"));
			case gradeJ: return(new Image(FileList.iconDir+"ic82.png"));
			case gradeI: return(new Image(FileList.iconDir+"ic82.png"));
			case gradeH: return(new Image(FileList.iconDir+"ic82.png"));
			case gradeG: return(new Image(FileList.iconDir+"ic82.png"));
			case gradeF: return(new Image(FileList.iconDir+"ic82.png"));
			case gradeE: return(new Image(FileList.iconDir+"ic82.png"));
			case gradeD: return(new Image(FileList.iconDir+"ic82.png"));
			case gradeC: return(new Image(FileList.iconDir+"ic82.png"));
			case gradeB: return(new Image(FileList.iconDir+"ic82.png"));
			case gradeA: return(new Image(FileList.iconDir+"ic82.png"));
			default: return(new Image(FileList.iconDir+"ic82.png"));
		}
	}
	
	private String name,comment;
	private short atk,matk;
	private int price;
	private byte intl,abi,lv;
}

enum Wizard_Amor{
}

enum Wizard_Hat{
}

enum slType{
	support,active,basic,comode;
	
	public String getName(){
		switch(this){
			case support : return("สนับสนุน");
			case active : return("ใ้ช้งานได้");
			case basic : return("พลังแห่งสัตว์โบราณ");
			case comode : return("เปิดปิดเพิ่อใช้งาน");
			default : return("หาข้อมูลไม่พบ");
		}
	}
}

enum EEW{
	war_0,war_1,war_2,war_3,war_4,war_5,war_6,war_7,war_8,war_9,war_10,war_11,war_12;
	
	public void LOAD(){
		switch(this){
			case war_1:{ //1
				start();
				sword  = 3;
				archer = 2;
				break;
			}
			case war_2:{
				start();
				sword  = 4;
				archer = 2;
				break;
			}
			case war_3:{ //2
				start();
				spear  = 3;
				archer = 2;
				break;
			}
			case war_4:{
				start();
				spear  = 4;
				archer = 4;
				break;
			}
			case war_5:{ //3
				start();
				superr = 3;
				archer = 5;
				break;
			}
			case war_6:{
				start();
				superr = 5;
				archer = 5;
				break;
			}
			case war_7:{ //4
				start();
				hero = 5;
				archer = 5;
				break;
			}
			case war_8:{
				start();
				hero = 6;
				archer = 6;
				break;
			}
			case war_9:{ //5
				start();
				hero = 5;
				gunner = 3;
				break;
			}
			case war_10:{
				start();
				hero = 6;
				gunner = 5;
				break;
			}
			case war_11:{//6
				start();
				hero = 10;
				superr  = 5;
				archer = 10;
				break;
			}
			case war_12:{
				start();
				hero = 10;
				superr  = 10;
				archer = 10;
				break;
			}
			default:{
				start();
			}
		}
	}
	
	public void start(){
		spear  = 0;
		archer = 0;
		hero = 0;
		superr = 0;
		gunner = 0;
	}
	
	public int sword,spear,archer,superr,gunner,hero;
}

enum ToolFarm{
	Nullmode,
	Digmode,
	Cutmode,
	Lifemode,
	Watermode,
	CropAmode,
	CropBmode,
	CropCmode,
	CropDmode,
}

class FileList{
	public static final String fontDir = "Resource/font/";
	public static final String imgeDir = "Resource/img/";
	public static final String mapsDir = "Resource/map/";
	public static final String bgsDir = "Resource/audio/bgs/";
	public static final String bgmDir = "Resource/audio/bgm/";
	public static final String effectDir = "Resource/effect/";
	public static final String objDir = "Resource/object/";
	public static final String logoDir = "Resource/Logo/";
	public static final String iconDir = "Resource/icon/";
	public static final String mapDir = "Resource/map/";
	public static final String farmDir = "Resource/farm/";
}

public class Arjong extends BasicGame{
	private Image title,mouse,arLog,boxWea,arLogo,edge,tile,hKey,storyImg;
	private Image[] logo = new Image[4];
	private Image loseLogo,winLogo,nuller,hLogo,bBack,gameItem[],story2,scene[] = new Image[2];
	private Could[] could = new Could[3];
	private Image cursorFarm[] = new Image[5],frameInterface;
	private Image bgC,back,but1[] = new Image[2],but2[] = new Image[2],menuDialog[] = new Image[2];
	private Image[] bm = new Image[3],button = new Image[5],menu = new Image[5],iconWea = new Image[3],icon = new Image[15],bg = new Image[3];
	private enum GAMESTATE{
		logo1,
		logo2,
		splash,
		title,
		creadit,
		load,
		how2,
		gamecreate,
		story,
		game,
		over,
		win,
		farm,
		Load,
		LoadOnStage,
		SaveOnStage,
		exit
	};
	private GAMESTATE GAME = GAMESTATE.logo1;
	private int alpha = 255,fade = 3,j=0; 
	private static int scrWidth,scrHeight;
	private Music[] music = new Music[6];
	private Sound[] sound = new Sound[9];
	private short _x,_y,x,y;
	private Font[] font = new Font[3];
	private boolean logic_mouse_hit = false;
	private ScrollTxt scrollTxt[] = new ScrollTxt[5];
	private ScrollStory scrollStory;
	private ScrollSkillList ssl;
	private ParticleSystem flowerAura,blood,aura,firehmai,smoke;
	private CHARACTER HERO,AC,WR,WZ;
	private static float version = 1.5f;
	private EEW Evolution;
	private ArrayList<ENEMY> enemy;
	private ArrayList<SOLIDER> solider;
	private HOME[] myHome,enHome;
	private int enemySum,soliderSum;
	private boolean[] menugame = {false,false,false,false,false,false,false};
	private boolean hitOK = false,loadEvo = false;
	private NPC[] npc = new NPC[9];
	private NPC_SOLIDER[] npcSolider_Arunetara = new NPC_SOLIDER[3];
	private NPC_ENEMY[] npcEnemy = new NPC_ENEMY[3];
	private Graphics g = new Graphics();
	private static AppGameContainer game;
	private static boolean VSyncMode;
	private boolean descript = false;
	private char gameSelf = 'g';
	private int delayEnd;
	private boolean oneSound = false,twoSound = false,showAI = false,farmMode = false;
	private ScrollList scrollList;
	private boolean loadgame = false;
	public static int WMAX = 15;
	public JMap map;
	public JMapManager mapFarm;
	private ToolFarm toolFarm = ToolFarm.Nullmode;
	private String txtA,txtB,txtC,nameMap;
	private ScrollMenu scrollMenu;
	private Myinterfacetop myInterTop;
	private NumberFormat many = NumberFormat.getIntegerInstance();
	private JDrawingText DrawingText;
	private int minProcess = 0,maxProcess = 100,delayProcess = random(30,100);
	private TargetLog tl;
	private TextField inputSave;
	private slowEffect slow;
	private boolean helpgame = true;
	private Image imgwarring;
	private Music musicHelp;
	private int modeIntDescript;
	
	public float getVersion(){
		return(this.version);
	}
	
	public Arjong(){
		super("Arjong - มหาสงครามศึกแผ่นดิผู้กล้า "+version);
	}
	
	public static void main(String[] args) throws SlickException{
		scrWidth = 1024; scrHeight = 768;
		boolean screenf = false;
		
		System.out.println(args[0]+" ,"+args[1]);
		if(args[0].equals("1")){
			VSyncMode = true;
		}else if(args[0].equals("0")){
			VSyncMode = false;
		}
		if(args[1].equals("1")) screenf = true;
		else if(args[1].equals("0")) screenf = false;
		
		
		System.out.println("Vsyncmode is : "+VSyncMode);
		game = new AppGameContainer(new Arjong(),scrWidth,scrHeight,screenf);
		game.setIcon(FileList.logoDir+"icon.PNG");
		game.setMouseGrabbed(true);
		game.setDefaultMouseCursor();
		//game.destroy();
		game.start();
	}

	private int random(int min,int max){
		return(min+(int)(Math.random()*max));
	}
	
	public void init(GameContainer container) throws SlickException{
		try{
			container.setShowFPS(false);
			container.setVSync(VSyncMode);
			container.setSmoothDeltas(true);
			
			//------------ General Variables ------------------
			
			myInterTop = new Myinterfacetop();
			this.DrawingText = new JDrawingText();
			map = new JMap(new int[][]{
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			},container);
					  
			mapFarm = new JMapManager(map,320,150);
			scrollMenu = new ScrollMenu();
			frameInterface = new Image(FileList.farmDir+"farmInterface.png");
			delayEnd = 250;
			myHome = new HOME[2];
			enHome = new HOME[2];
			
			AC = new Archer();
			WR = new Warrior();
			WZ = new Wizard();
			//-------------------------------------------------
			slow = new slowEffect(container);
			
			font[0] = new AngelCodeFont(FileList.fontDir+"font_01.fnt",FileList.fontDir+"font_01.png");
			font[1] = new AngelCodeFont(FileList.fontDir+"simpleFont.fnt",FileList.fontDir+"simpleFont.png");
			font[2] = new AngelCodeFont(FileList.fontDir+"font_02.fnt",FileList.fontDir+"font_02.png");
			
			imgwarring = new Image(FileList.logoDir+"warnig.png");
			
			musicHelp = new Music("Resource/audio/msg/Concept.wav");
			
			//--------------------------------------------------
			gameItem = new Image[]{
				new Image(FileList.iconDir+"icItem01.png"),
				new Image(FileList.iconDir+"icItem02.png"),
				new Image(FileList.iconDir+"icItem03.png"),
				new Image(FileList.iconDir+"icItem04.png"),
				new Image(FileList.iconDir+"icItem04.png"),
				new Image(FileList.iconDir+"icItem04.png"),
			};
			bBack = new Image(FileList.imgeDir+"bback.PNG");
			nuller = new Image("Resource/img/null");
			loseLogo = new Image(FileList.logoDir+"lose.png");
			bgC = new Image(FileList.imgeDir+"ScreenSelect.PNG");
			title = new Image(FileList.imgeDir+"Title.png");
			
			inputSave = new TextField(container,font[1],(scrWidth-300)/2,550,300,20);
			
			logo = new Image[]{
				new Image(FileList.imgeDir+"logoIt.PNG"),
				new Image(FileList.imgeDir+"logo2.png"),
				new Image(FileList.imgeDir+"logo3.png"),
			};
			
			mouse = new Image(FileList.imgeDir+"cursor.png");
			arLogo = new Image(FileList.imgeDir+"ArjongLogo.png");
			boxWea = new Image(FileList.imgeDir+"BoxWeapon");
			edge = new Image(FileList.imgeDir+"edge");
			hKey = new Image(FileList.imgeDir+"helpbox.PNG");
			back = new Image(FileList.imgeDir+"black.PNG");
			storyImg = new Image(FileList.imgeDir+"story.png");
			winLogo = new Image(FileList.logoDir+"winner.PNG");
			hLogo = new Image(FileList.iconDir+"myHome.png");
			story2 = new Image(FileList.imgeDir+"story2.png");
			cursorFarm = new Image[]{
				new Image(FileList.farmDir+"cursor/ic01.png"),
				new Image(FileList.farmDir+"cursor/ic02.png"),
				new Image(FileList.farmDir+"cursor/ic03.png"),
				new Image(FileList.farmDir+"cursor/ic04.png"),
				new Image(FileList.farmDir+"cursor/ic05.png"),
			};
			
			menuDialog = new Image[]{
				new Image(FileList.imgeDir+"Dialog_02.PNG"),
				new Image(FileList.imgeDir+"Dialog_03.PNG"),
			};
			
			button = new Image[]{
				new Image(FileList.imgeDir+"Buttonmenu.png"),
				new Image(FileList.imgeDir+"bClose.PNG"),
			};
			
			but1 = new Image[]{
				new Image(FileList.imgeDir+"ButtonED_01.PNG"),
				new Image(FileList.imgeDir+"ButtonED_02.PNG"),
			};
			
			but2 = new Image[]{
				new Image(FileList.imgeDir+"Button_ED03.PNG"),
				new Image(FileList.imgeDir+"Button_ED04.PNG"),
			};
			
			menu = new Image[]{
				new Image(FileList.imgeDir+"interface_menu"),
				new Image(FileList.imgeDir+"interface_menu2"),
				new Image(FileList.imgeDir+"main-interface"),
				new Image(FileList.imgeDir+"interface_mini"),
				new Image(FileList.imgeDir+"interface_menu3"),
				new Image(FileList.imgeDir+"interface_menu4.PNG"),
			};
			
			iconWea = new Image[]{
				new Image(FileList.imgeDir+"bow-icon"),
				new Image(FileList.imgeDir+"sword-icon"),
				new Image(FileList.imgeDir+"rod-icon"),
			};
			
			bm = new Image[]{
				new Image(FileList.imgeDir+"bm1"),
				new Image(FileList.imgeDir+"bm2"),
				new Image(FileList.iconDir+"bMenu.png"),
			};
			
			icon = new Image[]{
				new Image(FileList.iconDir+"ic1.png"),
				new Image(FileList.iconDir+"ic2.png"),
				new Image(FileList.iconDir+"ic12.png"),
				new Image(FileList.iconDir+"ic4.png"),
				new Image(FileList.iconDir+"ic5.png"),
				new Image(FileList.iconDir+"ic6.png"),
				new Image(FileList.iconDir+"ic7.png"),
				new Image(FileList.iconDir+"ic8.png"),
				new Image(FileList.iconDir+"ic9.png"),
				new Image(FileList.iconDir+"ic10.png"),
				new Image(FileList.iconDir+"icItem05.png"),
				new Image(FileList.iconDir+"icItem08.png"),
				new Image(FileList.iconDir+"icItem09.png"),
				new Image(FileList.iconDir+"icItem10.png"),
			};
		
			bg = new Image[]{
				new Image(FileList.mapsDir+"bg2.png"),
				new Image(FileList.mapsDir+"bg1.png"),
				new Image(FileList.mapsDir+"bg2.png"),
				new Image(FileList.mapsDir+"bg3.png"),
			};
			
			tile = new Image(FileList.mapsDir+"map_base.png");
			
			scene = new Image[]{
				new Image(FileList.imgeDir+"saveScreen.png"),
				new Image(FileList.imgeDir+"saveScreen.png"),
			};
			
			//---------------- Load Sound ----------------------
			music[0] = new Music(FileList.bgmDir+"bgm01.wav");
			music[1] = new Music(FileList.bgmDir+"bgm02.wav");
			music[2] = new Music(FileList.bgmDir+"over.wav");
			music[3] = new Music(FileList.bgmDir+"warring.wav");
			music[4] = new Music(FileList.bgmDir+"farm.wav");
			music[5] = new Music(FileList.bgmDir+"splash.wav");
			//--------------------------------------------------
			
			sound = new Sound[]{
				new Sound(FileList.bgsDir+"bgs02.wav"),
				new Sound(FileList.bgsDir+"bgs09.wav"),
				new Sound(FileList.bgsDir+"bgs11.wav"),
				new Sound(FileList.bgsDir+"bgs01.wav"),
				new Sound(FileList.bgsDir+"bgs12.wav"),
				new Sound(FileList.bgsDir+"end.wav"),
				new Sound(FileList.bgsDir+"waterdrinking.wav"),
				new Sound(FileList.bgsDir+"win.wav"),
				new Sound(FileList.bgsDir+"skill02.wav"),
			};
			
			//---------------- Effect --------------------------
			try{
				flowerAura = ParticleIO.loadConfiguredSystem(FileList.effectDir+"ef001.xml");
				flowerAura.setDefaultImageName(FileList.effectDir+"ef03.png");
				aura = ParticleIO.loadConfiguredSystem(FileList.effectDir+"ef003.xml");
				blood = ParticleIO.loadConfiguredSystem(FileList.effectDir+"bloody01.xml");
				blood.setDefaultImageName(FileList.effectDir+"ef01.png");
				firehmai = ParticleIO.loadConfiguredSystem(FileList.effectDir+"firehmai.xml");
				smoke = ParticleIO.loadConfiguredSystem(FileList.effectDir+"smoke.xml");
			}catch(IOException e){
			}
			
			could[0] = new Could(new Image(FileList.imgeDir+"ef2.png"),0.0f,50.0f);
			could[1] = new Could(new Image(FileList.imgeDir+"ef2.png"),1000.0f,300.0f);
			could[2] = new Could(new Image(FileList.imgeDir+"ef2.png"),600.0f,100.0f);
			
		}catch(Exception e){
			String errorReport = e.toString();
			int scrWidth = 300,scrHeight = 300;
			
			javax.swing.JFrame frame = new javax.swing.JFrame("Error Report !!");
			java.awt.Toolkit toolkit = frame.getToolkit();
			java.awt.Dimension size = toolkit.getScreenSize();
			
			frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
			frame.setSize(scrWidth,scrHeight);
			frame.setLocation((size.width-scrWidth)/2,(size.height-scrHeight)/2);
			javax.swing.JPanel panel = new javax.swing.JPanel();
			frame.getContentPane().add(panel);
			panel.setLayout(null);
			
			javax.swing.JButton butt = new javax.swing.JButton("Close");
			butt.setBounds((scrWidth-150)/2,scrHeight-100,130,60);
			
			butt.addActionListener(new java.awt.event.ActionListener(){
					public void actionPerformed(java.awt.event.ActionEvent e){
						System.exit(0);
					}
			});
			
			
			javax.swing.JTextPane pane = new javax.swing.JTextPane();
			pane.setBounds(0,10,scrWidth,scrHeight/2+10);
			pane.setContentType("text/html");
			String msg = "<p><b>Init Exception</p></b>"+
						"<p><b>init error is :</b> "+errorReport+"</p>"+
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
	
	public void update(GameContainer container,int deltra) throws SlickException{
		try{
			byte timerUpdate = 11;
			flowerAura.update((int)timerUpdate);
			aura.update((int)timerUpdate);
			smoke.update((int)timerUpdate);
			container.setSmoothDeltas(true);
			
			switch(GAME){
				case logo1:{
					alpha-=fade;
					if(alpha<=0-200){
						fade*=-1;
					}if(alpha>=255){
						fade*=-1;
						GAME = GAMESTATE.logo2;
				//		music[0].play();
				//		music[0].loop();
					}
					break;
				}
				case logo2:{
					alpha-=fade;
					if(alpha<=0-200){
						fade*=-1;
					}if(alpha>=255){
						fade*=-1;
						GAME = GAMESTATE.splash;
						music[0].play();
						music[0].loop();
				//		music[5].play();
				//		music[5].loop();
					}
					break;
				}
				case splash:{
					alpha-=fade;
					if(container.getInput().isKeyPressed(container.getInput().KEY_SPACE)){
						if(alpha<=255){
							alpha*=-1;
						//	music[5].stop();
							alpha = 255;
							GAME = GAMESTATE.title;
						}
					}
					break;
				}
				case title:{
					alpha-=fade;
					loadgame=true;
					break;
				}
				case creadit:{
					alpha-=fade;
					break;
				}
				case load:{
					alpha-=fade;
					break;
				}
				case how2:{
					alpha-=fade;
					break;
				}
				case gamecreate:{
					alpha-=fade;
					firehmai.update((int)timerUpdate);
					break;
				}
				case story:{
					alpha-=fade;
					break;
				}
				case game:{
					alpha-=fade*2;
					break;
				}
				case over:{
					alpha-=fade;
					break;
				}
				case win:{
					alpha-=fade;
					break;
				}
				case farm:{
					alpha-=fade;
					break;
				}
				case LoadOnStage:{
					alpha-=fade;
					break;
				}
				case SaveOnStage:{
					alpha-=fade;
					break;
				}
				case exit:{
					alpha-=fade;
					break;
				}
			}
		}
		catch(Exception e){
			String errorReport = e.toString();
			int scrWidth = 300,scrHeight = 300;
			
			javax.swing.JFrame frame = new javax.swing.JFrame("Error Report !!");
			java.awt.Toolkit toolkit = frame.getToolkit();
			java.awt.Dimension size = toolkit.getScreenSize();
			
			frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
			frame.setSize(scrWidth,scrHeight);
			frame.setLocation((size.width-scrWidth)/2,(size.height-scrHeight)/2);
			javax.swing.JPanel panel = new javax.swing.JPanel();
			frame.getContentPane().add(panel);
			panel.setLayout(null);
			
			javax.swing.JButton butt = new javax.swing.JButton("Close");
			butt.setBounds((scrWidth-150)/2,scrHeight-100,130,60);
			
			butt.addActionListener(new java.awt.event.ActionListener(){
					public void actionPerformed(java.awt.event.ActionEvent e){
						System.exit(0);
					}
			});
			
			
			javax.swing.JTextPane pane = new javax.swing.JTextPane();
			pane.setBounds(0,10,scrWidth,scrHeight/2+10);
			pane.setContentType("text/html");
			String msg = "<p><b>Update Exception</p></b>"+
						"<p><b>Update error is :</b> "+errorReport+"</p>"+
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
	
	public void render(GameContainer container,Graphics g) throws SlickException{
		try{
			g = new Graphics();
			g.setFont(font[1]);
			switch(GAME){
				case splash:{
					g.drawImage(new Image(FileList.imgeDir+"watting.PNG"),0,30);
					g.setFont(font[0]);
					g.drawString("Arjong มหาสงครามศึกแผ่นดินผู้กล้า Version 1.5",(1024-font[0].getWidth("Arjong มหาสงครามศึกแผ่นดิผู้กล้า Version 1.5"))/2,665);
					g.drawString("กด Space bar เมื่อพร้อม",(1024-font[0].getWidth("กด Space bar เมื่อพร้อม"))/2,630);
			//		drawBar(minProcess,maxProcess,227,615,571,33,new Color(50,30,20));
			//		g.drawString(minProcess+" %",(scrWidth-font[0].getWidth(minProcess+" %"))/2,615);
					
			//		if(minProcess<maxProcess){
			//			if(delayProcess > 0){
			//				delayProcess--;
			//			}else{
			//				delayProcess = random(1,100);
			//				minProcess+=random(1,10);
			//			}
			//		}else{
						
				//	}
					break;
				}
				case logo1:{
				/*	String labName = "Mind Technology";
					String year = "2009";
					String school = "Ubonratchathani Rajabhat University";
					String producerName = "Thammapon Oungtakool";
					String txt[] = {labName+" "+year,
									school,
									"present Arjong - Empire Warrior version  "+this.version+"  bye. "+producerName}; */
					g.drawImage(logo[0],0,0);
				/*	DrawingText.DrawText(txt[0],(scrWidth-font[1].getWidth(txt[0]))/2,(scrHeight-font[1].getHeight(txt[0]))-20,font[1]);
					DrawingText.DrawText(txt[1],(scrWidth-font[1].getWidth(txt[1]))/2,(scrHeight-font[1].getHeight(txt[1]))-10,font[1]);
					DrawingText.DrawText(txt[2],(scrWidth-font[1].getWidth(txt[2]))/2,(scrHeight-font[1].getHeight(txt[2])),font[1]);
				*/	break;
				}
				case logo2:{
					g.drawImage(logo[2],(scrWidth-logo[2].getWidth())/2,(scrHeight-logo[2].getHeight())/2);
					break;
				}
				case title:{
					titleRender();
					reMenu();
					
					g.drawImage(menu[0],(scrWidth-menu[0].getWidth())-63,230);

					if(BUTTON(button[0],"เริ่มเกม",container,(scrWidth-button[0].getWidth())/2+320,250)){
						GAME = GAMESTATE.gamecreate;
						scrollTxt[3] = new ScrollTxt();
						scrollTxt[3].setRect(730,327,285,245);
						
						alpha = 255;
						enemySum = 0;
						HERO = new Archer();
						HERO.JOB = JOBS.Archer;
						HERO.LOADANIM();
						HERO._LOAD();
						HERO.warpTo(498,384);

					};
					if(BUTTON(button[0],"แนะนำเบื้องต้น",container,(scrWidth-button[0].getWidth())/2+320,250+(51+5))){
						scrollTxt[0] = new ScrollTxt();
						scrollTxt[0].setRect(104,447,589,180);
						scrollTxt[0].subject("Tip");
						scrollTxt[0].addString("HP  : คือ ค่าพลังชีวิตของตัวละคร หากมันมีค่า ลดลง ก็หมายถึงตัวละครได้รับอันตรายและหาก พลังชีวิต \nเหลือ 0 ก็คือหมดสติ หรือ ตายไป และจะฝื้นตัวใหม่ภายใน 15 วินาที");
						scrollTxt[0].addString("SP  : คือ ค่าพลังเวทมนต์ของตัวละคร ในการใช้ทักษะ (Skill) ของตำแหน่งต่างๆ โดยจะลดลงตามค่าที่ ทักษะกำหนด");
						scrollTxt[0].addString("PW : คือ ค่าพลังงานในร่างกาย จะลดลงกับการวิ่ง และหาก PW เหลือ 0 ก็จะหยุดเดิน ไม่สามารถวิ่งได้ต่อ \nโดยในการวิ่งแต่ละครั้งค่า PW จะลดลง 3");
						scrollTxt[0].addString("WP : คือ ค่าความต้องการน้ำของร่างกาย โดยจะลดลง ทุกๆ 8 - 15 วินาที ครั้งละ 3 โดยจะส่งผล\nทำให้เกิดอาการผิดปกติต่างๆ และ อาจจะหมดสติไปได้");
						scrollTxt[0].addString("----------------------------------------------------------------------------------------------------------------------------------");
						scrollTxt[0].addString("STATUS ");
						scrollTxt[0].addString("----------------------------------------------------------------------------------------------------------------------------------");
						scrollTxt[0].addString("Con : คือค่าความอดทน แทนด้วยพลังชีวิต (HP) ของตัวละคร");
						scrollTxt[0].addString("Int   : คือค่าพลังการโจมตีทางเวทมนต์ และ พลังจิตร (SP) ของตัวละคร ");
						scrollTxt[0].addString("Abi   : คือความสามารถ ในการเป็นผู้นำ ช่วยในการเพิ่มจำนวนและความสามารถของลูกสมุรได้ ");
						scrollTxt[0].addString("Str   : คือค่าพลังโจมตีระยะใกล้ ด้วย ดาบ หรือ ไม้เท้า และส่งพลต่อสกิลสาย พลังงาน ");
						scrollTxt[0].addString("Dex  : คือค่าพลังโจมตีระยะไกลออกไปจากตัว เช่น ธนู แต่หากมีข้าศึกเข้ามาในระยะใกล้จะลดแรงลง ");
						scrollTxt[0].addString("Agi  : คือค่าความเร็วในการออกดาบของผู้เล่น และ ส่งผลต่อความเร็วในการดีดลูกดอก ด้วย ");
						scrollTxt[0].addString("Mem  : คือค่าพลังที่ส่งผลต่อความเร็วในการสกด หรือ ร่ายเวทมนต์ สำหรับนักเวท ");
						scrollTxt[0].addString("Weapon Attack  : คือค่าพลังโจมตีรวมของผู้เล่นและอาวุธ ");
						scrollTxt[0].addString("Magic Attack  : คือค่าพลังโจมตีที่รวมเอา int และ พลังของไม้เท้าเวท ");
						scrollTxt[0].addString("Aspd  : คือค่าความเร็วที่มาจาก ค่า agi โดยจะนับจาก agi/5 = aspd");
						scrollTxt[0].addString("Msspd  : ย่อมาจาก (Magic Spell Speed) : หรือค่าในการร่ายเวทมนต์ของตัวละครสายเวท");
						scrollTxt[0].addString("Def  : คือค่าทนทานของตัวละคร ช่วยในการรับคม มีด หอก ดาบ ฯลฯ ทำให้อึดขึ้น");
						
						GAME=GAMESTATE.how2;
						alpha=255;
					}
					if(BUTTON(button[0],"โหลดเกม",container,(scrWidth-button[0].getWidth())/2+320,250+(51+5)*2)){
						scrollList = new ScrollList();
						scrollList.setRect(124,208,773,359);
						scrollList.subject("LOAD GAME");
						this.LoadSystem();
						GAME=GAMESTATE.load;
						alpha=255;
					}
					if(BUTTON(button[0],"ผู้พัฒนา",container,(scrWidth-button[0].getWidth())/2+320,250+(51+5)*3)){
						scrollTxt[1] = new ScrollTxt();
						scrollTxt[1].setRect(104,219,589,398);
						scrollTxt[1].subject("ทีมงานผู้พัฒนา");
						scrollTxt[1].addString("โครงการ อาจอง - มหาสงครามศึกแผ่นดินผู้กล้า <12P11I042>");
						scrollTxt[1].addString("Keywords : โปรแกรมเพื่อความบันเทิง  ระดับ : นิสิต นักศึกษา");
						scrollTxt[1].addString("\nมีผู้ร่วมโครงการดังนี้");
						scrollTxt[1].addString("---------------------------------------------------------");
						scrollTxt[1].addString("หัวหน้าโครงการ");
						scrollTxt[1].addString("นาย ธรรมพล อึ้งตระกูล  <Programmer & Graphics Design>");
						scrollTxt[1].addString("ภาควิชา : เทคโนโลยีสารสนเทศ  คณะ : เทคโนโลยีอุตสาหกรรม");
						scrollTxt[1].addString("มหาวิทยาลัยราชภัฏอุบลราชธานี");
						scrollTxt[1].addString("---------------------------------------------------------");
						scrollTxt[1].addString("อาจารย์ที่ปรึกษาโครงการ");
						scrollTxt[1].addString("นาย สัณชัย ยงกุลวณิช");
						scrollTxt[1].addString("ภาควิชา : เทคโนโลยีสารสนเทศ  คณะ : เทคโนโลยีอุตสาหกรรม");
						scrollTxt[1].addString("มหาวิทยาลัยราชภัฏอุบลราชธานี");
						scrollTxt[1].addString("---------------------------------------------------------");
						scrollTxt[1].addString("กิตติกรรมประกาศ");
						scrollTxt[1].addString("โครงการ อาจอง มหาสงครามศึกแผ่นดินผู้กล้า <12P11I042>  จะไม่สำเร็จลุล่วงด้วยดีหากไม่ได้");
						scrollTxt[1].addString("รับทุนอุดหนุนโครงการการพัฒนาโปรแกรมคอมพิวเตอร์แห่งประเทศไทย ครั้งที่ 12 จาก ศูนย์เทคโนโลยี");
						scrollTxt[1].addString("อิเล็กทรอนิกส์และคอมพิวเตอร์แห่งชาติ สำนักงานพัฒนาวิทยาศาสตร์และเทคโนโลยีแห่งชาติ และ ");
						scrollTxt[1].addString("สำนักงานส่งเสริมอุตสาหกรรมซอฟต์แวร์แห่งชาติ");
						scrollTxt[1].addString("โครงการนี้จะไม่สามารถริเริ่มและไม่สามารถพัฒนาได้หากไม่ได้รับคำแนะนำและแนวคิดต่างๆจาก");
						scrollTxt[1].addString("อาจารย์ สัณชัย ยงกุลวณิช อาจารย์ที่ปรึกษาโครงการ และ โครงการนี้จะไม่สามารถพัฒนาต่อจนสำเร็จ");
						scrollTxt[1].addString("ได้หากไม่ได้รับ กำลังใจจาก ครอบครัว และ เพื่อนๆ สาขาเทคโนโลยีสารสนเทศ ทุกคน");
						scrollTxt[1].addString("ผู้พัฒนาขอขอบคุณทุกท่านที่ช่วยให้การพัฒนาโครงการ อาจอง - มหาสงครามศึกแผ่นดินผู้กล้า นี้สำเร็จ");
						scrollTxt[1].addString("ลุล่วงได้ด้วยดี");

						GAME=GAMESTATE.creadit;
						alpha=255;
					}
					if(BUTTON(button[0],"ออกจากเกม",container,(scrWidth-button[0].getWidth())/2+320,250+(51+5)*4)){
						GAME = GAMESTATE.exit;
						alpha=255;
						menugame[2] = true;
					}
					
					break;
				}
				case creadit:{
					g.drawImage(new Image(FileList.imgeDir+"LoadSplash.png"),0,0);
					scrollTxt[1].render(container,mouseHIT(container));		
					if(BUTTON(button[0],"ตกลง",container,(scrWidth-button[0].getWidth())/2+320,580)){GAME = GAMESTATE.title;alpha = 255;};		
					break;
				}
				case load:{
					g.drawImage(new Image(FileList.imgeDir+"LoadSplash.png"),0,0);
					scrollList.render(container,mouseHIT(container));
					
					if(BUTTON(button[0],"ตกลง",container,(scrWidth-button[0].getWidth())/2+320,580)){
						GAME = GAMESTATE.title;
						alpha = 255;
					}
					
					break;
				}
				case how2:{
					g.drawImage(new Image(FileList.imgeDir+"LoadSplash.png"),0,0);
					if(BUTTON(button[0],"ตกลง",container,(scrWidth-button[0].getWidth())/2+320,580)){
						GAME = GAMESTATE.title;
						alpha = 255;
					};

					scrollTxt[0].render(container,mouseHIT(container));
					g.drawImage(hKey,0,180);
					
					DrawingText.DrawText("ปุ่มลัด ทักษะและอาหาร",207,277,font[1]);
					DrawingText.DrawText("ปุ่ม โจมตี",274,353,font[1]);
					DrawingText.DrawText("ปุ่ม วิ่ง",640,205,font[1]);
					DrawingText.DrawText("ปุ่มเดิน ซ้าย/ขวา",750,353,font[1]);
					
					break;
				}
				case gamecreate:{
					g.drawImage(bgC,0,0);
					
					HERO.DrawSelf();
					HERO.Update();
					flowerAura.render();
					g.drawImage(back,0,0);
					String ver = "เวอร์ชั่น  "+this.version;
					g.drawImage(new Image(FileList.imgeDir+"chText.png"),100,80);
					
					switch(j){
						case 0:{
							if(hitOK){
								HERO = AC;
								HERO.JOB = JOBS.Archer;
								HERO.LOADANIM();
								HERO._LOAD();
								HERO.warpTo(498,384);
								hitOK=false;
							}
							break;
						}
						case 1:{
							if(hitOK){
								HERO = WR;
								HERO.JOB = JOBS.Warrior;
								HERO.LOADANIM();
								HERO._LOAD();
								HERO.warpTo(498,384);
								hitOK=false;
							}
							break;
						}
						case 2:{
							if(hitOK){
								HERO = WZ;
								HERO.JOB = JOBS.Wizard;
								HERO.LOADANIM();
								HERO._LOAD();
								HERO.warpTo(498,384);
								hitOK=false;
							}
							break;
						}
					}
					
					firehmai.render(490,450);
					
					if(BUTTON(button[0],"ตกลง",container,770,610)){
						if(HERO != null){
							soliderSum = 0;
							solider = new ArrayList<SOLIDER>();
						
							scrollTxt[2] = new ScrollTxt();
							scrollTxt[2].setRect(490,628,326,130);
							
							//NPC
							npc[0] = new NPC_01(1,700);
							npc[0].LOADMESSAGE("สวัสดี เธอสบายดีไหม,ร้อนจังเลยแหะ,เป็นหวัดไม่ดีนะ,กินน้ำไหม,เธอคือใคร,อืม......");
							
							npc[1] = new NPC_02(2,600);
							npc[1].LOADMESSAGE("ยายว่าอากาศมันดีนะ,เอาผักกาดไหม,พักก่อนไหม,โอ้ย ปวดหลังจังเลย,เธอว่ายายสวยไหม");
							
							npc[3] = new NPC_03(2,350);
							npc[3].LOADMESSAGE("สวัสดี ยายแปง,สวัสดี อาจอง,เธอสบายดีใช่ไหม,ให้ฉันช่วยอะไรไหม,พักนี้อากาศไม่ดี ระวังไม่สบายนะ,หวัดไม่ดีนะ");
							
							npc[4] = new NPC_04(3,308);
							npc[4].LOADMESSAGE("สวัสดี สหาย,หมู่บ้านเราจะปลอดภัย,หลักศิลายังสมบูรณ์,ทหารกล้า !!,สวัสดี อาจอง");
							
							npc[5] = new NPC_05(3,100);
							npc[5].LOADMESSAGE("อรุณทรา จะต้องเจริญ,ใครมีอะไรจะมาบริจาคช่วยหมู่้บ้านไหม,ข้าว่า...นะ,สวัสดีอาจอง,ทำไมคนเยอะจัง,พวกมันทำไรกันอะ");
							
							npc[6] = new NPC_06(3,500);
							npc[6].LOADMESSAGE("????,สู้สิ,ทหารกล้าจงสู้,สู้โว้ย,เจ้าต้องการอะไร ?,อากาศร้อนจริง,น่ารำคาญ,หนวกหูจริง !!");
							
							npc[7] = new NPC_07(1,815);
							npc[7].LOADMESSAGE("เจ้าหญิงค่ะ,<--ทางไปฟาร์มนะ,รับซื้อผักค๊าาา,กด z x c v b n ในฟาร์มเพื่อใช้เมนูนะ,เกมเราจะดังแปปเดียว *-*,หมู่บ้านเราสงบสุขเนาะ,ฉันรักที่นี้,เธอว่าไหมล่ะ,อิอิ");
							
							npc[8] = new NPC_08(1,170);
							npc[8].LOADMESSAGE("ท่าสามารถเข้ามายังแปลงเกษตรได้,กดที่ข้า");
							
							for(NPC n : npc){
								if(n != null){
									n.LOAD();
									n.setContainer(container);
								}
							}
							
							//Begin of solider
							
							for(int i = 0;i<3;i++){
								solider.add(new SOLIDER_ARCHER());
								solider.get(soliderSum).setMap(2,100+(50*soliderSum));
								solider.get(soliderSum).Load();
								soliderSum++;
							}
							for(int i = 0;i<3;i++){
								solider.add(new SOLIDER_SWORD());
								solider.get(soliderSum).setMap(2,100+(50*soliderSum));
								solider.get(soliderSum).Load();
								soliderSum++;
							}
							for(int i = 0;i<0;i++){
								solider.add(new SOLIDER_TWOHANDSWORD());
								solider.get(soliderSum).setMap(2,100+(50*soliderSum));
								solider.get(soliderSum).Load();
								soliderSum++;
							}
							for(int i = 0;i<1;i++){
								solider.add(new SOLIDER_AXE());
								solider.get(soliderSum).setMap(2,100+(50*soliderSum));
								solider.get(soliderSum).Load();
								soliderSum++;
							}
							for(int i = 0;i<0;i++){
								solider.add(new SOLIDER_HERO());
								solider.get(soliderSum).setMap(2,100+(50*soliderSum));
								solider.get(soliderSum).Load();
								soliderSum++;
							}
							
							npcSolider_Arunetara[0] = new NPC_archer(4,666,333);
							npcSolider_Arunetara[0].LOADMESSAGE("วันนี้อากาศดี นายว่าไหม,ฉันจะจัดการพวกมันเอง,เมืองเราจะต้องปลอดภัย,ดินแดนแห่งนี้สงบสุข");
							
							npcEnemy[0] = new NPC_comArcher(7,537,303);
							npcEnemy[0].Load();
							npcEnemy[1] = new NPC_comArcher(7,530,445);
							npcEnemy[1].Load();
							npcEnemy[2] = new NPC_comArcher(7,900,445);
							npcEnemy[2].Load();
							
							musicHelp.play();
							for(NPC_SOLIDER ns : npcSolider_Arunetara){
								if(ns != null){
									ns.Load();
									ns.setContainer(container);
								}
							}
							
							//home
							myHome[0] = new siriGuild(3,383,393);
							myHome[1] = new doorTown(4,705,410);
							
							enHome[0] = new doorTownOfENemy(7,478,466);
							enHome[1] = new Heart(7,743,365);
							//Ect Set
							alpha = 255;
							enemy = new ArrayList<ENEMY>();
							
							//Hero Set
							HERO.warpTo((int)(Math.random()*1024),445);

							scrollStory = new ScrollStory();
							scrollStory.setRect(210,123,630,500);
							
							for(int i = 0;i<10;i++){
								scrollStory.addString("");
							}
							
							scrollStory.addString("บทนำ");
							scrollStory.addString("ในปัจจุบันคอมพิวเตอร์ได้เข้ามามีบทบาทในชีวิตประจำวันของมนุษย์เป็นอย่างมาก ซึ่ง");
							scrollStory.addString("คอมพิวเตอร์สามารถช่วยอำนวยความสะดวกต่างๆให้กับมนุษย์ การแก้ปัญหา แม้กระทั่งเพื่อความ");
							scrollStory.addString("บันเทิง ซึ่งเกมคอมพิวเตอร์ได้มามีบทบาทอย่างมากต่อเยาวชนไทย และในปัจจุบันเกมคอมพิวเตอร์ได้");
							scrollStory.addString("ถูกพัฒนามาหลายแบบ ทั้งเกมที่ถูกพัฒนาเพื่อความเพลิดเพลิน ความบันเทิง ฯลฯ ซึ่งมีผลต่อสภาพ");
							scrollStory.addString("อารมณ์ และ พฤติกรรม ของเยาวชนไทย ผู้พัฒนาจึงได้คำนึงถึงผลกระทบ จากโครงการเป็นสำคัญ จึง");
							scrollStory.addString("ได้คิดพัฒนาเกมที่ทำให้ผู้เล่นได้ทั้งความบันเทิง ความเพลิดเพลิน และ ยังช่วยเสริมสร้าง ความคิด");
							scrollStory.addString("สร้างสรรค์ และ ทักษะการคิดให้กับเยาวชนไทย เพื่อที่จะได้นำไปสร้างประโยชน์ต่อประเทศชาติต่อไป\n\n");
							
							scrollStory.addString("วัตถุประสงค์");
							scrollStory.addString("-	เพื่อพัฒนาเกม 2 มิติสำหรับการเสริมสร้างทักษะการคิด การวางแผนให้กับเยาวชนไทย");
							scrollStory.addString("-	เพื่อนำเสนอเกมที่พัฒนาจากซอฟต์แวร์ถูกกฎหมาย Open Source\n\n");
							scrollStory.addString("เป้าหมายของเกม");
							scrollStory.addString("เป้าหมายของเกมคือ การป้องกันหลักศิลาประจำบ้านเมืองของอาจองและ ต้องบุกโจมตีข้าศึกเพื่อ");
							scrollStory.addString("ทำลายธงค่ายและสังหารแม่ทัพอันเป็นผู้บัญชาการของข้าศึกให้ได้ โดยต้องอาศัยความแข็งแกร่งและ");
							scrollStory.addString("ทักษะในการรุกและรับเป็นอย่างมากหากอ่อนแออาจทำให้ข้าศึกฝ่าแนวรับของเราและทำลายหลักศิลา");
							scrollStory.addString("ประจำบ้านเมืองของเราได้สำเร็จ จำทำให้ผู้เล่นเกมแพ้ในเกมครั้งนั้น");
							scrollStory.addString("โดยในเกมจะเกิดสงครามทุกๆวันที่เป็นเลขคี่หรือ 2 วันโดยจะแบ่งให้ผู้เล่นได้ใช้เวลาพัฒนาเมือง");
							scrollStory.addString("เป็นเวลาหนึ่งวันและ สงครามที่เกิดขึ้นจะไม่สามารถคาดการณ์ระยะเวลาที่จะเกิดและจะจบลงได้สงคราม");
							scrollStory.addString("ที่เกิดขึ้นในแต่ละครั้งจะมีการพัฒนาการของเหล่าข้าศึกที่แข็งแกร่งขึ้นและมีจำนวนเพิ่มมากขึ้นด้วยหาก");
							scrollStory.addString("ไม่สามารถเอาชนะข้าศึกในเวลา 30 วันได้ข้าศึกจะพัฒนาตัวเองไปถึงระดับที่ยากที่สุดและจะทำให้ผู้เล่น");
							scrollStory.addString("เอาชนะได้ยากขึ้นและจะเป็นฝ่ายผ่ายแพ้ในที่สุด");
							scrollStory.addString("เกมอาจอง เป็นเกมที่เสริมสร้างกระบวนการทักษะการคิดให้กับเยาวชนไทยส่งเสริมให้เยาวชน");
							scrollStory.addString("ผู้บริโภคที่ชื่นชอบในการเล่นเกมหันมาบริโภคเกมไทยกันมากขึ้น ทั้งนี้ผู้เล่นจะได้ทักษะการควบคุมเมาส์ ");
							scrollStory.addString("และ คีย์บอร์ดควบคู่กับการเล่นเกมด้วย\n\n");
							
							scrollStory.addString("เนื้อเรื่อง");
							scrollStory.addString("อาจอง มหาสงครามศึกแผ่นดินผู้กล้า เป็นเกม 2D มุมมองจากบุคคลที่ 3 และเป็นเกมที่ \nผสมผสานหลากหลายแนวเกมเข้าไว้ด้วยกันโดยผู้เล่นจะได้เข้าไปมีบทบาทในช่วงสงครามในยุคฟื้นฟู");
							scrollStory.addString("ทางการอาชีพและการเกษตรต่างๆ ผ่านทางตัวละครเพศชายอายุ 16 ปีชื่อ อาจอง ซึ่งเป็นเด็กหนุ่มที่ \nมีพลังอำนาจแห่งสัตว์โบราณแอบแฝงอยู่โดยที่เขาไม่สามารถควบคุมมันได้ แต่ทว่าพลังอำนาจนี้จะคง");
							scrollStory.addString("ฤทธิ์ อยู่ได้หากแผ่นดินยังไม่แตกสลายเท่านั้น  กิจกรรมต่างๆ ที่เขาต้องทำในแต่ละวัน คือการทำงาน \n อาชีพการเกษตรเพื่อฝึกฝนทักษะการเกษตร และ เพื่อช่วยเพิ่มเศรษฐกิจของเมืองให้ดีขั้นโดยใน");
							scrollStory.addString("กิจกรรมจะได้รับแต้มเพื่อนำไปฝึกทักษะอาชีพต่างๆได้ และ กิจกรรมต่างๆของเขายังไม่หมดเพียง \n เท่านั้นเขาต้องออกไปผจญภัยในป่าเพื่อฝึกฝนประสบการณ์ในการต่อสู้เพื่อควบคุมพลังที่มีอยู่ให้ได้ ");
							scrollStory.addString("เพื่อนำกลับมาปกป้องบ้านเมือง และ หาหนทางในการ ลับมือกัน ข้าศึกที่บุกเข้ามาให้ได้ ");
							
							scrollStory.addString("\n\n\n");
							
							scrollTxt[4] = new ScrollTxt();
							scrollTxt[4].setRect(8,25,270,100);
							
							alpha = 255;
							GAME=GAMESTATE.story;
						}
					}
					if(BUTTON(button[0],"ยกเลิก",container,770,610+60)){GAME = GAMESTATE.title;alpha = 255;};
					
					int dialog_X = (scrWidth-menuDialog[1].getWidth())/2;
					String txter = "อาวุธ : "+HERO.JOB.getWeapon();
					g.drawImage(menuDialog[0],scrWidth-menuDialog[0].getWidth(),220);
					g.drawImage(menuDialog[1],dialog_X,610);
					DrawingText.DrawText(txter,
						dialog_X+((menuDialog[1].getWidth())/2)-font[1].getWidth(txter)/2,
						610+5,font[1]);
					
					DrawingText.DrawText("ชื่อ : "+HERO.NAME,745,243,font[1]);
					DrawingText.DrawText("อาชีพ : "+HERO.WORKER.getName(),881,243,font[1]);
					DrawingText.DrawText("ตำแหน่ง และ หน้าที่ : "+HERO.JOB.getName(),745,243+20,font[1]);
					DrawingText.DrawText("พลังชีวิต : "+HERO.HP+" / "+HERO.HPMAX,745,243+20*2,font[1]);
					DrawingText.DrawText("พลังจิตร : "+HERO.SP+" / "+HERO.SPMAX,745,243+20*3,font[1]);
					DrawingText.DrawText("พลังกาย : "+HERO.PW+" / "+HERO.PWMAX,881,243+20*2,font[1]);
					DrawingText.DrawText("น้ำ : "+HERO.WP+" / "+HERO.WPMAX,881,243+20*3,font[1]);
					
					scrollTxt[3].msgclr();
					scrollTxt[3].subject("Status");
					scrollTxt[3].addString("Con : "+HERO.CON);
					scrollTxt[3].addString("Int : "+HERO.INT);
					scrollTxt[3].addString("Abi : "+HERO.ABI);
					scrollTxt[3].addString("Str : "+HERO.STR);
					scrollTxt[3].addString("Dex : "+HERO.DEX);
					scrollTxt[3].addString("Agi : "+HERO.AGI);
					scrollTxt[3].addString("Mem : "+HERO.MEM);
					scrollTxt[3].addString("Weapont Attack : "+HERO.ATK+" ~ "+HERO.ATKMAX);
					scrollTxt[3].addString("Magic Attack : "+HERO.MATK+" ~ "+HERO.MATKMAX);
					scrollTxt[3].addString("Aspd : "+HERO.resetSpeed());
					scrollTxt[3].addString("Def : "+HERO.DEF);
					scrollTxt[3].addString("Inc : "+HERO.MAXINCONTROL);
					
					scrollTxt[3].render(container,mouseHIT(container));	
					
					if(Button2(but1[0],but1[1],432-but1[0].getWidth(),585,mouseHIT(container),container.getInput())){
						if(j < 1)j = 3;
						j--;
						hitOK=true;
					}
					if(Button2(but2[0],but2[1],588,585,mouseHIT(container),container.getInput())){
						if(j > 1)j = -1;
						j++;
						hitOK=true;
					}
					
					DrawingText.DrawText(ver,scrWidth-font[1].getWidth(ver)-20,scrHeight-font[1].getHeight(ver)*2-20,font[1]);
					
					break;
				}
				case story:{
					g.drawImage(story2,0,0);
					flowerAura.render();
					g.drawImage(back,0,0);
					String ver = "เวอร์ชั่น  "+this.version;
					
					scrollStory.render(container,mouseHIT(container));
					scrollStory.updateSelf();
					
					if(BUTTON(button[0],"skip",container,770,610+60)) scrollStory.setEnd(true);
					
					if(scrollStory.getEnd()){
						music[0].stop();
						musicHelp.stop();
						music[1].play();
						music[1].loop();
						sound[2].play();
						sound[2].loop();
						
						title.destroy();
						blood=null;
						logo[0].destroy();
						logo[1].destroy();
						logo[2].destroy();
						
						alpha = 255;
						GAME=GAMESTATE.game;
					}
					
					break;
				}
				case game:{
					renderMap();
					Arrays.sort(myHome,new AStar());
					Arrays.sort(enemy.toArray(),new SortTarget());
					slow.update();
					((NPC_06)npc[6]).setTarget(solider);
					((NPC_05)npc[5]).setTarget(solider,this);
					((NPC_08)npc[8]).setTar(this);
					
					
					if(HERO.TIMER.endWar){
						if(HERO.getFirstMap){
							this.getMusic(1).stop();
							this.getSound(2).stop();
							this.getMusic(4).play();
							this.getMusic(4).loop();
							this.getScrollTxt(2).clr();
							this.getScrollTxt(2).setRect(680,620,344,140);
							this.goToFarm();
							this.realpha();
							
							
							HERO.getFirstMap=false;
						}
					}
					
					if(HERO.TIMER.endWar){
						if(!twoSound){
							music[3].stop();
							
							music[1].play();
							music[1].loop();
							modeIntDescript=2;
							descript=true;
							twoSound=!twoSound;
						}
					}else{
						g.drawImage(imgwarring,(scrWidth-imgwarring.getWidth())/2,50);
					}
					
					for(int i = 0;i<npc.length;i++){
						if(npc[i] != null){
							npc[i].drawSelf();
							npc[i].Update(HERO.mapX,scrollTxt[2]);
							npc[i].setCursorSelect(mouseHIT(container));
							npc[i].onClick();
							npc[i].setTarget(HERO);
						}
						/*
						
						*/
					}
					
					for(NPC_SOLIDER ns : npcSolider_Arunetara){
						if(ns != null){
							ns.drawSelf();
							ns.Update(HERO.mapX,scrollTxt[2]);
							ns.setCursorSelect(mouseHIT(container));
							ns.UpdateSelf(myHome,HERO);
							ns.onClick();
						}
					}
					
					for(NPC_ENEMY nE : npcEnemy){
						if(nE != null){
							nE.drawSelf();
							nE.Update(HERO.mapX);
							nE.UpdateSelf(solider,HERO, HERO.mSolider);
							nE.Updateing();
						}
					}
					
					for(HOME e : myHome){
						if(e != null){
							e.render();
							e.renderEffect();
							if((e.name == "หลักศิลา") && (e.die())){
								if(gameSelf == 'g'){
									sound[5].play();
									music[0].stop();
									music[1].stop();
									sound[2].stop();
									scrollTxt[2].addString("คุณเสียดินแดนแล้ว");
									oneSound = true;
									gameSelf = 'l';
									break;
								}
								break;
							}
						}
					}
					
					for(HOME e : enHome){
						if(e != null){
							e.render();
							e.Update(HERO.mapX);
							e.renderEffect();
							if((e.name == "ธงศัตรู") && (e.die())){
								if(gameSelf == 'g'){
									sound[5].play();
									music[0].stop();
									music[1].stop();
									sound[2].stop();
									scrollTxt[2].addString("คุณได้รับชัยชนะ");
									oneSound = true;
									gameSelf = 'w';
									break;
								}
								break;
							}
						}
					}
					
					for(SOLIDER sol : solider){
						if(sol != null){
							if(sol.isDie()){
								soliderSum--;
								solider.remove(sol);
								break;
							}else{
								sol.DrawSelf();
							}
						}
					}
					
					for(MYSOLIDER mSol : HERO.mSolider){
						if(mSol != null){
							if(!mSol.getDie()){
								mSol.DrawSelf();
								mSol.MasterTarget(HERO);
								mSol.Update(HERO.mapX,enemy,enHome);
							}else{
								HERO.mSolider.remove(mSol);	
								break;
							}
						}
					}
					
					HERO.drawTong();
					
					for(ENEMY e : enemy){
						if(e != null && !e.isDie()){
							try{
								tl.setPos(e._x,e.y);
								HERO.setScope(tl.box());
								break;
							}catch(NullPointerException er){
								tl = new TargetLog();
								break;
							}
						}
					}
					
					if(enemy.size() == 0){
						tl = new TargetLog();
						tl.setPos(-100,-100);
						HERO.setScope(new Rectangle(0,0,0,0));
					}
					
					updateing(container);
					HERO.Updating(enemy);
					HERO.DrawSelf();
					if(gameSelf == 'g') HERO.Update();
					HERO.UpdateStatus();
					HERO.Control(container,enemy,enHome);
					Evolution = EEW.valueOf("war_"+(HERO.TIMER.wTotal));

					if(HERO.JOB == JOBS.Archer) ((Archer)HERO).arrowAddTarget(enemy,enHome);
					
					for(ENEMY e : enemy){
						if(e.getDie()){
							enemySum--;
							enemy.remove(e);
							tl = null;
							break;
						}else{
							e.drawSelf();
						}
					}
					
					this.warmgr(true);
					
					HERO._MAP.rendingObjectMap(HERO.TIMER.getColor());
					HERO.TIMER.timerUpdate();
					
					for(HOME e : enHome){
						if(e != null){
							if(e.mapIndex == HERO.MAPINDEX){
								e.render2();
							}
						}
					}
					
					for(HOME e : myHome){
						if(e != null){
							if(e.mapIndex == HERO.MAPINDEX){
								e.render2();
							}
						}
					}
					
					for(NPC_SOLIDER ns : npcSolider_Arunetara){
						if(ns != null){
							ns.UpdateSelf(enemy,HERO);
						}
					}
					
					if(HERO.TIMER.isWar){
						if(HERO.TIMER.fade > 0){
							HERO.TIMER.fade--;
							g.drawImage(HERO.TIMER.getWarLogo(),scrWidth/2-(HERO.TIMER.getWarLogo().getWidth())/2,scrHeight/2-(HERO.TIMER.getWarLogo().getHeight()/2));
						}else{
							if(HERO.TIMER.fadeL > 0){
								HERO.TIMER.fadeL--;
								g.drawImage(HERO.TIMER.getWarLogo(),scrWidth/2-(HERO.TIMER.getWarLogo().getWidth())/2,scrHeight/2-(HERO.TIMER.getWarLogo().getHeight()/2),new Color(255,255,255,HERO.TIMER.fadeL));
							}else{
								if(HERO.TIMER.fadeAlpha > 0){
									HERO.TIMER.fadeAlpha--;
								}else{
									music[1].stop();
									music[3].play();
									music[3].loop();
									HERO.TIMER.fadeAlpha = 50;
									HERO.TIMER.fade = 500;
									HERO.TIMER.fadeL = 255;
									HERO.TIMER.isWar = false;
									
									modeIntDescript=1;
									descript = true;
									twoSound=!twoSound;
								}
							}
						}
					}
					
					for(ENEMY enemyer : enemy){
						if(gameSelf == 'g'){
							enemyer.setShowAI(showAI);
							enemyer.UPDATE(HERO.mapX);
							
						}
					}
					
					for(SOLIDER sol : solider){
						if(gameSelf == 'g'){
							if(sol != null){
								sol.updateing();
							}
						}
					}
					
					for(MYSOLIDER mSol : HERO.mSolider){
						if(mSol != null){
							if(!mSol.getDie()){
								mSol.updateing();
							}
						}
					}
					
					if(HERO.warp) this.alpha = HERO.getAlpha();
					if(menuOut(container))menugame[1]=true;
					
					for(HOME e : enHome){
						if(e != null){
							e.Updating();
						}
					}
					
					if(gameSelf == 'l' || gameSelf == 'w'){
						g.setColor(new Color(0,0,0,180));
						g.fillRect(0,0,scrWidth,scrHeight);
						
						if(delayEnd > 0){
							delayEnd--;
							
							if(gameSelf == 'l')
								g.drawImage(loseLogo,(scrWidth-loseLogo.getWidth())/2,(scrHeight-loseLogo.getHeight())/2);
							if(gameSelf == 'w')
								g.drawImage(winLogo,(scrWidth-winLogo.getWidth())/2,(scrHeight-winLogo.getHeight())/2);
						}else{	
							alpha = 255;
							music[2].play();
							music[2].loop();
							GAME=GAMESTATE.over;
						}
					}
					
					for(NPC n : npc){
						if(n != null)
							n.onData();
					}	
					
					if(helpgame){
						g.setColor(new Color(50,50,50,150));
						g.fillRect(0,0,1024,768);
					}
					
					isMenu(container);
					
					if(descript){
						interfaceDescript(container,modeIntDescript);
					}
					
					if(container.getInput().isKeyPressed(container.getInput().KEY_TAB)){
						helpgame=!helpgame;
					}
					
					if(helpgame){
						g.drawImage(new Image(FileList.imgeDir+"helpgame.PNG"),0,0);
					}
					HERO.Updating(enemy,enHome);
					break;
				}
				case over:{
					OverRender(container);
					break;
				}
				case win:{
					break;
				}
				case farm:{
					FarmRender(container);
					break;
				}
				case LoadOnStage:{
					break;
				}
				case SaveOnStage:{
					String[] db;
					db = new String[]{
						"Hour : "+HERO.TIMER.hour+"",
						"TotalWar : "+HERO.TIMER.totalWar+"",
						"Delay : "+HERO.TIMER.delay+"",
						"CallWar : "+HERO.TIMER.callWar+"",
						"Week : "+HERO.TIMER.week+"",
						"Day : "+HERO.TIMER.day+"",
						"Hp : "+HERO.HP+"",
						"Hp max : "+HERO.HPMAX+"",
						"Sp : "+HERO.SP+"",
						"Sp max : "+HERO.SPMAX+"",
						"Pw : "+HERO.PW+"",
						"Pw max: "+HERO.PWMAX+"",
						"Wp: "+HERO.WP+"",
						"Wp max "+HERO.WPMAX+"",
						"Plants : "+HERO.plant_am+"",
						"Gold : "+HERO.gold_am+"",
						"Con : "+HERO.CON+"",
						"Int : "+HERO.INT+"",
						"Abi : "+HERO.ABI+"",
						"Str : "+HERO.STR+"",
						"Dex : "+HERO.DEX+"",
						"Agi : "+HERO.AGI+"",
						"Mem : "+HERO.MEM+"",
						"Def : "+HERO.DEF+"",
					};
					frameSave(container,db);
					DrawingText.DrawText("ชื่อที่ต้องการบันทึก",362,522);
					break;
				}
				case exit:{
					g.drawImage(new Image(FileList.imgeDir+"watting.PNG"),0,30);
					g.drawImage(back,0,0);
					if(menugame[2]){
						char DailogMenu = dialog(icon[5],"ท่านต้องการออกจากเกมหรือไม่ !",container,(scrWidth-menu[3].getWidth())/2,(scrHeight-menu[3].getHeight())/2);
					if(DailogMenu == 'a'){
							container.exit();
						}else if(DailogMenu == 'b'){
							menugame[2]=false;
							alpha = 255;
							GAME = GAMESTATE.title;
						}
					}
					break;
				}
			}
			cutFrame();
			
			if(!farmMode)
				mouseControl(mouse,container);
			
			if(farmMode && toolFarm == ToolFarm.Nullmode)
				mouseControl(cursorFarm[0],container);
			
			if(farmMode && toolFarm == ToolFarm.Digmode)
				mouseControl(cursorFarm[1],container);
			
			if(farmMode && toolFarm == ToolFarm.CropDmode)
				mouseControl(cursorFarm[2],container);
			
			if(farmMode && toolFarm == ToolFarm.CropCmode)
				mouseControl(cursorFarm[2],container);
				
			if(farmMode && toolFarm == ToolFarm.CropBmode)
				mouseControl(cursorFarm[2],container);
				
			if(farmMode && toolFarm == ToolFarm.CropAmode)
				mouseControl(cursorFarm[2],container);

			if(farmMode && toolFarm == ToolFarm.Watermode)
				mouseControl(cursorFarm[3],container);	
			
			if(farmMode && toolFarm == ToolFarm.Lifemode)
				mouseControl(cursorFarm[4],container);	
				
			g.destroy();
			g = null;
		}
		catch(Exception e){
			/*
			String errorReport = e.toString();
			int scrWidth = 300,scrHeight = 300;
			
			javax.swing.JFrame frame = new javax.swing.JFrame("Error Report !!");
			java.awt.Toolkit toolkit = frame.getToolkit();
			java.awt.Dimension size = toolkit.getScreenSize();
			
			frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
			frame.setSize(scrWidth,scrHeight);
			frame.setLocation((size.width-scrWidth)/2,(size.height-scrHeight)/2);
			javax.swing.JPanel panel = new javax.swing.JPanel();
			frame.getContentPane().add(panel);
			panel.setLayout(null);
			
			javax.swing.JButton butt = new javax.swing.JButton("Close");
			butt.setBounds((scrWidth-150)/2,scrHeight-100,130,60);
			
			butt.addActionListener(new java.awt.event.ActionListener(){
					public void actionPerformed(java.awt.event.ActionEvent e){
						System.exit(0);
					}
			});
			
			javax.swing.JTextPane pane = new javax.swing.JTextPane();
			pane.setBounds(0,10,scrWidth,scrHeight/2+10);
			pane.setContentType("text/html");
			String msg = "<p><b>Render Exception</p></b>"+
						"<p><b>Render error is :</b> "+errorReport+"</p>"+
						"<r><p>send report to <b><u>http://www.mindtech.co.cc</u></b></p></r>";
		
			pane.setText(msg);
			pane.setEditable(false);
			panel.add(pane);
			panel.add(butt);
			frame.add(panel);
			frame.setResizable(false);
			frame.setVisible(true);
			
			return;
			*/
		}
	}
	
	public void frameSave(GameContainer container,String[] db) throws SlickException {
		g.drawImage(new Image(FileList.imgeDir+"LoadSplash.png"),0,0);
		g.drawImage(back,0,0);
		inputSave.render(container,new Graphics());
		
		if(BUTTON(button[0],"บันทึก",container,(scrWidth-button[0].getWidth())/2,580)){
			new File("load/").mkdirs();
			try{
				String url = "load/";
				String fName = inputSave.getText();
				new File(url+fName+"/").mkdirs();
				
				File[] f = new File[]{
					new File(url+fName+"/"+fName+".$main"),
					new File(url+fName+"/"+"eny_"+fName+".dat"),
					new File(url+fName+"/"+"sol_"+fName+".dat"),
					new File(url+fName+"/"+"eny_h_"+fName+".dat"),
					new File(url+fName+"/"+"sol_h_"+fName+".dat"),
					new File(url+fName+"/"+"frm_01_"+fName+".dat"),
					new File(url+fName+"/"+"frm_02_"+fName+".dat"),
				};
				int size = f.length;
				FileOutputStream[] fis = new FileOutputStream[size];
				ObjectOutputStream[] oos = new ObjectOutputStream[size];
				Object[] obj = new Object[]{
					HERO,enemy,solider,enHome,myHome,map,mapFarm
				};
				
				for(int i = 0;i<size;i++){
					fis[i] = new FileOutputStream(f[i]);
					oos[i] = new ObjectOutputStream(fis[i]);
					
					oos[i].writeObject(obj[i]);
					oos[i].close();
				}
				
				
				music[5].stop();
				music[1].play();
				music[1].loop();
				sound[2].play();
				sound[2].loop();
				alpha = 255;
				GAME=GAMESTATE.game;
			}catch(Exception e){
				javax.swing.JOptionPane.showMessageDialog(null,e.toString(),"Message",
				javax.swing.JOptionPane.ERROR_MESSAGE);
			}
		}
		if(BUTTON(button[0],"กลับสู่หน้าหลัก",container,(scrWidth-button[0].getWidth())/2,580+50)){
			music[5].stop();
			music[1].play();
			music[1].loop();
			sound[2].play();
			sound[2].loop();
			alpha = 255;
			GAME=GAMESTATE.game;
		}
	}
	
	public void LoadSystem(){
		try{
			String path = "load/";
			File myFile = new File(path);
			String mFile[] = myFile.list();
			
			for(int i = 0;i<mFile.length;i++){
				scrollList.addList(null,mFile[i]);
			}
		}catch(Exception e){
			scrollList.addList(null,"ไม่พบแฟ้ม load");
		}
	}
	
	private void updateing(GameContainer container) throws SlickException{
		if(enemySum <= 0){
			HERO.TIMER.endWar = true;		
			for(SOLIDER sol : solider){
				if(sol != null){
						sol.warring = false;
				}
			}
		}else{
			HERO.TIMER.endWar = false;
			for(SOLIDER sol : solider){
				if(sol != null){
					sol.warring = true;
				}
			}
		}
		
		for(ENEMY enemyer : enemy){
			if(enemyer.getDie()){
				enemySum--;
				enemy.remove(enemyer);
				break;
			}else{
				if(gameSelf == 'g')enemyer.setTarget(HERO,myHome,solider, HERO.mSolider,scrollTxt[2]);
			}
		}
		
		for(SOLIDER sol : solider){
			if(sol != null){
				if(!sol.isDie()){
					if(gameSelf == 'g')	sol.Update(HERO.mapX,enemy,enHome);
				}else{
					soliderSum--;
					solider.remove(sol);
					break;
				}
			}
		}
		
		for(HOME e : myHome){
			if(e != null){
				e.Update(HERO.mapX);
				if((e.name == "หลักศิลา") && (e.die())){
					if(gameSelf == 'g'){
						sound[5].play();
						music[0].stop();
						music[1].stop();
						sound[2].stop();
						scrollTxt[2].addString("คุณเสียดินแดนแล้ว");
						oneSound = true;
						gameSelf = 'l';
						break;
					}
					break;
				}
			}
		}
	}
	
	private void FarmRender(GameContainer container) throws SlickException {
		farmMode=true;
		mapFarm.render(toolFarm,HERO);
		Graphics g = new Graphics();
		HERO.TIMER.timerUpdate();
		
		for(int i = 0;i<could.length;i++){
			could[i].UpdateAndRender();
		}
		
		this.warmgr(false);
		HERO.UpdateStatus();
		
		if(HERO.TIMER.war){
			alpha = 255;
			scrollTxt[2] = new ScrollTxt();
			scrollTxt[2].setRect(490,628,326,130);
			music[4].stop();
			farmMode = false;
			GAME = GAMESTATE.game;
		}
		
		
		g.setFont(font[1]);
		g.drawImage(edge,0,0);
		
		Input inp = container.getInput();
		
		if(inp.isKeyPressed(inp.KEY_TAB)){
			toolFarm = ToolFarm.Nullmode;
		}
		
		if(BUTTON(bBack,"",container,900,120) || inp.isKeyPressed(inp.KEY_ESCAPE) || HERO.WP <= 0){
			alpha = 255;
			scrollTxt[2] = new ScrollTxt();
			scrollTxt[2].setRect(490,628,326,130);
			music[4].stop();
			sound[4].play();
			sound[2].play();
			sound[2].loop();
			music[1].play();
			music[1].loop();
			farmMode = false;
			GAME = GAMESTATE.game;
		}
		drawIB(container,10,10);
		/*
		if(mapFarm.getMouseOnTile() == 'h'){
			tFarm = digFarm;
		}else tFarm = NullFarm;
		*/
		
		scrollTxt[2].render(container,mouseHIT(container));
		scrollTxt[2].subject("วันที่ "+HERO.TIMER.getDay()+" สัปดาห์ที่ "+HERO.TIMER.getWeek());
		
		
		farminterface(container);
	}

	private void farminterface(GameContainer container) throws SlickException {
		Graphics g = new Graphics();
		g.drawImage(frameInterface,0,scrHeight-frameInterface.getHeight());
		DrawingText.DrawText("อาชีพ. "+HERO.WORKER.getName(),28,620,font[1]);
		DrawingText.DrawText("ตำแหน่ง. "+HERO.JOB.getName(),28,643,font[1]);
		
		this.drawBar(HERO.HP,HERO.HPMAX,19,664,107,14,Color.green);
		this.drawBar(HERO.SP,HERO.SPMAX,19,683,107,14,Color.blue);
		this.drawBar(HERO.PW,HERO.PWMAX,19,701,107,14,Color.orange);
		this.drawBar(HERO.WP,HERO.WPMAX,19,719,107,14,new Color(0,200,200));
		
		DrawingText.DrawText("HP. "+HERO.HP+" / "+HERO.HPMAX,28,664,font[1]);
		DrawingText.DrawText("MP. "+HERO.SP+" / "+HERO.SPMAX,28,683,font[1]);
		DrawingText.DrawText("PW. "+HERO.PW+" / "+HERO.PWMAX,28,701,font[1]);
		DrawingText.DrawText("WP. "+HERO.WP+" / "+HERO.WPMAX,28,719,font[1]);
		
		DrawingText.DrawText("Watk : "+HERO.ATK+" ~ "+HERO.ATKMAX,143,629,font[1]);
		DrawingText.DrawText("Matk : "+HERO.MATK+" ~ "+HERO.MATKMAX,143,643,font[1]);
		DrawingText.DrawText("Aspd : "+HERO.resetSpeed(),143,656,font[1]);
		DrawingText.DrawText("Def : "+HERO.DEF,143,669,font[1]);
		DrawingText.DrawText("Inc : "+HERO.MAXINCONTROL,143,681,font[1]);
		
		DrawingText.DrawText("Con : "+HERO.CON,300,629,font[1]);
		DrawingText.DrawText("INT : "+HERO.INT,300,643,font[1]);
		DrawingText.DrawText("ABI : "+HERO.ABI,300,656,font[1]);
		DrawingText.DrawText("STR : "+HERO.STR,300,669,font[1]);
		DrawingText.DrawText("DEX : "+HERO.DEX,300,681,font[1]);
		DrawingText.DrawText("AGI : "+HERO.AGI,300,694,font[1]);
		DrawingText.DrawText("MEM : "+HERO.MEM,300,707,font[1]);
		
		this.drawBar(HERO.MinExpSkill,HERO.MaxExpSkill,17,741,367,8,new Color(150,150,150));
		this.drawBar(HERO.MinExpJob,HERO.MaxExpJob,17,755,367,8,new Color(150,150,150));
		DrawingText.DrawText("Skill Point. "+HERO.skillLv,17,741,font[1]);
		DrawingText.DrawText("Job Point. "+HERO.jobLv,17,755,font[1]);
		
		if(HOTKEY(bm[2],icon[10],HERO.waterItem+"",(HERO.hpItem > 0),container,4,578,container.getInput().KEY_A,'A',"น้ำดื่มลดความกระหาย")){
			if(HERO.waterItem > 0 && HERO.WP < HERO.WPMAX-5){
				if(HERO.HP > 0){
					HERO.WP+=15;
					HERO.waterItem--;
				}
			}
		}
		
		if(HOTKEY(bm[2],icon[11],HERO.hpItem+"",(HERO.hpItem > 0),container,4+40,578,container.getInput().KEY_S,'S',"ข้าวห่อใบตอง")){
			if(HERO.hpItem > 0 && HERO.HP < HERO.HPMAX-5){
				if(HERO.HP > 0){
					HERO.HP+=20;
					HERO.hpItem--;
				}
			}
		}
		
		if(HOTKEY(bm[2],icon[12],HERO.mpItem+"",(HERO.hpItem > 0),container,4+40*2,578,container.getInput().KEY_D,'D',"ใบไม้เวทมนต์")){
			if(HERO.mpItem > 0 && HERO.SP < HERO.SPMAX-5){
				if(HERO.SP > 0){
					HERO.SP+=30;
					HERO.mpItem--;
				}
			}
		}
		
		if(HOTKEY(bm[2],icon[13],HERO.pwItem+"",(HERO.hpItem > 0),container,4+40*3,578,container.getInput().KEY_F,'F',"ขาหมู่ย่าง")){
			if(HERO.pwItem > 0 && HERO.PW < HERO.PWMAX-5){
				if(HERO.PW > 0){
					HERO.PW+=20;
					HERO.pwItem--;
				}
			}
		}
		
		this.showMenuTopInterface(container);
	}
	
	private void OverRender(GameContainer container){
		g.drawImage(storyImg,0,0);
		flowerAura.render();
		g.drawImage(back,0,0);
		
		if(BUTTON(button[0],"ปิด",container,(scrWidth-button[0].getWidth())/2,670))
			container.exit();
		
		String ver = "เวอร์ชั่น  "+this.version;
		String name = "http://www.mindtech.co.cc";
		DrawingText.DrawText(ver,scrWidth-font[1].getWidth(ver),scrHeight-font[1].getHeight(ver)*2,font[1]);
		DrawingText.DrawText(name,scrWidth-font[1].getWidth(name),scrHeight-font[1].getHeight(name),font[1]);
	}
	
	private void warmgr(boolean ok) throws SlickException {
		if(HERO.TIMER.warMangement(ok)){
			if(!loadEvo){
				scrollTxt[2].addString("สงครามได้เริ่มขึ้นแล้ว จงลุกขึ้นสู้เหล่าทหารกล้า");
				scrollTxt[2].nextLine();
				loadEvo = true;
			}
		}
		if(loadEvo){
			if(HERO.TIMER.war){
				if(HERO.TIMER.delay > 0){
					HERO.TIMER.delay--;
				}else{
					if(ok)Evolution.LOAD();
					if(HERO.TIMER.totalWar < HERO.TIMER.totalWarLength()){
					
						for(int n = 0;n<Evolution.hero;n++){
							enemy.add(enemySum,new ENEMY_HERO());
							enemy.get(enemySum).LOAD();
							enemy.get(enemySum).setMap(7,(int)(1024+(80*enemySum)));
							enemySum++;
						} 
					
						for(int n = 0;n<Evolution.sword;n++){
							enemy.add(enemySum,new ENEMY_SWORD());
							enemy.get(enemySum).LOAD();
							enemy.get(enemySum).setMap(7,(int)(1024+(80*enemySum)));
							enemySum++;
						}
						
						for(int n = 0;n<Evolution.spear;n++){
							enemy.add(enemySum,new ENEMY_SPEAR());
							enemy.get(enemySum).LOAD();
							enemy.get(enemySum).setMap(7,(int)(1024+(80*enemySum)));
							enemySum++;
						}
						
						for(int n = 0;n<Evolution.superr;n++){
							enemy.add(enemySum,new ENEMY_SUPER());
							enemy.get(enemySum).LOAD();
							enemy.get(enemySum).setMap(7,(int)(1024+(80*enemySum)));
							enemySum++;
						}	
						
						for(int n = 0;n<Evolution.archer;n++){
							enemy.add(enemySum,new ENEMY_ARCHER());
							enemy.get(enemySum).LOAD();
							enemy.get(enemySum).setMap(7,(int)(1024+(80*enemySum)));
							enemySum++;
						}
						
						for(int n = 0;n<Evolution.gunner;n++){
							enemy.add(enemySum,new ENEMY_GUNNER());
							enemy.get(enemySum).LOAD();
							enemy.get(enemySum).setMap(7,(int)(1024+(80*enemySum)));
							enemySum++;
						}	
						
						HERO.TIMER.delay = HERO.TIMER.war_redelay();
						HERO.TIMER.totalWar++;
					}else{
						loadEvo = false;
						HERO.TIMER.war = false;
					}
				}
			}
		}
	}
	
	private void reMenu(){
		for(int i = 0;i<menugame.length;i++) menugame[i] = false;
	}
	
	private void renderMap() throws SlickException{
		forMode(HERO.TIMER.getWeatherID());
		
		for(int i = 1;i<HERO._MAP.getSize()+1;i++){
			int mapX = -HERO._MAP.getWidth()+i*HERO._MAP.getMap().getWidth()+HERO.mapX;
			if(mapX >= 0 && mapX <= 1024){
				g.drawImage(HERO._MAP.valueOf("Map_"+i).getMap(),mapX,0,HERO.TIMER.getColor());
			}
		}
		
	}
	
	private void forMode(int id){
		Color c = new Color(255,255,255,clamp(HERO.TIMER.getAlpha(),0,255));
		
		switch(id){
			case 0:{
				g.drawImage(bg[0],0,0);
				g.drawImage(bg[3],0,0,c);
				break;
			}case 1:{
				g.drawImage(bg[1],0,0);
				g.drawImage(bg[0],0,0,c);
				break;
			}case 2:{
				g.drawImage(bg[2],0,0);
				g.drawImage(bg[1],0,0,c);
				break;
			}case 3:{
				g.drawImage(bg[3],0,0);
				g.drawImage(bg[2],0,0,c);
				break;
			}
		}
	}
	
	private void isMenu(GameContainer container) throws SlickException,IOException,NotSerializableException {
		Graphics g = new Graphics();
		g.setFont(font[1]);
		g.drawImage(edge,0,0);
		
		g.drawImage(menu[2],0,scrHeight-menu[2].getHeight());
		DrawingText.DrawText("อาชีพ. "+HERO.WORKER.getName(),28,620,font[1]);
		DrawingText.DrawText("ตำแหน่ง. "+HERO.JOB.getName(),28,643,font[1]);
		
		this.drawBar(HERO.HP,HERO.HPMAX,19,664,107,14,Color.green);
		this.drawBar(HERO.SP,HERO.SPMAX,19,683,107,14,Color.blue);
		this.drawBar(HERO.PW,HERO.PWMAX,19,701,107,14,Color.orange);
		this.drawBar(HERO.WP,HERO.WPMAX,19,719,107,14,new Color(0,200,200));
		
		DrawingText.DrawText("HP. "+HERO.HP+" / "+HERO.HPMAX,28,664,font[1]);
		DrawingText.DrawText("MP. "+HERO.SP+" / "+HERO.SPMAX,28,683,font[1]);
		DrawingText.DrawText("PW. "+HERO.PW+" / "+HERO.PWMAX,28,701,font[1]);
		DrawingText.DrawText("WP. "+HERO.WP+" / "+HERO.WPMAX,28,719,font[1]);
		
		DrawingText.DrawText("Watk : "+HERO.ATK+" ~ "+HERO.ATKMAX,143,629,font[1]);
		DrawingText.DrawText("Matk : "+HERO.MATK+" ~ "+HERO.MATKMAX,143,643,font[1]);
		DrawingText.DrawText("Aspd : "+HERO.resetSpeed(),143,656,font[1]);
		DrawingText.DrawText("Def : "+HERO.DEF,143,669,font[1]);
		DrawingText.DrawText("Inc : "+HERO.MAXINCONTROL,143,681,font[1]);
		
		DrawingText.DrawText("Con : "+HERO.CON,300,629,font[1]);
		DrawingText.DrawText("INT : "+HERO.INT,300,643,font[1]);
		DrawingText.DrawText("ABI : "+HERO.ABI,300,656,font[1]);
		DrawingText.DrawText("STR : "+HERO.STR,300,669,font[1]);
		DrawingText.DrawText("DEX : "+HERO.DEX,300,681,font[1]);
		DrawingText.DrawText("AGI : "+HERO.AGI,300,694,font[1]);
		DrawingText.DrawText("MEM : "+HERO.MEM,300,707,font[1]);
		
		this.drawBar(HERO.MinExpSkill,HERO.MaxExpSkill,17,741,367,8,new Color(150,150,150));
		this.drawBar(HERO.MinExpJob,HERO.MaxExpJob,17,755,367,8,new Color(150,150,150));
		DrawingText.DrawText("Skill Point. "+HERO.skillLv,17,741,font[1]);
		DrawingText.DrawText("Job Point. "+HERO.jobLv,17,755,font[1]);
		
		String time = new String(HERO.TIMER.getHour()+" : "+HERO.TIMER.getMinu());
		scrollTxt[2].render(container,mouseHIT(container));
		
		try{
			scrollTxt[2].subject("วันที่ "+HERO.TIMER.getDay()+" สัปดาห์ที่ "+HERO.TIMER.getWeek()+" เวลา : "+time);
		}catch(Exception e){
		}
		
		BUTTON2(nuller,nuller,HERO.getWeaponImg(),container,392,667,"โจมตี : "+HERO.getWeaponATK()+" Lv : "+HERO.getWeaponLv());
		if(HERO.JOB == JOBS.Archer){
			BUTTON2(nuller,nuller,(Image)(((Archer)HERO).arrow.getIcon()),container,437,667,((Archer)HERO).arrow.getComment()+" Atk : "+((Archer)HERO).arrow.getATK());
			DrawingText.DrawText(((Archer)HERO).allowVar+"",475-font[1].getWidth(((Archer)HERO).allowVar+""),695,font[1]);
		}else{
		}
		
		if(HOTKEY(bm[2],icon[10],HERO.waterItem+"",(HERO.hpItem > 0),container,4,578,container.getInput().KEY_A,'A',"น้ำดื่มลดความกระหาย")){
			if(HERO.waterItem > 0 && HERO.WP < HERO.WPMAX-5){
				if(HERO.HP > 0){
					HERO.WP+=15;
					HERO.waterItem--;
				}
			}
		}
		
		if(HOTKEY(bm[2],icon[11],HERO.hpItem+"",(HERO.hpItem > 0),container,4+40,578,container.getInput().KEY_S,'S',"ข้าวห่อใบตอง")){
			if(HERO.hpItem > 0 && HERO.HP < HERO.HPMAX-5){
				if(HERO.HP > 0){
					HERO.HP+=20;
					HERO.hpItem--;
				}
			}
		}
		
		if(HOTKEY(bm[2],icon[12],HERO.mpItem+"",(HERO.hpItem > 0),container,4+40*2,578,container.getInput().KEY_D,'D',"ใบไม้เวทมนต์")){
			if(HERO.mpItem > 0 && HERO.SP < HERO.SPMAX-5){
				if(HERO.SP > 0){
					HERO.SP+=30;
					HERO.mpItem--;
				}
			}
		}
		
		if(HOTKEY(bm[2],icon[13],HERO.pwItem+"",(HERO.hpItem > 0),container,4+40*3,578,container.getInput().KEY_F,'F',"ขาหมู่ย่าง")){
			if(HERO.pwItem > 0 && HERO.PW < HERO.PWMAX-5){
				if(HERO.PW > 0){
					HERO.PW+=20;
					HERO.pwItem--;
				}
			}
		}
		
		if(BUTTON2(bm[0],bm[1],icon[6],container,834,625,"อุปกรณ์และการก่อสร้าง")){
			menugame[6] = !menugame[6];
		}
		if(BUTTON2(bm[0],bm[1],icon[2],container,878,625,"ทักษะ และ ประสบการณ์")){
			menugame[4]=!menugame[4];
			ssl = new ScrollSkillList();
			
			ssl.setRect(283,272,459,239);
			for(Skill skill : HERO.skill){
				if(skill != null){
					ssl.addList(skill);
				}
			}
		}
		if(BUTTON2(bm[0],bm[1],icon[3],container,922,625,"สถานะ และ ประสบการณ์")){
			scrollMenu = new ScrollMenu();
			scrollMenu.setRect((scrWidth-menu[1].getWidth())/2+12,200,375,359);
			scrollMenu.addList("Con [ "+HERO.CON+" ] ช่วยเพิ่มพลังชีวิต");
			scrollMenu.addList("Int [ "+HERO.INT+" ] ช่วยเพิ่มสติปัญญาความรุนแรงทางเวทมนต์");
			scrollMenu.addList("Abi [ "+HERO.ABI+" ] ความสามารถในการคุมทัพ");
			scrollMenu.addList("Str [ "+HERO.STR+" ] ความรุนแรงในการโจมตีด้วยดาบ");
			scrollMenu.addList("Dex [ "+HERO.DEX+" ] ความรุนแรงในการโจมตีด้วยธนู");
			scrollMenu.addList("Agi [ "+HERO.AGI+" ] ความเร็วในการโจมตี");
			scrollMenu.addList("Mem [ "+HERO.MEM+" ] ยังไม่สามารถใช้การได้");
			menugame[3] = !menugame[3];
		}
		if(BUTTON2(bm[0],bm[1],icon[0],container,966,625,"เมนูหลัก")){
			menugame[0] = !menugame[0];
		}
		if(BUTTON2(bm[0],bm[1],icon[7],container,878,669,"ซ่อมประตูเมือง")){
			for(HOME h : myHome){
				if(h.name == "ประตูเมือง" && h.hp < h.hpMax-350){
					if(HERO.gold_am >= 350){
						HERO.gold_am -= 350;
						scrollTxt[2].addString("คุณได้ซ่อมประตูเมืองแล้ว เงิน - 350 เม็ด");
						scrollTxt[2].nextLine();
						h.hp += 500;
						break;
					}else{
						scrollTxt[2].addString("เสียใจด้วยคุณมีเงินไม่พอ ต้องเงินใช้ค่าซ่อม ประตูเมือง 350 เม็ด");
						scrollTxt[2].nextLine();
					}
				}
			}
		}
		if(BUTTON2(bm[0],bm[1],icon[8],container,923,669,"ซ่อมหินศิลา")){
			for(HOME h : myHome){
				if(h.name == "หลักศิลา" && h.hp < h.hpMax && h.hp > 0){
					if(HERO.gold_am >= 500){
						HERO.gold_am -= 500;
						scrollTxt[2].addString("คุณได้ซ่อมหลักศิลาแล้ว เงิน - 500 เม็ด");
						scrollTxt[2].nextLine();
						h.hp += 500;
						break;
					}else{
						scrollTxt[2].addString("เสียใจด้วยคุณมีเงินไม่พอ ต้องเงินใช้ค่าซ่อม หลักศิลา 500 เม็ด");
						scrollTxt[2].nextLine();
					}
				}
			}
		}
		if(BUTTON2(bm[0],bm[1],icon[9],container,966,669,"ส่งสายรายงาน")){
			showAI = !showAI;
		}
		
		if(MODEBUTTON(bm[0],bm[1],icon[4],container,834,670,container.getInput().KEY_R,'R',"วิ่งเร็ว speed+2"))HERO.setRunMode(true);
		else HERO.setRunMode(false);
		
		g.drawImage(hLogo,10,20);
		DrawingText.DrawText("ทหารใน : "+this.HERO.INCONTROL+" / "+this.HERO.MAXINCONTROL,113,41,Color.white);
		DrawingText.DrawText("ทหารนอก : "+this.solider.size()+" / "+WMAX,113,67,Color.white);
		
		Menu_main(menugame[0],container);
		skillShotcut(container);

		this.showMenuTopInterface(container);
	}
	
	public void showMenuTopInterface(GameContainer container){
		/*StringBuffer plant = new StringBuffer(many.format(HERO.plant_am)+"");
		StringBuffer gold = new StringBuffer(many.format(HERO.gold_am)+"");
		StringBuffer hour = new StringBuffer(HERO.TIMER.getHour()+" : "+HERO.TIMER.getMinu());
		StringBuffer date = new StringBuffer(HERO._MAP.getName());*/
		
		
		//myInterTop.show(plant.toString(),gold.toString(),hour.toString(),date.toString());
		topManu(container);
		return;
	}
	
	private void skillShotcut(GameContainer container) throws SlickException {
		for(Skill skill : HERO.skill){
			if(skill != null){
				if(skill.getLv() > 0){
					if(!this.HERO.getDie()){ 
						switch(skill.getHKey()){
							case 'z' : case 'Z':{
								int imgX = 834,imgY = 717;
								if(skill.getCoolDown() > 0){
									g.drawImage(skill.getBGIcon(),imgX,imgY);
									g.drawImage(skill.getIcon(),imgX,imgY);
									skill.setReCoolDown(1);
									g.setColor(new Color(0,0,0,150));
									g.fillRect(imgX,imgY,(int)(38*((double)skill.getCoolDown()/skill.getMaxCoolD())),39);
								}else{
									if(HOTKEY(skill.getBGIcon(),skill.getIcon(),skill.getLv()+"",(HERO.SP >= skill.getMp()),container,imgX,imgY,container.getInput().KEY_Z,skill.getHKey(),skill.getName())){
										switch(HERO.JOB){
											case Archer:{
												if(HERO.SP >= skill.getMp()){
													HERO.fireArrow = !HERO.fireArrow;
													HERO.SP -= skill.getMp();
													skill.reCoolDown();
												}
												break;
											}
											case Warrior:{
												break;
											}
											case Wizard:{
												if(HERO.SP >= skill.getMp()){
													HERO.fr.clear();
													HERO.fr.add(new Flashright(skill.getLv(),HERO.getMATK()));
													HERO.SP -= skill.getMp();
													(new Sound("Resource/audio/msg/"+"fire01.wav")).play();
													sound[8].play();
													skill.reCoolDown();
													return;
												}
												break;
											}
										}
									}
								}
								break;
							}
							case 'x' : case 'X':{
								int imgX = 879,imgY = 717;
								if(skill.getCoolDown() > 0){
									g.drawImage(skill.getBGIcon(),imgX,imgY);
									g.drawImage(skill.getIcon(),imgX,imgY);
									skill.setReCoolDown(1);
									g.setColor(new Color(0,0,0,150));
									g.fillRect(imgX,imgY,(int)(38*((double)skill.getCoolDown()/skill.getMaxCoolD())),39);
								}else{
									if(HOTKEY(skill.getBGIcon(),skill.getIcon(),skill.getLv()+"",(HERO.SP >= skill.getMp()),container,imgX,imgY,container.getInput().KEY_X,skill.getHKey(),skill.getName())){
										switch(HERO.JOB){
											case Archer:{
												if(HERO.SP >= skill.getMp()){
													HERO.arrowShot = !HERO.arrowShot;
													HERO.SP-=skill.getMp();
													(new Sound("Resource/audio/msg/"+"sondowtook.wav")).play();
													skill.reCoolDown();
												}
												break;
											}
											case Warrior:{
											//	System.out.println(HERO.PW+" >= "+skill.getPw()+" = "+(HERO.PW >= skill.getPw()));
												if(HERO.PW >= skill.getPw()){
													HERO.bs.add(new BigSword(skill.getLv(),HERO.CenterOf('x'),HERO.getATK(),HERO.getMode()));
													HERO.PW -= skill.getPw();
													(new Sound("Resource/audio/msg/"+"swordSS.wav")).play();
													skill.reCoolDown();
												}
												break;
											}
											case Wizard:{
												if(HERO.SP > skill.getMp()){
													HERO.healskill.add(new HealSkill(HERO.getMATK()+(skill.getLv()*HERO.getMATK()/4),HERO));
													for(SOLIDER sol : solider){
														if((sol._x >= -200+HERO._x) && (sol._x <= HERO._x+200) && !sol.getDie()){
															sol.healskill.add(new HealSkill(HERO.getMATK()+(skill.getLv()*HERO.getMATK()/4),sol));
														}
													}
													for(MYSOLIDER sol : HERO.mSolider){
														if((sol._x >= -200+HERO._x) && (sol._x <= HERO._x+200) && !sol.getDie()){
															sol.healskill.add(new HealSkill(HERO.getMATK()+(skill.getLv()*HERO.getMATK()/4),sol));
														}
													}
													
													(new Sound("Resource/audio/msg/"+"heal.wav")).play();
													
													HERO.SP -= skill.getMp();
													skill.reCoolDown();
												}
												break;
											}
										}
									}
								}
								break;
							}
							case 'c' : case 'C':{
								int imgX = 924,imgY = 717;
								if(skill.getCoolDown() > 0){
									g.drawImage(skill.getBGIcon(),imgX,imgY);
									g.drawImage(skill.getIcon(),imgX,imgY);
									skill.setReCoolDown(1);
									g.setColor(new Color(0,0,0,150));
									g.fillRect(imgX,imgY,(int)(38*((double)skill.getCoolDown()/skill.getMaxCoolD())),39);
								}else{
									if(HOTKEY(skill.getBGIcon(),skill.getIcon(),skill.getLv()+"",(HERO.SP >= skill.getMp()),container,imgX,imgY,container.getInput().KEY_C,skill.getHKey(),skill.getName())){
										switch(HERO.JOB){
											case Archer:{
												break;
											}
											case Warrior:{
												break;
											}
											case Wizard:{
												if(HERO.SP >= skill.getMp()){
													HERO.bb.add(new BuffaloBow(HERO.CenterOf('x'),skill.getLv(),HERO.getMATK(),HERO.getMode(),HERO.MAPINDEX));
													skill.reCoolDown();
													HERO.SP-=skill.getMp();
													(new Sound("Resource/audio/msg/"+"buffss.wav")).play();
													slow.start();
												}
												break;
											}
										}
									}
								}
								break;
							}
							case 'v' : case 'V':{
								int imgX = 967,imgY = 717;
								if(HOTKEY(skill.getBGIcon(),skill.getIcon(),skill.getLv()+"",(HERO.SP >= skill.getMp()),container,imgX,imgY,container.getInput().KEY_V,skill.getHKey(),skill.getName())){
									switch(HERO.JOB){
										case Archer:{
											break;
										}
										case Warrior:{
											break;
										}
										case Wizard:{
											break;
										}
									}
								}
								break;
							}
						}
					}
				}
			}
		}
	}
	
	private void Menu_main(boolean f,GameContainer container) throws SlickException {
		Input inp = container.getInput();
		
		if(gameSelf == 'g'){
			if(inp.isKeyPressed(inp.KEY_ESCAPE)){
				menugame[0] = !menugame[0];
				sound[4].play();
			}
			
			if(f){
				g.drawImage(menu[0],(scrWidth-menu[0].getWidth())/2,(scrHeight-menu[0].getHeight())/2);
				if(BUTTON(button[0],"กลับสู่เกม",container,393,230))menugame[0]=false;
				if(BUTTON(button[0],"บันทึกเกม",container,393,230+(52))){
					alpha = 255;
					music[1].stop();
					sound[2].stop();
					music[5].play();
					music[5].loop();
					GAME = GAMESTATE.SaveOnStage;
					menugame[0]=false;
				}
				if(BUTTON(button[0],"โหลดเกม",container,393,230+(52)*2));
				if(BUTTON(button[0],"ออกจากเกม",container,393,230+(52)*5))menugame[1]=true;
			}
			if(menugame[1]){
				menugame[0] = false;
				char DailogMenu = dialog(icon[5],"ท่านต้องการออกจากเกมหรือไม่ !",container,(scrWidth-menu[3].getWidth())/2,(scrHeight-menu[3].getHeight())/2);
				if(DailogMenu == 'a'){
					container.exit();
				}else if(DailogMenu == 'b'){
					menugame[1]=false;
				}
			}
			
			if(menugame[4]){
				SkillMenu(container);
			}
			
			if(menugame[6]){
				g.drawImage(menu[1],(scrWidth-menu[1].getWidth())/2,(scrHeight-menu[1].getHeight())/2);
				if(BUTTON(button[1],"",container,((scrWidth-menu[1].getWidth())/2)+menu[1].getWidth()-40,(scrHeight-menu[1].getHeight())/2+10)){
					menugame[6]=!menugame[6];
				}
				
				String subject = "อุปกรณ์และการก่อสร้าง";
				DrawingText.DrawText(subject,((scrWidth-menu[1].getWidth())/2)+30,(scrHeight-menu[1].getHeight())/2+10,font[1]);
			}
			
			if(menugame[3]){
				g.drawImage(menu[1],(scrWidth-menu[1].getWidth())/2,(scrHeight-menu[1].getHeight())/2);
				if(BUTTON(button[1],"",container,((scrWidth-menu[1].getWidth())/2)+menu[1].getWidth()-40,(scrHeight-menu[1].getHeight())/2+10)){
					menugame[3]=!menugame[3];
				}
				
				try{
					scrollMenu.render(container,mouseHIT(container));
					if(scrollMenu.getList().get(0).isButton()){
						if(HERO.jobLv >= 1){
							HERO.CON++;
							HERO.jobLv--;
							scrollMenu.getList().get(0).modifyTxt("Con [ "+HERO.CON+" ] ช่วยเพิ่มพลังชีวิต");
						}
					}
					if(scrollMenu.getList().get(1).isButton()){
						if(HERO.jobLv >= 1){
							HERO.INT++;
							HERO.jobLv--;
							scrollMenu.getList().get(1).modifyTxt("Int [ "+HERO.INT+" ] ช่วยเพิ่มสติปัญญาความรุนแรงทางเวทมนต์");
						}
					}
					if(scrollMenu.getList().get(2).isButton()){
						if(HERO.jobLv >= 1){
							HERO.ABI++;
							HERO.jobLv--;
							scrollMenu.getList().get(2).modifyTxt("Abi [ "+HERO.ABI+" ] ความสามารถในการคุมทัพ");
						}
					}
					if(scrollMenu.getList().get(3).isButton()){
						if(HERO.jobLv >= 1){
							HERO.STR++;
							HERO.jobLv--;
							scrollMenu.getList().get(3).modifyTxt("Str [ "+HERO.STR+" ] ความรุนแรงในการโจมตีด้วยดาบ");
						}
					}
					if(scrollMenu.getList().get(4).isButton()){
						if(HERO.jobLv >= 1){
							HERO.DEX++;
							HERO.jobLv--;
							scrollMenu.getList().get(4).modifyTxt("Dex [ "+HERO.DEX+" ] ความรุนแรงในการโจมตีด้วยธนู");
						}
					}
					if(scrollMenu.getList().get(5).isButton()){
						if(HERO.jobLv >= 1){
							HERO.AGI++;
							HERO.jobLv--;
							scrollMenu.getList().get(5).modifyTxt("Agi [ "+HERO.AGI+" ] ความเร็วในการโจมตี");
						}
					}
					if(scrollMenu.getList().get(6).isButton()){
						if(HERO.jobLv >= 1){
							HERO.MEM++;
							HERO.jobLv--;
							scrollMenu.getList().get(6).modifyTxt("Mem [ "+HERO.MEM+" ] ยังไม่สามารถใช้การได้");
						}
					}
					
				}catch(NullPointerException e){
				}
				String subject = "สถานะ และ ประสบการณ์";
				DrawingText.DrawText(subject,((scrWidth-menu[1].getWidth())/2)+30,(scrHeight-menu[1].getHeight())/2+10,font[1]);
			}
		}
	}
	
	private void SkillMenu(GameContainer container) throws SlickException {
		int x = (scrWidth-menu[5].getWidth())/2,y = (scrHeight-menu[5].getHeight())/2;
		String subject = "เรียนรู้ ทักษะ และ ประสบการณ์";
		Graphics g = new Graphics();
		g.drawImage(this.menu[5],x,y);
		ssl.render(container,mouseHIT(container));
		
		if(ssl.getList().get(0).isButton()){
			if(HERO.skillLv >= 1){
				if(HERO.skill[0].getLv() < HERO.skill[0].getLvMax()){
					HERO.skill[0].up();
					HERO.skillLv--;
				}
			}
		}
		
		if(ssl.getList().get(1).isButton()){
			if(HERO.skillLv >= 1){
				if(HERO.skill[1].getLv() < HERO.skill[1].getLvMax()){
					HERO.skill[1].up();
					HERO.AGI++;
					HERO.skillLv--;
				}
			}
		}
		
		if(ssl.getList().get(2).isButton()){
			if(HERO.skillLv >= 1){
				if(HERO.skill[2].getLv() < HERO.skill[2].getLvMax()){
					HERO.skill[2].up();
					HERO.skillLv--;
				}
			}
		}
		
		if(ssl.getList().get(3).isButton()){
			if(HERO.skillLv >= 1){
				if(HERO.skill[3].getLv() < HERO.skill[3].getLvMax()){
					HERO.skill[3].up();
					HERO.skillLv--;
				}
			}
		}
		
		if(ssl.getList().get(4).isButton()){
			if(HERO.skillLv >= 1){
				if(HERO.skill[4].getLv() < HERO.skill[4].getLvMax()){
					HERO.skill[4].up();
					HERO.skillLv--;
				}
			}
		}
		
		if(ssl.getList().get(5).isButton()){
			if(HERO.skillLv >= 1){
				if(HERO.skill[5].getLv() < HERO.skill[5].getLvMax()){
					HERO.skill[5].up();
					HERO.skillLv--;
				}
			}
		}
		
		if(BUTTON(button[1],"",container,x+menu[5].getWidth()-button[1].getWidth()-10,y+10)){
				menugame[4]=!menugame[4];
		}
		
		DrawingText.DrawText(subject,x+30,y+10,font[1]);
	}
	
	private void mouseControl(Image img,GameContainer container){
		img.draw(container.getInput().getMouseX(),container.getInput().getMouseY());
	}
	
	private void titleRender(){
		String ver = "เวอร์ชั่น  "+this.version;
		
		g.drawImage(title,0,0);
		
		for(int i = 0;i<could.length;i++){
			could[i].UpdateAndRender();
		}
		flowerAura.render();
		
		g.drawImage(back,0,0);
		g.drawImage(arLogo,100,100);
		DrawingText.DrawText(ver,scrWidth-font[1].getWidth(ver)-20,scrHeight-font[1].getHeight(ver)*2-20,font[1]);
	}
	
	private void interfaceDescript(GameContainer container,int step){
		try{
			Image sImg = new Image(0,0);
			Graphics g = new Graphics();
		
			switch(step){
				case 1:{
					sImg = new Image("Resource/descript/script01.PNG");
					break;
				}
				case 2:{
					sImg = new Image("Resource/descript/script02.PNG");
					break;
				}
			}
			
			g.drawImage(sImg,(scrWidth-sImg.getWidth())/2,(scrWidth-sImg.getHeight())/2-120,new Color(255,255,255,220));
			if(BUTTON(new Image("Resource/descript/bClose.PNG"),"",container,607,201)){
				descript=false;
			}
		}catch(SlickException e){
		}
	}
	
	private void topManu(GameContainer container){
		g=null;
		g=new Graphics();
		g.setFont(font[1]);
		try{
			Image imgMenuTop = new Image(FileList.imgeDir+"bgtop.PNG");
			
			String plant = new String(many.format(HERO.plant_am)+"");
			String gold = new String(many.format(HERO.gold_am)+"");
			
			String map = new String(HERO._MAP.getName());
			g.drawImage(imgMenuTop,scrWidth-imgMenuTop.getWidth(),0);
			//System.out.println("data is "+plant+","+gold+","+day+","+map);
			
			g.drawString("เขต : "+HERO._MAP.getName(),scrWidth-imgMenuTop.getWidth()+35,10);
			g.drawString("ผัก : "+many.format(HERO.plant_am),scrWidth-imgMenuTop.getWidth()+35,32);
			g.drawString("ทอง : "+many.format(HERO.gold_am),scrWidth-imgMenuTop.getWidth()+35,54);
			
			
		}catch(SlickException e){
		}catch(NullPointerException e){
			
		}
		g=null;
		g=new Graphics();
		g.setFont(font[1]);
	}
	
	private void drawIB(GameContainer container,int x,int y){
		if(HOTKEY(bm[2],gameItem[0],HERO.item[0]+"",(HERO.item[0] > 0),container,x,y+(bm[2].getHeight()*0),container.getInput().KEY_Z,'Z',"ปุ๋ยสำหรับผัก")){
			toolFarm = ToolFarm.Lifemode;
		}
		if(HOTKEY(bm[2],gameItem[1],HERO.item[1]+"",(HERO.item[1] > 0),container,x,y+(bm[2].getHeight()*1),container.getInput().KEY_X,'X',"ช้อนพรวนดิน")){
			toolFarm = ToolFarm.Digmode;
		}
		if(HOTKEY(bm[2],gameItem[2],HERO.item[2]+"",(HERO.item[2] > 0),container,x,y+(bm[2].getHeight()*2),container.getInput().KEY_C,'C',"น้ำสำหรับผัก")){
			toolFarm = ToolFarm.Watermode;
		}
		if(HOTKEY(bm[2],gameItem[3],HERO.item[3]+"",(HERO.item[3] > 0),container,x,y+(bm[2].getHeight()*3),container.getInput().KEY_V,'V',"ผัก D")){
			toolFarm = ToolFarm.CropDmode;
		}
		if(HOTKEY(bm[2],gameItem[4],HERO.item[4]+"",(HERO.item[4] > 0),container,x,y+(bm[2].getHeight()*4),container.getInput().KEY_B,'B',"ผัก C")){
			toolFarm = ToolFarm.CropCmode;
		}
		if(HOTKEY(bm[2],gameItem[5],HERO.item[5]+"",(HERO.item[5] > 0),container,x,y+(bm[2].getHeight()*5),container.getInput().KEY_N,'N',"ผัก B")){
			toolFarm = ToolFarm.CropBmode;
		}
		if(HOTKEY(bm[2],gameItem[5],HERO.item[6]+"",(HERO.item[6] > 0),container,x,y+(bm[2].getHeight()*6),container.getInput().KEY_M,'M',"ผัก A")){
			toolFarm = ToolFarm.CropAmode;
		}
	}
	
	private boolean menuOut(GameContainer container){
		Input key = container.getInput();
		boolean key1 = key.isKeyDown(key.KEY_LALT);
		boolean key2 = key.isKeyDown(key.KEY_F4);
		
		if(key1 && key2)return(true);
		return(false);
	}
	
	private boolean WEAPONBOX(Image img,Image img2,GameContainer container,int x,int y){
		Rectangle mRect = mouseHIT(container);
		Rectangle bRect = new Rectangle(x,y,img.getWidth(),img.getHeight());
		
		if(mRect.intersects(bRect)){
			if(container.getInput().isMousePressed(container.getInput().MOUSE_LEFT_BUTTON)){
				sound[1].play(); 
				return(true);
			}
			g.drawImage(img,x,y,new Color(255,255,255));
			g.drawImage(img2,x,y,new Color(255,255,255));
		}else{
			g.drawImage(img,x,y,new Color(200,200,200));
			g.drawImage(img2,x,y,new Color(0,0,0));
		}
		
		return(false);
	}
	
	private boolean BUTTON(Image img,String txt,GameContainer container,int x,int y){
		Rectangle mRect = mouseHIT(container);
		Rectangle bRect = new Rectangle(x,y,img.getWidth(),img.getHeight());
		
		if(mRect.intersects(bRect)){
			if(container.getInput().isMousePressed(container.getInput().MOUSE_LEFT_BUTTON)){
				sound[0].play();
				return(true);
			}
			logic_mouse_hit = true;
			g.drawImage(img,x,y,new Color(255,255,255));
		}else{
			logic_mouse_hit = false;
			g.drawImage(img,x,y,new Color(200,200,200));
		}
		
		DrawingText.DrawText(txt,x+(img.getWidth()-font[0].getWidth(txt))/2,y+(img.getHeight()-font[0].getHeight(txt))/2-5,font[0]);
		
		return(false);
	}
	
	private boolean BUTTON2(Image img1,Image img2,Image img3,GameContainer container,int x,int y,String comment){
		Rectangle mRect = mouseHIT(container);
		Rectangle bRect = new Rectangle(x,y,img1.getWidth(),img1.getHeight());
		
		if(mRect.intersects(bRect)){
			if(container.getInput().isMousePressed(container.getInput().MOUSE_LEFT_BUTTON)){
				sound[4].play();
				return(true);
			}else{
				logic_mouse_hit = true;
				g.drawImage(img2,x,y,new Color(200,200,200));
				g.drawImage(img3,x,y,new Color(255,255,255));
				Comment(img3,comment,829,577);
			}
		}else{
			logic_mouse_hit = false;
			g.drawImage(img1,x,y,new Color(200,200,200));
			g.drawImage(img3,x,y,new Color(150,150,150));
		}
	
		return(false);
	}
	
	private boolean BUTTON2(Image img1,Image img2,String val,boolean bool,GameContainer container,int x,int y,Sound sound,String comment){
		Rectangle mRect = mouseHIT(container);
		Rectangle bRect = new Rectangle(x,y,img1.getWidth(),img1.getHeight());
		
		if(mRect.intersects(bRect)){
			if(container.getInput().isMousePressed(container.getInput().MOUSE_LEFT_BUTTON)){
				if(bool) sound.play();
				else this.sound[4].play();
				return(true);
			}else{
				logic_mouse_hit = true;
				g.drawImage(img1,x,y,new Color(210,210,210));
				g.drawImage(img2,x,y,new Color(255,255,255));
				Comment(img2,comment,829,577);
			}
		}else{
			logic_mouse_hit = false;
			g.drawImage(img1,x,y,new Color(200,200,200));
			g.drawImage(img2,x,y,new Color(150,150,150));
		}
		DrawingText.DrawText(val,x+(img1.getWidth()-font[1].getWidth(val)),y+(img1.getHeight()-font[1].getHeight(val))-2,font[1]);
	
		return(false);
	}
	
	private boolean Button2(Image before,Image afther,int x,int y,Rectangle hit,Input input){
		Rectangle box = new Rectangle(x,y,before.getWidth(),before.getHeight());
		Graphics g = new Graphics();
		g.setFont(font[0]);
		
		if(box.intersects(hit)){
			g.drawImage(afther,x,y);
			if(input.isMousePressed(input.MOUSE_LEFT_BUTTON)){
				sound[0].play();
				return(true);
			}
		}else{
			g.drawImage(before,x,y,new Color(180,180,180));
		}

		return(false);
	}
	
	private void Comment(Image img,String comment,int x,int y){
		int width = 189;
		int height = 38;
		
		g.setColor(new Color(0,0,0,150));
		g.fillRect(x, y,width,height);
		g.setColor(new Color(255,255,255));
		g.drawImage(img,x+10,y);
		DrawingText.DrawText(comment,x+img.getWidth()+20,y+15,font[1]);
	}

	private boolean MODEBUTTON(Image img1,Image img2,Image img3,GameContainer container,int x,int y,int keyID,char Char,String comment){
		Rectangle mRect = mouseHIT(container);
		Rectangle bRect = new Rectangle(x,y,img1.getWidth(),img1.getHeight());
		Input key = container.getInput();
		String txtKey = Char+"";
		g.setFont(font[1]);
		
		if(key.isKeyPressed(keyID)){
			sound[4].play();
		}
		
		if(key.isKeyDown(keyID)){
			g.drawImage(img2,x,y,new Color(200,200,200));
			g.drawImage(img3,x,y,new Color(255,255,255));
			DrawingText.DrawText(txtKey,x+(img1.getWidth()-g.getFont().getWidth(txtKey)),y+(img1.getHeight()-g.getFont().getHeight(txtKey)),g.getFont());
			return(true);
		}else{
			g.drawImage(img1,x,y,new Color(200,200,200));
			g.drawImage(img3,x,y,new Color(150,150,150));
		}

		if(mRect.intersects(bRect)){
			g.drawImage(img2,x,y,new Color(200,200,200));
			g.drawImage(img3,x,y,new Color(255,255,255));
			Comment(img3,comment,829,577);
		}else{
			g.drawImage(img1,x,y,new Color(200,200,200));
			g.drawImage(img3,x,y,new Color(150,150,150));
		}
	
		DrawingText.DrawText(txtKey,x+(img1.getWidth()-g.getFont().getWidth(txtKey)),y+(img1.getHeight()-g.getFont().getHeight(txtKey)),g.getFont());
		return(false);
	}
	
	private boolean HOTKEY(Image img,Image icon,String val,boolean bool,GameContainer container,int x,int y,int keyID,char Char,String comment){
		Rectangle mRect = mouseHIT(container);
		Rectangle bRect = new Rectangle(x,y,img.getWidth(),img.getHeight());
		Input key = container.getInput();
		String txtKey = Char+"";
		
		if(key.isKeyPressed(keyID)){
			g.drawImage(img,x,y,new Color(200,200,200));
			g.drawImage(icon,x,y,new Color(255,255,255));
			sound[4].play();
			DrawingText.DrawText(val,x+(img.getWidth()-font[1].getWidth(val)),y+(img.getHeight()-font[1].getHeight(val))-2,font[1]);
			DrawingText.DrawText(txtKey,x,y,Color.yellow);
			return(true);
		}else{
			g.drawImage(img,x,y,new Color(200,200,200));
			g.drawImage(icon,x,y,new Color(150,150,150));
		}
		
		if(mRect.intersects(bRect)){
			if(container.getInput().isMousePressed(container.getInput().MOUSE_LEFT_BUTTON)){
				sound[4].play();
				return(true);
			}
		}
		
		if(mRect.intersects(bRect)){
			g.drawImage(img,x,y,new Color(200,200,200));
			g.drawImage(icon,x,y,new Color(255,255,255));
			Comment(icon,comment,829,577);
		}else{
			g.drawImage(img,x,y,new Color(200,200,200));
			g.drawImage(icon,x,y,new Color(150,150,150));
		}
		
		DrawingText.DrawText(val,x+(img.getWidth()-font[1].getWidth(val)),y+(img.getHeight()-font[1].getHeight(val))-2,font[1]);
		DrawingText.DrawText(txtKey,x,y,Color.yellow);
		return(false);
	}
	
	private char dialog(Image icon,String msg,GameContainer container,int x,int y){
		g.drawImage(menu[3],x,y);
		g.drawImage(icon,x+30,y+30);
		
		DrawingText.DrawText(msg,x+(icon.getWidth()+30),y+35,font[1]);
		
		if(BUTTON(button[0],"ตกลง",container,x+(menu[3].getWidth()-button[0].getWidth())/2,y+(menu[3].getHeight())-button[0].getHeight()*2-10)) return('a');
		if(BUTTON(button[0],"ยกเลิก",container,x+(menu[3].getWidth()-button[0].getWidth())/2,y+(menu[3].getHeight())-button[0].getHeight()-11)) return('b');
		
		return('c');
	}
	
	private void cutFrame(){
		Graphics g = new Graphics();
		if(alpha != 0){
			g.setColor(new Color(0,0,0,alpha));
			g.fillRect(0,0,scrWidth,scrHeight);
			g.setColor(new Color(255,255,255));
		}
		g.destroy();
		g=null;
	}
	
	private Rectangle mouseHIT(GameContainer container){
		return(new Rectangle(container.getInput().getMouseX(),container.getInput().getMouseY(),0,0));
	}
	
	private int clamp(int val,int min,int max){
		if(val <= min) val = min;
		if(val >= max) val = max;
		return(val);
	}
	
	public Music getMusic(int index){
		return(this.music[index]);
	}
	
	public Sound getSound(int index){
		return(this.sound[index]);
	}
	
	public void goToFarm(){
		realpha();
		this.GAME = GAMESTATE.farm;
	}
	
	public void realpha(){
		this.alpha = 255;
	}
	
	public ScrollTxt getScrollTxt(int index){
		return(scrollTxt[index]);
	}
	
	public void drawBar(int min,int max,int x,int y,int w,int h,Color color) throws SlickException {
		if(min < 0)min=0;
		if(min > max)min=max;
		
		g.setColor(new Color(0,0,0,150));
		g.fillRect(x+2,y,w+2,h);
		g.setColor(color);
		g.fillRect(x+3,y+1,(int)(w*((double)min/max)),h-2);
		g.setColor(new Color(255,255,255,150));
		g.fillRect(x+3,y+1,w,h/2);
	}
}

class slowEffect{
	private int speed,delay,alpha;
	private GameContainer container;
	private boolean pause=true;
	
	public slowEffect(GameContainer container){
		this.container = container;
		this.delay = redelay();
		this.speed = 50;
		this.alpha = 0;
	}
	
	public int redelay(){
		return(120);
	}
	
	private void clamp(int min,int max){
		if(alpha <= min) alpha = min;
		if(alpha >= max) alpha = max;
	}
	
	public void update(){
		Graphics g = new Graphics();
		clamp(0,130);
		if(!pause){
			if(delay > 0){
				delay--;
			}else pause=true;
			alpha+=5;
			System.out.println("Warning");
		}else{
			delay = redelay();
			speed = 50;
			alpha-=5;
		}
		g.setColor(new Color(0,0,0,alpha));
		g.fillRect(0,0,1024,768);
		
	}
	
	public void start(){
		if(pause){
			pause=false;
		}
	}
}

class TargetLog{
	private Graphics g = new Graphics();
	private int x,y,width = 30,height = 80;
	private Image img;
	
	public TargetLog(){
		try{
			this.x = 1500;
			img = new Image(FileList.imgeDir+"tarGet.PNG");
		}catch(SlickException e){
		}
	}
	
	public void setPos(int x,int y){
		g.drawImage(img,x+10,y-30);
		this.x = x;
		this.y = y;
	}
	
	public Rectangle box(){
		Rectangle box = new Rectangle(x,y,width,height);
		
		return(box);
	}
}

class Myinterfacetop{
	private int centerX,scrWidth,scrHeight;
	private Font font;
	private Image img;
	private Graphics g = new Graphics();
	
	public Myinterfacetop(){
		this.scrWidth = 1024;
		this.scrHeight = 768;
		try{
			this.img = new Image(FileList.imgeDir+"InterfaceTop.png");
			this.font = new AngelCodeFont(FileList.fontDir+"simpleFont.fnt",FileList.fontDir+"simpleFont.png");
			
			this.centerX = (scrWidth-img.getWidth())/2;
			this.g.setFont(this.font);
		}catch(SlickException e){
			javax.swing.JOptionPane.showMessageDialog(null,e.toString(),"Message",javax.swing.JOptionPane.ERROR_MESSAGE);
		}
	}

	public void show(String msg1,String msg2,String msg3,String msg4){
		try{
			g.drawImage(this.img,this.centerX,0);
			
			g.drawString(msg1,330, 9);
			g.drawString(msg2,510, 9);
			g.drawString(msg3,680, 9);
			g.drawString("เขต : "+msg4,scrWidth - 150,9);
		}catch(NullPointerException npe){
			javax.swing.JOptionPane.showMessageDialog(null,npe.toString(),"Message",javax.swing.JOptionPane.ERROR_MESSAGE);
		}
		return;
	}
	
	private void trace(String msg){
		System.out.println(msg+"\n");
	}
}

class Could{
	private Image img;
	private float x,y,speed;
	private Graphics g = new Graphics();
	
	public Could(Image img,float x,float y){
		try{
			this.img = img;
			this.x = x;
			this.y = y;
			speed  = 0.3f;
		}catch(Exception e){
		}
	}
	
	public void setPostion(float x,float y){
		this.x = x;
		this.y = y;
	}
	
	public void UpdateAndRender(){
		g.drawImage(img,x,y);
		
		if(x <= 1024){
			x+=speed;
		}else if(x > 1024){
			x=0-img.getWidth();
		}
	}
	
}

class DWar{
	public int day,update;
	
	public DWar(int day,int update){
		this.day = day;
		this.update = update;
	}
}

class Status{
	private int min,max,x,y,lv;
	private String name;
	
	public Status(String name,int min,int max){
		this.name = name;
		this.min = min;
		this.max = max;
		this.lv = 0;
	}
	
	public int getLv(){
		return(this.lv);
	}
	
	public String getName(){
		return(this.name);
	}
	
	public int getMin(){
		return(this.min);
	}
	
	public int getMax(){
		return(this.max);
	}
	
	public void drawing(){
	}
}

class DataNew{
	public int min,max;
	public String name;
	
	public DataNew(String name,int min,int max){
		this.name = name;
		this.min = min;
		this.max = max;
	}
}

class JDrawingText{
	private Font font;
	
	public JDrawingText(){
		try{
			font = new AngelCodeFont(FileList.fontDir+"simpleFont.fnt",FileList.fontDir+"simpleFont.png");
		}catch(SlickException e){
		}
	}
	
	public void DrawText(String txt,int x,int y){
		StringBuffer msg = new StringBuffer(txt);
		Graphics g = new Graphics();
		try{
			if(msg.toString().equals(txt)){
				g.setColor(new Color(0,0,0,200));
				g.drawString(msg.toString(),x+1,y+1);
				g.setColor(new Color(255,255,255));
				g.drawString(msg.toString(),x,y);
			}else msg = new StringBuffer(txt);
		}catch(NullPointerException e){
			g.setFont(font);
		}
		txt = null;
	}
	
	public void DrawText(String txt,int x,int y,Font f){
		StringBuffer msg = new StringBuffer(txt);
		Graphics g = new Graphics();
		try{
			if(msg.toString().equals(txt)){
				g.setFont(f);
				g.setColor(new Color(0,0,0,200));
				g.drawString(msg.toString(),x+1,y+1);
				g.setColor(new Color(255,255,255));
				g.drawString(msg.toString(),x,y);
			}else msg = new StringBuffer(txt);
		}catch(NullPointerException e){
			g.setFont(font);
		}
		txt = null;
	}
	
	public void DrawText(String txt,int x,int y,Color c){
		StringBuffer msg = new StringBuffer(txt);
		Graphics g = new Graphics();
		try{
			if(msg.toString().equals(txt)){
				g.setFont(font);
				g.setColor(new Color(0,0,0,200));
				g.drawString(msg.toString(),x+1,y+1);
				g.setColor(c);
				g.drawString(msg.toString(),x,y);
			}else msg = new StringBuffer(txt);
		}catch(NullPointerException e){
			g.setFont(font);
		}
		txt = null;
	}
	
	public void DrawText(String txt,int x,int y,Font f,Color c){
		StringBuffer msg = new StringBuffer(txt);
		Graphics g = new Graphics();
		try{
			if(msg.toString().equals(txt)){
				g.setFont(f);
				g.setColor(new Color(0,0,0,200));
				g.drawString(msg.toString(),x+1,y+1);
				g.setColor(c);
				g.drawString(msg.toString(),x,y);
			}else msg = new StringBuffer(txt);
		}catch(NullPointerException e){
			g.setFont(font);
		}
		txt = null;
	} 
	
	public int getTextWidth(String txt){
		return(font.getWidth(txt));
	}
	
	public int getCenterText(int width,String txt){
		int tWidth = font.getWidth(txt);
		return((width-tWidth)/2);
	}
}

class mediaTimer{
	protected Image img,wImg;
	protected ParticleSystem effect_01;
	protected Sound[] weaterSound = new Sound[4];
	protected Color c;
	
	public mediaTimer(){
		try{
			effect_01 = ParticleIO.loadConfiguredSystem("Resource/effect/ef001.xml");
			wImg = new Image(FileList.logoDir+"warlogo.png");
			weaterSound = new Sound[]{
			new Sound("Resource/audio/bgs/bgs04.wav"),
				new Sound("Resource/audio/bgs/war_ready.wav"),
				new Sound("Resource/audio/bgs/wolf.wav"),
			};
			img = new Image("Resource/img/ef1");
		}catch(SlickException e){
		}catch(IOException e){
		}
	}
}

class Timer extends mediaTimer implements Serializable{
	public int totalWar,delay,callWar = /*2*/8;
	public int hour,minu,sec,day,week;
	public DWar[] dwar;
	public boolean war,isWar;
	public int wTotal = 0,fade = 500,fadeAlpha = 100,fadeL = 255;
	private int weatherAlpha=0,secf,max = 200,weatherID=0,alpha=255;
	private int warMax = 11,update = 0;
	public boolean weekUP = false,endWar = true;
	private CHARACTER ch;
	
	public Timer(){
		war = false; isWar = false;
		hour = 6;
		minu = 0;
		sec = 0;
		week = 1;
		day = 1;
		totalWar = 0;
		delay = 10;
	}
	
	public void setHero(CHARACTER h){
		this.ch = h;
	}
	
	public int getHour(){
		return(hour);
	}
	
	public int getMinu(){
		return(minu);
	}
	
	public int getSec(){
		return(sec);
	}
	
	public int getDay(){
		return(day);
	}
	
	public int getWeek(){
		return(week);
	}
	
	public int war_redelay(){
		return(1000);
	}
	
	public int totalWarLength(){
		return(5);
	}
	
	public Image getWarLogo(){
		return(this.wImg);
	}
	
	public int randWar(){
		return((int)1+(new Random()).nextInt(23));
	}
	
	public boolean warMangement(boolean ok){
		//System.out.printf("time is %2d\n",hourWar);
		if((this.day%2) == 1){
			if((this.hour == callWar) && (this.minu == 1) && (this.sec == 1)){ 
				weaterSound[1].play();
				try{
					(new Sound("Resource/audio/msg/warring.wav")).play();
				}catch(SlickException e){
				}
				if(ok)
				if(wTotal <= 12)
				wTotal++;
				totalWar = 0;
				war = true;
				isWar = true;
				endWar = false;
				return(war);
			}
		}
		
		return(false);
	}
	
	private void setColor(int r,int g,int b){
		this.c = new Color(r,g,b);
	}
	
	public int getAlpha(){
		if(this.alpha > 0){
			this.alpha-=2;
		}
		return(alpha);
	}
	
	public Color getColor(){
		return(c);
	}
	
	private int clamp(int val,int min,int max){
		if(val <= min) val = min;
		if(val >= max) val = max;
		return(val);
	}
	
	public void timerUpdate() throws SlickException{
		
		int TimerUpdate = 11;
		
		Graphics g = new Graphics();
		effect_01.update((int)TimerUpdate);
		
		if((hour == 6) && (minu == 0) && (sec == 0)){alpha = 255; weaterSound[0].play();}
		if((hour == 8) && (minu == 0) && (sec == 0)) alpha = 255;
		if((hour == 17) && (minu == 0) && (sec == 0)) alpha = 255;
		if((hour == 19) && (minu == 0) && (sec == 0)) alpha = 255; 
		if((hour == 23) && (minu == 0) && (sec == 0)) weaterSound[2].play();
		
		if((hour >= 6) && (hour < 8)){
			weatherID = 0;
			setColor(220,220,220);
		}
		if((hour >= 8) && (hour < 17)){
			weatherID = 1;
			setColor(255,255,255);
		}
		if((hour >= 17) && (hour < 19)){
			weatherID = 2;
			setColor(220,220,220);
		}
		if((hour >= 19) || (hour < 6)){
			weatherID = 3;
			setColor(180,180,180);
			effect_01.render(0,0);
		}
		
		if(hour > 23){ 
			hour = 0;
			day++;
			callWar = randWar();
			this.ch.gold_am += 250;
			weekUP=true;
		}
		if((day%7)==1){
			if(weekUP){
				week++;
				weekUP=false;
			}
		}
		
		if(sec < 10){//10
			sec++;
		}else{
			sec = 0;
			minu++;
		}if(minu == 60){
			hour++;
			minu=0;
			sec=0;
		}
		
		if((hour >= 4) && (hour < 7)){
			if(weatherAlpha < 200) weatherAlpha++;
		}else{
			if(weatherAlpha > 0) weatherAlpha--;
		}
		
		g.drawImage(img,0,270,new Color(255,255,255,clamp(weatherAlpha,0,200)));
	}
	
	public int getWeatherID(){
		return(weatherID);
	}
}

abstract class HCHAR{
	protected Animation FrameR1[] = new Animation[7],FrameL1[] = new Animation[7];
	protected Animation FrameR2[] = new Animation[7],FrameL2[] = new Animation[7];
	protected Animation FrameR3[] = new Animation[7],FrameL3[] = new Animation[7];
	protected Animation FrameR4[] = new Animation[7],FrameL4[] = new Animation[7];
	protected Animation FrameR5[] = new Animation[7],FrameL5[] = new Animation[7];
	protected Animation FrameR6[] = new Animation[7],FrameL6[] = new Animation[7];
	protected Animation FrameR7[] = new Animation[7],FrameL7[] = new Animation[7];
	protected ParticleSystem[] effect = new ParticleSystem[5];
	protected boolean isPlayerDead;
	protected Font font,font2,gFont;
	protected Graphics g = new Graphics();
	protected Sound[] sound = new Sound[10];
	protected Sound soundArcher,soundD;
	protected char mode;
	protected Color color;
	protected int frameEf;
	public ArrayList<MYSOLIDER> mSolider = new ArrayList<MYSOLIDER>();
	public ArrayList<TARROW> arrowList = new ArrayList<TARROW>();
	protected ArrayList<PTS> pts = new ArrayList<PTS>();
	////////// status
	public ArrayList<HealSkill> healskill = new ArrayList<HealSkill>();
	public ArrayList<DMGTEXT> msgText = new ArrayList<DMGTEXT>();
	public ArrayList<Flashright> fr = new ArrayList<Flashright>();
	public ArrayList<BuffaloBow> bb = new ArrayList<BuffaloBow>();
	public ArrayList<BigSword> bs = new ArrayList<BigSword>();
	
	public HCHAR(){
		try{
			font = new AngelCodeFont("Resource/font/simpleFont.fnt","Resource/font/simpleFont.png");
			font2 = new AngelCodeFont("Resource/font/SimpleFont4.fnt","Resource/font/SimpleFont4.png");
			gFont = new AngelCodeFont("Resource/font/greenFont.fnt","Resource/font/greenFont.png");
			sound[9] = new Sound("Resource/audio/bgs/hit1.wav");
			soundD = new Sound(FileList.bgsDir+"heroDie.wav");
		}catch(SlickException e){
		}
	}
	
	protected void setAnimStopAt(char mode,int Animindex,int at){
		if(mode == 'R'){
			this.FrameR1[Animindex].stopAt(at);
			if(!isPlayerDead)
			this.FrameR2[Animindex].stopAt(at);
			this.FrameR3[Animindex].stopAt(at);
			this.FrameR4[Animindex].stopAt(at);
			this.FrameR5[Animindex].stopAt(at);
			this.FrameR6[Animindex].stopAt(at);
		}else{
			this.FrameL1[Animindex].stopAt(at);
			if(!isPlayerDead)
			this.FrameL2[Animindex].stopAt(at);
			this.FrameL3[Animindex].stopAt(at);
			this.FrameL4[Animindex].stopAt(at);
			this.FrameL5[Animindex].stopAt(at);
			this.FrameL6[Animindex].stopAt(at);
		}
	}
	
	protected void setAnimStop(int Animindex){
		this.FrameR1[Animindex].stop();
		if(!isPlayerDead)
		this.FrameR2[Animindex].stop();
		this.FrameR3[Animindex].stop();
		this.FrameR4[Animindex].stop();
		this.FrameR5[Animindex].stop();
		this.FrameR6[Animindex].stop();
						
		this.FrameL1[Animindex].stop();
		if(!isPlayerDead)
		this.FrameL2[Animindex].stop();
		this.FrameL3[Animindex].stop();
		this.FrameL4[Animindex].stop();
		this.FrameL5[Animindex].stop();
		this.FrameL6[Animindex].stop();
	}
	
	protected boolean setAnimReset(int Animindex){
		this.FrameR1[Animindex].restart();
		if(!isPlayerDead)
		this.FrameR2[Animindex].restart();
		this.FrameR3[Animindex].restart();
		this.FrameR4[Animindex].restart();
		this.FrameR5[Animindex].restart();
		this.FrameR6[Animindex].restart();
						
		this.FrameL1[Animindex].restart();
		if(!isPlayerDead)
		this.FrameL2[Animindex].restart();
		this.FrameL3[Animindex].restart();
		this.FrameL4[Animindex].restart();
		this.FrameL5[Animindex].restart();
		this.FrameL6[Animindex].restart();
		return(true);
	}
	
	protected void setAnimSpeed(int Animindex,int speed){
		this.FrameR1[Animindex].setSpeed(speed);
		if(!isPlayerDead)
		this.FrameR2[Animindex].setSpeed(speed);
		this.FrameR3[Animindex].setSpeed(speed);
		this.FrameR4[Animindex].setSpeed(speed);
		this.FrameR5[Animindex].setSpeed(speed);
		this.FrameR6[Animindex].setSpeed(speed);
						
		this.FrameL1[Animindex].setSpeed(speed);
		if(!isPlayerDead)
		this.FrameL2[Animindex].setSpeed(speed);
		this.FrameL3[Animindex].setSpeed(speed);
		this.FrameL4[Animindex].setSpeed(speed);
		this.FrameL5[Animindex].setSpeed(speed);
		this.FrameL6[Animindex].setSpeed(speed);
	}
	
	protected void setLooping(int Animindex,boolean logic){
		this.FrameR1[Animindex].setLooping(logic);
		if(!isPlayerDead)
		this.FrameR2[Animindex].setLooping(logic);
		this.FrameR3[Animindex].setLooping(logic);
		this.FrameR4[Animindex].setLooping(logic);
		this.FrameR5[Animindex].setLooping(logic);
		this.FrameR6[Animindex].setLooping(logic);
						
		this.FrameL1[Animindex].setLooping(logic);
		if(!isPlayerDead)
		this.FrameL2[Animindex].setLooping(logic);
		this.FrameL3[Animindex].setLooping(logic);
		this.FrameL4[Animindex].setLooping(logic);
		this.FrameL5[Animindex].setLooping(logic);
		this.FrameL6[Animindex].setLooping(logic);
	}
	
	protected void restart(int Animindex){
		this.FrameR1[Animindex].restart();
		if(!isPlayerDead)
		this.FrameR2[Animindex].restart();
		this.FrameR3[Animindex].restart();
		this.FrameR4[Animindex].restart();
		this.FrameR5[Animindex].restart();
		this.FrameR6[Animindex].restart();
						
		this.FrameL1[Animindex].restart();
		if(!isPlayerDead)
		this.FrameL2[Animindex].restart();
		this.FrameL3[Animindex].restart();
		this.FrameL4[Animindex].restart();
		this.FrameL5[Animindex].restart();
		this.FrameL6[Animindex].restart();
	}
	
	public char getMode(){
		return(mode);
	}
	
	public void setColor(Color color){
		this.color = color;
		frameEf = 20;
	}
	
	protected void drawString(String msg,int x,int y,Font font){	
		Graphics g = new Graphics();
		g.setFont(font);
		
		g.setColor(new Color(0,0,0,200));
		g.drawString(msg,x+1,y+1);
		g.setColor(new Color(255,255,255));
		g.drawString(msg,x,y);
	}
	
}

abstract class CHARACTER extends HCHAR implements Serializable{
	///////////// ATTIBUTE
	public String NAME;
	public byte LV,SPEED,MAPINDEX;
	public int CON,INT,ABI,STR,DEX,AGI,MEM;
	public int ASDMIN,ASDMAX,ASDRATE,DEF,INCONTROL,MAXINCONTROL;
	public int plant_am=200000,gold_am=10000;
	public int family_max;
	public short HP,HPMAX,SP,SPMAX,PW,PWMAX,WP,WPMAX;
	public int _x,_y;
	protected final int y = 445;
	public short ATK,ATKMAX,MATK,MATKMAX;
	protected int alpha,deadTime = 100,tDie=10,atking;
	protected boolean runMode = false;
	protected int frameStop,frameWalk,frameAtk[];
	public char WalkMode = '>';
	public boolean walkMode=false,getFirstMap = false;
	protected int modeIndex,onFrame=10,frame=0,frameSound=30,frames=0;
	public Map _MAP;
	protected Rectangle scope;
	public Map MAP;
	public WOLKERS WORKER = WOLKERS.Villagers;
	public JOBS JOB = JOBS.Archer;
	public POSITION position = POSITION.STOPR;
	protected boolean shoot = false,shooter = false;
	public int walkRun,walkLoad;
	protected float ARmin,ARmax,ARup;
	public int mapX,mapY;
	protected boolean a=false;
	protected short waterMin=0,waterMax=(short)(Math.random()*1000+800);
	protected int Combo,maxCombo;
	protected boolean waeaponAttackComplete,soundDie;
	public boolean warp = false,oneOnDie = false;
	protected int BodyAttkSize;
	public Status[] status = new Status[8];
	public int[] soliderID = {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
	
	///////////// SKILL
	public int MinExpSkill=0,MaxExpSkill=20,skillLv = 3;
	public int MinExpJob=0,MaxExpJob=20,jobLv = 5;
	public boolean zoomSkill = false;
	public boolean fireArrow = false;
	public boolean arrowShot = false;
	public final TSkill[] skill = new TSkill[7];
	
	///////////// ITEM
	public int waterItem = 2,hpItem = 0,mpItem = 0,pwItem = 0;
	public int allowVar = 0;
	public int item[] = {0,0,0,0,0,0,0};
	
	public Timer TIMER = new Timer();
	
	//method
	public abstract void Update();
	public abstract void DrawSelf() throws SlickException,IOException,NotSerializableException;
	public abstract void _LOAD() throws SlickException,IOException,NotSerializableException;
	public abstract int getWeaponATK();
	public abstract Image getWeaponImg() throws SlickException,IOException,NotSerializableException;
	public abstract int getWeaponPrice();
	public abstract int getWeaponLv();
	
	public CHARACTER(){
		NAME = "อาจอง";
		this.toMap((byte)2);
		allowVar=300;
		walkLoad=50;
		CON=1;
		INT=1;
		ABI=1;
		STR=1;
		DEX=1;
		AGI=1;
		MEM=1;
		family_max=50;
		ASDRATE = 100;
		SPEED=2;
		DEF = 0;
		Combo=0;
		maxCombo=5;
		BodyAttkSize = 30;
		waeaponAttackComplete=true;
		ARmax=9.0f;
		
		frameStop = 500;
		frameWalk = 150;
		frameAtk = new int[]{100,120,160,200};
		
		_MAP = Map.valueOf("Map_"+MAPINDEX);
		mapX = -(_MAP.getWidth()*(MAPINDEX))+1024;
		mapY = 0;
		
		//#################### Basic Skill ############################
		
		skill[0] = new TSkill(FileList.iconDir+"icSl2.png","รอยเท้าโบราณ",slType.basic,0,0);
		skill[0].create(0,5);
		skill[0].setComment("เพิ่ม พลังงาน +5 ทุกระดับ");
		
		skill[1] = new TSkill(FileList.iconDir+"icSl3.png","ว่องไวดั่งสายลม",slType.basic,0,0);
		skill[1].create(0,10);
		skill[1].setComment("เพิ่ม agi 1 ทุก ระดับ");
		
		skill[2] = new TSkill(FileList.iconDir+"icSl5.png","กายาทนทาน",slType.basic,0,0);
		skill[2].create(0,10);
		skill[2].setComment("เพิ่ม hp 15 ทุกๆระดับ");
		
		//######################### Status ###############################
		
		status[0] = new Status("Con."		,0,10);
		status[1] = new Status("Int."		,0,10);
		status[2] = new Status("Abi."		,0,10);
		status[3] = new Status("Str."		,0,10);
		status[4] = new Status("Dex."	,0,10);
		status[5] = new Status("Agi."		,0,10);
		status[6] = new Status("Mem."	,0,10);
		status[7] = new Status("Job."		,0,30);
		
	}
	public void setTeeb(int rang){
		this._x = rang;
	}
	public boolean isWarring(){
		return(false);
	}
	public void setRunMode(boolean l){
		this.runMode = l;
	}
	public boolean getRunMode(){
		return(runMode);
	}
	public Rectangle BOUNDINGBOX(){
		Rectangle r = new Rectangle(_x,_y,BodyAttkSize,30);
		
		return(r);
	}
	public void drawBar(int min,int max,int x,int y,int w,int h,Color color) throws SlickException {
		if(min < 0)min=0;
		if(min > max)min=max;
		
		g.setColor(new Color(0,0,0,150));
		g.fillRect(x+2,y+95,w+2,h);
		g.setColor(color);
		g.fillRect(x+3,y+95+1,(int)(w*((double)min/max)),h-2);
		g.setColor(new Color(255,255,255));
	}
	public void Control(GameContainer container,ArrayList<ENEMY> enemy,HOME[] home) throws SlickException,NotSerializableException{
		try{
			Input key = container.getInput();
			soundArcher = new Sound("Resource/audio/bgs/bgs14.wav");
			
			for(PTS psrrr : pts){
				if(psrrr.getMap() == this.MAPINDEX){
					psrrr.update();
				}else psrrr.kill();
			}
			
			for(PTS psrrr : pts){
				if(psrrr.getDie()){
					pts.remove(psrrr);
					break;
				}
			}
			
			
			if(!getDie()){
				if(getRunMode() && (this.PW >= 3)){
					this.SPEED = 4;
					if(walkRun<walkLoad){
						walkRun++;
					}else{
						walkRun=0;
						this.PW-=3;
					}
				}else{
					this.SPEED = 2;
				}		
				if(key.isKeyDown(key.KEY_LEFT)){
					this._x -= this.SPEED;
					this.position = POSITION.WALKL;
					this.WalkMode = '<';
					walkMode=true;
					if(this.getRunMode()){
						if((_x%5)==0){
							pts.add(new PTS(FileList.effectDir+"runsomke.xml",_x+30,_y+70,50,MAPINDEX));
						}
					}
				}
				if(key.isKeyDown(key.KEY_RIGHT)){
					try{
						if(!this.BOUNDINGBOX().intersects(scope)){
							for(HOME h : home){
								if(h != null){
									if(!this.BOUNDINGBOX().intersects(h.BOUNDINGBOX()) || h.die()){
										this._x += this.SPEED;
										this.position = POSITION.WALKR;
										this.WalkMode = '>';
										walkMode=true;
										break;
									}
									break;
								}
							}
						}
					}catch(NullPointerException e){
						scope = new Rectangle(0,0,0,0);
					}
					if(this.getRunMode()){
						if((_x%5)==0){
							pts.add(new PTS(FileList.effectDir+"runsomke.xml",_x,_y+70,50,MAPINDEX));
						}
					}
				}
				if(key.isKeyDown(key.KEY_SPACE) && !walkMode){
					switch(this.JOB){
						case Archer:{
							if(this.allowVar > 0){
								if(ARmin<ARmax){
									ARmin+=ARup;
								}
								
								if((this.WalkMode == '>') && (!walkMode)){
									this.position = POSITION.ATKR2;
								}if((this.WalkMode == '<') && (!walkMode)){
									this.position = POSITION.ATKL2;
								}
								if(!a){
									soundArcher.play();
									a=true;
								}
								if(ARmin>=2){
									this.shoot = true;
									this.shooter = false;
								}
							}else{
								if(waeaponAttackComplete){
									if((this.WalkMode == '>') && (!walkMode)){
										this.position = POSITION.ATKR1;
									}if((this.WalkMode == '<') && (!walkMode)){
										this.position = POSITION.ATKL1;
									}
									
									for(ENEMY e : enemy){
										if(this.BOUNDINGBOX().intersects(e.boundingBoxOfHit())){
											if(e.hp > 0){
												atking = this.getATK()-e.def;
												if(atking > 0){
													e.hp-=this.getATK();
													e.drawDmgFont2(this.getATK()+"",e._x+15,e._y);
												}else{
													e.hp-=1;
													e.drawDmgFont2("!",e._x+15,e._y);
												}
												
												e.addEffect(this.mode,e._x,e._y);
												
												e.setColor(Color.red);
												if(this.WalkMode == '>') e.setTeep(e._x+10);
												if(this.WalkMode == '<') e.setTeep(e._x-10);
												sound[9].play();
												break;
											}
										}
									}
									
									for(HOME e : home){
										if(e != null){
											if(this.BOUNDINGBOX().intersects(e.BOUNDINGBOX())){
												if(e.hp > 0){
													atking = this.getATK()-e.def;
													if(atking > 0){
														e.hp-=this.getATK();
														e.drawDmgFont2(this.getATK()+"",e._x+15,e.y);
													}else{
														e.hp-=1;
														e.drawDmgFont2("!",e._x+15,e.y);
													}
													sound[9].play();
													break;
												}
											}
										}
									}
									
									waeaponAttackComplete=false;
								}
							}
							break;
						}
						case Warrior:{
							if(waeaponAttackComplete){
								if((this.WalkMode == '>') && (!walkMode)){
									this.position = POSITION.valueOf("ATKR"+(int)(Math.random()*2+1));
								}if((this.WalkMode == '<') && (!walkMode)){
									this.position = POSITION.valueOf("ATKL"+(int)(Math.random()*2+1));
								}
								
								for(ENEMY e : enemy){
									if(this.BOUNDINGBOX().intersects(e.boundingBoxOfHit())){
										if(e.hp > 0){
											atking = this.getATK()-e.def;
											if(atking > 0){
												e.hp-=this.getATK();
												e.drawDmgFont2(this.getATK()+"",e._x+15,e._y);
											}else{
												e.hp-=1;
												e.drawDmgFont2("!",e._x+15,e._y);
											}
												
											e.addEffect(this.mode,e._x,e._y);
												
											e.setColor(Color.red);
											if(this.WalkMode == '>') e.setTeep(e._x+10);
											if(this.WalkMode == '<') e.setTeep(e._x-10);
											sound[9].play();
											break;
										}
									}
								}
									
								for(HOME e : home){
									if(e != null){
										if(this.BOUNDINGBOX().intersects(e.BOUNDINGBOX())){
											if(e.hp > 0){
												atking = this.getATK()-e.def;
												if(atking > 0){
													e.hp-=this.getATK();
													e.drawDmgFont2(this.getATK()+"",e._x+15,e.y);
												}else{
													e.hp-=1;
													e.drawDmgFont2("!",e._x+15,e.y);
												}
												sound[9].play();
												break;
											}
										}
									}
								}
								
								waeaponAttackComplete=false;
							}
							break;
						}
						case Wizard:{
							if(waeaponAttackComplete){
								if((this.WalkMode == '>') && (!walkMode)){
									this.position = POSITION.ATKR1;
								}if((this.WalkMode == '<') && (!walkMode)){
									this.position = POSITION.ATKL1;
								}
							
								for(ENEMY e : enemy){
									if(this.BOUNDINGBOX().intersects(e.boundingBoxOfHit())){
										if(e.hp > 0){
											atking = this.getATK()-e.def;
											if(atking > 0){
												e.hp-=this.getATK();
												e.drawDmgFont2(this.getATK()+"",e._x+15,e._y);
											}else{
												e.hp-=1;
												e.drawDmgFont2("!",e._x+15,e._y);
											}
												
											e.addEffect(this.mode,e._x,e._y);
												
											e.setColor(Color.red);
											if(this.WalkMode == '>') e.setTeep(e._x+10);
											if(this.WalkMode == '<') e.setTeep(e._x-10);
											sound[9].play();
											break;
										}
									}
								}
									
								for(HOME e : home){
									if(e != null){
										if(this.BOUNDINGBOX().intersects(e.BOUNDINGBOX())){
											if(e.hp > 0){
												atking = this.getATK()-e.def;
												if(atking > 0){
													e.hp-=this.getATK();
													e.drawDmgFont2(this.getATK()+"",e._x+15,e.y);
												}else{
													e.hp-=1;
													e.drawDmgFont2("!",e._x+15,e.y);
												}
												sound[9].play();
												break;
											}
										}
									}
								}
								
								waeaponAttackComplete=false;
							}
							break;
						}
					}
				}else{
					this.a=false;
					this.shooter = true;
				}
			}
		}catch(Exception e){
			javax.swing.JOptionPane.showMessageDialog(null,e.toString(),"Message",javax.swing.JOptionPane.ERROR_MESSAGE);
		}
	}
	public int getAlpha(){
		if(this.alpha > 0) this.alpha-=10;
		else warp = false;
		return(alpha);
	}
	public void LOADANIM() throws SlickException,IOException,NotSerializableException {
		String animDir = "Resource/animation/Player/";
		int frameDie = 200;
		try{
			effect = new ParticleSystem[]{
				ParticleIO.loadConfiguredSystem(FileList.effectDir+"runsomke.xml"),
			};
		
		
		
			FrameR1[0] = new Animation(
				new Image[]{
					new Image(animDir+"Hand/HandRS_0001"),
					new Image(animDir+"Hand/HandRS_0002"),
					new Image(animDir+"Hand/HandRS_0003"),
				},frameStop
			);
			
			FrameL1[0] = new Animation(
				new Image[]{
					new Image(animDir+"Hand/HandLS_0001"),
					new Image(animDir+"Hand/HandLS_0002"),
					new Image(animDir+"Hand/HandLS_0003"),
				},frameStop
			);
		
			FrameR3[0] = new Animation(
				new Image[]{
					new Image(animDir+"Head/HeadRS_0001"),
					new Image(animDir+"Head/HeadRS_0002"),
					new Image(animDir+"Head/HeadRS_0003"),
				},frameStop
			);
			
			FrameL3[0] = new Animation(
				new Image[]{
					new Image(animDir+"Head/HeadLS_0001"),
					new Image(animDir+"Head/HeadLS_0002"),
					new Image(animDir+"Head/HeadLS_0003"),
				},frameStop
			);
		
			FrameR4[0] = new Animation(
				new Image[]{
					new Image(animDir+"Body/BodyRS_0001"),
					new Image(animDir+"Body/BodyRS_0002"),
					new Image(animDir+"Body/BodyRS_0003"),
				},frameStop
			);
			
			FrameL4[0] = new Animation(
				new Image[]{
					new Image(animDir+"Body/BodyLS_0001"),
					new Image(animDir+"Body/BodyLS_0002"),
					new Image(animDir+"Body/BodyLS_0003"),
				},frameStop
			);
		
			FrameR5[0] = new Animation(
				new Image[]{
					new Image(animDir+"Leg/LegRS_0001"),
					new Image(animDir+"Leg/LegRS_0002"),
					new Image(animDir+"Leg/LegRS_0003"),
				},frameStop
			);
			
			FrameL5[0] = new Animation(
				new Image[]{
					new Image(animDir+"Leg/LegLS_0001"),
					new Image(animDir+"Leg/LegLS_0002"),
					new Image(animDir+"Leg/LegLS_0003"),
				},frameStop
			);
		
			FrameR6[0] = new Animation(
				new Image[]{
					new Image(animDir+"Hand/Hand2RS_0001"),
					new Image(animDir+"Hand/Hand2RS_0002"),
					new Image(animDir+"Hand/Hand2RS_0003"),
				},frameStop
			);
			
			FrameL6[0] = new Animation(
				new Image[]{
					new Image(animDir+"Hand/Hand2LS_0001"),
					new Image(animDir+"Hand/Hand2LS_0002"),
					new Image(animDir+"Hand/Hand2LS_0003"),
				},frameStop
			);
		
			FrameR1[1] = new Animation(
				new Image[]{
					new Image(animDir+"Hand/HandRW_0001"),
					new Image(animDir+"Hand/HandRW_0002"),
					new Image(animDir+"Hand/HandRW_0003"),
					new Image(animDir+"Hand/HandRW_0004"),
					new Image(animDir+"Hand/HandRW_0005"),
				},frameWalk
			);
			
			FrameL1[1] = new Animation(
				new Image[]{
					new Image(animDir+"Hand/HandLW_0001"),
					new Image(animDir+"Hand/HandLW_0002"),
					new Image(animDir+"Hand/HandLW_0003"),
					new Image(animDir+"Hand/HandLW_0004"),
					new Image(animDir+"Hand/HandLW_0005"),
				},frameWalk
			);
		
			FrameR3[1] = new Animation(
				new Image[]{
					new Image(animDir+"Head/HeadRW_0001"),
					new Image(animDir+"Head/HeadRW_0002"),
					new Image(animDir+"Head/HeadRW_0003"),
					new Image(animDir+"Head/HeadRW_0004"),
					new Image(animDir+"Head/HeadRW_0005"),
				},frameWalk
			);
			
			FrameL3[1] = new Animation(
				new Image[]{
					new Image(animDir+"Head/HeadLW_0001"),
					new Image(animDir+"Head/HeadLW_0002"),
					new Image(animDir+"Head/HeadLW_0003"),
					new Image(animDir+"Head/HeadLW_0004"),
					new Image(animDir+"Head/HeadLW_0005"),
				},frameWalk
			);
		
			FrameR4[1] = new Animation(
				new Image[]{
					new Image(animDir+"Body/BodyRW_0001"),
					new Image(animDir+"Body/BodyRW_0002"),
					new Image(animDir+"Body/BodyRW_0003"),
					new Image(animDir+"Body/BodyRW_0004"),
					new Image(animDir+"Body/BodyRW_0005"),
				},frameWalk
			);
			
			FrameL4[1] = new Animation(
				new Image[]{
					new Image(animDir+"Body/BodyLW_0001"),
					new Image(animDir+"Body/BodyLW_0002"),
					new Image(animDir+"Body/BodyLW_0003"),
					new Image(animDir+"Body/BodyLW_0004"),
					new Image(animDir+"Body/BodyLW_0005"),
				},frameWalk
			);
		
			FrameR5[1] = new Animation(
				new Image[]{
					new Image(animDir+"Leg/LegRW_0001"),
					new Image(animDir+"Leg/LegRW_0002"),
					new Image(animDir+"Leg/LegRW_0003"),
					new Image(animDir+"Leg/LegRW_0004"),
					new Image(animDir+"Leg/LegRW_0005"),
				},frameWalk
			);
			
			FrameL5[1] = new Animation(
				new Image[]{
					new Image(animDir+"Leg/LegLW_0001"),
					new Image(animDir+"Leg/LegLW_0002"),
					new Image(animDir+"Leg/LegLW_0003"),
					new Image(animDir+"Leg/LegLW_0004"),
					new Image(animDir+"Leg/LegLW_0005"),
				},frameWalk
			);
		
			FrameR6[1] = new Animation(
				new Image[]{
					new Image(animDir+"Hand/Hand2RW_0001"),
					new Image(animDir+"Hand/Hand2RW_0002"),
					new Image(animDir+"Hand/Hand2RW_0003"),
					new Image(animDir+"Hand/Hand2RW_0004"),
					new Image(animDir+"Hand/Hand2RW_0005"),
				},frameWalk
			);
			
			FrameL6[1] = new Animation(
				new Image[]{
					new Image(animDir+"Hand/Hand2LW_0001"),
					new Image(animDir+"Hand/Hand2LW_0002"),
					new Image(animDir+"Hand/Hand2LW_0003"),
					new Image(animDir+"Hand/Hand2LW_0004"),
					new Image(animDir+"Hand/Hand2LW_0005"),
				},frameWalk
			);
			
			FrameR1[2] = new Animation(
				new Image[]{
					new Image(animDir+"Hand/HaAR1_0001"),
					new Image(animDir+"Hand/HaAR1_0002"),
					new Image(animDir+"Hand/HaAR1_0003"),
					new Image(animDir+"Hand/HaAR1_0003"),
					new Image(animDir+"Hand/HaAR1_0003"),
				},frameAtk[0]
			);
		
			FrameL1[2] = new Animation(
				new Image[]{
					new Image(animDir+"Hand/HaAL1_0001"),
					new Image(animDir+"Hand/HaAL1_0002"),
					new Image(animDir+"Hand/HaAL1_0003"),
					new Image(animDir+"Hand/HaAL1_0003"),
					new Image(animDir+"Hand/HaAL1_0003"),
				},frameAtk[0]
			);
		
			FrameR3[2] = new Animation(
				new Image[]{
					new Image(animDir+"Head/HeAR1_0001"),
					new Image(animDir+"Head/HeAR1_0002"),
					new Image(animDir+"Head/HeAR1_0003"),
					new Image(animDir+"Head/HeAR1_0003"),
					new Image(animDir+"Head/HeAR1_0003"),
				},frameAtk[0]
			);
		
			FrameL3[2] = new Animation(
				new Image[]{
					new Image(animDir+"Head/HeAL1_0001"),
					new Image(animDir+"Head/HeAL1_0002"),
					new Image(animDir+"Head/HeAL1_0003"),
					new Image(animDir+"Head/HeAL1_0003"),
					new Image(animDir+"Head/HeAL1_0003"),
				},frameAtk[0]
			);
		
			FrameR4[2] = new Animation(
				new Image[]{
					new Image(animDir+"Body/ByAR1_0001"),
					new Image(animDir+"Body/ByAR1_0002"),
					new Image(animDir+"Body/ByAR1_0003"),
					new Image(animDir+"Body/ByAR1_0003"),
					new Image(animDir+"Body/ByAR1_0003"),
				},frameAtk[0]
			);
		
			FrameL4[2] = new Animation(
				new Image[]{
					new Image(animDir+"Body/ByAL1_0001"),
					new Image(animDir+"Body/ByAL1_0002"),
					new Image(animDir+"Body/ByAL1_0003"),
					new Image(animDir+"Body/ByAL1_0003"),
					new Image(animDir+"Body/ByAL1_0003"),
				},frameAtk[0]
			);
		
			FrameR5[2] = new Animation(
				new Image[]{
					new Image(animDir+"Leg/LegAR1_0001"),
					new Image(animDir+"Leg/LegAR1_0002"),
					new Image(animDir+"Leg/LegAR1_0003"),
					new Image(animDir+"Leg/LegAR1_0003"),
					new Image(animDir+"Leg/LegAR1_0003"),
				},frameAtk[0]
			);
		
			FrameL5[2] = new Animation(
				new Image[]{
					new Image(animDir+"Leg/LegAL1_0001"),
					new Image(animDir+"Leg/LegAL1_0002"),
					new Image(animDir+"Leg/LegAL1_0003"),
					new Image(animDir+"Leg/LegAL1_0003"),
					new Image(animDir+"Leg/LegAL1_0003"),
				},frameAtk[0]
			);
		
			FrameR6[2] = new Animation(
				new Image[]{
					new Image(animDir+"Hand/Ha2AR1_0001"),
					new Image(animDir+"Hand/Ha2AR1_0002"),
					new Image(animDir+"Hand/Ha2AR1_0003"),
					new Image(animDir+"Hand/Ha2AR1_0003"),
					new Image(animDir+"Hand/Ha2AR1_0003"),
				},frameAtk[0]
			);
		
			FrameL6[2] = new Animation(
				new Image[]{
					new Image(animDir+"Hand/Ha2AL1_0001"),
					new Image(animDir+"Hand/Ha2AL1_0002"),
					new Image(animDir+"Hand/Ha2AL1_0003"),
					new Image(animDir+"Hand/Ha2AL1_0003"),
					new Image(animDir+"Hand/Ha2AL1_0003"),
				},frameAtk[0]
			);
			
			//############################################
			//##########  DEAD
			//#######
			
			FrameR1[6] = new Animation(
				new Image[]{
					new Image(animDir+"Hand/HDR1_0001.png"),
					new Image(animDir+"Hand/HDR1_0002.png"),
					new Image(animDir+"Hand/HDR1_0003.png"),
					new Image(animDir+"Hand/HDR1_0004.png"),
					new Image(animDir+"Hand/HDR1_0005.png"),
					new Image(animDir+"Hand/HDR1_0006.png"),
				},frameDie
			);
			
			FrameL1[6] = new Animation(
				new Image[]{
					new Image(animDir+"Hand/HDL1_0001.png"),
					new Image(animDir+"Hand/HDL1_0002.png"),
					new Image(animDir+"Hand/HDL1_0003.png"),
					new Image(animDir+"Hand/HDL1_0004.png"),
					new Image(animDir+"Hand/HDL1_0005.png"),
					new Image(animDir+"Hand/HDL1_0006.png"),
				},frameDie
			);
			
			FrameR3[6] = new Animation(
				new Image[]{
					new Image(animDir+"Head/HDR_0001.png"),
					new Image(animDir+"Head/HDR_0002.png"),
					new Image(animDir+"Head/HDR_0003.png"),
					new Image(animDir+"Head/HDR_0004.png"),
					new Image(animDir+"Head/HDR_0005.png"),
					new Image(animDir+"Head/HDR_0006.png"),
				},frameDie
			);
			
			FrameL3[6] = new Animation(
				new Image[]{
					new Image(animDir+"Head/HDL_0001.png"),
					new Image(animDir+"Head/HDL_0002.png"),
					new Image(animDir+"Head/HDL_0003.png"),
					new Image(animDir+"Head/HDL_0004.png"),
					new Image(animDir+"Head/HDL_0005.png"),
					new Image(animDir+"Head/HDL_0006.png"),
				},frameDie
			);
			
			FrameR4[6] = new Animation(
				new Image[]{
					new Image(animDir+"Body/HDBR_0001.png"),
					new Image(animDir+"Body/HDBR_0002.png"),
					new Image(animDir+"Body/HDBR_0003.png"),
					new Image(animDir+"Body/HDBR_0004.png"),
					new Image(animDir+"Body/HDBR_0005.png"),
					new Image(animDir+"Body/HDBR_0006.png"),
				},frameDie
			);
			
			FrameL4[6] = new Animation(
				new Image[]{
					new Image(animDir+"Body/HDBL_0001.png"),
					new Image(animDir+"Body/HDBL_0002.png"),
					new Image(animDir+"Body/HDBL_0003.png"),
					new Image(animDir+"Body/HDBL_0004.png"),
					new Image(animDir+"Body/HDBL_0005.png"),
					new Image(animDir+"Body/HDBL_0006.png"),
				},frameDie
			);
			
			FrameR5[6] = new Animation(
				new Image[]{
					new Image(animDir+"Leg/HLDR_0001.png"),
					new Image(animDir+"Leg/HLDR_0002.png"),
					new Image(animDir+"Leg/HLDR_0003.png"),
					new Image(animDir+"Leg/HLDR_0004.png"),
					new Image(animDir+"Leg/HLDR_0005.png"),
					new Image(animDir+"Leg/HLDR_0006.png"),
				},frameDie
			);
			
			FrameL5[6] = new Animation(
				new Image[]{
					new Image(animDir+"Leg/HLDL_0001.png"),
					new Image(animDir+"Leg/HLDL_0002.png"),
					new Image(animDir+"Leg/HLDL_0003.png"),
					new Image(animDir+"Leg/HLDL_0004.png"),
					new Image(animDir+"Leg/HLDL_0005.png"),
					new Image(animDir+"Leg/HLDL_0006.png"),
				},frameDie
			);
			
			FrameR6[6] = new Animation(
				new Image[]{
					new Image(animDir+"Hand/HDR2_0001.png"),
					new Image(animDir+"Hand/HDR2_0002.png"),
					new Image(animDir+"Hand/HDR2_0003.png"),
					new Image(animDir+"Hand/HDR2_0004.png"),
					new Image(animDir+"Hand/HDR2_0005.png"),
					new Image(animDir+"Hand/HDR2_0006.png"),
				},frameDie
			);
			
			FrameL6[6] = new Animation(
				new Image[]{
					new Image(animDir+"Hand/HDL2_0001.png"),
					new Image(animDir+"Hand/HDL2_0002.png"),
					new Image(animDir+"Hand/HDL2_0003.png"),
					new Image(animDir+"Hand/HDL2_0004.png"),
					new Image(animDir+"Hand/HDL2_0005.png"),
					new Image(animDir+"Hand/HDL2_0006.png"),
				},frameDie
			);
		}catch(Exception e){
			javax.swing.JOptionPane.showMessageDialog(null,"HERO LOADANIM"+e.toString());
		}
	}	
	private void partcleUpdate(){
		int Timer = 11;
		effect[0].update(Timer);
	}
	protected void UpdateMap(){
		partcleUpdate();

		if(this._MAP.getIndex() == 1){
			if(this._x <= -13){
				getFirstMap = true;
				toMap((byte)2);
				warpTo((int)(Math.random()*1024),445);
			}else getFirstMap=false;
		}else{
			if(this._x <= -13){
				this.prevMap();
				this._x = 1000;
			}
		}
		
		if(this._MAP.getIndex() == _MAP.getSize()){
			if(this._x >= 980) this._x = 980;
		}else{
			if(this._x >= 1024){
				getFirstMap=false;
				this.nextMap();
				this._x = -10;
			}
		}
	}
	public void UpdateStatus(){
		if(waterMin < waterMax){
			waterMin++;
		}else{
			
			if(WP>0)WP-=3;
			
			waterMax=(short)(Math.random()*1000+800);
			waterMin=0;
		}
		
		INCONTROL = mSolider.size();
		checkStatus();
		if(mSolider.size() < soliderID.length && mSolider.size() > 0){
			soliderID[INCONTROL-1] = mSolider.get(INCONTROL-1).sJob.getID();
		}
	}
	public void warpTo(int _x,int _y){
		this._x = _x;
		this._y = _y;
	}	
	public boolean getDie(){
		if(HP > 0){
			return(false);
		}else{
			if(!soundDie){
				soundD.play();
				soundDie = true;
			}
			
			if(deadTime > 0){
				deadTime--;
			}else{
				tDie--;
				deadTime = 100;
			}
			
			if(tDie == 0){
				isPlayerDead = false;
				oneOnDie=false;
				HP = (short)(10);
				SP = (short)(10);
				PW = (short)(10);
				WP = (short)(10);
				
				if(this.mode == 'R' && HP > 0){
					this.position = POSITION.STOPR;
				}if(this.mode == 'L' && HP > 0){
					this.position = POSITION.STOPL;
				}
				toMap((byte)2);
				warpTo((int)(Math.random()*1024),445);
				tDie = 10;
				deadTime = 100;
			}
			if(isPlayerDead){
				if(!oneOnDie){
					this.restart(6);
					oneOnDie=true;
				}
				if(this.mode == 'R'){
					this.position = POSITION.DEADR;
				}
				if(this.mode == 'L'){
					this.position = POSITION.DEADL;
				}
			}	
			
			return(true);
		}
	}
	public void setScope(Rectangle e){
		this.scope = e;
	}
	protected void nextMap(){
		MAPINDEX++;
		mapX-=_MAP.getWidth();
		this._MAP = MAP.valueOf("Map_"+MAPINDEX);
		alpha = 255;
		warp = true;
	}	
	protected void prevMap(){
		MAPINDEX--;
		mapX+=_MAP.getWidth();
		this._MAP = MAP.valueOf("Map_"+MAPINDEX);
		alpha = 255;
		warp = true;
	}
	protected void toMap(byte index){
		alpha = 255;
		MAPINDEX = index;
		this._MAP = MAP.valueOf("Map_"+MAPINDEX);
		mapX = -(_MAP.getWidth()*(MAPINDEX))+1024;
		_y = y;
	}
	public int getATK(){
		switch(JOB){
			case Archer:  return((int)(ATK+Math.random()*DEX));	
			case Warrior: return((int)(ATK+Math.random()*STR));
			case Wizard:  return((int)(ATK+Math.random()*STR));
			default : return(1);
		}
	}
	public int getMATK(){
		int matk = (int)(this.MATK+Math.random()*INT);
		return(matk);
	}
	public void addGreenFont(String msg){
		msgText.add(new DMGTEXT(this.gFont,msg,CenterOf('x'),CenterOf('y')));
	}
	protected void checkStatus(){
		if(HP > 0){
			soundDie = false;
			isPlayerDead = false;
		}else{
			isPlayerDead = true;
		}
		
		if(this.HP <= 0)
			this.HP = 0;
		if(this.HP >= this.HPMAX)
			this.HP = this.HPMAX;
		if(this.SP <= 0)
			this.SP = 0;
		if(this.SP >= this.SPMAX)
			this.SP = this.SPMAX;
		if(this.PW <= 0)
			this.PW = 0;
		if(this.PW >= this.PWMAX)
			this.PW = this.PWMAX;
		if(this.WP <= 0){
			this.HP = 0;
			this.PW = 0;
		}
		if(this.WP >= this.WPMAX)
			this.WP = this.WPMAX;
			
		if(this.MinExpSkill > this.MaxExpSkill){
			this.MinExpSkill = 0;
			this.skillLv++;
			this.MaxExpSkill+=(int)((this.MaxExpSkill)/3);
		}
		if(this.MinExpJob > this.MaxExpJob){
			this.MinExpJob = 0;
			this.jobLv++;
			this.MaxExpJob+=(int)((this.MaxExpJob)/3);
		}
	}
	public short resetSpeed(){
		short asp = (short)(ASDRATE-ASDMAX);
		return(asp);
	}
	public int CenterOf(char c){
		if(c == 'x' || c == 'X') return(_x+50/2-5);
		if(c == 'y' || c == 'Y') return(_y+50/2-5);
		return(0);
	}
	public void DrawAnimFrame(int index,char c) throws SlickException,IOException,NotSerializableException {
		if(index == 6){
			if(c == 'R'){
				g.drawAnimation(FrameR6[index],this._x,this._y);
				g.drawAnimation(FrameR5[index],this._x,this._y);
				g.drawAnimation(FrameR4[index],this._x,this._y);
				g.drawAnimation(FrameR3[index],this._x,this._y);
				g.drawAnimation(FrameR1[index],this._x,this._y);
			}else{
				g.drawAnimation(FrameL6[index],this._x-30,this._y);
				g.drawAnimation(FrameL5[index],this._x-30,this._y);
				g.drawAnimation(FrameL4[index],this._x-30,this._y);
				g.drawAnimation(FrameL3[index],this._x-30,this._y);
				g.drawAnimation(FrameL1[index],this._x-30,this._y);
			}
		}else{
			if(c == 'R'){
				g.drawAnimation(FrameR6[index],this._x,this._y);
				g.drawAnimation(FrameR5[index],this._x,this._y);
				g.drawAnimation(FrameR4[index],this._x,this._y);
				g.drawAnimation(FrameR3[index],this._x,this._y);
				g.drawAnimation(FrameR2[index],this._x,this._y);
				g.drawAnimation(FrameR1[index],this._x,this._y);
			}else{
				g.drawAnimation(FrameL6[index],this._x,this._y);
				g.drawAnimation(FrameL5[index],this._x,this._y);
				g.drawAnimation(FrameL4[index],this._x,this._y);
				g.drawAnimation(FrameL3[index],this._x,this._y);
				g.drawAnimation(FrameL2[index],this._x,this._y);
				g.drawAnimation(FrameL1[index],this._x,this._y);
			}
		}
	}
	public void drawTong(){
		try{
			g.drawImage(new Image(FileList.imgeDir+"Self.PNG"),_x+10,y-30);
		}catch(SlickException e){
		}
	}
	public Rectangle getBox(){
		Rectangle r = new Rectangle(_x,_y,30,30);
		return(r);
	}
	public void Updating(ArrayList<ENEMY> en,HOME[] enH){
		for(HealSkill hs : healskill){
			if(!hs.getDie()) hs.update(this.CenterOf('x'),this.CenterOf('y')+20);
			else{
				healskill.remove(hs);
				break;
			}
		}
		
		for(Flashright fri : fr){
			if(!fri.getDie()){
				fri.update(this.CenterOf('x'),this.CenterOf('y')+10,WalkMode,this,en,enH);
			}else{
				fr.clear();
				break;
			}
			break;
		}
		
		for(DMGTEXT dmgTEXT : msgText){
			if(!dmgTEXT.getRemove()){
				dmgTEXT.UpdateAndRender();
			}else{
				msgText.remove(dmgTEXT);
				break;
			}
		}
	}
	public void Updating(ArrayList<ENEMY> en){
		for(BuffaloBow b : bb){
			if(!b.getDie())	b.update(en);
			else{
				bb.remove(b);
				break;
			}
		}
		try{
			for(PTS pt : bb.get(0).getPTS()){
				pt.update();
			}
		}catch(Exception e){
		}
		
		
		
		
		for(BigSword b : bs){
			if(!b.getDie()) b.update(en);
			else{
				bs.remove(b);
				break;
			}
		}
	}
}

class Archer extends CHARACTER implements Serializable{
	public Weapon_Bow WEAPON = Weapon_Bow.gradeL;
	public Arrow arrow = Arrow.a;
	public ArrayList<ENEMY> enemy;
	public HOME[] home;
	
	public Archer(){
		INT=10;
		DEX=10;
		AGI=10;
		
		ASDRATE = 30;
		MAXINCONTROL = (INT/10)+2;
		PWMAX = (short)((short)20+(STR)*((short)(5))+(AGI)*((short)(5))+skill[0].getLv()*2);
		HPMAX = (short)((short)80+(CON)*((short)(5))+skill[2].getLv()*15);
		SPMAX = (short)((short)25+(INT)*((short)(5)));
		WPMAX = (short)((short)20+(CON)*((short)(3)));
		HP = HPMAX;
		SP = SPMAX;
		PW = PWMAX;
		WP = WPMAX;
		
		//########################################################
		
		skill[3] = new TSkill(FileList.iconDir+"icSl1.png","แม่นธนู",slType.support,0,0);
		skill[3].create(0,10);
		skill[3].setComment("เพิ่มความรุนแรงในการโจมตี +3 ");
		
		skill[4] = new TSkill(FileList.iconDir+"icSl7.png","ลูกดอกไฟ",slType.comode,3,0);
		skill[4].create(0,5);
		skill[4].setComment("ไฟไหม้เป้าหมาย 10% ของ dmg จำนวน 1 วิต่อ ระดับ");
		skill[4].setHKey('Z');
		
		skill[5] = new TSkill(FileList.iconDir+"icSl8.png","ศรดาวตก",slType.comode,5,0);
		skill[5].create(0,10);
		skill[5].setComment("ปล่อยลูกธนูหลายลูกลอยไปในอากาศพร้อมกัน +1 ทุกระดับ");
		skill[5].setHKey('X');
		skill[5].setCoolDown(300);
		
		try{
			sound[0] = new Sound("Resource/audio/bgs/bgs10.wav");
			sound[1] = new Sound("Resource/audio/bgs/bgs13.wav");
		}catch(SlickException e){}
	}
	
	public int getWeaponATK(){
		return(this.WEAPON.getAtk());
	}
	
	public Image getWeaponImg() throws SlickException,IOException,NotSerializableException{
		return(this.WEAPON.getImg());
	}
	
	public int getWeaponPrice(){
		return(this.WEAPON.getPrice());
	}
	
	public int getWeaponLv(){
		return(this.WEAPON.getLv());
	}
	
	public void DrawSelf() throws SlickException,IOException,NotSerializableException{
		Image shardow = new Image("Resource/img/shardowA");
		Color color = Color.green;
		g.drawImage(shardow,this._x+(shardow.getWidth()-55)/2,this._y+70);
		this.drawString(NAME,_x+10,_y-15,font);
		switch(this.position){
			case STOPR:{
				modeIndex=0;
				mode = 'R';
				waeaponAttackComplete = true;
				walkMode=false;
				this.restart(3);
				break;
			}case STOPL:{
				modeIndex=0;
				mode = 'L';
				waeaponAttackComplete = true;
				walkMode=false;
				this.restart(3);
				break;
			}case WALKR:{
				modeIndex=1;
				mode = 'R';
				if(frame < onFrame) frame++;
				if(frame == onFrame){frame = 0; this.position = POSITION.STOPR;}
				if(frames < frameSound) frames++;
				if(frames == frameSound){frames = 0; sound[0].play();}
				break;
			}case WALKL:{
				modeIndex=1;
				mode = 'L';
				if(frame < onFrame) frame++;
				if(frame == onFrame){frame = 0; this.position = POSITION.STOPL;}
				if(frames < frameSound) frames++;
				if(frames == frameSound){frames = 0; sound[0].play();}
				break;
			}case ATKR1:{
				modeIndex=2;
				mode = 'R';
				if(ASDMIN > 0){
					ASDMIN--;
				}else{
					ASDMIN = resetSpeed();
					sound[1].play();
					this.position = POSITION.STOPR;
				}
				break;
			}case ATKL1:{
				modeIndex=2;
				mode = 'L';
				if(ASDMIN > 0){
					ASDMIN--;
				}else{
					ASDMIN = resetSpeed();
					sound[1].play();
					this.position = POSITION.STOPL;
				}
				break;
			}case ATKR2:{
				modeIndex=3;
				mode = 'R';
				if(FrameR1[3].getFrame()==FrameL1[3].getFrameCount()-1){
					this.setAnimStop(modeIndex);
				}
				break;
			}case ATKL2:{
				modeIndex=3;
				mode = 'L';
				if(FrameL1[3].getFrame()==FrameL1[3].getFrameCount()-1){
					this.setAnimStop(modeIndex);
				}
				break;
			}case SITR:{
				modeIndex=4;
				mode = 'R';
				break;
			}case SITL:{
				modeIndex=4;
				mode = 'L';
				break;
			}case BENDR:{
				modeIndex=5;
				mode = 'R';
				break;
			}case BENDL:{
				modeIndex=5;
				mode = 'L';
				break;
			}case DEADR:{
				modeIndex=6;
				mode = 'R';
				if(FrameR1[modeIndex].getFrame()==FrameR1[modeIndex].getFrameCount()-1){
					this.setAnimStop(modeIndex);
				}
				break;
			}case DEADL:{
				modeIndex=6;
				mode = 'L';
				if(FrameL1[modeIndex].getFrame()==FrameL1[modeIndex].getFrameCount()-1){
					this.setAnimStop(modeIndex);
				}
				break;
			}
		}
		DrawAnimFrame(modeIndex,mode);
		
		drawBar(WP,WPMAX,_x+3,_y-14,45,3,new Color(0,150,255));
		drawBar(HP,HPMAX,_x+3,_y-11,45,6,color);
		drawBar(PW,PWMAX,_x+3,_y-5,45,6,new Color(200,255,200));
		drawBar((int)ARmin,(int)ARmax,_x+3,_y,45,5,new Color(255,180,180));
	}
	
	public void _LOAD() throws SlickException,IOException,NotSerializableException{
		String packDir = "Resource/animation/Player/weapon/";
		String animDir = "Resource/animation/Player/";
		
		FrameR1[3] = new Animation(
			new Image[]{
				new Image(animDir+"Hand/haBAR2_0001"),
				new Image(animDir+"Hand/haBAR2_0002"),
				new Image(animDir+"Hand/haBAR2_0003"),
			},frameAtk[3]
		);
	
		FrameL1[3] = new Animation(
			new Image[]{
				new Image(animDir+"Hand/haBAL2_0001"),
				new Image(animDir+"Hand/haBAL2_0002"),
				new Image(animDir+"Hand/haBAL2_0003"),
			},frameAtk[3]
		);
	
		FrameR3[3] = new Animation(
			new Image[]{
				new Image(animDir+"Head/heBAR2_0001"),
				new Image(animDir+"Head/heBAR2_0002"),
				new Image(animDir+"Head/heBAR2_0003"),
			},frameAtk[3]
		);
	
		FrameL3[3] = new Animation(
			new Image[]{
				new Image(animDir+"Head/heBAL2_0001"),
				new Image(animDir+"Head/heBAL2_0002"),
				new Image(animDir+"Head/heBAL2_0003"),
			},frameAtk[3]
		);
	
		FrameR4[3] = new Animation(
			new Image[]{
				new Image(animDir+"Body/ByBAR2_0001"),
				new Image(animDir+"Body/ByBAR2_0002"),
				new Image(animDir+"Body/ByBAR2_0003"),
			},frameAtk[3]
		);
	
		FrameL4[3] = new Animation(
			new Image[]{
				new Image(animDir+"Body/ByBAL2_0001"),
				new Image(animDir+"Body/ByBAL2_0002"),
				new Image(animDir+"Body/ByBAL2_0003"),
			},frameAtk[3]
		);
	
		FrameR5[3] = new Animation(
			new Image[]{
				new Image(animDir+"Leg/LeBAR2_0001"),
				new Image(animDir+"Leg/LeBAR2_0002"),
				new Image(animDir+"Leg/LeBAR2_0003"),
			},frameAtk[3]
		);
	
		FrameL5[3] = new Animation(
			new Image[]{
				new Image(animDir+"Leg/LeBAL2_0001"),
				new Image(animDir+"Leg/LeBAL2_0002"),
				new Image(animDir+"Leg/LeBAL2_0003"),
			},frameAtk[3]
		);
	
		FrameR6[3] = new Animation(
			new Image[]{
				new Image(animDir+"Hand/ha2BAR2_0001"),
				new Image(animDir+"Hand/ha2BAR2_0002"),
				new Image(animDir+"Hand/ha2BAR2_0003"),
			},frameAtk[3]
		);
	
		FrameL6[3] = new Animation(
			new Image[]{
				new Image(animDir+"Hand/ha2BAL2_0001"),
				new Image(animDir+"Hand/ha2BAL2_0002"),
				new Image(animDir+"Hand/ha2BAL2_0003"),
			},frameAtk[3]
		);
		
		
		FrameR2[0] = new Animation(
			new Image[]{
				new Image(packDir+"BSR"+WEAPON.getLv()+"_0001"),
				new Image(packDir+"BSR"+WEAPON.getLv()+"_0002"),
				new Image(packDir+"BSR"+WEAPON.getLv()+"_0003"),
			},frameStop
		);
		FrameL2[0] = new Animation(
			new Image[]{
				new Image(packDir+"BSL"+WEAPON.getLv()+"_0001"),
				new Image(packDir+"BSL"+WEAPON.getLv()+"_0002"),
				new Image(packDir+"BSL"+WEAPON.getLv()+"_0003"),
			},frameStop
		);
	
		FrameR2[1] = new Animation(
			new Image[]{
				new Image(packDir+"BWR"+WEAPON.getLv()+"_0001"),
				new Image(packDir+"BWR"+WEAPON.getLv()+"_0002"),
				new Image(packDir+"BWR"+WEAPON.getLv()+"_0003"),
				new Image(packDir+"BWR"+WEAPON.getLv()+"_0004"),
				new Image(packDir+"BWR"+WEAPON.getLv()+"_0005"),
			},frameWalk
		);
		FrameL2[1] = new Animation(
			new Image[]{
				new Image(packDir+"BWL"+WEAPON.getLv()+"_0001"),
				new Image(packDir+"BWL"+WEAPON.getLv()+"_0002"),
				new Image(packDir+"BWL"+WEAPON.getLv()+"_0003"),
				new Image(packDir+"BWL"+WEAPON.getLv()+"_0004"),
				new Image(packDir+"BWL"+WEAPON.getLv()+"_0005"),
			},frameWalk
		);
	
		FrameR2[2] = new Animation(
			new Image[]{
				new Image(packDir+"B"+WEAPON.getLv()+"AR1_0001"),
				new Image(packDir+"B"+WEAPON.getLv()+"AR1_0002"),
				new Image(packDir+"B"+WEAPON.getLv()+"AR1_0003"),
				new Image(packDir+"B"+WEAPON.getLv()+"AR1_0003"),
				new Image(packDir+"B"+WEAPON.getLv()+"AR1_0003"),
			},frameAtk[0]
		);
		FrameL2[2] = new Animation(
			new Image[]{
				new Image(packDir+"B"+WEAPON.getLv()+"AL1_0001"),
				new Image(packDir+"B"+WEAPON.getLv()+"AL1_0002"),
				new Image(packDir+"B"+WEAPON.getLv()+"AL1_0003"),
				new Image(packDir+"B"+WEAPON.getLv()+"AL1_0003"),
				new Image(packDir+"B"+WEAPON.getLv()+"AL1_0003"),
			},frameAtk[0]
		);
		
		FrameR2[3] = new Animation(
			new Image[]{
				new Image(packDir+"B"+WEAPON.getLv()+"AR2_0001"),
				new Image(packDir+"B"+WEAPON.getLv()+"AR2_0002"),
				new Image(packDir+"B"+WEAPON.getLv()+"AR2_0003"),
			},frameAtk[3]
		);
		FrameL2[3] = new Animation(
			new Image[]{
				new Image(packDir+"B"+WEAPON.getLv()+"AL2_0001"),
				new Image(packDir+"B"+WEAPON.getLv()+"AL2_0002"),
				new Image(packDir+"B"+WEAPON.getLv()+"AL2_0003"),
			},frameAtk[3]
		);
	}
	
	public void arrowAddTarget(ArrayList<ENEMY> enemy,HOME[] h){
		this.enemy = enemy;
		this.home = h;
	}
	
	public void Update(){
		this.UpdateMap();
		
		ATK = (short)((WEAPON.getAtk()+arrow.getATK()/2)+(skill[3].getLv()*3));
		ATKMAX = (short)(((WEAPON.getAtk()+DEX)+arrow.getATK()/2)+(skill[3].getLv()*3));
		MATK = (short)(INT);
		MATKMAX = (short)(INT*2);
		
		int atk = (int)((ATK)+Math.random()*DEX);
		
		HPMAX = (short)((short)80+(CON)*((short)(5))+skill[2].getLv()*15);
		
		ASDMAX = (short)(AGI/5);
		ASDMIN = resetSpeed();
		ARup = (float)((float)AGI/10.0f)/10.0f+0.1f;
		
		PWMAX = (short)((short)20+(STR)*((short)(5))+(AGI)*((short)(5))+skill[0].getLv()*2);
		
		if(this.shooter && this.shoot && (JOB == JOBS.Archer)){
			if(arrowShot){
				for(int i = 0;i<(skill[5].getLv()+2);i++){
					if(this.allowVar >= i){
						arrowList.add(new TARROW(mode,(float)(ARmin),atk,_x,_y-(int)(Math.random()*100),fireArrow,skill[4].getLv()));
						this.allowVar--;
					}
				}
				arrowShot = false;
				fireArrow = false;
			}else{
				arrowList.add(new TARROW(mode,ARmin,atk,_x,_y,fireArrow,skill[4].getLv()));
				fireArrow = false;
				this.allowVar--;
			}
				
			setAnimReset(3);
			if(this.WalkMode == '>')this.position = POSITION.WALKR;
			if(this.WalkMode == '<')this.position = POSITION.WALKL;
			this.ARmin = 0;
			this.shoot=false;
		}

		for(TARROW arrows : arrowList){
			arrows.drawSelf();
			arrows.Update();
			arrows.setTarget(enemy,home);
			
			if(arrows.getRemove()){
				arrowList.remove(arrows);
				break;
			}
		}
		
		//#### skill zone
		if(this.skill[3].getLv() >= 1){
			zoomSkill = true;
		}
	}
}

class Warrior extends CHARACTER implements Serializable{
	public Weapon_Sword WEAPON = Weapon_Sword.gradeL;
	
	public Warrior(){
		CON=10;
		STR=10;
		AGI=10;
		ASDRATE = 30;
		MAXINCONTROL = (INT/10)+2;
		BodyAttkSize = 45;
		ASDMIN = resetSpeed();
		SPMAX = (short)((short)25+(INT)*((short)(5)));
		PWMAX = (short)((short)20+(STR)*((short)(5))+(AGI)*((short)(5))+skill[0].getLv()*2);
		HPMAX = (short)((short)80+(CON)*((short)(5))+skill[2].getLv()*15);
		WPMAX = (short)((short)20+(CON)*((short)(3)));
		HP = HPMAX;
		SP = SPMAX;
		PW = PWMAX;
		WP = WPMAX;
		
		skill[3] = new TSkill(FileList.iconDir+"icSl4.png","การใช้ดาบ",slType.support,0,0);
		skill[3].create(0,10);
		skill[3].setComment("เพิ่มพลังโจมตีด้วยดาบ +3 ทุกระดับ");
		
		skill[4] = new TSkill(FileList.iconDir+"icSl10.png","โล่นักรบ",slType.active,0,10);
		skill[4].create(0,10);
		skill[4].setComment("เพิ่มพลังป้องกัน 1 ทุก 2 ระดับและสามารถโยนโล่ใส่เป่าหมายได้");
		skill[4].setCoolDown(200);
		skill[4].setHKey('Z');
		
		skill[5] = new TSkill(FileList.iconDir+"icSl11.png","บ้าคลั่ง",slType.active,0,15);
		skill[5].create(0,5);
		skill[5].setComment("ฟาดเงาดาบออกไปใส่เป้าหมาย ด้วยความรุนแรง");
		skill[5].setCoolDown(300);
		skill[5].setHKey('X');
		
		try{
			sound[0] = new Sound("Resource/audio/bgs/bgs10.wav");
			sound[1] = new Sound("Resource/audio/bgs/bgs13.wav");
			sound[2] = new Sound("Resource/audio/bgs/bgs16.wav");
		}catch(SlickException e){}
	}
	
	public int getWeaponATK(){
		return(this.WEAPON.getAtk());
	}
	
	public Image getWeaponImg() throws SlickException,IOException,NotSerializableException {
		return(this.WEAPON.getImg());
	}
	
	public int getWeaponPrice(){
		return(this.WEAPON.getPrice());
	}
	
	public int getWeaponLv(){
		return(this.WEAPON.getLv());
	}
	
	public void DrawSelf() throws SlickException,IOException,NotSerializableException{
		Image shardow = new Image("Resource/img/shardowA");
		Color color = Color.green;
		g.drawImage(shardow,this._x+(shardow.getWidth()-55)/2,this._y+70);
		int kFrame=30;
		this.drawString(NAME,_x+10,_y-15,font);
		switch(this.position){
			case STOPR:{
				modeIndex=0;
				waeaponAttackComplete=true;
				mode = 'R';
				walkMode=false;
				break;
			}case STOPL:{
				modeIndex=0;
				waeaponAttackComplete=true;
				mode = 'L';
				walkMode=false;
				break;
			}case WALKR:{
				modeIndex=1;
				mode = 'R';
				if(frame < onFrame) frame++;
				if(frame == onFrame){
					frame = 0;
					this.position = POSITION.STOPR;
				}
				if(frames < frameSound) frames++;
				if(frames == frameSound){
					frames = 0;
					sound[0].play();
				}
				break;
			}case WALKL:{
				modeIndex=1;
				mode = 'L';
				if(frame < onFrame) frame++;
				if(frame == onFrame){
					frame = 0;
					this.position = POSITION.STOPL;
				}
				if(frames < frameSound) frames++;
				if(frames == frameSound){
					frames = 0;
					sound[0].play();
				}
				break;
			}case ATKR1:{
				modeIndex=2;
				mode = 'R';
				if(ASDMIN > 0){
					ASDMIN--;
				}else{
					ASDMIN = resetSpeed();
					sound[1].play();
					this.position = POSITION.STOPR;
				}
				break;
			}case ATKL1:{
				modeIndex=2;
				mode = 'L';
				if(ASDMIN > 0){
					ASDMIN--;
				}else{
					ASDMIN = resetSpeed();
					sound[1].play();
					this.position = POSITION.STOPL;
				}
				break;
			}case ATKR2:{
				modeIndex=3;
				mode = 'R';
				if(ASDMIN > 0){
					ASDMIN--;
				}else{
					ASDMIN = resetSpeed();
					sound[2].play();
					this.position = POSITION.STOPR;
				}
				break;
			}case ATKL2:{
				modeIndex=3;
				mode = 'L';
				if(ASDMIN > 0){
					ASDMIN--;
				}else{
					ASDMIN = resetSpeed();
					sound[2].play();
					this.position = POSITION.STOPL;
				}
				break;
			}case SITR:{
				modeIndex=4;
				mode = 'R';
				break;
			}case SITL:{
				modeIndex=4;
				mode = 'L';
				break;
			}case BENDR:{
				modeIndex=5;
				mode = 'R';
				break;
			}case BENDL:{
				modeIndex=5;
				mode = 'L';
				break;
			}case DEADR:{
				modeIndex=6;
				mode = 'R';
				if(FrameR1[modeIndex].getFrame()==FrameR1[modeIndex].getFrameCount()-1){
					this.setAnimStop(modeIndex);
				}
				break;
			}case DEADL:{
				modeIndex=6;
				mode = 'L';
				if(FrameL1[modeIndex].getFrame()==FrameL1[modeIndex].getFrameCount()-1){
					this.setAnimStop(modeIndex);
				}
				break;
			}
		}
		DrawAnimFrame(modeIndex,mode);
		drawBar(WP,WPMAX,_x+3,_y-14,45,3,new Color(0,150,255));
		drawBar(HP,HPMAX,_x+3,_y-11,45,6,color);
		drawBar(PW,PWMAX,_x+3,_y-5,45,6,new Color(200,255,200));
	}
	
	//Override
	public void _LOAD() throws SlickException,IOException,NotSerializableException{
		String packDir = "Resource/animation/Player/weapon/";
		String animDir = "Resource/animation/Player/";
		
		FrameR1[3] = new Animation(
			new Image[]{
				new Image(animDir+"Hand/HaAR2_0001"),
				new Image(animDir+"Hand/HaAR2_0002"),
				new Image(animDir+"Hand/HaAR2_0002"),
			},frameAtk[0]
		);
	
		FrameL1[3] = new Animation(
			new Image[]{
				new Image(animDir+"Hand/HaAL2_0001"),
				new Image(animDir+"Hand/HaAL2_0002"),
				new Image(animDir+"Hand/HaAL2_0002"),
			},frameAtk[0]
		);
		
		FrameR3[3] = new Animation(
			new Image[]{
				new Image(animDir+"Head/HeAR2_0001"),
				new Image(animDir+"Head/HeAR2_0002"),
				new Image(animDir+"Head/HeAR2_0002"),
			},frameAtk[0]
		);
	
		FrameL3[3] = new Animation(
			new Image[]{
				new Image(animDir+"Head/HeAL2_0001"),
				new Image(animDir+"Head/HeAL2_0002"),
				new Image(animDir+"Head/HeAL2_0002"),
			},frameAtk[0]
		);
	
		FrameR4[3] = new Animation(
			new Image[]{
				new Image(animDir+"Hand/Ha2AR_0001"),
				new Image(animDir+"Hand/Ha2AR_0002"),
				new Image(animDir+"Hand/Ha2AR_0002"),
			},frameAtk[0]
		);
	
		FrameL4[3] = new Animation(
			new Image[]{
				new Image(animDir+"Hand/Ha2AL_0001"),
				new Image(animDir+"Hand/Ha2AL_0002"),
				new Image(animDir+"Hand/Ha2AL_0002"),
			},frameAtk[0]
		);

		FrameR5[3] = new Animation(
			new Image[]{
				new Image(animDir+"Body/byAR2_0001"),
				new Image(animDir+"Body/byAR2_0002"),
				new Image(animDir+"Body/byAR2_0002"),
			},frameAtk[0]
		);
	
		FrameL5[3] = new Animation(
			new Image[]{
				new Image(animDir+"Body/byAL2_0001"),
				new Image(animDir+"Body/byAL2_0002"),
				new Image(animDir+"Body/byAL2_0002"),
			},frameAtk[0]
		);
	
	
		FrameR6[3] = new Animation(
			new Image[]{
				new Image(animDir+"Leg/LeAR2_0001"),
				new Image(animDir+"Leg/LeAR2_0002"),
				new Image(animDir+"Leg/LeAR2_0002"),
			},frameAtk[0]
		);
	
		FrameL6[3] = new Animation(
			new Image[]{
				new Image(animDir+"Leg/LeAL2_0001"),
				new Image(animDir+"Leg/LeAL2_0002"),
				new Image(animDir+"Leg/LeAL2_0002"),
			},frameAtk[0]
		);
	

		FrameR1[0] = new Animation(
			new Image[]{
				new Image(animDir+"Hand/HandRWS_0001"),
				new Image(animDir+"Hand/HandRWS_0002"),
				new Image(animDir+"Hand/HandRWS_0003"),
			},frameStop
		);
		
		FrameL1[0] = new Animation(
			new Image[]{
				new Image(animDir+"Hand/HandLWS_0001"),
				new Image(animDir+"Hand/HandLWS_0002"),
				new Image(animDir+"Hand/HandLWS_0003"),
			},frameStop
		);
	
		FrameR1[1] = new Animation(
			new Image[]{
				new Image(animDir+"Hand/Hand_SW_R_0001"),
				new Image(animDir+"Hand/Hand_SW_R_0002"),
				new Image(animDir+"Hand/Hand_SW_R_0003"),
				new Image(animDir+"Hand/Hand_SW_R_0004"),
				new Image(animDir+"Hand/Hand_SW_R_0005"),
			},frameStop
		);
		
		FrameL1[1] = new Animation(
			new Image[]{
				new Image(animDir+"Hand/Hand_SW_L_0001"),
				new Image(animDir+"Hand/Hand_SW_L_0002"),
				new Image(animDir+"Hand/Hand_SW_L_0003"),
				new Image(animDir+"Hand/Hand_SW_L_0004"),
				new Image(animDir+"Hand/Hand_SW_L_0005"),
			},frameStop
		);
	
		FrameR2[0] = new Animation(
			new Image[]{
				new Image(packDir+"WS"+WEAPON.getLv()+"_RS_0001"),
				new Image(packDir+"WS"+WEAPON.getLv()+"_RS_0002"),
				new Image(packDir+"WS"+WEAPON.getLv()+"_RS_0003"),
			},frameStop
		);
		FrameL2[0] = new Animation(
			new Image[]{
				new Image(packDir+"WS"+WEAPON.getLv()+"_LS_0001"),
				new Image(packDir+"WS"+WEAPON.getLv()+"_LS_0002"),
				new Image(packDir+"WS"+WEAPON.getLv()+"_LS_0003"),
			},frameStop
		);
	
		FrameR2[1] = new Animation(
			new Image[]{
				new Image(packDir+"SW"+WEAPON.getLv()+"_RW_0001"),
				new Image(packDir+"SW"+WEAPON.getLv()+"_RW_0002"),
				new Image(packDir+"SW"+WEAPON.getLv()+"_RW_0003"),
				new Image(packDir+"SW"+WEAPON.getLv()+"_RW_0004"),
				new Image(packDir+"SW"+WEAPON.getLv()+"_RW_0005"),
			},frameWalk
		);
		FrameL2[1] = new Animation(
			new Image[]{
				new Image(packDir+"SW"+WEAPON.getLv()+"_LW_0001"),
				new Image(packDir+"SW"+WEAPON.getLv()+"_LW_0002"),
				new Image(packDir+"SW"+WEAPON.getLv()+"_LW_0003"),
				new Image(packDir+"SW"+WEAPON.getLv()+"_LW_0004"),
				new Image(packDir+"SW"+WEAPON.getLv()+"_LW_0005"),
			},frameWalk
		);
	
		FrameR2[2] = new Animation(
			new Image[]{
				new Image(packDir+"S"+WEAPON.getLv()+"AR1_0001"),
				new Image(packDir+"S"+WEAPON.getLv()+"AR1_0002"),
				new Image(packDir+"S"+WEAPON.getLv()+"AR1_0003"),
				new Image(packDir+"S"+WEAPON.getLv()+"AR1_0003"),
				new Image(packDir+"S"+WEAPON.getLv()+"AR1_0003"),
			},frameAtk[0]
		);
		FrameL2[2] = new Animation(
			new Image[]{
				new Image(packDir+"S"+WEAPON.getLv()+"AL1_0001"),
				new Image(packDir+"S"+WEAPON.getLv()+"AL1_0002"),
				new Image(packDir+"S"+WEAPON.getLv()+"AL1_0003"),
				new Image(packDir+"S"+WEAPON.getLv()+"AL1_0003"),
				new Image(packDir+"S"+WEAPON.getLv()+"AL1_0003"),
			},frameAtk[0]
		);
	
		FrameR2[3] = new Animation(
			new Image[]{
				new Image(packDir+"Sw"+WEAPON.getLv()+"AR2_0001"),
				new Image(packDir+"Sw"+WEAPON.getLv()+"AR2_0002"),
				new Image(packDir+"Sw"+WEAPON.getLv()+"AR2_0002"),
			},frameAtk[0]
		);
	
		FrameL2[3] = new Animation(
			new Image[]{
				new Image(packDir+"Sw"+WEAPON.getLv()+"AL2_0001"),
				new Image(packDir+"Sw"+WEAPON.getLv()+"AL2_0002"),
				new Image(packDir+"Sw"+WEAPON.getLv()+"AL2_0002"),
			},frameAtk[0]
		);

	}
	public void Update(){
		this.UpdateMap();
		ASDMAX = (short)(AGI/5);
		ATK = (short)(WEAPON.getAtk()+(skill[3].getLv()*3));
		ATKMAX = (short)(WEAPON.getAtk()+STR+(skill[3].getLv()*3));
		MATK = (short)(INT);
		PWMAX = (short)((short)20+(STR)*((short)(5))+(AGI)*((short)(5))+skill[0].getLv()*2);
		HPMAX = (short)((short)80+(CON)*((short)(5))+skill[2].getLv()*15);
		DEF = skill[4].getLv()/2;
	}
}

class Wizard extends CHARACTER implements Serializable{
	public Weapon_Wizard WEAPON = Weapon_Wizard.gradeL;
	
	public Wizard(){
		INT=10;
		ABI=10;
		MEM=10;
		MAXINCONTROL = (INT/10)+2;
		ASDRATE = 30;
		
		//################## SKILL MANAGEMENT ###################
		
		skill[3] = new TSkill(FileList.iconDir+"icSl0.png","พ่นไฟ",slType.active,8,0);
		skill[3].create(0,5);
		skill[3].setHKey('Z');
		skill[3].setComment("พ่นไฟ เมื่อศัตรูถูกเผาไหมจะสร้างความเสียหาย เท่ากับ matk ตามระดับทักษะ");
		skill[3].setCoolDown(50);
		
		skill[4] = new TSkill(FileList.iconDir+"icSl6.png","ฟื้นฟู",slType.active,7,0);
		skill[4].create(0,10);
		skill[4].setHKey('X');
		skill[4].setComment("ฟื้นฟูพลังชีวิต ของ unit รอบๆตัว +25% เท่าของ matk ต่อระดับ ");
		skill[4].setCoolDown(100);
		
		skill[5] = new TSkill(FileList.iconDir+"icSl9.png","ปลุก ควายธนู",slType.active,20,0);
		skill[5].create(0,5);
		skill[5].setHKey('C');
		skill[5].setComment("ปลุก ควายธนู พุ่งชนเป้าหมาย ความรุนแรง เท่ากับ Matk");
		skill[5].setCoolDown(700);
		
		PWMAX = (short)((short)20+(STR)*((short)(5))+(AGI)*((short)(5))+skill[0].getLv()*2);
		HPMAX = (short)((short)80+(CON)*((short)(5))+skill[2].getLv()*15);
		SPMAX = (short)((short)25+(INT)*((short)(5)));
		WPMAX = (short)((short)20+(CON)*((short)(3)));
		HP = HPMAX;
		SP = SPMAX;
		PW = PWMAX;
		WP = WPMAX;
		ASDMIN = resetSpeed();
		
		try{
			sound[0] = new Sound("Resource/audio/bgs/bgs10.wav");
			sound[1] = new Sound("Resource/audio/bgs/bgs13.wav");
		}catch(SlickException e){}
	}
	
	public int getWeaponATK(){
		return(this.WEAPON.getAtk());
	}
	
	public Image getWeaponImg() throws SlickException,IOException,NotSerializableException {
		return(this.WEAPON.getImg());
	}
	
	public int getWeaponPrice(){
		return(this.WEAPON.getPrice());
	}
	
	public int getWeaponLv(){
		return(this.WEAPON.getLv());
	}
	
	public void DrawSelf() throws SlickException,IOException,NotSerializableException{
		Image shardow = new Image("Resource/img/shardowA");
		Color color = Color.green;
		g.drawImage(shardow,this._x+(shardow.getWidth()-55)/2,this._y+70);
		this.drawString(NAME,_x+10,_y-15,font);
		switch(this.position){
			case STOPR:{
				modeIndex=0;
				mode = 'R';
				waeaponAttackComplete=true;
				walkMode=false;
				break;
			}case STOPL:{
				modeIndex=0;
				mode = 'L';
				waeaponAttackComplete=true;
				walkMode=false;
				break;
			}case WALKR:{
				modeIndex=1;
				mode = 'R';
				if(frame < onFrame) frame++;
				if(frame == onFrame){
					frame = 0;
					this.position = POSITION.STOPR;
				}
				if(frames < frameSound) frames++;
				if(frames == frameSound){
					frames = 0;
					sound[0].play();
				}
				break;
			}case WALKL:{
				modeIndex=1;
				mode = 'L';
				if(frame < onFrame) frame++;
				if(frame == onFrame){
					frame = 0;
					this.position = POSITION.STOPL;
				}
				if(frames < frameSound) frames++;
				if(frames == frameSound){
					frames = 0;
					sound[0].play();
				}
				break;
			}case ATKR1:{
				modeIndex=2;
				mode = 'R';
				
				if(ASDMIN > 0){
					ASDMIN--;
				}else{
					ASDMIN = resetSpeed();
					this.position = POSITION.STOPR;
				}
				if(frames < frameSound) frames++;
				if(frames == frameSound){frames = 0; sound[1].play();}
				break;
			}case ATKL1:{
				modeIndex=2;
				mode = 'L';
				if(ASDMIN > 0){
					ASDMIN--;
				}else{
					ASDMIN = resetSpeed();
					this.position = POSITION.STOPL;
				}
				break;
			}case ATKR2:{
				modeIndex=3;
				mode = 'R';
				break;
			}case ATKL2:{
				modeIndex=3;
				mode = 'L';
				break;
			}case SITR:{
				modeIndex=4;
				mode = 'R';
				break;
			}case SITL:{
				modeIndex=4;
				mode = 'L';
				break;
			}case BENDR:{
				modeIndex=5;
				mode = 'R';
				break;
			}case BENDL:{
				modeIndex=5;
				mode = 'L';
				break;
			}case DEADR:{
				modeIndex=6;
				mode = 'R';
				if(FrameR1[modeIndex].getFrame()==FrameR1[modeIndex].getFrameCount()-1){
					this.setAnimStop(modeIndex);
				}
				break;
			}case DEADL:{
				modeIndex=6;
				mode = 'L';
				if(FrameL1[modeIndex].getFrame()==FrameL1[modeIndex].getFrameCount()-1){
					this.setAnimStop(modeIndex);
				}
				break;
			}
		}
		
		DrawAnimFrame(modeIndex,mode);
		drawBar(WP,WPMAX,_x+3,_y-14,45,3,new Color(0,150,255));
		drawBar(HP,HPMAX,_x+3,_y-11,45,6,color);
		drawBar(PW,PWMAX,_x+3,_y-5,45,6,new Color(200,255,200));
	}
	//Override
	public void _LOAD() throws SlickException,IOException,NotSerializableException{
		String packDir = "Resource/animation/Player/weapon/";
		
		FrameR2[0] = new Animation(
			new Image[]{
				new Image(packDir+"WSR"+WEAPON.getLv()+"_0001"),
				new Image(packDir+"WSR"+WEAPON.getLv()+"_0002"),
				new Image(packDir+"WSR"+WEAPON.getLv()+"_0003"),
			},frameStop
		);
		FrameL2[0] = new Animation(
			new Image[]{
				new Image(packDir+"WSL"+WEAPON.getLv()+"_0001"),
				new Image(packDir+"WSL"+WEAPON.getLv()+"_0002"),
				new Image(packDir+"WSL"+WEAPON.getLv()+"_0003"),
			},frameStop
		);
	
		FrameR2[1] = new Animation(
			new Image[]{
				new Image(packDir+"WWR"+WEAPON.getLv()+"_0001"),
				new Image(packDir+"WWR"+WEAPON.getLv()+"_0002"),
				new Image(packDir+"WWR"+WEAPON.getLv()+"_0003"),
				new Image(packDir+"WWR"+WEAPON.getLv()+"_0004"),
				new Image(packDir+"WWR"+WEAPON.getLv()+"_0005"),
			},frameWalk
		);
		FrameL2[1] = new Animation(
			new Image[]{
				new Image(packDir+"WWL"+WEAPON.getLv()+"_0001"),
				new Image(packDir+"WWL"+WEAPON.getLv()+"_0002"),
				new Image(packDir+"WWL"+WEAPON.getLv()+"_0003"),
				new Image(packDir+"WWL"+WEAPON.getLv()+"_0004"),
				new Image(packDir+"WWL"+WEAPON.getLv()+"_0005"),
			},frameWalk
		);
	
		FrameR2[2] = new Animation(
			new Image[]{
				new Image(packDir+"W"+WEAPON.getLv()+"AR1_0001"),
				new Image(packDir+"W"+WEAPON.getLv()+"AR1_0002"),
				new Image(packDir+"W"+WEAPON.getLv()+"AR1_0003"),
				new Image(packDir+"W"+WEAPON.getLv()+"AR1_0003"),
				new Image(packDir+"W"+WEAPON.getLv()+"AR1_0003"),
			},frameAtk[0]
		);
		FrameL2[2] = new Animation(
			new Image[]{
				new Image(packDir+"W"+WEAPON.getLv()+"AL1_0001"),
				new Image(packDir+"W"+WEAPON.getLv()+"AL1_0002"),
				new Image(packDir+"W"+WEAPON.getLv()+"AL1_0003"),
				new Image(packDir+"W"+WEAPON.getLv()+"AL1_0003"),
				new Image(packDir+"W"+WEAPON.getLv()+"AL1_0003"),
			},frameAtk[0]
		);
	}
	
	public void Update(){
		this.UpdateMap();
		ASDMAX = (short)(AGI/5);
		ATK = WEAPON.getAtk();
		ATKMAX = WEAPON.getAtk();
		MATK = (short)(WEAPON.getMatk());
		MATKMAX = (short)(WEAPON.getMatk()+INT*2);
		PWMAX = (short)((short)20+(STR)*((short)(5))+(AGI)*((short)(5))+skill[0].getLv()*2);
		HPMAX = (short)((short)80+(CON)*((short)(5))+skill[2].getLv()*15);
	}
}

class PTS{
	private ParticleSystem pt;
	private boolean die;
	private int x,y,delta = 10,life,mapID;
	
	public PTS(String url,int x,int y,int life,int mapID){
		this.x = x;
		this.y = y;
		this.mapID = mapID;
		this.life = life;
		try{
			this.pt = ParticleIO.loadConfiguredSystem(url);
			this.pt.setDefaultImageName(FileList.effectDir+"smokeW.PNG");
		}catch(Exception e){
			javax.swing.JOptionPane.showMessageDialog(null,e.toString(),"Message PTS",javax.swing.JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void kill(){
		pt.setVisible(false);
		die = true;
	}
	
	public int getMap(){
		return(mapID);
	}
	
	public boolean getDie(){
		return(this.die);
	}
	
	public void update(){
		if(life > 0){
			life--;
		}else this.kill();

		try{
			pt.update(delta);
			pt.render(x,y);
		}catch(NullPointerException e){
			javax.swing.JOptionPane.showMessageDialog(null,e.toString(),"Message",javax.swing.JOptionPane.ERROR_MESSAGE);
		}
	}
}

class AWARD implements Serializable{
	private int x,y,mapIndex;
	private ParticleSystem effect;
	private Image img;
	private Graphics g = new Graphics();
	
	public AWARD(int mapID,int x,int y){
		this.x = x;
		this.y = y;
		this.mapIndex = mapID;
		
		try{
			img = new Image("Resource/img/aWard");
			effect = ParticleIO.loadConfiguredSystem("Resource/effect/Fire_01.xml");
		}catch(SlickException e){
			System.out.printf("award SlError : %s \n",e.toString());
		}catch(IOException er){
			System.out.printf("award IOError : %s \n",er.toString());
		}
	}
	
	public ParticleSystem getParticle(){
		return(this.effect);
	}
	
	public void renderGround(int indexMode){
		if((this.x > 0 && this.x < 1024) && this.mapIndex == indexMode){
			g.drawImage(img,x,y);
		}
	}
	
	public void render(int indexMode){
		if((this.x > 0 && this.x < 1024) && this.mapIndex == indexMode){
			getParticle().render(x+(img.getWidth())/2,y);
			getParticle().update(11);
		}
	}
	
	public boolean openIn(boolean b){
		return(b);
	}
	
	public int getX(){
		return(x);
	}
	
	public int getY(){
		return(y);
	}
}

class TARROW implements Serializable{
	private float speed,garvity,max;
	private int x,y,frameD,frameMax=1,atk,atking,lv;
	private Image img;
	private boolean hit,remove,fireMode;
	private char mode;
	private int R=170,L=0;
	private Font font;
	private Sound sound;
	private Graphics g = new Graphics();

	public TARROW(Image img,char mode,float max,int atk,int x,int y){
		this.img = img;
		this.mode = mode;
		this.speed = (float)(max/1.2);
		this.garvity = (float)(max*2);
		this.atk = atk;
		this.x = x+10;
		this.y = y+25;
		this.img.setCenterOfRotation(img.getWidth()/2,img.getHeight()/2);
		
		try{
			font = new AngelCodeFont("Resource/font/SimpleFont4.fnt","Resource/font/SimpleFont4.png");
			sound = new Sound("Resource/audio/bgs/hit2.wav");
		}catch(SlickException e){
			System.out.printf("e : %s\n"+e.toString());
		}
	}
	
	public TARROW(char mode,float max,int atk,int x,int y){
		this.mode = mode;
		this.speed = (float)(max/1.2);
		this.garvity = (float)(max*2);
		this.atk = atk;
		this.x = x+10;
		this.y = y+25;

		try{
			this.img = new Image(FileList.imgeDir+"arrow1");
			this.img.setCenterOfRotation(img.getWidth()/2,img.getHeight()/2);
			font = new AngelCodeFont("Resource/font/SimpleFont4.fnt","Resource/font/SimpleFont4.png");
			sound = new Sound("Resource/audio/bgs/hit2.wav");
		}catch(SlickException e){
			System.out.printf("e : %s\n"+e.toString());
		}
	}
	
	public TARROW(char mode,float max,int atk,int x,int y,boolean fireMode,int lv){
		this.mode = mode;
		this.speed = (float)(max/1.2);
		this.garvity = (float)(max*2);
		this.atk = atk;
		this.x = x+10;
		this.y = y+25;
		this.fireMode = fireMode;
		this.lv = lv;
		
		try{
			if(fireMode) this.img = new Image(FileList.imgeDir+"arrowfire.png");
			else this.img = new Image(FileList.imgeDir+"arrow1");
			
			this.img.setCenterOfRotation(img.getWidth()/2,img.getHeight()/2);
			font = new AngelCodeFont("Resource/font/SimpleFont4.fnt","Resource/font/SimpleFont4.png");
			sound = new Sound("Resource/audio/bgs/hit2.wav");
		}catch(SlickException e){
			System.out.printf("e : %s\n"+e.toString());
		}
	}
	
	public int getAtk(){
		return(atk);
	}
	
	public void get(){
		System.out.println("-------------------------------------------");
		System.out.printf("Img : %s ,x : %d , y : %d \n",this.img.toString(),x,y);
		System.out.printf("mode : %c , hit : %b \n ",mode,hit);
	}
	
	public boolean getHit(){
		return(hit);
	}
	
	public boolean getRemove(){
		return(remove);
	}
	
	public void drawSelf(){
		g.drawImage(img,x,y);
	}
	
	public Rectangle BOUNDINGBOX(){
		Rectangle r = new Rectangle(x,y,img.getWidth(),img.getHeight());
		return(r);
	}
	
	public void setTarget(ArrayList<ENEMY> enemy,HOME[] home){
		for(HOME h : home){
			if(h != null){
				if(this.BOUNDINGBOX().intersects(h.BOUNDINGBOX())){
					if(!hit && !h.die()){
						atking = this.atk-h.def;
						if(fireMode)  h.fd.add(new FireDmg(lv+1,atk/10));
						if(atking > 0){
							h.hp -= atk;
							h.drawDmgFont2(atk+"",h.CenterOf('x'),h.CenterOf('y'));
						}else{
							h.hp -= 1;
							h.drawDmgFont2("!",h.CenterOf('x'),h.CenterOf('y'));
						}
						
						if(this.x > 0 && this.x < 1024)sound.play();
						hit=true;
						break;
					}
				}
			}
		}
		for(ENEMY e : enemy){
			if(this.BOUNDINGBOX().intersects(e.boundingBoxOfHit())){
				if(!hit && !e.isDie()){
					atking = this.atk-e.def;
					if(atking > 0){
						e.hp -= atking;
						e.drawDmgFont2(atking+"",x+15,y);
					}else{
						e.hp -= 1;
						e.drawDmgFont2("!",x+15,y);
					}
					e.addEffect(mode,x,y);
					if(fireMode)  e.fd.add(new FireDmg(lv+1,atk/10));
					e.setColor(Color.red);
					if(this.mode == 'R') e.setTeep(e._x+10);
					if(this.mode == 'L') e.setTeep(e._x-10);
					if(this.x > 0 && this.x < 1024)sound.play();
					hit=true;
					break;
				}
			}
		}
	}
	
	public void setTarget(ArrayList<ENEMY> enemy){
		for(ENEMY e : enemy){
			if(this.BOUNDINGBOX().intersects(e.boundingBoxOfHit())){
				if(!hit && !e.isDie()){
					e.addEffect(mode,x,y);
					atking = e.def-atk;
					
					e.hp -= atk;
					e.drawDmgFont2(atk+"",x+15,y);
					e.setColor(Color.red);
					if(this.mode == 'R') e.setTeep(e._x+10);
					if(this.mode == 'L') e.setTeep(e._x-10);
					if(this.x > 0 && this.x < 1024)sound.play();
					hit=true;
					break;
				}
			}
		}
	}
	
	public void setTarget(HOME[] home,CHARACTER hero,ArrayList<SOLIDER> sol,ArrayList<MYSOLIDER> subSol){
		if(this.BOUNDINGBOX().intersects(hero.getBox())){
			if(!hero.getDie()){
				if(this.atk > 0){
					hero.HP-=this.atk;
				}else{
					hero.HP-=1;
				}
				hit = true;
				if(this.x > 0 && this.x < 1024)sound.play();
				return;
			}
		}
		
		for(MYSOLIDER tar : subSol){
			if(tar != null && !tar.getDie()){
				if(this.BOUNDINGBOX().intersects(tar.BOUNDINGBOX())){
					if(this.atk > 0){
						tar.hp-=this.atk;
					}else{
						tar.hp-=1;
					}
					hit = true;
					if(this.x > 0 && this.x < 1024)sound.play();
					break;
				}
			}
		}
		
		for(SOLIDER tar : sol){
			if(tar != null && !tar.getDie()){
				if(this.BOUNDINGBOX().intersects(tar.BOUNDINGBOX())){
					if(this.atk > 0){
						tar.hp-=this.atk;
					}else{
						tar.hp-=1;
					}
					hit = true;
					if(this.x > 0 && this.x < 1024)sound.play();
					break;
				}
			}
		}
		
		for(HOME h : home){
			if(h != null && !h.die()){
				if(this.BOUNDINGBOX().intersects(h.BOUNDINGBOX())){
					if(this.atk > 0){
						h.hp-=this.atk;
					}else{
						h.hp-=1;
					}
					hit = true;
					if(this.x > 0 && this.x < 1024)sound.play();
					break;
				}
			}
		}
	}
	
	public void setTarget(CHARACTER hero,ArrayList<SOLIDER> sol){
		if(this.BOUNDINGBOX().intersects(hero.getBox())){
			if(!hero.getDie()){
				if(this.atk > 0){
					hero.HP-=this.atk;
				}else{
					hero.HP-=1;
				}
				hit = true;
				if(this.x > 0 && this.x < 1024)sound.play();
				return;
			}
		}
		
		for(SOLIDER tar : sol){
			if(tar != null && !tar.getDie()){
				if(this.BOUNDINGBOX().intersects(tar.BOUNDINGBOX())){
					if(this.atk > 0){
						tar.hp-=this.atk;
					}else{
						tar.hp-=1;
					}
					hit = true;
					if(this.x > 0 && this.x < 1024)sound.play();
					break;
				}
			}
		}
	}
	
	public void Update(){
		switch(mode){
			case 'R': case '>': {
				if(this.y < 512){
					this.img.setRotation(R);
					this.x += speed;
					this.y -= garvity/5;
					this.R += speed/5;
				}else hit=true;
					
				break;
			}case 'L': case '<': {
				if(this.y < 512){
					this.img.setRotation(L);
					this.x -= speed;
					this.y -= garvity/5;
					this.L -= speed/5;
				}else hit=true;
				
				break;
			}
		}
		garvity--;
		if(this.hit){
			if(frameD < frameMax) frameD++;
			else remove = true;
		}
	}
}

class Bullet implements Serializable{
	private Image img;
	private int x,y,speed,life = 40,atk;
	private char mode;
	private boolean hit = false;
	
	public Bullet(char mode,int atk,int x,int y){
		this.x = x;
		this.y = y;
		this.mode = mode;
		this.atk = atk;
		this.speed = 15;
		try{
			img = new Image(10,10);
		}catch(SlickException e){
			
		}
	}
	
	public void KillBullet(){
		this.hit = true;
	}
	
	public boolean getDie(){
		return(hit);
	}
	
	public Rectangle BOUNDINGBOX(){
		Rectangle r = new Rectangle(x,y,2,2);
		return(r);
	}
	
	public void setTarget(HOME[] home,CHARACTER hero,ArrayList<SOLIDER> sol,ArrayList<MYSOLIDER> subSol){
		if(this.BOUNDINGBOX().intersects(hero.getBox())){
			if(!hero.getDie()){
				if(this.atk > 0){
					hero.HP-=this.atk;
				}else{
					hero.HP-=1;
				}
				hit = true;
				return;
			}
		}
		
		for(MYSOLIDER tar : subSol){
			if(tar != null && !tar.getDie()){
				if(this.BOUNDINGBOX().intersects(tar.BOUNDINGBOX())){
					if(this.atk > 0){
						tar.hp-=this.atk;
					}else{
						tar.hp-=1;
					}
					hit = true;
					break;
				}
			}
		}
		
		for(SOLIDER tar : sol){
			if(tar != null && !tar.getDie()){
				if(this.BOUNDINGBOX().intersects(tar.BOUNDINGBOX())){
					if(this.atk > 0){
						tar.hp-=this.atk;
					}else{
						tar.hp-=1;
					}
					hit = true;
					break;
				}
			}
		}
		
		for(HOME h : home){
			if(h != null && !h.die()){
				if(this.BOUNDINGBOX().intersects(h.BOUNDINGBOX())){
					if(this.atk > 0){
						h.hp-=this.atk;
					}else{
						h.hp-=1;
					}
					hit = true;
					break;
				}
			}
		}
	}
	
	public void UpdateAndDraw(HOME[] home,CHARACTER hero,ArrayList<SOLIDER> sol,ArrayList<MYSOLIDER> subSol){
		switch(mode){
			case '<' : case 'L':{
				if(life > 0){
					life--;
					this.x -= speed;
				}else{
					KillBullet();
				}
			
				break;
			}
			case '>' : case 'R':{
				if(life > 0){
					life--;
					this.x += speed;
				}else{
					KillBullet();
				}
			
				break;
			}
		}
		setTarget(home,hero,sol,subSol);
	}
}

class BLOOD implements Serializable{
	private ParticleSystem particle;
	private int life,x,y,upf;
	private char mode;
	
	public BLOOD(char mode,int x,int y){
		life=100;
		upf=10;
		this.mode = mode;
		this.x = x;
		this.y = y+40;
		
		try{
			particle = ParticleIO.loadConfiguredSystem("Resource/effect/blood.xml");
		}catch(IOException e){
			System.out.println("Error : 3 ");
		}
	}
	
	public boolean getRemove(){
		if(life > 0)life--;
		else return(true);
		return(false);
	}
	
	public void render(){	
		if(this.x > 30)
		switch(mode){
			case 'R': particle.update(upf); particle.render(this.x,this.y); break;
			case 'L': particle.update(upf); particle.render(this.x+50,this.y); break;
		}
		return;
	}
	
	public void update(int x,int y){
		switch(mode){
			case 'R':this.x = x+15; break;
			case 'L':this.x = x-15; break;
		}
		this.y = y+40;
		return;
	}
	
	public void get(){
		System.out.printf("Mode : %c , Position : %d, %d\n",mode,x,y);
	}
}

class FireDmg implements Serializable{
	protected ParticleSystem ps;
	protected int delta,life,atk;
	protected boolean end;
	
	public FireDmg(int lvSkill,int dmg){
		this.life = lvSkill*100;
		this.delta = 10;
		this.atk = dmg;
		
		try{
			this.ps = ParticleIO.loadConfiguredSystem(FileList.effectDir+"Fire_02.xml");
		}catch(IOException e){
		}
	}
	
	public boolean getDie(){
		return(end);
	}
	
	public void update(int x,int y,ENEMY en){
		if((life%100)==0){
			en.hp-=atk;
			en.drawDmgFont2("-"+atk,x,y);
		}
		if(life > 0){
			life--;
			ps.update(delta);
			ps.render(x,y);
			en.setColor(Color.red);
		}else{
			en.setColor(new Color(255,255,255,255));
			this.end = true;
		}
	}
	
	public void update(int x,int y,HOME en){
		if((life%100)==0){
			en.hp-=atk;
			en.drawDmgFont2("-"+atk,x,y);
		}
		if(life > 0){
			life--;
			ps.update(delta);
			ps.render(x,y);
			en.setColor(Color.red);
		}else{
			en.setColor(new Color(255,255,255,255));
			this.end = true;
		}
	}
}

class HealSkill implements Serializable{
	protected ParticleSystem ps;
	protected int delta,life,val;
	protected boolean end;
	protected Sound sound;
	
	public HealSkill(int val,CHARACTER hero){
		try{
			this.ps = ParticleIO.loadConfiguredSystem("Resource/effect/ef006.xml");
			this.sound = new Sound(FileList.bgsDir+"Recovery.wav");
			this.end = false;
			this.life = 200;
			this.delta = 10;
			this.val = val;
			this.sound.play();
			hero.addGreenFont("+"+val);
			hero.HP += val;
		}catch(SlickException e){
		}catch(IOException e){
		}
	}
	
	public HealSkill(int val,SOLIDER solider){
		try{
			this.ps = ParticleIO.loadConfiguredSystem("Resource/effect/ef006.xml");
			this.sound = new Sound(FileList.bgsDir+"Recovery.wav");
			this.end = false;
			this.life = 200;
			this.delta = 10;
			this.val = val;
			this.sound.play();
			solider.addGreenFont("+"+val,solider.CenterOf('x'),solider.CenterOf('y'));
			solider.hp += val;
		}catch(SlickException e){
		}catch(IOException e){
		}
	}
	
	public HealSkill(MYSOLIDER ms){
		try{
			this.ps = ParticleIO.loadConfiguredSystem("Resource/effect/ef006.xml");
			this.sound = new Sound(FileList.bgsDir+"Recovery.wav");
			this.end = false;
			this.life = 200;
			this.delta = 10;
			this.val = val;
			this.sound.play();
			ms.addGreenFont("+"+val,ms.CenterOf('x'),ms.CenterOf('y'));
			ms.hp += val;
		}catch(SlickException e){
		}catch(IOException e){
		}
	}
	
	public boolean getDie(){
		return(end);
	}
	
	public void update(int x,int y){
		if(life > 0){
			life--;
			ps.update(delta);
			ps.render(x,y);
		}else this.end = true;
	}
}

class Flashright implements Serializable{
	protected ParticleSystem pR,pL;
	protected int delta,life,atk,lv;
	protected boolean end;
	
	public Flashright(int lv,int dmg){
		this.life = 150;
		this.delta = 10;
		this.atk = ((dmg/4)*lv);
		this.lv = lv;
		try{
			this.pR = ParticleIO.loadConfiguredSystem(FileList.effectDir+"wizardskill/fireR.xml");
			this.pL = ParticleIO.loadConfiguredSystem(FileList.effectDir+"wizardskill/fireL.xml");
		}catch(IOException e){
		}
	}
	
	public boolean getDie(){
		return(end);
	}
	
	public void update(int x,int y,char Mode,CHARACTER hero,ArrayList<ENEMY> en,HOME[] hm){
		if(life > 0){
			life--;
			switch(Mode){
				case '>' : case 'R':{
					for(HOME h : hm){
						if(h._x <= hero._x+200 && !h.fireHit){
							h.fd.add(new FireDmg(lv+1,atk));
						}
					}
					for(ENEMY e : en){
						if(e._x <= hero._x+200 && !e.fireHit){
							e.fd.add(new FireDmg(lv+1,atk));
							e.fireHit=true;
						}
					}
					pR.render(x,y);
					break;
				}
				case '<' : case 'L':{
					for(HOME h : hm){
						if(h._x <= hero._x+200 && !h.fireHit){
							h.fd.add(new FireDmg(lv+1,atk));
						}
					}
					for(ENEMY e : en){
						if(e._x <= hero._x-200 && !e.fireHit){
							e.fd.add(new FireDmg(lv+1,atk));
							e.fireHit=true;
						}
					}
					pL.render(x,y);
					break;
				}
			}
			
			pR.update(delta);
			pL.update(delta);
		}else this.end = true;
	}
}

class BuffaloBow implements Serializable{
	protected int life,live = 300,x,y,speed,atk,boomAtk,delta,lv,mapID;
	protected Animation r,l;
	protected boolean end,endBoom;
	protected ParticleSystem par,boom;
	protected char mode;
	protected Sound sound,soundBoom;
	protected ArrayList<PTS> pts = new ArrayList<PTS>();
	
	
	public BuffaloBow(int x,int lv,int atk,char mode,int mapID){
		this.x = x;
		this.y = 440;
		this.lv = lv;
		this.speed = 4+lv/2;
		this.atk = atk;
		this.end = false;
		this.mode = mode;
		this.delta = 10;
		this.life = 130;
		this.boomAtk = atk*lv;
		this.endBoom = false;
		this.mapID = mapID;
		
		try{
			r = new Animation(
				new Image[]{
					new Image("Resource/effect/wizardskill/Wzsr_0001.png"),
					new Image("Resource/effect/wizardskill/Wzsr_0002.png"),
				},300
			);
			
			l = new Animation(
				new Image[]{
					new Image("Resource/effect/wizardskill/Wzsl_0001.png"),
					new Image("Resource/effect/wizardskill/Wzsl_0002.png"),
				},300
			);
			sound = new Sound(FileList.bgsDir+"skill01.wav");
			soundBoom = new Sound(FileList.bgsDir+"skill03.wav");
			par = ParticleIO.loadConfiguredSystem(FileList.effectDir+"runsomke.xml");
			boom = ParticleIO.loadConfiguredSystem(FileList.effectDir+"wizardskill/boomFire.xml");
			sound.play();
		}catch(SlickException e){
		}catch(IOException er){
		}
	}
	
	public ArrayList<PTS> getPTS(){
		return(pts);
	}
	
	public boolean getDie(){
		return(this.end);
	}
	
	public char getMode(){
		return(mode);
	}
	
	public void update(ArrayList<ENEMY> en){
		par.update(delta);
		boom.update(delta);
		if(life > 0){
			life--;

			for(PTS pt : pts){
				if(pt.getDie()){
					pts.remove(pt);
					break;
				}
			}
			if(live == 300){
				switch(mode){
					case '<' : case 'L':{
						x-=speed;
						l.draw(x,y);
						if((x%5)==0){
							pts.add(new PTS(FileList.effectDir+"blackSmoke.xml",x+50,y+60,150,mapID));
						}
						
						for(ENEMY e : en){
							if(e._x <= this.x+50 && !e.hitHome) e.x-=this.speed+e.speed;
						}
						break;
					}
					case '>' : case 'R':{
						x+=speed;
						r.draw(x,y);
						if((x%5)==0){
							pts.add(new PTS(FileList.effectDir+"blackSmoke.xml",x-10,y+60,150,mapID));
						}
						
						for(ENEMY e : en){
							if(e._x < this.x+80 && !e.hitHome) e.x+=this.speed+e.speed;
						}
						break;
					}
				}
			}
		}else{
			if(!endBoom){
				soundBoom.play();
				endBoom = true;
			}
			switch(mode){
				case '<' : case 'L':{
					boom.render(this.x-50,this.y+20);
					break;
				}
				case '>' : case 'R':{
					boom.render(this.x+50,this.y+20);
					break;
				}
			}
			for(ENEMY e : en){
				if(e._x > this.x-110 && e._x <= this.x+220){
					if(!e.fireHit){
						e.fd.add(new FireDmg(this.lv,this.atk));
						e.drawDmgFont2(""+this.boomAtk,e.CenterOf('x'),e.CenterOf('y'));
						e.fireHit = true;
					}
				}
			}
			
			if(live > 0){
				live--;
			}else end = true;
		}
	}
}

class BigSword implements Serializable{
	private int life,x,y,speed,atk;
	private Animation r,l;
	private boolean hit;
	private Sound sound;
	private char mode;
	private int count,size,delay=100;
	
	public BigSword(int lv,int x,int atk,char mode){
		this.atk = atk*lv+1;
		this.x = x;
		this.y = 445;
		this.mode = mode;
		this.life = 20;
		this.speed = 14;
		this.hit = false;
		this.size = 3+lv;
		
		try{
			r = new Animation(
				new Image[]{
					new Image(FileList.effectDir+"warriorskill/skillR01.PNG"),
					new Image(FileList.effectDir+"warriorskill/skillR02.PNG"),
				},150
			);
			
			l = new Animation(
				new Image[]{
					new Image(FileList.effectDir+"warriorskill/skillL01.PNG"),
					new Image(FileList.effectDir+"warriorskill/skillL02.PNG"),
				},150
			);
			
			sound = new Sound(FileList.bgsDir+"skill04.wav");
			sound.play();
		}catch(SlickException e){
		}
	}
	
	public boolean getDie(){
		return(this.hit);
	}
	
	public void update(ArrayList<ENEMY> tar){
		if(life > 0){
			life--;
			
			switch(mode){
				case '>' : case 'R':{
					x+=speed;
					r.draw(x-50,y);
					
					for(ENEMY en : tar){
						if(count < size){
							if(en._x < this.x-100){
								en.x += speed+en.speed;
								if((delay%100)==0){
									en.hp -= this.atk;
									en.drawDmgFont2(this.atk+"",en.CenterOf('x'),en.CenterOf('y'));
									count++;
								}
								if(delay > 0) delay--;
								else delay = 100;
							}
						}
					}
					
					break;
				}
				case '<' : case 'L':{
					x-=speed;
					l.draw(x,y);
					
					for(ENEMY en : tar){
						if(count < size){
							if(en._x < this.x-100){
								en.x += speed+en.speed;
								if((delay%100)==0){
									en.hp -= this.atk;
									en.drawDmgFont2(this.atk+"",en.CenterOf('x'),en.CenterOf('y'));
									count++;
								}
								if(delay > 0) delay--;
								else delay = 100;
							}
						}
					}
					break;
				}
			}
		}else hit = true;
	}
}

class DMGTEXT implements Serializable{
	private int x,y,alpha;
	private String val;
	private int dmg;
	private Font font;
	private int speed;
	
	public DMGTEXT(Font font,String val,int x,int y){
		this.font = font;
		this.val = val;
		this.x = x;
		this.y = y;
		this.alpha = 255;
		this.speed = 3;
	}
	
	public void UpdateAndRender(){
		Graphics g = new Graphics();
		g.setFont(this.font);
		this.y -= speed;
		this.alpha -= speed*2;
		
		g.setColor(new Color(255,255,255,alpha));
		g.drawString(this.val,this.x,this.y);
		g.setColor(new Color(255,255,255));
	}
	
	public boolean getRemove(){
		if(this.alpha <= 0) return(true);
		else return(false);
	}
	
	public int getX(){
		return(x);
	}
	
	public int getY(){
		return(y);
	}
	
	public String getVal(){
		return(val);
	}
	
	public int getDmg(){
		return(dmg);
	}
}

class AStar implements Comparator{
	public int compare(Object a,Object b){
		HOME aObj = (HOME)a;
		HOME bObj = (HOME)b;
		
		if(bObj.mapIndex > aObj.mapIndex){
			if(bObj._x > aObj._x && !bObj.die()){
				return(1);
			}else{
				return(0);
			}
		}else if(aObj.mapIndex > bObj.mapIndex){
			if(aObj._x > bObj._x && !aObj.die()){
				return(-1);
			}else{
				return(0);
			}
		}else{
			return(0);
		}
	}
}

class SortTarget implements Comparator{
	public int compare(Object a,Object b){
		ENEMY aObj = (ENEMY)a;
		ENEMY bObj = (ENEMY)b;
		
		if(aObj._x < bObj._x){
			if(!aObj.isDie()){
				return(1);
			}else{
				return(0);
			}
		}else if(bObj._x < aObj._x){
			if(!bObj.isDie()){
				return(-1);
			}else{
				return(0);
			}
		}else{
			return(0);
		}
	}
}

class IMAGEING{
	protected Animation _stopR,_stopL,_atk1R,_atk1L,_atk2R,_atk2L;
	protected Animation _walkR,_walkL,_dieR,_dieL;
	protected int _x,x,mapX,y;
	protected enum MODE{
					stopR,stopL,
					atk1R,atk1L,
					atk2R,atk2L,
					walkR,walkL,
					dieR,dieL
	};
	protected MODE inMode = MODE.stopR;
	protected short[] frame;
	protected Font[] font = new Font[4];
	protected Font gFont;
	protected Image shardow;
	protected int walker,frameMn,frameMx;
	protected ArrayList<BLOOD> blood;
	protected Graphics g = new Graphics();
	protected char modeIn,atkIn;
	protected ArrayList<DMGTEXT> dmgTxt = new ArrayList<DMGTEXT>();
	public ArrayList<DMGTEXT> msgText = new ArrayList<DMGTEXT>();
	protected Color color;
	protected int frameEf;
	public boolean fireHit = false;
	protected ArrayList<TARROW> arrow = new ArrayList<TARROW>();
	protected ArrayList<Bullet> bullet = new ArrayList<Bullet>();
	protected ArrayList<SOLIDER> sol;
	protected ArrayList<MYSOLIDER> subSol;
	protected ArrayList<ENEMY> target;
	protected HOME[] obj;
	protected boolean walk,hit,combo,dier,dmgHit;
	protected Sound soundEffect[],sounds,soundDie;
	
	public IMAGEING(){
		frameMn = 0;
		frameMx = 200;
		blood = new ArrayList<BLOOD>();
		try{
			font[0] = new AngelCodeFont("Resource/font/simpleFont.fnt","Resource/font/simpleFont.png");
			font[1] = new AngelCodeFont("Resource/font/f1.fnt","Resource/font/f1.png");
			font[2] = new AngelCodeFont("Resource/font/f2.fnt","Resource/font/f2.png");
			font[3] = new AngelCodeFont("Resource/font/f3.fnt","Resource/font/f3.png");
			shardow = new Image("Resource/img/shardowA");
			gFont = new AngelCodeFont("Resource/font/greenFont.fnt","Resource/font/greenFont.png");
		}catch(SlickException e){
			System.out.printf("Error on %s : %s \n",this.getClass().getName(),e.toString());
		}
	}
	public void addEffect(char mode,int x,int y){
		this.blood.add(new BLOOD(mode,x,y));
	}
	protected void drawString(String msg,int x,int y,Color c){
		g.setFont(this.font[0]);
		
		g.setColor(new Color(0,0,0,200));
		g.drawString(msg,x+1,y+1);
		g.setColor(c);
		g.drawString(msg,x,y);
		g.setColor(new Color(255,255,255,255));
	}
	protected void drawDmgFont(String msg,int x,int y){
		dmgTxt.add(new DMGTEXT(this.font[1],msg,x,y));
	}
	protected void drawDmgFont3(String msg,int x,int y){
		dmgTxt.add(new DMGTEXT(this.font[3],msg,x,y));
	}
	public void drawDmgFont2(String msg,int x,int y){
		dmgTxt.add(new DMGTEXT(this.font[2],msg,x,y));
	}
	public void addGreenFont(String msg,int x,int y){
		msgText.add(new DMGTEXT(this.gFont,msg,x,y));
	}
	public int CenterOf(char c){
		if(c == 'x' || c == 'X') return(_x+50/2-5);
		if(c == 'y' || c == 'Y') return(y+50/2-5);
		return(0);
	}
	public void setColor(Color color){
		this.color = color;
		frameEf = 20;
	}
	/*
	public void ImageDestroy() throws SlickException {
	//	protected Animation _stopR,_stopL,_atk1R,_atk1L,_atk2R,_atk2L;
	//	protected Animation _walkR,_walkL,_dieR,_dieL;
	
		for(int i = 0;i<_stopR.getFrameCount();i++){
			if(_stopR.getImage(i)!=null)
			_stopR.getImage(i).destroy();
		}
		for(int i = 0;i<_stopL.getFrameCount();i++){
			if(_stopL.getImage(i)!=null)
			_stopL.getImage(i).destroy();
		}
		for(int i = 0;i<_atk1R.getFrameCount();i++){
			if(_atk1R.getImage(i)!=null)
			_atk1R.getImage(i).destroy();
		}
		for(int i = 0;i<_atk1L.getFrameCount();i++){
			if(_atk1L.getImage(i)!=null)
			_atk1L.getImage(i).destroy();
		}
		for(int i = 0;i<_atk2R.getFrameCount();i++){
			if(_atk2R.getImage(i)!=null)
			_atk2R.getImage(i).destroy();
		}
		for(int i = 0;i<_atk2L.getFrameCount();i++){
			if(_atk2L.getImage(i)!=null)
			_atk2L.getImage(i).destroy();
		}
		for(int i = 0;i<_walkR.getFrameCount();i++){
			if(_walkR.getImage(i)!=null)
			_walkR.getImage(i).destroy();
		}
		for(int i = 0;i<_walkL.getFrameCount();i++){
			if(_walkL.getImage(i)!=null)
			_walkL.getImage(i).destroy();
		}
		for(int i = 0;i<_dieR.getFrameCount();i++){
			if(_dieR.getImage(i)!=null)
			_dieR.getImage(i).destroy();
		}
		for(int i = 0;i<_dieL.getFrameCount();i++){
			if(_dieL.getImage(i)!=null)
			_dieL.getImage(i).destroy();
		}
	}*/
}

abstract class SOLIDER extends IMAGEING implements Serializable{
	protected int speed,mapIndex,range,atk,hpMax,def,defaultX,delayStoped = 400,delayStop = delayStoped;
	protected String name;
	public int hp,frameDieLife = 500;
	public boolean warring = false;
	public ArrayList<HealSkill> healskill = new ArrayList<HealSkill>();
	public SoliderJob sJob;
	
	public SOLIDER(){
		try{
			this.y = 445;
			soundDie = new Sound(FileList.bgsDir+"sDie.wav");
			soundEffect = new Sound[]{
				new Sound(FileList.bgsDir+"bgs17.wav"),
				new Sound(FileList.bgsDir+"bgs16.wav"),
				new Sound(FileList.bgsDir+"attking.wav"),
				new Sound(FileList.bgsDir+"hit3.wav"),
			};
		}catch(SlickException e){
			System.out.printf("Error : %s",e.toString());
		}
	}
	
	public Rectangle BOUNDINGBOX(){
		Rectangle r = new Rectangle(_x,y,30,30);
		
		return(r);
	}
	
	public int getX(){
		return(this.x);
	}
	
	public int _getX(){
		return(this._x);
	}
	
	public int getMapX(){
		return(this.mapX);
	}
	
	public int getATK(){
		return((int)(this.atk-3+Math.random()*this.atk/2));
	}
	
	public int getDef(){
		return(this.def);
	}
	
	public void setMap(int mapIndex,int x){
		this.x = -1024+1024*mapIndex+x;
		defaultX = this.x;
	}
	
	public boolean getDie(){
		if(this.hp <= 0){
			if(!dier && (this._x > 0 && this._x < 1024)){
				soundDie.play();
				dier=true;
			}
			if(this.modeIn == 'R')this.inMode = MODE.dieR;
			if(this.modeIn == 'L')this.inMode = MODE.dieL;
			return(true);
		}else return(false);
	}
	
	public void updateing(){
		if(hp > hpMax)hp=hpMax;
		for(HealSkill hs : healskill){
			if(!hs.getDie()){
				hs.update(this.CenterOf('x'),this.CenterOf('y')+20);
			}else{
				healskill.remove(hs);
				break;
			}
		}
		
		for(DMGTEXT dmgTEXT : dmgTxt){
			if(!dmgTEXT.getRemove()){
				dmgTEXT.UpdateAndRender();
			}else{
				dmgTxt.remove(dmgTEXT);
				break;
			}
		}
		
		for(DMGTEXT dmgTEXT : msgText){
			if(!dmgTEXT.getRemove()){
				dmgTEXT.UpdateAndRender();
			}else{
				msgText.remove(dmgTEXT);
				break;
			}
		}
	}
	
	public boolean isDie(){
		if(hp <= 0){
			frameDieLife--;
			if(frameDieLife<=0)return(true);
		}
		
		return(false);
	}
	
	public abstract void Update(int mapX,ArrayList<ENEMY> tar,HOME[] home);
	public abstract void Load() throws SlickException;
	public abstract void DrawSelf();
}

class SOLIDER_SWORD extends SOLIDER implements Serializable{
	
	public SOLIDER_SWORD(){
		this.hpMax = 120;
		this.hp = hpMax;
		this.speed = 2;
		this.frame = new short[]{550,200,230,80};
		this.range = 30;
		this.atk = 10;
		this.name = "พลดาบ";
		this.sJob = SoliderJob.sword;
	}
	
	public void Load() throws SlickException {
		String dirOfStop = "Resource/animation/Solider/Solider1/Stop/";
		String dirOfWalk = "Resource/animation/Solider/Solider1/Walk/";
		String dirOfAttk = "Resource/animation/Solider/Solider1/Atk/";
		String dirOfDead = "Resource/animation/Solider/Solider1/Die/";
		
		this._stopR = new Animation(
			new Image[]{
				new Image(dirOfStop+"Swsr_0001.png"),
				new Image(dirOfStop+"Swsr_0002.png"),
			},frame[0]
		);
		
		this._stopL = new Animation(
			new Image[]{
				new Image(dirOfStop+"Swsl_0001.png"),
				new Image(dirOfStop+"Swsl_0002.png"),
			},frame[0]
		);
		
		this._walkR = new Animation(
			new Image[]{
				new Image(dirOfWalk+"Swwr_0001.png"),
				new Image(dirOfWalk+"Swwr_0002.png"),
				new Image(dirOfWalk+"Swwr_0003.png"),
				new Image(dirOfWalk+"Swwr_0002.png"),
			},frame[1]
		);
		
		this._walkL = new Animation(
			new Image[]{
				new Image(dirOfWalk+"Swwl_0001.png"),
				new Image(dirOfWalk+"Swwl_0002.png"),
				new Image(dirOfWalk+"Swwl_0003.png"),
				new Image(dirOfWalk+"Swwl_0002.png"),
			},frame[1]
		);
		
		this._atk1R = new Animation(
			new Image[]{
				new Image(dirOfAttk+"Swar_0001.png"),
				new Image(dirOfAttk+"Swar_0002.png"),
				new Image(dirOfAttk+"Swar_0003.png"),
				new Image(dirOfAttk+"Swar_0003.png"),
				new Image(dirOfAttk+"Swar_0003.png"),
			},frame[2]
		);
		
		this._atk1L = new Animation(
			new Image[]{
				new Image(dirOfAttk+"Swal_0001.png"),
				new Image(dirOfAttk+"Swal_0002.png"),
				new Image(dirOfAttk+"Swal_0003.png"),
				new Image(dirOfAttk+"Swal_0003.png"),
			},frame[2]
		);
		
		this._dieR = new Animation(
			new Image[]{
				new Image(dirOfDead+"Swdr.png")
			},frame[3]
		);
		
		this._dieL = new Animation(
			new Image[]{
				new Image(dirOfDead+"Swdl.png")
			},frame[3]
		);
	}
	
	public void DrawSelf(){
		_x = x+this.mapX;
		if((this._x < 1024) && (this._x > 0))
			g.drawImage(shardow,_x,y+70);
			
		switch(inMode){
			case stopR:{
				g.drawAnimation(this._stopR,this._x,this.y);
				modeIn = 'R';
				walk = false;
				dmgHit = false;
				break;
			}
			case stopL:{
				g.drawAnimation(this._stopL,this._x,this.y);
				modeIn = 'L';
				walk = false;
				dmgHit = false;
				break;
			}
			case walkR:{
				g.drawAnimation(this._walkR,this._x,this.y);
				modeIn = 'R';
				inMode = MODE.stopR;
				walk = true;
				break;
			}
			case walkL:{
				g.drawAnimation(this._walkL,this._x,this.y);
				modeIn = 'L';
				inMode = MODE.stopL;
				walk = true;
				break;
			}
			case atk1R:{
				g.drawAnimation(this._atk1R,this._x,this.y);
				modeIn = 'R';
				inMode = MODE.stopR;
				dmgHit = true;
				break;
			}
			case atk1L:{
				g.drawAnimation(this._atk1L,this._x,this.y);
				modeIn = 'L';
				inMode = MODE.stopL;
				dmgHit = true;
				break;
			}
			case dieR :{
				g.drawAnimation(this._dieR,this._x,this.y);
				modeIn = 'R';
				break;
			}
			case dieL :{
				g.drawAnimation(this._dieL,this._x,this.y);
				modeIn = 'L';
				break;
			}
		}
		
		this.drawString(this.name,this._x+(55-font[0].getWidth(this.name))/2,this.y-15,Color.green);
	}
	
	public void Update(int mapX,ArrayList<ENEMY> tar,HOME[] home){
		this.mapX = mapX;
		
		if(warring){
			if(!this.getDie()){
				for(HOME h : home){
					if(h != null){
						if(this.BOUNDINGBOX().intersects(h.BOUNDINGBOX())){
							int atking = this.getATK()-h.def;
							if(modeIn == 'R') inMode = MODE.stopR;
							if(modeIn == 'L') inMode = MODE.stopL;
							
							if(!h.die()){
								if(modeIn == 'R'){
									inMode = MODE.atk1R;
									if(_atk1R.getFrame() == 0)hit=false;
									if(_atk1R.getFrame() == _atk1R.getFrameCount()-1 && !hit){
										if(atking > 0){
											h.hp -= atking;
											h.drawDmgFont2(atking+"",h._x,h.y-10);
											if(this._x > 0 && this._x < 1024)soundEffect[2].play();
											try{
												if((this._x < 1024) && (this._x > 0))
												(new Sound(FileList.bgsDir+"bgs17.wav")).play();
											}catch(SlickException error){
											}
										}else{
											h.hp -= 1;
											h.drawDmgFont2("!",h._x,h.y-10);
											if((this._x < 1024) && (this._x > 0))soundEffect[(int)(2+Math.random()*1)].play();
										}
										
										hit=true;
										dmgHit=true;
									}
								}
								if(modeIn == 'L'){
									inMode = MODE.atk1L;
									if(_atk1L.getFrame() == 0)hit=false;
									if(_atk1L.getFrame() == _atk1L.getFrameCount()-1 && !hit){
										if(atking > 0){
											h.hp -= atking;
											h.drawDmgFont2(atking+"",h._x+15,h.y-10);
											if(this._x > 0 && this._x < 1024)soundEffect[2].play();
											try{
												if((this._x < 1024) && (this._x > 0))
												(new Sound(FileList.bgsDir+"bgs17.wav")).play();
											}catch(SlickException error){
											}
										}else{
											h.hp -= 1;
											h.drawDmgFont2(1+"!",h._x+15,h.y-10);
											if((this._x < 1024) && (this._x > 0))soundEffect[(int)(2+Math.random()*1)].play();
										}
											
										hit=true;
										dmgHit=true;
									}
								}
								return;
							}
						}
					}
				}
				
				for(ENEMY tSelf : tar){
					if(this.BOUNDINGBOX().intersects(tSelf.boundingBoxOfHit())){
						int atking = this.getATK()-tSelf.def;
						if(modeIn == 'R') inMode = MODE.stopR;
						if(modeIn == 'L') inMode = MODE.stopL;
						
						if(!tSelf.isDie()){
							if(modeIn == 'L' && !walk){
								inMode = MODE.valueOf("atk"+1+"L");
								if(_atk1L.getFrame() == 0)hit=false;
								if(_atk1L.getFrame() == _atk1L.getFrameCount()-1 && !hit){
									if(atking > 0){
										tSelf.hp -= atking;
										tSelf.drawDmgFont2(atking+"",tSelf._x+15,tSelf.y-10);
										if(this._x > 0 && this._x < 1024)soundEffect[2].play();
										try{
											if((this._x < 1024) && (this._x > 0))
											(new Sound(FileList.bgsDir+"bgs17.wav")).play();
										}catch(SlickException error){
										}
									}else{
										tSelf.hp -= 1;
										tSelf.drawDmgFont2(1+"!",tSelf._x+15,tSelf.y-10);
										if((this._x < 1024) && (this._x > 0))soundEffect[(int)(2+Math.random()*1)].play();
									}
									
									tSelf.addEffect(modeIn,tSelf.CenterOf('x'),tSelf.CenterOf('y'));
									hit=true;
									dmgHit=true;
								}
							}
							if(modeIn == 'R' && !walk){
								inMode = MODE.valueOf("atk"+1+"R");
								if(_atk1R.getFrame() == 0)hit=false;
								if(_atk1R.getFrame() == _atk1R.getFrameCount()-1 && !hit){
									if(atking > 0){
										tSelf.hp -= atking;
										tSelf.drawDmgFont2(atking+"",tSelf._x,tSelf.y-10);
										if(this._x > 0 && this._x < 1024)soundEffect[2].play();
										try{
											if((this._x < 1024) && (this._x > 0))
											(new Sound(FileList.bgsDir+"bgs17.wav")).play();
										}catch(SlickException error){
										}
									}else{
										tSelf.hp -= 1;
										tSelf.drawDmgFont2("!",tSelf._x,tSelf.y-10);
										if((this._x < 1024) && (this._x > 0))soundEffect[(int)(2+Math.random()*1)].play();
									}
									
									tSelf.addEffect(modeIn,tSelf.CenterOf('x'),tSelf.CenterOf('y'));
									hit=true;
									dmgHit=true;
								}
							}
							return;
						}
					}else{
						if(!tSelf.isDie() && this._x < tSelf._x){
							if(!tSelf.isDie() && this._x < tSelf._x){
								for(HOME h : home){
									if(h != null){
										if(!this.BOUNDINGBOX().intersects(h.BOUNDINGBOX()) || h.die()){
											this.inMode = MODE.walkR;
											this.x += this.speed;
											this.walk = true;
											break;
										}
										break;
									}
								}
								break;
							}
						}
					}
					continue;
				}
			}
		}else{
			if(delayStop > 0)
				delayStop--;
			else{
				if(this.x >= this.defaultX){
					this.x -= this.speed;
					this.inMode = MODE.walkL;
					this.walk = true;
				}else this.inMode = MODE.stopR;
			}
		}
	}
}

class SOLIDER_ARCHER extends SOLIDER implements Serializable{
	private HOME[] obj;
	private int aspd = 150,delayAsp = aspd;
	
	public SOLIDER_ARCHER(){
		this.hpMax = 70;
		this.hp = hpMax;
		this.speed = 2;
		this.frame = new short[]{550,200,230,80};
		this.range = 250;
		this.atk = 15;
		this.name = "พลธนู";
		this.sJob = SoliderJob.archer;
	}
	
	public void Load() throws SlickException{
		String dirOfStop = "Resource/animation/Solider/Solider2/Stop/";
		String dirOfWalk = "Resource/animation/Solider/Solider2/Walk/";
		String dirOfAttk = "Resource/animation/Solider/Solider2/Atk/";
		String dirOfDead = "Resource/animation/Solider/Solider2/Die/";
		
		this._stopR = new Animation(
			new Image[]{
				new Image(dirOfStop+"Sasr_0001.png"),
				new Image(dirOfStop+"Sasr_0002.png"),
			},frame[0]
		);
		
		this._stopL = new Animation(
			new Image[]{
				new Image(dirOfStop+"Sasl_0001.png"),
				new Image(dirOfStop+"Sasl_0002.png"),
			},frame[0]
		);
		
		this._walkR = new Animation(
			new Image[]{
				new Image(dirOfWalk+"Sawr_0001.png"),
				new Image(dirOfWalk+"Sawr_0002.png"),
				new Image(dirOfWalk+"Sawr_0003.png"),
				new Image(dirOfWalk+"Sawr_0002.png"),
			},frame[1]
		);
		
		this._walkL = new Animation(
			new Image[]{
				new Image(dirOfWalk+"Sawl_0001.png"),
				new Image(dirOfWalk+"Sawl_0002.png"),
				new Image(dirOfWalk+"Sawl_0003.png"),
				new Image(dirOfWalk+"Sawl_0002.png"),
			},frame[1]
		);
		
		this._atk1R = new Animation(
			new Image[]{
				new Image(dirOfAttk+"Saar.png"),
			},frame[2]
		);
		
		this._atk1L = new Animation(
			new Image[]{
				new Image(dirOfAttk+"Saal.png"),
			},frame[2]
		);
		
		this._dieR = new Animation(
			new Image[]{
				new Image(dirOfDead+"dieR.png"),
			},frame[3]
		);
		
		this._dieL = new Animation(
			new Image[]{
				new Image(dirOfDead+"dieL.png"),
			},frame[3]
		);
	}
	
	public void DrawSelf(){
		_x = x+this.mapX;
		if((this._x < 1024) && (this._x > 0))
			g.drawImage(shardow,_x,y+70);
			
		switch(inMode){
			case stopR:{
				g.drawAnimation(this._stopR,this._x,this.y);
				modeIn = 'R';
				walk = false;
				dmgHit = false;
				break;
			}
			case stopL:{
				g.drawAnimation(this._stopL,this._x,this.y);
				modeIn = 'L';
				walk = false;
				dmgHit = false;
				break;
			}
			case walkR:{
				g.drawAnimation(this._walkR,this._x,this.y);
				modeIn = 'R';
				inMode = MODE.stopR;
				walk = true;
				break;
			}
			case walkL:{
				g.drawAnimation(this._walkL,this._x,this.y);
				modeIn = 'L';
				inMode = MODE.stopL;
				walk = true;
				break;
			}
			case atk1R:{
				g.drawAnimation(this._atk1R,this._x,this.y);
				modeIn = 'R';
				inMode = MODE.stopR;
				dmgHit = true;
				break;
			}
			case atk1L:{
				g.drawAnimation(this._atk1L,this._x,this.y);
				modeIn = 'L';
				inMode = MODE.stopL;
				dmgHit = true;
				break;
			}
			case dieR :{
				g.drawAnimation(this._dieR,this._x,this.y);
				modeIn = 'R';
				break;
			}
			case dieL :{
				g.drawAnimation(this._dieL,this._x,this.y);
				modeIn = 'L';
				break;
			}
		}
		
		this.drawString(this.name,this._x+(55-font[0].getWidth(this.name))/2,this.y-15,Color.green);
	}
	
	public void Update(int mapX,ArrayList<ENEMY> tar,HOME[] home){
		this.mapX = mapX;
		this.target = tar;
		this.obj = home;
		
		if(warring){
			if(!this.getDie()){
				for(ENEMY e : tar){
					int atking = this.getATK()-e.def;
					if(!e.isDie())
					if(this._x+range < e._x){
						for(HOME h : home){
							if(h != null){
								if(!this.BOUNDINGBOX().intersects(h.BOUNDINGBOX()) || h.die()){
									this.inMode = MODE.walkR;
									this.x += this.speed;
									this.walk = true;
									break;
								}
								break;
							}
						}
						break;
					}else{
						if(modeIn == 'R') inMode = MODE.atk1R;
						if(modeIn == 'L') inMode = MODE.atk1L;
						
						if(delayAsp > 0)
							delayAsp--;
						else{
							if(atking <= 0)atking=1;
							
							arrow.add(new TARROW(this.modeIn,(float)(2+Math.random()*10),atking,this._x,this.y));
							dmgHit = true;
							delayAsp = aspd;
							break;
						}
						break;
					}
				}
			}
		}else{
			if(delayStop > 0)
				delayStop--;
			else{
				if(this.x >= this.defaultX){
					this.x -= this.speed;
					this.inMode = MODE.walkL;
					this.walk = true;
				}else this.inMode = MODE.stopR;
			}
		}
		
		for(TARROW arrows : arrow){
			if(arrows != null && !arrows.getRemove()){
				arrows.Update();
				arrows.drawSelf();
				arrows.setTarget(this.target,obj);
			}else{
				arrow.remove(arrows);
				break;
			}
		}
	}
}

class SOLIDER_AXE extends SOLIDER implements Serializable{
	public SOLIDER_AXE(){
		this.hpMax = 250;
		this.hp = hpMax;
		this.speed = 2;
		this.frame = new short[]{550,200,230,80};
		this.range = 30;
		this.atk = 20;
		this.name = "พลขวาน";
		this.sJob = SoliderJob.axe;
	}
	
	public void Load() throws SlickException {
		String dirOfStop = "Resource/animation/Solider/Solider5/Stop/";
		String dirOfWalk = "Resource/animation/Solider/Solider5/Walk/";
		String dirOfAttk = "Resource/animation/Solider/Solider5/Atk/";
		String dirOfDead = "Resource/animation/Solider/Solider5/Die/";
		
		this._stopR = new Animation(
			new Image[]{
				new Image(dirOfStop+"Sasr_0001.png"),
				new Image(dirOfStop+"Sasr_0002.png"),
			},frame[0]
		);
		
		this._stopL = new Animation(
			new Image[]{
				new Image(dirOfStop+"Sasl_0001.png"),
				new Image(dirOfStop+"Sasl_0001.png"),
			},frame[0]
		);
		
		this._walkR = new Animation(
			new Image[]{
				new Image(dirOfWalk+"Sawr_0001.png"),
				new Image(dirOfWalk+"Sawr_0002.png"),
				new Image(dirOfWalk+"Sawr_0003.png"),
				new Image(dirOfWalk+"Sawr_0002.png"),
			},frame[1]
		);
		
		this._walkL = new Animation(
			new Image[]{
				new Image(dirOfWalk+"Sawl_0001.png"),
				new Image(dirOfWalk+"Sawl_0002.png"),
				new Image(dirOfWalk+"Sawl_0003.png"),
				new Image(dirOfWalk+"Sawl_0002.png"),
			},frame[1]
		);
		
		this._atk1R = new Animation(
			new Image[]{
				new Image(dirOfAttk+"Saar_0001.png"),
				new Image(dirOfAttk+"Saar_0002.png"),
				new Image(dirOfAttk+"Saar_0003.png"),
				new Image(dirOfAttk+"Saar_0003.png"),
				new Image(dirOfAttk+"Saar_0003.png"),
			},frame[2]
		);
		
		this._atk1L = new Animation(
			new Image[]{
				new Image(dirOfAttk+"Saal_0001.png"),
				new Image(dirOfAttk+"Saal_0002.png"),
				new Image(dirOfAttk+"Saal_0003.png"),
				new Image(dirOfAttk+"Saal_0003.png"),
				new Image(dirOfAttk+"Saal_0003.png"),
			},frame[2]
		);
		
		this._dieR = new Animation(
			new Image[]{
				new Image(dirOfDead+"dieR.png")
			},frame[3]
		);
		
		this._dieL = new Animation(
			new Image[]{
				new Image(dirOfDead+"dieL.png")
			},frame[3]
		);
	}
	
	public void DrawSelf(){
		_x = x+this.mapX;
		if((this._x < 1024) && (this._x > 0))
			g.drawImage(shardow,_x,y+70);
			
		switch(inMode){
			case stopR:{
				g.drawAnimation(this._stopR,this._x,this.y);
				modeIn = 'R';
				walk = false;
				dmgHit = false;
				break;
			}
			case stopL:{
				g.drawAnimation(this._stopL,this._x,this.y);
				modeIn = 'L';
				walk = false;
				dmgHit = false;
				break;
			}
			case walkR:{
				g.drawAnimation(this._walkR,this._x,this.y);
				modeIn = 'R';
				inMode = MODE.stopR;
				walk = true;
				break;
			}
			case walkL:{
				g.drawAnimation(this._walkL,this._x,this.y);
				modeIn = 'L';
				inMode = MODE.stopL;
				walk = true;
				break;
			}
			case atk1R:{
				g.drawAnimation(this._atk1R,this._x,this.y);
				modeIn = 'R';
				inMode = MODE.stopR;
				dmgHit = true;
				break;
			}
			case atk1L:{
				g.drawAnimation(this._atk1L,this._x,this.y);
				modeIn = 'L';
				inMode = MODE.stopL;
				dmgHit = true;
				break;
			}
			case dieR :{
				g.drawAnimation(this._dieR,this._x,this.y);
				modeIn = 'R';
				break;
			}
			case dieL :{
				g.drawAnimation(this._dieL,this._x,this.y);
				modeIn = 'L';
				break;
			}
		}
		
		this.drawString(this.name,this._x+(55-font[0].getWidth(this.name))/2,this.y-15,Color.green);
	}
	
	public void Update(int mapX,ArrayList<ENEMY> tar,HOME[] home){
		this.mapX = mapX;
		
		if(warring){
			if(!this.getDie()){
				for(HOME h : home){
					if(h != null){
						if(this.BOUNDINGBOX().intersects(h.BOUNDINGBOX())){
							int atking = this.getATK()-h.def;
							if(modeIn == 'R') inMode = MODE.stopR;
							if(modeIn == 'L') inMode = MODE.stopL;
							
							if(!h.die()){
								if(modeIn == 'R'){
									inMode = MODE.atk1R;
									if(_atk1R.getFrame() == 0)hit=false;
									if(_atk1R.getFrame() == _atk1R.getFrameCount()-1 && !hit){
										if(atking > 0){
											h.hp -= atking;
											h.drawDmgFont2(atking+"",h._x,h.y-10);
											if(this._x > 0 && this._x < 1024)soundEffect[2].play();
											try{
												if((this._x < 1024) && (this._x > 0))
												(new Sound(FileList.bgsDir+"bgs17.wav")).play();
											}catch(SlickException error){
											}
										}else{
											h.hp -= 1;
											h.drawDmgFont2("!",h._x,h.y-10);
											if((this._x < 1024) && (this._x > 0))soundEffect[(int)(2+Math.random()*1)].play();
										}
										
										hit=true;
										dmgHit=true;
									}
								}
								if(modeIn == 'L'){
									inMode = MODE.atk1L;
									if(_atk1L.getFrame() == 0)hit=false;
									if(_atk1L.getFrame() == _atk1L.getFrameCount()-1 && !hit){
										if(atking > 0){
											h.hp -= atking;
											h.drawDmgFont2(atking+"",h._x+15,h.y-10);
											if(this._x > 0 && this._x < 1024)soundEffect[2].play();
											try{
												if((this._x < 1024) && (this._x > 0))
												(new Sound(FileList.bgsDir+"bgs17.wav")).play();
											}catch(SlickException error){
											}
										}else{
											h.hp -= 1;
											h.drawDmgFont2(1+"!",h._x+15,h.y-10);
											if((this._x < 1024) && (this._x > 0))soundEffect[(int)(2+Math.random()*1)].play();
										}
											
										hit=true;
										dmgHit=true;
									}
								}
								return;
							}
						}
					}
				}
				
				for(ENEMY tSelf : tar){
					if(this.BOUNDINGBOX().intersects(tSelf.boundingBoxOfHit())){
						int atking = this.getATK()-tSelf.def;
						if(modeIn == 'R') inMode = MODE.stopR;
						if(modeIn == 'L') inMode = MODE.stopL;
						
						if(!tSelf.isDie()){
							if(modeIn == 'L' && !walk){
								inMode = MODE.valueOf("atk"+1+"L");
								if(_atk1L.getFrame() == 0)hit=false;
								if(_atk1L.getFrame() == _atk1L.getFrameCount()-1 && !hit){
									if(atking > 0){
										tSelf.hp -= atking;
										tSelf.drawDmgFont2(atking+"",tSelf._x+15,tSelf.y-10);
										if(this._x > 0 && this._x < 1024)soundEffect[2].play();
										try{
											if((this._x < 1024) && (this._x > 0))
											(new Sound(FileList.bgsDir+"bgs17.wav")).play();
										}catch(SlickException error){
										}
									}else{
										tSelf.hp -= 1;
										tSelf.drawDmgFont2(1+"!",tSelf._x+15,tSelf.y-10);
										if((this._x < 1024) && (this._x > 0))soundEffect[(int)(2+Math.random()*1)].play();
									}
									
									tSelf.addEffect(modeIn,tSelf.CenterOf('x'),tSelf.CenterOf('y'));
									hit=true;
									dmgHit=true;
								}
							}
							if(modeIn == 'R' && !walk){
								inMode = MODE.valueOf("atk"+1+"R");
								if(_atk1R.getFrame() == 0)hit=false;
								if(_atk1R.getFrame() == _atk1R.getFrameCount()-1 && !hit){
									if(atking > 0){
										tSelf.hp -= atking;
										tSelf.drawDmgFont2(atking+"",tSelf._x,tSelf.y-10);
										if(this._x > 0 && this._x < 1024)soundEffect[2].play();
										try{
											if((this._x < 1024) && (this._x > 0))
											(new Sound(FileList.bgsDir+"bgs17.wav")).play();
										}catch(SlickException error){
										}
									}else{
										tSelf.hp -= 1;
										tSelf.drawDmgFont2("!",tSelf._x,tSelf.y-10);
										if((this._x < 1024) && (this._x > 0))soundEffect[(int)(2+Math.random()*1)].play();
									}
									
									tSelf.addEffect(modeIn,tSelf.CenterOf('x'),tSelf.CenterOf('y'));
									hit=true;
									dmgHit=true;
								}
							}
							return;
						}
					}else{
						if(!tSelf.isDie() && this._x < tSelf._x){
							for(HOME h : home){
								if(h != null){
									if(!this.BOUNDINGBOX().intersects(h.BOUNDINGBOX()) || h.die()){
										this.inMode = MODE.walkR;
										this.x += this.speed;
										this.walk = true;
										break;
									}
									break;
								}
							}
							break;
						}
					}
					continue;
				}
			}
		}else{
			if(delayStop > 0)
				delayStop--;
			else{
				if(this.x >= this.defaultX){
					this.x -= this.speed;
					this.inMode = MODE.walkL;
					this.walk = true;
				}else this.inMode = MODE.stopR;
			}
		}
	}
}

class SOLIDER_TWOHANDSWORD extends SOLIDER implements Serializable{
	public SOLIDER_TWOHANDSWORD(){
		this.hpMax = 450;
		this.hp = hpMax;
		this.speed = 2;
		this.frame = new short[]{550,200,230,80};
		this.range = 30;
		this.atk = 40;
		this.name = "ยอดฝีมือ";
		this.sJob = SoliderJob.twohand;
		try{
			this.soundDie = new Sound(FileList.bgsDir+"mDie.wav");
		}catch(SlickException e){
		}
	}
	
	public void Load() throws SlickException {
		String dirOfStop = "Resource/animation/Solider/Solider3/Stop/";
		String dirOfWalk = "Resource/animation/Solider/Solider3/Walk/";
		String dirOfAttk = "Resource/animation/Solider/Solider3/Atk/";
		String dirOfDead = "Resource/animation/Solider/Solider3/Die/";
		
		this._stopR = new Animation(
			new Image[]{
				new Image(dirOfStop+"Sasr_0001.png"),
				new Image(dirOfStop+"Sasr_0002.png"),
			},frame[0]
		);
		
		this._stopL = new Animation(
			new Image[]{
				new Image(dirOfStop+"Sasl_0001.png"),
				new Image(dirOfStop+"Sasl_0001.png"),
			},frame[0]
		);
		
		this._walkR = new Animation(
			new Image[]{
				new Image(dirOfWalk+"Sawr_0001.png"),
				new Image(dirOfWalk+"Sawr_0002.png"),
				new Image(dirOfWalk+"Sawr_0003.png"),
				new Image(dirOfWalk+"Sawr_0002.png"),
			},frame[1]
		);
		
		this._walkL = new Animation(
			new Image[]{
				new Image(dirOfWalk+"Sawl_0001.png"),
				new Image(dirOfWalk+"Sawl_0002.png"),
				new Image(dirOfWalk+"Sawl_0003.png"),
				new Image(dirOfWalk+"Sawl_0002.png"),
			},frame[1]
		);
		
		this._atk1R = new Animation(
			new Image[]{
				new Image(dirOfAttk+"Saar_0001.png"),
				new Image(dirOfAttk+"Saar_0002.png"),
				new Image(dirOfAttk+"Saar_0003.png"),
				new Image(dirOfAttk+"Saar_0003.png"),
				new Image(dirOfAttk+"Saar_0003.png"),
			},frame[2]
		);
		
		this._atk1L = new Animation(
			new Image[]{
				new Image(dirOfAttk+"Saal_0001.png"),
				new Image(dirOfAttk+"Saal_0002.png"),
				new Image(dirOfAttk+"Saal_0003.png"),
				new Image(dirOfAttk+"Saal_0003.png"),
				new Image(dirOfAttk+"Saal_0003.png"),
			},frame[2]
		);
		
		this._dieR = new Animation(
			new Image[]{
				new Image(dirOfDead+"dieR.png")
			},frame[3]
		);
		
		this._dieL = new Animation(
			new Image[]{
				new Image(dirOfDead+"dieL.png")
			},frame[3]
		);
	}
	
	public void DrawSelf(){
		_x = x+this.mapX;
		if((this._x < 1024) && (this._x > 0))
			g.drawImage(shardow,_x,y+70);
			
		switch(inMode){
			case stopR:{
				g.drawAnimation(this._stopR,this._x,this.y);
				modeIn = 'R';
				walk = false;
				dmgHit = false;
				break;
			}
			case stopL:{
				g.drawAnimation(this._stopL,this._x,this.y);
				modeIn = 'L';
				walk = false;
				dmgHit = false;
				break;
			}
			case walkR:{
				g.drawAnimation(this._walkR,this._x,this.y);
				modeIn = 'R';
				inMode = MODE.stopR;
				walk = true;
				break;
			}
			case walkL:{
				g.drawAnimation(this._walkL,this._x,this.y);
				modeIn = 'L';
				inMode = MODE.stopL;
				walk = true;
				break;
			}
			case atk1R:{
				g.drawAnimation(this._atk1R,this._x,this.y);
				modeIn = 'R';
				inMode = MODE.stopR;
				dmgHit = true;
				break;
			}
			case atk1L:{
				g.drawAnimation(this._atk1L,this._x,this.y);
				modeIn = 'L';
				inMode = MODE.stopL;
				dmgHit = true;
				break;
			}
			case dieR :{
				g.drawAnimation(this._dieR,this._x,this.y);
				modeIn = 'R';
				break;
			}
			case dieL :{
				g.drawAnimation(this._dieL,this._x,this.y);
				modeIn = 'L';
				break;
			}
		}
		
		this.drawString(this.name,this._x+(55-font[0].getWidth(this.name))/2,this.y-15,Color.green);
	}
	
	public void Update(int mapX,ArrayList<ENEMY> tar,HOME[] home){
		this.mapX = mapX;
		
		if(warring){
			if(!this.getDie()){
				for(HOME h : home){
					if(h != null){
						if(this.BOUNDINGBOX().intersects(h.BOUNDINGBOX())){
							int atking = this.getATK()-h.def;
							if(modeIn == 'R') inMode = MODE.stopR;
							if(modeIn == 'L') inMode = MODE.stopL;
							
							if(!h.die()){
								if(modeIn == 'R'){
									inMode = MODE.atk1R;
									if(_atk1R.getFrame() == 0)hit=false;
									if(_atk1R.getFrame() == _atk1R.getFrameCount()-1 && !hit){
										if(atking > 0){
											h.hp -= atking;
											h.drawDmgFont2(atking+"",h._x,h.y-10);
											if(this._x > 0 && this._x < 1024)soundEffect[2].play();
											try{
												if((this._x < 1024) && (this._x > 0))
												(new Sound(FileList.bgsDir+"bgs17.wav")).play();
											}catch(SlickException error){
											}
										}else{
											h.hp -= 1;
											h.drawDmgFont2("!",h._x,h.y-10);
											if((this._x < 1024) && (this._x > 0))soundEffect[(int)(2+Math.random()*1)].play();
										}
										
										hit=true;
										dmgHit=true;
									}
								}
								if(modeIn == 'L'){
									inMode = MODE.atk1L;
									if(_atk1L.getFrame() == 0)hit=false;
									if(_atk1L.getFrame() == _atk1L.getFrameCount()-1 && !hit){
										if(atking > 0){
											h.hp -= atking;
											h.drawDmgFont2(atking+"",h._x+15,h.y-10);
											if(this._x > 0 && this._x < 1024)soundEffect[2].play();
											try{
												if((this._x < 1024) && (this._x > 0))
												(new Sound(FileList.bgsDir+"bgs17.wav")).play();
											}catch(SlickException error){
											}
										}else{
											h.hp -= 1;
											h.drawDmgFont2(1+"!",h._x+15,h.y-10);
											if((this._x < 1024) && (this._x > 0))soundEffect[(int)(2+Math.random()*1)].play();
										}
											
										hit=true;
										dmgHit=true;
									}
								}
								return;
							}
						}
					}
				}
				
				for(ENEMY tSelf : tar){
					if(this.BOUNDINGBOX().intersects(tSelf.boundingBoxOfHit())){
						int atking = this.getATK()-tSelf.def;
						if(modeIn == 'R') inMode = MODE.stopR;
						if(modeIn == 'L') inMode = MODE.stopL;
						
						if(!tSelf.isDie()){
							if(modeIn == 'L' && !walk){
								inMode = MODE.valueOf("atk"+1+"L");
								if(_atk1L.getFrame() == 0)hit=false;
								if(_atk1L.getFrame() == _atk1L.getFrameCount()-1 && !hit){
									if(atking > 0){
										tSelf.hp -= atking;
										tSelf.drawDmgFont2(atking+"",tSelf._x+15,tSelf.y-10);
										if(this._x > 0 && this._x < 1024)soundEffect[2].play();
										try{
											if((this._x < 1024) && (this._x > 0))
											(new Sound(FileList.bgsDir+"bgs17.wav")).play();
										}catch(SlickException error){
										}
									}else{
										tSelf.hp -= 1;
										tSelf.drawDmgFont2(1+"!",tSelf._x+15,tSelf.y-10);
										if((this._x < 1024) && (this._x > 0))soundEffect[(int)(2+Math.random()*1)].play();
									}
									
									tSelf.addEffect(modeIn,tSelf.CenterOf('x'),tSelf.CenterOf('y'));
									hit=true;
									dmgHit=true;
								}
							}
							if(modeIn == 'R' && !walk){
								inMode = MODE.valueOf("atk"+1+"R");
								if(_atk1R.getFrame() == 0)hit=false;
								if(_atk1R.getFrame() == _atk1R.getFrameCount()-1 && !hit){
									if(atking > 0){
										tSelf.hp -= atking;
										tSelf.drawDmgFont2(atking+"",tSelf._x,tSelf.y-10);
										if(this._x > 0 && this._x < 1024)soundEffect[2].play();
										try{
											if((this._x < 1024) && (this._x > 0))
											(new Sound(FileList.bgsDir+"bgs17.wav")).play();
										}catch(SlickException error){
										}
									}else{
										tSelf.hp -= 1;
										tSelf.drawDmgFont2("!",tSelf._x,tSelf.y-10);
										if((this._x < 1024) && (this._x > 0))soundEffect[(int)(2+Math.random()*1)].play();
									}
									
									tSelf.addEffect(modeIn,tSelf.CenterOf('x'),tSelf.CenterOf('y'));
									hit=true;
									dmgHit=true;
								}
							}
							return;
						}
					}else{
						if(!tSelf.isDie() && this._x < tSelf._x){
							for(HOME h : home){
								if(h != null){
									if(!this.BOUNDINGBOX().intersects(h.BOUNDINGBOX()) || h.die()){
										this.inMode = MODE.walkR;
										this.x += this.speed;
										this.walk = true;
										break;
									}
									break;
								}
							}
							break;
						}
					}
					continue;
				}
			}
		}else{
			if(delayStop > 0)
				delayStop--;
			else{
				if(this.x >= this.defaultX){
					this.x -= this.speed;
					this.inMode = MODE.walkL;
					this.walk = true;
				}else this.inMode = MODE.stopR;
			}
		}
	}
}

class SOLIDER_HERO extends SOLIDER implements Serializable{
	public SOLIDER_HERO(){
		this.hpMax = 700;
		this.hp = hpMax;
		this.speed = 2;
		this.frame = new short[]{550,200,230,80};
		this.range = 30;
		this.atk = 60;
		this.name = "สุดยอดฝีมือ";
		this.sJob = SoliderJob.hero;
		
		try{
			this.soundDie = new Sound(FileList.bgsDir+"mDie.wav");
		}catch(SlickException e){
		}
	}
	
	public void Load() throws SlickException {
		String dirOfStop = "Resource/animation/Solider/Solider4/Stop/";
		String dirOfWalk = "Resource/animation/Solider/Solider4/Walk/";
		String dirOfAttk = "Resource/animation/Solider/Solider4/Atk/";
		String dirOfDead = "Resource/animation/Solider/Solider4/Die/";
		
		this._stopR = new Animation(
			new Image[]{
				new Image(dirOfStop+"shsr_0001.png"),
				new Image(dirOfStop+"shsr_0002.png"),
			},frame[0]
		);
		
		this._stopL = new Animation(
			new Image[]{
				new Image(dirOfStop+"shsl_0001.png"),
				new Image(dirOfStop+"shsl_0002.png"),
			},frame[0]
		);
		
		this._walkR = new Animation(
			new Image[]{
				new Image(dirOfWalk+"shwr_0001.png"),
				new Image(dirOfWalk+"shwr_0002.png"),
				new Image(dirOfWalk+"shwr_0003.png"),
				new Image(dirOfWalk+"shwr_0002.png"),
			},frame[1]
		);
		
		this._walkL = new Animation(
			new Image[]{
				new Image(dirOfWalk+"shwl_0001.png"),
				new Image(dirOfWalk+"shwl_0002.png"),
				new Image(dirOfWalk+"shwl_0003.png"),
				new Image(dirOfWalk+"shwl_0002.png"),
			},frame[1]
		);
		
		this._atk1R = new Animation(
			new Image[]{
				new Image(dirOfAttk+"shar_0001.png"),
				new Image(dirOfAttk+"shar_0002.png"),
				new Image(dirOfAttk+"shar_0003.png"),
				new Image(dirOfAttk+"shar_0003.png"),
				new Image(dirOfAttk+"shar_0003.png"),
			},frame[2]
		);
		
		this._atk1L = new Animation(
			new Image[]{
				new Image(dirOfAttk+"shal_0001.png"),
				new Image(dirOfAttk+"shal_0002.png"),
				new Image(dirOfAttk+"shal_0003.png"),
				new Image(dirOfAttk+"shal_0003.png"),
				new Image(dirOfAttk+"shal_0003.png"),
			},frame[2]
		);
		
		this._dieR = new Animation(
			new Image[]{
				new Image(dirOfDead+"DieR.png")
			},frame[3]
		);
		
		this._dieL = new Animation(
			new Image[]{
				new Image(dirOfDead+"DieL.png")
			},frame[3]
		);
	}
	
	public void DrawSelf(){
		_x = x+this.mapX;
		if((this._x < 1024) && (this._x > 0))
			g.drawImage(shardow,_x,y+70);
			
		switch(inMode){
			case stopR:{
				g.drawAnimation(this._stopR,this._x-35,this.y);
				modeIn = 'R';
				walk = false;
				dmgHit = false;
				break;
			}
			case stopL:{
				g.drawAnimation(this._stopL,this._x,this.y);
				modeIn = 'L';
				walk = false;
				dmgHit = false;
				break;
			}
			case walkR:{
				g.drawAnimation(this._walkR,this._x-35,this.y);
				modeIn = 'R';
				inMode = MODE.stopR;
				walk = true;
				break;
			}
			case walkL:{
				g.drawAnimation(this._walkL,this._x,this.y);
				modeIn = 'L';
				inMode = MODE.stopL;
				walk = true;
				break;
			}
			case atk1R:{
				g.drawAnimation(this._atk1R,this._x-35,this.y);
				modeIn = 'R';
				inMode = MODE.stopR;
				dmgHit = true;
				break;
			}
			case atk1L:{
				g.drawAnimation(this._atk1L,this._x,this.y);
				modeIn = 'L';
				inMode = MODE.stopL;
				dmgHit = true;
				break;
			}
			case dieR :{
				g.drawAnimation(this._dieR,this._x-35,this.y);
				modeIn = 'R';
				break;
			}
			case dieL :{
				g.drawAnimation(this._dieL,this._x,this.y);
				modeIn = 'L';
				break;
			}
		}
		
		this.drawString(this.name,this._x+(55-font[0].getWidth(this.name))/2,this.y-15,Color.green);
	}
	
	public void Update(int mapX,ArrayList<ENEMY> tar,HOME[] home){
		this.mapX = mapX;
		
		if(warring){
			if(!this.getDie()){
				for(HOME h : home){
					if(h != null){
						if(this.BOUNDINGBOX().intersects(h.BOUNDINGBOX())){
							int atking = this.getATK()-h.def;
							if(modeIn == 'R') inMode = MODE.stopR;
							if(modeIn == 'L') inMode = MODE.stopL;
							
							if(!h.die()){
								if(modeIn == 'R'){
									inMode = MODE.atk1R;
									if(_atk1R.getFrame() == 0)hit=false;
									if(_atk1R.getFrame() == _atk1R.getFrameCount()-1 && !hit){
										if(atking > 0){
											h.hp -= atking;
											h.drawDmgFont2(atking+"",h._x,h.y-10);
											if(this._x > 0 && this._x < 1024)soundEffect[2].play();
											try{
												if((this._x < 1024) && (this._x > 0))
												(new Sound(FileList.bgsDir+"bgs17.wav")).play();
											}catch(SlickException error){
											}
										}else{
											h.hp -= 1;
											h.drawDmgFont2("!",h._x,h.y-10);
											if((this._x < 1024) && (this._x > 0))soundEffect[(int)(2+Math.random()*1)].play();
										}
										
										hit=true;
										dmgHit=true;
									}
								}
								if(modeIn == 'L'){
									inMode = MODE.atk1L;
									if(_atk1L.getFrame() == 0)hit=false;
									if(_atk1L.getFrame() == _atk1L.getFrameCount()-1 && !hit){
										if(atking > 0){
											h.hp -= atking;
											h.drawDmgFont2(atking+"",h._x+15,h.y-10);
											if(this._x > 0 && this._x < 1024)soundEffect[2].play();
											try{
												if((this._x < 1024) && (this._x > 0))
												(new Sound(FileList.bgsDir+"bgs17.wav")).play();
											}catch(SlickException error){
											}
										}else{
											h.hp -= 1;
											h.drawDmgFont2(1+"!",h._x+15,h.y-10);
											if((this._x < 1024) && (this._x > 0))soundEffect[(int)(2+Math.random()*1)].play();
										}
											
										hit=true;
										dmgHit=true;
									}
								}
								return;
							}
						}
					}
				}
				
				for(ENEMY tSelf : tar){
					if(this.BOUNDINGBOX().intersects(tSelf.boundingBoxOfHit())){
						int atking = this.getATK()-tSelf.def;
						if(modeIn == 'R') inMode = MODE.stopR;
						if(modeIn == 'L') inMode = MODE.stopL;
						
						if(!tSelf.isDie()){
							if(modeIn == 'L' && !walk){
								inMode = MODE.valueOf("atk"+1+"L");
								if(_atk1L.getFrame() == 0)hit=false;
								if(_atk1L.getFrame() == _atk1L.getFrameCount()-1 && !hit){
									if(atking > 0){
										tSelf.hp -= atking;
										tSelf.drawDmgFont2(atking+"",tSelf._x+15,tSelf.y-10);
										if(this._x > 0 && this._x < 1024)soundEffect[2].play();
										try{
											if((this._x < 1024) && (this._x > 0))
											(new Sound(FileList.bgsDir+"bgs17.wav")).play();
										}catch(SlickException error){
										}
									}else{
										tSelf.hp -= 1;
										tSelf.drawDmgFont2(1+"!",tSelf._x+15,tSelf.y-10);
										if((this._x < 1024) && (this._x > 0))soundEffect[(int)(2+Math.random()*1)].play();
									}
									
									tSelf.addEffect(modeIn,tSelf.CenterOf('x'),tSelf.CenterOf('y'));
									hit=true;
									dmgHit=true;
								}
							}
							if(modeIn == 'R' && !walk){
								inMode = MODE.valueOf("atk"+1+"R");
								if(_atk1R.getFrame() == 0)hit=false;
								if(_atk1R.getFrame() == _atk1R.getFrameCount()-1 && !hit){
									if(atking > 0){
										tSelf.hp -= atking;
										tSelf.drawDmgFont2(atking+"",tSelf._x,tSelf.y-10);
										if(this._x > 0 && this._x < 1024)soundEffect[2].play();
										try{
											if((this._x < 1024) && (this._x > 0))
											(new Sound(FileList.bgsDir+"bgs17.wav")).play();
										}catch(SlickException error){
										}
									}else{
										tSelf.hp -= 1;
										tSelf.drawDmgFont2("!",tSelf._x,tSelf.y-10);
										if((this._x < 1024) && (this._x > 0))soundEffect[(int)(2+Math.random()*1)].play();
									}
									
									tSelf.addEffect(modeIn,tSelf.CenterOf('x'),tSelf.CenterOf('y'));
									hit=true;
									dmgHit=true;
								}
							}
							return;
						}
					}else{
						if(!tSelf.isDie() && this._x < tSelf._x){
							for(HOME h : home){
								if(h != null){
									if(!this.BOUNDINGBOX().intersects(h.BOUNDINGBOX()) || h.die()){
										this.inMode = MODE.walkR;
										this.x += this.speed;
										this.walk = true;
										break;
									}
									break;
								}
							}
							break;
						}
					}
					continue;
				}
			}
		}else{
			if(delayStop > 0)
				delayStop--;
			else{
				if(this.x >= this.defaultX){
					this.x -= this.speed;
					this.inMode = MODE.walkL;
					this.walk = true;
				}else this.inMode = MODE.stopR;
			}
		}
	}
}

abstract class MYSOLIDER extends SOLIDER implements Serializable{
	protected CHARACTER hero;
	protected int eyerange;
	protected boolean protectMode = false;
	
	protected void rndMove(){
		Random rnd = new Random();
		eyerange = (int)(80+rnd.nextInt(80));
	}
	public void MasterTarget(CHARACTER h){
		hero = h;
	}
}

class MY_SOLIDER_SWORD extends MYSOLIDER implements Serializable{
	public MY_SOLIDER_SWORD(){
		this.hpMax = 120;
		this.hp = hpMax;
		this.speed = 2;
		this.frame = new short[]{550,200,230,80};
		this.range = 30;
		this.atk = 10;
		this.name = "พลดาบ ผู้ซื่อสัตย์";
		this.sJob = SoliderJob.sword;
		this.rndMove();
	}
	
	public void Load() throws SlickException {
		String dirOfStop = "Resource/animation/Solider/Solider1/Stop/";
		String dirOfWalk = "Resource/animation/Solider/Solider1/Walk/";
		String dirOfAttk = "Resource/animation/Solider/Solider1/Atk/";
		String dirOfDead = "Resource/animation/Solider/Solider1/Die/";
		
		this._stopR = new Animation(
			new Image[]{
				new Image(dirOfStop+"Swsr_0001.png"),
				new Image(dirOfStop+"Swsr_0002.png"),
			},frame[0]
		);
		
		this._stopL = new Animation(
			new Image[]{
				new Image(dirOfStop+"Swsl_0001.png"),
				new Image(dirOfStop+"Swsl_0002.png"),
			},frame[0]
		);
		
		this._walkR = new Animation(
			new Image[]{
				new Image(dirOfWalk+"Swwr_0001.png"),
				new Image(dirOfWalk+"Swwr_0002.png"),
				new Image(dirOfWalk+"Swwr_0003.png"),
				new Image(dirOfWalk+"Swwr_0002.png"),
			},frame[1]
		);
		
		this._walkL = new Animation(
			new Image[]{
				new Image(dirOfWalk+"Swwl_0001.png"),
				new Image(dirOfWalk+"Swwl_0002.png"),
				new Image(dirOfWalk+"Swwl_0003.png"),
				new Image(dirOfWalk+"Swwl_0002.png"),
			},frame[1]
		);
		
		this._atk1R = new Animation(
			new Image[]{
				new Image(dirOfAttk+"Swar_0001.png"),
				new Image(dirOfAttk+"Swar_0002.png"),
				new Image(dirOfAttk+"Swar_0003.png"),
				new Image(dirOfAttk+"Swar_0003.png"),
				new Image(dirOfAttk+"Swar_0003.png"),
			},frame[2]
		);
		
		this._atk1L = new Animation(
			new Image[]{
				new Image(dirOfAttk+"Swal_0001.png"),
				new Image(dirOfAttk+"Swal_0002.png"),
				new Image(dirOfAttk+"Swal_0003.png"),
				new Image(dirOfAttk+"Swal_0003.png"),
			},frame[2]
		);
		
		this._dieR = new Animation(
			new Image[]{
				new Image(dirOfDead+"Swdr.png")
			},frame[3]
		);
		
		this._dieL = new Animation(
			new Image[]{
				new Image(dirOfDead+"Swdl.png")
			},frame[3]
		);
	}
	
	public void DrawSelf(){
		_x = x+this.mapX;
		if((this._x < 1024) && (this._x > 0))
			g.drawImage(shardow,_x,y+70);
			
		switch(inMode){
			case stopR:{
				g.drawAnimation(this._stopR,this._x,this.y);
				modeIn = 'R';
				walk = false;
				dmgHit = false;
				break;
			}
			case stopL:{
				g.drawAnimation(this._stopL,this._x,this.y);
				modeIn = 'L';
				walk = false;
				dmgHit = false;
				break;
			}
			case walkR:{
				g.drawAnimation(this._walkR,this._x,this.y);
				modeIn = 'R';
				inMode = MODE.stopR;
				walk = true;
				break;
			}
			case walkL:{
				g.drawAnimation(this._walkL,this._x,this.y);
				modeIn = 'L';
				inMode = MODE.stopL;
				walk = true;
				break;
			}
			case atk1R:{
				g.drawAnimation(this._atk1R,this._x,this.y);
				modeIn = 'R';
				inMode = MODE.stopR;
				dmgHit = true;
				break;
			}
			case atk1L:{
				g.drawAnimation(this._atk1L,this._x,this.y);
				modeIn = 'L';
				inMode = MODE.stopL;
				dmgHit = true;
				break;
			}
			case dieR :{
				g.drawAnimation(this._dieR,this._x,this.y);
				modeIn = 'R';
				break;
			}
			case dieL :{
				g.drawAnimation(this._dieL,this._x,this.y);
				modeIn = 'L';
				break;
			}
		}
		
		this.drawString(this.name,this._x+(55-font[0].getWidth(this.name))/2,this.y-15,Color.green);
	}
	
	public void Update(int mapX,ArrayList<ENEMY> tar,HOME[] home){
		this.mapX = mapX;

		if(!hero.getDie() && !this.getDie() && !dmgHit){
			if(hero.getMode() == 'R'){
				if(hero._x+eyerange >= this._x){
					this.x += speed;
					this.inMode = MODE.walkR;
				}else{
					if(inMode == MODE.walkR){
						inMode = MODE.stopR;
						this.rndMove();
					}
					if(inMode == MODE.walkL){
						inMode = MODE.stopL;
						this.rndMove();
					}
				}
			}else if(hero.getMode() == 'L'){
				if(hero._x-eyerange <= this._x){
					this.x -= speed;
					this.inMode = MODE.walkL;
				}else{
					if(inMode == MODE.walkR){
						inMode = MODE.stopR;
						this.rndMove();
					}
					if(inMode == MODE.walkL){
						inMode = MODE.stopL;
						this.rndMove();
					}
				}
			}
		}
		
		for(ENEMY e : tar){
			if(e != null){
				if(this.BOUNDINGBOX().intersects(e.boundingBoxOfHit())){
					int atking = this.getATK()-e.def;
					if(modeIn == 'R') inMode = MODE.stopR;
					if(modeIn == 'L') inMode = MODE.stopL;
						
					if(!e.isDie()){
						if(modeIn == 'L' && !walk){
								inMode = MODE.valueOf("atk"+1+"L");
								if(_atk1L.getFrame() == 0)hit=false;
								if(_atk1L.getFrame() == _atk1L.getFrameCount()-1 && !hit){
									if(atking > 0){
										e.hp -= atking;
										e.drawDmgFont2(atking+"",e._x+15,e.y-10);
										if(this._x > 0 && this._x < 1024)soundEffect[2].play();
										try{
											if((this._x < 1024) && (this._x > 0))
											(new Sound(FileList.bgsDir+"bgs17.wav")).play();
										}catch(SlickException error){
										}
									}else{
										e.hp -= 1;
										e.drawDmgFont2(1+"!",e._x+15,e.y-10);
										if((this._x < 1024) && (this._x > 0))soundEffect[(int)(2+Math.random()*1)].play();
									}
									
									e.addEffect(modeIn,e._x,e.y);
									hit=true;
									dmgHit=true;
								}
							}
						if(modeIn == 'R' && !walk){
							inMode = MODE.valueOf("atk"+1+"R");
							if(_atk1R.getFrame() == 0)hit=false;
							if(_atk1R.getFrame() == _atk1R.getFrameCount()-1 && !hit){
								if(atking > 0){
									e.hp -= atking;
									e.drawDmgFont2(atking+"",e._x,e.y-10);
									if(this._x > 0 && this._x < 1024)soundEffect[2].play();
									try{
										if((this._x < 1024) && (this._x > 0))
										(new Sound(FileList.bgsDir+"bgs17.wav")).play();
									}catch(SlickException error){
									}
								}else{
									e.hp -= 1;
									e.drawDmgFont2("!",e._x,e.y-10);
									if((this._x < 1024) && (this._x > 0))soundEffect[(int)(2+Math.random()*1)].play();
								}
								
								e.addEffect(modeIn,e.CenterOf('x'),e.CenterOf('y'));
								hit=true;
								dmgHit=true;
							}
						}
						return;
					}
				}
			}
		}
		
		for(HOME h : home){
			if(h!=null){
				if(!h.die()){
					if(this.BOUNDINGBOX().intersects(h.BOUNDINGBOX())){
						int atking = this.getATK()-h.def;
						
						if(modeIn == 'R') inMode = MODE.stopR;
						if(modeIn == 'L') inMode = MODE.stopL;
						
						if(modeIn == 'R'){
									inMode = MODE.atk1R;
									if(_atk1R.getFrame() == 0)hit=false;
									if(_atk1R.getFrame() == _atk1R.getFrameCount()-1 && !hit){
										if(atking > 0){
											h.hp -= atking;
											h.drawDmgFont2(atking+"",h._x,h.y-10);
											if(this._x > 0 && this._x < 1024)soundEffect[2].play();
											try{
												if((this._x < 1024) && (this._x > 0))
												(new Sound(FileList.bgsDir+"bgs17.wav")).play();
											}catch(SlickException error){
											}
										}else{
											h.hp -= 1;
											h.drawDmgFont2("!",h._x,h.y-10);
											if((this._x < 1024) && (this._x > 0))soundEffect[(int)(2+Math.random()*1)].play();
										}
										
										hit=true;
										dmgHit=true;
									}
								}
						if(modeIn == 'L'){
							inMode = MODE.atk1L;
							if(_atk1L.getFrame() == 0)hit=false;
								if(_atk1L.getFrame() == _atk1L.getFrameCount()-1 && !hit){
									if(atking > 0){
										h.hp -= atking;
										h.drawDmgFont2(atking+"",h._x+15,h.y-10);
										if(this._x > 0 && this._x < 1024)soundEffect[2].play();
										try{
											if((this._x < 1024) && (this._x > 0))
											(new Sound(FileList.bgsDir+"bgs17.wav")).play();
										}catch(SlickException error){
										}
									}else{
										h.hp -= 1;
										h.drawDmgFont2(1+"!",h._x+15,h.y-10);
										if((this._x < 1024) && (this._x > 0))soundEffect[(int)(2+Math.random()*1)].play();
									}
											
									hit=true;
									dmgHit=true;
								}
							}
						return;
					}
				}
			}
		}
	
	}
}

class MY_SOLIDER_AXE extends MYSOLIDER implements Serializable{
	public MY_SOLIDER_AXE(){
		this.hpMax = 250;
		this.hp = hpMax;
		this.speed = 2;
		this.frame = new short[]{550,200,230,80};
		this.range = 30;
		this.atk = 20;
		this.name = "พลขวาน ผู้ซื่อสัตย์";
		this.rndMove();
		this.sJob = SoliderJob.axe;
		try{
			this.soundDie = new Sound(FileList.bgsDir+"mDie.wav");
		}catch(SlickException e){
		}
	}
	
	public void Load() throws SlickException {
		String dirOfStop = "Resource/animation/Solider/Solider5/Stop/";
		String dirOfWalk = "Resource/animation/Solider/Solider5/Walk/";
		String dirOfAttk = "Resource/animation/Solider/Solider5/Atk/";
		String dirOfDead = "Resource/animation/Solider/Solider5/Die/";
		
		this._stopR = new Animation(
			new Image[]{
				new Image(dirOfStop+"Sasr_0001.png"),
				new Image(dirOfStop+"Sasr_0002.png"),
			},frame[0]
		);
		
		this._stopL = new Animation(
			new Image[]{
				new Image(dirOfStop+"Sasl_0001.png"),
				new Image(dirOfStop+"Sasl_0001.png"),
			},frame[0]
		);
		
		this._walkR = new Animation(
			new Image[]{
				new Image(dirOfWalk+"Sawr_0001.png"),
				new Image(dirOfWalk+"Sawr_0002.png"),
				new Image(dirOfWalk+"Sawr_0003.png"),
				new Image(dirOfWalk+"Sawr_0002.png"),
			},frame[1]
		);
		
		this._walkL = new Animation(
			new Image[]{
				new Image(dirOfWalk+"Sawl_0001.png"),
				new Image(dirOfWalk+"Sawl_0002.png"),
				new Image(dirOfWalk+"Sawl_0003.png"),
				new Image(dirOfWalk+"Sawl_0002.png"),
			},frame[1]
		);
		
		this._atk1R = new Animation(
			new Image[]{
				new Image(dirOfAttk+"Saar_0001.png"),
				new Image(dirOfAttk+"Saar_0002.png"),
				new Image(dirOfAttk+"Saar_0003.png"),
				new Image(dirOfAttk+"Saar_0003.png"),
				new Image(dirOfAttk+"Saar_0003.png"),
			},frame[2]
		);
		
		this._atk1L = new Animation(
			new Image[]{
				new Image(dirOfAttk+"Saal_0001.png"),
				new Image(dirOfAttk+"Saal_0002.png"),
				new Image(dirOfAttk+"Saal_0003.png"),
				new Image(dirOfAttk+"Saal_0003.png"),
				new Image(dirOfAttk+"Saal_0003.png"),
			},frame[2]
		);
		
		this._dieR = new Animation(
			new Image[]{
				new Image(dirOfDead+"dieR.png")
			},frame[3]
		);
		
		this._dieL = new Animation(
			new Image[]{
				new Image(dirOfDead+"dieL.png")
			},frame[3]
		);
	}
	
	public void DrawSelf(){
		_x = x+this.mapX;
		if((this._x < 1024) && (this._x > 0))
			g.drawImage(shardow,_x,y+70);
			
		switch(inMode){
			case stopR:{
				g.drawAnimation(this._stopR,this._x,this.y);
				modeIn = 'R';
				walk = false;
				dmgHit = false;
				break;
			}
			case stopL:{
				g.drawAnimation(this._stopL,this._x,this.y);
				modeIn = 'L';
				walk = false;
				dmgHit = false;
				break;
			}
			case walkR:{
				g.drawAnimation(this._walkR,this._x,this.y);
				modeIn = 'R';
				inMode = MODE.stopR;
				walk = true;
				break;
			}
			case walkL:{
				g.drawAnimation(this._walkL,this._x,this.y);
				modeIn = 'L';
				inMode = MODE.stopL;
				walk = true;
				break;
			}
			case atk1R:{
				g.drawAnimation(this._atk1R,this._x,this.y);
				modeIn = 'R';
				inMode = MODE.stopR;
				dmgHit = true;
				break;
			}
			case atk1L:{
				g.drawAnimation(this._atk1L,this._x,this.y);
				modeIn = 'L';
				inMode = MODE.stopL;
				dmgHit = true;
				break;
			}
			case dieR :{
				g.drawAnimation(this._dieR,this._x,this.y);
				modeIn = 'R';
				break;
			}
			case dieL :{
				g.drawAnimation(this._dieL,this._x,this.y);
				modeIn = 'L';
				break;
			}
		}
		
		this.drawString(this.name,this._x+(55-font[0].getWidth(this.name))/2,this.y-15,Color.green);
	}
	
	public void Update(int mapX,ArrayList<ENEMY> tar,HOME[] home){
		this.mapX = mapX;

		if(!hero.getDie() && !this.getDie() && !dmgHit){
			if(hero.getMode() == 'R'){
				if(hero._x+eyerange >= this._x){
					this.x += speed;
					this.inMode = MODE.walkR;
				}else{
					if(inMode == MODE.walkR){
						inMode = MODE.stopR;
						this.rndMove();
					}
					if(inMode == MODE.walkL){
						inMode = MODE.stopL;
						this.rndMove();
					}
				}
			}else if(hero.getMode() == 'L'){
				if(hero._x-eyerange <= this._x){
					this.x -= speed;
					this.inMode = MODE.walkL;
				}else{
					if(inMode == MODE.walkR){
						inMode = MODE.stopR;
						this.rndMove();
					}
					if(inMode == MODE.walkL){
						inMode = MODE.stopL;
						this.rndMove();
					}
				}
			}
		}
		
		for(ENEMY e : tar){
			if(e != null){
				if(this.BOUNDINGBOX().intersects(e.boundingBoxOfHit())){
					int atking = this.getATK()-e.def;
					if(modeIn == 'R') inMode = MODE.stopR;
					if(modeIn == 'L') inMode = MODE.stopL;
						
					if(!e.isDie()){
						if(modeIn == 'L' && !walk){
								inMode = MODE.valueOf("atk"+1+"L");
								if(_atk1L.getFrame() == 0)hit=false;
								if(_atk1L.getFrame() == _atk1L.getFrameCount()-1 && !hit){
									if(atking > 0){
										e.hp -= atking;
										e.drawDmgFont2(atking+"",e._x+15,e.y-10);
										if(this._x > 0 && this._x < 1024)soundEffect[2].play();
										try{
											if((this._x < 1024) && (this._x > 0))
											(new Sound(FileList.bgsDir+"bgs17.wav")).play();
										}catch(SlickException error){
										}
									}else{
										e.hp -= 1;
										e.drawDmgFont2(1+"!",e._x+15,e.y-10);
										if((this._x < 1024) && (this._x > 0))soundEffect[(int)(2+Math.random()*1)].play();
									}
									
									e.addEffect(modeIn,e.CenterOf('x'),e.CenterOf('y'));
									hit=true;
									dmgHit=true;
								}
							}
						if(modeIn == 'R' && !walk){
							inMode = MODE.valueOf("atk"+1+"R");
							if(_atk1R.getFrame() == 0)hit=false;
							if(_atk1R.getFrame() == _atk1R.getFrameCount()-1 && !hit){
								if(atking > 0){
									e.hp -= atking;
									e.drawDmgFont2(atking+"",e._x,e.y-10);
									if(this._x > 0 && this._x < 1024)soundEffect[2].play();
									try{
										if((this._x < 1024) && (this._x > 0))
										(new Sound(FileList.bgsDir+"bgs17.wav")).play();
									}catch(SlickException error){
									}
								}else{
									e.hp -= 1;
									e.drawDmgFont2("!",e._x,e.y-10);
									if((this._x < 1024) && (this._x > 0))soundEffect[(int)(2+Math.random()*1)].play();
								}
								
								e.addEffect(modeIn,e.CenterOf('x'),e.CenterOf('y'));
								hit=true;
								dmgHit=true;
							}
						}
						return;
					}
				}
			}
		}
		
		for(HOME h : home){
			if(h!=null){
				if(!h.die()){
					if(this.BOUNDINGBOX().intersects(h.BOUNDINGBOX())){
						int atking = this.getATK()-h.def;
						
						if(modeIn == 'R') inMode = MODE.stopR;
						if(modeIn == 'L') inMode = MODE.stopL;
						
						if(modeIn == 'R'){
									inMode = MODE.atk1R;
									if(_atk1R.getFrame() == 0)hit=false;
									if(_atk1R.getFrame() == _atk1R.getFrameCount()-1 && !hit){
										if(atking > 0){
											h.hp -= atking;
											h.drawDmgFont2(atking+"",h._x,h.y-10);
											if(this._x > 0 && this._x < 1024)soundEffect[2].play();
											try{
												if((this._x < 1024) && (this._x > 0))
												(new Sound(FileList.bgsDir+"bgs17.wav")).play();
											}catch(SlickException error){
											}
										}else{
											h.hp -= 1;
											h.drawDmgFont2("!",h._x,h.y-10);
											if((this._x < 1024) && (this._x > 0))soundEffect[(int)(2+Math.random()*1)].play();
										}
										
										hit=true;
										dmgHit=true;
									}
								}
						if(modeIn == 'L'){
							inMode = MODE.atk1L;
							if(_atk1L.getFrame() == 0)hit=false;
								if(_atk1L.getFrame() == _atk1L.getFrameCount()-1 && !hit){
									if(atking > 0){
										h.hp -= atking;
										h.drawDmgFont2(atking+"",h._x+15,h.y-10);
										if(this._x > 0 && this._x < 1024)soundEffect[2].play();
										try{
											if((this._x < 1024) && (this._x > 0))
											(new Sound(FileList.bgsDir+"bgs17.wav")).play();
										}catch(SlickException error){
										}
									}else{
										h.hp -= 1;
										h.drawDmgFont2(1+"!",h._x+15,h.y-10);
										if((this._x < 1024) && (this._x > 0))soundEffect[(int)(2+Math.random()*1)].play();
									}
											
									hit=true;
									dmgHit=true;
								}
							}
						return;
					}
				}
			}
		}
	
	}
}

class MY_SOLIDER_TWOHANDSWORD extends MYSOLIDER implements Serializable{
	public MY_SOLIDER_TWOHANDSWORD(){
		this.hpMax = 450;
		this.hp = hpMax;
		this.speed = 2;
		this.frame = new short[]{550,200,230,80};
		this.range = 30;
		this.atk = 40;
		this.name = "ยอดฝีมือ ผู้ซื่อสัตย์";
		this.sJob = SoliderJob.twohand;
		this.rndMove();
		try{
			this.soundDie = new Sound(FileList.bgsDir+"mDie.wav");
		}catch(SlickException e){
		}
	}
	
	public void Load() throws SlickException {
		String dirOfStop = "Resource/animation/Solider/Solider3/Stop/";
		String dirOfWalk = "Resource/animation/Solider/Solider3/Walk/";
		String dirOfAttk = "Resource/animation/Solider/Solider3/Atk/";
		String dirOfDead = "Resource/animation/Solider/Solider3/Die/";
		
		this._stopR = new Animation(
			new Image[]{
				new Image(dirOfStop+"Sasr_0001.png"),
				new Image(dirOfStop+"Sasr_0002.png"),
			},frame[0]
		);
		
		this._stopL = new Animation(
			new Image[]{
				new Image(dirOfStop+"Sasl_0001.png"),
				new Image(dirOfStop+"Sasl_0001.png"),
			},frame[0]
		);
		
		this._walkR = new Animation(
			new Image[]{
				new Image(dirOfWalk+"Sawr_0001.png"),
				new Image(dirOfWalk+"Sawr_0002.png"),
				new Image(dirOfWalk+"Sawr_0003.png"),
				new Image(dirOfWalk+"Sawr_0002.png"),
			},frame[1]
		);
		
		this._walkL = new Animation(
			new Image[]{
				new Image(dirOfWalk+"Sawl_0001.png"),
				new Image(dirOfWalk+"Sawl_0002.png"),
				new Image(dirOfWalk+"Sawl_0003.png"),
				new Image(dirOfWalk+"Sawl_0002.png"),
			},frame[1]
		);
		
		this._atk1R = new Animation(
			new Image[]{
				new Image(dirOfAttk+"Saar_0001.png"),
				new Image(dirOfAttk+"Saar_0002.png"),
				new Image(dirOfAttk+"Saar_0003.png"),
				new Image(dirOfAttk+"Saar_0003.png"),
				new Image(dirOfAttk+"Saar_0003.png"),
			},frame[2]
		);
		
		this._atk1L = new Animation(
			new Image[]{
				new Image(dirOfAttk+"Saal_0001.png"),
				new Image(dirOfAttk+"Saal_0002.png"),
				new Image(dirOfAttk+"Saal_0003.png"),
				new Image(dirOfAttk+"Saal_0003.png"),
				new Image(dirOfAttk+"Saal_0003.png"),
			},frame[2]
		);
		
		this._dieR = new Animation(
			new Image[]{
				new Image(dirOfDead+"dieR.png")
			},frame[3]
		);
		
		this._dieL = new Animation(
			new Image[]{
				new Image(dirOfDead+"dieL.png")
			},frame[3]
		);
	}
	
	public void DrawSelf(){
		_x = x+this.mapX;
		if((this._x < 1024) && (this._x > 0))
			g.drawImage(shardow,_x,y+70);
			
		switch(inMode){
			case stopR:{
				g.drawAnimation(this._stopR,this._x,this.y);
				modeIn = 'R';
				walk = false;
				dmgHit = false;
				break;
			}
			case stopL:{
				g.drawAnimation(this._stopL,this._x,this.y);
				modeIn = 'L';
				walk = false;
				dmgHit = false;
				break;
			}
			case walkR:{
				g.drawAnimation(this._walkR,this._x,this.y);
				modeIn = 'R';
				inMode = MODE.stopR;
				walk = true;
				break;
			}
			case walkL:{
				g.drawAnimation(this._walkL,this._x,this.y);
				modeIn = 'L';
				inMode = MODE.stopL;
				walk = true;
				break;
			}
			case atk1R:{
				g.drawAnimation(this._atk1R,this._x,this.y);
				modeIn = 'R';
				inMode = MODE.stopR;
				dmgHit = true;
				break;
			}
			case atk1L:{
				g.drawAnimation(this._atk1L,this._x,this.y);
				modeIn = 'L';
				inMode = MODE.stopL;
				dmgHit = true;
				break;
			}
			case dieR :{
				g.drawAnimation(this._dieR,this._x,this.y);
				modeIn = 'R';
				break;
			}
			case dieL :{
				g.drawAnimation(this._dieL,this._x,this.y);
				modeIn = 'L';
				break;
			}
		}
		
		this.drawString(this.name,this._x+(55-font[0].getWidth(this.name))/2,this.y-15,Color.green);
	}
	
	public void Update(int mapX,ArrayList<ENEMY> tar,HOME[] home){
		this.mapX = mapX;

		if(!hero.getDie() && !this.getDie() && !dmgHit){
			if(hero.getMode() == 'R'){
				if(hero._x+eyerange >= this._x){
					this.x += speed;
					this.inMode = MODE.walkR;
				}else{
					if(inMode == MODE.walkR){
						inMode = MODE.stopR;
						this.rndMove();
					}
					if(inMode == MODE.walkL){
						inMode = MODE.stopL;
						this.rndMove();
					}
				}
			}else if(hero.getMode() == 'L'){
				if(hero._x-eyerange <= this._x){
					this.x -= speed;
					this.inMode = MODE.walkL;
				}else{
					if(inMode == MODE.walkR){
						inMode = MODE.stopR;
						this.rndMove();
					}
					if(inMode == MODE.walkL){
						inMode = MODE.stopL;
						this.rndMove();
					}
				}
			}
		}
		
		for(ENEMY e : tar){
			if(e != null){
				if(this.BOUNDINGBOX().intersects(e.boundingBoxOfHit())){
					int atking = this.getATK()-e.def;
					if(modeIn == 'R') inMode = MODE.stopR;
					if(modeIn == 'L') inMode = MODE.stopL;
						
					if(!e.isDie()){
						if(modeIn == 'L' && !walk){
								inMode = MODE.valueOf("atk"+1+"L");
								if(_atk1L.getFrame() == 0)hit=false;
								if(_atk1L.getFrame() == _atk1L.getFrameCount()-1 && !hit){
									if(atking > 0){
										e.hp -= atking;
										e.drawDmgFont2(atking+"",e._x+15,e.y-10);
										if(this._x > 0 && this._x < 1024)soundEffect[2].play();
										try{
											if((this._x < 1024) && (this._x > 0))
											(new Sound(FileList.bgsDir+"bgs17.wav")).play();
										}catch(SlickException error){
										}
									}else{
										e.hp -= 1;
										e.drawDmgFont2(1+"!",e._x+15,e.y-10);
										if((this._x < 1024) && (this._x > 0))soundEffect[(int)(2+Math.random()*1)].play();
									}
									
									e.addEffect(modeIn,e.CenterOf('x'),e.CenterOf('y'));
									hit=true;
									dmgHit=true;
								}
						}
						if(modeIn == 'R' && !walk){
							inMode = MODE.valueOf("atk"+1+"R");
							if(_atk1R.getFrame() == 0)hit=false;
							if(_atk1R.getFrame() == _atk1R.getFrameCount()-1 && !hit){
								if(atking > 0){
									e.hp -= atking;
									e.drawDmgFont2(atking+"",e._x,e.y-10);
									if(this._x > 0 && this._x < 1024)soundEffect[2].play();
									try{
										if((this._x < 1024) && (this._x > 0))
										(new Sound(FileList.bgsDir+"bgs17.wav")).play();
									}catch(SlickException error){
									}
								}else{
									e.hp -= 1;
									e.drawDmgFont2("!",e._x,e.y-10);
									if((this._x < 1024) && (this._x > 0))soundEffect[(int)(2+Math.random()*1)].play();
								}
								
								e.addEffect(modeIn,e.CenterOf('x'),e.CenterOf('y'));
								hit=true;
								dmgHit=true;
							}
						}
						return;
					}
				}
			}
		}
		
		for(HOME h : home){
			if(h!=null){
				if(!h.die()){
					if(this.BOUNDINGBOX().intersects(h.BOUNDINGBOX())){
						int atking = this.getATK()-h.def;
						
						if(modeIn == 'R') inMode = MODE.stopR;
						if(modeIn == 'L') inMode = MODE.stopL;
						
						if(modeIn == 'R'){
									inMode = MODE.atk1R;
									if(_atk1R.getFrame() == 0)hit=false;
									if(_atk1R.getFrame() == _atk1R.getFrameCount()-1 && !hit){
										if(atking > 0){
											h.hp -= atking;
											h.drawDmgFont2(atking+"",h._x,h.y-10);
											if(this._x > 0 && this._x < 1024)soundEffect[2].play();
											try{
												if((this._x < 1024) && (this._x > 0))
												(new Sound(FileList.bgsDir+"bgs17.wav")).play();
											}catch(SlickException error){
											}
										}else{
											h.hp -= 1;
											h.drawDmgFont2("!",h._x,h.y-10);
											if((this._x < 1024) && (this._x > 0))soundEffect[(int)(2+Math.random()*1)].play();
										}
										
										hit=true;
										dmgHit=true;
									}
								}
						if(modeIn == 'L'){
							inMode = MODE.atk1L;
							if(_atk1L.getFrame() == 0)hit=false;
								if(_atk1L.getFrame() == _atk1L.getFrameCount()-1 && !hit){
									if(atking > 0){
										h.hp -= atking;
										h.drawDmgFont2(atking+"",h._x+15,h.y-10);
										if(this._x > 0 && this._x < 1024)soundEffect[2].play();
										try{
											if((this._x < 1024) && (this._x > 0))
											(new Sound(FileList.bgsDir+"bgs17.wav")).play();
										}catch(SlickException error){
										}
									}else{
										h.hp -= 1;
										h.drawDmgFont2(1+"!",h._x+15,h.y-10);
										if((this._x < 1024) && (this._x > 0))soundEffect[(int)(2+Math.random()*1)].play();
									}
											
									hit=true;
									dmgHit=true;
								}
							}
						return;
					}
				}
			}
		}
	
	}
}

abstract class ENEMY extends IMAGEING implements Serializable{
	protected String name;
	protected int mapIndex,_x,_y;
	protected int atk,atkMax,comboFrame,teep;
	public int hp,hpMax,pw,pwMax,exp,def,speed;
	protected int frameDown,frameUp,frameDieLife,alpha;
	protected int randS,range;
	protected int money,objPositionX,objPositionY;
	protected boolean showAI = false,hitHome = false;
	public ArrayList<FireDmg> fd = new ArrayList<FireDmg>();
	
	public ENEMY(){
		y = 445;
		_y = y;
		frameDown=0;
		frameUp=200;
		comboFrame=(int)(Math.random()*2+1);
	    randS = (int)(50+Math.random()*200);
		money = (int)(10+Math.random()*14);
		combo = false;
		walk=false;
		hit=false;
		dier=false;
		dmgHit=false;
		frameDieLife = 255;
		alpha = 255;
		color = new Color(255,255,255,alpha);
		frameEf = 10;
		
		try{
			sounds = new Sound("Resource/audio/bgs/pick.wav");
		}catch(SlickException e){
		}
	}
	public void setTeep(int rang){
		this._x = rang;
	}
	public int getATK(){
		int dmg = (int)(atk+Math.random()*atkMax);
		return(dmg);
	}	
	public int getX(){
		return(this.x);
	}
	public int getY(){
		return(this.y);
	}	
	public void get(){
		System.out.println("Name : "+this.getClass().getName());
		System.out.println("Position X : "+this.getX());
	}
	public void setMap(int mapIndex,int x){
		this.x = -1024+1024*mapIndex+x;
	}
	protected void onDie(CHARACTER hero){
		drawDmgFont3("+"+money,hero._x,hero._y);
		hero.gold_am += money;
		hero.MinExpSkill += exp;
		sounds.play();
	}
	public Rectangle BOUNDINGBOX(){
		Rectangle r = new Rectangle(_x,_y,range,range);
		return(r);
	}
	public Rectangle boundingBoxOfHit(){
		Rectangle r = new Rectangle(_x,_y+21,30,50);
		return(r);
	}
	public boolean getRemove(boolean ok){
		return(ok);
	}
	public void setTarget(CHARACTER hero,HOME[] obj,ArrayList<SOLIDER> tar,ArrayList<MYSOLIDER> ms,ScrollTxt st){
		if(!this.isDie()){
			attack(hero,obj,tar,ms);
		}else{
			die();
			if(!dier){
				if((this._x > 0) && (this._x < 1024)){
					st.addString("คุณได้รับเงิน + "+this.money+" และ EXP + "+this.exp);
					st.nextLine();
					
					onDie(hero);
					soundDie.play();
				}
				dier=true;
			}
		}
	}
	public void die(){
		if(modeIn == '>') inMode = MODE.dieR;
		if(modeIn == '<') inMode = MODE.dieL;
	}
	public boolean isDie(){
		if(hp > 0) return(false);
		return(true);
	}
	public int getSpeed(int pos,int range){
		for(int i=1;i<range;i++){
			if((range%pos)==0) return(i);
		}
		return(0);
	}
	public void setShowAI(boolean e){
		this.showAI = e;
	}
	protected void updateing(){
		drawString(name,_x+8,y-20,Color.red);
		for(BLOOD blooding : blood){
			if(!blooding.getRemove()){
				blooding.render();
				blooding.update(this._x,this._y);
			}else{
				blood.remove(blooding);
				break;
			}
		}
		
		for(FireDmg f : fd){
			if(f != null){
				if(!f.getDie()){
					f.update(this._x+20,this.y+40,this);
				}else{
					fd.clear();
					fireHit = false;
				}
			}
			break;
		}
		
		for(DMGTEXT dmgTEXT : dmgTxt){
			if(!dmgTEXT.getRemove()){
				dmgTEXT.UpdateAndRender();
			}else{
				dmgTxt.remove(dmgTEXT);
				break;
			}
		}
		
		if(showAI){
			g.setColor(new Color(255,0,0,100));
			g.drawLine((float)this._x+20,(float)this.y+40,(float)objPositionX+40,(float)objPositionY);
		}
	}
	public boolean getDie(){
		if(hp <= 0){
			frameDieLife--;
			alpha--;
			if(frameDieLife<=0)return(true);
		}
		
		return(false);
	}
	public abstract void UPDATE(int mapX);
	public abstract void LOAD() throws SlickException;
	protected abstract void attack(CHARACTER hero,HOME[] obj,ArrayList<SOLIDER> tar,ArrayList<MYSOLIDER> ms);
	protected abstract void monitor(CHARACTER hero);
	public abstract void drawSelf();
}

class ENEMY_SWORD extends ENEMY implements Serializable{
	public ENEMY_SWORD(){
		name = "ข้าศึก";
		hpMax = 120;
		hp = hpMax;
		speed = 2;
		range = 50;
		frame = new short[]{350,200,230,80};
		atk = 5;atkMax = 5;
		teep = 5;
		exp = 3;
		money = (int)(10+Math.random()*14);
		def = 0;
		try{
			soundDie = new Sound("Resource/audio/bgs/eDie.wav");
			soundEffect = new Sound[]{
				new Sound("Resource/audio/bgs/bgs17.wav"),
				new Sound("Resource/audio/bgs/bgs16.wav"),
				new Sound("Resource/audio/bgs/hit1.wav"),
				new Sound("Resource/audio/bgs/hit3.wav"),
			};
		}catch(SlickException e){
			System.out.printf("Error : %s",e.toString());
		}
	}
	
	public void LOAD() throws SlickException{
		String dir = "Resource/animation/Enemy/Enemy01/";
		
		this._stopR = new Animation(
			new Image[]{
				new Image(dir+"stop/En1_sr_0001"),
				new Image(dir+"stop/En1_sr_0002"),
				new Image(dir+"stop/En1_sr_0001"),
			},frame[0]
		);
		
		this._stopL = new Animation(
			new Image[]{
				new Image(dir+"stop/En1_sl_0001"),
				new Image(dir+"stop/En1_sl_0002"),
				new Image(dir+"stop/En1_sl_0001"),
			},frame[0]
		);
	
		this._walkR = new Animation(
			new Image[]{
				new Image(dir+"walk/En1_wr_0001"),
				new Image(dir+"walk/En1_wr_0002"),
				new Image(dir+"walk/En1_wr_0003"),
				new Image(dir+"walk/En1_wr_0002"),
			},frame[1]
		);
	
		this._walkL = new Animation(
			new Image[]{
				new Image(dir+"walk/En1_wl_0001"),
				new Image(dir+"walk/En1_wl_0002"),
				new Image(dir+"walk/En1_wl_0003"),
				new Image(dir+"walk/En1_wl_0002"),
			},frame[1]
		);
	
		this._atk1R = new Animation(
			new Image[]{
				new Image(dir+"atk/En1_ar1_0001"),
				new Image(dir+"atk/En1_ar1_0002"),
				new Image(dir+"atk/En1_ar1_0003"),
				new Image(dir+"atk/En1_ar1_0003"),
			},frame[2]
		);
	
		this._atk1L = new Animation(
			new Image[]{
				new Image(dir+"atk/En1_al1_0001"),
				new Image(dir+"atk/En1_al1_0002"),
				new Image(dir+"atk/En1_al1_0003"),
				new Image(dir+"atk/En1_al1_0003"),
			},frame[2]
		);
		
		this._dieR = new Animation(
			new Image[]{
				new Image(dir+"die/dieR.png"),
			},1
		);
	
		this._dieL = new Animation(
			new Image[]{
				new Image(dir+"die/dieL.png"),
			},1
		);
	}
	
	protected void attack(CHARACTER hero,HOME[] obj,ArrayList<SOLIDER> tar,ArrayList<MYSOLIDER> ms){
		for(MYSOLIDER entity : ms){
			if(this.BOUNDINGBOX().intersects(entity.BOUNDINGBOX())){
				int atking = this.getATK()-entity.def;
				if(modeIn == '>') inMode = MODE.stopR;
				if(modeIn == '<') inMode = MODE.stopL;
				
				if(!entity.getDie()){
					if(modeIn == '<' && !walk){
						inMode = MODE.valueOf("atk"+1+"L");
						if(_atk1L.getFrame()==0)hit=false;
						if(_atk1L.getFrame() == _atk1L.getFrameCount()-1 && !hit){
							if(atking > 0){
								entity.hp-= atking;
								drawDmgFont(atking+"",entity.CenterOf('x'),entity.y-10);
								try{
									if((this._x < 1024) && (this._x > 0))
									(new Sound(FileList.bgsDir+"bgs17.wav")).play();
								}catch(SlickException error){
								}
							}else{
								entity.hp -= 1;
								drawDmgFont(1+"!",entity.CenterOf('x'),entity.y-10);
								if((this._x < 1024) && (this._x > 0))soundEffect[(int)(2+Math.random()*1)].play();
							}	
							hit=true;
							dmgHit=true;
						}
					}		
					if(modeIn == '>' && !walk){
						inMode = MODE.valueOf("atk"+1+"R");
						if(_atk1R.getFrame()==0)hit=false;
						if(_atk1R.getFrame() == _atk1R.getFrameCount()-1 && !hit){
							if(atking > 0){
								hero.HP -= atking;
								drawDmgFont(atking+"",hero.CenterOf('x'),hero._y-10);
								try{
									if((this._x < 1024) && (this._x > 0))
									(new Sound(FileList.bgsDir+"bgs17.wav")).play();
								}catch(SlickException error){
								}
							}else{
								hero.HP -= 1;
								drawDmgFont("!",hero.CenterOf('x'),hero._y-10);
								if((this._x < 1024) && (this._x > 0))soundEffect[(int)(2+Math.random()*1)].play();
							}
								
							hit=true;
							dmgHit=true;
						}
					}
					return;
				}
			}
		}
	
		for(SOLIDER entity : tar){
			if(this.BOUNDINGBOX().intersects(entity.BOUNDINGBOX())){
				int atking = this.getATK()-entity.def;
				if(modeIn == '>') inMode = MODE.stopR;
				if(modeIn == '<') inMode = MODE.stopL;
				
				if(!entity.getDie()){
					if(modeIn == '<' && !walk){
						inMode = MODE.valueOf("atk"+1+"L");
						if(_atk1L.getFrame()==0)hit=false;
						if(_atk1L.getFrame() == _atk1L.getFrameCount()-1 && !hit){
							if(atking > 0){
								entity.hp-= atking;
								drawDmgFont(atking+"",entity.CenterOf('x'),entity.y-10);
								try{
									if((this._x < 1024) && (this._x > 0))
									(new Sound(FileList.bgsDir+"bgs17.wav")).play();
								}catch(SlickException error){
								}
							}else{
								entity.hp -= 1;
								drawDmgFont(1+"!",entity.CenterOf('x'),entity.y-10);
								if((this._x < 1024) && (this._x > 0))soundEffect[(int)(2+Math.random()*1)].play();
							}	
							hit=true;
							dmgHit=true;
						}
					}		
					if(modeIn == '>' && !walk){
						inMode = MODE.valueOf("atk"+1+"R");
						if(_atk1R.getFrame()==0)hit=false;
						if(_atk1R.getFrame() == _atk1R.getFrameCount()-1 && !hit){
							if(atking > 0){
								hero.HP -= atking;
								drawDmgFont(atking+"",hero.CenterOf('x'),hero._y-10);
								try{
									if((this._x < 1024) && (this._x > 0))
									(new Sound(FileList.bgsDir+"bgs17.wav")).play();
								}catch(SlickException error){
								}
							}else{
								hero.HP -= 1;
								drawDmgFont("!",hero.CenterOf('x'),hero._y-10);
								if((this._x < 1024) && (this._x > 0))soundEffect[(int)(2+Math.random()*1)].play();
							}
								
							hit=true;
							dmgHit=true;
						}
					}
					return;
				}
			}
		}
		
		if(this.BOUNDINGBOX().intersects(hero.getBox())){
			int atking = this.getATK()-hero.DEF;
			if(modeIn == '>') inMode = MODE.stopR;
			if(modeIn == '<') inMode = MODE.stopL;
				
			if(!hero.getDie()){
				if(modeIn == '<' && !walk){
					inMode = MODE.valueOf("atk"+1+"L");
					if(_atk1L.getFrame()==0)hit=false;
					if(_atk1L.getFrame() == _atk1L.getFrameCount()-1 && !hit){
						if(atking > 0){
							hero.HP -= atking;
							drawDmgFont(atking+"",hero.CenterOf('x'),hero._y-10);
							try{
								if((this._x < 1024) && (this._x > 0))
								(new Sound(FileList.bgsDir+"bgs17.wav")).play();
							}catch(SlickException error){
							}
						}else{
							hero.HP -= 1;
							drawDmgFont(1+"!",hero.CenterOf('x'),hero._y-10);
							if((this._x < 1024) && (this._x > 0))soundEffect[(int)(2+Math.random()*1)].play();
						}	
						hit=true;
						dmgHit=true;
					}
				}		
				if(modeIn == '>' && !walk){
					inMode = MODE.valueOf("atk"+1+"R");
					if(_atk1R.getFrame()==0)hit=false;
					if(_atk1R.getFrame() == _atk1R.getFrameCount()-1 && !hit){
						if(atking > 0){
							hero.HP -= atking;
							drawDmgFont(atking+"",hero.CenterOf('x'),hero._y-10);
							try{
								if((this._x < 1024) && (this._x > 0))
								(new Sound(FileList.bgsDir+"bgs17.wav")).play();
							}catch(SlickException error){
							}
						}else{
							hero.HP -= 1;
							drawDmgFont("!",hero.CenterOf('x'),hero._y-10);
							if((this._x < 1024) && (this._x > 0))soundEffect[(int)(2+Math.random()*1)].play();
						}
								
						hit=true;
						dmgHit=true;
					}
				}
				return;
			}
		}
		
		for(HOME h : obj){
			if(h != null && !h.die()){
				objPositionX = h.CenterOf('x');
				objPositionY = h.CenterOf('Y');
				hitHome = false;
				
				if((objPositionX <= this._x-range) && !dmgHit){
					this.walk = true;
					inMode = MODE.walkL;
					this.x -= this.speed;
					
					break;
				}else if((objPositionX >= this._x+range) && !dmgHit){
					this.walk = true;
					inMode = MODE.walkR;
					this.x += this.speed;
					break;
				}else{
					if(modeIn == '>') inMode = MODE.stopR;
					if(modeIn == '<') inMode = MODE.stopL;
				}
				
				if(this.BOUNDINGBOX().intersects(h.BOUNDINGBOX())){
					int atkHome = this.getATK()-h.def;
					hitHome = true;
					if(!h.die()){
						if(modeIn == '<' && !walk){
							inMode = MODE.valueOf("atk"+1+"L");
							if(_atk1L.getFrame()==0)hit=false;
							if(_atk1L.getFrame() == _atk1L.getFrameCount()-1 && !hit){
								if(this.getATK()-h.def > 0){
									h.hp -= atkHome;
									drawDmgFont(atkHome+"",h.CenterOf('x'),h.y-10);
									try{
										if((this._x < 1024) && (this._x > 0))
										(new Sound(FileList.bgsDir+"bgs17.wav")).play();
									}catch(SlickException error){
									}
								}else{
									h.hp -= 1;
									drawDmgFont(1+"!",h.CenterOf('x'),h.y-10);
									if((this._x < 1024) && (this._x > 0))soundEffect[(int)(2+Math.random()*1)].play();
								}
								
								hit=true;
								dmgHit=true;
							}
						}		
						if(modeIn == '>' && !walk){
							inMode = MODE.valueOf("atk"+1+"R");
							if(_atk1R.getFrame()==0)hit=false;
							if(_atk1R.getFrame() == _atk1R.getFrameCount()-1 && !hit){
								if(this.getATK()-h.def > 0){
									h.hp -= atkHome;
									drawDmgFont(atkHome+"",h.CenterOf('x'),h.y-10);
									try{
										if((this._x < 1024) && (this._x > 0))
										(new Sound(FileList.bgsDir+"bgs17.wav")).play();
									}catch(SlickException error){
									}
								}else{
									h.hp -= 1;
									drawDmgFont("!",h.CenterOf('x'),h.y-10);
									if((this._x < 1024) && (this._x > 0))soundEffect[(int)(2+Math.random()*1)].play();
								}
								
								hit=true;
								dmgHit=true;
							}
						}
						break;
					}
				}
			}
		}
	}

	protected void monitor(CHARACTER hero){
	}
	
	public void drawSelf(){
		_x = x+this.mapX;
		if((this._x < 1024) && (this._x > 0))g.drawImage(shardow,_x,y+70);

		if(this.color.getRed() == 255){
			if(frameEf > 0)frameEf--;
			else color = new Color(255,255,255);
		}
		
		this.color.a = alpha;
		switch(inMode){
			case stopR:{
				walk=false;
				modeIn='>';
				dmgHit=false;
				g.drawAnimation(_stopR,_x,y,color);
				break;
			}case stopL:{
				walk=false;
				modeIn='<';
				dmgHit=false;
				g.drawAnimation(_stopL,_x,y,color);
				break;
			}case walkR:{
				walk=true;
				modeIn='>';
				g.drawAnimation(_walkR,_x,y,color);
				break;
			}case walkL:{
				walk=true;
				modeIn='<';
				g.drawAnimation(_walkL,_x,y,color);
				break;
			}case atk1R:{
				modeIn='>';
				g.drawAnimation(_atk1R,_x,y,color);
				break;
			}case atk1L:{
				modeIn='<';
				g.drawAnimation(_atk1L,_x,y,color);
				break;
			}case atk2R:{
				modeIn='>';
				g.drawAnimation(_atk2R,_x,y,color);
				break;
			}case atk2L:{
				modeIn='<';
				g.drawAnimation(_atk2L,_x,y,color);
				break;
			}case dieR:{
				modeIn='>';
				g.drawAnimation(_dieR,_x,y,color);
				break;
			}case dieL:{
				modeIn='<';
				g.drawAnimation(_dieL,_x,y,color);
				break;
			}
		}
		
		this.drawString(this.name,this.x+this.mapIndex+16,this.y-15,Color.red);
	}
	
	public void UPDATE(int mapX){
		this.mapX = mapX;
		this.updateing();
	}
}

class ENEMY_SPEAR extends ENEMY implements Serializable{
	public ENEMY_SPEAR(){
		name = "ข้าศึก";
		hpMax = 200;
		hp = hpMax;
		speed = 2;
		range = 30;
		frame = new short[]{350,200,230,80};
		atk = 10;atkMax = 15;
		exp = 5;
		money = (int)(20+Math.random()*20);
		try{
			soundDie = new Sound("Resource/audio/bgs/eDie.wav");
			soundEffect = new Sound[]{
				new Sound("Resource/audio/bgs/bgs17.wav"),
				new Sound("Resource/audio/bgs/bgs16.wav"),
				new Sound("Resource/audio/bgs/hit1.wav"),
				new Sound("Resource/audio/bgs/hit3.wav"),
			};
		}catch(SlickException e){
			System.out.printf("Error : %s",e.toString());
		}
	}
	
	public void LOAD() throws SlickException{
		String dir = "Resource/animation/Enemy/Enemy02/";
		
		this._stopR = new Animation(
			new Image[]{
				new Image(dir+"stop/En2_sr_0001.png"),
				new Image(dir+"stop/En2_sr_0002.png"),
				new Image(dir+"stop/En2_sr_0001.png"),
			},frame[0]
		);
	
		this._stopL = new Animation(
			new Image[]{
				new Image(dir+"stop/En2_sl_0001.png"),
				new Image(dir+"stop/En2_sl_0002.png"),
				new Image(dir+"stop/En2_sl_0001.png"),
			},frame[0]
		);
	
		this._walkR = new Animation(
			new Image[]{
				new Image(dir+"walk/En2_wr_0001.png"),
				new Image(dir+"walk/En2_wr_0002.png"),
				new Image(dir+"walk/En2_wr_0003.png"),
				new Image(dir+"walk/En2_wr_0002.png"),
			},frame[1]
		);
	
		this._walkL = new Animation(
			new Image[]{
				new Image(dir+"walk/En2_wl_0001.png"),
				new Image(dir+"walk/En2_wl_0002.png"),
				new Image(dir+"walk/En2_wl_0003.png"),
				new Image(dir+"walk/En2_wl_0002.png"),
			},frame[1]
		);
	
		this._atk1R = new Animation(
			new Image[]{
				new Image(dir+"atk/En2_ar_0001.png"),
				new Image(dir+"atk/En2_ar_0002.png"),
				new Image(dir+"atk/En2_ar_0003.png"),
				new Image(dir+"atk/En2_ar_0003.png"),
				new Image(dir+"atk/En2_ar_0003.png"),
				new Image(dir+"atk/En2_ar_0003.png"),
				new Image(dir+"atk/En2_ar_0003.png"),
			},frame[1]
		);
	
		this._atk1L = new Animation(
			new Image[]{
				new Image(dir+"atk/En2_al_0001.png"),
				new Image(dir+"atk/En2_al_0002.png"),
				new Image(dir+"atk/En2_al_0003.png"),
				new Image(dir+"atk/En2_al_0003.png"),
				new Image(dir+"atk/En2_al_0003.png"),
				new Image(dir+"atk/En2_al_0003.png"),
			},frame[1]
		);
	
		this._dieR = new Animation(
			new Image[]{
				new Image(dir+"die/dieR.png"),
			},frame[1]
		);
	
		this._dieL = new Animation(
			new Image[]{
				new Image(dir+"die/dieL.png"),
			},frame[1]
		);
	}
	
	protected void attack(CHARACTER hero,HOME[] obj,ArrayList<SOLIDER> tar,ArrayList<MYSOLIDER> ms){
		for(MYSOLIDER entity : ms){
			if(this.BOUNDINGBOX().intersects(entity.BOUNDINGBOX())){
				int atking = this.getATK()-entity.def;
				if(modeIn == '>') inMode = MODE.stopR;
				if(modeIn == '<') inMode = MODE.stopL;
				
				if(!entity.getDie()){
					if(modeIn == '<' && !walk){
						inMode = MODE.valueOf("atk"+1+"L");
						if(_atk1L.getFrame()==0)hit=false;
						if(_atk1L.getFrame() == _atk1L.getFrameCount()-1 && !hit){
							if(atking > 0){
								entity.hp-= atking;
								drawDmgFont(atking+"",entity.CenterOf('x'),entity.y-10);
								try{
									if((this._x < 1024) && (this._x > 0))
									(new Sound(FileList.bgsDir+"bgs17.wav")).play();
								}catch(SlickException error){
								}
							}else{
								entity.hp -= 1;
								drawDmgFont(1+"!",entity.CenterOf('x'),entity.y-10);
								if((this._x < 1024) && (this._x > 0))soundEffect[(int)(2+Math.random()*1)].play();
							}	
							hit=true;
							dmgHit=true;
						}
					}		
					if(modeIn == '>' && !walk){
						inMode = MODE.valueOf("atk"+1+"R");
						if(_atk1R.getFrame()==0)hit=false;
						if(_atk1R.getFrame() == _atk1R.getFrameCount()-1 && !hit){
							if(atking > 0){
								hero.HP -= atking;
								drawDmgFont(atking+"",hero.CenterOf('x'),hero._y-10);
								try{
									if((this._x < 1024) && (this._x > 0))
									(new Sound(FileList.bgsDir+"bgs17.wav")).play();
								}catch(SlickException error){
								}
							}else{
								hero.HP -= 1;
								drawDmgFont("!",hero.CenterOf('x'),hero._y-10);
								if((this._x < 1024) && (this._x > 0))soundEffect[(int)(2+Math.random()*1)].play();
							}
								
							hit=true;
							dmgHit=true;
						}
					}
					return;
				}
			}
		}
	
		for(SOLIDER entity : tar){
			if(this.BOUNDINGBOX().intersects(entity.BOUNDINGBOX())){
				int atking = this.getATK()-entity.def;
				if(modeIn == '>') inMode = MODE.stopR;
				if(modeIn == '<') inMode = MODE.stopL;
				
				if(!entity.getDie()){
					if(modeIn == '<' && !walk){
						inMode = MODE.valueOf("atk"+1+"L");
						if(_atk1L.getFrame()==0)hit=false;
						if(_atk1L.getFrame() == _atk1L.getFrameCount()-1 && !hit){
							if(atking > 0){
								entity.hp-= atking;
								drawDmgFont(atking+"",entity.CenterOf('x'),entity.y-10);
								try{
									if((this._x < 1024) && (this._x > 0))
									(new Sound(FileList.bgsDir+"bgs17.wav")).play();
								}catch(SlickException error){
								}
							}else{
								entity.hp -= 1;
								drawDmgFont(1+"!",entity.CenterOf('x'),entity.y-10);
								if((this._x < 1024) && (this._x > 0))soundEffect[(int)(2+Math.random()*1)].play();
							}	
							hit=true;
							dmgHit=true;
						}
					}		
					if(modeIn == '>' && !walk){
						inMode = MODE.valueOf("atk"+1+"R");
						if(_atk1R.getFrame()==0)hit=false;
						if(_atk1R.getFrame() == _atk1R.getFrameCount()-1 && !hit){
							if(atking > 0){
								hero.HP -= atking;
								drawDmgFont(atking+"",hero.CenterOf('x'),hero._y-10);
								try{
									if((this._x < 1024) && (this._x > 0))
									(new Sound(FileList.bgsDir+"bgs17.wav")).play();
								}catch(SlickException error){
								}
							}else{
								hero.HP -= 1;
								drawDmgFont("!",hero.CenterOf('x'),hero._y-10);
								if((this._x < 1024) && (this._x > 0))soundEffect[(int)(2+Math.random()*1)].play();
							}
								
							hit=true;
							dmgHit=true;
						}
					}
					return;
				}
			}
		}
		
		if(this.BOUNDINGBOX().intersects(hero.getBox())){
			int atking = this.getATK()-hero.DEF;
			if(modeIn == '>') inMode = MODE.stopR;
			if(modeIn == '<') inMode = MODE.stopL;
				
			if(!hero.getDie()){
				if(modeIn == '<' && !walk){
					inMode = MODE.valueOf("atk"+1+"L");
					if(_atk1L.getFrame()==0)hit=false;
					if(_atk1L.getFrame() == _atk1L.getFrameCount()-1 && !hit){
						if(atking > 0){
							hero.HP -= atking;
							drawDmgFont(atking+"",hero.CenterOf('x'),hero._y-10);
							try{
								if((this._x < 1024) && (this._x > 0))
								(new Sound(FileList.bgsDir+"bgs17.wav")).play();
							}catch(SlickException error){
							}
						}else{
							hero.HP -= 1;
							drawDmgFont(1+"!",hero.CenterOf('x'),hero._y-10);
							if((this._x < 1024) && (this._x > 0))soundEffect[(int)(2+Math.random()*1)].play();
						}	
						hit=true;
						dmgHit=true;
					}
				}		
				if(modeIn == '>' && !walk){
					inMode = MODE.valueOf("atk"+1+"R");
					if(_atk1R.getFrame()==0)hit=false;
					if(_atk1R.getFrame() == _atk1R.getFrameCount()-1 && !hit){
						if(atking > 0){
							hero.HP -= atking;
							drawDmgFont(atking+"",hero.CenterOf('x'),hero._y-10);
							try{
								if((this._x < 1024) && (this._x > 0))
								(new Sound(FileList.bgsDir+"bgs17.wav")).play();
							}catch(SlickException error){
							}
						}else{
							hero.HP -= 1;
							drawDmgFont("!",hero.CenterOf('x'),hero._y-10);
							if((this._x < 1024) && (this._x > 0))soundEffect[(int)(2+Math.random()*1)].play();
						}
								
						hit=true;
						dmgHit=true;
					}
				}
				return;
			}
		}
		
		for(HOME h : obj){
			if(h != null && !h.die()){
				objPositionX = h.CenterOf('x');
				objPositionY = h.CenterOf('Y');
				hitHome = false;
				
				if((objPositionX <= this._x-range) && !dmgHit){
					this.walk = true;
					inMode = MODE.walkL;
					this.x -= this.speed;
					
					break;
				}else if((objPositionX >= this._x+range) && !dmgHit){
					this.walk = true;
					inMode = MODE.walkR;
					this.x += this.speed;
					break;
				}else{
					if(modeIn == '>') inMode = MODE.stopR;
					if(modeIn == '<') inMode = MODE.stopL;
				}
				
				if(this.BOUNDINGBOX().intersects(h.BOUNDINGBOX())){
					int atkHome = this.getATK()-h.def;
					hitHome = true;
					if(!h.die()){
						if(modeIn == '<' && !walk){
							inMode = MODE.valueOf("atk"+1+"L");
							if(_atk1L.getFrame()==0)hit=false;
							if(_atk1L.getFrame() == _atk1L.getFrameCount()-1 && !hit){
								if(this.getATK()-h.def > 0){
									h.hp -= atkHome;
									drawDmgFont(atkHome+"",h.CenterOf('x'),h.y-10);
									try{
										if((this._x < 1024) && (this._x > 0))
										(new Sound(FileList.bgsDir+"bgs17.wav")).play();
									}catch(SlickException error){
									}
								}else{
									h.hp -= 1;
									drawDmgFont(1+"!",h.CenterOf('x'),h.y-10);
									if((this._x < 1024) && (this._x > 0))soundEffect[(int)(2+Math.random()*1)].play();
								}
								
								hit=true;
								dmgHit=true;
							}
						}		
						if(modeIn == '>' && !walk){
							inMode = MODE.valueOf("atk"+1+"R");
							if(_atk1R.getFrame()==0)hit=false;
							if(_atk1R.getFrame() == _atk1R.getFrameCount()-1 && !hit){
								if(this.getATK()-h.def > 0){
									h.hp -= atkHome;
									drawDmgFont(atkHome+"",h.CenterOf('x'),h.y-10);
									try{
										if((this._x < 1024) && (this._x > 0))
										(new Sound(FileList.bgsDir+"bgs17.wav")).play();
									}catch(SlickException error){
									}
								}else{
									h.hp -= 1;
									drawDmgFont("!",h.CenterOf('x'),h.y-10);
									if((this._x < 1024) && (this._x > 0))soundEffect[(int)(2+Math.random()*1)].play();
								}
								
								hit=true;
								dmgHit=true;
							}
						}
						break;
					}
				}
			}
		}
	}
	
	protected void monitor(CHARACTER hero){
	}
	
	public void drawSelf(){
		_x = x+this.mapX;
		
		if((this._x < 1024) && (this._x > 0))g.drawImage(shardow,_x,y+70);

		if(this.color.getRed() == 255){
			if(frameEf > 0)frameEf--;
			else color = new Color(255,255,255);
		}
		
		this.color.a = alpha;
		switch(inMode){
			case stopR:{
				walk=false;
				modeIn='>';
				dmgHit=false;
				g.drawAnimation(_stopR,_x,y,color);
				break;
			}case stopL:{
				walk=false;
				modeIn='<';
				dmgHit=false;
				g.drawAnimation(_stopL,_x,y,color);
				break;
			}case walkR:{
				walk=true;
				modeIn='>';
				g.drawAnimation(_walkR,_x,y,color);
				break;
			}case walkL:{
				walk=true;
				modeIn='<';
				g.drawAnimation(_walkL,_x,y,color);
				break;
			}case atk1R:{
				modeIn='>';
				g.drawAnimation(_atk1R,_x,y,color);
				break;
			}case atk1L:{
				modeIn='<';
				g.drawAnimation(_atk1L,_x-20,y,color);
				break;
			}case atk2R:{
				modeIn='>';
				g.drawAnimation(_atk2R,_x,y,color);
				break;
			}case atk2L:{
				modeIn='<';
				g.drawAnimation(_atk2L,_x,y,color);
				break;
			}case dieR:{
				modeIn='>';
				g.drawAnimation(_dieR,_x,y,color);
				break;
			}case dieL:{
				modeIn='<';
				g.drawAnimation(_dieL,_x,y,color);
				break;
			}
		}
		
		this.drawString(this.name,this.x+this.mapIndex+16,this.y-15,Color.red);
	}
	
	public void UPDATE(int mapX){
		this.mapX = mapX;
		this.updateing();
	}
}

class ENEMY_SUPER extends ENEMY implements Serializable{
	public ENEMY_SUPER(){
		name = "ข้าศึก";
		hpMax = 450;
		hp = hpMax;
		speed = 2;
		range = 30;
		frame = new short[]{350,200,230,80};
		atk = 20;atkMax = 30;
		exp = 18;
		money = (int)(50+Math.random()*80);
		try{
			soundDie = new Sound("Resource/audio/bgs/eDie.wav");
			soundEffect = new Sound[]{
				new Sound("Resource/audio/bgs/bgs17.wav"),
				new Sound("Resource/audio/bgs/bgs16.wav"),
				new Sound("Resource/audio/bgs/hit1.wav"),
				new Sound("Resource/audio/bgs/hit3.wav"),
			};
		}catch(SlickException e){
			System.out.printf("Error : %s",e.toString());
		}
	}
	
	public void LOAD() throws SlickException{
		String dir = "Resource/animation/Enemy/Enemy04/";
		
		this._stopR = new Animation(
			new Image[]{
				new Image(dir+"stop/En4_sr_0001.png"),
				new Image(dir+"stop/En4_sr_0002.png"),
				new Image(dir+"stop/En4_sr_0001.png"),
			},frame[0]
		);
	
		this._stopL = new Animation(
			new Image[]{
				new Image(dir+"stop/En4_sl_0001.png"),
				new Image(dir+"stop/En4_sl_0002.png"),
				new Image(dir+"stop/En4_sl_0001.png"),
			},frame[0]
		);
	
		this._walkR = new Animation(
			new Image[]{
				new Image(dir+"walk/En4_wr_0001.png"),
				new Image(dir+"walk/En4_wr_0002.png"),
				new Image(dir+"walk/En4_wr_0003.png"),
				new Image(dir+"walk/En4_wr_0002.png"),
			},frame[1]
		);
	
		this._walkL = new Animation(
			new Image[]{
				new Image(dir+"walk/En4_wl_0001.png"),
				new Image(dir+"walk/En4_wl_0002.png"),
				new Image(dir+"walk/En4_wl_0003.png"),
				new Image(dir+"walk/En4_wl_0002.png"),
			},frame[1]
		);
	
		this._atk1R = new Animation(
			new Image[]{
				new Image(dir+"atk/En4_ar_0001.png"),
				new Image(dir+"atk/En4_ar_0002.png"),
				new Image(dir+"atk/En4_ar_0003.png"),
				new Image(dir+"atk/En4_ar_0003.png"),
				new Image(dir+"atk/En4_ar_0003.png"),
				new Image(dir+"atk/En4_ar_0003.png"),
				new Image(dir+"atk/En4_ar_0003.png"),
			},frame[1]
		);
	
		this._atk1L = new Animation(
			new Image[]{
				new Image(dir+"atk/En4_al_0001.png"),
				new Image(dir+"atk/En4_al_0002.png"),
				new Image(dir+"atk/En4_al_0003.png"),
				new Image(dir+"atk/En4_al_0003.png"),
				new Image(dir+"atk/En4_al_0003.png"),
				new Image(dir+"atk/En4_al_0003.png"),
				new Image(dir+"atk/En4_al_0003.png"),
			},frame[1]
		);
	
		this._dieR = new Animation(
			new Image[]{
				new Image(dir+"die/dieR.png"),
			},frame[1]
		);
	
		this._dieL = new Animation(
			new Image[]{
				new Image(dir+"die/dieL.png"),
			},frame[1]
		);
	}
	
	protected void attack(CHARACTER hero,HOME[] obj,ArrayList<SOLIDER> tar,ArrayList<MYSOLIDER> ms){
		for(MYSOLIDER entity : ms){
			if(this.BOUNDINGBOX().intersects(entity.BOUNDINGBOX())){
				int atking = this.getATK()-entity.def;
				if(modeIn == '>') inMode = MODE.stopR;
				if(modeIn == '<') inMode = MODE.stopL;
				
				if(!entity.getDie()){
					if(modeIn == '<' && !walk){
						inMode = MODE.valueOf("atk"+1+"L");
						if(_atk1L.getFrame()==0)hit=false;
						if(_atk1L.getFrame() == _atk1L.getFrameCount()-1 && !hit){
							if(atking > 0){
								entity.hp-= atking;
								drawDmgFont(atking+"",entity.CenterOf('x'),entity.y-10);
								try{
									if((this._x < 1024) && (this._x > 0))
									(new Sound(FileList.bgsDir+"bgs17.wav")).play();
								}catch(SlickException error){
								}
							}else{
								entity.hp -= 1;
								drawDmgFont(1+"!",entity.CenterOf('x'),entity.y-10);
								if((this._x < 1024) && (this._x > 0))soundEffect[(int)(2+Math.random()*1)].play();
							}	
							hit=true;
							dmgHit=true;
						}
					}		
					if(modeIn == '>' && !walk){
						inMode = MODE.valueOf("atk"+1+"R");
						if(_atk1R.getFrame()==0)hit=false;
						if(_atk1R.getFrame() == _atk1R.getFrameCount()-1 && !hit){
							if(atking > 0){
								hero.HP -= atking;
								drawDmgFont(atking+"",hero.CenterOf('x'),hero._y-10);
								try{
									if((this._x < 1024) && (this._x > 0))
									(new Sound(FileList.bgsDir+"bgs17.wav")).play();
								}catch(SlickException error){
								}
							}else{
								hero.HP -= 1;
								drawDmgFont("!",hero.CenterOf('x'),hero._y-10);
								if((this._x < 1024) && (this._x > 0))soundEffect[(int)(2+Math.random()*1)].play();
							}
								
							hit=true;
							dmgHit=true;
						}
					}
					return;
				}
			}
		}
	
		for(SOLIDER entity : tar){
			if(this.BOUNDINGBOX().intersects(entity.BOUNDINGBOX())){
				int atking = this.getATK()-entity.def;
				if(modeIn == '>') inMode = MODE.stopR;
				if(modeIn == '<') inMode = MODE.stopL;
				
				if(!entity.getDie()){
					if(modeIn == '<' && !walk){
						inMode = MODE.valueOf("atk"+1+"L");
						if(_atk1L.getFrame()==0)hit=false;
						if(_atk1L.getFrame() == _atk1L.getFrameCount()-1 && !hit){
							if(atking > 0){
								entity.hp-= atking;
								drawDmgFont(atking+"",entity.CenterOf('x'),entity.y-10);
								try{
									if((this._x < 1024) && (this._x > 0))
									(new Sound(FileList.bgsDir+"bgs17.wav")).play();
								}catch(SlickException error){
								}
							}else{
								entity.hp -= 1;
								drawDmgFont(1+"!",entity.CenterOf('x'),entity.y-10);
								if((this._x < 1024) && (this._x > 0))soundEffect[(int)(2+Math.random()*1)].play();
							}	
							hit=true;
							dmgHit=true;
						}
					}		
					if(modeIn == '>' && !walk){
						inMode = MODE.valueOf("atk"+1+"R");
						if(_atk1R.getFrame()==0)hit=false;
						if(_atk1R.getFrame() == _atk1R.getFrameCount()-1 && !hit){
							if(atking > 0){
								hero.HP -= atking;
								drawDmgFont(atking+"",hero.CenterOf('x'),hero._y-10);
								try{
									if((this._x < 1024) && (this._x > 0))
									(new Sound(FileList.bgsDir+"bgs17.wav")).play();
								}catch(SlickException error){
								}
							}else{
								hero.HP -= 1;
								drawDmgFont("!",hero.CenterOf('x'),hero._y-10);
								if((this._x < 1024) && (this._x > 0))soundEffect[(int)(2+Math.random()*1)].play();
							}
								
							hit=true;
							dmgHit=true;
						}
					}
					return;
				}
			}
		}
		
		if(this.BOUNDINGBOX().intersects(hero.getBox())){
			int atking = this.getATK()-hero.DEF;
			if(modeIn == '>') inMode = MODE.stopR;
			if(modeIn == '<') inMode = MODE.stopL;
				
			if(!hero.getDie()){
				if(modeIn == '<' && !walk){
					inMode = MODE.valueOf("atk"+1+"L");
					if(_atk1L.getFrame()==0)hit=false;
					if(_atk1L.getFrame() == _atk1L.getFrameCount()-1 && !hit){
						if(atking > 0){
							hero.HP -= atking;
							drawDmgFont(atking+"",hero.CenterOf('x'),hero._y-10);
							try{
								if((this._x < 1024) && (this._x > 0))
								(new Sound(FileList.bgsDir+"bgs17.wav")).play();
							}catch(SlickException error){
							}
						}else{
							hero.HP -= 1;
							drawDmgFont(1+"!",hero.CenterOf('x'),hero._y-10);
							if((this._x < 1024) && (this._x > 0))soundEffect[(int)(2+Math.random()*1)].play();
						}	
						hit=true;
						dmgHit=true;
					}
				}		
				if(modeIn == '>' && !walk){
					inMode = MODE.valueOf("atk"+1+"R");
					if(_atk1R.getFrame()==0)hit=false;
					if(_atk1R.getFrame() == _atk1R.getFrameCount()-1 && !hit){
						if(atking > 0){
							hero.HP -= atking;
							drawDmgFont(atking+"",hero.CenterOf('x'),hero._y-10);
							try{
								if((this._x < 1024) && (this._x > 0))
								(new Sound(FileList.bgsDir+"bgs17.wav")).play();
							}catch(SlickException error){
							}
						}else{
							hero.HP -= 1;
							drawDmgFont("!",hero.CenterOf('x'),hero._y-10);
							if((this._x < 1024) && (this._x > 0))soundEffect[(int)(2+Math.random()*1)].play();
						}
								
						hit=true;
						dmgHit=true;
					}
				}
				return;
			}
		}
		
		for(HOME h : obj){
			if(h != null && !h.die()){
				objPositionX = h.CenterOf('x');
				objPositionY = h.CenterOf('Y');
				hitHome = false;
				
				if((objPositionX <= this._x-range) && !dmgHit){
					this.walk = true;
					inMode = MODE.walkL;
					this.x -= this.speed;
					
					break;
				}else if((objPositionX >= this._x+range) && !dmgHit){
					this.walk = true;
					inMode = MODE.walkR;
					this.x += this.speed;
					break;
				}else{
					if(modeIn == '>') inMode = MODE.stopR;
					if(modeIn == '<') inMode = MODE.stopL;
				}
				
				if(this.BOUNDINGBOX().intersects(h.BOUNDINGBOX())){
					int atkHome = this.getATK()-h.def;
					hitHome = true;
					if(!h.die()){
						if(modeIn == '<' && !walk){
							inMode = MODE.valueOf("atk"+1+"L");
							if(_atk1L.getFrame()==0)hit=false;
							if(_atk1L.getFrame() == _atk1L.getFrameCount()-1 && !hit){
								if(this.getATK()-h.def > 0){
									h.hp -= atkHome;
									drawDmgFont(atkHome+"",h.CenterOf('x'),h.y-10);
									try{
										if((this._x < 1024) && (this._x > 0))
										(new Sound(FileList.bgsDir+"bgs17.wav")).play();
									}catch(SlickException error){
									}
								}else{
									h.hp -= 1;
									drawDmgFont(1+"!",h.CenterOf('x'),h.y-10);
									if((this._x < 1024) && (this._x > 0))soundEffect[(int)(2+Math.random()*1)].play();
								}
								
								hit=true;
								dmgHit=true;
							}
						}		
						if(modeIn == '>' && !walk){
							inMode = MODE.valueOf("atk"+1+"R");
							if(_atk1R.getFrame()==0)hit=false;
							if(_atk1R.getFrame() == _atk1R.getFrameCount()-1 && !hit){
								if(this.getATK()-h.def > 0){
									h.hp -= atkHome;
									drawDmgFont(atkHome+"",h.CenterOf('x'),h.y-10);
									try{
										if((this._x < 1024) && (this._x > 0))
										(new Sound(FileList.bgsDir+"bgs17.wav")).play();
									}catch(SlickException error){
									}
								}else{
									h.hp -= 1;
									drawDmgFont("!",h.CenterOf('x'),h.y-10);
									if((this._x < 1024) && (this._x > 0))soundEffect[(int)(2+Math.random()*1)].play();
								}
								
								hit=true;
								dmgHit=true;
							}
						}
						break;
					}
				}
			}
		}
	}
	
	protected void monitor(CHARACTER hero){
	}
	
	public void drawSelf(){
		_x = x+this.mapX;
		
		if((this._x < 1024) && (this._x > 0))g.drawImage(shardow,_x,y+70);

		if(this.color.getRed() == 255){
			if(frameEf > 0)frameEf--;
			else color = new Color(255,255,255);
		}
		
		this.color.a = alpha;
		switch(inMode){
			case stopR:{
				walk=false;
				modeIn='>';
				dmgHit=false;
				g.drawAnimation(_stopR,_x,y,color);
				break;
			}case stopL:{
				walk=false;
				modeIn='<';
				dmgHit=false;
				g.drawAnimation(_stopL,_x,y,color);
				break;
			}case walkR:{
				walk=true;
				modeIn='>';
				g.drawAnimation(_walkR,_x,y,color);
				break;
			}case walkL:{
				walk=true;
				modeIn='<';
				g.drawAnimation(_walkL,_x,y,color);
				break;
			}case atk1R:{
				modeIn='>';
				g.drawAnimation(_atk1R,_x,y,color);
				break;
			}case atk1L:{
				modeIn='<';
				g.drawAnimation(_atk1L,_x-20,y,color);
				break;
			}case atk2R:{
				modeIn='>';
				g.drawAnimation(_atk2R,_x,y,color);
				break;
			}case atk2L:{
				modeIn='<';
				g.drawAnimation(_atk2L,_x,y,color);
				break;
			}case dieR:{
				modeIn='>';
				g.drawAnimation(_dieR,_x,y,color);
				break;
			}case dieL:{
				modeIn='<';
				g.drawAnimation(_dieL,_x,y,color);
				break;
			}
		}
		
		this.drawString(this.name,this.x+this.mapIndex+16,this.y-15,Color.red);
	}
	
	public void UPDATE(int mapX){
		this.mapX = mapX;
		this.updateing();
	}
}

class ENEMY_ARCHER extends ENEMY implements Serializable{
	private final int aspd = 150;
	private int delayAsp = aspd,atking;
	private CHARACTER tar;
	
	public ENEMY_ARCHER(){
		name = "ข้าศึก";
		hpMax = 80;
		hp = hpMax;
		speed = 2;
		range = 250;
		frame = new short[]{350,200,230,80};
		atk = 5;atkMax = 10;
		teep = 5;
		exp = 7;
		money = (int)(20+Math.random()*30);
		try{
			soundDie = new Sound("Resource/audio/bgs/eDie.wav");
			soundEffect = new Sound[]{
				new Sound("Resource/audio/bgs/bgs17.wav"),
				new Sound("Resource/audio/bgs/bgs16.wav"),
				new Sound("Resource/audio/bgs/hit1.wav"),
				new Sound("Resource/audio/bgs/hit3.wav"),
			};
		}catch(SlickException e){
			System.out.printf("Error : %s",e.toString());
		}
	}
	
	public void LOAD() throws SlickException{
		String dir = "Resource/animation/Enemy/Enemy03/";
		
		this._stopR = new Animation(
			new Image[]{
				new Image(dir+"stop/En3_sr_0001.png"),
				new Image(dir+"stop/En3_sr_0002.png"),
				new Image(dir+"stop/En3_sr_0001.png"),
			},frame[0]
		);
		
		this._stopL = new Animation(
			new Image[]{
				new Image(dir+"stop/En3_sl_0001.png"),
				new Image(dir+"stop/En3_sl_0002.png"),
				new Image(dir+"stop/En3_sl_0001.png"),
			},frame[0]
		);
		
		this._walkR = new Animation(
			new Image[]{
				new Image(dir+"walk/En3_wr_0001.png"),
				new Image(dir+"walk/En3_wr_0002.png"),
				new Image(dir+"walk/En3_wr_0003.png"),
				new Image(dir+"walk/En3_wr_0002.png"),
			},frame[1]
		);
		
		this._walkL = new Animation(
			new Image[]{
				new Image(dir+"walk/En3_wl_0001.png"),
				new Image(dir+"walk/En3_wl_0002.png"),
				new Image(dir+"walk/En3_wl_0003.png"),
				new Image(dir+"walk/En3_wl_0002.png"),
			},frame[1]
		);
		
		this._atk1R = new Animation(
			new Image[]{
				new Image(dir+"atk/En1_ar_.png"),
			},5
		);
		
		this._atk1L = new Animation(
			new Image[]{
				new Image(dir+"atk/En1_al_.png"),
			},5
		);
		
		this._dieR = new Animation(
			new Image[]{
				new Image(dir+"die/dier.png")
			},5
		);
		
		this._dieL = new Animation(
			new Image[]{
				new Image(dir+"die/diel.png")
			},5
		);
	}
	
	protected void attack(CHARACTER hero,HOME[] obj,ArrayList<SOLIDER> tar,ArrayList<MYSOLIDER> ms){
		this.tar = hero;
		this.obj = obj;
		this.sol = tar;
		this.subSol = ms;
		
		for(MYSOLIDER sol : ms){
			if(!sol.isDie()){
				if(sol._x >= this._x-range){
					if(modeIn == '<') inMode = MODE.atk1L;
					
					if(delayAsp > 0)
						delayAsp--;
					else{
						arrow.add(new TARROW(this.modeIn,(float)(2+Math.random()*10),atking,this._x,this.y));
						dmgHit = true;
						delayAsp = aspd;
						return;
					}
					return;
				}
			}
		}
		
		for(SOLIDER sol : tar){
			if(!sol.getDie()){
				if(sol._x >= this._x-range){
					if(modeIn == '<') inMode = MODE.atk1L;
					
					if(delayAsp > 0)
						delayAsp--;
					else{
						arrow.add(new TARROW(this.modeIn,(float)(2+Math.random()*10),atking,this._x,this.y));
						dmgHit = true;
						delayAsp = aspd;
						return;
					}
					return;
				}
			}
		}
		
		if(!hero.getDie()){
			if(hero._x >= this._x-range){
				if(modeIn == '<') inMode = MODE.atk1L;
				
				if(delayAsp > 0)
					delayAsp--;
				else{
					arrow.add(new TARROW(this.modeIn,(float)(2+Math.random()*10),atking,this._x,this.y));
					dmgHit = true;
					delayAsp = aspd;
				}
				return;
			}
		}
		
		for(HOME h : this.obj){
			if(h != null && !h.die()){
				objPositionX = h.CenterOf('x');
				objPositionY = h.CenterOf('Y');
				
				atking = this.getATK()-h.def;
				
				if(objPositionX <= this._x-range && !dmgHit){
					inMode = MODE.walkL;
					this.x -= this.speed;
					if(this.BOUNDINGBOX().intersects(h.BOUNDINGBOX()))hitHome = true;
					else hitHome = false;
					break;
				}
				if(objPositionX >= this._x-range){
					if(modeIn == '<') inMode = MODE.atk1L;
					
					if(delayAsp > 0)
						delayAsp--;
					else{
						arrow.add(new TARROW(this.modeIn,(float)(2+Math.random()*10),atking,this._x,this.y));
						dmgHit = true;
						delayAsp = aspd;
					}
				}
				break;
			}
		}
	}
	
	protected void monitor(CHARACTER hero){
	}
	
	public void drawSelf(){
		_x = x+this.mapX;
		if((this._x < 1024) && (this._x > 0))
			g.drawImage(shardow,_x,y+70);

		if(this.color.getRed() == 255){
			if(frameEf > 0)frameEf--;
			else color = new Color(255,255,255);
		}
		
		this.color.a = alpha;
		switch(inMode){
			case stopR:{
				walk=false;
				modeIn='>';
				dmgHit=false;
				g.drawAnimation(_stopR,_x,y,color);
				break;
			}case stopL:{
				walk=false;
				modeIn='<';
				dmgHit=false;
				g.drawAnimation(_stopL,_x,y,color);
				break;
			}case walkR:{
				walk=true;
				modeIn='>';
				g.drawAnimation(_walkR,_x,y,color);
				break;
			}case walkL:{
				walk=true;
				modeIn='<';
				g.drawAnimation(_walkL,_x,y,color);
				break;
			}case atk1R:{
				modeIn='>';
				g.drawAnimation(_atk1R,_x,y,color);
				inMode = MODE.stopR;
				break;
			}case atk1L:{
				modeIn='<';
				g.drawAnimation(_atk1L,_x,y,color);
				inMode = MODE.stopL;
				break;
			}case atk2R:{
				modeIn='>';
				g.drawAnimation(_atk2R,_x,y,color);
				break;
			}case atk2L:{
				modeIn='<';
				g.drawAnimation(_atk2L,_x,y,color);
				break;
			}case dieR:{
				modeIn='>';
				g.drawAnimation(_dieR,_x,y,color);
				break;
			}case dieL:{
				modeIn='<';
				g.drawAnimation(_dieL,_x,y,color);
				break;
			}
		}
		
		this.drawString(this.name,this.x+this.mapIndex+16,this.y-15,Color.red);
	}
	
	public void UPDATE(int mapX){
		this.mapX = mapX;
		this.updateing();
		
		for(TARROW arrows : arrow){
			if(arrows != null && !arrows.getRemove()){
				arrows.Update();
				arrows.drawSelf();
				arrows.setTarget(obj,tar,sol,subSol);
			}else{
				arrow.remove(arrows);
				break;
			}
		}
	}
}

class ENEMY_HERO extends ENEMY implements Serializable{
	public ENEMY_HERO(){
		name = "ข้าศึก";
		hpMax = 800;
		hp = hpMax;
		speed = 2;
		range = 30;
		frame = new short[]{350,200,230,80};
		atk = 60;atkMax = 25;
		exp = 25;
		money = (int)(100+Math.random()*100);
		try{
			soundDie = new Sound("Resource/audio/bgs/eDie.wav");
			soundEffect = new Sound[]{
				new Sound("Resource/audio/bgs/bgs17.wav"),
				new Sound("Resource/audio/bgs/bgs16.wav"),
				new Sound("Resource/audio/bgs/hit1.wav"),
				new Sound("Resource/audio/bgs/hit3.wav"),
			};
		}catch(SlickException e){
			System.out.printf("Error : %s",e.toString());
		}
	}
	
	public void LOAD() throws SlickException{
		String dir = "Resource/animation/Enemy/Enemy05/";
		
		this._stopR = new Animation(
			new Image[]{
				new Image(dir+"stop/En5_sr_0001.png"),
				new Image(dir+"stop/En5_sr_0002.png"),
				new Image(dir+"stop/En5_sr_0001.png"),
			},frame[0]
		);
	
		this._stopL = new Animation(
			new Image[]{
				new Image(dir+"stop/En5_sl_0001.png"),
				new Image(dir+"stop/En5_sl_0002.png"),
				new Image(dir+"stop/En5_sl_0001.png"),
			},frame[0]
		);
	
		this._walkR = new Animation(
			new Image[]{
				new Image(dir+"walk/En5_wr_0001.png"),
				new Image(dir+"walk/En5_wr_0002.png"),
				new Image(dir+"walk/En5_wr_0003.png"),
				new Image(dir+"walk/En5_wr_0002.png"),
			},frame[1]
		);
	
		this._walkL = new Animation(
			new Image[]{
				new Image(dir+"walk/En5_wl_0001.png"),
				new Image(dir+"walk/En5_wl_0002.png"),
				new Image(dir+"walk/En5_wl_0003.png"),
				new Image(dir+"walk/En5_wl_0002.png"),
			},frame[1]
		);
	
		this._atk1R = new Animation(
			new Image[]{
				new Image(dir+"atk/En5_ar_0001.png"),
				new Image(dir+"atk/En5_ar_0002.png"),
				new Image(dir+"atk/En5_ar_0003.png"),
				new Image(dir+"atk/En5_ar_0003.png"),
				new Image(dir+"atk/En5_ar_0003.png"),
				new Image(dir+"atk/En5_ar_0003.png"),
				new Image(dir+"atk/En5_ar_0003.png"),
			},frame[1]
		);
	
		this._atk1L = new Animation(
			new Image[]{
				new Image(dir+"atk/En5_al_0001.png"),
				new Image(dir+"atk/En5_al_0002.png"),
				new Image(dir+"atk/En5_al_0003.png"),
				new Image(dir+"atk/En5_al_0003.png"),
				new Image(dir+"atk/En5_al_0003.png"),
				new Image(dir+"atk/En5_al_0003.png"),
				new Image(dir+"atk/En5_al_0003.png"),
			},frame[1]
		);
	
		this._dieR = new Animation(
			new Image[]{
				new Image(dir+"die/dieR.png"),
			},frame[1]
		);
	
		this._dieL = new Animation(
			new Image[]{
				new Image(dir+"die/dieL.png"),
			},frame[1]
		);
	}
	
	protected void attack(CHARACTER hero,HOME[] obj,ArrayList<SOLIDER> tar,ArrayList<MYSOLIDER> ms){
		for(MYSOLIDER entity : ms){
			if(this.BOUNDINGBOX().intersects(entity.BOUNDINGBOX())){
				int atking = this.getATK()-entity.def;
				if(modeIn == '>') inMode = MODE.stopR;
				if(modeIn == '<') inMode = MODE.stopL;
				
				if(!entity.getDie()){
					if(modeIn == '<' && !walk){
						inMode = MODE.valueOf("atk"+1+"L");
						if(_atk1L.getFrame()==0)hit=false;
						if(_atk1L.getFrame() == _atk1L.getFrameCount()-1 && !hit){
							if(atking > 0){
								entity.hp-= atking;
								drawDmgFont(atking+"",entity.CenterOf('x'),entity.y-10);
								try{
									if((this._x < 1024) && (this._x > 0))
									(new Sound(FileList.bgsDir+"bgs17.wav")).play();
								}catch(SlickException error){
								}
							}else{
								entity.hp -= 1;
								drawDmgFont(1+"!",entity.CenterOf('x'),entity.y-10);
								if((this._x < 1024) && (this._x > 0))soundEffect[(int)(2+Math.random()*1)].play();
							}	
							hit=true;
							dmgHit=true;
						}
					}		
					if(modeIn == '>' && !walk){
						inMode = MODE.valueOf("atk"+1+"R");
						if(_atk1R.getFrame()==0)hit=false;
						if(_atk1R.getFrame() == _atk1R.getFrameCount()-1 && !hit){
							if(atking > 0){
								hero.HP -= atking;
								drawDmgFont(atking+"",hero.CenterOf('x'),hero._y-10);
								try{
									if((this._x < 1024) && (this._x > 0))
									(new Sound(FileList.bgsDir+"bgs17.wav")).play();
								}catch(SlickException error){
								}
							}else{
								hero.HP -= 1;
								drawDmgFont("!",hero.CenterOf('x'),hero._y-10);
								if((this._x < 1024) && (this._x > 0))soundEffect[(int)(2+Math.random()*1)].play();
							}
								
							hit=true;
							dmgHit=true;
						}
					}
					return;
				}
			}
		}
	
		for(SOLIDER entity : tar){
			if(this.BOUNDINGBOX().intersects(entity.BOUNDINGBOX())){
				int atking = this.getATK()-entity.def;
				if(modeIn == '>') inMode = MODE.stopR;
				if(modeIn == '<') inMode = MODE.stopL;
				
				if(!entity.getDie()){
					if(modeIn == '<' && !walk){
						inMode = MODE.valueOf("atk"+1+"L");
						if(_atk1L.getFrame()==0)hit=false;
						if(_atk1L.getFrame() == _atk1L.getFrameCount()-1 && !hit){
							if(atking > 0){
								entity.hp-= atking;
								drawDmgFont(atking+"",entity.CenterOf('x'),entity.y-10);
								try{
									if((this._x < 1024) && (this._x > 0))
									(new Sound(FileList.bgsDir+"bgs17.wav")).play();
								}catch(SlickException error){
								}
							}else{
								entity.hp -= 1;
								drawDmgFont(1+"!",entity.CenterOf('x'),entity.y-10);
								if((this._x < 1024) && (this._x > 0))soundEffect[(int)(2+Math.random()*1)].play();
							}	
							hit=true;
							dmgHit=true;
						}
					}		
					if(modeIn == '>' && !walk){
						inMode = MODE.valueOf("atk"+1+"R");
						if(_atk1R.getFrame()==0)hit=false;
						if(_atk1R.getFrame() == _atk1R.getFrameCount()-1 && !hit){
							if(atking > 0){
								hero.HP -= atking;
								drawDmgFont(atking+"",hero.CenterOf('x'),hero._y-10);
								try{
									if((this._x < 1024) && (this._x > 0))
									(new Sound(FileList.bgsDir+"bgs17.wav")).play();
								}catch(SlickException error){
								}
							}else{
								hero.HP -= 1;
								drawDmgFont("!",hero.CenterOf('x'),hero._y-10);
								if((this._x < 1024) && (this._x > 0))soundEffect[(int)(2+Math.random()*1)].play();
							}
								
							hit=true;
							dmgHit=true;
						}
					}
					return;
				}
			}
		}
		
		if(this.BOUNDINGBOX().intersects(hero.getBox())){
			int atking = this.getATK()-hero.DEF;
			if(modeIn == '>') inMode = MODE.stopR;
			if(modeIn == '<') inMode = MODE.stopL;
				
			if(!hero.getDie()){
				if(modeIn == '<' && !walk){
					inMode = MODE.valueOf("atk"+1+"L");
					if(_atk1L.getFrame()==0)hit=false;
					if(_atk1L.getFrame() == _atk1L.getFrameCount()-1 && !hit){
						if(atking > 0){
							hero.HP -= atking;
							drawDmgFont(atking+"",hero.CenterOf('x'),hero._y-10);
							try{
								if((this._x < 1024) && (this._x > 0))
								(new Sound(FileList.bgsDir+"bgs17.wav")).play();
							}catch(SlickException error){
							}
						}else{
							hero.HP -= 1;
							drawDmgFont(1+"!",hero.CenterOf('x'),hero._y-10);
							if((this._x < 1024) && (this._x > 0))soundEffect[(int)(2+Math.random()*1)].play();
						}	
						hit=true;
						dmgHit=true;
					}
				}		
				if(modeIn == '>' && !walk){
					inMode = MODE.valueOf("atk"+1+"R");
					if(_atk1R.getFrame()==0)hit=false;
					if(_atk1R.getFrame() == _atk1R.getFrameCount()-1 && !hit){
						if(atking > 0){
							hero.HP -= atking;
							drawDmgFont(atking+"",hero.CenterOf('x'),hero._y-10);
							try{
								if((this._x < 1024) && (this._x > 0))
								(new Sound(FileList.bgsDir+"bgs17.wav")).play();
							}catch(SlickException error){
							}
						}else{
							hero.HP -= 1;
							drawDmgFont("!",hero.CenterOf('x'),hero._y-10);
							if((this._x < 1024) && (this._x > 0))soundEffect[(int)(2+Math.random()*1)].play();
						}
								
						hit=true;
						dmgHit=true;
					}
				}
				return;
			}
		}
		
		for(HOME h : obj){
			if(h != null && !h.die()){
				objPositionX = h.CenterOf('x');
				objPositionY = h.CenterOf('Y');
				hitHome = false;
				
				if((objPositionX <= this._x-range) && !dmgHit){
					this.walk = true;
					inMode = MODE.walkL;
					this.x -= this.speed;
					
					break;
				}else if((objPositionX >= this._x+range) && !dmgHit){
					this.walk = true;
					inMode = MODE.walkR;
					this.x += this.speed;
					break;
				}else{
					if(modeIn == '>') inMode = MODE.stopR;
					if(modeIn == '<') inMode = MODE.stopL;
				}
				
				if(this.BOUNDINGBOX().intersects(h.BOUNDINGBOX())){
					int atkHome = this.getATK()-h.def;
					hitHome = true;
					if(!h.die()){
						if(modeIn == '<' && !walk){
							inMode = MODE.valueOf("atk"+1+"L");
							if(_atk1L.getFrame()==0)hit=false;
							if(_atk1L.getFrame() == _atk1L.getFrameCount()-1 && !hit){
								if(this.getATK()-h.def > 0){
									h.hp -= atkHome;
									drawDmgFont(atkHome+"",h.CenterOf('x'),h.y-10);
									try{
										if((this._x < 1024) && (this._x > 0))
										(new Sound(FileList.bgsDir+"bgs17.wav")).play();
									}catch(SlickException error){
									}
								}else{
									h.hp -= 1;
									drawDmgFont(1+"!",h.CenterOf('x'),h.y-10);
									if((this._x < 1024) && (this._x > 0))soundEffect[(int)(2+Math.random()*1)].play();
								}
								
								hit=true;
								dmgHit=true;
							}
						}		
						if(modeIn == '>' && !walk){
							inMode = MODE.valueOf("atk"+1+"R");
							if(_atk1R.getFrame()==0)hit=false;
							if(_atk1R.getFrame() == _atk1R.getFrameCount()-1 && !hit){
								if(this.getATK()-h.def > 0){
									h.hp -= atkHome;
									drawDmgFont(atkHome+"",h.CenterOf('x'),h.y-10);
									try{
										if((this._x < 1024) && (this._x > 0))
										(new Sound(FileList.bgsDir+"bgs17.wav")).play();
									}catch(SlickException error){
									}
								}else{
									h.hp -= 1;
									drawDmgFont("!",h.CenterOf('x'),h.y-10);
									if((this._x < 1024) && (this._x > 0))soundEffect[(int)(2+Math.random()*1)].play();
								}
								
								hit=true;
								dmgHit=true;
							}
						}
						break;
					}
				}
			}
		}
	}
	
	protected void monitor(CHARACTER hero){
	}
	
	public void drawSelf(){
		_x = x+this.mapX;
		
		if((this._x < 1024) && (this._x > 0))g.drawImage(shardow,_x,y+70);

		if(this.color.getRed() == 255){
			if(frameEf > 0)frameEf--;
			else color = new Color(255,255,255);
		}
		
		this.color.a = alpha;
		switch(inMode){
			case stopR:{
				walk=false;
				modeIn='>';
				dmgHit=false;
				g.drawAnimation(_stopR,_x,y,color);
				break;
			}case stopL:{
				walk=false;
				modeIn='<';
				dmgHit=false;
				g.drawAnimation(_stopL,_x,y,color);
				break;
			}case walkR:{
				walk=true;
				modeIn='>';
				g.drawAnimation(_walkR,_x,y,color);
				break;
			}case walkL:{
				walk=true;
				modeIn='<';
				g.drawAnimation(_walkL,_x,y,color);
				break;
			}case atk1R:{
				modeIn='>';
				g.drawAnimation(_atk1R,_x,y,color);
				break;
			}case atk1L:{
				modeIn='<';
				g.drawAnimation(_atk1L,_x-20,y,color);
				break;
			}case atk2R:{
				modeIn='>';
				g.drawAnimation(_atk2R,_x,y,color);
				break;
			}case atk2L:{
				modeIn='<';
				g.drawAnimation(_atk2L,_x,y,color);
				break;
			}case dieR:{
				modeIn='>';
				g.drawAnimation(_dieR,_x,y,color);
				break;
			}case dieL:{
				modeIn='<';
				g.drawAnimation(_dieL,_x,y,color);
				break;
			}
		}
		
		this.drawString(this.name,this.x+this.mapIndex+16,this.y-15,Color.red);
	}
	
	public void UPDATE(int mapX){
		this.mapX = mapX;
		this.updateing();
	}
}

class ENEMY_GUNNER extends ENEMY implements Serializable{
	private final int aspd = 150;
	private int delayAsp = aspd,atking;
	private CHARACTER tar;
	private boolean shoot = false;
	
	public ENEMY_GUNNER(){
		name = "ข้าศึก";
		hpMax = 150;
		hp = hpMax;
		speed = 2;
		range = 250;
		frame = new short[]{350,200,230,80};
		atk = 80;atkMax = 50;
		teep = 5;
		exp = 50;
		money = (int)(100+Math.random()*150);
		try{
			soundDie = new Sound("Resource/audio/bgs/eDie.wav");
			soundEffect = new Sound[]{
				new Sound("Resource/audio/bgs/bgs17.wav"),
				new Sound("Resource/audio/bgs/bgs16.wav"),
				new Sound("Resource/audio/bgs/hit1.wav"),
				new Sound("Resource/audio/bgs/hit3.wav"),
			};
		}catch(SlickException e){
			System.out.printf("Error : %s",e.toString());
		}
	}
	
	public void LOAD() throws SlickException{
		String dir = "Resource/animation/Enemy/Enemy06/";
		
		this._stopR = new Animation(
			new Image[]{
				new Image(dir+"stop/En6_sr_0001.png"),
				new Image(dir+"stop/En6_sr_0002.png"),
				new Image(dir+"stop/En6_sr_0001.png"),
			},frame[0]
		);
		
		this._stopL = new Animation(
			new Image[]{
				new Image(dir+"stop/En6_sl_0001.png"),
				new Image(dir+"stop/En6_sl_0002.png"),
				new Image(dir+"stop/En6_sl_0001.png"),
			},frame[0]
		);
		
		this._walkR = new Animation(
			new Image[]{
				new Image(dir+"walk/En6_wr_0001.png"),
				new Image(dir+"walk/En6_wr_0002.png"),
				new Image(dir+"walk/En6_wr_0003.png"),
				new Image(dir+"walk/En6_wr_0002.png"),
			},frame[1]
		);
		
		this._walkL = new Animation(
			new Image[]{
				new Image(dir+"walk/En6_wl_0001.png"),
				new Image(dir+"walk/En6_wl_0002.png"),
				new Image(dir+"walk/En6_wl_0003.png"),
				new Image(dir+"walk/En6_wl_0002.png"),
			},frame[1]
		);
		
		this._atk1R = new Animation(
			new Image[]{
				new Image(dir+"atk/atkR.png"),
				new Image(dir+"atk/atkR.png"),
				new Image(dir+"atk/atkR.png"),
				new Image(dir+"atk/atkR.png"),
				new Image(dir+"atk/atkR.png"),
			},1
		);
		
		this._atk1L = new Animation(
			new Image[]{
				new Image(dir+"atk/atkL.png"),
				new Image(dir+"atk/atkL.png"),
				new Image(dir+"atk/atkL.png"),
				new Image(dir+"atk/atkL.png"),
				new Image(dir+"atk/atkL.png"),
			},1
		);
		
		this._dieR = new Animation(
			new Image[]{
				new Image(dir+"die/DieR.png")
			},10
		);
		
		this._dieL = new Animation(
			new Image[]{
				new Image(dir+"die/DieL.png")
			},5
		);
	}
	
	protected void attack(CHARACTER hero,HOME[] obj,ArrayList<SOLIDER> tar,ArrayList<MYSOLIDER> ms){
		this.tar = hero;
		this.obj = obj;
		this.sol = tar;
		this.subSol = ms;
		
		for(MYSOLIDER sol : ms){
			if(!sol.isDie()){
				if(sol._x >= this._x-range){
					if(modeIn == '<') inMode = MODE.atk1L;
					
					if(delayAsp > 0)
						delayAsp--;
					else{
						if((this._x < 1024) && (this._x > 0)){
							try{
								Sound s = new Sound(FileList.bgsDir+"Weapon_gun_01.wav");
								s.play();
								shoot=true;
							}catch(SlickException e){
								javax.swing.JOptionPane.showMessageDialog(null,e.toString(),"Message",javax.swing.JOptionPane.ERROR_MESSAGE);
							}
						}
						bullet.add(new Bullet(this.modeIn,atking,this._x,this.y));
						dmgHit = true;
						delayAsp = aspd;
						return;
					}
					return;
				}
			}
		}
		
		for(SOLIDER sol : tar){
			if(!sol.getDie()){
				if(sol._x >= this._x-range){
					if(modeIn == '<') inMode = MODE.atk1L;
					
					if(delayAsp > 0)
						delayAsp--;
					else{
						if((this._x < 1024) && (this._x > 0)){
							try{
								Sound s = new Sound(FileList.bgsDir+"Weapon_gun_01.wav");
								s.play();
								shoot=true;
							}catch(SlickException e){
								javax.swing.JOptionPane.showMessageDialog(null,e.toString(),"Message",javax.swing.JOptionPane.ERROR_MESSAGE);
							}
						}
						bullet.add(new Bullet(this.modeIn,atking,this._x,this.y));
						dmgHit = true;
						delayAsp = aspd;
						return;
					}
					return;
				}
			}
		}
		
		if(!hero.getDie()){
			if(hero._x >= this._x-range){
				if(modeIn == '<') inMode = MODE.atk1L;
				
				if(delayAsp > 0)
					delayAsp--;
				else{
					if((this._x < 1024) && (this._x > 0)){
							try{
								Sound s = new Sound(FileList.bgsDir+"Weapon_gun_01.wav");
								s.play();
								shoot=true;
							}catch(SlickException e){
								javax.swing.JOptionPane.showMessageDialog(null,e.toString(),"Message",javax.swing.JOptionPane.ERROR_MESSAGE);
							}
						}
					bullet.add(new Bullet(this.modeIn,atking,this._x,this.y));
					dmgHit = true;
					delayAsp = aspd;
				}
				return;
			}
		}
		
		for(HOME h : this.obj){
			if(h != null && !h.die()){
				objPositionX = h.CenterOf('x');
				objPositionY = h.CenterOf('Y');
				
				atking = this.getATK()-h.def;
				
				if(objPositionX <= this._x-range && !dmgHit){
					inMode = MODE.walkL;
					this.x -= this.speed;
					if(this.BOUNDINGBOX().intersects(h.BOUNDINGBOX()))hitHome = true;
					else hitHome = false;
					break;
				}
				if(objPositionX >= this._x-range){
					if(modeIn == '<') inMode = MODE.atk1L;
					
					if(delayAsp > 0)
						delayAsp--;
					else{
						if((this._x < 1024) && (this._x > 0)){
							try{
								Sound s = new Sound(FileList.bgsDir+"Weapon_gun_01.wav");
								s.play();
								shoot=true;
							}catch(SlickException e){
								javax.swing.JOptionPane.showMessageDialog(null,e.toString(),"Message",javax.swing.JOptionPane.ERROR_MESSAGE);
							}
						}
						bullet.add(new Bullet(this.modeIn,atking,this._x,this.y));
						dmgHit = true;
						delayAsp = aspd;
					}
				}
				break;
			}
		}
	}
	
	protected void monitor(CHARACTER hero){
	}
	
	public void drawSelf(){
		_x = x+this.mapX;
		if((this._x < 1024) && (this._x > 0))
			g.drawImage(shardow,_x,y+70);

		if(this.color.getRed() == 255){
			if(frameEf > 0)frameEf--;
			else color = new Color(255,255,255);
		}
		
		
		this.color.a = alpha;
		switch(inMode){
			case stopR:{
				walk=false;
				modeIn='>';
				dmgHit=false;
				g.drawAnimation(_stopR,_x,y,color);
				if(shoot){
					if((this._x < 1024) && (this._x > 0)){
						try{
							Sound s = new Sound(FileList.bgsDir+"Weapon_gun_02.wav");
							s.play();
						}catch(SlickException e){
							javax.swing.JOptionPane.showMessageDialog(null,e.toString(),"Message",javax.swing.JOptionPane.ERROR_MESSAGE);
							shoot=false;
						}
					}
					shoot=false;
				}
				break;
			}
			case stopL:{
				walk=false;
				modeIn='<';
				dmgHit=false;
				g.drawAnimation(_stopL,_x,y,color);
				if(shoot){
					if((this._x < 1024) && (this._x > 0)){
						try{
							Sound s = new Sound(FileList.bgsDir+"Weapon_gun_02.wav");
							s.play();
						}catch(SlickException e){
							javax.swing.JOptionPane.showMessageDialog(null,e.toString(),"Message",javax.swing.JOptionPane.ERROR_MESSAGE);
							shoot=false;
						}
					}
					shoot=false;
				}
				break;
			}
			case walkR:{
				walk=true;
				modeIn='>';
				g.drawAnimation(_walkR,_x,y,color);
				break;
			}
			case walkL:{
				walk=true;
				modeIn='<';
				g.drawAnimation(_walkL,_x,y,color);
				break;
			}
			case atk1R:{
				modeIn='>';
				g.drawAnimation(_atk1R,_x,y,color);
				inMode = MODE.stopR;
				if(this._atk1R.getFrame() >= _atk1R.getFrameCount()-1){
					shoot=false;
				}
				break;
			}
			case atk1L:{
				modeIn='<';
				g.drawAnimation(_atk1L,_x,y,color);
				inMode = MODE.stopL;
				if(this._atk1R.getFrame() >= _atk1R.getFrameCount()-1){
					shoot=false;
				}
				break;
			}
			case atk2R:{
				modeIn='>';
				g.drawAnimation(_atk2R,_x,y,color);
				break;
			}case atk2L:{
				modeIn='<';
				g.drawAnimation(_atk2L,_x,y,color);
				break;
			}
			case dieR:{
				modeIn='>';
				g.drawAnimation(_dieR,_x,y,color);
				break;
			}
			case dieL:{
				modeIn='<';
				g.drawAnimation(_dieL,_x,y,color);
				break;
			}
		}
		
		this.drawString(this.name,this.x+this.mapIndex+16,this.y-15,Color.red);
	}
	
	public void UPDATE(int mapX){
		this.mapX = mapX;
		this.updateing();
		
		for(Bullet bull : bullet){
			if(bull != null && !bull.getDie()){
				bull.UpdateAndDraw(obj,tar,sol,subSol);
			}else{
				bullet.remove(bull);
				break;
			}
		}
	}
}

abstract class NPC implements Serializable {
	protected Animation anim,msger;
	protected String name,msg[];
	protected int x,mapX,_x,y;
	protected int mapIndex;
	protected int frameU = 0,frameN = 0;
	protected Font font;
	protected Image shardow,dialog[] = new Image[3],button[] = new Image[3];
	protected Sound sound[] = new Sound[4];
	protected int rnd;
	protected boolean talk,click,clicked;
	protected GameContainer container;
	protected Graphics g = new Graphics();
	protected Rectangle m;
	protected ScrollTxt scrollText;
	protected ScrollMenu scrollMenu;
	protected ScrollListItem scrollListItem;
	protected CHARACTER tar;
	protected ParticleSystem[] pr = new ParticleSystem[2];
	protected int dialogCenterX,buttomCenterX;
	protected ScrollTxt sTarget;
	
	public NPC(){
		y = 435;
		talk = false;
		clicked = false;
		
		try{
			scrollText = new ScrollTxt();
			scrollMenu = new ScrollMenu();
			scrollListItem = new ScrollListItem();
			
			font = new AngelCodeFont("Resource/font/simpleFont.fnt","Resource/font/simpleFont.png");
			shardow = new Image("Resource/img/shardowA");
			String emotionDir = "Resource/emotion/";
			pr[0] = ParticleIO.loadConfiguredSystem("Resource/effect/ef006.xml");
			
			msger = new Animation(
				new Image[]{
					new Image(emotionDir+"em01_01.png"),
					new Image(emotionDir+"em01_02.png"),
					new Image(emotionDir+"em01_03.png"),
					new Image(emotionDir+"em01_04.png"),
				},200
			);
			
			dialog = new Image[]{
				new Image(FileList.imgeDir+"interface_menu2"),
			};
			
			button = new Image[]{
				new Image(FileList.imgeDir+"bClose.PNG"),
			};
			
			sound = new Sound[]{
				new Sound(FileList.bgsDir+"bgs02.wav"),
				new Sound(FileList.bgsDir+"bgs03.wav"),
				new Sound(FileList.bgsDir+"Recovery.wav"),
				new Sound(FileList.bgsDir+"castsound.wav"),
			};
		}catch(SlickException e){
		}catch(IOException er){
		}
	}
	
	public ScrollMenu getMenu(){
		return(this.scrollMenu);
	}
	
	protected void rndm(){
		if(rnd > this.msg.length){
			rndm();
		}else{
			rnd = (int)(0+Math.random()*this.msg.length-1);
		}
	}
	
	public void LOADMESSAGE(String msg){
		this.msg = msg.split(",");
		this.rndm();
	}
	
	public void setContainer(GameContainer container){
		this.container = container;
	}
	
	public void setCursorSelect(Rectangle m){
		this.m = m;
	}
	
	public Rectangle BOUNDINGBOX(){
		Rectangle r = new Rectangle(_x,y,40,65);
		return(r);
	}
	
	public void onClick(){
		Input input = container.getInput();
		
		if(m.intersects(BOUNDINGBOX())){
			click = true;
			try{
				if(input.isMousePressed(input.MOUSE_LEFT_BUTTON)){
					sound[1].play();
					clicked = !clicked;
					input=null;
					return;
				}
			}catch(Exception e){
			}
		}else{
			click = false;
		}
	}
	
	public void drawSelf(){
		this._x = x+this.mapX;
		
		if((this._x < 1024) && (this._x > 0))g.drawImage(shardow,_x,y+70);
		if((this._x < 1024) && (this._x > 0))g.drawAnimation(this.anim,_x,y);
		if((this._x < 1024) && (this._x > 0))
			drawString(this.name,this._x+(55/font.getWidth(name))/2,this.y-15,Color.green);
	}
	
	protected void drawString(String msg,int x,int y,Color c){
		Graphics g = new Graphics();
		g.setFont(font);
		
		g.setColor(new Color(0,0,0,200));
		g.drawString(msg,x+1,y+1);
		g.setColor(c);
		g.drawString(msg,x,y);
	}
	
	public void Update(int mapX,ScrollTxt scrollT){
		this.mapX = mapX;
		this.sTarget = scrollT;
		String txt;
		try{
			txt = this.msg[this.rnd];
		}catch(NullPointerException e){
			txt = this.msg[0];
		}
		pr[0].update(11);
		if(frameN > 0){
			frameN--;
		}else{
			if(frameU > 0){
				if(!talk){
					if((this._x < 1024) && (this._x > 0)){
						scrollT.addString(this.name+" : "+txt);
						scrollT.nextLine();
					}
					talk=true;
				}
				int widthser = 20+font.getWidth(txt);
				int msgX = _x+(55-widthser)/2;
				g.setColor(new Color(0,0,0,150));
				g.fillRoundRect(msgX,y-50,widthser,20+font.getHeight(txt),5);
				g.setColor(new Color(255,255,255));
				this.drawString(txt,msgX+(widthser-font.getWidth(txt))/2,y-50+font.getHeight(txt)/2,Color.white);
				frameU--;
			}else{
				talk=false;
				frameN = (int)Math.random()*500+300;
				frameU = (int)Math.random()*500+200;
				this.rndm();
			}
		}
	}
	
	protected boolean Button(Image img,int x,int y){
		Input input = container.getInput();
		Rectangle button = new Rectangle(x,y,img.getWidth(),img.getHeight());
		
		if(m.intersects(button)){
			if(input.isMousePressed(input.MOUSE_LEFT_BUTTON)){
				sound[0].play();
				return(true);
			}
			g.drawImage(img,x,y,new Color(255,255,255));
		}
		g.drawImage(img,x,y,new Color(150,150,150));
		
		return(false);
	}
	
	public void setTarget(CHARACTER h){
		this.tar = h;
	}
	
	public String getName(){
		return(this.name);
	}
	
	public abstract void LOAD() throws SlickException;
	protected abstract void onData();
}

class NPC_01 extends NPC{
	public NPC_01(int mapIndex,int x){
		this.name = "ลุงขวาน";
		this.mapIndex = mapIndex;
		this.x = -1024+1024*mapIndex+x;
		frameU = (int)Math.random()*500+300;
		frameN = (int)Math.random()*500+300;
		dialogCenterX = (1024-dialog[0].getWidth())/2;
		buttomCenterX = dialogCenterX+dialog[0].getWidth()-button[0].getWidth()-11;
		
		scrollListItem.setRect(dialogCenterX+12,200,375,359);
		scrollListItem.subject("เมนูค้าขาย");
		scrollListItem.addList(FileList.iconDir+"icItem01.png","ปุ๋ย",10,"ปุ๋ย สำหรับพืช");
		scrollListItem.addList(FileList.iconDir+"icItem02.png","ช้อนพรวนดิน",200,"ช้อนสำหรับพรวนดิน");
		scrollListItem.addList(FileList.iconDir+"icItem03.png","น้ำเปล่า",8,"น้ำเปล่าสำหรับลดน้ำต้นไม้");
		scrollListItem.addList(FileList.iconDir+"icItem04.png","เม็ดพันธุ์ผัก D",8,"เมล็ดพันธุ์สำหรับปลูกผัก");
		scrollListItem.addList(FileList.iconDir+"icItem04.png","เม็ดพันธุ์ผัก C",15,"เมล็ดพันธุ์สำหรับปลูกผัก");
		scrollListItem.addList(FileList.iconDir+"icItem04.png","เม็ดพันธุ์ผัก B",30,"เมล็ดพันธุ์สำหรับปลูกผัก");
		scrollListItem.addList(FileList.iconDir+"icItem04.png","เม็ดพันธุ์ผัก A",60,"เมล็ดพันธุ์สำหรับปลูกผัก");
	}
	
	protected void onData(){
		if(click){
			g.drawAnimation(msger,_x,y-50);
		}
		
		if(clicked && !tar.getDie()){
			g.drawImage(dialog[0],dialogCenterX,150);
			
			if(Button(button[0],buttomCenterX,137+button[0].getHeight())){
				clicked = false;
			}
			
			try{
				scrollListItem.render(container,m);
				
				if(scrollListItem.getList().get(0).isButton()){
					if(tar.gold_am >= scrollListItem.getList().get(0).getPrice()){
						tar.item[0]+=10;
						sound[3].play();
						tar.gold_am-=scrollListItem.getList().get(0).getPrice();
					}
				}
				
				if(scrollListItem.getList().get(1).isButton()){
					if(tar.gold_am >= scrollListItem.getList().get(1).getPrice()){
						tar.item[1]+=5;
						sound[3].play();
						tar.gold_am-=scrollListItem.getList().get(1).getPrice();
					}
				}
				
				if(scrollListItem.getList().get(2).isButton()){
					if(tar.gold_am >= scrollListItem.getList().get(2).getPrice()){
						tar.item[2]+=10;
						sound[3].play();
						tar.gold_am-=scrollListItem.getList().get(2).getPrice();
					}
				}
				
				if(scrollListItem.getList().get(3).isButton()){
					if(tar.gold_am >= scrollListItem.getList().get(3).getPrice()){
						tar.item[3]++;
						sound[3].play();
						tar.gold_am-=scrollListItem.getList().get(3).getPrice();
					}
				}
				
				if(scrollListItem.getList().get(4).isButton()){
					if(tar.gold_am >= scrollListItem.getList().get(4).getPrice()){
						tar.item[4]++;
						sound[3].play();
						tar.gold_am-=scrollListItem.getList().get(4).getPrice();
					}
				}
				
				if(scrollListItem.getList().get(5).isButton()){
					if(tar.gold_am >= scrollListItem.getList().get(4).getPrice()){
						tar.item[5]++;
						sound[3].play();
						tar.gold_am-=scrollListItem.getList().get(5).getPrice();
					}
				}
				
				if(scrollListItem.getList().get(6).isButton()){
					if(tar.gold_am >= scrollListItem.getList().get(6).getPrice()){
						tar.item[6]++;
						sound[3].play();
						tar.gold_am-=scrollListItem.getList().get(6).getPrice();
					}
				}
				
			}catch(SlickException e){
			}
		}
	}
	
	public void LOAD() throws SlickException{
		int frameIt = 500;
		anim = new Animation(
			new Image[]{
				new Image("Resource/npc/npc01_0001"),
				new Image("Resource/npc/npc01_0002"),
			},frameIt
		);
	}
}

class NPC_02 extends NPC{
	public NPC_02(int mapIndex,int x){
		this.name = "ยายแปง";
		this.mapIndex = mapIndex;
		this.x = -1024+1024*mapIndex+x;
		frameU = (int)Math.random()*500+300;
		frameN = (int)Math.random()*500+300;
		dialogCenterX = (1024-dialog[0].getWidth())/2;
		buttomCenterX = dialogCenterX+dialog[0].getWidth()-button[0].getWidth()-11;
		
		scrollListItem.setRect(dialogCenterX+12,200,376,359);
		scrollListItem.subject("เมนูค้าขาย");
		scrollListItem.addList(FileList.iconDir+"icItem05.png","น้ำดืมสะอาด",50,"น้ำดืม สะอาดช่วยแก้กระหายน้ำ WP + 15");
		scrollListItem.addList(FileList.iconDir+"icItem07.png","ลูกธนูไม้",100,"ลูกดอกสำหรับธนู บรรจุไว้ 100 นัด");
		scrollListItem.addList(FileList.iconDir+"icItem08.png","ข้าวห่อใบตอง",30,"อาหารสำหรับนักเดินทาง HP + 20");
		scrollListItem.addList(FileList.iconDir+"icItem09.png","ใบแห่งพลัง",50,"ใบไม้ที่มีพลังเวทแฝงอยู่ MP + 30");
		scrollListItem.addList(FileList.iconDir+"icItem10.png","ขาหมูย่าง",40,"ขาหมู่ย่างกลิ่นหอม PW + 20");
	}
	
	protected void onData(){
		if(click){
			g.drawAnimation(msger,_x,y-50);
		}
		
		if(clicked && !tar.getDie()){
			g.drawImage(dialog[0],dialogCenterX,150);

			if(Button(button[0],buttomCenterX,137+button[0].getHeight())){
				clicked = false;
			}
			
			try{
				scrollListItem.render(container,m);
			}catch(SlickException e){
			}
			
			//System.out.println(scrollListItem.getList().get(0));
			
			if(scrollListItem.getList().get(0).isButton()){
				if(this.tar.gold_am >= scrollListItem.getList().get(0).getPrice()){
					this.tar.gold_am-=scrollListItem.getList().get(0).getPrice();
					this.tar.waterItem++;
					this.sound[3].play();
				}
			}
			
			if(scrollListItem.getList().get(1).isButton()){
				if(this.tar.gold_am >= scrollListItem.getList().get(1).getPrice()){
					this.tar.gold_am-=scrollListItem.getList().get(1).getPrice();
					this.tar.allowVar+=100;
					this.sound[3].play();
				}
			}
			
			if(scrollListItem.getList().get(2).isButton()){
				if(this.tar.gold_am >= scrollListItem.getList().get(2).getPrice()){
					this.tar.gold_am-=scrollListItem.getList().get(2).getPrice();
					this.tar.hpItem++;
					this.sound[3].play();
				}
			}
			
			if(scrollListItem.getList().get(3).isButton()){
				if(this.tar.gold_am >= scrollListItem.getList().get(3).getPrice()){
					this.tar.gold_am-=scrollListItem.getList().get(3).getPrice();
					this.tar.mpItem++;
					this.sound[3].play();
				}
			}
			
			if(scrollListItem.getList().get(4).isButton()){
				if(this.tar.gold_am >= scrollListItem.getList().get(4).getPrice()){
					this.tar.gold_am-=scrollListItem.getList().get(4).getPrice();
					this.tar.pwItem++;
					this.sound[3].play();
				}
			}
		}
	}
	
	public void LOAD() throws SlickException{
		int frameIt = 500;
		anim = new Animation(
			new Image[]{
				new Image("Resource/npc/npc02_0001"),
				new Image("Resource/npc/npc02_0002"),
			},frameIt
		);
	}
}

class NPC_03 extends NPC{
	private int delay = 300;
	private boolean buff = false;
	
	public NPC_03(int mapIndex,int x){
		this.name = "หมอบุณ";
		this.mapIndex = mapIndex;
		this.x = -1024+1024*mapIndex+x;
		frameU = (int)Math.random()*500+300;
		frameN = (int)Math.random()*500+300;
		dialogCenterX = (1024-dialog[0].getWidth())/2;
		buttomCenterX = dialogCenterX+dialog[0].getWidth()-button[0].getWidth()-11;
		
		scrollText.setRect(dialogCenterX+13,200,373,160);
		scrollText.addString("สวัสดี 'อาจอง' เธอมีอาไรให้ฉันช่วยไหม ?");
		
		scrollMenu.setRect(dialogCenterX+13,395,373,160);
		scrollMenu.addList("ฟื้นฟูพลังชีวิต(50 b)");
		scrollMenu.addList("ฟื้นฟูพลังเวท(60 b)");
		scrollMenu.addList("ฟื้นฟูพลังงาน(70 b).");
	}
	
	protected void onData(){
		if(click){
			g.drawAnimation(msger,_x,y-50);
		}
		
		if(buff){
			if(delay > 0){
				pr[0].render(tar.CenterOf('x'),tar.CenterOf('y')+20);
				delay--;
			}else{
				delay=300;
				buff=false;
			}
		}
		
		if(clicked && !tar.getDie()){
			g.drawImage(dialog[0],dialogCenterX,150);
			if(Button(button[0],buttomCenterX,137+button[0].getHeight())){
				clicked = false;
			}
			try{
				scrollText.render(container,m);
				scrollMenu.render(container,m);
			}catch(SlickException e){}
			
			scrollText.subject("ข้อความ");
			scrollMenu.subject("ตัวเลือก");
			
			if(scrollMenu.getList().get(0).isButton()){
				if(tar.gold_am >= 50 && tar.HP < tar.HPMAX){
					tar.HP = tar.HPMAX;
					tar.gold_am -= 50;
					sound[2].play();
					buff=true;
					clicked=false;
				}
			}
			
			if(scrollMenu.getList().get(1).isButton()){
				if(tar.gold_am >= 60 && tar.SP < tar.SPMAX){
					tar.SP = tar.SPMAX;
					tar.gold_am -= 60;
					sound[2].play();
					buff=true;
					clicked=false;
				}
			}
			
			if(scrollMenu.getList().get(2).isButton()){
				if(tar.gold_am >= 70 && tar.PW < tar.PWMAX){
					tar.PW = tar.PWMAX;
					tar.gold_am -= 70;
					sound[2].play();
					buff=true;
					clicked=false;
				}
			}
		}
	}
	
	public void LOAD() throws SlickException{
		int frameIt = 500;
		anim = new Animation(
			new Image[]{
				new Image("Resource/npc/npc03_0001"),
				new Image("Resource/npc/npc03_0002"),
			},frameIt
		);
	}
}

class NPC_04 extends NPC{
	public NPC_04(int mapIndex,int x){
		this.name = "ทหารยาม";
		this.mapIndex = mapIndex;
		this.x = -1024+1024*mapIndex+x;
		frameU = (int)Math.random()*500+300;
		frameN = (int)Math.random()*500+300;
		dialogCenterX = (1024-dialog[0].getWidth())/2;
		buttomCenterX = dialogCenterX+dialog[0].getWidth()-button[0].getWidth()-11;
		
		this.scrollListItem.setRect(dialogCenterX+12,200,376,359);
		scrollListItem.subject("กองทัพ ทหารผู้ซื่อสัตย์");
		scrollListItem.addList(FileList.iconDir+"icSol01.png","พลดาบ ผู้ซื่อสัตย์",150,"Atk 7 - 15 , Hp 120");
		scrollListItem.addList(FileList.iconDir+"icSol05.png","พลขวาน ผู้ซื่อสัตย์",200,"Atk 25 - 35 , Hp 250");
		scrollListItem.addList(FileList.iconDir+"icSol03.png","ยอดฝีมือ ผู้ซื่อสัตย์",800,"Atk 27 - 45 , Hp 300");
	}
	
	protected void onData(){
		if(click){
			g.drawAnimation(msger,_x,y-50);
		}
		
		if(clicked && !tar.getDie()){
			g.drawImage(dialog[0],dialogCenterX,150);

			if(Button(button[0],buttomCenterX,137+button[0].getHeight())){
				clicked = false;
			}
			
			try{
			
				scrollListItem.render(container,m);
				
				if(scrollListItem.getList().get(0).isButton()){
					if(this.tar.gold_am >= scrollListItem.getList().get(0).getPrice()){
						if(this.tar.INCONTROL < this.tar.MAXINCONTROL){
							this.tar.mSolider.add(new MY_SOLIDER_SWORD());
							this.tar.mSolider.get(this.tar.mSolider.size()-1).setMap(3,100+(50*this.tar.mSolider.size()-1));
							this.tar.mSolider.get(this.tar.mSolider.size()-1).Load();
							this.tar.gold_am -= scrollListItem.getList().get(0).getPrice();
							sound[3].play();
						}
					}
				}
				
				if(scrollListItem.getList().get(1).isButton()){
					if(this.tar.gold_am >= scrollListItem.getList().get(1).getPrice()){
						if(this.tar.INCONTROL < this.tar.MAXINCONTROL){
							this.tar.mSolider.add(new MY_SOLIDER_AXE());
							this.tar.mSolider.get(this.tar.mSolider.size()-1).setMap(3,100+(50*this.tar.mSolider.size()-1));
							this.tar.mSolider.get(this.tar.mSolider.size()-1).Load();
							this.tar.gold_am -= scrollListItem.getList().get(1).getPrice();
							sound[3].play();
						}
					}
				}
				
				if(scrollListItem.getList().get(2).isButton()){
					if(this.tar.gold_am >= scrollListItem.getList().get(2).getPrice()){
						if(this.tar.INCONTROL < this.tar.MAXINCONTROL){
							this.tar.mSolider.add(new MY_SOLIDER_TWOHANDSWORD());
							this.tar.mSolider.get(this.tar.mSolider.size()-1).setMap(3,100+(50*this.tar.mSolider.size()-1));
							this.tar.mSolider.get(this.tar.mSolider.size()-1).Load();
							this.tar.gold_am -= scrollListItem.getList().get(2).getPrice();
							sound[3].play();
						}
					}
				}
				
			}catch(SlickException e){
			}
		}
	}
	
	public void LOAD() throws SlickException{
		int frameIt = 500;
		anim = new Animation(
			new Image[]{
				new Image("Resource/npc/npc04_0001"),
				new Image("Resource/npc/npc04_0002"),
			},frameIt
		);
	}
}

class NPC_05 extends NPC{
	private ArrayList<SOLIDER> solider;
	private DataNew lvMenu;
	private Arjong app;
	
	public NPC_05(int mapIndex,int x){
		this.name = "นนท์";
		this.mapIndex = mapIndex;
		this.x = -1024+1024*mapIndex+x;
		frameU = (int)Math.random()*500+300;
		frameN = (int)Math.random()*500+300;
		dialogCenterX = (1024-dialog[0].getWidth())/2;
		buttomCenterX = dialogCenterX+dialog[0].getWidth()-button[0].getWidth()-11;
		
		this.scrollListItem.setRect(dialogCenterX+12,200,376,359);
		
		scrollListItem.subject("บริจาคให้กองทัพ");
		scrollListItem.addList(FileList.iconDir+"icCla01.png","บริจาค อาวุธ",50,100,"เพิ่มความสมารถในการใช้อาวุธ +5");
		scrollListItem.addList(FileList.iconDir+"icCla02.png","บริจาค เกราะ",50,100,"เพิ่มประสิทธิภาพของชุดเกราะให้ทหาร +2");
		scrollListItem.addList(FileList.iconDir+"icCla03.png","บริจาค เพิ่มกำลังพล",250,500,"เพิ่มกำลังพล 2");
		
		this.lvMenu = new DataNew("กำลังพล",0,5);
	}
	
	protected void onData(){	
		if(click){
			g.drawAnimation(msger,_x,y-50);
		}
		
		if(clicked && !tar.getDie()){
			g.drawImage(dialog[0],dialogCenterX,150);
			if(Button(button[0],buttomCenterX,137+button[0].getHeight())){
				clicked = false;
			}
				
			try{
				scrollListItem.render(container,m);
				
				if(scrollListItem.getList().get(0).isButton()){
					if(tar.gold_am >= scrollListItem.getList().get(0).getPrice()){
						if(tar.plant_am >= scrollListItem.getList().get(0).getPPrice()){
							for(SOLIDER s : solider){
								if(s != null){
									s.atk+=5;
								}
							}
							sound[2].play();
							tar.gold_am -= scrollListItem.getList().get(0).getPrice();
							tar.plant_am -= scrollListItem.getList().get(0).getPPrice();
						}
					}
				}
				
				if(scrollListItem.getList().get(1).isButton()){
					if(tar.gold_am >= scrollListItem.getList().get(1).getPrice()){
						if(tar.plant_am >= scrollListItem.getList().get(1).getPPrice()){
							for(SOLIDER s : solider){
								if(s != null){
									s.def+=2;
								}
							}
							sound[2].play();
							tar.gold_am -= scrollListItem.getList().get(1).getPrice();
							tar.plant_am -= scrollListItem.getList().get(1).getPPrice();
						}
					}
				}
				
				if(scrollListItem.getList().get(2).isButton()){
					if(tar.gold_am >= scrollListItem.getList().get(2).getPrice()){
						if(tar.plant_am >= scrollListItem.getList().get(2).getPPrice()){
							if(lvMenu.min < lvMenu.max){
								sound[2].play();
								tar.gold_am -= scrollListItem.getList().get(2).getPrice();
								app.WMAX+=2;
								lvMenu.min++;
							}
						}
					}
				}
				
			}catch(SlickException e){
			}
		}
	}
	
	public void setTarget(ArrayList<SOLIDER> solider,Arjong app){
		this.solider = solider;
		this.app = app;
	}
	
	public void LOAD() throws SlickException{
		int frameIt = 500;
		anim = new Animation(
			new Image[]{
				new Image("Resource/npc/npc05_0001"),
				new Image("Resource/npc/npc05_0002"),
			},frameIt
		);
	}
}

class NPC_06 extends NPC{
	private ArrayList<SOLIDER> solider;
	
	public NPC_06(int mapIndex,int x){
		this.name = "หัวหน้าทหาร";
		this.mapIndex = mapIndex;
		this.x = -1024+1024*mapIndex+x;
		frameU = (int)Math.random()*500+300;
		frameN = (int)Math.random()*500+300;
		
		dialogCenterX = (1024-dialog[0].getWidth())/2;
		buttomCenterX = dialogCenterX+dialog[0].getWidth()-button[0].getWidth()-11;
		
		this.scrollListItem.setRect(dialogCenterX+12,200,376,359);
		
		scrollListItem.subject("กองทัพ");
		scrollListItem.addList(FileList.iconDir+"icSol01.png","พลทหาร",100,"Atk 7 - 15 , Hp 120");
		scrollListItem.addList(FileList.iconDir+"icSol02.png","พลธนู",80,"Atk 13 - 20 , Hp 70");
		scrollListItem.addList(FileList.iconDir+"icSol05.png","พลขวาน",170,"Atk 25 - 35 , Hp 250");
		scrollListItem.addList(FileList.iconDir+"icSol03.png","ยอดฝีมือ",500,"Atk 35 - 55 , Hp 450");
		scrollListItem.addList(FileList.iconDir+"icSol04.png","ยอดฝีมือ",1000,"Atk 55 - 70 , Hp 700");
	}
	
	protected void onData(){
		if(click){
			g.drawAnimation(msger,_x,y-50);
		}
		
		if(clicked && !tar.getDie()){
			g.drawImage(dialog[0],dialogCenterX,150);

			if(Button(button[0],buttomCenterX,137+button[0].getHeight())){
				clicked = false;
			}
			
			try{
				scrollListItem.render(container,m);
				
				if(scrollListItem.getList().get(0).isButton()){
					if(this.tar.gold_am >= scrollListItem.getList().get(0).getPrice()){
						if(this.solider.size() < Arjong.WMAX){
							this.solider.add(new SOLIDER_SWORD());
							this.solider.get(this.solider.size()-1).setMap(2,100+(50*this.solider.size()-1));
							this.solider.get(this.solider.size()-1).Load();
							this.sound[3].play();
							this.tar.gold_am-=scrollListItem.getList().get(0).getPrice();
						}
					}
				}
				
				if(scrollListItem.getList().get(1).isButton()){
					if(this.tar.gold_am >= scrollListItem.getList().get(1).getPrice()){
						if(this.solider.size() < Arjong.WMAX){
							this.solider.add(new SOLIDER_ARCHER());
							this.solider.get(this.solider.size()-1).setMap(2,100+(50*this.solider.size()-1));
							this.solider.get(this.solider.size()-1).Load();
							this.sound[3].play();
							this.tar.gold_am-=scrollListItem.getList().get(1).getPrice();
						}
					}
				}
				
				if(scrollListItem.getList().get(2).isButton()){
					if(this.tar.gold_am >= scrollListItem.getList().get(2).getPrice()){
						if(this.solider.size() < Arjong.WMAX){
							this.solider.add(new SOLIDER_AXE());
							this.solider.get(this.solider.size()-1).setMap(2,100+(50*this.solider.size()-1));
							this.solider.get(this.solider.size()-1).Load();
							this.sound[3].play();
							this.tar.gold_am-=scrollListItem.getList().get(2).getPrice();
						}
					}
				}
				
				if(scrollListItem.getList().get(3).isButton()){
					if(this.tar.gold_am >= scrollListItem.getList().get(3).getPrice()){
						if(this.solider.size() < Arjong.WMAX){
							this.solider.add(new SOLIDER_TWOHANDSWORD());
							this.solider.get(this.solider.size()-1).setMap(2,100+(50*this.solider.size()-1));
							this.solider.get(this.solider.size()-1).Load();
							this.sound[3].play();
							this.tar.gold_am-=scrollListItem.getList().get(3).getPrice();
						}
					}
				}
				
				if(scrollListItem.getList().get(4).isButton()){
					if(this.tar.gold_am >= scrollListItem.getList().get(4).getPrice()){
						if(this.solider.size() < Arjong.WMAX){
							this.solider.add(new SOLIDER_HERO());
							this.solider.get(this.solider.size()-1).setMap(2,100+(50*this.solider.size()-1));
							this.solider.get(this.solider.size()-1).Load();
							this.sound[3].play();
							this.tar.gold_am-=scrollListItem.getList().get(4).getPrice();
						}
					}
				}
				
			}catch(SlickException e){
			}
		}
	}
	
	public void setTarget(ArrayList<SOLIDER> solider){
		this.solider = solider;
	}
	
	public void LOAD() throws SlickException{
		int frameIt = 500;
		anim = new Animation(
			new Image[]{
				new Image("Resource/npc/npc06_0001"),
				new Image("Resource/npc/npc06_0002"),
			},frameIt
		);
	}
}

class NPC_07 extends NPC{
	private ArrayList<SOLIDER> solider;
	
	public NPC_07(int mapIndex,int x){
		this.name = "นิ่มนวล";
		this.mapIndex = mapIndex;
		this.x = -1024+1024*mapIndex+x;
		frameU = (int)Math.random()*500+300;
		frameN = (int)Math.random()*500+300;
		
		dialogCenterX = (1024-dialog[0].getWidth())/2;
		buttomCenterX = dialogCenterX+dialog[0].getWidth()-button[0].getWidth()-11;
		
		this.scrollListItem.setRect(dialogCenterX+12,200,376,359);
		
		scrollListItem.subject("รายการ");
		scrollListItem.addList(FileList.iconDir+"icItem06.png","รับซื้อผัก",0,10,"อัตราการแลกเปลี่ยน 1.25%");
		scrollListItem.addList(FileList.iconDir+"icItem06.png","รับซื้อผัก",0,50,"อัตราการแลกเปลี่ยน 1.25%");
	}
	
	protected void onData(){
		if(click){
			g.drawAnimation(msger,_x,y-50);
		}
		
		if(clicked && !tar.getDie()){
			g.drawImage(dialog[0],dialogCenterX,150);

			if(Button(button[0],buttomCenterX,137+button[0].getHeight())){
				clicked = false;
			}
			
			try{
				scrollListItem.render(container,m);	
				
				if(scrollListItem.getList().get(0).isButton()){
					if(tar.plant_am >= scrollListItem.getList().get(0).getPPrice()){
						tar.gold_am += (int)(scrollListItem.getList().get(0).getPPrice()*2);
						tar.plant_am -= scrollListItem.getList().get(0).getPPrice();
						this.sound[3].play();
					}
				}
				if(scrollListItem.getList().get(1).isButton()){
					if(tar.plant_am >= scrollListItem.getList().get(1).getPPrice()){
						tar.gold_am += (int)(scrollListItem.getList().get(1).getPPrice()*3);
						tar.plant_am -= scrollListItem.getList().get(1).getPPrice();
						this.sound[3].play();
					}
				}
				
			}catch(SlickException e){
			}
		}
	}
	
	public void LOAD() throws SlickException{
		int frameIt = 500;
		anim = new Animation(
			new Image[]{
				new Image("Resource/npc/npc07_0001"),
				new Image("Resource/npc/npc07_0002"),
			},frameIt
		);
	}
}

class NPC_08 extends NPC{
	private Arjong app;
	
	public NPC_08(int mapIndex,int x){
		this.name = "แปลงผัก";
		this.mapIndex = mapIndex;
		this.x = -1024+1024*mapIndex+x;
		frameU = (int)Math.random()*500+300;
		frameN = (int)Math.random()*500+300;
	}
	
	protected void onData(){
		if(click){
			g.drawAnimation(msger,_x,y-50);
		}
		
		if(clicked && !tar.getDie()){
			if(tar.TIMER.endWar){
				this.app.getMusic(1).stop();
				this.app.getSound(2).stop();
				this.app.getMusic(4).play();
				this.app.getMusic(4).loop();
				this.app.goToFarm();
			}
			clicked=false;
		}
	}
	
	public void setTar(Arjong app){
		this.app = app;
	}
	
	public void LOAD() throws SlickException{
		int frameIt = 500;
		anim = new Animation(
			new Image[]{
				new Image(0,0),
			},frameIt
		);
	}
}

abstract class NPC_SOLIDER implements Serializable{
	protected enum State{stop,atk};
	protected State state = State.stop;
	protected Animation stoping,atking,emotion;
	protected Image shardow,dialog,button;
	protected int atk,x,y,mapX,_x,mapIndex,delayAtk,delayAtkCount = 250,range;
	protected Font font;
	protected Graphics g = new Graphics();
	protected String name,msg[];
	protected Rectangle m;
	protected GameContainer container;
	protected boolean talk,click,clicked;
	protected Sound sound[] = new Sound[3];
	protected int frameU = 0,frameN = 0,rnd;
	protected char modeIn;
	protected ScrollListItem scrollListItem;
	protected ScrollTxt scrollText;
	protected int dialogCenterX,buttomCenterX;
	protected HOME[] h;
	
	public NPC_SOLIDER(){
		try{
			dialog = new Image(FileList.imgeDir+"interface_menu2");
			button = new Image(FileList.imgeDir+"bClose.PNG");
			
			dialogCenterX = (1024-dialog.getWidth())/2;
			buttomCenterX = dialogCenterX+dialog.getWidth()-button.getWidth()-11;
			
			this.scrollListItem = new ScrollListItem();
			this.scrollText = new ScrollTxt();
			
			this.delayAtk = delayAtkCount;
			String emotionDir = "Resource/emotion/";
			emotion = new Animation(
				new Image[]{
					new Image(emotionDir+"em01_01.png"),
					new Image(emotionDir+"em01_02.png"),
					new Image(emotionDir+"em01_03.png"),
					new Image(emotionDir+"em01_04.png"),
				},200
			);
			
			sound = new Sound[]{
				new Sound(FileList.bgsDir+"bgs02.wav"),
				new Sound(FileList.bgsDir+"bgs03.wav"),
				new Sound(FileList.bgsDir+"Recovery.wav"),
				new Sound(FileList.bgsDir+"castsound.wav"),
			};
			shardow = new Image("Resource/img/shardowA");
			font = new AngelCodeFont("Resource/font/simpleFont.fnt","Resource/font/simpleFont.png");
		}catch(SlickException e){
		}
	}
	
	protected void rndm(){
		if(rnd > this.msg.length){
			rndm();
		}else{
			rnd = (int)(Math.random()*this.msg.length-1);
		}
	}
	
	public void LOADMESSAGE(String msg){
		this.msg = msg.split(",");
		this.rndm();
	}
	
	public void onClick(){
		Input input = container.getInput();
		
		if(m.intersects(BOUNDINGBOX())){
			click = true;
			if(input.isMousePressed(input.MOUSE_LEFT_BUTTON)){
				sound[1].play();
				clicked = !clicked;
				return;
			}
		}else{
			click = false;
		}
	}
	
	public void setContainer(GameContainer container){
		this.container = container;
	}
	
	public void setCursorSelect(Rectangle m){
		this.m = m;
	}
	
	protected Rectangle BOUNDINGBOX(){
		Rectangle r = new Rectangle(_x,y,40,65);
		return(r);
	}
	
	protected void drawString(String msg,int x,int y,Color c){
		Graphics g = new Graphics();
		g.setFont(font);
		
		g.setColor(new Color(0,0,0,200));
		g.drawString(msg,x+1,y+1);
		g.setColor(c);
		g.drawString(msg,x,y);
	}
	
	public void drawSelf(){
		this._x = x+this.mapX;
		if((this._x > 0) && (this._x < 1024))
		g.drawImage(shardow,_x,y+70);
		switch(state){
			case stop:{
				this.modeIn = '>';
				if((this._x > 0) && (this._x < 1024))
					g.drawAnimation(this.stoping,_x,y);
				state = State.stop;
				break;
			}
			case atk:{
				this.modeIn = '>';
				if((this._x > 0) && (this._x < 1024))
					g.drawAnimation(this.atking,_x,y);
				state = State.stop;
				break;
			}
		}
		if((this._x < 1024) && (this._x > 0))
		drawString(this.name,this._x+(55/font.getWidth(name))/2,this.y-15,Color.blue);
	}
	
	public void Update(int mapX,ScrollTxt scrollT){
		this.mapX = mapX;
		String txt = this.msg[this.rnd];
		
		if(frameN > 0){
			frameN--;
		}else{
			if(frameU > 0){
				if(!talk){
					if((this._x < 1024) && (this._x > 0)){
						scrollT.addString(this.name+" : "+txt);
						scrollT.nextLine();
					}
					talk=true;
				}
				int widthser = 20+font.getWidth(txt);
				int msgX = _x+(55-widthser)/2;
				g.setColor(new Color(0,0,0,150));
				g.fillRoundRect(msgX,y-50,widthser,20+font.getHeight(txt),5);
				g.setColor(new Color(255,255,255));
				this.drawString(txt,msgX+(widthser-font.getWidth(txt))/2,y-50+font.getHeight(txt)/2,Color.white);
				frameU--;
			}else{
				talk=false;
				frameN = (int)Math.random()*500+300;
				frameU = (int)Math.random()*500+200;
				this.rndm();
			}
		}
	}
	
	protected boolean Button(Image img,int x,int y){
		Input input = container.getInput();
		Rectangle button = new Rectangle(x,y,img.getWidth(),img.getHeight());
		
		if(m.intersects(button)){
			if(input.isMousePressed(input.MOUSE_LEFT_BUTTON)){
				sound[0].play();
				return(true);
			}
			g.drawImage(img,x,y,new Color(255,255,255));
		}
		g.drawImage(img,x,y,new Color(150,150,150));
		
		return(false);
	}
	
	public abstract void Load() throws SlickException;
	public abstract void UpdateSelf(ArrayList<ENEMY> tar,CHARACTER hero);
	public abstract void UpdateSelf(HOME[] h,CHARACTER hero);
}

class NPC_archer extends NPC_SOLIDER{
	public ArrayList<TARROW> arrow = new ArrayList<TARROW>();
	protected AWARD award;
	protected DataNew[] lvMenu = new DataNew[4];
	
	public NPC_archer(int mapIndex,int x,int y){
		this.name = "ทหารธนู";
		this.mapIndex = mapIndex;
		this.x = -1024+1024*mapIndex+x;
		this.y = y;
		this.atk = 10;
		this.modeIn = '>';
		this.range = 350;
		this.award = new AWARD(mapIndex,x+50,y+40);
		this.scrollListItem.setRect(dialogCenterX+12,385,376,170);
		this.scrollText.setRect(dialogCenterX+12,200,385,170);
		this.scrollText.subject("คำอธิบาย");
		
		this.lvMenu[0] = new DataNew("ป้องกัน",0,15);
		this.lvMenu[1] = new DataNew("โจมตี",0,15);
		this.lvMenu[2] = new DataNew("ความเร็ว",0,15);
		this.lvMenu[3] = new DataNew("ความแข็งแรง",0,15);
		
		this.scrollListItem.addList(FileList.iconDir+"icMenu01.png","ป้องกัน ",300,300,"บริจาค เพิ่มความแข็งแรงให้กับรั้วเมือง +1");
		this.scrollListItem.addList(FileList.iconDir+"icMenu02.png","โจมตี ",200,200,"บริจาค เพิ่มความแรงของธนูของข้า +5");
		this.scrollListItem.addList(FileList.iconDir+"icMenu03.png","ความเร็ว ",500,100,"บริจาค เพิ่มความเร็วในการโจมตี 10 หน่วย ห้ามบริจาคเกิน 15 ครั้ง");
		this.scrollListItem.addList(FileList.iconDir+"icMenu04.png","ความแข็งแรง ",200,100,"บริจาค เพิ่มความแข็งแรง hp + 100");
	}
	
	public void UpdateSelf(ArrayList<ENEMY> tar,CHARACTER hero){
		if(click){
			g.drawAnimation(emotion,_x,y-50);
		}
		
		if(clicked && !hero.getDie()){
			g.drawImage(dialog,dialogCenterX,150);

			if(Button(button,buttomCenterX,137+button.getHeight())){
				clicked = false;
			}
			
			for(HOME H : h){
				if(H != null)
				if(H.getName().equals("ประตูเมือง")){
					this.scrollText.addString("พลังชีวิต : "+H.hp+" / "+H.hpMax);
					this.scrollText.addString("พลังป้องกัน : "+H.def);
					break;
				}
			}
			
			this.scrollText.addString("พลังโจมตี : "+this.atk);
			this.scrollText.addString("ความเร็วในการโจมตี : "+this.delayAtkCount);
			this.scrollText.addString("บริจาค ป้องกัน : "+"[ "+lvMenu[0].min+"/"+lvMenu[0].max+" ]"+" ครั้ง");
			this.scrollText.addString("บริจาค โจมตี : "+"[ "+lvMenu[1].min+"/"+lvMenu[1].max+" ]"+" ครั้ง");
			this.scrollText.addString("บริจาค ความเร็ว : "+"[ "+lvMenu[2].min+"/"+lvMenu[2].max+" ]"+" ครั้ง");
			this.scrollText.addString("บริจาค ความแข็งแรง :  "+"[ "+lvMenu[3].min+"/"+lvMenu[3].max+" ]"+" ครั้ง");
			
			try{
				scrollListItem.render(container,m);
				scrollText.render(container,m);
			}catch(SlickException e){}
			this.scrollText.msgclr();
			
			if(this.scrollListItem.getList().get(0).isButton()){
				if(hero.gold_am >= this.scrollListItem.getList().get(0).getPrice() && this.lvMenu[0].min < this.lvMenu[0].max){
					if(hero.plant_am >= this.scrollListItem.getList().get(0).getPPrice()){
						for(HOME H : h){
							if(H != null)
							if(H.getName().equals("ประตูเมือง")){
								H.def++;
								hero.gold_am-=this.scrollListItem.getList().get(0).getPrice();
								hero.plant_am-=this.scrollListItem.getList().get(0).getPPrice();
								sound[3].play();
								this.lvMenu[0].min++;
								break;
							}
						}
					}
				}
			}
			
			if(this.scrollListItem.getList().get(1).isButton()){
				if(hero.gold_am >= this.scrollListItem.getList().get(1).getPrice() && this.lvMenu[1].min < this.lvMenu[1].max){
					if(hero.plant_am >= this.scrollListItem.getList().get(1).getPPrice()){
						this.atk+=5;
						sound[3].play();
						this.lvMenu[1].min++;
						
						hero.plant_am-=this.scrollListItem.getList().get(1).getPPrice();
						hero.gold_am-=this.scrollListItem.getList().get(1).getPrice();
					}
				}
			}

			if(this.scrollListItem.getList().get(2).isButton()){
				if(hero.gold_am >= this.scrollListItem.getList().get(2).getPrice() && this.lvMenu[2].min < this.lvMenu[2].max){
					if(hero.plant_am >= this.scrollListItem.getList().get(2).getPPrice()){
						this.delayAtkCount-=10;
						sound[3].play();
						this.lvMenu[2].min++;
						
						hero.plant_am-=this.scrollListItem.getList().get(2).getPPrice();
						hero.gold_am-=this.scrollListItem.getList().get(2).getPrice();
					}
				}
			}
			
			if(this.scrollListItem.getList().get(3).isButton()){
				if(hero.gold_am >= this.scrollListItem.getList().get(3).getPrice() && this.lvMenu[3].min < this.lvMenu[3].max){
					if(hero.plant_am >= this.scrollListItem.getList().get(3).getPPrice()){
						for(HOME H : h){
							if(H != null)
							if(H.getName().equals("ประตูเมือง")){
								H.hpMax+=100;
								hero.plant_am-=this.scrollListItem.getList().get(3).getPPrice();
								hero.gold_am-=this.scrollListItem.getList().get(3).getPrice();
								sound[3].play();
								this.lvMenu[3].min++;
								break;
							}
						}
					}
				}
			}
			
			scrollListItem.subject("เมนู");
		}

		for(ENEMY t : tar){
			if(t != null){
				if(t._x <= this._x+range && !t.getDie()){
					state = State.atk;

					if(delayAtk > 0){
						delayAtk--;
					}else{
						arrow.add(new TARROW(modeIn,2.0f+(float)Math.random()*10.0f,this.atk,this._x,this.y));
						delayAtk = delayAtkCount;
					}
				}
			}
		}
		
		for(TARROW ars : arrow){
			if(ars != null && !ars.getRemove()){
				ars.Update();
				ars.drawSelf();
				ars.setTarget(tar);
			}else{
				arrow.remove(ars);
				break;
			}
		}
	}
	
	public void UpdateSelf(HOME[] h,CHARACTER hero){
		this.h = h;
		this.award.renderGround(hero.MAPINDEX);
		if(this.award.openIn(hero.TIMER.getHour() > 18 || hero.TIMER.getHour() < 6))
			this.award.render(hero.MAPINDEX);
	}
	
	public void Load() throws SlickException {
		String animDir = "Resource/npc/npc_solider/arunetara/npc01/";
		int frameStop = 500,frameAtk = 1;
		
		this.stoping = new Animation(
			new Image[]{
				new Image(animDir+"npcsR04_0001.png"),
				new Image(animDir+"npcsR04_0002.png"),
				new Image(animDir+"npcsR04_0001.png"),
			},frameStop
		);
		
		this.atking = new Animation(
			new Image[]{
				new Image(animDir+"npcaR04_0003.png"),
			},frameAtk
		);
	}
}

abstract class NPC_ENEMY implements Serializable{
	protected enum State{stop,atk};
	protected State state = State.stop;
	protected Animation stoping,atking;
	protected Image shardow,dialog,button;
	protected int atk,x,y,mapX,_x,mapIndex,delayAtk,delayAtkCount = 180,range;
	protected Font font;
	protected Graphics g = new Graphics();
	protected String name,msg[];
	protected Rectangle m;
	protected GameContainer container;
	protected boolean talk,click,clicked;
	protected Sound sound[] = new Sound[3];
	protected int frameU = 0,frameN = 0,rnd;
	protected char modeIn;
	protected HOME[] h;
	
	public NPC_ENEMY(){
		try{
			sound = new Sound[]{
				new Sound(FileList.bgsDir+"bgs02.wav"),
				new Sound(FileList.bgsDir+"bgs03.wav"),
				new Sound(FileList.bgsDir+"Recovery.wav"),
				new Sound(FileList.bgsDir+"castsound.wav"),
			};
			shardow = new Image("Resource/img/shardowA");
			font = new AngelCodeFont("Resource/font/simpleFont.fnt","Resource/font/simpleFont.png");
		}catch(SlickException e){
		}
	}
	
	protected Rectangle BOUNDINGBOX(){
		Rectangle r = new Rectangle(_x,y,40,65);
		return(r);
	}
	
	protected void drawString(String msg,int x,int y,Color c){
		Graphics g = new Graphics();
		g.setFont(font);
		
		g.setColor(new Color(0,0,0,200));
		g.drawString(msg,x+1,y+1);
		g.setColor(c);
		g.drawString(msg,x,y);
	}
	
	public void drawSelf(){
		this._x = x+this.mapX;
		if((this._x > 0) && (this._x < 1024))
		g.drawImage(shardow,_x,y+70);
		switch(state){
			case stop:{
				this.modeIn = '>';
				if((this._x > 0) && (this._x < 1024))
					g.drawAnimation(this.stoping,_x,y);
				state = State.stop;
				break;
			}
			case atk:{
				this.modeIn = '>';
				if((this._x > 0) && (this._x < 1024))
					g.drawAnimation(this.atking,_x,y);
				state = State.stop;
				break;
			}
		}
		if((this._x < 1024) && (this._x > 0))
		drawString(this.name,this._x+(55/font.getWidth(name))/2,this.y-15,Color.red);
	}
	
	public void Update(int mapX){
		this.mapX = mapX;
	}
	
	public abstract void Load() throws SlickException;
	public abstract void UpdateSelf(ArrayList<SOLIDER> tar,CHARACTER hero,ArrayList<MYSOLIDER> ms);
	public abstract void Updateing();
}

class NPC_comArcher extends NPC_ENEMY{
	public ArrayList<TARROW> arrow = new ArrayList<TARROW>();
	protected AWARD award;
	private ArrayList<SOLIDER> tar;
	private CHARACTER hero;
	
	public NPC_comArcher(int mapIndex,int x,int y){
		this.name = "ศตรู";
		this.mapIndex = mapIndex;
		this.x = -1024+1024*mapIndex+x;
		this.y = y;
		this.atk = 10;
		this.modeIn = '<';
		this.range = 450;
		this.award = new AWARD(mapIndex,x-10,y+40);
		this.delayAtk = this.delayAtkCount;
	}
	
	public void UpdateSelf(ArrayList<SOLIDER> tar,CHARACTER hero,ArrayList<MYSOLIDER> ms){
		this.award.renderGround(hero.MAPINDEX);
		if(this.award.openIn(hero.TIMER.getHour() > 18 || hero.TIMER.getHour() < 6))
			this.award.render(hero.MAPINDEX);
		
		this.tar = tar;
		this.hero = hero;
		
		for(SOLIDER sol : tar){
			if(!sol.getDie()){
				if(sol._x >= this._x-range){
					if(delayAtk > 0)
						delayAtk--;
					else{
						modeIn = '<';
						arrow.add(new TARROW(this.modeIn,(float)(2+Math.random()*10),this.atk,this._x,this.y));
						delayAtk = delayAtkCount;
						return;
					}
					return;
				}
			}
		}
		
		if(!hero.getDie()){
			if(hero._x >= this._x-range){
				state = State.atk;
				if(delayAtk > 0)
					delayAtk--;
				else{
					modeIn = '<';
					arrow.add(new TARROW(this.modeIn,(float)(2+Math.random()*10),this.atk,this._x,this.y));
					delayAtk = delayAtkCount;
				}
				return;
			}
		}
	}
	
	public void Updateing(){
		for(TARROW ars : arrow){
			if(ars != null && !ars.getRemove()){
				ars.Update();
				ars.drawSelf();
				ars.setTarget(hero,tar);
			}else{
				arrow.remove(ars);
				break;
			}
		}
	}
	
	public void Load() throws SlickException {
		String animDir = "Resource/npc/npc_solider/seeveta/npc01/";
		int frameStop = 500,frameAtk = 1;
		
		this.stoping = new Animation(
			new Image[]{
				new Image(animDir+"En3_sl_0001.png"),
				new Image(animDir+"En3_sl_0002.png"),
				new Image(animDir+"En3_sl_0001.png"),
			},frameStop
		);
		
		this.atking = new Animation(
			new Image[]{
				new Image(animDir+"En1_al_.png"),
			},frameAtk
		);
	}
}

abstract class HOME implements Serializable{
	public int x,y,_x,mapX,hp,hpMax,def,money;
	protected String name;
	protected Image img,img2;
	protected int mapIndex,posX,posY;
	protected ParticleSystem pr[] = new ParticleSystem[2];
	protected Font font;
	protected int width = 100,height = 200;
	protected Color myColor;
	protected ArrayList<DMGTEXT> dmgTxt = new ArrayList<DMGTEXT>();
	protected Font[] fonts = new Font[4];
	public ArrayList<FireDmg> fd = new ArrayList<FireDmg>();
	protected Color color;
	protected int frameEf;
	public boolean fireHit = false;
	
	public HOME(){
		try{
			myColor = Color.green;
			font = new AngelCodeFont("Resource/font/simpleFont.fnt","Resource/font/simpleFont.png");
			fonts[0] = new AngelCodeFont("Resource/font/simpleFont.fnt","Resource/font/simpleFont.png");
			fonts[1] = new AngelCodeFont("Resource/font/f1.fnt","Resource/font/f1.png");
			fonts[2] = new AngelCodeFont("Resource/font/f2.fnt","Resource/font/f2.png");
			fonts[3] = new AngelCodeFont("Resource/font/f3.fnt","Resource/font/f3.png");
		}catch(Exception e){
		}
	}
	
	public int CenterOf(char c){
		if(c == 'x' || c == 'X') return(_x+this.img.getWidth()/2-5);
		if(c == 'y' || c == 'Y') return(y+this.img.getHeight()/2-5);
		return(0);
	}
	
	public void setMoney(int money){
		this.money = money;
	}
	
	public boolean die(){
		if(hp > 0)
			return(false);
		else
			return(true);
	}

	protected void drawString(String msg,int x,int y,Color c){
		Graphics g = new Graphics();
		g.setFont(font);
		g.setColor(new Color(0,0,0,200));
		g.drawString(msg,x+1,y+1);
		g.setColor(c);
		g.drawString(msg,x,y);
	}
	
	public String getName(){
		return(this.name);
	}
	
	public void render(){
		Graphics g = new Graphics();
		this._x = x+this.mapX;
		
		g.drawImage(this.img,this._x,this.y);
		if(img2 != null) g.drawImage(this.img2,this._x-posX,this.y-posY);
	}
	
	public void render2(){
		this.drawBar(hp,hpMax,_x+8,y-100,80,5,Color.red);
		this.drawString(this.name,_x+30,y-30,myColor);
	}
	
	public Rectangle BOUNDINGBOX(){
		return(new Rectangle(_x,y,width,height));
	}
	
	public void Update(int mapX){
		this.mapX = mapX;
	}
	
	public void drawBar(int min,int max,int x,int y,int w,int h,Color color){
		Graphics g = new Graphics();
		
		if(min < 0)min=0;
		if(min > max)min=max;
		
		g.setColor(new Color(0,0,0,150));
		g.fillRect(x+2,y+95,w+2,h);
		g.setColor(color);
		g.fillRect(x+3,y+95+1,(int)(w*((double)min/max)),h-2);
		g.setColor(new Color(255,255,255));
	}
	
	public void drawDmgFont2(String msg,int x,int y){
		dmgTxt.add(new DMGTEXT(this.fonts[2],msg,x,y));
	}
	
	public void renderEffect(){
		int update = 11;
		
		pr[0].update((int)update);
		pr[0].render(_x+50,y+60);
	}
	
	public void Updating(){
		for(FireDmg f : fd){
			if(f != null){
				if(!f.getDie()){
					f.update(this.CenterOf('x'),this.CenterOf('y'),this);
				}else{
					fd.clear();
				}
			}
			break;
		}
	
		for(DMGTEXT dmgTEXT : dmgTxt){
			if(!dmgTEXT.getRemove()){
				dmgTEXT.UpdateAndRender();
			}else{
				dmgTxt.remove(dmgTEXT);
				break;
			}
		}
	}
	
	public void setColor(Color color){
		this.color = color;
		frameEf = 20;
	}
}

class siriGuild extends HOME implements Serializable{
	public siriGuild(int mapID,int x,int y){
		this.mapIndex = mapID;
		this.x = -1024+1024*mapIndex+x;
		this.y = y;
		this.name = "หลักศิลา";
		hpMax = 3000;
		hp = hpMax;
		def = 5;
		
		try{
			this.img = new Image(FileList.objDir+"Sira.png");
			
			pr[0] = ParticleIO.loadConfiguredSystem("Resource/effect/ef005.xml");
		}catch(IOException er){
		}catch(SlickException e){
		}
	}
}

class doorTown extends HOME implements Serializable{
	public doorTown(int mapID,int x,int y){
		this.mapIndex = mapID;
		this.x = -1024+1024*mapIndex+x;
		this.y = y;
		this.name = "ประตูเมือง";
		this.posX = 150;
		this.posY = 50;
		hpMax = 2000;
		hp = hpMax;
		def = 5;
		
		try{
			this.img = new Image(FileList.objDir+"doorx.png");
			this.img2 = new Image(FileList.objDir+"Fort_01.png");
			pr[0] = ParticleIO.loadConfiguredSystem("Resource/effect/ef005.xml");
		}catch(IOException er){
		}catch(SlickException e){
		}
	}
}

class doorTownOfENemy extends HOME implements Serializable{
	public doorTownOfENemy(int mapID,int x,int y){
		this.mapIndex = mapID;
		this.x = -1024+1024*mapIndex+x;
		this.y = y;
		this.name = "ประตูเมือง";
		hpMax = 5000;
		hp = hpMax;
		def = 20;
		this.myColor = Color.red;
		this.posX = -20;
		this.posY = 110;
		
		try{
			this.img = new Image(FileList.objDir+"doorx.png");
			this.img2 = new Image(FileList.objDir+"Fort_02.png");
			pr[0] = ParticleIO.loadConfiguredSystem("Resource/effect/ef005.xml");
		}catch(IOException er){
		}catch(SlickException e){
		}
	}
}

class Heart extends HOME implements Serializable{
	public Heart(int mapID,int x,int y){
		this.mapIndex = mapID;
		this.x = -1024+1024*mapIndex+x;
		this.y = y;
		this.name = "ธงศัตรู";
		hpMax = 10000;
		hp = hpMax;
		def = 40;
		this.myColor = Color.red;
		
		try{
			this.img = new Image(FileList.objDir+"Siras.png");
			
			pr[0] = ParticleIO.loadConfiguredSystem("Resource/effect/ef005.xml");
		}catch(IOException er){
		}catch(SlickException e){
		}
	}
}

class ScrollBox implements Serializable{
	protected Graphics g;
	protected int x,y,width,height;
	protected int defaultX,defaultY,defaultWidth,defaultHeight,scrHeight;
	protected float scX,scY;
	protected String msg = "",sub = "";
	protected boolean clearScreen = true;
	protected Font font,font2;
	protected Image button[] = new Image[2];
	private float txtY,fixVals,d;
	
	public void clr(){
		clearScreen = true;
	}
	
	public void clrSubject(){
		sub=null;
	}
	
	public void msgclr(){
		msg = "";
	}
	
	public ScrollBox(){	
		try{
			font = new AngelCodeFont(FileList.fontDir+"simpleFont.fnt",FileList.fontDir+"simpleFont.png");
		}catch(SlickException e){
		}
	}
	
	public void nextLine(){
		txtY = (this.scY+scrHeight);
		fixVals = (defaultY+defaultHeight)-20;
		d = (float)scrHeight-fixVals+3;
		
		if(txtY > fixVals){
			this.scY = -d;
		}
	}
	
	public void setRect(int x,int y,int width,int height){
		if(this.clearScreen){
			try{
				font =  new AngelCodeFont(FileList.fontDir+"simpleFont.fnt",FileList.fontDir+"simpleFont.png");
				font2 = new AngelCodeFont(FileList.fontDir+"font_01.fnt",FileList.fontDir+"font_01.png");
				button = new Image[]{
					new Image("Resource/img/bUp"),
					new Image("Resource/img/bDown")
				};
			}catch(SlickException e){
			}
			this.defaultX = x;
			this.defaultY = y;
			this.defaultWidth = width;
			this.defaultHeight = height;
			this.x = defaultX;
			this.y = defaultY;
			this.width = defaultWidth;
			this.height = defaultHeight;
			this.scX = x;
			this.scY = y;
			this.g = new Graphics(width,height);
			this.g.setFont(font);
			this.clearScreen=false;
		}
	}
	
	public boolean subject(String txt){
		this.clrSubject();
		try{
			sub = txt;
		}catch(NullPointerException e){
		}
		return(true);
	}
	
	public void addString(String txt){
		msg += txt+"\n";
		scrHeight = g.getFont().getHeight(msg);
	}
	
	protected void drawString(String txt,int x,int y,Color c){
		g.setColor(new Color(0,0,0,150));
		g.drawString(txt,x+1,y+1);
		g.setColor(c);
		g.drawString(txt,x,y);
		g.setColor(new Color(255,255,255,255));
	}
}

class ScrollTxt extends ScrollBox{
	public void render(GameContainer container,Rectangle hit) throws SlickException {
		Input inp = container.getInput();
		
		g.setWorldClip(x,y,width,height);
		g.setColor(new Color(0,0,0,150));
		g.fillRoundRect(x+1,y+1,width-2,height-2,4);
		g.setColor(new Color(255,255,255,100));
		drawString(msg,((int)scX+5),((int)scY+15),new Color(255,255,255,255));
		g.setColor(new Color(0,0,0,255));
		g.fillRoundRect(x+2,y,width-6,15,4);
		drawString(sub,defaultX+5,defaultY,new Color(255,255,0,255));
		g.setColor(new Color(255,255,255,255));
		g.setColor(new Color(0,0,0,100));
		g.fillRoundRect(x+width-22,y+3,20,height-4,4);
		g.setColor(new Color(255,255,255,255));
		scrHeight = g.getFont().getHeight(msg);
		g.flush();
		
		
		if(hit.intersects(new Rectangle(x+width-25,y+3,button[0].getWidth(),button[0].getHeight()))){
			if(inp.isMouseButtonDown(inp.MOUSE_LEFT_BUTTON)){
				if(scY < defaultY) scY++;
				
			}
			g.drawImage(button[0],x+width-25,y+3,new Color(255,255,255,255));
		}else{
			g.drawImage(button[0],x+width-25,y+3,new Color(200,200,200,255));
		}
		
		if(hit.intersects(new Rectangle(x+width-25,y+height-2-button[1].getHeight(),button[1].getWidth(),button[1].getHeight()))){
			if(inp.isMouseButtonDown(inp.MOUSE_LEFT_BUTTON)){
				if((scY+scrHeight) > (defaultY+defaultHeight)-20)scY--;
			}
			g.drawImage(button[1],x+width-25,y+height-2-button[1].getHeight(),new Color(255,255,255,255));
		}else{
			g.drawImage(button[1],x+width-25,y+height-2-button[1].getHeight(),new Color(200,200,200,255));
		}
		g.clearWorldClip();
	}
}

class ScrollStory extends ScrollBox{
	private boolean end = false;
	
	public void setEnd(boolean b){
		this.end = b;
	}
	
	public boolean getEnd(){
		return(end);
	}
	
	public void updateSelf(){
		if((scY+scrHeight) < defaultY)end = true;
		else scY-=0.5;
	}
	
	public void render(GameContainer container,Rectangle hit) throws SlickException {
		g.setFont(super.font2);
		g.setWorldClip(x,y,width,height);
		drawString(msg,((int)scX+5),((int)scY+15),new Color(255,255,255,255));
		scrHeight = g.getFont().getHeight(msg);
		
		
		g.flush();
		
		g.clearWorldClip();
	}
}

class ScrollList extends ScrollBox{
	private ArrayList<BoxList> boxList = new ArrayList<BoxList>();
	private float speed = 2.0f;
	
	public void clear(){
		this.boxList = new ArrayList<BoxList>();
	}
	
	public ArrayList<BoxList> getList(){
		return(this.boxList);
	}
	
	public void addList(Image icon,String name){
		boxList.add(new BoxList(icon,name));
	}
	
	public void render(GameContainer container,Rectangle hit) throws SlickException {
		Input inp = container.getInput();
		g.setWorldClip(x,y,width,height);
		
		g.setColor(new Color(20,20,20,200));
		g.fillRoundRect(x+1,y+1,width-2,height-2,4);
		g.setColor(new Color(255,255,255,100));
		drawString(msg,((int)scX+5),((int)scY+15),new Color(255,255,255,255));
		
		for(int i = 0;i<this.boxList.size();i++){
			if(this.boxList.get(i) != null){
				boxList.get(i).setting(container,hit);
				boxList.get(i).onClick();
				boxList.get(i).setRectangle(g,this.defaultX+5,((int)this.scY+15+(boxList.get(i).getHeight())*i),this.defaultWidth-30,55);
				if((boxList.get(i).getY()+boxList.get(i).getHeight()/2 < this.defaultY+this.defaultHeight) && 
				  (boxList.get(i).getY()+boxList.get(i).getHeight()/2 > this.defaultY))
				boxList.get(i).drawSelf();
			}
		}
		
		g.setColor(new Color(0,0,0,255));
		g.fillRoundRect(x+2,y,width-6,15,4);
		drawString(sub,defaultX+5,defaultY,new Color(255,255,0,255));
		g.setColor(new Color(255,255,255,255));
		g.setColor(new Color(0,0,0,100));
		g.fillRoundRect(x+width-22,y+3,20,height-4,4);
		g.setColor(new Color(255,255,255,255));
		scrHeight = (55*this.boxList.size()+20);
		g.flush();
		
		if(hit.intersects(new Rectangle(x+width-25,y+3,button[0].getWidth(),button[0].getHeight()))){
			if(inp.isMouseButtonDown(inp.MOUSE_LEFT_BUTTON)){
				if(scY < defaultY) scY+=speed;
				
			}
			g.drawImage(button[0],x+width-25,y+3,new Color(255,255,255,255));
		}else{
			g.drawImage(button[0],x+width-25,y+3,new Color(200,200,200,255));
		}
		
		if(hit.intersects(new Rectangle(x+width-25,y+height-2-button[1].getHeight(),button[1].getWidth(),button[1].getHeight()))){
			if(inp.isMouseButtonDown(inp.MOUSE_LEFT_BUTTON)){
				if((scY+scrHeight) > (defaultY+defaultHeight)-15)scY-=speed;
			}
			g.drawImage(button[1],x+width-25,y+height-2-button[1].getHeight(),new Color(255,255,255,255));
		}else{
			g.drawImage(button[1],x+width-25,y+height-2-button[1].getHeight(),new Color(200,200,200,255));
		}
		
		g.clearWorldClip();
	}
}

class ScrollListItem extends ScrollBox{
	private ArrayList<ItemList> boxList = new ArrayList<ItemList>();
	private float speed = 2.0f;
	
	public void clear(){
		this.boxList = new ArrayList<ItemList>();
	}
	
	public ArrayList<ItemList> getList(){
		return(this.boxList);
	}
	
	public void addList(String icon,String name,int price,String comment){
		boxList.add(new ItemList(icon,name,price,comment));
	}
	
	public void addList(String icon,String name,int price,int platPrice,String comment){
		boxList.add(new ItemList(icon,name,price,platPrice,comment));
	}
	
	public void render(GameContainer container,Rectangle hit) throws SlickException {
		Input inp = container.getInput();
		g.setWorldClip(x,y,width,height);
		
		g.setColor(new Color(20,20,20,200));
		g.fillRoundRect(x+1,y+1,width-2,height-2,4);
		g.setColor(new Color(255,255,255,100));
		drawString(msg,((int)scX+5),((int)scY+15),new Color(255,255,255,255));
		
		for(int i = 0;i<this.boxList.size();i++){
			if(this.boxList.get(i) != null){
				boxList.get(i).setting(container,hit);
				boxList.get(i).onClick();
				boxList.get(i).setRectangle(g,this.defaultX+5,((int)this.scY+15+(boxList.get(i).getHeight())*i),this.defaultWidth-30,55);
				if((boxList.get(i).getY()+boxList.get(i).getHeight()/2 < this.defaultY+this.defaultHeight) && 
				  (boxList.get(i).getY()+boxList.get(i).getHeight()/2 > this.defaultY))
				boxList.get(i).drawSelf();
			}
		}
		
		g.setColor(new Color(0,0,0,255));
		g.fillRoundRect(x+2,y,width-6,15,4);
		drawString(sub,defaultX+5,defaultY,new Color(255,255,0,255));
		g.setColor(new Color(255,255,255,255));
		g.setColor(new Color(0,0,0,100));
		g.fillRoundRect(x+width-22,y+3,20,height-4,4);
		g.setColor(new Color(255,255,255,255));
		scrHeight = (55*this.boxList.size()+20);
		g.flush();
		
		if(hit.intersects(new Rectangle(x+width-25,y+3,button[0].getWidth(),button[0].getHeight()))){
			if(inp.isMouseButtonDown(inp.MOUSE_LEFT_BUTTON)){
				if(scY < defaultY) scY+=speed;
				
			}
			g.drawImage(button[0],x+width-25,y+3,new Color(255,255,255,255));
		}else{
			g.drawImage(button[0],x+width-25,y+3,new Color(200,200,200,255));
		}
		
		if(hit.intersects(new Rectangle(x+width-25,y+height-2-button[1].getHeight(),button[1].getWidth(),button[1].getHeight()))){
			if(inp.isMouseButtonDown(inp.MOUSE_LEFT_BUTTON)){
				if((scY+scrHeight) > (defaultY+defaultHeight)-15)scY-=speed;
			}
			g.drawImage(button[1],x+width-25,y+height-2-button[1].getHeight(),new Color(255,255,255,255));
		}else{
			g.drawImage(button[1],x+width-25,y+height-2-button[1].getHeight(),new Color(200,200,200,255));
		}
		
		g.clearWorldClip();
	}
}

class ScrollMenu extends ScrollBox{
	private ArrayList<MenuList> boxList = new ArrayList<MenuList>();
	private float speed = 2.0f;
	private int area = 30;
	
	public void clear(){
		this.boxList = new ArrayList<MenuList>();
	}
	
	public ArrayList<MenuList> getList(){
		return(this.boxList);
	}
	
	public void addList(String msg){
		boxList.add(new MenuList(msg));
	}
	
	public void render(GameContainer container,Rectangle hit) throws SlickException {
		Input inp = container.getInput();
		g.setWorldClip(x,y,width,height);
		
		g.setColor(new Color(20,20,20,200));
		g.fillRoundRect(x+1,y+1,width-2,height-2,4);
		g.setColor(new Color(255,255,255,100));
		drawString(msg,((int)scX+5),((int)scY+15),new Color(255,255,255,255));
		
		for(int i = 0;i<this.boxList.size();i++){
			if(this.boxList.get(i) != null){
				boxList.get(i).setting(container,hit);
				boxList.get(i).onClick();
				boxList.get(i).setRectangle(g,this.defaultX+5,((int)this.scY+15+(boxList.get(i).getHeight())*i),this.defaultWidth-30,area);
				if((boxList.get(i).getY()+boxList.get(i).getHeight()/2 < this.defaultY+this.defaultHeight) && 
				  (boxList.get(i).getY()+boxList.get(i).getHeight()/2 > this.defaultY))
				boxList.get(i).drawSelf();
			}
		}
		
		g.setColor(new Color(0,0,0,255));
		g.fillRoundRect(x+2,y,width-6,15,4);
		drawString(sub,defaultX+5,defaultY,new Color(255,255,0,255));
		g.setColor(new Color(255,255,255,255));
		g.setColor(new Color(0,0,0,100));
		g.fillRoundRect(x+width-22,y+3,20,height-4,4);
		g.setColor(new Color(255,255,255,255));
		scrHeight = (area*this.boxList.size()+20);
		g.flush();
		
		if(hit.intersects(new Rectangle(x+width-25,y+3,button[0].getWidth(),button[0].getHeight()))){
			if(inp.isMouseButtonDown(inp.MOUSE_LEFT_BUTTON)){
				if(scY < defaultY) scY+=speed;
				
			}
			g.drawImage(button[0],x+width-25,y+3,new Color(255,255,255,255));
		}else{
			g.drawImage(button[0],x+width-25,y+3,new Color(200,200,200,255));
		}
		
		if(hit.intersects(new Rectangle(x+width-25,y+height-2-button[1].getHeight(),button[1].getWidth(),button[1].getHeight()))){
			if(inp.isMouseButtonDown(inp.MOUSE_LEFT_BUTTON)){
				if((scY+scrHeight) > (defaultY+defaultHeight)-15)scY-=speed;
			}
			g.drawImage(button[1],x+width-25,y+height-2-button[1].getHeight(),new Color(255,255,255,255));
		}else{
			g.drawImage(button[1],x+width-25,y+height-2-button[1].getHeight(),new Color(200,200,200,255));
		}
		
		g.clearWorldClip();
	}
}

class ScrollSkillList extends ScrollBox{
	private ArrayList<Skill> boxList = new ArrayList<Skill>();
	private float speed = 2.0f;
	
	public void clear(){
		this.boxList = new ArrayList<Skill>();
	}
	
	public ArrayList<Skill> getList(){
		return(this.boxList);
	}
	
	public void addList(Skill skill){
		boxList.add(skill);
	}
	
	public void render(GameContainer container,Rectangle hit) throws SlickException {
		Input inp = container.getInput();
		g.setWorldClip(x,y,width,height);
		
		g.setColor(new Color(20,20,20,200));
		g.fillRoundRect(x+1,y+1,width-2,height-2,4);
		g.setColor(new Color(255,255,255,100));
		drawString(msg,((int)scX+5),((int)scY+15),new Color(255,255,255,255));
		
		for(int i = 0;i<this.boxList.size();i++){
			if(this.boxList.get(i) != null){
				boxList.get(i).setting(container,hit);
				boxList.get(i).onClick();
				boxList.get(i).setRect(this.defaultX+5,((int)this.scY+15+(boxList.get(i).getHeight())*i),this.defaultWidth-30,55);
				if((boxList.get(i).getY()+boxList.get(i).getHeight()/2 < this.defaultY+this.defaultHeight) && 
				  (boxList.get(i).getY()+boxList.get(i).getHeight()/2 > this.defaultY))
				boxList.get(i).drawSelf();
			}
		}
		
		g.setColor(new Color(0,0,0,255));
		g.fillRoundRect(x+2,y,width-6,15,4);
		drawString(sub,defaultX+5,defaultY,new Color(255,255,0,255));
		g.setColor(new Color(255,255,255,255));
		g.setColor(new Color(0,0,0,100));
		g.fillRoundRect(x+width-22,y+3,20,height-4,4);
		g.setColor(new Color(255,255,255,255));
		scrHeight = (55*this.boxList.size()+20);
		g.flush();
		
		if(hit.intersects(new Rectangle(x+width-25,y+3,button[0].getWidth(),button[0].getHeight()))){
			if(inp.isMouseButtonDown(inp.MOUSE_LEFT_BUTTON)){
				if(scY < defaultY) scY+=speed;
				
			}
			g.drawImage(button[0],x+width-25,y+3,new Color(255,255,255,255));
		}else{
			g.drawImage(button[0],x+width-25,y+3,new Color(200,200,200,255));
		}
		
		if(hit.intersects(new Rectangle(x+width-25,y+height-2-button[1].getHeight(),button[1].getWidth(),button[1].getHeight()))){
			if(inp.isMouseButtonDown(inp.MOUSE_LEFT_BUTTON)){
				if((scY+scrHeight) > (defaultY+defaultHeight)-15)scY-=speed;
			}
			g.drawImage(button[1],x+width-25,y+height-2-button[1].getHeight(),new Color(255,255,255,255));
		}else{
			g.drawImage(button[1],x+width-25,y+height-2-button[1].getHeight(),new Color(200,200,200,255));
		}
		
		g.clearWorldClip();
	}
}

class MenuList implements Serializable{
	private String msg;
	private int x,y,width,height;
	private Graphics g;
	private Font font;
	private boolean hitBox = false,soundHit = false;
	private GameContainer container;
	private Rectangle m;
	private Sound sound[] = new Sound[3];
	
	public MenuList(String msg){
		this.msg = msg;
		try{
			this.font = new AngelCodeFont(FileList.fontDir+"simpleFont.fnt",FileList.fontDir+"simpleFont.png");
			sound = new Sound[]{
				new Sound(FileList.bgsDir+"bgs02.wav"),
				new Sound(FileList.bgsDir+"bgs03.wav"),
			};
		}catch(SlickException e){}
	}
	
	public void modifyTxt(String txt){
		this.msg = txt;
	}
	
	public void setRectangle(Graphics g,int x,int y,int width,int height){
		this.g = g;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public String getMsg(){
		return(this.msg);
	}
	
	public int getX(){
		return(x);
	}
	
	public int getY(){
		return(y);
	}
	
	public int getWidth(){
		return(width);
	}
	
	public int getHeight(){
		return(height);
	}
	
	public void drawSelf(){
		if(hitBox){
			g.setColor(new Color(50,50,50,255));
		}else{
			g.setColor(new Color(20,20,20,50));
		}
		
		g.fillRoundRect(x,y,width,height,5);
		this.drawString(this.msg,x+40,y+5,Color.green);
		
		g.flush();
	}
	
	public void setting(GameContainer container,Rectangle m){
		this.container = container;
		this.m = m;
	}
	
	public boolean isButton(){
		Input input = container.getInput();
		
		if(m.intersects(this.BoundingBox())){
			if(input.isMousePressed(input.MOUSE_LEFT_BUTTON)){
				return(true);
			}
		}
		return(false);
	}
	
	public Rectangle BoundingBox(){
		Rectangle r = new Rectangle(x,y,width,height);
		return(r);
	}
	
	public void onClick(){
		Input input = container.getInput();
		
		if(m.intersects(this.BoundingBox())){
			if(!soundHit){
				sound[1].play();
				soundHit=true;
			}
			hitBox = true;
		}else{
			hitBox = false;
			soundHit=false;
		}
	}
	
	protected void drawString(String txt,int x,int y,Color c){
		this.g.setFont(this.font);
		this.g.setColor(new Color(0,0,0,150));
		this.g.drawString(txt,x+1,y+1);
		this.g.setColor(c);
		this.g.drawString(txt,x,y);
		this.g.setColor(new Color(255,255,255,255));
	}
}

class ItemList implements Serializable{
	private String name,comment;
	private int x,y,width,height,price,plantPrice;
	public Image icon,bgIcon,p_p,p_g;
	private Graphics g;
	private Font font;
	private boolean hitBox = false,soundHit = false;
	private GameContainer container;
	private Rectangle m;
	private Sound sound[] = new Sound[3];
	
	public ItemList(String icon,String name,int price,String comment){
		this.name = name;
		this.price = price;
		this.plantPrice = 0;
		this.comment = comment;
		try{
			this.bgIcon = new Image(FileList.iconDir+"bMenu.png");
			this.icon = new Image(icon);
			
			this.p_p = new Image(FileList.iconDir+"p_p.png");
			this.p_g = new Image(FileList.iconDir+"p_g.png");
			
			this.font = new AngelCodeFont(FileList.fontDir+"simpleFont.fnt",FileList.fontDir+"simpleFont.png");
			sound = new Sound[]{
				new Sound(FileList.bgsDir+"bgs02.wav"),
				new Sound(FileList.bgsDir+"bgs03.wav"),
			};
		}catch(SlickException e){}
	}
	
	public ItemList(String icon,String name,int price,int plantPrice,String comment){
		this.name = name;
		this.price = price;
		this.plantPrice = plantPrice;
		this.comment = comment;
		
		try{
			this.bgIcon = new Image(FileList.iconDir+"bMenu.png");
			this.icon = new Image(icon);
			
			this.p_p = new Image(FileList.iconDir+"p_p.png");
			this.p_g = new Image(FileList.iconDir+"p_g.png");
			
			this.font = new AngelCodeFont(FileList.fontDir+"simpleFont.fnt",FileList.fontDir+"simpleFont.png");
			sound = new Sound[]{
				new Sound(FileList.bgsDir+"bgs02.wav"),
				new Sound(FileList.bgsDir+"bgs03.wav"),
			};
		}catch(SlickException e){}
	}
	
	public void setRectangle(Graphics g,int x,int y,int width,int height){
		this.g = g;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public String getName(){
		return(name);
	}
	
	public void resetName(){
		this.name = this.getName();
	}
	
	public int getX(){
		return(x);
	}
	
	public int getY(){
		return(y);
	}
	
	public int getWidth(){
		return(width);
	}
	
	public int getHeight(){
		return(height);
	}
	
	public void drawSelf(){
		if(hitBox){
			g.setColor(new Color(50,50,50,255));
		}else{
			g.setColor(new Color(20,20,20,50));
		}

		g.fillRoundRect(x,y,width,height,5);
		g.drawImage(bgIcon,x+10,y+(this.height-bgIcon.getHeight())/2);
		g.setColor(new Color(255,255,255,255));
		g.drawImage(icon,x+10,y+(this.height-icon.getHeight())/2);
		
		this.drawString(this.name,x+55,y+10,Color.blue);
		
		
		g.drawImage(p_g,x+this.width-55,this.y+10);
		this.drawString(this.price+" b.",x+this.width-40,y+10,Color.yellow);
		
		
		g.drawImage(p_p,x+this.width-120,this.y+10);
		this.drawString(this.plantPrice+" p.",x+this.width-103,y+10,Color.green);
		
		this.drawString("คำอธิบาย : "+comment,x+55,y+25,Color.white);
		g.flush();
	}
	
	public int getPrice(){
		return(this.price);
	}
	
	public int getPPrice(){
		return(this.plantPrice);
	}
	
	public void setting(GameContainer container,Rectangle m){
		this.container = container;
		this.m = m;
	}
	
	public boolean isButton(){
		Input input = container.getInput();
		
		if(m.intersects(this.BoundingBox())){
			if(input.isMousePressed(input.MOUSE_LEFT_BUTTON)){
				return(true);
			}
		}
		return(false);
	}
	
	public Rectangle BoundingBox(){
		Rectangle r = new Rectangle(x,y,width,height);
		return(r);
	}
	
	public void onClick(){
		Input input = container.getInput();
		
		if(m.intersects(this.BoundingBox())){
			if(!soundHit){
				sound[1].play();
				soundHit=true;
			}
			hitBox = true;
		}else{
			hitBox = false;
			soundHit=false;
		}
	}
	
	protected void drawString(String txt,int x,int y,Color c){
		this.g.setFont(this.font);
		this.g.setColor(new Color(0,0,0,150));
		this.g.drawString(txt,x+1,y+1);
		this.g.setColor(c);
		this.g.drawString(txt,x,y);
		this.g.setColor(new Color(255,255,255,255));
	}
}

class BoxList implements Serializable{
	private String name;
	private int x,y,width,height;
	private Image icon;
	private Graphics g;
	private Font font;
	private boolean hitBox = false,soundHit = false;
	private GameContainer container;
	private Rectangle m;
	private Sound sound[] = new Sound[3];
	
	public BoxList(Image icon,String name){
		this.name = name;
		try{
			if(icon == null)
				this.icon = new Image(FileList.iconDir+"ic1.png");
			else
				this.icon = icon;
			
			this.font = new AngelCodeFont(FileList.fontDir+"simpleFont.fnt",FileList.fontDir+"simpleFont.png");
			sound = new Sound[]{
				new Sound(FileList.bgsDir+"bgs02.wav"),
				new Sound(FileList.bgsDir+"bgs03.wav"),
			};
		}catch(SlickException e){}
	}
	
	public void setRectangle(Graphics g,int x,int y,int width,int height){
		this.g = g;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public String getName(){
		return(name);
	}
	
	public int getX(){
		return(x);
	}
	
	public int getY(){
		return(y);
	}
	
	public int getWidth(){
		return(width);
	}
	
	public int getHeight(){
		return(height);
	}
	
	public void drawSelf(){
		if(hitBox){
			g.setColor(new Color(50,50,50,255));
		}else{
			g.setColor(new Color(20,20,20,50));
		}
		
		g.fillRoundRect(x,y,width,height,5);
		g.setColor(new Color(255,255,255,255));
		g.drawImage(icon,x+8,y+10);
		this.drawString(this.name,x+55,y+10,Color.yellow);
		g.flush();
	}
	
	public void setting(GameContainer container,Rectangle m){
		this.container = container;
		this.m = m;
	}
	
	public boolean isButton(){
		Input input = container.getInput();
		
		if(m.intersects(this.BoundingBox())){
			if(input.isMousePressed(input.MOUSE_LEFT_BUTTON)){
				return(true);
			}
		}
		return(false);
	}
	
	public Rectangle BoundingBox(){
		Rectangle r = new Rectangle(x,y,width,height);
		return(r);
	}
	
	public void onClick(){
		Input input = container.getInput();
		
		if(m.intersects(this.BoundingBox())){
			if(!soundHit){
				sound[1].play();
				soundHit=true;
			}
			hitBox = true;
		}else{
			hitBox = false;
			soundHit=false;
		}
	}
	
	protected void drawString(String txt,int x,int y,Color c){
		this.g.setFont(this.font);
		this.g.setColor(new Color(0,0,0,150));
		this.g.drawString(txt,x+1,y+1);
		this.g.setColor(c);
		this.g.drawString(txt,x,y);
		this.g.setColor(new Color(255,255,255,255));
	}
}

abstract class Skill implements Serializable{
	protected Image bgIcon,icon;
	protected char hKey;
	protected slType type;
	protected int mp,pw,lv,lvMax,x,y,width,height,cooldown,maxcooldown;
	protected String name,comment;
	protected Sound sound[] = new Sound[3];
	protected Rectangle m;
	protected GameContainer container;
	protected boolean hitBox = false,soundHit = false;
	protected Font font;
	protected Graphics g;
	
	public void up(){
		this.lv++;
	}
	
	public int getX(){
		return(x);
	}
	
	public int getY(){
		return(y);
	}
	
	public int getWidth(){
		return(width);
	}
	
	public int getHeight(){
		return(height);
	}
	
	public int getLv(){
		return(this.lv);
	}
	
	public int getLvMax(){
		return(lvMax);
	}
	
	public char getHKey(){
		return(this.hKey);
	}
	
	public int getMp(){
		return(this.mp);
	}
	
	public int getPw(){
		return(this.pw);
	}
	
	public void setReCoolDown(int a){
		this.cooldown-=a;
	}
	
	public int getCoolDown(){
		return(cooldown);
	}
	
	public int getMaxCoolD(){
		return(maxcooldown);
	}
	
	public Image getBGIcon(){
		return(this.bgIcon);
	}
	
	public Image getIcon(){
		return(this.icon);
	}
	
	public String getName(){
		return(this.name);
	}
	
	public void setCoolDown(int cooldown){
		this.cooldown = 0;
		this.maxcooldown = cooldown;
	}
	
	public void reCoolDown(){
		this.cooldown = this.maxcooldown;
	}
	
	public void create(int lvMin,int lvMax){
		try{
			this.lv = lvMin;
			this.lvMax = lvMax;
			this.g = new Graphics();
			this.bgIcon = new Image(FileList.iconDir+"ic12.png");
			this.font = new AngelCodeFont(FileList.fontDir+"simpleFont.fnt",FileList.fontDir+"simpleFont.png");
			sound = new Sound[]{
				new Sound(FileList.bgsDir+"bgs02.wav"),
				new Sound(FileList.bgsDir+"bgs03.wav"),
			};
		}catch(SlickException e){
		}
	}
	
	public Rectangle BoundingBox(){
		Rectangle r = new Rectangle(x,y,width,height);
		return(r);
	}
	
	public void setting(GameContainer container,Rectangle m){
		this.container = container;
		this.m = m;
	}
	
	public void setComment(String comment){
		this.comment = comment;
	}
	
	public void setHKey(char hKey){
		this.hKey = hKey;
	}
	
	public void setRect(int x,int y,int width,int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void drawSelf(){
		if(hitBox){
			g.setColor(new Color(50,50,50,255));
		}else{
			g.setColor(new Color(20,20,20,50));
		}
		
		g.fillRoundRect(x,y,width,height,5);
		g.drawImage(bgIcon,x+8,y+(this.getHeight()-bgIcon.getHeight())/2);
		g.drawImage(icon,x+8,y+(this.getHeight()-bgIcon.getHeight())/2);
		
		this.drawString(""+this.lv,x+(8+bgIcon.getWidth()-font.getWidth(""+this.lv)),this.y+(this.getHeight()-bgIcon.getHeight())/2+bgIcon.getHeight()-font.getHeight(""+this.lv),Color.red);
		this.drawString(this.name,x+55,y+10,Color.yellow);
		this.drawString("ชนิด : "+this.type.getName(),x+180,y+10,Color.white);
		
		this.drawString("คำอธิบาย : "+this.comment,x+55,y+25,Color.white);
		this.drawString("Mp : "+this.mp,this.x+this.getWidth()-50,y+10,Color.green);
		this.drawString("Pw : "+this.pw,this.x+this.getWidth()-50,y+25,Color.green);
		g.flush();
	}
	
	public void onClick(){
		Input input = container.getInput();
		
		if(m.intersects(this.BoundingBox())){
			if(!soundHit){
				sound[1].play();
				soundHit=true;
			}
			hitBox = true;
		}else{
			hitBox = false;
			soundHit=false;
		}
	}
	
	protected void drawString(String txt,int x,int y,Color c){
		this.g.setFont(this.font);
		this.g.setColor(new Color(0,0,0,150));
		this.g.drawString(txt,x+1,y+1);
		this.g.setColor(c);
		this.g.drawString(txt,x,y);
		this.g.setColor(new Color(255,255,255,255));
	}
	
	public boolean isButton(){
		Input input = container.getInput();
		
		if(m.intersects(this.BoundingBox())){
			if(input.isMousePressed(input.MOUSE_LEFT_BUTTON)){
				return(true);
			}
		}
		return(false);
	}
	
	abstract void data();
}

class TSkill extends Skill implements Serializable{
	public TSkill(String icon,String name,slType type,int mp,int pw){
		try{
			this.icon = new Image(icon);
			this.name = name;
			this.type = type;
			this.mp = mp;
			this.pw = pw;
			this.lv = 0;
		}catch(SlickException e){
		}
	}
	
	public void data(){
	}
}

class Item implements Serializable{
	public Image img;
	public String name,comment;
	public int price;
	public short hp,sp,pw,wp,num;
}

class JPlent implements Serializable{
	protected Image[] img = new Image[4];
	protected int price,life,lifeMax,puival,hp,hpMax,frame=0,delay,lv=0;
	protected boolean up;
	protected Graphics g = new Graphics();
	protected Font font;
	
	public JPlent(){
		try{
			font = new AngelCodeFont("Resource/farm/farmfont/font.fnt","Resource/farm/farmfont/font.png");
			g.setFont(font);
		}catch(SlickException er){
		}
	}
	
	public void render(float x,float y){
		if(img[frame] != null)
		img[frame].draw(x,y);
	}
	
	public void update(){
		if(life > lifeMax-puival)life--;
		if(delay == (lifeMax/7)) HPD();
		
		if(delay < (lifeMax/img.length))
			delay++;
		else{
			if(life > lifeMax-puival){
				if(frame<img.length-1){
					frame++;
					lv++;
					delay = 0;
				}
			}else delay = 0;
		}
	}
	
	public int getPuival(){
		return(puival);
	}
	
	public void upPuival(int val){
		puival+=val;
	}
	
	public int getPrice(){
		return(price);
	}
	
	public int getLv(){
		return(lv);
	}
	
	public Image getImage(){
		return(img[frame]);
	}
	
	public int getLife(){
		return(life);
	}
	
	public int getLifeMax(){
		return(lifeMax);
	}
	
	public int getHpMax(){
		return(this.hpMax);
	}
	
	public int getHp(){
		return(hp);
	}
	
	public void WaterSelf(){
		this.hp += 20;
	}
	
	public void HPD(){
		this.hp -= 35;
	}
}

class JPlentA extends JPlent implements Serializable{
	public JPlentA(){
		this.up = false;
		this.lifeMax = 1500;
		this.life = lifeMax;
		this.hpMax = 40;
		this.hp = hpMax;
		this.price+=100;
		this.puival = lifeMax/3;
		try{
			img = new Image[]{
				new Image(FileList.farmDir+"A/pl_01.PNG"),
				new Image(FileList.farmDir+"A/pl_02.PNG"),
				new Image(FileList.farmDir+"A/pl_03.PNG"),
				new Image(FileList.farmDir+"A/pl_04.PNG"),
				new Image(FileList.farmDir+"A/pl_05.PNG"),
			};
		}catch(SlickException e){
		}
	}
}

class JPlentB extends JPlent implements Serializable{
	public JPlentB(){
		this.up = false;
		this.lifeMax = 2000;
		this.life = lifeMax;
		this.hpMax = 90;
		this.hp = hpMax;
		this.price+=60;
		this.puival = lifeMax/2;
		try{
			img = new Image[]{
				new Image(FileList.farmDir+"B/pl_01.PNG"),
				new Image(FileList.farmDir+"B/pl_02.PNG"),
				new Image(FileList.farmDir+"B/pl_03.PNG"),
				new Image(FileList.farmDir+"B/pl_04.PNG"),
				new Image(FileList.farmDir+"B/pl_05.PNG"),
			};
		}catch(SlickException e){
		}
	}
}

class JPlentC extends JPlent implements Serializable{
	public JPlentC(){
		this.up = false;
		this.lifeMax = 2500;
		this.life = lifeMax;
		this.hpMax = 110;
		this.hp = hpMax;
		this.price+=45;
		this.puival = lifeMax/2;
		try{
			img = new Image[]{
				new Image(FileList.farmDir+"C/pl_01.PNG"),
				new Image(FileList.farmDir+"C/pl_02.PNG"),
				new Image(FileList.farmDir+"C/pl_03.PNG"),
				new Image(FileList.farmDir+"C/pl_04.PNG"),
				new Image(FileList.farmDir+"C/pl_05.PNG"),
			};
		}catch(SlickException e){
		}
	}
}

class JPlentD extends JPlent implements Serializable{
	public JPlentD(){
		this.up = false;
		this.lifeMax = 3000;
		this.life = lifeMax;
		this.hpMax = 200;
		this.hp = hpMax;
		this.price+=38;
		this.puival = lifeMax/2;
		try{
			img = new Image[]{
				new Image(FileList.farmDir+"D/pl_01.PNG"),
				new Image(FileList.farmDir+"D/pl_02.PNG"),
				new Image(FileList.farmDir+"D/pl_03.PNG"),
				new Image(FileList.farmDir+"D/pl_04.PNG"),
				new Image(FileList.farmDir+"D/pl_05.PNG"),
			};
		}catch(SlickException e){
		}
	}
}

class JTile implements Serializable{
	private float x,y;
	private int column,row,index,width,height,wUP,pUP;
	private Image[] img;
	private Image maxImg;
	private JPlent plent;
	private Graphics g;
	private GameContainer container;
	private Sound[] sound = new Sound[6];
	private ParticleSystem pts,pts2;
	public boolean waterMode = false,puiMode=false;
	
	public JTile(GameContainer container){
		try{
			maxImg = new Image(FileList.farmDir+"length.png");
			this.container = container;
			g = new Graphics();
			g.setFont(container.getDefaultFont());
			sound = new Sound[]{
				new Sound(FileList.farmDir+"farmsound/dig.wav"),
				new Sound(FileList.farmDir+"farmsound/crop.wav"),
				new Sound(FileList.bgsDir+"castsound.wav"),
				new Sound(FileList.farmDir+"farmsound/delete.wav"),
				new Sound(FileList.bgsDir+"Recovery.wav"),
				new Sound(FileList.bgsDir+"pui.wav"),
			};
			img = new Image[]{
				new Image(FileList.farmDir+"tile01.png"),
				new Image(FileList.farmDir+"tile02.png"),

			};
			width = img[0].getWidth();
			height = img[0].getHeight();
			
			pts = ParticleIO.loadConfiguredSystem(FileList.effectDir+"water1.xml");
			pts2= ParticleIO.loadConfiguredSystem(FileList.effectDir+"pui.xml");
			this.resetPui();
			this.resetWater();
		}catch(SlickException e){
		}catch(IOException e){
		}
	}
	
	public void set(int c,int r,int i){
		this.column = c;
		this.row = r;
		this.index = i;
	}

	public void render(int x,int y){
		this.x = x;
		this.y = y;
		this.img[index].draw(x,y);
		
		try{
			if(plent != null && this.getPlent()!=null){
				if(plent.getLife() <= 0 || plent.getHp() <= 0){
					sound[3].play();
					removePlent();
				}
				plent.render(x+this.getWidth()/2,y-plent.getImage().getHeight()/2);
				plent.update();
				g.setColor(Color.green);
				
				if(waterMode){
					pts.update(10);
					
					if(wUP>0){
						wUP--;
						pts.render(x+this.getWidth()/2+21,y-plent.getImage().getHeight()/2+40);
					}else{
						waterMode=false;
						resetWater();
					}
					
				}
				
				if(puiMode){
					pts2.update(10);
					if(pUP>0){
						pUP--;
						pts2.render(x+this.getWidth()/2+21,y-plent.getImage().getHeight()/2+50);
					}else{
						resetPui();
						puiMode=false;
					}
				}
				
				if(this.getPlent().getLv()==3){
					g.drawImage(maxImg,x+this.getWidth()/2,y-plent.getImage().getHeight()/2);
				}
				
				this.drawBar(plent.getHp(),plent.getHpMax(),x+this.getWidth()/2,y-110,40,3,Color.red);
				this.drawBar(plent.getLife(),plent.getLifeMax(),x+this.getWidth()/2,y-105,40,3,Color.white);
			}
		}catch(NullPointerException e){
		}catch(SlickException er){
		}
	}
	
	public void addPlent(JPlent p){
		this.plent = p;
	}
	
	public JPlent getPlent(){
		return(this.plent);
	}
	
	public void removePlent(){
		this.plent = null;
	}
	
	public int getWidth(){
		return(width/2);
	}
	
	public int getHeight(){
		return(height);
	}
	
	public void resetWater(){
		wUP=150;
		pts.reset();
	}
	
	public void resetPui(){
		pUP=150;
		pts2.reset();
	}
	
	public int getRow(){
		return(row);
	}
	
	public int getColumn(){
		return(column);
	}
	
	public float getX(){
		return(x);
	}
	
	public float getY(){
		return(y);
	}
	
	public void get(){
		System.out.println("Mouse X "+container.getInput().getMouseX());
		System.out.println("Mouse Y "+container.getInput().getMouseY());
	}
	
	public char onClick(){
		Input KEY = container.getInput();
		
		if(Mousebox().intersects(Boundingbox())){
			if(KEY.isMousePressed(KEY.MOUSE_LEFT_BUTTON)){
				return('l');
			}
			if(KEY.isMousePressed(KEY.MOUSE_RIGHT_BUTTON)) return('r');
			if(KEY.isMousePressed(KEY.MOUSE_MIDDLE_BUTTON))return('m');
			return('h');
		}
		return('n');
	}
	
	public Rectangle Boundingbox(){
		Rectangle tBox = new Rectangle(x+25,y,30,30);
		//g.fillRect(x+25,y+35,30,30);
		return(tBox);
	}
	
	public Rectangle Mousebox(){
		Input KEY = container.getInput();
		
		Rectangle mBox = new Rectangle(KEY.getMouseX(),KEY.getMouseY(),0,0);
		return(mBox);
	}
	
	public void next(){
		this.index++;
	}
	
	public void prev(){
		this.index--;
	}
	
	public void reset(){
		this.index=0;
	}
	
	public int getLength(){
		return(img.length-1);
	}
	
	public Image getImage(){
		return(this.img[index]);
	}
	
	public void showStatus(){
	}
	
	public void drawBar(int min,int max,int x,int y,int w,int h,Color color) throws SlickException {
		if(min < 0)min=0;
		if(min > max)min=max;
		
		g.setColor(new Color(0,0,0,150));
		g.fillRect(x+2,y+95,w+2,h);
		g.setColor(color);
		g.fillRect(x+3,y+95+1,(int)(w*((double)min/max)),h-2);
		g.setColor(new Color(255,255,255));
	}
	
	public Sound getSound(int index){
		return(sound[index]);
	}
	
	public int getIndex(){
		return(index);
	}
}

class JLayer implements Serializable{
	private int[][] map;
	private JTile[][] tile;
	
	public void set(int[][] map,JTile[][] tile){
		this.map = map;
		this.tile = tile;
	}
	
	public int[][] getMap(){
		return(map);
	}
	
	public void setMap(int c,int r,int index){
		this.map[c][r] = index;
	}
	
	public JTile[][] getTile(){
		return(tile);
	}
}

class JMap implements Serializable{
	private JLayer[] layer = new JLayer[3];
	private int _root = 0;
	
	public JMap(int[][] map,GameContainer container){
		layer[_root] = new JLayer();
		layer[_root].set(map,new JTile[map.length][map[0].length]);
		
		for(int i = 0;i<map.length;i++){
			for(int j = 0;j<map[0].length;j++){
				layer[_root].getTile()[i][j] = new JTile(container);
				layer[_root].getTile()[i][j].set(i,j,map[i][j]);
			}
		}
		
		System.out.printf("colums is : %d \nrows is : %d\n",getWidth(),getHeight());
	}
	
	public void modify(int c,int r,int index){
		layer[_root].setMap(c,r,index);
		layer[_root].getTile()[c][r].set(c,r,index);
	}
	
	public JTile[][] getTile(){
		return(layer[_root].getTile());
	}
	
	public int getWidth(){
		return(layer[_root].getTile()[0].length);
	}
	
	public int getHeight(){
		return(layer[_root].getTile().length);
	}
}

class JMapManager implements Serializable{
	private int x,y; //start drawing map x and y
	private JMap map;
	private Image cursor;
	
	public JMapManager(JMap map,int x,int y){
		this.x = x;
		this.y = y;
		this.map = map;
		
		try{
			this.cursor = new Image(FileList.farmDir+"cursor.png");
		}catch(SlickException e){
		}
	}
	
	public void render(ToolFarm tool,CHARACTER hero){
		for(int i = 0;i<map.getHeight();i++){
			for(int j = 0;j<map.getWidth();j++){
				JTile t = map.getTile()[i][j];
				int mapX = x+(j-i)*t.getWidth()/2;
				int mapY = y+(j+i)*t.getWidth();
				t.render(mapX,mapY);
				
				
				switch(tool){
					case Nullmode:break;
					case Lifemode:{
						if(t.onClick() == 'l'){
							if(hero.item[0] >= 1){
								if(t.getIndex() == 1 && t.getPlent() != null){
									if(t.getPlent().getPuival() <= t.getPlent().getPuival()+50){
										t.getPlent().upPuival(300);
										hero.item[0]--;
										t.getSound(5).play();
										t.puiMode=true;
										t.resetPui();
									}
								}
							}
						}
						break;
					}
					case Watermode:{
						if(t.onClick() == 'l'){
							if(hero.item[2] >= 1){
								if(t.getIndex() == 1 && t.getPlent() != null){
									hero.item[2]--;
									t.getPlent().WaterSelf();
									t.getSound(4).play();
									t.waterMode=true;
									t.resetWater();
								}
							}
						}
						break;
					}
					case Digmode:{
						if(t.onClick() == 'l'){
							if(t.getPlent() != null){
								t.getSound(0).play();
								t.removePlent();
							}
							if(t.getIndex() == 0){
								if(hero.item[1] >= 1){
									t.next();
									map.modify(i,j,t.getIndex());
									t.getSound(0).play();
									hero.item[1]--;
									break;
								}
							}
						}
						break;
					}
					case CropDmode:{
						if(t.onClick() == 'l'){
							if(t.getIndex() == 1 && t.getPlent() == null){
								if(hero.item[3] >= 1){
									t.addPlent(new JPlentD());
									hero.item[3]--;
									hero.MinExpJob += 3;
									t.getSound(1).play();
								}
							}else tool = ToolFarm.Nullmode;
							if(t.getPlent() != null && t.getPlent().getLv() == 3){
								if(t.getPlent().getLv() == 3) hero.plant_am+=t.getPlent().getPrice();
								hero.MinExpJob += 2;	
								t.getSound(2).play();
								t.removePlent();
							}
						}
						break;
					}
					case CropCmode:{
						if(t.onClick() == 'l'){
							if(t.getIndex() == 1 && t.getPlent() == null){
								if(hero.item[4] >= 1){
									t.addPlent(new JPlentC());
									hero.item[4]--;
									hero.MinExpJob += 3;
									t.getSound(1).play();
								}
							}else tool = ToolFarm.Nullmode;
							if(t.getPlent() != null && t.getPlent().getLv() == 3){
								if(t.getPlent().getLv() == 3) hero.plant_am+=t.getPlent().getPrice();
								hero.MinExpJob += 2;	
								t.getSound(2).play();
								t.removePlent();
							}
						}
						break;
					}
					case CropBmode:{
						if(t.onClick() == 'l'){
							if(t.getIndex() == 1 && t.getPlent() == null){
								if(hero.item[5] >= 1){
									t.addPlent(new JPlentB());
									hero.item[5]--;
									hero.MinExpJob += 3;
									t.getSound(1).play();
								}
							}else tool = ToolFarm.Nullmode;
							if(t.getPlent() != null && t.getPlent().getLv() == 3){
								if(t.getPlent().getLv() == 3) hero.plant_am+=t.getPlent().getPrice();
								hero.MinExpJob += 2;	
								t.getSound(2).play();
								t.removePlent();
							}
						}
						break;
					}
					case CropAmode:{
						if(t.onClick() == 'l'){
							if(t.getIndex() == 1 && t.getPlent() == null){
								if(hero.item[6] >= 1){
									t.addPlent(new JPlentA());
									hero.item[6]--;
									hero.MinExpJob += 3;
									t.getSound(1).play();
								}
							}else tool = ToolFarm.Nullmode;
							if(t.getPlent() != null && t.getPlent().getLv() == 3){
								if(t.getPlent().getLv() == 3) hero.plant_am+=t.getPlent().getPrice();
								hero.MinExpJob += 2;	
								t.getSound(2).play();
								t.removePlent();
							}
						}
						break;
					}
					case Cutmode:break;
				}
				if(t.onClick() == 'h'){
					cursor.draw(t.getX(),t.getY());
				}
			}
		}
	}
}
