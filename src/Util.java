import java.text.DecimalFormat;

import static javax.swing.JOptionPane.*;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.lang.Double.parseDouble;

public class Util {
    private Bilhete[] bilhete = new Bilhete[3];
    private int index = 0;
    public void menuPrincipal(){
        int opcao;
        String menu = "1. Administrador\n2. Usuário\n3. Finalizar\n";
        do{
            opcao = parseInt(showInputDialog(menu));
            if (opcao < 1 || opcao > 3){
                showMessageDialog(null, "Opção inválida");
            }
            else {
                switch (opcao){
                    case 1:
                        menuAdministrador();
                        break;
                    case 2:
                        menuUsuario();
                        break;
                }
            }
        }while (opcao !=3);
    }

    // menu com as funcionalidades do adm
    private void menuAdministrador(){
        int opcao;
        String menu = "1. Emitir bilhete\n2. Listar bilhetes\n3. Remover\n4. Sair";
        do {
            opcao = parseInt(showInputDialog(menu));
            if (opcao < 1 || opcao > 4){
                showMessageDialog(null, "Opção inválida");
            }
            else {
                switch (opcao){
                    case 1:
                        emitirB();
                        break;
                    case 2:
                        listarB();
                        break;
                    case 3:
                        removerB();
                }
            }
        }while (opcao != 4);
    }

    // método para remover um bilhete a partir de um cpf
    private void removerB() {
        int resposta;
        int posicao = pesquisar();
        if(posicao != -1){
            resposta = showConfirmDialog(null, "Tem certeza que deseja remover o bilhete? :(");
            if (resposta == YES_OPTION){
                index--;
                bilhete[posicao] = bilhete[index];
            }
        }
    }

    // método para emitir um bilhete --> gerar um objeto e armazenar no vetor
    private void emitirB() {
        String nome, perfil;
        long cpf;

        if (index < bilhete.length){
            nome = showInputDialog("Nome");
            cpf = parseLong(showInputDialog("CPF"));
            perfil = showInputDialog("Tipo de tarifa (perfil) --> Professor ou Estudante ou Comum");
            bilhete[index] = new Bilhete(new Usuario(cpf, nome, perfil));
            index++;
        }
        else{
            showMessageDialog(null, "Procure  a administração");
        }

    }

    // método para listar os dados do bilhete
    private void listarB() {
        DecimalFormat df = new DecimalFormat("0.00");
        String aux = "";
        for (int i = 0; i < index; i++){
            aux += "Nome do usuário: " + bilhete[i].usuario.nome + "\n";
            aux += "CPF: " + bilhete[i].usuario.cpf + "\n";
            aux += "Número do bilhete: " + bilhete[i].num + "\n";
            aux += "Saldo: R$" + df.format(bilhete[i].saldo) + "\n";
            aux += "Perfil (tipo de tarifa): " + bilhete[i].usuario.perfil + "\n";
            aux += "--------------------------------------------\n";
        }
        showMessageDialog(null, aux);
    }
    
    //private void removerB() {}

    // método com as funcionalidades do usúario do bilhete
    private void menuUsuario() {
        int opcao;
        String menu = "1. consultar saldo\n2. Carregar bilhete\n3. Passar na catraca\n4. Sair";
        do {
            opcao = parseInt(showInputDialog(menu));
           if (opcao < 1 || opcao > 4){
               showMessageDialog(null, "Opção inválida");
           }
           else {
               switch (opcao){
                   case 1:
                       consultarSaldo();
                       break;
                   case 2: 
                       carregarB();
                       break;
                   case 3:
                       passarNaCatraca();
                       break;
               }
           }
        }while (opcao != 4);
    }
    
    // método carregar o bilhete
    private void carregarB() {
        double valor;
        int posicao = pesquisar();
        if (posicao != -1){
            valor = parseDouble(showInputDialog("Valor para carregar o bilhete"));
            bilhete[posicao].carregar(valor);
        }
    }

    // método para consultar o saldo
    private void consultarSaldo() {
        int posicao = pesquisar();
        if (posicao != -1){
            showMessageDialog(null, "Saldo = R$ " + bilhete[posicao].consultarSaldo());
        }
    }
    
    // método para passar na catraca --> simulação
    private void passarNaCatraca() {
        int posicao = pesquisar();
        if (posicao != -1){
            showMessageDialog(null, bilhete[posicao].passarNaCatraca());
        }
    }
    
    // método auxiliar para os outros métodos da aplicação
    private int pesquisar(){
        long cpf = parseLong(showInputDialog("CPF"));
        for (int i = 0; i < index; i++){
            if (bilhete[i].usuario.cpf ==  cpf){
                return i;
            }
        }
        showMessageDialog(null, cpf + "não encontrado");
        return -1;
    }
}
