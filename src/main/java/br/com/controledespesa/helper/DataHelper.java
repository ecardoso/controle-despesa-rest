package br.com.controledespesa.helper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;

public class DataHelper {

	private static final String SDF_DMY = "dd/MM/yyyy";

	private DataHelper() {

	}

	public static LocalDateTime getPrimeiroDiaDoMes(LocalDateTime data) {
		LocalDate lData = LocalDate.of(data.getYear(), data.getMonthValue(), 1);
		LocalTime time = LocalTime.of(0, 0, 0);
		return LocalDateTime.of(lData, time);
	}

	public static LocalDateTime getUltimoDiaDoMes(LocalDateTime data) {
		LocalDate lData = LocalDate.of(data.getYear(), data.getMonthValue(), data.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth());
		LocalTime time = LocalTime.of(23, 59, 59);
		return LocalDateTime.of(lData, time);
	}

	public static Date addMesByDia(int dia, int mes, int quantidade) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.DAY_OF_MONTH, dia);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		calendar.set(Calendar.MONTH, mes);
		calendar.add(Calendar.MONTH, quantidade);
		return calendar.getTime();
	}

	public static LocalDateTime converterStringParaDate(String data) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(SDF_DMY);
		LocalDate ld = LocalDate.parse(data, dtf);
		return LocalDateTime.of(ld, LocalTime.of(0, 0, 0));
	}

	public static boolean dataEntreMelhorCompra(LocalDateTime dataInicial, LocalDateTime dataFinal) {
		return dataInicial.isAfter(dataFinal);
	}

	public static LocalDateTime setHora(LocalDateTime data, int hora, int minuto, int segundo) {
		LocalDate lData = LocalDate.of(data.getYear(), data.getMonthValue(), data.getDayOfMonth());
		LocalTime time = LocalTime.of(hora, minuto, segundo);
		return LocalDateTime.of(lData, time);
	}

}
