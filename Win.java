import javax.swing.JFrame;


public class Win extends JFrame{
	public Win(){
		super("Tetris--v1.0");
		this.setLocation(200, 200);
		this.setSize(380, 420);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		tetris tetris =new tetris();
		add(tetris);
	}
	
	public static void main(String[] args){
		new Win();
	}
}
