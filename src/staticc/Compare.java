package staticc;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

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

	private static boolean MEASURE_TIME = true;

    private Compare() {

    }

    public static ArrayList<CompareFiles> runCompare() {
        if (Settings.getProject() == null || Settings.getBase() == null) {
            return null;
        }

        TokenProject tokenProject = Tokenization.tokenProject(Settings.getProject());
        TokenProjects baseProjects = Tokenization.tokenProjects(Settings.getBase());

        TimeWatch watch = TimeWatch.start();
        ArrayList<CompareFiles> compareFiles = compareMain(tokenProject, baseProjects);

        if (MEASURE_TIME == true) {
        	long passedTimeInMilliseconds = watch.time(TimeUnit.MILLISECONDS);
        	System.out.println("Compare Time: " + passedTimeInMilliseconds + " ms");
        }

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
                CompareFiles compareFiles = compareFiles(project.getDirectory(), projectFile, baseProject.getDirectory(), baseFile);
                if (compareFiles == null) {
                    continue;
                }
                compareProjects.add(compareFiles);
            }
        }

        return compareProjects;
    }

    private static CompareFiles compareFiles(File projectDir, TokenFile projectFile, File baseDir, TokenFile baseFile) {
        // false - pattern = project, text = base; true - odwrotnie
        boolean switchPlaces = projectFile.getTotalTokenLines() >= baseFile.getTotalTokenLines() ? false : true;

        PlagResult result = runAlgorithm(projectFile, baseFile, switchPlaces);

        if (result == null) {
            return null;
        }
        else if (result.getSimilarity() < Settings.getMinimalSimilarityAsValue()) {
            return null;
        }

        ArrayList<CompareFragments> compareFragments = createFragments(projectFile, baseFile, result, switchPlaces);
        if (compareFragments.isEmpty()) {
            return null;
        }

        CompareFiles compareFiles = new CompareFiles(projectDir,
                                                     projectFile.getFile(),
                                                     projectFile.getTotalLines(),
                                                     baseDir,
                                                     baseFile.getFile(),
                                                     baseFile.getTotalLines(),
                                                     findLongestMatch(result),
                                                     result.getSimilarity(),
                                                     compareFragments);
        return compareFiles;
    }

    private static PlagResult runAlgorithm(TokenFile projectFile, TokenFile baseFile, boolean switchPlaces) {
        String pattern = (switchPlaces ? baseFile.createTokenLineStrings() : projectFile.createTokenLineStrings());
        String text = (switchPlaces ? projectFile.createTokenLineStrings() : baseFile.createTokenLineStrings());
        if (pattern.isEmpty() || text.isEmpty()) {
            return null;
        }

        PlagResult result = GreedyStringTiling.run(pattern, text, Settings.getMinimalMatchedLinesValue(), (float)0.5, false);

        return result;
    }

    private static ArrayList<CompareFragments> createFragments(TokenFile projectFile, TokenFile baseFile, PlagResult result, boolean switchPlaces) {
        ArrayList<CompareFragments> compareFragments = new ArrayList<>();

        for (MatchVals tile : result.getTiles()){
            CompareFragments fragments = createFragment(projectFile, baseFile, tile, switchPlaces, tile.length);
            compareFragments.add(fragments);
        }

        return compareFragments;
    }

    private static CompareFragments createFragment(TokenFile projectFile, TokenFile baseFile, MatchVals tile, boolean switchPlaces, int length) {
        int projectPosition = (switchPlaces ? tile.textPosition : tile.patternPostion);
        int projectCodeLineStart = projectFile.getTokenLines().get(projectPosition).getLineNumber();
        int projectCodeLineDistance = projectFile.codeLineDistance(projectPosition, tile.length);
        int projectCodeLineEnd = projectCodeLineStart + projectCodeLineDistance;

        int basePosition = (switchPlaces ? tile.patternPostion : tile.textPosition);
        int baseCodeLineStart = baseFile.getTokenLines().get(basePosition).getLineNumber();
        int baseCodeLineDistance = baseFile.codeLineDistance(basePosition, tile.length);
        int baseCodeLineEnd = baseCodeLineStart + baseCodeLineDistance;

        FileMarked markedProject = new FileMarked(projectFile.getFile(), projectCodeLineStart, projectCodeLineEnd, length);
        FileMarked markedBase = new FileMarked(baseFile.getFile(), baseCodeLineStart, baseCodeLineEnd, length);

        return new CompareFragments(markedProject, markedBase);
    }

    private static int findLongestMatch(PlagResult result) {
        int longestMatch = 0;

        for (MatchVals tile : result.getTiles()) {
            if (tile.length > longestMatch) {
                longestMatch = tile.length;
            }
        }

        return longestMatch;
    }

}
