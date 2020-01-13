//import libraries
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImageProcessing extends JFrame {
	
	//Declare global variables
	private static final long serialVersionUID = 1L;
	
	
	private final JPanel buttonPanel;
	private ImagePanel imagePanel;
	
	private final JButton rsButton;
	private final JButton gsButton;
	private final JButton bsButton;
	private final JButton loadButton;
	private final JButton saveButton;

	public ImageProcessing() {
		
		//The default image file -testing
		try {
			imagePanel = new ImagePanel(ImageIO.read(new File("bee.png")));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//create a GUI with corresponding action listeners
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		//buttons for Scaling
		rsButton = new JButton("Redscale");
		rsButton.addActionListener(new ButtonEvent());
		gsButton = new JButton("Grayscale");
		gsButton.addActionListener(new ButtonEvent());
		bsButton = new JButton("BlueScale");
		bsButton.addActionListener(new ButtonEvent());
		
		loadButton = new JButton("Upload Image");
		loadButton.addActionListener(new ButtonEvent());
		saveButton = new JButton("Save your File");
		saveButton.addActionListener(new ButtonEvent());

		//Add the buttons to the Panel created for buttons
		buttonPanel.add(rsButton);
		buttonPanel.add(gsButton);
		buttonPanel.add(bsButton);	
		buttonPanel.add(loadButton);
		buttonPanel.add(saveButton);

		//set up the panels for button and image container
		getContentPane().add(buttonPanel, BorderLayout.NORTH);	
		getContentPane().add(imagePanel, BorderLayout.CENTER);
	}

	//Class for Eventhandling
	private class ButtonEvent implements ActionListener {

		//Change the color of the current image
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == rsButton) {
				//call the redScale method to change the color of the image to red
				imagePanel.redScale();
				//Show the new red colored image on interface
				imagePanel.repaint();
				
			} else if (e.getSource() == gsButton) {
				//call the convertToGrayscale method to change the color of the image to gray
				imagePanel.convertToGrayscale();
				//Show the new blue colored image on interface
				imagePanel.repaint();
				
			}  else if (e.getSource() == bsButton) {
				//call the blueScale method to change the color of the image to blue
				imagePanel.blueScale();
				//Show the new red colored image on interface
				imagePanel.repaint();
				
				//choose an specific image file from the computer then replace the default image
			} else if (e.getSource() == loadButton) {
				File file = null;
				JFileChooser fc = new JFileChooser("/home");
				//check if filename is an image file
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG, PNG & GIF Images", "jpg", "gif",
						"png");
				fc.setFileFilter(filter);
				int ret = fc.showOpenDialog(null);
				if (ret == JFileChooser.APPROVE_OPTION) {
					file = fc.getSelectedFile();
				}
				if (file != null) {
					//load the file then show it to interface
					imagePanel.loadOriginalImage(file);
					imagePanel.repaint();
				}

				//save the manipulated file to the computer
				//Create a JFileChooser object for choosing an image file in the computer 
			} else if (e.getSource() == saveButton) {
				if (imagePanel.getImage() != null) {
					JFileChooser fc = new JFileChooser("/home");
					fc.setDialogTitle("Save file as JPG");
					int ret = fc.showSaveDialog(null);
					if (ret == JFileChooser.APPROVE_OPTION) {
						File file = fc.getSelectedFile();
						try {
							ImageIO.write(imagePanel.getImage(), "jpg", file);
							//FELHANTERING
						} catch (java.io.FileNotFoundException ae) {
							ae.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		}
	}

	//The program starts here <---> 
	public static void main(String[] args) {
		// start application - create an ip object 
		ImageProcessing ip = new ImageProcessing();
		ip.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ip.setSize(450, 300);
		ip.setVisible(true);
	}
}