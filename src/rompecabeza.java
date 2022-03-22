/**
 * @(#)prueba4x4.java
 *
 * prueba4x4 Applet application
 *
 * @author 
 * @version 1.00 2014/6/4
 */
 
import java.awt.*;
import java.applet.*;
import javax.swing.*;
import java.awt.event.*;
public class rompecabeza extends JFrame implements ActionListener {
	//instrucciones
	JScrollPane scroll;
	JDialog instrucciones;
	JTextArea inst;
	String i;
	//fin de instrucciones
	JMenuBar menu;//menu barra
	JMenu m,instru;//opc menu
	JMenuItem salir,nuev,ordena,pist,ayud;//items de opc de menu
	JWindow ayuda;//ventana de img grande de ayuda
	ImageIcon q[]=new ImageIcon [100],principal,ay,comple;//imgs de rompe,img de ayuda grande,img de ayuda peque,img de completado
	int max;//nun max de col y fil
	JButton bot[][]=new JButton[10][10],aux,ver,img,ayu,completado;//botones de rompe,boton de intercambio,boton ayuda1,boton de ayuda 2,boton de salir de ayuda 2
	int cont,x1,y1,x2,y2,conta,contb,n,ranx[]=new int[10],rany []= new int[10],xr,yr,pista,xpista,ypista;
	boolean contt,completa,pistaclick;
	String s;
	public rompecabeza() {
		setLayout(null);
		//intruccione
		i="Instrucciones: \n \n Un rompecabezas o puzle (también denominado con el término inglés \n  puzzle) es un juego de mesa cuyo objetivo es formar una figura \n combinando correctamente las partes de ésta, que se encuentran \n en distintos pedazos o piezas planas.  \n \n Mover piezas: \n \n  Para mover una pieza del rompecabezas, simplemente se debe dar click \n izquierdo sobre la pieza que se desea mover, luego se hace click  \n izquiendo sobre la posicion final a la que se desea llevar la pieza, \n cuando se selecciona una pieza se bordea en  amarillo. \n  \n Boton IMAGEN:\n  \n Al presionar el boton imagen ubicado en la parte inferior derecha \n de la pantalla aparecera una imagen miniatura del rompecabezas \n en la parte superior del boton, si se hace click sobre esta  \n imagen miniatura aparecera una imagen del rompecabezas del  \n tama;o original del mismo, para salir de de la imagen grande \n simplemente se hace click sobre la misma. \n  \n MENU de Juego: \n  \n El menu de juego esta ubicado en la barra superior derecha (Juego) \n y consta de 4 opciones: \n  \n 1.-Nuevo juego: crea un nuevo juego eliminado el juego anterior \n 2.-Ordenar: ordena el rompecabeza de manera  correcta automaticamente \n 3.-Pista: la opcion pista se puede usar de dos formas. \n  \n 1.- Pista selectiva: la pista selectiva consiste en seleccionar \n una pieza del rompecabezas (ya sea que no encuentra o no ve donde  \n deberia ir la pieza correctamente o simplemente quiera saber donde  \n va ubicada una pieza ) una vez seleccionada la pieza (quedara en  \n borde amarrillo) se va al menu de Juego y selecciona la opcion  \n pista, en el  tablero se le marcara con un borde rojo la posicion \n  a la que debe ser movida la pieza  selecciona(simplemente se hace  \n click en el cuadro bordeado de rojo) para que la pieza  este  \n en su posicion correcta.  \n 2.-Pista ordenada: si no se seleciona ninguna pieza y se utiliza  \n la opcion pista, las pistas se iran dando ordenadamente, es decir de  \n arriba hacia abajo. \n  \n 4.-Salir: opcion para salir del juego. \n  \n Completado: una vez completado el rompecabezas para iniciar \n un nuevo juego se puede hacer click en la imagen  \n  que aparece al lado del rompecabezas (dice completado) \n o tambien se puede ir al menu de juego y hacer un nuevo juego  \n  \n"  ;
		instrucciones= new JDialog();
		instrucciones.setVisible(false);
		instrucciones.setSize(600,600);
		instrucciones.setLocation(400,100);
		instrucciones.setTitle("Instrucciones");
		inst=new JTextArea(i,45,30);
		inst.setEditable(false);
		inst.setBorder(BorderFactory.createBevelBorder(1,Color.GRAY,Color.GRAY));
		scroll=new JScrollPane(inst);
		instrucciones.add(scroll );
	
		//fin instrucciones
		//barra de menu
		menu=new JMenuBar();
		menu.setBounds(1210,0,270,20);
		m= new JMenu("Juego");
		menu.add(m);
		instru= new JMenu("AYUDA");
		menu.add(instru);
		ayud= new JMenuItem("Instrucciones");
		ayud.addActionListener(this);
		instru.add(ayud);
		nuev= new JMenuItem("Nuevo juego");
		nuev.addActionListener(this);
		m.add(nuev);
		ordena= new JMenuItem("Ordenar");
		ordena.addActionListener(this);
		m.add(ordena);
		pist= new JMenuItem("Pista");
		pist.addActionListener(this);
		m.add(pist);
		salir= new JMenuItem("Salir");
		salir.addActionListener(this);
		m.add(salir);
		add(menu);
		//fin de barra de menu
		cont=conta=contb=yr=xr=n=pista=0;
		xpista=ypista=-1;
		max=10;
		contt=completa=pistaclick=false;
			//crea imagenes
         iniciaimgs();
		//fin de cre imagenes
		comple=new ImageIcon("completado;.jpg");
		ay=new ImageIcon("principal.jpg");
	principal=new ImageIcon("principal2.jpg");
	completado= new JButton(comple);	
ver= new JButton(principal);
img= new JButton("IMAGEN");
ayu=new JButton(ay);
ayuda= new JWindow();	
ayu.setSize(1200,800);
ayuda.setSize(1200,800);//tam ventana

ayuda.setVisible(false);
bareajar(); //baraje y cre jbuttons con imagenes 
//ordenar(); //en caso de prueba inicia ordenado
	
//pos inicial de botones		
setboundsbot();
ver.setBounds(1210,40,270,300);
ver.setVisible(false);
completado.setBounds(1210,20,270,300);
completado.setVisible(false);
img.setBounds(1280,600,140,40);
img.addActionListener(this);
ayu.addActionListener(this);
ver.addActionListener(this);
completado.addActionListener(this);
//atributos a LOS BOTONES
propiedadesbot();
//add botones
addbot();

ayuda.add(ayu);
        add(img);
		add(ver);
		add(completado);		
		setSize(1500,800);//tamano frame


	}

