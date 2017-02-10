package model;

import java.io.File;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class Settings {

    private final Preferences preferences;

    private final String PROJECT_PATH_NAME = "project_path";
    private final String BASE_PATH_NAME = "base_path";

    public Settings() {
        preferences = Preferences.userRoot();
    }

    public void clear() {
        try {
            preferences.clear();
        } catch (BackingStoreException e) {
            e.printStackTrace();
        }
    }

    public String getProjectPath() {
        String projectPath = preferences.get(PROJECT_PATH_NAME, null);

        if (projectPath != null) {
            File fileProject = new File(projectPath);

            // Checks if settings aren't pointing to a file that was deleted
            if (fileProject.exists() == false) {
                preferences.remove(PROJECT_PATH_NAME);
            }
        }

        return preferences.get(PROJECT_PATH_NAME, null);
    }

    public void setProjectPath(String projectPath) {
        preferences.put(PROJECT_PATH_NAME, projectPath);
    }

    public String getBasePath() {
        String basePath = preferences.get(BASE_PATH_NAME, null);

        if (basePath != null) {
            File fileBase = new File(basePath);

            // Checks if settings aren't pointing to a file that was deleted
            if (fileBase.exists() == false) {
                preferences.remove(BASE_PATH_NAME);
            }
        }

        return preferences.get(BASE_PATH_NAME, null);
    }

    public void setBasePath(String basePath) {
        preferences.put(BASE_PATH_NAME, basePath);
    }

}
