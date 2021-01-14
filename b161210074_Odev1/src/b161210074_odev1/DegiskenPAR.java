/**
*
* @author Ahmet Yasir Akbal - ahmet.akbal@ogr.sakarya.edu.tr
* @since 11.03.2018
* <p>
* Lexical analys yapan program
* </p>
*/
package b161210074_odev1;

public class DegiskenPAR {
    
    private String veriTipi;
    private String degiskenAdi;
    
    public void vTipiAta(String veriTipi)
    {
        this.veriTipi = veriTipi;
    }
    
    public void dAdiAta(String degiskenAdi)
    {
        this.degiskenAdi = degiskenAdi;
    }
    
    public String VTipiDondur()
    {
        return veriTipi;
    }
    
    public String dAdiDondur()
    {
        return degiskenAdi;
    }
    
}