	public void paint(Graphics g) {
		
	//	g.drawString("Welcome to Java!!", 50, 60 );
		super.paint(g);
	}
	public void actionPerformed(ActionEvent e){
		
		if(completa==false){
		
		//acion del rompecabezas para guardar pos iniciales y pos finales
		for(int x=0;x<max;x++){
			for(int y=0;y<max;y++){
			
			if(cont%2==0){
			if(e.getSource()==bot[x][y]){
				x1=xpista=x;
				y1=ypista=y;
				bot[x][y].setBorder(BorderFactory.createBevelBorder(1,Color.yellow,Color.YELLOW));
			cont++;	
				pistaclick=true;
			
			}
			}else{
					if(e.getSource()==bot[x][y]){
				x2=x;
				y2=y;
			cont++;	
				contt=true;	 
					xpista=ypista=-1;
					pistaclick=false;   
			}				
				
			}
			
		}
		}//fin de aciones rompecabezas
		
	}//fin de if completa
		//mueve pos rompecabezas
		if(contt){
			
			//cambia atributos entre botones de piezas
			aux=bot[x1][y1];
			bot[x1][y1]=bot[x2][y2];
			bot[x2][y2]=aux;
	//asigna pos nueva
	bordernull();
	setboundsbot();
	x2=y2=y1=x1=-1;
	contt=false;

	verifica();//verifica si al mover pieza esta ordenado de forma correcta
	}//fin de mueve pos rompe
	if(e.getSource()==img){//en caso de precionar boton ayuda
		ver.setVisible(true);
		completado.setVisible(false);
			
		
	}else if(e.getSource()==ver){//en caso de presionar boton de imagen
		ayuda.setVisible(true);
		
	}else if(e.getSource()==ayu){//en caso de presionar boton de imgagen en ventana
		ayuda.setVisible(false);
		
	}else if(e.getSource()==completado){//en caso de presionar boton de completado
		nuevo();
		completa=false;//para que pueda usar movimientos
		pista=0;
	}else if(e.getSource()==nuev){//nueva partida menu
		nuevo();
		completa=false;
		pista=0;
	}else if(e.getSource()==salir){//salir menu
		System.exit(0);
	
	}else if(e.getSource()== ordena){//ordena correctamente menu
		nuevoordenado();
		completa=false;
		pista=0;
	}else if(e.getSource()==pist){
		pistayuda();
	}
	else if(e.getSource()==ayud){
		instrucciones.setVisible(true);
	}
			
		}
		
public void  aleatoreo(){//genera 2 arreglos de numeros aleatoreos sin repetir
conta=contb=n=0;
while(conta<max){
			if(conta==0){
				ranx[conta]=(int) Math.floor(Math.random()*max);
				
				conta++;
			}else{
				n=(int) Math.floor(Math.random()*max);
				for(int x=0;x<max;x++){
					if(ranx[x]==n){
						contb++;
					}
				}
			}
			if(contb==0){
				ranx[conta]=n;
				
				conta++;
				contb=0;
			}else{
				contb=0;
			}			
		}
		conta=contb=n=0;
			while(conta<max){
			if(conta==0){
				rany[conta]=(int) Math.floor(Math.random()*max);
				conta++;
			}else{
				n=(int) Math.floor(Math.random()*max);
				for(int x=0;x<max;x++){
					if(rany[x]==n){
						contb++;
					}
				}
			}
			if(contb==0){
				rany[conta]=n;
				conta++;
				contb=0;
			}else{
				contb=0;
			}			
		}
}
public void verifica(){//verifica si el rompe esta completado correctamente
	String g[]=new String [100];
	int win=0;
	for(int r=0,x=0;x<max;x++){
	for(int y=0;y<max;y++){
	g[r] = bot[x][y].getText();
	r++;
	}
}

for(int  x=0;x<100;x++){
	if(g[x].equals(String.valueOf(x))){
		win++;
		
	}
}
if(String.valueOf(win).equals("100")){
	completado.setVisible(true);
	ver.setVisible(false);
	win=0;
	completa=true;//para que no pueda mover mas hasta bareajar de nuevo
}
}

public void bareajar(){//generea rompe ordenado aleatoreamente
		aleatoreo();
posicionale(); 
}

public void ordenar(){//ordena en forma corecta solo para modo prueba
		for(int xr=0,x=0;x<max;x++){
	for(int y=0;y<max;y++){
	bot[x][y]=new JButton(String.valueOf(xr),q[xr]);xr++;
	}
}
}	  
public void nuevo(){ //genera nuevo juego revisar tiene errores
botnovisible();
botnull();
System.gc();
aleatoreonull();
System.gc();
bareajar();
	System.gc();	
setboundsbot();

 propiedadesbot();

addbot();

ver.setVisible(false);
completado.setVisible(false);
}
public void nuevoordenado(){ //genera nuevo juego revisar tiene errores
botnovisible();
botnull();
System.gc();
aleatoreonull();
ordenar();
		
setboundsbot();

 propiedadesbot();

addbot();

ver.setVisible(false);
completado.setVisible(false);
}
public void iniciaimgs(){
			for(int x=0;x<100;x++){
			s=String.valueOf(1+x)+".jpg";
			q[x]=new ImageIcon(s);
			
		}
}

public void propiedadesbot(){
	for(int x=0;x<max;x++){
	for(int y=0;y<max;y++){
		bot[x][y].setBorder(null);
		bot[x][y].setBackground(null);
	bot[x][y].addActionListener(this);
	bot[x][y].setVisible(true);
bot[x][y].setMargin(new Insets(0,0,0,0));
Font fuente=new Font("Monospaced", Font.PLAIN, -3);
bot[x][y].setFont(fuente);
	
	}
}
}
public void addbot(){
	for(int x=0;x<max;x++){
for(int y=0;y<max;y++){
	
	add(bot[x][y]);
	}
}
}
public void setboundsbot(){
		for(int x=0;x<max;x++){
	for(int y=0;y<max;y++){
	bot[y][x].setBounds(x*120,y*80,120,80);
	}
}
}
public void botnovisible(){
		for(int x=0;x<max;x++){
	for(int y=0;y<max;y++){
		bot[x][y].setVisible(false);
	
	}
}
}
public void botnull(){
	for(int xr=0,x=0;x<max;x++){
for(int y=0;y<max;y++){
bot[x][y]=null;
	}
} 
}

public void posicionale(){
	for(int xr=0,x=0;x<max;x++){
	for(int y=0;y<max;y++){
//	bot[ranx[x]][rany[y]]=new JButton(String.valueOf(xr));xr++;//en caso de usarlo modo prueba para ver los numeros y no img
bot[ranx[x]][rany[y]]=new JButton(String.valueOf(xr),q[xr]);xr++;
	}
}
}

public void aleatoreonull(){
	for(int x=0;x<max;x++){
		ranx[x]=-1;
		rany[x]=-1;
	}
	System.gc();
}

public void bordernull(){
		for(int x=0;x<max;x++){
	for(int y=0;y<max;y++){
		bot[y][x].setBorder(null);//elimina borde amarrillo luego de mover pos
	}
}
}

public void pistayuda(){
	int g,k;
bordernull();
if(pistaclick==false){
if(pista>=0||pista<=99){
		for(int x=0;x<max;x++){
	for(int y=0;y<max;y++){
		g=(int)pista%10;
		k=(int)pista/10;
		if(Integer.parseInt(bot[x][y].getText())==pista){
			bot[x][y].setBorder(BorderFactory.createBevelBorder(1,Color.RED,Color.RED));
		
		g=(int)pista%10;
		k=(int)pista/10;
		bot[k][g].setBorder(BorderFactory.createBevelBorder(1,Color.RED,Color.RED));
		pist.setText("pista  "+(pista+1));
		}
	}
		}
		pista++;
		
}else{
	pista=0;
}
}else{bot[xpista][ypista].setBorder(BorderFactory.createBevelBorder(1,Color.yellow,Color.yellow));
		g=(int)Integer.parseInt(bot[xpista][ypista].getText())%10;
		k=(int)Integer.parseInt(bot[xpista][ypista].getText())/10;
		bot[k][g].setBorder(BorderFactory.createBevelBorder(1,Color.RED,Color.RED));
	
}
	
}


public static void main(String[] ar) {
        rompecabeza formulario1=new rompecabeza();

        formulario1.setVisible(true);
    }


}