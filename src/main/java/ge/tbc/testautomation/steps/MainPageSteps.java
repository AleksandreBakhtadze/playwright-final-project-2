package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import ge.tbc.testautomation.data.DatabaseSteps;
import ge.tbc.testautomation.pages.MainPage;
import ge.tbc.testautomation.data.TBCContants;
import org.testng.reporters.jq.Main;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.AssertJUnit.assertTrue;

public class MainPageSteps {

    private final Page page;
    private final MainPage mainPage;
    private final TBCContants tbcContants = new TBCContants();
    private final DatabaseSteps databaseSteps = new DatabaseSteps();

    public MainPageSteps(Page page) {
        this.page = page;
        this.mainPage = new MainPage(page);
    }

    public MainPageSteps openMainPage() {
        page.navigate(tbcContants.mainPageLink);
        return this;
    }

    public MainPageSteps hoverOnNavigation(){
        assertThat(mainPage.forMe).isVisible();
        mainPage.forMe.hover();
        page.waitForTimeout(2000);
        return this;
    }

    public void goToLocationsPage() {
        assertThat(mainPage.locationsLink).isVisible();
        mainPage.locationsLink.click();
        page.waitForFunction("() => window.location.href.includes('atm') || window.location.href.includes('branches')");
    }

    public void goToProductsPage() {
        assertThat(mainPage.productLink).isVisible();
        mainPage.productLink.click();
        page.waitForFunction("() => window.location.href.includes('money') || window.location.href.includes('transfers')");
    }

    public MainPageSteps acceptCookies() {
        try {
            assertThat(mainPage.cookiesAcceptButton).isVisible();
            if (mainPage.cookiesAcceptButton.isVisible()) {
                mainPage.cookiesAcceptButton.click();
            }
        } catch (Exception e) {// Cookie button not visible, continue
        }
        return this;
    }

    public  MainPageSteps hoveOnSlider(){
        mainPage.slider.hover();
        page.waitForTimeout(1000);
        return this;
    }

    public Locator getCurrentSlide(int i) {
        assertThat(mainPage.getCurrentSlide()).isVisible();
        assertThat(mainPage.getCurrentSlide().locator("h2.tbcx-pw-hero-slider-section__slide-title"))
                .hasText(tbcContants.mainPageSlides[i]);
        return mainPage.getCurrentSlide();
    }

    public MainPageSteps checkSliderCTABtn(Locator slider) {
        //Locator button = slider.locator("tbcx-pw-button a");
        assertThat(mainPage.getSliderBtn(slider)).isVisible();
        assertThat(mainPage.getSliderBtn(slider)).hasAttribute("href", java.util.regex.Pattern.compile(".*"));
        assertTrue(mainPage.getSliderBtn(slider).textContent().contains(tbcContants.BtnTexts[0])
                ||mainPage.getSliderBtn(slider).textContent().contains(tbcContants.BtnTexts[1])
                ||mainPage.getSliderBtn(slider).textContent().contains(tbcContants.BtnTexts[2]));
        return this;
    }

    public MainPageSteps moveOnNextSlide(){
        assertThat(mainPage.sliderNextButton).isEnabled();
        mainPage.sliderNextButton.click();
        page.waitForTimeout(2000);
        return this;
    }

    public MainPageSteps checkMainTitle() {
        assertThat(mainPage.mainTitle).hasText(tbcContants.homaPageMainTitle);
        return this;
    }

    public MainPageSteps checkH1Title(int i) {
        assertThat(mainPage.h1s.nth(i)).hasText(tbcContants.h1Titles[i]);
        return this;
    }

    public MainPageSteps checkCTABtns(int i) {
        assertThat(mainPage.getButtonForH1(i)).isVisible();
        assertThat(mainPage.getButtonForH1(i)).hasText(tbcContants.BtnTexts[0]);
        assertThat(mainPage.getButtonForH1(i)).hasCSS("background-color", java.util.regex.Pattern.compile("rgba?\\(0,\\s*173,\\s*238.*\\)|rgb\\(0,\\s*173,\\s*238\\)"));
        return this;
    }

    public MainPageSteps checkSectionName(String sectionName, int index) {
        assertThat(mainPage.sections.nth(index)).containsText(sectionName);
        return this;
    }

    public int getCardsCount(int index) {
        return mainPage.getSectionCards(index).count();
    }

    public MainPageSteps checkCardText(String sectionName, int i, int index) throws SQLException {
        List<String> cardTitles = databaseSteps.getSectionCardTitles(sectionName);
        if (cardTitles != null && i < cardTitles.size()) {
            assertThat(mainPage.getSectionCards(index).nth(i)).containsText(cardTitles.get(i));
        }
        return this;
    }
    public MainPageSteps clickSectionNextBtn(int i , int index){
        if (i == 2 && index != 1) {
            mainPage.sectionNextButtons.nth(Math.max(index - 1, 0)).click();
        }
        return this;
    }

    public MainPageSteps clickSectionBackBtn(int index){
        if(index!=1)mainPage.sectionBackButtons.nth(Math.max(index - 1, 0)).click();
        return this;
    }

    public MainPageSteps checkCardLink(String sectionName, int i, int index){
        assertThat(mainPage.getCardLink(index,i)).hasAttribute("href", tbcContants.sectionCardsUrls.get(sectionName)[i]);
        return this;
    }

    public String getCardTargetAttribute(int i, int index){
        String targetAttribute;
        try {
            targetAttribute = mainPage.getCardLink(index,i).getAttribute("target");
        } catch (Exception e) {
            targetAttribute = null;
        }
        return targetAttribute;
    }

    public MainPageSteps validateCardLink(String targetAttribute, int index,int i ,String sectionName){
        if (targetAttribute != null && targetAttribute.equals("_blank")) {
            Page newPage = page.context().waitForPage(() -> {
                mainPage.getCardLink(index,i).click();
                page.waitForTimeout(1000);
            });
            assertThat(newPage).hasURL(tbcContants.sectionCardsUrls.get(sectionName)[i]);
            newPage.close();
        } else {
            mainPage.getCardLink(index,i).click();
            page.waitForTimeout(1000);
            page.waitForFunction("() => window.location.href.includes('" + tbcContants.sectionCardsUrls.get(sectionName)[i] + "')");
            page.goBack();
        }
        return this;
    }

    public MainPageSteps reloadCards(int index){
        page.waitForTimeout(1000);
        assertThat(mainPage.sections.nth(index)).isVisible();
        return this;
    }
}