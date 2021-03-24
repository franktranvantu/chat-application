/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.client;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

public class ClientThread implements Runnable {

    private DataInputStream input;
    private JTextArea txtFromServer;

    public ClientThread(DataInputStream input, JTextArea txtFromServer) {
        this.input = input;
        this.txtFromServer = txtFromServer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String fromServerText = input.readUTF();
                String display = String.format("Server: %s%n", fromServerText);
                StringBuilder sb = new StringBuilder();
                sb.append(txtFromServer.getText());
                sb.append(display);
                txtFromServer.setText(sb.toString());
            }
        } catch (IOException ex) {
            Logger.getLogger(ClientForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
