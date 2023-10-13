package shooting_test;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class frame extends JFrame {
	public shootPanel panel;//shootPanelクラスを呼び出す変数を生成
	
	public frame() {
		
		panel = new shootPanel();//panel変数に入れる
		
		this.add(panel);//ここのthisはJFrameを指している。よってJFrameにshootPanelクラスをaddすることで画面に表示できるようになる
		
		this.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosed(WindowEvent e) {
				super.windowClosed(e);
				shoot.loop = false;
			}
			
		});
		
		this.addKeyListener(new Keyboard());//キーボード入力を受け取るインスタンスを生成
		
		
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setTitle("shooting!");
		this.pack();
		this.setSize(900, 900);
		this.setLocationRelativeTo(null);//カッコ内をnullにすることで中央に表示できる
		this.setResizable(false);
		this.setVisible(true);
	}
}
