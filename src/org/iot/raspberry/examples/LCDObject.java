/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iot.raspberry.examples;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import rmi.api.iot.LCD;

import org.iot.raspberry.grovepi.GrovePi;
import org.iot.raspberry.grovepi.devices.GroveRgbLcd;

/**
 *
 * @author andre
 */
public class LCDObject implements LCD{
    GrovePi grovePi;

    @Override
    public void mostrarMensaje(String mensaje) throws RemoteException {
         int[][] colors = new int[][]{
      {50, 255, 30},
      {15, 88, 245},
      {248, 52, 100},
      {48, 56, 190},
      {178, 25, 180},
      {210, 210, 210}
    };
        GroveRgbLcd lcd;
        try {
            lcd = grovePi.getLCD();
              int[] color = colors[new Random().nextInt(colors.length)];
        lcd.setRGB(color[0], color[1], color[2]);
        lcd.setText(mensaje);
        } catch (IOException ex) {
            Logger.getLogger(LCDObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public LCDObject(GrovePi grovePi) {
        this.grovePi = grovePi;
    }

    
}
