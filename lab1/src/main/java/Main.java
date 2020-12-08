import ini.Collections.SectionContainer;
import ini.model.IniParser;

import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {
        IniParser iniParser = new IniParser();
//        try {
            SectionContainer sectionContainer = iniParser.parseFile(
                    new File("D:\\jetbrains\\projs\\IdeaProjects\\OOP-labs\\lab1\\src\\main\\java\\test.ini"));
            System.out.println(
                    sectionContainer.
                            section("COMMON")
                                .getKey("DiskCachePath"));
            System.out.println
                    (sectionContainer.
                            section("ADC_DEV").
                            getKey("Driver"));
            System.out.println
                    (sectionContainer.
                            section("ADC_DEV").
                                getDouble("SampleRate"));
            System.out.println
                    (sectionContainer.
                            section("ADC_DEV").
                                getString("Driver"));
            System.out.println
                    (sectionContainer.
                            section("COMMON").
                                getInt("StatisterTimeMs"));
            System.out.println
                    (sectionContainer.
                            section("ADC_DEV").
                                getString("Driver"));
            System.out.println
                    (sectionContainer.
                            section("NCMD").
                                getString("BufferLenSeconds"));
        }
//        catch(Exception ex) {
//            throw new FileNotFoundException("File not found");
//        }
    }