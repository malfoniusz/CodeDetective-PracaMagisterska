package utilities;

import java.io.File;

import staticc.PropertiesReader;

public class Constants {

    public static final File FILE_T = new File(PropertiesReader.readProperty("file_t_path"));
    public static final File FILE_T_NORMALIZED = new File(PropertiesReader.readProperty("file_t_normalized_path"));
    public static final File FILE_T_TOKENIZED = new File(PropertiesReader.readProperty("file_t_tokenized_path"));

}
