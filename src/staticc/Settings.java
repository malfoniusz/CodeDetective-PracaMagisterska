package staticc;

import java.io.File;
import java.util.prefs.Preferences;

import model.Project;
import model.Projects;

public final class Settings {

    private static final Preferences preferences = Preferences.userRoot().node(Settings.class.toString());

    private static final String PROJECT_PATH = "project_path";
    private static final String BASE_PATH = "base_path";
    private static final String MINIMAL_MATCHED_LINES_VALUE = "minimal_matched_lines_value";
    private static final int MINIMAL_MATCHED_LINES_VALUE_DEFAULT = 5;

    private Settings() {

    }

    public static Project getProject() {
        return loadProject();
    }

    public static void setProject(Project p) {
        String projectPath = p.getDirectory().getAbsolutePath();
        preferences.put(PROJECT_PATH, projectPath);
    }

    private static Project loadProject() {
        String projectPath = getPreferencePath(PROJECT_PATH);

        if (projectPath == null) {
            return null;
        }
        else {
            File projectDirectory = new File(projectPath);
            Project project = new Project(projectDirectory);
            return project;
        }
    }

    private static String getPreferencePath(final String PATH) {
        String basePath = preferences.get(PATH, null);

        // Sprawdz czy preference jest ustawiony
        if (basePath != null) {
            // Sprawdz czy wskazany plik istnieje
            File fileBase = new File(basePath);
            if (fileBase.exists() == false) {
                preferences.remove(PATH);
                basePath = preferences.get(PATH, null);
            }
        }

        return basePath;
    }



    public static Projects getBase() {
        return loadBase();
    }

    public static void setBase(Projects b) {
        String basePath = b.getDirectory().getAbsolutePath();
        preferences.put(BASE_PATH, basePath);
    }

    private static Projects loadBase() {
        String basePath = getPreferencePath(BASE_PATH);

        if (basePath == null) {
            return null;
        }
        else {
            File baseDirectory = new File(basePath);
            Projects base = new Projects(baseDirectory);
            return base;
        }
    }



    public static int getMinimalMatchedLinesValue() {
        return preferences.getInt(MINIMAL_MATCHED_LINES_VALUE, MINIMAL_MATCHED_LINES_VALUE_DEFAULT);
    }

    public static void setMinimalMatchedLinesValue(int value) {
        preferences.putInt(MINIMAL_MATCHED_LINES_VALUE, value);
    }

}
