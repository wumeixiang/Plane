package reply.fancy.danei;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

/*
 * ´óÕÐ
 */
public class Ulti {
	//Í¼Æ¬
	Image img;
	//Î»ÖÃ
	int x;
	int y;
	//·Ö±ð×óÓÒÒÆ¶¯µÄÁ½¸öÖù×Ó
	int lx;
	int rx;
	//·½Ïò
	int dirx = -2;
	int diry = 2;
	//´óÐ¡
	int width;
	int height;
	//ÊÇ·ñÊÍ·Å´óÕÐ
	boolean ulti = false;
	//´óÕÐ¼ÆÊý£¬Ò»´Î´óÕÐÒª·ÅÁ½¸ö»ØºÏ
	int count = 2;
	
	public Ulti() {
		// TODO Auto-generated constructor stub
		img = new ImageIcon("images/bullet/bullet_4.png").getImage();
		x = 200-15;
		y = 0;
		width = img.getWidth(null);
		height = img.getHeight(null);
	}
	
	//¸Ä±äÍ¼Æ¬
	public void setImg(Image img) {
		this.img = img;
		width = img.getWidth(null);
		height = img.getHeight(null);
	}		
	//»­´óÕÐ
	public void drawUlti(Graphics g) {
		//»­×ó±ßµÄ
		if(ulti) {
			for(int i=0; i<800; i+=height) {
				g.drawImage(img, lx, i, null);
				g.drawImage(img, rx, i, null);
			}
		}
	}
	//´óÕÐÒÆ¶¯
	public void move() {
		if(ulti) {
			lx += dirx;
			rx += diry;
		}
		if(lx<1 || rx<0) {
			count--;
			//ÖØÖÃ
			dirx *= -1;
			diry *= -1;
		}
		if(count == 0) {
			ulti = false;
			count = 2;
			lx = x;
			rx = x;
		}
	}
}
