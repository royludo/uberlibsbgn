import com.github.difflib.DiffUtils;
import com.github.difflib.algorithm.DiffException;
import com.github.difflib.patch.AbstractDelta;
import com.github.difflib.patch.Patch;
import com.github.difflib.text.DiffRow;
import com.github.difflib.text.DiffRowGenerator;
import com.google.common.collect.Lists;
import org.apache.xml.security.c14n.Canonicalizer;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestCanonical {

    public static void main(String args[]) {

        System.out.println("wiiz");

        org.apache.xml.security.Init.init();

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        docFactory.setNamespaceAware(true);
        docFactory.setValidating(true);

        DocumentBuilder docBuilder = null;
        try {
            docBuilder = docFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            System.exit(1);
        }

        String xml1 = null;
        String xml2 = null;
        try {

            xml1 = new String ( Files.readAllBytes( Paths.get("C:\\Programmation\\Java\\Projects\\uberlibsbgn\\SBGN-PD_all.sbgn") ) );
            xml2 = new String ( Files.readAllBytes( Paths.get("C:\\Programmation\\Java\\Projects\\uberlibsbgn\\SBGN-PD_all_modif.sbgn") ) );
        } catch (IOException e) {
            e.printStackTrace();
        }

        String canon1 = null, canon2 = null;
        try {

            canon1 = new String( canonicalize(docBuilder, xml1));
            canon2 = new String( canonicalize(docBuilder, xml2));
            /*System.out.println("Output:");
            System.out.println(canon1);*/
        } catch (Exception e) {
            e.printStackTrace();
        }

        //build simple lists of the lines of the two testfiles
        List<String> original = Lists.newArrayList(canon1.split("\n"));
        List<String> revised = Lists.newArrayList(canon2.split("\n"));

        //compute the patch: this is the diffutils part
        Patch<String> patch = null;
        try {
            patch = DiffUtils.diff(original, revised);
        } catch (DiffException e) {
            e.printStackTrace();
        }

        //simple output the computed patch to console
        for (AbstractDelta<String> delta : patch.getDeltas()) {
            System.out.println(delta);
        }

        DiffRowGenerator generator = DiffRowGenerator.create()
                .showInlineDiffs(true)
                .mergeOriginalRevised(true)
                .inlineDiffByWord(true)
                .oldTag(f -> "~")      //introduce markdown style for strikethrough
                .newTag(f -> "**")     //introduce markdown style for bold
                .build();

        //compute the differences for two test texts.
        try {
            List<DiffRow> rows = generator.generateDiffRows(original, revised);
            for(DiffRow d: rows) {
                if(d.getTag() != DiffRow.Tag.EQUAL) {
                    System.out.println(d.getOldLine() + "||||" + d.getNewLine());
                }
            }
        } catch (DiffException e) {
            e.printStackTrace();
        }


    }

    public static byte[] canonicalize(DocumentBuilder docBuilder, String input) throws Exception {
        byte inputBytes[] = input.getBytes();
        Document doc = docBuilder.parse(new ByteArrayInputStream(inputBytes));

        Canonicalizer c14n = Canonicalizer.getInstance(Canonicalizer.ALGO_ID_C14N11_OMIT_COMMENTS);
                //"http://www.w3.org/TR/2001/REC-xml-c14n-20010315#WithComments");

        return c14n.canonicalizeSubtree(doc);
    }
}
