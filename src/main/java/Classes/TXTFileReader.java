package Classes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TXTFileReader {

    public static List<String> readFacts(String filename) throws IOException {
        List<String> list = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filename));

        String line;
        String[] facts;

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

    public static List<String> readRules(String filename) throws IOException {
        List<String> list = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filename));

        String line;

        while((line = br.readLine()) != null){
            //.strip() removes whitespace characters
            list.add(line.strip());

            //removes non-characters, and adds to string array
            //facts = line.split("\\W+");
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
