package org.iot.raspberry.examples;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.iot.raspberry.grovepi.GrovePi;
import org.iot.raspberry.grovepi.devices.GroveSoundSensor;
import rmi.api.iot.SensorRuido;

/**
 * @author andre
 */
public class SensorRuidoObject implements SensorRuido {
    GrovePi grovePi;
    int pin;

    @Override
    public Double obtenerRuido() throws RemoteException {
        double soundLevel = 0;
        try {
            GroveSoundSensor soundSensor = new GroveSoundSensor(grovePi, pin);
            soundLevel = soundSensor.get();
            System.out.println("Ruido Actual" + String.valueOf(soundLevel) + "db");
        } catch (IOException ex) {
            Logger.getLogger(SensorRuidoObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return soundLevel;
    }

    public SensorRuidoObject(GrovePi grovePi, int pin) {
        this.grovePi = grovePi;
        this.pin = pin;
    }
}

