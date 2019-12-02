package org.edb.main.model;

public class TargetWebsite {
    private String targetURL;

    public String getTargetURL() {
        return targetURL;
    }

    public TargetWebsite(String targetURL) {
        this.targetURL = targetURL;
    }

    @Override
    public String toString() {
        return "TargetWebsite{" +
                "targetURL='" + targetURL + '\'' +
                '}';
    }
}
