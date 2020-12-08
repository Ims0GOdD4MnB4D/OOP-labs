package ini.model;

import Exceptions.InvalidFormatException;
import Exceptions.InvalidTypeException;
import ini.Collections.SectionContainer;
import ini.key.Key;
import ini.section.Section;

import java.io.FileNotFoundException;
import java.util.regex.Pattern;
import java.util.Scanner;
import java.io.File;

public class IniParser {

    private Pattern sectionReg =
                        Pattern.compile("\\s*" + "\\[[a-zA-Z_]*]\\s*" + "\\s*(" + ";.*" + ")?");
    private Pattern sectionNameReg =
                        Pattern.compile("\\[[a-zA-Z_]*]\\s*");

    private Pattern keyReg = Pattern.compile("\\s*" + "\\w+\\s*=\\s*[\\w.,/]+"
                                        + "\\s*(" + ";.*" + ")?");

    public Key parseKey(String line) throws InvalidFormatException, InvalidTypeException {
        if (!keyReg.matcher(line).matches())
            throw new InvalidFormatException();

        String str = line.replaceFirst(";.*", "").trim();

        Key key = new Key(str.split("=")[0].stripTrailing(),
                        str.split("=")[1].stripLeading());

        return key;
    }

    public Section parseSec(Scanner opr) {

            String secNameString = opr.next(sectionReg);

            Section refreshedSec = new Section(secNameString);

            while (!opr.hasNext(sectionReg) && opr.hasNextLine()) {
                String line = opr.nextLine();

                if (keyReg.matcher(line).matches())
                    refreshedSec.addKey(
                            parseKey(line)
                    );
            }

            return refreshedSec;
    }

    public SectionContainer parseFile(File iniToParse) throws FileNotFoundException {
        SectionContainer sectionContainer = new SectionContainer();

        Scanner scanner = new Scanner(iniToParse);
        while (scanner.hasNextLine()) {
            if (scanner.hasNext(sectionNameReg))
                sectionContainer.addSection(parseSec(scanner));
            else
                scanner.nextLine();
        }
        return sectionContainer;
    }
}