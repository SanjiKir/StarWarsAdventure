/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

import java.util.*;
import java.util.stream.Collectors;

/*******************************************************************************
 * Instance třídy Planet představují ...
 *
 * @author    jméno autora
 * @version   0.00.000
 */
public class Planeta
{
    private String jmeno;
    private String popis;
    private Set<Planeta> exits; 
    private Prostor startLocation;
    /**
     * Vytvoření planety se zadaným popisem, např. "Korribian", "Tatooine"
     *
     * @param nazev nazev planety, jednoznačný identifikátor, jedno slovo nebo
     * víceslovný název bez mezer.
     * @param popis Popis planety.
     */
    public Planeta(String jmeno, String popis, Prostor startLocation) {
        this.jmeno = jmeno;
        this.popis = popis;
        exits = new HashSet<>();
        this.startLocation = startLocation;
    }

    /**
     * Definuje východ z planety (vsechny planety). Vzhledem k tomu,
     * že je použit Set pro uložení východů, může být sousední planeta uvedena
     * pouze jednou (tj. nelze mít dvoje vychodu do stejné sousední planety).
     * Druhé zadání stejné planety tiše přepíše předchozí zadání (neobjeví se
     * žádné chybové hlášení). Lze zadat též cestu ze do sebe sama.
     *
     * @param vedlejsi planeta, který sousedi s aktualni planetou
     *
     */
    public void setExit(Planeta theClosest) {
        exits.add(theClosest);
    }

    /** Kazda planeta ma startovy prostor, metoda slouzi pro vraceni tohoto startoveho prosotru*/
    public Prostor getStartLocation(){
        return startLocation;
    }
    
     public String seznamVychodu() {
        String vracenyText = "exits:";
        for (Planeta sousedni : exits) {
             vracenyText += " " + sousedni.getName();
        }
        return vracenyText;
    }
    /**
     * Metoda equals pro porovnání dvou planet. Překrývá se metoda equals ze
     * třídy Object. Dve planety jsou shodné, pokud mají stejný název. Tato
     * metoda je důležitá z hlediska správného fungování seznamu východů (Set).
     *
     * Bližší popis metody equals je u třídy Object.
     *
     * @param o object, který se má porovnávat s aktuální planetou
     * @return hodnotu true, pokud má zadana planeta stejný název, jinak false
     */  
    @Override
    public boolean equals(Object o) {
        // porovnáváme zda se nejedná o dva odkazy na stejnou instanci
        if (this == o) {
            return true;
        }
        // porovnáváme jakého typu je parametr 
        if (!(o instanceof Planeta)) {
            return false;    // pokud parametr není typu Prostor, vrátíme false
        }
        // přetypujeme parametr na typ Prostor 
        Planeta second = (Planeta) o;

        //metoda equals třídy java.util.Objects porovná hodnoty obou názvů. 
        //Vrátí true pro stejné názvy a i v případě, že jsou oba názvy null,
        //jinak vrátí false.

        return (java.util.Objects.equals(this.jmeno, second.jmeno));       
    }

    /**
     * metoda hashCode vraci ciselny identifikator instance, ktery se pouziva
     * pro optimalizaci ukladani v dynamickych datovych strukturach. Pri
     * prekryti metody equals je potreba prekryt i metodu hashCode. Podrobny
     * popis pravidel pro vytvareni metody hashCode je u metody hashCode ve
     * tride Object
     */
    @Override
    public int hashCode() {
        int result = 3;
        int hashName = java.util.Objects.hashCode(this.jmeno);
        result = 37 * result + hashName;
        return result;
    }

    /**
     * Vrací název planety (byl zadán při vytváření prostoru jako parametr
     * konstruktoru)
     *
     * @return název prostoru
     */
    public String getName() {
        return jmeno;       
    }

    /**
     * Vrací "dlouhý" popis planety, který může vypadat následovně: You're on
     *  the planet Tatooine. Planets you can fly:
     * Korribian, D'Qar
     *
     * @return Dlouhý popis planety
     */
    public String longDescription() {
        return "You're on the planet " + jmeno + ". " + popis + ".\n"+exitDescription();
    }

    /**
     * Vrací textový řetězec, který popisuje sousední planety, například:
     * "Planets you can fly: Tatooine ".
     *
     * @return Popis východů - názvů sousedních planet
     */
    public String exitDescription() {
        String text = "Planets you can fly:";
        for (Planeta neighbor : exits) {
            text += " " + neighbor.getName();
        }
        return text;
    }

    /**
     * Vrací planetu, ktera sousedí s aktuální planetou a jejiz název je zadán
     * jako parametr. Pokud planeta s udaným jménem nesousedí s aktuální
     * planetou, vrací se hodnota null.
     *
     * @param neighbor Jméno sousedního planety (východu)
     * @return Planeta, ktera se nachází za příslušným východem, nebo hodnota
     * null, pokud planeta zadaného jména není sousedem.
     */
    public Planeta getTheClosestPlanet(String jmenoPlanety) {
        List<Planeta>planets = 
            exits.stream()
            .filter(  neighbor -> neighbor.getName().equals(jmenoPlanety))
        .collect(Collectors.toList());
        if(planets.isEmpty()){
            return null;
        }
        else {
            return planets.get(0);
        }
    }

    /**
     * Vrací kolekci obsahující planety, se kterými tato Planeta sousedí.
     * Takto získaný seznam sousedních planet nelze upravovat (přidávat,
     * odebírat východy) protože z hlediska správného návrhu je to plně
     * záležitostí třídy Planeta.
     *
     * @return Nemodifikovatelná kolekce planet (východů), se kterými tato
     * planeta sousedí.
     */
    public Collection<Planeta> getVychody() {
        return Collections.unmodifiableCollection(exits);
    }

   
}

