import java.io.FileInputStream;
import java.io.InputStream;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.util.Span;

public class SentenceDetectionME {

    public static void runModel(String sen) throws Exception {
        System.out.println("Input string:\n"+sen+"\n\nDetected Sentences & Probabilities:");

        //Loading a sentence model
        InputStream inputStream = new FileInputStream("models/en-sent.bin");
        SentenceModel model = new SentenceModel(inputStream);

        //Instantiating the SentenceDetectorME class
        SentenceDetectorME detector = new SentenceDetectorME(model);

        //Detecting the position of the sentences in the paragraph
        Span[] spans = detector.sentPosDetect(sen);
        //Get the probabilities of the last decoded sequence
        double[] probs = detector.getSentenceProbabilities();

        //Printing the sentences and their spans of a paragraph, with sentDetect probabilities
        int i=0;
        for (Span span : spans) {
            System.out.print(sen.substring(span.getStart(), span.getEnd()) + " " + span + " ");
            System.out.print(String.format("%.2f",probs[i]*100) + "%");
            System.out.println("");
            i++;
        }
    }

    public static void main(String args[]) throws Exception{

        String sen1 = "Hi. How are you? Welcome to Tutorialspoint."
                + " We provide free tutorials on various technologies";
        String sen2 = "The grass is wet... it must've rained today.  wow, I can't believe it finally rained! you can't? no...? ok";

        runModel(sen2);

    }
}