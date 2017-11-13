package projetointegrador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Math.pow;
import java.util.Scanner;

public class ProjetoIntegrador {
    static Scanner ler = new Scanner(System.in);
    
    //Módulo de criptografia
    public static void criptografia(){
        int opcao, shift = 0;
        String texto = "", txtCriptografado = "", nomeArquivo = "";
        
        System.out.println("\n--------- MÓDULO DE CRIPTOGRAFIA ---------\n");
        
        //Menu de opções
        do{
            System.out.println("Selecione a opção desejada: ");
            System.out.println("1 - Criptografar um texto digitado");
            System.out.println("2 - Descriptografar o texto digitado na opção 1");
            System.out.println("3 - Criptografar um arquivo texto");
            System.out.println("4 - Descriptografar o arquivo texto da opção 3");
            System.out.println("5 - Ir para o módulo de conversão");
            System.out.println("0 - Voltar para o menu principal");
               
            opcao = ler.nextInt();

            switch(opcao){
                case 1: //Criptografar um texto digitado no console
                    ler.nextLine(); //Limpa o buffer do teclado
                    
                    System.out.print("Digite um texto qualquer: ");
                    texto = ler.nextLine();
                    
                    texto = texto.toUpperCase(); //Passa todas as letras do texto recebido para caracteres maiúsculos
        
                    System.out.print("Digite o shift: ");
                    shift = ler.nextInt(); //Digita o espaçamento
                    
                    txtCriptografado = cripTexto(texto, shift); //Criptografar um texto digitado pelo usuário
                    System.out.println();
                    break;
               
                case 2: //Descriptografar o texto digitado no console anteriormente
                    ler.nextLine();
                    
                    System.out.print("Digite o shift: ");
                    shift = ler.nextInt(); //Digita o espaçamento
                    
                    descripTexto(txtCriptografado, shift); //Descriptografar o texto digitado pelo usuário
                    System.out.println();
                    break;
               
                case 3: //Criptografar um arquivo texto
                    ler.nextLine();
                    
                    System.out.print("Digite o nome do arquivo: ");
                    nomeArquivo = ler.nextLine(); //Armazena o nome do arquivo
                    
                    System.out.print("Digite o shift: ");
                    shift = ler.nextInt(); //Armazena o espaçamento
                    
                    cripArq(nomeArquivo, shift); //Criar um arquivo texto e criptografar o mesmo
                    break;
                
                case 4: //Descriptografar um arquivo texto
                    ler.nextLine();
                    
                    System.out.print("Digite o nome do arquivo: ");
                    nomeArquivo = ler.nextLine(); //Armazena o nome do arquivo
                    
                    System.out.print("Digite o shift: ");
                    shift = ler.nextInt(); //Armazena o espaçamento
                    
                    descripArq(nomeArquivo, shift); // Descriptografar o arquivo texto
                    break;
                    
                case 5: //Ir para o módulo de conversão de valores
                    conversao();
                    break;
                    
                case 0: //Finalizar o programa
                    System.out.println("\nVoltando para o menu principal!\n");
                    break;
                    
                default: //Caso o usuário digite um valor que não existe em nenhum case
                    System.out.println("\nOpção inválida!\nDigite novamente!\n");
                    break;
            }
        }while(opcao != 0);
    }
    
