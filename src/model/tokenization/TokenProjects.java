package model.tokenization;

import java.util.ArrayList;

public class TokenProjects {

    private ArrayList<TokenProject> tokenProjects;

    public TokenProjects(ArrayList<TokenProject> tokenProjects) {
        this.tokenProjects = tokenProjects;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (TokenProject tokenProject : tokenProjects) {
            sb.append(tokenProject.toString());
            sb.append(System.lineSeparator());
        }

        return sb.toString();
    }

    public ArrayList<TokenProject> getTokenProjects() {
        return tokenProjects;
    }

    public void setTokenProjects(ArrayList<TokenProject> tokenProjects) {
        this.tokenProjects = tokenProjects;
    }

}
