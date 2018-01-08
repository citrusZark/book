package org.test.bookpubstarter.dbcount;

import java.util.Collection;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;

@Configuration
public class DbCountAutoConfiguration {
	@Bean
	@ConditionalOnMissingBean
	public DbCountRunner dbCountRunner(Collection<CrudRepository> repositories) {
		return new DbCountRunner(repositories);
	}
}
