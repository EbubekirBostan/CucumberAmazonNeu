package utilities;

import com.google.inject.AbstractModule;
import org.openqa.selenium.WebDriver;

public class FrameworkModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(WebDriver.class).toProvider(DriverProvider.class).asEagerSingleton();
    }
}
