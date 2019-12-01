package org.edb.main.model;

public class TargetProgram {
    private String targetName;
    private String targetPath;

    public TargetProgram(String targetName, String targetPath) {
        this.targetName = targetName;
        this.targetPath = targetPath;
    }

    @Override
    public String toString() {
        return "TargetProgram{" +
                "targetName='" + targetName + '\'' +
                ", targetPath='" + targetPath + '\'' +
                '}';
    }
}
