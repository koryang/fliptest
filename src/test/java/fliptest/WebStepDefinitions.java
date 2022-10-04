package fliptest;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.lang3.StringUtils.replace;
import static org.apache.commons.lang3.StringUtils.strip;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WebStepDefinitions {
    public static WebDriver driver = new ChromeDriver();
    DecimalFormat f = new DecimalFormat("##.00");
    Float kurs;
    Float nominal;

    @Given("user visit Filp website")
    public void userVisitFlipWebsite() {
//        driver.get(hostProperties.webhost);
        driver.get("https://flip.id/");
    }

    @When("user click update toggle language to {string}")
    public void userClickUpdateToggleLanguageTo(String language) {
        if(language == "English") {
            Helper.waitForElementClassUntilClickable("toggle-language");
            driver.findElement(By.className("toggle-language")).click();
        }
    }

    @Then("user should see the {string} displayed")
    public void userShouldSeeTheDisplayed(String text) {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement playerbar = driver.findElement(By.xpath("//*[contains(text(),'"+text+"')]"));
        assertTrue(playerbar.isDisplayed());
    }

    @When("user click Digital Product sumbenu in Product menu")
    public void user_click_digital_product_sumbenu_in_product_menu() {
//        WebElement menus =  driver.findElement(By.id("popover-trigger-Produk"));
//        WebElement sub_menu =  driver.findElement(By.xpath("//*[ text() = 'Digital Product']"));
//        Actions action = new Actions(driver);
//        action.moveToElement(menus).moveToElement(sub_menu).click().build().perform();
        driver.get("https://flip.id/produk-digital");
    }

    @Then("user should see the {string} provider displayed")
    public void userShouldSeeTheProviderDisplayed(String text) {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement playerbar = driver.findElement(By.xpath("(//img[contains(@alt, '"+text+"')])[1]"));
        assertTrue(playerbar.isDisplayed());
    }

    @When("user visit Flip Globe")
    public void user_visit_flip_globe() {
//        WebElement menus =  driver.findElement(By.id("popover-trigger-Produk"));
//        WebElement sub_menu =  driver.findElement(By.xpath("//*[ text() = 'Flip Globe']"));
//        Actions action = new Actions(driver);
//        action.moveToElement(menus).moveToElement(sub_menu).click().build().perform();
        driver.get("https://flip.id/flip-globe");
//        Helper.waitForElementXpathUntilVisible("(//*[contains(text(),'Globe')])[1]");
    }
    @When("user select negara tujuan as {string}")
    public void user_select_negara_tujuan_as(String string) {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        Helper.waitForElementIdUntilClickable("menu-button-5");
        driver.findElement(By.id("menu-button-5")).click();
        driver.findElement(By.xpath("(//p[@class='chakra-text css-1i33ipy' and contains(text(),'United Kingdom')])[1]")).click();
        Helper.waitForElementXpathUntilVisible("(//p[@class=\"chakra-text css-1int6b7\"])[1]");
        String text = driver.findElement(By.xpath("(//p[@class=\"chakra-text css-1int6b7\"])[1]")).getText();
        text = text.replace("1 GBP = ", "");
        text = text.replace(".", "");
        text = text.replace(",", ".");
        kurs = Float.parseFloat(strip(text, " IDR"));
    }
    @When("user fill the nominal IDR {string}")
    public void user_fill_the_nominal_idr(String nominals) {
        Helper.waitForElementXpathUntilVisible("//input[@placeholder='Masukkan nominal dalam IDR']");
        driver.findElement(By.xpath("//input[@placeholder='Masukkan nominal dalam IDR']")).sendKeys(nominals);
        nominal = Float.parseFloat(nominals);
    }
    @Then("user verify the nominal GBP is correct")
    public void user_verify_the_nominal_gbp_is_correct() throws InterruptedException {
        Thread.sleep(5000);
        Float total = nominal/kurs;
        f.setRoundingMode(RoundingMode.HALF_UP);
        Float totals = Float.valueOf(f.format(total));
        Helper.waitForElementXpathUntilVisible("//input[@placeholder='Masukkan nominal dalam GBP']");
        String value = driver.findElement(By.xpath("//input[@placeholder='Masukkan nominal dalam GBP']")).getAttribute("value");
        Float values = Float.valueOf(value.replace(",", "."));
        System.out.println("nominal is " + nominal);
        System.out.println("kurs is " + kurs);
        System.out.println("total is " + totals);
        System.out.println("value is " + values);
        assertEquals(totals,values);
    }
    @Then("user verify the nominal to pay")
    public void user_verify_the_nominal_to_pay() throws InterruptedException {
        Thread.sleep(5000);
        Helper.waitForElementXpathUntilVisible("(//p[@class = 'chakra-text css-1int6b7'])[2]");
        String biaya = driver.findElement(By.xpath("(//p[@class = 'chakra-text css-1int6b7'])[2]")).getText();
        biaya = biaya.replace(" IDR", "");
        biaya = biaya.replace(".", "");
        biaya = biaya.replace(",", ".");
        Helper.waitForElementXpathUntilVisible("//p[@class = 'chakra-text css-1151a72']");
        String total = driver.findElement(By.xpath("//p[@class = 'chakra-text css-1151a72']")).getText();
        total = total.replace(" IDR", "");
        total = total.replace(".", "");
        total = total.replace(",", ".");
        Float nominalToPay = nominal + Float.valueOf(biaya);
        System.out.println("biaya is " + biaya);
        System.out.println("nominal to pay is " + nominalToPay);
        System.out.println("total is " + total);

        assertEquals(nominalToPay,Float.valueOf(total));
    }

    @After()
    public void closeBrowser(){
        driver.quit();
    }
}
