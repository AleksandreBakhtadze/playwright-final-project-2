package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import ge.tbc.testautomation.data.DatabaseSteps;
import ge.tbc.testautomation.pages.ProductPage;
import ge.tbc.testautomation.utils.HelperFunctions;
import ge.tbc.testautomation.data.TBCContants;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.Assert.assertTrue;

public class ProductPageSteps {
    private final Page page;
    private final ProductPage productPage;
    private final HelperFunctions helperFunctions = new HelperFunctions();
    private final TBCContants tbcContants = new TBCContants();
    private final DatabaseSteps databaseSteps = new DatabaseSteps();
    private Page externalPage;

    public ProductPageSteps(Page page) {
        this.page = page;
        this.productPage = new ProductPage(page);
    }

    public ProductPageSteps validateTitle(String title) {
        assertThat(productPage.titles.nth(0)).hasText(title);
        return this;
    }

    public ProductPageSteps checkCTAButtonsColours(){
        for (int i = 0; i < productPage.CTAButtons.count(); i++) {
            assertThat(productPage.CTAButtons.nth(i)).hasCSS("background-color", java.util.regex.Pattern.compile("rgba?\\(0,\\s*173,\\s*238.*\\)|rgb\\(0,\\s*173,\\s*238\\)"));
        }
        return this;
    }

    public ProductPageSteps checkCTAButtonsAviability(){
        for (int i = 0; i < productPage.CTAButtons.count(); i++) {
            assertThat(productPage.CTAButtons.nth(i)).isEnabled();
        }
        return this;
    }

    public ProductPageSteps checkCTAButtonsText(String text){
        for (int i = 0; i < productPage.CTAButtons.count(); i++) {
            assertThat(productPage.CTAButtons.nth(i)).hasText(text);
        }
        return this;
    }

    public ProductPageSteps clickOnSecondCTAButton(){
        productPage.CTAButtons.nth(1).click();
        return this;
    }
    public ProductPageSteps checkPopupDetails(){
        assertThat(productPage.popUpDetails).isVisible();
        assertThat(productPage.closePopUpDetails).isEnabled();
        return this;
    }

    public ProductPageSteps closePopUpDetails(){
        productPage.closePopUpDetails.click();
        page.waitForTimeout(200);
        return this;
    }

    public ProductPageSteps validateConvertingTabHeader(){
        assertThat(productPage.additionalButtons.nth(0)).hasText(tbcContants.convertTabText);
        assertThat(productPage.additionalButtons.nth(0)).hasClass(java.util.regex.Pattern.compile(".*active.*"));
        return this;
    }

    public ProductPageSteps selectCurrency(String currency){
        productPage.leftCurrencyDropdown.click();
        productPage.currencyOptions.filter(new Locator.FilterOptions().setHasText(currency)).first().click(new Locator.ClickOptions().setForce(true));
        return this;
    }

    public ProductPageSteps selectCurrencyOptionToConvert(String currency){
        productPage.rightCurrencyDropdown.click();
        productPage.currencyOptions.filter(new Locator.FilterOptions().setHasText(currency)).first().click();
        page.waitForTimeout(1000);
        return this;
    }

    public double getExchangeRate(){
        return helperFunctions.getExchangeRate(productPage.currentExchange.textContent());
    }

    public ProductPageSteps fillAmountField(){
        productPage.convertFields.nth(0).clear();
        productPage.convertFields.nth(0).fill("100");
        return this;
    }

    public ProductPageSteps checkConvertedAmount(double exchange){
        String expectedValue = helperFunctions.formatExpectedValue(100 * exchange);
        assertThat(productPage.convertFields.nth(1)).hasValue(expectedValue);
        return this;
    }

    public ProductPageSteps validateSwapButton() {
        String left = productPage.leftCurrencyDropdown.textContent();
        String right = productPage.rightCurrencyDropdown.textContent();
        productPage.swapButton.click();
        assertThat(productPage.leftCurrencyDropdown).hasText(right);
        assertThat(productPage.rightCurrencyDropdown).hasText(left);
        return this;
    }

