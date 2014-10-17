
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Vector;

public class Proyecto_Transformaciones 
{
	double fig[][]; // Esta será la matriz de la figura.
	double minx,ancho=324,alto=551;
	double maxx;
	double maxy;
	double miny;
	Color color;
	Rectangle2D limite,contorno;
	int op,npuntos = 976,gradosfv=0,gradosct=0, opcolor=0,reflex=0;
	double posx, posy;
	String nombrefig = "León",status1="Dimensiones: Original",status2="Orientación: Original",colors="Color: Original",reflejado="Reflejado: No";
	boolean mover,cont,fav;
	Vector <double [][]>figuramv; // Figura que se encontrará en el mapeo de ventana.
	Vector <double[][]>copia; // Ésta copia servirá para tener un respaldo de la figura, en caso de que queramos restaurarla.
	Vector <double[][]> figura; // Figura original a la cuál se le aplicarán las transformaciones.
	
	public Proyecto_Transformaciones(Vector <double[][]>fig) // Para CUALQUIER Figura.
	{
		figura = fig;
	}
	
	public void Restaurar(int opc) // Método que restaurará la figura original.
	{
		op=opc;
		if(op == 0)
		{
			Leon obr = new Leon();
			copia = obr.Generar();
			nombrefig="León"; npuntos = 976; status1="Dimensiones: Original"; status2="Orientación: Original"; colors="Color: Original"; reflejado="Reflejado: No";
			ancho=324;alto=551;
			figura = copia;
		}
		if(op == 1)
		{
			Guitarra obg = new Guitarra();
			copia = obg.Generar();
			nombrefig="Guitarra"; npuntos = 136; status1="Dimensiones: Original"; status2="Orientación: Original"; colors="Color: Original"; reflejado="Reflejado: No";
			ancho=436;alto=332;
			figura = copia;
		}
	}
	
	public void Encuentra_MinMax()
	{
		minx = figura.get(0)[0][0];
		miny = figura.get(0)[0][1];
		
		for (int i = 0; i < figura.size(); i++) 
			for (int j = 0; j < figura.get(i).length; j++) 
			{
			if(figura.get(i)[j][0]<minx)
				minx = figura.get(i)[j][0];
			if(figura.get(i)[j][1]<miny)
				miny = figura.get(i)[j][1];
			}
		maxx = 0;maxy = 0;
		for (int i = 0; i < figura.size(); i++) 
			for (int j = 0; j < figura.get(i).length; j++) 
			{
			if(figura.get(i)[j][0]>maxx)
				maxx = figura.get(i)[j][0];
			if(figura.get(i)[j][1]>maxy)
				maxy = figura.get(i)[j][1];
			}
	}
	
