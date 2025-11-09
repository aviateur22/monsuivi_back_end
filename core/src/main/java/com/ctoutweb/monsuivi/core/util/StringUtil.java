package com.ctoutweb.monsuivi.core.util;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class StringUtil {
    public static String removeAccent(String textWithAccent) {
        String normalize = Normalizer.normalize(textWithAccent, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(normalize).replaceAll("");
    }
}
