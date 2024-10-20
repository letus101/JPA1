package org.example.jpa1;

import org.example.jpa1.entities.Product;
import org.example.jpa1.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Jpa1Application implements CommandLineRunner {
    @Autowired
    private ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(Jpa1Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //ajouter des produits
        productRepository.save(new Product(null,"TV", 1000.0, 10));
        productRepository.save(new Product(null,"Mouse", 50.0, 100));
        productRepository.save(new Product(null,"PC", 3000.0, 20));
        //afficher tous les produits
        List<Product> products = productRepository.findAll();
        products.forEach(System.out::println);
        //afficher un produit par id
        Product p = productRepository.findById(1L).get();
        System.out.println(p.getId() + " " + p.getName() + " " + p.getPrice() + " " + p.getQuantity());
        //metre à jour un produit
        Product p2 = productRepository.findById(1L).get();
        p2.setPrice(2000.0);
        productRepository.save(p2);
        System.out.println("Produit modifié : " + p2.getId() + " " + p2.getName() + " " + p2.getPrice() + " " + p2.getQuantity());
        //supprimer un produit
        productRepository.deleteById(2L);
        System.out.println("Produit avec id=2 supprimé");
        //afficher tous les produits
        List<Product> products2 = productRepository.findAll();
        products2.forEach(System.out::println);

    }
}
