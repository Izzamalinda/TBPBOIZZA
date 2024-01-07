import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashSet;

public class Penyajian implements AhviProgram {
    public void date() {
         Date date = new Date();
            SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
            System.out.print("Tanggal         : " +d.format(date));
    }

    public void set() {

        LinkedHashSet<String> m = new LinkedHashSet <String>();
        m.add("Geometris");
        m.add("Floral");
        m.add("Kartun");
        m.add("Tribal");
        m.add("Glitter");
        m.add("Marmer");
        m.add("Vintage");
        m.add("Gradientcolour");

        System.out.println("\n\nMotif:             " + m);

        m.remove("Tribal");
        m.remove("marmer");

        System.out.println("Motif yang tersedia:   " + m);
    }
}

