package utilities;

import com.google.inject.Injector;
import io.cucumber.guice.CucumberModules;
import io.cucumber.guice.InjectorSource;

public class TestInjectorSource implements InjectorSource {

    @Override
    public Injector getInjector() {
        return com.google.inject.Guice.createInjector(
                CucumberModules.createScenarioModule(),
                new FrameworkModule()
        );
    }
}