    //Módulo de conversão
    public static void conversao(){
        int opcao;
        
        System.out.println("\n--------- MÓDULO DE CONVERSÃO ---------\n");
        
        //Menu de opções
        do{
            System.out.println("Selecione a opção desejada: ");
            System.out.println("1 - Converter um número DECIMAL para HEXADECIMAL e BINÁRIO");
            System.out.println("2 - Converter um número BINARIO para HEXADECIMAL e DECIMAL");
            System.out.println("3 - Converter um número HEXADECILAM para DECIMAL e BINÁRIO");
            System.out.println("4 - Ir para o módulo de criptografia");
            System.out.println("0 - Voltar para o menu principal");
            
            opcao = ler.nextInt();

            switch(opcao){
                case 1:
                    hexaBin(); //Converter um número Decimal para Hexadecimal e Binário
                    break;
                
                case 2:
                    hexaDeci(); //Converter um número Binário para Hexadecimal e Decimal
                    break;
                
                case 3:
                    deciBin(); //Converter um número Hexadecimal para Decimal e Binário
                    break;
                
                case 4: //Ir para o módulo de criptografia
                    criptografia();
                    break;
                    
                case 0: //Finalizar o programa
                    System.out.println("\nVoltando para o menu principal!\n");
                    break;
                    
                default: //Caso o usuário digite alguma opção inválida
                    System.out.println("\nOpção inválida!\nDigite novamente!\n");
                    break;
            }
        }while(opcao != 0);
    }
    
    public static String cripTexto(String texto, int shift){
        String txtCriptografado = ""; //Texto criptografado inicializado como vazio no primeiro momento
        int aux = 0; //Inteiro auxiliar para armazenar o caractere somado com o shift
        
        for(int i = 0; i < texto.length(); i++){ //Percorrendo a string digitada pelo usuário
            if((int)texto.charAt(i) >= 65 && (int)texto.charAt(i) <= 90){
                aux = (int)texto.charAt(i) + shift; //aux recebe o valor ASCII da letra na posição i da string e soma esse valor com o shift

                /* Como todas as letras estão sendo transformadas para maiúsculas, sabe-se que a última letra maiúscula da tabela ASCII
                 * possui o valor 90. Sendo assim, se a soma com o shift ultrapassar 90, devemos subtrair 26 já que temos 26 letras no total.
                 */
                if(aux > 90){ 
                    aux = aux - 26;
                }
            }
            else{
                aux = (int)texto.charAt(i);
            }
            
            //Com o caractere já criptografado, concatenamos o conteúdo atual de txtCriptografado com o novo caractere criptografado
            txtCriptografado = txtCriptografado + (char)aux;
        }
        
        //Mostrando o texto original e o texto criptografado
        System.out.println("\nTexto original: " + texto);
        System.out.println("Texto criptografado: " + txtCriptografado + "\n");
        
        return txtCriptografado;
    }
    
    public static String descripTexto(String texto, int shift){
        String txtDescriptografado = ""; //Texto descriptografado inicializado como vazio no primeiro momento
        int aux; //Inteiro auxiliar para armazenar o caractere subtraído do shift
        
        /* Se o texto que veio por parâmetro estiver vazio,
         * quer dizer que o usuário não digitou nenhum texto no console anteriormente.
         * Sendo assim, não existe texto para descriptografar uma vez que esse texto não é armazenado em nenhum outro lugar
         * que não seja o próprio console do Java.
         */
        if(texto.length() == 0){
            System.out.println("\n\nNenhum texto foi digitado anteriormente!\nExecute a opção 1 antes de executar a opção 2!\n\n");
        }
        else{ //Se não, o usuário já executou a opção de criptografar um texto do console.
            for(int i = 0; i < texto.length(); i++){ //Loop para percorrer os caracteres da string individualmente
                if((int)texto.charAt(i) >= 65 && (int)texto.charAt(i) <= 90){
                    aux = (int)texto.charAt(i) - shift; //aux recebe o valor ASCII da letra na posição i (calculado com o shit da opção anterior) e subtrai o mesmo shift para obtermos o caractere "original"

                    /* Agora, como sabemos que a primeira letra do alfabeto maiúsculo da tabela ASCII, caso a subtração
                     * resulte em um número menor que 65, devemos somar 26 que é o total de letras do alfabeto
                     */
                    if(aux < 65){
                        aux = aux + 26;
                    }
                }
                else{
                    aux = (int)texto.charAt(i);
                }
                txtDescriptografado = txtDescriptografado + (char)aux; //Concateno o caractere atual descriptografado com a string que tinhamos anteriormente
            }
            
            //Mostrando o texto descriptografado e o texto criptografado em seguida
            System.out.println("\nTexto descriptografado: " + txtDescriptografado);
            System.out.println("Texto criptografado: " + texto + "\n");
        }
        return txtDescriptografado;
    }
    
