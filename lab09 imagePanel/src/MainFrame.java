import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class MainFrame extends JFrame{
	
	public MainFrame() {
		super("Image Frame");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800,600);
		
		
		ImagePanel imagePanel = new ImagePanel();
		imagePanel.loadImage("./images/Desert.jpg");
		imagePanel.setFitToScreen(false);
		imagePanel.setAspectRatio(true);
		
		setContentPane(new JScrollPane(imagePanel));
		setVisible(true);
	}

	public static void main(String[] args) {
		new MainFrame();
	}
}
