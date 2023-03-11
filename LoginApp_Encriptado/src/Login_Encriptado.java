
import com.sun.org.apache.xml.internal.security.utils.Base64;
import java.awt.BorderLayout;
import static java.awt.BorderLayout.SOUTH;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import static java.awt.Font.PLAIN;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import static javax.swing.SwingConstants.RIGHT;
import javax.swing.border.LineBorder;


public class Login_Encriptado extends JFrame{
    
  public static void main(String[] args) {
       Login_Encriptado LoginPrueba = new  Login_Encriptado();// creamos una ventana
        //Le damos tamaño
       LoginPrueba.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        //La hacemos visible
       LoginPrueba.setSize(700,400);  //Anchor y largo
//        //Y el comportamiento de cierre
       LoginPrueba.setVisible(true);
   }
 
     //VARIABLES UTILIZADAS
    BorderLayout JFrameBorderLayout; //borderLayout para el JFrame
    Color colorGrisClaro = new Color(200,200,200); //color gris clarito
    JPanel panelFondo, panelBotones;
    JLabel labelOeste;
    FlowLayout centroFlowLayout;
    JLabel labelTitulo, labelEmail, labelContraseña;
    JTextField textFieldEmail;
    JPasswordField passwordFieldContraseña;
    JButton botonGuardar;
    String email, clave ;
    JLabel errorEmail, errorClave;
    String LLAVE = "";
  public Login_Encriptado()
  {
      super("CHATCONRIQUEZ: INICIO DE SESION");
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);
        setResizable(false); //No permitir que se pueda mover las medidas de el Frame
        inicializarPanelFondo();
        inicializarPanelBotones();
       
  }
  
  
  private void inicializarPanelFondo(){
        panelFondo = new JPanel(); 
        panelFondo.setBackground(colorGrisClaro); //Color de fondo
        add(panelFondo); 
        panelFondo.setPreferredSize(new Dimension(700,300)); //Establezco dimensiones del panel
        
           //Inicializo label Titulo
        labelTitulo = new JLabel("BIENVENIDOS"); 
        labelTitulo.setHorizontalAlignment(labelTitulo.CENTER);
        labelTitulo.setPreferredSize(new Dimension(500,35));
        labelTitulo.setFont(new Font("ARIAL",PLAIN,25));
        panelFondo.add(labelTitulo);
        
        
        //Inicializo label email
        labelEmail = new JLabel(" CORREO ELECTRONICO: "); 
        labelEmail.setPreferredSize(new Dimension(200,35));
        labelEmail.setFont(new Font("ARIAL",PLAIN,16));
        panelFondo.add(labelEmail);

        
        //Inicializo el textfield Email
        textFieldEmail = new JTextField(); 
        textFieldEmail.setPreferredSize(new Dimension(400,35));
        textFieldEmail.setFont(new Font("ARIAL",PLAIN,16));
        textFieldEmail.setBorder(new LineBorder(Color.BLACK));
        panelFondo.add(textFieldEmail);
        
        //Inicializo label Contraseña
        labelContraseña = new JLabel("  CONTRASEÑA: "); 
        labelContraseña.setPreferredSize(new Dimension(200,35));
        labelContraseña.setFont(new Font("ARIAL",PLAIN,16));
        panelFondo.add(labelContraseña);
        
        //Inicializo el passwordfield Contraseña
        passwordFieldContraseña = new JPasswordField(); 
        passwordFieldContraseña.setPreferredSize(new Dimension(400,35));
        passwordFieldContraseña.setFont(new Font("ARIAL",PLAIN,16));
        passwordFieldContraseña.setBorder(new LineBorder(Color.BLACK));
        panelFondo.add(passwordFieldContraseña);
        
        //MENSAJES DE ERRORES EN LOS JTEXTFIELDS
        errorEmail = new JLabel(""); //Inicializo error email
        errorEmail.setPreferredSize(new Dimension(600,40));
        errorEmail.setFont(new Font("ARIAL",PLAIN,16));
        errorEmail.setForeground(Color.RED);
        errorEmail.setHorizontalAlignment(RIGHT);
        panelFondo.add(errorEmail);
        
        errorClave = new JLabel(""); //Inicializo error clave
        errorClave.setPreferredSize(new Dimension(600,20));
        errorClave.setFont(new Font("ARIAL",PLAIN,16));
        errorClave.setForeground(Color.RED);
        errorClave.setHorizontalAlignment(RIGHT);
        panelFondo.add(errorClave);
        
        //_____________________________________________________________________
        
       

    }
  private void inicializarPanelBotones(){
        panelBotones = new JPanel(); //Inicializo panel
        panelBotones.setBackground(colorGrisClaro); //Color de fondo
        add(panelBotones,SOUTH); //Lo añado al sur del JFrame
        panelBotones.setPreferredSize(new Dimension(700,100)); //Establezco dimensiones del panel

       //Añado botones Guardar
       
        botonGuardar = new JButton("Ver datos");
        botonGuardar.setPreferredSize(new Dimension(100,40));
        botonGuardar.setFont(new Font("ARIAL",PLAIN,18));
        botonGuardar.setOpaque(true);
        botonGuardar.setFocusPainted(false);
        botonGuardar.setBackground(Color.darkGray);
        botonGuardar.setBorder(new LineBorder(Color.DARK_GRAY));
        botonGuardar.setForeground(Color.WHITE);
        panelBotones.add(botonGuardar); 
                        
        //Acciones
        botonGuardar.addActionListener(new ActionListener(){
            @Override
        public void actionPerformed(ActionEvent e) {
              //Aplicamos condiciones para aceptar el correo y contraseña
                email = String.valueOf(textFieldEmail.getText());
                clave = String.valueOf(passwordFieldContraseña.getPassword());
            if (email.equals("")||clave.equals("")){
                errorEmail.setText("El email no es válido");
                textFieldEmail.requestFocus();
                
                errorClave.setText("Ingrese la contraseña");
                passwordFieldContraseña.requestFocus();
            }
            else{
              errorClave.setText("");
              errorEmail.setText("");
              File archivo;
              PrintWriter lineatxt;
              FileWriter escribir;
        String cad = "";
        char temp4;
        String directorioRaiz = System.getProperty("user.dir");
        for (int i = 0; i < directorioRaiz.length(); i++) {
            temp4 = directorioRaiz.charAt(i);
            if (temp4 == 92) {
                temp4 = 47;
                cad = cad + temp4;
            } else {
                cad = cad + temp4;
            }
        }
        directorioRaiz = cad + "/src/Login_datos";
        System.out.println(directorioRaiz);
              archivo = new File(directorioRaiz);
              String Emailtexto = textFieldEmail.getText();
              String Clavetexto = new String(passwordFieldContraseña.getPassword());
              
              //CARACTERISTICAS A ENCRIPTAR Y GUARDAR EN ARCHIVO TXT
              String encriptada = "";
              String aEnccriptar = clave;
              Login_Encriptado main = new Login_Encriptado();
              encriptada = main.Encriptar(clave);
              JOptionPane.showMessageDialog(main, "Se agregaron los datos correctamente");
//              JOptionPane.showMessageDialog(null, encriptada);
//              JOptionPane.showMessageDialog(null, main.Desencriptar(encriptada));
              if (!archivo.exists()) {
                  try {
                      archivo.createNewFile();
                      FileWriter fw = new FileWriter(archivo);
                      escribir = new FileWriter(archivo,true);
                      lineatxt = new PrintWriter(escribir);
                      lineatxt.println("Correo electronico: "+Emailtexto);
                      //lineatxt.println("Contraseña agregada: "+Clavetexto);
                      lineatxt.println("______");
                      lineatxt.println("Contraseña encriptada: "+encriptada);
                      lineatxt.println("______");
                      lineatxt.close();
                      escribir.close();
                  } catch (IOException ex) {
                      Logger.getLogger(Login_Encriptado.class.getName()).log(Level.SEVERE, null, ex);
                  }
             }
              else{
                  try {
//                      FileWriter fw = new FileWriter(archivo);
                      escribir = new FileWriter(archivo,true);
                      lineatxt = new PrintWriter(escribir);
                      lineatxt.println("Correo electronico: "+Emailtexto);
                      //lineatxt.println("Contraseña: "+Clavetexto);
                      lineatxt.println("______");
                      lineatxt.println("Contraseña encriptada: "+encriptada);
                      lineatxt.println("______");
                      lineatxt.close();
                      escribir.close();
                  } catch (IOException ex) {
                      Logger.getLogger(Login_Encriptado.class.getName()).log(Level.SEVERE, null, ex);
                  }
              } 
            }
                 
      
                
                    }
        });       
        
  }
   // Clave de encriptación / desencriptación
    public SecretKeySpec CrearClave(String llave) {
        try {
            byte[] cadena = llave.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            cadena = md.digest(cadena);
            cadena = Arrays.copyOf(cadena, 16);
            SecretKeySpec secretKeySpec = new SecretKeySpec(cadena, "AES");
            return secretKeySpec;
        } catch (Exception e) {
            return null;
        }

    }

    // Encriptar
    public String Encriptar(String encriptar) {
     
        try {
        SecretKeySpec secretKeySpec = CrearClave(LLAVE);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            
            byte [] cadena = encriptar.getBytes("UTF-8");
            byte [] encriptada = cipher.doFinal(cadena);
            String cadena_encriptada = Base64.encode(encriptada);
            return cadena_encriptada;
            
        } catch (Exception e) {
            return "";
        }
    }

    // Des-encriptación
     public String Desencriptar(String desencriptar) {
     
        try {
            SecretKeySpec secretKeySpec = CrearClave(LLAVE);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            
            byte [] cadena = Base64.decode(desencriptar);
            byte [] desencriptacioon = cipher.doFinal(cadena);
            String cadena_desencriptada = new String(desencriptacioon);
            return cadena_desencriptada;
            
        } catch (Exception e) {
            return "";
        }
    }
             
    
}


