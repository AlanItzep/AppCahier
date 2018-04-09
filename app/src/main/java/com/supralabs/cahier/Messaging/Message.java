package com.supralabs.cahier.Messaging;

import java.util.Date;

/**
 * Created by SONY on 21/09/2017.
 */

//#####################################################
//################### MENSAJEDETEXTO ##################
//#####################################################
public class Message {
    private String id;
    private String mensaje;
    private int tipoDeMensaje;
    private String horaDelMensaje;

    public Message() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getTipoDeMensaje() {
        return tipoDeMensaje;
    }

    public void setTipoDeMensaje(int tipoDeMensaje) {
        this.tipoDeMensaje = tipoDeMensaje;
    }

    public String getHoraDelMensaje() {
        Date horaActual = new Date();
        horaDelMensaje = horaActual.getHours()+":"+ horaActual.getMinutes();
        return horaDelMensaje;
    }

    public void setHoraDelMensaje(String horaDelMensaje){
        this.horaDelMensaje = horaDelMensaje;
    }
}
