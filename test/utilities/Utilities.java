package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import model.tokenization.CodeLine;
import model.tokenization.NormalizedCode;
import model.tokenization.Token;
import model.tokenization.TokenFile;
import model.tokenization.TokenLine;

public class Utilities {

	private Utilities() {

	}

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

        return
        		new NormalizedCode(file, codeLines);
    }

    public static TokenFile loadTokenizedFile(File file) {
    	ArrayList<TokenLine> tokenLines = new ArrayList<>();

        try {
            Scanner A_scanner = new Scanner(file);
            while(A_scanner.hasNextLine()) {
            	String line = A_scanner.nextLine();
            	Scanner B_lineScanner = new Scanner(line);

                int lineNumber = B_lineScanner.nextInt();

                ArrayList<Token> tokens = new ArrayList<>();
                while(B_lineScanner.hasNext()) {
                	String strToken = B_lineScanner.next();
                	Token token = Token.valueOf(strToken);
                	tokens.add(token);
                }
                B_lineScanner.close();

                TokenLine tokenLine = new TokenLine(lineNumber, tokens);
                tokenLines.add(tokenLine);
            }

            A_scanner.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }

        return
        		new TokenFile(file, tokenLines);
    }

}
