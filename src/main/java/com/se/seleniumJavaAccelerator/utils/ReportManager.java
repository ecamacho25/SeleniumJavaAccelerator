package com.se.seleniumJavaAccelerator.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ReportManager {
    private static ExtentReports extent;
    private static ExtentSparkReporter sparkReporter;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static void setUpReport(){
        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/ExtentReport.html");
        sparkReporter.config().setDocumentTitle("Automation Report");
        sparkReporter.config().setReportName("Functional Testing");
        sparkReporter.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }

    public static synchronized void startTest(String testName, String description, String[] categories) {
        ExtentTest extentTest = extent.createTest(testName, description);
        
        if (categories != null) {
            for (String category : categories) {
                extentTest.assignCategory(category);
            }
        }
        
        test.set(extentTest);
    }

    public static synchronized void logInfo(String message) {
        test.get().info(message);
    }

    public static synchronized void logPass(String message) {
        test.get().pass(message);
    }

    public static synchronized void logFail(String message) {
        test.get().fail(message);
    }

    public static synchronized void tearDown() {
        if (extent != null) {
            extent.flush();
        }
    }
}


