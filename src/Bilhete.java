import java.util.Random;

public class Bilhete {
   static final double tarifaBase = 5.20;
    double saldo;
    long num;
    Usuario usuario;

    public Bilhete(Usuario usuario){
        this.num = gerarNum();
        this.usuario = usuario;
    }

    private long gerarNum(){
        Random rd = new Random();
        return rd.nextLong(1000, 10000);
    }

    // método para carregar bilhete
    public void carregar(double valor){
        saldo += valor;
    }

    // método para consultar o saldo
    public double consultarSaldo(){
        return saldo;
    }

    // método para passar na catraca
    public String passarNaCatraca(){
        double debito = tarifaBase / 2;
        if(usuario.perfil.equalsIgnoreCase("comum")){
            debito = tarifaBase;
        }
        if (saldo >= debito){
            saldo -= debito;
            return "Ta liberado";
        }
        return "Ta liso dorme";
    }
}
