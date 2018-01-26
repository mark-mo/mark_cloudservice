package com.gcu.edu.dao;

import java.util.LinkedList;
import java.util.Queue;

import com.gcu.edu.model.AccelSensorModel;

public class AccelSensorDAO implements AccelSensorDAOInterface {

	Queue<AccelSensorModel> queue = new LinkedList<>();

	@Override
	public boolean saveAccelSensorData(AccelSensorModel sensorModel) {
		return queue.add(sensorModel);
	}

	@Override
	public AccelSensorModel getAccelSensorData() {
		if (!queue.isEmpty()) {
			return queue.poll();
		}
		return null;
	}

}
