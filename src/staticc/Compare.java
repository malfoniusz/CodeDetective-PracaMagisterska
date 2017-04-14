package staticc;

import java.util.ArrayList;

import model.CompareFiles;
import model.CompareFragments;
import model.FileMarked;
import model.tokenization.TokenFile;
import model.tokenization.TokenProject;
import model.tokenization.TokenProjects;

public class Compare {

    private Compare() {

    }

    public static ArrayList<CompareFiles> runCompare() {
        if (Settings.getProject() == null || Settings.getBase() == null) {
            return null;
        }

        TokenProject tokenProject = Tokenization.tokenProject(Settings.getProject());
        TokenProjects baseProjects = Tokenization.tokenProjects(Settings.getBase());

        ArrayList<CompareFiles> compareFiles = compareMain(tokenProject, baseProjects);
        return compareFiles;
    }

    private static ArrayList<CompareFiles> compareMain(TokenProject tokenProject, TokenProjects baseProjects) {
        ArrayList<CompareFiles> compareFiles = new ArrayList<>();

        for (TokenProject baseProject : baseProjects.getTokenProjects()) {
            ArrayList<CompareFiles> compareProjects = compareProjects(tokenProject, baseProject);
            compareFiles.addAll(compareProjects);
        }

        return compareFiles;
    }

    private static ArrayList<CompareFiles> compareProjects(TokenProject project, TokenProject baseProject) {
        ArrayList<CompareFiles> compareProjects = new ArrayList<>();

        ArrayList<TokenFile> projectFiles = project.getTokenFiles();
        ArrayList<TokenFile> baseFiles = baseProject.getTokenFiles();

        for (TokenFile baseFile : baseFiles) {
            for (TokenFile projectFile : projectFiles) {
                CompareFiles compareFiles = compareFiles(project.getName(), projectFile, baseProject.getName(), baseFile);
                if (compareFiles == null) {
                    continue;
                }
                compareProjects.add(compareFiles);
            }
        }

        return compareProjects;
    }

    private static CompareFiles compareFiles(String projectName, TokenFile projectFile, String baseName, TokenFile baseFile) {
        ArrayList<CompareFragments> compareFragments = algorithm(projectFile, baseFile);
        if (compareFragments.isEmpty()) {
            return null;
        }

        int similarity = calculateSimilarity(compareFragments, projectFile.getTotalTokenLines(), baseFile.getTotalTokenLines());

        CompareFiles compareFiles = new CompareFiles(projectName,
                                                     projectFile.getFile(),
                                                     projectFile.getTotalLines(),
                                                     baseName,
                                                     baseFile.getFile(),
                                                     baseFile.getTotalLines(),
                                                     similarity,
                                                     compareFragments);
        return compareFiles;
    }

    private static int calculateSimilarity(ArrayList<CompareFragments> compareFragments, int totalTokenLinesProject, int totalTokenLinesBase) {
        int totalProject = 0;
        int totalBase = 0;
        for (CompareFragments compareFragment : compareFragments) {
            FileMarked fileMarkedProject = compareFragment.getFileMarkedProject();
            FileMarked fileMarkedBase = compareFragment.getFileMarkedBase();

            int differenceProject = fileMarkedProject.getToLine() - fileMarkedProject.getFromLine();
            totalProject += differenceProject;
            int differenceBase = fileMarkedBase.getToLine() - fileMarkedBase.getFromLine();
            totalBase += differenceBase;
        }

        int similarityProject = (totalProject*100) / totalTokenLinesProject;
        int similarityBase = (totalBase*100) / totalTokenLinesBase;

        int higher = (similarityProject > similarityBase ? similarityProject : similarityBase);
        return higher;
    }

    private static ArrayList<CompareFragments> algorithm(TokenFile projectFile, TokenFile baseFile) {
        ArrayList<CompareFragments> compareFragments = new ArrayList<>();

        // TODO: algorytm

        FileMarked fileMarkedProject = new FileMarked(projectFile.getFile(), 20, 38);
        FileMarked fileMarkedBase = new FileMarked(baseFile.getFile(), 15, 32);
        CompareFragments fragments = new CompareFragments(fileMarkedProject, fileMarkedBase);
        compareFragments.add(fragments);

        return compareFragments;
    }

}