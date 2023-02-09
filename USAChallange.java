package DemoPrograms;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class USAChallange {

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "D:\\EclipseWorkspace\\House\\Drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://petdiseasealerts.org/forecast-map/#/");

		System.out.println(driver.getTitle());
		Thread.sleep(5000);

		//driver.switchTo().frame(0); // select first frame
		
		//did not select iframe id because it's dynamic 
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='capc-map-embed']/iframe")));
		System.out.println("switched to frame");


		String ExpectedState = "California";
		List<WebElement> States = driver.findElements(By.cssSelector(".rpath path "));
		System.out.println("States count = " + States.size());

		for (WebElement state : States) {
			

			String name = state.getAttribute("name");
			System.out.println("=>"+name);
			if (ExpectedState.equals(name)) {
				state.click();
			}
		}

		/* validating is expected is equal to actual*/
		
		String ActualSelectedstate = driver.findElement(By.xpath("//uL[@class='breadcrumb']/li[2]/span")).getText().toLowerCase();
		if (ExpectedState.toLowerCase().equals(ActualSelectedstate)) {
			System.out.println("Correct state is selected:" + ExpectedState);
		} else {
			System.out.println("Incorrect state is selected: " + ActualSelectedstate);
		}
	}

}
