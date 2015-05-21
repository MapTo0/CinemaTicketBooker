package cinema.services;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.sql.rowset.serial.SerialException;

import cinema.utils.DatabaseUtils;


@Singleton
@Startup
public class TestDataInserter {
	
	@EJB
	private DatabaseUtils utils;
	
	public TestDataInserter() {
		
	}
	
	@PostConstruct
	public void init() throws SerialException {
		utils.addTestDataToDB();
	}

}
