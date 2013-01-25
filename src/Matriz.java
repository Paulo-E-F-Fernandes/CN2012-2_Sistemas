/**
 *
 * @author pargles
 * @version 1.0
 */
public class Matriz {
    public int linhas;
    public int colunas;
    public double matriz[][];
    private double coeficientes[];
    private String solucao;//string utilizada para ver se sistema tem unica solucao, infinitas ou é impossível

    //metodo construtor
    public Matriz(int linhas, int colunas){
        this.linhas = linhas;
        this.colunas = colunas;
        matriz = new double [linhas][colunas];//matriz e separa dos coeficientes pois necessario efetuar calculos apenas com ela
        coeficientes=new double[linhas];//coeficientes separados

    }

    public Matriz()
    {
        //construtor vazio
    }

    /* metodo que transpoe a matriz
     * @param void
     * @return void
     */
    public void transposta()
    {

    }

    /* metodo que retorna a matriz identidade da matriz atual
     * @param void
     * @return Matriz identidade
     */
    public Matriz identidade()
    {
        Matriz identidade = new Matriz();
        return identidade;
    }

    /* metodo que retorna se matriz tem solucao unica
     * infinita ou nao tem solucao
     * @param void
     * @return void
     */
    public String tipoSolucao()
    {
        String solucaotemp = "unica";// OU "infinitas" ou "nenhuma"
        return solucaotemp;
    }

    /* calcula o determinante de uma matriz 2x2
     * exemplo parametro {{2,2},{2,2}}
     * @param int mat2x2
     * @return int determinante
     */
    public int det2x2(int[][]mat2x2)
    {
        return mat2x2[0][0]*mat2x2[1][1]-mat2x2[0][1]*mat2x2[1][0];
    }


    /* metodo que imprime no terminal a matriz ampliada
     * @param void
     * @return
     */
    public void printaMatrizAmpliada()
    {

    }

    /* metodo que seta a dimensao da matriz
     * @param int linhas e colunas
     * @return void
     */
    public void setDimensao(int linhas, int colunas)
    {
        this.linhas = linhas;
        this.colunas = colunas;
    }

    public void setMatriz(int[][]m)
    {
        for(int i =0;i<linhas;i++)
        {
            System.arraycopy(m[i], 0, this.matriz[i], 0, colunas);
        }
    }

    /* metodo que retorna o vetor de coeficientes
     * @param void
     * @return int[] matrizCoeficientes
     */
    public int[] matrizCoeficientes()
    {
        int v[] = {2,2};
        return v;
    }

}
