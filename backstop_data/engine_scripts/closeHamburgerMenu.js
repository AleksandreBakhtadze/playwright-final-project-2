module.exports = async (page, scenario) => {
    await page.click('div[class*=\'tbcx-pw-hamburger-menu\'] button');
    await page.waitForTimeout(1000);
};