// 代码生成时间: 2025-09-22 15:06:55
import com.google.common.io.Files;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files as NioFiles;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * A simple batch file renamer tool using Java and Dropwizard framework.
 * This class provides functionality to rename files in a directory based on a specified pattern.
 *
 * @author YourName
 * @version 1.0
 */
public class BatchFileRenamer {

    private static final Logger LOGGER = LoggerFactory.getLogger(BatchFileRenamer.class);

    public static void main(String[] args) {
        if (args.length < 2) {
            LOGGER.error("Usage: BatchFileRenamer <sourceDirectory> <pattern> <replacement>");
            System.exit(1);
        }

        String sourceDirectory = args[0];
        String pattern = args[1];
        String replacement = args[2];

        renameFilesInDirectory(sourceDirectory, pattern, replacement);
    }

    /*
     * Renames files in the specified directory based on the given pattern and replacement.
     *
     * @param directoryPath The path to the directory containing files to be renamed.
     * @param pattern The regex pattern to find in the file names.
     * @param replacement The replacement string for the found pattern.
     */
    public static void renameFilesInDirectory(String directoryPath, String pattern, String replacement) {
        File directory = new File(directoryPath);
        if (!directory.exists() || !directory.isDirectory()) {
            LOGGER.error("The specified path is not a valid directory: {}", directoryPath);
            return;
        }

        File[] files = directory.listFiles();
        if (files == null) {
            LOGGER.error("Failed to list files in the directory: {}", directoryPath);
            return;
        }

        Pattern regexPattern = Pattern.compile(pattern);

        for (File file : files) {
            if (file.isFile()) {
                String fileName = file.getName();
                Matcher matcher = regexPattern.matcher(fileName);
                if (matcher.find()) {
                    String newFileName = matcher.replaceFirst(replacement);
                    renameFile(file, newFileName);
                }
            }
        }
    }

    /*
     * Renames a single file.
     *
     * @param file The file to be renamed.
     * @param newFileName The new name for the file.
     */
    private static void renameFile(File file, String newFileName) {
        File newFile = new File(file.getParent(), newFileName);
        if (newFile.exists()) {
            LOGGER.error("Cannot rename file {} to {}: File already exists.", file.getName(), newFileName);
            return;
        }

        try {
            Files.move(file.toPath(), newFile.toPath());
            LOGGER.info("Renamed file {} to {}.", file.getName(), newFileName);
        } catch (IOException e) {
            LOGGER.error("Failed to rename file {} to {}: {}