    public ProductPageSteps checkSecondaryLinksAvailability(){
        for (int i = 0; i < productPage.secondaryLinks.count(); i++) {
            assertThat(productPage.secondaryLinks.nth(i)).isEnabled();
        }
        return this;
    }

    public  ProductPageSteps checkSecondaryLinksText(){
        for (int i = 0; i < productPage.secondaryLinks.count(); i++) {
            assertThat(productPage.secondaryLinks.nth(i)).hasText(tbcContants.productSecondaryTitles[i]);
        }
        return this;
    }

    public ProductPageSteps openCommissionCalculator() {
        assertThat(productPage.additionalButtons.nth(1)).hasText(tbcContants.commissionTabText);
        productPage.additionalButtons.nth(1).click();
        assertThat(productPage.additionalButtons.nth(1)).hasClass(java.util.regex.Pattern.compile(".*active.*"));
        return this;
    }

    public ProductPageSteps selectUSDAsTransferCurrency(){
        productPage.transactionDropDowns.nth(0).click();
        productPage.currencyOptions.filter(new Locator.FilterOptions().setHasText("USD")).first().click();
        return this;
    }

    public ProductPageSteps fillTransferAmountField(){
        productPage.transactionField.clear();
        productPage.transactionField.fill("100");
        return this;
    }

    public ProductPageSteps selectCountryToTransfer(String countryName){
        productPage.transactionDropDowns.nth(1).click();
        productPage.transactionCountries.filter(new Locator.FilterOptions().setHasText(countryName)).first().click();
        page.waitForTimeout(1500);
        return this;
    }

    public ProductPageSteps waitLoadersToDisappear(){
        page.waitForFunction("() => document.querySelectorAll('" + productPage.loadingPlaceholders.first().getAttribute("class") + "').length === 0");
        return this;
    }

    public ProductPageSteps checkLoadedCards(String countryName) throws SQLException {
        page.waitForTimeout(2000);
        Integer expectedCount = databaseSteps.getExpectedCardCount(countryName);
        assertThat(productPage.loadedTransferCards).hasCount(expectedCount);
        assertThat(productPage.loadedTransferCards.last()).isVisible();
        return this;
    }

    public ProductPageSteps validateLoadedCardsData(String countryName) throws SQLException {
        List<Map<String, Object>> commissions = databaseSteps.getCommissionsForCountry(countryName);
        for (int i = 7; i < productPage.loadedTransferCards.count(); i++) {
            Map<String, Object> commission = commissions.get(i - 7); // Adjust index to match array offset
            assertThat(productPage.loadedTransferCards.nth(i)).containsText(commission.get("commission_details").toString());
        }
        return this;
    }

    public ProductPageSteps checkExternalLinkPresence(){
        assertThat(productPage.externalLink).isVisible();
        assertThat(productPage.externalLink).hasAttribute("target", "_blank");
        productPage.externalLink.hover();
        assertThat(productPage.externalLink).isEnabled();
        return this;
    }

    public ProductPageSteps clickExternalLinkButton(){
        externalPage = page.context().waitForPage(() -> {
            productPage.externalLink.click();
        });
        return this;
    }
    public ProductPageSteps checkExternalPageURL(){
        externalPage.waitForLoadState();
        assertThat(externalPage).hasURL(tbcContants.externalPageLink);
        return this;
    }

    public ProductPageSteps closeExternalPage(){
        externalPage.close();
        return this;
    }

    public void productOtherInformation() throws SQLException {
        List<Map<String, Object>> transferSystems = databaseSteps.getTransferSystems();
        for (int i = 0; i < transferSystems.size(); i++) {
            String expectedText = transferSystems.get(i).get("system_name") + "\n" + "currency - " + transferSystems.get(i).get("currencies");
            assertThat(productPage.loadedTransferCards.nth(i)).containsText(expectedText);
        }
    }
}