    public static void cripArq(String nomeArquivo, int shift){
        try{
            FileReader arq = new FileReader(nomeArquivo + ".txt"); //Cria um arquivo txt com um nome digitado pelo usuário
            BufferedReader lerArq = new BufferedReader(arq);
            
            String texto = lerArq.readLine();
            
            FileWriter escArq = new FileWriter(nomeArquivo + ".txt");
            PrintWriter gravaArq = new PrintWriter(escArq);

            while(texto != null){
                texto = texto.toUpperCase();
                gravaArq.println(cripTexto(texto, shift));
                
                texto = lerArq.readLine();
            }
            
            arq.close();
            escArq.close();
            
        }catch(IOException e){
            System.out.print(e.getMessage()); //Exceção de erro de arquivo, mostra uma mensagem caso não seja possível criar o arquivo ou algo do gênero
        }
        catch(Exception e){
            System.out.println("\n\nHouve um erro!\n\n"); //Exceção generica caso ocorra algum erro que não é ligado a entrada e saída de dados do arquivo
        }
    }
    
    public static void descripArq(String nomeArquivo, int shift){
        //ler.nextLine(); //Limpando o buffer do teclado
     
        try{
            FileReader arq = new FileReader(nomeArquivo + ".txt"); //Criamos um leitor de arquivo, passando o nome digitado pelo usuário concatenado com sua extensão
            BufferedReader lerArq = new BufferedReader(arq); //Objeto para ler o conteúdo do arquivo e armazenar em um buffer
            
            String texto = lerArq.readLine(); //Lendo a segunda linha do arquivo (a primeira já foi lida pois era o shift)
            
            FileWriter escreveArq = new FileWriter(nomeArquivo + ".txt"); //Criando um objeto para escrever dados no arquivo pois vamos sobrescrever o texto criptografado pelo texto não criptografado 
            PrintWriter gravaArq = new PrintWriter(escreveArq);
            
            while(texto != null){ //Loop para ler o arquivo enquanto não for final de arquivo
                texto = texto.toUpperCase();
                gravaArq.println(descripTexto(texto, shift)); //Descriptografa a linha atual
                
                texto = lerArq.readLine(); //Passa para a próxima linha
            }
            
            arq.close(); //Fecha o leitor do arquivo
            escreveArq.close(); //Fecha o escritor do arquivo
        }catch(IOException e){
            System.out.println("\n" + e.getMessage() + "\n"); //Exceção de erro de arquivo, mostra uma mensagem caso não seja possível criar o arquivo ou algo do gênero
        }
        catch(Exception e){
            System.out.println("\n\nHouve um erro!\n"); //Exceção generica caso ocorra algum erro que não é ligado a entrada e saída de dados do arquivo
        }
    }
    
    public static void hexaBin(){
        int decimal, res = 0, resultado;
        String binario = "", hexadecimal = "", binAux = "", hexaAux = "";
        
        System.out.print("\nDigite um número inteiro: ");
        decimal = ler.nextInt(); //Armazena um valor digitado no formato decimal
        
        
        //Mostra os resultados
        System.out.println("\nDecimal: " + decimal);
        System.out.println("Binario: " + convBinario(decimal));
        System.out.println("Hexadecimal: " + convHexa(decimal) + "\n");
    }
    
