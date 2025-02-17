package Classes;

import Interfaces.IRegressive;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Regressive implements IRegressive {

    private List<String> baseSet;
    private List<String> factSet;

    public Regressive() {
        this.baseSet = new ArrayList<>();
        this.factSet = new ArrayList<>();
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
    public boolean execute(String goal) {
       return RegressiveHelper.wnRegres(baseSet,factSet,goal);
    }

    @Override
    public List<String> returnHeadsOfRulesSet() {
        return null;
    }

}
