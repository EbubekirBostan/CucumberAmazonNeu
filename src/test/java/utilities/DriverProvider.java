package utilities;

import com.google.inject.Provider;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;
import org.openqa.selenium.MutableCapabilities;

public class DriverProvider implements Provider<WebDriver> {

    @Override
    public WebDriver get() {

        String gridUrl = ConfigReader.getProperty("grid.url");
        String browser = ConfigReader.getProperty("browser");

        try {


            if (gridUrl != null && !gridUrl.trim().isEmpty()) {
                MutableCapabilities caps = new MutableCapabilities();
                caps.setCapability("browserName", browser == null ? "chrome" : browser);

                return new RemoteWebDriver(new URL(gridUrl), caps);
            }

        } catch (Exception e) {
            throw new RuntimeException("RemoteWebDriver bağlanamadı: " + e.getMessage(), e);
        }

        // 🔹 Local Chrome
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }
}
