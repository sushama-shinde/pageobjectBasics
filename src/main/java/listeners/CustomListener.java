package listeners;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;

import com.relevantcodes.extentreports.LogStatus;

import base.Page;
import utilities.MonitoringMail;
import utilities.TestConfig;
import utilities.Utilities;

//ITestListener is called for test cases listener
//ISuiteListener is called for test suite completed

public class CustomListener extends Page implements ITestListener, ISuiteListener {

	public static String messageBody;
	public void onTestStart(ITestResult result) {

		// while generating extent report you should to tell report to stat test case
		test = report.startTest(result.getName().toUpperCase());

		// Runmode=Y,check test case will execute or not
		if (!Utilities.isTestRunnable(result.getName(), excel)) {
			throw new SkipException(
					"Skipping the test case " + result.getName().toUpperCase() + " as the run mode is NO");
		}
	}

	public void onTestSuccess(ITestResult result) {

		// for extent reports
		test.log(LogStatus.PASS, result.getName().toUpperCase() + " PASS");
		report.endTest(test);
		report.flush();
		// --------
	}

	public void onTestFailure(ITestResult result) {

		// for screen shot capture
		System.setProperty("org.uncommons.reportng.escape-output", "false"); // set flag to display image insted of html
																				// code(link)
		Reporter.log("Click to see Screenshot");
		try {
			Utilities.captureScreenshot();
		} catch (IOException e) {

			e.printStackTrace();
		}
		test.log(LogStatus.FAIL, result.getName().toUpperCase() + " Failed.." + result.getThrowable());// for extent
																										// report
		// after generating extent report capture screenshot
		test.log(LogStatus.FAIL, test.addScreenCapture(Utilities.screenshotName));

		// Hard coded path for img Reporter.log("<a target=\"_blank\"
		// href=\"D:\\Selenium\\error.jpg\">Screenshot </a>");
		Reporter.log("Click to see screenshot");
		Reporter.log("<a target=\"_blank\" href=" + Utilities.screenshotName + ">Screenshot </a>");
		Reporter.log("<br>");
		Reporter.log("<br>");
		Reporter.log("<a target=\"_blank\"href=" + Utilities.screenshotName + "><img src=" + Utilities.screenshotName
				+ " height=200 width=200></img> </a>");
		// --------------
		report.endTest(test);
		report.flush();
	}

	public void onTestSkipped(ITestResult result) {

		test.log(LogStatus.SKIP, "Skipped the test case " + result.getName().toUpperCase() + " as the run mode is NO");
		report.endTest(test);
		report.flush();
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}

	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub

	}

	public void onFinish(ISuite suite) {
		/*MonitoringMail mail = new MonitoringMail();

		try {
			messageBody = "http://" + InetAddress.getLocalHost().getHostAddress()
					+ ":8080/job/DataDrivenLiveProject/HTML_20Report/";
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, messageBody);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

	}

}
