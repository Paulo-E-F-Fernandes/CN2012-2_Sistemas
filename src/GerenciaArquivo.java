import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author angelin
 */
public class GerenciaArquivo {
	private BufferedWriter relatorioSaida;
	int tamanho;
	
	public GerenciaArquivo(int tamanho) {
		this.tamanho = tamanho;
		try {
			new File(System.getProperty("user.home")+"/CNRelatorio/").mkdir();
			this.relatorioSaida = new BufferedWriter(new FileWriter(System.getProperty("user.home")+"/CNRelatorio/"+this.geraNome()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void escreveRelatorio(double[] resultado, String nomeMetodo) {
		int i;
		
		try {
			this.relatorioSaida.write(nomeMetodo);
			this.relatorioSaida.newLine();
			for(i = 0; i < this.tamanho - 1; i++) {
				this.relatorioSaida.write("Caminhão ("+(i+1)+")");
				this.relatorioSaida.write(',');
			}
			this.relatorioSaida.write("Caminhão ("+(i+1)+")");
			this.relatorioSaida.newLine();
			
			for(i = 0; i < resultado.length - 1; i++) {
	    		this.relatorioSaida.write(Double.toString(resultado[i]));
				this.relatorioSaida.write(',');
	    	}
	    	this.relatorioSaida.write(Double.toString(resultado[i]));
			this.relatorioSaida.newLine();
	    	this.relatorioSaida.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
	}
	
	public void encerrar() {
		try {
			this.relatorioSaida.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
	private String geraNome() {
		Locale locale = new Locale("pt","BR");
        GregorianCalendar calendar = new GregorianCalendar();
        SimpleDateFormat formatador = new SimpleDateFormat("yyyyMMddHHmmss",locale);
        
        return "Relatorio_"+formatador.format(calendar.getTime())+".csv";
	}
	
}
