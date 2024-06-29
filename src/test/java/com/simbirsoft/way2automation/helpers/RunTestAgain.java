package com.simbirsoft.way2automation.helpers;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RunTestAgain implements IRetryAnalyzer {
    private static final int maxCount = 1;

    private int nowCount = 0;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (nowCount < maxCount) {
            nowCount++;
            return true;
        }
        System.out.println("ТЕСТ ПРОВАЛЕН ДВАЖДЫ!!! ");
        nowCount = 0;
        return false;
    }
}
