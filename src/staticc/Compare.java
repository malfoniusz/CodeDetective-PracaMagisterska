package staticc;

import java.util.ArrayList;

import com.nlputil.gst.GreedyStringTiling;
import com.nlputil.gst.MatchVals;
import com.nlputil.gst.PlagResult;

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
        // false - pattern = project, text = base; true - odwrotnie
        boolean switchPlaces = projectFile.getTotalTokenLines() >= baseFile.getTotalTokenLines() ? false : true;

        PlagResult result = runAlgorithm(projectFile, baseFile, switchPlaces);

        ArrayList<CompareFragments> compareFragments = createFragments(projectFile, baseFile, result, switchPlaces);
        if (compareFragments.isEmpty()) {
            return null;
        }

        CompareFiles compareFiles = new CompareFiles(projectName,
                                                     projectFile.getFile(),
                                                     projectFile.getTotalLines(),
                                                     baseName,
                                                     baseFile.getFile(),
                                                     baseFile.getTotalLines(),
                                                     result.getSimilarity(),
                                                     compareFragments);
        return compareFiles;
    }

    private static PlagResult runAlgorithm(TokenFile projectFile, TokenFile baseFile, boolean switchPlaces) {
        String pattern = (switchPlaces ? baseFile.createTokenLineStrings() : projectFile.createTokenLineStrings());
        String text = (switchPlaces ? projectFile.createTokenLineStrings() : baseFile.createTokenLineStrings());
        PlagResult result = GreedyStringTiling.run(pattern, text, Settings.getConsecutiveLinesValue(), (float)0.5, false);

        return result;
    }

    private static ArrayList<CompareFragments> createFragments(TokenFile projectFile, TokenFile baseFile, PlagResult result, boolean switchPlaces) {
        ArrayList<CompareFragments> compareFragments = new ArrayList<>();

        for (MatchVals tiles : result.getTiles()){
            CompareFragments fragments = createFragment(projectFile, baseFile, tiles, switchPlaces);
            compareFragments.add(fragments);
        }

        return compareFragments;
    }

    private static CompareFragments createFragment(TokenFile projectFile, TokenFile baseFile, MatchVals tiles, boolean switchPlaces) {
        int projectPosition = (switchPlaces ? tiles.textPosition : tiles.patternPostion);
        int projectCodeLineStart = projectFile.getTokenLines().get(projectPosition).getLineNumber();
        int projectCodeLineDistance = projectFile.codeLineDistance(projectPosition, tiles.length);
        int projectCodeLineEnd = projectCodeLineStart + projectCodeLineDistance;

        int basePosition = (switchPlaces ? tiles.patternPostion : tiles.textPosition);
        int baseCodeLineStart = baseFile.getTokenLines().get(basePosition).getLineNumber();
        int baseCodeLineDistance = baseFile.codeLineDistance(basePosition, tiles.length);
        int baseCodeLineEnd = baseCodeLineStart + baseCodeLineDistance;

        FileMarked markedProject = new FileMarked(projectFile.getFile(), projectCodeLineStart, projectCodeLineEnd);
        FileMarked markedBase = new FileMarked(baseFile.getFile(), baseCodeLineStart, baseCodeLineEnd);

        return new CompareFragments(markedProject, markedBase);
    }

}
