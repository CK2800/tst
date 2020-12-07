# Assignment 5

### Selenium IDE tests

How to demo:  
Download the Selenium IDE [here](https://www.selenium.dev/selenium-ide/).

The tests have been created using the [Firefox browser](https://www.mozilla.org/da/firefox/new/).

1. Open the plugin from the browser,
2. select 'Open an existing project'
3. and choose findYoutubeVideo.side
4. Press play and observe your browser.
5. In the end, you should be able to enjoy 'Van Halen - Jump' by pressing the 'k' key.

6. Now repeat step 2 and choose formTest.side.
7. Observe that text is echo'ed as the 14th step in Selenium IDE.

### Selenium JAVA tests

How to demo:  
Since the project includes the 64 bit version of the Mozilla Gecko driver v0.28.0, you may not need to do anything else than run the SeleniumTest.java.

If you are on a 32 bit system, download the appropriate Mozilla Gecko driver [here](https://github.com/mozilla/geckodriver/releases).

Unzip the downloaded file into the src/main/resources and name it geckodriver.exe.

Now run the SeleniumTest.java.

We discovered a way to await load completion of a web page in the webdriver, so that our expected values were met.  
However, we do find this way a bit hackish, as it is reported to rely heavily on the browser of choice.

### Cucumber JAVA tests:

#### Cucumber tutorial

[Cucumber tutorial](https://cucumber.io/docs/guides/10-minute-tutorial/) is completed in the assignment 5/Cucumber project. The Cucumber test uses the feature file, located in resources in the test folder. Step definitions are declared in the hellocucumber package.

To demo, just run the test suite.

The tests are using Scenario Outline to test multiple parameters.

#### Cucumber booking system extension

The Booking system are located in [assignment 3](https://github.com/CK2800/tst/tree/master/3/test2020fall-BookingSystem).

The Cucumber test uses the feature files, located in resources in the test folder.

Step definitions are declared in the cucumber package.

We discovered a problem with running the tests, as the booking system uses junit5 and cucumber-junit uses junit4. If the project imports junit 4, all the tests with jupiter (junit 5) will be ignored and vice versa.

The problem are solved with the junit-vintage-engine dependency.

                <dependency>
                    <groupId>org.junit.vintage</groupId>
                    <artifactId>junit-vintage-engine</artifactId>
                    <version>5.6.3</version>
                </dependency>

The cucumber tests cover create customer / employee tests.

The tests are using Scenario Outline to test for an empty customer or employee to fail.

To demo, follow the setup located in the booking systems README file and run the following command to run the testsuite.

                mvn verify

