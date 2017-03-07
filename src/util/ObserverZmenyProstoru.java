/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author wooow
 */
public interface ObserverZmenyProstoru {
    /**
 * Metoda, ve které proběhne aktualizace pozorovatele,
 * je volána prostřednictvím metody upozorniPozorovatele z rozhraní SubjektZmenyProstoru
 * 
 * @param aktualniProstor
 */
public void aktualizuj();

}
