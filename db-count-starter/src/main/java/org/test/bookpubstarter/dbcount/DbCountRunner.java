package org.test.bookpubstarter.dbcount;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.repository.CrudRepository;

public class DbCountRunner implements CommandLineRunner {

	protected final Log logger = LogFactory.getLog(getClass());
	private Collection<CrudRepository> repositories;

	public DbCountRunner(Collection<CrudRepository> repositories) {
		super();
		this.repositories = repositories;
	}

	@Override
	public void run(String... args) throws Exception {
		repositories.forEach(crudRepository -> logger.info(String.format("%s has %s entries",
				getRepositoryName(crudRepository.getClass()), crudRepository.count())));
	}

	private static String getRepositoryName(Class crudRepositoryClass) {
		for (Class repositoryInterface : crudRepositoryClass.getInterfaces()) {
			if (repositoryInterface.getName().startsWith("org.test.bookpub.repository")) {
				return repositoryInterface.getSimpleName();
			}
		}
		return "Unknown Repository";
	}

}
