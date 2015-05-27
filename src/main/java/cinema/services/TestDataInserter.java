package cinema.services;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.servlet.ServletException;

import cinema.utils.DatabaseUtils;

/**
 * Session Bean implementation class TestDataInserter
 */
@Singleton
@Startup
public class TestDataInserter {
    
    @EJB
    private DatabaseUtils utils;
    
    public TestDataInserter() {
    }
    
    @PostConstruct
    public void init() throws ServletException {
        utils.addTestDataToDB();
    }
}
