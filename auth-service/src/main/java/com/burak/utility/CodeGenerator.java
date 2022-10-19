package com.burak.utility;


import org.springframework.stereotype.Component;

@Component
public class CodeGenerator {

    /*
    public String generateCode(String value){
        String code = null;
        String[] data = value.split("-");
        for (int i = 0;i<data.length;i++){
            code +=data[i].charAt(0);
        }
        return code;
    }
    */

    /**
     * StringbUilder kullanımı daha hızlı çalışmasını saglar
     * @param value
     * @return
     */
    public static String generatedCode(String value){
        String [] data = value.split("-");
        StringBuilder newCode = new StringBuilder();

        for (String s: data) {
            newCode.append(s.charAt(0));
        }
        return newCode.toString();
    }


}
