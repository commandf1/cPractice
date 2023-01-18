package space.commandf1.practice.run;

import java.io.InputStream;
import java.util.Scanner;

public class cPracticeRunner {

    public static void main(String[] args) {
        InputStream in = cPracticeRunner.class.getClassLoader().getResourceAsStream("plugin.yml");
        if (in == null) {
            System.out.println("Can not find the file \"plugin.yml\"");
            return;
        }
        Scanner scanner = new Scanner(in);

        String version = "Unknown";
        while (scanner.hasNext()) {
            String text = scanner.nextLine();
            if (text.toLowerCase().startsWith("version")) {
                version = text.replace("version: ", "");
                break;
            }
        }
        System.out.println("cPractice version -> " + version + (version.toUpperCase().endsWith("SNAPSHOT") ? " [DEV VERSION]" : ""));
        System.out.println("Powered By commandf1");
        System.out.println("Link: https://commandf1.space/");
    }
}
