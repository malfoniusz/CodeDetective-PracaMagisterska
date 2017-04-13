package staticc;

import java.io.File;
import java.util.prefs.Preferences;

import model.ConsecutiveOption;
import model.Project;
import model.Projects;

public final class Settings {

    private static final Preferences preferences = Preferences.userRoot();

    private static final String PROJECT_PATH = "project_path";
    private static final String BASE_PATH = "base_path";
    private static final String CONSECUTIVE_LINES_VALUE = "consecutive_lines_value";
    private static final int CONSECUTIVE_LINES_VALUE_DEFAULT = 10;
    private static final String CONSECUTIVE_TOKENS_VALUE = "consecutive_tokens_value";
    private static final int CONSECUTIVE_TOKENS_VALUE_DEFAULT = 30;
    private static final String CONSECUTIVE_OPTION = "consecutive_option";
    private static final String CONSECUTIVE_OPTION_DEFAULT = ConsecutiveOption.LINES.toString();

    private static Project project = null;
    private static Projects base = null;

    private Settings() {

    }



    public static Project getProject() {
        if (project == null) {
            project = loadProject();
        }

        return project;
    }

    public static void setProject(Project p) {
        project = new Project(p.getDirectory());
        String basePath = p.getDirectory().getAbsolutePath();
        preferences.put(BASE_PATH, basePath);
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
        String basePath = preferences.get(BASE_PATH, null);

        // Sprawdz czy preference jest ustawiony
        if (basePath != null) {
            // Sprawdz czy wskazany plik istnieje
            File fileBase = new File(basePath);
            if (fileBase.exists() == false) {
                preferences.remove(BASE_PATH);
                basePath = preferences.get(BASE_PATH, null);
            }
        }

        return basePath;
    }



    public static Projects getBase() {
        if (base == null) {
            base = loadBase();
        }

        return base;
    }

    public static void setBase(Projects b) {
        base = new Projects(b.getDirectory());
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



    public static int getConsecutiveLinesValue() {
        return preferences.getInt(CONSECUTIVE_LINES_VALUE, CONSECUTIVE_LINES_VALUE_DEFAULT);
    }

    public static void setConsecutiveLinesValue(int value) {
        preferences.putInt(CONSECUTIVE_LINES_VALUE, value);
    }

    public static int getConsecutiveTokensValue() {
        return preferences.getInt(CONSECUTIVE_TOKENS_VALUE, CONSECUTIVE_TOKENS_VALUE_DEFAULT);
    }

    public static void setConsecutiveTokensValue(int value) {
        preferences.putInt(CONSECUTIVE_TOKENS_VALUE, value);
    }

    public static String getConsecutiveOption() {
        return preferences.get(CONSECUTIVE_OPTION, CONSECUTIVE_OPTION_DEFAULT);
    }

    public static void setConsecutiveOption(String str) {
        preferences.put(CONSECUTIVE_OPTION, str);
    }

}
