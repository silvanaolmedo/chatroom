package chat.vistas;


import chat.controladores.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class VistaRegistroUsuario extends JFrame
{
    private JLabel lblUser;
    private JLabel lblPassword;
    private JTextField txtUser;
    private JPasswordField txtPassword; 
    private JPanel panel;
    private JButton btnRegistrarse;
    
    private Registro registro;
    
    public VistaRegistroUsuario(Registro registro)
    {
        this.registro = registro;
        
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
        
               
        btnRegistrarse = new JButton("Registrarse");
        btnRegistrarse.setBounds(20, 150, 110, 25);
        panel.add(btnRegistrarse);
        
        btnRegistrarse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnRegistrarseActionPerformed();
            }
        });
    }
    
    public void btnRegistrarseActionPerformed()
    {
        
    }
}
