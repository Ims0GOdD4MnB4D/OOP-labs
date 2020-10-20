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

    public Section section(String key) throws SectionNotFoundException {
        try  {
            return (data.get(key));
        } catch (RuntimeException ex) {
            throw new SectionNotFoundException();
        }
    }

    public void addSection(Section section) {
        data.put(section.getVal(), section);
    }

}
