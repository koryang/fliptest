package properties;

public class tokenProperties {
    private static final PropertiesReader propertiesReader = new PropertiesReader();

    public static final String token = propertiesReader.getToken();
}