    public static void hexaDeci(){
        String binario, auxStr;
        int decimal = 0, aux = 0;
        boolean valido = true;
        
        ler.nextLine(); //Limpando o buffer do teclado
        System.out.print("\nDigite um número binário: ");
        binario = ler.nextLine(); //Armazena um valor digitado no formato binário
        
        for(int i = 0; i < binario.length(); i++){ //Percorre a string digitada pelo usuário para saber se é um valor válido
            if(binario.charAt(i) != '0' && binario.charAt(i) != '1'){ //Se o caractere na posição i não for 0 e nem for 1
                System.out.println("\nValor inválido!\nDigite apenas um valores formados por 0 e/ou 1\n");
                valido = false; //A entrada não é válida
            }
        }
        if(valido){ //Se a entrada for válida, realiza os próximos passos de conversão
            //Loop para percorrer todos os dígitos do valor binário individualmente
            for(int i = binario.length() - 1; i >= 0; i--){
                auxStr = String.valueOf(binario.charAt(i)); //Como a função charAt() retorna um char, é necessário convertê-lo para String para que seja possível realizar o cálculo

                /* Cálculo para converter o valor binário para decimal.
                 * Nesse momento, estamos pegando o caractere no último indice, convertendo para double e multiplicando por 2 ^ x,
                 * onde x é a posição desse caractere no valor binário digitado anteriormente
                 */
                decimal = (int) ((int) decimal + (pow(2, aux) * Double.parseDouble(auxStr)));
                aux++; //Como mudou a posição, incrementamos auxiliar para realizar a próxima potência.
            }

            System.out.println("\nDecimal: " + decimal);
            System.out.println("Binario: " + binario);
            System.out.println("Hexadecimal: " + convHexa(decimal) + "\n");
        }
    }
    
    public static void deciBin(){
        String hexadecimal, auxStr;
        int decimal = 0, aux = 0;
        boolean valido = true;
        
        ler.nextLine();
        System.out.print("\nDigite um valor em hexadecimal: ");
        hexadecimal = ler.nextLine(); //Armazena um valor digitado no formato hexadecimal
        
        hexadecimal = hexadecimal.toUpperCase(); //Passa os caracteres para maiúsculos
        
        for(int i = 0; i < hexadecimal.length(); i++){ //Loop para verificar se é um valor hexadecimal válido
            
            //Se o valor não estiver entre 0 e 9 (usando tabela ASCII) e não for nem A, B, C, D, E ou F
            if((hexadecimal.charAt(i) < 48 || hexadecimal.charAt(i) > 57) &&
                    (hexadecimal.charAt(i) != 'A' && hexadecimal.charAt(i) != 'B' && hexadecimal.charAt(i) != 'C' && hexadecimal.charAt(i) != 'D' && hexadecimal.charAt(i) != 'E' && hexadecimal.charAt(i) != 'F')){
                System.out.println("\nValor inválido!\nDigite apenas um valores formados de 0 até F\n");
                valido = false; //A entrada não é válida
            }
        }
        
        if(valido){ //Se a entrada estiver válida, continua com o resto dos cálculos
            for(int i = hexadecimal.length() - 1; i >= 0; i--){ //Loop para percorrer cada caractere da String individualmente
                auxStr = String.valueOf(hexadecimal.charAt(i)); //Convertendo o char atual para String

                switch(auxStr){ //Verificando qual caractere foi encontrado e, dependendo disso, o valor que irá multiplicar o resultado de 16^x será alterado
                    case "A":
                        decimal = (int) ((int) decimal + (pow(16, aux) * 10)); //10 caso seja A
                        break;

                    case "B":
                        decimal = (int) ((int) decimal + (pow(16, aux) * 11)); //11 caso seja B
                        break;

                    case "C":
                        decimal = (int) ((int) decimal + (pow(16, aux) * 12)); //12 caso seja C
                        break;

                    case "D":
                        decimal = (int) ((int) decimal + (pow(16, aux) * 13)); //13 caso seja D
                        break;

                    case "E":
                        decimal = (int) ((int) decimal + (pow(16, aux) * 14)); //14 caso seja E
                        break;

                    case "F":
                        decimal = (int) ((int) decimal + (pow(16, aux) * 15)); //15 caso seja F
                        break;

                    default: //Se não for nenhum desses caracteres, devemos fazer o mesmo processo que foi feito para binário
                        decimal = (int) ((int) decimal + (pow(16, aux) * Double.parseDouble(auxStr)));
                        break;
                }
                aux++; //Como mudou a posição, incrementamos auxiliar para realizar a próxima potência.
            }

            //Mostrando os resultados
            System.out.println("\nDecimal: " + decimal);
            System.out.println("Binario: " + convBinario(decimal));
            System.out.println("Hexadecimal: " + hexadecimal + "\n");
        }
    }
    
