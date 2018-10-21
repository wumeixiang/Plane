package reply.fancy.danei;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

/*
 * ÔÂ±ý¹²ÓÐ4ÖÖ£¬Ò»ÖÖ¼ÓÑª£¬ÁíÍâÈýÖÖ»»×Óµ¯
 */
public class MoonCake {

	//ÔÂ±ý×ø±ê
	int mx;
	int my;
	//ÔÂ±ýÍ¼Æ¬
	Image mImg;
	//¶¨ÒåËÙ¶È
	int speed;
	//ÔÂ±ýÊÇ·ñ´æÔÚ
	boolean isExist = true;
	
	//¶¨ÒåÑªÁ¿
	int hp;
	
	public MoonCake() {
		Random random = new Random();
		int number = random.nextInt(4) + 1;
		this.mx = random.nextInt(351);
		this.my = -50;
		this.speed = 1;
		this.hp = number;
		this.mImg = new ImageIcon("images/moon/moon_" + number + ".png").getImage();

	}
	
	public MoonCake(int mx,int my,int speed,int hp,Image mImg) {
		this.mx = mx;
		this.my = my;
		this.speed = speed;
		this.hp = hp;
		this.mImg = mImg;
	}
	
	//»æÖÆÔÂ±ý
	public void drawMoonCake(Graphics g) {
		g.drawImage(mImg, mx, my, null);
	}
	
	//ÊµÏÖÔÂ±ýÒÆ¶¯
	public void moveMoonCake() {
		my+=speed;
		if(my >=600) {
			isExist = false;
		}
	}
}
