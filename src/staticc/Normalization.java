package staticc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.tokenization.CodeLine;
import model.tokenization.NormalizedCode;

public final class Normalization {

    private Normalization() {

    }

    public static NormalizedCode codeNormalization(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            ArrayList<CodeLine> codeLines = new ArrayList<>();

            int lineNumber = 1;
            String line = br.readLine();

            String combinedLine = "";
            int savedLineNumber = 0;
            boolean isMultiComment = false;

            while (line != null) {
                line = clearLine(line);

                // Wykrywanie komentarzy wielolinijkowych
                if (line.indexOf("/*") != -1) {
                    isMultiComment = true;
                }
                else if (line.indexOf("*/") != -1) {
                    isMultiComment = false;
                }
                else if (isMultiComment == false) {
                    // Sprawdzenie czy nie ma kilku instrukcji w jednej linijce
                    ArrayList<String> instructions = findInstructions(line);
                    if (instructions.size() > 1) {
                        line = instructions.get(0);
                    }

                    combinedLine += " " + line;
                    combinedLine = clearUnnecesarySpaces(combinedLine);
                    boolean lineEnd = codeLineEnds(combinedLine);
                    if (lineEnd) {
                        if (savedLineNumber == 0) {
                            savedLineNumber = lineNumber;
                        }

                        CodeLine codeLine = new CodeLine(savedLineNumber, combinedLine);
                        codeLines.add(codeLine);

                        combinedLine = "";
                        savedLineNumber = 0;

                        if (instructions.size() > 1) {
                            line = instructions.get(1);
                            continue;
                        }
                    }
                    // Kod jest kontunuowany w kolejnej linii
                    else if (combinedLine.isEmpty() == false && savedLineNumber == 0) {
                        savedLineNumber = lineNumber;
                    }
                }

                line = br.readLine();
                lineNumber++;
            }

            // Usuniecie linii zawierajacy samo { - problem z normalizacja: else <br> {
            for (int i = 0; i < codeLines.size(); i++) {
                if (codeLines.get(i).getCode().equals("{")) {
                    codeLines.remove(i);
                    i--;
                }
            }

            NormalizedCode normalizedCode = new NormalizedCode(file, codeLines);
            return normalizedCode;
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
        else if (line.startsWith("if(") || line.startsWith("else{") || line.equals("else")) {
            return true;
        }
        else if (hasForLoop(line) || line.startsWith("while(")) {
            return true;
        }
        else if (line.startsWith("case") || line.startsWith("default:")) {
            return true;
        }

        return false;
    }

    private static boolean hasForLoop(String line) {
        if (line.startsWith("for(")) {
            return true;
        }

        return false;
    }

    private static String clearLine(String line) {
        // UWAGA przy przestawianiu kolejności wywoływanych instrukcji
        line = clearStrings(line);
        line = clearComment(line);
        line = clearMultiComment(line);
        line = line.replace("}", "");
        line = clearAccess(line);
        line = clearLonelyNegativeNumbers(line);

        line = line.trim();
        if (line.startsWith("import")) {
            line = "";
        }
        if (line.startsWith("package")) {
            line = "";
        }
        if (line.startsWith("@")) {
            line = "";
        }

        return line;
    }

    private static String clearLonelyNegativeNumbers(String line) {
        // Negatywne liczby sa usuwane, aby nie zaklocaly w procesie wykrywania operacji arytmetycznych
        return line.replaceAll("([^\\w])-[0-9]", "$1");
    }

    private static String clearUnnecesarySpaces(String line) {
        line = line.replaceAll("(?<!\\w)(\\s)", "");
        line = line.replaceAll("(\\s)(?!\\w)", "");
        return line;
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

    private static boolean isMultiComment(String line) {
        if (line.indexOf("/*") != -1 && line.indexOf("*/") != -1) {
            return true;
        }

        return false;
    }

    private static String clearMultiComment(String line) {
        if (isMultiComment(line)) {
            line = line.replaceAll("\\/\\*.*\\*\\/", "");
        }

        return line;
    }

    private static ArrayList<String> findInstructions(String line) {
        ArrayList<String> instructions = new ArrayList<>();
        line = clearUnnecesarySpaces(line);

        if (hasForLoop(line)) {
            return instructions;
        }

        Pattern pattern = Pattern.compile("[^;]*;");
        Matcher matcher = pattern.matcher(line);

        int count = 0;
        while (matcher.find()) {
            count++;
            if (count > 1) {
                String str = line.substring(matcher.start());
                instructions.add(str);
                break;
            }

            String str = line.substring(matcher.start(), matcher.end());
            instructions.add(str);
        }

        return instructions;
    }

}
