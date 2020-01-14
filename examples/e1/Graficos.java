import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class Graficos extends JPanel{
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//int ancho = getWidth();
		//int alto  = getHeight();		
		g.setColor(new Color(255, 0, 0));
		g.drawLine(0, 0, 300, 200);	
	}
}