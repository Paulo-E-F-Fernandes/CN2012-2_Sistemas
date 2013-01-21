
public class ResolucaoSistemas {

	//private static EstruturaDados sistema;
	//private static Resultado resultado;
	
	public static void main(String[] args) {
		//String tipoSistema;
		//InterfaceGrafica interfaceGrafica = new InterfaceGrafica();
		
		//GerenciaArquivos arqruivo = new GerenciaArquivo();
		//sistema = new EstruturaDados();
		//sistema.set(arquivo.lerArquivo(args[1]));
		
		Verificador verificador = new Verificador();
		//tipoSistema = verificador.executar(sistema.get());

		//if (tipoSistema.compareTo("Sistema Linear Incompatível") == 0) {
		//	interfaceGrafica.erro(sistema.get());
		//}
		//else {
		//	if (interfaceGrafica.telaInicial(sistema.get())) {
				Loading loading = new Loading();
				Thread loadingThread = new Thread(loading);
				loadingThread.start();
				executar();
		//		arquivo.geraRelatorio(resultado);
				loading.exit();
		//	}
		//	else {
				System.exit(0);
		//	}
		//}
		
		//interfaceGrafica.resultado(tipoSistema, resultado);
		System.exit(0);
	}
	
	public static void executar() {
		//Resolvedor resolvedor = new Resolvedor();
		//resultado = new Resultado();
		//resultado.set(resolvedor.executar(sistema.get()));
	}

}
