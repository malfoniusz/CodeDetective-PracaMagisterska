package utilities.settings.model;

public class SettingsSave {

	public String project_path;
	public String base_path;
	public int minimal_matched_lines;
 	public double minimal_similarity_value;

 	public SettingsSave(String project_path,
 			String base_path,
 			int minimal_matched_lines,
 			double minimal_similarity_value) {
 		this.project_path = project_path;
 		this.base_path = base_path;
 		this.minimal_matched_lines = minimal_matched_lines;
 		this.minimal_similarity_value = minimal_similarity_value;
 	}

}
