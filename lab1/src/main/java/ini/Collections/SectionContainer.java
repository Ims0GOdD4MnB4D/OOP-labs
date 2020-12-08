package ini.Collections;

import ini.section.Section;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SectionContainer {

    private Map<String, Section> value = new HashMap<>();

    public SectionContainer(Collection<Section> sections) {
        for(var item : sections) {
            value.put(item.getVal(), item);
        }
    }

    public SectionContainer(Section ... sections) {
        this(List.of(sections));
    }

    public Section section(String key) {
            return (value.get(key));
    }

    public void addSection(Section section) {
        value.put(section.getVal(), section);
    }

}
