import java.net.HttpURLConnection;
import java.net.URL;


public class SeleniumExample extends SeleniumConfig {

    private final SeleniumConfig config;

    public SeleniumExample() {
        config = new SeleniumConfig();
        String url = " http://the-internet.herokuapp.com";
        config.getDriver().get(url);
    }

    public void closeWindow() {
        this.config.getDriver().close();
    }

    public static void verifyLinks(String linkUrl)
    {
        try
        {
            URL url = new URL(linkUrl);

            //Now we will be creating url connection and getting the response code
            HttpURLConnection httpURLConnect=(HttpURLConnection)url.openConnection();
            httpURLConnect.setConnectTimeout(5000);
            httpURLConnect.connect();
            if(httpURLConnect.getResponseCode()>=400)
            {
                System.out.println("HTTP STATUS - " + httpURLConnect.getResponseMessage() + "is a broken link");
            }

            //Fetching and Printing the response code obtained
            else{
                System.out.println("HTTP STATUS - " + httpURLConnect.getResponseMessage());
            }
        }catch (Exception e) {
        }
    }
}
