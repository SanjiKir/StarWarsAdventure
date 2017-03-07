package logika;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import logika.Hra;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída HraTest slouží ke komplexnímu otestování
 * třídy Hra
 *
 * @author    Jarmila Pavlíčková
 * @version  pro školní rok 2015/2016
 */
public class HraTest {
    private Hra hra1;

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
        hra1 = new Hra();
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }

    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================

    /***************************************************************************
     * Testuje průběh hry, po zavolání každěho příkazu testuje, zda hra končí
     * a v jaké aktuální místnosti se hráč nachází.
     * Při dalším rozšiřování hry doporučujeme testovat i jaké věci nebo osoby
     * jsou v místnosti a jaké věci jsou v batohu hráče.
     * 
     */
    @Test
    public void testPrubehHry() {
        assertEquals("Compartment", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals("D'Qar", hra1.getHerniPlan().getPlanet().getName());
        hra1.zpracujPrikaz("take sword");
        hra1.zpracujPrikaz("take bed");
        assertFalse(hra1.getHerniPlan().getInventory().isInInventory("bed"));
        assertTrue(hra1.getHerniPlan().getInventory().isInInventory("sword"));
        ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);
        assertFalse(hra1.getHerniPlan().getAktualniProstor().findPerson("Trader").getJizMluvil());
        hra1.zpracujPrikaz("speak Trader");
        System.setIn(System.in);
        assertFalse(hra1.getHerniPlan().getAktualniProstor().findPerson("Trader").getAlive());
        assertTrue(hra1.getHerniPlan().getAktualniProstor().findPerson("Trader").getJizMluvil());
        hra1.zpracujPrikaz("go Outpost");
        assertEquals(false, hra1.konecHry());
        assertEquals("Outpost", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals("D'Qar", hra1.getHerniPlan().getPlanet().getName());
        hra1.zpracujPrikaz("drop  sword");
        assertFalse(hra1.getHerniPlan().getInventory().isInInventory("sword"));
        hra1.zpracujPrikaz("take sword");
        assertTrue(hra1.getHerniPlan().getInventory().isInInventory("sword"));
        hra1.zpracujPrikaz("give Pilot sword");
        assertTrue(hra1.getHerniPlan().getInventory().isInInventory("sword"));
        assertEquals(false, hra1.getHerniPlan().getAktualniProstor().findPerson("Pilot").getAlive());
        assertEquals(false, hra1.getHerniPlan().getAktualniProstor().findPerson("Citizen").getAlive());
        hra1.zpracujPrikaz("fly Corellia");
        assertEquals(false, hra1.konecHry());
        assertEquals("Port", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals("Corellia", hra1.getHerniPlan().getPlanet().getName());
        hra1.zpracujPrikaz("take laser");
        assertTrue( hra1.getHerniPlan().getInventory().isInInventory("laser"));
        in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);
        hra1.zpracujPrikaz("speak Soldier");
        System.setIn(System.in);
        assertEquals(false, hra1.getHerniPlan().getAktualniProstor().findPerson("Soldier").getAlive()); 
        assertEquals(true, hra1.getHerniPlan().getAktualniProstor().vratSousedniProstor("Ruins").ifClosed());
        hra1.zpracujPrikaz("open Ruins 582");
        assertEquals(false, hra1.getHerniPlan().getAktualniProstor().vratSousedniProstor("Ruins").ifClosed());
        hra1.zpracujPrikaz("go Ruins");
        assertEquals(false, hra1.konecHry());
        assertEquals("Ruins", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals("Corellia", hra1.getHerniPlan().getPlanet().getName());
        hra1.zpracujPrikaz("kill Creature");
        assertEquals(true, hra1.getHerniPlan().getAktualniProstor().findPerson("Creature").getAlive());
        hra1.zpracujPrikaz("take head");
        assertEquals(true, hra1.getHerniPlan().getInventory().isInInventory("head"));
        hra1.zpracujPrikaz("go Port");
        assertEquals(false, hra1.konecHry());
        assertEquals("Port", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals("Corellia", hra1.getHerniPlan().getPlanet().getName());
        hra1.zpracujPrikaz("give Soldier head");
        assertFalse(hra1.getHerniPlan().getInventory().isInInventory("head"));
        assertEquals(false, hra1.getHerniPlan().getAktualniProstor().findPerson("Soldier").getAlive());
        hra1.zpracujPrikaz("fly Tatooine");
        assertEquals(false, hra1.konecHry());
        assertEquals("Rock", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals("Tatooine", hra1.getHerniPlan().getPlanet().getName());
        assertEquals(false, hra1.getHerniPlan().getAktualniProstor().findPerson("Stranger").getAlive());
        hra1.zpracujPrikaz("kill Stranger");
        assertEquals(true, hra1.getHerniPlan().getAktualniProstor().findPerson("Stranger").getAlive());
        hra1.zpracujPrikaz("take paper");
        assertEquals(true, hra1.getHerniPlan().getInventory().isInInventory("paper"));
        hra1.zpracujPrikaz("fly Korribian");
        assertEquals(false, hra1.konecHry());
        assertEquals("Tombs_enter", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals("Korribian", hra1.getHerniPlan().getPlanet().getName());
        assertEquals(true, hra1.getHerniPlan().getAktualniProstor().vratSousedniProstor("Tomb").ifClosed());
        hra1.zpracujPrikaz("open Tomb 546897");
        assertEquals(false, hra1.getHerniPlan().getAktualniProstor().vratSousedniProstor("Tomb").ifClosed());
        hra1.zpracujPrikaz("go Tomb");
        assertEquals(false, hra1.konecHry());
        assertEquals("Tomb", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals("Korribian", hra1.getHerniPlan().getPlanet().getName());
        assertEquals(false, hra1.getHerniPlan().getAktualniProstor().findPerson("Ancient_droid").getAlive());
        hra1.zpracujPrikaz("speak Ancient_droid");;
        hra1.zpracujPrikaz("answer Ancient_droid revan");
        assertEquals(true, hra1.konecHry());
    }
}
