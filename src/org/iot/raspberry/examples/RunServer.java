package org.iot.raspberry.examples;

import java.io.File;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.iot.raspberry.grovepi.GrovePi;
import org.iot.raspberry.grovepi.pi4j.GrovePi4J;
import rmi.api.iot.LCD;
import rmi.api.iot.Led;
import rmi.api.iot.SensorRuido;

/**
 * @author andre
 */
public class RunServer {
    public static void main(String[] args) throws Exception {
        GrovePi grovePi = new GrovePi4J();
        LedObject led = new LedObject(grovePi, 2);
        LCDObject lcd = new LCDObject(grovePi);
        SensorRuidoObject sensorRuido = new SensorRuidoObject(grovePi, 0);
        Registry registry = LocateRegistry.createRegistry(2526);
        System.out.println("RUNNING Server: ");   
        Led stub_led = (Led) UnicastRemoteObject.exportObject(led, 0);
        registry.rebind("Led", stub_led);
        LCD stub_lcd = (LCD) UnicastRemoteObject.exportObject(lcd,0);
        registry.rebind("lcd", stub_lcd);
        SensorRuido stub_s_ruido = (SensorRuido) UnicastRemoteObject.exportObject(sensorRuido,0);
        registry.rebind("s_ruido", stub_s_ruido);
        
    }

}
