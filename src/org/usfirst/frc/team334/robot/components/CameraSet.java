package org.usfirst.frc.team334.robot.components;

import org.opencv.core.Mat;
import org.usfirst.frc.team334.robot.controls.Controls;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CameraSet {

    //	Button to switch between cameras
    private int switchButton;
    //	Multiplier to change resolution, default 160x120
    private double multiplier = 2;
    private boolean isToggled = false;

    private Controls controls;

    private UsbCamera cam1;
    private UsbCamera cam2;

    private CvSource outputStream = CameraServer.getInstance().putVideo("camera_set", (int) (multiplier * 160), (int) (multiplier * 120));
    private Mat source = new Mat();
    private CvSink cvSink;

    //  The default camera is cam1
    public CameraSet(Controls controls, int switchButton, String devpath1, String devpath2) {
        this.controls = controls;
        this.switchButton = switchButton;
        this.cam1 = CameraServer.getInstance().startAutomaticCapture("cam1", devpath1);
        this.cam2 = CameraServer.getInstance().startAutomaticCapture("cam2", devpath2);

        cam1.setResolution((int) (this.multiplier * 160), (int) (this.multiplier * 120));
        cam2.setResolution((int) (this.multiplier * 160), (int) (this.multiplier * 120));

        cvSink = CameraServer.getInstance().getVideo(cam1);
    }

    public void setResolution(int multiplier) {
        cam1.setResolution(multiplier * 160, multiplier * 120);
        cam2.setResolution(multiplier * 160, multiplier * 120);
    }

    public void enable() {
        new Thread(() -> {
            while (!Thread.interrupted()) {
                SmartDashboard.putString("current camera", cvSink.getSource().getName());

                cvSink.grabFrame(source);
                outputStream.putFrame(source);

                if (controls.getToggleCamera() && !isToggled) {
                    isToggled = true;
                    cvSink = CameraServer.getInstance().getVideo(cam2);
                } else if (controls.getToggleCamera() && isToggled) {
                    isToggled = false;
                    cvSink = CameraServer.getInstance().getVideo(cam1);
                }
            }
        }).start();
    }
}
