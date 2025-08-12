import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ToDoApp {
    private JFrame frame;
    private JPanel panel;
    private JTextField taskInput;
    private JButton addButton;
    private JButton deleteButton;
    private JList<String> taskList;
    private DefaultListModel<String> listModel;
    private ArrayList<String> tasks;

    public ToDoApp() {
        //Initializing components
        frame = new JFrame("To-Do List App");
        panel = new JPanel();
        taskInput = new JTextField(20);
        addButton = new JButton("Add");
        deleteButton = new JButton("Delete");
        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);
        tasks = new ArrayList<>();

        //Setting up panel layout
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //Creating input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        inputPanel.add(taskInput);
        inputPanel.add(addButton);
        inputPanel.add(deleteButton);

        //Adding components to main panel
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(taskList), BorderLayout.CENTER);

        //Adding action listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteTask();
            }
        });

        //Setting up frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    //adding Method to add the new Task
    private void addTask() {
        String task = taskInput.getText().trim();
        if (!task.isEmpty()) {
            tasks.add(task);
            listModel.addElement(task);
            taskInput.setText("");
        } else {
            JOptionPane.showMessageDialog(frame, "Please enter a task", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Adding Method to delete the task
    private void deleteTask() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex != -1) {
            listModel.remove(selectedIndex);
            tasks.remove(selectedIndex);
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a task to delete", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ToDoApp();
            }
        });
    }
}