import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.TagFilter;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;
import tests.TestParent;

import java.util.List;

import static org.junit.platform.engine.discovery.DiscoverySelectors.*;

public class TestsRunClass {

    public static void main(String[] args) {
        String[] filters = {"syntax", "functionality"};
        if (args.length > 0) {
            filters = args;
        }

        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                .selectors(
                        selectPackage(String.valueOf(TestParent.class.getPackage().getName())))
                .filters(
                        TagFilter.includeTags(filters)
                )
                .build();

        Launcher launcher = LauncherFactory.create();
        SummaryGeneratingListener listener = new SummaryGeneratingListener();
        launcher.registerTestExecutionListeners(listener);
        launcher.execute(request);

        TestExecutionSummary summary = listener.getSummary();
        long testFoundCount = summary.getTestsFoundCount();
        List<TestExecutionSummary.Failure> failures = summary.getFailures();
        System.out.println("Founding tests: " + testFoundCount);
        System.out.println("Succeeded tests: " + summary.getTestsSucceededCount());
        failures.forEach(failure -> System.out.println("failure - " + failure.getException()));
    }
}