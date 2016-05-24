import org.jgrapht.Graph;
import org.jgrapht.ext.DOTExporter;
import org.jgrapht.ext.EdgeNameProvider;
import org.jgrapht.ext.StringEdgeNameProvider;
import org.jgrapht.ext.VertexNameProvider;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GraphPrinter {
    public static class XQueryVertexNameProvider<T> implements VertexNameProvider<T> {
        @Override
        public String getVertexName(T vertex) {
            return vertex.toString()
                    .replace("$", "v")
                    .replaceAll("\"", "")
                    .replaceAll("\\(", "_")
                    .replaceAll("\\)", "_")
                    .replaceAll("/", "SLASH")
                    .replaceAll("\\.", "DOT")
                    .replaceAll("\\s", "_");
        }
    }

    public static void printGraph(Graph graph, String targetDirectory, String fileName) {
        VertexNameProvider<String> stringNameProvider = new XQueryVertexNameProvider<>();
        EdgeNameProvider<String> stringEdgeNameProvider = new StringEdgeNameProvider<>();

        DOTExporter exporter = new DOTExporter<>(stringNameProvider,
                stringNameProvider, stringEdgeNameProvider);
        new File(targetDirectory).mkdirs();

        String fullPath = targetDirectory + "/" + fileName;
        String fullPathDot = fullPath + ".dot";
        String fullPathPng = fullPath + ".png";
        System.out.println("full path is" + fullPathDot);
        System.out.println("full path is" + fullPathPng);
        System.out.println("after transformation, xquery becomes===============");
        System.out.println(graph.toString());
        try {
            exporter.export(new FileWriter(fullPathDot), graph);
        } catch (IOException e) {
            e.printStackTrace();
        }
/*
        Runtime r = Runtime.getRuntime();
        try {
            Process p = r.exec(String.format("/usr/local/bin/dot -Tpng %s -o %s", fullPathDot, fullPathPng));
            p.waitFor();
            r.exec(String.format("rm %s", fullPathDot));
            System.out.println("finish dot");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }*/
    }

}