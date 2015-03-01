package edu.csupomona.cs480.util;

import java.io.File;

/**
 * This is an utility class to help file locating.
 */
public class ResourceResolver {

    /**
     * Get the file used to store the user object JSON
     *
     * @return
     */
    public static File getUserFile() {
        File file = new File("src/main/resources/static/food-map-HASHMAP_VERSION.json");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        return file;
    }
}
