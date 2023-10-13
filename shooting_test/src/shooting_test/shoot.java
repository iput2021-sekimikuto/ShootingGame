package shooting_test;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class shoot {
	public static frame f;	//frameクラスを呼び出すための変数を作成
	public static boolean loop;
	public static void main(String[] args) {
		f = new frame();
		loop = true;
		
		
	
		Graphics gra = f.panel.image.createGraphics();
		
		//fps
		long startTime = System.currentTimeMillis();
		long fpsTime = 0;
		int fps = 30;
		int FPS = 0;
		int FPSCount = 0;
		
		EnumShootScrean screen = EnumShootScrean.START;
		
		
		//ゲーム画面を作る
		int playerX = 0, playerY = 0;//プレイヤーの座標を取得する
		ArrayList<Bullet> bullets = new ArrayList<>();
		ArrayList<Enemy> enemies = new ArrayList<>();
		
		
		//ゲームのループ部分を作成
		while(loop) {
			if((System.currentTimeMillis() - fpsTime) >= 1000) {
				fpsTime = System.currentTimeMillis();
				FPS = FPSCount;
				FPSCount = 0;
			}
			FPSCount++;
			startTime = System.currentTimeMillis();
			
			gra.setColor(Color.WHITE);
			gra.fillRect(0, 0,1000, 1000);
			
			switch (screen) {
			//スタート画面
				case START:
				gra.setColor(Color.BLACK);
				Font font = new Font("SansSerif", Font.PLAIN, 40);
				gra.setFont(font);
				FontMetrics metrics = gra.getFontMetrics(font);
				gra.drawString("Shooting", 450 - (metrics.stringWidth("Shooting") / 2), 100);
				font = new Font("SansSerif", Font.PLAIN, 20);
				gra.setFont(font);
				metrics = gra.getFontMetrics(font);
				gra.drawString("Press SPACE to Start", 450 - (metrics.stringWidth("Press SPACE to START") / 2), 150);
				if(Keyboard.isKeyPressed(KeyEvent.VK_SPACE)) {
					screen = EnumShootScrean.GAME;

					bullets = new ArrayList<>();
					enemies = new ArrayList<>();
					playerX = 425;
					playerY = 700;
					
				}
				break;

			//ゲーム画面
				case GAME:
					//playerを表示する
					gra.setColor(Color.BLUE);
					gra.fillRect(playerX + 10, playerY, 10, 10);
					gra.fillRect(playerX, playerY + 10, 30, 10);
					
					//弾の可視化
					for(int i = 0; i < bullets.size(); i++) {//拡張for文を使って配列の要素を取得し、要素の数だけループする
						Bullet bullet = bullets.get(i);
						gra.setColor(Color.BLUE);
						gra.fillRect(bullet.x, bullet.y, 5, 3);
						//bullet.x += 10; //x方向に弾を飛ばす
						bullet.y -= 10; //y方向に弾を飛ばす
						if(bullet.y < 0) bullets.remove(bullet);//弾が画面外になったら消す
					}
					
					
					//playerの入力を取得し反映する
					if(Keyboard.isKeyPressed(KeyEvent.VK_D) && playerX<800){//Aキーが押下されたら
						playerX += 10;//playerのX座標をマイナス方向に動かす
					}else if(Keyboard.isKeyPressed(KeyEvent.VK_A) && playerX>100) {
						playerX -= 10;
					}else if(Keyboard.isKeyPressed(KeyEvent.VK_S) && playerY<700) {
						playerY += 10;
					}else if(Keyboard.isKeyPressed(KeyEvent.VK_W) && playerY>0) {//Graphicsライブラリでは左上が0,0になっているため、マイナス方向にすると画面上に移動する
						playerY -= 10;
					}
					
					//射撃できるようにする
					if(Keyboard.isKeyPressed(KeyEvent.VK_SPACE)) {//スペースキーで撃てるようにする
						bullets.add(new Bullet(playerX + 12, playerY));//bullets配列に座標を入れる
						
					}
					
					
					break;
				case GAME_OVER:
					break;
			}
			
			
			//gra.setColor(Color.BLACK);
			//gra.fillRect(100, 100, 100, 100);
			
			gra.setColor(Color.BLACK);
			gra.setFont(new Font("SansSerif", Font.PLAIN, 10));
			gra.drawString(FPS + "FPS", 0, 770);
			
			
			
			f.panel.draw();
			
			try {
				long runTime = System.currentTimeMillis() - startTime;
			    long sleepTime = (1000 / fps) - runTime;
			    
			    if (sleepTime > 0) {
			        Thread.sleep(sleepTime);
			    }
			}catch (InterruptedException e) {
			    e.printStackTrace();
			}
		}
		System.out.println(System.currentTimeMillis() - startTime );
	}
	
	
}
