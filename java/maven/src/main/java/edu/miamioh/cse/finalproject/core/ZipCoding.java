package edu.miamioh.cse.finalproject.core;

import java.util.*;

public class ZipCoding {
    private Map<String, Integer> zipCodeMapper = new HashMap<String, Integer>(){{
            put("||:::", 0);
            put(":::||", 1);
            put("::|:|", 2);
            put("::||:", 3);
            put(":|::|", 4);
            put(":|:|:", 5);
            put(":||::", 6);
            put("|:::|", 7);
            put("|::|:", 8);
            put("|:|::", 9);
    }};

    
    public TranslateMessage toZipCode(String barCode) {
        if (!valid(barCode)) {
            TranslateMessage result = new TranslateMessage();
            result.setMessage("Please input correct bar code.");
            result.setSuccess(false);
            return result;
        }
        List<String> barCodeList = splitBarCode(barCode);
        List<Integer> zipCodeList = translateBarCodeList(barCodeList);
        
        if( (!postValidByLength(barCodeList, zipCodeList)) 
                || (!postValidByCD(zipCodeList) ) ){
            TranslateMessage result = new TranslateMessage();
            result.setMessage("Please input correct bar code.");
            result.setSuccess(false);
            return result;
        }
        
        TranslateMessage result = new TranslateMessage();
        result.setMessage(joinToZipCode(zipCodeList));
        result.setSuccess(true);
        return result;
    }

    public List<String> splitBarCode(String barCode) {
        String barCodeWithoutFlag = barCode.substring(1, barCode.length() - 1);
        List<String> result = new ArrayList<String>();
        int currentIndex = 0;
        while (currentIndex < barCodeWithoutFlag.length()) {
            String barCodeItem = barCodeWithoutFlag.substring(currentIndex, currentIndex + 5);
            result.add(barCodeItem);
            currentIndex += 5;
        }
        return result;
    }

    public List<Integer> translateBarCodeList(List<String> barCodeList) {
        List<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < barCodeList.size(); i++) {
            String barCode = barCodeList.get(i);

            Integer integer = zipCodeMapper.get(barCode);
            if (integer != null) {
                result.add(integer);
            }
        }
        return result;
    }


    public boolean valid(String barCode) {
        int barcodeLength = barCode.length() - 2;
        return barcodeLength %5 ==0 && (barcodeLength/5 == 6 || barcodeLength/5 ==11);
    }

    public boolean postValidByLength(List<String> barCodeList, List<Integer> zipCodeList) {
        return barCodeList.size() == zipCodeList.size();
    }

    public boolean postValidByCD(List<Integer> zipCodeList) {
        Integer cd = new BarCoding().computeCDInteger(zipCodeList.subList(0, zipCodeList.size() - 1));
        return cd.equals(zipCodeList.get(zipCodeList.size() - 1));
    }

    public String joinToZipCode(List<Integer> zipCodeList) {
        List<Integer> zipCodeListWithoutCD = zipCodeList.subList(0, zipCodeList.size() - 1);
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < zipCodeListWithoutCD.size(); i++) {
            if(i == 5) {
                result.append("-");
            }
            result.append(zipCodeListWithoutCD.get(i));
        }
        return result.toString();
    }
}
