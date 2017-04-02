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
                }
                else if (combinedLine.isEmpty() == false) {
                    boolean isMultiComment = isMultiComment(combinedLine);
                    if (isMultiComment) {
                        combinedLine = clearMultiComment(combinedLine);
                    }

                    if (isMultiComment && savedLineNumber == 0) {
                        savedLineNumber = 0;
                    }
                    else if (savedLineNumber == 0) {
                        savedLineNumber = lineNumber;
                    }
                }

                line = br.readLine();
                lineNumber++;
            }

            // Usuniecie linii zawierajacy samo { - problem z normalizacja: else <br> {
            for (int i = 0; i < codeLines.size(); i++) {
                if (codeLines.get(i).code.equals("{")) {
                    codeLines.remove(i);
                    i--;
                }
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
        else if (line.startsWith("if(") || line.startsWith("else{") || line.equals("else")) {
            return true;
        }
        else if (line.startsWith("for(") || line.startsWith("while(")) {
            return true;
        }
        else if (line.startsWith("case") || line.startsWith("default:")) {
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
        line = clearLonelyNegativeNumbers(line);

        line = line.trim();
        if (line.startsWith("import")) {
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

}
