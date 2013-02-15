import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 *
 * @author angelin
 */
public class GerenciaArquivo {
	private BufferedWriter relatorioSaida;
	int tamanho;
	/* metodo construtor da classe gerencia arquivo
         * @param int tamanho
         * @return void
         */
	public GerenciaArquivo(int tamanho) {
		this.tamanho = tamanho;
		try {
			new File(System.getProperty("user.home")+"/CNRelatorio/").mkdir();
			this.relatorioSaida = new BufferedWriter(new FileWriter(System.getProperty("user.home")+"/CNRelatorio/"+this.geraNome()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

        /* metodo que grava no arquivo o resultado dos metodos
         * @param double[] resultado String metodo
         * @return void
         */
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

        /* metodo que fecha o arquivo contendo o relatorio
         * @param void
         * @return void
         */
	public void encerrar() {
		try {
			this.relatorioSaida.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

        /* metod que gera um nome do arquivo de acordo com o dia mes e ano
         * @param void
         * @return String nome do arquivo
         */
	private String geraNome() {
		Locale locale = new Locale("pt","BR");
        GregorianCalendar calendar = new GregorianCalendar();
        SimpleDateFormat formatador = new SimpleDateFormat("yyyyMMddHHmmss",locale);
        
        return "Relatorio_"+formatador.format(calendar.getTime())+".csv";
	}
	
}