    public static String convBinario(int valor){ //Converter um valor decimal para binário
        String binario = "", binAux = "";
        int res;
        //Convertendo um número decimal para Binário
        while(valor >= 1){ //Enquanto o valor final for maior ou igual a 1
            res = valor % 2; //Pega o resto da divisão por 2
            valor = valor / 2; //Pega o resultado dessa mesma divisão
            
            binario = binario + String.valueOf(res); //Armazena o resto (que será sempre 0 ou 1) na String "binario".
        }
        
        /* Como devemos inverter a ordem dos restos e do resultado final para conseguirmos a setença binária correta,
         * temos um loop que irá percorrer a String "binario" final de traz para frente e ir armazenando isso em "binAux".
         * Com isso, temos a ordem inversa dos restos obtidos pelas divisões anteriores e, consequentemente, a sequência binária correta.
         */
        for(int i = binario.length() - 1; i >= 0; i--){
            binAux = binAux + String.valueOf(binario.charAt(i));
        }
        
        return binAux;
    }
    
    public static String convHexa(int valor){ //Converter um valor decimal para hexadecimal
        int res;
        String hexadecimal = "", hexaAux = "";
        while(valor >= 1){ //Enquanto o valor final for maior ou igual a 1
            res = valor % 16; //Pega o resto da divisão por 16
            valor = valor / 16; //Pega o resultado dessa mesma divisão
            
            //Sequência de if - else para verificar todas as possibilidades de resto que devem se tornar alguma letra de A até F
            if(res == 10){
                hexadecimal = hexadecimal + "A";
            }
            else{
                if(res == 11){
                    hexadecimal = hexadecimal + "B";
                }
                else{
                    if(res == 12){
                        hexadecimal = hexadecimal + "C";
                    }
                    else{
                        if(res == 13){
                            hexadecimal = hexadecimal + "D";
                        }
                        else{
                            if(res == 14){
                                hexadecimal = hexadecimal + "E";
                            }
                            else{
                                if(res == 15){
                                    hexadecimal = hexadecimal + "F";
                                }
                                else{ //Se o resto for menor que 10 e maior que 15, adicionamos o resultado normalmente à String "hexadecimal"
                                    hexadecimal = hexadecimal + String.valueOf(res);
                                }
                            }
                        }
                    }
                }
            }
        }
            
        /* Como devemos inverter a ordem dos restos e do resultado final para conseguirmos a setença hexadecimal correta,
         * temos um loop que irá percorrer a String "hexadecimal" final de traz para frente e ir armazenando isso em "hexaAux".
         * Com isso, temos a ordem inversa dos restos obtidos pelas divisões anteriores e, consequentemente, a sequência hexadecimal correta.
         */
        for(int i = hexadecimal.length() - 1; i >= 0; i--){
            hexaAux = hexaAux + hexadecimal.charAt(i);
        }

        return hexaAux;
    }
    
    public static void main(String[] args) {
        int opcao;
        
        do{
            System.out.println("Selecione o módulo desejado: ");
            System.out.println("1 - Criptografia");
            System.out.println("2 - Conversão");
            
            opcao = ler.nextInt();
            
            switch(opcao){
                case 1:
                    criptografia();
                    break;
                case 2:
                    conversao();
                    break;
                case 0:
                    System.out.println("\nPrograma finalizado!\n");
                    break;
                default:
                    System.out.println("\nOpção inválida!\nDigite novamente!\n");
                    break;
            }
            
        }while(opcao != 0);
    }
}