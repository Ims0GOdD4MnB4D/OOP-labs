import ini.Collections.SectionContainer;
import ini.model.IniParser;

import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {
        IniParser iniParser = new IniParser();
        SectionContainer sectionContainer = iniParser.parse(
                new File("D:\\jetbrains\\projs\\IdeaProjects\\INIparser\\src\\main\\java\\test.ini"));
        System.out.println
                (sectionContainer.
                        section("COMMON").
                            getString("DiscCachePath"));
        System.out.println
                (sectionContainer.
                        section("ADC_DEV").
                            getString("Driver"));
        System.out.println
                (sectionContainer.
                        section("NCMD").
                            getDouble("SampleRate"));
        System.out.println
                (sectionContainer.
                        section("COMMON").
                            getInt("StatisterTimeMs"));
    }
}