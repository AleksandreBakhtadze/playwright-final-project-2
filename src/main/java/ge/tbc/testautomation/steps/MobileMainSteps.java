package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import ge.tbc.testautomation.data.DatabaseSteps;
import ge.tbc.testautomation.data.TBCContants;
import ge.tbc.testautomation.pages.MainPage;
import ge.tbc.testautomation.pages.MobileMainPage;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class MobileMainSteps {


    private final Page page;
    private final MobileMainPage mobileMainPage;
    private final TBCContants tbcContants = new TBCContants();
    private final DatabaseSteps databaseSteps = new DatabaseSteps();

    public MobileMainSteps(Page page) {
        this.page = page;
        mobileMainPage = new MobileMainPage(page);
    }

    public MobileMainSteps openMainPage() {
        page.navigate(tbcContants.mainPageLink);
        return this;
    }

    public MobileMainSteps acceptCookies() {
        try {
            assertThat(mobileMainPage.cookiesAcceptButton).isVisible();
            if (mobileMainPage.cookiesAcceptButton.isVisible()) {
                mobileMainPage.cookiesAcceptButton.click();
            }
        } catch (Exception e) {// Cookie button not visible, continue
        }
        return this;
    }
    public MobileMainSteps setMobileResolution() {
        page.setViewportSize(375, 667);
        page.waitForTimeout(3000);
        captureScreenshot("step1_initial_mobile_layout.png", "საიტი ჩატვირთულია მობილურის Viewport-ით");
        return this;
    }

    public MobileMainSteps openHamburgerMenu() {
        assertThat(mobileMainPage.hamburgerMenuButton).isVisible();
        mobileMainPage.hamburgerMenuButton.click();
        page.waitForTimeout(1000);
        captureScreenshot("step2_hamburger_menu_expanded.png", "ჰამბურგერ მენიუ გახსნილია და ოფციები ჩამოშლილი");
        return this;
    }

    public MobileMainSteps checkMegaMenuMainItems() {
        for (int i = 0; i < tbcContants.mainNavigationItems.length; i++) {
            assertThat(mobileMainPage.megaMenuMainNavigationItems.nth(i)).isVisible();
            assertThat(mobileMainPage.megaMenuMainNavigationItems.nth(i)).containsText(tbcContants.mainNavigationItems[i]);
        }
        return this;
    }

    public int getSubNavItemsCount() {
        return mobileMainPage.megaMenuMainNavigationItems.count();
    }

    public MobileMainSteps validateSubNavItems(int i) {
        assertThat(mobileMainPage.megaMenuMainNavigationItems.nth(i)).isVisible();
        assertThat(mobileMainPage.megaMenuMainNavigationItems.nth(i)).containsText(tbcContants.mainNavigationItems[i]);
        return this;
    }

    public MobileMainSteps clickSubNavItem(int i){
        mobileMainPage.megaMenuMainNavigationItems.nth(i).click(new Locator.ClickOptions().setForce(true));
        return this;
    }

    public MobileMainSteps checkSubNavItemColor(int i){
        assertThat(mobileMainPage.megaMenuMainNavigationItems.nth(i))
                .hasCSS("color", java.util.regex.Pattern.compile("rgba?\\(0,\\s*173,\\s*238.*\\)|rgb\\(0,\\s*173,\\s*238\\)"));
        page.waitForTimeout(700);
        return this;
    }

    public int getSubItemsCount() {
        return mobileMainPage.subNavigationItems.count();
    }

    public MobileMainSteps checkSubItemText(int i,int j){
        assertThat(mobileMainPage.subNavigationItems.nth(j))
                .containsText(tbcContants.subNavigationItems[i][j]);
        return this;
    }

    public MobileMainSteps clickSubItem(int i,int j){
        if (mobileMainPage.subNavigationItems.nth(j).textContent().contains("chevron-down-outlined")) {
            mobileMainPage.subNavigationItems.nth(j).click();
        }
        return this;
    }

    public void closeHamburgerMenu() {
        assertThat(mobileMainPage.hamburgerMenuButton).isVisible();
        mobileMainPage.hamburgerMenuButton.click();
        page.waitForTimeout(1000);
        captureScreenshot("step2_hamburger_menu_closed.png", "ჩამოშლილი ჰამბურგერ მენიუ აიკეცა");
    }

    public MobileMainSteps selectHeader() {
        assertThat(mobileMainPage.header).isVisible();
        assertThat(mobileMainPage.header).hasCSS("position", "sticky");
        captureScreenshot("step3_sticky_header.png", "sticky header სქროლვის შემდგომაც ჩანს");
        return this;
    }

    public MobileMainSteps scrollIntoView() {
        page.evaluate("window.scrollBy(0, 1500)");
        page.waitForTimeout(3000);
        page.evaluate("window.scrollBy(0, -500)");
        page.waitForTimeout(3000);
        return this;
    }

    public int getCTAButtonCount() {
        return mobileMainPage.keyCTAButtons.count();
    }

    public MobileMainSteps checkCTABtnIsVisible(int i){
        assertThat(mobileMainPage.keyCTAButtons.nth(i)).isVisible();
        if(i==0)captureScreenshot("step4_cta_buttons.png", "CTA გამოკვეთილადჩანს ლურჯი ფერით");
        return this;
    }

    public MobileMainSteps checkCTABtnText(int i){
        assertThat(mobileMainPage.keyCTAButtons.nth(i)).containsText(tbcContants.mainPageKeyCTAButtonTexts[i]);
        return this;
    }

    public MobileMainSteps checkCTABtnColor(int i){
        assertThat(mobileMainPage.keyCTAButtons.nth(i))
                .hasCSS("color", java.util.regex.Pattern.compile("rgba?\\(0,\\s*0,\\s*0.*\\)|rgb\\(0,\\s*0,\\s*0\\)"));
        return this;
    }

    private void captureScreenshot(String fileName, String description) {
        String screenshotPath = Paths.get("screenshots", fileName).toString();
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get(screenshotPath))
                .setFullPage(false));
        System.out.println("Screenshot saved: " + screenshotPath + " - " + description);
    }
}

