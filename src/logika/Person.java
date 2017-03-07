/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

/*******************************************************************************
 * Instance třídy Person představují ...
 *
 * @author    jméno autora
 * @version   0.00.000
 */
public class Person
{
    //== Datové atributy (statické i instancí)======================================
    private String name;
    private String dialogue;
    private String light;
    private String dark;
    private String secret;
    private Vec needed;
    private String dontWant;
    private String want;
    private String afterExchange;
    private Vec weaponToKill;
    private Vec drop;
    private boolean exchange;
    private boolean alive;
    private boolean jizMluvil;
    private boolean answerable;
    private boolean isDrop;
    private boolean isEvil;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public Person(String name, String dialogue, boolean answerable, Vec weaponToKill, boolean isDrop, boolean isEvil)
    {
        this.name = name;
        this.dialogue=dialogue;
        this.answerable=answerable;
        this.weaponToKill=weaponToKill;
        this.isDrop = isDrop;
        this.isEvil = isEvil;

    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    //@return isEvil - je person zla nebo dobra
    public boolean isEvil(){
        return isEvil;
    }

    /** @return isDrop - pada neco z osobyn nebo ne*/
    public boolean isDrop(){
        return isDrop;
    }

    /** @return weaponToKill - zbran pro zabiti persony*/
    public Vec getWeapon(){
        return weaponToKill;
    }

    /**@parametr light - svetla odpoved
    @parametr dark - tmava (spatna) odpoved */
    public void setAnswers(String light, String dark){
        this.light = light;
        this.dark = dark;
    }

    /** @return answerable - vraci jestli lze osobe odvpovedet nebo ne */
    public boolean getAnswerable(){
        return answerable;
    }

    /** @return name - jmeno osoby  */
    public String getName(){
        return name;
    }

    /**@return afterExchange - to co rekni osoba po vymene
    @return dialogue - zacatecni text osoby
    @parametr exchange - stav vymeny*/
    public String getDialogue(){
        if (exchange){ return afterExchange ;}
        else {return dialogue ;}
    }

    /** @return light - svetla odpoved */
    public String getLightAnswer(){
        return light;
    }

    /** @parametr dark - vraci spatnou odvpoed*/
    public String getDarkAnswer(){
        return dark;
    }

    /** @return alive - vraci true kdyz osoba je mrtva a false kdyz osoba je ziva */
    public boolean getAlive(){
        return alive;
    }

    /** @return jizMluvil - obratili se uz na osobu nebo ne*/
    public boolean getJizMluvil(){
        return jizMluvil;
    }

    /** @return exchange - stav vymeny */
    public boolean getExchange(){
        return exchange;
    }

    /** @return want - vraci rec kdyz osoba chce vec
    @return dontWant - vraci rec kdyz person necchce vec
    @parametr needed - to co chce osoba
    @parametr exchange - stav vymeny
    @return secret - sekret osoby*/
    public String exchange(Vec needed){
        if (this.needed!=null){
            if (this.needed==needed){
                this.exchange = true;
                return want + "\n" + secret;
            }
            else { return dontWant + this.needed.getNazev();}}
        else { return "I don't need this";}
    }

    /**@parametr alive - stav zivotu osoby */
    public void setAlive(boolean alive){
        this.alive = alive;
    }

    /** @parametr jizMluvil - stav obraceni na osobu */
    public void setJizMluvil(boolean jizMluvil){
        this.jizMluvil = jizMluvil;
    }

    /**@parametr exchange - stav vymeny  */
    public void setExchange(boolean exchange){
        this.exchange = exchange;
    }

    /** @parametr  drop - vec ktera pada z osoby*/
    public void setDrop(Vec drop){
        this.drop = drop;
    }

    /**@return drop - vec ktera pada z osoby*/
    public Vec getDrop(){
        return drop;
    }

    /**Nastavit vymenu 
     * @parametr secret - co rika persona pri spravne vymenene. 
     * @parametr needed - kterou vec chce. 
     * @parametr dontWant - co rekne kdyz tu vec neche. 
     * @parametr want - co rekne kdyz tu vec chce. 
     * @parametr afterExchange - co rekne po vymene*/
    public void setTrade(String secret, Vec needed, String dontWant, String want, String afterExchange){
        this.secret = secret;
        this.needed = needed;
        this.dontWant = dontWant;
        this.want = want;
        this.afterExchange = afterExchange;
    }
    //== Soukromé metody (instancí i třídy) ========================================
}
