import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import datastructures.MyArrayList;
import datastructures.MyStack;

public class XMLParser {

    private MyArrayList<String> errorMessages = new MyArrayList<>();

    public void parseXML(String filePath) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8);
            checkForErrors(content);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        if (errorMessages.isEmpty()) {
            System.out.println("No syntax errors found in XML.");
        } else {
            System.out.println("Syntax errors found:");
            for (int i = 0; i < errorMessages.size(); i++) {
                System.out.println(errorMessages.get(i));
            }
        }
    }

    private void checkForErrors(String content) {
        MyStack<String> tagStack = new MyStack<>();
        boolean isTag = false;
        boolean isClosingTag = false;
        StringBuilder tagNameBuilder = new StringBuilder();

        for (char ch : content.toCharArray()) {
            if (ch == '<') {
                isTag = true;
                tagNameBuilder.setLength(0); // Reset tag name builder
                isClosingTag = false;
            } else if (isTag && ch == '/') {
                if (tagNameBuilder.length() == 0) { // It's a closing tag
                    isClosingTag = true;
                } else if (tagNameBuilder.charAt(tagNameBuilder.length() - 1) != ' ') {
                    tagNameBuilder.append(ch);
                }
            } else if (isTag && ch == '>') {
                isTag = false;
                String tagName = tagNameBuilder.toString().trim();
                if (tagName.startsWith("?xml")) {
                    // Skip XML declaration
                    continue;
                }
                if (tagName.endsWith("/")) {
                    // It's a self-closing tag, do nothing
                } else if (!isClosingTag) {
                    // Opening tag
                    tagStack.push(tagName.split("\\s+")[0]); // Push only the tag name, ignoring attributes
                } else {
                    // Closing tag
                    tagName = tagName.split("\\s+")[0];
                    if (tagStack.isEmpty() || !tagStack.pop().equals(tagName)) {
                        errorMessages.add("Mismatched closing tag or orphan closing tag: </" + tagName + ">");
                    }
                }
            } else if (isTag) {
                tagNameBuilder.append(ch); // Continue building tag name
            }
        }

        if (!tagStack.isEmpty()) {
            errorMessages.add("Unmatched opening tag(s) found: " + tagStack.peek());
        }
    }


    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java XMLParser <XMLFilePath>");
            return;
        }
        new XMLParser().parseXML(args[0]);
    }
}
