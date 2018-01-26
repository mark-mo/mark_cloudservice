package com.gcu.edu.business;

import com.gcu.edu.model.AccelSensorModel;

public interface AccelSensorServiceInterface {
	public boolean saveAccelSensorData(AccelSensorModel sensorModel);
	public AccelSensorModel getAccelSensorData();
}
