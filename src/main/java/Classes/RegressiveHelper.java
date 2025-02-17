package Classes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RegressiveHelper {
    public static Queue<String> createS(List<String> rules, String target){
            Queue startingS = new LinkedList();
            for (String rule : rules){
                if (rule.contains("<-")){
                    String[] headTail = rule.split("<-");
                    if (headTail[0].equals(target)){
                        startingS.add(rule);
                    }
                }else {
                    if (rule.equals(target)) startingS.add(rule);
                }
            }
            return startingS;
    }

    public static List<String> returnPremises(String r){
        List<String> premises = new ArrayList<>();
        if (r.contains("<-")){
            String[] headTail = r.split("<-");
            String[] tail = headTail[1].split(",");
            for (String premise : tail){
                premises.add(premise);
            }
        }
        return premises;
    }

    public static boolean checkIfPremiseTrue(String premise, List<String> facts){
       if (facts.contains(premise)){
           return true;
       }else {
           return false;
       }
    }

    public static String returnHead(String r){
        String[] headTail = r.split("<-");
        return headTail[0];
    }

    public static boolean wnRegres(List<String> rules,List<String> facts, String target) {
        if (facts.contains(target)) {
            return true;
        }

        Queue<String> startinS = createS(rules, target);

        if (startinS.isEmpty()){
            return false;
        }
        boolean premisIsTrue = false;

        while (!premisIsTrue && !startinS.isEmpty()){
                String r = startinS.poll();
                String head = returnHead(r);
               List<String> premises = returnPremises(r);
               premisIsTrue = true;
               for (String premise : premises){
                   if (!checkIfPremiseTrue(premise,facts)){
                      premisIsTrue = wnRegres(rules,facts,premise);
                      if (!premisIsTrue){
                          break;
                      }
                   }
               }

            if (premisIsTrue){
                facts.add(head);
                return true;
            }
        }
        return false;
    }
}
