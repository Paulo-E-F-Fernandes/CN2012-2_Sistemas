/**
 * @see http://introcs.cs.princeton.edu/java/95linear/
 * @see https://sites.google.com/site/programacaocpp/calculo-numerico/eliminacao-de-gauss-com-pivoteamento-parcial
 * 
 * @author pargles
 * @version 2.0
 */
public class Gauss {
    private double[] resultados;
    private double[] termosIndependentes;
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
        termosIndependentes = new double[linhas];
        tempMat = new double[linhas][m.colunas-1];
        System.arraycopy(m.termosIndependentes, 0, termosIndependentes, 0, linhas);
        for(int i=0;i<linhas;i++)
        {
            System.arraycopy(m.matriz[i], 0, tempMat[i], 0, m.colunas-1);
        }
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
                // Escolher como pivô o elemento de maior múdulo entre os termosIndependentes...
                //if(Math.abs(tempMat[k][k]) < Math.abs(tempMat[i][k])){
                if(tempMat[k][k]==0){// a diagonal principal nao pode ter zeros
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
                termosIndependentes[i] = termosIndependentes[i] - m * termosIndependentes[k];
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
        aux = termosIndependentes[linha1];
        termosIndependentes[linha1] = termosIndependentes[linha2];
        termosIndependentes[linha2] = aux;
    }
    
    private void calcularResultado() {
        linhas--;    // para facilitar os cáculos já que o indece de matrizes em java começam em zero!
        resultados[linhas] = termosIndependentes[linhas] / tempMat[linhas][linhas];//calcula o ultimo x, ja que o resultado e praticamente direto
        for (int k = linhas; k > -1; k--) {
            double s = 0;
            for (int j = (k + 1); j < (linhas + 1); j++) {
                s = s + tempMat[k][j] * resultados[j];
                resultados[k] = (termosIndependentes[k] - s) / tempMat[k][k];
            }
        }
        linhas++;    // para acertar o valor das linhas
    }
}