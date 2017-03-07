/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika;

/**
 *
 * @author wooow
 */
public class PrikazAnswer implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "answer";
    private HerniPlan plan;
    private Hra hra;
    public Vec creatureHead;

    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public PrikazAnswer(HerniPlan plan, Hra hra)
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
            return "You have to choose person you're answering";
        }

        String  personName = parametry[0];
        
        if (parametry.length == 1) {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            return "You have to choose answer";
        }
        
        
        Prostor aktualni = plan.getAktualniProstor();
        Inventory inventory = plan.getInventory();
        Person person = aktualni.findPerson(personName);
        String answer = parametry[1];

        if(person == null)
        {
            return "There is no characters with name " + personName + " here";
        }

        if (person.getAlive()){
            return "You can't answer to dead people";
        }
        if (person.getAnswerable()){ 
            if (person.getName().equals("Ancient_droid")){
              if (answer.equals("Revan") || answer.equals("revan")){
                            
                            if (plan.getPowerPoints()>=0){
                                plan.getInventory().putItem(person.getDrop());
                                return "This is right answer! You can take the lightsaber" + "\n You have ended a game with " + plan.getPowerPoints() + " light points. You're a jedi now!";}
                            else {plan.getInventory().putItem(person.getDrop()); 
                                return "This is right answer! You can take the lightsaber" + "\n You have ended a game with " + plan.getPowerPoints() + " dark points. You're a sith now!";}

                        }
                        else {
                            hra.setKonecHry(true);
                            return "Sorry, but the answer is wrong. You will not survive this.";}
            }    
        if (answer.equals("1")){
                        return ("You have left " + person.getName() + " alone");
                    }
                    
                    else if (answer.equals("2")){
                        Vec weaponToKill = person.getWeapon();
                        if (inventory.isInInventory(weaponToKill.getNazev())){
                            person.setAlive(true);
                            if (person.isDrop()){
                                aktualni.vlozVec(person.getDrop()); 
                            plan.decreasePoints();
                            return (person.getName() + " has dropped " + person.getDrop().getNazev() +"\n You have got +1 score to your dark points! The person " +person.getName() + " is dead");
                            }
                            else {
                                plan.decreasePoints();
                            return ("You have got +1 score to your dark points! The person " +person.getName() + " is dead");
                            }
                        }
                        else {
                            hra.setKonecHry(true);
                            return "You have tried to killed " + person.getName() + " but failed. You are dead now";}
                    }
                    else {
                        return "There is no sucn an option to answer";}
                }
       else  return "This person didnt ask for your answer";
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
