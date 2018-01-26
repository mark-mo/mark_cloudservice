package com.gcu.edu.business;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcu.edu.dao.AccelSensorDAO;
import com.gcu.edu.model.AccelSensorModel;

public class AccelSensorService implements AccelSensorServiceInterface {
   AccelSensorDAO dao;

   /**
    * 
    */
   @Override
   public boolean saveAccelSensorData(AccelSensorModel sensorModel) {
      return dao.saveAccelSensorData(sensorModel);
   }

   /**
    * 
    */
   @Override
   public AccelSensorModel getAccelSensorData() {
      return dao.getAccelSensorData();
   }
	
   /**
    * 
    * @param dao
    */
   public void setOrdersDataService(AccelSensorDAO dao) {
      this.dao = dao;
   }
}
