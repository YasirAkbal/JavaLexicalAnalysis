/**
*
* @author Ahmet Yasir Akbal - ahmet.akbal@ogr.sakarya.edu.tr
* @since 11.03.2018
* <p>
* Lexical analys yapan program
* </p>
*/

package b161210074_odev1;


public class Fonksiyonlar {
    
    private String fonkAdi;
    private String fonkGDTipi;
    private int parSayisi;
    public DegiskenPAR[] parametreler = new DegiskenPAR[20];
    
    
    public void fonkAdiAta(String fonkAdi)
    {
        this.fonkAdi = fonkAdi;
    }
    
    public void fonkGDTipiAta(String fonkGDTipi)
    {
        this.fonkGDTipi = fonkGDTipi;
    }
    
    public void parSayisiArttir()
    {
        parSayisi++;
    } 
    
    public String fonkAdiDondur()
    {
        return fonkAdi;
    }
    
    public String fonkGDTipiDondur()
    {
        return fonkGDTipi;
    }
    
    public int parSayisiDondur()
    {
        return parSayisi;
    }
    
    public void parSayisiAta(int parSayisi)
    {
        this.parSayisi = parSayisi;
    }
    
    
}
