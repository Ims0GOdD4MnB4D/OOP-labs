package ini.model;

import exceptions.InvalidFormatException;
import exceptions.InvalidTypeException;
import exceptions.ParserException;
import ini.collection.SectionContainer;
import ini.property.Property;
import ini.section.Section;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IniParser {

    private final String sectionReg = "\\[\\w+]";
    private final String propertyReg = "\\w+\\s*=\\s*[\\w.,/]+";

    private final String commentReg = ";.*";
    private final String lineEndingReg = "\\s*(" + commentReg + ")?";

    private final Pattern validLinePattern =
            Pattern.compile("\\s*(" + sectionReg + "|" + propertyReg + ")?" + lineEndingReg);

    private final Pattern sectionNameStringPattern = Pattern.compile("\\s*" + sectionReg + lineEndingReg);
    private final Pattern sectionNamePattern = Pattern.compile(sectionReg);

    private final Pattern propertyStringPattern = Pattern.compile("\\s*" + propertyReg + lineEndingReg);

    private Property parseProperty(String line) throws InvalidFormatException, InvalidTypeException {
        if (!propertyStringPattern.matcher(line).matches())
            throw new InvalidFormatException();

        String property = line.replaceFirst(commentReg, "").trim();

        return new Property(
                property.split("=")[0].stripTrailing(),
                property.split("=")[1].stripLeading()
        );
    }

    private Section parseNextSection(Scanner scanner) throws InvalidFormatException {
        try {
            if (!scanner.hasNextLine() || !scanner.hasNext(sectionNameStringPattern))
                throw new InvalidFormatException();

            String sectionNameString = scanner.next(sectionNameStringPattern);

            Matcher matcher = sectionNamePattern.matcher(sectionNameString);
            if (!matcher.find())
                throw new InvalidFormatException("Line " + sectionNameString + " does not have section name");

            String sectionName = sectionNameString.substring(
                    matcher.start() + 1,
                    matcher.end() - 1
            ).trim();

            Section section = new Section(sectionName);

            while (!scanner.hasNext(sectionNameStringPattern) && scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (!validLinePattern.matcher(line).matches())
                    throw new InvalidFormatException();
                if (propertyStringPattern.matcher(line).matches())
                    section.addProperty(
                            parseProperty(line)
                    );
            }

            return section;

        } catch (NoSuchElementException | InvalidTypeException ex) {
            throw new InvalidFormatException();
        }
    }

    public SectionContainer parse(File file) throws FileNotFoundException, InvalidFormatException, ParserException {
        SectionContainer sectionContainer = new SectionContainer();

        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            if (!scanner.hasNext(validLinePattern))
                throw new InvalidFormatException();

            if (scanner.hasNext(sectionNamePattern))
                sectionContainer.addSection(parseNextSection(scanner));
            else if (scanner.hasNext(propertyStringPattern))
                throw new ParserException("An error occurred while trying to parse file");
            else
                scanner.nextLine();
        }


        return sectionContainer;
    }


}
