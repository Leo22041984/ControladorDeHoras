import java.time.Duration;
import java.time.LocalDateTime;

public class Tarefa {
    private String nome;
    private LocalDateTime inicio;
    private LocalDateTime fim;

    public Tarefa(String nome) {
        this.nome = nome;
    }

    public void iniciarTarefa() {
        inicio = LocalDateTime.now();
    }

    public void finalizarTarefa() {
        fim = LocalDateTime.now();
    }

    public Duration calcularDuracao() {
        if (inicio != null && fim != null) {
            return Duration.between(inicio, fim);
        }
        return Duration.ZERO;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return nome + " - Início: " + inicio + ", Fim: " + fim;
    }
}

