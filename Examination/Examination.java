package examination;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class Examination extends JFrame implements ActionListener {
    private List<Question> questions;
    private int currentQuestionIndex;
    private Timer timer;
    private int timeRemaining;

    private JLabel questionLabel;
    private JRadioButton[] answerOptions;
    private JButton nextButton;
    private JLabel timerLabel;
    
    public Examination() {
        setTitle("MCQ Quiz Program");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(400, 300);
        setLayout(new FlowLayout());

        questions = fetchQuestionsFromDatabase();

        currentQuestionIndex = 0;
        timeRemaining = 60; 

        questionLabel = new JLabel(questions.get(currentQuestionIndex).getQuestionText());
        questionLabel.setPreferredSize(new Dimension(350, 50));
        add(questionLabel);

        ButtonGroup answerGroup = new ButtonGroup();
        answerOptions = new JRadioButton[questions.get(currentQuestionIndex).getAnswerOptions().size()];
        for (int i = 0; i < answerOptions.length; i++) {
            answerOptions[i] = new JRadioButton(questions.get(currentQuestionIndex).getAnswerOptions().get(i));
            answerGroup.add(answerOptions[i]);
            add(answerOptions[i]);
        }

        nextButton = new JButton("Next");
        nextButton.addActionListener(this);
        add(nextButton);

        timerLabel = new JLabel("Time Remaining: " + timeRemaining + " seconds");
        timerLabel.setPreferredSize(new Dimension(350, 30));
        add(timerLabel);

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeRemaining--;
                timerLabel.setText("Time Remaining: " + timeRemaining + " seconds");

                if (timeRemaining <= 0) {
                    timer.stop();
                    submitQuiz();
                }
            }
        });

        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nextButton) {
            if (currentQuestionIndex < questions.size() - 1) {
                currentQuestionIndex++;

                questionLabel.setText(questions.get(currentQuestionIndex).getQuestionText());

                List<String> answerOptionsList = questions.get(currentQuestionIndex).getAnswerOptions();
                for (int i = 0; i < answerOptionsList.size(); i++) {
                    answerOptions[i].setText(answerOptionsList.get(i));
                    answerOptions[i].setSelected(false);
                }

                if (currentQuestionIndex == questions.size() - 1) {
                    nextButton.setEnabled(false);
                }
            } else {
                submitQuiz();
            }
        }
    }

    private void submitQuiz() {
        JOptionPane.showMessageDialog(this, "Your test submitted successfully! You have been logged out...");
        System.exit(0);
}


    private List<Question> fetchQuestionsFromDatabase() {
        List<Question> questions = new ArrayList<>();

    String url = "jdbc:mysql://localhost:3306/examination";
    String username = "root";
    String password = "";

    try (Connection connection = DriverManager.getConnection(url, username, password)) {
        String query = "SELECT question_text, option_text FROM questions " +
                "JOIN answer_options ON questions.question_id = answer_options.question_id";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            String currentQuestion = null;
            List<String> answerOptions = new ArrayList<>();

            while (resultSet.next()) {
                String questionText = resultSet.getString("question_text");
                String optionText = resultSet.getString("option_text");

                if (currentQuestion == null || !currentQuestion.equals(questionText)) {
                    if (currentQuestion != null) {
                        questions.add(new Question(currentQuestion, answerOptions));
                        answerOptions = new ArrayList<>();
                    }

                    currentQuestion = questionText;
                }

                answerOptions.add(optionText);
            }

            if (currentQuestion != null) {
                questions.add(new Question(currentQuestion, answerOptions));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return questions;
    }

    private static class Question {
        private final String questionText;
        private final List<String> answerOptions;

        public Question(String questionText, List<String> answerOptions) {
            this.questionText = questionText;
            this.answerOptions = answerOptions;
        }

        public String getQuestionText() {
            return questionText;
        }

        public List<String> getAnswerOptions() {
            return answerOptions;
        }

        private int getCorrectAnswerIndex(int questionId) {
    String url = "jdbc:mysql://localhost:3306/examination";
    String username = "root";
    String password = "";
    int correctAnswerIndex = -1;

    try (Connection connection = DriverManager.getConnection(url, username, password)) {
        // Prepare the SQL query to fetch the correct answer index
        String query = "SELECT correct_answer_index FROM questions WHERE question_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            // Set the parameter value for the SQL query
            statement.setInt(1, questionId);

            // Execute the SQL query to fetch the correct answer index
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    correctAnswerIndex = resultSet.getInt("correct_answer_index");
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return correctAnswerIndex;
}

    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Examination().setVisible(true);
            }
        });
    }
}
 