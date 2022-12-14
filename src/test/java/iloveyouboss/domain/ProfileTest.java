package iloveyouboss.domain;

import iloveyouboss.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ProfileTest {
    private Profile profile;
    private BooleanQuestion question;
    private Criteria criteria;

    @BeforeEach
    public void create() {
        profile = new Profile("Bull Hockey, Inc.");
//        question = new BooleanQuestion(1, "Got bonuses?");
        criteria = new Criteria();
    }

    @Test
    public void matches() {
        Profile profile = new Profile("Bull Hockey, Inc.");
//        Question question = new BooleanQuestion(1, "Got milk?");

        // must-match 항목이 맞지 않으면 false
        profile.add(new Answer(question, Bool.FALSE));
        Criteria criteria = new Criteria();
        criteria.add(
                new Criterion(new Answer(question, Bool.TRUE), Weight.MustMatch));

        assertThat(profile.matches(criteria)).isFalse();

        // don't care 항목에 대해서는 true
        profile.add(new Answer(question, Bool.FALSE));
        criteria = new Criteria();
        criteria.add(
                new Criterion(new Answer(question, Bool.TRUE), Weight.DontCare));

        assertThat(profile.matches(criteria)).isTrue();
    }

    @Test
    public void matchAnswersFalseWhenMustMatchCriteriaNotMet() {
        profile.add(new Answer(question, Bool.FALSE));
        criteria.add(new Criterion(new Answer(question, Bool.TRUE), Weight.MustMatch));

        boolean matches = profile.matches(criteria);

        assertThat(matches).isFalse();
    }

    @Test
    public void matchAnswersTrueForAnyDontCareCriteria() {
        profile.add(new Answer(question, Bool.FALSE));
        criteria.add(new Criterion(new Answer(question, Bool.TRUE), Weight.DontCare));

        boolean matches = profile.matches(criteria);

        assertThat(matches).isTrue();
    }
}