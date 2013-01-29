/**
 * @author gaci
 * @version 1.0
 * @see http://introcs.cs.princeton.edu/java/95linear/
 * @see https://sites.google.com/site/programacaocpp/calculo-numerico/eliminacao-de-gauss-com-pivoteamento-parcial
 */
public class Gauss {
    private double[] resultados;
    private double[] coeficientes;
    private double[][] tempMat;
    private int linhas;

    /* metodo padrao de todos os metodos numericos
     * @param Matriz m
     * @return int [] vetor com resuldados de x
     */
    public double[] executar(Matriz m)
    {
        linhas = m.linhas;
        resultados = new double[linhas];//cada linha vai ter um resultado
        coeficientes = m.getCoeficientes();
        tempMat = m.getMatriz();
        //System.arraycopy(m.getCoeficientes(), 0, coeficientes, 0, linhas);//faz uma copia dos coeficientes
        //System.arraycopy(m.getMatriz(), 0, tempMat, 0, m.matriz.length);//faz uma copia da matriz sem os coeficientes
        calcularTriangularSuperior();
        calcularResultado();       
        return resultados;  
    }
    
    /* metod que calcula a matriz triangular superiror por referencia
     * @param void
     * @return void
     */
    public void calcularTriangularSuperior()
    {
        for(int k = 0; k < (linhas - 1); k++){
            // Percorre linhas secundárias... Até o fim da matriz ou seja... até n
            for(int i = (k + 1); i < linhas; i++){
                // Escolher como pivô o elemento de maior múdulo entre os coeficientes...
                if(Math.abs(tempMat[k][k]) < Math.abs(tempMat[i][k])){
                    // Neste casó é necessário trocar as linhas k e i
                    System.out.printf("Trocando Linhas: %d por %d\n", k + 1, i + 1);
                    TrocarLinhas(k, i);
                }

                double m = tempMat[i][k] / tempMat[k][k];//aki divide o pivo pela linha acima
                tempMat[i][k]  = 0;//zera o elemento da linha abaixo

                // laco que calcula os demais elementos da linha
                for(int j = (k + 1); j < linhas; j++){
                    tempMat[i][j] = tempMat[i][j] - m * tempMat[k][j];
                }
                coeficientes[i] = coeficientes[i] - m * coeficientes[k];
            }
        }
    }
    
  
    private void TrocarLinhas(int linha1, int linha2) {
        double aux = 0.00;
        // Troca elementos das linhas na matriz: a
        for (int i = 0; i < linhas; i++) {
            aux = tempMat[linha1][i];
            tempMat[linha1][i] = tempMat[linha2][i];
            tempMat[linha2][i] = aux;
        }
        // Troca os elementos do vetor: b
        aux = coeficientes[linha1];
        coeficientes[linha1] = coeficientes[linha2];
        coeficientes[linha2] = aux;
    }
    
    private void calcularResultado() {
        linhas--;    // para facilitar os cáculos já que o indece de matrizes em java começam em zero!
        resultados[linhas] = coeficientes[linhas] / tempMat[linhas][linhas];//calcula o ultimo x, ja que o resultado e praticamente direto
        for (int k = linhas; k > -1; k--) {
            double s = 0;
            for (int j = (k + 1); j < (linhas + 1); j++) {
                s = s + tempMat[k][j] * resultados[j];
                resultados[k] = (coeficientes[k] - s) / tempMat[k][k];
            }
        }
        linhas++;    // para acertar o valor das linhas
    }
    
    /* metodo que imprime o vetor resposta
     * @param void
     * @return void
     */
    public void printaResposta()
    {
        System.out.print("Resultado: ");
        for(int i=0;i<resultados.length;i++)
        {
            System.out.print(resultados[i]+" , ");
        }
        System.out.println();//\n
    }
}
