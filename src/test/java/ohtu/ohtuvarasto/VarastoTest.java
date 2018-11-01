package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto varasto2;
    Varasto luontiSaldolla;
    Varasto luontiSaldolla2;
    Varasto miinusSaldo;
    Varasto ylimaaraSaldoa;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        varasto2=new Varasto(0);
        luontiSaldolla=new Varasto(10, 2);
        luontiSaldolla2=new Varasto(0, 0);
        miinusSaldo=new Varasto(10, -1);
        ylimaaraSaldoa=new Varasto(10, 11);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriLuoVarastonSaldolla() {
        assertEquals(2, luontiSaldolla.getSaldo(), vertailuTarkkuus);
    }
    @Test
    public void KonstruktorimukauttaaSaldonTilavuuteen() {
        assertEquals(10, ylimaaraSaldoa.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void saldollaLuodunVarastonTilavuusOikein() {
        assertEquals(10, luontiSaldolla.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void KelvotonVarastoSaldolla() {
        assertEquals(0, luontiSaldolla2.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, miinusSaldo.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void kelvotonVarastoTunnistuu() {
        assertEquals(0, varasto2.getTilavuus(), vertailuTarkkuus);
    }
    

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }
    @Test
    public void lisaysToimiiOikein() {
        varasto.lisaaVarastoon(11);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
        luontiSaldolla.lisaaVarastoon(-1);
        assertEquals(2, luontiSaldolla.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void ottaminenvarastostaToimiiOikein() {
        luontiSaldolla.otaVarastosta(-2);
        assertEquals(2, luontiSaldolla.getSaldo(), vertailuTarkkuus);
        luontiSaldolla.otaVarastosta(10);
        assertEquals(0, luontiSaldolla.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void testataanTulostus() {
        String tuloste="saldo = " + luontiSaldolla.getSaldo() + ", vielä tilaa " + luontiSaldolla.paljonkoMahtuu();
        assertEquals(tuloste, luontiSaldolla.toString());
    }

}