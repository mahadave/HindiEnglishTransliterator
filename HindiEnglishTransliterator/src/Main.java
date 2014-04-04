
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.StringTokenizer;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author megadave
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    static String filename;
    static String outFilename;
    static HashMap<Character, Varn> h2e;
    static boolean debug=false;

    public static void main(String[] args) {
        filename = args[0];
        outFilename = filename+".out.txt";
        h2e = new HashMap<Character, Varn>();

        try {
            initMap(h2e);
            readFileAndTransliterate(filename,outFilename);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void readFileAndTransliterate(String filename,String outFile) throws UnsupportedEncodingException, FileNotFoundException, IOException {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),"UTF-8"));
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filename)), "UTF-8"));
        String line = in.readLine();
        while (line != null) {
            StringTokenizer st = new StringTokenizer(line, " ");
            while (st.hasMoreTokens()) {
                String word = st.nextToken();
                word = word.trim();
                String outWord=sandhivichhed(word);
                if(debug) System.out.print(word + "["+outWord+"]");
                out.write(outWord+" ");
            }
            line = in.readLine();
            if(debug) System.out.println();
            out.write("\n");
        }
        out.flush();
        out.close();
        in.close();
    }

    private static String sandhivichhed(String word) {
        char shabd[] = word.toCharArray();
        StringBuffer outWord=new StringBuffer();
        int ptr = 0;
        if(debug)
        for(int i=0;i<shabd.length;i++)
            System.out.print(shabd[i]+"+");
       
           
        while (ptr < shabd.length - 1) {
            if(debug) System.out.println(outWord);
            char cur = shabd[ptr];
            char next = shabd[ptr + 1];
            if(debug) System.out.print("pair : (" + cur + "," + next + ")");
            if(!h2e.containsKey(cur) || !(h2e.containsKey(next)))
            {
                if(debug) System.out.println("SKIP");
                ptr++; 
                continue;
            }
            else
            {
                Varn curVarn = h2e.get(cur);
                Varn nextVarn = h2e.get(next);
                if(curVarn.isVyanjan() && nextVarn.isVyanjan())
                {
                    if(debug) System.out.println("Both");
                    outWord.append(curVarn.getTransliteration()+"a"); // add "a" which is implicit
                    ptr++;
                }
                else
                {
                    outWord.append(curVarn.getTransliteration()+nextVarn.getTransliteration());
                    ptr+=2;   
                }
            }            
        }
        
        if(ptr==(shabd.length-1) && h2e.containsKey(shabd[ptr]))
            outWord.append(h2e.get(shabd[ptr]).getTransliteration());
        
        return (outWord.toString());
    }
    
    
    private static void initMap(HashMap<Character, Varn> h2e) {

        h2e.put('ॐ', new Varn("om", true));
        h2e.put('क', new Varn("k", true));
        h2e.put('ख', new Varn("kh", true));
        h2e.put('ग', new Varn("g", true));
        h2e.put('घ', new Varn("gh", true));

        h2e.put('च', new Varn("ch", true));
        h2e.put('छ', new Varn("chh", true));
        h2e.put('ज', new Varn("j", true));
        h2e.put('झ', new Varn("jh", true));
        h2e.put('ञ', new Varn("ngy", true));

        h2e.put('ट', new Varn("T", true));
        h2e.put('ठ', new Varn("Th", true));
        h2e.put('ड', new Varn("D", true));
        h2e.put('ड', new Varn("D", true));
        h2e.put('ड़', new Varn("Dd", true));
        h2e.put('ढ', new Varn("Dh", true));
        h2e.put('ढ़', new Varn("DdH", true));
        h2e.put('ण', new Varn("N", true));

        h2e.put('त', new Varn("t", true));
        h2e.put('थ', new Varn("th", true));
        h2e.put('द', new Varn("d", true));
        h2e.put('ध', new Varn("dh", true));
        h2e.put('न', new Varn("n", true));

        h2e.put('प', new Varn("p", true));
        h2e.put('फ', new Varn("ph", true));
        h2e.put('ब', new Varn("b", true));
        h2e.put('भ', new Varn("bh", true));
        h2e.put('म', new Varn("m", true));

        h2e.put('क़', new Varn("q", true));
        h2e.put('ख़', new Varn("kH", true));
        h2e.put('ग़', new Varn("gH", true));
        h2e.put('ज़', new Varn("z", true));
        h2e.put('फ़', new Varn("f", true));
        

        h2e.put('य', new Varn("y", true));
        h2e.put('र', new Varn("r", true));
        h2e.put('ल', new Varn("l", true));
        h2e.put('ळ', new Varn("l", true));
        h2e.put('व', new Varn("v", true));

        h2e.put('श', new Varn("sh", true));
        h2e.put('ष', new Varn("Sh", true));
        h2e.put('स', new Varn("s", true));
        h2e.put('ह', new Varn("h", true));


        //--Swar

        h2e.put('अ', new Varn("a",false));
        h2e.put('आ', new Varn("aa",false));
        h2e.put('इ', new Varn("i",false));
        h2e.put('ई', new Varn("ee",false));
        h2e.put('उ', new Varn("u",false));
        h2e.put('ऊ', new Varn("oo",false));
        h2e.put('ऋ', new Varn("r",false));
        h2e.put('ए', new Varn("ye",false));
        h2e.put('ऐ', new Varn("ai",false));
        h2e.put('ओ', new Varn("o",false));
        h2e.put('औ', new Varn("au",false));
        
        //--gandna
        
        h2e.put('०', new Varn("0",false));
        h2e.put('१', new Varn("1",false));
        h2e.put('२', new Varn("2",false));
        h2e.put('३', new Varn("3",false));
        h2e.put('४', new Varn("4",false));
        h2e.put('५', new Varn("5",false));
        h2e.put('६', new Varn("6",false));
        h2e.put('७', new Varn("7",false));
        h2e.put('८', new Varn("8",false));
        h2e.put('९', new Varn("9",false));
        
        //--- Swar-matraa

        h2e.put('ा', new Varn("aa",false));
        h2e.put('ि', new Varn("i",false));
        h2e.put('ी', new Varn("ee",false));
        h2e.put('ु', new Varn("u",false));
        h2e.put('ू', new Varn("oo",false));
        h2e.put('ृ', new Varn("r",false));
        h2e.put('े', new Varn("e",false));
        h2e.put('ै', new Varn("ai",false));
        h2e.put('ो', new Varn("o",false));
        h2e.put('ौ', new Varn("au",false));
        h2e.put('ः', new Varn("ah",false));
        h2e.put('ँ', new Varn("nn",false));

        h2e.put('ं', new Varn("n",false));
        h2e.put('्', new Varn("",false));

        //-- viram chihna

        h2e.put('।', new Varn(".",false));
        h2e.put('॥', new Varn(".",false));

    }

}

class Varn {

    String transliteration;
    boolean vyanjanFlag;

    public Varn(String trans, boolean flag) {
        transliteration = trans;
        vyanjanFlag = flag;
    }
    
    public boolean isVyanjan()
    {
        return vyanjanFlag;
    }
    
    
    public String getTransliteration()
    {
        return transliteration;
    }
}

