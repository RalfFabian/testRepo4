import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MainFrameImageBox extends JFrame{
	
	private JButton btnOpen;
	private JList<String> imageList;
	private DefaultListModel<String> imageListModel;
	private ImagePanel mainPanel;
	private ImagePanel imagePanel;
	private JFileChooser fileChooser;
	
	File[] files;
	File dir;
	
	
	public MainFrameImageBox() {
		super("Image Box");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800,600);
		
		btnOpen = new JButton("Open...");
		imageList = new JList<String>();
		imageListModel = new DefaultListModel<>();
		imageList.setModel(imageListModel);
		imageList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		files = null;
		dir = null;
		
		fileChooser = new JFileChooser(".");
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		
		mainPanel = new ImagePanel();
		mainPanel.setLayout(null);
		
		btnOpen.setBounds(20, 20, 200, 30);
		mainPanel.add(btnOpen);
		
		JScrollPane sp = new JScrollPane(imageList);
		sp.setBounds(20, 60, 200, 400);
		mainPanel.add(sp);
		
//		imagePanel = new ImagePanel();
//		imagePanel.loadImage("./images/Desert.jpg");
//		imagePanel.setFitToScreen(true);
//		imagePanel.setAspectRatio(true);
		

		mainPanel.setFitToScreen(false);
		mainPanel.setAspectRatio(true);
		
//		imagePanel.setBounds(250, 20, 500, 400);
//		mainPanel.add(imagePanel);
		
		setContentPane(mainPanel);
		
		btnOpen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				onOpen();
				
			}
		});
		
		imageList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				onItemSelect();
				
			}
		});
		
		setVisible(true);
	}
	
	private void onOpen() {
		if(fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			dir = fileChooser.getSelectedFile();
			files = dir.listFiles();
			imageListModel.clear();
			
			for (int i = 0; i < files.length; i++) {
				if(files[i].isFile() && files[i].getName().toLowerCase().endsWith(".jpg")) {
					imageListModel.addElement(files[i].getName());
				}
			}
		}
	}

	private void onItemSelect() {
		
		if(imageListModel.isEmpty())
			return;
		int index = imageList.getSelectedIndex();
		
//		imagePanel.loadImage(files[index].getAbsolutePath());
//		imagePanel.setImageName(files[index].getName());
		
		mainPanel.loadImage(files[index].getAbsolutePath());
		mainPanel.setImageName(files[index].getName());
	}
}
