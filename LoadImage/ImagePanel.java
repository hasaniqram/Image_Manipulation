//import libraries
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private BufferedImage image;

	// contructor to get the copy of the uploaded image
	public ImagePanel(BufferedImage image) {
		this.image = image;
	}

	// Get the image
	public BufferedImage getImage() {
		return image;
	}

	// paint the image
	public void paint(Graphics g) {
		g.drawImage(image, 0, 0, this);
		g.dispose();
	}

	// Method for the new downloaded image file
	public void loadOriginalImage(File file) {
		try {
			image = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//Color the image to red
	public void redScale() {
		if (image == null)
			return;
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				int p = image.getRGB(x, y);

				int a = (p >> 24) & 0xff;
				int r = (p >> 16) & 0xff;

				// set new RGB
				// keeping the r value same as in original
				// image and setting g and b as 0.
				p = (a << 24) | (r << 16) | (0 << 8) | 0;

				image.setRGB(x, y, p);
			}
		}
	}

	//Color the image to blue
	public void blueScale() {
		if (image == null)
			return;
		// convert to blue image
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				int p = image.getRGB(x, y);

				int a = (p >> 24) & 0xff;
				int b = (p >> 16) & 0xff;

				// set new RGB
				// keeping the b value same as in original
				// image and setting r and g as 0.
				p = (a << 24) | (0 << 16) | (0 << 8) | b;

				image.setRGB(x, y, p);
			}
		}
	}

	// color the image to gray
	public void convertToGrayscale() {
		if (image == null)
			return;
		for (int i = 0; i < image.getHeight(); i++) {
			for (int j = 0; j < image.getWidth(); j++) {
				Color imageColor = new Color(image.getRGB(j, i));
				int rgb = (int) (imageColor.getRed() * 0.299) + (int) (imageColor.getGreen() * 0.587)
						+ (int) (imageColor.getBlue() * 0.114);
				Color newColor = new Color(rgb, rgb, rgb);
				image.setRGB(j, i, newColor.getRGB());
			}
		}
	}

}
