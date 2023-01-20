/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

/**
 *
 * @author orlando
 */
public class URLConnection {
    String url;
    String driver;

    public String getUrl() {
        return url;
    }

    public String getDriver() {
        return driver;
    }

    public URLConnection() {
    }

    public URLConnection(String url, String driver) {
        this.url = url;
        this.driver = driver;
    }
    
}
