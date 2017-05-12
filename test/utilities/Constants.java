package utilities;

import java.io.File;

import staticc.PropertiesReader;

public class Constants {

    public static final File FILE_T = new File(PropertiesReader.readProperty("file_t_path"));
    public static final File FILE_T_NORMALIZED = new File(PropertiesReader.readProperty("file_t_normalized_path"));
    public static final File FILE_T_TOKENIZED_FULL = new File(PropertiesReader.readProperty("file_t_tokenized_full_path"));
    public static final File FILE_T_TOKENIZED_NORMAL = new File(PropertiesReader.readProperty("file_t_tokenized_normal_path"));
    public static final File FILE_T_TOKENIZED_MINIMALISTIC = new File(PropertiesReader.readProperty("file_t_tokenized_minimalistic_path"));

    public static final File PROJECT_FOLDER = new File(PropertiesReader.readProperty("test_project_folder"));
    public static final File BASE_FOLDER = new File(PropertiesReader.readProperty("test_base_folder"));

}
