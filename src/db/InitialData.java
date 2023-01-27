package db;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import repository.UserLoginRepository;
import repository.*;

public class InitialData {
	public InitialData() {
		try {
			GoCSV goCSV = new GoCSV("/Users/Laon/Downloads/kdrama 2.csv");
			String[] line = null;
			int counter = 0;

			DramaRepository dramaRepository = new DramaRepository();
			dramaRepository.createDramaTable();
			UserRepository userRepository = new UserRepository();
			userRepository.createUserTable();
			UserLoginRepository userLoginRepository = new UserLoginRepository();
			userLoginRepository.createUserLoginTable();
			
			
			while ((line = goCSV.nextRead()) != null) {
				if (counter < 1) {
					counter++;
					continue;
				} else {
					List<String> list = Arrays.asList(line);
					dramaRepository.insertTable(list);
					
				}
			
			}
			dramaRepository.updateOriginalNetwork();
			PlatformRepository platform = new PlatformRepository();
			platform.createPlatformTable();
			platform.insertPlatform();
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
