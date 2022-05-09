package resman.qa.resources;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class reTryAnalyzer implements IRetryAnalyzer {
//Starting from 0
	int count = 0;
	int retryCount = 1;
	

	@Override
	public boolean retry(ITestResult result) {
		while(count<retryCount) {
			return true;
		}
		return false;
	}
}
