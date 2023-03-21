
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import static java.awt.Font.PLAIN;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class MenuFrame extends JFrame{
    public static void main(String[] args) { 
    MenuFrame menuframe = new  MenuFrame();// creamos una ventana
     menuframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Dimension ss = Toolkit.getDefaultToolkit ().getScreenSize ();
    Dimension frameSize = new Dimension ( 700, 400 );

 menuframe.setBounds ( ss.width / 2 - frameSize.width / 2, ss.height 
   / 2 - frameSize.height / 2,frameSize.width, frameSize.height );
       menuframe.setVisible ( true );
      
   }
    
    JLabel Bienvenida,Aviso;
       
    public MenuFrame(){
        super("LOGIN_APP: MENU");
        setLayout(new FlowLayout());

    Bienvenida = new JLabel("BIENVENIDOS"); 
    Bienvenida.setHorizontalAlignment(Bienvenida.CENTER);
    Bienvenida.setPreferredSize(new Dimension(200,35));
    Bienvenida.setFont(new Font("ARIAL",PLAIN,25));
    add(Bienvenida);
    
    Aviso = new JLabel("USTED INGRESO CORRECTAMENTE"); 
    Aviso.setHorizontalAlignment(Aviso.CENTER);
    Aviso.setPreferredSize(new Dimension(300,35));
    Aviso.setFont(new Font("ARIAL",PLAIN,15));
    add(Aviso);
    
    }
}
