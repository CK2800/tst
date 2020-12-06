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
Download the Mozilla Gecko driver v0.28.0 [here](https://github.com/mozilla/geckodriver/releases).  
We used the 64bit version on Windows.

Unzip the downloaded file into a folder of your choosing.  
Add the folder path to the system PATH variable, e.g.

        C:\Program Files (x86)\selenium-drivers

You may need to reboot the system.

Now run the SeleniumTest.java.

We discovered a way to await load completion of a web page in the webdriver, so that our expected values were met.  
However, we do find this way a bit hackish, as it is reported to rely heavily on the browser of choice.

### Cucumber JAVA tests:

TBD  
Also see extensions made to the project in assignment 3 -> file.....
