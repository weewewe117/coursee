package PACKAGE_NAME;

public class homework14 {
}

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class homework14 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("What do you want to do? Options: view, edit, copy");
        String action = scanner.nextLine();

        switch (action.toLowerCase()) {
            case "view":
                viewFile(scanner);
                break;
            case "edit":
                editFile(scanner);
                break;
            case "copy":
                copyFile(scanner);
                break;
            default:
                System.out.println("Invalid option. Please choose 'view', 'edit', or 'copy'.");
        }

        scanner.close();
    }

    private static void viewFile(Scanner scanner) {
        System.out.println("Enter the name of the file to view:");
        String fileName = scanner.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    private static void editFile(Scanner scanner) {
        System.out.println("Enter lines of text (type 'exit' to finish):");
        StringBuilder content = new StringBuilder();

        while (true) {
            String line = scanner.nextLine();
            if (line.equalsIgnoreCase("exit")) {
                break;
            }
            content.append(line).append(System.lineSeparator());
        }

        System.out.println("Enter the name of the file to save:");
        String fileName = scanner.nextLine();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content.toString());
            System.out.println("File saved successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    private static void copyFile(Scanner scanner) {
        System.out.println("Enter the name of the source file:");
        String sourceFile = scanner.nextLine();

        System.out.println("Enter the name of the destination file:");
        String destinationFile = scanner.nextLine();

        try (InputStream in = new FileInputStream(sourceFile);
             OutputStream out = new FileOutputStream(destinationFile)) {

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }

            System.out.println("File copied successfully.");
        } catch (IOException e) {
            System.out.println("Error copying file: " + e.getMessage());
        }
    }
}
