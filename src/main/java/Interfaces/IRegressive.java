package Interfaces;

import java.util.List;
public interface IRegressive {
    boolean loadKnowledgeBaseSet(String filename);
    boolean loadFactSet(String filename);
    void eraseFactSet();
    void eraseKnowledgeBase();
    boolean execute(String goal);
    List<String> returnHeadsOfRulesSet();
}
