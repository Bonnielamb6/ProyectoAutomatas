import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Ventana extends JFrame {

    JTextArea textArea;


    public Ventana() {
        setSize(800, 800);
        setLocationRelativeTo(null);
        setTitle("Proyecto automatas");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();

        setVisible(true);
        setResizable(false);

    }

    private void initComponents() {
        setLayout(null);
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Archivo");
        JMenuItem fileMenuSave = new JMenuItem("Guardar");
        JMenuItem fileMenuLoad = new JMenuItem("Cargar");

        fileMenu.add(fileMenuSave);
        fileMenu.add(fileMenuLoad);

        menuBar.add(fileMenu);

        setJMenuBar(menuBar);
        fileMenuSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarArchivo();
            }
        });

        fileMenuLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarArchivo();
            }
        });

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);


        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(50, 50, 700, 600);


        JButton button = new JButton("Evaluar");
        button.setBounds(350, 700, 100, 30);

        // Añadir el JScrollPane en el centro y el botón en el sur del BorderLayout
        add(scrollPane);
        add(button);
    }

    private void cargarArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                textArea.read(reader, null);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al cargar el archivo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void guardarArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showSaveDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(textArea.getText());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar el archivo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    public static void main(String[] args) {
        Ventana ventana = new Ventana();
    }
}
