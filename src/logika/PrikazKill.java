/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;


/*******************************************************************************
 * Instance třídy PrikazKill představují ...
 *
 * @author    jméno autora
 * @version   0.00.000
 */
public class PrikazKill implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "kill";
    private HerniPlan plan;
    private Hra hra;
    public Vec creatureHead;

    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public PrikazKill(HerniPlan plan, Hra hra)
    {
        this.plan = plan;
        this.hra = hra;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     *  Provádí příkaz "kill". Pokud v mistnosti persona neni vypise chybovou hlasku.. 
     *  Jinak zkusi zabit osobu, kdyz v batohu bude nutna zbran osoba zemre
     *  jinak zemre hrac
     *
     *  @param  parametry   -jako  parametr se zadává jméno osoby kterou chce hrac zabit
     *  @return             zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            return "You want me to kill myself?";
        }

        String  personName = parametry[0];


        Prostor aktualni = plan.getAktualniProstor();
        Inventory inventory = plan.getInventory();
        Person person = aktualni.findPerson(personName);


        if(person == null)
        {
            return "There is no characters with name " + personName + " here";
        }

        if (person.getAlive()){
            return "You can't kill someone who is already dead";
        }
        Vec weaponToKill = person.getWeapon();
        if (inventory.isInInventory(weaponToKill.getNazev())){
            person.setAlive(true);
            if (person.isDrop()){
                aktualni.vlozVec(person.getDrop());
            if (person.isEvil()){
                plan.increasePoints();
                return person.getName() + " has dropped " + person.getDrop().getNazev() +" \n You have killed " + person.getName() + "\n You got +1 to light side points";}
            else {
                plan.decreasePoints();
                return person.getName() + " has dropped " + person.getDrop().getNazev() + "\n You have killed " + person.getName() + "\n You got +1 to dark side points";}}
            else{
                 if (person.isEvil()){
                plan.increasePoints();
                return "You have killed " + person.getName() + "\n You got +1 to light side points";}
            else {
                plan.decreasePoints();
                return "You have killed " + person.getName() + "\n You got +1 to dark side points";}}
            }
           
        else {
            hra.setKonecHry(true);
            return "You have tried to killed " + person.getName() + " but failed. You are dead now";}

  
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
    //== Soukromé metody (instancí i třídy) ========================================

}