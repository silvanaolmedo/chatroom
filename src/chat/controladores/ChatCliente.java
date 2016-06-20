package chat.controladores;

import chat.utilidades.*;
import chat.vistas.VistaChat;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;

public class ChatCliente implements Runnable
{
    private Usuario user;
    private VistaChat vistaChat;
    private Socket socket;
    private ObjectInputStream lector;
    private ObjectOutputStream escritor;
    private int port;
    private String host;
    private boolean running;
    
    
    
    public ChatCliente(String host, int port, Usuario user)
    {
        this.user = user;
        vistaChat = new VistaChat(this,user);
        running = true;
        this.host = host;
        this.port = port;
        
        this.conectar();
        
        Thread clientThread = new Thread(this);
        clientThread.start();
    }
    
    public void conectar()
    {
        try {
            socket = new Socket(host, port);
            lector = new ObjectInputStream(socket.getInputStream());
            escritor = new ObjectOutputStream(socket.getOutputStream());
            Mensaje msj = new Mensaje(Mensaje.TIPO_NOMBRE_USUARIO,user.getUsuario(),"ha iniciado sesion","");
            enviarMensaje(msj);
            vistaChat.setVisible(true);
        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(vistaChat, "No se pudo establecer una conexión");
            vistaChat.dispose();
        }
    }
    
    public void enviarMensaje(Mensaje msj)
    {
        if (!Validador.validarVacio(msj.getTexto())) 
        {
            try 
            {
                escritor.writeObject(msj);
                escritor.flush();
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
            }
        }
        vistaChat.getTxtMensaje().setText("");
        vistaChat.getTxtMensaje().requestFocus();
    }
    
    
    
    public void cerrar()
    {
        running = false;
        try {
            escritor.close();
        } catch (Exception e) {
        }
        System.exit(0);
    }

    @Override
    public void run() 
    {
        Mensaje msj;
        running = true;
        while (running) 
        {
            try 
            {
                msj = (Mensaje)lector.readObject();
                if (msj.getTipo() == Mensaje.TIPO_NOMBRE_USUARIO && !msj.getFrom().equals(user.getUsuario())) 
                {
                    vistaChat.getJTextAreaMensajes().append(msj.getFrom()+" "+msj.getTexto()+"\n");
                    vistaChat.getListModelConectados().addElement(msj.getFrom());
                }
                if (msj.getTipo() == Mensaje.TIPO_LOGOUT && !msj.getFrom().equals(user.getUsuario())) 
                {
                    vistaChat.getListModelConectados().removeElement(msj.getFrom());
                    vistaChat.getJTextAreaMensajes().append(msj.getFrom()+" "+msj.getTexto()+"\n");
                }
                if (msj.getTipo() == Mensaje.TIPO_MENSAJE) 
                {
                    vistaChat.getJTextAreaMensajes().append(msj.toString()+"\n");
                }
            } 
            catch (Exception e) 
            {
                running =false;
            }
            finally
            {
                cerrar();
            }
        
        }
        
        
        
    }
    
}
