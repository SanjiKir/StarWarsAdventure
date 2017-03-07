/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Instance třídy PrikazFly představují ...
 *
 * @author    jméno autora
 * @version   0.00.000
 */
class PrikazLeti implements IPrikaz {
    private static final String NAZEV = "fly";
    private HerniPlan plan;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "letat" 
    */    
    public PrikazLeti(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     *  Provádí příkaz "fly". Zkouší se vyletet do zadané planety. Pokud planeta
     *  existuje, vstoupí se do nové planety. Pokud zadana sousední planeta
     *  (východ) není, vypíše se chybové hlášení.
     *  Pokud planeta existuje, pri zmeny planety take se meni aktualni prostor, bereme hodnoty z planety.
     *@param parametry - jako  parametr obsahuje jméno planety (východu),
     *                         do kterého se má letet.
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            return "Which planet u want to fly? You should write the name.";
        }
        if (!plan.getPlanet().getStartLocation().equals(plan.getAktualniProstor())){
        return "You can't fly there from this location";}
        else {
        String smer = parametry[0];
        

        // zkoušíme přejít do sousedního prostoru
        Planeta soused = plan.getPlanet().getTheClosestPlanet(smer);
        
      

        if (soused == null) {
            return "You can't fly there from here";
        }
        else {
            plan.setPlanet(soused);
            plan.setAktualniProstor(soused.getStartLocation());
            return soused.longDescription() + "\n" + soused.getStartLocation().dlouhyPopis();
        }
    }
    }
    
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }

}
