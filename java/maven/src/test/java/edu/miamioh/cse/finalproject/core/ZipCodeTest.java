package edu.miamioh.cse.finalproject.core;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;

public class ZipCodeTest {
    @Test
    public void main_should_translate_barcode_to_zipcode(){
        ZipCoding zipCoding = new ZipCoding();
        String barCode = "||:|:::|:|:|:::|:::||::||::|:|:|";
        TranslateMessage t = zipCoding.toZipCode(barCode);

        assertThat(t.getSuccess()).isEqualTo(true);
        assertThat(t.getMessage()).isEqualTo("95713");
    }

    @Test
    public void main_should_not_translate_barcode_to_zipcode(){
        ZipCoding zipCoding = new ZipCoding();
        String barCode = "||:|:::|:|:|:::|:::||::||::|:|||";
        TranslateMessage t = zipCoding.toZipCode(barCode);

        assertThat(t.getSuccess()).isEqualTo(false);
        assertThat(t.getMessage()).isEqualTo("Please input correct bar code.");
    }
    
    @Test
    public void should_split_barCode_to_barCodeList(){

        ZipCoding zipCoding = new ZipCoding();
        String barCode = "||:|:::|:|:|:::|:::||::||::|:|:|";
        List<String> barCodeList = zipCoding.splitBarCode(barCode);

        assertThat(barCodeList.size()).isEqualTo(6);
        assertThat(barCodeList).isEqualTo(Arrays.asList("|:|::", ":|:|:", "|:::|", ":::||", "::||:", ":|:|:"));
    }
    
    @Test
    public void should_map_barCodeList_to_zipCodeList(){
        ZipCoding zipCoding = new ZipCoding();
        List<String> barCodeList = Arrays.asList("|:|::", ":|:|:", "|:::|", ":::||", "::||:", ":|:|:");
        List<Integer> zipCodeList = zipCoding.translateBarCodeList(barCodeList);

        assertThat(zipCodeList.size()).isEqualTo(6);
        assertThat(zipCodeList).isEqualTo(Arrays.asList(9,5,7,1,3,5));
    }
    
    @Test
    public void should_valid_by_length(){
        ZipCoding zipCoding = new ZipCoding();
        String barCode = "||:|:::|:|:|:::|:::||::||::|:|:|";
        boolean isValid = zipCoding.valid(barCode);

        assertThat(isValid).isEqualTo(true);
    }

    @Test
    public void should_not_valid_by_length(){
        ZipCoding zipCoding = new ZipCoding();
        String barCode = "|:|:::|:|:|:::|:::||::||::|:|:|";
        boolean isValid = zipCoding.valid(barCode);

        assertThat(isValid).isEqualTo(false);
    }

    @Test
    public void should_not_valid_by_length_is_not_equal_6_or_11(){
        ZipCoding zipCoding = new ZipCoding();
        String barCode = "||:|:::|:|:|:::|:::||::||::|:|::|:|:|";
        boolean isValid = zipCoding.valid(barCode);

        assertThat(isValid).isEqualTo(false);
    }
    
    @Test
    public void should_post_valid_by_length(){
        ZipCoding zipCoding = new ZipCoding();
        List<String> barCodeList = Arrays.asList("|:|::", ":|:|:", "|:::|", ":::||", "::||:", ":|:|:");
        List<Integer> zipCodeList = Arrays.asList(9, 5, 7, 1, 3, 5);
        boolean isValid = zipCoding.postValidByLength(barCodeList,zipCodeList);

        assertThat(isValid).isEqualTo(true);
    }

    @Test
    public void should_map_barCodeList_to_zipCodeList_without_illegal_symbol(){
        ZipCoding zipCoding = new ZipCoding();
        List<String> barCodeList = Arrays.asList("|:|::", ":|:|:", "|:::#", ":::||", "::||:", ":|:|:");
        List<Integer> zipCodeList = zipCoding.translateBarCodeList(barCodeList);

        assertThat(zipCodeList.size()).isEqualTo(5);
        assertThat(zipCodeList).isEqualTo(Arrays.asList(9,5,1,3,5));
    }
    
    @Test
    public void should_post_valid_by_cd(){
        ZipCoding zipCoding = new ZipCoding();
        List<Integer> zipCodeList = Arrays.asList(9, 5, 7, 1, 3, 5);

        boolean isValid = zipCoding.postValidByCD(zipCodeList);
        assertThat(isValid).isEqualTo(true);
    }

    @Test
    public void should_not_post_valid_by_cd(){
        ZipCoding zipCoding = new ZipCoding();
        List<Integer> zipCodeList = Arrays.asList(9, 5, 7, 1, 3, 4);

        boolean isValid = zipCoding.postValidByCD(zipCodeList);
        assertThat(isValid).isEqualTo(false);
    }
    
    @Test
    public void should_join_to_string(){
        ZipCoding zipCoding = new ZipCoding();

        List<Integer> zipCodeList = Arrays.asList(9, 5, 7, 1, 3, 4);

        String zipCode = zipCoding.joinToZipCode(zipCodeList);

        assertThat(zipCode).isEqualTo("95713");

        zipCodeList = Arrays.asList(4,5,0,5,6,1,2,3,4,0);

        zipCode = zipCoding.joinToZipCode(zipCodeList);
        assertThat(zipCode).isEqualTo("45056-1234");
    }
}
