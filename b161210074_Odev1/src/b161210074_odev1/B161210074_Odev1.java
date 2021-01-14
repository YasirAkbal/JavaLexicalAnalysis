/**
*
* @author Ahmet Yasir Akbal - ahmet.akbal@ogr.sakarya.edu.tr
* @since 11.03.2018
* <p>
* Lexical analysis yapan program
* </p>
*/
package b161210074_odev1;


public class B161210074_Odev1 {

    private static Sinif sinif = new Sinif();
    private static int bas; // sinifin bloğunun({) başladığı indis
    private static int acma; // { ifadesinin sayısını tutmak için değişken
    private static int kapama; // } ifadesinin sayısını tutmak için değişken
    
    public static boolean doWhileKontrol(int indis,String[] kelimeler) // do-while bloklarını dikkate almaması için
    {   
        for(int i=indis;i > bas && !kelimeler[i].equals("}");i--)
        {
            if(kelimeler[i].equals("{"))
            {
                return true;
            }
        }
        return false;
    }
    
    public static void baslangicAta(String[] kelimeler,int kelimeSayisi) //sınıfın basladığı indisi belirle
    {
        for(int i=0;i<kelimeSayisi;i++) // baslangıç indisini bul
        {
            if(kelimeler[i].equals("{"))
            {
                bas = i;
                break;
            }             
        }
    }
      
    public static void analizEt(String[] kelimeler,int kelimeSayisi)
    {   
        baslangicAta(kelimeler,kelimeSayisi); //baslangic indisini atamak için fonksiyon çağrısı
        int i = 0;
        for(;i<kelimeSayisi-1;i++) // sinif ismini bulur
        {
            if(kelimeler[i].equals("class")) //keliemeler[i] "class" stringine eşitse
            {
                sinif.sinifAdiAta(kelimeler[i+1]); // kelimeler[i+1]'i sınıf ismi olarak ata
                break;
            }
        }
             
        for(i=bas;i<kelimeSayisi-2;i++)
        {   //eğer ( işareti ardından { işareti geliyorsa bu bloğun içine gir. metot veya fonksiyon bloğunu bulur    
            if(kelimeler[i].equals(")") && kelimeler[i+1].equals("{")) //kontrol ve döngü bloklarının içini es geçmek için
            {     
                acma = 1; //zaten acma parantezinin 1 oldugunu biliyorduk üstteki kontrol sayesinde
                kapama = 0; //amac acma ve kapama parantezlerinin sayısını eşitlemek.kapama parantezini henüz görmedik
                for(i = i+2;i < kelimeSayisi-4 && acma != kapama;i++) //acma kapamaya esit olmadigi sürece devam et
                {   
                    if(kelimeler[i].equals("{"))
                        acma++;
                    else if(kelimeler[i].equals("}"))
                        kapama++;                        
                }
            }
            //bu if ifadesi değişkenleri bulmak için
            if(kelimeler[i].equals(";") && !doWhileKontrol(i,kelimeler))//kelimeler[i] ";" işaretine eşitse ve kelimeler[i] do-while döngüsünün içinde değilse
            {   
                int a = i-1;/*noktalı virgülden önceki kelime değişken adıdır fakat = ile atama yapılmadıysa
                aşağıdaki döngünün içine her iki durumda da girecektir fakat = ile atama yapıldıysa a değişkenini
                manipüle edecektir*/
                for(int j=a;j>bas && !kelimeler[j].equals(";");j--) //";" işareti görene kadar
                {
                    if(kelimeler[j].equals("="))
                    {
                        a = j-1;
                        break;
                    }
                }
                            
                int eSayisi = sinif.elemanSayisiDondur();
                sinif.degiskenvepar[eSayisi] = new DegiskenPAR();             
                       
                sinif.degiskenvepar[eSayisi].vTipiAta(kelimeler[a-1]);
                sinif.degiskenvepar[eSayisi].dAdiAta(kelimeler[a]);
                
                sinif.elemanSayisiArttir();
            }   //kurucu da dahil olmak üzere tüm metot ve fonksiyonları bulan if ifadesi
            else if(((kelimeler[i+2].equals("(")) || kelimeler[i].equals(sinif.sinifAdiDondur())) && !kelimeler[i+1].equals("while"))
            {     
                int k = i;
                String gDTipi = kelimeler[i];
                if(kelimeler[i].equals(sinif.sinifAdiDondur()))//fonksiyon&metot tanımlama kısmında sınıf adı varsa bu kurucu metottur
                {
                    gDTipi = "Yok"; //kurucuların geri dönüş tipi olmaz
                    k = i-1; //geri dönüş tipi olmadığı için fonksiyon adı,geridönüş tipi ve parametleri doğru atamak için k değişkeni değiştirilir
                }
                if(gDTipi.equals("public") || gDTipi.equals("private") || gDTipi.equals("protected")) continue; //yapıcı fonksiyonu 2 kere okumasını engellemek için
                
                int fSayisi = sinif.fonkSayisiDondur();
                sinif.fonksiyonlar[fSayisi] = new Fonksiyonlar();
                
                sinif.fonksiyonlar[fSayisi].fonkGDTipiAta(gDTipi);
                sinif.fonksiyonlar[fSayisi].fonkAdiAta(kelimeler[k+1]);
                 
                for(int j=k+3;!kelimeler[j].equals(")");j+=3)//parametler veriTipi degiskenAdi seklinde tanimlanir
                {/*1den fazla parametre varsa araya virgül konulur ve kalıpda tanımlamaya devam edilir.Döngü ) işaretini
                  görene kadar ve , işaretini gördüğü sürece dönmeye devam edecek.*/
                    
                    int fPSayisi = sinif.fonksiyonlar[fSayisi].parSayisiDondur();
                    sinif.fonksiyonlar[fSayisi].parametreler[fPSayisi] = new DegiskenPAR();

                    sinif.fonksiyonlar[fSayisi].parametreler[fPSayisi].vTipiAta(kelimeler[j]);
                    sinif.fonksiyonlar[fSayisi].parametreler[fPSayisi].dAdiAta(kelimeler[j+1]);                   

                    sinif.fonksiyonlar[fSayisi].parSayisiArttir();
                    if(!kelimeler[j+2].equals(","))//eğer j+2 indisinde bulunan string ","e eşit değilse başka parametre yoktur döngüden çık
                        break;   
                }
                sinif.fonkSayisiArttir();
            }
        }   
    }
     
    public static void main(String[] args) {
        
        DosyaOku d = new DosyaOku();
        d.oku();
        analizEt(d.kelimeleriDondur(),d.kelimeSayisiDondur());
        sinif.goster();    
        
    }    
}

