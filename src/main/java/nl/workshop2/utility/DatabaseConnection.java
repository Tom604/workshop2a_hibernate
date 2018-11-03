package nl.workshop2.utility;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author Vosjes
 */
public class DatabaseConnection {

    private static String driver;
    private static String url;
    private static String username;
    private static String password;
    static Logger log = LoggerFactory.getLogger(DatabaseConnection.class);
    
    public static Connection getConnection() {
        
        Connection connection = null;
        
        try {
            //Create Document object (with Builder and BuilderFactory)
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File("mysql_data.xml"));
            doc.getDocumentElement().normalize();
        
            //Read data from xml through the Document object
            driver = doc.getElementsByTagName("jdbc_driver").item(0).getTextContent();
            url = doc.getElementsByTagName("jdbc_url").item(0).getTextContent();
            username = doc.getElementsByTagName("jdbc_username").item(0).getTextContent();
            password = doc.getElementsByTagName("jdbc_password").item(0).getTextContent();

            Class.forName(driver);
            log.info("Driver loaded");

            connection = DriverManager.getConnection(url, username, password);
            
        } catch (ParserConfigurationException | SAXException | IOException |
            ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched in DatabaseConnection");
        }
        
        return connection;
    }
    
    public static int getDatabaseType() {
        
        int type = 1;

/*        
        TODO Implement example code below to offer the possibility of using several
        different database systems when running this application.
        
        The 'x' represents the option for another DB than MySQL (being the default),
        this can be extended to as many DB's as needed.
        Depending on the precise implementation of x (where it gets its information,
        like from an XML document), the implementation may change. 

        if (x)
            type = 2;
        
        return type;
*/

        return type;
    }
}
