/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.server;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

public class ServerThread implements Runnable {

    private DataInputStream input;
    private JTextArea txtFromClient;

    public ServerThread(DataInputStream input, JTextArea txtFromClient) {
        this.input = input;
        this.txtFromClient = txtFromClient;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String fromClientText = input.readUTF();
                String display = String.format("Client: %s%n", fromClientText);
                StringBuilder sb = new StringBuilder();
                sb.append(txtFromClient.getText());
                sb.append(display);
                txtFromClient.setText(sb.toString());
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
