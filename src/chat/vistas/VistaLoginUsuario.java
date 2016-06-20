package chat.vistas;

import javax.swing.*;
import chat.controladores.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaLoginUsuario extends JFrame
{
    private Login login;
    
    private JLabel lblUser;
    private JLabel lblPassword;
    private JTextField txtUser;
    private JPasswordField txtPassword; 
    private JPanel panel;
    private JButton btnConectarse;
    private JButton btnRegistrarse;
    
    public VistaLoginUsuario(Login login)
    {
        this.login = login;
        initComponents();
    }
    
    public void initComponents()
    {
        setTitle("Inicio");
        setBounds(100, 100, 300, 300);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        
        panel = new JPanel();
        panel.setBounds(10,10,300,300);
        getContentPane().add(panel);
        panel.setLayout(null);
        
        lblUser = new JLabel("Usuario");
        lblUser.setBounds(30, 50, 50, 20);
        panel.add(lblUser);
        
        lblPassword = new JLabel("Contraseña");
        lblPassword.setBounds(30, 90, 80, 10);
        panel.add(lblPassword);
        
        txtUser = new JTextField();
        txtUser.setBounds(120, 50, 100, 20);
        panel.add(txtUser);
        
        txtPassword = new JPasswordField();
        txtPassword.setBounds(120, 90, 100, 20);
        panel.add(txtPassword);
        
        btnConectarse = new JButton("Conectarse");
        btnConectarse.setBounds(20, 150, 100, 25);
        panel.add(btnConectarse);
        
        btnRegistrarse = new JButton("Registrarse");
        btnRegistrarse.setBounds(140, 150, 110, 25);
        panel.add(btnRegistrarse);
        
        btnConectarse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnConectarseActionPerformed();
            }
        });
        
        btnRegistrarse.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                
            }
        });
    }
        
        
    
    public void btnConectarseActionPerformed()
    {
        String user = txtUser.getText();
        String pass = String.copyValueOf(txtPassword.getPassword());
        login.iniciarSesion(user, pass);
    }
    
    public void btnRegistrarseActionPerformed()
    {
        login.registrarUsuario();
    }

}