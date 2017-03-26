package staticc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import model.tokenization.CodeFile;
import model.tokenization.CodeLine;

public final class Normalization {

    public static CodeFile codeNormalization(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            ArrayList<CodeLine> codeLines = new ArrayList<>();

            int lineNumber = 1;
            String line = br.readLine();

            String combinedLine = "";
            int savedLineNumber = 0;

            while (line != null) {
                line = clearLine(line);
                line = clearUnnecesarySpaces(line);
                boolean lineEnd = codeLineEnds(line);

                if (lineEnd) {
                    if (combinedLine.isEmpty() == false) {
                        line = combinedLine + " " + line;
                        line = clearUnnecesarySpaces(line);
                        combinedLine = "";
                    }
                    else {
                        savedLineNumber = lineNumber;
                    }

                    CodeLine codeLine = new CodeLine(savedLineNumber, line);
                    codeLines.add(codeLine);

                    savedLineNumber = 0;

                }
                else if (line.isEmpty() == false) {
                    combinedLine += " " + line;

                    String combinedLineCleared = clearMultiComment(combinedLine);
                    if (combinedLine.equals(combinedLineCleared) == false && savedLineNumber == 0) {
                        savedLineNumber = 0;
                    }
                    combinedLine = combinedLineCleared;

                    if (savedLineNumber == 0) {
                        savedLineNumber = lineNumber;
                    }

                    combinedLine = combinedLine.trim();
                }

                line = br.readLine();
                lineNumber++;
            }

            CodeFile codeFile = new CodeFile(codeLines);
            return codeFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static boolean codeLineEnds(String line) {
        if (line.indexOf(";") != -1) {
            return true;
        }
        else if (line.indexOf("{") != -1) {
            return true;
        }

        return false;
    }

    private static String clearLine(String line) {
        // UWAGA przy przestawianiu kolejności wywoływanych instrukcji
        line = clearStrings(line);
        line = clearComment(line);
        line = line.replace("}", "");
        line = clearAccess(line);

        line = line.trim();
        if (line.startsWith("import")) {
            line = "";
        }
        if (line.startsWith("@")) {
            line = "";
        }

        return line;
    }

    private static String clearUnnecesarySpaces(String line) {
        return line.replaceAll("(?<!\\w)(\\s)|(\\s)(?!\\w)", "");
    }

    private static String clearAccess(String line) {
        line = line.replace("public ", "");
        line = line.replace("protected ", "");
        line = line.replace("private ", "");

        return line;
    }

    private static String clearStrings(String line) {
        if (line.contains("\"")) {  // "tes\"st" = ""
            line = line.replace("\\\"", "");
            line = line.replaceAll("\".*?\"", "\"\"");
        }

        if (line.contains("\'")) {  // '\'' = ''
            line = line.replace("\\\'", "");
            line = line.replaceAll("\'.*?\'", "\'\'");
        }

        return line;
    }

    private static String clearComment(String line) {
        if (line.contains("//")) {
            line = line.replaceAll("\\/\\/.*", "");
        }

        line = clearMultiComment(line);

        return line;
    }

    private static String clearMultiComment(String line) {
        if (line.indexOf("/*") != -1 && line.indexOf("*/") != -1) {
            line = line.replaceAll("\\/\\*.*\\*\\/", "");
        }

        return line;
    }

}
