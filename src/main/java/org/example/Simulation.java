package org.example;

import org.example.services.InitAction;
import org.example.services.ChooserMap;
import org.example.services.Renderer;
import org.example.services.TurnAction;
import org.example.statics.WorldMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Simulation extends Thread {
    private final int mapSize = ChooserMap.chooseMapSize();
    private final WorldMap worldMap = new WorldMap(mapSize);
    private final Renderer renderer = new Renderer(worldMap);
    private final InitAction initAction = new InitAction(worldMap);
    private final TurnAction turnAction = new TurnAction(worldMap);
    private boolean running = false;
    private int step;
    private final Logger logger = LoggerFactory.getLogger(Simulation.class);


    public void initSimulation() {
        initAction.initActions();
        logger.info("Init Simulation");
        System.out.println("Init Simulation");
        renderer.render();
        this.startSimulation();
    }

    public void startSimulation() {
        logger.info("Start Simulation.");
        this.running = true;
        this.start();
    }

    @Override
    public void run() {
        while (running) {
            step++;
            System.out.println("-----------------------");
            System.out.println("Write any symbol for stopping the simulation.");
            System.out.printf("Step : %d\r\n", step);
            this.nextTurn();
            try {
                this.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void nextTurn() {
        int quantityStepsForAddEntities = 7;
        if (step % quantityStepsForAddEntities == 0) {
            turnAction.checkAndAddEntities();
        }
        turnAction.turnActions();
        renderer.render();
    }

    public void stopSimulation() {
        this.running = false;
        logger.info("Stop Simulation");
    }
}
