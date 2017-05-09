package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import model.tokenization.CodeLine;
import model.tokenization.NormalizedCode;

public class Utilities {

    public static NormalizedCode loadNormalizedFile(File file) {
        ArrayList<CodeLine> codeLines = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()) {
                int lineNumber = scanner.nextInt();
                String lineCode = scanner.nextLine();
                lineCode = lineCode.trim();

                CodeLine codeLine = new CodeLine(lineNumber, lineCode);
                codeLines.add(codeLine);
            }

            scanner.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }

        NormalizedCode normCode = new NormalizedCode(file, codeLines);
        return normCode;
    }

}
