package Interfaces;

import java.util.List;

public interface IProgressive {
        boolean loadKnowledgeBaseSet(String filename);
        boolean loadFactSet(String filename);
        void eraseFactSet();
        void eraseKnowledgeBase();
        List<String> returnCurrentFactSet();
        List<String> execute();
    }


