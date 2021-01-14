
package Okunacak;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Program {
    //Java dilinde ArrayList varsa static dizi gibi kavramlara pek hakim olmadığım için sabit dizi kullandım.
    private static int maxKelime = 3000; //program.java'dan okunabilecek max kelime sayısı
    private static int kelimeSayisi = 0; //kelime için sayaç
    public static String[] kelimeler = new String[maxKelime]; //
    private static char[] ayraclar = {',',';','(',')','{','}','='};
    
    public static boolean ayracKontrol(int i)
    {
        for(int j=0;j<ayraclar.length;j++)
        {
            if((char)i == ayraclar[j])//fonksiyondan aldığın i değerinin char değeri belirlenen ayraçlardan birini eşitse
                return true;
        }
        return false;
    }
    

    public void oku()
    {
        File dosya = new File("test\\Okunacak\\Program.java");
        
        try
        {
             FileReader fr = new FileReader(dosya);
             BufferedReader br = new BufferedReader(fr);
             String kelime = "";
             int i;
             
             while((i = br.read()) != -1) //br.read() fonksiyonu -1 döndürmediği sürece devam et
             {           
                if(i == '/') //Yorum satırı mı değil mi ?
                {  
                    i = br.read();
                    if(i != -1 && i == '/') //Tek satırlık yorum ise tüm satırı oku fakat atama yapma
                        br.readLine();
                    else if(i != -1 && i == '*') //Blok yorum ise bitiş ifadesine kadar oku (*/) ve atama yapma
                    {
                        while(i != -1)
                        {   
                            i = br.read();
                            if(i == '*' && (i = br.read()) == '/')
                                break;
                        }
                    }
                    continue; // döngüye geri dön
                }
                
                while(i != -1 && i != ' ' && i != '\n' && i != '\t') /*Dosyanın sonuna gelinmediği sürece, okunan karakter
                   boşluk, satır sonu veya tab olmadığı sürece döngüye devam*/
                {
                    if(i == '"' || i == '\'') //i tirnak isaretlerinden birini esitse
                    {   
                        while((i = br.read()) != '"' && i != '\''){} //tirnak kapatalincaya kadar oku, atama yapma
                    }
                    if(ayracKontrol(i)) //ayraç görürsen bunları yap
                    {
                        if(kelime.trim() != "" && kelime.toCharArray()[0] != '\r')
                        kelimeler[kelimeSayisi++] = kelime.trim();
                        
                        kelimeler[kelimeSayisi++] = "" + (char)i;
                        kelime = "";
                        break;
                    }
                    kelime += (char)i;
                    i = br.read();
                }
                
                if(kelime.trim() != "" && kelime.toCharArray()[0] != '\r')
                    kelimeler[kelimeSayisi++] = kelime.trim();
                
                kelime = "";
             }                    
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }
    
    public int kelimeSayisiDondur()
    {
        return kelimeSayisi;
    }
    
    public String[] kelimeleriDondur()
    {
        return kelimeler;
    }
    
    public void yaz() //tüm kelimeleri yazdırmak için.Ödevi yaparken yardımcı oldu fakat programın son halinde bir işlevi yok
    {
        for(int i=0;i<kelimeSayisi;i++)
            System.out.println("i = " + i + " ==> " + kelimeler[i]);
        System.out.println(kelimeSayisi);
    }
    
}
