import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MainScrapper {

	public static void main(String[] args) {
		// variables
		String miUrl="https://www.bolsamadrid.es/esp/aspx/Mercados/Precios.aspx?indice=ESI100000000&punto=indice";
		int tiempo = 60000;

		try {
			// extrae el documento html
			Document doc = Jsoup.connect(miUrl).userAgent("Mozilla/5.0").timeout(100000).get();
			//selecciona la primera tabla 
			Element primerTab = doc.select("table").get(0);
			//selecciona las filas
			Elements filas = primerTab.select("tr");
			// recorre las filas
			for (int i=0;i<filas.size();i++) {
				// selecciona la fila
				Element fila = filas.get(i);
				//selecciona las columnas
				Elements cols = fila.select("td");
				// recorre las columnas y escribe en pantalla
				for (int j=0;j<cols.size();j++) {
					//System.out.println("Fila      "+filas.get(i).html());
					if ((i>7) && (i<44)){
						if ((j==0) || (j==1) || (j==7) || (j==8)) {
							if (cols.get(j).text()!=null) {
								System.out.print(" -  "+cols.get(j).text());
							}
						}
					}
				}
				System.out.println();
			}
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
