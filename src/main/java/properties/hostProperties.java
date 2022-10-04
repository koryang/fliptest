package properties;

public class hostProperties {
    private static final PropertiesReader propertiesReader = new PropertiesReader();
    public static final String host = propertiesReader.getHost();
    public static final String webhost = propertiesReader.getWebHost();
}
