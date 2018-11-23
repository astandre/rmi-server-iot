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
        //File control = new File("RUNNINGSAMPLES");
        //control.deleteOnExit();
        //Example example = (Example) Class.forName("org.iot.raspberry.examples.encenderledobject" ).newInstance();
        System.out.println("RUNNING EXAMPLE: " + args[0] + " USING: PI4J");
        final ExecutorService runner = Executors.newSingleThreadExecutor();
        //final ExecutorService consoleMonitor = Executors.newSingleThreadExecutor();
        final ExecutorService fileMonitor = Executors.newSingleThreadExecutor();
        final Semaphore lock = new Semaphore(0);
        final Monitor monitor = new Monitor();
        LedObject led = new LedObject(grovePi, 2);
        LCDObject lcd = new LCDObject(grovePi);
        SensorRuidoObject sensorRuido = new SensorRuidoObject(grovePi, 2);
        Registry registry = LocateRegistry.createRegistry(2525);

        runner.execute(() -> {
            try {
                System.out.println(args[0]);
                Led stub = (Led) UnicastRemoteObject.exportObject(led, 0);
                registry.rebind("Led", stub);
                //LCD stub2 = (LCD) UnicastRemoteObject.exportObject(lcd,0);
                //SensorRuido stub3 = (SensorRuido) UnicastRemoteObject.exportObject(sensorRuido,0);

            } catch (Exception ex) {
                Logger.getLogger(Runner.class.getName()).log(Level.SEVERE, null, ex);
            }
            lock.release();
        });

        //consoleMonitor.execute(() -> {
        //try (Scanner scanner = new Scanner(System.in)) {
        // String command;
        //while (!(command = scanner.next()).equalsIgnoreCase("quit")) {
        // System.out.println("Command " + command + " not recognized, try quit");
        // }
        // }
        // monitor.stop();
        // lock.release();
        // });

        // fileMonitor.execute(() -> {
        // while (control.exists()) {
        /// try {
        // Thread.sleep(100);
        // } catch (InterruptedException ex) {
        // }
        // }
        //monitor.stop();
        //lock.release();
        //});

        lock.acquire();
        runner.shutdown();
        //consoleMonitor.shutdownNow();
        fileMonitor.shutdownNow();
        runner.awaitTermination(10, TimeUnit.SECONDS);
        // control.delete();
        //System.exit(0);
    }

}
