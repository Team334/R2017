package org.usfirst.frc.team334.robot.util;

import java.util.Arrays;

public class TrackTarget {

    // tracks # of frames that lost target
    private int counter = 0;
    private int nFrames;

    public TrackTarget(int nFrames) {
        this.nFrames = nFrames;
    }

    // calculate number of frames target has been lost
    public void addTargetFound(boolean found) {
        counter = (!found) ? counter++ : 0;
    }

    /**
     * Checks if target was seen in last nFrames
     *
     * @returns true number of frames lost was more than nFrames
      */
    public boolean lostTarget() {
        return counter >= nFrames;
    }
}
