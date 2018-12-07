package es.msanchez.frameworks.catchexception;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Examples {

    public String getImpossibleIndex() {
        final List<String> list = new ArrayList<>();
        return list.get(0);
    }

}
