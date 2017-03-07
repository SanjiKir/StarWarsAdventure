package logika;

import logika.Person;
import logika.Prostor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída ProstorTest slouží ke komplexnímu otestování
 * třídy Prostor
 *
 * @author    Jarmila Pavlíčková
 * @version   pro skolní rok 2015/2016
 */
public class ProstorTest
{
    //== Datové atributy (statické i instancí)======================================

    //== Konstruktory a tovární metody =============================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------

    //== Příprava a úklid přípravku ================================================

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @Before
    public void setUp() {
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }

    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================

    /**
     * Testuje, zda jsou správně nastaveny průchody mezi prostory hry. Prostory
     * nemusí odpovídat vlastní hře, 
     */
    @Test
    public  void testLzeProjit() {      
        Prostor prostor1 = new Prostor("hala", "vstupní hala budovy VŠE na Jižním městě", 200, 50);
        Prostor prostor2 = new Prostor("bufet", "bufet, kam si můžete zajít na svačinku", 200, 50);
        prostor1.setVychod(prostor2);
        prostor2.setVychod(prostor1);
        assertEquals(prostor2, prostor1.vratSousedniProstor("bufet"));
        assertEquals(null, prostor2.vratSousedniProstor("pokoj"));

    }

    /**Metoda testuje moznost pridani osob do mistnost a 
     * jestli se vubec pridavaji. 
     */
    @Test
    public  void testPerson() {  
        Prostor prostor1 = new Prostor("hala", "vstupní hala budovy VŠE na Jižním městě", 200, 50);
        Person person = new Person("Pilot", "Hey",false, null, false, false);
        Person person1 = new Person("Pilot1", "Hey",false, null, false, false);
        prostor1.addPerson(person);
        prostor1.addPerson(person1);
        assertEquals(person, prostor1.findPerson("Pilot"));
        assertEquals(person1, prostor1.findPerson("Pilot1"));
    }
    /**Metoda testuje moznost uzavreni mistnosti
     * 
     */
    @Test
    public void testClosed()
    {
        Prostor prostor1 = new Prostor("hala", "vstupní hala budovy VŠE na Jižním městě", 200, 50);
        prostor1.close(true);
        assertEquals(true, prostor1.ifClosed());
        prostor1.setKey("1");
        assertEquals("1", prostor1.getKey());
    }

    /**
     * Metoda testVeci
     * Testuje se vkladani a odebirani veci do mistnosti
     */
    @Test
    public void testVeci()
    {
        logika.Vec vec1 = new logika.Vec("a", true);
        logika.Vec vec2 = new logika.Vec("b", false);
        logika.Prostor prostor1 = new logika.Prostor(null, null, 200, 50);
        prostor1.vlozVec(vec1);
        prostor1.vlozVec(vec2);
        assertNull(prostor1.odeberVec("c"));
        assertSame(vec1, prostor1.odeberVec("a"));
        assertSame(vec2, prostor1.odeberVec("b"));
    }
}

