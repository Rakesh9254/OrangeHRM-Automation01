package com.qa.util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

    public static ExtentReports initReport() {
        // Create a new reporter (generates HTML report)
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("reports/ExtentReport.html");
        sparkReporter.config().setReportName("Automation Report");
        sparkReporter.config().setDocumentTitle("QA Execution Results");

        // Attach reporter to ExtentReports
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        // Add system info
        extent.setSystemInfo("Project Name", "OrangeHRM Automation01");
        extent.setSystemInfo("Tester", "Rakesh Roshan");
        extent.setSystemInfo("Email_Id", "rakeshkumarroshan62@gmail.com");

        return extent;
    }
}
