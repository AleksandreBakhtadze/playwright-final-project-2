package ge.tbc.testautomation.data;

import org.testng.annotations.DataProvider;

public class DataSupplier {

    @DataProvider(name = "sections")
    public Object[][] getSections() {
        return new Object[][] {
                {"მობაილბანკის უპირატესობები",0},
                {"სხვადასხვა",1},
                {"ბლოგები",2}
        };
    }

    @DataProvider(name = "currencies")
    public Object[][] getCurrencies() {
        return new Object[][] {
                {"USD"},
                {"EUR"},
                {"GBP"},
                {"GEL"}
        };
    }

    @DataProvider(name = "countries")
    public Object[][] getCountries() {
        return new Object[][] {
                {"საქართველო"},
                {"აშშ"},
                {"კანადა"}
        };
    }


}
