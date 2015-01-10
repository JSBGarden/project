package com.project.remoteserver;

import java.awt.AWTException;
import java.awt.Robot;

public class KeybordEvents {
	public Robot robot;
	
	//consturctor
	public KeybordEvents(Robot robot) {
		this.robot=robot;
	}
	
	
	//if single key is pressed
	public void keyPress(int key1){
		  try {     
		            Robot robot = new Robot();
		            robot.keyPress(key1);
		            robot.keyRelease(key1);
		        }
		  catch (AWTException e) {
		            e.printStackTrace();
		  }
	}
	
	// if two key are pressed as once
	public void keyPress(int key1,int key2){
		  try {
		            Robot robot = new Robot();
		            robot.keyPress(key1);
		            robot.keyPress(key2);
		            robot.keyRelease(key2);
		            robot.keyRelease(key1);
		        }
		  catch (AWTException e) {
		            e.printStackTrace();
		  }
	}
	
}
