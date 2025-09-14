package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.data.DataSupplier;
import ge.tbc.testautomation.data.TBCContants;
import org.testng.annotations.Test;
import ge.tbc.testautomation.runners.BaseTest;
import ge.tbc.testautomation.steps.MainPageSteps;
import ge.tbc.testautomation.steps.ProductPageSteps;

public class ProductDetailsTests extends BaseTest {

    private MainPageSteps mainPageSteps;
    private ProductPageSteps productPageSteps;
    private final TBCContants tbcContants = new TBCContants();

    @Test(priority = 1, description = "FP1-T3")
    public void navigateToProductPageAndValidateTitle() {
        mainPageSteps = new MainPageSteps(page);
        productPageSteps = new ProductPageSteps(page);

        mainPageSteps.openMainPage().acceptCookies()
                .goToProductsPage();
        productPageSteps.validateTitle(tbcContants.productTitle);
    }

    @Test(priority = 2, dependsOnMethods = "navigateToProductPageAndValidateTitle")
    public void validatePrimaryCTAButtonsPresence() {
        productPageSteps.checkCTAButtonsColours()
                .checkCTAButtonsAviability()
                .checkCTAButtonsText("დეტალურად")
                .clickOnSecondCTAButton()
                .checkPopupDetails()
                .closePopUpDetails();

    }

    @Test(priority = 3, dataProvider = "currencies", dataProviderClass = DataSupplier.class, dependsOnMethods = "navigateToProductPageAndValidateTitle")
    public void validateSecondaryCTAButtonsAndLinks(String currency) {
        productPageSteps.validateConvertingTabHeader()
                .selectCurrency(currency);
        for(int i = 0 ; i <tbcContants.currencies.length;i++){
            productPageSteps.selectCurrencyOptionToConvert(tbcContants.currencies[i]);
            double exchange = productPageSteps.getExchangeRate();
            productPageSteps.fillAmountField().checkConvertedAmount(exchange);
        }
    }

    @Test(priority = 4, dependsOnMethods = "navigateToProductPageAndValidateTitle")
    public void validateExternalLinksAndCommissionCalculator() {
        productPageSteps.validateSwapButton()
                .checkSecondaryLinksAvailability()
                .checkSecondaryLinksText()
                .openCommissionCalculator();
    }

    @Test(priority = 5, dataProvider = "countries", dataProviderClass = DataSupplier.class, dependsOnMethods = "navigateToProductPageAndValidateTitle")
    public void validateCommissionCalculationByCountry(String country) {
        productPageSteps.selectUSDAsTransferCurrency()
                .fillTransferAmountField()
                .selectCountryToTransfer(country)
                .waitLoadersToDisappear()
                .checkLoadedCards(country)
                .validateLoadedCardsData(country);
    }

    @Test(priority = 6, dependsOnMethods = "navigateToProductPageAndValidateTitle")
    public void validateExternalLinksRedirection() {
        productPageSteps.checkExternalLinkPresence()
                .clickExternalLinkButton()
                .checkExternalPageURL()
                .closeExternalPage();
    }

    @Test(priority = 7, dependsOnMethods = "navigateToProductPageAndValidateTitle")
    public void verifyProductInformationCompleteness() {
        productPageSteps.productOtherInformation();
    }
}