import java.util.ArrayList;
import java.util.List;

public class ControladorDeHoras {
    private List<Tarefa> tarefas;

    public ControladorDeHoras() {
        tarefas = new ArrayList<>();
    }

    public void adicionarTarefa(Tarefa tarefa) {
        tarefas.add(tarefa);
    }

    public List<Tarefa> obterTarefas() {
        return tarefas;
    }

    public String gerarRelatorio() {
        StringBuilder relatorio = new StringBuilder();
        for (Tarefa tarefa : tarefas) {
            relatorio.append(tarefa.toString()).append(" - Tempo gasto: ").append(tarefa.calcularDuracao().toMinutes()).append(" minutos\n");
        }
        return relatorio.toString();
    }
}

