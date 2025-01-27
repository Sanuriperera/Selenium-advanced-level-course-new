package com.pragmatic.selenium;

import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;




public class Log4j2LoggingTest {

    private static final Logger logger= LogManager.getLogger(Log4j2LoggingTest.class);

    @Test
    public void testLog4JLogging(){
        logger.trace("I am a trace log message");
        logger.debug("I am a debug log message ");
        logger.info("I am a info log message");
        logger.error("I am a error log message");
        logger.fatal("I am a fatal log message");
    }
}
