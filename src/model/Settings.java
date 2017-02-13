package model;

import java.io.File;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class Settings {

    private final Preferences preferences;

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
