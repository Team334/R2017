package org.usfirst.frc.team334.robot.util;

public class TrackTarget {

    // tracks # of frames that lost target
    private int lostFrames = 0;
    private int nFrames;

    public TrackTarget(int nFrames) {
        this.nFrames = nFrames;
    }

    // calculate number of frames target has been lost
    public void addTargetFound(boolean found) {
        lostFrames = (found) ? 0 : ++lostFrames;
    }

    /**
     * Checks if target was seen in last nFrames
     *
     * @return true number of frames lost was more than nFrames
      */
    public boolean lostTarget() {
        return lostFrames >= nFrames;
    }
}
