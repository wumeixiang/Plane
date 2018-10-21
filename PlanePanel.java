package reply.fancy.danei;


import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class PlanePanel extends JPanel implements Runnable,MouseMotionListener,MouseListener{
	//¿ªÊ¼±³¾°×ø±ê
	int x = 0;
	int y = 0;	
	// Ñ¡Ôñ·É»ú1.¶¨Òå¿ÉÑ¡·É»ú×óÍ¼Æ¬×ø±ê
	int cxleft = 35;
	int cyleft = 390;
	// ¶¨Òå¿ÉÑ¡·É»úÍ¼Æ¬
	static Image cpleftImage;
	// ÓÒ·É»ú
	int cxright = 295;
	int cyright = 390;
	static Image cprightImage;
	//±³¾°½çÃæ
	static Image bgImg;
	//ÊÇ¿ªÊ¼ÓÎÏ·½çÃæ£¬¹â±êÓÐÐÎÌ¬
	boolean startState = true;
	//×Óµ¯
	static Image herobulletImg;
	static Image enemybulletImg;
	//Ó¢ÐÛ×Óµ¯¼¯ºÏ
	ArrayList<Bullet> herobulletArray = new ArrayList<>();
	//µÐÈË×Óµ¯¼¯ºÏ
	ArrayList<Bullet> enemybulletArray = new ArrayList<>();
	// ¶¨ÒåÔÂ±ý¼¯ºÏ
	ArrayList<MoonCake> mooncakes = new ArrayList<>();
	// ¶¨ÒåÁÙÊ±ÔÂ±ý
	MoonCake m;
	static int number;
	//×Óµ¯·¢ËÍ¼ä¸ô¼ÆÊýÆ÷
	int count = 0;
	//Ó¢ÐÛ»ú
	static HeroPlane hero = new HeroPlane();
	//Ó¢ÐÛ»úÊý×éÏÂ±ê£¬ÓÃÓÚÇÐ»»Ó¢ÐÛ»úÍ¼Æ¬Æðµ½¶¯Ì¬Ð§¹û
	int index = hero.index;
	//Ô¤¼ÓÔØ±Ø±¸µÄÍ¼Æ¬
	static {
		//¿ªÊ¼½çÃæ
		bgImg = new ImageIcon("images/GameInterface/interface_1.png").getImage();
		// ¿ÉÑ¡·É»úÍ¼Æ¬
		cpleftImage = new ImageIcon("images/HeroPlane/1_1.png").getImage();
		cprightImage = new ImageIcon("images/HeroPlane/1_2.png").getImage();
		//×Óµ¯
		herobulletImg = new ImageIcon("images/bullet/bullet_1.png").getImage();
		enemybulletImg = new ImageIcon("images/bullet/bullet_7.png").getImage();
	}
	//´óboss
	BigBoss bigboss;
	//µÐ»ú¼¯ºÏ
	ArrayList<EnemyPlane> enemyArray = new ArrayList<>();
	//·ÖÊý
	int score;
	//¹Ø¿¨
	int lever=1;
	//ÉúÃüÍ¼Æ¬
	Image hpImg = new ImageIcon("images/award/award_1.png").getImage();
	
	//Åö×²¼ì²â
	Collision collision = new Collision();
	//Ïß³Ì
	Thread t;
	
	//ÔËÐÐ×´Ì¬
	final static int START = 1;
	final static int PAUSE = 2;
	final static int RUNNING = 3;
	final static int OVER = 4;
	final static int PASS = 5;	//¹ý¹Ø×´Ì¬
	final static int PASS2 = 6;  //¹ý¹ØÖÐ
	final static int THANKS = 7;	//È«²¿Í¨¹Ø£¬ÓÎÏ·½áÊø
	//³õÊ¼×´Ì¬
	int state = START; 
	
	public PlanePanel() {
		// TODO Auto-generated constructor stub
		//³õÊ¼ÎäÆ÷
		Bullet bullet = new Bullet(herobulletImg,hero.x,hero.y,0,4);
		hero.weapon(bullet);
		//Ìí¼ÓÊó±ê¼àÌý
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		//ÓÎÏ·Í¨¹Ø£¬È«²¿½áÊø
		if(state == THANKS) {
			g.drawString("ÓÎÏ·½áÊø£¬·Ç³£¸ÐÐ»£¡£¡£¡£¡£¡£¡£¡", 170, 280);
		}
		//»æÖÆµØÍ¼
		g.drawImage(bgImg, x, y, null);	
		// »æÖÆ¿ÉÑ¡·É»úÍ¼Æ¬
		if(state == START) {
			g.drawImage(cpleftImage, cxleft, cyleft, null);
			g.drawImage(cprightImage, cxright, cyright, null);
		}
		//»æÖÆÓ¢ÐÛ·É»ú
		if(!startState) {
			//»­Ó¢ÐÛ»ú
			index = index == 0 ? 1 : 0;
			g.drawImage(hero.heroImg[index], hero.x, hero.y, null);
			g.setColor(Color.red);
			//»æÖÆ·ÖÊý
			g.drawString("·Ö Êý£º"+score, 10, 20);
			//»æÖÆ¹Ø¿¨
			g.drawString("µ±Ç°¹Ø¿¨£º"+lever, 170, 20);
			//»æÖÆÑªÌõ
			g.drawString("Ñª Á¿£º"+hero.hp, 330, 20);
			//»æÖÆ´óÕÐÊýÁ¿
			g.drawString("´ó ÕÐ£º"+hero.ultinum, 330, 40);
			//»æÖÆbossÑªÌõ
			if(bigboss != null) {
				for(int i=0; i<bigboss.hp*2; i+=2)
					g.drawString("|", bigboss.x+i, bigboss.y-10);
			}
			//»æÖÆÓ¢ÐÛ×Óµ¯
			for(int i=0; i<herobulletArray.size(); i++) {
				Bullet bullet = herobulletArray.get(i);
				bullet.drawBullet(g);
			}
			//»æÖÆµÐ»ú×Óµ¯
			for(int i=0; i<enemybulletArray.size(); i++) {
				Bullet bullet = enemybulletArray.get(i);
				bullet.drawBullet(g);
			}
			//»æÖÆµÐ»ú
			for(int i=0; i<enemyArray.size(); i++) {
				EnemyPlane enemy = enemyArray.get(i);
				enemy.drawPlane(g);
			}
			//»æÖÆboss
			if(bigboss != null) {
				bigboss.drawPlane(g);
				if(!bigboss.Exist) {
					hero.drawString(g);
				}
			}
			//»æÖÆ´óÕÐ
			hero.ulti.drawUlti(g);
			// »æÖÆÔÂ±ýÍ¼Æ¬
			for (int i = 0; i < mooncakes.size(); i++) {
				MoonCake mooncake = mooncakes.get(i);
				mooncake.drawMoonCake(g);
			}
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			//È«²¿Í¨¹Ø£¬ÓÎÏ·½áÊø
			if(state == THANKS) {
				bgImg = new ImageIcon("images/plane1.png").getImage();
				repaint();
				break;
			}
			//½øÈë¹Ø¿¨¶¯»­£¬·É»ú·ÉÈëÆÁÄ»
			if(state==PASS){
				hero.fly();
				if(hero.y >= 450 && hero.y <= 455) {
					state = RUNNING;
					startState = false;
				}
			}
			//Í¨¹Ø¶¯»­£¬·É»ú´ÓÆÁÄ»·É³ö
			if(state == PASS2) {
				if(hero.y>=-100){
					System.out.println(hero.y);
					hero.fly();
				}
				else if(hero.y<-100){
					state = PASS;
					hero.setLocation();
					//¹§Ï²Í¨¹Ø
					if(lever > 3);
						state = THANKS;
					//¸Ä±ä±³¾°ÎªÓÎÏ·µØÍ¼
					lever++;
					bgImg = new ImageIcon("images/background/background_"+lever+".png").getImage();
					y = 600-6000;	//Ãæ°å³¤¶È-µØÍ¼³¤¶È
					repaint();
				}
			}
			if(state == RUNNING) {
				//ÏÞÖÆ×Óµ¯·¢Éä¼ä¸ô
				if(count%25 == 0) 
					herobulletArray.add(creatBullet());
				if(count < 1000) 
					count++;
				else
					count = 0;
				//´´½¨Ìí¼ÓµÐ»ú
				if(count%90 == 0 && bigboss == null) {
					enemyArray.add(creatEnemy());
				}
				//´´½¨´óboss
				if(y == -100)
					createBigboss();
				// ´´½¨ÔÂ±ý
				if (8000%(y-1) == 1000) {
					mooncakes.add(new MoonCake());
				}

				/****************ÒÆ¶¯****************/
				//µØÍ¼ÒÆ¶¯
				if(y < 0) 
					y++;
				//´óbossÒÆ¶¯
				else if(y == 0) {
					bigboss.move();
					//´´½¨boss×Óµ¯
					if(count%25 == 0 && bigboss.Exist)
						enemybulletArray.add(creatBossBullet(bigboss));
				}
				//´óÕÐÒÆ¶¯
				hero.ulti.move();
				//µÐ»úÒÆ¶¯
				for(int i=0; i<enemyArray.size(); i++){
					EnemyPlane enemy = enemyArray.get(i);
					enemy.move();
					//µ±µÐ»úÊýÁ¿Îª2µÄÊ±£¬µÚ2¼Ü·É»úÍùÓÒ·É
					if(i==1)
						enemy.leftmove();
					//µ±µÐ»úÊýÁ¿Îª3µÄÊ±£¬µÚ3¼Ü·É»úÍù×ó·É
					else if(i==2)
						enemy.rightmove();
					//µÐ»ú·¢Éä×Óµ¯
					if(enemy.y >= 99 && enemy.y <=101) {
						enemybulletArray.add(creatEnemyBullet(enemy));
					}
					//³¬³ö´°¿Ú,»ò·¢ÉúÅö×²£¬ÏÈ±¬Õ¨£¬ºóÒÆ³ýµÐ»ú
					if(!enemy.Exist) {
						//µÐ»úÔ­µØ±¬Õ¨
						enemy.boom();
						enemy.speedX = 0;
						enemy.speedY = 0;
						if(enemy.boomcout > enemy.sumboom) {
							score += enemy.score;
							enemyArray.remove(i);
						}
					}
				}
				//Ó¢ÐÛ×Óµ¯ÒÆ¶¯
				for(int i=0; i<herobulletArray.size(); i++){
					Bullet bullet = herobulletArray.get(i);
					bullet.move();
					//³¬³ö´°¿Ú£¬ÒÆ³ý×Óµ¯
					if(!bullet.Exist) 
						herobulletArray.remove(i);
				}
				//µÐ»ú×Óµ¯ÒÆ¶¯
				for(int i=0; i<enemybulletArray.size(); i++) {
					Bullet bullet = enemybulletArray.get(i);
					bullet.enemyMove();
					//³¬³ö´°¿Ú£¬ÒÆ³ý×Óµ¯
					if(!bullet.Exist) 
						enemybulletArray.remove(i);
				}
				// ÔÂ±ýÒÆ¶¯ºÍÅö×²¼ì²â
				for (int i = 0; i < mooncakes.size(); i++) {
					MoonCake mooncake = mooncakes.get(i);
					mooncake.moveMoonCake();
					// ÒÆ³ýÎÞÐ§ÔÂ±ý
					boolean flag = collision.hAndMCollision(hero, mooncake);
					if (flag) {
						//1¼ÓÑª¼Ó´óÕÐ£¬2-4»»×Óµ¯
						if (mooncake.hp == 1) {
							if (hero.hp < 5) {
								hero.hp += 1;
								hero.ultinum++;
							}
						}
						if (mooncake.hp == 2) {
							hero.bullet.setImg(new ImageIcon("images/bullet/bullet_2.png").getImage());
						}
						if (mooncake.hp == 3) {
							hero.bullet.setImg(new ImageIcon("images/bullet/bullet_5.png").getImage());
						}
						if (mooncake.hp == 4) {
							hero.bullet.setImg(new ImageIcon("images/bullet/bullet_7.png").getImage());
						}
						mooncake.isExist = false;
					}
					if (!mooncake.isExist) 
						mooncakes.remove(i);
				}
								
				/******************Åö×²¼ì²â******************/
				//ÅÐ¶ÏÓ¢ÐÛ»úÊÇ·ñºÍ´óbossÏà×²
				if(bigboss != null) {
					//ÅÐ¶Ï×Óµ¯ÊÇ·ñºÍ´óbossÏà×²
					for(int i=0; i<herobulletArray.size(); i++){
						Bullet bullet = herobulletArray.get(i);
						boolean flag = collision.beCollision(bullet, bigboss);
						if(flag && bigboss.Exist) {
							bigboss.hp--;
							if(bigboss.hp <= 0)
								bigboss.Exist = false;
							bullet.Exist = false;
							continue;
						}
					}
					if(collision.ehCollision(bigboss,hero) && bigboss.Exist) {
						hero.hp--;
						bigboss.hp--;					
					}
				}
				for(int j=0; j<enemyArray.size(); j++){
					EnemyPlane enemy = enemyArray.get(j);
					//ÅÐ¶ÏÓ¢ÐÛ»úÊÇ·ñºÍµÐ»úÏà×²
					boolean ehflag = collision.ehCollision(enemy,hero);
					if(ehflag && enemy.boomcout == enemy.boom) {
						hero.hp--;
						enemy.Exist = false;
						continue;
					}
					//ÅÐ¶Ï×Óµ¯ÊÇ·ñºÍµÐ»úÏà×²
					for(int i=0; i<herobulletArray.size(); i++){
						Bullet bullet = herobulletArray.get(i);
						boolean flag = collision.beCollision(bullet, enemy);
						if(flag && enemy.Exist) {
							enemy.hp--;
							if(enemy.hp == 0)
								enemy.Exist = false;
							bullet.Exist = false;
							continue;
						}
					}
					//´óÕÐºÍµÐ»úÅö×²
					if(hero.ulti.ulti) {
						if(collision.ulenemyCollision(hero.ulti, enemy))
							enemy.Exist = false;
					}
				}
				//µÐÈË×Óµ¯ºÍÓ¢ÐÛÅö×²
				for(int i=0; i<enemybulletArray.size(); i++) {
					Bullet bullet = enemybulletArray.get(i);
					if(collision.ebhCollision(bullet,hero)) {
						bullet.Exist = false;
						hero.hp--;
					}
					//µÐÈË×Óµ¯ºÍ´óÕÐÅö×²
					if(hero.ulti.ulti) {
						if(collision.ulbulCollision(hero.ulti, bullet))
							bullet.Exist = false;
					}
				}
				
				/***********************************/
				//´ò°Üboss£¬½øÈëÏÂÒ»¹Ø
				if(bigboss!=null) {
					if(!bigboss.Exist) {
						//µÐ»úÔ­µØ±¬Õ¨
						bigboss.boom();
						bigboss.speedX = 0;
						if(bigboss.boomcout > bigboss.sumboom) {
							score += bigboss.score;
							state = PASS2;
							enemybulletArray.clear();
							herobulletArray.clear();
							bigboss = null;
							repaint();
						}	
					}
				}
				
				//ÓÎÏ·½áÊø£¬ÒÆ³ýÓ¢ÐÛ»ú
				if (hero.hp <= 0) { // Ó¢ÐÛ»úÏú»Ù£¬Ó¢ÐÛ»ú¸Ä±äÎª±¬Õ¨Í¼Æ¬£¬
					// ¸Ä±ä±³¾°Í¼Æ¬ÒÔ¼°×ø±ê
					bgImg = new ImageIcon("images/GameInterface/jeimian_2.png").getImage();
					x = 0;
					y = 0;
					// ¸Ä±äÓ¢ÐÛ»úÍ¼Æ¬
					hero.heroImg[index] = new ImageIcon("images/blast/blast_3.png").getImage();
					hero.setImg(hero.heroImg[1], hero.heroImg[index]);
//					hero.heroImg[0] = hero.heroImg[1] = hero.heroImg[index];
					//ÒÆ³ýboss
					bigboss = null;
					//ÓÎÏ·½áÊø
					state = OVER;
					repaint();
					t.stop();
				}
			}
			//ÐÝÃß
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//ÖØ»æ
			repaint();
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//¿ªÊ¼½çÃæ
		if(state == START) {
			//×ó·É»ú
			if (startState && e.getX() >= 35 && e.getX() <= 95 && e.getY() >= 390 && e.getY() <= 430) {
				cpleftImage = new ImageIcon("images/HeroPlane/1_1_1.png").getImage();
				cprightImage = new ImageIcon("images/HeroPlane/1_2.png").getImage();
				// Ó¢ÐÛ»úÍ¼Æ¬
				Image img1 = new ImageIcon("images/HeroPlane/1.png").getImage();
				Image img2 = new ImageIcon("images/HeroPlane/11.png").getImage();
				hero.setImg(img1, img2);
				repaint();
			}
			//ÓÒ·É»ú
			if(startState && e.getX() >= 295 && e.getX() <= 330 && e.getY() >= 390 && e.getY() <= 430) {
				cprightImage = new ImageIcon("images/HeroPlane/1_2_2.png").getImage();
				cpleftImage = new ImageIcon("images/HeroPlane/1_1.png").getImage();
				Image img1 = new ImageIcon("images/HeroPlane/plane_3.png").getImage();
				Image img2 = new ImageIcon("images/HeroPlane/plane_3.png").getImage();
				hero.setImg(img1, img2);
				repaint();
			}
			//µã»÷¿ªÊ¼ºó½øÈë¹Ø¿¨×´Ì¬
			if(startState && e.getX() >= 130 && e.getX() <= 260 && e.getY() >= 390 && e.getY() <= 430) {
				state = PASS;
				hero.setLocation();
				startState = false;
				bgImg = new ImageIcon("images/background/background_"+lever+".png").getImage();
				y = 600-6000;	//Ãæ°å³¤¶È-µØÍ¼³¤¶È
//				y = -500;	//ÎªÁË¿ìËÙµ½´ïboss£¬²âÊÔboss
				repaint();
				//¿ªÆôÏß³Ì
				t = new Thread(this);
				t.start();
			}
			//·É»ú·Éµ½500µÄÎ»ÖÃ,ÓÎÏ·¿ªÊ¼
			if(hero.y >= 500 && hero.y <= 510) {
					state = RUNNING;
			}
			//Ìí¼ÓÒôÀÖ
		/*	File file = new File("music/game_music2.wav");
			try {
				AudioClip sound = Applet.newAudioClip(file.toURL());
				sound.loop();
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}*/
		}
		//ÓÒ¼üÔÝÍ£ºÍÔËÐÐ
		if (!startState && e.getButton() == MouseEvent.BUTTON3 && state != OVER) {
			state = state == PAUSE ? RUNNING : PAUSE;
		}
		//Êó±êÖÐ¼ü·Å´óÕÐ
		if (!startState && e.getButton() == MouseEvent.BUTTON2 && state != OVER && hero.ultinum > 0) {
			hero.ulti.ulti = true;
			hero.ultinum--;
			if(bigboss != null)
				bigboss.hp -= 20;
		}
		else if(state == OVER){
			//Èç¹ûÊÇ½áÊø×´Ì¬
			state = START;
			startState = true;
			enemyArray.clear();
			herobulletArray.clear();
			enemybulletArray.clear();
			score = 0;
			hero.hp = 5;
			hero.ultinum = 3;
			lever = 1;
			bgImg = new ImageIcon("images/GameInterface/interface_1.png").getImage();
			Image img1 = new ImageIcon("images/1.png").getImage();
			Image img2 = new ImageIcon("images/2.png").getImage();
			hero.setImg(img1, img2);
			repaint();
		}
	}
	
	//´´½¨Ó¢ÐÛÕýÔÚ·¢ÉäµÄ×Óµ¯
	public Bullet creatBullet() {
		herobulletImg = hero.bullet.bulletImg;
		//¸Ä±ä×Óµ¯Î»ÖÃÎªÓ¢ÐÛ»úÕýÖÐ¼ä
		int btx = hero.x+hero.width/2-hero.bullet.width/2;
		int bty= hero.y;
		int speedX = hero.bullet.speedX;
		int speedY = hero.bullet.speedY;
		Bullet bullet = new Bullet(herobulletImg,btx,bty,speedX,speedY);
		return bullet;
	}
	//´´½¨µÐ»ú×Óµ¯
	public Bullet creatEnemyBullet(EnemyPlane enemy) {
		//xÖá·½ÏòµÐ»úºÍÓ¢ÐÛ¾àÀë£¬yÖá·½ÏòµÐ»úºÍÓ¢ÐÛ
		int dx = hero.x - enemy.x;
		int dy = hero.y - enemy.y;
		int absx = dx;
		int absy = dy;
		//×Óµ¯ËÙ¶È
		int speedX;
		int speedY;
		//±ÜÃâ³ý0´íÎó
		if(dy == 0) 
			dy = 1;
		if(Math.abs(absy) >= Math.abs(absx)) {
			speedX = 3*dx/dy;
			speedY = 3;
		}
		else {
			speedY = dx/dy/3;
			speedX = 1;
		}
		//±æ±ð×Óµ¯µÄ·½Ïò
		if(dx<0 && dy<0) {//Íù×óÉÏ·É
			speedX *= -1;
		}
		else if(dx>0 && dy<0) {//ÍùÓÒÉÏ·É
			speedY *= -1;
		}
		//×Óµ¯·¢ÉäµÄ³õÊ¼×ø±ê
		int ex = enemy.x+enemy.width/2;
		int ey = enemy.y+enemy.heihgt/2;
		
		Bullet bullet = new Bullet(enemybulletImg,ex,ey,speedX,speedY);
		return bullet;
	}
	//´´½¨bossÕýÔÚ·¢ÉäµÄ×Óµ¯
	public Bullet creatBossBullet(BigBoss enemy) {
		//¸Ä±ä×Óµ¯Î»ÖÃÎªbossÕýÖÐ¼ä
		int btx = enemy.x+enemybulletImg.getWidth(null);
		int bty= enemy.y+enemybulletImg.getHeight(null);
		int speedX = (int) (Math.random()*3);//x·½ÏòËÙ¶È
		if(btx >= hero.x)	//¸Ä±ä×Óµ¯×óÓÒ·½Ïò
			speedX *= -1;
//		int speedY = (int) (Math.random()*3+1);//y·½ÏòËÙ¶È
		Bullet bullet = new Bullet(enemybulletImg,btx,bty,speedX,3);
		return bullet;
	}
	//´´½¨µÐ»ú
	public EnemyPlane creatEnemy() {
		int num = (int) (Math.random()*5+2);
		Image eImg = new ImageIcon("images/LittlePlane/plane"+num+".png").getImage();
		int ex = (int) (Math.random()*(400-80+1));
		int ey = (int) (Math.random()*(-50)-30);
		int speedX = (int) (Math.random()*1+2);
		int speedY = 2;
		int esh = (int) (Math.random()*4+1);	//·ÖÊýºÍÑªÁ¿
		EnemyPlane enemy = new EnemyPlane(eImg, ex, ey, speedX,speedY, esh, esh);
		return enemy;
	}
	//´´½¨´óboss
	public void createBigboss() {
		Image image = new ImageIcon("images/BossPlane/plane_"+lever+".png").getImage();
		int bx = 200-image.getWidth(null)/2;
		int by = -100;
		int speedX = 0;
		int speedY = 1;
		int esh = 50;	//·ÖÊýºÍÑªÁ¿
		bigboss = new BigBoss(image,bx,by,speedX,speedY,esh,esh);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		//ÔÚ¿ªÊ¼ÓÎÏ·½çÃæµÄ¿ªÊ¼ÓÎÏ·Ñ¡Ôñ¿òÉÏ£¬¸Ä±äÊó±êÑùÊ½
		if(startState && e.getX() >= 130 && e.getX() <= 260 && e.getY() >= 390 && e.getY() <= 430) {
			setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		else {
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		if(state == RUNNING) {
			//Ó¢ÐÛ»úÎ»ÖÃÎªÊó±êµ±Ç°Î»ÖÃ
			hero.x = e.getX()-hero.width/2;
			hero.y = e.getY()-hero.height/2;
		}
	}
}
