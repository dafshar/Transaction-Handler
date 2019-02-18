package com.transactionhandler.policy;


import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 
import org.springframework.batch.core.step.skip.SkipLimitExceededException; 
import org.springframework.batch.core.step.skip.SkipPolicy; 
 

public class ProcessorSkipPolicy implements SkipPolicy { 
 
    private final Logger logger = LoggerFactory.getLogger(this.getClass()); 
    private int skipLimit=5;
 
    @Override 
    public boolean shouldSkip(Throwable t, int skipCount) throws SkipLimitExceededException { 
        logger.debug("shouldSkip:" + t.toString()); 
        if (t instanceof Exception && skipCount <= skipLimit) { 
            return true; 
        } else { 
            return false; 
        } 
    } 
}
