package model;

import java.io.File;
import java.util.prefs.Preferences;

public class Settings {

    private final Preferences preferences = Preferences.userRoot();
    private final String PREFERENCE_NAME_BASE_PATH = "base_path";

    private Project project;    // Wybrany projekt do porownania
    private Projects base;      // Baza projektow, z którymi projekt bedzie porownywany

    public Settings() {
        this.project = null;
        this.base = loadBase();
    }

    private Projects loadBase() {
        String basePath = getPreferenceBasePath();

        if (basePath == null) {
            return null;
        }
        else {
            File baseDirectory = new File(basePath);
            Projects base = new Projects(baseDirectory);
            return base;
        }
    }

    private String getPreferenceBasePath() {
        String basePath = preferences.get(PREFERENCE_NAME_BASE_PATH, null);

        // Sprawdz czy preference jest ustawiony
        if (basePath != null) {
            // Sprawdz czy wskazany plik istnieje
            File fileBase = new File(basePath);
            if (fileBase.exists() == false) {
                preferences.remove(PREFERENCE_NAME_BASE_PATH);
                basePath = preferences.get(PREFERENCE_NAME_BASE_PATH, null);
            }
        }

        return basePath;
    }

    private void setPreferenceBasePath(String basePath) {
        preferences.put(PREFERENCE_NAME_BASE_PATH, basePath);
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Projects getBase() {
        return base;
    }

    public void setBase(Projects base) {
        this.base = new Projects(base.getDirectory());
        setPreferenceBasePath(base.getDirectory().getAbsolutePath());
    }

}
