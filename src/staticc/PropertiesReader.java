package staticc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertiesReader {

    private PropertiesReader() {

    }

    public static String readProperty(String propName) {
        InputStream inputStream = null;

        try {
            inputStream = ClassLoader.getSystemResourceAsStream("config.properties");

            Properties properties = new Properties();
            properties.load(inputStream);
            inputStream.close();

            return properties.getProperty(propName);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return null;
    }

}
