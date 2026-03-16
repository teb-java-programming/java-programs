import static java.lang.System.in;
import static java.lang.System.out;

import com.teb.practice.bean.Author;
import com.teb.practice.bean.Book;
import com.teb.practice.bean.BookClub;
import com.teb.practice.bean.Series;
import com.teb.practice.bean.Volunteer;

import lombok.Cleanup;
import lombok.extern.java.Log;

import java.util.Scanner;

// @Log invokes an instance of Java Logger class
@Log
public class App {

    public static void main(String[] args) {

        // @Cleanup invokes the close method for relevant instance objects
        @Cleanup Scanner scan = new Scanner(in);

        Author author = new Author("A16");
        author.setAuthorName("Joe Williamson");

        Book book = new Book("H1024", "Dreams Made of Nightmares", author);

        Volunteer volunteer =
                Volunteer.builder()
                        .volunteerName("Mark Downey")
                        .age(24)
                        .workExperience("Librarian")
                        .build();

        Series series = new Series(new Book("H512", "Wide Awake", author), 1);
        Series updatedSeries = series.withEdition(2);

        out.print("Enter the name of your store: ");
        String bookClubName = scan.nextLine();

        BookClub bookClub = new BookClub(bookClubName, "Edinburgh, Scotland", book, volunteer);

        log.info(
                "Welcome to "
                        + bookClub.getBookClubName()
                        + ". You will be taken care of by "
                        + bookClub.getVolunteer().getVolunteerName()
                        + ". \nYour last book was edition "
                        + updatedSeries.getEdition()
                        + " of "
                        + updatedSeries.getBook().getBookName()
                        + " series. \nNext, we recommend "
                        + bookClub.getBook().getBookName()
                        + " by "
                        + bookClub.getBook().getAuthor().getAuthorName());
    }
}
