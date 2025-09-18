package ge.tbc.testautomation.runners;

import com.microsoft.playwright.*;
import org.testng.annotations.*;

public class CrossBrowser {
    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    @BeforeTest
    @Parameters({"browserType"})
    public void setUp(String browserType) {
        playwright = Playwright.create();

        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions()
                .setHeadless(false);

        if (browserType.equals("chrome")) {
            browser = playwright.chromium().launch(launchOptions);
        } else if (browserType.equals("firefox")) {
            browser = playwright.firefox().launch(launchOptions);
        } else if (browserType.equals("webkit")) {
            browser = playwright.webkit().launch(launchOptions);
        } else {
            browser = playwright.chromium().launch(launchOptions);
        }

        Browser.NewContextOptions contextOptions = new Browser.NewContextOptions()
                .setViewportSize(1920, 1080);

        context = browser.newContext(contextOptions);
        context.setDefaultTimeout(10000);

        page = context.newPage();
    }

    @AfterTest
    public void afterTest() {
        if (page != null) {
            page.close();
        }
        if (context != null) {
            context.close();
        }
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }
}