package reply.fancy.danei;

/*
 * 碰撞检测
 */
public class Collision {
	
	//检测敌机和英雄机的碰撞
	public boolean ehCollision(EnemyPlane enemy,HeroPlane hero) {
		//---------英雄机--------
		//左上角
		int xl = hero.x;
		int yu = hero.y;
		//右上角
		int xr = xl + hero.width;
		//左下角
		int yd = yu + hero.height;
		//---------敌机-------
		//左上角
		int exl = enemy.x;
		int eyu = enemy.y;
		//左下角
		int eyd = enemy.y + enemy.heihgt;
		//右下角
		int exr = enemy.x + enemy.width;
		//角互相在对方里
		if(xl>exl && xl<exr && yu>eyu && yu<eyd || xr>exl && xr<exr && yu>eyu && yu<eyd || 
				xl>exl && xl<exr && yd>eyu && yd<eyd || xr>exl && xr<exr && yd>eyu && yd<eyd) {
			return true;
		}
		else
			return false;
	}
	//检测大boss和英雄机的碰撞
	public boolean ehCollision(BigBoss enemy,HeroPlane hero) {
		//---------英雄机--------
		//左上角
		int xl = hero.x;
		int yu = hero.y;
		//右上角
		int xr = xl + hero.width;
		//左下角
		int yd = yu + hero.height;
		//---------敌机-------
		//左上角
		int exl = enemy.x;
		int eyu = enemy.y;
		//左下角
		int eyd = enemy.y + enemy.height;
		//右下角
		int exr = enemy.x + enemy.width;
		//角互相在对方里
		if(xl>exl && xl<exr && yu>eyu && yu<eyd || xr>exl && xr<exr && yu>eyu && yu<eyd || 
				xl>exl && xl<exr && yd>eyu && yd<eyd || xr>exl && xr<exr && yd>eyu && yd<eyd) {
			return true;
		}
		else
			return false;
	}
	//检测子弹和敌机的碰撞
	public boolean beCollision(Bullet bullet, EnemyPlane enemy) {
		//---------子弹--------
		//上方的中间
		int centerx = bullet.x+bullet.width/2;
		//左上角
		int xl = bullet.x;
		int yu = bullet.y;
		//右上角
		int xr = xl + bullet.width;
		//左下角
		int yd = yu + bullet.height;
		//---------敌机-------
		//左上角
		int exl = enemy.x;
		int eyu = enemy.y;
		//左下角
		int eyd = enemy.y + enemy.heihgt;
		//右下角
		int exr = enemy.x + enemy.width;
		//角互相在对方里
		if(xl>exl && xl<exr && yu>eyu && yu<eyd || xr>exl && xr<exr && yu>eyu && yu<eyd || 
				xl>exl && xl<exr && yd>eyu && yd<eyd || xr>exl && xr<exr && yd>eyu && yd<eyd
				|| centerx>exl && centerx<exr && yu>eyu && yu<eyd) {
			return true;
		}
		else
			return false;
	}
	//检测敌机子弹和英雄相撞
	public boolean ebhCollision(Bullet bullet,HeroPlane hero) {
		//---------子弹--------
		//下方的中间
		int centerx = bullet.x+bullet.width/2;
		//左上角
		int xl = bullet.x;
		int yu = bullet.y;
		//右上角
		int xr = xl + bullet.width;
		//左下角
		int yd = yu + bullet.height;
		//---------敌机-------
		//左上角
		int exl = hero.x;
		int eyu = hero.y;
		//左下角
		int eyd = hero.y + hero.height;
		//右下角
		int exr = hero.x + hero.width;
		//角互相在对方里
		if(xl>exl && xl<exr && yu>eyu && yu<eyd || xr>exl && xr<exr && yu>eyu && yu<eyd || 
			xl>exl && xl<exr && yd>eyu && yd<eyd || xr>exl && xr<exr && yd>eyu && yd<eyd
			|| centerx>exl && centerx<exr && yu>eyu && yu<eyd) {
			return true;
		}
		else
			return false;
	}
	//检测子弹和大boss的碰撞
	public boolean beCollision(Bullet bullet, BigBoss enemy) {
		//---------子弹--------
		//上方的中间
		int centerx = bullet.x+bullet.width/2;
		//左上角
		int xl = bullet.x;
		int yu = bullet.y;
		//右上角
		int xr = xl + bullet.width;
		//左下角
		int yd = yu + bullet.height;
		//---------敌机-------
		//左上角
		int exl = enemy.x;
		int eyu = enemy.y;
		//左下角
		int eyd = enemy.y + enemy.height;
		//右下角
		int exr = enemy.x + enemy.width;
		//角互相在对方里,或者顶端中点在对方里
		if(xl>exl && xl<exr && yu>eyu && yu<eyd || xr>exl && xr<exr && yu>eyu && yu<eyd || 
				xl>exl && xl<exr && yd>eyu && yd<eyd || xr>exl && xr<exr && yd>eyu && yd<eyd
				|| centerx>exl && centerx<exr && yu>eyu && yu<eyd) {
			return true;
		}
		else
			return false;
	}
	//月饼和英雄机相撞
	public boolean hAndMCollision(HeroPlane hero, MoonCake mooncake) {
		int p1x = hero.x-hero.width/2;
		int p1y = hero.y-hero.height/2;
		int p2x = p1x + hero.width;
		int p3y = p1y + hero.height;
		
		int e1x = mooncake.mx;
		int e1y = mooncake.my;
		if(e1x >= p1x && e1x <= p2x && e1y >= p1y && e1y <= p3y) {
			return true;
		}
		return false;

	}
	//大招和敌机碰撞检测
	public boolean ulenemyCollision(Ulti ulti,EnemyPlane enemy) {
		//大招的左右电弧
		int ulx = ulti.lx+ulti.width/2;
		int urx = ulti.rx+ulti.width/2;
		//敌机的左右位置
		int elx = enemy.x;
		int erx = enemy.x+enemy.width;
		
		if(ulx>elx && ulx<erx || urx>elx && urx<erx)
			return true;
		else
			return false;
	}
	//大招和敌人子弹碰撞检测
	public boolean ulbulCollision(Ulti ulti,Bullet bullet) {
		//大招的左右电弧
		int ulx = ulti.lx+ulti.width/2;
		int urx = ulti.rx+ulti.width/2;
		//敌机的左右位置
		int elx = bullet.x;
		int erx = bullet.x+bullet.width;
		
		if(ulx>elx && ulx<erx || urx>elx && urx<erx)
			return true;
		else
			return false;
	}
}
