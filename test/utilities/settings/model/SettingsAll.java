package utilities.settings.model;

public class SettingsAll {

	public SettingsSave settingsSave;
	public SettingsTokensSave settingsTokensSave;

	public SettingsAll(SettingsSave settingsSave, SettingsTokensSave settingsTokensSave) {
		this.settingsSave = settingsSave;
		this.settingsTokensSave = settingsTokensSave;
	}
}
