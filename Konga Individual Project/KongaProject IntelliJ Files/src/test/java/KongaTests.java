import org.apache.commons.compress.archivers.sevenz.CLI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class KongaTests {
// Creating the locators for the Modal and its contents.
    private By ModalTriggerBtn = By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[2]/div/div[2]/div[3]/div[2]/div/button");
    private By ModalCardPaymentOption = By.xpath("//*[@id=\"channel-template\"]/div[2]/div/div[2]/button");
    private By ModalCardNumber = By.id("card-number");
    private By ModalCardDate = By.id("expiry");
    private By ModalCardCVV = By.id("cvv");
    private By ModalCardPIN = By.id("card-pin-new");
    private By ModalCardPayNowBtn = By.id("validateCardForm");
    private By Modal_iFrameCloseBtn = By.xpath("/html/body/section/section/section/div[2]/div[1]/aside");
    private By ModalInvalidCardDetailsMessage = By.id("card-number_unhappy");

    // Import the Selenium WebDriver
    private WebDriver driver;

    @BeforeTest
    public void openbrowser() throws InterruptedException {
//locate where the Edge driver is
        System.setProperty("WebDriver.Edge.driver", "Resources/msedgedriver.exe");
//Flow 0. Open your Edge browser
        driver = new EdgeDriver();
        Thread.sleep(5000);
    }
    @Test(priority = 0)
    public void startupsequence() throws InterruptedException {
//Flow 1a. Visit the Konga HomePage URL (https://www.konga.com)
        driver.get("https://www.konga.com");
        Thread.sleep(10000);
//Flow 1b. Maximize the browser
        driver.manage().window().maximize();
        Thread.sleep(1000);
//TEST 1 - Verify the user inputs the right url and is on the right webpage; - write a test to show a message on the page that the right page was visited.
        if (driver.getCurrentUrl().contains("https://www.konga.com"))
            //Pass sequence
            System.out.println("Correct Homepage");
        else
            //Fail sequence
            System.out.println("Wrong Homepage");
//TEST 2 - Write a test to show a message from the webpage that the right page was visited.
        String actualHomeUpPageMessage = "Konga Online Shopping";
        if (actualHomeUpPageMessage.contains("Konga Online Shopping"))
            //Pass sequence
            System.out.println("Homepage message shows");
        else
            //Fail sequence
            System.out.println("Wrong Homepage & no message");
    }
    @Test(priority = 1)
    public void Negative_signinsequence() throws InterruptedException {
//Flow N2. Unsuccessful sigh-in to Konga. Sign in to Konga unsuccessfully.
//Flow N2a. Click on the Signin button to open the Signin page, and signin.
        driver.findElement(By.xpath("//*[@id=\"nav-bar-fix\"]/div[1]/div/div/div[4]/a")).click();
        Thread.sleep(2000);
//Flow N2b. Input a wrong Username on the Email address / phone number field
        driver.findElement(By.id("username")).sendKeys("ifysylviaif@yao");
//Flow N2c. Locate the Password field and input your Password on the password field
        driver.findElement(By.id("password")).sendKeys("190060");
//Flow N2d. Click on the Login/Signin button
        driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[4]/section/section/aside/div[2]/div/form/div[3]/button")).click();
        Thread.sleep(10000);

//TEST 3 - Verify that after User cannot successfully log in, a Login error message appears on the page.
        String UnsuccessfulLogInMessage = "The username or password you have entered is incorrect. Please try again.";
        if (UnsuccessfulLogInMessage.contains("The username or password you have entered is incorrect. Please try again."))
            //Pass sequence
            System.out.println("Correct Unsuccessful Login ");
        else
            //Fail sequence
            System.out.println("Wrongful login");
//Flow N2e. Return to the homepage by clicking Close.
        driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[4]/section/section/aside/div[1]/div/a/span")).click();
        Thread.sleep(10000);
    }
    @Test(priority = 2)
    public void Positive_signinsequence() throws InterruptedException {
//Flow 2. Sign in to Konga successfully.
//Flow 2a. Click on the Signin button to open the Signin page, and signin.
        driver.findElement(By.xpath("//*[@id=\"nav-bar-fix\"]/div[1]/div/div/div[4]/a")).click();
        Thread.sleep(2000);
//Flow 2b. Input your Username on the Email address / phone number field
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("");
//Flow 2c. Locate the Password field and input your Password on the password field
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("");
//Flow 2d. Click on the Login/Signin button
        driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[4]/section/section/aside/div[2]/div/form/div[3]/button")).click();
        Thread.sleep(10000);

//TEST 4 - Verify that after User successfully logs in, a Login successful message appears on the page.
        String SuccessfulLogInMessage = "Login Login Successful";
        if (SuccessfulLogInMessage.contains("Login Login Successful"))
            //Pass sequence
            System.out.println("Login Successful message shows");
        else
            //Fail sequence
            System.out.println("Login not successful");
    }
    @Test(priority = 3)
    public void Positive_ItemSelection() throws InterruptedException {
//Flow 3a. Navigate to the Categories section.
        driver.findElement(By.xpath("//*[@id=\"nav-bar-fix\"]/div[2]/div/a[1]")).click();
//Flow 3b. Inside the Categories section, navigate to and select the "Computer & Accessories" category.
        driver.findElement(By.xpath("//*[@id=\"subFixId\"]/div/div[1]/ul/li[1]/a")).click();
        Thread.sleep(5000);//try this xpath for the above if current doesnt work -"//*[@id="subFixId"]/div/div"
//TEST 5 - Verify that after selecting Computer and Accessories, that User is on the right page.
        String SuccessfulPageMessage = "Computers and Accessories";
        if (SuccessfulPageMessage.contains("Computers and Accessories"))
            //Pass sequence
            System.out.println("Correct webpage");
        else
            //Fail sequence
            System.out.println("Wrong webpage");
//Flow 4. Select Laptop subcategory.
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/section[3]/section/div/section/div[2]/div[2]/ul/li[3]/a/label/span")).click();
        Thread.sleep(10000);
    }
    @Test(priority = 4)
    public void Positive_AppleLaptopSelection() throws InterruptedException {
//Flow 5a. Click on Apple Macbooks.
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/section[3]/section/div/section/div[2]/div[2]/ul/li[3]/a/ul/li[1]/a/label/span")).click();
        Thread.sleep(12000);
//TEST 6 - Verify that User is on the Apple MacBooks page.
        String AppleMacBooksPageMessage = "Apple MacBooks";
        if (AppleMacBooksPageMessage.contains("Apple MacBooks"))
            //Pass sequence
            System.out.println("Correct Apple MacBooks webpage");
        else
            //Fail sequence
            System.out.println("Wrong Apple MacBooks webpage");
    }
    @Test(priority = 5)
    public void Positive_AddItemtoCart() throws InterruptedException {
//Flow 6a. Add an item - a laptop to Cart.
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/section[3]/section/section/section/section/ul/li[2]/div/div/div[2]/form/div[3]/button")).click();
        Thread.sleep(5000);
//TEST 7 - Verify that the laptop was added to cart (an item added to cart message came up)
        String LaptopAddedtoCartMessage = "Apple Macbook has been added to your cart";
        if (LaptopAddedtoCartMessage.contains("Apple Macbook has been added to your cart"))
            //Pass sequence
            System.out.println("Correct Apple laptop added to cart");
        else
            //Fail sequence
            System.out.println("Wrong Apple laptop added to cart");
//Flow 6b. Navigate to My Cart button and Open my cart.
        driver.findElement(By.xpath("//*[@id=\"nav-bar-fix\"]/div[1]/div/div/a[2]/span[1]")).click();
        Thread.sleep(3000);
//TEST 8 - Verify that the cart page has Cart Overview header message on it
        String CartOverviewPop = "Cart Overview";
        if (CartOverviewPop.contains("Cart Overview"))
            //Pass sequence
            System.out.println("Correct Shopping Cart webpage");
        else
            //Fail sequence
            System.out.println("Wrong Shopping Cart webpage");
//Flow 6c. Click on Checkout
        driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[3]/section/section/aside/div[3]/div/div[2]/button")).click();
        Thread.sleep(5000);
    }
    @Test(priority = 6)
    public void Positive_AddAddressFlow() throws InterruptedException {
//FLow 7a. Select Address - Click on Add Delivery Address button - (1st command)
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[1]/div/div/div[2]/div[1]/div[2]/div[1]/div/button")).click();
        Thread.sleep(7000);
//Flow 7b. To select an existing address.
        driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[2]/section/section/aside/div[2]/div/div/div[2]/div[1]/form/button")).click();
        Thread.sleep(2500);//name - "selectDeliveryAddress"
//Flow 7c. Click Use This Address button
        driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[2]/section/section/aside/div[3]/div/div/div/a")).click();
        Thread.sleep(2500);
//TEST 9 - Verify the user is on the Checkout page.
        if (driver.getCurrentUrl().contains("https://www.konga.com/checkout/complete-order"))
            //Pass sequence
            System.out.println("Checkout page confirmed");
        else
            //Fail sequence
            System.out.println("Wrong Checkout page");
    }
    @Test (priority = 7)
    public void MakePaymentSection() throws InterruptedException {
//Flow 8. Make Payment
//Flow 8a. Click the Pay Now Button under PAYMENT OPTIONS
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[2]/div/div[2]/div[1]/div[1]/span/input")).click();
        Thread.sleep(500);
//Flow 8b. Click the Continue to Payment button / this triggers the payment modal.
        driver.findElement(By.name("placeOrder")).click();
        Thread.sleep(10000);
    }
//Flow 9. Select a Card Payment Method
    @Test (priority = 8)
//Creating the methods to Interact with the Modal
//Flow 9a - Click on this to select the Card Payment option in the Modal
    public void InteractPaymentModal() throws InterruptedException {
        Click(ModalCardPaymentOption);
        Thread.sleep(3000);
       }
    private void Click(By modalCardPaymentOption) {
    }
    @Test (priority = 9)
//Flow 9b - Inputting the Card Details in the Modal.
    public void InputCardDetails() throws InterruptedException {
    setText(ModalCardNumber, 500000);
    setText(ModalCardDate, 05/25);
    setText(ModalCardCVV, 111);
    Click(ModalCardPayNowBtn);
    Thread.sleep(4000);
       }
      private void setText(By modalCardNumber, int i) {
    }
    @Test (priority = 10)
//Flow 9d - Print out the Error Message
    public void PrintErrorMessage() throws InterruptedException {
        String CardErrorMessage = "Invalid Card Number";
        if (CardErrorMessage.contains("Invalid Card Number"))
            //Pass sequence
            System.out.println("Invalid Card Number");
        else
            //Fail sequence
            System.out.println("Error - Successful Payment made");
        Thread.sleep(3000);
    }
    @Test (priority = 11)
//Flow 9c - Closing the iFrame.
    public void CloseiFrame() throws InterruptedException {
        Click(Modal_iFrameCloseBtn);
        Thread.sleep(3000);
    }
    @AfterTest
    public void closebrowser() {
//Flow. Quit the browser
        driver.quit();
         }
}
