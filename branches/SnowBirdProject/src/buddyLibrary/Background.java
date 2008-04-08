package buddyLibrary;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Knight
 */
public class Background extends JPanel {

    public Background() {
        this.repaint();
    }

    public void paint(Graphics g) {
        String imageFilePath = "bg.jpg";
        ImageIcon ii = new ImageIcon(imageFilePath);
        Image image = ii.getImage();
        int h = image.getHeight(null), w = image.getWidth(null);
        java.awt.Color c = this.getBackground();
        g.drawImage(image, h, w, c, null);
        super.paint(g);
    }
}
