/**
*
* Written by Bowen Tao
*
* */


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;



public class tetris extends JPanel implements KeyListener, ActionListener{
	JButton start=new JButton("Start");
	JButton end=new JButton("End");
	JButton exit=new JButton("Exit");
	JLabel label=new JLabel();
	JDialog dialog=new JDialog();
	int score,speed,brick;
	boolean run=false;
	Random random=new Random();
	Coor[] brick1 =new Coor[4];
	Coor[] brick2=new Coor[4];
	int[][] array=new int[10][18];
	Timer timer;
	
	public tetris(){
		this.setLayout(new FlowLayout(FlowLayout.RIGHT));
		add(start);
		add(end);
		start.addActionListener(this);
		end.addActionListener(this);
		exit.addActionListener(this);
		this.addKeyListener(this);
		
		dialog.setLayout(new GridLayout(2,1));
		dialog.setLocation(200, 200);
		dialog.setSize(300,300);
		dialog.add(exit);
		dialog.add(label);
		
		//initialization
		for (int i=0;i<4;i++){
			brick1[i]=new Coor();
		}
		
		for (int i=0;i<4;i++){
			brick2[i]=new Coor();
		}
		
		for (int i=0;i<10;i++){
			for (int j=0;j<18;j++){
				array[i][j]=0;
			}
		}
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawRect(9, 10, 200, 360);
		g.drawString("Score: "+score, 220, 60);
		g.drawLine(220, 65, 360, 65);
		g.drawString("Speed: "+speed, 220, 90);
		g.drawLine(220, 95, 360, 95);
		g.drawString("Next Brick", 220, 120);
		
		if (run){
			g.setColor(Color.MAGENTA);
			for (int i=0;i<4;i++){
				g.fillRect(10+brick1[i].x*20, 10+brick1[i].y*20, 20, 20);
			}
			//next brick
			g.setColor(Color.BLUE);
			for (int i=0;i<4;i++){
				g.fillRect(220+brick2[i].x*20, 180+brick2[i].y*20, 20, 20);
			}
			
			//save brick
			for (int i=0;i<10;i++){
				for (int j=0;j<18;j++){
					if (array[i][j]==1){
						g.fillRect(10+i*20, 10+j*20, 20, 20);
					}
				}
			}
			
		}
	}
	
	public boolean Brick(){
		switch (brick) {
		case 0:
			brick1[0].x=1;brick1[0].y=0;
			brick1[1].x=2;brick1[1].y=0;
			brick1[2].x=3;brick1[2].y=0;
			brick1[3].x=4;brick1[3].y=0;
			break;

		case 1:
			brick1[0].x=2;brick1[0].y=0;
			brick1[1].x=2;brick1[1].y=1;
			brick1[2].x=2;brick1[2].y=2;
			brick1[3].x=3;brick1[3].y=2;
			break;
			
		case 2:
			brick1[0].x=3;brick1[0].y=0;
			brick1[1].x=3;brick1[1].y=1;
			brick1[2].x=3;brick1[2].y=2;
			brick1[3].x=2;brick1[3].y=2;
			break;
			
		case 3:
			brick1[0].x=2;brick1[0].y=0;
			brick1[1].x=3;brick1[1].y=0;
			brick1[2].x=2;brick1[2].y=1;
			brick1[3].x=3;brick1[3].y=1;
			break;
	
		case 4:
			brick1[0].x=3;brick1[0].y=0;
			brick1[1].x=2;brick1[1].y=1;
			brick1[2].x=3;brick1[2].y=1;
			brick1[3].x=4;brick1[3].y=1;
			break;
	
		case 5:
			brick1[0].x=2;brick1[0].y=0;
			brick1[1].x=3;brick1[1].y=0;
			brick1[2].x=3;brick1[2].y=1;
			brick1[3].x=4;brick1[3].y=1;
			break;
	
		case 6:
			brick1[0].x=4;brick1[0].y=0;
			brick1[1].x=3;brick1[1].y=0;
			brick1[2].x=3;brick1[2].y=1;
			brick1[3].x=2;brick1[3].y=1;
			break;
		}
		

		for (int i=0;i<4;i++){
			if (maxLimite(brick1[i].x, brick1[i].y)){
				return false;
			}
		}
		
		return true;
	}
	
