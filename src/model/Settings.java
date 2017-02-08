package model;

import java.io.File;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class Settings {

    private final static Preferences preferences = Preferences.userRoot();

    private final static String PROJECT_PATH_NAME = "project_path";
    private final static String BASE_PATH_NAME = "base_path";

    private Settings() {
        // This is a static class
    }

    public static void clear() {
        try {
            preferences.clear();
        } catch (BackingStoreException e) {
            e.printStackTrace();
        }
    }

    public static String getProjectPath() {
        String projectPath = preferences.get(PROJECT_PATH_NAME, null);

        if (projectPath != null) {
            File fileProject = new File(projectPath);

            // Checks if settings aren't pointing to a file that was deleted
            if (fileProject.exists() == false) {
                setProjectPath(null);
            }
        }

        return preferences.get(PROJECT_PATH_NAME, null);
    }

    public static void setProjectPath(String projectPath) {
        preferences.put(PROJECT_PATH_NAME, projectPath);
    }

    public static String getBasePath() {
        String basePath = preferences.get(BASE_PATH_NAME, null);

        if (basePath != null) {
            File fileBase = new File(basePath);

            // Checks if settings aren't pointing to a file that was deleted
            if (fileBase.exists() == false) {
                setBasePath(null);
            }
        }

        return preferences.get(BASE_PATH_NAME, null);
    }

    public static void setBasePath(String basePath) {
        preferences.put(BASE_PATH_NAME, basePath);
    }

}
