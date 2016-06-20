package chat.controladores;

import chat.utilidades.Mensaje;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class Server 
{
    private Socket socket;
    private ServerSocket serverSocket;
    private int port;
    private ObjectOutputStream escritor;
    private ObjectInputStream lector;
    private ArrayList clientes;
    //private Thread serverThread;
    
    public Server(int port)
    {
        this.port = port;
        clientes = new ArrayList();
    }
    
    public void startServer()
    {
        
        try 
        {
            serverSocket = new ServerSocket(port);
            
            while(true)
            {                
                socket = serverSocket.accept();
                ClientThread clientThread = new ClientThread(this, socket);
                clientes.add(clientThread);

                Thread serverThread = new Thread(clientThread);
                serverThread.start();
            }
        } 
        catch (IOException ex) 
        {
            ex.printStackTrace();
        }
    }

    public void manejarMensaje(Mensaje msj)
    {
        if (msj.getTipo() == Mensaje.TIPO_LOGOUT) 
        {
            int id = buscarID(msj.getFrom());
            System.out.println("Manejar mensaje id: "+id+" "+msj.toString());
            removerConexion(id);
        }
        else
        {
            enviarATodos(msj);
        }
        
    }
    
    public  void enviarATodos(Mensaje msj)
    {
        synchronized(clientes)
        {
            Iterator it = clientes.iterator();
            while(it.hasNext())
            {
                ClientThread client = (ClientThread) it.next();
                client.send(msj);
            }
        }
        
    }
    
    public int buscarID(String user)
    {
        
        int id = -1;
        for (int i = 0; i < clientes.size(); i++) 
        {
            ClientThread clientThread = (ClientThread) clientes.get(i);
            if (clientThread.getUser().equals(user)) {
                id = i;
            }
        }
        return id;
    }
    
    public void removerConexion(int id)
    {
        synchronized(clientes)
        {
            ClientThread clienteAEliminar = (ClientThread) clientes.get(id);
            Socket socketClienteAEliminar = clienteAEliminar.getSocket();
            ObjectInputStream lectorCliente = clienteAEliminar.getObjectInputStream();
            clientes.remove(clienteAEliminar);
            try 
            {
                lectorCliente.close();
                socketClienteAEliminar.close();
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
            } 
        }
        
    }
    
    
}
