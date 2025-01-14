package com.SpringBootMongoDB.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.SpringBootMongoDB.domain.Post;
import com.SpringBootMongoDB.domain.User;
import com.SpringBootMongoDB.dto.AuthorDTO;
import com.SpringBootMongoDB.dto.CommentDTO;
import com.SpringBootMongoDB.repositories.PostRepository;
import com.SpringBootMongoDB.repositories.UserRepository;

@Configuration
public class TestConfig implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PostRepository postRepository;
	

	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown","maria@gmai.com");
		User alex = new User(null, "Alex Green","alex@gmail.com");
		User bob = new User(null,"Bob Grey","bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex,bob));
		
		Post post1 = new Post(null,sdf.parse("21/03/2018"),"Partiu Viagem","Vou Viajar para Sao Paulo. Abraços",new AuthorDTO(maria));
		Post post2 = new Post(null,sdf.parse("23/03/2018"),"Bom dia","Acordei Feliz Hoje !",new AuthorDTO(maria));
		
		CommentDTO c1 = new CommentDTO("Boa Viagem Mano",sdf.parse("21/03/2018"),new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Aproveite",sdf.parse("22/03/2018"),new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("Tenha um Otimo dia",sdf.parse("23/03/2018"),new AuthorDTO(alex));
		
		post1.getComments().addAll(Arrays.asList(c1,c2));
		post2.getComments().add(c3);
		
		postRepository.saveAll(Arrays.asList(post1,post2));
		
		maria.getPosts().add(post2);
		maria.getPosts().add(post1);
		
		userRepository.saveAll(Arrays.asList(maria));
	

		
		
	}

}
