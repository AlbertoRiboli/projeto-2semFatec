package br.edu.fateccotia.projectsmartrow.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class ConversorData {

	public static String ConverterDataSp(Instant iso8601) {

		ZoneId fusoSp = ZoneId.of("America/Sao_Paulo");
		LocalDateTime dataHoraSp = LocalDateTime.ofInstant(iso8601, fusoSp);

		DateTimeFormatter formatoSp = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

		String dataFormatada = dataHoraSp.format(formatoSp);

		return dataFormatada;

	}
}
