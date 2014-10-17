/* Dani Vega's DRAWING NOTEBOOK - Proyecto Unidad 2 */

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.net.*;
import java.util.Vector;

public class Main extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	JFrame ventana;
	Container cont;
	JButton BFig, BEscO, BEscP, BDef, BRot, BTras, BRef;
	Proyecto_Transformaciones F;
	JToolBar barra;
	JLabel cordx,cordy,properties;
	Toolkit TK = Toolkit.getDefaultToolkit();
	BufferedImage buf;
	JPopupMenu pop;
    JMenuItem restaurar,salir;
    Color color;
    JCheckBox cuad;
	JColorChooser colorc;
	URL nuevourl,ucursor,ucursorpencil,diburl,escurl,defurl,rotcurl,rotsurl,trasurl,refurl,salurl,diburlg,escurlmg,escurlmng,defurlg,rotcurlg,rotsurlg,trasurlg,refurlg,salurlg,colorurl,ayurl;
	Cursor cursor;
	boolean mover,cuadri=true;
	int opc, posx, posy;
	static Vector<double[][]> figura = new Vector<double[][]>();
	
	public Main(String tit,Vector<double[][]> rosa)
	{
		    JFrame.setDefaultLookAndFeelDecorated(true);
		    JDialog.setDefaultLookAndFeelDecorated(true);
		    
		    try {
		    	UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		    	} catch (Exception e){}
		    
		    	// Construcción de la ventana
				ventana = new JFrame(tit);
				cont = ventana.getContentPane();
				cont.setLayout(new BorderLayout());
				cont.setBackground(new Color(159,181,251));
				ventana.setSize(1000, 700);
				ventana.setBounds(0, 35, 1366, 730);
				buf = loadImage("/Resources/fondo.jpg");
			     
				// Menu PopUp
				this.addMouseListener(new MouseAdapter() {
				
					public void mousePressed(MouseEvent arg) 
					{
						pop = new JPopupMenu();
						restaurar = new JMenuItem("Restaurar");
						salir = new JMenuItem("Salir");
						pop.add(restaurar);pop.add(salir);
						
						if(arg.getButton()==MouseEvent.BUTTON3)
						{
							pop.show(ventana, arg.getX()+50, arg.getY()+50);
							restaurar.addActionListener(new ActionListener() 
							{
								public void actionPerformed(ActionEvent arg0) 
								{
									if(arg0.getSource() == restaurar)
										F.Restaurar(0);
										F.opcolor=0;
									repaint();
								}
							});
							salir.addActionListener(new ActionListener()
							{
								public void actionPerformed(ActionEvent e) 
								{
									if(e.getSource()==salir)
										System.exit(0);
								}
							});
						}
					}
				});
				
				this.addMouseListener(new Raton_Listener(this));
				this.addMouseMotionListener(new Raton_MotionListener(this));
				this.addMouseWheelListener(new MouseWheelListener() 
				{
					public void mouseWheelMoved(MouseWheelEvent mwe) 
					{
						int mov = mwe.getWheelRotation();
						if(mov<0)
							F.Escalar_H(1.1, 1.1);
						else
							F.Escalar_H(0.9, 0.9);
						repaint();
					}
				});
				
				// Barra de herramientas.
				barra = new JToolBar("Transformaciones rápidas",JToolBar.VERTICAL);
				cont.add(barra,BorderLayout.WEST);
				/* --------------------------------------------------------------------------------------------------------------------------------------------------- */
				diburlg = getClass().getResource("/Resources/rose.png");
				Action a1 = new AbstractAction("Escalar",new ImageIcon(diburlg))
				{
					private static final long serialVersionUID = 1L;

					public void actionPerformed(ActionEvent ap)
					{
						opc = 0;
						F.Restaurar(opc);
						F.opcolor=0;
						repaint();
					}
				};
				a1.putValue(Action.SHORT_DESCRIPTION, "Dibuja una rosa");
				a1.putValue(Action.MNEMONIC_KEY, new Integer('O'));
				a1.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_O,InputEvent.CTRL_MASK));
				/* --------------------------------------------------------------------------------------------------------------------------------------------------- */
				escurlmg = getClass().getResource("/Resources/escalarmasg.png");
				Action a2 = new AbstractAction("Escalar",new ImageIcon(escurlmg))
				{
					private static final long serialVersionUID = 1L;

					public void actionPerformed(ActionEvent ap)
					{
						F.Escalar_H(1.1, 1.1);
						repaint();
					}
				};
				a2.putValue(Action.SHORT_DESCRIPTION, "Escala la imagen de manera automática (+1).");
				a2.putValue(Action.MNEMONIC_KEY, new Integer('E'));
				a2.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_E,InputEvent.CTRL_MASK));
				/* --------------------------------------------------------------------------------------------------------------------------------------------------- */
				escurlmng = getClass().getResource("/Resources/escalarmenosg.png");
				Action a3 = new AbstractAction("Dibujar",new ImageIcon(escurlmng))
				{
					private static final long serialVersionUID = 1L;

					public void actionPerformed(ActionEvent ap)
					{
						F.Escalar_H(0.9, 0.9);
						repaint();
					}
				};
				a3.putValue(Action.SHORT_DESCRIPTION, "Escala la imagen de marea automática (-1).");
				a3.putValue(Action.MNEMONIC_KEY, new Integer('D'));
				a3.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D,InputEvent.CTRL_MASK));
				/* --------------------------------------------------------------------------------------------------------------------------------------------------- */
				defurlg = getClass().getResource("/Resources/deformarg.png");
				Action a4 = new AbstractAction("Deformar",new ImageIcon(defurlg))
				{
					private static final long serialVersionUID = 1L;

					public void actionPerformed(ActionEvent ap)
					{
						F.Deformar_Punto(0.1,0.1);
						repaint();
					}
				};
				a4.putValue(Action.SHORT_DESCRIPTION, "Deforma la figura.");
				a4.putValue(Action.MNEMONIC_KEY, new Integer('f'));
				a4.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F,InputEvent.CTRL_MASK));
				/* --------------------------------------------------------------------------------------------------------------------------------------------------- */
				rotsurlg = getClass().getResource("/Resources/rotarsg.png");
				Action a5 = new AbstractAction("Rotar en sentido a las  manecillas del reloj",new ImageIcon(rotsurlg))
				{
					private static final long serialVersionUID = 1L;

					public void actionPerformed(ActionEvent ap)
					{
						F.Rotar_Favor_Punto(1);
						F.fav=true;
						F.cont=false;
						repaint();
					}
				};
				a5.putValue(Action.SHORT_DESCRIPTION, "Rota la figura en sentido a las manecillas del reloj.");
				a5.putValue(Action.MNEMONIC_KEY, new Integer('S'));
				a5.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_MASK));
				/* --------------------------------------------------------------------------------------------------------------------------------------------------- */
				rotcurlg = getClass().getResource("/Resources/rotarcg.png");
				Action a6 = new AbstractAction("Rotar en contra del sentido de las manecillas del reloj",new ImageIcon(rotcurlg))
				{
					private static final long serialVersionUID = 1L;

					public void actionPerformed(ActionEvent ap)
					{
						F.Rotar_Contra_Punto(1);
						F.cont=true;
						F.fav=false;
						repaint();
					}
				};
				a6.putValue(Action.SHORT_DESCRIPTION, "Rota la figura en sentido contrario a las manecillas del reloj.");
				a6.putValue(Action.MNEMONIC_KEY, new Integer('C'));
				a6.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_C,InputEvent.CTRL_MASK));
				/* --------------------------------------------------------------------------------------------------------------------------------------------------- */
				trasurlg = getClass().getResource("/Resources/trasladarg.png");
				Action a7 = new AbstractAction("Trasladar",new ImageIcon(trasurlg))
				{
					private static final long serialVersionUID = 1L;

					public void actionPerformed(ActionEvent ap)
					{
						Dialog_Trasladar obt = new Dialog_Trasladar(ventana,true);
						int vec[] = obt.Mostrar();
						F.Trasladar(vec[0], vec[1]);
						repaint();
					}
				};
				a7.putValue(Action.SHORT_DESCRIPTION, "Traslada la figura a un punto definido por el usuario.");
				a7.putValue(Action.MNEMONIC_KEY, new Integer('T'));
				a7.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_T,InputEvent.CTRL_MASK));
				/* --------------------------------------------------------------------------------------------------------------------------------------------------- */
				refurlg = getClass().getResource("/Resources/reflejarg.png");
				Action a8 = new AbstractAction("Reflejar",new ImageIcon(refurlg))
				{
					private static final long serialVersionUID = 1L;

					public void actionPerformed(ActionEvent ap)
					{
						Dialog_Reflexion obt = new Dialog_Reflexion(ventana,true);
						int opc[] = obt.Mostrar();
						if(opc[0]==1&&opc[1]==1)
							F.reflex=0;
						else if(opc[0]==-1&&opc[1]==1)
							F.reflex=1;
						else if(opc[0]==1&&opc[1]==-1)
							F.reflex=2;
						else if(opc[0]==-1&&opc[1]==-1)
							F.reflex=3;
						F.Reflexion(opc[0], opc[1]);
						repaint();
					}
				};
				a8.putValue(Action.SHORT_DESCRIPTION, "Refleja la figura en x, y o en yx.");
				a8.putValue(Action.MNEMONIC_KEY, new Integer('J'));
				a8.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_J,InputEvent.CTRL_MASK));
				/* --------------------------------------------------------------------------------------------------------------------------------------------------- */
				colorurl = getClass().getResource("/Resources/colorsel.png");
				Action a9 = new AbstractAction("Cambiar Color",new ImageIcon(colorurl))
				{
					private static final long serialVersionUID = 1L;

					public void actionPerformed(ActionEvent ap)
					{
						color = JColorChooser.showDialog(Main.this, "Selecciona el color a aplicar", Color.black);
						F.opcolor = 1;
						F.color = color;
						F.colors="Color: ["+F.color.getRed()+", "+F.color.getGreen()+", "+F.color.getBlue()+"]";
						repaint();
					}
				};
				a9.putValue(Action.SHORT_DESCRIPTION, "Selector de colores.");
				a9.putValue(Action.MNEMONIC_KEY, new Integer('X'));
				a9.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_X,InputEvent.CTRL_MASK));
				/* --------------------------------------------------------------------------------------------------------------------------------------------------- */
				colorurl = getClass().getResource("/Resources/guitar.png");
				Action a10 = new AbstractAction("Cambiar Color",new ImageIcon(colorurl))
				{
					private static final long serialVersionUID = 1L;

					public void actionPerformed(ActionEvent ap)
					{
						opc = 1;
						F.Restaurar(opc);
						F.opcolor = 0;
						repaint();
					}
				};
				a10.putValue(Action.SHORT_DESCRIPTION, "Dibuja una guitarra.");
				a10.putValue(Action.MNEMONIC_KEY, new Integer('U'));
				a10.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_U,InputEvent.CTRL_MASK));
				/* --------------------------------------------------------------------------------------------------------------------------------------------------- */
				
				ayurl = getClass().getResource("/Resources/ayuda.png");
				Action ayuda = new AbstractAction("Ayuda",new ImageIcon(ayurl))
				{
					private static final long serialVersionUID = 1L;

					public void actionPerformed(ActionEvent ap)
					{
						@SuppressWarnings("unused")
						Ayuda oba = new Ayuda();
					}
				};
				ayuda.putValue(Action.SHORT_DESCRIPTION, "Muestra la ayuda");
				ayuda.putValue(Action.MNEMONIC_KEY, 112);
				ayuda.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F1,InputEvent.CTRL_MASK));
				/* --------------------------------------------------------------------------------------------------------------------------------------------------- */
				barra.add(a1);barra.add(a10);barra.add(a2);barra.add(a3);barra.add(a4);barra.add(a5);barra.add(a6);barra.add(a7);barra.add(a8);barra.add(a9);barra.add(ayuda);
				barra.setBackground(new Color(137,172,254));
				/* --------------------------------------------------------------------------------------------------------------------------------------------------- */
				
				cuad = new JCheckBox();
				cuad.setText("Cuadrícula");
				cuad.setBounds(1055, 400, 100, 20);
				cuad.setSelected(true);
				cont.add(cuad);
				cuad.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e) 
					{
						if(cuad.isSelected())
							cuadri = true;
						else
							cuadri = false;
						repaint();
					}
				});
				
				cont.add(this,BorderLayout.CENTER);
				BFig = new JButton("Dibujar");
					BFig.addActionListener(this);
				BEscP = new JButton("Escalar");
					BEscP.addActionListener(this);
				BDef = new JButton("Deformación");
					BDef.addActionListener(this);
				BRot = new JButton("Rotación");
					BRot.addActionListener(this);
				BTras = new JButton("Traslación");
					BTras.addActionListener(this);
				BRef = new JButton("Reflexión");
					BRef.addActionListener(this);
				
				F = new Proyecto_Transformaciones(figura);
				ventana.setResizable(false);
				ventana.setVisible(true);
				ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public BufferedImage loadImage(String nombre) 
	{
        URL url = null;
        try 
        {
        	url = getClass().getResource(nombre);
        	return ImageIO.read(url);

        } 
        catch (Exception e) 
        {
        	System.out.println("No se pudo cargar la imagen " + nombre +" de "+url);
        	System.out.println("El error fue : "+e.getClass().getName()+" "+e.getMessage());
        	return null; 
        }
     }
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(buf, 0, 0, this);
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		int ex = 0, ye=0;
		if(cuadri==true)
		{
			for (int i = 0; i < 20; i++) 
			{
				g2.setStroke(new BasicStroke(1f,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,0, new float[]{10}, 0));
				g2.setColor(new Color(234,234,234));
				g2.drawLine(0, ye, 1000, ye);
				g2.setColor(Color.LIGHT_GRAY);
				g2.drawString(""+ex, ex+3, 15);
				
				g2.setColor(new Color(234,234,234));
				g2.drawLine(ex, 0, ex, 700);
				g2.setColor(Color.LIGHT_GRAY);
				g2.drawString(""+ye, 3, ye-5);
				
				g2.setStroke(new BasicStroke(1f));
				ex+=50;
				ye+=50;
			}
		}
		
			F.Dibujar(g);
			F.Encuentra_MinMax();
			int w = ventana.getWidth();
			int h = ventana.getHeight();
			F.Mapeo_Ventana(w,h);
			F.Dibujar_MV(g);
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) 
	{
		Loader obloader = new Loader();
		Leon obr = new Leon();
		figura =  obr.Generar();
		new Main("DaniVega's Drawing Notebook - Documento 1",figura);
	}
	
	public void actionPerformed(ActionEvent arg0) {}
}