	public void Dibujar(Graphics g) // Método para dibujar la figura.
	{	
		Graphics2D g2 = (Graphics2D)g;
		limite = new Rectangle2D.Double(1000,0,366,730);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		GradientPaint gr = new GradientPaint((float)1000,(float)350,new Color(159,181,251),(float)1366,(float)350,new Color(53,118,248));
		g2.setPaint(gr);
		g2.fill(limite);
		
		if(op == 0)
		{
			if(opcolor==0)
			{
				g.setColor(new Color(251,57,63));
			for (int i = 0; i < figura.size(); i++) 
				for (int j = 0; j < figura.get(i).length-1; j++) 
				{
					g.setColor(Color.black);
					g.drawLine((int)figura.get(i)[j][0], (int)figura.get(i)[j][1], (int)figura.get(i)[j+1][0], (int)figura.get(i)[j+1][1]);
				}
			}
			else
			{
				g.setColor(color);
				for (int i = 0; i < figura.size(); i++) 
					for (int j = 0; j < figura.get(i).length-1; j++) 
					{
						g.drawLine((int)figura.get(i)[j][0], (int)figura.get(i)[j][1], (int)figura.get(i)[j+1][0], (int)figura.get(i)[j+1][1]);
					}
			}
			
		}
		if(op == 1)
		{
			if(opcolor == 0)
			{
				g.setColor(new Color(107,48,3));
				for (int i = 0; i < figura.size(); i++) 
					for (int j = 0; j < figura.get(i).length-1; j++) 
					{
						g.drawLine((int)figura.get(i)[j][0], (int)figura.get(i)[j][1], (int)figura.get(i)[j+1][0], (int)figura.get(i)[j+1][1]);
					}
			}
			else
			{
				g.setColor(color);
				for (int i = 0; i < figura.size(); i++) 
					for (int j = 0; j < figura.get(i).length-1; j++) 
					{
						g.drawLine((int)figura.get(i)[j][0], (int)figura.get(i)[j][1], (int)figura.get(i)[j+1][0], (int)figura.get(i)[j+1][1]);
					}
			}
			
		}
		
		if(mover == true)
		{
			g2.setStroke(new BasicStroke(1f,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,0, new float[]{5}, 0));
			Encuentra_MinMax();
			contorno = new Rectangle2D.Double(minx, miny, maxx-minx, maxy-miny);
			g2.setColor(Color.DARK_GRAY);
			g2.draw(contorno);
			g2.setStroke(new BasicStroke(1f));
		}
		else
		{
			minx=0;maxx=0;miny=0;maxy=0;
		}
		g2.setColor(Color.black);
		g2.setFont(new Font("Calibri",Font.BOLD+Font.ITALIC,18));
		g2.drawString("Propiedades de la figura:", 1010, 30);
		g2.drawLine(1210, 25, 1310, 25); 
		g2.drawLine(1310, 25, 1310, 690); 
		g2.drawLine(1310, 690, 1125, 690); 
		g2.drawLine(1310, 380, 1170, 380); 
		g2.drawLine(1310, 600, 1015, 600); 
		g2.drawLine(1010, 400, 1010, 675); 
		g2.setFont(new Font("Calibri",Font.PLAIN,12));
		// Propiedades: NOMBRE Y PUNTOS
		g2.setColor(Color.darkGray);
		g2.drawLine(1010, 60, 1020, 60);
		g2.drawLine(1010, 60, 1010, 130);
		g2.drawLine(1010, 130, 1300, 130);
		g2.drawLine(1300, 130, 1300, 60);
		g2.drawLine(1300, 60, 1155, 60);
		g2.drawString("Nombre y descripción:", 1025, 65);
		g2.setColor(Color.black);
		g2.drawString("Nombre: "+nombrefig, 1040, 90);
		g2.drawString("Núm. de puntos:  "+npuntos, 1040, 110);
		// Propiedades: TAMAÑO Y POSICIÓN
		Encuentra_MinMax();
		g2.setColor(Color.darkGray);
		g2.drawLine(1010, 160, 1020, 160);
		g2.drawLine(1010, 160, 1010, 210);
		g2.drawLine(1010, 210, 1300, 210);
		g2.drawLine(1300, 210, 1300, 160);
		g2.drawLine(1300, 160, 1135, 160);
		g2.drawString("Posición y tamaño:", 1025, 165);
		g2.setColor(Color.black);
		g2.drawString("X: "+(int)minx, 1040, 190);
		g2.drawString("Y: "+(int)miny, 1100, 190);
		g2.drawString("Ancho: "+((int)maxx-(int)minx), 1140, 190);
		g2.drawString("Alto: "+((int)maxy-(int)miny), 1220, 190);
		// Propiedades: ESTADO
		g2.setColor(Color.darkGray);
		g2.drawLine(1010, 250, 1020, 250);
		g2.drawLine(1010, 250, 1010, 360);
		g2.drawLine(1010, 360, 1300, 360);
		g2.drawLine(1300, 360, 1300, 250);
		g2.drawLine(1300, 250, 1135, 250);
		g2.drawString("Estado de imagen: ", 1025, 255);
		g2.setColor(Color.black);
		g2.drawString(status1, 1040, 280);
		g2.drawString(status2, 1040, 300);
		g2.drawString(colors, 1040, 320);
		g2.drawString(reflejado, 1040, 340);
		g2.setColor(Color.black);
		g2.setFont(new Font("Calibri",Font.BOLD+Font.ITALIC,18));
		// Mapeo de ventana:
		g2.drawString("Mapeo de ventana:", 1010, 385);
		g2.setColor(Color.white);
		g2.fillRect(1100, 400, 195, 173);
		g2.setColor(new Color(234,234,234));
		int ex=1100,ye=400;
		for (int i = 0; i < 16; i++) 
		{
			g2.setStroke(new BasicStroke(1f,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,0, new float[]{3}, 0));
			g2.drawLine(1100, ye, 1295, ye);
			ye+=11;
		}
		for (int i = 0; i < 18; i++) 
		{
			g2.setStroke(new BasicStroke(1f,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,0, new float[]{6}, 0));
			g2.drawLine(ex, 400, ex, 573);
			ex+=11;
		}
		g2.setStroke(new BasicStroke(1f));
		g2.setColor(Color.black);
		g2.drawLine(1100, 400, 1295, 400);
		g2.drawLine(1100, 400, 1100, 573);
		g2.drawLine(1100, 573, 1295, 573);
		g2.drawLine(1295, 573, 1295, 400);
		g2.setFont(new Font("Calibri",Font.ITALIC,13));
		g2.drawString("Para obtener ayuda, presione ALT + F1", 1065, 645);
		g2.setFont(new Font("Calibri",Font.PLAIN,12));
		g2.setColor(Color.darkGray);
		g2.drawString("Ratón: ["+posx+", "+posy+"]", 1015, 690);
	}
	
//	MÉTODOS DE 'ESCALAR' --------------------------------------------------------------------------------------------------------------
	
