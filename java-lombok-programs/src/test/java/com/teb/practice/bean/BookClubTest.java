package com.teb.practice.bean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static java.util.Arrays.stream;

import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.extern.java.Log;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

// @Log invokes an instance of Java Logger class
@Log
class BookClubTest {

    private static final String LOG_MESSAGE = "Welcome to the book club!";
    private static final String BOOK_ID_ONE = "B512";
    private static final String BOOK_NAME_ONE = "Wide Awake";
    private static final String BOOK_NAME_TWO = "Dreams Made of Nightmares";
    private static final String AUTHOR_ID_ONE = "A16";
    private static final String AUTHOR_NAME_ONE = "Joe Williamson";
    private static final String AUTHOR_NAME_TWO = "Lionel Owen";
    private static final String VOLUNTEER_NAME = "Chris Johansson";
    private static final String BOOK_CLUB_NAME = "Read Dreams";
    private static final String BOOK_CLUB_ADDRESS = "Edinburgh, Scotland";

    @Test
    void testSeries() {

        Book book = createBook(BOOK_ID_ONE, BOOK_NAME_ONE, AUTHOR_ID_ONE, AUTHOR_NAME_ONE);

        Series series = new Series(book, 1);
        Series updatedSeries = series.withEdition(2);

        assertEquals(1, series.getEdition());

        assertEquals(2, updatedSeries.getEdition());
        assertEquals(BOOK_ID_ONE, updatedSeries.getBook().getBookId());
        assertEquals(BOOK_NAME_ONE, updatedSeries.getBook().getBookName());
        assertEquals(AUTHOR_NAME_ONE, updatedSeries.getBook().getAuthor().getAuthorName());
    }

    @Test
    void testBookClub() {

        List<Book> books =
                List.of(
                        createBook(BOOK_ID_ONE, BOOK_NAME_ONE, AUTHOR_ID_ONE, AUTHOR_NAME_ONE),
                        createBook("B768", BOOK_NAME_TWO, "A64", AUTHOR_NAME_TWO));
        List<Volunteer> volunteers =
                List.of(
                        createVolunteer("Mark Downey", 53, "Iron Man, The Hulk"),
                        createVolunteer(VOLUNTEER_NAME, 36, "Captain America, Black Widow, Thor"));

        BookClub club = new BookClub(BOOK_CLUB_NAME, BOOK_CLUB_ADDRESS, books, volunteers);

        assertEquals(BOOK_CLUB_NAME, club.getBookClubName());
        assertEquals(BOOK_CLUB_ADDRESS, club.getBookClubAddress());

        assertEquals(BOOK_NAME_ONE, club.getBooks().getFirst().getBookName());
        assertEquals(BOOK_NAME_TWO, club.getBooks().getLast().getBookName());
        assertEquals(AUTHOR_NAME_TWO, club.getBooks().getLast().getAuthor().getAuthorName());

        assertEquals(VOLUNTEER_NAME, club.getVolunteers().getLast().getVolunteerName());
        assertEquals(
                List.of("Iron Man", "The Hulk"),
                club.getVolunteers().getFirst().getWorkExperiences());
    }

    @Test
    void testLogAndCleanupHandler() {

        Logger logger = getLombokLogger();
        List<LogRecord> logRecords = new ArrayList<>();
        final boolean[] closedFlag = {false};

        {
            // @Cleanup invokes the close method for relevant instance objects
            @Cleanup
            Handler handler =
                    new Handler() {
                        @Override
                        public void publish(LogRecord logRecord) {
                            logRecords.add(logRecord);
                        }

                        @Override
                        public void flush() {}

                        @Override
                        public void close() {
                            closedFlag[0] = true;
                            logger.removeHandler(this);
                        }
                    };

            logger.addHandler(handler);

            log.info(LOG_MESSAGE);

            assertFalse(closedFlag[0]);

            assertFalse(logRecords.isEmpty());
            assertEquals(LOG_MESSAGE, logRecords.getFirst().getMessage());
        }

        assertTrue(closedFlag[0]);
    }

    private Author createAuthor(String authorId, String authorName) {

        Author author = new Author(authorId);
        author.setAuthorName(authorName);

        return author;
    }

    private Book createBook(String bookId, String bookName, String authorId, String authorName) {

        return new Book(bookId, bookName, createAuthor(authorId, authorName));
    }

    private Volunteer createVolunteer(String name, int age, String... workExperiences) {

        Volunteer.VolunteerBuilder builder =
                Volunteer.builder().volunteerName(name).volunteerAge(age);

        stream(workExperiences)
                .flatMap(x -> stream(x.split(",")))
                .map(String::trim)
                .forEach(builder::workExperience);

        return builder.build();
    }

    @SneakyThrows
    private Logger getLombokLogger() {

        Field logField = BookClubTest.class.getDeclaredField("log");
        logField.setAccessible(true);

        return (Logger) logField.get(null);
    }
}
