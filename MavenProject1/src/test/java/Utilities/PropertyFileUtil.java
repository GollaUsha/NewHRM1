package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileUtil {
public static String getValaueForkey(String key) throws Throwable, IOException
{
	Properties configProperties =new Properties();
	configProperties.load(new FileInputStream(new File("./ConfigFile/Environment.properties")));
	return configProperties.getProperty(key);
}
}
