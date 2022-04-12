package org.jboss.arquillian.container.glassfish.managed_6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class StartGFTest {
    private static void testJava() throws Exception {
        final List<String> cmd = new ArrayList<>();
        cmd.add("java");
        cmd.add("--version");
        final Process process = new ProcessBuilder(cmd).redirectErrorStream(true).start();
        Runnable consoleReader = () -> {
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        new Thread(consoleReader).start();
        int result = process.waitFor();
        System.out.printf("Process exit status is: %d\n", result);
    }

    public static void main(String[] args) throws Exception {
        final List<String> cmd = new ArrayList<>();
        cmd.add("java");
        cmd.add("-jar");
        cmd.add("/Users/starksm/Dev/JBoss/Glassfish/rh-arq-container-glassfish/glassfish-managed-3.1/target/glassfish6/glassfish/modules/admin-cli.jar");
        cmd.add("start-domain");
        cmd.add("--debug");
        cmd.add("-t");
        final Process process = new ProcessBuilder(cmd).redirectErrorStream(true).start();
        Runnable consoleReader = () -> {
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        new Thread(consoleReader).start();
        int result = process.waitFor();
        System.out.printf("Process exit status is: %d\n", result);
    }

}
