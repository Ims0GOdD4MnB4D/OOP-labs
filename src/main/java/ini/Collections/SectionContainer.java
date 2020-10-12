package ini.Collections;

import Exceptions.SectionNotFoundException;
import ini.section.Section;

import java.util.*;

public class SectionContainer {

    private final Map<String, Section> data = new LinkedHashMap<>();

    public SectionContainer(Collection<Section> sections) {
        sections.forEach(section -> data.put(section.getVal(), section));
    }

    public Map<String, Section> getData() {
        return data;
    }


    public SectionContainer(Section ... sections) {
        this(List.of(sections));
    }

    public Section section(String name) throws SectionNotFoundException {
        return Optional
                .ofNullable(data.get(name))
                .orElseThrow(SectionNotFoundException::new);
    }

    public void addSection(Section section) {
        data.put(section.getVal(), section);
    }

    public void removeSection(String name) {
        data.remove(name);
    }
}
