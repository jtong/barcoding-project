package edu.miamioh.cse.finalproject.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mac on 5/1/16.
 */
public class BarCoding {

    private  List<String> barCodeList = Arrays.asList("||:::",":::||","::|:|","::||:",":|::|",":|:|:",":||::","|:::|","|::|:","|:|::");

    public  TranslateMessage toBarcode(String input) {
//        if(input.length()==5)return t.getSuccess()=
        if(!valid(input)){
            TranslateMessage result = new TranslateMessage();
            result.setMessage("Please input correct zip code.");
            result.setSuccess(false);
            return result;
        }
        String formattedInput = input;
        if(input.length() == 10){
            formattedInput = input.substring(0, input.indexOf("-"))+ input.substring(input.indexOf("-")+1);
        }
        String digitalBarcode = generateDigitalBarCodes(formattedInput);
        String cd = computeCD(splitZipCode(formattedInput));
        String cdBarcode = getBarCode(cd);
        String message = "|"+digitalBarcode+cdBarcode+"|";

        TranslateMessage result = new TranslateMessage();
        result.setMessage(message);
        result.setSuccess(true);
        return result;
    }

     boolean valid(String input) {
        if(input.length() == 5 || input.length() == 9 || input.length() == 10){
            String formattedInput = input;
            if(input.length() == 10){
                if(input.indexOf("-") == -1 ||input.indexOf("-") != 5 ){
                    return false;
                }
                formattedInput = input.substring(0, input.indexOf("-"))+ input.substring(input.indexOf("-")+1);
            }
            try {
                int i = Integer.parseInt(formattedInput);
                if(i<0){
                    return false;
                }
            }catch(NumberFormatException e){
                return false;
            }
            return true;
        }
        return false;
    }

    private  String generateDigitalBarCodes(String input) {
        List<String> zipCodeList = splitZipCode(input);
        List<String> barCodeList = translateZipCodeList(zipCodeList);

        return combineList(barCodeList);
    }

     List<String> splitZipCode(String input) {
        return Arrays.asList(input.split(""));
    }

    public  List<String> translateZipCodeList(List<String> zipCodeLit) {
        List<String> result = new ArrayList<String>();
        for(int i = 0 ; i < zipCodeLit.size();i++){
            result.add(getBarCode(zipCodeLit.get(i)));
        }
        return result;
    }

    private  String getBarCode(String digit) {
        return barCodeList.get(Integer.valueOf(digit));
    }


    public  String combineList(List<String> barCodes) {
        return String.join("", barCodes);
    }

    public  String computeCD(List<String> zipCodeList) {
        List<Integer> zipCodeListInteger = new ArrayList<Integer>();
        for (int i = 0; i < zipCodeList.size(); i++) {
            zipCodeListInteger.add(Integer.valueOf(zipCodeList.get(i)));
        }
        return computeCDInteger(zipCodeListInteger).toString();
    }

    public Integer computeCDInteger(List<Integer> zipCodeList){
        int summary = 0;
        for (int i = 0; i < zipCodeList.size(); i++) {
            summary += zipCodeList.get(i);
        }
        int modTenLeft = summary % 10;
        if(modTenLeft == 0){
            return 0;
        }
        return 10 - modTenLeft;

    }
    
}
