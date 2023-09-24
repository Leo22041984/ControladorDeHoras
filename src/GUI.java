import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    private ControladorDeHoras controlador;
    private JTextField nomeTarefaField;
    private JButton iniciarButton;
    private JButton finalizarButton;
    private JButton gerarRelatorioButton;
    private JTextArea relatorioArea;

    private Tarefa tarefaAtual;

    public GUI(ControladorDeHoras controlador) {
        this.controlador = controlador;
        this.tarefaAtual = null;

        setTitle("Controlador de Horas");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel labelNomeTarefa = new JLabel("Nome da Tarefa:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(labelNomeTarefa, constraints);

        nomeTarefaField = new JTextField();
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        panel.add(nomeTarefaField, constraints);

        iniciarButton = new JButton("Iniciar Tarefa");
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        panel.add(iniciarButton, constraints);

        finalizarButton = new JButton("Finalizar Tarefa");
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        panel.add(finalizarButton, constraints);

        gerarRelatorioButton = new JButton("Gerar Relatório");
        constraints.gridx = 2;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        panel.add(gerarRelatorioButton, constraints);

        relatorioArea = new JTextArea();
        relatorioArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(relatorioArea);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 3;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.BOTH;
        panel.add(scrollPane, constraints);

        add(panel);

        iniciarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                iniciarTarefa();
            }
        });

        finalizarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                finalizarTarefa();
            }
        });

        gerarRelatorioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gerarRelatorio();
            }
        });
    }

    private void iniciarTarefa() {
        if (tarefaAtual != null) {
            JOptionPane.showMessageDialog(this, "Uma tarefa já está em andamento. Finalize-a antes de iniciar outra.");
            return;
        }

        String nomeTarefa = nomeTarefaField.getText();
        if (nomeTarefa.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite o nome da tarefa.");
            return;
        }

        tarefaAtual = new Tarefa(nomeTarefa);
        tarefaAtual.iniciarTarefa();
        relatorioArea.setText("Tarefa em andamento: " + tarefaAtual.getNome());
        iniciarButton.setEnabled(false);
        finalizarButton.setEnabled(true);
    }

    private void finalizarTarefa() {
        if (tarefaAtual == null) {
            JOptionPane.showMessageDialog(this, "Nenhuma tarefa em andamento.");
            return;
        }

        tarefaAtual.finalizarTarefa();
        controlador.adicionarTarefa(tarefaAtual);
        relatorioArea.append("\n" + tarefaAtual + " - Tempo gasto: " + tarefaAtual.calcularDuracao().toMinutes() + " minutos.");
        tarefaAtual = null;
        iniciarButton.setEnabled(true);
        finalizarButton.setEnabled(false);
    }

    private void gerarRelatorio() {
        String relatorio = controlador.gerarRelatorio();
        JOptionPane.showMessageDialog(this, "Relatório de Tarefas:\n" + relatorio);
    }
}
