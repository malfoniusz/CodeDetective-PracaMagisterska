package model;

import java.io.File;
import java.util.prefs.Preferences;

public class Settings {

    private final Preferences preferences = Preferences.userRoot();
    private final String BASE_PATH = "base_path";
    private final String CONSECUTIVE_LINES_VALUE = "consecutive_lines_value";
    private final int CONSECUTIVE_LINES_VALUE_DEFAULT = 10;
    private final String CONSECUTIVE_TOKENS_VALUE = "consecutive_tokens_value";
    private final int CONSECUTIVE_TOKENS_VALUE_DEFAULT = 30;
    private final String CONSECUTIVE_OPTION = "consecutive_option";
    private final String CONSECUTIVE_OPTION_DEFAULT = ConsecutiveOption.LINES.toString();

    private Project project;    // Wybrany projekt do porownania
    private Projects base;      // Baza projektow, z którymi projekt bedzie porownywany
    private int consecutiveLinesValue;
    private int consecutiveTokensValue;
    private String consecutiveOption;

    public Settings() {
        this.project = null;
        this.base = loadBase();
        this.consecutiveLinesValue = loadConsecutiveLinesValue();
        this.consecutiveTokensValue = loadConsecutiveTokensValue();
        this.consecutiveOption = loadConsecutiveOption();
    }

    private int loadConsecutiveLinesValue() {
        int consecutiveLines = preferences.getInt(CONSECUTIVE_LINES_VALUE, -1);

        if (consecutiveLines != -1) {
            return consecutiveLines;
        }

        setConsecutiveLinesValue(CONSECUTIVE_LINES_VALUE_DEFAULT);
        return CONSECUTIVE_LINES_VALUE_DEFAULT;
    }

    private int loadConsecutiveTokensValue() {
        int consecutiveTokens = preferences.getInt(CONSECUTIVE_TOKENS_VALUE, -1);

        if (consecutiveTokens != -1) {
            return consecutiveTokens;
        }

        setConsecutiveTokensValue(CONSECUTIVE_TOKENS_VALUE_DEFAULT);
        return CONSECUTIVE_TOKENS_VALUE_DEFAULT;
    }

    private String loadConsecutiveOption() {
        String option = preferences.get(CONSECUTIVE_OPTION, null);

        if (option != null) {
            return option;
        }

        setConsecutiveOption(CONSECUTIVE_OPTION_DEFAULT);
        return CONSECUTIVE_OPTION_DEFAULT;
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

    private void setPreferenceBasePath(String basePath) {
        preferences.put(BASE_PATH, basePath);
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

    public int getConsecutiveLinesValue() {
        return consecutiveLinesValue;
    }

    public void setConsecutiveLinesValue(int consecutiveLinesValue) {
        this.consecutiveLinesValue = consecutiveLinesValue;
        preferences.putInt(CONSECUTIVE_LINES_VALUE, consecutiveLinesValue);
    }

    public int getConsecutiveTokensValue() {
        return consecutiveTokensValue;
    }

    public void setConsecutiveTokensValue(int consecutiveTokensValue) {
        this.consecutiveTokensValue = consecutiveTokensValue;
        preferences.putInt(CONSECUTIVE_TOKENS_VALUE, consecutiveTokensValue);
    }

    public String getConsecutiveOption() {
        return consecutiveOption;
    }

    public void setConsecutiveOption(String consecutiveOption) {
        this.consecutiveOption = consecutiveOption;
        preferences.put(CONSECUTIVE_OPTION, consecutiveOption);
    }

}
