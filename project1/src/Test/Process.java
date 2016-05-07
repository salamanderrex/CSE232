package project1.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by kezhang on 5/6/16.
 */
public class Process {

    public void modify(){
        Path path = Paths.get("1.txt");
        System.out.println(path.toAbsolutePath().toString());
        Charset charset = StandardCharsets.UTF_8;

        String content = null;
        try {
            content = new String(Files.readAllBytes(path), charset);
            content = content.replaceAll("let", ",");
            content = content.replaceAll(":=", " in ");
            Files.write(path, content.getBytes(charset));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args){
        Process p = new Process();
        p.modify();
    }


}
