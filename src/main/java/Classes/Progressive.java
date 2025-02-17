package Classes;

import Interfaces.IProgressive;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Progressive implements IProgressive{

    private List<String> baseSet;
    private List<String> factSet;
    private String target;

    public Progressive()  {
        this.baseSet = new ArrayList<>();
        this.factSet = new ArrayList<>();
        this.target = null;
    }

    public Progressive(String target) {
        this.baseSet = new ArrayList<>();
        this.factSet = new ArrayList<>();
        this.target = target;
    }

    @Override
    public boolean loadKnowledgeBaseSet(String filename) {
       try{
           if(filename.endsWith(".txt")){
                baseSet = TXTFileReader.readRules(filename);
           } else if (filename.endsWith(".xlsx")) {
                baseSet = XLSXFileReader.readRules(filename);
           }
           return true;

       }catch (Exception e){
           return false;
       }
    }

    @Override
    public boolean loadFactSet(String filename) {
        try{
            if(filename.endsWith(".txt")){
                factSet = TXTFileReader.readFacts(filename);
            } else if (filename.endsWith(".xlsx")) {
                factSet = XLSXFileReader.readFacts(filename);
            }
            return true;

        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    @Override
    public void eraseFactSet() {
        this.factSet.clear();
    }

    @Override
    public void eraseKnowledgeBase() {
        this.baseSet.clear();
    }

    @Override
    public List<String> returnCurrentFactSet() {return this.factSet;}


    //TODO
    @Override
    public List<String> execute() {
        Queue<String> listS = new LinkedList<>();
        List<String> listA = new ArrayList<>();

        listS.addAll(ProgressiveHelper.createS(baseSet, factSet,listS));

        while(!listS.isEmpty() && !factSet.contains(target)){
            String r = ProgressiveHelper.returnHead(listS.peek());
            listA.add(listS.peek());

            //checks if rule was already added to facts
            if(!factSet.contains(r))
                factSet.add(r);

            baseSet.removeAll(listA);

            listS = ProgressiveHelper.createS(baseSet,factSet,listS);
            listS.removeAll(listA);
        }

        return factSet;
    }

}
