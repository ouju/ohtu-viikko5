package ohtu.intjoukkosovellus;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class IntJoukkoTest {

    IntJoukko joukko;

    @Before
    public void setUp() {
        joukko = new IntJoukko();
        joukko.lisaa(10);
        joukko.lisaa(3);
    }
    
    @Test
    public void poistaToimiiKunLukuEiOleLukujonossa(){
        joukko.poista(1);
        assertEquals(joukko.alkioita(), 2);
    }
    
    @Test
    public void kapasiteettiNeg() {
        IntJoukko joukko1 = new IntJoukko(-1);
        assertEquals("{}", joukko1.toString());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIndexOutOfBoundsExceptionNegKapasiteetilla() {
        IntJoukko joukko1 = new IntJoukko(-1, 1);
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void testIndexOutOfBoundsExceptionNegKasvatuskoolla() {
        IntJoukko joukko1 = new IntJoukko(1, -1);
    }

    @Test
    public void lukujaLisattyMaara() {
        joukko.lisaa(4);
        assertEquals(3, joukko.alkioita());
    }

    @Test
    public void samaLukuMeneeJoukkoonVaanKerran() {
        joukko.lisaa(10);
        joukko.lisaa(3);
        assertEquals(2, joukko.alkioita());
    }

    @Test
    public void vainLisatytLuvutLoytyvat() {
        assertTrue(joukko.loytyyLukujonosta(10));
        assertFalse(joukko.loytyyLukujonosta(5));
        assertTrue(joukko.loytyyLukujonosta(3));
    }

    @Test
    public void poistettuEiOleEnaaJoukossa() {
        joukko.poista(3);
        assertFalse(joukko.loytyyLukujonosta(3));
        assertEquals(1, joukko.alkioita());
    }

    @Test
    public void palautetaanOikeaTaulukko() {
        int[] odotettu = {3, 55, 99};

        joukko.lisaa(55);
        joukko.poista(10);
        joukko.lisaa(99);

        int[] vastaus = joukko.toIntArray();
        Arrays.sort(vastaus);
        assertArrayEquals(odotettu, vastaus);
    }

    @Test
    public void toimiiKasvatuksenJalkeen() {
        int[] lisattavat = {1, 2, 4, 5, 6, 7, 8, 9, 11, 12, 13, 14};
        for (int luku : lisattavat) {
            joukko.lisaa(luku);
        }
        assertEquals(14, joukko.alkioita());
        assertTrue(joukko.loytyyLukujonosta(11));
        joukko.poista(11);
        assertFalse(joukko.loytyyLukujonosta(11));
        assertEquals(13, joukko.alkioita());
    }

    @Test
    public void toStringToimii() {
        assertEquals("{10, 3}", joukko.toString());
    }

    @Test
    public void toStringToimiiYhdenKokoiselleJoukolla() {
        joukko = new IntJoukko();
        joukko.lisaa(1);
        assertEquals("{1}", joukko.toString());
    }

    @Test
    public void toStringToimiiTyhjallaJoukolla() {
        joukko = new IntJoukko();
        assertEquals("{}", joukko.toString());
    }
}
