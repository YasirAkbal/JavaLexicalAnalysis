/**
*
* @author Ahmet Yasir Akbal - ahmet.akbal@ogr.sakarya.edu.tr
* @since 11.03.2018
* <p>
* Lexical analys yapan program
* </p>
*/
package b161210074_odev1;

public class Sinif {
    
    private String sinifAdi;
    private int elemanSayisi;
    private int fonkSayisi;
    public DegiskenPAR[] degiskenvepar = new DegiskenPAR[100];
    public Fonksiyonlar[] fonksiyonlar = new Fonksiyonlar[100];
    
    public String sinifAdiDondur()
    {
        return sinifAdi;
    }
    
    public void sinifAdiAta(String sinifAdi)
    {
        this.sinifAdi = sinifAdi;
    }
    
    public void elemanSayisiArttir()
    {
        elemanSayisi++;
    }
    
    public int elemanSayisiDondur()
    {
        return elemanSayisi;
    }
    
    public void fonkSayisiArttir()
    {
        fonkSayisi++;
    }
    
    public int fonkSayisiDondur()
    {
        return fonkSayisi;
    }
    
    public void goster()
    {
        System.out.println("Sınıf Adı: " + sinifAdiDondur());
        System.out.println("--------------------------------------");
        System.out.println("Alt Elemanlar: " + elemanSayisiDondur());
        for(int i=0;i<elemanSayisiDondur();i++)
        {
            System.out.println(degiskenvepar[i].dAdiDondur() + " - " + degiskenvepar[i].VTipiDondur());      
        }
        System.out.println("--------------------------------------");
        System.out.println("Üye Fonksiyonlar: " + fonkSayisiDondur());
        for(int i=0;i<fonkSayisiDondur();i++)
        {
            System.out.println("-----------");
            System.out.println(fonksiyonlar[i].fonkAdiDondur());
            System.out.println("Dönüş türü: " + fonksiyonlar[i].fonkGDTipiDondur());
            System.out.println("Aldığı Parametre: " + fonksiyonlar[i].parSayisiDondur());
            for(int j=0;j<fonksiyonlar[i].parSayisiDondur();j++)
                System.out.println(fonksiyonlar[i].parametreler[j].dAdiDondur() + " - " + fonksiyonlar[i].parametreler[j].VTipiDondur());
        }
    }
    
}
