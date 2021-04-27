public class App {
     private static double[] populacao;
    private static String[] binarios;
    private static int[] decimais;
    private static double[] fitness;
    private static double cruzamentomentoProb = 0.4;
    private static double mutacaoProb = 0.1;
    private static int individuos = 400;
    private static int bases = 8;
    
    public static void main(String[] args) {
        gerarPopulacaoInicial();
        fitness = new double[individuos];
        for (int j = 0; j < fitness.length; j++){ fitness[j] = fitness(decimais[j]);}
        for (int i = 0; i < bases*individuos; i++) {
            int pai = paiMen();
            int mae = maeMen(pai);
            cruzamento(pai,mae);
            mutacao(pai,mae);
            for (int j = 0; j < individuos; j++) decimais[j] = binarioEmDecimal(binarios[j]);
            for (int j = 0; j < fitness.length; j++){ fitness[j] = fitness(decimais[j]);}
        }
        System.out.println("Resultado da função");
        System.out.println("x = "+ binarios[paiMen()]);
        System.out.println("f(x) = "+fitness[paiMen()]);
    }
    
    public static void gerarPopulacaoInicial() {
        int espacos = individuos*bases, cont = 0;
        populacao = new double[espacos];
        decimais = new int[individuos];
        binarios = new String[individuos];
        for (int i = 0; i < espacos; i++) {
            populacao[i] = Math.random();
        }
        
        for (int i = 0; i < individuos; i++) {
            String binario = "";
            for (int j = 0; j < bases; j++) {
                binario += (int) Math.round(populacao[cont]);
                cont ++;
                binarios[i] = binario;
            }
            decimais[i] = binarioEmDecimal(binario);
        }
    }
    
    public static int binarioEmDecimal(String binario) {
        char[] cadeiaa = binario.toCharArray();
        char[] cadeia = new char[cadeiaa.length];
        for (int i = 0; i < cadeiaa.length; i++) cadeia[cadeiaa.length-i-1] = cadeiaa[i];
        int valor = 0, resultado = 0, aux = 0;
        for (int i = 0; i < cadeia.length; i++) {
            valor = aux * 2;
            if (valor == 0) valor = 1;
            if (cadeia[i] == '1') {
                resultado += valor;
            }
            aux = valor;
        }
        return resultado;
    }
    
    public static double fitness(int x) {return Math.pow(x,2) -(12*x)+16;} //x2 - 12x + 16
     
    public static int paiMai() {
        double maior = fitness[0];
        int posicao = 0;
        for (int i = 1; i < fitness.length; i++) {
            if (fitness[i] > maior){
                maior = fitness[i];
                posicao = i;
            }
        }
        return posicao;
    }
    
    public static int maeMai(double papa) {
        double mayor = 0; int posicion = 0;
        if (papa == 0) mayor = fitness[1];
        else mayor = fitness[0];
        for (int i = 0; i < fitness.length; i++) {
            if (fitness[i] > mayor && i != papa) {
                mayor = fitness[i];
                posicion = i;
            }
        }
        return posicion;
    }
    
    public static int paiMen() {
        double menor = fitness[0];
        int posicion = 0;
        for (int i = 1; i < fitness.length; i++) {
            if (fitness[i] < menor){
                menor = fitness[i];
                posicion = i;
            }
        }
        return posicion;
    }
    
    public static int maeMen(double papa) {
        double menor = 0; int posicion = 0;
        if (papa == 0) menor = fitness[1];
        else menor = fitness[0];
        for (int i = 0; i < fitness.length; i++) {
            if (fitness[i] < menor && i != papa) {
                menor = fitness[i];
                posicion = i;
            }
        }
        return posicion;
    }
    
    public static void cruzamento(int pai, int mae) {
        if (Math.random() < cruzamentomentoProb) {
            char[] binarioPa = binarios[pai].toCharArray();
            char[] binarioMa = binarios[mae].toCharArray();
            String cadeia = "";
            int cruzamento = Math.round(binarioPa.length/2);
            for (int i = 0; i < cruzamento; i++) cadeia += binarioPa[i];
            cruzamento = binarioMa.length - cruzamento;
            for (int i = cruzamento; i < binarioMa.length; i++) cadeia += binarioMa[i];
            binarios[0] = cadeia;
        }

    }
    
    public static void mutacao(int pai, int mae) {
        if (Math.random() < mutacaoProb) {
            char[] binarioPa = binarios[pai].toCharArray();
            int a = (binarioPa.length) -1;
            int posicion = (int) Math.round(Math.random() * a);
            if (binarioPa[posicion] == '0') binarioPa[posicion] = '1'; 
            else binarioPa[posicion] = '0';
            binarios[1] = String.valueOf(binarioPa);
            }
        if (Math.random() < mutacaoProb) {
            char[] binarioMae = binarios[mae].toCharArray();
            int a = (binarioMae.length) -1;
            int posicion = (int) Math.round(Math.random() * a);
            if (binarioMae[posicion] == '0') binarioMae[posicion] = '1'; 
            else binarioMae[posicion] = '0';
            binarios[2] = String.valueOf(binarioMae);
            
        }
    }
}
