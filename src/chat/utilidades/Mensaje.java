
package chat.utilidades;

import java.io.Serializable;

public class Mensaje implements Serializable
{
    public static final int TIPO_NOMBRE_USUARIO = 0;
    public static final int TIPO_MENSAJE = 1;
    public static final int TIPO_LOGOUT = 2;
    
    private int tipo;
    private String from;
    private String texto;
    private String to;
    
    public Mensaje(int tipo, String texto)
    {
        this.tipo = tipo;
        this.texto = texto;
    }
    
    public Mensaje(int tipo, String from, String texto, String to)
    {
        this.tipo = tipo;
        this.from = from;
        this.texto = texto;
        this.to = to;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return getFrom()+": "+getTexto();
    }
    
    
    
    
    
    
    
}
