package Classes;

import java.io.*;
import java.util.*;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class XLSXFileReader {

    public static List<String> readRules(String filename) throws IOException{
        Map<String, List<String>> map = new HashMap<>();
        List<String> listToReturn = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filename);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);


            for (Row row : sheet) {
                StringBuilder sb = new StringBuilder();
                if (row.getRowNum()!= 1 ){
                    for (Cell cell : row) {
                        if (cell.getColumnIndex() != 0 && cell.getColumnIndex() != 2 && cell.getColumnIndex() != 22){
                            if (cell.getColumnIndex() == 1 && !cell.getStringCellValue().equals("") && !cell.getStringCellValue().equals(null)){
                                sb.append(cell.getStringCellValue() + "<-");
                            }else {
                                if (!cell.getStringCellValue().equals("")){
                                    sb.append(cell.getStringCellValue() + ",");
                                }
                            }
                        }
                    }
                }

                if(sb.toString().contains("<-")){
                    listToReturn.add(sb.toString().substring(0,sb.length()-1));
                }
            }

        } catch (IOException e) {
            System.out.println("XLSX error");
        }

        return listToReturn;
    }


    public static List<String> readFacts(String filename) throws IOException{
        List<String> list = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filename));

        String line;
        String[] facts = null;

        while((line = br.readLine()) != null){
            //.strip() removes whitespace characters
            line.strip();

            //removes non-characters, and adds to string array
            facts = line.split(",");

            for(String fact : facts){
                list.add(fact);
            }
        }

        br.close();
        return  list;
    }
    public static List<String> getTargetList(String filename) throws IOException {
        List<String> resultList = new ArrayList<>();
        List<String> rulesList = readRules(filename);

        for (String rule : rulesList){
            String[] splitedRule = rule.split("<-");
            resultList.add(splitedRule[0]);
        }

        return resultList;
    }


}