package com.simbirsoft.way2automation.helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RunTestAgain implements IRetryAnalyzer {
    private static final int maxCount = 1;
    private static final Logger logger = LoggerFactory.getLogger(RunTestAgain.class);

    private int nowCount = 0;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (nowCount < maxCount) {
            nowCount++;
            return true;
        }
        logger.error("ТЕСТ ПРОВАЛЕН ДВАЖДЫ!!! ");
        nowCount = 0;
        return false;
    }
}
