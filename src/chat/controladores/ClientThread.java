package chat.controladores;

import chat.utilidades.Mensaje;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientThread implements Runnable
{

    private Socket clientSocket;
    private Server server;
    private ObjectInputStream lector;
    private ObjectOutputStream escritor;
    private String user;
    private boolean running;
    
    public ClientThread(Server server, Socket clientSocket)
    {
        this.server = server;
        this.clientSocket = clientSocket;
        running = true;
        this.init();
        
        
    }
    
    public void init()
    {
        try {
            escritor = new ObjectOutputStream(clientSocket.getOutputStream());
            lector = new ObjectInputStream(clientSocket.getInputStream());
            Mensaje unMensaje = (Mensaje) lector.readObject();
            if (unMensaje.getTipo() == Mensaje.TIPO_NOMBRE_USUARIO) {
                user = unMensaje.getFrom();
            }
        } 
        catch (Exception e) 
        {
            
        }
    }
            
    
    @Override
    public void run() 
    {
        while(running)
        {
            try 
            {
                Mensaje msj = (Mensaje) lector.readObject();
                server.manejarMensaje(msj);
            } 
            catch (Exception ex) 
            {
                running = false;
            }
            finally
            {
               
            }
        }
    }
    
    public void send(Mensaje msj)
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
    
    public String getUser()
    {
        return user;
    }
    
    public void setUser(String user)
    {
        this.user = user;
    }
    
    public Socket getSocket()
    {
        return clientSocket;
    }
    
    public ObjectInputStream getObjectInputStream()
    {
        return lector;
    }
    
            
    
}
