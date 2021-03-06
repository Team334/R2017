package org.usfirst.frc.team334.robot.components;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.opencv.core.Mat;
import org.usfirst.frc.team334.robot.controls.Controls;

public class CameraSet {

    // Multiplier to change resolution, default is 160x120
    private final double multiplier = 1;
    private boolean isToggled = false;

    // Toggle once for every button click(accounts for button hold)
    private boolean buttonHeld = false;

    private Controls controls;

    private UsbCamera cam1;
    private UsbCamera cam2;

    private CvSource outputStream;
    private Mat source;
    private CvSink cvSink;

    // The default camera is cam1
    public CameraSet(Controls controls, String devpath1, String devpath2) {
        this.controls = controls;
        this.cam1 = CameraServer.getInstance().startAutomaticCapture("Back", devpath2);
        this.cam2 = CameraServer.getInstance().startAutomaticCapture("Front", devpath1);

//        cam1.setResolution((int) (this.multiplier * 160), (int) (this.multiplier * 120));
//        cam2.setResolution((int) (this.multiplier * 160), (int) (this.multiplier * 120));

        outputStream = CameraServer.getInstance().putVideo("camera_set", (int) (multiplier * 160), (int) (multiplier * 120));
        source = new Mat();

    }

    public void setResolution(int multiplier) {
        cam1.setResolution(multiplier * 160, multiplier * 120);
        cam2.setResolution(multiplier * 160, multiplier * 120);
    }

    public void enable() {
        new Thread(() -> {
            cvSink = CameraServer.getInstance().getVideo(cam2);
            while (!Thread.interrupted()) {
                SmartDashboard.putString("Camera", cvSink.getSource().getName());

                cvSink.grabFrame(source);
                outputStream.putFrame(source);

                if (controls.getToggleCamera() && !buttonHeld && !isToggled) {
                    isToggled = true;
                    cvSink = CameraServer.getInstance().getVideo(cam2);
                    System.out.println("toggled");
                } else if (controls.getToggleCamera() && !buttonHeld && isToggled) {
                    isToggled = false;
                    cvSink = CameraServer.getInstance().getVideo(cam1);
                    System.out.println("toggled");
                }
                buttonHeld = controls.getToggleCamera();
            }
        }).start();
    }

}
