package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] lukujono;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    private void exceptionNegatiivisille(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasitteetti ei saa olla negatiivinen");//heitin vaan jotain :D
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("Kasvatuskoko ei saa olla negatiivinen");//heitin vaan jotain :D
        }
    }

    private void lukujono(int kapasiteetti) {
        lukujono = new int[kapasiteetti];
        for (int i = 0; i < lukujono.length; i++) {
            lukujono[i] = 0;
        }
        alkioidenLkm = 0;
    }

    public IntJoukko() {
        lukujono(KAPASITEETTI);
        this.kasvatuskoko = OLETUSKASVATUS;
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            return;
        }
        lukujono(kapasiteetti);
        this.kasvatuskoko = OLETUSKASVATUS;

    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        exceptionNegatiivisille(kapasiteetti, kasvatuskoko);
        lukujono(kapasiteetti);
        this.kasvatuskoko = kasvatuskoko;
    }

    public boolean lisaa(int luku) {
        if (!loytyyLukujonosta(luku)) {
            lukujono[alkioidenLkm] = luku;
            alkioidenLkm++;
            kasvataTaulukkoa();
            return true;
        }
        return false;
    }

    public void kasvataTaulukkoa() {
        if (alkioidenLkm % lukujono.length == 0) {
            int[] vanhaTaulukko = lukujono;
            lukujono = new int[alkioidenLkm + kasvatuskoko];
            kopioiTaulukko(vanhaTaulukko, lukujono);
        }
    }

    public boolean loytyyLukujonosta(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukujono[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        int sijainti = luvunSijainti(luku);
        if (sijainti != -1) {
            siirraVasemmalle(sijainti);
            alkioidenLkm--;
            return true;
        }
        return false;
    }

    private void siirraVasemmalle(int sijainti) {
        int apu;
        for (int j = sijainti; j < alkioidenLkm - 1; j++) {
            apu = lukujono[j];
            lukujono[j] = lukujono[j + 1];
            lukujono[j + 1] = apu;
        }
    }

    private int luvunSijainti(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukujono[i]) {
                return i;
            }
        }
        return -1;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }

    }

    public int alkioita() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";
        } else {
            String tuotos = "{";
            tuotos += listaaLuvut();
            tuotos += "}";
            return tuotos;
        }
    }

    private String listaaLuvut() {
        String tuotos = "";
        for (int i = 0; i < alkioidenLkm - 1; i++) {
            tuotos += lukujono[i];
            tuotos += ", ";
        }
        tuotos += lukujono[alkioidenLkm - 1];
        return tuotos;
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = lukujono[i];
        }
        return taulu;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        //IntJoukko yhdistetty = new IntJoukko();
        //int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        IntJoukko yhdistetty = a;
        for (int i = 0; i < bTaulu.length; i++) {
            yhdistetty.lisaa(bTaulu[i]);
        }
        return yhdistetty;
    }
    
    

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko leikattu = new IntJoukko();
        for (int i = 0; i < a.alkioita(); i++) {
            for (int j = 0; j < b.alkioita(); j++) {
                if (a.haeIndeksilla(i) == b.haeIndeksilla(j)) {
                    leikattu.lisaa(b.haeIndeksilla(j));
                }
            }
        }
        return leikattu;

    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko tulos = a.kopioiJoukko();
        for (int i = 0; i < b.alkioita(); i++) {
            tulos.poista(b.haeIndeksilla(i));
        }

        return tulos;
    }
    
    private int haeIndeksilla(int index){
        return lukujono[index];
    }
    
    private IntJoukko kopioiJoukko(){
        IntJoukko kopio = new IntJoukko();
        for (int luku : toIntArray()){
            kopio.lisaa(luku);
        }
        return kopio;
    }
}
