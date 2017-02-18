package org.usfirst.frc.team334.robot;

import org.opencv.core.Mat;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CameraSet {

//	Button to switch between cameras
	int switchButton;
//	Multiplier to change resolution, default 160x120
	double multiplier = 2;
	boolean isToggled = false;

	Joystick joy;

	UsbCamera cam1;
	UsbCamera cam2;

    CvSource outputStream = CameraServer.getInstance().putVideo("camera_set", (int)(multiplier * 160), (int)(multiplier * 120));
    Mat source = new Mat();
    CvSink cvSink;

//  The default camera is cam1
    public CameraSet(Joystick joy, int switchButton, String devpath1, String devpath2) {
    	this.joy = joy;
    	this.switchButton = switchButton;
    	this.cam1 = CameraServer.getInstance().startAutomaticCapture("cam1", devpath1);
    	this.cam2 = CameraServer.getInstance().startAutomaticCapture("cam2", devpath2);

    	cam1.setResolution((int)(this.multiplier * 160), (int)(this.multiplier * 120));
		cam2.setResolution((int)(this.multiplier * 160), (int)(this.multiplier * 120));

		cvSink = CameraServer.getInstance().getVideo(cam1);
    }

	public void setResolution(double multiplier) {
		cam1.setResolution((int)(multiplier * 160), (int)(multiplier * 120));
		cam2.setResolution((int)(multiplier * 160), (int)(multiplier * 120));
	}

	public void enable() {
		new Thread(() -> {
			while(!Thread.interrupted()) {
				SmartDashboard.putString("current camera", cvSink.getSource().getName());

				cvSink.grabFrame(source);
				outputStream.putFrame(source);

				if(joy.getRawButton(switchButton)) {
					if(!isToggled) {
						isToggled = !isToggled;
						cvSink = CameraServer.getInstance().getVideo(cam2);
					}
				} else {
					if(isToggled) {
						isToggled = !isToggled;
						cvSink = CameraServer.getInstance().getVideo(cam1);
					}
				}
			}
		}).start();
	}
}
