package sample.service;


import sample.model.Pos;
import sample.model.Vehicle;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;


public class TrackingService {

    //private final Logger logger = LoggerFactory.getLogger(TrackingService.class);

    public void justDoIt(float x, float y) throws IOException {
        final Vehicle mainVehicle = new Vehicle(1L, new Pos(10,10), new Stack<Pos>());
        //logger.debug("Created vehicle number '{}' with position '{}'", mainVehicle.getId(), mainVehicle.getCurrentPos());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final Pos newTarget = new Pos(x,y);
        mainVehicle.getTargetList().push(newTarget);

        moveVehicle(mainVehicle,1);
    }

    private void moveVehicle(Vehicle vehicle, int step) {
        final Pos to = vehicle.getTargetList().pop();
        while (true) {
            final Pos from = vehicle.getCurrentPos();
            final Pos unitVector = getUnitVector(from, to);
            final Pos currentPoint = vehicle.getCurrentPos();
            final Pos nextPoint = new Pos((currentPoint.getX() + unitVector.getX() * step),
                    (currentPoint.getY() + unitVector.getY() * step));
            vehicle.setCurrentPos(nextPoint);
            if (((nextPoint.getX() <= to.getX() + 0.5) && (nextPoint.getX() >= to.getX() - 0.5))
                    && ((nextPoint.getY() <= to.getY() + 0.5) && (nextPoint.getY() >= to.getY() - 0.5))) {
                vehicle.setCurrentPos(to);
                break;
            }
        }
    }

    private Pos getUnitVector(Pos from, Pos to) {
        final Pos pos = new Pos();
        pos.setX(to.getX() - from.getX());
        pos.setY(to.getY() - from.getY());
        final float length = (float) Math.sqrt(pos.getX()*pos.getX() + pos.getY()*pos.getY());
        pos.setX(pos.getX()/length);
        pos.setY(pos.getY()/length);
        return pos;
    }
}
