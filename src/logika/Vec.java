/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Instance třídy Vec představují ...
 *
 * @author    jméno autora
 * @version   0.00.000
 */
public class Vec
{
    //== Datové atributy (statické i instancí)======================================
    private String nazev;
    private boolean prenositelnost;
    private String cestaKObrazku;
    //== Konstruktory a tovární metody =============================================

    public String getCestaKObrazku() {
        return cestaKObrazku;
    }

    public void setCestaKObrazku(String cestaKObrazku) {
        this.cestaKObrazku = cestaKObrazku;
    }

    /***************************************************************************
     *  Konstruktor ....
     */
    public Vec(String nazev, boolean prenositelnost)
    {
        this.nazev = nazev;
        this.prenositelnost = prenositelnost;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    public String getNazev()
    {
        return nazev;
    }
    
    public boolean jePrenositelna()
    {
        return prenositelnost;
    }
    
    public void setPrenositelnost(boolean prenos){
    this.prenositelnost = prenos;
    }

    //== Soukromé metody (instancí i třídy) ========================================

}
