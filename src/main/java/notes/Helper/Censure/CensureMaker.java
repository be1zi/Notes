package notes.Helper.Censure;

import notes.Model.Censure;

import java.util.List;

public class CensureMaker {

    public static String createCensuredText(String fromText, List<Censure> censuredWords) {

        if (fromText == null || fromText.isEmpty()) {
            return null;
        }

        if (censuredWords == null || censuredWords.size() == 0) {
            return fromText;
        }

        for (Censure censure : censuredWords) {
            fromText = fromText.replaceAll(censure.getText(), censure.getPattern());
        }

        return fromText;
    }

    public static String deleteCensures(String fromText, List<Censure> censuredWords) {

        if (fromText == null || fromText.isEmpty()) {
            return null;
        }

        if (censuredWords == null || censuredWords.size() == 0) {
            return fromText;
        }

        fromText = fromText.replaceAll("\\*", "_");

        for (Censure censure: censuredWords) {
            String pattern = censure.getPattern().replaceAll("\\*", "_");
            fromText = fromText.replaceAll(pattern, censure.getText());
        }

        return fromText;
    }
}
