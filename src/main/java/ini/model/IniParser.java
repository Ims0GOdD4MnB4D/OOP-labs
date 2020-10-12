package ini.model;

import Exceptions.InvalidFormatException;
import Exceptions.InvalidTypeException;
import Exceptions.ParserException;
import ini.Collections.SectionContainer;
import ini.property.Property;
import ini.section.Section;

import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;
import java.io.File;

public class IniParser {

    private final String sectionReg = "\\[\\w+]";
    private final String propertyReg = "\\w+\\s*=\\s*[\\w./]+";

    private final String commentReg = ";.*";
    private final String lineEndingRegEx = "\\s*(" + commentReg + ")?";

    private final Pattern validLinePattern =
            Pattern.compile("\\s*(" + sectionReg + "|" + propertyReg + ")?" + lineEndingRegEx);

    private final Pattern sectionNameStringPattern = Pattern.compile("\\s*" + sectionReg + lineEndingRegEx);
    private final Pattern sectionNamePattern = Pattern.compile(sectionReg);

    private final Pattern propertyStringPattern = Pattern.compile("\\s*" + propertyReg + lineEndingRegEx);

    private Property parseProperty(String line) throws InvalidFormatException {
        if (!propertyStringPattern.matcher(line).matches())
            throw new InvalidFormatException();

        String property = line.replaceFirst(commentReg, "").trim();

        return Property.createProperty(
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

        } catch (NoSuchElementException ex) {
            throw new InvalidFormatException();
        }
    }

    public SectionContainer parse(File file) throws FileNotFoundException, InvalidFormatException {
        SectionContainer sectionContainer = new SectionContainer();

        try (Scanner scanner = new Scanner(file)) {
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

        } catch (FileNotFoundException ex) {
            throw ex;
        }
        catch (InvalidFormatException ex) {
            throw new InvalidFormatException(ex);
        } catch (ParserException e) {
            e.printStackTrace();
        }

        return sectionContainer;
    }

    public boolean checkFileFormat(File file) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                if (!validLinePattern.matcher(scanner.nextLine()).find())
                    return false;
            }
        }
        return true;
    }

}
