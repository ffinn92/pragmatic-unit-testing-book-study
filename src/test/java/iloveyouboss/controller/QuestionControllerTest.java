package iloveyouboss.controller;

import static org.assertj.core.api.Assertions.*;

import java.time.*;
import java.util.*;
import java.util.stream.*;
import iloveyouboss.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class QuestionControllerTest {

    private QuestionController controller;
    @BeforeEach
    public void create() {
        controller = new QuestionController();
        controller.deleteAll();
    }

    @Test
    public void findsPersistedQuestionById() {
        int id = controller.addBooleanQuestion("question text");

        Question question = controller.find(id);

        assertThat(question.getText()).isEqualTo("question text");
    }

    @Test
    public void questionAnswersDateAdded() {
        Instant now = new Date().toInstant();
        controller.setClock(Clock.fixed(now, ZoneId.of("America/Denver")));
        int id = controller.addBooleanQuestion("text");

        Question question = controller.find(id);

        assertThat(question.getCreateTimestamp()).isEqualTo(now);
    }

    @Test
    public void answersMultiplePersistedQuestions() {
        controller.addBooleanQuestion("q1");
        controller.addBooleanQuestion("q2");
        controller.addPercentileQuestion("q3", new String[] { "a1", "a2"});

        List<Question> questions = controller.getAll();

        assertThat(
                questions.stream().map(Question::getText).collect(Collectors.toList())).
                isEqualTo(Arrays.asList("q1", "q2", "q3"));
    }

    @Test
    public void findsMatchingEntries() {
        controller.addBooleanQuestion("alpha 1");
        controller.addBooleanQuestion("alpha 2");
        controller.addBooleanQuestion("beta 1");

        List<Question> questions = controller.findWithMatchingText("alpha");

        assertThat(
                questions.stream().map(Question::getText).collect(Collectors.toList())).
                isEqualTo(Arrays.asList("alpha 1", "alpha 2"));
    }
}
