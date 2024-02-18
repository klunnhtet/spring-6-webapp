package guru.springframework.spring6webapp.bookstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import guru.springframework.spring6webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BookstrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;
    public BookstrapData(AuthorRepository authorRepository, BookRepository bookRepository,PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository=publisherRepository;
    }

    @Override
    public void run(String...args) throws Exception{
        Author eric=new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Evans");

        Book ddd=new Book();
        ddd.setTitle("Domain Driven Desing");
        ddd.setIbsn("123456");

        Author ericSaved=authorRepository.save(eric);
        Book dddSaved=bookRepository.save(ddd);

        Author rod=new Author();
        rod.setFirstName("Rod");
        rod.setLastName("JohnSon");

        Book noEJB=new Book();
        noEJB.setTitle("J2EE Development without EJB");
        noEJB.setIbsn("54757585");

        Author rodSaved=authorRepository.save(rod);
        Book noEJBSaved=bookRepository.save(noEJB);

        ericSaved.getBooks().add(dddSaved);
        rodSaved.getBooks().add(noEJBSaved);
        dddSaved.getAuthors().add(ericSaved);
        noEJBSaved.getAuthors().add(rodSaved);



        Publisher publisher=new Publisher();
        publisher.setPublisherName("My publisher");
        publisher.setAddress("123 Main");
        Publisher savedPublisher = publisherRepository.save(publisher);

        dddSaved.setPublisher(savedPublisher);
        noEJBSaved.setPublisher(savedPublisher);

        authorRepository.save(ericSaved);
        authorRepository.save(rodSaved);
        bookRepository.save(dddSaved);
        bookRepository.save(noEJBSaved);

        System.out.println("In Bootstrap");
        System.out.println("Author Count : " +authorRepository.count());
        System.out.println("Book Count : "+bookRepository.count() );
        System.out.println("Publisher Count : "+publisherRepository.count() );
    }
}
