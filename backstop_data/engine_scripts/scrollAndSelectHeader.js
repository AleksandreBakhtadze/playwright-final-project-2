module.exports = async (page, scenario) => {
    await page.evaluate(() => window.scrollBy(0, 1500));
    await page.waitForTimeout(3000);
    await page.evaluate(() => window.scrollBy(0, -500));
    await page.waitForTimeout(3000);
};