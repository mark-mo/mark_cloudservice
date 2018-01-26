package com.gcu.edu.dao;

import com.gcu.edu.model.AccelSensorModel;

public interface AccelSensorDAOInterface {
	public boolean saveAccelSensorData(AccelSensorModel sensorModel);
	public AccelSensorModel getAccelSensorData();
}
