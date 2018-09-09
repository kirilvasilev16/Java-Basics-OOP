import java.io.*;
import java.nio.file.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class directories {
    public static String dir = "C:\\";

    void change_dir(String name) {
        this.dir = name;
        Path path = Paths.get(this.dir);
        if (Files.exists(path)) {
            this.dir = this.dir.concat("\\");
            System.out.println("Current Directory: " + this.dir);
        } else {
            System.out.println("Chosen directory doesn't exist!");
        }

    }

    void create_dir(String name) {
        Path path = Paths.get(name);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {

            } finally {
                System.out.println("Directory successfully created directory in " + dir + "!");
            }
        }
    }

    void delete_dir(String name) {
        Path rootPath = Paths.get(name);
        try {
            if (Files.exists(rootPath)) {
                if (isDirEmpty(rootPath)) {
                    Files.deleteIfExists(rootPath);
                    System.out.println("Directory successfully deleted from " + dir + "!");
                } else {
                    System.out.println("Directory isn't empty and can't be deleted!");
                }
            } else {
                System.out.println("Text directory doesn't exist in " + directories.dir + "!");
            }
        } catch (IOException e) {

        }
    }

    boolean isDirEmpty(final Path directory) throws IOException {
        try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(directory)) {
            return !dirStream.iterator().hasNext();
        }
    }
}

class file {
    void create_file(String name) {
        try {
            File f = new File(name + ".txt");
            f.getParentFile().mkdirs();
            f.createNewFile();
        } catch (IOException e) {
        } finally {
            System.out.println("Text file successfully created in " + directories.dir + "!");
        }
    }

    void read_file(String name) {
        try {
            FileReader fr =
                    new FileReader(name + ".txt");
            int i;
            while ((i = fr.read()) != -1) {
                System.out.print((char) i);
            }
            System.out.println();
        } catch (IOException e) {
        } finally {
            System.out.println("Text file successfully read! ^");
        }
    }

    void delete_file(String name) {
        Path path = Paths.get(name + ".txt");
        try {
            if (Files.exists(path)) {
                Files.delete(path);
                System.out.println("Text file successfully deleted from " + directories.dir + "!");
            } else {
                System.out.println("Text file doesn't exist in " + directories.dir + "!");
            }
        } catch (NoSuchFileException x) {
            System.err.format("%s: no such" + " file or directory exists!%n", path);
        } catch (IOException x) {
            // File permission problems are caught here.
            System.err.println(x);
        }
    }

    void check_file(String name) {
        Path path = Paths.get(name + ".txt");
        if (Files.exists(path)) {
            System.out.println("File found you may start writing data bellow! V");
        } else {
            System.out.println("Text file doesn't exist in " + directories.dir + "!");
        }
    }

    void edit_file(String name, String message) {
        name = name + ".txt";
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(name);
            fos.write(message.getBytes("UTF-8"));
        } catch (IOException e) {
        }
    }
}

public class Main {

    public static void main(String[] args) {
        List<String> lines = Arrays.asList("Commands:" + "\n 0 + path -> go further directory", "\n 1 + name -> add directory",
                "\n 2 + name -> remove directory", "\n 3 + name -> create file", "\n 4 + name -> read file",
                "\n 5 + name -> delete file", "\n 6 + name -> edit file", "\n 100 -> End Program!");
        System.out.println(lines);
        directories file = new directories();
        file text = new file();
        System.out.println("Current Directory: " + file.dir);
        Scanner sc = new Scanner(System.in);
        String name, message;
        int command;
        do {
            command = sc.nextInt();
            name = sc.nextLine();


            name = file.dir.trim() + name.trim();

            switch (command) {
                case 0:
                    file.change_dir(name);
                    break;
                case 1:
                    file.create_dir(name);
                    break;
                case 2:
                    file.delete_dir(name);
                    break;
                case 3:
                    text.create_file(name);
                    break;
                case 4:
                    text.read_file(name);
                    break;
                case 5:
                    text.delete_file(name);
                    break;
                case 6:
                    text.check_file(name);
                    message = sc.nextLine();
                    text.edit_file(name, message);
                    System.out.println("Filed successfully edited and saved");
                    break;
                case 100:
                    System.out.println("Program finished!");
                    break;
                default:
                    System.out.println("Wrong command!");
            }
        }
        while (command != 100);
    }
}