	public void nextBrick(){
		switch (brick) {
		case 0:
			brick2[0].x=1;brick2[0].y=0;
			brick2[1].x=2;brick2[1].y=0;
			brick2[2].x=3;brick2[2].y=0;
			brick2[3].x=4;brick2[3].y=0;
			break;

		case 1:
			brick2[0].x=2;brick2[0].y=0;
			brick2[1].x=2;brick2[1].y=1;
			brick2[2].x=2;brick2[2].y=2;
			brick2[3].x=3;brick2[3].y=2;
			break;
			
		case 2:
			brick2[0].x=3;brick2[0].y=0;
			brick2[1].x=3;brick2[1].y=1;
			brick2[2].x=3;brick2[2].y=2;
			brick2[3].x=2;brick2[3].y=2;
			break;
			
		case 3:
			brick2[0].x=2;brick2[0].y=0;
			brick2[1].x=3;brick2[1].y=0;
			brick2[2].x=2;brick2[2].y=1;
			brick2[3].x=3;brick2[3].y=1;
			break;
	
		case 4:
			brick2[0].x=3;brick2[0].y=0;
			brick2[1].x=2;brick2[1].y=1;
			brick2[2].x=3;brick2[2].y=1;
			brick2[3].x=4;brick2[3].y=1;
			break;
	
		case 5:
			brick2[0].x=2;brick2[0].y=0;
			brick2[1].x=3;brick2[1].y=0;
			brick2[2].x=3;brick2[2].y=1;
			brick2[3].x=4;brick2[3].y=1;
			break;
	
		case 6:
			brick2[0].x=4;brick2[0].y=0;
			brick2[1].x=3;brick2[1].y=0;
			brick2[2].x=3;brick2[2].y=1;
			brick2[3].x=2;brick2[3].y=1;
			break;
		}
	}
	
	public boolean maxLimite(int x, int y){
		if (x<0 || x>=10 || y<0 || y>=18){
			return false;
		}
		if (array[x][y]==1){
			return false;
		}
		return true;
	}
	
	public boolean minLimite(int x, int y){
		for (int i=0;i<4;i++){
			if(!maxLimite(brick1[i].x+x, brick1[i].y+y)){
				return false;
			}
		}
		return true;
	}
	
	
	
	public void move(int x, int y){
		if (minLimite(x, y)){
			for (int i=0;i<4;i++){
				brick1[i].x+=x;
				brick1[i].y+=y;
			}
			repaint();
		}
	}
	
	public void moveDown(){
		if (minLimite(0, 1)){
			for (int i=0;i<4;i++){
				brick1[i].y+=1;
			}
			repaint();
		}else{
			for (int i=0;i<4;i++){
				array[brick1[i].x][brick1[i].y]=1;
			}
			int line=deleteLine();
			if (line!=0){
				score+=10*line*line;
			}
			if (score>10 && score % 100==0){
				speed++;
				if (speed>9){
					speed=9;
				}
			}
			if (Brick()){
				timer.stop();
				dialog.setVisible(true);
				label.setText("Game Over! Your Score is: "+score);
			}else{
				brick=random.nextInt(7);
				nextBrick();
				timer.start();
			}
		}
	}
	
	public int deleteLine(){
		int line=0;
		for (int i=0;i<18;i++){
			int j;
			for (j=0;j<10;j++){
				if (array[j][i]==0){
					break;
				}
			}
			if (j==10){
				line++;
				if (i!=0){
					for (int m=i-1;m>0;m--){
						for (int n=0;n<10;n++){
							array[n][m+1]=array[n][m];
						}
					}
					for (int k=0;k<10;k++){
						array[0][k]=0;
					}
				}
			}
		}
		repaint();
		return line;
	}
	
	public void spin(){
		Coor[] c=new Coor[4];
		for (int i=0;i<4;i++){
			c[i]=new Coor();
			c[i].x=brick1[i].x;
			c[i].y=brick1[i].y;
		}
		int x=(c[0].x+c[1].x+c[2].x+c[3].x)/4;
		int y=(c[0].y+c[1].y+c[2].y+c[3].y)/4;
		for (int i=0;i<4;i++){
			c[i].x=x+y-brick1[i].y;
			c[i].y=y-x+brick1[i].x;
		}
		for (int i=0;i<4;i++){
			if (!maxLimite(c[i].x, c[i].y)){
				return;
			}
		}
		for (int i=0;i<4;i++){
			brick1[i].x=c[i].x;
			brick1[i].y=c[i].y;
		}
		repaint();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource()==start){
			if (arg0.getActionCommand().equals("Start")){
				start.setText("Restart");
				run=true;
				requestFocus();
				if (Brick()){
					return;
				}else{
					timer=new Timer(1000-(100*speed), new myTimer());
					timer.start();
					brick=random.nextInt(7);
					nextBrick();
					repaint();
				}
			}else{
				start.setText("Start");
				run=false;
			}
			if (arg0.getActionCommand().equals("Restart")){
				for(int i=0;i<10;i++){
					for (int j=0;j<18;j++){
						array[i][j]=0;
					}
				}
			}
			
			
		}
		if (arg0.getSource()==exit){
			score=0;
			speed=0;
			for(int i=0;i<10;i++){
				for (int j=0;j<18;j++){
					array[i][j]=0;
				}
			}
			run=false;
			start.setText("Start");
			dialog.setVisible(false);
			repaint();
		}
		if (arg0.getSource()==end){
			System.exit(0);
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if (run){
			switch (arg0.getKeyCode()) {
				case KeyEvent.VK_UP:
					spin();
					break;

				case KeyEvent.VK_DOWN:
					moveDown();
					break;
					
				case KeyEvent.VK_LEFT:
					move(-1, 0);
					break;
					
				case KeyEvent.VK_RIGHT:
					move(1, 0);
					break;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public class myTimer implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (run){
				moveDown();
			}
		}

	}
	
}
