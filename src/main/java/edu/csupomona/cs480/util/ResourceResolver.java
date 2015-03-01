package edu.csupomona.cs480.util;

import java.io.File;
import java.net.URL;

/**
 * This is an utility class to help file locating.
 */
public class ResourceResolver {

    /**
     * The base folder to store all the data used by this project.
     */
    private static final String BASE_DIR = System.getProperty("user.home") + "/cs480";

    /**
     * Get the file used to store the user object JSON
     *
     * @return
     */
    public static File getUserFile () {
        File file = new File(BASE_DIR + "/food-map-HASHMAP_VERSION.json");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        return file;
    }
}
