package reply.fancy.danei;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

/*
 * 大boss
 */
public class BigBoss {
	//图片
	Image bossImg;
	//爆炸图片
	Image boomImg;
	//坐标
	int x;
	int y;
	//爆炸坐标
	int boomx;
	int boomy;
	//大小
	int width;
	int height;
	//速度
	int speedX;
	int speedY;
	//生命
	int hp;
	//分数
	int score;
	//飞机爆炸时间
	int boomtime = 99;
	int boom = boomtime/3;	//分三次爆炸,每次的间隔时间
	int boomcout = boomtime/3;	//从第一次爆炸开始计时
	int sumboom = boomtime+boom;	//总爆炸时间
	//飞机状态，是否存在
	boolean Exist = true;

	public BigBoss(Image img,int x,int y,int speedX,int speedY,int score,int hp) {
		// TODO Auto-generated constructor stub
		this.bossImg = img;
		this.x = x;
		this.y = y;
		this.width = img.getWidth(null);
		this.height = img.getWidth(null);
		this.speedX = speedX;
		this.speedY = speedY;
		this.score = score;
		this.hp = hp;
	}
	//改变图片
	public void setImg(Image img) {
		bossImg = img;
		this.width = img.getWidth(null);
		this.height = img.getHeight(null);
	}
	//绘制飞机
	public void drawPlane(Graphics g) {
		g.drawImage(bossImg, x, y, null);
		for(int i = 0; i<3; i++) {
			int bx = (int) (Math.random()*width+x-30);
			int by = (int) (Math.random()*height+y-30);
			g.drawImage(boomImg, bx, by, null);
		}
	}
	//飞机移动
	public void move() {
		x += speedX;
		y += speedY;
		//大boss在100时，不在前进
		if(y == 50) {
			speedY = 0;
		}
		else if(y == 49)
			speedX = 1;
		//在界面左右徘徊
		if(x >= 400-width || x <=0)
			speedX *= -1;
	}
	//飞机爆炸
	public void boom() {
		if(!Exist && boomcout<=sumboom) {			
			if(boomcout%boom == 0) {
				boomImg = new ImageIcon("images/blast/blast_"+3+".png").getImage();			
			}
			boomcout++;			
		}
	}
}