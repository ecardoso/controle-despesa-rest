package br.com.controledespesa.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DataHelper {

	public static final SimpleDateFormat SDF_DMY = new SimpleDateFormat("dd/MM/yyyy");

	public static Date getDataByDia(int dia) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.DAY_OF_MONTH, dia);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		return calendar.getTime();
	}

	public static Date getPrimeiroDiaDoMes(Date data) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		return calendar.getTime();
	}

	public static Date getUltimoDiaDoMes(Date data) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);

		return calendar.getTime();
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

	public static Date converterStringParaDate(String data) throws ParseException {
		return SDF_DMY.parse(data);
	}

	public static boolean dataEntreMelhorCompra(Date dataInicial, Date dataFinal) {
		return dataInicial.after(dataFinal);
	}

}
