package com.gcu.edu.web.service;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import com.gcu.edu.business.AccelSensorServiceInterface;
import com.gcu.edu.exception.DAOException;
import com.gcu.edu.model.AccelSensorModel;
import com.gcu.edu.model.ResponseDataModel;
import com.gcu.edu.model.ResponseModel;

import org.slf4j.Logger;

/**
 * Code retrieved and modified from Mark Reha's Cloud Computing code This class
 * is REST Service that implements the REST API to support the Weather Sensor
 * IoT.
 * 
 * @author Mark Mott
 * @version 1
 */
@Path("accel")
public class RestService {
	Logger logger = LoggerFactory.getLogger(RestService.class);
	static AccelSensorServiceInterface service;

	/**
	 * Test Service API at /test using HTTP GET.
	 * 
	 * @return Response Model
	 */
	@GET
	@Path("/test")
	@Produces("application/json")
	public ResponseModel test() {
		// Log the API call
		// TODO: Set up logger
		// logger.info("Entering RestService.test()");

		// Return a Test Response
		ResponseModel response = new ResponseModel(0, "This is a test");
		return response;
	}

	/**
	 * Save Sensor Data API at /save using HTTP POST.
	 * 
	 * @param model
	 *            The Weather Data to save.
	 * @return Response Model with error code of 1 for no error, 0 if save failed,
	 *         -2 if database error
	 */
	@GET
	@Path("/save")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ModelAndView saveAccelSensorData(AccelSensorModel model) {
		try {
			// Log the API call
			// TODO: Set up logger
			//logger.info("Entering RestService.saveAccelSensorData()");
			//logger.debug("Model: " + model.toString());

			// Insert the model into the queue
			boolean OK = service.saveAccelSensorData(model);

			// Return OK Response
			ResponseModel response = new ResponseModel(OK ? 1 : 0, OK ? "OK" : "Error");
			return new ModelAndView("output", "response", response);
		} catch (DAOException e) {
			// Return Database Exception Response
			ResponseModel response = new ResponseModel(-2, "Database Exception: " + e.getMessage());
			return null;
		}
	}

	/**
	 * Get Sensor Data for a specified Sensor Device ID at /get/{device}/{id} using
	 * HTTP GET.
	 * 
	 * @param deviceID
	 *            Device ID to query Sensor Data from
	 * @param id
	 *            Sensor ID to return
	 * @return Response Model with error code of 0 for no error, -1 if no sensor
	 *         data found, -2 if database error
	 */
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseDataModel getAccelSensorData() {
		// Get data from python code
		try {
			// Log the API call
			// TODO: Set up logger
			//logger.info("Entering RestService.getAccelSensorData()");

			// Call Business Service to get the Sensor Data for a specified Sensor Data ID
			AccelSensorModel data = service.getAccelSensorData();

			// Return Response and Data
			int returnError = 0;
			String returnMessage = "OK";
			List<AccelSensorModel> returnData = new ArrayList<AccelSensorModel>();
			returnData.add(data);
			ResponseDataModel response = new ResponseDataModel(returnError, returnMessage, returnData);
			return response;
		} catch (NotFoundException e) {
			// Return Database Error Response
			ResponseDataModel response = new ResponseDataModel(-1, "Sensor Data Not Found" + e.getMessage(),
					new ArrayList<AccelSensorModel>());
			return response;
		} catch (DAOException e) {
			// Return Database Error Response
			ResponseDataModel response = new ResponseDataModel(-2, "Database Exception: " + e.getMessage(),
					new ArrayList<AccelSensorModel>());
			return response;
		}
	}

	// ***** Dependencies and Helper Functions *****

	/**
	 * IoC helper function.
	 * 
	 * @param service
	 *            WeatherServiceInterface to inject into this service
	 *            implementation.
	 */
	public void setService(AccelSensorServiceInterface service) {
		RestService.service = service;
	}
}