package org.usfirst.frc.team334.robot.util;

import java.util.Arrays;

public class TrackTarget {

    private boolean[] targetFound;

    public TrackTarget(int nImages) {
        this.targetFound = new boolean[nImages];
        Arrays.fill(targetFound, true);
    }

    // add value to front of array
    public void addTargetFound(boolean value) {
        for (int i = 1; i < targetFound.length; i++) {
            targetFound[i] = targetFound[i - 1];
        }
        targetFound[0] = value;
    }

    /**
     * Checks if target was seen in last nImages
     *
     * @returns true if target was seen recently, else false
      */

    public boolean lostTarget() {
        for (boolean found : targetFound) {
            if (found) {
                return true;
            }
        }
        return false;
    }
}
