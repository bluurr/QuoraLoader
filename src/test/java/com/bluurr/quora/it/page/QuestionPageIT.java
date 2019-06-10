package com.bluurr.quora.it.page;

import com.bluurr.quora.domain.*;
import com.bluurr.quora.it.BaseIntegrationTest;
import com.bluurr.quora.page.LoginPage;
import com.bluurr.quora.page.question.QuestionPage;
import com.bluurr.quora.page.search.SearchPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Every.everyItem;

/**
 * 
 * @author Bluurr
 *
 */
class QuestionPageIT extends BaseIntegrationTest {
	/**
	 * Likely to always return topics.
	 */
	private static final String SEARCH_TERM = "Java";

	@Resource
	private LoginCredential credential;
	
	private QuestionSummary summary;
	
	@BeforeEach
	void before() {
		SearchPage page = LoginPage.open(QUORA_HOST).login(credential).search(SEARCH_TERM);
		summary = page.getQuestions(1).get(0);
	}

	@Test
	void loadQuestion() {
		QuestionPage page = QuestionPage.open(summary.getLocation());
		assertThat(page, notNullValue());
		Question question = page.getQuestion(Answers.limit(5));
		assertThat(question, notNullValue());
		assertThat(question.getAnswers(), notNullValue());
		assertThat(question.getAnswers(), everyItem(notNullValue(Answer.class)));
	}
}