	public void Escalar_H(double escx, double escy)
	{
			double tx = figura.get(0)[0][0], ty = figura.get(0)[0][1];
		
		for (int i = 0; i < figura.size(); i++) 
			for (int j = 0; j < figura.get(i).length; j++) 
			{
			figura.get(i)[j][0] = (figura.get(i)[j][0] * escx - tx * escx + tx);
			figura.get(i)[j][1] = (figura.get(i)[j][1] * escy - ty * escy + ty);
			}
		if(((int)maxx-(int)minx)<(int)ancho&&((int)maxy-(int)miny)<(int)alto)
			status1 = "Dimensiones: Escalado";
		if((maxx-minx)>ancho&&(maxy-miny)>alto)
			status1 = "Dimensiones: Aumentado";
	}
	
// MÉTODOS DE 'DEFORMAR' --------------------------------------------------------------------------------------------------------------
	
	public void Deformar(double defx, double defy)
	{
		 for (int i = 0; i < figura.size(); i++) 
			for (int j = 0; j < figura.get(i).length; j++) 
			{
			   figura.get(i)[j][0]=(figura.get(i)[j][0]+defy*figura.get(i)[j][1]);
			   figura.get(i)[j][1]=(defx*figura.get(i)[j][0]+figura.get(i)[j][1]);
		   }
	}
	
	public void Deformar_Punto(double defx, double defy)
	{
		// 1. Trasladarla al origen
		double tx = figura.get(0)[0][0], ty = figura.get(0)[0][1];
		Trasladar(-tx,-ty);
				
		// 2. Aplicar la transformación de Escalar
		Deformar(defx,defy);
				
		// 3. Regresar la figura al punto original
		Trasladar(tx,ty);
	}
	
//	MÉTODOS DE 'ROTAR' ----------------------------------------------------------------------------------------------------------------
	
	public void Rotar_Favor(int grados)
	{
		double rad = Math.toRadians(grados);
		double sen = Math.sin(rad);
		double cos = Math.cos(rad);
		
		for (int i = 0; i < figura.size(); i++) 
			for (int j = 0; j < figura.get(i).length; j++) 
			{
			double x = figura.get(i)[j][0], y = figura.get(i)[j][1];
			figura.get(i)[j][0] =  (x*cos-y*sen); // En X
			figura.get(i)[j][1] =  (x*sen+y*cos); // En Y
			}
		if(cont==true)
			gradosct--;
		else if(gradosfv==0||gradosfv>0)
		{
			gradosfv++;
			status2="Orientación: "+gradosfv+"° FMR";
		}
	}
	
	public void Rotar_Favor_Punto(int grados)
	{
	
		// 1. Trasladarla al origen
		double tx = figura.get(0)[0][0], ty = figura.get(0)[0][1];
		Trasladar(-tx,-ty);
		
		// 2. Aplicar la transformación de Escalar
		Rotar_Favor(grados);
		
		// 3. Regresar la figura al punto original
		Trasladar(tx,ty);
	}
	
