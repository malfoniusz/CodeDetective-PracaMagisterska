package model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Project {

    private File directory;
    private ArrayList<File> files;

    public Project(File directory) {
        this.directory = directory;
        this.files = searchForFiles(directory);
    }

    private ArrayList<File> searchForFiles(File directory) {
        ArrayList<File> javaFiles = new ArrayList<File>();

        String path = directory.getAbsolutePath();
        try {
            @SuppressWarnings("resource")
            Stream<Path> paths = Files.walk(Paths.get(path));
            paths = paths.filter(p -> p.toString().endsWith(".java"));

            paths.forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    File javaFile = new File(filePath.toString());
                    javaFiles.add(javaFile);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return javaFiles;
    }

    public File getDirectory() {
        return directory;
    }

    public void setDirectory(File directory) {
        this.directory = directory;
    }

    public ArrayList<File> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<File> files) {
        this.files = files;
    }

    @Override
    public String toString() {
        String str = directory.getPath() + "\n";
        for (File file : files) {
            str += file.getPath() + "\n";
        }
        return str;
    }
}
