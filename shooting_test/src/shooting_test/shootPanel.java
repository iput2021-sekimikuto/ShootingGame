package shooting_test;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class shootPanel extends JPanel{
	public BufferedImage image;
	
	public shootPanel() {
		super();
		this.image = new BufferedImage(900, 900, BufferedImage.TYPE_INT_RGB);
	}
	
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		//描画した結果を表示する部分を作成
		g.drawImage(image, 0, 0, this);//x0y0の場所に表示、observerはthisを設定
	}
	
	//結果を表示する関数
	public void draw() {
		this.repaint();
	}
}
