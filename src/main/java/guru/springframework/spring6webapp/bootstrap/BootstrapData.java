package guru.springframework.spring6webapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import guru.springframework.spring6webapp.repositories.PublisherRepository;


// Any class that implements the CommandLineRunner will be executed by spring boot start up.
@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;


    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        
        Author eric = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Whatever");
    
        Book ddd = new Book();
        ddd.setTitle("Design Driver Domain");
        ddd.setIsbn("WhateverIsbn");

        Author savedEric = authorRepository.save(eric);
        Book savedDDD = bookRepository.save(ddd);
        
        Author rod = new Author();
        rod.setFirstName("Rod");
        rod.setLastName("Johnson");
    
        Book noEJB = new Book();
        noEJB.setTitle("J2EE Development without EJB");
        noEJB.setIsbn("WhateverIsbn");

        Author savedRod = authorRepository.save(rod);
        Book savedNoEJB = bookRepository.save(noEJB);

        savedEric.getBooks().add(savedDDD);
        savedRod.getBooks().add(savedNoEJB);

        System.out.println("In bootstrap:");
        System.out.println("Author Count: " + authorRepository.count() );
        System.out.println("Book Count: " + bookRepository.count() );

        Publisher publisher = new Publisher();
        publisher.setPublisherName("This is a publisher test name");
        publisher.setAddress("False street 123");
        publisher.setCity("Cdmx");
        publisher.setState("Mexico State");
        publisher.setZip("05348");

        Publisher savedPublisher = publisherRepository.save(publisher);

        System.out.println("Saving publisher");
        System.out.println("Publisher count:  " + publisherRepository.count());

    }
}
