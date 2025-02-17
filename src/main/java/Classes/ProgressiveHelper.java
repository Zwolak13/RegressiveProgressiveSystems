package Classes;

import java.util.*;

public class ProgressiveHelper {

    public static boolean tailContainsFacts(String rule, List<String> facts) {
        //checks if rule is made of facts
        if (rule.contains("<-")) {
            //splits rule into head and tail
            String[] tmpRule = rule.split("<-");
            String head = tmpRule[0];
            String[] tail = tmpRule[1].split(",");

            int counter = 0;

            //checks if factSet contains facts from rule
            for (String factToCheck : tail) {
                if (facts.contains(factToCheck)) {
                    counter++;
                }
            }

            if (counter == tail.length) {
                return true;
            }
        } else {
            return true;
        }

        return false;
    }

    public static String returnHead(String rule) {
        String[] headAndTail = rule.split("<-");
        return headAndTail[0];
    }

    //TODO
    public static Queue<String> createS(List<String> rules, List<String> facts,Queue<String> listS) {
        Queue<String> resultList = new LinkedList<>();
        resultList.addAll(listS);
        for (String rule : rules) {
            if(!resultList.contains(rule)){
                //System.out.println("in loop");
                if (tailContainsFacts(rule, facts)) {
                    //System.out.println("true");
                    resultList.add(rule);
                }
            }
        }
        return resultList;
    }
}
