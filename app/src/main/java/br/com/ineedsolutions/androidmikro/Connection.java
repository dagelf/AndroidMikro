package br.com.ineedsolutions.androidmikro;

import javax.net.SocketFactory;

import me.legrange.mikrotik.ApiConnection;

/**
 * Created by jeferson on 15/04/18.
 */


abstract class Connection {

    protected void connect() throws Exception {
        con = ApiConnection.connect(SocketFactory.getDefault(), Config.HOST, ApiConnection.DEFAULT_PORT, 2000);
        con.login(Config.USERNAME, Config.PASSWORD);
        System.out.println("TESSSSSTEESTESTE");
        con.execute("/system/reboot");
    }

    protected void disconnect() throws Exception {
        con.close();
    }

    protected void comando() throws Exception{
        con.execute("/system/reboot");
    }

    protected ApiConnection con;

}