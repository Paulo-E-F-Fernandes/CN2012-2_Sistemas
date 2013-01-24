//ACHO QUE NÃO É NECESSÁRIO UMA CLASSE PARA O VERIFICADOR
//ISSO PODERIA SER UM METODO DA CLASSE MATRIZ ONDE VAI IDENTIFICAR
//SE AQUELA MATRIZ POSSUI OU NAO SOLUCAO
public class Verificador {

	public Verificador() {}
	
	public String executar(Object objeto) {
		int resultado = this.verificaSolucoes(objeto);
		
		if (resultado == 1) {
			return "Sistema Linear Compatível Determinado";
		}
		
		else { 
			if (resultado == 2) {
				return "Sistema Linear Compatível Indeterminado";
			}
			else {
				return "Sistema Linear Incompatível";
			}
		}
	}
	
	public int verificaSolucoes(Object objeto) {
		/****Pseudo-Código*****/
		//fatorMult = 0
		//if celula[0X0] > celula[1X0]
		//	fatorMult = celula[0X0] / celula[1X0]
		//else
		//	fatorMult = celula[1X0] / celula[0X0]
		
		//enquanto coluna = 0 < tamanhoColuna
		//	enquanto linha = 1 < tamanhoLinha
		//  	if (this.divisor(celula[linhaXcoluna], celula[linha-1Xcoluna]) != fatorMult) {
		//			return 2;
		//		}
		
		//enquanto linha = 1 < tamanhoLinha
		//	if (this.divisor(celula[linhaX0], celula[linha-1X0]) != fatorMult) {
		//		return 3;
		//	}
		/****Pseudo-Código*****/
		return 1;
	}
	
	//public int divisor(int celula1, int celula2) {
	/****Pseudo-Código*****/
	//	if celula1 > celula2
	//		return celula1 / celula2;
	//	else
	//		return celula2 / celula1;
	/****Pseudo-Código*****/
	//}
}
