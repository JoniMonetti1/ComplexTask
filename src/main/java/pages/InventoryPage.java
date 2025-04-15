package pages;

public class InventoryPage extends BasePage{
    private static final String TITLE_XPATH = "//div[@class='app_logo']";

    /**
     * Getting inventory page title
     * @return title text
     */
    public String getTitle() {
        logger.info("Getting inventory page title");
        return findElementByXPath(TITLE_XPATH).getText();
    }

    /**
     * Verify is title matches the expected title
     * @param expectedTitle expected title
     * @return true if matches false otherwise
     */
    public boolean verifyTitle(String expectedTitle) {
        logger.info("Verifying title: {}", expectedTitle);
        String actualTitle = getTitle();
        return actualTitle.equals(expectedTitle);
    }
}
