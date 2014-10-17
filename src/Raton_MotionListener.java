
import java.awt.event.*;
import java.awt.geom.Point2D;

public class Raton_MotionListener extends MouseMotionAdapter
{
	Main temp;
	
	public Raton_MotionListener(Main t)
	{
		temp = t;
	}
	
	public void mouseDragged(MouseEvent me)
	{
		if(temp.mover)
		{
			int rx = me.getX();
			int ry = me.getY();
			double dx = rx-temp.posx;
			double dy = ry-temp.posy;
			temp.posx = rx;
			temp.posy = ry;
			temp.F.Trasladar(dx, dy);
			temp.F.Encuentra_MinMax();
			temp.repaint();
			if(temp.F.contorno.getMaxX()>=1000||temp.F.contorno.getMinY()<=0||temp.F.contorno.getMinX()<=0||temp.F.contorno.getMaxY()>=700)
			{
				temp.mover = false;
			}
			else
			{
				temp.mover=true;
			}
			temp.repaint();
		}
		
	}
	
	public void mouseMoved(MouseEvent me) 
	{
		Point2D unto = me.getLocationOnScreen();
		temp.F.posx = unto.getX();
		temp.F.posy = unto.getY();
	}
}
