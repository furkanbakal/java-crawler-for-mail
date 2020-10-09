/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fırat.mail;

import java.util.ArrayList;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author Furkan
 */
public class FıratMail {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ArrayList<String> kodlar = new ArrayList<String>();
        try {
            Document doc = Jsoup.connect("http://ogrenci.db.firat.edu.tr/tr/node/38").get();

            Elements trs = doc.select("table").select("tr");

            for (int i = 2; i < trs.size(); i++) {
                try {
                    String bolumKodu = trs.get(i).select("td").get(0).text();
                    if (!isInteger(bolumKodu)) {
                        bolumKodu = trs.get(i).select("td").get(1).text();
                    }
                    if (isInteger(bolumKodu)) {
                        kodlar.add(bolumKodu);
                    }
                } catch (Exception e) {
                    // System.out.println("bu trr de bölüm kodu yok");
                }

            }
        } catch (Exception e) {
            System.out.println(e);
        }

        for (int year = 14; year <= 20; year++) {
            for (String kod : kodlar) {
                for (int girisSirasi = 1; girisSirasi < 120; girisSirasi++) {
                    String ogrenciNo = "";
                    int adet = 0;
                    int temp = girisSirasi;
                    while (temp != 0) {
                        temp /= 10;
                        ++adet;
                    }

                    switch (adet) {
                        case 1:
                            ogrenciNo = year + kod + "00" + girisSirasi + "@firat.edu.tr";
                            break;
                        case 2:
                            ogrenciNo = year + kod + "0" + girisSirasi + "@firat.edu.tr";
                            break;
                        case 3:
                            ogrenciNo = year + kod + girisSirasi + "@firat.edu.tr";
                            break;
                        default:
                            break;
                    }
                    System.out.println(ogrenciNo);
                }
            }
        }

    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    
    

}