	public void Rotar_Contra(int grados)
	{
		double rad = Math.toRadians(grados);
		double sen = Math.sin(rad);
		double cos = Math.cos(rad);
		
		for (int i = 0; i < figura.size(); i++) 
			for (int j = 0; j < figura.get(i).length; j++) 
			{
				double x = figura.get(i)[j][0], y = figura.get(i)[j][1];
				figura.get(i)[j][0] = (x*cos+y*sen); // En X
				figura.get(i)[j][1] = (-1*x*sen+y*cos); // En Y
			}
		if(fav==true)
			gradosfv--;
		else if(gradosct==0||gradosct>0)
		{
			gradosct++;
			status2="Orientación: "+gradosct+"° CMR";
		}
	}
	
	public void Rotar_Contra_Punto(int grados)
	{
		// 1. Trasladarla al origen
		double tx = figura.get(0)[0][0], ty = figura.get(0)[0][1];
		Trasladar(-tx,-ty);
		
		// 2. Aplicar la transformación de Escalar
		Rotar_Contra(grados);
				
		// 3. Regresar la figura al punto original
		Trasladar(tx,ty);
	}

//	MÉTODOS DE 'TRASLADAR' ------------------------------------------------------------------------------------------------------------
	
	public void Trasladar(double tx, double ty)
	{
		for (int i = 0; i < figura.size(); i++) 
			for (int j = 0; j < figura.get(i).length; j++) 
			{
			figura.get(i)[j][0] += tx; // En X
			figura.get(i)[j][1] += ty; // En Y
			}
	}
	
//	MÉTODOS DE 'REFLEJAR' --------------------------------------------------------------------------------------------------------------	
	
	public void Reflexion(int rx, int ry)
	{
	   double tx = figura.get(0)[0][0], ty = figura.get(0)[0][1];
		
	   for (int i = 0; i < figura.size(); i++) 
			for (int j = 0; j < figura.get(i).length; j++) 
			{
				figura.get(i)[j][0] = figura.get(i)[j][0]*rx-tx*rx+tx;
				figura.get(i)[j][1] = figura.get(i)[j][1]*ry-ty*ry+ty;
			}
	   if(reflex==0)
		   reflejado = "Reflejado: No";
	   else if(reflex==1)
		   reflejado = "Reflejado: En el eje X";
	   else if(reflex==2)
		   reflejado = "Reflejado: En el eje Y";
	   else if(reflex==3)
		   reflejado = "Reflejado: En el eje XY";
	}
	
//	MAPEO DE VENTANA -------------------------------------------------------------------------------------------------------------------
	
	public void Mapeo_Ventana(int width, int height)
	{
		int xvmin = 1100, yvmin = 400, xwmax = 1366, ywmax = 530;
		
		double sx = (double)(xwmax-xvmin)/xwmax;
		double sy = (double)(ywmax-yvmin)/ywmax;
		
		if(op == 0)
		{
			Leon obr = new Leon();
			figuramv = obr.Generar();
		}
		if(op == 1)
		{
			Guitarra obr = new Guitarra();
			figuramv = obr.Generar();
		}
		
		for (int i = 0; i < figuramv.size(); i++) 
			for (int j = 0; j < figuramv.get(i).length; j++) 
			{
				figuramv.get(i)[j][0] = (int)(xvmin+(int)(figura.get(i)[j][0]*sx));
				figuramv.get(i)[j][1] = (int)(yvmin+(int)(figura.get(i)[j][1]*sy));
			}
	}
	
	public void Dibujar_MV(Graphics g) // Método para dibujar la figura.
	{
		if(op == 0)
		{
			g.setColor(new Color(251,57,63));
			for (int i = 0; i < figuramv.size(); i++) 
				for (int j = 0; j < figuramv.get(i).length-1; j++) 
				{
					g.setColor(Color.black);
					g.drawLine((int)figuramv.get(i)[j][0], (int)figuramv.get(i)[j][1], (int)figuramv.get(i)[j+1][0], (int)figuramv.get(i)[j+1][1]);
				}
		}
			if(op == 1)
			{
				g.setColor(new Color(107,48,3));
				for (int i = 0; i < figuramv.size(); i++) 
					for (int j = 0; j < figuramv.get(i).length-1; j++) 
					{
						g.drawLine((int)figuramv.get(i)[j][0], (int)figuramv.get(i)[j][1], (int)figuramv.get(i)[j+1][0], (int)figuramv.get(i)[j+1][1]);
					}
			}
		}
}