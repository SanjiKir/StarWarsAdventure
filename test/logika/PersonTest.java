/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import logika.Person;
import logika.Vec;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída PersonTest slouží ke komplexnímu otestování třídy ... 
 *
 * @author    jméno autora
 * @version   0.00.000
 */
public class PersonTest
{
    //== KONSTRUKTORY A TOVÁRNÍ METODY =========================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------

    /***************************************************************************
     * Inicializace předcházející spuštění každého testu a připravující tzv.
     * přípravek (fixture), což je sada objektů, s nimiž budou testy pracovat.
     */
    @Before
    public void setUp()
    {
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každého testu.
     */
    @After
    public void tearDown()
    {
    }

    //== VLASTNÍ TESTY =========================================================
    //
    //     /********************************************************************
    //      *
    //      */
    //     @Test
    //     public void testXxx()
    //     {
    //     }
    
        /**
         * Metoda testPersonDialogue
         * Testujou se moznosti mluveni s osobami.
         */
        @Test
    public  void testPersonDialogue() {
        Person person = new Person ("1", "I'm 1", true, null, false, false);
        Person person2 = new Person ("2", "I'm 2", false, null, false, false);
        person.setAnswers("light", "dark");
        assertTrue(person.getAnswerable());
        assertFalse(person2.getAnswerable());
        ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);
        assertEquals("I'm 1", person.getDialogue());
        System.setIn(System.in);
        assertEquals("light", person.getLightAnswer());
        assertEquals("dark", person.getDarkAnswer());
        assertEquals("I'm 2", person2.getDialogue());
        Vec needed = new Vec("needed", true);
        Vec lol = new Vec("lol", true);
        assertFalse(person.getExchange());
        person.setTrade("secret", needed, "No", "Yes", "Thanks");
        assertEquals("No" + needed.getNazev(), person.exchange(lol));
        assertEquals("Yes" + "\n" + "secret", person.exchange(needed));
        assertTrue(person.getExchange());
    }
}
