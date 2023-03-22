package br.edu.fatec.projectsmartrow.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ConverterEmString2 {

	public static String converterEmString() throws FileNotFoundException {
		FileReader fr = new FileReader("sqlestruturabd.sql");
		
		try {
			BufferedReader arq = new BufferedReader(fr);
			String line = null;
			StringBuffer strb = new StringBuffer();
			while ((line = arq.readLine()) != null) {
				strb.append(line);
//				strb.append("\n");
			}
			String sql = strb.toString();
			return sql;

		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

}
