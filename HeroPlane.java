package reply.fancy.danei;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

//Ó¢ÐÛ»ú
public class HeroPlane{
	//Ó¢ÐÛ»úÍ¼Æ¬
	Image[] heroImg;
	//Ó¢ÐÛ»úÊý×éÏÂ±ê£¬ÓÃÓÚÇÐ»»Ó¢ÐÛ»úÍ¼Æ¬Æðµ½¶¯Ì¬Ð§¹û
	int index = 0;
	//Î»ÖÃ
	int x;
	int y;
	//´óÐ¡
	int width;
	int height;
	//×Óµ¯¶ÔÏó
	Bullet bullet;
	//´óÕÐ¶ÔÏó
	Ulti ulti = new Ulti();
	//´óÕÐÊ¹ÓÃ´ÎÊý
	int ultinum = 3;
	//ÑªÁ¿
	int hp=5;
	//·ÖÊý
	int score=0;
	
	public HeroPlane() {
		// TODO Auto-generated constructor stub
		//Ó¢ÐÛÍ¼Æ¬
		heroImg = new Image[2];
		heroImg[0] = new ImageIcon("images/HeroPlane/1.png").getImage();
		heroImg[1] = new ImageIcon("images/HeroPlane/11.png").getImage();
		//´óÐ¡
		width = heroImg[0].getWidth(null);
		height = heroImg[0].getHeight(null);
	}
	//¸Ä±äÍ¼Æ¬
	public void setImg(Image img1,Image img2) {
		heroImg[0] = img1;
		heroImg[1] = img2;
		width = img1.getWidth(null);
		height = img1.getHeight(null);
	}	
	//»æÖÆÓ¢ÐÛ»ú
	public void drawPlane(Graphics g) {
		g.drawImage(heroImg[0], x, y, null);
	}
	//×°±¸ÎäÆ÷
	public void weapon(Bullet bullet2) {
		bullet = bullet2;
	}
	//ÒÆ¶¯
/*	public void move() {
		y -= 3;
	}*/
	//ÒÆ¶¯
	public void setLocation() {
		x = 190;
		y = 700;
	}
	
	//»æ»­×Ö·û´®
	public void drawString(Graphics g) {
		g.setColor(Color.RED);
		g.setFont(new Font("ºÚÌå",Font.BOLD, 30));
		g.drawString("Í¨¹Ø³É¹¦£¡", 500,500);
	}
		
	public void fly(){
		//·É»ú´ÓÆÁÄ»·É³ö
		y=y-3;
	}
}