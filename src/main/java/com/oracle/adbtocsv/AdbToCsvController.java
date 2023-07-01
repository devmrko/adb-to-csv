package com.oracle.adbtocsv;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.adbtocsv.mapper.AdbtocsvMapper;

@RestController
public class AdbToCsvController {

	private static final Logger logger = LoggerFactory.getLogger(AdbToCsvController.class);

	@Value("${filePath}")
	String filePath;
	
	@Autowired
	AdbtocsvMapper adbtocsvMapper;

	@GetMapping("/getLivelabs")
	public String getLivelabs() {

		List<HashMap<String, Object>> resultList = adbtocsvMapper.getLivelabs();
		HashMap<String, Object> result;
		
		LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
        String formattedDateTime = currentDateTime.format(formatter);

		
		try (FileWriter fileWriter = new FileWriter(filePath + formattedDateTime + ".csv")) {
			for (int i = 0; i < resultList.size(); i++) {
				result = resultList.get(i);
	            String line = result.get("ID") + "," + result.get("TITLE") + "," + result.get("URL") + "\n";
	            fileWriter.write(line);
			}
			logger.debug("##### ##### ##### ", formattedDateTime + " - success");
			return formattedDateTime + " - success";
		} catch (IOException e) {
			logger.error(e.getLocalizedMessage());
			e.printStackTrace();
			return formattedDateTime + " - failed";
		}
		
	}

}
