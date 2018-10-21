package reply.fancy.danei;


import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

//µÐ»ú
public class EnemyPlane{
	//Í¼Æ¬
	Image enemyImg;
	//×ø±ê
	int x;
	int y;
	//´óÐ¡
	int width;
	int heihgt;
	//ËÙ¶È
	int speedX;
	int speedY;
	//·ÖÊý
	int score;
	//ÉúÃü
	int hp;
	//·É»ú±¬Õ¨Ê±¼ä
	int boomtime = 27;
	int boom = boomtime/3;	//·ÖÈý´Î±¬Õ¨,Ã¿´ÎµÄ¼ä¸ôÊ±¼ä
	int boomcout = boomtime/3;	//´ÓµÚÒ»´Î±¬Õ¨¿ªÊ¼¼ÆÊ±
	int sumboom = boomtime+boom;	//×Ü±¬Õ¨Ê±¼ä
	
	//·É»ú×´Ì¬£¬ÊÇ·ñ´æÔÚ
	boolean Exist = true;
	
	public EnemyPlane(Image img,int x,int y,int speedX,int speedY,int score,int hp) {
		// TODO Auto-generated constructor stub
		this.enemyImg = img;
		this.x = x;
		this.y = y;
		this.width = img.getWidth(null);
		this.heihgt = img.getHeight(null);
		this.speedX = speedX;
		this.speedY = speedY;
		this.score = score;
		this.hp = hp;
	}
	//¸Ä±äÍ¼Æ¬
	public void setImg(Image img) {
		enemyImg = img;
		this.width = img.getWidth(null);
		this.heihgt = img.getHeight(null);
	}	
	//»æÖÆ·É»ú
	public void drawPlane(Graphics g) {
		g.drawImage(enemyImg, x, y, null);
	}
	//·É»úÒÆ¶¯
	public void move() {
		y += speedY;
		//Èç¹û³¬³ö½çÃæ£¬·É»ú²»´æÔÚ
		if(y >= 800)
			Exist = false;
	}
	public void leftmove(){
		x+=speedX;
//		y += speedY;
		//Èç¹û³¬³ö½çÃæ£¬·É»ú²»´æÔÚ
		if(y >= 800  || x<=-100 || x>=500)
			Exist = false;
	}
	public void rightmove(){
		x-=speedX;
//		y += speedY;
		//Èç¹û³¬³ö½çÃæ£¬·É»ú²»´æÔÚ
		if(y >= 800  || x<=-100 || x>=500)
			Exist = false;
	}
	//·É»ú±¬Õ¨
	public void boom() {
		if(!Exist && boomcout<=sumboom) {			
			if(boomcout%boom == 0) {
				int centerx = x+enemyImg.getWidth(null)/2;
				int centery = y+enemyImg.getHeight(null)/2;
				enemyImg = new ImageIcon("images/blast/blast_"+boomcout/boom+".png").getImage();			
				x = centerx-enemyImg.getWidth(null)/2;
				y = centery-enemyImg.getHeight(null)/2;
			}
			boomcout++;			
		}
	}
}
