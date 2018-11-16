/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iot.raspberry.examples;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.iot.raspberry.grovepi.GroveDigitalOut;
import org.iot.raspberry.grovepi.GrovePi;
import rmi.api.iot.Led;
/**
 *
 * @author andre
 */
public class LedObject implements Led{
GrovePi grovePi;
int pin ;
    @Override
    public void encenderLed() throws RemoteException {
        
    GroveDigitalOut led;
    try {
        led = grovePi.getDigitalOut(pin);
        led.set(true);
        //Thread.sleep(500);
        //led.set(false);
    } catch (IOException ex) {
        Logger.getLogger(LedObject.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    
    @Override
    public void apagarLed() throws RemoteException {
         GroveDigitalOut led;
    try {
        led = grovePi.getDigitalOut(pin);
        led.set(false);

    } catch (IOException ex) {
        Logger.getLogger(LedObject.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    public LedObject(GrovePi grovePi,int pin) {
        this.grovePi = grovePi;
        this.pin = pin;
    }


    
    
}
