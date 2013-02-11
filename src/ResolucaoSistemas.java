import java.text.DecimalFormat;
import java.text.NumberFormat;


/**
 * @author paulo
 * @version 1.0
 */
public class ResolucaoSistemas {
    
    private Matriz matriz;
    private enum metodos{Todos, Gauss,Cholesky,LU,Jacobi,Seidel;}
    private Gauss ga;
    private Cholesky cho;
    private LU lu;
    private GaussJacobi jo;
    private GaussSeidel gs;
    private NumberFormat formatacaoSaida;

    //metodo construtor
    public ResolucaoSistemas(Matriz m)
    {
        this.matriz =m;
        ga = new Gauss();
        cho= new Cholesky();
        lu = new LU();
        jo = new GaussJacobi();
        gs = new GaussSeidel();
        formatacaoSaida = new DecimalFormat("0.00000000");
    }

/* metodo que vai executar os metodos diretos
 * executando apenas os selecinados
 * @param String metodo
 * @return int[] vetor com os resultados de x
 */
    public double[] executar(String metodo) {
        double[] temp = {25, 25};
        
        switch (metodos.valueOf(metodo)) {
            case Gauss:
                temp = ga.executar(matriz);
                break;
            case Cholesky:
            	temp = cho.executar(matriz);
                break;

            case LU:
            	temp = lu.executar(matriz);
                break;
        }
        System.out.println("Resultado método "+metodo+" =");
        printarResultado(temp);
        return temp;
    }
    /* metodo que vai executar os metodos iterativos que precisam tbem de um vetor
     * @param String metodo, int[] vetor
     * @return int[] resultado
     */
    public double[] executar(String metodo, double[]vetor,double erro) {
        double[] temp = {21, 21};
        
        switch (metodos.valueOf(metodo)) {
            case Jacobi:
                temp = jo.executar(matriz, vetor, erro);
                break;
            case Seidel:
            	temp = gs.executar(matriz, vetor, erro);
                break;
        }
        System.out.println("Método: "+metodo+" =");
        printarResultado(temp);
        return temp;
    }
    
    /* metodo que printa no terminal o vetor resultante
     * @param double[] vetor
     * @return void
     */
    public void printarResultado(double[] vet)
    {
        for(int i=0;i<vet.length;i++)
        {
            System.out.println(formatacaoSaida.format(vet[i]));
        }
    }
}
