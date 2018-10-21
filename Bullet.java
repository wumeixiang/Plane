package reply.fancy.danei;


import java.awt.Graphics;
import java.awt.Image;

//子弹类
public class Bullet {
	//子弹图片
	Image bulletImg;
	//子弹坐标
	int x;
	int y;
	//子弹大小
	int width;
	int height;
	//子弹速度
	int speedX;
	int speedY;
	//子弹状态，是否存在，如果超出界面，就不存在
	boolean Exist = true;
	
	public Bullet(Image bulletImg,int x,int y,int speedX,int speedY) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.bulletImg = bulletImg;
		this.speedX = speedX;
		this.speedY = speedY;
		this.width = bulletImg.getWidth(null);
		this.height = bulletImg.getHeight(null);
	}
	//改变图片
	public void setImg(Image img) {
		bulletImg = img;
		this.width = img.getWidth(null);
		this.height = img.getHeight(null);
	}
	//绘制子弹
	public void drawBullet(Graphics g) {
		g.drawImage(bulletImg, x, y, null);
	}
	//英雄机子弹移动
	public void move() {
		y -= speedY;
		//超出屏幕，就消失
		if(y < -bulletImg.getHeight(null))
			Exist = false;
	}
	//敌机子弹移动
	public void enemyMove() {
		x += speedX;
		y += speedY;
	}
}
