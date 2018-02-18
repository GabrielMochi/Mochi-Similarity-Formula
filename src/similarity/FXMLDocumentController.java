package similarity;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author Gabriel Mochi Ribeiro <gmochi56@icloud.com>
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private JFXTextField wordsTextField;
    @FXML
    private Label similarityResultLabel;
    
    @FXML
    private void handleCalculateButtonAction(ActionEvent event) {
        int totalCharacters = 0;
        int similiarCharacters = 0;
        List<String> words = Arrays.asList(wordsTextField.getText().split(","));

        if (words.size() >= 2) {
            words.replaceAll((word) -> { return word.trim(); });

            totalCharacters = words.stream().map((word) -> word.length()).reduce(totalCharacters, Integer::sum);

            for (int i = 0; i < words.size() - 1; i++) {
                String actualWord = words.get(i);

                for (int j = i + 1; j < words.size(); j++) {
                    String nextWord = words.get(j);

                    for (int k = 0; k < Math.min(actualWord.length(), nextWord.length()); k++) {
                        if (actualWord.charAt(k) == nextWord.charAt(k)) {
                            similiarCharacters++;
                        }
                    }
                }
            }

            similarityResultLabel.setText("Similaridade: " + new DecimalFormat("#.00").format(Math.abs((2 * (double) similiarCharacters) / ((words.size() - 1) * (double) totalCharacters)) * 100) + "%");
        } else {
            similarityResultLabel.setText(Character.toString((char) 0x26A0) + " Insira duas ou mais palavras.");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
}
