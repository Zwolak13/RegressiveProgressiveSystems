module org.example.pojektzaliczeniowy {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.xml;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;

    opens org.example.pojektzaliczeniowy to javafx.fxml;
    exports org.example.pojektzaliczeniowy;
}