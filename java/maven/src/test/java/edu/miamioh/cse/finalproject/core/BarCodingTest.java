package edu.miamioh.cse.finalproject.core;

import edu.miamioh.cse.finalproject.core.BarCoding;
import edu.miamioh.cse.finalproject.core.TranslateMessage;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Created by mac on 5/1/16.
 */
public class BarCodingTest {
    
    private BarCoding barCoding = new BarCoding();
    @Test
    public void main_zipCode_to_barCode_Test(){
        String input = "95713";
        TranslateMessage instance = barCoding.toBarcode(input);
        assertThat(instance.getMessage()).isEqualTo("||:|:::|:|:|:::|:::||::||::|:|:|");
        assertThat(instance.getSuccess()).isEqualTo(true);
    }


    @Test
    public void zipCode_to_zipCode_list(){
        String input = "95713";
        List<String> zipCodeList = barCoding.splitZipCode(input);
        assertThat(zipCodeList.size()).isEqualTo(5);
        assertThat(zipCodeList).isEqualTo(Arrays.asList("9", "5", "7", "1", "3"));
    }

    @Test
    public void zipCodeList_to_barCodeList(){
        List<String> zipCodeLit = Arrays.asList("9");
//        List<String> zipCodeLit = new ArrayList<String>();
//        zipCodeLit.add("9");
//        zipCodeLit.add("5");
        List<String> barCodeList = barCoding.translateZipCodeList(zipCodeLit);

        assertThat(barCodeList.size()).isEqualTo(1);
        assertThat(barCodeList).isEqualTo(Arrays.asList("|:|::"));

    }
    
    @Test
    public void combine_barCode_List() {
        List<String> barCodes = Arrays.asList("1", "2", "3");
        String result = barCoding.combineList(barCodes);
        assertThat(result).isEqualTo("123");
    }
    
    @Test
    public void should_compute_cd(){
        List<String> zipCodeList = Arrays.asList("9", "5", "7", "1", "3");
        String CD = barCoding.computeCD(zipCodeList);
        assertThat(CD).isEqualTo("5");
    }

    @Test
    public void should_compute_cd_2(){
        List<String> zipCodeList = Arrays.asList("9", "5", "7", "1", "4");
        String CD = barCoding.computeCD(zipCodeList);
        assertThat(CD).isEqualTo("4");
    }


    @Test
    public void main_zipCode_to_barCode_Error(){
        String input = "c";
        TranslateMessage instance = barCoding.toBarcode(input);
        assertThat(instance.getMessage()).isEqualTo("Please input correct zip code.");
        assertThat(instance.getSuccess()).isEqualTo(false);
    }
    
    @Test
    public void should_no_integer_be_Error(){
        String input = "ddddd";        
        assertThat(barCoding.valid(input)).isEqualTo(false);


        input = "123.4";
        assertThat(barCoding.valid(input)).isEqualTo(false);

    }

    @Test
    public void should_ten_digit_with_hyphen_be_valid(){
        String input = "45056-1234";
        assertThat(barCoding.valid(input)).isEqualTo(true);
        
    }

    @Test
    public void should_other_expression_with_hyphen_be_error(){
        String input = "450d6-1234";
        assertThat(barCoding.valid(input)).isEqualTo(false);
        input = "dddd-ddddd";
        assertThat(barCoding.valid(input)).isEqualTo(false);
        
    }

    @Test
    public void should_ten_digit_without_hyphen_be_error(){
        String input = "1234567890";
        assertThat(barCoding.valid(input)).isEqualTo(false);

    }

    @Test
    public void should_other_expression_with_hyphen_be_error_2(){
        String input = "-1234";
        assertThat(barCoding.valid(input)).isEqualTo(false);
    }

    @Test
    public void main_ten_digit_zipCode_to_barCode(){
        String input = "45056-1234";
        TranslateMessage instance = barCoding.toBarcode(input);
        assertThat(instance.getMessage()).isEqualTo("|:|::|:|:|:||::::|:|::||:::::||::|:|::||::|::|||:::|");
        assertThat(instance.getSuccess()).isEqualTo(true);
    }

    @Test
    public void main_nine_digit_zipCode_to_barCode(){
        String input = "450561234";
        TranslateMessage instance = barCoding.toBarcode(input);
        assertThat(instance.getMessage()).isEqualTo("|:|::|:|:|:||::::|:|::||:::::||::|:|::||::|::|||:::|");
        assertThat(instance.getSuccess()).isEqualTo(true);
    }
}


