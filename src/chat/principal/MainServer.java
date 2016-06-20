package chat.principal;

//import chat.controladores.ChatServer;

import chat.controladores.Server;


/**
 *
 * @author Silvana
 */
public class MainServer 
{
    public static void main(String[] args) {
        Server server = new Server(5001);
        server.startServer();
        
    }
}
