import java.awt.*;
import java.util.*;
import javax.swing.*;

public class ChatBotGUI {

    private static Map<String, String> faq = new HashMap<>();

    static {
        faq.put("hello", "Hello! How can I help you?");
        faq.put("hi", "Hi there! Ask me anything.");
        faq.put("how are you", "I'm just a bot, but I'm doing great!");
        faq.put("what is ai", "Artificial Intelligence is the simulation of human intelligence in machines.");
        faq.put("what is java", "Java is a high-level, object-oriented programming language.");
        faq.put("bye", "Goodbye! Have a great day 😊");
    }

    private static String preprocess(String input) {
        return input.toLowerCase()
                .replaceAll("[^a-z ]", "")
                .trim();
    }

    private static String getResponse(String input) {
        input = preprocess(input);
        for (String key : faq.keySet()) {
            if (input.contains(key)) {
                return faq.get(key);
            }
        }
        return "Sorry, I didn't understand that.";
    }

    private static JPanel createBubble(String text, Color bg, int align) {
        JLabel label = new JLabel("<html><p style='width:200px'>" + text + "</p></html>");
        label.setOpaque(true);
        label.setBackground(bg);
        label.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JPanel panel = new JPanel(new FlowLayout(align));
        panel.setBackground(new Color(245, 245, 245));
        panel.add(label);
        return panel;
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("AI Chatbot");
        frame.setSize(420, 550);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Header
        JLabel header = new JLabel("🤖 AI Chatbot", SwingConstants.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 18));
        header.setOpaque(true);
        header.setBackground(new Color(98, 0, 238));
        header.setForeground(Color.WHITE);
        header.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(header, BorderLayout.NORTH);

        // Chat area
        JPanel chatPanel = new JPanel();
        chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.Y_AXIS));
        chatPanel.setBackground(new Color(245, 245, 245));

        JScrollPane scrollPane = new JScrollPane(chatPanel);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Input area
        JTextField inputField = new JTextField();
        JButton sendButton = new JButton("Send");
        sendButton.setBackground(new Color(98, 0, 238));
        sendButton.setForeground(Color.WHITE);

        JPanel inputPanel = new JPanel(new BorderLayout(8, 8));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        frame.add(inputPanel, BorderLayout.SOUTH);

        // Welcome message
        chatPanel.add(createBubble("Hello! I’m your AI chatbot 🤖", new Color(230, 230, 250), FlowLayout.LEFT));

        sendButton.addActionListener(e -> {
            String userText = inputField.getText();
            if (userText.isEmpty()) return;

            chatPanel.add(createBubble(userText, new Color(187, 222, 251), FlowLayout.RIGHT));
            chatPanel.add(createBubble(getResponse(userText), new Color(230, 230, 250), FlowLayout.LEFT));

            inputField.setText("");
            chatPanel.revalidate();
            scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
        });

        inputField.addActionListener(e -> sendButton.doClick());

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
