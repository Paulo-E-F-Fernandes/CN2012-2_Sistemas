/**
 * @author paulo
 * @version 1.0
 */
public class ResolucaoSistemas {
    
    private Matriz matriz;
    private enum metodos{Todos, Gauss,Cholesky,LU,Jacobi,Seidel;}
    private Gauss ga;
    //private Cholesky cho;
    //private LU lu;

    //metodo construtor
    public ResolucaoSistemas(Matriz m)
    {
        this.matriz =m;
        ga = new Gauss();
        //cho= new Cholesky();
        //lu = new LU();

    }
    //private static EstruturaDados sistema;
    //private static Resultado resultado;
    //public static void main(String[] args) {
        //String tipoSistema;
        //InterfaceGrafica interfaceGrafica = new InterfaceGrafica();

        //GerenciaArquivos arqruivo = new GerenciaArquivo();
        //sistema = new EstruturaDados();
        //sistema.set(arquivo.lerArquivo(args[1]));

//        Verificador verificador = new Verificador();
        //tipoSistema = verificador.executar(sistema.get());

        //if (tipoSistema.compareTo("Sistema Linear Incompatível") == 0) {
        //	interfaceGrafica.erro(sistema.get());
        //}
        //else {
        //	if (interfaceGrafica.telaInicial(sistema.get())) {
        //Loading loading = new Loading();
        //Thread loadingThread = new Thread(loading);
        //loadingThread.start();
        //executar();
        //		arquivo.geraRelatorio(resultado);
        //loading.exit();
        //	}
        //	else {
        //System.exit(0);
        //	}
        //}

        //interfaceGrafica.resultado(tipoSistema, resultado);
        //System.exit(0);
    
/* metodo que vai executar os metodos diretos
 * executando apenas os selecinados
 * @param String metodo
 * @return int[] vetor com os resultados de x
 */
    public double[] executar(String metodo) {
        double[] temp = {2, 2};
        Loading loading = new Loading();
        Thread loadingThread = new Thread(loading);
        
        loadingThread.start();
        switch (metodos.valueOf(metodo)) {
            case Gauss:
                temp = ga.executar(matriz);
                break;
            case Cholesky:
                break;

            case LU:
                break;
        }
        loading.exit();
        return temp;
    }
    /* metodo que vai executar os metodos iterativos que precisam tbem de um vetor
     * @param String metodo, int[] vetor
     * @return int[] resultado
     */
    public int[] executar(String metodo, int[]vetor) {
        int[] temp = {2, 2};
        Loading loading = new Loading();
        Thread loadingThread = new Thread(loading);
        
        loadingThread.start();
        switch (metodos.valueOf(metodo)) {
            case Jacobi:
                break;
            case Seidel:
                break;
        }
        loading.exit();
        return temp;
    }
}
