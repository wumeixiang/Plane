package reply.fancy.danei;


import javax.swing.JFrame;

/*
 * ÐÂ·É»ú´óÕ½£¬¿ÉÒÔÓÐÐÂµÄÔöÒæÎïÆ·
 */
public class PlaneFrame extends JFrame{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new PlaneFrame();
	}
	
	public PlaneFrame() {
		// TODO Auto-generated constructor stub
		//±êÌâ
		this.setTitle("·É»ú´óÕ½");
		//³ß´ç
		this.setBounds(400, 100, 400, 600);
		//¹Ì¶¨´óÐ¡
		this.setResizable(false);
		//Ä¬ÈÏÍË³ö
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		//Ìí¼Ó»­²¼
		PlanePanel panel = new PlanePanel();
		this.add(panel);
		
		//´°¿Ú¿É¼û
		this.setVisible(true);
	}
}
